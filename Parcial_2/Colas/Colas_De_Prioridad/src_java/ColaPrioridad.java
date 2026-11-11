// INTERFAZ: define QUE se puede hacer, no COMO.
public interface ColaPrioridad<T> {
    void insertar(T dato, int prioridad); // menor numero = mayor prioridad
    T extraer();                          // saca el de mayor prioridad
    T frente();                           // ve el siguiente sin sacarlo
    boolean estaVacia();
    int tamano();
    java.util.List<T> listar();           // para dibujar la cola sin sacar nada
}
