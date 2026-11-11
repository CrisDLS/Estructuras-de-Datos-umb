from lista import Lista


class NodoEnlazado:
    """Nodo que guarda un dato y la referencia al siguiente nodo."""
    def __init__(self, dato):
        self.dato = dato                 # Valor almacenado en el nodo.
        self.siguiente = None            # Enlace al siguiente nodo.


class ListaEnlazada(Lista):
    """Lista enlazada simple: cada nodo apunta únicamente al siguiente."""
    def __init__(self):
        super().__init__()                # Inicializa el contador de elementos.
        self.cabeza = None               # Primer nodo; None indica lista vacía.

    def insertar(self, dato):
        nuevo = NodoEnlazado(dato)        # Crea el nodo que se agregará.
        if self.esta_vacia():
            self.cabeza = nuevo           # Si no hay nodos, el nuevo será el primero.
        else:
            actual = self.cabeza
            while actual.siguiente is not None:  # Avanza hasta el último nodo.
                actual = actual.siguiente
            actual.siguiente = nuevo      # Conecta el último nodo con el nuevo.
        self._tamano += 1                 # Actualiza el número de elementos.

    def eliminar(self, dato):
        anterior = None                   # Nodo anterior al que se está revisando.
        actual = self.cabeza              # Comienza la búsqueda desde la cabeza.
        while actual is not None:
            if actual.dato == dato:       # Se encontró el dato que se desea quitar.
                if anterior is None:
                    self.cabeza = actual.siguiente  # Elimina el primer nodo.
                else:
                    anterior.siguiente = actual.siguiente  # Salta el nodo eliminado.
                self._tamano -= 1         # Reduce el tamaño de la lista.
                return True               # Indica que sí se eliminó.
            anterior, actual = actual, actual.siguiente  # Avanza un nodo.
        return False                      # El dato no estaba en la lista.

    def buscar(self, dato):
        actual = self.cabeza
        posicion = 0                      # La primera posición es 0.
        while actual is not None:
            if actual.dato == dato:
                return posicion           # Devuelve la posición donde se encontró.
            actual = actual.siguiente     # Continúa con el siguiente nodo.
            posicion += 1
        return -1                         # -1 significa que no se encontró.

    def mostrar(self):
        valores = []
        actual = self.cabeza
        while actual is not None:
            valores.append(str(actual.dato))  # Guarda el dato para mostrarlo.
            actual = actual.siguiente
        # None representa que después del último nodo no hay otro enlace.
        return " -> ".join(valores) + " -> None" if valores else "(lista vacía)"
