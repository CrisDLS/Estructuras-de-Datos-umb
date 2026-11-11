#pragma once
#include "Lista.h"  // Incluye la clase base Lista
#include <iostream> // Para mostrar mensajes en pantalla (cout)

// =============================================================================
// ESTRUCTURA: NodoSimple
// Representa un nodo de una lista simplemente enlazada.
// Guarda un valor entero y un puntero que apunta al siguiente nodo.
// =============================================================================
struct NodoSimple {
    int dato;              // Valor numérico que guarda este nodo
    NodoSimple* siguiente; // Puntero que apunta al siguiente nodo de la lista

    // Constructor: guarda el valor y conecta con el siguiente nodo (por defecto ninguno)
    NodoSimple(int val, NodoSimple* sig = nullptr) : dato(val), siguiente(sig) {}
};

// =============================================================================
// CLASE: ListaSimple
// Implementa una lista enlazada donde cada nodo apunta únicamente al siguiente.
// Hereda todas las operaciones definidas en la clase base Lista.
// =============================================================================
class ListaSimple : public Lista {
private:
    NodoSimple* cabeza; // Puntero que apunta al primer nodo de la lista
    size_t longitud;    // Contador con la cantidad de elementos en la lista

public:
    // Constructor: inicializa la lista vacía con cabeza en nullptr y tamaño en 0
    ListaSimple() : cabeza(nullptr), longitud(0) {}

    // Destructor: se ejecuta al finalizar la lista para liberar toda la memoria ocupada
    ~ListaSimple() override {
        vaciar();
    }

    // Deshabilita la copia para evitar problemas de memoria duplicada
    ListaSimple(const ListaSimple&) = delete;
    ListaSimple& operator=(const ListaSimple&) = delete;

    // Inserta un número al inicio de la lista
    void insertarInicio(int dato) override {
        // Creamos el nuevo nodo con el valor recibido y lo conectamos a la cabeza actual
        cabeza = new NodoSimple(dato, cabeza);
        // Aumentamos en uno el contador de elementos
        longitud++;
    }

    // Inserta un número al final de la lista
    void insertarFinal(int dato) override {
        // Si la lista está vacía, insertar al final es lo mismo que insertar al inicio
        if (estaVacia()) {
            insertarInicio(dato);
            return;
        }

        // Creamos un puntero temporal para recorrer la lista desde el primer nodo
        NodoSimple* actual = cabeza;

        // Avanzamos hasta llegar al último nodo (el nodo cuyo siguiente sea nullptr)
        while (actual->siguiente != nullptr) {
            actual = actual->siguiente;
        }

        // Conectamos el nuevo nodo después del último nodo encontrado
        actual->siguiente = new NodoSimple(dato, nullptr);

        // Aumentamos en uno el contador de elementos
        longitud++;
    }

    // Inserta un número en una posición específica de la lista
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
        NodoSimple* actual = cabeza;

        // Avanzamos hasta pararnos en el nodo anterior a la posición deseada
        for (int i = 0; i < pos - 1; ++i) {
            actual = actual->siguiente;
        }

        // Conectamos el nuevo nodo entre el nodo actual y su siguiente
        actual->siguiente = new NodoSimple(dato, actual->siguiente);

        // Aumentamos en uno el contador de elementos
        longitud++;
    }

    // Busca y elimina el primer nodo que contenga el valor especificado
    bool eliminar(int dato) override {
        // Si la lista no tiene elementos, no se puede eliminar nada
        if (estaVacia()) {
            return false;
        }

        // Caso 1: El valor que queremos eliminar está en el primer nodo (la cabeza)
        if (cabeza->dato == dato) {
            NodoSimple* temp = cabeza;  // Guardamos el nodo en una variable temporal
            cabeza = cabeza->siguiente; // Movemos la cabeza al segundo nodo de la lista
            delete temp;                // Liberamos la memoria del nodo eliminado
            longitud--;                 // Reducimos en uno el contador de elementos
            return true;                // Retornamos true indicando éxito
        }

        // Caso 2: El valor puede estar más adelante en la lista
        NodoSimple* actual = cabeza;

        // Avanzamos mientras haya un siguiente nodo y su dato sea diferente al buscado
        while (actual->siguiente != nullptr && actual->siguiente->dato != dato) {
            actual = actual->siguiente;
        }

        // Si llegamos al final de la lista sin encontrar el valor
        if (actual->siguiente == nullptr) {
            return false;
        }

        // Guardamos el nodo que vamos a eliminar
        NodoSimple* aEliminar = actual->siguiente;

        // Saltamos el nodo a eliminar conectando el nodo actual con el que sigue después
        actual->siguiente = aEliminar->siguiente;

        // Liberamos la memoria del nodo eliminado
        delete aEliminar;

        // Reducimos en uno el contador de elementos
        longitud--;

        // Retornamos true indicando que el elemento fue eliminado
        return true;
    }

    // Busca si un valor existe dentro de la lista
    bool buscar(int dato) const override {
        // Puntero para recorrer la lista comenzando en la cabeza
        NodoSimple* actual = cabeza;

        // Recorremos la lista nodo por nodo
        while (actual != nullptr) {
            // Si el valor del nodo actual coincide con el buscado, retornamos true
            if (actual->dato == dato) {
                return true;
            }
            actual = actual->siguiente; // Avanzamos al siguiente nodo
        }

        // Si revisamos toda la lista y no lo encontramos, retornamos false
        return false;
    }

    // Muestra todos los elementos de la lista en la pantalla
    void mostrar() const override {
        // Si la lista está vacía, mostramos un mensaje informativo
        if (estaVacia()) {
            std::cout << "[Lista simple vacia]" << std::endl;
            return;
        }

        // Puntero para recorrer la lista desde el inicio
        NodoSimple* actual = cabeza;

        // Imprimimos cada valor seguido de una flecha
        while (actual != nullptr) {
            std::cout << actual->dato << " -> ";
            actual = actual->siguiente; // Avanzamos al siguiente nodo
        }

        // Mostramos nullptr para indicar el final de la lista
        std::cout << "nullptr" << std::endl;
    }

    // Verifica si la lista no tiene elementos
    bool estaVacia() const override {
        return cabeza == nullptr; // Retorna true si la cabeza no apunta a ningún nodo
    }

    // Retorna la cantidad total de elementos guardados
    size_t tamano() const override {
        return longitud;
    }

    // Elimina todos los nodos de la lista uno por uno
    void vaciar() override {
        // Mientras todavía existan nodos en la lista
        while (cabeza != nullptr) {
            NodoSimple* temp = cabeza;  // Guardamos el nodo actual en una variable temporal
            cabeza = cabeza->siguiente; // Avanzamos la cabeza al siguiente nodo
            delete temp;                // Liberamos la memoria del nodo guardado
        }

        // Restablecemos el contador de elementos en cero
        longitud = 0;
    }

    // Retorna el nombre del tipo de lista ("Lista Simple")
    std::string obtenerTipo() const override {
        return "Lista Simple";
    }
};
