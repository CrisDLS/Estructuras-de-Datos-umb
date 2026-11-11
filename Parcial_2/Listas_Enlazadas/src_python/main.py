from lista_circular import ListaCircular
from lista import Lista
from lista_simple import ListaSimple
from lista_enlazada import ListaEnlazada
from lista_doble import ListaDoble
from multilista import Multilista


def menu_operaciones(lista: Lista):
    # Menú común para insertar, eliminar, buscar y consultar cualquier lista.
    while True:
        print('\n--- OPERACIONES ---')
        print('1. Insertar al final')
        print('2. Eliminar por valor')
        print('3. Buscar por valor')
        print('4. Mostrar lista')
        print('5. Verificar si está vacía')
        print('6. Obtener tamaño')
        if isinstance(lista, ListaDoble):
            print('7. Mostrar en sentido inverso')
        opcion = input('Selecciona una opción: ').strip()
        if opcion == '0':
            return
        elif opcion == '1':
            try:
                lista.insertar(int(input('Dato a insertar: ')))
                print('Elemento insertado.')
            except ValueError:
                print('Error: Por favor, ingresa un número válido.')
        elif opcion == '2':
            try:
                dato = int(input('Dato a eliminar: '))
                print('Elemento eliminado.' if lista.eliminar(dato) else 'Elemento no encontrado.')
            except ValueError:
                print('Error: Por favor, ingresa un número válido.')
        elif opcion == '3':
            try:
                posicion = lista.buscar(int(input('Dato a buscar: ')))
                print(f'Encontrado en posición {posicion} (desde 0).' if posicion >= 0 else 'Elemento no encontrado.')
            except ValueError:
                print('Error: Por favor, ingresa un número válido.')
        elif opcion == '4':
            print(lista.mostrar())
        elif opcion == '5':
            print('La lista está vacía.' if lista.esta_vacia() else 'La lista contiene elementos.')
        elif opcion == '6':
            print('Tamaño:', lista.obtener_tamano())
        elif opcion == '7' and isinstance(lista, ListaDoble):
            print(lista.mostrar_inverso())
        else:
            print('Opción inválida.')


def menu_multilista(multilista: Multilista):
    # Este menú administra tanto los nodos principales como sus sublistas.
    """Menú específico para administrar principales y sus sublistas."""
    while True:
        print('\n--- OPERACIONES DE MULTILISTA ---')
        print('1. Insertar nodo principal al final')
        print('2. Insertar nodo principal al inicio')
        print('3. Insertar elemento en una sublista')
        print('4. Eliminar por valor (principal o sublista)')
        print('5. Buscar valor')
        print('6. Mostrar multilista')
        print('7. Contar nodos principales')
        print('8. Contar elementos de una sublista')
        print('9. Obtener tamaño total')
        print('10. Vaciar multilista')
        print('0. Volver al menú principal')
        opcion = input('Selecciona una opción: ').strip()

        try:
            if opcion == '0':
                return
            elif opcion == '1':
                multilista.insertar_principal(int(input('Dato principal: ')))
                print('Nodo principal insertado.')
            elif opcion == '2':
                multilista.insertar_inicio(int(input('Dato principal: ')))
                print('Nodo principal insertado al inicio.')
            elif opcion == '3':
                padre = int(input('Valor del nodo principal: '))
                dato = int(input('Dato para la sublista: '))
                if multilista.insertar_sub(padre, dato):
                    print('Elemento insertado en la sublista.')
                else:
                    print('No existe ese nodo principal.')
            elif opcion == '4':
                dato = int(input('Dato a eliminar: '))
                print('Elemento eliminado.' if multilista.eliminar(dato) else 'Elemento no encontrado.')
            elif opcion == '5':
                dato = int(input('Dato a buscar: '))
                posicion = multilista.buscar(dato)
                print(f'Encontrado en posición {posicion} (desde 0).' if posicion >= 0 else 'Elemento no encontrado.')
            elif opcion == '6':
                print(multilista.mostrar())
            elif opcion == '7':
                print('Nodos principales:', multilista.contar_principales())
            elif opcion == '8':
                padre = int(input('Valor del nodo principal: '))
                cantidad = multilista.contar_sub(padre)
                print('No existe ese nodo principal.' if cantidad < 0 else f'Elementos en la sublista: {cantidad}')
            elif opcion == '9':
                print('Tamaño total:', multilista.obtener_tamano())
            elif opcion == '10':
                multilista.vaciar()
                print('Multilista vaciada.')
            else:
                print('Opción inválida.')
        except ValueError:
            print('Error: Por favor, ingresa un número válido.')


def main():
    # Crea una instancia independiente de cada estructura de datos.
    enlazada = ListaEnlazada()
    simple = ListaSimple()
    doble = ListaDoble()
    circular = ListaCircular()
    multilista = Multilista()
    while True:
        print('\n========== TIPOS DE LISTAS ==========')
        print('1. Lista enlazada')
        print('2. Lista simple')
        print('3. Lista doble')
        print('4. Lista circular')
        print('5. Multilista')
        print('0. Salir')
        opcion = input('Selecciona una opción: ').strip()
        if opcion == '1':
            menu_operaciones(enlazada)
        elif opcion == '2':
            menu_operaciones(simple)
        elif opcion == '3':
            menu_operaciones(doble)
        elif opcion == '4':
            menu_operaciones(circular)
        elif opcion == '5':
            menu_multilista(multilista)
        elif opcion == '0':
            print('Hasta luego.')
            break
        else:
            print('Opción inválida.')


if __name__ == '__main__':
    main()

