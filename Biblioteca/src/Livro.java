public class Livro implements Exibivel {

    private String titulo;
    private String autor;
    private String genero;
    private int quantidade;

    public Livro(String titulo, String autor, String genero, int quantidade) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.quantidade = quantidade;
    }

    // Getters
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getGenero() {
        return genero;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public boolean isDisponivel() {
        return quantidade > 0;
    }

    public void emprestar() {
        if (quantidade > 0) {
            quantidade--;
        } else {
            System.out.println("O livro '" + titulo + "' não está disponível para empréstimo.");
        }
    }

    public void devolver() {
        quantidade++;
    }

    @Override
    public void mostrar() {
        System.out.println(" ,---.               " + titulo);
        System.out.println(" |   |               Autor: " + autor);
        System.out.println(" |   |               Gênero: " + genero);
        System.out.println(" |   |               Disponíveis: " + quantidade);
        System.out.println(" '---'");
        System.out.println("----------------------");
    }
}