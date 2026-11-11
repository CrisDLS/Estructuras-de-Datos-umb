import java.util.InputMismatchException;
import java.util.Scanner;

// INTERFAZ
interface IColaCircularArreglo {
    void Enqueue(int dato);      // agrega al final de la cola
    int Dequeue();               // saca y devuelve el dato del frente
    int verFrente();             // devuelve el dato del frente sin sacarlo
    boolean estaVacia();
    boolean estaLlena();
    int tamano();                // cantidad de elementos actuales
    void mostrar();
}

// IMPLEMENTACIÓN
public class Colacirculaarreglo implements IColaCircularArreglo {

    private int[] datos;
    private int frente;      // posición del primer elemento
    private int fin;         // posición donde se insertará el siguiente
    private int tamano;
    private int capacidad;

    public Colacirculaarreglo() {
        this(5); // capacidad por defecto
    }

    public Colacirculaarreglo(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor a 0");
        }
        this.capacidad = capacidad;
        datos = new int[capacidad];
        frente = 0;
        fin = 0;
        tamano = 0;
    }

    @Override
    public void Enqueue(int dato) {
        if (estaLlena()) {
            throw new IllegalStateException("La cola está llena");
        }
        datos[fin] = dato;
        fin = (fin + 1) % capacidad;   // si llega al final, regresa a 0
        tamano++;
    }

    @Override
    public int Dequeue() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola está vacía");
        }
        int dato = datos[frente];
        frente = (frente + 1) % capacidad;   // avanza de forma circular
        tamano--;
        return dato;
    }

    @Override
    public int verFrente() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola está vacía");
        }
        return datos[frente];
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
        StringBuilder sb = new StringBuilder("Frente -> [");
        for (int i = 0; i < tamano; i++) {
            sb.append(datos[(frente + i) % capacidad]);
            if (i < tamano - 1) sb.append(", ");
        }
        sb.append("] <- Fin");
        System.out.println(sb);
    }

    // Muestra el arreglo completo con los índices frente y fin (útil para ver lo "circular")
    public void mostrarArreglo() {
        StringBuilder sb = new StringBuilder("Arreglo: [");
        for (int i = 0; i < capacidad; i++) {
            sb.append(datos[i]);
            if (i < capacidad - 1) sb.append(", ");
        }
        sb.append("]  frente=").append(frente).append("  fin=").append(fin);
        System.out.println(sb);
    }

    // ---------- PRUEBAS INTERACTIVAS ----------
    // El usuario elige las operaciones y los valores al ejecutar el programa.
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Se repite la pregunta hasta que la capacidad sea válida (mayor a 0)
        int capacidad = leerCapacidad(sc);
        Colacirculaarreglo cola = new Colacirculaarreglo(capacidad);

        int opcion;
        do {
            System.out.println();
            System.out.println("===== COLA CIRCULAR =====");
            System.out.println("1. Enqueue (agregar)");
            System.out.println("2. Dequeue (eliminar)");
            System.out.println("3. Ver frente");
            System.out.println("4. Mostrar cola");
            System.out.println("5. Mostrar arreglo (frente y fin)");
            System.out.println("6. ¿Está llena?");
            System.out.println("7. ¿Está vacía?");
            System.out.println("8. Tamaño");
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
                        cola.mostrar();
                        break;
                    case 5:
                        cola.mostrarArreglo();
                        break;
                    case 6:
                        // Muestra el mensaje según el estado real de la cola
                        if (cola.estaLlena()) {
                            System.out.println("La cola está llena");
                        } else {
                            System.out.println("La cola aun tiene espacios");
                        }
                        break;
                    case 7:
                        // Muestra el mensaje según el estado real de la cola
                        if (cola.estaVacia()) {
                            System.out.println("La cola está vacía");
                        } else {
                            System.out.println("La cola no está vacía");
                        }
                        break;
                    case 8:
                        System.out.println("Tamaño: " + cola.tamano());
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

    // Pide la capacidad. Si no es un número o es menor o igual a 0,
    // se llama a sí mismo y vuelve a pedirla hasta que sea válida.
    private static int leerCapacidad(Scanner sc) {
        int capacidad = leerEntero(sc, "Capacidad de la cola: ");
        if (capacidad <= 0) {
            System.out.println("La capacidad debe ser mayor a 0. Inténtalo de nuevo.");
            return leerCapacidad(sc);
        }
        return capacidad;
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