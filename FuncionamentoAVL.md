## Árvore AVL no Projeto de Catálogo de Produtos

Esta documentação explica como a estrutura de dados Árvore AVL (Adelson-Velsky e Landis) é utilizada neste projeto para gerenciar o catálogo de produtos de forma eficiente e balanceada.

---

### 1. Objetivo da AVL no Projeto

A AVL garante que operações de inserção, busca, atualização e remoção de produtos mantenham sempre complexidade O(log n), independentemente da ordem em que os produtos são adicionados. Isso preserva performance mesmo em catálogos com muitos itens.

---

### 2. O que Cada Nó Armazena

Cada nó (`No`) contém:

- `chave`: código numérico único do produto (int) – usado como critério de ordenação.
- `valor`: instância de `Produto` (nome, preço, quantidade, categoria).
- `esquerda` / `direita`: referências para nós filhos.
- `altura`: usada para cálculo de balanceamento.

Arquivo principal da estrutura: `backend/arvore_avl.py`

---

### 3. Operações Principais

#### Inserção (`inserir_chave`)

1. Percorre recursivamente até posição de inserção.
2. Atualiza altura do nó.
3. Calcula fator de balanceamento: `altura(esquerda) - altura(direita)`.
4. Se desbalanceado aplica uma das rotações:
   - Caso Esquerda-Esquerda: rotação à direita.
   - Caso Direita-Direita: rotação à esquerda.
   - Caso Esquerda-Direita: rotação à esquerda no filho + direita na raiz.
   - Caso Direita-Esquerda: rotação à direita no filho + esquerda na raiz.

#### Remoção (`remover_chave`)

1. Localiza o nó pela chave.
2. Caso tenha dois filhos: substitui pelo sucessor in-ordem (menor da subárvore direita).
3. Atualiza alturas na subida da recursão.
4. Rebalanceia aplicando rotações conforme necessário.

#### Busca (`buscar`)

Percurso binário comparando a chave buscada com as chaves dos nós: segue para esquerda se menor, direita se maior. O(log n).

#### Percurso Em Ordem (`percorrer_em_ordem` / usado indiretamente)

Utilizado para listar produtos ordenados por código: esquerda → raiz → direita.

#### Geração de Diagrama (`gerar_mermaid`)

Cria string Mermaid para visualização hierárquica dos nós com: nome (limitado), preço e quantidade.

---

### 4. Integração com Camada de Catálogo

O arquivo `backend/catalogo_produtos_avl.py` encapsula a AVL fornecendo operações de alto nível:

- `adicionar_produto(produto)` → converte produto para nó (chave = `produto.codigo`).
- `remover_produto(codigo)` → remove nó correspondente.
- `buscar_produto(codigo)` → retorna produto ou `None`.
- `listar_produtos()` → monta lista ordenada de dicionários para resposta da API.
- `para_mermaid()` → expõe diagrama para o frontend.

---

### 5. Exposição via API (FastAPI)

No arquivo `backend/app.py`:

- POST `/produtos` → chama `catalogo.adicionar_produto`.
- GET `/produtos/{codigo}` → chama `catalogo.buscar_produto`.
- DELETE `/produtos/{codigo}` → chama `catalogo.remover_produto`.
- PUT `/produtos/{codigo}` → remove e re-insere (mantém balanceamento).
- GET `/tree/visualize` ou `/arvore/avl` → diagrama Mermaid da estrutura.
- GET `/estatisticas` → altura atual e total de produtos.

---

### 6. Exemplo de Inserções

Inserindo produtos com códigos: 100, 50, 150, 25, 75, 125, 175
Resultado balanceado:

```
        100
       /   \
     50     150
    /  \    /  \
   25  75 125 175
```

Altura = 3. Todas as operações de busca por código seguem no máximo três comparações.

---

### 7. Complexidades

- Inserção: O(log n)
- Remoção: O(log n)
- Busca: O(log n)
- Listagem completa (percurso em ordem): O(n)

O balanceamento automático evita crescimento degenerado (como ocorreria numa árvore binária simples em ordem crescente).

---

### 8. Visualização Mermaid

Exemplo simplificado de saída (`/tree/visualize`):

```
graph TD;
Node100["Notebook<br/>R$ 3500.00<br/>Qtd: 10"] --> Node50
Node100 --> Node150
style Node100 fill:#60a5fa,stroke:#2563eb,stroke-width:2px,color:#fff
...
```

O frontend interpreta essa string para desenhar o gráfico interativo.

---

### 9. Possíveis Extensões Futuras

- Persistência em disco (salvar/recuperar AVL entre reinícios).
- Estatísticas adicionais: profundidade média, fator de preenchimento.
- Rotas para limpeza completa (`reset`) da árvore.
- Suporte a atualização de chave sem remover/reinserir.

---

### 10. Benefícios no Contexto do Projeto

| Benefício                   | Impacto                                        |
| ---------------------------- | ---------------------------------------------- |
| Ordenação automática      | Lista de produtos sempre por código           |
| Performance previsível      | Evita pior caso de árvore degenerada          |
| Visualização clara         | Facilita entendimento didático da estrutura   |
| Simplicidade de integração | API chama métodos de alto nível do catálogo |

---

### 11. Código Essencial (Trechos)

Inserção com rebalanceamento (resumo):

```python
def inserir(self, no, chave, valor=None):
    if not no:
        return No(chave, valor)
    elif chave < no.chave:
        no.esquerda = self.inserir(no.esquerda, chave, valor)
    else:
        no.direita = self.inserir(no.direita, chave, valor)

    no.altura = 1 + max(self.obter_altura(no.esquerda), self.obter_altura(no.direita))
    balanceamento = self.obter_balanceamento(no)
    # Casos de rotação ...
    return no
```

Remoção (conceito chave): substituição pelo sucessor in-ordem quando há dois filhos.

---

### 12. Como Reiniciar a AVL (Limpar Produtos)

Enquanto não há persistência, reiniciar o processo do backend limpa todos os produtos:

```powershell
Stop-Process -Name "python" -Force -ErrorAction SilentlyContinue
cd backend
uvicorn app:app --reload --host 0.0.0.0 --port 8000
```

---

### 13. Perguntas Frequentes (FAQ)

**Por que remover e reinserir para atualizar?**  Simplifica a lógica garantindo que balanceamento e chave permaneçam consistentes.

**Por que usar código como chave?**  É um identificador único e facilita ordenação crescente dos produtos.

**O que acontece se inserir códigos em ordem crescente?**  A AVL se rebalanceia aplicando rotações, evitando altura O(n).

---

### 14. Referências

- AVL original: G. M. Adelson-Velsky, E. M. Landis (1962)
- FastAPI: https://fastapi.tiangolo.com/
- Mermaid: https://mermaid.js.org/

---

Documento gerado automaticamente para suporte ao entendimento da estrutura AVL utilizada neste projeto.
