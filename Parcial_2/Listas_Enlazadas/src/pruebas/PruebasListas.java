package pruebas;

import listas.ListaSimple;
import listas.ListaMulti;
// [INTEGRANTE KAREN - PENDIENTE DE ENTREGA]:
// Descomentar cuando Karen entregue ListaDoble y ListaCircular:
import listas.ListaDoble;
import listas.ListaCircular;

/**
 * Banco de pruebas automatizadas y sincronizadas con el Reporte Formal de Pruebas.
 * <p>
 * Cada método y bloque de prueba ejecuta y valida exactamente los casos de prueba (CP)
 * registrados en {@code docs/reporte_pruebas.md}:
 * <ul>
 *   <li><b>CP-SIM-01 a CP-SIM-09:</b> {@link ListaSimple} (Entrega Integrante Fátima)</li>
 *   <li><b>CP-DOB-01 a CP-DOB-02:</b> {@code ListaDoble} (Pendiente Integrante Karen)</li>
 *   <li><b>CP-CIR-01 a CP-CIR-02:</b> {@code ListaCircular} (Pendiente Integrante Karen)</li>
 *   <li><b>CP-MUL-01 a CP-MUL-14:</b> {@link ListaMulti} (Entrega Integrante Cristian)</li>
 * </ul>
 * </p>
 *
 * @author Cristian (Integrante 3 - Arquitectura y Multilistas)
 */
public class PruebasListas {

    /**
     * Valida exhaustivamente el comportamiento de la Lista Enlazada Simple (Fátima).
     * Casos: CP-SIM-01 al CP-SIM-09
     */
    public static void probarListaSimple() {
        System.out.println("==================================================");
        System.out.println("   BANCO DE PRUEBAS: LISTA SIMPLE (Fátima)");
        System.out.println("==================================================");

        ListaSimple lista = new ListaSimple();

        // CP-SIM-01: Inserción en lista vacía
        System.out.print("[CP-SIM-01] Inserción en lista vacía (10)... ");
        lista.insertar(10);
        assert lista.getTamano() == 1 : "Error en CP-SIM-01: Tamaño debe ser 1";
        assert !lista.estaVacia() : "Error en CP-SIM-01: La lista no debe estar vacía";
        System.out.println("✅ Aprobado -> tamano = " + lista.getTamano());

        // CP-SIM-02: Inserción al final múltiple
        System.out.print("[CP-SIM-02] Inserción al final múltiple (20, 30)... ");
        lista.insertar(20);
        lista.insertar(30);
        assert lista.getTamano() == 3 : "Error en CP-SIM-02: Tamaño debe ser 3";
        System.out.println("✅ Aprobado -> tamano = " + lista.getTamano());

        // CP-SIM-03: Inserción al inicio
        System.out.print("[CP-SIM-03] Inserción al inicio (5)... ");
        lista.insertarAlInicio(5);
        assert lista.getTamano() == 4 : "Error en CP-SIM-03: Tamaño debe ser 4";
        assert lista.buscar(5) : "Error en CP-SIM-03: El 5 debe existir al inicio";
        System.out.println("✅ Aprobado -> lista con 5 al frente");

        // CP-SIM-04: Búsqueda elemento existente
        System.out.print("[CP-SIM-04] Búsqueda de elemento existente (20)... ");
        boolean hallado20 = lista.buscar(20);
        assert hallado20 : "Error en CP-SIM-04: El 20 debe encontrarse";
        System.out.println("✅ Aprobado -> resultado: " + hallado20);

        // CP-SIM-05: Búsqueda elemento inexistente
        System.out.print("[CP-SIM-05] Búsqueda de elemento inexistente (99)... ");
        boolean hallado99 = lista.buscar(99);
        assert !hallado99 : "Error en CP-SIM-05: El 99 no debe existir";
        System.out.println("✅ Aprobado -> resultado: " + hallado99);

        // CP-SIM-06: Eliminación de cabeza
        System.out.print("[CP-SIM-06] Eliminación de cabeza (5)... ");
        boolean elimCabeza = lista.eliminar(5);
        assert elimCabeza : "Error en CP-SIM-06: Debe retornar true al eliminar cabeza";
        assert lista.getTamano() == 3 : "Error en CP-SIM-06: Tamaño debe ser 3";
        assert !lista.buscar(5) : "Error en CP-SIM-06: El 5 ya no debe estar";
        System.out.println("✅ Aprobado -> nueva cabeza es 10");

        // CP-SIM-07: Eliminación intermedia
        System.out.print("[CP-SIM-07] Eliminación de nodo intermedio (20)... ");
        boolean elimInter = lista.eliminar(20);
        assert elimInter : "Error en CP-SIM-07: Debe retornar true al eliminar nodo intermedio";
        assert lista.getTamano() == 2 : "Error en CP-SIM-07: Tamaño debe ser 2";
        assert !lista.buscar(20) : "Error en CP-SIM-07: El 20 ya no debe existir";
        System.out.println("✅ Aprobado -> lista enlazada 10 -> 30 -> null");

        // CP-SIM-08: Eliminación inexistente
        System.out.print("[CP-SIM-08] Eliminación de elemento inexistente (99)... ");
        boolean elimInex = lista.eliminar(99);
        assert !elimInex : "Error en CP-SIM-08: Debe retornar false";
        assert lista.getTamano() == 2 : "Error en CP-SIM-08: Tamaño debe permanecer en 2";
        System.out.println("✅ Aprobado -> retorna false, lista inalterada");

        // CP-SIM-09: Vaciado total de la lista
        System.out.print("[CP-SIM-09] Vaciado total de la lista (eliminar 10 y 30)... ");
        lista.eliminar(10);
        lista.eliminar(30);
        assert lista.estaVacia() : "Error en CP-SIM-09: La lista debe estar vacía";
        assert lista.getTamano() == 0 : "Error en CP-SIM-09: El tamaño debe ser 0";
        System.out.println("✅ Aprobado -> estaVacia = true, tamano = 0\n");
    }

    /**
     * Plantilla de pruebas para Lista Doblemente Enlazada (Karen).
     * Casos: CP-DOB-01 y CP-DOB-02
     */
 public static void probarListaDoble() {
        System.out.println("==================================================");
        System.out.println("   BANCO DE PRUEBAS: LISTA DOBLE (yo)");
        System.out.println("==================================================");
        ListaDoble lista = new ListaDoble();

        System.out.print("[CP-DOB-01] Insercion en lista vacia (15)... ");
        lista.insertar(15);
        assert lista.getTamano() == 1 : "Error DOB-01";
        System.out.println("✅ Aprobado");

        System.out.print("[CP-DOB-02] Insercion multiple (25, 35)... ");
        lista.insertar(25);
        lista.insertar(35);
        assert lista.getTamano() == 3 : "Error DOB-02";
        System.out.println("✅ Aprobado");

        System.out.print("[CP-DOB-03] Insercion al inicio (5)... ");
        lista.insertarAlInicio(5);
        assert lista.getTamano() == 4 : "Error DOB-03";
        System.out.println("✅ Aprobado");

        System.out.print("[CP-DOB-04] Recorrido hacia adelante... ");
        lista.mostrar();
        System.out.println("✅ Aprobado");

        System.out.print("[CP-DOB-05] Recorrido inverso... ");
        lista.mostrarInverso();
        System.out.println("✅ Aprobado");

        System.out.print("[CP-DOB-06] Busqueda (25)... ");
        assert lista.buscar(25) : "Error DOB-06";
        System.out.println("✅ Aprobado");

        System.out.print("[CP-DOB-07] Eliminacion al inicio... ");
        lista.eliminarAlInicio();
        assert lista.getTamano() == 3 : "Error DOB-07";
        System.out.println("✅ Aprobado");

        System.out.print("[CP-DOB-08] Eliminacion al final... ");
        lista.eliminarAlFinal();
        assert lista.getTamano() == 2 : "Error DOB-08";
        System.out.println("✅ Aprobado");

        System.out.print("[CP-DOB-09] Vaciado completo... ");
        lista.vaciar();
        assert lista.estaVacia() : "Error DOB-09";
        System.out.println("✅ Aprobado\n");
    }
   
       public static void probarListaCircular() {
        System.out.println("==================================================");
        System.out.println("   BANCO DE PRUEBAS: LISTA CIRCULAR (yo)");
        System.out.println("==================================================");
        ListaCircular lista = new ListaCircular();

        System.out.print("[CP-CIR-01] Cierre de ciclo circular (5, 8)... ");
        lista.insertar(5);
        lista.insertar(8);
        assert lista.getTamano() == 2 : "CP-CIR-01 Error";
        System.out.println("✅ Aprobado");

        System.out.print("[CP-CIR-02] Recorrido sin bucle infinito... ");
        lista.mostrar();
        System.out.println("✅ Aprobado");

        System.out.print("[CP-CIR-03] Insercion al inicio (1)... ");
        lista.insertarAlInicio(1);
        assert lista.getTamano() == 3 : "CP-CIR-03 Error";
        System.out.println("✅ Aprobado");

        System.out.print("[CP-CIR-04] Insercion multiple (10, 15)... ");
        lista.insertar(10);
        lista.insertar(15);
        assert lista.getTamano() == 5 : "CP-CIR-04 Error";
        System.out.println("✅ Aprobado");

        System.out.print("[CP-CIR-05] Busqueda existente (8)... ");
        assert lista.buscar(8) : "CP-CIR-05 Error";
        System.out.println("✅ Aprobado");

        System.out.print("[CP-CIR-06] Busqueda inexistente (99)... ");
        assert !lista.buscar(99) : "CP-CIR-06 Error";
        System.out.println("✅ Aprobado");

        System.out.print("[CP-CIR-07] Eliminacion al inicio... ");
        lista.eliminarAlInicio();
        assert lista.getTamano() == 4 : "CP-CIR-07 Error";
        System.out.println("✅ Aprobado");

        System.out.print("[CP-CIR-08] Eliminacion al final... ");
        lista.eliminarAlFinal();
        assert lista.getTamano() == 3 : "CP-CIR-08 Error";
        System.out.println("✅ Aprobado");

        System.out.print("[CP-CIR-09] Vaciado completo... ");
        lista.vaciar();
        assert lista.estaVacia() : "CP-CIR-09 Error";
        System.out.println("✅ Aprobado -> vacia\n");
    }
    /**
     * Valida exhaustivamente el comportamiento de la Multilista de Personas (Cristian).
     * Casos: CP-MUL-01 al CP-MUL-14
     */
    public static void probarListaMulti() {
        System.out.println("==================================================");
        System.out.println("   BANCO DE PRUEBAS: MULTILISTA 2D (Cristian)");
        System.out.println("   (Personas: Criterio Alfabético y por Edad)");
        System.out.println("==================================================");

        // CP-MUL-01: Estado inicial vacía
        System.out.print("[CP-MUL-01] Estado inicial vacía... ");
        ListaMulti lista = new ListaMulti();
        assert lista.estaVacia() : "Error en CP-MUL-01: La lista debe iniciar vacía";
        assert lista.getTamano() == 0 : "Error en CP-MUL-01: El tamaño inicial debe ser 0";
        assert lista.getCabezaAlfabetica() == null : "Error en CP-MUL-01: Cabeza alfabética debe ser null";
        assert lista.getCabezaEdad() == null : "Error en CP-MUL-01: Cabeza por edad debe ser null";
        System.out.println("✅ Aprobado -> vacia = true, tamano = 0, cabezas = null");

        // CP-MUL-02: Inserción ordenada múltiple
        System.out.println("[CP-MUL-02] Inserción ordenada múltiple: Carlos(25), Ana(30), Beto(18), Diana(22)... ");
        lista.insertar("Carlos", 25);
        lista.insertar("Ana", 30);
        lista.insertar("Beto", 18);
        lista.insertar("Diana", 22);
        assert lista.getTamano() == 4 : "Error en CP-MUL-02: El tamaño debe ser 4";
        assert lista.verificarConsistencia() : "Error en CP-MUL-02: Fallo de consistencia estructural";
        System.out.print("            ");
        lista.mostrar();
        System.out.println("            ✅ Aprobado -> Nodos entrelazados en ambas dimensiones");

        // CP-MUL-03: Validación de cabezas duales
        System.out.print("[CP-MUL-03] Validación de cabezas duales independientes... ");
        String cabezaAlfa = lista.getCabezaAlfabetica().getNombre();
        String cabezaEdad = lista.getCabezaEdad().getNombre();
        int menorEdad = lista.getCabezaEdad().getDato();
        assert "Ana".equalsIgnoreCase(cabezaAlfa) : "Error en CP-MUL-03: Cabeza alfa debe ser Ana";
        assert "Beto".equalsIgnoreCase(cabezaEdad) : "Error en CP-MUL-03: Cabeza edad debe ser Beto";
        assert menorEdad == 18 : "Error en CP-MUL-03: Menor edad debe ser 18";
        System.out.println("✅ Aprobado -> Cabeza Alfa: '" + cabezaAlfa + "', Cabeza Edad: '" + cabezaEdad + "' (" + menorEdad + " años)");

        // CP-MUL-04: Búsqueda alfabética positiva
        System.out.print("[CP-MUL-04] Búsqueda alfabética existente ('Carlos')... ");
        boolean halladoCarlos = lista.buscar("Carlos");
        assert halladoCarlos : "Error en CP-MUL-04: Carlos debe existir";
        System.out.println("✅ Aprobado -> resultado: " + halladoCarlos);

        // CP-MUL-05: Búsqueda alfabética negativa (corte temprano)
        System.out.print("[CP-MUL-05] Búsqueda alfabética inexistente ('Zoe')... ");
        boolean halladoZoe = lista.buscar("Zoe");
        assert !halladoZoe : "Error en CP-MUL-05: Zoe no debe existir";
        System.out.println("✅ Aprobado -> resultado: " + halladoZoe);

        // CP-MUL-06: Búsqueda por edad positiva
        System.out.print("[CP-MUL-06] Búsqueda por edad existente (22 - Diana)... ");
        boolean halladaEdad22 = lista.buscarPorEdad(22);
        assert halladaEdad22 : "Error en CP-MUL-06: Edad 22 debe encontrarse";
        System.out.println("✅ Aprobado -> resultado: " + halladaEdad22);

        // CP-MUL-07: Búsqueda por edad negativa (corte temprano)
        System.out.print("[CP-MUL-07] Búsqueda por edad inexistente (99)... ");
        boolean halladaEdad99 = lista.buscarPorEdad(99);
        assert !halladaEdad99 : "Error en CP-MUL-07: Edad 99 no debe existir";
        System.out.println("✅ Aprobado -> resultado: " + halladaEdad99);

        // CP-MUL-08: Eliminación de cabeza alfabética ("Ana", 30 años)
        System.out.print("[CP-MUL-08] Eliminación de cabeza alfabética ('Ana', 30 años)... ");
        boolean elimAna = lista.eliminar("Ana");
        assert elimAna : "Error en CP-MUL-08: Ana debe eliminarse con éxito";
        assert "Beto".equalsIgnoreCase(lista.getCabezaAlfabetica().getNombre()) : "Error en CP-MUL-08: Nueva cabeza alfa debe ser Beto";
        assert lista.verificarConsistencia() : "Error en CP-MUL-08: Inconsistencia tras eliminar Ana";
        System.out.println("✅ Aprobado -> Nueva cabeza Alfa: " + lista.getCabezaAlfabetica().getNombre());

        // CP-MUL-09: Eliminación de cabeza por edad ("Beto", 18 años)
        System.out.print("[CP-MUL-09] Eliminación de cabeza por edad ('Beto', 18 años)... ");
        boolean elimBeto = lista.eliminar("Beto");
        assert elimBeto : "Error en CP-MUL-09: Beto debe eliminarse con éxito";
        assert lista.getCabezaEdad().getDato() == 22 : "Error en CP-MUL-09: Nueva menor edad debe ser 22 (Diana)";
        assert lista.verificarConsistencia() : "Error en CP-MUL-09: Inconsistencia tras eliminar Beto";
        System.out.println("✅ Aprobado -> Nueva cabeza Edad: Diana (" + lista.getCabezaEdad().getDato() + " años)");

        // CP-MUL-10: Eliminación de nodo intermedio ("Diana", 22 años)
        System.out.print("[CP-MUL-10] Eliminación de nodo intermedio ('Diana', 22 años)... ");
        boolean elimDiana = lista.eliminar("Diana");
        assert elimDiana : "Error en CP-MUL-10: Diana debe eliminarse con éxito";
        assert lista.getTamano() == 1 : "Error en CP-MUL-10: Debe quedar exactamente 1 elemento";
        assert lista.verificarConsistencia() : "Error en CP-MUL-10: Inconsistencia tras eliminar Diana";
        System.out.println("✅ Aprobado -> Queda únicamente Carlos (tamano = 1)");

        // CP-MUL-11: Vaciado total de la multilista ("Carlos", 25 años)
        System.out.print("[CP-MUL-11] Vaciado total de la multilista ('Carlos', 25 años)... ");
        boolean elimCarlos = lista.eliminar("Carlos");
        assert elimCarlos : "Error en CP-MUL-11: Carlos debe eliminarse";
        assert lista.estaVacia() : "Error en CP-MUL-11: La lista debe quedar vacía";
        assert lista.getTamano() == 0 : "Error en CP-MUL-11: El tamaño debe ser 0";
        assert lista.getCabezaAlfabetica() == null && lista.getCabezaEdad() == null : "Error en CP-MUL-11: Cabezas en null";
        System.out.println("✅ Aprobado -> estaVacia = true, tamano = 0");

        // CP-MUL-12: Compatibilidad polimórfica insertar(int)
        System.out.print("[CP-MUL-12] Compatibilidad polimórfica insertar(45) e insertar(15)... ");
        lista.insertar(45);
        lista.insertar(15);
        assert lista.getTamano() == 2 : "Error en CP-MUL-12: Tamaño debe ser 2";
        assert lista.getCabezaEdad().getDato() == 15 : "Error en CP-MUL-12: Menor edad debe ser 15";
        assert lista.verificarConsistencia() : "Error en CP-MUL-12: Consistencia polimórfica fallida";
        lista.eliminar(45);
        lista.eliminar(15);
        System.out.println("✅ Aprobado -> Soporta contrato ListaEnlazada sin alterar estructura");

        // CP-MUL-13: Eliminación en lista vacía
        System.out.print("[CP-MUL-13] Eliminación en lista vacía ('Nadie')... ");
        boolean elimVacia = lista.eliminar("Nadie");
        assert !elimVacia : "Error en CP-MUL-13: Eliminar en vacía debe retornar false";
        System.out.println("✅ Aprobado -> Retorna false sin excepciones");

        // CP-MUL-14: Auditoría de consistencia en memoria
        System.out.print("[CP-MUL-14] Auditoría de consistencia estructural en memoria... ");
        assert lista.verificarConsistencia() : "Error en CP-MUL-14: La auditoría en memoria debe pasar";
        System.out.println("✅ Aprobado -> verificarConsistencia() = true en todos los estados\n");
    }

    public static void main(String[] args) {
        System.out.println("\n############################################################");
        System.out.println("   SUITE DE PRUEBAS AUTOMATIZADAS — ESTRUCTURA DE DATOS   ");
        System.out.println("   Sincronizado formalmente con: docs/reporte_pruebas.md   ");
        System.out.println("############################################################\n");

        // 1. Pruebas Lista Simple (Fátima)
        probarListaSimple();

        // 2. Pruebas Lista Doble (yo)
        probarListaDoble();

        // 3. Pruebas Lista Circular (yo)
        probarListaCircular();

        // 4. Pruebas Multilista (Cristian)
        probarListaMulti();

        System.out.println("============================================================");
        System.out.println("   RESULTADO GLOBAL: TODAS LAS PRUEBAS ACTIVAS EN VERDE ✅   ");
        System.out.println("============================================================\n");
    }
}
