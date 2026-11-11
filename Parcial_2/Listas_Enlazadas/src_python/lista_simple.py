from lista import Lista


class NodoSimple:
    """Nodo de una lista simple: contiene un dato y un enlace siguiente."""
    def __init__(self, dato):
        self.dato = dato          # Información guardada.
        self.siguiente = None     # Referencia al próximo nodo.


class ListaSimple(Lista):
    """Estructura donde cada nodo conoce solamente al que sigue."""
    def __init__(self):
        super().__init__()        # Prepara el contador heredado de Lista.
        self.cabeza = None        # Referencia al primer nodo.

    def insertar(self, dato):
        nuevo = NodoSimple(dato)  # Crea el nodo nuevo.
        if self.esta_vacia():
            self.cabeza = nuevo   # El nuevo nodo será la cabeza.
        else:
            actual = self.cabeza
            while actual.siguiente is not None:  # Busca el final.
                actual = actual.siguiente
            actual.siguiente = nuevo            # Enlaza el nuevo nodo al final.
        self._tamano += 1         # Suma un elemento al tamaño.

    def eliminar(self, dato):
        anterior = None            # Permite reconectar la lista al borrar.
        actual = self.cabeza
        while actual is not None:
            if actual.dato == dato:
                if anterior is None:
                    self.cabeza = actual.siguiente  # El dato estaba al inicio.
                else:
                    anterior.siguiente = actual.siguiente  # Omite el nodo actual.
                self._tamano -= 1
                return True
            anterior, actual = actual, actual.siguiente  # Recorre la lista.
        return False               # No se encontró el valor.

    def buscar(self, dato):
        actual = self.cabeza
        posicion = 0
        while actual is not None:
            if actual.dato == dato:
                return posicion    # Regresa el índice del dato.
            actual = actual.siguiente
            posicion += 1
        return -1                  # Valor no encontrado.

    def mostrar(self):
        valores = []
        actual = self.cabeza
        while actual is not None:
            valores.append(str(actual.dato))
            actual = actual.siguiente
        # La flecha muestra la dirección de los enlaces.
        return ' -> '.join(valores) + ' -> None' if valores else '(lista vacía)'
