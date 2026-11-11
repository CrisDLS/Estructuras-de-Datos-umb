import java.util.InputMismatchException;
import java.util.Scanner;

// NODO SIMPLEMENTE ENLAZADO
class NodoSimple {
    int dato;
    NodoSimple siguiente;

    NodoSimple(int dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}

// INTERFAZ
interface IColaSimpleNodos {
    void encolar(int dato);
    int desencolar();
    int verFrente();
    int verFinal();
    boolean estaVacia();
    int tamano();
    void mostrar();
}

// IMPLEMENTACIÓN DE COLA SIMPLE (FIFO) CON NODOS ENLAZADOS
public class ColaSimpleNodos implements IColaSimpleNodos {

    private NodoSimple frente;
    private NodoSimple fin;
    private int tamano;

    public ColaSimpleNodos() {
        this.frente = null;
        this.fin = null;
        this.tamano = 0;
    }

    @Override
    public void encolar(int dato) {
        NodoSimple nuevo = new NodoSimple(dato);
        if (estaVacia()) {
            frente = fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
        tamano++;
    }

    @Override
    public int desencolar() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola simple está vacía");
        }
        int valor = frente.dato;
        frente = frente.siguiente;
        if (frente == null) {
            fin = null;
        }
        tamano--;
        return valor;
    }

    @Override
    public int verFrente() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola simple está vacía");
        }
        return frente.dato;
    }

    @Override
    public int verFinal() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola simple está vacía");
        }
        return fin.dato;
    }

    @Override
    public boolean estaVacia() {
        return frente == null;
    }

    @Override
    public int tamano() {
        return tamano;
    }

    @Override
    public void mostrar() {
        if (estaVacia()) {
            System.out.println("Cola Simple: [vacía]");
            return;
        }
        StringBuilder sb = new StringBuilder("FRENTE -> ");
        NodoSimple actual = frente;
        while (actual != null) {
            sb.append(actual.dato);
            if (actual.siguiente != null) {
                sb.append(" -> ");
            }
            actual = actual.siguiente;
        }
        sb.append(" <- FINAL (Total: ").append(tamano).append(")");
        System.out.println(sb);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ColaSimpleNodos cola = new ColaSimpleNodos();

        int opcion;
        do {
            System.out.println("\n===== COLA SIMPLE CON NODOS =====");
            System.out.println("1. Enqueue (encolar)");
            System.out.println("2. Dequeue (desencolar)");
            System.out.println("3. Ver frente");
            System.out.println("4. Ver final");
            System.out.println("5. Mostrar cola");
            System.out.println("6. Tamaño y estado");
            System.out.println("0. Salir");
            opcion = leerEntero(sc, "Opción: ");

            try {
                switch (opcion) {
                    case 1:
                        int dato = leerEntero(sc, "Valor a encolar: ");
                        cola.encolar(dato);
                        System.out.println("Encolado: " + dato);
                        cola.mostrar();
                        break;
                    case 2:
                        System.out.println("Desencolado: " + cola.desencolar());
                        cola.mostrar();
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
                        System.out.println("Tamaño: " + cola.tamano() + " | ¿Vacía?: " + cola.estaVacia());
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

    private static int leerEntero(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        try {
            return sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Entrada no válida: escribe un número entero.");
            sc.next();
            return leerEntero(sc, mensaje);
        }
    }
}
