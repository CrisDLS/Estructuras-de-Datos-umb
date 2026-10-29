package listas;

import nodos.Nodo;

/** Lista enlazada simple. insertar() agrega al final. */
public class ListaSimple extends ListaEnlazada {

    @Override
    public void insertar(int dato) {
        Nodo nuevo = new Nodo(dato);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo a = cabeza;
            while (a.getSiguiente() != null) a = a.getSiguiente();
            a.setSiguiente(nuevo);
        }
        tamano++;
    }

    public void insertarAlInicio(int dato) {
        Nodo nuevo = new Nodo(dato);
        nuevo.setSiguiente(cabeza);
        cabeza = nuevo;
        tamano++;
    }

    @Override
    public boolean eliminar(int dato) {
        if (cabeza == null) return false;
        if (cabeza.getDato() == dato) {
            cabeza = cabeza.getSiguiente();
            tamano--;
            return true;
        }
        Nodo a = cabeza;
        while (a.getSiguiente() != null) {
            if (a.getSiguiente().getDato() == dato) {
                a.setSiguiente(a.getSiguiente().getSiguiente());
                tamano--;
                return true;
            }
            a = a.getSiguiente();
        }
        return false;
    }

    public boolean eliminarAlInicio() {
        if (estaVacia()) return false;
        cabeza = cabeza.getSiguiente();
        tamano--;
        return true;
    }

    public boolean eliminarAlFinal() {
        if (estaVacia()) return false;
        if (cabeza.getSiguiente() == null) {
            cabeza = null;
            tamano = 0;
            return true;
        }
        Nodo a = cabeza;
        while (a.getSiguiente().getSiguiente() != null) {
            a = a.getSiguiente();
        }
        a.setSiguiente(null);
        tamano--;
        return true;
    }
}
