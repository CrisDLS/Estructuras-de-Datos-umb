# ⚡ Colas de Prioridad en C++

**Responsable:** Cris

Implementación modular en **C++** del Tipo de Dato Abstracto (TDA) Cola de Prioridad bajo tres enfoques algorítmicos.

---

## 📁 Archivos de la Implementación

* `ColaPrioridadNodos.h`: Cola de prioridad implementada mediante nodos enlazados con inserción ordenada.
* `ColaPrioridadArreglo.h`: Cola de prioridad implementada sobre arreglo dinámico.
* `ColaPrioridadHeap.h`: Cola de prioridad implementada mediante montículo binario (*Binary Max/Min Heap*).
* `main.cpp`: Menú interactivo por consola para demostrar la funcionalidad de las tres estructuras.

---

## 🛠️ Compilación y Ejecución

### Opción 1: Compilador GCC / MinGW (`g++`)
```bash
# Compilar
g++ -std=c++11 -Wall main.cpp -o main

# Ejecutar en Windows
.\main.exe

# Ejecutar en Linux / macOS
./main
```

### Opción 2: Compilador Microsoft Visual C++ (`cl.exe`)
```cmd
cl.exe /EHsc /nologo main.cpp /Fe:main.exe
main.exe
```

> **Nota:** Todos los archivos generados por la compilación (`*.exe`, `*.obj`, `*.o`, `*.pdb`) están ignorados por `.gitignore` para mantener limpio el repositorio.
