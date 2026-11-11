#ifndef COLA_CIRCULAR_ARREGLO_H
#define COLA_CIRCULAR_ARREGLO_H

#include <string>
using namespace std;

class ColaCircularArreglo {
private:
    string clientes[5];
    int frente;
    int final;
    int cantidad;

public:
    ColaCircularArreglo();

    void encolar(string nombre);
    void desencolar();
    void mostrar();
    bool estaVacia();
    bool estaLlena();
};

#endif

