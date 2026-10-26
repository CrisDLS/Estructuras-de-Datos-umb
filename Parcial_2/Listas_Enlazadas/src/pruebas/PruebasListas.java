package pruebas;

import listas.ListaMulti;

/**
 * Banco de pruebas automatizadas para verificar las implementaciones de listas.
 * <p>
 * Incluye pruebas exhaustivas para {@link ListaMulti} modelando registros de Personas
 * con doble criterio simultáneo (Alfabético por Nombre y Numérico por Edad).
 * </p>
 */
public class PruebasListas {

    public static void probarListaMulti() {
        System.out.println("==========================================================");
        System.out.println("   BANCO DE PRUEBAS: LISTAMULTI (PERSONAS: NOMBRE + EDAD) ");
        System.out.println("==========================================================");

        ListaMulti lista = new ListaMulti();

        // 1. Verificación inicial de lista vacía
        System.out.println("\n--- 1. Verificación Inicial de Lista Vacía ---");
        assert lista.estaVacia() : "Error: La lista recién instanciada debe estar vacía";
        assert lista.getTamano() == 0 : "Error: El tamaño inicial debe ser 0";
        assert lista.verificarConsistencia() : "Error: Una lista vacía debe ser consistente";
        System.out.println("Estado inicial: Vacía = " + lista.estaVacia() + ", Tamaño = " + lista.getTamano());

        // 2. Inserción de personas de prueba
        System.out.println("\n--- 2. Inserción de Personas ---");
        System.out.println("Insertando: Carlos (25), Ana (30), Beto (18), Diana (22)...");
        lista.insertar("Carlos", 25);
        lista.insertar("Ana", 30);
        lista.insertar("Beto", 18);
        lista.insertar("Diana", 22);

        lista.mostrar();

        assert lista.getTamano() == 4 : "Error: El tamaño debe ser 4 tras 4 inserciones";
        assert lista.verificarConsistencia() : "Error: Fallo de consistencia tras inserciones";

        // 3. Validación de Cabezas según Criterio
        System.out.println("\n--- 3. Validación de Cabezas Duales ---");
        String cabezaAlfa = lista.getCabezaAlfabetica().getNombre();
        String cabezaEdad = lista.getCabezaEdad().getNombre();
        int menorEdad = lista.getCabezaEdad().getEdad();

        System.out.println("Cabeza Alfabética esperada: 'Ana'  | Obtenida: '" + cabezaAlfa + "'");
        System.out.println("Cabeza por Edad esperada:   'Beto' | Obtenida: '" + cabezaEdad + "' (" + menorEdad + " años)");

        assert "Ana".equalsIgnoreCase(cabezaAlfa) : "Error: La cabeza alfabética debe ser 'Ana'";
        assert "Beto".equalsIgnoreCase(cabezaEdad) : "Error: La cabeza por edad debe ser 'Beto'";
        assert menorEdad == 18 : "Error: La menor edad debe ser 18";

        // 4. Búsquedas Positivas y Negativas
        System.out.println("\n--- 4. Pruebas de Búsqueda ---");
        boolean bCarlos = lista.buscar("Carlos");
        boolean bZoe = lista.buscar("Zoe");
        boolean bEdad22 = lista.buscarPorEdad(22);
        boolean bEdad99 = lista.buscarPorEdad(99);

        System.out.println("Buscar 'Carlos' (Alfabético): " + bCarlos + " (Esperado: true)");
        System.out.println("Buscar 'Zoe'    (Alfabético): " + bZoe + " (Esperado: false)");
        System.out.println("Buscar edad 22  (Por Edad):   " + bEdad22 + " (Esperado: true)");
        System.out.println("Buscar edad 99  (Por Edad):   " + bEdad99 + " (Esperado: false)");

        assert bCarlos : "Error: 'Carlos' debe ser encontrado";
        assert !bZoe : "Error: 'Zoe' no debe ser encontrada";
        assert bEdad22 : "Error: La edad 22 ('Diana') debe ser encontrada";
        assert !bEdad99 : "Error: La edad 99 no debe ser encontrada";

        // 5. Eliminación de Cabeza Alfabética ("Ana")
        System.out.println("\n--- 5. Eliminación de Cabeza Alfabética ('Ana') ---");
        boolean elAna = lista.eliminar("Ana");
        System.out.println("Eliminar 'Ana': " + elAna);
        lista.mostrar();
        assert elAna : "Error: 'Ana' debió ser eliminada";
        assert "Beto".equalsIgnoreCase(lista.getCabezaAlfabetica().getNombre()) :
                "Error: Tras eliminar a 'Ana', la nueva cabeza alfabética debe ser 'Beto'";
        assert lista.verificarConsistencia() : "Error: Inconsistencia estructural tras eliminar 'Ana'";

        // 6. Eliminación de Cabeza por Edad ("Beto")
        System.out.println("\n--- 6. Eliminación de Cabeza por Edad ('Beto') ---");
        boolean elBeto = lista.eliminar("Beto");
        System.out.println("Eliminar 'Beto': " + elBeto);
        lista.mostrar();
        assert elBeto : "Error: 'Beto' debió ser eliminado";
        assert "Diana".equalsIgnoreCase(lista.getCabezaEdad().getNombre()) :
                "Error: Tras eliminar a 'Beto', la nueva cabeza por edad debe ser 'Diana'";
        assert lista.getCabezaEdad().getEdad() == 22 : "Error: La nueva menor edad debe ser 22";
        assert lista.verificarConsistencia() : "Error: Inconsistencia estructural tras eliminar 'Beto'";

        // 7. Eliminación de Nodo Intermedio ("Diana")
        System.out.println("\n--- 7. Eliminación de Nodo Intermedio ('Diana') ---");
        boolean elDiana = lista.eliminar("Diana");
        System.out.println("Eliminar 'Diana': " + elDiana);
        lista.mostrar();
        assert elDiana : "Error: 'Diana' debió ser eliminada";
        assert lista.getTamano() == 1 : "Error: Debe quedar exactamente 1 elemento";
        assert lista.verificarConsistencia() : "Error: Inconsistencia tras eliminar 'Diana'";

        // 8. Vaciado Total (Eliminar "Carlos")
        System.out.println("\n--- 8. Eliminación del Último Elemento ('Carlos') ---");
        boolean elCarlos = lista.eliminar("Carlos");
        System.out.println("Eliminar 'Carlos': " + elCarlos);
        lista.mostrar();
        assert elCarlos : "Error: 'Carlos' debió ser eliminado";
        assert lista.estaVacia() : "Error: La lista debe quedar completamente vacía";
        assert lista.getTamano() == 0 : "Error: El tamaño debe ser 0";
        assert lista.verificarConsistencia() : "Error: La lista vacía debe ser consistente";

        // 9. Comprobación de Compatibilidad Polimórfica (insertar(int))
        System.out.println("\n--- 9. Comprobación de Polimorfismo con insertar(int) ---");
        lista.insertar(45);
        lista.insertar(15);
        lista.mostrar();
        assert lista.getTamano() == 2 : "Error: Deben existir 2 elementos insertados polimórficamente";
        assert lista.verificarConsistencia() : "Error: Fallo de consistencia en inserción polimórfica";
        assert lista.eliminar(45) : "Error: Debe permitir eliminar por edad polimórficamente";
        assert lista.eliminar(15) : "Error: Debe permitir eliminar el último elemento polimórficamente";
        assert lista.estaVacia() : "Error: La lista debe quedar vacía";

        System.out.println("\n>>> TODAS LAS PRUEBAS DE LISTAMULTI PASARON EXITOSAMENTE <<<\n");
    }

    public static void main(String[] args) {
        probarListaMulti();
    }
}
