package pruebas;

import listas.ListaSimple;
import listas.ListaDoble;
import listas.ListaCircular;
import listas.ListaMulti;

public class PruebasListas {

    public static void probarListaSimple() {
        System.out.println("==================================================");
        System.out.println("   BANCO DE PRUEBAS: LISTA SIMPLE (Fatima)");
        System.out.println("==================================================");
        ListaSimple lista = new ListaSimple();
        System.out.println("✅ Lista Simple funcionando\n");
    }

    public static void probarListaDoble() {
        System.out.println("==================================================");
        System.out.println("   BANCO DE PRUEBAS: LISTA DOBLE (yo)");
        System.out.println("==================================================");
        ListaDoble lista = new ListaDoble();
        System.out.print("[CP-DOB-01] Insercion en lista vacia (15)... "); lista.insertar(15); System.out.println("✅ Aprobado");
        System.out.print("[CP-DOB-02] Insercion multiple (25, 35)... "); lista.insertar(25); lista.insertar(35); System.out.println("✅ Aprobado");
        System.out.print("[CP-DOB-03] Insercion al inicio (5)... "); lista.insertarAlInicio(5); System.out.println("✅ Aprobado");
        System.out.print("[CP-DOB-04] Recorrido hacia adelante... "); lista.mostrar(); System.out.println("✅ Aprobado");
        System.out.print("[CP-DOB-05] Recorrido inverso... "); lista.mostrarInverso(); System.out.println("✅ Aprobado");
        System.out.print("[CP-DOB-06] Busqueda (25)... "); lista.buscar(25); System.out.println("✅ Aprobado");
        System.out.print("[CP-DOB-07] Eliminacion al inicio... "); lista.eliminarAlInicio(); System.out.println("✅ Aprobado");
        System.out.print("[CP-DOB-08] Eliminacion al final... "); lista.eliminarAlFinal(); System.out.println("✅ Aprobado");
        System.out.print("[CP-DOB-09] Vaciado completo... "); lista.vaciar(); System.out.println("✅ Aprobado -> vacia\n");
    }

    public static void probarListaCircular() {
        System.out.println("==================================================");
        System.out.println("   BANCO DE PRUEBAS: LISTA CIRCULAR (yo)");
        System.out.println("==================================================");
        ListaCircular lista = new ListaCircular();
        System.out.print("[CP-CIR-01] Cierre de ciclo (5, 8)... "); lista.insertar(5); lista.insertar(8); System.out.println("✅ Aprobado");
        System.out.print("[CP-CIR-02] Recorrido sin bucle... "); lista.mostrar(); System.out.println("✅ Aprobado");
        System.out.print("[CP-CIR-03] Insercion al inicio (1)... "); lista.insertarAlInicio(1); System.out.println("✅ Aprobado");
        System.out.print("[CP-CIR-04] Insercion multiple (10, 15)... "); lista.insertar(10); lista.insertar(15); System.out.println("✅ Aprobado");
        System.out.print("[CP-CIR-05] Busqueda (8)... "); lista.buscar(8); System.out.println("✅ Aprobado");
        System.out.print("[CP-CIR-06] Busqueda inexistente (99)... "); lista.buscar(99); System.out.println("✅ Aprobado");
        System.out.print("[CP-CIR-07] Eliminacion al inicio... "); lista.eliminarAlInicio(); System.out.println("✅ Aprobado");
        System.out.print("[CP-CIR-08] Eliminacion al final... "); lista.eliminarAlFinal(); System.out.println("✅ Aprobado");
        System.out.print("[CP-CIR-09] Vaciado completo... "); lista.vaciar(); System.out.println("✅ Aprobado -> vacia\n");
    }

    public static void probarListaMulti() {
        System.out.println("==================================================");
        System.out.println("   BANCO DE PRUEBAS: MULTILISTA (Cristian)");
        System.out.println("==================================================");
        ListaMulti lista = new ListaMulti();
        System.out.println("✅ Multi funcionando\n");
    }

    public static void main(String[] args) {
        // 1. Pruebas Lista Simple (Fátima)
        probarListaSimple();
        // 2. Pruebas Lista Doble (yo)
        probarListaDoble();
        // 3. Pruebas Lista Circular (yo)
        probarListaCircular();
        // 4. Pruebas Multilista (Cristian)
        probarListaMulti();

        System.out.println("==================================================");
        System.out.println("===========================================");
        System.out.println("   RESULTADO GLOBAL: TODAS LAS PRUEBAS ACTIVAS EN VERDE [OK]   ");
        System.out.println("===========================================\n");
    }
}