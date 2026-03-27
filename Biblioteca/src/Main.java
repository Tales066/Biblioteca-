import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        do {
            System.out.println("\n===============================================");
            System.out.println("             SISTEMA DE BIBLIOTECA ");
            System.out.println("===============================================");
            System.out.println("1 - Adicionar usuário");
            System.out.println("2 - Listar usuários");
            System.out.println("3 - Adicionar livro");
            System.out.println("4 - Listar livros");
            System.out.println("5 - Emprestar livro");
            System.out.println("6 - Devolver livro");
            System.out.println("7 - Buscar livro por autor");
            System.out.println("8 - Ver histórico de empréstimos");
            System.out.println("9 - Ver perfil do usuário");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); 

                switch (opcao) {
                    case 1:
                        System.out.print("Digite o nome do usuário: ");
                        String nomeUsuario = scanner.nextLine();
                        System.out.print("Digite o CPF do usuário: ");
                        String cpfUsuario = scanner.nextLine();

                        int tipo = -1;
                        do {
                            System.out.println("Escolha o tipo de usuário:");
                            System.out.println("1 - Graduação");
                            System.out.println("2 - Pós-graduação");
                            System.out.print("Sua opção: "); 
                            if (scanner.hasNextInt()){
                                tipo = scanner.nextInt();
                                scanner.nextLine();  
                            } else {
                                scanner.nextLine();
                                tipo = -1;
                            } 
                        } while (tipo < 1 || tipo > 2);

                        String tipoUsuario = (tipo == 1) ? "Graduação" : "Pós-graduação";
                        biblioteca.cadastrarUser(new Usuario(nomeUsuario, cpfUsuario, tipoUsuario));
                        System.out.println("Usuário adicionado com sucesso!");
                        break;

                    case 2:
                        System.out.println("=== Lista de Usuários ===");
                        for (Usuario usuario : biblioteca.getUsuarios()) {  // Acessa todos os usuários
                            usuario.mostrar();  // Exibe o perfil do usuário
                        }
                        break;

                    case 3:
                        System.out.print("Digite o título do livro: ");
                        String titulo = scanner.nextLine();
                        System.out.print("Digite o autor do livro: ");
                        String autor = scanner.nextLine();
                        System.out.print("Digite o gênero do livro: ");
                        String genero = scanner.nextLine();
                        System.out.print("Digite a quantidade disponível: ");
                        int quantidade = scanner.nextInt();
                        scanner.nextLine();  
                        biblioteca.cadastrarLivro(new Livro(titulo, autor, genero, quantidade));
                        System.out.println("Livro adicionado com sucesso!");
                        break;

                    case 4:
                        System.out.println("=== Lista de Livros ===");
                        for (Livro livro : biblioteca.getLivros()) {  
                            livro.mostrar();
                        }
                        break;

                    case 5:
                        System.out.print("Digite o título do livro para empréstimo: ");
                        String tituloEmprestimo = scanner.nextLine();
                        Livro livro = biblioteca.procurarLivro(tituloEmprestimo);

                        if (livro != null && livro.isDisponivel()) {
                            System.out.print("Digite o nome do usuário: ");
                            String nomeUser = scanner.nextLine();
                            Usuario usuario = biblioteca.procurarUsuario(nomeUser);

                            if (usuario != null) {
                                if (usuario.podePegarLivro()) {
                                    livro.emprestar();
                                    Emprestimo emprestimo = new Emprestimo(livro, usuario);
                                    usuario.pegarEmprestimo(emprestimo);
                                    biblioteca.registrarEmprestimo(emprestimo);

                                    System.out.println("Livro emprestado com sucesso!");
                                    System.out.println("Data de devolução: " + emprestimo.getDataDevolucao());
                                    System.out.println("Dias restantes para devolução: " + emprestimo.getPrazoDevolucao());
                                } else {
                                    System.out.println("Usuário já atingiu o limite de 4 livros.");
                                }
                            } else {
                                System.out.println("Usuário não encontrado.");
                            }
                        } else {
                            System.out.println("Livro não disponível ou não encontrado.");
                        }
                        break;

                    case 6:
                        System.out.print("Digite o título do livro para devolução: ");
                        String tituloDevolucao = scanner.nextLine();
                        Livro livroDevolucao = biblioteca.procurarLivro(tituloDevolucao);

                        if (livroDevolucao != null) {
                            Emprestimo emprestimoAtivo = null;
                            for (Emprestimo e : biblioteca.getHistorico()) {
                                if (e.getLivro() == livroDevolucao && e.isAtivo()) {
                                    emprestimoAtivo = e;
                                    break;
                                }
                            }

                            if (emprestimoAtivo != null) {
                                emprestimoAtivo.finalizar();
                                emprestimoAtivo.getUsuario().devolverEmprestimo(emprestimoAtivo);
                                livroDevolucao.devolver();
                                System.out.println("Livro devolvido com sucesso!");
                            } else {
                                System.out.println("Nenhum empréstimo ativo encontrado para este livro.");
                            }
                        } else {
                            System.out.println("Livro não encontrado.");
                        }
                        break;

                    case 7:
                        System.out.print("Digite o nome do autor: ");
                        String autorBusca = scanner.nextLine();
                        biblioteca.procurarPorAutor(autorBusca);
                        break;

                    case 8:
                        System.out.println("=== Histórico de Empréstimos ===");
                        biblioteca.listarHistorico();
                        break;

                    case 9:
                        System.out.print("Digite o nome do usuário para visualizar o perfil: ");
                        String nomePerfil = scanner.nextLine();
                        Usuario usuarioPerfil = biblioteca.procurarUsuario(nomePerfil);
                        if (usuarioPerfil != null) {
                            usuarioPerfil.mostrar();
                        } else {
                            System.out.println("Usuário não encontrado.");
                        }
                        break;

                    case 0:
                        System.out.println("Saindo... Até depois hihi!");
                        break;

                    default:
                        System.out.println("Opção inválida! Escolha novamente.");
                }

            } catch (Exception e) {
                System.out.println("Entrada inválida. Tente novamente.");
                scanner.nextLine(); 
            }

        } while (opcao != 0);

        scanner.close();
    }
}