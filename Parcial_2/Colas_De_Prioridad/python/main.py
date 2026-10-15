from Cola_de_prioridad import ColaPrioridad

def convertir_dato(valor):
    """Convierte el texto ingresado a su tipo real (bool, int, float o str)."""
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


def menu(cola):
    """Muestra el menú interactivo en consola y ejecuta las operaciones."""
    while True:
        print("\n===== COLA DE PRIORIDAD =====")
        print("1. Ingresar dato")
        print("2. Eliminar dato")
        print("3. Mostrar cola")
        print("4. Ver cantidad de datos")
        print("5. Verificar si esta vacia")
        print("6. Salir")

        opcion = input("Selecciona una opcion: ").strip()

        if opcion == "1":
            if cola.esta_llena():
                print("No puedes ingresar mas datos: cola llena.")
                continue

            valor = input("Ingresa el dato: ")
            elemento = convertir_dato(valor)

            try:
                prioridad = int(input("Ingresa el nivel de prioridad: "))
                if cola.insertar(elemento, prioridad):
                    print("Dato ingresado correctamente.")
                else:
                    print("La cola esta llena.")
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
            print(f"Datos actuales: {cola.cantidad()} de {cola.capacidad}")

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


def main():
    """Configura el tamaño inicial de la cola e inicia el programa."""
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