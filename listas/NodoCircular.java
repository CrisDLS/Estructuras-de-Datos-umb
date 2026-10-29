package listas;

public class NodoCircular {
    private Object dato;
    private NodoCircular siguiente;

    public NodoCircular(Object dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public Object getDato() { return dato; }
    public void setDato(Object dato) { this.dato = dato; }
    public NodoCircular getSiguiente() { return siguiente; }
    public void setSiguiente(NodoCircular siguiente) { this.siguiente = siguiente; }
}