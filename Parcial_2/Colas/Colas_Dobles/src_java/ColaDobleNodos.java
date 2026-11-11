import java.util.InputMismatchException;
import java.util.Scanner;

// NODO DOBLEMENTE ENLAZADO
class NodoDoble {
    int dato;
    NodoDoble siguiente;
    NodoDoble anterior;

    NodoDoble(int dato) {
        this.dato = dato;
        this.siguiente = null;
        this.anterior = null;
    }
}

// INTERFAZ
interface IColaDobleNodos {
    void insertarFrente(int dato);
    void insertarFinal(int dato);
    int eliminarFrente();
    int eliminarFinal();
    int verFrente();
    int verFinal();
    boolean estaVacia();
    int tamano();
    void mostrar();
}

// IMPLEMENTACIÓN DE COLA DOBLE (BICOLA / DEQUE) CON NODOS ENLAZADOS
public class ColaDobleNodos implements IColaDobleNodos {

    private NodoDoble frente;
    private NodoDoble fin;
    private int tamano;

    public ColaDobleNodos() {
        this.frente = null;
        this.fin = null;
        this.tamano = 0;
    }

    @Override
    public void insertarFrente(int dato) {
        NodoDoble nuevo = new NodoDoble(dato);
        if (estaVacia()) {
            frente = fin = nuevo;
        } else {
            nuevo.siguiente = frente;
            frente.anterior = nuevo;
            frente = nuevo;
        }
        tamano++;
    }

    @Override
    public void insertarFinal(int dato) {
        NodoDoble nuevo = new NodoDoble(dato);
        if (estaVacia()) {
            frente = fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            nuevo.anterior = fin;
            fin = nuevo;
        }
        tamano++;
    }

    @Override
    public int eliminarFrente() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola doble está vacía");
        }
        int valor = frente.dato;
        if (frente == fin) {
            frente = fin = null;
        } else {
            frente = frente.siguiente;
            frente.anterior = null;
        }
        tamano--;
        return valor;
    }

    @Override
    public int eliminarFinal() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola doble está vacía");
        }
        int valor = fin.dato;
        if (frente == fin) {
            frente = fin = null;
        } else {
            fin = fin.anterior;
            fin.siguiente = null;
        }
        tamano--;
        return valor;
    }

    @Override
    public int verFrente() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola doble está vacía");
        }
        return frente.dato;
    }

    @Override
    public int verFinal() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola doble está vacía");
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
            System.out.println("Cola Doble: [vacía]");
            return;
        }
        StringBuilder sb = new StringBuilder("FRENTE -> ");
        NodoDoble actual = frente;
        while (actual != null) {
            sb.append(actual.dato);
            if (actual.siguiente != null) {
                sb.append(" <-> ");
            }
            actual = actual.siguiente;
        }
        sb.append(" <- FINAL (Total: ").append(tamano).append(")");
        System.out.println(sb);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ColaDobleNodos deque = new ColaDobleNodos();

        int opcion;
        do {
            System.out.println("\n===== COLA DOBLE CON NODOS =====");
            System.out.println("1. Insertar al frente");
            System.out.println("2. Insertar al final");
            System.out.println("3. Eliminar del frente");
            System.out.println("4. Eliminar del final");
            System.out.println("5. Ver frente");
            System.out.println("6. Ver final");
            System.out.println("7. Mostrar cola doble");
            System.out.println("8. Tamaño y estado");
            System.out.println("0. Salir");
            opcion = leerEntero(sc, "Opción: ");

            try {
                switch (opcion) {
                    case 1:
                        int datoFrente = leerEntero(sc, "Valor a insertar al frente: ");
                        deque.insertarFrente(datoFrente);
                        System.out.println("Insertado al frente: " + datoFrente);
                        deque.mostrar();
                        break;
                    case 2:
                        int datoFinal = leerEntero(sc, "Valor a insertar al final: ");
                        deque.insertarFinal(datoFinal);
                        System.out.println("Insertado al final: " + datoFinal);
                        deque.mostrar();
                        break;
                    case 3:
                        System.out.println("Eliminado del frente: " + deque.eliminarFrente());
                        deque.mostrar();
                        break;
                    case 4:
                        System.out.println("Eliminado del final: " + deque.eliminarFinal());
                        deque.mostrar();
                        break;
                    case 5:
                        System.out.println("Elemento al frente: " + deque.verFrente());
                        break;
                    case 6:
                        System.out.println("Elemento al final: " + deque.verFinal());
                        break;
                    case 7:
                        deque.mostrar();
                        break;
                    case 8:
                        System.out.println("Tamaño actual: " + deque.tamano() + " | ¿Vacía?: " + deque.estaVacia());
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
