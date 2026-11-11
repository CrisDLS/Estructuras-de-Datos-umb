# ⚡ Proyecto de Listas Enlazadas en C++ (`src_c++` — C++17)

Este proyecto implementa diferentes variantes de **Listas Enlazadas** en C++ moderno (estándar C++17) bajo arquitectura de **cabeceras modulares (*Header-Only*, `.h`) y punto de entrada único (`main.cpp`)**, haciendo un uso exhaustivo de los principios de **Programación Orientada a Objetos (POO)**: abstracción, encapsulamiento, herencia, polimorfismo y gestión dinámica de memoria basada en RAII.

---

## 📂 Estructura del Proyecto

```text
src_c++/
├── Lista.h                   # Clase base abstracta con la interfaz común (métodos virtuales puros)
├── ListaSimple.h             # Lista simplemente enlazada y NodoSimple
├── ListaDoble.h              # Lista doblemente enlazada, NodoDoble y recorrido inverso
├── ListaCircular.h           # Lista circular simple, NodoCircular y recorrido cíclico
├── Multilista.h              # Multilista (lista de listas), NodoMultilista y NodoSublista
├── main.cpp                  # Punto de entrada único: menú interactivo y demostración de polimorfismo
├── DIAGRAMA_HERENCIA.md      # Diagramas UML Mermaid y matriz comparativa de polimorfismo
└── README.md                 # Documentación del proyecto y guía de compilación
```

---

## 🏛️ Diseño Orientado a Objetos: Herencia y Polimorfismo

### 1. Clase Base Abstracta (`Lista`)
Define el contrato que toda lista debe cumplir mediante **métodos virtuales puros** (`= 0`) y un **destructor virtual**:
- `virtual ~Lista() = default;` $\rightarrow$ Imprescindible para que al destruir un objeto derivado a través de un puntero `Lista*` o `std::unique_ptr<Lista>`, se llame al destructor correcto de la clase hija y no se produzcan fugas de memoria.
- `insertarInicio(int dato)`
- `insertarFinal(int dato)`
- `insertarEnPosicion(int dato, int pos)`
- `eliminar(int dato)`
- `buscar(int dato) const`
- `mostrar() const`
- `estaVacia() const`
- `tamano() const`
- `vaciar()`
- `obtenerTipo() const`

### 2. Clases Derivadas (`.h`)
`ListaSimple`, `ListaDoble`, `ListaCircular` y `Multilista` heredan de `Lista` mediante herencia pública (`public Lista`) y sobrescriben cada método usando la palabra clave `override`.

### 3. Polimorfismo en Tiempo de Ejecución (*Dynamic Dispatch*)
En `main.cpp`, la función `ejecutarDemostracionPolimorfismo()` demuestra el polimorfismo instanciando un vector de punteros inteligentes a la clase base:
```cpp
std::vector<std::unique_ptr<Lista>> coleccion;
coleccion.push_back(std::make_unique<ListaSimple>());
coleccion.push_back(std::make_unique<ListaDoble>());
coleccion.push_back(std::make_unique<ListaCircular>());
coleccion.push_back(std::make_unique<Multilista>());

for (const auto& lista : coleccion) {
    lista->insertarInicio(10); // Despacho dinámico mediante la vtable
    lista->mostrar();
}
```
El compilador utiliza la tabla de funciones virtuales (**vtable**) para determinar en tiempo de ejecución qué método específico invocar según el tipo real del objeto subyacente.

---

## 🛠️ Compilación y Ejecución

Gracias a que las 5 clases de listas están encapsuladas como librerías de cabecera (`.h`), basta con compilar únicamente `main.cpp`:

### Opción 1: Compilador GCC / MinGW (`g++`)
```bash
cd Parcial_2/Listas_Enlazadas/src_c++
g++ -std=c++17 -Wall -Wextra main.cpp -o main.exe
.\main.exe
```

### Opción 2: Compilador Microsoft Visual C++ (`cl.exe`)
```cmd
cd Parcial_2\Listas_Enlazadas\src_c++
cl.exe /std:c++17 /EHsc /nologo main.cpp /Fe:main.exe
main.exe
```

---

## 🛡️ Robustez y Manejo de Casos Límite
- **Validación de entradas:** La función `leerEntero()` intercepta errores en el flujo de entrada de consola (`cin.fail()`), limpia los flags de error y vacía el búfer para evitar bucles infinitos cuando el usuario introduce caracteres no numéricos.
- **Lista vacía:** Todas las operaciones de eliminación, búsqueda y visualización verifican primero si la lista está vacía antes de desreferenciar punteros.
- **Un solo elemento:** En listas dobles y circulares, los casos donde solo existe un nodo actualizan apropiadamente tanto `cabeza` como `cola` o el puntero autorreferencial.
- **Lista Circular:** El destructor rompe el enlace cíclico previo a la liberación de memoria para evitar bucles infinitos durante la eliminación secuencial de nodos.
- **Multilista:** Tanto `eliminar(dato)` como `vaciar()` liberan recursivamente todos los nodos secundarios de cada sublista antes de eliminar el nodo principal correspondiente.
