import java.util.ArrayList;

public abstract class Usuario implements Exibivel { 

    private String nome;
    private String cpf;
    private String tipo;
    private ArrayList<Emprestimo> emprestimosAtivos;

    public Usuario(String nome, String cpf, String tipo) {
        this.nome = nome;
        this.cpf = cpf;
        this.tipo = tipo;
        this.emprestimosAtivos = new ArrayList<>();
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTipo() {
        return tipo;
    }

    public ArrayList<Emprestimo> getEmprestimosAtivos() {
        return emprestimosAtivos;
    }

    public abstract boolean podePegarLivro();

    public int quantidadeEmprestimos() {
        return emprestimosAtivos.size();
    }

    public void pegarEmprestimo(Emprestimo emprestimo) {
        emprestimosAtivos.add(emprestimo);
    }

    public void devolverEmprestimo(Emprestimo emprestimo) {
        emprestimosAtivos.remove(emprestimo);
    }

    public void listarLivrosPorGenero(String generoDesejado) {
        System.out.println("\n═══■ FILTRANDO POR GÊNERO: " + generoDesejado.toUpperCase() + " ■═══");

        boolean encontrou = false;
        for (Emprestimo e : emprestimosAtivos) {
            if (e.getLivro().getGenero().equalsIgnoreCase(generoDesejado)) {
                encontrou = true;
                System.out.println(" ,---.               " + e.getLivro().getTitulo());
                System.out.println(" |   |               Autor: " + e.getLivro().getAutor());
                System.out.println(" |   |               Data empréstimo: " + e.getDataEmprestimo());
                System.out.println(" |   |               Data devolução: " + e.getDataDevolucao());
                System.out.println(" |   |               Atrasado: " + (e.estaAtrasado() ? "Sim" : "Não"));
                System.out.println(" '---'");
                System.out.println("----------------------");
            }
        }

        if (!encontrou) {
            System.out.println("Você não possui livros de " + generoDesejado + " emprestados.");
        }
        System.out.println("═══■════■════■════■════■════■════■════■════■═══");
    }

    private void mostrarEmprestimosAtivos() {
        if (emprestimosAtivos.isEmpty()) {
            System.out.println("Nenhum empréstimo ativo.");
        } else {
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
        }
    }

    @Override
    public void mostrar() {
        System.out.println("\n═══■════■════■════■════■════■════■════■════■═══");
        System.out.println("     ✦✧✦ PERFIL DO USUÁRIO ✦✧✦");
        System.out.println("═══■════■════■════■════■════■════■════■════■═══");

        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Tipo: " + tipo);
        System.out.println("Quantidade de empréstimos ativos: " + emprestimosAtivos.size());
        mostrarEmprestimosAtivos();

        System.out.println("═══■════■════■════■════■════■════■════■════■═══");
    }
}
