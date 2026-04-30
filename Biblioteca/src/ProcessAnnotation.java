import java.lang.reflect.Method;

public class ProcessAnnotation {

    public static void processar(Object obj) {

        Class<?> classe = obj.getClass();

        for (Method metodo : classe.getDeclaredMethods()) {

            if (metodo.isAnnotationPresent(Log.class)) {

                Log log = metodo.getAnnotation(Log.class);

                System.out.println("\n════════════════════════════════════");
                System.out.println("     ANOTAÇÃO IDENTIFICADA ");
                System.out.println("   🔹 Método: " + metodo.getName());
                System.out.println("   🔹 Ação: " + log.valor());
                System.out.println("═══════════════════════════════════════\n");
            }
        }
    }
}