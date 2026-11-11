# ☕ Listas Enlazadas en Java (`src_java`)
**Estructura de Datos — Licenciatura en Informática UMB**

Implementación modular y orientada a objetos de los distintos tipos de Listas Enlazadas y sus respectivos Nodos en el lenguaje **Java**.

---

## 👥 Reparto del Módulo en Equipo

| Integrante | Rol / Módulo Asignado | Clases / Archivos Asignados |
| :--- | :--- | :--- |
| **Fátima** | Nodos Base y Lista Simple | `Nodo.java`, `ListaEnlazada.java`, `ListaSimple.java`, `app/Main.java` |
| **Karen** | Listas Dobles y Circulares | `NodoDoble.java`, `ListaDoble.java`, `ListaCircular.java` |
| **Cristian** | Arquitectura, Multilistas y Pruebas | `NodoMulti.java`, `ListaMulti.java`, `pruebas/PruebasListas.java`, `docs/` |

---

## 📂 Estructura del Directorio

```text
src_java/
├── README.md                          # Guía del módulo en Java
├── nodos/                             # Definiciones de nodos
│   ├── Nodo.java
│   ├── NodoDoble.java
│   └── NodoMulti.java
├── listas/                            # Jerarquía de listas enlazadas
│   ├── ListaEnlazada.java
│   ├── ListaSimple.java
│   ├── ListaDoble.java
│   ├── ListaCircular.java
│   └── ListaMulti.java
├── app/
│   └── Main.java                      # Punto de entrada interactivo
├── pruebas/
│   └── PruebasListas.java             # Banco de pruebas unitarias
└── docs/
    ├── diagrama_clases.md             # Diagrama de arquitectura de clases
    └── reporte_pruebas.md             # Reporte formal de pruebas
```

---

## 🛠️ Compilación y Ejecución

Desde este directorio (`Parcial_2/Listas_Enlazadas/src_java/`):

```bash
# Compilar todos los paquetes hacia una carpeta temporal bin (ignorada por git)
javac -d bin nodos/*.java listas/*.java app/*.java pruebas/*.java

# Ejecutar la aplicación principal
java -cp bin app.Main

# Ejecutar la suite de pruebas automatizadas
java -cp bin pruebas.PruebasListas
```
