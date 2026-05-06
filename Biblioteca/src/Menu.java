import java.util.Scanner;

public class Menu {

    public static int navegar(String[] opcoes, Scanner scanner) {
        int pos = 0;

        while (true) {
            ConsoleUtils.clearConsole();
            UI.titulo("Menu");

            for (int i = 0; i < opcoes.length; i++) {
                UI.opcao(opcoes[i], i == pos);
            }
            // ================= Opções de navegação =================
            System.out.println("\n⋆⋆⋆⋆⋆⋆⋆⋆⋆⋆⋆⋆⋆⋆⋆⋆⋆⋆⋆⋆");
            System.out.println("Use W (cima), S (baixo) e ENTER");

            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("w") && pos > 0) pos--;
            if (input.equalsIgnoreCase("s") && pos < opcoes.length - 1) pos++;
            if (input.equals("")) return pos;
        }
    }
}