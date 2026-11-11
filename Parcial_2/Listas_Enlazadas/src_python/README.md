# 🐍 Listas Enlazadas en Python (`src_python`)
**Segundo Parcial — Estructura de Datos (UMB)**

Implementación orientada a objetos de la familia de **Listas Enlazadas** en **Python**, utilizando clases base abstractas (`abc.ABC`) y polimorfismo.

---

## 📁 Archivos de la Implementación

* `lista.py`: Clase base abstracta `Lista(ABC)` que define la interfaz común (`insertar`, `eliminar`, `buscar`, `mostrar`, `esta_vacia`, `obtener_tamano`).
* `lista_enlazada.py`: Implementación de `NodoEnlazado` y `ListaEnlazada` independiente.
* `lista_simple.py`: Implementación de `NodoSimple` y `ListaSimple`.
* `lista_doble.py`: Implementación de `NodoDoble` y `ListaDoble` con soporte para recorrido inverso (`mostrar_inverso`).
* `lista_circular.py`: Implementación de `NodoCircular` y `ListaCircular` con control de ciclo cerrado.
* `multilista.py`: Implementación jerárquica `Nodot` y `Multilista` para administrar nodos principales y sus sublistas asociadas.
* `main.py`: Menú interactivo de consola para operar cualquiera de las estructuras.

---

## 🚀 Requisitos y Ejecución

* **Requisitos:** Python 3.8 o superior (sin librerías externas).
* **Notas de uso:**
  * Los datos introducidos por el menú se almacenan como números enteros.
  * El submenú de `Multilista` permite gestionar tanto los nodos principales como las sublistas de cada nodo.

```bash
cd Parcial_2/Listas_Enlazadas/src_python
python main.py
```
