import java.util.InputMismatchException;
import java.util.Scanner;

class NodoCircular {
    int dato;
    NodoCircular siguiente;

    NodoCircular(int dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}

//INTERFAZ
interface ColaCircularNodos {
    void Enqueue(int dato);      // agrega al final de la cola
    int Dequeue();            // saca y devuelve el dato del frente
    int verFrente();             // devuelve el dato del frente sin sacarlo
    int verFinal();              // devuelve el dato del final sin sacarlo
    boolean estaVacia();
    int tamano();
    void mostrar();
}

//IMPLEMENTACIÓN
public class Colacircularnodo implements ColaCircularNodos {

    // Solo guardamos el último nodo (fin).
    // El frente siempre es fin.siguiente, porque la lista es circular.
    private NodoCircular fin;
    private int tamano;

    public Colacircularnodo() {
        fin = null;
        tamano = 0;
    }

    @Override
    public void Enqueue(int dato) {
        NodoCircular nuevo = new NodoCircular(dato);
        if (fin == null) {
            fin = nuevo;
            fin.siguiente = fin;           // un solo nodo: se apunta a sí mismo
        } else {
            nuevo.siguiente = fin.siguiente;   // el nuevo apunta al frente
            fin.siguiente = nuevo;             // el antiguo fin apunta al nuevo
            fin = nuevo;                       // el nuevo es ahora el fin
        }
        tamano++;
    }

    @Override
    public int Dequeue() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola está vacía");
        }
        NodoCircular frente = fin.siguiente;
        int dato = frente.dato;
        if (fin == frente) {
            fin = null;                        // era el único nodo
        } else {
            fin.siguiente = frente.siguiente;  // el fin salta al nuevo frente
        }
        tamano--;
        return dato;
    }

    @Override
    public int verFrente() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola está vacía");
        }
        return fin.siguiente.dato;
    }

    @Override
    public int verFinal() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola está vacía");
        }
        return fin.dato;                       // fin apunta al último nodo
    }

    @Override
    public boolean estaVacia() {
        return fin == null;
    }

    @Override
    public int tamano() {
        return tamano;
    }

    @Override
    public void mostrar() {
        if (estaVacia()) {
            System.out.println("Cola vacía");
            return;
        }
        StringBuilder sb = new StringBuilder();
        NodoCircular actual = fin.siguiente; //Empieza el frente
        for (int i = 0; i < tamano; i++) {
            sb.append(actual.dato).append(" -> ");
            actual = actual.siguiente;
        }
        sb.append("(vuelve al frente)");
        System.out.println(sb);
        System.out.println("Frente: " + fin.siguiente.dato + " | Fin: " + fin.dato);
    }

    // PRUEBAS INTERACTIVAS
    // El usuario elige las operaciones y los valores al ejecutar el programa.
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ColaCircularNodos cola = new Colacircularnodo();

        int opcion;
        do {
            System.out.println();
            System.out.println("===== COLA CIRCULAR CON NODOS =====");
            System.out.println("1. Enqueue (agregar)");
            System.out.println("2. Dequeue (eliminar)");
            System.out.println("3. Ver frente");
            System.out.println("4. Ver final");
            System.out.println("5. Mostrar cola");
            System.out.println("6. ¿Está vacía?");
            System.out.println("7. Tamaño");
            System.out.println("0. Salir");
            opcion = leerEntero(sc, "Opción: ");

            try {
                switch (opcion) {
                    case 1:
                        int dato = leerEntero(sc, "Valor a agregar: ");
                        cola.Enqueue(dato);
                        System.out.println("Agregado: " + dato);
                        break;
                    case 2:
                        System.out.println("Eliminado: " + cola.Dequeue());
                        break;
                    case 3:
                        System.out.println("Frente: " + cola.verFrente());
                        break;
                    case 4:
                        System.out.println("Final: " + cola.verFinal());
                        break;
                    case 5:
                        cola.mostrar();
                        break;
                    case 6:
                        System.out.println("¿Vacía? " + cola.estaVacia());
                        break;
                    case 7:
                        if (cola.tamano() > 0) {
                            System.out.println("Tamaño: " + cola.tamano());
                        } else {
                            System.out.println("El tamaño debe ser mayor a 0 (la cola está vacía).");
                        }
                        break;
                    case 0:
                        System.out.println("Fin del programa.");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 0);

        sc.close();
    }

    // Lee un entero. Si el usuario escribe una letra o carácter, el método
    // se llama a sí mismo (recursión) para que pueda corregirlo.
    private static int leerEntero(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        try {
            return sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Entrada no válida: escribe un número entero.");
            sc.next();                       // descarta lo escrito
            return leerEntero(sc, mensaje);  // llamada recursiva: vuelve a pedir el dato
        }
    }
}