public class Graduacao extends Usuario {

    public Graduacao(String nome) {
        super(nome);
    }

    @Override
    public boolean podePegarLivro() {
        return quantidadeEmprestimos() < 4;
    }
}