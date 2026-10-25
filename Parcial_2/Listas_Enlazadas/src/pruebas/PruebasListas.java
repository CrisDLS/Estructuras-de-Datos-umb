package pruebas;

import listas.ListaMulti;

/**
 * Banco de pruebas automatizadas para verificar las implementaciones de listas.
 * 
 * @author Cristian (Integrante 3)
 */
public class PruebasListas {

    public static void probarListaMulti() {
        System.out.println("==================================================");
        System.out.println("   EJECUTANDO BANCO DE PRUEBAS: LISTAMULTI       ");
        System.out.println("==================================================");

        ListaMulti lista = new ListaMulti();
        assert lista.estaVacia() : "Error: La lista recién creada debe estar vacía";

        // 1. Inserción de prueba
        int[] datos = {30, 10, 50, 20, 40};
        System.out.println("\n--- 1. Insertando datos: {30, 10, 50, 20, 40} ---");
        for (int d : datos) {
            lista.insertar(d);
        }
        lista.mostrar();
        assert lista.getTamano() == 5 : "Error: El tamaño debe ser 5";
        assert lista.verificarConsistencia() : "Error: Fallo de consistencia tras inserciones";

        // 2. Pruebas de Búsqueda
        System.out.println("\n--- 2. Pruebas de Búsqueda ---");
        System.out.println("Buscar 20 en Criterio Primario: " + lista.buscar(20));
        System.out.println("Buscar 20 en Criterio Secundario: " + lista.buscarCriterioSecundario(20));
        System.out.println("Buscar 99 (inexistente): " + lista.buscarCriterioSecundario(99));
        assert lista.buscar(20) : "Error: El elemento 20 debe existir";
        assert !lista.buscar(99) : "Error: El elemento 99 no debe existir";

        // 3. Pruebas de Eliminación
        System.out.println("\n--- 3. Eliminación de Cabeza Primaria (30) ---");
        lista.eliminar(30);
        lista.mostrar();
        assert lista.verificarConsistencia() : "Error: Inconsistencia al eliminar cabeza primaria";

        System.out.println("\n--- 4. Eliminación de Cabeza Secundaria (10) ---");
        lista.eliminar(10);
        lista.mostrar();
        assert lista.verificarConsistencia() : "Error: Inconsistencia al eliminar cabeza secundaria";

        System.out.println("\n--- 5. Eliminación de Cola (50) ---");
        lista.eliminar(50);
        lista.mostrar();
        assert lista.verificarConsistencia() : "Error: Inconsistencia al eliminar cola";

        System.out.println("\n--- 6. Eliminación Intermedia (20) ---");
        lista.eliminar(20);
        lista.mostrar();
        assert lista.verificarConsistencia() : "Error: Inconsistencia al eliminar nodo intermedio";

        System.out.println("\n--- 7. Eliminación Último Elemento (40) ---");
        lista.eliminar(40);
        lista.mostrar();
        assert lista.estaVacia() : "Error: La lista debe quedar vacía";
        assert lista.verificarConsistencia() : "Error: Inconsistencia en lista vacía";

        System.out.println("\n>>> TODAS LAS PRUEBAS DE LISTAMULTI PASARON CON ÉXITO <<<\n");
    }

    public static void main(String[] args) {
        probarListaMulti();
    }
}
