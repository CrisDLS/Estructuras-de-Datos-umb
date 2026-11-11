#pragma once
#include <iostream>

// =============================================================================
// CLASE: ColaSimpleArreglo
// Implementa una Cola Simple (FIFO: First-In, First-Out) sobre un arreglo
// en memoria contigua de capacidad fija.
// =============================================================================
class ColaSimpleArreglo {
private:
    int* datos;
    int capacidad;
    int frente;
    int final;
    int cantidad;

public:
    // Constructor: reserva memoria para el arreglo con la capacidad indicada
    ColaSimpleArreglo(int cap = 5) {
        capacidad = (cap > 0) ? cap : 5;
        datos = new int[capacidad];
        frente = 0;
        final = -1;
        cantidad = 0;
    }

    // Destructor: libera el bloque de memoria del arreglo
    ~ColaSimpleArreglo() {
        delete[] datos;
    }

    // Deshabilita copia superficial
    ColaSimpleArreglo(const ColaSimpleArreglo&) = delete;
    ColaSimpleArreglo& operator=(const ColaSimpleArreglo&) = delete;

    // Verifica si la cola no tiene elementos
    bool estaVacia() const {
        return cantidad == 0;
    }

    // Verifica si se alcanzó el límite del arreglo lineal
    bool estaLlena() const {
        return final == capacidad - 1;
    }

    // Retorna el número de elementos actuales
    int tamano() const {
        return cantidad;
    }

    // Inserta un elemento al final de la cola (Enqueue)
    bool encolar(int valor) {
        if (estaLlena()) {
            std::cout << "  [!] Overflow: El arreglo de la cola simple llego a su limite.\n";
            return false;
        }
        final++;
        datos[final] = valor;
        cantidad++;
        return true;
    }

    // Elimina y retorna el elemento del frente de la cola (Dequeue)
    bool desencolar(int& valorExtraido) {
        if (estaVacia()) {
            std::cout << "  [!] Underflow: La cola simple esta vacia.\n";
            return false;
        }
        valorExtraido = datos[frente];
        frente++;
        cantidad--;

        // Si la cola queda vacía tras desencolar, reiniciamos los índices para reutilizar el arreglo
        if (cantidad == 0) {
            frente = 0;
            final = -1;
        }
        return true;
    }

    // Consulta el elemento del frente sin retirarlo (Peek / Front)
    bool verFrente(int& valorFrente) const {
        if (estaVacia()) {
            return false;
        }
        valorFrente = datos[frente];
        return true;
    }

    // Consulta el elemento del final sin retirarlo (Rear)
    bool verFinal(int& valorFinal) const {
        if (estaVacia()) {
            return false;
        }
        valorFinal = datos[final];
        return true;
    }

    // Muestra todos los elementos de frente a final
    void mostrar() const {
        if (estaVacia()) {
            std::cout << "  Cola Simple (Arreglo): [vacia]\n";
            return;
        }
        std::cout << "  FRENTE -> [ ";
        for (int i = frente; i <= final; ++i) {
            std::cout << datos[i];
            if (i < final) {
                std::cout << " -> ";
            }
        }
        std::cout << " ] <- FINAL\n";
    }
};
