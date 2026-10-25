# Reporte de Pruebas — Módulo Listas Enlazadas

**Materia:** Estructura de Datos  
**Evaluación:** Segundo Parcial  
**Responsable de Reporte y Multilistas (Integrante 3):** Cristian  

---

## 1. Estructura General del Plan de Pruebas

El objetivo de este documento es registrar de manera sistemática los casos de prueba unitarios y de integración para cada una de las variantes de listas enlazadas que conforman el proyecto:
- **Lista Simple** (Responsable: Fátima)
- **Lista Doble** (Responsable: Karen)
- **Lista Circular** (Responsable: Karen)
- **Lista Múltiple (Multilista)** (Responsable: Cristian)

---

## 2. Matriz General de Casos de Prueba

| ID | Clase | Caso | Entrada | Resultado Esperado | Resultado Obtenido | Estado |
| :---: | :--- | :--- | :--- | :--- | :--- | :---: |
| **CP-SIM-01** | `ListaSimple` | Inserción en lista vacía | Insertar 10 | `cabeza -> 10 -> null`, `tamano = 1` | *(Pendiente Integrante 1)* | ⏳ Pendiente |
| **CP-SIM-02** | `ListaSimple` | Inserción al final múltiple | Insertar 20, 30 | `10 -> 20 -> 30 -> null`, `tamano = 3` | *(Pendiente Integrante 1)* | ⏳ Pendiente |
| **CP-SIM-03** | `ListaSimple` | Eliminación de cabeza | Eliminar 10 | `20 -> 30 -> null`, `tamano = 2`, retorna `true` | *(Pendiente Integrante 1)* | ⏳ Pendiente |
| **CP-DOB-01** | `ListaDoble` | Inserción bidireccional | Insertar 15, 25 | Enlaces `anterior` y `siguiente` consistentes | *(Pendiente Integrante 2)* | ⏳ Pendiente |
| **CP-DOB-02** | `ListaDoble` | Recorrido inverso | `mostrarInverso()` | Elementos impresos desde cola hasta cabeza | *(Pendiente Integrante 2)* | ⏳ Pendiente |
| **CP-CIR-01** | `ListaCircular`| Cierre de ciclo | Insertar 5, 8 | El último nodo apunta de regreso a cabeza | *(Pendiente Integrante 2)* | ⏳ Pendiente |
| **CP-CIR-02** | `ListaCircular`| Recorrido sin bucle infinito | `mostrar()` | Recorre exactamente `tamano` nodos y detiene | *(Pendiente Integrante 2)* | ⏳ Pendiente |
| **CP-MUL-01** | `ListaMulti` | Estado inicial vacía | `new ListaMulti()` | `estaVacia() = true`, `tamano = 0`, cabezas en `null` | `estaVacia() = true`, `tamano = 0`, cabezas en `null` | ✅ Aprobado |
| **CP-MUL-02** | `ListaMulti` | Inserción en lista vacía | `insertar(30)` | C1: `30 -> null`<br>C2: `30 -> null`<br>`tamano = 1`, consistencia `true` | C1: `30 -> null`<br>C2: `30 -> null`<br>`tamano = 1`, consistencia `true` | ✅ Aprobado |
| **CP-MUL-03** | `ListaMulti` | Inserción: Cabeza en C2 y Cola en C1 | `insertar(10)` tras el 30 | C1 (Cronológico): `30 -> 10 -> null`<br>C2 (Ascendente): `10 -> 30 -> null` | C1: `30 -> 10 -> null`<br>C2: `10 -> 30 -> null` | ✅ Aprobado |
| **CP-MUL-04** | `ListaMulti` | Inserción: Cola en ambos criterios | `insertar(50)` | C1: `30 -> 10 -> 50 -> null`<br>C2: `10 -> 30 -> 50 -> null` | C1: `30 -> 10 -> 50 -> null`<br>C2: `10 -> 30 -> 50 -> null` | ✅ Aprobado |
| **CP-MUL-05** | `ListaMulti` | Inserción intermedia en C2 y cola en C1 | `insertar(20)`, `insertar(40)` | C1: `30 -> 10 -> 50 -> 20 -> 40 -> null`<br>C2: `10 -> 20 -> 30 -> 40 -> 50 -> null` | C1: `30 -> 10 -> 50 -> 20 -> 40 -> null`<br>C2: `10 -> 20 -> 30 -> 40 -> 50 -> null` | ✅ Aprobado |
| **CP-MUL-06** | `ListaMulti` | Búsqueda exitosa en ambos criterios | `buscar(20)` y `buscarCriterioSecundario(20)` | Ambos retornan `true` | C1: `true`, C2: `true` | ✅ Aprobado |
| **CP-MUL-07** | `ListaMulti` | Búsqueda elemento inexistente | `buscar(99)` y `buscarCriterioSecundario(99)` | Ambos retornan `false` (C2 corta tempranamente) | C1: `false`, C2: `false` | ✅ Aprobado |
| **CP-MUL-08** | `ListaMulti` | Eliminación de cabeza en C1 (nodo intermedio en C2) | `eliminar(30)` | Retorna `true`. `30` desenlazado en C1 y C2.<br>C1: `10 -> 50 -> 20 -> 40`<br>C2: `10 -> 20 -> 40 -> 50` | Retorna `true`. Nodos desenlazados en ambos caminos.<br>Consistencia: `true` | ✅ Aprobado |
| **CP-MUL-09** | `ListaMulti` | Eliminación de cabeza en C2 (nodo intermedio en C1) | `eliminar(10)` | Retorna `true`. `cabezaSecundaria` avanza a `20`.<br>C1: `50 -> 20 -> 40`<br>C2: `20 -> 40 -> 50` | Retorna `true`. Nueva cabeza C2 correcta.<br>Consistencia: `true` | ✅ Aprobado |
| **CP-MUL-10** | `ListaMulti` | Eliminación de cola en C2 | `eliminar(50)` | Retorna `true`. `50` desenlazado en ambos.<br>C1: `20 -> 40`<br>C2: `20 -> 40` | Retorna `true`. Nodos sincronizados.<br>Consistencia: `true` | ✅ Aprobado |
| **CP-MUL-11** | `ListaMulti` | Eliminación de nodo intermedio | `eliminar(20)` | Retorna `true`. Queda solo `40`.<br>C1: `40 -> null`<br>C2: `40 -> null` | Retorna `true`. C1 y C2 apuntan al mismo nodo único.<br>Consistencia: `true` | ✅ Aprobado |
| **CP-MUL-12** | `ListaMulti` | Eliminación del último nodo restante | `eliminar(40)` | Retorna `true`. Lista queda vacía (`cabeza = null`, `cabezaSecundaria = null`, `tamano = 0`) | Retorna `true`. `estaVacia() = true`, `tamano = 0` | ✅ Aprobado |
| **CP-MUL-13** | `ListaMulti` | Eliminación en lista vacía | `eliminar(40)` en vacía | Retorna `false` sin excepciones | Retorna `false` | ✅ Aprobado |
| **CP-MUL-14** | `ListaMulti` | Auditoría de consistencia y cardinalidad | Método `verificarConsistencia()` | `true` en todas las fases de inserción y eliminación | `true` verificado en todos los estados intermedios | ✅ Aprobado |

---

## 3. Criterios de Aceptación para la Multilista

1. **Persistencia dual en un solo objeto en memoria:** Cada inserción crea un único objeto `NodoMulti` referenciado concurrentemente por dos punteros independientes (`siguiente` y `siguienteSecundario`).
2. **Desvinculación atómica en eliminación:** Al eliminar cualquier elemento, se actualizan los punteros de predecesores tanto en la cadena primaria como en la secundaria, impidiendo pérdidas de enlaces ("fugas de nodos") o desincronización de tamaños.
3. **Comportamiento polimórfico:** `ListaMulti` puede ser tratada como `ListaEnlazada` en tiempo de ejecución, invocando `insertar(dato)`, `eliminar(dato)`, `buscar(dato)` y `mostrar()` sin romper el contrato de la superclase.
