# Reporte de Pruebas — Módulo Listas Enlazadas

**Materia:** Estructura de Datos  
**Evaluación:** Segundo Parcial  
**Responsable de Reporte y Multilistas (Integrante 3):** Cristian  

---

## 1. Estructura General del Plan de Pruebas

El objetivo de este documento es registrar de manera sistemática los casos de prueba unitarios y de integración para cada una de las variantes de listas enlazadas que conforman el proyecto:
- **Lista Simple** (Responsable: Fátima) — **[Implementado y Validado ✅]**
- **Lista Doble** (Responsable: Karen) — *[Pendiente de entrega por Integrante 2 ⏳]*
- **Lista Circular** (Responsable: Karen) — *[Pendiente de entrega por Integrante 2 ⏳]*
- **Lista Múltiple (Multilista de Personas: Nombre + Edad)** (Responsable: Cristian) — **[Implementado y Validado ✅]**

---

## 2. Matriz General de Casos de Prueba

| ID | Clase | Caso de Prueba | Entrada / Operación | Resultado Esperado | Resultado Obtenido | Estado |
| :---: | :--- | :--- | :--- | :--- | :--- | :---: |
| **CP-SIM-01** | `ListaSimple` | Inserción en lista vacía | `insertar(10)` | `cabeza -> 10 -> null`, `tamano = 1` | `10 -> null`, `tamano = 1`, `estaVacia() = false` | ✅ Aprobado |
| **CP-SIM-02** | `ListaSimple` | Inserción al final múltiple | `insertar(20)`, `insertar(30)` | `10 -> 20 -> 30 -> null`, `tamano = 3` | `10 -> 20 -> 30 -> null`, `tamano = 3` | ✅ Aprobado |
| **CP-SIM-03** | `ListaSimple` | Inserción al inicio (O(1)) | `insertarAlInicio(5)` | `5 -> 10 -> 20 -> 30 -> null`, `tamano = 4` | `5 -> 10 -> 20 -> 30 -> null`, `tamano = 4` | ✅ Aprobado |
| **CP-SIM-04** | `ListaSimple` | Búsqueda elemento existente | `buscar(20)` | Retorna `true` | `true` | ✅ Aprobado |
| **CP-SIM-05** | `ListaSimple` | Búsqueda elemento inexistente | `buscar(99)` | Retorna `false` | `false` | ✅ Aprobado |
| **CP-SIM-06** | `ListaSimple` | Eliminación de cabeza | `eliminar(5)` | Retorna `true`, nueva cabeza es `10`, `tamano = 3` | Retorna `true`, `10 -> 20 -> 30 -> null`, `tamano = 3` | ✅ Aprobado |
| **CP-SIM-07** | `ListaSimple` | Eliminación intermedia | `eliminar(20)` | Retorna `true`, `10 -> 30 -> null`, `tamano = 2` | Retorna `true`, `10 -> 30 -> null`, `tamano = 2` | ✅ Aprobado |
| **CP-SIM-08** | `ListaSimple` | Eliminación inexistente | `eliminar(99)` | Retorna `false`, lista inalterada | Retorna `false`, `tamano = 2` | ✅ Aprobado |
| **CP-SIM-09** | `ListaSimple` | Vaciado total de la lista | `eliminar(10)`, `eliminar(30)` | Retorna `true`, `estaVacia() = true`, `tamano = 0` | Retorna `true`, `estaVacia() = true`, `tamano = 0` | ✅ Aprobado |
| **CP-DOB-01** | `ListaDoble` | Inserción bidireccional | Insertar 15, 25 | Enlaces `anterior` y `siguiente` consistentes | *(Pendiente Integrante 2)* | ⏳ Pendiente |
| **CP-DOB-02** | `ListaDoble` | Recorrido inverso | `mostrarInverso()` | Elementos impresos desde cola hasta cabeza | *(Pendiente Integrante 2)* | ⏳ Pendiente |
| **CP-CIR-01** | `ListaCircular`| Cierre de ciclo | Insertar 5, 8 | El último nodo apunta de regreso a cabeza | *(Pendiente Integrante 2)* | ⏳ Pendiente |
| **CP-CIR-02** | `ListaCircular`| Recorrido sin bucle infinito | `mostrar()` | Recorre exactamente `tamano` nodos y detiene | *(Pendiente Integrante 2)* | ⏳ Pendiente |
| **CP-MUL-01** | `ListaMulti` | Estado inicial vacía | `new ListaMulti()` | `estaVacia() = true`, `tamano = 0`, cabezas en `null` | `estaVacia() = true`, `tamano = 0`, cabezas en `null` | ✅ Aprobado |
| **CP-MUL-02** | `ListaMulti` | Inserción ordenada múltiple | ("Carlos", 25), ("Ana", 30), ("Beto", 18), ("Diana", 22) | C1 (Alfa): Ana(30) -> Beto(18) -> Carlos(25) -> Diana(22)<br>C2 (Edad): Beto(18) -> Diana(22) -> Carlos(25) -> Ana(30) | Nodos cruzados en ambas dimensiones con `tamano = 4` | ✅ Aprobado |
| **CP-MUL-03** | `ListaMulti` | Validación de cabezas duales | Comprobar cabezas tras inserción | Cabeza Alfabética: "Ana"<br>Cabeza por Edad: "Beto" (18 años) | Cabeza Alfa: "Ana"<br>Cabeza Edad: "Beto" (18) | ✅ Aprobado |
| **CP-MUL-04** | `ListaMulti` | Búsqueda alfabética positiva | `buscar("Carlos")` | Retorna `true` | `true` | ✅ Aprobado |
| **CP-MUL-05** | `ListaMulti` | Búsqueda alfabética negativa | `buscar("Zoe")` | Retorna `false` (corte temprano alfabético) | `false` | ✅ Aprobado |
| **CP-MUL-06** | `ListaMulti` | Búsqueda por edad positiva | `buscarPorEdad(22)` | Retorna `true` (encuentra a Diana) | `true` | ✅ Aprobado |
| **CP-MUL-07** | `ListaMulti` | Búsqueda por edad negativa | `buscarPorEdad(99)` | Retorna `false` (corte temprano por edad) | `false` | ✅ Aprobado |
| **CP-MUL-08** | `ListaMulti` | Eliminación de cabeza alfabética | `eliminar("Ana")` | Retorna `true`. Nueva cabeza alfabética es "Beto". "Ana" eliminada de ambas dimensiones. | Retorna `true`. Nueva cabeza Alfa: "Beto". Consistencia: `true` | ✅ Aprobado |
| **CP-MUL-09** | `ListaMulti` | Eliminación de cabeza por edad | `eliminar("Beto")` | Retorna `true`. Nueva cabeza por edad es "Diana" (22). Desenlazado atómico. | Retorna `true`. Nueva cabeza Edad: "Diana" (22). Consistencia: `true` | ✅ Aprobado |
| **CP-MUL-10** | `ListaMulti` | Eliminación de nodo intermedio | `eliminar("Diana")` | Retorna `true`. Queda únicamente "Carlos" (25) en ambas cadenas. `tamano = 1`. | Retorna `true`. Queda "Carlos" (25). Consistencia: `true` | ✅ Aprobado |
| **CP-MUL-11** | `ListaMulti` | Vaciado total de la multilista | `eliminar("Carlos")` | Retorna `true`. `estaVacia() = true`, `tamano = 0`, cabezas en `null`. | Retorna `true`. Lista queda completamente vacía. | ✅ Aprobado |
| **CP-MUL-12** | `ListaMulti` | Compatibilidad polimórfica `insertar(int)` | `insertar(45)`, `insertar(15)` | Crea "Item-45" e "Item-15" ordenados por nombre y edad. | `tamano = 2`. Inserción y eliminación polimórfica exitosa. | ✅ Aprobado |
| **CP-MUL-13** | `ListaMulti` | Eliminación en lista vacía | `eliminar("Nadie")` | Retorna `false` sin excepciones | Retorna `false` | ✅ Aprobado |
| **CP-MUL-14** | `ListaMulti` | Auditoría de consistencia en memoria | `verificarConsistencia()` | `true` en todos los estados (nodos compartidos, cardinalidad y sumas iguales). | `true` verificado tras cada operación | ✅ Aprobado |

---

## 3. Criterios de Aceptación para la Multilista

1. **Persistencia dual en un solo objeto en memoria:** Cada inserción crea un único objeto `NodoMulti` referenciado concurrentemente por dos punteros independientes (`siguiente` para orden alfabético y `siguienteEdad` para orden numérico de edad).
2. **Desvinculación atómica en eliminación:** Al eliminar una persona por nombre o edad, se actualizan los punteros de predecesores tanto en la cadena alfabética como en la de edades, impidiendo pérdidas de enlaces ("fugas de nodos") o desincronización de tamaños.
3. **Comportamiento polimórfico:** `ListaMulti` implementa el contrato de `ListaEnlazada` (`insertar(int)` y `eliminar(int)`) sin romper el menú ni los consumidores polimórficos de la aplicación.
