# 🔄 Colas Circulares Multilenguaje
**Segundo Parcial — Estructura de Datos (UMB)**

Implementación del Tipo de Dato Abstracto (TDA) **Cola Circular (Ring Buffer / Circular Queue)** mediante **Arreglos con aritmética modular** y **Nodos Enlazados Circulares**.

---

## 📂 Organización por Lenguaje

| Lenguaje | Directorio | Variantes Incluidas | Estado |
| :---: | :---: | :--- | :---: |
| **C++** | [`src_c++/`](src_c++/) | `ColaCircularArreglo` (`.h`/`.cpp`) y `ColaCircularNodos` (`.h`/`.cpp`) | ✅ Completo |
| **Java** | [`src_java/`](src_java/) | `Colacirculaarreglo.java` (con interfaz) y `Colacircularnodo.java` (con interfaz) | ✅ Completo |
| **Python** | [`src_python/`](src_python/) | `Colas_Circulares.py` (implementación sobre arreglo y menú interactivo) | ⚠️ Falta variante con nodos |

---

## 🚀 Guías de Compilación y Ejecución

* **C++ (`src_c++/`):**
  ```bash
  cd Parcial_2/Colas/Colas_Circulares/src_c++
  g++ -Wall colaCircularArreglo.cpp -o cola_arreglo
  g++ -Wall colaCircularNodos.cpp -o cola_nodos
  ```
* **Java (`src_java/`):**
  ```bash
  cd Parcial_2/Colas/Colas_Circulares/src_java
  javac Colacirculaarreglo.java Colacircularnodo.java
  java Colacirculaarreglo
  java Colacircularnodo
  ```
* **Python (`src_python/`):**
  ```bash
  cd Parcial_2/Colas/Colas_Circulares/src_python
  python Colas_Circulares.py
  ```
