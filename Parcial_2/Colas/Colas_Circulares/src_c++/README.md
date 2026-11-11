# ⚡ Colas Circulares en C++ (`src_c++`)
**Origen:** Aportación de otros equipos (Compañeros de grupo)

Implementación en **C++** del TDA **Cola Circular** aplicada a un sistema de atención a clientes bajo dos variantes:
* `colaCircularArreglo.h` y `colaCircularArreglo.cpp`: Cola circular sobre arreglo fijo con aritmética modular (`% 5`).
* `colaCircularNodos.h` y `colaCircularNodos.cpp`: Cola circular dinámica con nodos enlazados donde `final->siguiente` apunta a `frente`.

---

## 🛠️ Compilación y Ejecución

```bash
cd Parcial_2/Colas/Colas_Circulares/src_c++

# 1. Versión con Arreglo
g++ -Wall colaCircularArreglo.cpp -o cola_arreglo.exe
.\cola_arreglo.exe

# 2. Versión con Nodos
g++ -Wall colaCircularNodos.cpp -o cola_nodos.exe
.\cola_nodos.exe
```
