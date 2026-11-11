# -----------------------------------------
# IMPLEMENTACIÓN DE LA COLA SIMPLE (FIFO) EN PYTHON
# -----------------------------------------
# Nota técnica: Python maneja listas dinámicas de forma nativa, por lo que no
# existe un arreglo estático primitivo fijo; esta implementación gestiona
# el comportamiento FIFO (First-In, First-Out) en un único archivo.

class ColaSimple:

    def __init__(self):
        self.cola = []

    # Verificar si la cola está vacía
    def esta_vacia(self):
        return len(self.cola) == 0

    # Insertar un elemento al final (Enqueue)
    def encolar(self, elemento):
        self.cola.append(elemento)

    # Eliminar el elemento del frente (Dequeue)
    def desencolar(self):
        if self.esta_vacia():
            return None
        return self.cola.pop(0)

    # Consultar el primer elemento (Frente / Peek)
    def primero(self):
        if self.esta_vacia():
            return None
        return self.cola[0]

    # Consultar el último elemento (Final / Rear)
    def ultimo(self):
        if self.esta_vacia():
            return None
        return self.cola[-1]

    # Obtener cantidad de elementos
    def tamano(self):
        return len(self.cola)

    # Mostrar los elementos en orden FIFO
    def mostrar(self):
        if self.esta_vacia():
            return "Cola Simple: [vacía]"
        return "FRENTE -> " + " -> ".join(str(x) for x in self.cola) + " <- FINAL"


# ------------------------------------------
# INTERFAZ DE USUARIO
# ------------------------------------------

def interfaz():
    cola = ColaSimple()

    while True:
        print("\n========== COLA SIMPLE (FIFO) ==========")
        print("1. Encolar elemento (Enqueue)")
        print("2. Desencolar elemento (Dequeue)")
        print("3. Consultar primer elemento (Frente)")
        print("4. Consultar último elemento (Final)")
        print("5. Mostrar cola")
        print("6. Verificar estado y tamaño")
        print("0. Salir")
        print("========================================")

        opcion = input("Seleccione una opción: ").strip()

        if opcion == "1":
            elemento = input("Ingrese el elemento a encolar: ")
            cola.encolar(elemento)
            print(f"Elemento '{elemento}' agregado al final.")
            print(cola.mostrar())

        elif opcion == "2":
            if cola.esta_vacia():
                print("La cola simple está vacía.")
            else:
                print("Elemento atendido/eliminado:", cola.desencolar())
                print(cola.mostrar())

        elif opcion == "3":
            if cola.esta_vacia():
                print("La cola simple está vacía.")
            else:
                print("Primer elemento (Frente):", cola.primero())

        elif opcion == "4":
            if cola.esta_vacia():
                print("La cola simple está vacía.")
            else:
                print("Último elemento (Final):", cola.ultimo())

        elif opcion == "5":
            print(cola.mostrar())

        elif opcion == "6":
            estado = "Vacía" if cola.esta_vacia() else "Con elementos"
            print(f"Estado: {estado} | Tamaño actual: {cola.tamano()} elemento(s)")

        elif opcion == "0":
            print("Programa finalizado.")
            break

        else:
            print("Opción no válida. Intente nuevamente.")


if __name__ == "__main__":
    interfaz()
