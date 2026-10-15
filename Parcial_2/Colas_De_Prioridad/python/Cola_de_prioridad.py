# ==========================================
# 1. IMPLEMENTACION DE LA COLA DE PRIORIDAD
# ==========================================

class ColaPrioridad:
    def __init__(self, capacidad):
        self.cola = []
        self.capacidad = capacidad

    def insertar(self, elemento, prioridad):
        if len(self.cola) >= self.capacidad:
            print("La cola esta llena.")
            return False

        self.cola.append((prioridad, elemento))
        self.cola.sort(key=lambda x: x[0])
        print("Dato ingresado correctamente.")
        return True

    def eliminar(self):
        if self.esta_vacia():
            return None

        return self.cola.pop(0)

    def mostrar(self):
        return self.cola

    def esta_vacia(self):
        return len(self.cola) == 0

    def esta_llena(self):
        return len(self.cola) >= self.capacidad

    def cantidad(self):
        return len(self.cola)


# ==========================================
# 2. CONVERSION DE TIPOS DE DATOS
# ==========================================

def convertir_dato(valor):
    valor = valor.strip()

    if valor.lower() == "true":
        return True
    elif valor.lower() == "false":
        return False

    try:
        return int(valor)
    except ValueError:
        pass

    try:
        return float(valor)
    except ValueError:
        pass

    return valor


# ==========================================
# 3. INTERFAZ DEL USUARIO
# ==========================================

def menu(cola):
    while True:
        print("\n===== COLA DE PRIORIDAD =====")
        print("1. Ingresar dato")
        print("2. Eliminar dato")
        print("3. Mostrar cola")
        print("4. Ver cantidad de datos")
        print("5. Verificar si esta vacia")
        print("6. Salir")

        opcion = input("Selecciona una opcion: ")

        if opcion == "1":
            if cola.esta_llena():
                print("No puedes ingresar mas datos: cola llena.")
                continue

            valor = input("Ingresa el dato: ")
            elemento = convertir_dato(valor)

            try:
                prioridad = int(
                    input("Ingresa el nivel de prioridad: ")
                )

                cola.insertar(elemento, prioridad)

            except ValueError:
                print("La prioridad debe ser un numero entero.")

        elif opcion == "2":
            eliminado = cola.eliminar()

            if eliminado is None:
                print("La cola esta vacia.")
            else:
                prioridad, elemento = eliminado
                print("Dato eliminado:", elemento)
                print("Prioridad:", prioridad)

        elif opcion == "3":
            if cola.esta_vacia():
                print("La cola esta vacia.")
            else:
                print("\n--- DATOS DE LA COLA ---")

                for prioridad, elemento in cola.mostrar():
                    print(
                        f"Dato: {elemento} | "
                        f"Tipo: {type(elemento).__name__} | "
                        f"Prioridad: {prioridad}"
                    )

        elif opcion == "4":
            print(
                f"Datos actuales: {cola.cantidad()} "
                f"de {cola.capacidad}"
            )

        elif opcion == "5":
            if cola.esta_vacia():
                print("La cola esta vacia.")
            else:
                print("La cola contiene datos.")

        elif opcion == "6":
            print("Programa finalizado.")
            break

        else:
            print("Opcion no valida.")


# ==========================================
# 4. INICIO DEL PROGRAMA
# ==========================================

def main():
    print("===== CONFIGURACION DE LA COLA =====")

    while True:
        try:
            tamano = int(input("Ingresa el tamano de la cola: "))

            if tamano > 0:
                break

            print("El tamano debe ser mayor que cero.")

        except ValueError:
            print("Debes ingresar un numero entero.")

    cola = ColaPrioridad(tamano)

    print(f"\nCola creada con capacidad para {tamano} datos.")

    menu(cola)


if __name__ == "__main__":
    main()