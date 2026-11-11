
#include <iostream>
using namespace std;

class Deque {
private:
    int *arr;
    int capacity;
    int front;
    int rear;
    int size;

public:
    // Constructor
    Deque(int c) {
        capacity = c;
        arr = new int[capacity];
        front = -1;
        rear = -1;
        size = 0;
    }

    // Destructor
    ~Deque() {
        delete[] arr;
    }

    // Verifica si esta llena
    bool isFull() {
        return size == capacity;
    }

    // Verifica si esta vacia
    bool isEmpty() {
        return size == 0;
    }

    // Inserta al frente
    void insertFront(int key) {
        if (isFull()) {
            cout << "Overflow: La cola esta llena.\n";
            return;
        }

        if (isEmpty()) {
            front = rear = 0;
        }
        else if (front == 0) {
            front = capacity - 1;
        }
        else {
            front--;
        }

        arr[front] = key;
        size++;

        cout << "Insertado " << key << " al frente.\n";
        mostrar();
    }

    // Inserta al final
    void insertRear(int key) {
        if (isFull()) {
            cout << "Overflow: La cola esta llena.\n";
            return;
        }

        if (isEmpty()) {
            front = rear = 0;
        }
        else if (rear == capacity - 1) {
            rear = 0;
        }
        else {
            rear++;
        }

        arr[rear] = key;
        size++;

        cout << "Insertado " << key << " al final.\n";
        mostrar();
    }

    // Elimina del frente
    void deleteFront() {
        if (isEmpty()) {
            cout << "Underflow: La cola esta vacia.\n";
            return;
        }

        cout << "Eliminado del frente: " << arr[front] << "\n";

        if (front == rear) {
            front = rear = -1;
        }
        else if (front == capacity - 1) {
            front = 0;
        }
        else {
            front++;
        }

        size--;
        mostrar();
    }

    // Elimina del final
    void deleteRear() {
        if (isEmpty()) {
            cout << "Underflow: La cola esta vacia.\n";
            return;
        }

        cout << "Eliminado del final: " << arr[rear] << "\n";

        if (front == rear) {
            front = rear = -1;
        }
        else if (rear == 0) {
            rear = capacity - 1;
        }
        else {
            rear--;
        }

        size--;
        mostrar();
    }

    // Consulta el frente
    int getFront() {
        if (isEmpty()) {
            cout << "La cola esta vacia.\n";
            return -1;
        }

        return arr[front];
    }

    // Consulta el final
    int getRear() {
        if (isEmpty()) {
            cout << "La cola esta vacia.\n";
            return -1;
        }

        return arr[rear];
    }

    // Muestra toda la cola
    void mostrar() {
        if (isEmpty()) {
            cout << "Cola: [vacia]\n\n";
            return;
        }

        cout << "Cola: FRENTE -> ";

        int i = front;

        for (int contador = 0; contador < size; contador++) {
            cout << arr[i];

            if (contador < size - 1) {
                cout << " -> ";
            }

            i = (i + 1) % capacity;
        }

        cout << " -> FINAL\n\n";
    }
};

int main() {
    Deque dq(5);

    dq.insertRear(10);
    dq.insertRear(20);
    dq.insertFront(5);

    cout << "Elemento al frente: " << dq.getFront() << "\n";
    cout << "Elemento al final: " << dq.getRear() << "\n\n";

    dq.deleteFront();

    dq.insertFront(15);

    dq.deleteRear();

    return 0;
}
