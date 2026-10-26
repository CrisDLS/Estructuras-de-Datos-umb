# 🔗 Listas Enlazadas en Java — Segundo Parcial
**Estructura de Datos — Licenciatura en Informática UMB**

Implementación modular y orientada a objetos de los distintos tipos de Listas Enlazadas y sus respectivos Nodos en el lenguaje **Java**.

---

## 👥 Reparto General del Equipo

| Integrante | Rol / Módulo Asignado | Clases / Archivos Asignados |
| :--- | :--- | :--- |
| **Fátima** | Nodos Base y Lista Simple | `Nodo.java`, `ListaEnlazada.java`, `ListaSimple.java`, `app/Main.java` |
| **Karen** | Listas Dobles y Circulares | `NodoDoble.java`, `ListaDoble.java`, `ListaCircular.java` |
| **Cristian** | Arquitectura, Multilistas y Pruebas | `NodoMulti.java`, `ListaMulti.java`, `pruebas/PruebasListas.java`, `docs/` |

---

## 📂 Estructura del Módulo

```text
Listas_Enlazadas/
├── README.md                          # Este documento informativo
├── Simulador de listas enlazadas.html # Simulador web interactivo
├── src/
│   ├── nodos/                         # Definiciones de nodos
│   │   ├── Nodo.java
│   │   ├── NodoDoble.java
│   │   └── NodoMulti.java
│   ├── listas/                        # Tipos de listas enlazadas
│   │   ├── ListaEnlazada.java
│   │   ├── ListaSimple.java
│   │   ├── ListaDoble.java
│   │   ├── ListaCircular.java
│   │   └── ListaMulti.java
│   ├── app/
│   │   └── Main.java                  # Punto de entrada interactivo
│   └── pruebas/
│       └── PruebasListas.java         # Banco de pruebas unitarias
└── docs/
    ├── diagrama_clases.md             # Diagrama de arquitectura de clases
    └── reporte_pruebas.md             # Reporte formal de pruebas
```

---

## 🌐 Simulador Web Interactivo
Este módulo incluye un simulador visual con animaciones fluidas para ejecutarse directamente en cualquier navegador:
* Archivo: [`Simulador de listas enlazadas.html`](Simulador%20de%20listas%20enlazadas.html)
* **Características:**
  * Pestañas para **Lista Simple**, **Lista Doble**, **Lista Circular** y **Multilista (Nombre + Edad)**.
  * Direcciones de memoria simuladas en el Heap (`0x1A40`).
  * Enlace dual en tiempo real para Multilista (Pista Azul alfabética y Pista Verde por edad).
  * Control de animación paso a paso, pausa, velocidad y log de código Java en tiempo real.
