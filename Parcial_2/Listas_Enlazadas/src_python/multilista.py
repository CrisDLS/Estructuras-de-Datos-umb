"""Multilista compatible con la clase abstracta Lista del proyecto.

El tamaño cuenta todos los nodos: principales y elementos de sublistas.
"""

try:
    from lista import Lista
except ImportError:  # Permite importar este módulo también de forma independiente.
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
            raise NotImplementedError

        @abstractmethod
        def eliminar(self, dato):
            raise NotImplementedError

        @abstractmethod
        def buscar(self, dato):
            raise NotImplementedError

        @abstractmethod
        def mostrar(self):
            raise NotImplementedError


class Nodot:
    # Cada nodo tiene un enlace horizontal (siguiente) y otro vertical (abajo).
    """Nodo con enlace lateral y enlace a la cabeza de su sublista."""

    def __init__(self, dato):
        self.dato = dato          # Dato guardado en este nodo.
        self.siguiente = None     # Enlace al siguiente nodo principal o de sublista.
        self.abajo = None         # Enlace al primer elemento de su sublista.


class Multilista(Lista):
    # Organiza nodos principales y, debajo de cada uno, una lista de subelementos.
    def __init__(self):
        super().__init__()
        self.cabeza = None

    # Interfaz abstracta de Lista.
    def insertar(self, dato):
        """Inserta un elemento principal al final."""
        self.insertar_principal(dato)

    def eliminar(self, dato):
        """Elimina el principal coincidente o, si no existe, el primer subdato."""
        if self.eliminar_principal(dato):
            return True
        principal = self.cabeza
        while principal is not None:
            if self.eliminar_sub(principal.dato, dato):
                return True
            principal = principal.siguiente
        return False

    def buscar(self, dato):
        """Devuelve la posición aplanada (como ListaSimple.buscar) o -1."""
        posicion = 0
        principal = self.cabeza
        while principal is not None:
            if principal.dato == dato:
                return posicion
            posicion += 1
            sub = principal.abajo
            while sub is not None:
                if sub.dato == dato:
                    return posicion
                posicion += 1
                sub = sub.siguiente
            principal = principal.siguiente
        return -1

    def mostrar(self):
        """Devuelve texto en lugar de imprimirlo, como las demás listas."""
        if self.cabeza is None:
            return "(multilista vacía)"
        lineas = []
        principal = self.cabeza
        while principal is not None:
            lineas.append(str(principal.dato))
            sub = principal.abajo
            if sub is None:
                lineas.append("   |-- (sin datos)")
            while sub is not None:
                lineas.append(f"   |-- {sub.dato}")
                sub = sub.siguiente
            principal = principal.siguiente
        return "\n".join(lineas)

    # Métodos de la lista principal.
    def insertar_principal(self, dato):
        # Agrega un nodo a la cadena horizontal de elementos principales.
        nuevo = Nodot(dato)
        if self.cabeza is None:
            self.cabeza = nuevo
        else:
            actual = self.cabeza
            while actual.siguiente is not None:
                actual = actual.siguiente
            actual.siguiente = nuevo
        self._tamano += 1
        return nuevo

    def insertar_inicio(self, dato):
        # Coloca el nuevo nodo antes de la cabeza actual.
        nuevo = Nodot(dato)
        nuevo.siguiente = self.cabeza
        self.cabeza = nuevo
        self._tamano += 1
        return nuevo

    def buscar_principal(self, dato):
        # Busca únicamente en el nivel principal, sin entrar a las sublistas.
        actual = self.cabeza
        while actual is not None:
            if actual.dato == dato:
                return actual
            actual = actual.siguiente
        return None

    def eliminar_principal(self, dato):
        # Al quitar un principal también se descuentan sus elementos inferiores.
        anterior = None
        actual = self.cabeza
        while actual is not None and actual.dato != dato:
            anterior, actual = actual, actual.siguiente
        if actual is None:
            return False
        sublista_eliminada = self.contar_sub(dato)
        if anterior is None:
            self.cabeza = actual.siguiente
        else:
            anterior.siguiente = actual.siguiente
        self._tamano -= 1 + sublista_eliminada
        return True

    def contar_principales(self):
        total = 0
        actual = self.cabeza
        while actual is not None:
            total += 1
            actual = actual.siguiente
        return total

    # Métodos de las sublistas.
    def insertar_sub(self, principal, dato):
        # Encuentra el padre y agrega el dato al final de su enlace vertical.
        padre = self.buscar_principal(principal)
        if padre is None:
            return False
        nuevo = Nodot(dato)
        if padre.abajo is None:
            padre.abajo = nuevo
        else:
            actual = padre.abajo
            while actual.siguiente is not None:
                actual = actual.siguiente
            actual.siguiente = nuevo
        self._tamano += 1
        return True

    def buscar_sub(self, principal, dato):
        # Revisa solamente la sublista perteneciente al principal indicado.
        padre = self.buscar_principal(principal)
        if padre is None:
            return None
        actual = padre.abajo
        while actual is not None:
            if actual.dato == dato:
                return actual
            actual = actual.siguiente
        return None

    def eliminar_sub(self, principal, dato):
        # Desconecta el elemento encontrado y conserva los enlaces restantes.
        padre = self.buscar_principal(principal)
        if padre is None:
            return False
        anterior = None
        actual = padre.abajo
        while actual is not None and actual.dato != dato:
            anterior, actual = actual, actual.siguiente
        if actual is None:
            return False
        if anterior is None:
            padre.abajo = actual.siguiente
        else:
            anterior.siguiente = actual.siguiente
        self._tamano -= 1
        return True

    def contar_sub(self, principal):
        # Cuenta cuántos nodos cuelgan del principal indicado.
        padre = self.buscar_principal(principal)
        if padre is None:
            return -1
        total = 0
        actual = padre.abajo
        while actual is not None:
            total += 1
            actual = actual.siguiente
        return total

    # Operaciones generales conservadas del código original.
    def buscar_en_todo(self, dato):
        # Busca el dato en sublistas y devuelve el valor de su nodo principal.
        principal = self.cabeza
        while principal is not None:
            sub = principal.abajo
            while sub is not None:
                if sub.dato == dato:
                    return principal.dato
                sub = sub.siguiente
            principal = principal.siguiente
        return None

    def contar_total(self):
        return self._tamano

    def vaciar(self):
        # Quita la referencia a la cabeza y reinicia el contador total.
        self.cabeza = None
        self._tamano = 0

