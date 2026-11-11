# 📗 Segundo Parcial: Estructuras de Datos Avanzadas
**Licenciatura en Informática — Universidad Mexiquense del Bicentenario (UMB)**

En este directorio se concentran las entregas formales correspondientes al **Segundo Parcial**, desarrolladas en equipo colaborativo bajo estándares modulares, aislamiento por lenguaje (`src_c++`, `src_java`, `src_python`), cero binarios y enfoque orientado a objetos.

---

## 👥 Equipo de Trabajo

* **Cristian**
* **Fatima**
* **Karen**

---

## 📂 Proyectos Principales del Segundo Parcial

| # | Módulo / Proyecto | Estructuras Incluidas | Lenguajes | Acceso |
| :-: | :--- | :--- | :---: | :---: |
| **1** | **Módulo de Colas (`Colas/`)** | • **Colas de Prioridad** (Nodos, Arreglo, Binary Heap)<br>• **Colas Circulares** (Arreglo, Nodos)<br>• **Colas Dobles / Bicolas** (Arreglo, Nodos)<br>• **Colas Simples** *(Pendiente)* | Multilenguaje<br>(`src_c++`, `src_java`, `src_python`) | [Ver Módulo `Colas/`](Colas/) |
| **2** | **Listas Enlazadas (`Listas_Enlazadas/`)** | Jerarquía OOP con herencia y polimorfismo: **Lista Simple, Doble, Circular y Multilista**, banco de pruebas y simulador web. | Multilenguaje<br>(`src_java`, `src_python`, `src_c++`) | [Ver Módulo `Listas_Enlazadas/`](Listas_Enlazadas/) |

---

## 📌 Control de Pendientes en `Colas/`

Para completar al 100% la matriz multilenguaje de `Parcial_2/Colas/`, quedan registrados los siguientes pendientes:
1. **`Colas/Colas_Simples/`:** Implementar la Cola Simple (Arreglo y Nodos) en los 3 lenguajes (`src_c++`, `src_java`, `src_python`).
2. **`Colas/Colas_Dobles/`:** Actualmente cuenta con la versión completa en `src_c++/` (`Arreglos.cpp` y `Nodos.cpp`). **Faltan las versiones en `src_java/` y `src_python/`**.
3. **`Colas/Colas_Circulares/`:** Completo en `src_c++/` y `src_java/` (Arreglo y Nodos). En `src_python/` cuenta con la versión de arreglo (`Colas_Circulares.py`), **falta agregar la variante con nodos**.

---

## 🧭 Mapa de Navegación del Parcial

```text
Parcial_2/
├── README.md                                  # Índice general del Segundo Parcial
│
├── Colas/                                     # Proyecto 1: Familia de TDAs de Colas
│   ├── README.md                              # Índice central de Colas y tabla de pendientes
│   ├── Colas_De_Prioridad/                    # ✅ Completo (src_c++, src_java, src_python + Simulador HTML)
│   ├── Colas_Circulares/                      # ✅ C++ y Java completos | ⚠️ Python con arreglo
│   ├── Colas_Dobles/                          # ✅ C++ completo | ❌ Faltan Java y Python
│   └── Colas_Simples/                         # ❌ Pendiente en los 3 lenguajes
│
└── Listas_Enlazadas/                          # Proyecto 2: Jerarquía Polimórfica de Listas Enlazadas
    ├── README.md                              # Índice general de Listas Enlazadas
    ├── Simulador de listas enlazadas.html     # Simulador interactivo web
    ├── src_java/                              # ✅ Implementación modular en Java + docs/
    ├── src_python/                            # ✅ Implementación OOP en Python
    └── src_c++/                               # ✅ Implementación polimórfica en C++17 (.h + main.cpp)
```
