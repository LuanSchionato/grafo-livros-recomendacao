import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        // criando os livros
        Livro l1  = new Livro("O Senhor dos Aneis", "J.R.R. Tolkien", "Fantasia");
        Livro l2  = new Livro("O Hobbit", "J.R.R. Tolkien", "Fantasia");
        Livro l3  = new Livro("Harry Potter e a Pedra Filosofal", "J.K. Rowling", "Fantasia");
        Livro l4  = new Livro("As Cronicas de Narnia", "C.S. Lewis", "Fantasia");
        Livro l5  = new Livro("Duna", "Frank Herbert", "Ficcao Cientifica");
        Livro l6  = new Livro("Fundacao", "Isaac Asimov", "Ficcao Cientifica");
        Livro l7  = new Livro("1984", "George Orwell", "Distopia");
        Livro l8  = new Livro("Admiravel Mundo Novo", "Aldous Huxley", "Distopia");
        Livro l9  = new Livro("O Conto da Aia", "Margaret Atwood", "Distopia");
        Livro l10 = new Livro("Jogos Vorazes", "Suzanne Collins", "Distopia");
        Livro l11 = new Livro("Neuromancer", "William Gibson", "Ficcao Cientifica");

        GrafoLivros grafo = new GrafoLivros();

        // fantasia
        grafo.adicionarRelacao(l1, l2);
        grafo.adicionarRelacao(l1, l3);
        grafo.adicionarRelacao(l2, l4);
        grafo.adicionarRelacao(l3, l4);

        // ficcao cientifica
        grafo.adicionarRelacao(l5, l6);
        grafo.adicionarRelacao(l5, l11);
        grafo.adicionarRelacao(l6, l11);

        // distopia
        grafo.adicionarRelacao(l7, l8);
        grafo.adicionarRelacao(l7, l9);
        grafo.adicionarRelacao(l8, l9);
        grafo.adicionarRelacao(l9, l10);
        grafo.adicionarRelacao(l7, l10);

        // algumas conexoes entre generos
        grafo.adicionarRelacao(l7, l5);
        grafo.adicionarRelacao(l1, l7);
        grafo.adicionarRelacao(l10, l3);

        System.out.println("=== grafo completo ===");
        grafo.mostrarGrafo();

        // testando o sistema de recomendacao simples (fase anterior)
        System.out.println("\n=== recomendacoes diretas ===");

        System.out.println("\nVoce leu: " + l1.getTitulo());
        System.out.println("Recomendacoes:");
        Set<Livro> rec1 = grafo.recomendar(l1);
        for (Livro r : rec1) {
            System.out.println("  " + r);
        }

        System.out.println("\nVoce leu: " + l7.getTitulo());
        System.out.println("Recomendacoes:");
        Set<Livro> rec2 = grafo.recomendar(l7);
        for (Livro r : rec2) {
            System.out.println("  " + r);
        }

        System.out.println("\nVoce leu: " + l5.getTitulo());
        System.out.println("Recomendacoes:");
        Set<Livro> rec3 = grafo.recomendar(l5);
        for (Livro r : rec3) {
            System.out.println("  " + r);
        }

        System.out.println("\n=== recomendacoes por distancia (Dijkstra) ===");

        System.out.println("\nLivro de origem: " + l1.getTitulo());
        Map<Livro, Integer> distancias1 = GrafoLivros.djikstraSimples(grafo.getGrafo(), l1);
        for (Map.Entry<Livro, Integer> entrada : distancias1.entrySet()) {
            if (!entrada.getKey().equals(l1)) {
                System.out.println("  distancia ate \"" + entrada.getKey().getTitulo() + "\": " + entrada.getValue());
            }
        }

        System.out.println("\nLivro de origem: " + l7.getTitulo());
        Map<Livro, Integer> distancias2 = GrafoLivros.djikstraSimples(grafo.getGrafo(), l7);
        for (Map.Entry<Livro, Integer> entrada : distancias2.entrySet()) {
            if (!entrada.getKey().equals(l7)) {
                System.out.println("  distancia ate \"" + entrada.getKey().getTitulo() + "\": " + entrada.getValue());
            }
        }
    }
}
