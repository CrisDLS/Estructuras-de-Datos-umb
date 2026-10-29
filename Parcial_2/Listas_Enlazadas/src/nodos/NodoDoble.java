package nodos;

public class NodoDoble extends Nodo {
    private NodoDoble anterior;

    public NodoDoble(int dato) {
        super(dato);
        this.anterior = null;
    }

    public NodoDoble getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoDoble anterior) {
        this.anterior = anterior;
    }

    @Override
    public NodoDoble getSiguiente() {
        return (NodoDoble) super.getSiguiente();
    }
}
