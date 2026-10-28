package listas;

import nodos.NodoDoble;

public class ListaCircular<T> {
    private NodoDoble<T> cabeza;
    private int tamano;

    public boolean estaVacia() { return cabeza == null; }
    public int tamano() { return tamano; }

    public void insertarFinal(T dato) {
        NodoDoble<T> n = new NodoDoble<>(dato);
        if (estaVacia()) {
            n.siguiente = n;
            n.anterior = n;
            cabeza = n;
        } else {
            NodoDoble<T> ultimo = cabeza.anterior;
            n.siguiente = cabeza;
            n.anterior = ultimo;
            ultimo.siguiente = n;
            cabeza.anterior = n;
        }
        tamano++;
    }

    public void insertarInicio(T dato) {
        insertarFinal(dato);
        cabeza = cabeza.anterior;
    }

    public boolean eliminar(T dato) {
        if (estaVacia()) return false;
        NodoDoble<T> actual = cabeza;
        do {
            if (actual.dato.equals(dato)) {
                if (tamano == 1) {
                    cabeza = null;
                } else {
                    actual.anterior.siguiente = actual.siguiente;
                    actual.siguiente.anterior = actual.anterior;
                    if (actual == cabeza) cabeza = actual.siguiente;
                }
                tamano--;
                return true;
            }
            actual = actual.siguiente;
        } while (actual != cabeza);
        return false;
    }

    public boolean buscar(T dato) {
        if (estaVacia()) return false;
        NodoDoble<T> a = cabeza;
        do {
            if (a.dato.equals(dato)) return true;
            a = a.siguiente;
        } while (a != cabeza);
        return false;
    }

    public String recorrer() {
        if (estaVacia()) return "(vacía)";
        StringBuilder sb = new StringBuilder();
        NodoDoble<T> a = cabeza;
        do {
            sb.append(a.dato).append(" -> ");
            a = a.siguiente;
        } while (a != cabeza);
        return sb.append("(vuelve a ").append(cabeza.dato).append(")").toString();
    }
}
