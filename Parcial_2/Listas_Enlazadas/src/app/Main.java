package app;

import java.util.Scanner;
import listas.*;

/** Menú interactivo y demostración de polimorfismo. */
public class Main {

    // Método polimórfico: acepta cualquier tipo de lista
    static void llenar(ListaEnlazada lista) {
        for (int d : new int[]{30, 10, 50, 20, 40}) lista.insertar(d);
    }

    static void demostracion() {
        ListaEnlazada[] listas = {
            new ListaSimple(), new ListaOrdenada(), new ListaDoble(), new ListaCircular()
        };
        for (ListaEnlazada l : listas) {
            llenar(l); // mismo código, comportamiento distinto
            System.out.print(l.getClass().getSimpleName() + ": ");
            l.mostrar();
        }
        System.out.println("\nEliminar el 50 en todas:");
        for (ListaEnlazada l : listas) {
            l.eliminar(50);
            System.out.print(l.getClass().getSimpleName() + ": ");
            l.mostrar();
        }
        if (listas[2] instanceof ListaDoble) {
            System.out.print("\nListaDoble inverso: ");
            ((ListaDoble) listas[2]).mostrarInverso();
        }
    }

    static void interactivo(Scanner in) {
        System.out.println("Tipo de lista: 1) Simple 2) Ordenada 3) Doble 4) Circular");
        ListaEnlazada lista;
        switch (in.nextInt()) {
            case 2:  lista = new ListaOrdenada(); break;
            case 3:  lista = new ListaDoble();    break;
            case 4:  lista = new ListaCircular(); break;
            default: lista = new ListaSimple();
        }
        int op;
        do {
            System.out.println("\n1) Insertar 2) Eliminar 3) Buscar 4) Mostrar 0) Volver");
            op = in.nextInt();
            if (op >= 1 && op <= 3) {
                System.out.print("Valor: ");
                int v = in.nextInt();
                if (op == 1) lista.insertar(v);
                else if (op == 2) System.out.println(lista.eliminar(v) ? "Eliminado" : "No existe");
                else System.out.println(lista.buscar(v) ? "Encontrado" : "No encontrado");
            } else if (op == 4) {
                lista.mostrar();
            }
        } while (op != 0);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int op;
        do {
            System.out.println("\n=== PROYECTO LISTAS ===\n1) Demostración de polimorfismo\n2) Modo interactivo\n0) Salir");
            op = in.nextInt();
            if (op == 1) demostracion();
            else if (op == 2) interactivo(in);
        } while (op != 0);
    }
}
