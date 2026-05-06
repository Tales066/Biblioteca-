public class UI {

    private static final int LARGURA = 50;

    public static void linha() {
        System.out.println("⋆".repeat(LARGURA));
    }

    public static void titulo(String texto) {
        System.out.println("\n✧･ﾟ: *✧･ﾟ:* " + texto.toUpperCase() + " *:･ﾟ✧*:･ﾟ✧\n");
    }

    public static void opcao(String texto, boolean selecionado) {
        if (selecionado) {
            System.out.println("\033[1;30m▸ " + texto + "\033[0m");
        } else {
            System.out.println("  " + texto);
        }
    }

    public static void centralizar(String texto) {
        int espacos = (LARGURA - texto.length()) / 2;
        System.out.println(" ".repeat(Math.max(0, espacos)) + texto);
    }

    public static void sucesso(String msg) {
        System.out.println(Cores.VERDE + "✔ " + msg + Cores.RESET);
    }

    public static void erro(String msg) {
        System.out.println(Cores.VERMELHO + "⚠ " + msg + Cores.RESET);
    }
}