#include <iostream>
#include "ColaCircularNodos.h"
using namespace std;

// Constructor
ColaCircularNodos::ColaCircularNodos() {
    frente = nullptr;
    final = nullptr;
}

// Libera la memoria al terminar
ColaCircularNodos::~ColaCircularNodos() {
    while (!estaVacia()) {
        Nodo* temporal = frente;

        if (frente == final) {
            frente = nullptr;
            final = nullptr;
        } else {
            frente = frente->siguiente;
            final->siguiente = frente;
        }

        delete temporal;
    }
}

// Verifica si esta vacia
bool ColaCircularNodos::estaVacia() {
    return frente == nullptr;
}

// Agrega un cliente
void ColaCircularNodos::encolar(string nombre) {
    Nodo* nuevo = new Nodo;
    nuevo->nombre = nombre;

    if (estaVacia()) {
        frente = nuevo;
        final = nuevo;
        nuevo->siguiente = nuevo;
    } else {
        nuevo->siguiente = frente;
        final->siguiente = nuevo;
        final = nuevo;
    }
}

// Atiende al primer cliente
void ColaCircularNodos::desencolar() {
    if (estaVacia()) {
        cout << "No hay clientes por atender.\n";
        return;
    }

    Nodo* temporal = frente;

    cout << "Atendiendo a: "
         << frente->nombre << endl;

    if (frente == final) {
        frente = nullptr;
        final = nullptr;
    } else {
        frente = frente->siguiente;
        final->siguiente = frente;
    }

    delete temporal;
}

// Muestra los clientes pendientes
void ColaCircularNodos::mostrar() {
    if (estaVacia()) {
        cout << "No hay clientes en espera.\n";
        return;
    }

    Nodo* actual = frente;

    cout << "Clientes en espera: ";

    do {
        cout << actual->nombre << " ";
        actual = actual->siguiente;
    } while (actual != frente);

    cout << endl;
}

// Programa principal

int main() {
    ColaCircularNodos cola;
    int cantidad;
    string nombre;

    cout << "=== ATENCION A CLIENTES ===\n";
    cout << "Cuantos clientes desea registrar? ";
    cin >> cantidad;

    if (cantidad < 0) {
        cout << "Cantidad no valida.\n";
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
