/**
 * ColaPrioridadNodos.h
 * ---------------------------------------------------------------
 * Cola de prioridad implementada con NODOS (lista enlazada simple).
 *
 * Criterio de prioridad: MENOR número = MAYOR prioridad
 * (prioridad 1 se atiende antes que prioridad 5).
 * Si dos elementos tienen la misma prioridad, se atiende primero
 * el que llegó antes (orden FIFO).
 *
 * Estrategia: la lista se mantiene SIEMPRE ordenada.
 *   - encolar():   recorre la lista y busca la posición  -> O(n)
 *   - desencolar(): quita el primer nodo (el frente)     -> O(1)
 *
 * Referencias: ver documento de investigación (README / respuesta).
 */

#ifndef COLA_PRIORIDAD_NODOS_H   // Evita que el archivo se incluya dos veces
#define COLA_PRIORIDAD_NODOS_H   // Define la marca de "ya incluido"

#include <iostream>   // Para cout (imprimir en consola)
#include <string>     // Para usar el tipo string

using namespace std;  // Evita escribir std:: antes de cada elemento

/**
 * Nodo: unidad básica de la lista enlazada.
 * Guarda un dato, su prioridad y un puntero al siguiente nodo.
 */
struct Nodo {
    string dato;       // Información que guarda el nodo (ej. nombre de una tarea)
    int prioridad;     // Prioridad del dato (menor número = más urgente)
    Nodo* siguiente;   // Puntero al siguiente nodo (nullptr si es el último)

    // Constructor: crea un nodo con dato y prioridad, sin siguiente todavía
    Nodo(const string& d, int p) {
        dato = d;               // Guarda el dato recibido
        prioridad = p;          // Guarda la prioridad recibida
        siguiente = nullptr;    // Al inicio no apunta a ningún nodo
    }
};

/**
 * Clase ColaPrioridadNodos: administra la lista enlazada de nodos.
 */
class ColaPrioridadNodos {
private:
    Nodo* frente;   // Puntero al primer nodo (el de mayor prioridad)
    int cantidad;   // Cuántos elementos hay en la cola

public:
    /** Constructor: crea una cola vacía. */
    ColaPrioridadNodos() {
        frente = nullptr;   // Sin nodos, el frente no apunta a nada
        cantidad = 0;       // Cola con 0 elementos
    }

    /** Destructor: libera la memoria de todos los nodos al destruir la cola. */
    ~ColaPrioridadNodos() {
        vaciar();   // Borra todos los nodos para evitar fugas de memoria
    }

    // Se prohíbe copiar la cola (evita que dos colas compartan los mismos nodos)
    ColaPrioridadNodos(const ColaPrioridadNodos&) = delete;
    ColaPrioridadNodos& operator=(const ColaPrioridadNodos&) = delete;

    /**
     * estaVacia: indica si la cola no tiene elementos.
     * @return true si está vacía, false en caso contrario.
     */
    bool estaVacia() const {
        return frente == nullptr;   // Si no hay frente, no hay nodos
    }

    /**
     * estaLlena: con nodos la memoria es dinámica, por lo que
     * no existe un límite fijo (se incluye para tener la misma
     * interfaz que la versión con arreglo).
     * @return siempre false.
     */
    bool estaLlena() const {
        return false;   // Nunca se llena (limitada solo por la memoria de la PC)
    }

    /**
     * tamanio: devuelve la cantidad de elementos almacenados.
     */
    int tamanio() const {
        return cantidad;   // Contador mantenido en encolar y desencolar
    }

    /**
     * encolar: inserta un elemento en la posición que le corresponde
     * según su prioridad (lista ordenada de menor a mayor número).
     * @param dato      información a guardar
     * @param prioridad prioridad del elemento
     * @return true si se insertó (siempre, salvo falta de memoria).
     */
    bool encolar(const string& dato, int prioridad) {
        Nodo* nuevo = new Nodo(dato, prioridad);   // Reserva memoria para el nodo nuevo

        // CASO 1: la cola está vacía o el nuevo tiene MÁS prioridad que el frente
        if (frente == nullptr || prioridad < frente->prioridad) {
            nuevo->siguiente = frente;   // El nuevo apunta al antiguo frente
            frente = nuevo;              // El nuevo pasa a ser el frente
        } else {
            // CASO 2: buscar el nodo después del cual debe ir el nuevo
            Nodo* actual = frente;   // Empezamos a recorrer desde el frente

            // Avanzamos mientras exista siguiente y su prioridad sea <= a la nueva
            // (el "<=" mantiene el orden de llegada entre prioridades iguales)
            while (actual->siguiente != nullptr &&
                   actual->siguiente->prioridad <= prioridad) {
                actual = actual->siguiente;   // Pasamos al siguiente nodo
            }

            nuevo->siguiente = actual->siguiente;   // El nuevo apunta a lo que seguía
            actual->siguiente = nuevo;              // El nodo actual apunta al nuevo
        }

        cantidad++;     // Ahora hay un elemento más
        return true;    // Inserción exitosa
    }

    /**
     * desencolar: elimina el elemento de mayor prioridad (el frente).
     * @param dato      (salida) dato del elemento eliminado
     * @param prioridad (salida) prioridad del elemento eliminado
     * @return true si se eliminó, false si la cola estaba vacía.
     */
    bool desencolar(string& dato, int& prioridad) {
        if (estaVacia()) {      // Si no hay elementos...
            return false;       // ...no se puede eliminar nada
        }

        Nodo* aEliminar = frente;           // Guardamos el nodo que vamos a borrar
        dato = aEliminar->dato;             // Devolvemos su dato
        prioridad = aEliminar->prioridad;   // Devolvemos su prioridad

        frente = frente->siguiente;   // El frente ahora es el segundo nodo
        delete aEliminar;             // Liberamos la memoria del nodo eliminado
        cantidad--;                   // Ahora hay un elemento menos
        return true;                  // Eliminación exitosa
    }

    /**
     * verFrente: consulta el elemento de mayor prioridad SIN eliminarlo.
     * @param dato      (salida) dato del frente
     * @param prioridad (salida) prioridad del frente
     * @return true si hay frente, false si la cola está vacía.
     */
    bool verFrente(string& dato, int& prioridad) const {
        if (estaVacia()) {      // Si no hay elementos...
            return false;       // ...no hay frente que mostrar
        }
        dato = frente->dato;             // Copiamos el dato del frente
        prioridad = frente->prioridad;   // Copiamos la prioridad del frente
        return true;                     // Consulta exitosa
    }

    /**
     * mostrar: imprime todos los elementos en orden de atención.
     */
    void mostrar() const {
        if (estaVacia()) {                    // Si no hay elementos...
            cout << "(cola vacia)" << endl;   // ...avisamos al usuario
            return;                           // ...y terminamos la función
        }

        Nodo* actual = frente;   // Recorremos desde el frente
        int posicion = 1;        // Número de turno que se muestra al usuario

        while (actual != nullptr) {   // Mientras queden nodos por visitar
            cout << posicion << ". [Prioridad " << actual->prioridad << "] "
                 << actual->dato << endl;    // Imprime turno, prioridad y dato
            actual = actual->siguiente;      // Avanza al siguiente nodo
            posicion++;                      // Incrementa el número de turno
        }
    }

    /**
     * vaciar: elimina todos los nodos y deja la cola vacía.
     */
    void vaciar() {
        while (frente != nullptr) {          // Mientras haya nodos
            Nodo* aEliminar = frente;        // Guardamos el nodo actual
            frente = frente->siguiente;      // Movemos el frente al siguiente
            delete aEliminar;                // Liberamos el nodo guardado
        }
        cantidad = 0;   // La cola queda en 0 elementos
    }
};

#endif  // Fin de COLA_PRIORIDAD_NODOS_H
