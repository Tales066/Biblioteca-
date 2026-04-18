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

   
    public void listarItens() {
        if (itens.isEmpty()) {
            System.out.println("Nenhum item registrado no sistema.");
            return;
        }
        for (Exibivel item : itens) {
            item.mostrar();  
        }
    }

    public Usuario procurarUsuario(String nome) {
        for (Exibivel item : itens) {
            if (item instanceof Usuario) {  
                Usuario usuario = (Usuario) item;  
                if (usuario.getNome().equalsIgnoreCase(nome)) {
                    return usuario;
                }
            }
        }
        return null;
    }

    public Livro procurarLivro(String titulo) {
        for (Exibivel item : itens) {
            if (item instanceof Livro) {  
                Livro livro = (Livro) item;  
                if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                    return livro;
                }
            }
        }
        return null;
    }

    public void procurarPorAutor(String autor) {
        boolean encontrado = false;
        for (Exibivel item : itens) {
            if (item instanceof Livro) {  
                Livro livro = (Livro) item;  
                if (livro.getAutor().equalsIgnoreCase(autor)) {
                    livro.mostrar();
                    encontrado = true;
                }
            }
        }
        if (!encontrado) {
            System.out.println("Nenhum livro encontrado para o autor: " + autor);
        }
    }

    public void listarHistorico() {
        if (historicoEmprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo registrado no histórico.");
            return;
        }
        for (Emprestimo emprestimo : historicoEmprestimos) {
            emprestimo.mostrar();
        }
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