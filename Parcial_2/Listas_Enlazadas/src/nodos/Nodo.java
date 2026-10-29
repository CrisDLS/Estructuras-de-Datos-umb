package nodos;

/** Nodo de lista simple: dato + referencia al siguiente. */
public class Nodo {
    private int dato;
    private Nodo siguiente;

    public Nodo(int dato) { this.dato = dato; }

    public int getDato() { return dato; }
    public void setDato(int dato) { this.dato = dato; }
    public Nodo getSiguiente() { return siguiente; }
    public void setSiguiente(Nodo siguiente) { this.siguiente = siguiente; }
}
