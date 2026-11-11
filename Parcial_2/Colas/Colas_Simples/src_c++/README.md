# ⚡ Colas Simples (FIFO) en C++ (`src_c++`)
**Segundo Parcial — Estructura de Datos (UMB)**

Implementación en **C++** del TDA **Cola Simple (`FIFO`)** bajo el esquema de **cabeceras modulares (`.h`) y un único punto de entrada (`main.cpp`)**:
* `ColaSimpleArreglo.h`: Implementación sobre arreglo en memoria contigua.
* `ColaSimpleNodos.h`: Implementación dinámica mediante nodos enlazados (`NodoColaSimple`) en el *Heap*.
* `main.cpp`: Menú interactivo unificado que permite elegir entre la cola por arreglo o por nodos.

---

## 🛠️ Compilación y Ejecución

```bash
cd Parcial_2/Colas/Colas_Simples/src_c++
g++ -std=c++11 -Wall main.cpp -o main.exe
.\main.exe
```
