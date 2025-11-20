# 🗂️ **DeOlho NoLixo – Documentação **Beta** do Banco de Dados (Firebase Firestore)**

<img width="100%" alt="Diagrama do Banco de Dados" src="https://github.com/user-attachments/assets/926c64b9-2334-4915-8778-fa02d4e6c444" />

---

## 📌 Sumário

* [1. Visão Geral](#1-visão-geral)
* [2. Arquitetura do Sistema](#2-arquitetura-do-sistema)
* [3. Estrutura do Banco de Dados (Schema)](#3-estrutura-do-banco-de-dados-firestore)
    * [3.1 Users](#31-coleção-users)
    * [3.2 Reports](#32-coleção-reports)
    * [3.3 Interactions (Comments/Likes)](#33-coleção-comments)
    * [3.4 System (Notifications/Config)](#35-coleção-notifications)
    * [3.5 Agencies](#36-coleção-agencies)
* [4. Índices Otimizados](#4-índices-otimizados)
* [5. Regras de Segurança (Security Rules)](#5-regras-de-segurança-do-firestore)
* [6. Fluxo de Dados](#6-fluxo-de-dados)
* [7. Justificativa Técnica](#7-justificativa-técnica--por-que-firestore)
* [8. Checklist](#8-checklist-de-implementação)

---

# 1. Visão Geral

O **DeOlho NoLixo** é uma aplicação mobile que permite aos cidadãos denunciar pontos de descarte irregular de resíduos.
O sistema integra:

* **Firebase Authentication**: Gestão de identidade.
* **Firestore (NoSQL)**: Banco de dados principal.
* **Firebase Storage**: Armazenamento de mídias (fotos).
* **IA**: Algoritmos para classificação automática do lixo.
* **Geolocalização**: Uso de Geohash + Lat/Lng.

---

# 2. Arquitetura do Sistema

```mermaid
graph TD
    App[App Mobile React Native] --> Auth[Firebase Auth]
    App --> Firestore[Firestore DB]
    App --> Storage[Firebase Storage]
    Firestore <--> Rules[Regras de Segurança]
    Firestore --> Functions[Cloud Functions]
    Functions --> AI[IA Classificadora]
    Functions --> Email[Envio para Agências]
````

-----

# 3\. Estrutura do Banco de Dados (Firestore)

A modelagem segue o padrão **NoSQL Orientado a Documentos**. As chaves estão em inglês (padrão de código), mas as descrições de valores suportam português.

## 3.1 Coleção: `users`

Armazena perfis de cidadãos, gestores e admins.

```json
{
  "uid": "string (unique auth id)",
  "name": "string",
  "email": "string",
  "city": "string",
  "photoUrl": "string | null",
  "role": "citizen | manager | admin",

  "preferences": {
    "notifications": {
      "statusChange": true,
      "marketing": false,
      "social": true
    },
    "permissions": {
      "locationAllowed": true
    }
  },

  "stats": {
    "reportsCount": 0
  },

  "createdAt": "timestamp",
  "updatedAt": "timestamp",
  "lastLoginAt": "timestamp",
  "isActive": true
}
```

## 3.2 Coleção: `reports`

Coleção principal. Cada documento é uma denúncia.

```json
{
  "userId": "users/{uid}",

  "status": "NEW | CONFIRMED | ANALYSIS | RESOLVED",
  "category": "domestic | debris | industrial | organic | other",
  "description": "string",

  "createdAt": "timestamp",
  "updatedAt": "timestamp",

  "location": {
    "lat": 0.0,
    "lng": 0.0,
    "geohash": "string",
    "address": "string | null"
  },

  "mainImageUrl": "string (URL)",
  "photoUrls": ["string (URL)"],

  "aiAnalysis": {
    "inferredType": "string",
    "environmentalImpact": "non-existent | low | medium | high",
    "confidenceScore": 0.0,
    "modelVersion": "string",
    "analyzedAt": "timestamp"
  },

  "metrics": {
    "likeCount": 0,
    "commentCount": 0,
    "shareCount": 0
  },

  "forwardedTo": ["agency:1"],

  "lastStatusChangeAt": "timestamp",

  "statusHistory": [
    {
      "status": "string",
      "changedBy": "user:1 | system",
      "changedAt": "timestamp",
      "reason": "string | null"
    }
  ]
}
```

## 3.3 Coleção: `comments` & `likes`

**comments**

```json
{
  "reportId": "reports/{id}",
  "userId": "users/{uid}",
  "text": "string",
  "createdAt": "timestamp",
  "updatedAt": "timestamp"
}
```

**likes**

```json
{
  "reportId": "reports/{id}",
  "userId": "users/{uid}",
  "createdAt": "timestamp"
}
```

## 3.5 Coleção: `notifications`

```json
{
  "userId": "users/{uid}",
  "type": "STATUS_CHANGE | NEW_COMMENT | NEW_LIKE | SYSTEM_MSG",
  "title": "string",
  "body": "string",
  "targetId": "reports/{id} | null",
  "isRead": false,
  "createdAt": "timestamp",

  "metadata": {
    "oldStatus": "string | null",
    "newStatus": "string | null"
  }
}
```

## 3.6 Coleção: `agencies` & `agencyReports`

**agencies**

```json
{
  "name": "string",
  "email": "string",
  "phone": "string | null",
  "coverageAreas": [
    {
      "type": "GEOJSON | custom",
      "value": "string"
    }
  ],
  "servedCategories": ["string"],
  "isActive": true,
  "createdAt": "timestamp",
  "stats": { "reportsForwarded": 0 }
}
```

**agencyReports** (Log de envio)

```json
{
  "agencyId": "agency:1",
  "reportId": "reports/{id}",
  "forwardedAt": "timestamp",
  "forwardStatus": "SUCCESS | FAILED | PENDING",
  "emailSent": true,
  "lastAgencyFeedback": "string | null"
}
```

## 3.8 Coleção: `feedConfig` (Remote Config)

```json
{
  "mainImageField": "mainImageUrl",
  "enabledFilters": {
    "category": true,
    "status": true,
    "distance": true
  }
}
```

-----

# 4\. Índices Otimizados

Configurações JSON para colar no `firestore.indexes.json` ou configurar no console.

### **Index 1 – Feed Principal**

*Objetivo:* Listar denúncias ordenadas por data, permitindo filtros de categoria e status.

```json
{
  "collection": "reports",
  "fields": [
    {"field": "status", "mode": "ASC"},
    {"field": "category", "mode": "ASC"},
    {"field": "createdAt", "mode": "DESC"}
  ]
}
```

### **Index 2 – GeoQuery**

*Objetivo:* Buscar denúncias próximas baseadas no GeoHash.

```json
{
  "collection": "reports",
  "fields": [
    {"field": "location.geohash", "mode": "ASC"},
    {"field": "status", "mode": "ASC"}
  ]
}
```

### **Index 3 – Perfil do Usuário**

*Objetivo:* Mostrar histórico de denúncias de um usuário específico.

```json
{
  "collection": "reports",
  "fields": [
    {"field": "userId", "mode": "ASC"},
    {"field": "createdAt", "mode": "DESC"}
  ]
}
```

### **Index 4 – Notificações**

```json
{
  "collection": "notifications",
  "fields": [
    {"field": "userId", "mode": "ASC"},
    {"field": "createdAt", "mode": "DESC"}
  ]
}
```

-----

# 5\. Regras de Segurança do Firestore

Copie este bloco integralmente para a aba **Rules** do Firebase Console.

```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    
    // --- Funções Auxiliares ---
    function isSignedIn() {
      return request.auth != null;
    }
    function isAdmin() {
      return isSignedIn() && request.auth.token.role == 'admin';
    }
    function isOwner(userId) {
      return isSignedIn() && request.auth.uid == userId;
    }
    function isValidString(text, maxLen) {
      return text is string && text.size() > 0 && text.size() <= maxLen;
    }

    // --- Regras por Coleção ---

    // 1. Usuários
    match /users/{userId} {
      allow read: if isOwner(userId) || isAdmin();
      allow create: if isSignedIn() && request.auth.uid == userId;
      allow update: if isOwner(userId) || isAdmin();
      allow delete: if false;
    }

    // 2. Denúncias (Reports)
    match /reports/{reportId} {
      allow read: if true; // Público
      allow create: if isSignedIn()
                    // Validação de Schema Obrigatório
                    && request.resource.data.location.lat is number
                    && request.resource.data.location.lng is number
                    && request.resource.data.location.geohash is string
                    && request.resource.data.photoUrls is list
                    && request.resource.data.photoUrls.size() > 0
                    && request.resource.data.photoUrls.size() <= 5
                    && isValidString(request.resource.data.description, 2000)
                    && request.resource.data.userId == request.auth.uid;
      allow update: if isOwner(resource.data.userId) || isAdmin();
      allow delete: if isAdmin();
    }

    // 3. Comentários
    match /comments/{commentId} {
      allow read: if true;
      allow create: if isSignedIn()
                    && request.resource.data.userId == request.auth.uid
                    && isValidString(request.resource.data.text, 500);
      allow update, delete: if isOwner(resource.data.userId) || isAdmin();
    }

    // 4. Likes
    match /likes/{likeId} {
      allow read: if true;
      allow create: if isSignedIn() && request.resource.data.userId == request.auth.uid;
      allow delete: if isOwner(resource.data.userId) || isAdmin();
      allow update: if false; // Likes não se editam
    }

    // 5. Notificações
    match /notifications/{notificationId} {
      allow read: if isSignedIn() && resource.data.userId == request.auth.uid;
      allow create, update, delete: if isAdmin(); // Apenas sistema cria notificações
    }

    // 6. Agências
    match /agencies/{agencyId} {
      allow read: if true;
      allow write: if isAdmin();
    }

    // 7. Relatórios de Agências
    match /agencyReports/{arId} {
      allow read: if isAdmin() || (isSignedIn() && request.auth.uid == resource.data.agencyId);
      allow write: if isAdmin();
    }

    // 8. Configuração Remota
    match /feedConfig/{docId} {
      allow read: if true;
      allow write: if isAdmin();
    }
  }
}
```

-----

# 6\. Fluxo de Dados

1.  **Captura:** Usuário abre o app, tira foto e preenche descrição.
2.  **Upload:** Imagens vão para o Firebase Storage; URL é gerada.
3.  **Persistência:** Documento JSON criado em `/reports` com GeoHash calculado.
4.  **Processamento (Backend):** Trigger do Firestore ativa Cloud Function para analisar imagem via IA.
5.  **Atualização:** Documento é atualizado com `aiAnalysis`.
6.  **Distribuição:** Se confirmado risco alto, e-mail é disparado para Agência cadastrada.

-----

# 7\. Justificativa Técnica – Por que Firestore?

  * **Requisito NoSQL:** Atende à demanda acadêmica e técnica por flexibilidade de schema.
  * **Tempo Real:** Sincronização nativa ideal para feeds de redes sociais.
  * **Escalabilidade:** Modelo Serverless que suporta picos de acesso sem configuração de servidor.
  * **GeoQueries:** Suporte eficiente para consultas por proximidade (necessário para mapas).

-----

# 8\. Checklist de Implementação

| Funcionalidade | Coleção Principal | Status |
| :--- | :--- | :--- |
| Login / Auth | `users` | ✅ Concluído |
| Criar Denúncia | `reports` | ⚙️ Em Desenv. |
| Upload Imagens | `storage` | ⚙️ Em Desenv. |
| Feed Principal | `reports` | ⚙️ Em Desenv. |
| IA Classificadora | `aiAnalysis` | ⚙️ Em Desenv. |
| Curtir / Comentar | `likes` / `comments` | ⚙️ Em Desenv. |
| Integração Agências | `agencyReports` | ⚙️ Em Desenv. |
| Notificações | `notifications` | ⚙️ Em Desenv. |
| Regras de Segurança | `firestore.rules` | 🔐 Aplicado |

```
