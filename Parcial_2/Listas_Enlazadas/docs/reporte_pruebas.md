# Reporte de Pruebas — Módulo Listas Enlazadas

**Materia:** Estructura de Datos  
**Evaluación:** Segundo Parcial  
**Proyecto:** Implementación Integral de Listas Enlazadas (Simple, Doble, Circular y Multilista)

---

## 1. Estructura General del Plan de Pruebas

El objetivo de este documento es registrar de manera sistemática los casos de prueba unitarios y de integración para cada una de las variantes de listas enlazadas que conforman el proyecto:
- **Lista Simple** — **[Implementado y Validado ✅]**
- **Lista Doble** — **[Implementado y Validado ✅]**
- **Lista Circular** — **[Implementado y Validado ✅]**
- **Lista Múltiple (Multilista de Personas: Nombre + Edad)** — **[Implementado y Validado ✅]**

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
| **CP-DOB-01** | `ListaDoble` | Inserción en lista vacía | `insertar(15)` | `cabeza <-> 15 <-> null`, `tamano = 1` | `15 <-> null`, `tamano = 1`, `estaVacia() = false` | ✅ Aprobado |
| **CP-DOB-02** | `ListaDoble` | Inserción múltiple al final | `insertar(25)`, `insertar(35)` | `15 <-> 25 <-> 35 <-> null`, `tamano = 3` | `15 <-> 25 <-> 35 <-> null`, `tamano = 3` | ✅ Aprobado |
| **CP-DOB-03** | `ListaDoble` | Inserción al inicio (O(1)) | `insertarAlInicio(5)` | `5 <-> 15 <-> 25 <-> 35 <-> null`, `tamano = 4` | `5 <-> 15 <-> 25 <-> 35 <-> null`, `tamano = 4` | ✅ Aprobado |
| **CP-DOB-04** | `ListaDoble` | Recorrido hacia adelante | `mostrar()` | Enlaces `siguiente` consistentes | `5 -> 15 -> 25 -> 35 -> null` | ✅ Aprobado |
| **CP-DOB-05** | `ListaDoble` | Recorrido inverso bidireccional | `mostrarInverso()` | Elementos impresos desde cola hasta cabeza usando `anterior` | `null <- 35 <-> 25 <-> 15 <-> 5 <-> [cabeza]` | ✅ Aprobado |
| **CP-DOB-06** | `ListaDoble` | Búsqueda elemento existente | `buscar(25)` | Retorna `true` | `true` | ✅ Aprobado |
| **CP-DOB-07** | `ListaDoble` | Eliminación al inicio (O(1)) | `eliminarAlInicio()` | Remueve 5, nueva cabeza es 15, `tamano = 3` | `tamano = 3`, cabeza = 15, `anterior` en `null` | ✅ Aprobado |
| **CP-DOB-08** | `ListaDoble` | Eliminación al final (O(1)) | `eliminarAlFinal()` | Remueve 35, nueva cola es 25, `tamano = 2` | `tamano = 2`, cola = 25, `siguiente` en `null` | ✅ Aprobado |
| **CP-DOB-09** | `ListaDoble` | Vaciado total de la lista | `limpiar()` | `estaVacia() = true`, `tamano = 0`, `cabeza = cola = null` | Retorna `estaVacia() = true`, `tamano = 0` | ✅ Aprobado |
| **CP-CIR-01** | `ListaCircular`| Cierre de ciclo circular | `insertar(5)`, `insertar(8)` | El último nodo apunta de regreso a cabeza, `tamano = 2` | Ciclo cerrado verificado, `tamano = 2` | ✅ Aprobado |
| **CP-CIR-02** | `ListaCircular`| Recorrido sin bucle infinito | `mostrar()` | Recorre exactamente `tamano` nodos y detiene | `5 -> 8 -> (cabeza)` | ✅ Aprobado |
| **CP-CIR-03** | `ListaCircular`| Inserción al inicio circular | `insertarAlInicio(1)` | Nueva cabeza es 1, último nodo re-enlazado a 1 | `1 -> 5 -> 8 -> (cabeza)`, `tamano = 3` | ✅ Aprobado |
| **CP-CIR-04** | `ListaCircular`| Inserción múltiple al final | `insertar(10)`, `insertar(15)` | `1 -> 5 -> 8 -> 10 -> 15 -> (cabeza)`, `tamano = 5` | `tamano = 5`, ciclo cerrado mantenido | ✅ Aprobado |
| **CP-CIR-05** | `ListaCircular`| Búsqueda existente en ciclo | `buscar(8)` | Retorna `true` | `true` | ✅ Aprobado |
| **CP-CIR-06** | `ListaCircular`| Búsqueda inexistente en ciclo | `buscar(99)` | Retorna `false` sin bucle infinito | `false` | ✅ Aprobado |
| **CP-CIR-07** | `ListaCircular`| Eliminación al inicio | `eliminarAlInicio()` | Remueve cabeza 1, última apunta a 5, `tamano = 4` | Nueva cabeza 5, ciclo intacto, `tamano = 4` | ✅ Aprobado |
| **CP-CIR-08** | `ListaCircular`| Eliminación al final | `eliminarAlFinal()` | Remueve 15, penúltimo (10) apunta a 5, `tamano = 3` | Último nodo 10 apunta a 5, `tamano = 3` | ✅ Aprobado |
| **CP-CIR-09** | `ListaCircular`| Vaciado total de la lista | `limpiar()` | `estaVacia() = true`, `tamano = 0`, `cabeza = null` | Retorna `estaVacia() = true`, `tamano = 0` | ✅ Aprobado |
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

## 3. Criterios de Aceptación Arquitectónicos

1. **Herencia y Polimorfismo:** Todas las listas (`ListaSimple`, `ListaDoble`, `ListaCircular` y `ListaMulti`) extienden de `ListaEnlazada`, permitiendo su manipulación uniforme mediante métodos polimórficos (`insertar(int)`, `eliminar(int)`, `mostrar()`, `buscar(int)` y `limpiar()`).
2. **Integridad de Punteros en Lista Doble:** Los punteros `siguiente` y `anterior` se sincronizan bidireccionalmente en inserciones y eliminaciones, tanto en extremos como en posiciones intermedias.
3. **Cierre de Ciclo en Lista Circular:** La lista circular garantiza que el puntero `siguiente` del último elemento referencia a la `cabeza`, sobreescribiendo los métodos de recorrido y búsqueda para evitar bucles infinitos.
4. **Persistencia dual en Multilista:** Cada inserción crea un único objeto `NodoMulti` referenciado concurrentemente por dos punteros independientes (`siguiente` para orden alfabético y `siguienteEdad` para orden numérico de edad) con desvinculación atómica durante eliminaciones.
