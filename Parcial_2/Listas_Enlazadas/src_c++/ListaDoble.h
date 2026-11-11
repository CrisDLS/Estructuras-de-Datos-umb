#pragma once
#include "Lista.h"  // Incluye la clase base Lista
#include <iostream> // Para mostrar mensajes en pantalla (cout)

// =============================================================================
// ESTRUCTURA: NodoDoble
// Representa un nodo de una lista doblemente enlazada.
// Guarda un valor entero y dos punteros: uno hacia el siguiente y otro hacia el anterior.
// =============================================================================
struct NodoDoble {
    int dato;             // Valor numérico que guarda este nodo
    NodoDoble* siguiente; // Puntero hacia el siguiente nodo (hacia adelante)
    NodoDoble* anterior;  // Puntero hacia el nodo anterior (hacia atrás)

    // Constructor: guarda el valor y asigna opcionalmente los enlaces siguiente y anterior
    NodoDoble(int val, NodoDoble* sig = nullptr, NodoDoble* ant = nullptr)
        : dato(val), siguiente(sig), anterior(ant) {}
};

// =============================================================================
// CLASE: ListaDoble
// Implementa una lista enlazada bidireccional (permite avanzar y retroceder).
// Mantiene un puntero al primer elemento (cabeza) y otro al último (cola).
// =============================================================================
class ListaDoble : public Lista {
private:
    NodoDoble* cabeza; // Puntero que apunta al primer nodo de la lista
    NodoDoble* cola;   // Puntero que apunta al último nodo de la lista
    size_t longitud;   // Contador con la cantidad total de elementos

public:
    // Constructor: inicializa cabeza y cola en nullptr y la cantidad de elementos en 0
    ListaDoble() : cabeza(nullptr), cola(nullptr), longitud(0) {}

    // Destructor: se ejecuta al terminar para liberar toda la memoria ocupada
    ~ListaDoble() override {
        vaciar();
    }

    // Deshabilita la copia para evitar problemas de memoria duplicada
    ListaDoble(const ListaDoble&) = delete;
    ListaDoble& operator=(const ListaDoble&) = delete;

    // Inserta un número al inicio de la lista doble
    void insertarInicio(int dato) override {
        // Creamos el nuevo nodo: su siguiente es la cabeza actual y su anterior es nullptr
        NodoDoble* nuevo = new NodoDoble(dato, cabeza, nullptr);

        // Si la lista está vacía, el nuevo nodo es tanto la cabeza como la cola
        if (estaVacia()) {
            cabeza = cola = nuevo;
        } else {
            // Conectamos el nodo cabeza previo hacia atrás con el nuevo nodo
            cabeza->anterior = nuevo;
            // La cabeza oficial de la lista pasa a ser el nuevo nodo
            cabeza = nuevo;
        }

        // Aumentamos en uno el contador de elementos
        longitud++;
    }

    // Inserta un número al final de la lista doble de forma directa usando la cola
    void insertarFinal(int dato) override {
        // Si la lista está vacía, insertar al final es lo mismo que insertar al inicio
        if (estaVacia()) {
            insertarInicio(dato);
            return;
        }

        // Creamos el nuevo nodo: su anterior es la cola actual y su siguiente es nullptr
        NodoDoble* nuevo = new NodoDoble(dato, nullptr, cola);

        // Conectamos la cola actual hacia adelante con el nuevo nodo
        cola->siguiente = nuevo;

        // La cola oficial de la lista pasa a ser el nuevo nodo
        cola = nuevo;

        // Aumentamos en uno el contador de elementos
        longitud++;
    }

    // Inserta un número en una posición específica de la lista doble
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
        NodoDoble* actual = cabeza;

        // Avanzamos hasta pararnos en el nodo que actualmente ocupa la posición deseada
        for (int i = 0; i < pos; ++i) {
            actual = actual->siguiente;
        }

        // Creamos el nuevo nodo enlazándolo entre el nodo anterior y el nodo actual
        NodoDoble* nuevo = new NodoDoble(dato, actual, actual->anterior);

        // Conectamos el nodo anterior hacia adelante con el nuevo nodo
        actual->anterior->siguiente = nuevo;

        // Conectamos el nodo actual hacia atrás con el nuevo nodo
        actual->anterior = nuevo;

        // Aumentamos en uno el contador de elementos
        longitud++;
    }

    // Busca y elimina el primer nodo que contenga el valor especificado
    bool eliminar(int dato) override {
        // Si la lista está vacía, no se puede eliminar nada
        if (estaVacia()) {
            return false;
        }

        // Puntero para buscar el nodo con el valor deseado
        NodoDoble* actual = cabeza;

        // Avanzamos nodo por nodo mientras no encontremos el valor
        while (actual != nullptr && actual->dato != dato) {
            actual = actual->siguiente;
        }

        // Si llegamos al final de la lista y no encontramos el valor
        if (actual == nullptr) {
            return false;
        }

        // Caso 1: La lista tiene un solo nodo (cabeza y cola son el mismo)
        if (cabeza == cola) {
            cabeza = cola = nullptr;
        }
        // Caso 2: El nodo a eliminar es el primero (la cabeza)
        else if (actual == cabeza) {
            cabeza = cabeza->siguiente; // Movemos la cabeza al segundo nodo
            cabeza->anterior = nullptr; // El nuevo primer nodo ya no tiene nodo anterior
        }
        // Caso 3: El nodo a eliminar es el último (la cola)
        else if (actual == cola) {
            cola = cola->anterior;     // Movemos la cola al penúltimo nodo
            cola->siguiente = nullptr; // El nuevo último nodo ya no tiene nodo siguiente
        }
        // Caso 4: El nodo a eliminar está en medio de otros dos nodos
        else {
            actual->anterior->siguiente = actual->siguiente; // El nodo anterior se salta al siguiente
            actual->siguiente->anterior = actual->anterior; // El nodo siguiente se salta al anterior
        }

        // Liberamos la memoria del nodo eliminado
        delete actual;

        // Reducimos en uno el contador de elementos
        longitud--;

        // Retornamos true indicando éxito
        return true;
    }

    // Busca si un valor existe dentro de la lista doble
    bool buscar(int dato) const override {
        // Puntero para recorrer la lista comenzando en la cabeza
        NodoDoble* actual = cabeza;

        // Recorremos la lista nodo por nodo
        while (actual != nullptr) {
            // Si el valor coincide con el buscado, retornamos true
            if (actual->dato == dato) {
                return true;
            }
            actual = actual->siguiente; // Avanzamos hacia adelante
        }

        // Si revisamos toda la lista y no lo encontramos, retornamos false
        return false;
    }

    // Muestra los elementos en orden normal (desde la cabeza hasta la cola)
    void mostrar() const override {
        // Si la lista está vacía, mostramos un mensaje informativo
        if (estaVacia()) {
            std::cout << "[Lista doble vacia]" << std::endl;
            return;
        }

        std::cout << "nullptr <-> ";

        // Puntero para recorrer la lista desde el primer nodo
        NodoDoble* actual = cabeza;

        // Imprimimos cada valor con flechas dobles de enlace
        while (actual != nullptr) {
            std::cout << actual->dato << " <-> ";
            actual = actual->siguiente; // Avanzamos hacia adelante
        }

        std::cout << "nullptr" << std::endl;
    }

    // Muestra los elementos en orden inverso (desde la cola hasta la cabeza)
    void mostrarInverso() const {
        // Si la lista está vacía, mostramos un mensaje informativo
        if (estaVacia()) {
            std::cout << "[Lista doble vacia]" << std::endl;
            return;
        }

        std::cout << "nullptr <-> ";

        // Puntero para recorrer la lista comenzando desde el último nodo (cola)
        NodoDoble* actual = cola;

        // Imprimimos cada valor retrocediendo con el puntero anterior
        while (actual != nullptr) {
            std::cout << actual->dato << " <-> ";
            actual = actual->anterior; // Retrocedemos hacia atrás
        }

        std::cout << "nullptr" << std::endl;
    }

    // Verifica si la lista no contiene elementos
    bool estaVacia() const override {
        return cabeza == nullptr;
    }

    // Retorna la cantidad total de elementos guardados
    size_t tamano() const override {
        return longitud;
    }

    // Elimina todos los nodos de la lista uno por uno
    void vaciar() override {
        // Puntero para recorrer la lista desde la cabeza
        NodoDoble* actual = cabeza;

        // Mientras existan nodos en la lista
        while (actual != nullptr) {
            NodoDoble* temp = actual;   // Guardamos el nodo actual en una variable temporal
            actual = actual->siguiente; // Avanzamos al siguiente nodo
            delete temp;                // Liberamos la memoria del nodo guardado
        }

        // Restablecemos los punteros y el contador en cero
        cabeza = nullptr;
        cola = nullptr;
        longitud = 0;
    }

    // Retorna el nombre del tipo de lista ("Lista Doble")
    std::string obtenerTipo() const override {
        return "Lista Doble";
    }
};
