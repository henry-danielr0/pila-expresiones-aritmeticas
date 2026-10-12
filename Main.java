import java.util.ArrayList;
import java.util.Scanner;

// Clase principal: muestra el menú y conecta al usuario con la pila
public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static String expresion = "";   // expresión actual
    private static ArrayList<Token> tokens = new ArrayList<>();

    public static void main(String[] args) {
        menu();
    }

    // Muestra el menú en bucle hasta que el usuario elija salir
    private static void menu() {
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\n===== ANALIZADOR DE EXPRESIONES (PILA) =====");
            System.out.println("Expresión actual: " + (expresion.isEmpty() ? "(ninguna)" : expresion));
            System.out.println("1. Ingresar una expresión");
            System.out.println("2. Ver los tokens de la expresión");
            System.out.println("3. Validar paréntesis");
            System.out.println("4. Validar paréntesis paso a paso (ver la pila)");
            System.out.println("0. Salir");
            System.out.print("Elija una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                opcion = -1; // lo que escribió no es un número
            }

            switch (opcion) {
                case 1 -> ingresarExpresion();
                case 2 -> verTokens();
                case 3 -> validar(false);
                case 4 -> validar(true);
                case 0 -> System.out.println("Hasta luego!");
                default -> System.out.println("Opción no válida, intente de nuevo.");
            }
        }
    }

    // Pide la expresión y la separa en tokens
    private static void ingresarExpresion() {
        System.out.print("Escriba la expresión (ej: (3 + x) * (4 - 2)): ");
        String texto = scanner.nextLine().trim();

        if (texto.isEmpty()) {
            System.out.println("No escribió nada.");
            return;
        }
        try {
            tokens = Analizador.tokenizar(texto);
            expresion = texto;
            System.out.println("Expresión guardada.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Muestra cada token con su tipo
    private static void verTokens() {
        if (!hayExpresion()) return;
        for (Token t : tokens) {
            System.out.println("  " + t.getValor() + "  ->  " + t.getTipo());
        }
    }

    // Valida los paréntesis, normal o paso a paso
    private static void validar(boolean pasoAPaso) {
        if (!hayExpresion()) return;

        boolean ok = pasoAPaso
                ? Analizador.validarPasoAPaso(tokens)
                : Analizador.parentesisBalanceados(tokens);

        System.out.println(ok ? "Resultado: paréntesis balanceados ✔"
                            : "Resultado: paréntesis NO balanceados ✘");
    }

    // Avisa si todavía no se ha ingresado una expresión
    private static boolean hayExpresion() {
        if (expresion.isEmpty()) {
            System.out.println("Primero ingrese una expresión (opción 1).");
            return false;
        }
        return true;
    }
}