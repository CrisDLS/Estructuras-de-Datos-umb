# -----------------------------------------
# IMPLEMENTACIÓN DE LA COLA DOBLE (BICOLA / DEQUE) EN PYTHON
# -----------------------------------------
# Nota técnica: Python maneja listas dinámicas de forma nativa, por lo que no
# existe un arreglo estático primitivo fijo; esta implementación gestiona
# inserciones y eliminaciones por ambos extremos (frente y final).

class ColaDoble:

    def __init__(self):
        self.cola = []

    # Verificar si la cola doble está vacía
    def esta_vacia(self):
        return len(self.cola) == 0

    # Insertar un elemento al frente
    def insertar_frente(self, elemento):
        self.cola.insert(0, elemento)

    # Insertar un elemento al final
    def insertar_final(self, elemento):
        self.cola.append(elemento)

    # Eliminar un elemento del frente
    def eliminar_frente(self):
        if self.esta_vacia():
            return None
        return self.cola.pop(0)

    # Eliminar un elemento del final
    def eliminar_final(self):
        if self.esta_vacia():
            return None
        return self.cola.pop()

    # Consultar el elemento del frente sin eliminarlo
    def ver_frente(self):
        if self.esta_vacia():
            return None
        return self.cola[0]

    # Consultar el elemento del final sin eliminarlo
    def ver_final(self):
        if self.esta_vacia():
            return None
        return self.cola[-1]

    # Obtener cantidad de elementos
    def tamano(self):
        return len(self.cola)

    # Mostrar los elementos de frente a final
    def mostrar(self):
        if self.esta_vacia():
            return "Cola Doble: [vacía]"
        return "FRENTE -> " + " <-> ".join(str(x) for x in self.cola) + " <- FINAL"


# ------------------------------------------
# INTERFAZ DE USUARIO
# ------------------------------------------

def interfaz():
    deque = ColaDoble()

    while True:
        print("\n========== COLA DOBLE (BICOLA) ==========")
        print("1. Insertar al frente")
        print("2. Insertar al final")
        print("3. Eliminar del frente")
        print("4. Eliminar del final")
        print("5. Consultar frente")
        print("6. Consultar final")
        print("7. Mostrar cola doble")
        print("8. Verificar estado y tamaño")
        print("0. Salir")
        print("=========================================")

        opcion = input("Seleccione una opción: ").strip()

        if opcion == "1":
            elemento = input("Ingrese el elemento a insertar al frente: ")
            deque.insertar_frente(elemento)
            print(f"Elemento '{elemento}' insertado al frente.")
            print(deque.mostrar())

        elif opcion == "2":
            elemento = input("Ingrese el elemento a insertar al final: ")
            deque.insertar_final(elemento)
            print(f"Elemento '{elemento}' insertado al final.")
            print(deque.mostrar())

        elif opcion == "3":
            if deque.esta_vacia():
                print("La cola doble está vacía.")
            else:
                print("Elemento eliminado del frente:", deque.eliminar_frente())
                print(deque.mostrar())

        elif opcion == "4":
            if deque.esta_vacia():
                print("La cola doble está vacía.")
            else:
                print("Elemento eliminado del final:", deque.eliminar_final())
                print(deque.mostrar())

        elif opcion == "5":
            if deque.esta_vacia():
                print("La cola doble está vacía.")
            else:
                print("Elemento al frente:", deque.ver_frente())

        elif opcion == "6":
            if deque.esta_vacia():
                print("La cola doble está vacía.")
            else:
                print("Elemento al final:", deque.ver_final())

        elif opcion == "7":
            print(deque.mostrar())

        elif opcion == "8":
            estado = "Vacía" if deque.esta_vacia() else "Con elementos"
            print(f"Estado: {estado} | Tamaño actual: {deque.tamano()} elemento(s)")

        elif opcion == "0":
            print("Programa finalizado.")
            break

        else:
            print("Opción no válida. Intente nuevamente.")


if __name__ == "__main__":
    interfaz()
