import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo {
    private Livro livro;
    private Usuario usuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private boolean ativo;

    public Emprestimo(Livro livro, Usuario usuario) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucao = dataEmprestimo.plusMonths(1); 
    }

    // Getters
    public Livro getLivro() {
        return livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public long getPrazoDevolucao() {
        return ChronoUnit.DAYS.between(LocalDate.now(), dataDevolucao);
    }
   
    public void finalizar() {
        this.ativo = false;
    }
 
    public boolean estaAtrasado() {
        return LocalDate.now().isAfter(dataDevolucao);
    }
    
    public void mostrar() {
        System.out.println("Livro: " + livro.getTitulo());
        System.out.println("Usuário: " + usuario.getNome());
        System.out.println("Data de empréstimo: " + dataEmprestimo);
        System.out.println("Data de devolução: " + dataDevolucao);
        System.out.println("Prazo restante (dias): " + getPrazoDevolucao());
        System.out.println("Status: " + (ativo ? "Ativo" : "Finalizado"));
        System.out.println("Atrasado: " + (estaAtrasado() ? "Sim" : "Não"));
        System.out.println("----------------------");
    }
}