package app;

import java.util.Scanner;
import listas.*;

/**
 * Menú interactivo y demostración de polimorfismo para el proyecto de Listas Enlazadas.
 * 
 * @author Fátima (Base y Aplicación) / Integración: Cristian
 */
public class Main {

    // Método polimórfico: acepta cualquier tipo de lista derivada de ListaEnlazada
    static void llenar(ListaEnlazada lista) {
        for (int d : new int[]{30, 10, 50, 20, 40}) {
            lista.insertar(d);
        }
    }

    static void llenar(ListaMulti lista){
        lista.insertar("Ana", 20);
        lista.insertar("Fatima", 18);
        lista.insertar("Cristian", 21);
        lista.insertar("Luis", 15);
    }

    static void demostracion() {
        System.out.println("==================================================");
        System.out.println("       DEMOSTRACIÓN DE POLIMORFISMO DINÁMICO      ");
        System.out.println("==================================================");

        // Se incluyen las listas completadas del equipo.
        // ListaDoble y ListaCircular permanecen comentadas temporalmente hasta la entrega de Karen.
        ListaEnlazada[] listas = {
            new ListaSimple(),
            new ListaMulti()
            // new ListaDoble(),
            // new ListaCircular()
        };

        System.out.println("\n--- 1. Llenado polimórfico con datos: {30, 10, 50, 20, 40} ---");
        for (ListaEnlazada l : listas) {
            llenar(l); // Mismo código cliente, comportamiento especializado según la clase
            System.out.print("\n[" + l.getClass().getSimpleName() + "]:\n");
            l.mostrar();
        }

        System.out.println("\n--- 2. Eliminación polimórfica del valor 50 ---");
        for (ListaEnlazada l : listas) {
            boolean eliminado = l.eliminar(50);
            System.out.println("\nEliminado 50 de " + l.getClass().getSimpleName() + ": " + (eliminado ? "Éxito" : "No encontrado"));
            l.mostrar();
        }

        /* 
        // Bloque reservado para ejecución bidireccional cuando Karen entregue ListaDoble:
        if (listas.length > 2 && listas[2] instanceof ListaDoble) {
            System.out.print("\nListaDoble inverso: ");
            ((ListaDoble) listas[2]).mostrarInverso();
        }
        */

        System.out.println("\n--- Demostración de la Multilista con criterios de Nombre y Edad ---");
        ListaMulti miMulti = new ListaMulti();
        llenar(miMulti); // Llenado con nombres y edades
        miMulti.mostrarAlfabetico(); // Muestra la lista ordenada alfabéticamente por nombre
        miMulti.mostrarPorEdad(); // Muestra la lista ordenada por edad

        System.out.println("\n--- Eliminación de 'Fatima' de la Multilista ---");
        miMulti.eliminar("Fatima");
        miMulti.mostrarAlfabetico(); // Muestra la lista ordenada alfabéticamente por nombre
        miMulti.mostrarPorEdad(); // Muestra la lista ordenada por edad
        
        System.out.println("\n>>> Fin de la demostración de polimorfismo <<<\n");
    }

    static void interactivo(Scanner in) {
        System.out.println("\n--- SELECCIÓN DE ESTRUCTURA ---");
        System.out.println("1) Lista Simple");
        System.out.println("2) Multilista: Personas [Nombre + Edad]");
        System.out.println("3) Lista Doble");
        System.out.println("4) Lista Circular");
        System.out.print("Elige una opción: ");

        int tipo = in.nextInt();
        ListaEnlazada lista;
        switch (tipo) {
            case 2:
                lista = new ListaMulti();
                System.out.println("\n-> Has seleccionado: Multilista de Personas (Doble Criterio: Alfabético y Edad).");
                break;
            case 3:
            case 4:
                System.out.println("\n[!] Módulo en desarrollo por Integrante Karen. Asignando Lista Simple temporalmente.");
                lista = new ListaSimple();
                break;
            default:
                lista = new ListaSimple();
                System.out.println("\n-> Has seleccionado: Lista Simple.");
        }

        int op;
        do {
            System.out.println("\n----------------------------------------");
            System.out.println("Operaciones para [" + lista.getClass().getSimpleName() + "]:");
            System.out.println("1) Insertar");
            System.out.println("2) Eliminar");
            System.out.println("3) Buscar");
            System.out.println("4) Mostrar lista");
            System.out.println("0) Volver al menú principal");
            System.out.print("Selecciona una opción: ");
            op = in.nextInt();

            switch (op) {
                case 1:
                    if (lista instanceof ListaMulti) {
                        System.out.print("Ingresa el Nombre de la persona: ");
                        String nom = in.next();
                        System.out.print("Ingresa la Edad: ");
                        int edad = in.nextInt();
                        ((ListaMulti) lista).insertar(nom, edad);
                        System.out.println("-> Persona agregada a la Multilista en ambas dimensiones.");
                    } else {
                        System.out.print("Ingresa un valor entero: ");
                        int v = in.nextInt();
                        lista.insertar(v);
                        System.out.println("-> Elemento insertado en la lista simple.");
                    }
                    break;

                case 2:
                    if (lista instanceof ListaMulti) {
                        System.out.print("Ingresa el Nombre de la persona a eliminar: ");
                        String nom = in.next();
                        boolean r = ((ListaMulti) lista).eliminar(nom);
                        System.out.println(r ? "-> Persona eliminada atómicamente de ambos criterios." : "-> La persona no existe en la Multilista.");
                    } else {
                        System.out.print("Ingresa el valor entero a eliminar: ");
                        int v = in.nextInt();
                        boolean r = lista.eliminar(v);
                        System.out.println(r ? "-> Elemento eliminado con éxito." : "-> El valor no se encontró.");
                    }
                    break;

                case 3:
                    if (lista instanceof ListaMulti) {
                        System.out.println("Buscar por: 1) Nombre  2) Edad");
                        int subOp = in.nextInt();
                        if (subOp == 1) {
                            System.out.print("Nombre a buscar: ");
                            String nom = in.next();
                            boolean r = ((ListaMulti) lista).buscar(nom);
                            System.out.println(r ? "-> Encontrado en el criterio alfabético." : "-> No encontrado.");
                        } else {
                            System.out.print("Edad a buscar: ");
                            int ed = in.nextInt();
                            boolean r = ((ListaMulti) lista).buscarPorEdad(ed);
                            System.out.println(r ? "-> Encontrado en el criterio por edad." : "-> No encontrado.");
                        }
                    } else {
                        System.out.print("Ingresa el valor entero a buscar: ");
                        int v = in.nextInt();
                        boolean r = lista.buscar(v);
                        System.out.println(r ? "-> Elemento encontrado en la lista." : "-> No encontrado.");
                    }
                    break;

                case 4:
                    System.out.println("\nEstado actual de la estructura:");
                    lista.mostrar();
                    break;

                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (op != 0);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int op;
        do {
            System.out.println("\n==================================================");
            System.out.println("       PROYECTO: LISTAS ENLAZADAS (JAVA)          ");
            System.out.println("       Estructura de Datos - Segundo Parcial      ");
            System.out.println("==================================================");
            System.out.println("1) Demostración de Polimorfismo Dinámico");
            System.out.println("2) Modo Interactivo por Consola");
            System.out.println("0) Salir");
            System.out.print("Selecciona una opción: ");
            
            if (!in.hasNextInt()) {
                in.next(); // Limpiar entrada errónea
                op = -1;
                continue;
            }
            op = in.nextInt();

            if (op == 1) {
                demostracion();
            } else if (op == 2) {
                interactivo(in);
            } else if (op == 0) {
                System.out.println("\n¡Hasta luego! Proyecto Listas Enlazadas finalizado.");
            } else {
                System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } while (op != 0);
        in.close();
    }
}
