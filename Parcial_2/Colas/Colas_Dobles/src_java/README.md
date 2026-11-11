# ☕ Colas Dobles (Bicola / Deque) en Java (`src_java`)
**Origen:** Complemento de integración para el módulo de Colas Dobles

Dado que Java posee tipado estático y arreglos nativos de longitud fija (`int[]`), se incluyen ambas implementaciones:
* `ColaDobleArreglo.java`: Interfaz `IColaDobleArreglo` y clase `ColaDobleArreglo` con manejo de índices circulares para frente y final.
* `ColaDobleNodos.java`: Clase `NodoDoble`, interfaz `IColaDobleNodos` y clase `ColaDobleNodos` con referencias doblemente enlazadas.

---

## 🛠️ Compilación y Ejecución

```bash
cd Parcial_2/Colas/Colas_Dobles/src_java
javac ColaDobleArreglo.java ColaDobleNodos.java

# Ejecutar versión con Arreglo
java ColaDobleArreglo

# Ejecutar versión con Nodos
java ColaDobleNodos
```
