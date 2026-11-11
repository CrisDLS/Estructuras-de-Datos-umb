// IMPLEMENTACION 2: arreglo ordenado de mayor a menor NUMERO de prioridad.
// El ultimo elemento es el siguiente en salir (extraer es rapido, sin desplazar).
public class ColaPrioridadArreglo<T> implements ColaPrioridad<T> {
    private Object[] datos;
    private int[] prioridades;
    private int tamano;

    public ColaPrioridadArreglo(int capacidad) {
        datos = new Object[capacidad];
        prioridades = new int[capacidad];
        tamano = 0;
    }

    @Override
    public void insertar(T dato, int prioridad) {
        if (tamano == datos.length) {
            System.out.println("Cola llena");
            return;
        }
        int i = tamano - 1;
        // desplazamos a la derecha los que deben salir ANTES que el nuevo
        while (i >= 0 && prioridades[i] <= prioridad) {
            datos[i + 1] = datos[i];
            prioridades[i + 1] = prioridades[i];
            i--;
        }
        datos[i + 1] = dato;
        prioridades[i + 1] = prioridad;
        tamano++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T extraer() {
        if (estaVacia()) return null;
        T dato = (T) datos[--tamano]; // prefijo: baja 1 ANTES de usarse
        datos[tamano] = null;
        return dato;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T frente() {
        return estaVacia() ? null : (T) datos[tamano - 1];
    }

    @Override
    public boolean estaVacia() { return tamano == 0; }

    @Override
    public int tamano() { return tamano; }

    @Override
    @SuppressWarnings("unchecked")
    public java.util.List<T> listar() {
        java.util.List<T> lista = new java.util.ArrayList<>();
        for (int i = tamano - 1; i >= 0; i--) lista.add((T) datos[i]);
        return lista;
    }
}
