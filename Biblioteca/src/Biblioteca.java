import java.util.ArrayList;

public class Biblioteca {

    private ArrayList<Exibivel> itens = new ArrayList<>(); 
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Livro> livros = new ArrayList<>();    
    private ArrayList<Emprestimo> historicoEmprestimos = new ArrayList<>(); 

    public void cadastrarUser(Usuario usuario) {
        usuarios.add(usuario); 
        itens.add(usuario);  
    }

    public void cadastrarLivro(Livro livro) {
        livros.add(livro);  
        itens.add(livro);   
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios; 
    }

    public ArrayList<Livro> getLivros() {
        return livros; 
    }

    public void registrarEmprestimo(Emprestimo emprestimo) {
        historicoEmprestimos.add(emprestimo); 
        itens.add(emprestimo);  
    }

    public ArrayList<Emprestimo> getHistorico() {
        return historicoEmprestimos; 
    }

    //Aplicando o lambda 
    public void listarItens() {
        if (itens.isEmpty()) {
            System.out.println("Nenhum item registrado no sistema.");
            return;
        }
        itens.forEach(item -> item.mostrar());
    }

    // Aplicando generics para evitar duplicação de código
    public <T> T procurarItem(Class<T> tipo, String valor) {
        for (Exibivel item : itens) {
            if (tipo.isInstance(item)) {
                T obj = tipo.cast(item);

                if (obj instanceof Usuario) {
                    if (((Usuario) obj).getNome().equalsIgnoreCase(valor)) {
                        return obj;
                    }
                }

                if (obj instanceof Livro) {
                    if (((Livro) obj).getTitulo().equalsIgnoreCase(valor)) {
                        return obj;
                    }
                }
            }
        }
        return null;
    }

    public Usuario procurarUsuario(String nome) {
        return procurarItem(Usuario.class, nome);
    }

    public Livro procurarLivro(String titulo) {
        return procurarItem(Livro.class, titulo);
    }

    public void procurarPorAutor(String autor) {
        boolean encontrado = itens.stream()
            .filter(item -> item instanceof Livro)
            .map(item -> (Livro) item)
            .filter(livro -> livro.getAutor().equalsIgnoreCase(autor))
            .peek(livro -> livro.mostrar())
            .findAny()
            .isPresent();

        if (!encontrado) {
            System.out.println("Nenhum livro encontrado para o autor: " + autor);
        }
    }


    public void listarHistorico() {
        if (historicoEmprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo registrado no histórico.");
            return;
        }
        historicoEmprestimos.forEach(e -> e.mostrar());
    }

    public void realizarEmprestimo(String titulo, String nomeUsuario) throws EmprestimoExcecao {

        Livro livro = procurarLivro(titulo);
        if (livro == null) {
            throw new EmprestimoExcecao("Livro não encontrado.");
        }

        Usuario usuario = procurarUsuario(nomeUsuario);
        if (usuario == null) {
            throw new EmprestimoExcecao("Usuário não encontrado.");
        }

        if (!usuario.podePegarLivro()) {
            throw new EmprestimoExcecao("Usuário atingiu o limite de empréstimos.");
        }

        livro.emprestar();  

        Emprestimo emprestimo = new Emprestimo(livro, usuario);
        usuario.pegarEmprestimo(emprestimo);
        registrarEmprestimo(emprestimo);
    }

    public void realizarDevolucao(String titulo) throws EmprestimoExcecao {

        Livro livro = procurarLivro(titulo);
        if (livro == null) {
            throw new EmprestimoExcecao("Este livro não foi encontrado.");
        }

        Emprestimo emprestimoAtivo = null;

        for (Emprestimo e : historicoEmprestimos) {
            if (e.getLivro().equals(livro) && e.isAtivo()) {
                emprestimoAtivo = e;
                break;
            }
        }

        if (emprestimoAtivo == null) {
            throw new EmprestimoExcecao("Nenhum empréstimo ativo para este livro.");
        }

        if (!emprestimoAtivo.isAtivo()) {
            throw new EmprestimoExcecao("Este empréstimo já foi finalizado.");
        }

        emprestimoAtivo.finalizar();
        emprestimoAtivo.getUsuario().devolverEmprestimo(emprestimoAtivo);
        livro.devolver();
    }
}