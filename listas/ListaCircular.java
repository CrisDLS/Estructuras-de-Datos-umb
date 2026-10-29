package listas;

public class ListaCircular {
    private NodoCircular cabeza;
    private int tamano;

    public ListaCircular() {
        cabeza = null;
        tamano = 0;
    }

    public boolean estaVacia() { return cabeza == null; }
    public int tamanio() { return tamano; }

    public void insertarInicio(Object dato) {
        NodoCircular nuevo = new NodoCircular(dato);
        if (estaVacia()) {
            cabeza = nuevo;
            cabeza.setSiguiente(cabeza);
        } else {
            NodoCircular ultimo = cabeza;
            while (ultimo.getSiguiente() != cabeza) ultimo = ultimo.getSiguiente();
            nuevo.setSiguiente(cabeza);
            ultimo.setSiguiente(nuevo);
            cabeza = nuevo;
        }
        tamano++;
    }

    public void insertarFinal(Object dato) {
        if (estaVacia()) { insertarInicio(dato); return; }
        NodoCircular nuevo = new NodoCircular(dato);
        NodoCircular ultimo = cabeza;
        while (ultimo.getSiguiente() != cabeza) ultimo = ultimo.getSiguiente();
        ultimo.setSiguiente(nuevo);
        nuevo.setSiguiente(cabeza);
        tamano++;
    }

    public boolean eliminar(Object dato) {
        if (estaVacia()) return false;
        if (cabeza.getDato().equals(dato)) {
            if (tamano == 1) { cabeza = null; tamano = 0; return true; }
            NodoCircular ultimo = cabeza;
            while (ultimo.getSiguiente() != cabeza) ultimo = ultimo.getSiguiente();
            cabeza = cabeza.getSiguiente();
            ultimo.setSiguiente(cabeza);
            tamano--;
            return true;
        }
        NodoCircular actual = cabeza;
        while (actual.getSiguiente() != cabeza) {
            if (actual.getSiguiente().getDato().equals(dato)) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                tamano--;
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    public void limpiar() { cabeza = null; tamano = 0; }
    public void mostrar() {
        if (estaVacia()) { System.out.println("Vacia"); return; }
        NodoCircular actual = cabeza;
        do {
            System.out.print(actual.getDato() + " -> ");
            actual = actual.getSiguiente();
        } while (actual != cabeza);
        System.out.println("(circular)");
    }
       public void insertar(int dato) { insertarFinal(dato); }
    public void insertar(Object dato) { insertarFinal(dato); }
    
    public NodoCircular buscar(int dato) { 
        if (estaVacia()) return null;
        NodoCircular actual = cabeza;
        do {
            if (actual.getDato().equals(dato)) return actual;
            actual = actual.getSiguiente();
        } while (actual != cabeza);
        return null;
    }

    // ===== ALIAS =====
    public void insertarAlInicio(Object dato) { insertarInicio(dato); }
    public Object eliminarAlInicio() {
        if (estaVacia()) return null;
        Object d = cabeza.getDato();
        eliminar(d);
        return d;
    }
    public Object eliminarAlFinal() {
        if (estaVacia()) return null;
        NodoCircular ultimo = cabeza;
        while (ultimo.getSiguiente() != cabeza) ultimo = ultimo.getSiguiente();
        Object d = ultimo.getDato();
        eliminar(d);
        return d;
    }
    public void vaciar() { limpiar(); }
}