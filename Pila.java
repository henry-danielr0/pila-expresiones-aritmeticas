// Pila implementada con nodos enlazados (último en entrar, primero en salir)
public class Pila {
    private Nodo tope;   // el plato de arriba
    private int tamano;

    public Pila() {
        tope = null;
        tamano = 0;
    }

    // Pone un token arriba de la pila
    public void push(Token t) {
        Nodo nuevo = new Nodo(t);
        nuevo.setSiguiente(tope);
        tope = nuevo;
        tamano++;
    }

    // Quita y devuelve el token de arriba (null si está vacía)
    public Token pop() {
        if (estaVacia()) {
            return null;
        }
        Token t = tope.getDato();
        tope = tope.getSiguiente();
        tamano--;
        return t;
    }

    // Mira el token de arriba sin quitarlo
    public Token peek() {
        return estaVacia() ? null : tope.getDato();
    }

    public boolean estaVacia() { return tope == null; }
    public int getTamano() { return tamano; }
}