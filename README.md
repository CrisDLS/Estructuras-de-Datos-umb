# 📚 Estructura de Datos — Repositorio Académico
**Licenciatura en Informática — Universidad Mexiquense del Bicentenario (UMB)**

Repositorio académico centralizado y estructurado como **monorepo modular** para la asignatura de **Estructura de Datos**. Contiene la implementación formal de Tipos de Datos Abstractos (TDA), pruebas de rendimiento temporal y análisis algorítmico en múltiples lenguajes de programación.

---

## 👥 Organización de Integrantes y Parciales

| Parcial | Integrantes | Estructuras Evaluadas | Lenguajes |
| :---: | :--- | :--- | :---: |
| **Parcial 1** | Cristian & Karen | Arreglos, Pilas, Colas, Listas | Python |
| **Parcial 2** | Equipo de 3 (Cristian, Karen, Fatima) | Colas de Prioridad | C++, Python, Java |

---

## 📂 Mapa de Navegación del Repositorio

```text
Estructura_De_Datos/
├── .gitignore                                 # Reglas globales para C++, Java, Python e IDEs
├── README.md                                  # Documentación principal del repositorio
│
├── Parcial_1/                                 # Entregas del Primer Parcial (Python)
│   ├── README.md                              # Guía y tabla de TDAs del Parcial 1
│   ├── Arreglos/                              # TDA Array y pruebas de rendimiento
│   ├── Pilas/                                 # TDA Pila (LIFO) y pruebas de rendimiento
│   ├── Colas/                                 # TDA Cola estándar y genérica
│   ├── Cola_Circular/                         # TDA Cola Circular
│   ├── Lista_Enlazada/                        # Lista simplemente enlazada
│   └── main.py                                # Menú interactivo de demostración
│
└── Parcial_2/                                 # Entregas del Segundo Parcial
    ├── README.md                              # Especificación de la práctica de Colas de Prioridad
    ├── Colas_De_Prioridad/
    │   ├── cpp/                               # Implementación en C++ (Cris)
    │   │   ├── ColaPrioridadNodos.h           # Cola con lista enlazada ordenada
    │   │   ├── ColaPrioridadArreglo.h         # Cola con arreglo
    │   │   ├── ColaPrioridadHeap.h            # Cola con montículo binario (heap)
    │   │   ├── main.cpp                       # Consola interactiva
    │   │   └── README.md                      # Instrucciones de compilación
    │   │
    │   ├── python/                            # Implementación en Python (Fatima)
    │   │   ├── .gitkeep
    │   │   └── README.md                      # Guía de inicio y requerimientos
    │   │
    │   └── java/                              # Implementación en Java (Karen)
    │       ├── .gitkeep
    │       └── README.md                      # Guía de compilación y empaquetado
    │
    └── Listas_Enlazadas/                      # Implementación modular en Java
        ├── README.md                          # Guía y distribución del equipo
        ├── src/
        │   ├── nodos/                         # Clases de nodos (Simple, Doble, Multi)
        │   ├── listas/                        # Tipos de listas enlazadas
        │   ├── app/                           # Punto de entrada (Main)
        │   └── pruebas/                       # Casos de prueba
        └── docs/                              # Diagrama de clases y reporte de pruebas
```

---

## 🚀 Guía Rápida de Compilación y Ejecución

### 1. Parcial 1 (Python)
```bash
# Ejecutar menú interactivo general
python Parcial_1/main.py

# Ejecutar medición de Arreglos
python Parcial_1/Arreglos/Operaciones_Arreglos.py

# Ejecutar medición de Pilas
python Parcial_1/Pilas/Operaciones_Pilas.py
```

### 2. Parcial 2 — Colas de Prioridad

#### C++ (`Parcial_2/Colas_De_Prioridad/cpp/`)
```bash
cd Parcial_2/Colas_De_Prioridad/cpp
g++ -std=c++11 -Wall main.cpp -o main
.\main.exe
```

#### Python (`Parcial_2/Colas_De_Prioridad/python/`)
```bash
cd Parcial_2/Colas_De_Prioridad/python
python main.py
```

#### Java (`Parcial_2/Colas_De_Prioridad/java/`)
```bash
cd Parcial_2/Colas_De_Prioridad/java
javac Main.java
java Main
```

---

## 🤝 Reglas de Colaboración en Git

1. **Aislamiento por Lenguaje:** Cada integrante trabaja exclusivamente dentro de su directorio (`cpp/`, `python/` o `java/`). Esto previene conflictos de fusión (*merge conflicts*).
2. **Cero Binarios:** El archivo `.gitignore` ya está preconfigurado para ignorar `.exe`, `.class`, `__pycache__`, carpetas de IDEs (`.vscode`, `.idea`) y entornos virtuales. Nunca fuerces la inclusión de binarios con `-f`.
3. **Commits Claros y Semánticos:**
   - `feat(cpp): implementada cola con heap binario`
   - `feat(python): agregada clase ColaPrioridadNodos`
   - `feat(java): estructura inicial de clases`
   - `docs: actualizacion de manual de ejecucion`