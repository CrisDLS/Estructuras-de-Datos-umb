package listas;

import nodos.NodoDoble;

/**
 * Lista doblemente enlazada.
 * Hereda de ListaEnlazada y gestiona referencias bidireccionales
 * mediante punteros cabeza y cola.
 */
public class ListaDoble extends ListaEnlazada {
    private NodoDoble cola;

    public ListaDoble() {
        super();
        this.cola = null;
    }

    @Override
    public void insertar(int dato) {
        NodoDoble nuevo = new NodoDoble(dato);
        if (estaVacia()) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.setSiguiente(nuevo);
            nuevo.setAnterior(cola);
            cola = nuevo;
        }
        tamano++;
    }

    public void insertarAlInicio(int dato) {
        NodoDoble nuevo = new NodoDoble(dato);
        if (estaVacia()) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            nuevo.setSiguiente(cabeza);
            ((NodoDoble) cabeza).setAnterior(nuevo);
            cabeza = nuevo;
        }
        tamano++;
    }

    @Override
    public boolean eliminar(int dato) {
        if (estaVacia()) return false;
        
        NodoDoble actual = (NodoDoble) cabeza;
        while (actual != null) {
            if (actual.getDato() == dato) {
                if (actual.getAnterior() != null) {
                    actual.getAnterior().setSiguiente(actual.getSiguiente());
                } else {
                    cabeza = actual.getSiguiente();
                }
                if (actual.getSiguiente() != null) {
                    ((NodoDoble) actual.getSiguiente()).setAnterior(actual.getAnterior());
                } else {
                    cola = actual.getAnterior();
                }
                tamano--;
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    public boolean eliminarAlInicio() {
        if (estaVacia()) return false;
        if (cabeza == cola) {
            cabeza = null;
            cola = null;
        } else {
            cabeza = cabeza.getSiguiente();
            ((NodoDoble) cabeza).setAnterior(null);
        }
        tamano--;
        return true;
    }

    public boolean eliminarAlFinal() {
        if (estaVacia()) return false;
        if (cabeza == cola) {
            cabeza = null;
            cola = null;
        } else {
            cola = cola.getAnterior();
            cola.setSiguiente(null);
        }
        tamano--;
        return true;
    }

    public void mostrarInverso() {
        if (estaVacia()) { System.out.println("[lista vacía]"); return; }
        StringBuilder sb = new StringBuilder("null <- ");
        for (NodoDoble a = cola; a != null; a = a.getAnterior()) {
            sb.append(a.getDato()).append(" <-> ");
        }
        System.out.println(sb + "[cabeza]");
    }

    @Override
    public void limpiar() {
        super.limpiar();
        cola = null;
    }

    public NodoDoble getCola() {
        return cola;
    }
}
