#include <iostream>
#include <stdexcept>
using namespace std;

// Estructura para cada nodo
struct Nodo {
    int dato;
    Nodo* siguiente;
    Nodo* anterior;

    Nodo(int numero) : dato(numero), siguiente(nullptr), anterior(nullptr) {}
};

// Clase para la cola doble
class Deque {
private:
    Nodo* frente;
    Nodo* cola;
    int tamano;

public:
    // Constructor
    Deque() : frente(nullptr), cola(nullptr), tamano(0) {}

    // Destructor
    ~Deque() {
        while (!estaVacia()) {
            pop_front();
        }
    }

    // Verifica si la cola esta vacia
    bool estaVacia() {
        return frente == nullptr;
    }

    // Insertar al frente
    void push_front(int val) {
        Nodo* nuevo = new Nodo(val);

        if (estaVacia()) {
            frente = cola = nuevo;
        } else {
            nuevo->siguiente = frente;
            frente->anterior = nuevo;
            frente = nuevo;
        }

        tamano++;
        cout << "[INSERTAR FRENTE] Se agrego: " << val << "\n";
        mostrar();
    }

    // Insertar al final
    void push_back(int val) {
        Nodo* nuevo = new Nodo(val);

        if (estaVacia()) {
            frente = cola = nuevo;
        } else {
            cola->siguiente = nuevo;
            nuevo->anterior = cola;
            cola = nuevo;
        }

        tamano++;
        cout << "[INSERTAR FINAL] Se agrego: " << val << "\n";
        mostrar();
    }

    // Eliminar del frente
    void pop_front() {
        if (estaVacia()) {
            cout << "La cola doble esta vacia, no se puede eliminar del frente.\n\n";
            return;
        }

        Nodo* temp = frente;
        int valor = temp->dato;

        if (frente == cola) {
            frente = cola = nullptr;
        } else {
            frente = frente->siguiente;
            frente->anterior = nullptr;
        }

        delete temp;
        tamano--;

        cout << "[ELIMINAR FRENTE] Se elimino: " << valor << "\n";
        mostrar();
    }

    // Eliminar del final
    void pop_back() {
        if (estaVacia()) {
            cout << "La cola doble esta vacia, no se puede eliminar del final.\n\n";
            return;
        }

        Nodo* temp = cola;
        int valor = temp->dato;

        if (frente == cola) {
            frente = cola = nullptr;
        } else {
            cola = cola->anterior;
            cola->siguiente = nullptr;
        }

        delete temp;
        tamano--;

        cout << "[ELIMINAR FINAL] Se elimino: " << valor << "\n";
        mostrar();
    }

    // Consultar el frente
    int front() {
        if (estaVacia()) {
            throw runtime_error("La cola esta vacia");
        }
        return frente->dato;
    }

    // Consultar el final
    int back() {
        if (estaVacia()) {
            throw runtime_error("La cola esta vacia");
        }
        return cola->dato;
    }

    // Mostrar todos los elementos
    void mostrar() {
        if (estaVacia()) {
            cout << "Cola: [vacia]\n\n";
            return;
        }

        Nodo* actual = frente;

        cout << "Estado actual: FRENTE -> ";

        while (actual != nullptr) {
            cout << actual->dato;

            if (actual->siguiente != nullptr) {
                cout << " <-> ";
            }

            actual = actual->siguiente;
        }

        cout << " <- FINAL\n";
        cout << "Elementos totales: " << tamano << "\n\n";
    }
};

// Funcion principal con multiples operaciones mixtas
int main() {
    Deque dq;

    cout << "=== INICIO DE OPERACIONES EN LA COLA DOBLE ===\n\n";

    // 1. Inserciones iniciales
    dq.push_back(10);
    dq.push_back(20);
    dq.push_front(5);

    // 2. Eliminaciones en el frente y final
    dq.pop_front(); // Elimina el 5
    dq.pop_back();  // Elimina el 20

    // 3. Nuevas inserciones por ambos lados
    dq.push_front(99);
    dq.push_back(50);

    // 4. Otra eliminación por el frente
    dq.pop_front(); // Elimina el 99

    // Mostramos los valores extremos finales
    cout << "Frente actual final: " << dq.front() << "\n";
    cout << "Final actual final: " << dq.back() << "\n";

    return 0;
}