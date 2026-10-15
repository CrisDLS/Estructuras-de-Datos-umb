class ColaPrioridad:
    """Estructura de Cola de Prioridad (menor número = mayor prioridad)."""

    def __init__(self, capacidad):
        self.cola = []
        self.capacidad = capacidad

    def insertar(self, elemento, prioridad):
        """Inserta el dato ordenado por prioridad respetando el orden de llegada (FIFO)."""
        if self.esta_llena():
            return False

        nuevo = (prioridad, elemento)

        # Busca la posición según prioridad sin desordenar los que ya estaban
        indice = 0
        while indice < len(self.cola) and self.cola[indice][0] <= prioridad:
            indice += 1

        self.cola.insert(indice, nuevo)
        return True

    def eliminar(self):
        """Saca y devuelve el elemento con mayor prioridad (el primero de la fila)."""
        if self.esta_vacia():
            return None

        return self.cola.pop(0)

    def mostrar(self):
        """Devuelve la lista actual de elementos."""
        return self.cola

    def esta_vacia(self):
        """Retorna True si no hay elementos en la cola."""
        return len(self.cola) == 0

    def esta_llena(self):
        """Retorna True si se alcanzó la capacidad máxima."""
        return len(self.cola) >= self.capacidad

    def cantidad(self):
        """Retorna el número de elementos actuales."""
        return len(self.cola)