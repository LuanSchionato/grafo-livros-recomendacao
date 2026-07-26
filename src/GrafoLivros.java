import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class GrafoLivros {

    // cada livro aponta pra um conjunto de livros recomendados
    private HashMap<Livro, Set<Livro>> grafo;

    public GrafoLivros() {
        grafo = new HashMap<>();
    }

    public void adicionarLivro(Livro livro) {
        if (!grafo.containsKey(livro)) {
            grafo.put(livro, new HashSet<>());
        }
    }

    public void adicionarRelacao(Livro livro1, Livro livro2) {
        // garante que os dois livros existem no grafo antes de conectar
        adicionarLivro(livro1);
        adicionarLivro(livro2);

        grafo.get(livro1).add(livro2);
        grafo.get(livro2).add(livro1);
    }

    public Set<Livro> recomendar(Livro livroLido) {
        if (!grafo.containsKey(livroLido)) {
            System.out.println("Livro nao encontrado no grafo.");
            return new HashSet<>();
        }
        return grafo.get(livroLido);
    }

    // mostra todos os livros e suas conexoes
    public void mostrarGrafo() {
        for (Livro livro : grafo.keySet()) {
            System.out.println("\n" + livro.getTitulo() + ":");
            Set<Livro> recomendados = grafo.get(livro);
            for (Livro rec : recomendados) {
                System.out.println("  -> " + rec.getTitulo());
            }
        }
    }

    public static Map<Livro, Integer> djikstraSimples(HashMap<Livro, Set<Livro>> grafo, Livro origem) {
        Map<Livro, Integer> distancias = new HashMap<>();
        Queue<Livro> fila = new LinkedList<>();

        distancias.put(origem, 0); // como não temos pesos entre os nós, o peso padrão é 0
        fila.add(origem);

        while (!fila.isEmpty()) {
            Livro atual = fila.poll();
            int distanciaAtual = distancias.get(atual);

            for (Livro vizinho : grafo.getOrDefault(atual, new HashSet<>())) {
                if (!distancias.containsKey(vizinho)) {
                    distancias.put(vizinho, distanciaAtual + 1);
                    fila.add(vizinho);
                }
            }
        }
        return distancias;
    }

    // metodo auxiliar para expor o grafo interno ao djikstraSimples
    public HashMap<Livro, Set<Livro>> getGrafo() {
        return grafo;
    }
}
