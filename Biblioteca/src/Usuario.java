import java.util.ArrayList;

public class Usuario {

    private String nome;
    private ArrayList<Emprestimo> emprestimosAtivos;

    
    public Usuario(String nome) {
        this.nome = nome;
        this.emprestimosAtivos = new ArrayList<>();
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public ArrayList<Emprestimo> getEmprestimosAtivos() {
        return emprestimosAtivos;
    }

    public boolean podePegarLivro() {
        return emprestimosAtivos.size() < 4; 
    }

    public int quantidadeEmprestimos() {
        return emprestimosAtivos.size();
    }

    public void pegarEmprestimo(Emprestimo emprestimo) {
        emprestimosAtivos.add(emprestimo);
    }

    public void devolverEmprestimo(Emprestimo emprestimo) {
        emprestimosAtivos.remove(emprestimo);
    }

    public void mostrarPerfil() {
        System.out.println("\n═══■════■════■════■════■════■════■════■════■═══");
        System.out.println("     ✦✧✦ PERFIL DO USUÁRIO ✦✧✦");
        System.out.println("═══■════■════■════■════■════■════■════■════■═══");

        System.out.println("Nome: " + nome);
        System.out.println("Quantidade de empréstimos ativos: " + emprestimosAtivos.size());

        if (!emprestimosAtivos.isEmpty()) {
            System.out.println("Empréstimos ativos:");
            for (Emprestimo e : emprestimosAtivos) {
                System.out.println(" ,---.               " + e.getLivro().getTitulo());
                System.out.println(" |   |               Autor: " + e.getLivro().getAutor());
                System.out.println(" |   |               Data empréstimo: " + e.getDataEmprestimo());
                System.out.println(" |   |               Data devolução: " + e.getDataDevolucao());
                System.out.println(" |   |               Atrasado: " + (e.estaAtrasado() ? "Sim" : "Não"));
                System.out.println(" '---'");
                System.out.println("----------------------");
            }
        } else {
            System.out.println("Nenhum empréstimo ativo.");
        }

        System.out.println("═══■════■════■════■════■════■════■════■════■═══");
    }
}