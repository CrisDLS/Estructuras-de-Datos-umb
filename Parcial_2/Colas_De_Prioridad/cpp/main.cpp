/**
 * main.cpp
 * ---------------------------------------------------------------
 * Interfaz de consola para probar las dos implementaciones de la
 * cola de prioridad:
 *   1) ColaPrioridadNodos   (lista enlazada)
 *   2) ColaPrioridadArreglo (arreglo)
 *   3) ColaPrioridadHeap    (monticulo binario sobre arreglo)
 *
 * Compilar:  g++ -std=c++11 -Wall main.cpp -o cola
 * Ejecutar:  ./cola   (en Windows: cola.exe)
 */

#include <iostream>   // cin y cout (entrada/salida por consola)
#include <string>     // Tipo string y getline
#include <limits>     // numeric_limits, para limpiar el búfer de entrada

#include "ColaPrioridadNodos.h"     // Implementación con nodos
#include "ColaPrioridadArreglo.h"   // Implementación con arreglo
#include "ColaPrioridadHeap.h"      // Implementación con heap (montículo binario)

using namespace std;  // Evita escribir std:: antes de cada elemento

/**
 * leerEntero: muestra un mensaje y lee un número entero válido.
 * Repite la pregunta hasta que el usuario escriba un entero.
 * @param mensaje texto que se muestra al usuario
 * @return el entero leído
 */
int leerEntero(const string& mensaje) {
    int valor;   // Aquí se guarda el número leído
    while (true) {                 // Ciclo que se repite hasta tener un valor válido
        cout << mensaje;           // Muestra la pregunta
        if (cin >> valor) {        // Si la lectura del entero fue correcta...
            cin.ignore(numeric_limits<streamsize>::max(), '\n');  // ...descarta el resto de la línea
            return valor;          // ...y devuelve el valor
        }
        cin.clear();               // Limpia el estado de error de cin
        cin.ignore(numeric_limits<streamsize>::max(), '\n');      // Descarta la entrada inválida
        cout << "Entrada invalida. Escribe un numero entero." << endl;  // Avisa del error
    }
}

/**
 * mostrarMenu: imprime las opciones disponibles.
 */
void mostrarMenu() {
    cout << "\n===== COLA DE PRIORIDAD =====" << endl;
    cout << "(menor numero = mayor prioridad)" << endl;
    cout << "1. Encolar elemento" << endl;
    cout << "2. Desencolar (atender) elemento" << endl;
    cout << "3. Ver frente" << endl;
    cout << "4. Mostrar cola" << endl;
    cout << "5. Ver tamanio" << endl;
    cout << "6. Vaciar cola" << endl;
    cout << "0. Volver / salir" << endl;
}

/**
 * ejecutarMenu: maneja el menú para CUALQUIER cola que tenga los
 * mismos métodos (plantilla). Así se evita repetir código para
 * la versión de nodos y la de arreglo.
 * @param cola referencia a la cola con la que se trabaja
 */
template <typename Cola>
void ejecutarMenu(Cola& cola) {
    int opcion = -1;   // Opción elegida por el usuario

    while (opcion != 0) {   // Se repite hasta que el usuario elija 0
        mostrarMenu();                         // Muestra las opciones
        opcion = leerEntero("Elige una opcion: ");   // Lee la opción

        if (opcion == 1) {   // ----- ENCOLAR -----
            string dato;     // Dato que escribirá el usuario
            cout << "Dato (texto): ";   // Pide el dato
            getline(cin, dato);         // Lee la línea completa (admite espacios)
            int prioridad = leerEntero("Prioridad (entero, 1 = maxima): ");  // Pide la prioridad

            if (cola.encolar(dato, prioridad)) {   // Intenta insertar
                cout << "Elemento encolado." << endl;   // Éxito
            } else {
                cout << "No se pudo encolar: la cola esta llena." << endl;  // Arreglo lleno
            }

        } else if (opcion == 2) {   // ----- DESENCOLAR -----
            string dato;       // Recibirá el dato eliminado
            int prioridad;     // Recibirá la prioridad eliminada
            if (cola.desencolar(dato, prioridad)) {   // Intenta eliminar el frente
                cout << "Atendido: [Prioridad " << prioridad << "] " << dato << endl;
            } else {
                cout << "La cola esta vacia." << endl;   // No había elementos
            }

        } else if (opcion == 3) {   // ----- VER FRENTE -----
            string dato;       // Recibirá el dato del frente
            int prioridad;     // Recibirá la prioridad del frente
            if (cola.verFrente(dato, prioridad)) {   // Consulta sin eliminar
                cout << "Frente: [Prioridad " << prioridad << "] " << dato << endl;
            } else {
                cout << "La cola esta vacia." << endl;   // No había elementos
            }

        } else if (opcion == 4) {   // ----- MOSTRAR -----
            cout << "--- Contenido de la cola ---" << endl;
            cola.mostrar();   // Imprime todos los elementos en orden de atención

        } else if (opcion == 5) {   // ----- TAMAÑO -----
            cout << "Elementos en la cola: " << cola.tamanio() << endl;

        } else if (opcion == 6) {   // ----- VACIAR -----
            cola.vaciar();   // Elimina todos los elementos
            cout << "Cola vaciada." << endl;

        } else if (opcion != 0) {   // Cualquier otro número no es válido
            cout << "Opcion no valida." << endl;
        }
    }
}

/**
 * main: punto de entrada del programa. Permite elegir la implementación.
 */
int main() {
    int eleccion = -1;   // Implementación elegida por el usuario

    while (eleccion != 0) {   // Se repite hasta que el usuario elija salir
        cout << "\n===== MENU PRINCIPAL =====" << endl;
        cout << "1. Cola de prioridad con NODOS" << endl;
        cout << "2. Cola de prioridad con ARREGLO" << endl;
        cout << "3. Cola de prioridad con HEAP" << endl;
        cout << "0. Salir" << endl;
        eleccion = leerEntero("Elige una implementacion: ");   // Lee la elección

        if (eleccion == 1) {                 // Versión con nodos
            ColaPrioridadNodos cola;         // Crea la cola (sin límite de tamaño)
            ejecutarMenu(cola);              // Abre el menú de operaciones
        } else if (eleccion == 2) {          // Versión con arreglo
            int cap = leerEntero("Capacidad maxima del arreglo: ");  // Pide la capacidad
            ColaPrioridadArreglo cola(cap);  // Crea la cola con esa capacidad
            ejecutarMenu(cola);              // Abre el menú de operaciones
        } else if (eleccion == 3) {          // Versión con heap
            ColaPrioridadHeap cola;          // Crea la cola (el arreglo crece solo)
            ejecutarMenu(cola);              // Abre el menú de operaciones
        } else if (eleccion != 0) {          // Opción fuera de rango
            cout << "Opcion no valida." << endl;
        }
    }

    cout << "Programa terminado." << endl;   // Mensaje de despedida
    return 0;   // 0 indica que el programa terminó sin errores
}
