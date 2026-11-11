#pragma once
#include "Lista.h"  // Incluye la clase base Lista
#include <iostream> // Para mostrar mensajes en pantalla (cout)

// =============================================================================
// ESTRUCTURA: NodoSublista
// Representa un nodo secundario dentro de la sublista de un nodo principal.
// Por ejemplo: una materia que pertenece a un alumno.
// =============================================================================
struct NodoSublista {
    int dato;                // Valor o código del elemento secundario (ej: código de materia)
    NodoSublista* siguiente; // Puntero hacia el siguiente nodo de esta misma sublista

    // Constructor: guarda el valor y asigna opcionalmente el enlace siguiente
    NodoSublista(int val, NodoSublista* sig = nullptr) : dato(val), siguiente(sig) {}
};

// =============================================================================
// ESTRUCTURA: NodoMultilista
// Representa un nodo principal dentro de una Multilista.
// Guarda su propio identificador y tiene un puntero hacia su sublista secundaria.
// Por ejemplo: un alumno con su ID y la lista de materias que cursa.
// =============================================================================
struct NodoMultilista {
    int dato;                  // Identificador principal (ej: ID de Alumno)
    NodoSublista* sublista;    // Puntero hacia el primer nodo de su sublista de materias
    NodoMultilista* siguiente; // Puntero hacia el siguiente nodo principal de la lista

    // Constructor: guarda el ID principal y deja la sublista vacía al inicio
    NodoMultilista(int val, NodoMultilista* sig = nullptr)
        : dato(val), sublista(nullptr), siguiente(sig) {}
};

// =============================================================================
// CLASE: Multilista
// Implementa una lista de listas (estructura jerárquica).
// Cada elemento de la lista principal tiene su propia sublista independiente.
// =============================================================================
class Multilista : public Lista {
private:
    NodoMultilista* cabeza; // Puntero que apunta al primer nodo principal
    size_t longitud;        // Contador con la cantidad de nodos principales

    // Función auxiliar que elimina uno por uno todos los nodos de una sublista
    void liberarSublista(NodoSublista*& inicio) {
        // Mientras existan elementos en la sublista
        while (inicio != nullptr) {
            NodoSublista* temp = inicio; // Guardamos el subnodo actual en una variable temporal
            inicio = inicio->siguiente;  // Avanzamos al siguiente elemento de la sublista
            delete temp;                 // Liberamos la memoria del subnodo guardado
        }
        // Dejamos el puntero inicial en nullptr
        inicio = nullptr;
    }

public:
    // Constructor: inicializa la multilista vacía con cabeza en nullptr y longitud en 0
    Multilista() : cabeza(nullptr), longitud(0) {}

    // Destructor: se ejecuta al terminar para liberar todos los nodos principales y sus sublistas
    ~Multilista() override {
        vaciar();
    }

    // Deshabilita la copia para evitar problemas de memoria duplicada
    Multilista(const Multilista&) = delete;
    Multilista& operator=(const Multilista&) = delete;

    // Inserta un nuevo nodo principal al inicio de la lista
    void insertarInicio(int dato) override {
        // Creamos el nuevo nodo principal y lo conectamos a la cabeza actual
        cabeza = new NodoMultilista(dato, cabeza);
        // Aumentamos en uno el contador de nodos principales
        longitud++;
    }

    // Inserta un nuevo nodo principal al final de la lista
    void insertarFinal(int dato) override {
        // Si la lista está vacía, insertar al final es lo mismo que insertar al inicio
        if (estaVacia()) {
            insertarInicio(dato);
            return;
        }

        // Puntero para recorrer la lista principal desde la cabeza
        NodoMultilista* actual = cabeza;

        // Avanzamos hasta pararnos en el último nodo principal
        while (actual->siguiente != nullptr) {
            actual = actual->siguiente;
        }

        // Conectamos el nuevo nodo principal al final
        actual->siguiente = new NodoMultilista(dato, nullptr);

        // Aumentamos en uno el contador de nodos principales
        longitud++;
    }

    // Inserta un nodo principal en una posición específica de la lista
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

        // Puntero para recorrer la lista principal desde la cabeza
        NodoMultilista* actual = cabeza;

        // Avanzamos hasta pararnos en el nodo anterior a la posición deseada
        for (int i = 0; i < pos - 1; ++i) {
            actual = actual->siguiente;
        }

        // Conectamos el nuevo nodo principal en medio
        actual->siguiente = new NodoMultilista(dato, actual->siguiente);

        // Aumentamos en uno el contador de nodos principales
        longitud++;
    }

    // Elimina un nodo principal y toda su sublista asociada
    bool eliminar(int dato) override {
        // Si la lista está vacía, no se puede eliminar nada
        if (estaVacia()) {
            return false;
        }

        // Caso 1: El nodo principal a eliminar está en la cabeza
        if (cabeza->dato == dato) {
            NodoMultilista* temp = cabeza;  // Guardamos el nodo principal en una variable temporal
            cabeza = cabeza->siguiente;     // Movemos la cabeza al siguiente nodo principal
            liberarSublista(temp->sublista);// Primero liberamos toda su sublista asociada
            delete temp;                    // Luego liberamos el nodo principal de la memoria
            longitud--;                     // Reducimos en uno el contador de nodos principales
            return true;                    // Retornamos true indicando éxito
        }

        // Caso 2: El nodo principal está más adelante en la lista
        NodoMultilista* actual = cabeza;
        while (actual->siguiente != nullptr && actual->siguiente->dato != dato) {
            actual = actual->siguiente; // Avanzamos al siguiente nodo principal
        }

        // Si llegamos al final de la lista y no encontramos el nodo principal
        if (actual->siguiente == nullptr) {
            return false;
        }

        // Guardamos el nodo principal que vamos a eliminar
        NodoMultilista* aEliminar = actual->siguiente;

        // Saltamos el nodo a eliminar conectando el nodo actual con el que sigue después
        actual->siguiente = aEliminar->siguiente;

        // Primero liberamos toda la sublista que pertenecía a este nodo principal
        liberarSublista(aEliminar->sublista);

        // Luego liberamos el nodo principal de la memoria
        delete aEliminar;

        // Reducimos en uno el contador de nodos principales
        longitud--;

        // Retornamos true indicando que fue eliminado
        return true;
    }

    // Busca si un valor existe como nodo principal o adentro de alguna sublista
    bool buscar(int dato) const override {
        // Puntero para recorrer la lista principal
        NodoMultilista* actual = cabeza;

        // Recorremos nodo principal por nodo principal
        while (actual != nullptr) {
            // Nivel 1: Verificamos si el valor coincide con el dato del nodo principal
            if (actual->dato == dato) {
                return true;
            }

            // Nivel 2: Verificamos si el valor está dentro de su sublista asociada
            NodoSublista* subActual = actual->sublista;
            while (subActual != nullptr) {
                if (subActual->dato == dato) {
                    return true; // Encontrado dentro de una sublista
                }
                subActual = subActual->siguiente; // Avanzamos al siguiente elemento de la sublista
            }

            actual = actual->siguiente; // Avanzamos al siguiente nodo principal
        }

        // Si revisamos todos los nodos principales y sus sublistas sin encontrarlo
        return false;
    }

    // Muestra en pantalla cada nodo principal junto con su sublista de elementos
    void mostrar() const override {
        // Si la lista está vacía, mostramos un mensaje informativo
        if (estaVacia()) {
            std::cout << "[Multilista vacia]" << std::endl;
            return;
        }

        // Puntero para recorrer la lista principal desde la cabeza
        NodoMultilista* actual = cabeza;

        // Recorremos cada nodo principal
        while (actual != nullptr) {
            // Mostramos el valor del nodo principal
            std::cout << "  [Principal: " << actual->dato << "] -> Sublista: ";

            // Si este nodo principal no tiene elementos en su sublista
            if (actual->sublista == nullptr) {
                std::cout << "(vacia)";
            } else {
                // Recorremos y mostramos los elementos de su sublista
                NodoSublista* sub = actual->sublista;
                std::cout << "( ";
                while (sub != nullptr) {
                    std::cout << sub->dato;
                    if (sub->siguiente != nullptr) {
                        std::cout << " -> ";
                    }
                    sub = sub->siguiente; // Avanzamos al siguiente subnodo
                }
                std::cout << " )";
            }

            std::cout << std::endl;
            actual = actual->siguiente; // Avanzamos al siguiente nodo principal
        }
    }

    // Verifica si la lista principal no contiene elementos
    bool estaVacia() const override {
        return cabeza == nullptr;
    }

    // Retorna la cantidad total de nodos principales guardados
    size_t tamano() const override {
        return longitud;
    }

    // Elimina todos los nodos principales y todas las sublistas de la memoria
    void vaciar() override {
        // Mientras existan nodos principales en la lista
        while (cabeza != nullptr) {
            NodoMultilista* temp = cabeza;  // Guardamos el nodo principal actual en una variable temporal
            cabeza = cabeza->siguiente;     // Avanzamos la cabeza al siguiente nodo principal
            liberarSublista(temp->sublista);// Primero liberamos su sublista asociada
            delete temp;                    // Luego liberamos el nodo principal de la memoria
        }

        // Restablecemos el contador en cero
        longitud = 0;
    }

    // Retorna el nombre del tipo de lista ("Multilista")
    std::string obtenerTipo() const override {
        return "Multilista";
    }

    // Agrega un elemento a la sublista de un nodo principal específico
    bool insertarEnSublista(int idPrincipal, int datoSublista) {
        // Puntero para buscar el nodo principal
        NodoMultilista* actual = cabeza;

        // Buscamos el nodo principal que tenga el identificador solicitado
        while (actual != nullptr && actual->dato != idPrincipal) {
            actual = actual->siguiente;
        }

        // Si el nodo principal no existe en la lista
        if (actual == nullptr) {
            return false;
        }

        // Si su sublista estaba vacía, creamos el primer elemento de la sublista
        if (actual->sublista == nullptr) {
            actual->sublista = new NodoSublista(datoSublista);
        } else {
            // Si ya tenía elementos, avanzamos hasta el último elemento de la sublista
            NodoSublista* sub = actual->sublista;
            while (sub->siguiente != nullptr) {
                sub = sub->siguiente;
            }
            // Conectamos el nuevo elemento al final de la sublista
            sub->siguiente = new NodoSublista(datoSublista);
        }

        // Retornamos true indicando éxito
        return true;
    }

    // Elimina un elemento de la sublista de un nodo principal específico
    bool eliminarDeSublista(int idPrincipal, int datoSublista) {
        // Puntero para buscar el nodo principal
        NodoMultilista* actual = cabeza;

        // Buscamos el nodo principal que tenga el identificador solicitado
        while (actual != nullptr && actual->dato != idPrincipal) {
            actual = actual->siguiente;
        }

        // Si el nodo principal no existe o su sublista no tiene elementos
        if (actual == nullptr || actual->sublista == nullptr) {
            return false;
        }

        // Caso 1: El elemento a eliminar es el primero de la sublista
        if (actual->sublista->dato == datoSublista) {
            NodoSublista* temp = actual->sublista;          // Guardamos el subnodo en una variable temporal
            actual->sublista = actual->sublista->siguiente; // Movemos el inicio de la sublista al siguiente
            delete temp;                                    // Liberamos la memoria del subnodo
            return true;                                    // Retornamos true indicando éxito
        }

        // Caso 2: El elemento está más adelante en la sublista
        NodoSublista* sub = actual->sublista;
        while (sub->siguiente != nullptr && sub->siguiente->dato != datoSublista) {
            sub = sub->siguiente;
        }

        // Si recorrimos toda la sublista sin encontrar el elemento
        if (sub->siguiente == nullptr) {
            return false;
        }

        // Guardamos el subnodo que vamos a eliminar
        NodoSublista* aEliminar = sub->siguiente;

        // Saltamos el subnodo a eliminar conectando el actual con el que sigue después
        sub->siguiente = aEliminar->siguiente;

        // Liberamos la memoria del subnodo eliminado
        delete aEliminar;

        // Retornamos true indicando éxito
        return true;
    }
};
