#include <iostream>
#include <limits>
#include <string>

#include "ColaSimpleArreglo.h"
#include "ColaSimpleNodos.h"

// Lee un número entero validando la entrada del teclado
int leerEntero(const std::string& mensaje) {
    int valor;
    while (true) {
        std::cout << mensaje;
        if (std::cin >> valor) {
            return valor;
        }
        std::cout << "  [!] Error: Ingrese un numero entero valido.\n";
        std::cin.clear();
        std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
    }
}

void menuArreglo() {
    int cap = 0;
    while (cap <= 0) {
        cap = leerEntero("Ingrese la capacidad del arreglo (mayor a 0): ");
    }
    ColaSimpleArreglo cola(cap);

    int opcion = 0;
    do {
        std::cout << "\n--- COLA SIMPLE CON ARREGLO (FIFO) ---\n";
        std::cout << "1. Encolar (Enqueue)\n";
        std::cout << "2. Desencolar (Dequeue)\n";
        std::cout << "3. Ver Frente\n";
        std::cout << "4. Ver Final\n";
        std::cout << "5. Mostrar Cola\n";
        std::cout << "6. Estado y Tamano\n";
        std::cout << "0. Volver al menu principal\n";
        opcion = leerEntero("Opcion: ");

        int valor;
        switch (opcion) {
            case 1:
                valor = leerEntero("Valor a encolar: ");
                if (cola.encolar(valor)) {
                    std::cout << "  -> Encolado: " << valor << "\n";
                    cola.mostrar();
                }
                break;
            case 2:
                if (cola.desencolar(valor)) {
                    std::cout << "  -> Desencolado: " << valor << "\n";
                    cola.mostrar();
                }
                break;
            case 3:
                if (cola.verFrente(valor)) {
                    std::cout << "  -> Frente actual: " << valor << "\n";
                } else {
                    std::cout << "  [!] La cola esta vacia.\n";
                }
                break;
            case 4:
                if (cola.verFinal(valor)) {
                    std::cout << "  -> Final actual: " << valor << "\n";
                } else {
                    std::cout << "  [!] La cola esta vacia.\n";
                }
                break;
            case 5:
                cola.mostrar();
                break;
            case 6:
                std::cout << "  -> Tamano: " << cola.tamano()
                          << " | Vacia: " << (cola.estaVacia() ? "Si" : "No")
                          << " | Llena: " << (cola.estaLlena() ? "Si" : "No") << "\n";
                break;
            case 0:
                break;
            default:
                std::cout << "Opcion no valida.\n";
        }
    } while (opcion != 0);
}

void menuNodos() {
    ColaSimpleNodos cola;

    int opcion = 0;
    do {
        std::cout << "\n--- COLA SIMPLE CON NODOS ENLAZADOS (FIFO) ---\n";
        std::cout << "1. Encolar (Enqueue)\n";
        std::cout << "2. Desencolar (Dequeue)\n";
        std::cout << "3. Ver Frente\n";
        std::cout << "4. Ver Final\n";
        std::cout << "5. Mostrar Cola\n";
        std::cout << "6. Estado y Tamano\n";
        std::cout << "0. Volver al menu principal\n";
        opcion = leerEntero("Opcion: ");

        int valor;
        switch (opcion) {
            case 1:
                valor = leerEntero("Valor a encolar: ");
                cola.encolar(valor);
                std::cout << "  -> Encolado: " << valor << "\n";
                cola.mostrar();
                break;
            case 2:
                if (cola.desencolar(valor)) {
                    std::cout << "  -> Desencolado: " << valor << "\n";
                    cola.mostrar();
                }
                break;
            case 3:
                if (cola.verFrente(valor)) {
                    std::cout << "  -> Frente actual: " << valor << "\n";
                } else {
                    std::cout << "  [!] La cola esta vacia.\n";
                }
                break;
            case 4:
                if (cola.verFinal(valor)) {
                    std::cout << "  -> Final actual: " << valor << "\n";
                } else {
                    std::cout << "  [!] La cola esta vacia.\n";
                }
                break;
            case 5:
                cola.mostrar();
                break;
            case 6:
                std::cout << "  -> Tamano: " << cola.tamano()
                          << " | Vacia: " << (cola.estaVacia() ? "Si" : "No") << "\n";
                break;
            case 0:
                break;
            default:
                std::cout << "Opcion no valida.\n";
        }
    } while (opcion != 0);
}

int main() {
    int opcion = 0;
    do {
        std::cout << "\n=============================================\n";
        std::cout << "        SISTEMA DE COLAS SIMPLES (C++)       \n";
        std::cout << "=============================================\n";
        std::cout << "1. Operar Cola Simple con Arreglo\n";
        std::cout << "2. Operar Cola Simple con Nodos Enlazados\n";
        std::cout << "0. Salir\n";
        opcion = leerEntero("Seleccione una opcion: ");

        switch (opcion) {
            case 1:
                menuArreglo();
                break;
            case 2:
                menuNodos();
                break;
            case 0:
                std::cout << "Programa finalizado.\n";
                break;
            default:
                std::cout << "Opcion no valida.\n";
        }
    } while (opcion != 0);

    return 0;
}
