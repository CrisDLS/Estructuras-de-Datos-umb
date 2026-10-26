package nodos;

/**
 * Representa un nodo multienlazado que modela a una Persona dentro de una Multilista.
 * <p>
 * Extiende de {@link Nodo}, reutilizando el atributo {@code dato} para almacenar
 * la edad de la persona (accedida mediante {@code super(edad)}) y la referencia {@code siguiente}
 * para el <b>Criterio 1 (Orden Alfabético por Nombre)</b>.
 * Incorpora un atributo específico para el nombre y una referencia {@code siguienteEdad}
 * para el <b>Criterio 2 (Orden Numérico Ascendente por Edad)</b>.
 * </p>
 *
 * <h3>Estructura del Nodo en Memoria:</h3>
 * <pre>
 *   +-------------------------------------------------------+
 *   |                       NodoMulti                       |
 *   +-------------------------------------------------------+
 *   | nombre: String                                        |
 *   | dato: int (heredado de Nodo, representa la edad)      |
 *   | siguiente: Nodo (heredado, Criterio 1: Alfabético)   |
 *   | siguienteEdad: NodoMulti (Criterio 2: Por Edad)       |
 *   +-------------------------------------------------------+
 * </pre>
 */
public class NodoMulti extends Nodo {

    /**
     * Nombre de la persona. Criterio base para el recorrido alfabético.
     */
    private String nombre;

    /**
     * Referencia al siguiente nodo según el orden numérico de edad (de menor a mayor).
     */
    private NodoMulti siguienteEdad;

    /**
     * Constructor principal que inicializa nombre y edad de la persona.
     *
     * @param nombre Nombre de la persona.
     * @param edad Edad en años (almacenada en {@code dato} de la superclase).
     */
    public NodoMulti(String nombre, int edad) {
        super(edad);
        this.nombre = nombre;
        this.siguienteEdad = null;
    }

    /**
     * Constructor sobrecargado para compatibilidad numérica directa.
     * Asigna un nombre predeterminado con el formato {@code "Persona-" + edad}.
     *
     * @param edad Edad en años.
     */
    public NodoMulti(int edad) {
        this("Persona-" + edad, edad);
    }

    /**
     * Constructor sobrecargado con enlaces explícitos.
     *
     * @param nombre Nombre de la persona.
     * @param edad Edad en años.
     * @param siguiente Referencia al siguiente nodo en orden alfabético.
     * @param siguienteEdad Referencia al siguiente nodo en orden de edad.
     */
    public NodoMulti(String nombre, int edad, Nodo siguiente, NodoMulti siguienteEdad) {
        super(edad);
        this.nombre = nombre;
        this.setSiguiente(siguiente);
        this.siguienteEdad = siguienteEdad;
    }

    // ==========================================
    // GETTERS Y SETTERS
    // ==========================================

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la edad de la persona (alias de conveniencia sobre {@code getDato()}).
     *
     * @return La edad almacenada en el nodo.
     */
    public int getEdad() {
        return getDato();
    }

    /**
     * Establece la edad de la persona (alias de conveniencia sobre {@code setDato(int)}).
     *
     * @param edad Nueva edad a asignar.
     */
    public void setEdad(int edad) {
        setDato(edad);
    }

    public NodoMulti getSiguienteEdad() {
        return siguienteEdad;
    }

    public void setSiguienteEdad(NodoMulti siguienteEdad) {
        this.siguienteEdad = siguienteEdad;
    }

    /**
     * Retorna el siguiente nodo en el criterio alfabético tipado directamente como {@link NodoMulti}.
     *
     * @return El siguiente {@link NodoMulti} en la cadena alfabética, o {@code null} si es el último.
     */
    public NodoMulti getSiguienteMulti() {
        return (NodoMulti) super.getSiguiente();
    }

    @Override
    public String toString() {
        return nombre + " (" + getEdad() + ")";
    }
}
