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
}