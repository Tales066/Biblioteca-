import java.util.ArrayList;

public abstract class Usuario implements Exibivel {

    private String nome;
    private String cpf;
    private String tipo;
    private Perfil perfil;
    private ArrayList<Emprestimo> emprestimosAtivos;

    public Usuario(String nome, String cpf, String tipo, Perfil perfil) {
        this.nome = nome;
        this.cpf = cpf;
        this.tipo = tipo;
        this.perfil = perfil;
        this.emprestimosAtivos = new ArrayList<>();
    }

    // ================= GETTERS =================
    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTipo() {
        return tipo;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public ArrayList<Emprestimo> getEmprestimosAtivos() {
        return emprestimosAtivos;
    }

    // ================ REGRAS DE NEGÓCIO =================
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

    // ================= Como Exibir =================

    private void mostrarEmprestimosAtivos() {
        if (emprestimosAtivos.isEmpty()) {
            System.out.println("Nenhum empréstimo ativo.");
        } else {
            System.out.println("\n Empréstimos ativos:\n");

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

        System.out.println("═══■════■════■════■════■════■════■════■═══");
    }

    @Override
    public void mostrar() {
        System.out.println("\n═══■════■════■════■════■════■════■════■════■═══");
        System.out.println("     ✦✧✦ PERFIL DO USUÁRIO ✦✧✦");
        System.out.println("═══■════■════■════■════■════■════■════■════■═══");

        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Tipo: " + tipo);
        System.out.println("Perfil: " + perfil);
        System.out.println("Quantidade de empréstimos ativos: " + emprestimosAtivos.size());

        mostrarEmprestimosAtivos();

        System.out.println("═══■════■════■════■════■════■════■════■════■═══");
    }
}