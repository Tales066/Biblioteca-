public class Graduacao extends Usuario {

    public Graduacao(String nome, String cpf) {
        super(nome, cpf, "Graduação");
    }

    @Override
    public boolean podePegarLivro() {
        return quantidadeEmprestimos() < 4;
    }
}