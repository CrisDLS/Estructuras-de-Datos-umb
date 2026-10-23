package listas;

import nodos.Nodo;

/**
 * Clase base abstracta. Define el contrato (insertar, eliminar) y deja
 * implementaciones por defecto de recorrido para listas lineales.
 * Las subclases pueden sobrescribir buscar() y mostrar() si su estructura
 * es distinta (por ejemplo, la circular).
 */
public abstract class ListaEnlazada {
    protected Nodo cabeza;
    protected int tamano;

    public abstract void insertar(int dato);
    public abstract boolean eliminar(int dato);

    public boolean estaVacia() { return tamano == 0; }
    public int getTamano() { return tamano; }

    public void limpiar() {
        cabeza = null;
        tamano = 0;
    }

    public boolean buscar(int dato) {
        for (Nodo a = cabeza; a != null; a = a.getSiguiente()) {
            if (a.getDato() == dato) return true;
        }
        return false;
    }

    public void mostrar() {
        if (estaVacia()) { System.out.println("[lista vacía]"); return; }
        StringBuilder sb = new StringBuilder();
        for (Nodo a = cabeza; a != null; a = a.getSiguiente()) {
            sb.append(a.getDato()).append(" -> ");
        }
        System.out.println(sb + "null");
    }
}
