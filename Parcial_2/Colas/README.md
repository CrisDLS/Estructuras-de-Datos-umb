# 📥 Módulo Central de Colas (`Colas/`)
**Segundo Parcial — Estructura de Datos (UMB)**

Directorio raíz que agrupa todas las variantes del Tipo de Dato Abstracto (TDA) **Cola (Queue)** desarrolladas durante el Segundo Parcial bajo un esquema multilenguaje estandarizado (`src_c++`, `src_java`, `src_python`).

---

## 📊 Estado de Implementación y Registro de Pendientes

| Variante de Cola | Directorio | `src_c++` | `src_java` | `src_python` | Notas y Pendientes por Implementar |
| :--- | :---: | :---: | :---: | :---: | :--- |
| **1. Colas de Prioridad** | [`Colas_De_Prioridad/`](Colas_De_Prioridad/) | ✅ Completo | ✅ Completo | ✅ Completo | **100% Completado.** Incluye Nodos, Arreglo, Binary Heap y simulador web HTML. |
| **2. Colas Circulares** | [`Colas_Circulares/`](Colas_Circulares/) | ✅ Completo | ✅ Completo | ⚠️ Parcial | En C++ y Java incluye Arreglo y Nodos. **Pendiente en Python:** agregar variante con Nodos (actualmente tiene arreglo). |
| **3. Colas Dobles (Bicola / Deque)** | [`Colas_Dobles/`](Colas_Dobles/) | ✅ Completo | ❌ Pendiente | ❌ Pendiente | C++ cuenta con `Arreglos.cpp` y `Nodos.cpp`. **Pendiente:** implementar versiones en `src_java/` y `src_python/`. |
| **4. Colas Simples (FIFO)** | [`Colas_Simples/`](Colas_Simples/) | ❌ Pendiente | ❌ Pendiente | ❌ Pendiente | **Pendiente:** migrar/implementar TDA Cola Simple (Arreglo y Nodos) en los 3 lenguajes (`src_c++`, `src_java`, `src_python`). |

---

## 🧭 Mapa de Navegación de `Colas/`

```text
Colas/
├── README.md                                  # Este índice central y control de pendientes
│
├── Colas_De_Prioridad/                        # TDA Cola de Prioridad (Nodos, Arreglo, Heap)
│   ├── README.md
│   ├── Simulador de cola de prioridad.html
│   ├── src_c++/
│   ├── src_java/
│   └── src_python/
│
├── Colas_Circulares/                          # TDA Cola Circular en anillo (Arreglo y Nodos)
│   ├── README.md
│   ├── src_c++/
│   ├── src_java/
│   └── src_python/
│
├── Colas_Dobles/                              # TDA Bicola / Deque (Inserción y eliminación por ambos extremos)
│   ├── README.md
│   ├── src_c++/
│   ├── src_java/                              # [Pendiente]
│   └── src_python/                            # [Pendiente]
│
└── Colas_Simples/                             # TDA Cola Simple FIFO
    ├── README.md
    ├── src_c++/                               # [Pendiente]
    ├── src_java/                              # [Pendiente]
    └── src_python/                            # [Pendiente]
```
