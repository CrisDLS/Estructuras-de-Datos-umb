/**
 * ColaPrioridadHeap.h
 * ---------------------------------------------------------------
 * Cola de prioridad implementada con un MONTÍCULO BINARIO (heap)
 * almacenado en un arreglo.
 *
 * Criterio de prioridad: MENOR número = MAYOR prioridad (min-heap).
 * Desempate: si dos elementos tienen la misma prioridad, sale primero
 * el que llegó antes (se usa un número de orden de llegada).
 *
 * Idea del heap en arreglo (índices desde 0):
 *   - hijo izquierdo de i  ->  2*i + 1
 *   - hijo derecho de i    ->  2*i + 2
 *   - padre de i           ->  (i - 1) / 2
 *   - propiedad: todo padre es "mejor" (sale antes) que sus hijos,
 *     por lo tanto la RAÍZ (índice 0) siempre es el siguiente en salir.
 *
 * Costos:
 *   - encolar():    inserta al final y "sube" (subir)    -> O(log n)
 *   - desencolar(): quita la raíz y "baja" (bajar)       -> O(log n)
 *   - verFrente():  lee la raíz                          -> O(1)
 *
 * Referencias: ver documento de investigación (respuesta).
 */

#ifndef COLA_PRIORIDAD_HEAP_H   // Evita incluir el archivo dos veces
#define COLA_PRIORIDAD_HEAP_H   // Define la marca de "ya incluido"

#include <iostream>   // Para cout (imprimir en consola)
#include <string>     // Para usar el tipo string
#include <vector>     // Solo se usa en mostrar() para ordenar una copia
#include <algorithm>  // Para sort en mostrar()

using namespace std;  // Evita escribir std:: antes de cada elemento

/**
 * ElementoHeap: registro que se guarda en cada casilla del heap.
 */
struct ElementoHeap {
    string dato;       // Información que guarda el elemento
    int prioridad;     // Prioridad (menor número = más urgente)
    long orden;        // Número de llegada, usado para desempatar
};

/**
 * Clase ColaPrioridadHeap: administra el montículo binario.
 */
class ColaPrioridadHeap {
private:
    ElementoHeap* datos;   // Arreglo dinámico que contiene el heap
    int capacidad;         // Espacio reservado en el arreglo
    int cantidad;          // Cuántos elementos hay actualmente
    long contadorLlegada;  // Lleva la cuenta de llegadas para el desempate

    /**
     * esMejor: indica si el elemento 'a' debe salir ANTES que 'b'.
     * Primero compara prioridad; si empatan, gana el que llegó antes.
     */
    bool esMejor(const ElementoHeap& a, const ElementoHeap& b) const {
        if (a.prioridad != b.prioridad) {      // Si las prioridades son distintas...
            return a.prioridad < b.prioridad;  // ...gana el número menor
        }
        return a.orden < b.orden;              // Si empatan, gana el que llegó primero
    }

    /**
     * intercambiar: intercambia los elementos de las posiciones i y j.
     */
    void intercambiar(int i, int j) {
        ElementoHeap temporal = datos[i];   // Guarda el elemento i en una variable temporal
        datos[i] = datos[j];                // Copia j en i
        datos[j] = temporal;                // Copia el temporal (antiguo i) en j
    }

    /**
     * subir: reacomoda un elemento hacia arriba hasta que su padre
     * sea mejor que él. Se usa después de insertar.
     * @param i posición del elemento que se va a subir
     */
    void subir(int i) {
        while (i > 0) {                      // Mientras no lleguemos a la raíz
            int padre = (i - 1) / 2;         // Calcula la posición del padre
            if (esMejor(datos[i], datos[padre])) {   // Si el hijo debe salir antes que el padre...
                intercambiar(i, padre);      // ...los intercambiamos
                i = padre;                   // ...y seguimos subiendo desde la posición del padre
            } else {
                break;                       // Si el padre ya es mejor, el heap está correcto
            }
        }
    }

    /**
     * bajar: reacomoda un elemento hacia abajo hasta que sus hijos
     * sean peores que él. Se usa después de eliminar la raíz.
     * @param i posición del elemento que se va a bajar
     */
    void bajar(int i) {
        while (true) {                       // Se repite hasta que el elemento quede en su lugar
            int izq = 2 * i + 1;             // Posición del hijo izquierdo
            int der = 2 * i + 2;             // Posición del hijo derecho
            int mejor = i;                   // Supone que el mejor es el propio elemento

            // Si existe hijo izquierdo y es mejor que el "mejor" actual...
            if (izq < cantidad && esMejor(datos[izq], datos[mejor])) {
                mejor = izq;                 // ...el mejor pasa a ser el hijo izquierdo
            }
            // Si existe hijo derecho y es mejor que el "mejor" actual...
            if (der < cantidad && esMejor(datos[der], datos[mejor])) {
                mejor = der;                 // ...el mejor pasa a ser el hijo derecho
            }

            if (mejor == i) {                // Si ningún hijo es mejor...
                break;                       // ...el elemento ya está en su lugar
            }
            intercambiar(i, mejor);          // Si no, lo intercambiamos con el mejor hijo
            i = mejor;                       // ...y seguimos bajando desde esa posición
        }
    }

    /**
     * agrandar: duplica la capacidad del arreglo cuando se llena.
     */
    void agrandar() {
        int nuevaCapacidad = capacidad * 2;                 // Nueva capacidad: el doble
        ElementoHeap* nuevo = new ElementoHeap[nuevaCapacidad];  // Reserva el arreglo más grande
        for (int i = 0; i < cantidad; i++) {                // Copia todos los elementos existentes
            nuevo[i] = datos[i];
        }
        delete[] datos;                // Libera el arreglo antiguo
        datos = nuevo;                 // Ahora usamos el arreglo nuevo
        capacidad = nuevaCapacidad;    // Actualiza la capacidad
    }

public:
    /**
     * Constructor: crea un heap vacío.
     * @param cap capacidad inicial (por defecto 10; mínimo 1)
     */
    ColaPrioridadHeap(int cap = 10) {
        if (cap < 1) {          // Si la capacidad no es válida...
            cap = 1;            // ...se usa 1 como mínimo
        }
        capacidad = cap;                       // Guarda la capacidad inicial
        datos = new ElementoHeap[capacidad];   // Reserva memoria para el arreglo
        cantidad = 0;                          // Inicia sin elementos
        contadorLlegada = 0;                   // Aún no ha llegado nadie
    }

    /** Destructor: libera la memoria del arreglo. */
    ~ColaPrioridadHeap() {
        delete[] datos;   // delete[] se usa para liberar arreglos
    }

    // Se prohíbe copiar la cola (evita que dos colas compartan el mismo arreglo)
    ColaPrioridadHeap(const ColaPrioridadHeap&) = delete;
    ColaPrioridadHeap& operator=(const ColaPrioridadHeap&) = delete;

    /** estaVacia: indica si no hay elementos. */
    bool estaVacia() const {
        return cantidad == 0;   // Vacía si el contador está en 0
    }

    /**
     * estaLlena: como el arreglo crece automáticamente, nunca se llena
     * (se incluye para tener la misma interfaz que las otras versiones).
     */
    bool estaLlena() const {
        return false;
    }

    /** tamanio: devuelve la cantidad de elementos almacenados. */
    int tamanio() const {
        return cantidad;
    }

    /**
     * encolar: inserta un elemento al final del arreglo y lo sube
     * hasta su posición correcta.
     * @return true siempre (el arreglo crece si hace falta).
     */
    bool encolar(const string& dato, int prioridad) {
        if (cantidad == capacidad) {   // Si el arreglo está lleno...
            agrandar();                // ...se duplica su tamaño
        }

        datos[cantidad].dato = dato;                  // Coloca el dato en la primera casilla libre
        datos[cantidad].prioridad = prioridad;        // Guarda su prioridad
        datos[cantidad].orden = contadorLlegada++;    // Asigna el orden de llegada y lo incrementa

        subir(cantidad);   // Reacomoda el nuevo elemento hacia arriba
        cantidad++;        // Ahora hay un elemento más
        return true;       // Inserción exitosa
    }

    /**
     * desencolar: elimina la raíz (el elemento de mayor prioridad).
     * @param dato      (salida) dato del elemento eliminado
     * @param prioridad (salida) prioridad del elemento eliminado
     * @return true si se eliminó, false si la cola estaba vacía.
     */
    bool desencolar(string& dato, int& prioridad) {
        if (estaVacia()) {   // Si no hay elementos...
            return false;    // ...no se puede eliminar nada
        }

        dato = datos[0].dato;             // Devolvemos el dato de la raíz
        prioridad = datos[0].prioridad;   // Devolvemos su prioridad

        cantidad--;                       // Hay un elemento menos
        datos[0] = datos[cantidad];       // El último elemento pasa a ocupar la raíz
        if (cantidad > 0) {               // Si todavía quedan elementos...
            bajar(0);                     // ...se baja la nueva raíz hasta su lugar
        }
        return true;                      // Eliminación exitosa
    }

    /**
     * verFrente: consulta la raíz SIN eliminarla.
     * @return true si hay elementos, false si la cola está vacía.
     */
    bool verFrente(string& dato, int& prioridad) const {
        if (estaVacia()) {   // Si no hay elementos...
            return false;    // ...no hay frente que mostrar
        }
        dato = datos[0].dato;             // Copia el dato de la raíz
        prioridad = datos[0].prioridad;   // Copia la prioridad de la raíz
        return true;                      // Consulta exitosa
    }

    /**
     * mostrar: imprime los elementos en ORDEN DE ATENCIÓN.
     * Un heap no está completamente ordenado en el arreglo, por eso
     * se ordena una COPIA solo para imprimir (el heap no se modifica).
     */
    void mostrar() const {
        if (estaVacia()) {                    // Si no hay elementos...
            cout << "(cola vacia)" << endl;   // ...avisamos al usuario
            return;                           // ...y terminamos la función
        }

        vector<ElementoHeap> copia(datos, datos + cantidad);   // Copia los elementos a un vector

        // Ordena la copia: primero por prioridad y, si empatan, por orden de llegada
        sort(copia.begin(), copia.end(),
             [this](const ElementoHeap& a, const ElementoHeap& b) {
                 return esMejor(a, b);
             });

        for (size_t i = 0; i < copia.size(); i++) {   // Recorre la copia ya ordenada
            cout << (i + 1) << ". [Prioridad " << copia[i].prioridad << "] "
                 << copia[i].dato << endl;            // Imprime turno, prioridad y dato
        }
    }

    /** vaciar: deja la cola sin elementos (el arreglo se reutiliza). */
    void vaciar() {
        cantidad = 0;   // Basta con reiniciar el contador
    }
};

#endif  // Fin de COLA_PRIORIDAD_HEAP_H
