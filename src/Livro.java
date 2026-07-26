import java.util.Objects;

public class Livro {

    private String titulo;
    private String autor;
    private String genero;

    public Livro(String titulo, String autor, String genero) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getGenero() {
        return genero;
    }

    // usei equals e hashCode porque o HashMap precisa disso pra funcionar corretamente
    // sem isso dois objetos Livro com mesmo titulo seriam tratados como diferentes
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Livro outro = (Livro) obj;
        return Objects.equals(titulo, outro.titulo) && Objects.equals(autor, outro.autor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, autor);
    }

    @Override
    public String toString() {
        return titulo + " - " + autor + " (" + genero + ")";
    }
}
