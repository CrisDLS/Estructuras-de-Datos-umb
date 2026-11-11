#include <iostream> // Para mostrar mensajes en pantalla (cout) y leer teclado (cin)
#include <vector>   // Para guardar colecciones de listas en un vector
#include <memory>   // Para usar punteros inteligentes seguros (unique_ptr)
#include <limits>   // Para limpiar el búfer del teclado
#include <string>   // Para manejar texto (string)

#include "Lista.h"         // Clase base Lista
#include "ListaSimple.h"   // Lista simple
#include "ListaDoble.h"    // Lista doble
#include "ListaCircular.h" // Lista circular
#include "Multilista.h"    // Multilista

// Lee un número entero del teclado validando que el usuario no ingrese letras o símbolos
int leerEntero(const std::string& mensaje) {
    int valor;
    while (true) {
        // Mostramos el mensaje solicitando el dato
        std::cout << mensaje;

        // Si el usuario ingresó un número entero válido
        if (std::cin >> valor) {
            return valor; // Retornamos el número de inmediato
        }

        // Si el usuario ingresó letras o un formato incorrecto
        std::cout << "  [!] Error: Debe ingresar un numero entero valido.\n";

        // Limpiamos el estado de error de la consola
        std::cin.clear();

        // Descartamos los caracteres incorrectos del teclado hasta el salto de línea (Enter)
        std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
    }
}

// Pausa la ejecución en pantalla y espera a que el usuario presione Enter para continuar
void pausar() {
    std::cout << "\nPresione Enter para continuar...";
    // Limpiamos saltos de línea pendientes en el búfer
    std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
    // Esperamos a que el usuario presione la tecla Enter
    std::cin.get();
}

// Ejecuta una prueba automática de las operaciones sobre todos los tipos de listas
void ejecutarDemostracionPolimorfismo() {
    std::cout << "\n=======================================================\n";
    std::cout << "       DEMOSTRACION UNIFICADA DE TODAS LAS LISTAS      \n";
    std::cout << "=======================================================\n";
    std::cout << "Guardamos las 4 listas en una misma coleccion\n";
    std::cout << "y ejecutamos las mismas operaciones sobre cada una.\n\n";

    // Creamos un vector que almacena punteros a la clase base Lista
    std::vector<std::unique_ptr<Lista>> coleccion;

    // Agregamos una instancia de cada tipo de lista a la colección
    coleccion.push_back(std::make_unique<ListaSimple>());   // Agregamos ListaSimple
    coleccion.push_back(std::make_unique<ListaDoble>());    // Agregamos ListaDoble
    coleccion.push_back(std::make_unique<ListaCircular>()); // Agregamos ListaCircular
    coleccion.push_back(std::make_unique<Multilista>());    // Agregamos Multilista

    // Recorremos la colección lista por lista aplicando las mismas operaciones
    for (const auto& lista : coleccion) {
        std::cout << "-------------------------------------------------------\n";
        // Mostramos el nombre de la lista que estamos evaluando
        std::cout << "-> Evaluando: " << lista->obtenerTipo() << "\n";
        std::cout << "-------------------------------------------------------\n";

        // 1. Insertamos elementos al inicio
        std::cout << "1. Insertando al inicio: 10, luego 5\n";
        lista->insertarInicio(10);
        lista->insertarInicio(5);

        // 2. Insertamos elementos al final
        std::cout << "2. Insertando al final: 20, luego 30\n";
        lista->insertarFinal(20);
        lista->insertarFinal(30);

        // 3. Insertamos un elemento en la posición 2
        std::cout << "3. Insertando en posicion 2 el valor 15\n";
        lista->insertarEnPosicion(15, 2);

        // 4. Mostramos el contenido de la lista
        std::cout << "4. Estado de la lista (mostrar()):\n   ";
        lista->mostrar();

        // 5. Consultamos la cantidad de elementos
        std::cout << "5. Tamano actual: " << lista->tamano() << " elementos\n";

        // 6. Buscamos si existe el número 15
        int valorBuscar = 15;
        std::cout << "6. Buscando el valor " << valorBuscar << ": "
                  << (lista->buscar(valorBuscar) ? "ENCONTRADO [OK]" : "NO ENCONTRADO") << "\n";

        // 7. Eliminamos el número 15
        int valorEliminar = 15;
        std::cout << "7. Eliminando valor " << valorEliminar << ": "
                  << (lista->eliminar(valorEliminar) ? "ELIMINADO [OK]" : "FALLO") << "\n";

        // 8. Mostramos el contenido tras la eliminación
        std::cout << "8. Estado tras eliminacion:\n   ";
        lista->mostrar();

        // 9. Consultamos el tamaño final
        std::cout << "9. Tamano tras eliminacion: " << lista->tamano() << " elementos\n\n";
    }

    std::cout << "=======================================================\n";
    std::cout << "Fin de la evaluacion unificada con exito.\n";
    std::cout << "Toda la memoria se libera de forma automatica al terminar.\n";
    std::cout << "=======================================================\n";
    pausar();
}

// Menú de operaciones interactivo que funciona con cualquier tipo de lista
void menuOperaciones(Lista* lista) {
    // Verificamos si la lista actual es de tipo ListaDoble
    ListaDoble* listaDoble = dynamic_cast<ListaDoble*>(lista);

    // Verificamos si la lista actual es de tipo Multilista
    Multilista* multilista = dynamic_cast<Multilista*>(lista);

    int opcion = 0;
    do {
        // Mostramos el encabezado con el tipo de lista actual
        std::cout << "\n-------------------------------------------------------\n";
        std::cout << "  Operando sobre: " << lista->obtenerTipo() << "\n";
        std::cout << "-------------------------------------------------------\n";
        std::cout << "1. Insertar al inicio\n";
        std::cout << "2. Insertar al final\n";
        std::cout << "3. Insertar en posicion\n";
        std::cout << "4. Eliminar por valor\n";
        std::cout << "5. Buscar un elemento\n";
        std::cout << "6. Mostrar lista\n";
        std::cout << "7. Ver tamano y estado (vacia / con elementos)\n";
        std::cout << "8. Vaciar lista completa\n";

        // Opciones adicionales disponibles según el tipo de lista
        if (listaDoble != nullptr) {
            std::cout << "9. [Lista Doble] Mostrar en orden inverso\n";
        }
        if (multilista != nullptr) {
            std::cout << "10. [Multilista] Insertar elemento en sublista\n";
            std::cout << "11. [Multilista] Eliminar elemento de sublista\n";
        }
        std::cout << "0. Volver al menu principal\n";

        // Leemos la opción elegida con validación
        opcion = leerEntero("Seleccione una opcion: ");

        // Ejecutamos la acción seleccionada
        switch (opcion) {
            case 1: {
                int dato = leerEntero("Ingrese el valor a insertar al inicio: ");
                lista->insertarInicio(dato);
                std::cout << "  -> Elemento " << dato << " insertado al inicio.\n";
                break;
            }
            case 2: {
                int dato = leerEntero("Ingrese el valor a insertar al final: ");
                lista->insertarFinal(dato);
                std::cout << "  -> Elemento " << dato << " insertado al final.\n";
                break;
            }
            case 3: {
                int dato = leerEntero("Ingrese el valor a insertar: ");
                int pos = leerEntero("Ingrese la posicion (empezando en 0): ");
                lista->insertarEnPosicion(dato, pos);
                std::cout << "  -> Elemento " << dato << " insertado en posicion " << pos << ".\n";
                break;
            }
            case 4: {
                int dato = leerEntero("Ingrese el valor a eliminar: ");
                if (lista->eliminar(dato)) {
                    std::cout << "  -> Elemento " << dato << " eliminado correctamente.\n";
                } else {
                    std::cout << "  -> El elemento " << dato << " no fue encontrado en la lista.\n";
                }
                break;
            }
            case 5: {
                int dato = leerEntero("Ingrese el valor a buscar: ");
                if (lista->buscar(dato)) {
                    std::cout << "  -> El elemento " << dato << " SI se encuentra en la lista.\n";
                } else {
                    std::cout << "  -> El elemento " << dato << " NO se encuentra en la lista.\n";
                }
                break;
            }
            case 6: {
                std::cout << "Contenido de la lista:\n";
                lista->mostrar();
                break;
            }
            case 7: {
                std::cout << "Tamano actual: " << lista->tamano() << " elementos.\n";
                std::cout << "Estado: " << (lista->estaVacia() ? "Vacia" : "Contiene elementos") << ".\n";
                break;
            }
            case 8: {
                lista->vaciar();
                std::cout << "  -> Lista vaciada con exito (memoria liberada).\n";
                break;
            }
            case 9: {
                if (listaDoble != nullptr) {
                    std::cout << "Contenido en orden inverso:\n";
                    listaDoble->mostrarInverso();
                } else {
                    std::cout << "Opcion no valida para este tipo de lista.\n";
                }
                break;
            }
            case 10: {
                if (multilista != nullptr) {
                    int idPrincipal = leerEntero("Ingrese el ID del nodo principal: ");
                    int datoSub = leerEntero("Ingrese el valor para la sublista: ");
                    if (multilista->insertarEnSublista(idPrincipal, datoSub)) {
                        std::cout << "  -> Valor " << datoSub << " insertado en la sublista del nodo " << idPrincipal << ".\n";
                    } else {
                        std::cout << "  -> No se encontro el nodo principal con ID " << idPrincipal << ".\n";
                    }
                } else {
                    std::cout << "Opcion no valida para este tipo de lista.\n";
                }
                break;
            }
            case 11: {
                if (multilista != nullptr) {
                    int idPrincipal = leerEntero("Ingrese el ID del nodo principal: ");
                    int datoSub = leerEntero("Ingrese el valor a eliminar de la sublista: ");
                    if (multilista->eliminarDeSublista(idPrincipal, datoSub)) {
                        std::cout << "  -> Valor " << datoSub << " eliminado de la sublista del nodo " << idPrincipal << ".\n";
                    } else {
                        std::cout << "  -> No se pudo eliminar (nodo principal o elemento no encontrado).\n";
                    }
                } else {
                    std::cout << "Opcion no valida para este tipo de lista.\n";
                }
                break;
            }
            case 0:
                std::cout << "Regresando al menu principal...\n";
                break;
            default:
                std::cout << "Opcion invalida. Intente de nuevo.\n";
                break;
        }
    } while (opcion != 0);
}

// Función principal: punto de inicio del programa
int main() {
    // Creamos una instancia de cada tipo de lista
    ListaSimple listaSimple;
    ListaDoble listaDoble;
    ListaCircular listaCircular;
    Multilista multilista;

    int opcion = 0;
    do {
        // Mostramos el menú principal de opciones
        std::cout << "\n=======================================================\n";
        std::cout << "     SISTEMA DE GESTION DE LISTAS ENLAZADAS EN C++     \n";
        std::cout << "=======================================================\n";
        std::cout << "1. Trabajar con Lista Simple\n";
        std::cout << "2. Trabajar con Lista Doble\n";
        std::cout << "3. Trabajar con Lista Circular\n";
        std::cout << "4. Trabajar con Multilista (Lista de Listas)\n";
        std::cout << "5. Demostracion automatica unificada\n";
        std::cout << "0. Salir\n";
        std::cout << "=======================================================\n";

        // Leemos la opción elegida por el usuario
        opcion = leerEntero("Seleccione una opcion: ");

        // Abrimos el menú correspondiente según la opción elegida
        switch (opcion) {
            case 1:
                menuOperaciones(&listaSimple);
                break;
            case 2:
                menuOperaciones(&listaDoble);
                break;
            case 3:
                menuOperaciones(&listaCircular);
                break;
            case 4:
                menuOperaciones(&multilista);
                break;
            case 5:
                ejecutarDemostracionPolimorfismo();
                break;
            case 0:
                std::cout << "\nCerrando programa y liberando memoria. Hasta pronto!\n";
                break;
            default:
                std::cout << "Opcion invalida. Por favor seleccione una opcion valida.\n";
                break;
        }
    } while (opcion != 0);

    return 0; // Finaliza la ejecución del programa con éxito
}
