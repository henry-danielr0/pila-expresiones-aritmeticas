// Nodo de la pila: guarda un token y apunta al nodo que está debajo
public class Nodo {
    private Token dato;
    private Nodo siguiente;

    public Nodo(Token dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public Token getDato() { return dato; }
    public Nodo getSiguiente() { return siguiente; }
    public void setSiguiente(Nodo siguiente) { this.siguiente = siguiente; }
}