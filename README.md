## Sistema de Recomendação de Livros via Grafo

Recomendação de livros modelada como um grafo não direcionado: cada livro é um nó, e uma aresta liga dois livros quando eles são relacionados (mesmo gênero, mesmo autor, etc). Feito em duas fases, saindo de uma recomendação direta pra um cálculo de distância entre livros no grafo.

---

## Como funciona

O grafo é um `HashMap<Livro, Set<Livro>>`, onde cada livro aponta pro conjunto de livros ligados diretamente a ele.

`Livro` sobrescreve `equals` e `hashCode` com base em título e autor. Sem isso o `HashMap` trataria dois objetos com o mesmo livro como se fossem diferentes, só porque são instâncias distintas.

Na primeira fase, `recomendar(livro)` simplesmente retorna o conjunto de livros direto ligados a ele.

Na segunda fase adicionei `djikstraSimples`, que calcula a distância de um livro até todos os outros alcançáveis no grafo. Como as arestas não têm peso, isso na prática é uma busca em largura (BFS): cada salto soma 1 na distância. Deixei o nome do método como estava no trabalho original, mas o algoritmo em si é BFS, não Dijkstra de verdade (que faz sentido quando as arestas têm pesos diferentes).

---

## Exemplo de uso

```java
GrafoLivros grafo = new GrafoLivros();

Livro l1 = new Livro("O Senhor dos Aneis", "J.R.R. Tolkien", "Fantasia");
Livro l2 = new Livro("O Hobbit", "J.R.R. Tolkien", "Fantasia");

grafo.adicionarRelacao(l1, l2);

Set<Livro> recomendados = grafo.recomendar(l1);
```

Recomendação direta:
```
Voce leu: O Senhor dos Aneis
Recomendacoes:
  O Hobbit
  Harry Potter e a Pedra Filosofal
```

Distância no grafo:
```
Livro de origem: O Senhor dos Aneis
  distancia ate "O Hobbit": 1
  distancia ate "1984": 1
  distancia ate "Duna": 2
```

---

## Tecnologias

- Java
- HashMap, HashSet, Queue
- Busca em largura (BFS)

---

## Estrutura do projeto

```
src/
├── Livro.java          modelo do livro: titulo, autor, genero
├── GrafoLivros.java     estrutura do grafo, recomendacao direta e calculo de distancias
└── Main.java            exemplo de uso com um conjunto de livros de teste
```

---

## Como executar

```bash
javac src/*.java -d out
java -cp out Main
```

---

## Licença

Projeto de portfólio acadêmico. Livre para uso e referência.
