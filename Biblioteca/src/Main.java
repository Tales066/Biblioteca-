import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);

        Usuario admin =
                new Graduacao("admin", "000", Perfil.ADMIN);

        Usuario u1 =
                new Graduacao("Talyta", "123", Perfil.ESTUDANTE);

        Usuario u2 =
                new Posgraduacao("Joao", "456", Perfil.ESTUDANTE);

        biblioteca.cadastrarUser(admin);
        biblioteca.cadastrarUser(u1);
        biblioteca.cadastrarUser(u2);

        while (true) {

            String[] opcoesLogin = {
                    "Login",
                    "Sair"
            };

            int escolhaMenu =
                    Menu.navegar(opcoesLogin, scanner);

            if (escolhaMenu == 1) {

                System.out.println(
                        Cores.VERMELHO
                                + "\nEncerrando sistema..."
                                + Cores.RESET
                );

                break;
            }

            System.out.print(
                    Cores.AMARELO
                            + "\nDigite seu nome: "
                            + Cores.RESET
            );

            String nomeLogin =
                    scanner.nextLine().trim();

            Usuario usuarioLogado =
                    biblioteca.procurarUsuario(nomeLogin);

            if (usuarioLogado == null) {

                UI.erro("Usuário não encontrado!");

                ConsoleUtils.pause();

                continue;
            }

            UI.sucesso("Login realizado com sucesso!");

            ConsoleUtils.pause();

            // ================= MENU ESTUDANTE ========================
            if (usuarioLogado.getPerfil()
                    == Perfil.ESTUDANTE) {

                String[] opcoes = {

                        "Pegar livro",
                        "Devolver livro",
                        "Ver perfil",
                        "Buscar por autor",
                        "Meu histórico",
                        "Sair"
                };

                while (true) {

                    int op =
                            Menu.navegar(opcoes, scanner);

                    switch (op) {

                        case 0:

                            try {

                                System.out.print(
                                        "Título: "
                                );

                                biblioteca.realizarEmprestimo(
                                        scanner.nextLine(),
                                        usuarioLogado.getNome()
                                );

                                UI.sucesso(
                                        "Livro emprestado!"
                                );

                            } catch (Exception e) {

                                UI.erro(e.getMessage());
                            }

                            break;

                        case 1:

                            try {

                                System.out.print(
                                        "Título: "
                                );

                                biblioteca.realizarDevolucao(
                                        scanner.nextLine()
                                );

                                UI.sucesso(
                                        "Livro devolvido!"
                                );

                            } catch (Exception e) {

                                UI.erro(e.getMessage());
                            }

                            break;

                        case 2:

                            usuarioLogado.mostrar();

                            break;

                        case 3:

                            System.out.print("Autor: ");

                            biblioteca.procurarPorAutor(
                                    scanner.nextLine()
                            );

                            break;

                        case 4:

                            biblioteca
                                    .listarHistoricoDoUsuario(
                                            usuarioLogado
                                    );

                            break;

                        case 5:

                            break;
                    }

                    if (op == 5) {
                        break;
                    }

                    ConsoleUtils.pause();
                }
            }
            // ================= MENU ADMIN ============================
            else {

                String[] opcoes = {

                        "Listar usuários",
                        "Adicionar usuário",
                        "Adicionar livro",
                        "Listar livros",
                        "Buscar por autor",
                        "Ver histórico",
                        "Ver perfil usuário",
                        "Verificar atrasos",
                        "Sair"
                };

                while (true) {

                    int op =
                            Menu.navegar(opcoes, scanner);

                    switch (op) {

                        case 0:

                            biblioteca.getUsuarios()
                                    .forEach(
                                            Usuario::mostrar
                                    );

                            break;

                        case 1:

                            System.out.print("Nome: ");
                            String nome =
                                    scanner.nextLine();

                            System.out.print("CPF: ");
                            String cpf =
                                    scanner.nextLine();

                            int tipo = -1;

                            do {

                                System.out.println(
                                        "Tipo: 1-Graduação | 2-Pós"
                                );

                                if (scanner.hasNextInt()) {

                                    tipo =
                                            scanner.nextInt();

                                    scanner.nextLine();

                                } else {

                                    scanner.nextLine();
                                }

                            } while (tipo < 1 || tipo > 2);

                            Usuario novo;

                            if (tipo == 1) {

                                novo = new Graduacao(
                                        nome,
                                        cpf,
                                        Perfil.ESTUDANTE
                                );

                            } else {

                                novo = new Posgraduacao(
                                        nome,
                                        cpf,
                                        Perfil.ESTUDANTE
                                );
                            }

                            biblioteca.cadastrarUser(novo);

                            UI.sucesso(
                                    "Usuário cadastrado!"
                            );

                            break;

                        case 2:

                            System.out.print("Título: ");
                            String titulo =
                                    scanner.nextLine();

                            System.out.print("Autor: ");
                            String autor =
                                    scanner.nextLine();

                            System.out.print("Gênero: ");
                            String genero =
                                    scanner.nextLine();

                            System.out.print("Quantidade: ");

                            int qtd =
                                    scanner.nextInt();

                            scanner.nextLine();

                            biblioteca.cadastrarLivro(

                                    new Livro(
                                            titulo,
                                            autor,
                                            genero,
                                            qtd
                                    )
                            );

                            UI.sucesso(
                                    "Livro cadastrado!"
                            );

                            break;

                        case 3:

                            biblioteca.getLivros()
                                    .forEach(
                                            Livro::mostrar
                                    );

                            break;

                        case 4:

                            System.out.print("Autor: ");

                            biblioteca.procurarPorAutor(
                                    scanner.nextLine()
                            );

                            break;

                        case 5:

                            biblioteca.listarHistorico();

                            break;

                        case 6:

                            System.out.print(
                                    "Nome do usuário: "
                            );

                            Usuario u =
                                    biblioteca.procurarUsuario(
                                            scanner.nextLine()
                                    );

                            if (u != null) {

                                u.mostrar();

                            } else {

                                UI.erro(
                                        "Usuário não encontrado!"
                                );
                            }

                            break;

                        case 7:

                            biblioteca
                                    .verificarEmprestimosAtrasados();

                            break;

                        case 8:

                            break;
                    }

                    if (op == 8) {
                        break;
                    }

                    ConsoleUtils.pause();
                }
            }
        }

        biblioteca.encerrarSistema();

        scanner.close();

        System.out.println("Sistema encerrado.");
    }
}