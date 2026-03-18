public class Livro {

    private String titulo;
    private String autor;
    private int quantidade;

    public Livro(String titulo, String autor, int quantidade) {
        this.titulo = titulo;
        this.autor = autor;
        this.quantidade = quantidade;
    }

    // Getters
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
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

    public void mostrar() {
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Quantidade disponível: " + quantidade);
        System.out.println("----------------------");
    }
}