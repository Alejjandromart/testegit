# Como a AVL funciona neste projeto 🌳

A Árvore AVL é a estrutura responsável por manter o catálogo de produtos sempre ordenado, eficiente e balanceado, garantindo desempenho consistente mesmo com milhares de itens.

## 📊 **Estrutura Principal**

A AVL armazena os produtos de forma **ordenada e balanceada** usando o **código do produto** como chave de ordenação.

``` Python
# Cada nó armazena:
- chave: código do produto (int) - usado para ordenação
- valor: objeto Produto completo (nome, preço, quantidade, etc.)
- esquerda/direita: referências para filhos
- altura: para manter balanceamento
```

## 🔑 **Operações Principais**
### **1. Inserção**

```Python
# Exemplo de uso:
produto = Produto(codigo=150, nome="Notebook", preco=3500.0, quantidade=10)
arvore.inserir_chave(chave=150, valor=produto)
```

**Processo:**

1. Insere recursivamente mantendo ordem BST (código menor → esquerda, maior → direita)
2. Calcula o fator de balanceamento
3. Se desbalanceado (|balanceamento| > 1), aplica rotações:
    - **Esquerda-Esquerda**: rotação direita simples
    - **Direita-Direita**: rotação esquerda simples
    - **Esquerda-Direita**: rotação esquerda + direita (dupla)
    - **Direita-Esquerda**: rotação direita + esquerda (dupla)

**Complexidade:** [O(log n)] - sempre balanceada!
```Python
## Busca por código do produto

no = arvore.buscar(arvore.raiz, chave=150)

produto = no.valor if no else None
```

**2. Buscar**

```Python
## Busca por código do produto

no = arvore.buscar(arvore.raiz, chave=150)

produto = no.valor if no else None
```
**Complexidade:** [O(log n)] - navegação binária otimizada

### **3. Remoção**
```Python
##arvore.remover_chave(chave=150)
```
**Processo:**

1. Remove o nó encontrado
2. Se tem 2 filhos: substitui pelo sucessor in-ordem (menor nó da direita)
3. Rebalanceia a árvore subindo recursivamente

**Complexidade:** [O(log n)](vscode-file://vscode-app/c:/Users/Alejj/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-browser/workbench/workbench.html)

## 🎯 **Por que AVL neste projeto?**

|Vantagem|Impacto|
|---|---|
|**Busca rápida**|Encontra produtos por código em O(log n)|
|**Sempre balanceada**|Garante performance mesmo com muitos produtos|
|**Ordenação automática**|Produtos ficam ordenados por código|
|**Visualização clara**|Método [gerar_mermaid()](vscode-file://vscode-app/c:/Users/Alejj/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-browser/workbench/workbench.html) mostra a estrutura|

## 📈 **Exemplo Prático**

```txt
        100
       /   \
     50     150
    /  \    /  \
   25  75 125  175
```



**Altura:** 3 (balanceada!)  
**Busca pelo produto 175:** apenas 3 comparações (100 → 150 → 175)

## 🔄 **Integração com o Backend**

No arquivo `app.py`, a AVL é usada para:

```Python
# Inicialização
arvore = ArvoreAVL()

# Adicionar produto
@app.post("/produtos")
def adicionar_produto(produto: Produto):
    arvore.inserir_chave(produto.codigo, produto)

# Buscar produto
@app.get("/produtos/{codigo}")
def obter_produto(codigo: int):
    no = arvore.buscar(arvore.raiz, codigo)
    return no.valor if no else None

# Listar todos (em ordem crescente de código)
@app.get("/produtos")
def listar_produtos():
    # Percorre em ordem: esquerda → raiz → direita
```
## 💡 **Visualização Mermaid**

O método [gerar_mermaid()] cria um diagrama mostrando:

- Nome do produto (limitado a 20 chars)
- Preço formatado
- Quantidade em estoque
- Estrutura da árvore com cores

Isso permite **ver visualmente** como os produtos estão organizados na memória!

---

**Resumo:** A AVL garante que operações de busca, inserção e remoção sejam **sempre rápidas** (O(log n)), independente da ordem em que os produtos são adicionados. É como ter um catálogo que se reorganiza automaticamente! 📚✨
