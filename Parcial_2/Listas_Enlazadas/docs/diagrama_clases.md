# Diagrama de Clases — Listas Enlazadas

Diagrama de clases UML del módulo `src` para las listas enlazadas y sus respectivos nodos, incorporando la modelación completa y estandarizada de las cuatro variantes: `ListaSimple`, `ListaDoble`, `ListaCircular` y `ListaMulti`.

```mermaid
classDiagram
    direction TB

    namespace nodos {
        class Nodo {
            -int dato
            -Nodo siguiente
            +Nodo(int dato)
            +getDato() int
            +setDato(int) void
            +getSiguiente() Nodo
            +setSiguiente(Nodo) void
        }
        class NodoDoble {
            -NodoDoble anterior
            +NodoDoble(int dato)
            +getAnterior() NodoDoble
            +setAnterior(NodoDoble) void
            +getSiguiente() NodoDoble
        }
        class NodoMulti {
            -String nombre
            -NodoMulti siguienteEdad
            +NodoMulti(String nombre, int edad)
            +NodoMulti(int edad)
            +NodoMulti(String nombre, int edad, Nodo siguiente, NodoMulti siguienteEdad)
            +getNombre() String
            +setNombre(String) void
            +getEdad() int
            +setEdad(int) void
            +getSiguienteEdad() NodoMulti
            +setSiguienteEdad(NodoMulti) void
            +getSiguiente() NodoMulti
            +toString() String
        }
    }

    namespace listas {
        class ListaEnlazada {
            <<abstract>>
            #Nodo cabeza
            #int tamano
            +insertar(int dato)* void
            +eliminar(int dato)* boolean
            +estaVacia() boolean
            +getTamano() int
            +limpiar() void
            +buscar(int dato) boolean
            +mostrar() void
        }
        class ListaSimple {
            +insertar(int dato) void
            +insertarAlInicio(int dato) void
            +eliminar(int dato) boolean
            +eliminarAlInicio() boolean
            +eliminarAlFinal() boolean
        }
        class ListaDoble {
            -NodoDoble cola
            +ListaDoble()
            +insertar(int dato) void
            +insertarAlInicio(int dato) void
            +eliminar(int dato) boolean
            +eliminarAlInicio() boolean
            +eliminarAlFinal() boolean
            +mostrarInverso() void
            +limpiar() void
            +getCola() NodoDoble
        }
        class ListaCircular {
            +insertar(int dato) void
            +insertarAlInicio(int dato) void
            +eliminar(int dato) boolean
            +eliminarAlInicio() boolean
            +eliminarAlFinal() boolean
            +mostrar() void
            +buscar(int dato) boolean
        }
        class ListaMulti {
            #NodoMulti cabezaEdad
            +ListaMulti()
            +insertar(String nombre, int edad) void
            +insertar(int dato) void
            +eliminar(String nombre) boolean
            +eliminar(int dato) boolean
            +buscar(String nombre) boolean
            +buscarPorEdad(int edad) boolean
            +buscarNodo(String nombre) NodoMulti
            +mostrar() void
            +mostrarAlfabetico() void
            +mostrarPorEdad() void
            +limpiar() void
            +getCabezaAlfabetica() NodoMulti
            +getCabezaEdad() NodoMulti
            +verificarConsistencia() boolean
        }
    }

    Nodo <|-- NodoDoble : hereda
    Nodo <|-- NodoMulti : hereda

    ListaEnlazada <|-- ListaSimple : hereda
    ListaEnlazada <|-- ListaDoble : hereda
    ListaEnlazada <|-- ListaCircular : hereda
    ListaEnlazada <|-- ListaMulti : hereda

    ListaSimple --> Nodo
    ListaDoble --> NodoDoble
    ListaCircular --> Nodo
    ListaMulti --> NodoMulti
```
