public class Posgraduacao extends Usuario {

    public Posgraduacao(String nome, String cpf) {
        super(nome, cpf, "Pós-graduação");
    }

    @Override
    public boolean podePegarLivro() {
        return quantidadeEmprestimos() < 6;
    }
}