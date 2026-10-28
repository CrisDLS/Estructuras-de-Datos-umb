package nodos;

public class NodoDoble<T> {
    public T dato;
    public NodoDoble<T> siguiente;
    public NodoDoble<T> anterior;

    public NodoDoble(T dato) {
        this.dato = dato;
    }
}
