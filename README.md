# 📚 Estructura de Datos — Repositorio Académico
**Licenciatura en Informática — Universidad Mexiquense del Bicentenario (UMB)**

Repositorio académico centralizado y estructurado como **monorepo modular** para la asignatura de **Estructura de Datos**. Contiene la implementación formal de Tipos de Datos Abstractos (TDA), pruebas de rendimiento temporal y análisis algorítmico en múltiples lenguajes de programación.

---

## 👥 Organización de Integrantes y Parciales

| Parcial | Integrantes | Estructuras Evaluadas | Lenguajes |
| :---: | :--- | :--- | :---: |
| **Parcial 1** | Cristian & Karen | Arreglos, Pilas, Colas, Listas | Python |
| **Parcial 2** | Cristian, Karen & Fatima | **Colas** (Prioridad, Circulares, Dobles, Simples) y **Listas Enlazadas** (Simple, Doble, Circular, Multilista) | C++, Python, Java |

---

## 📂 Mapa de Navegación del Repositorio

```text
Estructura_De_Datos/
├── .gitignore                                 # Reglas globales para C++, Java, Python e IDEs (Cero Binarios)
├── README.md                                  # Documentación principal del repositorio
│
├── Parcial_1/                                 # Entregas del Primer Parcial (Python)
│   ├── README.md                              # Guía y tabla de TDAs del Primer Parcial
│   ├── Arreglos/                              # TDA Array y pruebas de rendimiento
│   ├── Pilas/                                 # TDA Pila (LIFO) y pruebas de rendimiento
│   ├── Colas/                                 # TDA Cola estándar y genérica
│   ├── Cola_Circular/                         # TDA Cola Circular
│   ├── Lista_Enlazada/                        # Lista simplemente enlazada
│   └── main.py                                # Menú interactivo de demostración
│
└── Parcial_2/                                 # Entregas del Segundo Parcial (Multilenguaje)
    ├── README.md                              # Especificación general y control de pendientes del Parcial 2
    │
    ├── Colas/                                 # Módulo Raíz de Colas
    │   ├── README.md                          # Estado de implementación por tipo de cola y lenguaje
    │   ├── Colas_De_Prioridad/                # Nodos, Arreglo y Binary Heap (src_c++, src_java, src_python)
    │   ├── Colas_Circulares/                  # Anillo en Arreglo y Nodos (src_c++, src_java, src_python)
    │   ├── Colas_Dobles/                      # Bicola / Deque (src_c++, pendiente src_java y src_python)
    │   └── Colas_Simples/                     # Cola FIFO (pendiente en src_c++, src_java, src_python)
    │
    └── Listas_Enlazadas/                      # Jerarquía de Listas Enlazadas (Herencia y Polimorfismo)
        ├── README.md                          # Guía general y arquitectura multilenguaje
        ├── Simulador de listas enlazadas.html # Simulador visual interactivo
        ├── src_java/                          # Implementación modular en Java (nodos, listas, app, pruebas, docs)
        ├── src_python/                        # Implementación con clases abstractas en Python
        └── src_c++/                           # Implementación polimórfica en C++17
```

---

## 🤝 Convenciones Oficiales y Reglas de Colaboración en Git

1. **Nombres de Directorios Estandarizados:**
   * Los módulos y proyectos utilizan `Title_Snake_Case` sin espacios (`Colas_De_Prioridad`, `Colas_Circulares`, `Listas_Enlazadas`).
   * Las implementaciones por lenguaje dentro de cada proyecto del Segundo Parcial utilizan el prefijo `src_`: **`src_c++/`**, **`src_java/`** y **`src_python/`**.
2. **Aislamiento por Lenguaje:** Cada integrante trabaja dentro del directorio de su lenguaje asignado para prevenir conflictos de fusión (*merge conflicts*).
3. **Cero Binarios:** El archivo [`.gitignore`](.gitignore) ignora automáticamente `.exe`, `.class`, `.o`, `.obj`, `.pdb`, `__pycache__/`, `bin/` y carpetas de configuración local como `.vscode/` o `.idea/`.
4. **Documentación Jerárquica:** Todo directorio de proyecto y subdirectorio de lenguaje cuenta con su propio `README.md` con instrucciones precisas de compilación y ejecución.
