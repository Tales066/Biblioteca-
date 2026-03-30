public class ConsoleUtils {

    public static void clearConsole() {
        try {
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls")
                        .inheritIO()
                        .start()
                        .waitFor();
            } else {
                new ProcessBuilder("clear")
                        .inheritIO()
                        .start()
                        .waitFor();
            }

        } catch (Exception e) {
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }

    public static void pause() {
        System.out.println("\nPressione ENTER para continuar...");
        try {
            System.in.read();
        } catch (Exception e) {
            // Ignora o erro, pois é apenas uma pausa simples.
        }
    }
}

