// IMPLEMENTACION 1: lista enlazada ordenada por prioridad.
public class ColaPrioridadNodos<T> implements ColaPrioridad<T> {
    private Nodo<T> frente;
    private int tamano;

    public ColaPrioridadNodos() {
        frente = null;
        tamano = 0;
    }

    @Override
    public void insertar(T dato, int prioridad) {
        Nodo<T> nuevo = new Nodo<>(dato, prioridad);
        if (frente == null || prioridad < frente.prioridad) {
            nuevo.siguiente = frente;      // va al inicio
            frente = nuevo;
        } else {
            Nodo<T> actual = frente;
            // <= para que, con igual prioridad, salga primero el que llego antes (FIFO)
            while (actual.siguiente != null && actual.siguiente.prioridad <= prioridad) {
                actual = actual.siguiente;
            }
            nuevo.siguiente = actual.siguiente;
            actual.siguiente = nuevo;
        }
        tamano++; // posfijo: sube 1 despues de usarse
    }

    @Override
    public T extraer() {
        if (estaVacia()) return null;
        T dato = frente.dato;
        frente = frente.siguiente;
        tamano--;
        return dato;
    }

    @Override
    public T frente() {
        return estaVacia() ? null : frente.dato;
    }

    @Override
    public boolean estaVacia() { return frente == null; }

    @Override
    public int tamano() { return tamano; }

    @Override
    public java.util.List<T> listar() {
        java.util.List<T> lista = new java.util.ArrayList<>();
        Nodo<T> actual = frente;
        while (actual != null) {
            lista.add(actual.dato);
            actual = actual.siguiente;
        }
        return lista;
    }
}
