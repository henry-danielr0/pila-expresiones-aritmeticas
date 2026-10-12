import java.util.ArrayList;

// Analiza expresiones aritméticas usando una pila
public class Analizador {

    // Separa el texto en tokens (números, variables, operadores y paréntesis)
    public static ArrayList<Token> tokenizar(String expresion) {
        ArrayList<Token> tokens = new ArrayList<>();
        int i = 0;

        while (i < expresion.length()) {
            char c = expresion.charAt(i);

            if (Character.isWhitespace(c)) {
                i++; // los espacios se ignoran

            } else if (Character.isDigit(c) || c == '.') {
                // Número literal: junta todos los dígitos seguidos (ej: 125 o 3.14)
                StringBuilder sb = new StringBuilder();
                while (i < expresion.length()
                        && (Character.isDigit(expresion.charAt(i)) || expresion.charAt(i) == '.')) {
                    sb.append(expresion.charAt(i));
                    i++;
                }
                tokens.add(new Token(sb.toString(), "NUMERO"));

            } else if (Character.isLetter(c)) {
                // Variable: letras, y después letras, números o guion bajo (ej: x, total1)
                StringBuilder sb = new StringBuilder();
                while (i < expresion.length()
                        && (Character.isLetterOrDigit(expresion.charAt(i)) || expresion.charAt(i) == '_')) {
                    sb.append(expresion.charAt(i));
                    i++;
                }
                tokens.add(new Token(sb.toString(), "VARIABLE"));

            } else if (c == '(' || c == ')') {
                tokens.add(new Token(String.valueOf(c), "PARENTESIS"));
                i++;

            } else if ("+-*/^".indexOf(c) >= 0) {
                tokens.add(new Token(String.valueOf(c), "OPERADOR"));
                i++;

            } else {
                throw new IllegalArgumentException("Carácter no válido: " + c);
            }
        }
        return tokens;
    }

    // Revisa que cada "(" tenga su ")" usando una pila
    public static boolean parentesisBalanceados(ArrayList<Token> tokens) {
        Pila pila = new Pila();

        for (Token t : tokens) {
            if (t.getTipo().equals("PARENTESIS")) {
                if (t.getValor().equals("(")) {
                    pila.push(t);               // abre: apila
                } else {
                    if (pila.estaVacia()) {
                        return false;           // cierra sin haber abierto
                    }
                    pila.pop();                 // cierra: desapila
                }
            }
        }
        return pila.estaVacia();                // si sobró alguno, faltó cerrar
    }

        // Igual que parentesisBalanceados, pero mostrando la pila en cada paso
    public static boolean validarPasoAPaso(ArrayList<Token> tokens) {
        Pila pila = new Pila();

        for (Token t : tokens) {
            if (t.getTipo().equals("PARENTESIS")) {
                if (t.getValor().equals("(")) {
                    pila.push(t);
                    System.out.println("Leo '(' -> push");
                } else {
                    if (pila.estaVacia()) {
                        System.out.println("Leo ')' pero la pila está vacía -> ERROR");
                        return false;
                    }
                    pila.pop();
                    System.out.println("Leo ')' -> pop");
                }
                pila.mostrar();
            }
        }

        if (pila.estaVacia()) {
            System.out.println("La pila quedó vacía -> expresión balanceada");
            return true;
        }
        System.out.println("Sobraron '(' en la pila -> ERROR");
        return false;
    }
    
        
    
}