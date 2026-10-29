package listas;

import nodos.Nodo;

public class ListaCircular extends ListaEnlazada {

    @Override
    public void insertar(int dato) {
        Nodo nuevo = new Nodo(dato);
        if (estaVacia()) {
            cabeza = nuevo;
            nuevo.setSiguiente(cabeza); // apunta a sí misma
        } else {
            Nodo actual = cabeza;
            while (actual.getSiguiente() != cabeza) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
            nuevo.setSiguiente(cabeza);
        }
        tamano++;
    }

    @Override
    public boolean eliminar(int dato) {
        if (estaVacia()) return false;

        Nodo actual = cabeza;
        Nodo anterior = null;

        // Buscar el último para mantener referencia circular
        Nodo ultimo = cabeza;
        while (ultimo.getSiguiente() != cabeza) {
            ultimo = ultimo.getSiguiente();
        }

        do {
            if (actual.getDato() == dato) {
                if (actual == cabeza) {
                    if (tamano == 1) {
                        cabeza = null;
                    } else {
                        cabeza = cabeza.getSiguiente();
                        ultimo.setSiguiente(cabeza);
                    }
                } else {
                    anterior.setSiguiente(actual.getSiguiente());
                }
                tamano--;
                return true;
            }
            anterior = actual;
            actual = actual.getSiguiente();
        } while (actual != cabeza);

        return false;
    }

    @Override
    public void mostrar() {
        if (estaVacia()) { System.out.println("[lista vacía]"); return; }
        StringBuilder sb = new StringBuilder();
        Nodo actual = cabeza;
        do {
            sb.append(actual.getDato()).append(" -> ");
            actual = actual.getSiguiente();
        } while (actual != cabeza);
        sb.append("(cabeza)");
        System.out.println(sb);
    }

    @Override
    public boolean buscar(int dato) {
        if (estaVacia()) return false;
        Nodo actual = cabeza;
        do {
            if (actual.getDato() == dato) return true;
            actual = actual.getSiguiente();
        } while (actual != cabeza);
        return false;
    }
}
