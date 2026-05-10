import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Biblioteca {

    // ================= THREAD POOL =================
    private ExecutorService executor = Executors.newFixedThreadPool(4);

    private List<Exibivel> itens =
            Collections.synchronizedList(new ArrayList<>());

    private List<Usuario> usuarios =
            Collections.synchronizedList(new ArrayList<>());

    private List<Livro> livros =
            Collections.synchronizedList(new ArrayList<>());

    private List<Emprestimo> historicoEmprestimos =
            Collections.synchronizedList(new ArrayList<>());

    public void cadastrarUser(Usuario usuario) {
        usuarios.add(usuario);
        itens.add(usuario);
    }

    public void cadastrarLivro(Livro livro) {
        livros.add(livro);
        itens.add(livro);
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void registrarEmprestimo(Emprestimo emprestimo) {
        historicoEmprestimos.add(emprestimo);
        itens.add(emprestimo);
    }

    public List<Emprestimo> getHistorico() {
        return historicoEmprestimos;
    }

    public void listarItens() {

        if (itens.isEmpty()) {
            System.out.println("Nenhum item registrado.");
            return;
        }

        itens.forEach(Exibivel::mostrar);
    }

    // GENERICS + SYNCHRONIZED

    public <T> T procurarItem(Class<T> tipo, String valor) {

        synchronized (itens) {

            for (Exibivel item : itens) {

                if (tipo.isInstance(item)) {

                    T obj = tipo.cast(item);

                    if (obj instanceof Usuario) {

                        if (((Usuario) obj).getNome()
                                .equalsIgnoreCase(valor)) {

                            return obj;
                        }
                    }

                    if (obj instanceof Livro) {

                        if (((Livro) obj).getTitulo()
                                .equalsIgnoreCase(valor)) {

                            return obj;
                        }
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

        boolean encontrado = itens.parallelStream()
                .filter(item -> item instanceof Livro)
                .map(item -> (Livro) item)
                .filter(livro ->
                        livro.getAutor().equalsIgnoreCase(autor))
                .peek(Livro::mostrar)
                .findAny()
                .isPresent();

        if (!encontrado) {
            System.out.println(
                    "Nenhum livro encontrado para o autor: "
                            + autor);
        }
    }

    public void listarHistorico() {

        if (historicoEmprestimos.isEmpty()) {

            System.out.println(
                    "Nenhum empréstimo registrado.");
            return;
        }

        historicoEmprestimos.forEach(Emprestimo::mostrar);
    }

    public void listarHistoricoDoUsuario(Usuario usuario) {

        boolean encontrado = false;

        synchronized (historicoEmprestimos) {

            for (Emprestimo e : historicoEmprestimos) {

                if (e.getUsuario().equals(usuario)) {

                    e.mostrar();
                    encontrado = true;
                }
            }
        }

        if (!encontrado) {

            System.out.println(
                    "Nenhum histórico encontrado.");
        }
    }

    // ================= EXECUTORSERVICE =================

    public void verificarEmprestimosAtrasados() {

        if (historicoEmprestimos.isEmpty()) {

            System.out.println(
                    "Nenhum empréstimo registrado.");
            return;
        }

        historicoEmprestimos.forEach(emprestimo -> {

            executor.submit(() -> {

                if (emprestimo.estaAtrasado()) {

                    synchronized (System.out) {

                        System.out.println(
                                "\nLivro atrasado: "
                                        + emprestimo.getLivro()
                                        .getTitulo());

                        System.out.println(
                                "Usuário: "
                                        + emprestimo.getUsuario()
                                        .getNome());

                        System.out.println(
                                "Dias atrasado: "
                                        + Math.abs(
                                        emprestimo
                                                .getPrazoDevolucao()
                                ));
                    }
                }
            });
        });
    }

    @Log(valor = "Realizando empréstimo de livro")
    public void realizarEmprestimo(
            String titulo,
            String nomeUsuario
    ) throws EmprestimoExcecao {

        Livro livro = procurarLivro(titulo);

        if (livro == null) {
            throw new EmprestimoExcecao(
                    "Livro não encontrado.");
        }

        Usuario usuario = procurarUsuario(nomeUsuario);

        if (usuario == null) {
            throw new EmprestimoExcecao(
                    "Usuário não encontrado.");
        }

        if (!usuario.podePegarLivro()) {

            throw new EmprestimoExcecao(
                    "Usuário atingiu limite.");
        }

        livro.emprestar();

        Emprestimo emprestimo =
                new Emprestimo(livro, usuario);

        usuario.pegarEmprestimo(emprestimo);

        registrarEmprestimo(emprestimo);
    }

    @Log(valor = "Realizando devolução do livro")
    public void realizarDevolucao(String titulo)
            throws EmprestimoExcecao {

        Livro livro = procurarLivro(titulo);

        if (livro == null) {

            throw new EmprestimoExcecao(
                    "Livro não encontrado.");
        }

        Emprestimo emprestimoAtivo = null;

        synchronized (historicoEmprestimos) {

            for (Emprestimo e : historicoEmprestimos) {

                if (e.getLivro().equals(livro)
                        && e.isAtivo()) {

                    emprestimoAtivo = e;
                    break;
                }
            }
        }

        if (emprestimoAtivo == null) {

            throw new EmprestimoExcecao(
                    "Nenhum empréstimo ativo.");
        }

        emprestimoAtivo.finalizar();

        emprestimoAtivo.getUsuario()
                .devolverEmprestimo(
                        emprestimoAtivo
                );

        livro.devolver();
    }

    public void encerrarSistema() {
        executor.shutdown();
    }
}