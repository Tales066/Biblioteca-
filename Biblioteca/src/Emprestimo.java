import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo implements Exibivel {  

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
        this.ativo = true;  
    }

    public Livro getLivro() { return livro; }
    public Usuario getUsuario() { return usuario; }
    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
    public LocalDate getDataDevolucao() { return dataDevolucao; }
    public boolean isAtivo() { return ativo; }

    public long getPrazoDevolucao() {
        return ChronoUnit.DAYS.between(LocalDate.now(), dataDevolucao);
    }
   
    public void finalizar() {
        this.ativo = false;
    }
 
    public boolean estaAtrasado() {
        return ativo && LocalDate.now().isAfter(dataDevolucao);
    }
    
    @Override
    public void mostrar() {
        long prazo = getPrazoDevolucao();
        System.out.println("Livro: " + livro.getTitulo());
        System.out.println("Usuário: " + usuario.getNome());
        System.out.println("Data de empréstimo: " + dataEmprestimo);
        System.out.println("Data de devolução: " + dataDevolucao);
        
        if (ativo) {
            if (prazo >= 0) {
                System.out.println("Prazo restante: " + prazo + " dias.");
            } else {
                System.out.println("ATRASADO HÁ: " + Math.abs(prazo) + " dias.");
            }
        }
        
        System.out.println("Status: " + (ativo ? "Ativo" : "Finalizado"));
        System.out.println("----------------------");
    }
}