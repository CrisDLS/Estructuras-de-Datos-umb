# 🔗 Proyecto: Listas Enlazadas Multilenguaje
**Segundo Parcial — Estructura de Datos (UMB)**

Implementación modular y orientada a objetos de la jerarquía de **Listas Enlazadas** (Lista Simple, Lista Doble, Lista Circular y Multilista) aplicando **herencia y polimorfismo** en tres lenguajes de programación.

---

## 📂 Organización por Lenguaje

| Lenguaje | Directorio | Estado | Contenido Principal |
| :---: | :---: | :---: | :--- |
| **Java** | [`src_java/`](src_java/) | ✅ Completo | Paquetes modulares (`nodos`, `listas`, `app`, `pruebas`) y documentación UML/pruebas en `docs/`. |
| **Python** | [`src_python/`](src_python/) | ✅ Completo | Jerarquía abstracta `Lista(ABC)`, implementaciones de listas, multilista y menú interactivo `main.py`. |
| **C++** | [`src_c++/`](src_c++/) | ✅ Completo | Jerarquía polimórfica C++17 en cabeceras modulares (`Lista.h`, `ListaSimple.h`, `ListaDoble.h`, `ListaCircular.h`, `Multilista.h`), `main.cpp` y `DIAGRAMA_HERENCIA.md`. |

---

## 🧭 Mapa de Navegación del Proyecto

```text
Listas_Enlazadas/
├── README.md                                  # Índice general del proyecto de Listas Enlazadas
├── Simulador de listas enlazadas.html         # Simulador visual interactivo en navegador
│
├── src_java/                                  # Implementación en Java (Modular OOP)
│   ├── README.md
│   ├── nodos/                                 # Nodo, NodoDoble, NodoMulti
│   ├── listas/                                # ListaEnlazada, ListaSimple, ListaDoble, ListaCircular, ListaMulti
│   ├── app/                                   # Main.java
│   ├── pruebas/                               # PruebasListas.java
│   └── docs/                                  # Diagrama de clases y reporte de pruebas
│
├── src_python/                                # Implementación en Python (ABC + Polimorfismo)
│   ├── README.md
│   ├── lista.py                               # Clase base abstracta Lista
│   ├── lista_enlazada.py
│   ├── lista_simple.py
│   ├── lista_doble.py
│   ├── lista_circular.py
│   ├── multilista.py
│   └── main.py                                # Menú interactivo por consola
│
└── src_c++/                                   # Implementación en C++17 (Librerías .h + main.cpp)
    ├── README.md
    ├── DIAGRAMA_HERENCIA.md
    ├── Lista.h
    ├── ListaSimple.h
    ├── ListaDoble.h
    ├── ListaCircular.h
    ├── Multilista.h
    └── main.cpp
```

---

## 🌐 Simulador Web Interactivo
Este proyecto incluye un simulador visual con animaciones fluidas ejecutable en cualquier navegador:
* **Archivo:** [`Simulador de listas enlazadas.html`](Simulador%20de%20listas%20enlazadas.html)
* **Características:**
  * Pestañas para **Lista Simple**, **Lista Doble**, **Lista Circular** y **Multilista (Nombre + Edad)**.
  * Direcciones de memoria simuladas en el Heap (`0x1A40`).
  * Control de animación paso a paso, pausa, velocidad y registro de código en tiempo real.
