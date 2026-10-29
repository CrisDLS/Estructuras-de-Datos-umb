/**
 * ColaPrioridadArreglo.h
 * ---------------------------------------------------------------
 * Cola de prioridad implementada con un ARREGLO de capacidad fija.
 *
 * Criterio de prioridad: MENOR número = MAYOR prioridad
 * (prioridad 1 se atiende antes que prioridad 5).
 * Si dos elementos tienen la misma prioridad, se atiende primero
 * el que llegó antes (orden FIFO).
 *
 * Estrategia: el arreglo se mantiene SIEMPRE ordenado de menor a
 * mayor número de prioridad; el índice 0 es el frente.
 *   - encolar():    busca la posición y desplaza elementos  -> O(n)
 *   - desencolar(): quita el índice 0 y recorre a la izquierda -> O(n)
 *
 * Referencias: ver documento de investigación (respuesta).
 */

#ifndef COLA_PRIORIDAD_ARREGLO_H   // Evita incluir el archivo dos veces
#define COLA_PRIORIDAD_ARREGLO_H   // Define la marca de "ya incluido"

#include <iostream>   // Para cout (imprimir en consola)
#include <string>     // Para usar el tipo string

using namespace std;  // Evita escribir std:: antes de cada elemento

/**
 * Elemento: registro que se guarda en cada casilla del arreglo.
 */
struct Elemento {
    string dato;     // Información que guarda el elemento
    int prioridad;   // Prioridad del dato (menor número = más urgente)
};

/**
 * Clase ColaPrioridadArreglo: administra el arreglo de elementos.
 */
class ColaPrioridadArreglo {
private:
    Elemento* datos;   // Arreglo dinámico donde se guardan los elementos
    int capacidad;     // Máximo de elementos que caben en el arreglo
    int cantidad;      // Cuántos elementos hay actualmente

public:
    /**
     * Constructor: reserva el arreglo con la capacidad indicada.
     * @param cap capacidad máxima (por defecto 10; mínimo 1)
     */
    ColaPrioridadArreglo(int cap = 10) {
        if (cap < 1) {          // Si la capacidad no es válida...
            cap = 1;            // ...se usa 1 como mínimo
        }
        capacidad = cap;                 // Guarda la capacidad
        datos = new Elemento[capacidad]; // Reserva memoria para el arreglo
        cantidad = 0;                    // Inicia sin elementos
    }

    /** Destructor: libera la memoria del arreglo. */
    ~ColaPrioridadArreglo() {
        delete[] datos;   // delete[] se usa para liberar arreglos
    }

    // Se prohíbe copiar la cola (evita que dos colas compartan el mismo arreglo)
    ColaPrioridadArreglo(const ColaPrioridadArreglo&) = delete;
    ColaPrioridadArreglo& operator=(const ColaPrioridadArreglo&) = delete;

    /**
     * estaVacia: indica si no hay elementos.
     */
    bool estaVacia() const {
        return cantidad == 0;   // Vacía si el contador está en 0
    }

    /**
     * estaLlena: indica si el arreglo ya no admite más elementos.
     */
    bool estaLlena() const {
        return cantidad == capacidad;   // Llena si se usó toda la capacidad
    }

    /**
     * tamanio: devuelve la cantidad de elementos almacenados.
     */
    int tamanio() const {
        return cantidad;   // Contador de elementos
    }

    /**
     * encolar: inserta un elemento manteniendo el arreglo ordenado.
     * @param dato      información a guardar
     * @param prioridad prioridad del elemento
     * @return true si se insertó, false si el arreglo está lleno.
     */
    bool encolar(const string& dato, int prioridad) {
        if (estaLlena()) {   // Si no hay espacio...
            return false;    // ...no se puede insertar
        }

        int i = cantidad - 1;   // Empezamos a revisar desde el último elemento

        // Mientras haya elementos y el de la posición i tenga MENOR prioridad
        // (número mayor) que el nuevo, lo desplazamos una casilla a la derecha.
        // Se usa ">" (y no ">=") para respetar el orden de llegada en empates.
        while (i >= 0 && datos[i].prioridad > prioridad) {
            datos[i + 1] = datos[i];   // Copia el elemento una posición a la derecha
            i--;                       // Pasa al elemento anterior
        }

        datos[i + 1].dato = dato;            // Coloca el dato en el hueco encontrado
        datos[i + 1].prioridad = prioridad;  // Coloca su prioridad
        cantidad++;                          // Ahora hay un elemento más
        return true;                         // Inserción exitosa
    }

    /**
     * desencolar: elimina el elemento de mayor prioridad (índice 0).
     * @param dato      (salida) dato del elemento eliminado
     * @param prioridad (salida) prioridad del elemento eliminado
     * @return true si se eliminó, false si la cola estaba vacía.
     */
    bool desencolar(string& dato, int& prioridad) {
        if (estaVacia()) {   // Si no hay elementos...
            return false;    // ...no se puede eliminar nada
        }

        dato = datos[0].dato;             // Devolvemos el dato del frente
        prioridad = datos[0].prioridad;   // Devolvemos la prioridad del frente

        // Recorremos todos los elementos una posición a la izquierda
        // para tapar el hueco que dejó el índice 0.
        for (int i = 1; i < cantidad; i++) {
            datos[i - 1] = datos[i];   // Mueve el elemento i a la posición i-1
        }

        cantidad--;    // Ahora hay un elemento menos
        return true;   // Eliminación exitosa
    }

    /**
     * verFrente: consulta el elemento de mayor prioridad SIN eliminarlo.
     * @param dato      (salida) dato del frente
     * @param prioridad (salida) prioridad del frente
     * @return true si hay frente, false si la cola está vacía.
     */
    bool verFrente(string& dato, int& prioridad) const {
        if (estaVacia()) {   // Si no hay elementos...
            return false;    // ...no hay frente que mostrar
        }
        dato = datos[0].dato;             // Copia el dato del índice 0
        prioridad = datos[0].prioridad;   // Copia la prioridad del índice 0
        return true;                      // Consulta exitosa
    }

    /**
     * mostrar: imprime todos los elementos en orden de atención.
     */
    void mostrar() const {
        if (estaVacia()) {                    // Si no hay elementos...
            cout << "(cola vacia)" << endl;   // ...avisamos al usuario
            return;                           // ...y terminamos la función
        }

        for (int i = 0; i < cantidad; i++) {   // Recorre desde el índice 0
            cout << (i + 1) << ". [Prioridad " << datos[i].prioridad << "] "
                 << datos[i].dato << endl;     // Imprime turno, prioridad y dato
        }
    }

    /**
     * vaciar: deja la cola sin elementos (el arreglo se reutiliza).
     */
    void vaciar() {
        cantidad = 0;   // Basta con reiniciar el contador
    }
};

#endif  // Fin de COLA_PRIORIDAD_ARREGLO_H
