from lista import Lista


class NodoCircular:
    """Nodo cuyo enlace siguiente forma parte de un ciclo."""
    def __init__(self, dato):
        self.dato = dato
        self.siguiente = None  # Se conectará con otro nodo, incluso con la cabeza.


class ListaCircular(Lista):
    """Lista en la que el último nodo vuelve a apuntar al primero."""
    def __init__(self):
        super().__init__()
        self.cabeza = None    # Primer nodo.
        self.cola = None      # Último nodo.

    def insertar(self, dato):
        nuevo = NodoCircular(dato)
        if self.esta_vacia():
            self.cabeza = nuevo
            self.cola = nuevo
            nuevo.siguiente = self.cabeza  # Cierra el ciclo de un solo nodo.
        else:
            nuevo.siguiente = self.cabeza  # El nuevo apunta al inicio.
            self.cola.siguiente = nuevo    # El último apunta al nuevo.
            self.cola = nuevo             # Actualiza el último nodo.
        self._tamano += 1

    def eliminar(self, dato):
        if self.esta_vacia():
            return False
        actual = self.cabeza
        anterior = self.cola  # Al inicio, el anterior de la cabeza es la cola.
        # Se revisa como máximo el número de nodos para no recorrer el ciclo sin fin.
        for _ in range(self._tamano):
            if actual.dato == dato:
                if self._tamano == 1:
                    self.cabeza = None
                    self.cola = None
                else:
                    anterior.siguiente = actual.siguiente  # Salta el nodo eliminado.
                    if actual == self.cabeza:
                        self.cabeza = actual.siguiente
                    if actual == self.cola:
                        self.cola = anterior
                    self.cola.siguiente = self.cabeza  # Mantiene cerrado el ciclo.
                self._tamano -= 1
                return True
            anterior = actual
            actual = actual.siguiente
        return False

    def buscar(self, dato):
        actual = self.cabeza
        # El contador limita el recorrido a una vuelta completa.
        for posicion in range(self._tamano):
            if actual.dato == dato:
                return posicion
            actual = actual.siguiente
        return -1

    def mostrar(self):
        if self.esta_vacia():
            return '(lista vacía)'
        valores = []
        actual = self.cabeza
        for _ in range(self._tamano):  # Evita imprimir el ciclo indefinidamente.
            valores.append(str(actual.dato))
            actual = actual.siguiente
        return ' -> '.join(valores) + ' -> vuelve a ' + str(self.cabeza.dato)
