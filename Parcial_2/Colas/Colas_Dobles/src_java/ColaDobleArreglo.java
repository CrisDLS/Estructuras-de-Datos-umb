import java.util.InputMismatchException;
import java.util.Scanner;

// INTERFAZ
interface IColaDobleArreglo {
    void insertarFrente(int dato);
    void insertarFinal(int dato);
    int eliminarFrente();
    int eliminarFinal();
    int verFrente();
    int verFinal();
    boolean estaVacia();
    boolean estaLlena();
    int tamano();
    void mostrar();
}

// IMPLEMENTACIÓN DE COLA DOBLE (BICOLA / DEQUE) CON ARREGLO CIRCULAR
public class ColaDobleArreglo implements IColaDobleArreglo {

    private int[] datos;
    private int frente;
    private int fin;
    private int tamano;
    private int capacidad;

    public ColaDobleArreglo() {
        this(5);
    }

    public ColaDobleArreglo(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor a 0");
        }
        this.capacidad = capacidad;
        this.datos = new int[capacidad];
        this.frente = -1;
        this.fin = -1;
        this.tamano = 0;
    }

    @Override
    public void insertarFrente(int dato) {
        if (estaLlena()) {
            throw new IllegalStateException("Overflow: La cola doble está llena");
        }
        if (estaVacia()) {
            frente = fin = 0;
        } else if (frente == 0) {
            frente = capacidad - 1;
        } else {
            frente--;
        }
        datos[frente] = dato;
        tamano++;
    }

    @Override
    public void insertarFinal(int dato) {
        if (estaLlena()) {
            throw new IllegalStateException("Overflow: La cola doble está llena");
        }
        if (estaVacia()) {
            frente = fin = 0;
        } else if (fin == capacidad - 1) {
            fin = 0;
        } else {
            fin++;
        }
        datos[fin] = dato;
        tamano++;
    }

    @Override
    public int eliminarFrente() {
        if (estaVacia()) {
            throw new IllegalStateException("Underflow: La cola doble está vacía");
        }
        int valor = datos[frente];
        if (frente == fin) {
            frente = fin = -1;
        } else if (frente == capacidad - 1) {
            frente = 0;
        } else {
            frente++;
        }
        tamano--;
        return valor;
    }

    @Override
    public int eliminarFinal() {
        if (estaVacia()) {
            throw new IllegalStateException("Underflow: La cola doble está vacía");
        }
        int valor = datos[fin];
        if (frente == fin) {
            frente = fin = -1;
        } else if (fin == 0) {
            fin = capacidad - 1;
        } else {
            fin--;
        }
        tamano--;
        return valor;
    }

    @Override
    public int verFrente() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola doble está vacía");
        }
        return datos[frente];
    }

    @Override
    public int verFinal() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola doble está vacía");
        }
        return datos[fin];
    }

    @Override
    public boolean estaVacia() {
        return tamano == 0;
    }

    @Override
    public boolean estaLlena() {
        return tamano == capacidad;
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
        StringBuilder sb = new StringBuilder("FRENTE -> [");
        for (int i = 0; i < tamano; i++) {
            sb.append(datos[(frente + i) % capacidad]);
            if (i < tamano - 1) {
                sb.append(" <-> ");
            }
        }
        sb.append("] <- FINAL");
        System.out.println(sb);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int capacidad = leerCapacidad(sc);
        ColaDobleArreglo deque = new ColaDobleArreglo(capacidad);

        int opcion;
        do {
            System.out.println("\n===== COLA DOBLE (ARREGLO) =====");
            System.out.println("1. Insertar al frente");
            System.out.println("2. Insertar al final");
            System.out.println("3. Eliminar del frente");
            System.out.println("4. Eliminar del final");
            System.out.println("5. Ver frente");
            System.out.println("6. Ver final");
            System.out.println("7. Mostrar cola doble");
            System.out.println("8. Estado (vacía / llena / tamaño)");
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
                        System.out.println("Tamaño: " + deque.tamano() + " / " + capacidad
                                + " | ¿Vacía?: " + deque.estaVacia()
                                + " | ¿Llena?: " + deque.estaLlena());
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
        int cap = leerEntero(sc, "Capacidad de la cola doble: ");
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
