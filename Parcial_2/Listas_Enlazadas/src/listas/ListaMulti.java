package listas;

import nodos.Nodo;
import nodos.NodoMulti;

/**
 * =============================================================================
 * CLASE: ListaMulti (Multilista de Personas)
 * =============================================================================
 * Estructura de datos avanzada que implementa el TDA Multilista (Lista Multienlazada)
 * extendiendo de {@link ListaEnlazada}.
 * 
 * ¿QUÉ ES Y CÓMO FUNCIONA?
 * En esta estructura, cada persona registrada existe como un ÚNICO objeto {@link NodoMulti}
 * físico en la memoria Heap. Sin embargo, ese mismo nodo está atravesado simultáneamente
 * por DOS cadenas de punteros independientes que permiten dos recorridos ordenados:
 * 
 *   1. CRITERIO 1 (Orden Alfabético A-Z por Nombre):
 *      Gobernado por el puntero heredado {@code cabeza} y los enlaces {@code siguiente}
 *      de cada nodo. Mantiene a las personas ordenadas lexicográficamente.
 * 
 *   2. CRITERIO 2 (Orden Numérico Ascendente por Edad):
 *      Gobernado por el puntero {@code cabezaEdad} y los enlaces {@code siguienteEdad}
 *      de cada nodo. Mantiene a las mismas personas ordenadas de menor a mayor edad.
 * 
 * PRINCIPIO DE INTEGRIDAD Y DESENLACE ATÓMICO:
 * Tanto la inserción como la eliminación garantizan sincronización atómica:
 * una persona se enlaza en ambos recorridos al crearse, y al eliminarse se desenlaza
 * de ambas dimensiones a la vez, manteniendo un único contador de {@code tamano}
 * y previniendo fugas de memoria o punteros huérfanos.
 * 
 * @author Cristian (Integrante 3)
 * @version 2.0 (Modelo de Personas: Nombre + Edad)
 */
public class ListaMulti extends ListaEnlazada {

    /**
     * Puntero al primer nodo del recorrido ordenado por edad (la persona más joven).
     * NOTA: La cabeza del recorrido alfabético es la variable {@code cabeza}, heredada de ListaEnlazada.
     */
    protected NodoMulti cabezaEdad;

    /**
     * Constructor: Inicializa una Multilista de personas vacía.
     * Ambas cabezas ('cabeza' heredada y 'cabezaEdad') inician en null, y el tamaño en 0.
     */
    public ListaMulti() {
        super();                  // Invoca el constructor de ListaEnlazada (cabeza = null, tamano = 0)
        this.cabezaEdad = null;   // Inicializa la cabeza secundaria de edades en null
    }

    // =========================================================================
    // MÉTODOS DE INSERCIÓN
    // =========================================================================

    /**
     * Inserta una nueva persona en la Multilista enlazándola simultáneamente
     * en ambos criterios: alfabéticamente por nombre y ascendentemente por edad.
     * 
     * @param nombre Nombre de la persona (no puede ser nulo ni estar en blanco).
     * @param edad   Edad de la persona en años (número entero positivo).
     * @throws IllegalArgumentException si el nombre es nulo o está vacío.
     */
    public void insertar(String nombre, int edad) {
        // ---------------------------------------------------------------------
        // PASO 0: Validación y creación del nodo físico único en memoria
        // ---------------------------------------------------------------------
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo ni vacío.");
        }

        // Creamos UN SOLO objeto físico en el Heap.
        // Sus dos referencias internas ('siguiente' y 'siguienteEdad') inician en null.
        NodoMulti nuevo = new NodoMulti(nombre.trim(), edad);

        // =====================================================================
        // CRITERIO 1: Inserción en la cadena Alfabética por Nombre (A-Z)
        // Usamos: 'cabeza' (heredada) y los punteros 'siguiente'
        // =====================================================================

        // CASO 1.A: La lista alfabética está vacía O el nuevo nombre va antes (o igual)
        // que el nombre de la cabeza actual según el orden lexicográfico (A-Z).
        // compareToIgnoreCase() devuelve <= 0 si nuevo.nombre es menor o igual a cabeza.nombre.
        if (cabeza == null || nuevo.getNombre().compareToIgnoreCase(((NodoMulti) cabeza).getNombre()) <= 0) {
            nuevo.setSiguiente(cabeza); // El nuevo nodo apunta a la que era la cabeza actual
            cabeza = nuevo;             // La referencia principal 'cabeza' ahora apunta al nuevo nodo
        } 
        // CASO 1.B: El nuevo nodo va en una posición intermedia o al final de la lista alfabética.
        else {
            // Creamos un puntero auxiliar para recorrer la lista sin perder la 'cabeza'
            NodoMulti actualAlfa = (NodoMulti) cabeza;

            // Avanzamos mientras haya un siguiente nodo Y ese siguiente nodo tenga un nombre
            // que alfabéticamente siga siendo menor o igual al que queremos insertar.
            while (actualAlfa.getSiguiente() != null &&
                   ((NodoMulti) actualAlfa.getSiguiente()).getNombre().compareToIgnoreCase(nuevo.getNombre()) <= 0) {
                actualAlfa = (NodoMulti) actualAlfa.getSiguiente();
            }

            // Al salir del ciclo, 'actualAlfa' es el nodo justo anterior a donde debe colocarse 'nuevo'.
            // Reconexión en 2 pasos:
            nuevo.setSiguiente(actualAlfa.getSiguiente()); // 1. 'nuevo' apunta al que estaba adelante
            actualAlfa.setSiguiente(nuevo);               // 2. 'actualAlfa' ahora apunta a 'nuevo'
        }

        // =====================================================================
        // CRITERIO 2: Inserción en la cadena por Edad (Menor a Mayor)
        // Usamos: 'cabezaEdad' y los punteros 'siguienteEdad'
        // =====================================================================

        // CASO 2.A: La lista de edades está vacía O el nuevo integrante es MÁS JOVEN (o igual)
        // que la persona ubicada actualmente en 'cabezaEdad'.
        if (cabezaEdad == null || edad <= cabezaEdad.getEdad()) {
            nuevo.setSiguienteEdad(cabezaEdad); // 'nuevo' apunta a la que era la cabeza más joven
            cabezaEdad = nuevo;                 // 'cabezaEdad' ahora apunta al nuevo integrante
        } 
        // CASO 2.B: El nuevo integrante tiene una edad intermedia o es el de mayor edad de todos.
        else {
            // Puntero auxiliar para recorrer la secuencia de edades
            NodoMulti actualEdad = cabezaEdad;

            // Avanzamos mientras exista un siguiente nodo por edad Y su edad siga siendo menor o igual
            while (actualEdad.getSiguienteEdad() != null &&
                   actualEdad.getSiguienteEdad().getEdad() <= edad) {
                actualEdad = actualEdad.getSiguienteEdad();
            }

            // Al terminar el recorrido, 'actualEdad' es el nodo con la edad inmediatamente menor.
            // Reconexión en 2 pasos:
            nuevo.setSiguienteEdad(actualEdad.getSiguienteEdad()); // 1. 'nuevo' apunta al siguiente por edad
            actualEdad.setSiguienteEdad(nuevo);                   // 2. 'actualEdad' ahora apunta a 'nuevo'
        }

        // ---------------------------------------------------------------------
        // PASO FINAL: Incrementamos el tamaño total de la estructura.
        // Solo se incrementa una vez porque es una sola persona física insertada.
        // ---------------------------------------------------------------------
        tamano++;
    }

    /**
     * Sobrecarga polimórfica obligatoria por heredar de {@link ListaEnlazada#insertar(int)}.
     * <p>
     * Permite que el menú general o funciones polimórficas (como {@code llenar(ListaEnlazada)})
     * operen con enteros sin romperse, asignando automáticamente un nombre con formato
     * {@code "Item-" + dato} y la edad {@code dato}.
     * </p>
     * 
     * @param dato Valor entero recibido (se almacena como edad).
     */
    @Override
    public void insertar(int dato) {
        this.insertar("Item-" + dato, dato);
    }

    // =========================================================================
    // MÉTODOS DE ELIMINACIÓN
    // =========================================================================

    /**
     * Elimina a una persona de la Multilista a partir de su nombre, garantizando
     * DESENLACE ATÓMICO: el nodo se desconecta tanto de la cadena alfabética
     * como de la cadena de edad de forma perfectamente sincronizada.
     * 
     * @param nombre Nombre de la persona a eliminar.
     * @return {@code true} si la persona existía y fue removida; {@code false} en caso contrario.
     */
    public boolean eliminar(String nombre) {
        // Validación: si la lista está vacía o el nombre es nulo, no hay nada que eliminar
        if (estaVacia() || nombre == null) {
            return false;
        }

        // Verificamos primero si la persona existe en la estructura antes de modificar punteros
        if (!buscar(nombre)) {
            return false;
        }

        // ---------------------------------------------------------------------
        // 1. DESENLACE EN CRITERIO 1: Cadena Alfabética
        // ---------------------------------------------------------------------
        // Caso 1.1: Si la persona a eliminar es la cabeza alfabética actual:
        if (((NodoMulti) cabeza).getNombre().equalsIgnoreCase(nombre)) {
            cabeza = cabeza.getSiguiente(); // 'cabeza' avanza al siguiente nodo de la lista
        } 
        // Caso 1.2: La persona está en una posición intermedia o al final:
        else {
            Nodo actualAlfa = cabeza;
            // Buscamos el nodo que está justo ANTES de la persona a eliminar
            while (actualAlfa.getSiguiente() != null &&
                   !((NodoMulti) actualAlfa.getSiguiente()).getNombre().equalsIgnoreCase(nombre)) {
                actualAlfa = actualAlfa.getSiguiente();
            }
            // Saltamos el nodo eliminado: el anterior ahora apunta al que le seguía al eliminado
            if (actualAlfa.getSiguiente() != null) {
                actualAlfa.setSiguiente(actualAlfa.getSiguiente().getSiguiente());
            }
        }

        // ---------------------------------------------------------------------
        // 2. DESENLACE EN CRITERIO 2: Cadena por Edad
        // ---------------------------------------------------------------------
        // Caso 2.1: Si la persona a eliminar es la cabeza de menor edad:
        if (cabezaEdad != null && cabezaEdad.getNombre().equalsIgnoreCase(nombre)) {
            cabezaEdad = cabezaEdad.getSiguienteEdad(); // 'cabezaEdad' avanza al siguiente más joven
        } 
        // Caso 2.2: La persona está en una posición intermedia o final en la secuencia de edades:
        else if (cabezaEdad != null) {
            NodoMulti actualEdad = cabezaEdad;
            // Buscamos el nodo justo anterior en la cadena de edades
            while (actualEdad.getSiguienteEdad() != null &&
                   !actualEdad.getSiguienteEdad().getNombre().equalsIgnoreCase(nombre)) {
                actualEdad = actualEdad.getSiguienteEdad();
            }
            // Saltamos el nodo eliminado en la cadena de edad
            if (actualEdad.getSiguienteEdad() != null) {
                actualEdad.setSiguienteEdad(actualEdad.getSiguienteEdad().getSiguienteEdad());
            }
        }

        // ---------------------------------------------------------------------
        // PASO FINAL: Decrementamos el tamaño unificado.
        // El objeto NodoMulti queda completamente desconectado de ambas cadenas
        // y el Garbage Collector de Java reclama su memoria automáticamente.
        // ---------------------------------------------------------------------
        tamano--;
        return true;
    }

    /**
     * Sobrecarga polimórfica obligatoria de {@link ListaEnlazada#eliminar(int)}.
     * <p>
     * Busca la primera persona cuya edad coincida con {@code dato} y procede
     * a eliminarla atómicamente de ambos criterios mediante su nombre.
     * </p>
     * 
     * @param dato Edad de la persona a buscar y eliminar.
     * @return {@code true} si se encontró y eliminó a la persona; {@code false} si no existía.
     */
    @Override
    public boolean eliminar(int dato) {
        if (estaVacia()) {
            return false;
        }

        // Recorremos la cadena ordenada por edad para localizar rápidamente a la persona
        NodoMulti actual = cabezaEdad;
        while (actual != null) {
            if (actual.getEdad() == dato) {
                // Encontrado: delegamos en eliminar(nombre) para desenlazar en ambos criterios
                return eliminar(actual.getNombre());
            }
            if (actual.getEdad() > dato) {
                // Optimización: como la lista está ordenada por edad, si encontramos una edad mayor,
                // sabemos con certeza matemática que el valor buscado ya no aparecerá adelante.
                break;
            }
            actual = actual.getSiguienteEdad();
        }
        return false;
    }

    // =========================================================================
    // MÉTODOS DE BÚSQUEDA
    // =========================================================================

    /**
     * Busca si existe una persona en la Multilista según su nombre.
     * <p>
     * Aplica <b>Corte Temprano (Early Exit)</b>: aprovecha que la cadena está ordenada
     * alfabéticamente para detener la búsqueda en cuanto se encuentre un nombre
     * alfabéticamente mayor al buscado, evitando recorrer nodos innecesarios.
     * </p>
     * 
     * @param nombre Nombre a buscar.
     * @return {@code true} si la persona existe; {@code false} en caso contrario.
     */
    public boolean buscar(String nombre) {
        if (nombre == null) return false;

        // Recorremos la cadena alfabética
        for (Nodo a = cabeza; a != null; a = a.getSiguiente()) {
            NodoMulti nm = (NodoMulti) a;
            int cmp = nm.getNombre().compareToIgnoreCase(nombre);
            if (cmp == 0) return true;  // ¡Encontrado exactamente!
            if (cmp > 0) return false;  // Corte temprano: ya nos pasamos alfabéticamente
        }
        return false;
    }

    /**
     * Busca si existe alguna persona registrada con la edad indicada.
     * <p>
     * Aplica <b>Corte Temprano</b>: recorre la secuencia de {@code cabezaEdad} y se detiene
     * anticipadamente en cuanto una edad supere la buscada.
     * </p>
     * 
     * @param edad Edad a buscar.
     * @return {@code true} si existe al menos una persona con esa edad; {@code false} si no.
     */
    public boolean buscarPorEdad(int edad) {
        for (NodoMulti a = cabezaEdad; a != null; a = a.getSiguienteEdad()) {
            if (a.getEdad() == edad) return true; // ¡Encontrado!
            if (a.getEdad() > edad) return false; // Corte temprano: las siguientes edades serán mayores
        }
        return false;
    }

    /**
     * Busca y retorna la referencia al nodo completo de una persona por nombre.
     * 
     * @param nombre Nombre buscado.
     * @return La instancia de {@link NodoMulti} encontrada, o {@code null} si no existe.
     */
    public NodoMulti buscarNodo(String nombre) {
        if (nombre == null) return null;
        for (Nodo a = cabeza; a != null; a = a.getSiguiente()) {
            NodoMulti nm = (NodoMulti) a;
            if (nm.getNombre().equalsIgnoreCase(nombre)) {
                return nm;
            }
        }
        return null;
    }

    // =========================================================================
    // VISUALIZACIÓN Y RECORRIDOS
    // =========================================================================

    /**
     * Imprime en consola ambos recorridos simultáneos de la Multilista,
     * permitiendo apreciar las dos dimensiones ordenadas sobre los mismos datos.
     */
    @Override
    public void mostrar() {
        if (estaVacia()) {
            System.out.println("[Multilista vacía]");
            return;
        }
        System.out.println("=== MULTILISTA DE PERSONAS (Tamaño: " + tamano + ") ===");
        mostrarAlfabetico();
        mostrarPorEdad();
    }

    /**
     * Imprime el recorrido bajo el Criterio 1: Orden Alfabético por Nombre (A-Z).
     * Recorre usando {@code cabeza} y los punteros {@code siguiente}.
     */
    public void mostrarAlfabetico() {
        StringBuilder sb = new StringBuilder("[Criterio 1 - Alfabético]: ");
        for (Nodo a = cabeza; a != null; a = a.getSiguiente()) {
            sb.append(a.toString()).append(" -> ");
        }
        sb.append("null");
        System.out.println(sb.toString());
    }

    /**
     * Imprime el recorrido bajo el Criterio 2: Orden Numérico por Edad (Menor a Mayor).
     * Recorre usando {@code cabezaEdad} y los punteros {@code siguienteEdad}.
     */
    public void mostrarPorEdad() {
        StringBuilder sb = new StringBuilder("[Criterio 2 - Por Edad]:   ");
        for (NodoMulti a = cabezaEdad; a != null; a = a.getSiguienteEdad()) {
            sb.append(a.toString()).append(" -> ");
        }
        sb.append("null");
        System.out.println(sb.toString());
    }

    /**
     * Vacía completamente la Multilista reseteando ambas cabezas a null y el tamaño a 0.
     */
    @Override
    public void limpiar() {
        super.limpiar();           // Limpia cabeza = null y tamano = 0
        this.cabezaEdad = null;    // Limpia la cabeza de edades
    }

    /**
     * Obtiene la referencia al nodo cabeza del recorrido alfabético.
     * @return El primer {@link NodoMulti} en orden A-Z.
     */
    public NodoMulti getCabezaAlfabetica() {
        return (NodoMulti) cabeza;
    }

    /**
     * Obtiene la referencia al nodo cabeza del recorrido por edad.
     * @return El {@link NodoMulti} de la persona más joven.
     */
    public NodoMulti getCabezaEdad() {
        return cabezaEdad;
    }

    // =========================================================================
    // AUDITORÍA DE CONSISTENCIA ESTRUCTURAL
    // =========================================================================

    /**
     * Auditoría formal de integridad matemática y estructural de la Multilista.
     * <p>
     * Este método recorre independientemente ambas dimensiones y valida 3 axiomas:
     * <ol>
     *   <li>Que la cadena alfabética contenga exactamente {@code tamano} nodos y cumpla el orden A-Z.</li>
     *   <li>Que la cadena de edades contenga exactamente {@code tamano} nodos y cumpla el orden creciente.</li>
     *   <li>Que la suma de todas las edades sea idéntica en ambos recorridos (conservación de datos en memoria).</li>
     * </ol>
     * </p>
     * 
     * @return {@code true} si la estructura es 100% consistente y no hay anomalías; {@code false} en caso contrario.
     */
    public boolean verificarConsistencia() {
        // --- 1. Auditar Cadena Alfabética ---
        int conteoAlfa = 0;
        long sumaEdadAlfa = 0;
        String previoNombre = null;

        for (Nodo a = cabeza; a != null; a = a.getSiguiente()) {
            conteoAlfa++;
            NodoMulti nm = (NodoMulti) a;
            sumaEdadAlfa += nm.getEdad();

            // Validar que el orden alfabético nunca retroceda
            if (previoNombre != null && nm.getNombre().compareToIgnoreCase(previoNombre) < 0) {
                return false; // Error: inconsistencia en el orden alfabético
            }
            previoNombre = nm.getNombre();
        }

        // --- 2. Auditar Cadena por Edad ---
        int conteoEdad = 0;
        long sumaEdadPorEdad = 0;
        int previaEdad = Integer.MIN_VALUE;

        for (NodoMulti a = cabezaEdad; a != null; a = a.getSiguienteEdad()) {
            conteoEdad++;
            sumaEdadPorEdad += a.getEdad();

            // Validar que la edad nunca disminuya a lo largo del recorrido
            if (a.getEdad() < previaEdad) {
                return false; // Error: inconsistencia en el orden numérico
            }
            previaEdad = a.getEdad();
        }

        // --- 3. Validación de Axiomas Finales ---
        // Deben coincidir: cantidad de nodos en alfa, cantidad de nodos en edad y suma de edades.
        return (conteoAlfa == tamano) && (conteoEdad == tamano) && (sumaEdadAlfa == sumaEdadPorEdad);
    }
}
