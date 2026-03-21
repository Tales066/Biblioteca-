import java.util.ArrayList;

public class Biblioteca {

    private ArrayList<Livro> livros = new ArrayList<>();
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Emprestimo> historico = new ArrayList<>();

    public void cadastrarUser(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void cadastrarUser(String nome) {
        Usuario usuario = new Usuario(nome);
        usuarios.add(usuario);
    }

    public void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário registrado no sistema.");
            return;
        }
        for (Usuario usuario : usuarios) {
            usuario.mostrarPerfil();
        }
    }

    public Usuario procurarUsuario(String nome) {
        for (Usuario u : usuarios) {
            if (u.getNome().equalsIgnoreCase(nome)) {
                return u;
            }
        }
        return null;
    }

    // --- Livros ---
    public void cadastrarLivro(Livro livro) {
        livros.add(livro);
    }

    // para incluir gênero 
    public void cadastrarLivro(String titulo, String autor, String genero, int quantidade) {
        Livro livro = new Livro(titulo, autor, genero, quantidade);
        livros.add(livro);
    }

    public void listarLivros() {
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro registrado no sistema.");
            return;
        }
        for (Livro l : livros) {
            l.mostrar();
        }
    }

    // focado no acervo geral com sua estrutura visual
    public void listarLivrosPorGenero(String genero) {
        boolean encontrado = false;
        System.out.println("\n═══════════════════════════════════════════════");
        System.out.println("       LIVROS DO GÊNERO: " + genero.toUpperCase());
        System.out.println("═══════════════════════════════════════════════");
    
        for (Livro l : livros) {
            if (l.getGenero().equalsIgnoreCase(genero)) {
                encontrado = true;
                System.out.println(" ,---.               " + l.getTitulo());
                System.out.println(" |   |               Autor: " + l.getAutor());
                System.out.println(" |   |               Disponíveis: " + l.getQuantidade());
                System.out.println(" '---'");
                System.out.println("-----------------------------------------------");
            }
        }

        if (!encontrado) {
            System.out.println("Nenhum livro cadastrado no gênero: " + genero);
        }
        System.out.println("═══════════════════════════════════════════════");
    }

    public Livro procurarLivro(String titulo) {
        for (Livro l : livros) {
            if (l.getTitulo().equalsIgnoreCase(titulo)) {
                return l;
            }
        }
        return null;
    }

    public Livro procurarLivro(String titulo, String autor) {
        for (Livro l : livros) {
            if (l.getTitulo().equalsIgnoreCase(titulo) && l.getAutor().equalsIgnoreCase(autor)) {
                return l;
            }
        }
        return null;
    }

    public void procurarPorAutor(String autor) {
        boolean encontrado = false;
        for (Livro l : livros) {
            if (l.getAutor().equalsIgnoreCase(autor)) {
                l.mostrar();
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("Nenhum livro encontrado para o autor: " + autor);
        }
    }

    // --- Histórico de empréstimos ---
    public void registrarEmprestimo(Emprestimo emprestimo) {
        historico.add(emprestimo);
    }

    public ArrayList<Emprestimo> getHistorico() {
        return historico;
    }

    public void listarHistorico() {
        if (historico.isEmpty()) {
            System.out.println("Nenhum empréstimo registrado no sistema.");
            return;
        }
        for (Emprestimo e : historico) {
            e.mostrar();
        }
    }

    // --- Disponibilidade e regras ---
    public boolean verificarDisponibilidade(String titulo) {
        Livro livro = procurarLivro(titulo);
        return livro != null && livro.isDisponivel();
    }

    public boolean podeEmprestar(Usuario usuario) {
        return usuario.getEmprestimosAtivos().size() < 4;
    }

    // --- Empréstimos ---
    public void emprestarLivro(Usuario usuario, String titulo) {
        if (!podeEmprestar(usuario)) {
            System.out.println("Usuário " + usuario.getNome() + " já atingiu o limite de empréstimos ativos.");
            return;
        }

        Livro livro = procurarLivro(titulo);
        if (livro != null && livro.isDisponivel()) {
            livro.emprestar();

            Emprestimo emprestimo = new Emprestimo(livro, usuario);

            usuario.pegarEmprestimo(emprestimo);
            registrarEmprestimo(emprestimo);

            System.out.println("Livro '" + titulo + "' emprestado para " + usuario.getNome() + ".");
            System.out.println("Data de empréstimo: " + emprestimo.getDataEmprestimo());
            System.out.println("Data de devolução: " + emprestimo.getDataDevolucao());
            System.out.println("Prazo para devolução: " + emprestimo.getPrazoDevolucao() + " dias.");
        } else {
            System.out.println("Livro '" + titulo + "' não está disponível para empréstimo.");
        }
    }

    // --- Devolução ---
    public void devolverLivro(Usuario usuario, String titulo) {
        Livro livro = procurarLivro(titulo);
        if (livro == null) {
            System.out.println("Livro '" + titulo + "' não encontrado.");
            return;
        }
        
        Emprestimo emprestimoAtivo = null;
        for (Emprestimo e : historico) {
            if (e.getLivro() == livro && e.getUsuario() == usuario && e.isAtivo()) {
                emprestimoAtivo = e;
                break;
            }
        }

        if (emprestimoAtivo != null) {
            emprestimoAtivo.finalizar();
            usuario.devolverEmprestimo(emprestimoAtivo);
            livro.devolver();
            System.out.println("Livro '" + titulo + "' devolvido por " + usuario.getNome() + ".");
        } else {
            System.out.println("Nenhum empréstimo ativo encontrado para este livro e usuário.");
        }
    }
}