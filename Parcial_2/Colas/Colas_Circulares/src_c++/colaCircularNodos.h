#ifndef COLA_CIRCULAR_NODOS_H
#define COLA_CIRCULAR_NODOS_H

#include <string>
using namespace std;

struct Nodo {
    string nombre;
    Nodo* siguiente;
};

class ColaCircularNodos {
private:
    Nodo* frente;
    Nodo* final;

public:
    ColaCircularNodos();
    ~ColaCircularNodos();

    void encolar(string nombre);
    void desencolar();
    void mostrar();
    bool estaVacia();
};

#endif
