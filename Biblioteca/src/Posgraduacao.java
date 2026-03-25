public class Posgraduacao extends Usuario {

    public Posgraduacao(String nome) {
        super(nome);
    }

    @Override
    public boolean podePegarLivro() {
        return quantidadeEmprestimos() < 6;
    }
}