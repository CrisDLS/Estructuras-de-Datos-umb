import java.util.Scanner;

// SIMULADOR: sala de urgencias. 1 = critico, 2 = urgente, 3 = leve.
public class Simulador {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("1) Con nodos   2) Con arreglo");
        int tipo = sc.nextInt();
        sc.nextLine();
        ColaPrioridad<String> cola;
        if (tipo == 1) cola = new ColaPrioridadNodos<>();
        else cola = new ColaPrioridadArreglo<>(50);

        int contador = 0; // numero de ficha
        int opcion;
        do {
            System.out.println("\n1) Registrar paciente  2) Atender  3) Ver siguiente  4) Cuantos esperan  0) Salir");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Prioridad (1 critico, 2 urgente, 3 leve): ");
                    int prio = sc.nextInt();
                    sc.nextLine();
                    int ficha = ++contador; // prefijo: sube y LUEGO asigna (primera ficha = 1)
                    cola.insertar("Ficha " + ficha + " - " + nombre, prio);
                    System.out.println("Registrado: ficha " + ficha);
                    break;
                case 2:
                    String atendido = cola.extraer();
                    System.out.println(atendido == null ? "No hay pacientes" : "Atendiendo: " + atendido);
                    break;
                case 3:
                    String sig = cola.frente();
                    System.out.println(sig == null ? "No hay pacientes" : "Sigue: " + sig);
                    break;
                case 4:
                    System.out.println("En espera: " + cola.tamano());
                    break;
            }
        } while (opcion != 0);
        sc.close();
        System.out.println("Fin");
    }
}
