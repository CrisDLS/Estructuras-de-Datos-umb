
# -----------------------------------------
# IMPLEMENTACIÓN DE LA COLA CIRCULAR
# -----------------------------------------

class ColaCircular:

    def __init__(self, capacidad):
        self.capacidad = capacidad
        self.cola = [None] * capacidad
        self.frente = 0
        self.final = -1
        self.cantidad = 0

    # Verificar si la cola está vacía
    def esta_vacia(self):
        return self.cantidad == 0

    # Verificar si la cola está llena
    def esta_llena(self):
        return self.cantidad == self.capacidad

    # Insertar un elemento
    def encolar(self, elemento):

        if self.esta_llena():
            return False

        self.final = (self.final + 1) % self.capacidad
        self.cola[self.final] = elemento
        self.cantidad += 1

        return True

    # Eliminar un elemento
    def desencolar(self):

        if self.esta_vacia():
            return None

        elemento = self.cola[self.frente]
        self.cola[self.frente] = None

        self.frente = (self.frente + 1) % self.capacidad
        self.cantidad -= 1

        return elemento

    # Consultar el primer elemento
    def primero(self):

        if self.esta_vacia():
            return None

        return self.cola[self.frente]

    # Mostrar los elementos en orden
    def mostrar(self):

        elementos = []

        for i in range(self.cantidad):
            posicion = (self.frente + i) % self.capacidad
            elementos.append(self.cola[posicion])

        return elementos


# ------------------------------------------
# INTERFAZ DE USUARIO
# ------------------------------------------

def interfaz():

    print("=== COLA CIRCULAR ===")

    while True:
        try:
            capacidad = int(input("Ingrese la capacidad de la cola: "))

            if capacidad > 0:
                break

            print("La capacidad debe ser mayor que cero.")

        except ValueError:
            print("Ingrese un número válido.")

    cola = ColaCircular(capacidad)

    while True:

        print("\n========== MENÚ ==========")
        print("1. Encolar elemento")
        print("2. Desencolar elemento")
        print("3. Consultar primer elemento")
        print("4. Mostrar cola")
        print("5. Verificar si está vacía")
        print("6. Verificar si está llena")
        print("7. Salir")
        print("==========================")

        opcion = input("Seleccione una opción: ")

        if opcion == "1":

            elemento = input("Ingrese el elemento: ")

            if cola.encolar(elemento):
                print("Elemento agregado correctamente.")
            else:
                print("La cola está llena.")

        elif opcion == "2":

            if cola.esta_vacia():
                print("La cola está vacía.")
            else:
                print("Elemento eliminado:", cola.desencolar())

        elif opcion == "3":

            if cola.esta_vacia():
                print("La cola está vacía.")
            else:
                print("Primer elemento:", cola.primero())

        elif opcion == "4":

            print("Cola actual:", cola.mostrar())
            print("Elementos:", cola.cantidad, "/", cola.capacidad)

        elif opcion == "5":

            if cola.esta_vacia():
                print("La cola está vacía.")
            else:
                print("La cola contiene elementos.")

        elif opcion == "6":

            if cola.esta_llena():
                print("La cola está llena.")
            else:
                print("Todavía hay espacio disponible.")

        elif opcion == "7":

            print("Programa finalizado.")
            break

        else:
            print("Opción no válida. Intente nuevamente.")


# ------------------------------------------
# EJECUCIÓN PRINCIPAL
# ------------------------------------------

if __name__ == "__main__":
    interfaz()
