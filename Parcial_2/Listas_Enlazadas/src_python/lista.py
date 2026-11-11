from abc import ABC, abstractmethod


class Lista(ABC):
    def __init__(self):
        self._tamano = 0

    def esta_vacia(self):
        return self._tamano == 0

    def obtener_tamano(self):
        return self._tamano

    @abstractmethod
    def insertar(self, dato):
        pass

    @abstractmethod
    def eliminar(self, dato):
        pass

    @abstractmethod
    def buscar(self, dato):
        pass

    @abstractmethod
    def mostrar(self):
        pass
