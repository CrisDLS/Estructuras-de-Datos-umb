#include <iostream>
#include "ColaCircularArreglo.h"
using namespace std;

// Constructor
ColaCircularArreglo::ColaCircularArreglo() {
    frente = 0;
    final = -1;
    cantidad = 0;
}

// Verifica si esta vacia
bool ColaCircularArreglo::estaVacia() {
    return cantidad == 0;
}

// Verifica si esta llena
bool ColaCircularArreglo::estaLlena() {
    return cantidad == 5;
}

// Agrega un cliente
void ColaCircularArreglo::encolar(string nombre) {
    if (estaLlena()) {
        cout << "La cola esta llena.\n";
        return;
    }

    final = (final + 1) % 5;
    clientes[final] = nombre;
    cantidad++;
}

// Atiende al primer cliente
void ColaCircularArreglo::desencolar() {
    if (estaVacia()) {
        cout << "No hay clientes por atender.\n";
        return;
    }

    cout << "Atendiendo a: "
         << clientes[frente] << endl;

    frente = (frente + 1) % 5;
    cantidad--;
}

// Muestra los clientes pendientes
void ColaCircularArreglo::mostrar() {
    if (estaVacia()) {
        cout << "No hay clientes en espera.\n";
        return;
    }

    cout << "Clientes en espera: ";

    for (int i = 0; i < cantidad; i++) {
        cout << clientes[(frente + i) % 5] << " ";
    }

    cout << endl;
}

// Programa principal

int main() {
    ColaCircularArreglo cola;
    int cantidad;
    string nombre;

    cout << "=== ATENCION A CLIENTES ===\n";
    cout << "Maximo 5 clientes\n";
    cout << "Cuantos clientes desea registrar? ";
    cin >> cantidad;

    if (cantidad < 0 || cantidad > 5) {
        cout << "Ingrese una cantidad de 0 a 5.\n";
        return 0;
    }

    for (int i = 0; i < cantidad; i++) {
        cout << "Nombre del cliente " << i + 1 << ": ";
        cin >> nombre;

        cola.encolar(nombre);
    }

    cout << "\n=== COLA INICIAL ===\n";
    cola.mostrar();

    cout << "\n=== INICIANDO ATENCION ===\n";

    while (!cola.estaVacia()) {
        cola.desencolar();

        cout << "Estado actual de la cola:\n";
        cola.mostrar();

        cout << "-------------------------\n";
    }

    cout << "\nTodos los clientes fueron atendidos.\n";

    return 0;
}
