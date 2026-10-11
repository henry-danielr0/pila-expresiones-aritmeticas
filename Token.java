// Representa una pieza de la expresión: número, variable, operador o paréntesis
public class Token {
    private String valor;
    private String tipo; // "NUMERO", "VARIABLE", "OPERADOR", "PARENTESIS"

    public Token(String valor, String tipo) {
        this.valor = valor;
        this.tipo = tipo;
    }

    public String getValor() { return valor; }
    public String getTipo() { return tipo; }

    @Override
    public String toString() { return valor; }
}