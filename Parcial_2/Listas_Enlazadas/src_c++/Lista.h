#pragma once
#include <iostream> // Para mostrar mensajes en pantalla (cout)
#include <string>   // Para manejar texto (string)
#include <cstddef>  // Para el tipo size_t (números enteros positivos para contar)

// =============================================================================
// CLASE BASE: Lista
// Define las operaciones que obligatoriamente deben tener todas las listas.
// Funciona como un modelo general para ListaSimple, ListaDoble, ListaCircular y Multilista.
// =============================================================================
class Lista {
public:
    // Destructor: limpia la memoria cuando la lista se deja de usar
    virtual ~Lista() = default;

    // Agrega un número al inicio de la lista
    virtual void insertarInicio(int dato) = 0;

    // Agrega un número al final de la lista
    virtual void insertarFinal(int dato) = 0;

    // Agrega un número en una posición específica (empezando desde 0)
    virtual void insertarEnPosicion(int dato, int pos) = 0;

    // Busca un número y lo elimina de la lista (retorna true si lo encontró y eliminó, o false si no)
    virtual bool eliminar(int dato) = 0;

    // Revisa si un número existe dentro de la lista (retorna true si existe o false si no)
    virtual bool buscar(int dato) const = 0;

    // Muestra en pantalla todos los elementos que tiene la lista
    virtual void mostrar() const = 0;

    // Indica si la lista no tiene ningún elemento guardado
    virtual bool estaVacia() const = 0;

    // Retorna la cantidad total de elementos guardados en la lista
    virtual size_t tamano() const = 0;

    // Elimina todos los elementos de la lista y libera la memoria
    virtual void vaciar() = 0;

    // Retorna el nombre del tipo de lista (por ejemplo: "Lista Simple")
    virtual std::string obtenerTipo() const = 0;
};
