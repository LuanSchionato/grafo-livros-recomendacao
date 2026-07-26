# Sistema de Recomendação de Livros via Grafo

Sistema de recomendação de livros modelado como um grafo não direcionado, onde cada livro é um nó e as arestas representam relações de similaridade (mesmo gênero, mesmo autor, etc). Desenvolvido em duas fases, evoluindo de uma recomendação direta para o cálculo de distância entre livros no grafo.

## Como funciona

- **Estrutura do grafo:** implementada com `HashMap<Livro, Set<Livro>>` — cada livro aponta para o conjunto de livros diretamente relacionados a ele.
- **`Livro`** sobrescreve `equals` e `hashCode` (baseados em título + autor), para que o `HashMap` identifique corretamente livros repetidos.
- **Recomendação direta:** dado um livro lido, retorna o conjunto de livros diretamente conectados a ele no grafo.
- **Cálculo de distância (Fase 2):** implementado um algoritmo de busca em largura (BFS) sobre o grafo — como as arestas não têm peso, a busca em largura já calcula a menor distância (em "saltos") entre um livro de origem e todos os outros livros alcançáveis, permitindo recomendações mais refinadas, priorizando livros mais próximos no grafo de relações.

## Exemplo de uso

```java
GrafoLivros grafo = new GrafoLivros();

Livro l1 = new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", "Fantasia");
Livro l2 = new Livro("O Hobbit", "J.R.R. Tolkien", "Fantasia");

grafo.adicionarRelacao(l1, l2);

Set<Livro> recomendados = grafo.recomendar(l1);
```

Saída (recomendação direta):
```
Você leu: O Senhor dos Anéis
Recomendações:
  O Hobbit
  Harry Potter e a Pedra Filosofal
```

Saída (distância no grafo):
```
Livro de origem: O Senhor dos Anéis
  distância até "O Hobbit": 1
  distância até "1984": 1
  distância até "Duna": 2
```

## Estrutura do repositório

```
├── src/
│   ├── Livro.java          # modelo do livro (título, autor, gênero)
│   ├── GrafoLivros.java    # estrutura do grafo, recomendação direta e cálculo de distâncias
│   └── Main.java           # exemplo de uso com um conjunto de livros de teste
└── README.md
```

## Tecnologias

Java · Estruturas de Dados (HashMap, HashSet, Queue) · Busca em Largura (BFS)

## Como executar

```bash
javac src/*.java -d out
java -cp out Main
```
