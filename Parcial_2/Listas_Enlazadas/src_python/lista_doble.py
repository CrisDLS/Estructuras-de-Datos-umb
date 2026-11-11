from lista import Lista


class NodoDoble:
    """Nodo con dos enlaces: uno al anterior y otro al siguiente."""
    def __init__(self, dato):
        self.dato = dato          # Valor almacenado.
        self.anterior = None      # Enlace hacia el nodo previo.
        self.siguiente = None     # Enlace hacia el nodo posterior.


class ListaDoble(Lista):
    """Lista que puede recorrerse hacia adelante y hacia atrás."""
    def __init__(self):
        super().__init__()
        self.cabeza = None        # Primer nodo de la lista.
        self.cola = None          # Último nodo de la lista.

    def insertar(self, dato):
        nuevo = NodoDoble(dato)
        if self.esta_vacia():
            self.cabeza = self.cola = nuevo  # El primer nodo es inicio y final.
        else:
            nuevo.anterior = self.cola        # El nuevo apunta al último actual.
            self.cola.siguiente = nuevo       # El último actual apunta al nuevo.
            self.cola = nuevo                # Actualiza el final de la lista.
        self._tamano += 1

    def eliminar(self, dato):
        actual = self.cabeza
        while actual is not None:
            if actual.dato == dato:
                if actual.anterior is None:
                    self.cabeza = actual.siguiente  # Elimina la cabeza.
                else:
                    actual.anterior.siguiente = actual.siguiente  # Une hacia delante.
                if actual.siguiente is None:
                    self.cola = actual.anterior    # Elimina la cola.
                else:
                    actual.siguiente.anterior = actual.anterior  # Une hacia atrás.
                self._tamano -= 1
                return True
            actual = actual.siguiente               # Busca el siguiente nodo.
        return False

    def buscar(self, dato):
        actual = self.cabeza
        posicion = 0
        while actual is not None:
            if actual.dato == dato:
                return posicion
            actual = actual.siguiente
            posicion += 1
        return -1

    def mostrar(self):
        valores = []
        actual = self.cabeza
        while actual is not None:
            valores.append(str(actual.dato))
            actual = actual.siguiente
        # <-> indica que los enlaces permiten avanzar y retroceder.
        return 'None <- ' + ' <-> '.join(valores) + ' -> None' if valores else '(lista vacía)'

    def mostrar_inverso(self):
        valores = []
        actual = self.cola               # Comienza desde el último nodo.
        while actual is not None:
            valores.append(str(actual.dato))
            actual = actual.anterior      # Retrocede usando el enlace anterior.
        return 'None <- ' + ' <-> '.join(valores) + ' -> None' if valores else '(lista vacía)'
