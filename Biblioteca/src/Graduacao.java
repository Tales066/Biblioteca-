public class Graduacao extends Usuario {

    public Graduacao(String nome, String cpf, Perfil perfil) {
        super(nome, cpf, "Graduação", perfil);
    }

    @Override
    public boolean podePegarLivro() {
        return quantidadeEmprestimos() < 4;
    }
}