package nodos;

/**
 * Representa un nodo para una Multilista (Lista Multienlazada).
 * <p>
 * Extiende de la clase base {@link Nodo}, heredando el almacenamiento del dato primitivo
 * {@code int} y la referencia al enlace primario ({@code siguiente}). Adicionalmente,
 * incorpora una segunda referencia de enlace ({@code siguienteSecundario}) que permite
 * hilar el mismo nodo a través de un segundo criterio o dimensión de recorrido.
 * </p>
 *
 * <h3>Estructura del Nodo en Memoria:</h3>
 * <pre>
 *   +-----------------------------------------------+
 *   |                   NodoMulti                   |
 *   +-----------------------------------------------+
 *   | dato: int (heredado de Nodo)                  |
 *   | siguiente: Nodo (heredado, Criterio 1)        |
 *   | siguienteSecundario: NodoMulti (Criterio 2)   |
 *   +-----------------------------------------------+
 * </pre>
 *
 * @author Cristian (Integrante 3)
 * @version 1.0
 */
public class NodoMulti extends Nodo {

    /**
     * Referencia al siguiente nodo bajo el segundo criterio de enlace
     * (por ejemplo: orden numérico ascendente, dimensión vertical o categoría).
     */
    private NodoMulti siguienteSecundario;

    /**
     * Constructor principal que inicializa el dato del nodo.
     * Ambos punteros de enlace (primario y secundario) inician en {@code null}.
     *
     * @param dato Valor entero almacenado en el nodo.
     */
    public NodoMulti(int dato) {
        super(dato);
        this.siguienteSecundario = null;
    }

    /**
     * Constructor sobrecargado que permite especificar enlaces iniciales.
     *
     * @param dato Valor entero almacenado.
     * @param siguiente Referencia al siguiente nodo en el criterio primario.
     * @param siguienteSecundario Referencia al siguiente nodo en el criterio secundario.
     */
    public NodoMulti(int dato, Nodo siguiente, NodoMulti siguienteSecundario) {
        super(dato);
        this.setSiguiente(siguiente);
        this.siguienteSecundario = siguienteSecundario;
    }

    /**
     * Obtiene el siguiente nodo bajo el criterio secundario.
     *
     * @return El {@link NodoMulti} enlazado secundariamente, o {@code null} si es el final.
     */
    public NodoMulti getSiguienteSecundario() {
        return siguienteSecundario;
    }

    /**
     * Establece el siguiente nodo bajo el criterio secundario.
     *
     * @param siguienteSecundario Nuevo nodo a enlazar en el segundo criterio.
     */
    public void setSiguienteSecundario(NodoMulti siguienteSecundario) {
        this.siguienteSecundario = siguienteSecundario;
    }

    /**
     * Método de conveniencia tipado para obtener el siguiente nodo del criterio primario
     * como una instancia de {@link NodoMulti}, facilitando recorridos específicos de multilista
     * sin necesidad de continuos casteos manuales.
     *
     * @return El siguiente {@link NodoMulti} en el enlace primario, o {@code null} si es el final.
     */
    public NodoMulti getSiguienteMulti() {
        return (NodoMulti) super.getSiguiente();
    }

    @Override
    public String toString() {
        return "NodoMulti{" +
                "dato=" + getDato() +
                ", sigPrimario=" + (getSiguiente() != null ? getSiguiente().getDato() : "null") +
                ", sigSecundario=" + (siguienteSecundario != null ? siguienteSecundario.getDato() : "null") +
                '}';
    }
}
