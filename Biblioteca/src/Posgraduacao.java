public class Posgraduacao extends Usuario {

    public Posgraduacao(String nome, String cpf, Perfil perfil) {
        super(nome, cpf, "Pós-graduação", perfil);
    }

    @Override
    public boolean podePegarLivro() {
        return quantidadeEmprestimos() < 6;
    }
}