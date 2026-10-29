package listas;

public class ListaDoble {
    private NodoDoble cabeza;
    private NodoDoble cola;
    private int tamano;

    public ListaDoble() {
        cabeza = null;
        cola = null;
        tamano = 0;
    }

    public boolean estaVacia() { return cabeza == null; }
    public int tamanio() { return tamano; }

    // Metodos originales 
    public void insertarInicio(Object dato) {
        NodoDoble nuevo = new NodoDoble(dato);
        if (estaVacia()) {
            cabeza = cola = nuevo;
        } else {
            nuevo.setSiguiente(cabeza);
            cabeza.setAnterior(nuevo);
            cabeza = nuevo;
        }
        tamano++;
    }

    public void insertarFinal(Object dato) {
        NodoDoble nuevo = new NodoDoble(dato);
        if (estaVacia()) {
            cabeza = cola = nuevo;
        } else {
            cola.setSiguiente(nuevo);
            nuevo.setAnterior(cola);
            cola = nuevo;
        }
        tamano++;
    }

    public boolean eliminar(Object dato) {
        if (estaVacia()) return false;
        NodoDoble actual = cabeza;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                if (actual == cabeza) cabeza = actual.getSiguiente();
                if (actual == cola) cola = actual.getAnterior();
                if (actual.getAnterior() != null) actual.getAnterior().setSiguiente(actual.getSiguiente());
                if (actual.getSiguiente() != null) actual.getSiguiente().setAnterior(actual.getAnterior());
                tamano--;
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    public void limpiar() {
        cabeza = null;
        cola = null;
        tamano = 0;
    }

    public void mostrar() {
        NodoDoble actual = cabeza;
        while (actual != null) {
            System.out.print(actual.getDato() + " <-> ");
            actual = actual.getSiguiente();
        }
        System.out.println("NULL");
    }

    public void mostrarReversa() {
        NodoDoble actual = cola;
        while (actual != null) {
            System.out.print(actual.getDato() + " <-> ");
            actual = actual.getAnterior();
        }
        System.out.println("NULL");
    }
    // Para que compile con PruebasListas.java que usa insertar(int)
    public void insertar(int dato) { insertarFinal(dato); }
    public void insertar(Object dato) { insertarFinal(dato); }
    
    public NodoDoble buscar(int dato) { 
        NodoDoble actual = cabeza;
        while (actual != null) {
            if (actual.getDato().equals(dato)) return actual;
            actual = actual.getSiguiente();
        }
        return null;
    }
    public NodoDoble buscar(Object dato) { return buscar((int)dato); }

    // ===== ALIAS QUE TE FALTABAN PARA QUE COMPILE =====
    public void insertarAlInicio(Object dato) { insertarInicio(dato); }
    
    public Object eliminarAlInicio() {
        if (estaVacia()) return null;
        Object d = cabeza.getDato();
        eliminar(d);
        return d;
    }

    public Object eliminarAlFinal() {
        if (estaVacia()) return null;
        Object d = cola.getDato();
        eliminar(d);
        return d;
    }

    public void vaciar() { limpiar(); }
    public void mostrarInverso() { mostrarReversa(); }
}