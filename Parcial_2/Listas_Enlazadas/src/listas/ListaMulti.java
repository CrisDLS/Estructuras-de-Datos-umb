package listas;

import nodos.Nodo;
import nodos.NodoMulti;

/**
 * Implementación de una Multilista (Lista Multienlazada) que extiende de {@link ListaEnlazada}.
 * <p>
 * En esta estructura de datos avanzada, cada nodo individual ({@link NodoMulti}) pertenece
 * simultáneamente a <b>dos secuencias lógicas independientes</b> dentro de la misma memoria:
 * </p>
 * <ol>
 *   <li><b>Criterio Primario (Orden Cronológico / Inserción):</b> Utiliza la referencia
 *       {@code cabeza} heredada y el puntero {@code siguiente} de cada nodo. Preserva el orden
 *       exacto en el que los elementos fueron ingresados (FIFO).</li>
 *   <li><b>Criterio Secundario (Orden Numérico Ascendente):</b> Utiliza la referencia
 *       {@code cabezaSecundaria} y el puntero {@code siguienteSecundario}. Mantiene los mismos
 *       nodos ordenados de menor a mayor automáticamente durante la inserción.</li>
 * </ol>
 *
 * <p><b>Principio de Integridad y Consistencia:</b>
 * Tanto la inserción como la eliminación garantizan sincronización atómica: un nodo insertado
 * se enlaza en ambos recorridos, y un nodo eliminado se desenlaza de ambos caminos de navegación,
 * manteniendo el conteo unificado de {@code tamano}.
 * </p>
 *
 * @author Cristian (Integrante 3)
 * @version 1.0
 */
public class ListaMulti extends ListaEnlazada {

    /**
     * Puntero inicial para el recorrido bajo el Criterio 2 (Orden Ascendente).
     * El puntero para el Criterio 1 es {@code cabeza}, heredado de {@link ListaEnlazada}.
     */
    protected NodoMulti cabezaSecundaria;

    /**
     * Construye una Multilista vacía inicializando ambas cabezas en {@code null}
     * y el tamaño en 0.
     */
    public ListaMulti() {
        super();
        this.cabezaSecundaria = null;
    }

    /**
     * Inserta un nuevo elemento en la Multilista integrándolo simultáneamente
     * en ambos criterios de ordenamiento:
     * <ul>
     *   <li>En el <b>Criterio Primario</b> se inserta al final (orden cronológico).</li>
     *   <li>En el <b>Criterio Secundario</b> se inserta ordenadamente de menor a mayor.</li>
     * </ul>
     *
     * @param dato Valor entero a insertar.
     */
    @Override
    public void insertar(int dato) {
        NodoMulti nuevo = new NodoMulti(dato);

        // -------------------------------------------------------------
        // 1. INSERCIÓN EN CRITERIO 1 (Cronológico / Al Final)
        // -------------------------------------------------------------
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo actualPrim = cabeza;
            while (actualPrim.getSiguiente() != null) {
                actualPrim = actualPrim.getSiguiente();
            }
            actualPrim.setSiguiente(nuevo);
        }

        // -------------------------------------------------------------
        // 2. INSERCIÓN EN CRITERIO 2 (Orden Numérico Ascendente)
        // -------------------------------------------------------------
        if (cabezaSecundaria == null || dato < cabezaSecundaria.getDato()) {
            // Caso A: La lista secundaria está vacía o el nuevo dato es menor que la cabeza
            nuevo.setSiguienteSecundario(cabezaSecundaria);
            cabezaSecundaria = nuevo;
        } else {
            // Caso B: Buscar posición intermedia o final según valor numérico
            NodoMulti actualSec = cabezaSecundaria;
            while (actualSec.getSiguienteSecundario() != null &&
                   actualSec.getSiguienteSecundario().getDato() <= dato) {
                actualSec = actualSec.getSiguienteSecundario();
            }
            nuevo.setSiguienteSecundario(actualSec.getSiguienteSecundario());
            actualSec.setSiguienteSecundario(nuevo);
        }

        tamano++;
    }

    /**
     * Elimina la primera ocurrencia de un dato en la Multilista, asegurando
     * el desenlace consistente en ambos recorridos (primario y secundario).
     *
     * @param dato Valor entero a eliminar.
     * @return {@code true} si el dato existía y fue removido exitosamente; {@code false} si no se encontró.
     */
    @Override
    public boolean eliminar(int dato) {
        if (estaVacia()) {
            return false;
        }

        // Primero verificamos si el dato existe mediante el criterio primario
        if (!buscar(dato)) {
            return false;
        }

        // -------------------------------------------------------------
        // 1. DESENLACE EN CRITERIO 1 (Cadena Primaria)
        // -------------------------------------------------------------
        if (cabeza.getDato() == dato) {
            cabeza = cabeza.getSiguiente();
        } else {
            Nodo actualPrim = cabeza;
            while (actualPrim.getSiguiente() != null && actualPrim.getSiguiente().getDato() != dato) {
                actualPrim = actualPrim.getSiguiente();
            }
            if (actualPrim.getSiguiente() != null) {
                actualPrim.setSiguiente(actualPrim.getSiguiente().getSiguiente());
            }
        }

        // -------------------------------------------------------------
        // 2. DESENLACE EN CRITERIO 2 (Cadena Secundaria)
        // -------------------------------------------------------------
        if (cabezaSecundaria.getDato() == dato) {
            cabezaSecundaria = cabezaSecundaria.getSiguienteSecundario();
        } else {
            NodoMulti actualSec = cabezaSecundaria;
            while (actualSec.getSiguienteSecundario() != null &&
                   actualSec.getSiguienteSecundario().getDato() != dato) {
                actualSec = actualSec.getSiguienteSecundario();
            }
            if (actualSec.getSiguienteSecundario() != null) {
                actualSec.setSiguienteSecundario(actualSec.getSiguienteSecundario().getSiguienteSecundario());
            }
        }

        tamano--;
        return true;
    }

    /**
     * Busca un valor utilizando el recorrido secundario (ordenado).
     * <p>
     * Al estar ordenado ascendentemente, puede optimizar la búsqueda terminando
     * de forma temprana si encuentra un valor mayor al buscado.
     * </p>
     *
     * @param dato Valor a buscar en el recorrido secundario.
     * @return {@code true} si se encuentra el valor, {@code false} en caso contrario.
     */
    public boolean buscarCriterioSecundario(int dato) {
        NodoMulti actual = cabezaSecundaria;
        while (actual != null) {
            if (actual.getDato() == dato) {
                return true;
            }
            if (actual.getDato() > dato) {
                // Optimización por orden ascendente: no existirá más adelante
                return false;
            }
            actual = actual.getSiguienteSecundario();
        }
        return false;
    }

    /**
     * Muestra la representación de la Multilista en consola, exhibiendo
     * los dos órdenes de recorrido simultáneos para inspección visual.
     */
    @Override
    public void mostrar() {
        if (estaVacia()) {
            System.out.println("[Multilista vacía]");
            return;
        }
        System.out.println("=== MULTILISTA (Tamaño: " + tamano + ") ===");
        mostrarCriterioPrimario();
        mostrarCriterioSecundario();
    }

    /**
     * Imprime en consola únicamente el recorrido del Criterio Primario (Orden de Inserción).
     */
    public void mostrarCriterioPrimario() {
        StringBuilder sb = new StringBuilder("[Criterio 1 - Inserción]: ");
        for (Nodo a = cabeza; a != null; a = a.getSiguiente()) {
            sb.append(a.getDato()).append(" -> ");
        }
        sb.append("null");
        System.out.println(sb.toString());
    }

    /**
     * Imprime en consola únicamente el recorrido del Criterio Secundario (Orden Ascendente).
     */
    public void mostrarCriterioSecundario() {
        StringBuilder sb = new StringBuilder("[Criterio 2 - Ascendente]: ");
        for (NodoMulti a = cabezaSecundaria; a != null; a = a.getSiguienteSecundario()) {
            sb.append(a.getDato()).append(" -> ");
        }
        sb.append("null");
        System.out.println(sb.toString());
    }

    /**
     * Reinicia la multilista desvinculando ambas cabezas y restableciendo el tamaño a 0.
     */
    @Override
    public void limpiar() {
        super.limpiar();
        this.cabezaSecundaria = null;
    }

    /**
     * Obtiene la referencia a la cabeza del recorrido secundario.
     *
     * @return El {@link NodoMulti} inicial del Criterio 2, o {@code null} si está vacía.
     */
    public NodoMulti getCabezaSecundaria() {
        return cabezaSecundaria;
    }

    /**
     * Obtiene la cabeza del criterio primario tipada como {@link NodoMulti}.
     *
     * @return El {@link NodoMulti} inicial del Criterio 1, o {@code null} si está vacía.
     */
    public NodoMulti getCabezaMulti() {
        return (NodoMulti) cabeza;
    }

    /**
     * Método de auditoría e integridad estructural.
     * <p>
     * Verifica formalmente que:
     * <ol>
     *   <li>El número de nodos alcanzables por el Criterio 1 sea igual a {@code tamano}.</li>
     *   <li>El número de nodos alcanzables por el Criterio 2 sea igual a {@code tamano}.</li>
     *   <li>El recorrido secundario esté estrictamente en orden no decreciente.</li>
     *   <li>Ambos recorridos contengan exactamente la misma suma de elementos (conservación de datos).</li>
     * </ol>
     * </p>
     *
     * @return {@code true} si la multilista es completamente consistente; {@code false} si hay anomalías.
     */
    public boolean verificarConsistencia() {
        int conteo1 = 0;
        long suma1 = 0;
        for (Nodo n = cabeza; n != null; n = n.getSiguiente()) {
            conteo1++;
            suma1 += n.getDato();
        }

        int conteo2 = 0;
        long suma2 = 0;
        int previo = Integer.MIN_VALUE;
        for (NodoMulti n = cabezaSecundaria; n != null; n = n.getSiguienteSecundario()) {
            conteo2++;
            suma2 += n.getDato();
            if (n.getDato() < previo) {
                return false; // Violación de orden ascendente en Criterio 2
            }
            previo = n.getDato();
        }

        return (conteo1 == tamano) && (conteo2 == tamano) && (suma1 == suma2);
    }
}
