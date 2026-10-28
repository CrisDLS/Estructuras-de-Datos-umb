package listas;

import nodos.NodoDoble;

public class ListaDoble<T> {
    private NodoDoble<T> cabeza;
    private NodoDoble<T> cola;
    private int tamano;

    public boolean estaVacia() { return cabeza == null; }
    public int tamano() { return tamano; }

    public void insertarInicio(T dato) {
        NodoDoble<T> n = new NodoDoble<>(dato);
        if (estaVacia()) {
            cabeza = cola = n;
        } else {
            n.siguiente = cabeza;
            cabeza.anterior = n;
            cabeza = n;
        }
        tamano++;
    }

    public void insertarFinal(T dato) {
        NodoDoble<T> n = new NodoDoble<>(dato);
        if (estaVacia()) {
            cabeza = cola = n;
        } else {
            n.anterior = cola;
            cola.siguiente = n;
            cola = n;
        }
        tamano++;
    }

    public boolean eliminar(T dato) {
        NodoDoble<T> actual = cabeza;
        while (actual != null) {
            if (actual.dato.equals(dato)) {
                if (actual.anterior != null) actual.anterior.siguiente = actual.siguiente;
                else cabeza = actual.siguiente;
                if (actual.siguiente != null) actual.siguiente.anterior = actual.anterior;
                else cola = actual.anterior;
                tamano--;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    public boolean buscar(T dato) {
        for (NodoDoble<T> a = cabeza; a != null; a = a.siguiente)
            if (a.dato.equals(dato)) return true;
        return false;
    }

    public String recorrerAdelante() {
        StringBuilder sb = new StringBuilder("null <-> ");
        for (NodoDoble<T> a = cabeza; a != null; a = a.siguiente)
            sb.append(a.dato).append(" <-> ");
        return sb.append("null").toString();
    }

    public String recorrerAtras() {
        StringBuilder sb = new StringBuilder("null <-> ");
        for (NodoDoble<T> a = cola; a != null; a = a.anterior)
            sb.append(a.dato).append(" <-> ");
        return sb.append("null").toString();
    }
}
