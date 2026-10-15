// NODO: una "caja" con dato, prioridad y enlace al siguiente.
public class Nodo<T> {
    T dato;
    int prioridad;
    Nodo<T> siguiente;

    public Nodo(T dato, int prioridad) {
        this.dato = dato;
        this.prioridad = prioridad;
        this.siguiente = null;
    }
}
