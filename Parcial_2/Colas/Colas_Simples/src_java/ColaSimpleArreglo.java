import java.util.InputMismatchException;
import java.util.Scanner;

// INTERFAZ
interface IColaSimpleArreglo {
    void encolar(int dato);
    int desencolar();
    int verFrente();
    int verFinal();
    boolean estaVacia();
    boolean estaLlena();
    int tamano();
    void mostrar();
}

// IMPLEMENTACIÓN DE COLA SIMPLE (FIFO) CON ARREGLO
public class ColaSimpleArreglo implements IColaSimpleArreglo {

    private int[] datos;
    private int frente;
    private int fin;
    private int cantidad;
    private int capacidad;

    public ColaSimpleArreglo(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor a 0");
        }
        this.capacidad = capacidad;
        this.datos = new int[capacidad];
        this.frente = 0;
        this.fin = -1;
        this.cantidad = 0;
    }

    @Override
    public void encolar(int dato) {
        if (estaLlena()) {
            throw new IllegalStateException("Overflow: La cola simple está llena");
        }
        fin++;
        datos[fin] = dato;
        cantidad++;
    }

    @Override
    public int desencolar() {
        if (estaVacia()) {
            throw new IllegalStateException("Underflow: La cola simple está vacía");
        }
        int valor = datos[frente];
        frente++;
        cantidad--;
        if (cantidad == 0) {
            frente = 0;
            fin = -1;
        }
        return valor;
    }

    @Override
    public int verFrente() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola simple está vacía");
        }
        return datos[frente];
    }

    @Override
    public int verFinal() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola simple está vacía");
        }
        return datos[fin];
    }

    @Override
    public boolean estaVacia() {
        return cantidad == 0;
    }

    @Override
    public boolean estaLlena() {
        return fin == capacidad - 1;
    }

    @Override
    public int tamano() {
        return cantidad;
    }

    @Override
    public void mostrar() {
        if (estaVacia()) {
            System.out.println("Cola Simple: [vacía]");
            return;
        }
        StringBuilder sb = new StringBuilder("FRENTE -> [");
        for (int i = frente; i <= fin; i++) {
            sb.append(datos[i]);
            if (i < fin) {
                sb.append(" -> ");
            }
        }
        sb.append("] <- FINAL");
        System.out.println(sb);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int capacidad = leerCapacidad(sc);
        ColaSimpleArreglo cola = new ColaSimpleArreglo(capacidad);

        int opcion;
        do {
            System.out.println("\n===== COLA SIMPLE (ARREGLO) =====");
            System.out.println("1. Enqueue (encolar)");
            System.out.println("2. Dequeue (desencolar)");
            System.out.println("3. Ver frente");
            System.out.println("4. Ver final");
            System.out.println("5. Mostrar cola");
            System.out.println("6. Estado y tamaño");
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
                        System.out.println("Tamaño: " + cola.tamano()
                                + " | ¿Vacía?: " + cola.estaVacia()
                                + " | ¿Llena?: " + cola.estaLlena());
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

    private static int leerCapacidad(Scanner sc) {
        int cap = leerEntero(sc, "Capacidad de la cola simple: ");
        if (cap <= 0) {
            System.out.println("La capacidad debe ser mayor a 0.");
            return leerCapacidad(sc);
        }
        return cap;
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
