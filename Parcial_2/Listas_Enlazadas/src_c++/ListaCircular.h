#pragma once
#include "Lista.h"  // Incluye la clase base Lista
#include <iostream> // Para mostrar mensajes en pantalla (cout)

// =============================================================================
// ESTRUCTURA: NodoCircular
// Representa un nodo de una lista enlazada circular.
// Guarda un valor entero y un puntero hacia el siguiente nodo.
// En una lista circular, ningún nodo apunta a nullptr; el último nodo apunta a la cabeza.
// =============================================================================
struct NodoCircular {
    int dato;                // Valor numérico que guarda este nodo
    NodoCircular* siguiente; // Puntero que apunta al siguiente nodo del ciclo

    // Constructor: guarda el valor y asigna opcionalmente el enlace siguiente
    NodoCircular(int val, NodoCircular* sig = nullptr) : dato(val), siguiente(sig) {}
};

// =============================================================================
// CLASE: ListaCircular
// Implementa una lista simplemente enlazada circular.
// El último nodo se conecta de regreso con el primer nodo (cabeza), formando un ciclo.
// =============================================================================
class ListaCircular : public Lista {
private:
    NodoCircular* cabeza; // Puntero que apunta al primer nodo del ciclo
    size_t longitud;      // Contador con la cantidad de elementos en el ciclo

public:
    // Constructor: inicializa la lista circular vacía con cabeza en nullptr y tamaño en 0
    ListaCircular() : cabeza(nullptr), longitud(0) {}

    // Destructor: se ejecuta al terminar para romper el ciclo y liberar toda la memoria ocupada
    ~ListaCircular() override {
        vaciar();
    }

    // Deshabilita la copia para evitar problemas de memoria duplicada
    ListaCircular(const ListaCircular&) = delete;
    ListaCircular& operator=(const ListaCircular&) = delete;

    // Inserta un número al inicio del ciclo circular
    void insertarInicio(int dato) override {
        // Creamos el nuevo nodo con el valor recibido
        NodoCircular* nuevo = new NodoCircular(dato);

        // Si la lista está vacía
        if (estaVacia()) {
            nuevo->siguiente = nuevo; // El nodo apunta a sí mismo formando un ciclo de un solo elemento
            cabeza = nuevo;           // El nuevo nodo pasa a ser la cabeza de la lista
        } else {
            // Si ya hay elementos, buscamos el último nodo del ciclo (el nodo que apunta a la cabeza)
            NodoCircular* ultimo = cabeza;
            while (ultimo->siguiente != cabeza) {
                ultimo = ultimo->siguiente;
            }

            // Conectamos el nuevo nodo hacia la cabeza actual
            nuevo->siguiente = cabeza;

            // Conectamos el último nodo hacia el nuevo nodo
            ultimo->siguiente = nuevo;

            // La cabeza oficial de la lista pasa a ser el nuevo nodo
            cabeza = nuevo;
        }

        // Aumentamos en uno el contador de elementos
        longitud++;
    }

    // Inserta un número al final del ciclo circular
    void insertarFinal(int dato) override {
        // Si la lista está vacía, insertar al final es lo mismo que insertar al inicio
        if (estaVacia()) {
            insertarInicio(dato);
            return;
        }

        // Creamos el nuevo nodo apuntando a la cabeza para cerrar el ciclo
        NodoCircular* nuevo = new NodoCircular(dato, cabeza);

        // Buscamos el último nodo actual del ciclo
        NodoCircular* ultimo = cabeza;
        while (ultimo->siguiente != cabeza) {
            ultimo = ultimo->siguiente;
        }

        // Conectamos el último nodo actual hacia el nuevo nodo
        ultimo->siguiente = nuevo;

        // Aumentamos en uno el contador de elementos (la cabeza no cambia)
        longitud++;
    }

    // Inserta un número en una posición específica del ciclo circular
    void insertarEnPosicion(int dato, int pos) override {
        // Si la posición es 0 o menor, o la lista está vacía, insertamos al inicio
        if (pos <= 0 || estaVacia()) {
            insertarInicio(dato);
            return;
        }

        // Si la posición es mayor o igual al tamaño actual, insertamos al final
        if (pos >= static_cast<int>(longitud)) {
            insertarFinal(dato);
            return;
        }

        // Puntero para recorrer la lista comenzando en la cabeza
        NodoCircular* actual = cabeza;

        // Avanzamos hasta el nodo anterior a la posición deseada
        for (int i = 0; i < pos - 1; ++i) {
            actual = actual->siguiente;
        }

        // Conectamos el nuevo nodo en medio del nodo actual y su siguiente
        actual->siguiente = new NodoCircular(dato, actual->siguiente);

        // Aumentamos en uno el contador de elementos
        longitud++;
    }

    // Busca y elimina el primer nodo que contenga el valor especificado
    bool eliminar(int dato) override {
        // Si la lista está vacía, no se puede eliminar nada
        if (estaVacia()) {
            return false;
        }

        // Caso 1: El valor que queremos eliminar está en la cabeza
        if (cabeza->dato == dato) {
            // Si es el único nodo que hay en la lista (apunta a sí mismo)
            if (cabeza->siguiente == cabeza) {
                delete cabeza;    // Liberamos la memoria del nodo
                cabeza = nullptr; // La lista queda vacía
            } else {
                // Si hay más nodos, buscamos el último nodo del ciclo
                NodoCircular* ultimo = cabeza;
                while (ultimo->siguiente != cabeza) {
                    ultimo = ultimo->siguiente;
                }

                NodoCircular* temp = cabeza;  // Guardamos la cabeza actual en una variable temporal
                cabeza = cabeza->siguiente;   // Movemos la cabeza al segundo nodo de la lista
                ultimo->siguiente = cabeza;   // El último nodo ahora apunta a la nueva cabeza
                delete temp;                  // Liberamos la memoria de la cabeza anterior
            }

            // Reducimos en uno el contador de elementos y confirmamos la eliminación
            longitud--;
            return true;
        }

        // Caso 2: El valor está en otro nodo del ciclo; usamos 'actual' para buscarlo
        NodoCircular* actual = cabeza;

        // Avanzamos mientras no demos una vuelta completa y no encontremos el valor
        while (actual->siguiente != cabeza && actual->siguiente->dato != dato) {
            actual = actual->siguiente;
        }

        // Si dimos la vuelta completa y regresamos a la cabeza sin encontrar el valor
        if (actual->siguiente == cabeza) {
            return false;
        }

        // Guardamos el nodo que vamos a eliminar
        NodoCircular* aEliminar = actual->siguiente;

        // Conectamos el nodo actual con el que sigue después del nodo a eliminar
        actual->siguiente = aEliminar->siguiente;

        // Liberamos la memoria del nodo eliminado
        delete aEliminar;

        // Reducimos en uno el contador de elementos
        longitud--;

        // Retornamos true indicando éxito
        return true;
    }

    // Busca si un valor existe dentro de la lista circular
    bool buscar(int dato) const override {
        // Si la lista está vacía, retornamos false
        if (estaVacia()) {
            return false;
        }

        // Puntero para recorrer la lista comenzando en la cabeza
        NodoCircular* actual = cabeza;

        // Recorremos la lista con un bucle do-while para evaluar cada nodo al menos una vez
        do {
            // Si el valor del nodo actual coincide con el buscado, retornamos true
            if (actual->dato == dato) {
                return true;
            }
            actual = actual->siguiente; // Avanzamos al siguiente nodo del ciclo
        } while (actual != cabeza);     // Nos detenemos al dar la vuelta completa y regresar a la cabeza

        // Si dimos la vuelta completa y no lo encontramos, retornamos false
        return false;
    }

    // Muestra los elementos de la lista completando una vuelta al ciclo
    void mostrar() const override {
        // Si la lista está vacía, mostramos un mensaje informativo
        if (estaVacia()) {
            std::cout << "[Lista circular vacia]" << std::endl;
            return;
        }

        // Puntero para recorrer la lista desde el primer nodo
        NodoCircular* actual = cabeza;

        // Recorremos la lista exactamente una vuelta completa
        do {
            std::cout << actual->dato << " -> ";
            actual = actual->siguiente; // Avanzamos al siguiente nodo
        } while (actual != cabeza);     // Condición para detener el bucle al volver a la cabeza

        // Mostramos que el ciclo regresa nuevamente a la cabeza
        std::cout << "(regresa a cabeza: " << cabeza->dato << ")" << std::endl;
    }

    // Verifica si la lista circular no contiene elementos
    bool estaVacia() const override {
        return cabeza == nullptr;
    }

    // Retorna la cantidad total de elementos en el ciclo
    size_t tamano() const override {
        return longitud;
    }

    // Elimina todos los nodos de la lista circular de forma segura
    void vaciar() override {
        // Si la lista ya está vacía, no hacemos nada
        if (estaVacia()) {
            return;
        }

        // Paso 1: Buscamos el último nodo de la lista circular
        NodoCircular* ultimo = cabeza;
        while (ultimo->siguiente != cabeza) {
            ultimo = ultimo->siguiente;
        }

        // Paso 2: Rompemos el ciclo haciendo que el último nodo apunte a nullptr (evita bucles infinitos)
        ultimo->siguiente = nullptr;

        // Paso 3: Borramos los nodos uno por uno en línea recta
        while (cabeza != nullptr) {
            NodoCircular* temp = cabeza;  // Guardamos el nodo actual en una variable temporal
            cabeza = cabeza->siguiente;   // Avanzamos la cabeza al siguiente nodo
            delete temp;                  // Liberamos la memoria del nodo guardado
        }

        // Restablecemos la cabeza en nullptr y el contador en cero
        cabeza = nullptr;
        longitud = 0;
    }

    // Retorna el nombre del tipo de lista ("Lista Circular")
    std::string obtenerTipo() const override {
        return "Lista Circular";
    }
};
