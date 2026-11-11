# ⚡ Colas de Prioridad Multilenguaje
**Segundo Parcial — Estructura de Datos (UMB)**

Implementación comparativa y modular del Tipo de Dato Abstracto (TDA) **Cola de Prioridad (Priority Queue)** bajo tres enfoques algorítmicos distintos y desarrollado en **tres lenguajes de programación**.

---

## 👥 Equipo y Asignación de Lenguajes

| Lenguaje | Integrante Responsable | Módulos e Implementaciones | Directorio |
| :---: | :--- | :--- | :---: |
| **C++** | Cristian | Nodos, Arreglo, Binary Heap y consola interactiva | [`src_c++/`](src_c++/) |
| **Python** | Fatima | Clase ColaPrioridad y menú interactivo de consola | [`src_python/`](src_python/) |
| **Java** | Karen | Nodos, Arreglo, simulador por consola y visual | [`src_java/`](src_java/) |

---

## 🎯 Modelos Algorítmicos Implementados

1. **Nodos Enlazados (Lista Ordenada):** Inserción ordenada según prioridad manteniendo el elemento de mayor relevancia al frente ($O(n)$ en inserción, $O(1)$ en extracción).
2. **Arreglo Dinámico:** Manejo en memoria contigua con corrimiento o búsqueda lineal de máxima prioridad.
3. **Montículo Binario (Binary Max/Min Heap):** Implementación sobre arreglo que garantiza balance estructural completo y alta eficiencia algorítmica ($O(\log n)$ en inserción y extracción).

---

## 🌐 Simulador Web Interactivo
Este proyecto incluye un simulador visual en navegador:
* Archivo: [`Simulador de cola de prioridad.html`](Simulador%20de%20cola%20de%20prioridad.html)
* Permite observar en tiempo real la inserción, ordenamiento por prioridad y extracción gráfica de elementos.

---

## 🚀 Guías de Compilación y Ejecución por Lenguaje

* **C++:** Revisa las instrucciones en [`src_c++/README.md`](src_c++/README.md).
* **Python:** Revisa las instrucciones en [`src_python/README.md`](src_python/README.md).
* **Java:** Revisa las instrucciones en [`src_java/README.md`](src_java/README.md).
