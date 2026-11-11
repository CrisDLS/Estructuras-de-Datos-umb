# 📥 Módulo Central de Colas (`Colas/`)
**Segundo Parcial — Estructura de Datos (UMB)**

Directorio raíz que agrupa todas las variantes del Tipo de Dato Abstracto (TDA) **Cola (Queue)** desarrolladas durante el Segundo Parcial bajo un esquema multilenguaje estandarizado (`src_c++`, `src_java`, `src_python`).

---

## 👥 Autoría y Origen de los Módulos

* **Equipo Principal (Cristian, Karen y Fátima):** Desarrolladores del proyecto base de **Colas de Prioridad** y de la integración general del repositorio.
* **Aportación de otros equipos:** Los proyectos base de **Colas Circulares** y **Colas Dobles (C++)** fueron aportados por otros equipos del grupo y estandarizados/complementados en este repositorio para mantener la cobertura completa en los tres lenguajes.

---

## 📊 Matriz de Implementación por Variante y Lenguaje

* **Criterio Técnico:** En **C++ y Java** (tipado fuerte), las colas se implementan mediante **Arreglos** y mediante **Nodos Enlazados** (además de Binary Heap en Prioridad). En **Python** (tipado dinámico), donde `list` es dinámica de forma nativa, se emplea un archivo único autocontenido.

| Variante de Cola | Directorio | `src_c++` | `src_java` | `src_python` | Créditos / Origen |
| :--- | :---: | :---: | :---: | :---: | :--- |
| **1. Colas de Prioridad** | [`Colas_De_Prioridad/`](Colas_De_Prioridad/) | ✅ Completo<br>*(Arreglo, Nodos, Heap)* | ✅ Completo<br>*(Arreglo, Nodos, Simulador)* | ✅ Completo<br>*(`ColaPrioridad.py`)* | **Equipo Principal**<br>(Cristian, Karen y Fátima) |
| **2. Colas Circulares** | [`Colas_Circulares/`](Colas_Circulares/) | ✅ Completo<br>*(Arreglo y Nodos)* | ✅ Completo<br>*(Arreglo y Nodos)* | ✅ Completo<br>*(`Colas_Circulares.py`)* | **Aportación de otros equipos** |
| **3. Colas Dobles (Bicola)** | [`Colas_Dobles/`](Colas_Dobles/) | ✅ Completo<br>*(Arreglo y Nodos)* | ✅ Completo<br>*(Arreglo y Nodos)* | ✅ Completo<br>*(`Colas_Dobles.py`)* | **Aportación de otros equipos** (C++) + Integración (Java/Python) |
| **4. Colas Simples (FIFO)** | [`Colas_Simples/`](Colas_Simples/) | ✅ Completo<br>*(Cabeceras `.h` + `main`)* | ✅ Completo<br>*(Arreglo y Nodos)* | ✅ Completo<br>*(`Colas_Simples.py`)* | **Integración del Repositorio** |

---

## 🧭 Mapa de Navegación de `Colas/`

```text
Colas/
├── README.md                                  # Índice central del módulo de Colas
│
├── Colas_De_Prioridad/                        # Equipo Principal (Cristian, Karen y Fátima)
│   ├── README.md
│   ├── Simulador de cola de prioridad.html
│   ├── src_c++/                               # ColaPrioridadArreglo.h, ColaPrioridadNodos.h, ColaPrioridadHeap.h, main.cpp
│   ├── src_java/                              # ColaPrioridadArreglo.java, ColaPrioridadNodos.java, Simulador.java
│   └── src_python/                            # ColaPrioridad.py, main.py
│
├── Colas_Circulares/                          # Aportación de otros equipos
│   ├── README.md
│   ├── src_c++/                               # colaCircularArreglo.h/.cpp, colaCircularNodos.h/.cpp
│   ├── src_java/                              # Colacirculaarreglo.java, Colacircularnodo.java
│   └── src_python/                            # Colas_Circulares.py
│
├── Colas_Dobles/                              # Aportación de otros equipos + Integración
│   ├── README.md
│   ├── src_c++/                               # Arreglos.cpp, Nodos.cpp
│   ├── src_java/                              # ColaDobleArreglo.java, ColaDobleNodos.java
│   └── src_python/                            # Colas_Dobles.py
│
└── Colas_Simples/                             # TDA Cola Simple FIFO
    ├── README.md
    ├── src_c++/                               # ColaSimpleArreglo.h, ColaSimpleNodos.h, main.cpp
    ├── src_java/                              # ColaSimpleArreglo.java, ColaSimpleNodos.java
    └── src_python/                            # Colas_Simples.py
```
