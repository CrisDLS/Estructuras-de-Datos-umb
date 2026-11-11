#pragma once
#include <iostream>

// =============================================================================
// ESTRUCTURA: NodoColaSimple
// Nodo para una cola simple dinámica enlazada en el Heap.
// =============================================================================
struct NodoColaSimple {
    int dato;
    NodoColaSimple* siguiente;

    NodoColaSimple(int val) : dato(val), siguiente(nullptr) {}
};

// =============================================================================
// CLASE: ColaSimpleNodos
// Implementa una Cola Simple (FIFO) dinámica mediante nodos enlazados.
// =============================================================================
class ColaSimpleNodos {
private:
    NodoColaSimple* frente;
    NodoColaSimple* final;
    int cantidad;

public:
    // Constructor: inicializa la cola vacía
    ColaSimpleNodos() : frente(nullptr), final(nullptr), cantidad(0) {}

    // Destructor: libera todos los nodos restantes en memoria
    ~ColaSimpleNodos() {
        int temp;
        while (!estaVacia()) {
            desencolar(temp);
        }
    }

    // Deshabilita copia superficial
    ColaSimpleNodos(const ColaSimpleNodos&) = delete;
    ColaSimpleNodos& operator=(const ColaSimpleNodos&) = delete;

    // Verifica si la cola está vacía
    bool estaVacia() const {
        return frente == nullptr;
    }

    // Retorna el número de elementos actuales
    int tamano() const {
        return cantidad;
    }

    // Inserta un elemento al final de la cola en O(1)
    void encolar(int valor) {
        NodoColaSimple* nuevo = new NodoColaSimple(valor);
        if (estaVacia()) {
            frente = final = nuevo;
        } else {
            final->siguiente = nuevo;
            final = nuevo;
        }
        cantidad++;
    }

    // Elimina el elemento del frente de la cola en O(1)
    bool desencolar(int& valorExtraido) {
        if (estaVacia()) {
            std::cout << "  [!] Underflow: La cola simple con nodos esta vacia.\n";
            return false;
        }
        NodoColaSimple* temp = frente;
        valorExtraido = temp->dato;
        frente = frente->siguiente;

        if (frente == nullptr) {
            final = nullptr;
        }

        delete temp;
        cantidad--;
        return true;
    }

    // Consulta el elemento del frente sin eliminarlo
    bool verFrente(int& valorFrente) const {
        if (estaVacia()) {
            return false;
        }
        valorFrente = frente->dato;
        return true;
    }

    // Consulta el elemento del final sin eliminarlo
    bool verFinal(int& valorFinal) const {
        if (estaVacia()) {
            return false;
        }
        valorFinal = final->dato;
        return true;
    }

    // Muestra todos los nodos en espera desde el frente hasta el final
    void mostrar() const {
        if (estaVacia()) {
            std::cout << "  Cola Simple (Nodos): [vacia]\n";
            return;
        }
        std::cout << "  FRENTE -> ";
        NodoColaSimple* actual = frente;
        while (actual != nullptr) {
            std::cout << actual->dato;
            if (actual->siguiente != nullptr) {
                std::cout << " -> ";
            }
            actual = actual->siguiente;
        }
        std::cout << " <- FINAL (Total: " << cantidad << ")\n";
    }
};
