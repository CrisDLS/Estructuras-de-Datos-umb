package listas;

import nodos.Nodo;
import nodos.NodoMulti;

/**
 * Implementación de una Multilista (Lista Multienlazada) de Personas que extiende de {@link ListaEnlazada}.
 * <p>
 * Cada persona registrada en la estructura está representada por una única instancia de {@link NodoMulti}
 * en memoria, enhebrada simultáneamente en <b>dos secuencias lógicas independientes</b>:
 * </p>
 * <ol>
 *   <li><b>Criterio 1 (Orden Alfabético A-Z por Nombre):</b> Gobernado por la referencia {@code cabeza}
 *       heredada de la superclase y el puntero {@code siguiente} de cada nodo.</li>
 *   <li><b>Criterio 2 (Orden Numérico Ascendente por Edad):</b> Gobernado por la referencia
 *       {@code cabezaEdad} y el puntero {@code siguienteEdad} de cada nodo.</li>
 * </ol>
 *
 * <p><b>Polimorfismo e Integridad:</b>
 * Para garantizar la compatibilidad con el resto del proyecto y no romper el contrato de {@link ListaEnlazada},
 * las firmas heredadas {@link #insertar(int)} y {@link #eliminar(int)} operan sobre la edad, asignando o
 * buscando nombres automáticamente. Toda inserción o eliminación mantiene sincronización atómica entre ambos órdenes.
 * </p>
 */
public class ListaMulti extends ListaEnlazada {

    /**
     * Puntero a la cabeza del recorrido ordenado por edad (menor a mayor).
     * La cabeza del orden alfabético es {@code cabeza}, heredada de {@link ListaEnlazada}.
     */
    protected NodoMulti cabezaEdad;

    /**
     * Construye una Multilista de personas vacía.
     */
    public ListaMulti() {
        super();
        this.cabezaEdad = null;
    }

    // =========================================================================
    // MÉTODOS DE INSERCIÓN
    // =========================================================================

    /**
     * Inserta una nueva persona en la Multilista enhebrándola atómicamente en ambos criterios:
     * alfabéticamente por nombre y ascendentemente por edad.
     *
     * @param nombre Nombre de la persona (no nulo).
     * @param edad Edad de la persona en años.
     */
    public void insertar(String nombre, int edad) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo ni vacío.");
        }

        NodoMulti nuevo = new NodoMulti(nombre.trim(), edad);

        // ---------------------------------------------------------------------
        // 1. INSERCIÓN EN CRITERIO 1: Orden Alfabético por Nombre (A-Z)
        // ---------------------------------------------------------------------
        if (cabeza == null || nuevo.getNombre().compareToIgnoreCase(((NodoMulti) cabeza).getNombre()) <= 0) {
            nuevo.setSiguiente(cabeza);
            cabeza = nuevo;
        } else {
            NodoMulti actualAlfa = (NodoMulti) cabeza;
            while (actualAlfa.getSiguiente() != null &&
                   ((NodoMulti) actualAlfa.getSiguiente()).getNombre().compareToIgnoreCase(nuevo.getNombre()) <= 0) {
                actualAlfa = (NodoMulti) actualAlfa.getSiguiente();
            }
            nuevo.setSiguiente(actualAlfa.getSiguiente());
            actualAlfa.setSiguiente(nuevo);
        }

        // ---------------------------------------------------------------------
        // 2. INSERCIÓN EN CRITERIO 2: Orden Numérico Ascendente por Edad
        // ---------------------------------------------------------------------
        if (cabezaEdad == null || edad <= cabezaEdad.getEdad()) {
            nuevo.setSiguienteEdad(cabezaEdad);
            cabezaEdad = nuevo;
        } else {
            NodoMulti actualEdad = cabezaEdad;
            while (actualEdad.getSiguienteEdad() != null &&
                   actualEdad.getSiguienteEdad().getEdad() <= edad) {
                actualEdad = actualEdad.getSiguienteEdad();
            }
            nuevo.setSiguienteEdad(actualEdad.getSiguienteEdad());
            actualEdad.setSiguienteEdad(nuevo);
        }

        tamano++;
    }

    /**
     * Sobrecarga polimórfica obligatoria de {@link ListaEnlazada#insertar(int)}.
     * <p>
     * Inserta un registro asignando automáticamente un nombre con formato {@code "Item-" + dato},
     * garantizando que el punto de entrada genérico {@code Main.java} continúe operando sin errores.
     * </p>
     *
     * @param dato Edad numérica a registrar.
     */
    @Override
    public void insertar(int dato) {
        this.insertar("Item-" + dato, dato);
    }

    // =========================================================================
    // MÉTODOS DE ELIMINACIÓN
    // =========================================================================

    /**
     * Elimina a una persona por su nombre, desenlazando el nodo de forma consistente
     * tanto de la cadena alfabética como de la cadena de edad.
     *
     * @param nombre Nombre de la persona a eliminar.
     * @return {@code true} si la persona existía y fue removida; {@code false} en caso contrario.
     */
    public boolean eliminar(String nombre) {
        if (estaVacia() || nombre == null) {
            return false;
        }

        // Verificamos si existe antes de desenlazar
        if (!buscar(nombre)) {
            return false;
        }

        // ---------------------------------------------------------------------
        // 1. DESENLACE EN CRITERIO 1: Cadena Alfabética
        // ---------------------------------------------------------------------
        if (((NodoMulti) cabeza).getNombre().equalsIgnoreCase(nombre)) {
            cabeza = cabeza.getSiguiente();
        } else {
            Nodo actualAlfa = cabeza;
            while (actualAlfa.getSiguiente() != null &&
                   !((NodoMulti) actualAlfa.getSiguiente()).getNombre().equalsIgnoreCase(nombre)) {
                actualAlfa = actualAlfa.getSiguiente();
            }
            if (actualAlfa.getSiguiente() != null) {
                actualAlfa.setSiguiente(actualAlfa.getSiguiente().getSiguiente());
            }
        }

        // ---------------------------------------------------------------------
        // 2. DESENLACE EN CRITERIO 2: Cadena por Edad
        // ---------------------------------------------------------------------
        if (cabezaEdad != null && cabezaEdad.getNombre().equalsIgnoreCase(nombre)) {
            cabezaEdad = cabezaEdad.getSiguienteEdad();
        } else if (cabezaEdad != null) {
            NodoMulti actualEdad = cabezaEdad;
            while (actualEdad.getSiguienteEdad() != null &&
                   !actualEdad.getSiguienteEdad().getNombre().equalsIgnoreCase(nombre)) {
                actualEdad = actualEdad.getSiguienteEdad();
            }
            if (actualEdad.getSiguienteEdad() != null) {
                actualEdad.setSiguienteEdad(actualEdad.getSiguienteEdad().getSiguienteEdad());
            }
        }

        tamano--;
        return true;
    }

    /**
     * Sobrecarga polimórfica obligatoria de {@link ListaEnlazada#eliminar(int)}.
     * <p>
     * Busca la primera persona cuya edad coincida con {@code dato} y procede
     * a eliminarla atómicamente de ambos criterios.
     * </p>
     *
     * @param dato Edad de la persona a eliminar.
     * @return {@code true} si se encontró y eliminó a la persona; {@code false} si no existía.
     */
    @Override
    public boolean eliminar(int dato) {
        if (estaVacia()) {
            return false;
        }

        // Localizar a la primera persona con esa edad en la cadena de edades
        NodoMulti actual = cabezaEdad;
        while (actual != null) {
            if (actual.getEdad() == dato) {
                return eliminar(actual.getNombre());
            }
            if (actual.getEdad() > dato) {
                // Como está ordenado por edad, no existirá más adelante
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
     * Busca si existe una persona en la lista según su nombre.
     * <p>
     * Aprovecha el orden alfabético para realizar corte temprano si el nombre
     * actual supera lexicográficamente al buscado.
     * </p>
     *
     * @param nombre Nombre a buscar.
     * @return {@code true} si existe una persona con ese nombre; {@code false} si no.
     */
    public boolean buscar(String nombre) {
        if (nombre == null) return false;
        for (Nodo a = cabeza; a != null; a = a.getSiguiente()) {
            NodoMulti nm = (NodoMulti) a;
            int cmp = nm.getNombre().compareToIgnoreCase(nombre);
            if (cmp == 0) return true;
            if (cmp > 0) return false; // Corte temprano alfabético
        }
        return false;
    }

    /**
     * Busca si existe alguna persona registrada con la edad indicada.
     * <p>
     * Aprovecha el orden numérico de {@code cabezaEdad} para terminar anticipadamente
     * cuando se supera la edad especificada.
     * </p>
     *
     * @param edad Edad a buscar.
     * @return {@code true} si existe al menos una persona con esa edad; {@code false} en caso contrario.
     */
    public boolean buscarPorEdad(int edad) {
        for (NodoMulti a = cabezaEdad; a != null; a = a.getSiguienteEdad()) {
            if (a.getEdad() == edad) return true;
            if (a.getEdad() > edad) return false; // Corte temprano por edad
        }
        return false;
    }

    /**
     * Obtiene la referencia al nodo de una persona por nombre.
     *
     * @param nombre Nombre buscado.
     * @return Instancia de {@link NodoMulti} correspondiente, o {@code null} si no existe.
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
    // VISUALIZACIÓN Y ACCESO
    // =========================================================================

    /**
     * Imprime en consola ambos recorridos simultáneos de la Multilista.
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
     * Imprime el recorrido bajo el Criterio 1 (Orden Alfabético).
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
     * Imprime el recorrido bajo el Criterio 2 (Orden por Edad).
     */
    public void mostrarPorEdad() {
        StringBuilder sb = new StringBuilder("[Criterio 2 - Por Edad]:   ");
        for (NodoMulti a = cabezaEdad; a != null; a = a.getSiguienteEdad()) {
            sb.append(a.toString()).append(" -> ");
        }
        sb.append("null");
        System.out.println(sb.toString());
    }

    @Override
    public void limpiar() {
        super.limpiar();
        this.cabezaEdad = null;
    }

    public NodoMulti getCabezaAlfabetica() {
        return (NodoMulti) cabeza;
    }

    public NodoMulti getCabezaEdad() {
        return cabezaEdad;
    }

    // =========================================================================
    // AUDITORÍA DE CONSISTENCIA
    // =========================================================================

    /**
     * Auditoría formal de integridad estructural.
     * <p>
     * Verifica que:
     * <ol>
     *   <li>El recorrido alfabético contenga exactamente {@code tamano} nodos y respete el orden A-Z.</li>
     *   <li>El recorrido por edad contenga exactamente {@code tamano} nodos y respete el orden numérico creciente.</li>
     *   <li>Ambas secuencias compartan la misma suma de edades (conservación de datos en memoria).</li>
     * </ol>
     * </p>
     *
     * @return {@code true} si la estructura es 100% íntegra y consistente; {@code false} si hay anomalías.
     */
    public boolean verificarConsistencia() {
        int conteoAlfa = 0;
        long sumaEdadAlfa = 0;
        String previoNombre = null;

        for (Nodo a = cabeza; a != null; a = a.getSiguiente()) {
            conteoAlfa++;
            NodoMulti nm = (NodoMulti) a;
            sumaEdadAlfa += nm.getEdad();

            if (previoNombre != null && nm.getNombre().compareToIgnoreCase(previoNombre) < 0) {
                return false; // Violación de orden alfabético
            }
            previoNombre = nm.getNombre();
        }

        int conteoEdad = 0;
        long sumaEdadPorEdad = 0;
        int previaEdad = Integer.MIN_VALUE;

        for (NodoMulti a = cabezaEdad; a != null; a = a.getSiguienteEdad()) {
            conteoEdad++;
            sumaEdadPorEdad += a.getEdad();

            if (a.getEdad() < previaEdad) {
                return false; // Violación de orden por edad
            }
            previaEdad = a.getEdad();
        }

        return (conteoAlfa == tamano) && (conteoEdad == tamano) && (sumaEdadAlfa == sumaEdadPorEdad);
    }
}
