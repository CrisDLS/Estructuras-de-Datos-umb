<table>
  <thead>
    <tr>
      <th colspan="4" align="center">Cola de Prioridad : implementada con Arreglo (Estática)</th>
    </tr>
    <tr>
      <th>Método / Operación</th>
      <th>C++ (<code>ColaPrioridadArreglo.h</code>)</th>
      <th>Java (<code>ColaPrioridadArreglo.java</code>)</th>
      <th>Python (<code>ColaPrioridad.py</code>)</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><b>Atributos y Constructor</b></td>
      <td><pre>struct Elemento {
    string dato;
    int prioridad;
};
Elemento* datos;
int capacidad;
int cantidad;
ColaPrioridadArreglo(int cap = 10) {
    if (cap &lt; 1) cap = 1;
    capacidad = cap;
    datos = new Elemento[capacidad];
    cantidad = 0;
}</pre></td>
      <td><pre>private Object[] datos;
private int[] prioridades;
private int tamano;
public ColaPrioridadArreglo(int capacidad) {
    datos = new Object[capacidad];
    prioridades = new int[capacidad];
    tamano = 0;
}</pre></td>
      <td><pre>class ColaPrioridad:
    def __init__(self, capacidad):
        self.cola = []
        self.capacidad = capacidad</pre></td>
    </tr>
    <tr>
      <td><b>Está vacía</b></td>
      <td><pre>bool estaVacia() const {
    return cantidad == 0;
}</pre></td>
      <td><pre>public boolean estaVacia() {
    return tamano == 0;
}</pre></td>
      <td><pre>def esta_vacia(self):
    return len(self.cola) == 0</pre></td>
    </tr>
    <tr>
      <td><b>Está llena</b></td>
      <td><pre>bool estaLlena() const {
    return cantidad == capacidad;
}</pre></td>
      <td><pre>public boolean estaLlena() {
    return tamano == datos.length;
}</pre></td>
      <td><pre>def esta_llena(self):
    return len(self.cola) &gt;= self.capacidad</pre></td>
    </tr>
    <tr>
      <td><b>Encolar / Insertar</b></td>
      <td><pre>bool encolar(const string&amp; dato, int prioridad) {
    if (estaLlena()) return false;
    int i = cantidad - 1;
    while (i &gt;= 0 &amp;&amp; datos[i].prioridad &gt; prioridad) {
        datos[i + 1] = datos[i];
        i--;
    }
    datos[i + 1].dato = dato;
    datos[i + 1].prioridad = prioridad;
    cantidad++;
    return true;
}</pre></td>
      <td><pre>public void insertar(T dato, int prioridad) {
    if (estaLlena()) {
        System.out.println("Cola llena");
        return;
    }
    int i = tamano - 1;
    while (i &gt;= 0 &amp;&amp; prioridades[i] &lt;= prioridad) {
        datos[i + 1] = datos[i];
        prioridades[i + 1] = prioridades[i];
        i--;
    }
    datos[i + 1] = dato;
    prioridades[i + 1] = prioridad;
    tamano++;
}</pre></td>
      <td><pre>def insertar(self, elemento, prioridad):
    if self.esta_llena():
        return False
    nuevo = (prioridad, elemento)
    indice = 0
    while indice &lt; len(self.cola) and self.cola[indice][0] &lt;= prioridad:
        indice += 1
    self.cola.insert(indice, nuevo)
    return True</pre></td>
    </tr>
    <tr>
      <td><b>Desencolar / Extraer / Eliminar</b></td>
      <td><pre>bool desencolar(string&amp; dato, int&amp; prioridad) {
    if (estaVacia()) return false;
    dato = datos[0].dato;
    prioridad = datos[0].prioridad;
    for (int i = 1; i &lt; cantidad; i++) {
        datos[i - 1] = datos[i];
    }
    cantidad--;
    return true;
}</pre></td>
      <td><pre>public T extraer() {
    if (estaVacia()) return null;
    T dato = (T) datos[--tamano];
    datos[tamano] = null;
    return dato;
}</pre></td>
      <td><pre>def eliminar(self):
    if self.esta_vacia():
        return None
    return self.cola.pop(0)</pre></td>
    </tr>
    <tr>
      <td><b>Ver frente</b></td>
      <td><pre>bool verFrente(string&amp; dato, int&amp; prioridad) const {
    if (estaVacia()) return false;
    dato = datos[0].dato;
    prioridad = datos[0].prioridad;
    return true;
}</pre></td>
      <td><pre>public T frente() {
    return estaVacia() ? null : (T) datos[tamano - 1];
}</pre></td>
      <td><pre>// No definido en ColaPrioridad.py (se accede con self.cola[0])</pre></td>
    </tr>
    <tr>
      <td><b>Tamaño / Cantidad</b></td>
      <td><pre>int tamanio() const {
    return cantidad;
}</pre></td>
      <td><pre>public int tamano() {
    return tamano;
}</pre></td>
      <td><pre>def cantidad(self):
    return len(self.cola)</pre></td>
    </tr>
    <tr>
      <td><b>Mostrar / Listar</b></td>
      <td><pre>void mostrar() const {
    if (estaVacia()) {
        cout &lt;&lt; "(cola vacia)" &lt;&lt; endl;
        return;
    }
    for (int i = 0; i &lt; cantidad; i++) {
        cout &lt;&lt; (i + 1) &lt;&lt; ". [Prioridad "
             &lt;&lt; datos[i].prioridad &lt;&lt; "] "
             &lt;&lt; datos[i].dato &lt;&lt; endl;
    }
}</pre></td>
      <td><pre>public List&lt;T&gt; listar() {
    List&lt;T&gt; lista = new ArrayList&lt;&gt;();
    for (int i = tamano - 1; i &gt;= 0; i--)
        lista.add((T) datos[i]);
    return lista;
}</pre></td>
      <td><pre>def mostrar(self):
    return self.cola</pre></td>
    </tr>
  </tbody>
</table>

<br>

<table>
  <thead>
    <tr>
      <th colspan="4" align="center">Cola de Prioridad : implementada con Nodos (Dinámica)</th>
    </tr>
    <tr>
      <th>Método / Operación</th>
      <th>C++ (<code>ColaPrioridadNodos.h</code>)</th>
      <th>Java (<code>ColaPrioridadNodos.java</code>)</th>
      <th>Python (<code>ColaPrioridad.py</code>)</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><b>Nodo y Constructor</b></td>
      <td><pre>struct Nodo {
    string dato;
    int prioridad;
    Nodo* siguiente;
    Nodo(const string&amp; d, int p) {
        dato = d;
        prioridad = p;
        siguiente = nullptr;
    }
};
Nodo* frente = nullptr;
int cantidad = 0;</pre></td>
      <td><pre>public class Nodo&lt;T&gt; {
    T dato;
    int prioridad;
    Nodo&lt;T&gt; siguiente;
}
private Nodo&lt;T&gt; frente = null;
private int tamano = 0;</pre></td>
      <td><pre>class ColaPrioridad:
    def __init__(self, capacidad):
        self.cola = []
        self.capacidad = capacidad</pre></td>
    </tr>
    <tr>
      <td><b>Está vacía</b></td>
      <td><pre>bool estaVacia() const {
    return frente == nullptr;
}</pre></td>
      <td><pre>public boolean estaVacia() {
    return frente == null;
}</pre></td>
      <td><pre>def esta_vacia(self):
    return len(self.cola) == 0</pre></td>
    </tr>
    <tr>
      <td><b>Está llena</b></td>
      <td><pre>bool estaLlena() const {
    return false;
}</pre></td>
      <td><pre>// No definido en ColaPrioridadNodos.java (memoria dinámica)</pre></td>
      <td><pre>def esta_llena(self):
    return len(self.cola) &gt;= self.capacidad</pre></td>
    </tr>
    <tr>
      <td><b>Encolar / Insertar</b></td>
      <td><pre>bool encolar(const string&amp; dato, int prioridad) {
    Nodo* nuevo = new Nodo(dato, prioridad);
    if (frente == nullptr || prioridad &lt; frente-&gt;prioridad) {
        nuevo-&gt;siguiente = frente;
        frente = nuevo;
    } else {
        Nodo* actual = frente;
        while (actual-&gt;siguiente != nullptr &amp;&amp;
               actual-&gt;siguiente-&gt;prioridad &lt;= prioridad) {
            actual = actual-&gt;siguiente;
        }
        nuevo-&gt;siguiente = actual-&gt;siguiente;
        actual-&gt;siguiente = nuevo;
    }
    cantidad++;
    return true;
}</pre></td>
      <td><pre>public void insertar(T dato, int prioridad) {
    Nodo&lt;T&gt; nuevo = new Nodo&lt;&gt;(dato, prioridad);
    if (frente == null || prioridad &lt; frente.prioridad) {
        nuevo.siguiente = frente;
        frente = nuevo;
    } else {
        Nodo&lt;T&gt; actual = frente;
        while (actual.siguiente != null &amp;&amp;
               actual.siguiente.prioridad &lt;= prioridad) {
            actual = actual.siguiente;
        }
        nuevo.siguiente = actual.siguiente;
        actual.siguiente = nuevo;
    }
    tamano++;
}</pre></td>
      <td><pre>def insertar(self, elemento, prioridad):
    if self.esta_llena():
        return False
    nuevo = (prioridad, elemento)
    indice = 0
    while indice &lt; len(self.cola) and self.cola[indice][0] &lt;= prioridad:
        indice += 1
    self.cola.insert(indice, nuevo)
    return True</pre></td>
    </tr>
    <tr>
      <td><b>Desencolar / Extraer / Eliminar</b></td>
      <td><pre>bool desencolar(string&amp; dato, int&amp; prioridad) {
    if (estaVacia()) return false;
    Nodo* aEliminar = frente;
    dato = aEliminar-&gt;dato;
    prioridad = aEliminar-&gt;prioridad;
    frente = frente-&gt;siguiente;
    delete aEliminar;
    cantidad--;
    return true;
}</pre></td>
      <td><pre>public T extraer() {
    if (estaVacia()) return null;
    T dato = frente.dato;
    frente = frente.siguiente;
    tamano--;
    return dato;
}</pre></td>
      <td><pre>def eliminar(self):
    if self.esta_vacia():
        return None
    return self.cola.pop(0)</pre></td>
    </tr>
    <tr>
      <td><b>Ver frente</b></td>
      <td><pre>bool verFrente(string&amp; dato, int&amp; prioridad) const {
    if (estaVacia()) return false;
    dato = frente-&gt;dato;
    prioridad = frente-&gt;prioridad;
    return true;
}</pre></td>
      <td><pre>public T frente() {
    return estaVacia() ? null : frente.dato;
}</pre></td>
      <td><pre>// No definido en ColaPrioridad.py (se accede con self.cola[0])</pre></td>
    </tr>
    <tr>
      <td><b>Tamaño / Cantidad</b></td>
      <td><pre>int tamanio() const {
    return cantidad;
}</pre></td>
      <td><pre>public int tamano() {
    return tamano;
}</pre></td>
      <td><pre>def cantidad(self):
    return len(self.cola)</pre></td>
    </tr>
    <tr>
      <td><b>Mostrar / Listar</b></td>
      <td><pre>void mostrar() const {
    if (estaVacia()) {
        cout &lt;&lt; "(cola vacia)" &lt;&lt; endl;
        return;
    }
    Nodo* actual = frente;
    int posicion = 1;
    while (actual != nullptr) {
        cout &lt;&lt; posicion &lt;&lt; ". [Prioridad "
             &lt;&lt; actual-&gt;prioridad &lt;&lt; "] "
             &lt;&lt; actual-&gt;dato &lt;&lt; endl;
        actual = actual-&gt;siguiente;
        posicion++;
    }
}</pre></td>
      <td><pre>public List&lt;T&gt; listar() {
    List&lt;T&gt; lista = new ArrayList&lt;&gt;();
    Nodo&lt;T&gt; actual = frente;
    while (actual != null) {
        lista.add(actual.dato);
        actual = actual.siguiente;
    }
    return lista;
}</pre></td>
      <td><pre>def mostrar(self):
    return self.cola</pre></td>
    </tr>
  </tbody>
</table>

<br>

<table>
  <thead>
    <tr>
      <th colspan="4" align="center">Cola Circular : implementada con Arreglo (Estática)</th>
    </tr>
    <tr>
      <th>Método / Operación</th>
      <th>C++ (<code>colaCircularArreglo.cpp</code>)</th>
      <th>Java (<code>Colacirculaarreglo.java</code>)</th>
      <th>Python (<code>Colas_Circulares.py</code>)</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><b>Atributos y Constructor</b></td>
      <td><pre>string clientes[5];
int frente;
int final;
int cantidad;
ColaCircularArreglo::ColaCircularArreglo() {
    frente = 0;
    final = -1;
    cantidad = 0;
}</pre></td>
      <td><pre>private int[] datos;
private int frente;
private int fin;
private int tamano;
private int capacidad;
public Colacirculaarreglo(int capacidad) {
    this.capacidad = capacidad;
    datos = new int[capacidad];
    frente = 0;
    fin = 0;
    tamano = 0;
}</pre></td>
      <td><pre>class ColaCircular:
    def __init__(self, capacidad):
        self.capacidad = capacidad
        self.cola = [None] * capacidad
        self.frente = 0
        self.final = -1
        self.cantidad = 0</pre></td>
    </tr>
    <tr>
      <td><b>Está vacía</b></td>
      <td><pre>bool ColaCircularArreglo::estaVacia() {
    return cantidad == 0;
}</pre></td>
      <td><pre>public boolean estaVacia() {
    return tamano == 0;
}</pre></td>
      <td><pre>def esta_vacia(self):
    return self.cantidad == 0</pre></td>
    </tr>
    <tr>
      <td><b>Está llena</b></td>
      <td><pre>bool ColaCircularArreglo::estaLlena() {
    return cantidad == 5;
}</pre></td>
      <td><pre>public boolean estaLlena() {
    return tamano == capacidad;
}</pre></td>
      <td><pre>def esta_llena(self):
    return self.cantidad == self.capacidad</pre></td>
    </tr>
    <tr>
      <td><b>Encolar / Enqueue</b></td>
      <td><pre>void ColaCircularArreglo::encolar(string nombre) {
    if (estaLlena()) {
        cout &lt;&lt; "La cola esta llena.\n";
        return;
    }
    final = (final + 1) % 5;
    clientes[final] = nombre;
    cantidad++;
}</pre></td>
      <td><pre>public void Enqueue(int dato) {
    if (estaLlena()) {
        throw new IllegalStateException("La cola está llena");
    }
    datos[fin] = dato;
    fin = (fin + 1) % capacidad;
    tamano++;
}</pre></td>
      <td><pre>def encolar(self, elemento):
    if self.esta_llena():
        return False
    self.final = (self.final + 1) % self.capacidad
    self.cola[self.final] = elemento
    self.cantidad += 1
    return True</pre></td>
    </tr>
    <tr>
      <td><b>Desencolar / Dequeue</b></td>
      <td><pre>void ColaCircularArreglo::desencolar() {
    if (estaVacia()) {
        cout &lt;&lt; "No hay clientes por atender.\n";
        return;
    }
    cout &lt;&lt; "Atendiendo a: " &lt;&lt; clientes[frente] &lt;&lt; endl;
    frente = (frente + 1) % 5;
    cantidad--;
}</pre></td>
      <td><pre>public int Dequeue() {
    if (estaVacia()) {
        throw new IllegalStateException("La cola está vacía");
    }
    int dato = datos[frente];
    frente = (frente + 1) % capacidad;
    tamano--;
    return dato;
}</pre></td>
      <td><pre>def desencolar(self):
    if self.esta_vacia():
        return None
    elemento = self.cola[self.frente]
    self.cola[self.frente] = None
    self.frente = (self.frente + 1) % self.capacidad
    self.cantidad -= 1
    return elemento</pre></td>
    </tr>
    <tr>
      <td><b>Ver frente / Primero</b></td>
      <td><pre>// No definido por separado (se muestra clientes[frente] dentro de desencolar)</pre></td>
      <td><pre>public int verFrente() {
    if (estaVacia()) {
        throw new IllegalStateException("La cola está vacía");
    }
    return datos[frente];
}</pre></td>
      <td><pre>def primero(self):
    if self.esta_vacia():
        return None
    return self.cola[self.frente]</pre></td>
    </tr>
    <tr>
      <td><b>Tamaño</b></td>
      <td><pre>// No definido como método (se controla con el atributo cantidad)</pre></td>
      <td><pre>public int tamano() {
    return tamano;
}</pre></td>
      <td><pre>// No definido como método (se consulta el atributo self.cantidad)</pre></td>
    </tr>
    <tr>
      <td><b>Mostrar</b></td>
      <td><pre>void ColaCircularArreglo::mostrar() {
    if (estaVacia()) {
        cout &lt;&lt; "No hay clientes en espera.\n";
        return;
    }
    cout &lt;&lt; "Clientes en espera: ";
    for (int i = 0; i &lt; cantidad; i++) {
        cout &lt;&lt; clientes[(frente + i) % 5] &lt;&lt; " ";
    }
    cout &lt;&lt; endl;
}</pre></td>
      <td><pre>public void mostrar() {
    StringBuilder sb = new StringBuilder("Frente -&gt; [");
    for (int i = 0; i &lt; tamano; i++) {
        sb.append(datos[(frente + i) % capacidad]);
        if (i &lt; tamano - 1) sb.append(", ");
    }
    sb.append("] &lt;- Fin");
    System.out.println(sb);
}</pre></td>
      <td><pre>def mostrar(self):
    elementos = []
    for i in range(self.cantidad):
        posicion = (self.frente + i) % self.capacidad
        elementos.append(self.cola[posicion])
    return elementos</pre></td>
    </tr>
  </tbody>
</table>

<br>

<table>
  <thead>
    <tr>
      <th colspan="4" align="center">Cola Circular : implementada con Nodos (Dinámica)</th>
    </tr>
    <tr>
      <th>Método / Operación</th>
      <th>C++ (<code>colaCircularNodos.cpp</code>)</th>
      <th>Java (<code>Colacircularnodo.java</code>)</th>
      <th>Python (<code>Colas_Circulares.py</code>)</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><b>Nodo y Constructor</b></td>
      <td><pre>struct Nodo {
    string nombre;
    Nodo* siguiente;
};
ColaCircularNodos::ColaCircularNodos() {
    frente = nullptr;
    final = nullptr;
}</pre></td>
      <td><pre>class NodoCircular {
    int dato;
    NodoCircular siguiente;
    NodoCircular(int dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}
private NodoCircular fin = null;
private int tamano = 0;</pre></td>
      <td><pre>class ColaCircular:
    def __init__(self, capacidad):
        self.capacidad = capacidad
        self.cola = [None] * capacidad
        self.frente = 0
        self.final = -1
        self.cantidad = 0</pre></td>
    </tr>
    <tr>
      <td><b>Está vacía</b></td>
      <td><pre>bool ColaCircularNodos::estaVacia() {
    return frente == nullptr;
}</pre></td>
      <td><pre>public boolean estaVacia() {
    return fin == null;
}</pre></td>
      <td><pre>def esta_vacia(self):
    return self.cantidad == 0</pre></td>
    </tr>
    <tr>
      <td><b>Encolar / Enqueue</b></td>
      <td><pre>void ColaCircularNodos::encolar(string nombre) {
    Nodo* nuevo = new Nodo;
    nuevo-&gt;nombre = nombre;
    if (estaVacia()) {
        frente = nuevo;
        final = nuevo;
        nuevo-&gt;siguiente = nuevo;
    } else {
        nuevo-&gt;siguiente = frente;
        final-&gt;siguiente = nuevo;
        final = nuevo;
    }
}</pre></td>
      <td><pre>public void Enqueue(int dato) {
    NodoCircular nuevo = new NodoCircular(dato);
    if (fin == null) {
        fin = nuevo;
        fin.siguiente = fin;
    } else {
        nuevo.siguiente = fin.siguiente;
        fin.siguiente = nuevo;
        fin = nuevo;
    }
    tamano++;
}</pre></td>
      <td><pre>def encolar(self, elemento):
    if self.esta_llena():
        return False
    self.final = (self.final + 1) % self.capacidad
    self.cola[self.final] = elemento
    self.cantidad += 1
    return True</pre></td>
    </tr>
    <tr>
      <td><b>Desencolar / Dequeue</b></td>
      <td><pre>void ColaCircularNodos::desencolar() {
    if (estaVacia()) {
        cout &lt;&lt; "No hay clientes por atender.\n";
        return;
    }
    Nodo* temporal = frente;
    cout &lt;&lt; "Atendiendo a: " &lt;&lt; frente-&gt;nombre &lt;&lt; endl;
    if (frente == final) {
        frente = nullptr;
        final = nullptr;
    } else {
        frente = frente-&gt;siguiente;
        final-&gt;siguiente = frente;
    }
    delete temporal;
}</pre></td>
      <td><pre>public int Dequeue() {
    if (estaVacia()) {
        throw new IllegalStateException("La cola está vacía");
    }
    NodoCircular frente = fin.siguiente;
    int dato = frente.dato;
    if (fin == frente) {
        fin = null;
    } else {
        fin.siguiente = frente.siguiente;
    }
    tamano--;
    return dato;
}</pre></td>
      <td><pre>def desencolar(self):
    if self.esta_vacia():
        return None
    elemento = self.cola[self.frente]
    self.cola[self.frente] = None
    self.frente = (self.frente + 1) % self.capacidad
    self.cantidad -= 1
    return elemento</pre></td>
    </tr>
    <tr>
      <td><b>Ver frente / Primero</b></td>
      <td><pre>// No definido por separado (se accede a frente-&gt;nombre en desencolar)</pre></td>
      <td><pre>public int verFrente() {
    if (estaVacia()) {
        throw new IllegalStateException("La cola está vacía");
    }
    return fin.siguiente.dato;
}</pre></td>
      <td><pre>def primero(self):
    if self.esta_vacia():
        return None
    return self.cola[self.frente]</pre></td>
    </tr>
    <tr>
      <td><b>Ver final</b></td>
      <td><pre>// No definido como método en colaCircularNodos.cpp</pre></td>
      <td><pre>public int verFinal() {
    if (estaVacia()) {
        throw new IllegalStateException("La cola está vacía");
    }
    return fin.dato;
}</pre></td>
      <td><pre>// No definido como método en Colas_Circulares.py</pre></td>
    </tr>
    <tr>
      <td><b>Tamaño</b></td>
      <td><pre>// No definido como método en colaCircularNodos.cpp</pre></td>
      <td><pre>public int tamano() {
    return tamano;
}</pre></td>
      <td><pre>// No definido como método (se consulta el atributo self.cantidad)</pre></td>
    </tr>
    <tr>
      <td><b>Mostrar</b></td>
      <td><pre>void ColaCircularNodos::mostrar() {
    if (estaVacia()) {
        cout &lt;&lt; "No hay clientes en espera.\n";
        return;
    }
    Nodo* actual = frente;
    cout &lt;&lt; "Clientes en espera: ";
    do {
        cout &lt;&lt; actual-&gt;nombre &lt;&lt; " ";
        actual = actual-&gt;siguiente;
    } while (actual != frente);
    cout &lt;&lt; endl;
}</pre></td>
      <td><pre>public void mostrar() {
    if (estaVacia()) {
        System.out.println("Cola vacía");
        return;
    }
    StringBuilder sb = new StringBuilder();
    NodoCircular actual = fin.siguiente;
    for (int i = 0; i &lt; tamano; i++) {
        sb.append(actual.dato).append(" -&gt; ");
        actual = actual.siguiente;
    }
    sb.append("(vuelve al frente)");
    System.out.println(sb);
}</pre></td>
      <td><pre>def mostrar(self):
    elementos = []
    for i in range(self.cantidad):
        posicion = (self.frente + i) % self.capacidad
        elementos.append(self.cola[posicion])
    return elementos</pre></td>
    </tr>
  </tbody>
</table>

<br>

<table>
  <thead>
    <tr>
      <th colspan="4" align="center">Cola Doble (Bicola) : implementada con Arreglo (Estática)</th>
    </tr>
    <tr>
      <th>Método / Operación</th>
      <th>C++ (<code>Arreglos.cpp</code>)</th>
      <th>Java (<code>ColaDobleArreglo.java</code>)</th>
      <th>Python (<code>Colas_Dobles.py</code>)</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><b>Atributos y Constructor</b></td>
      <td><pre>int *arr;
int capacity;
int front;
int rear;
int size;
Deque(int c) {
    capacity = c;
    arr = new int[capacity];
    front = -1;
    rear = -1;
    size = 0;
}</pre></td>
      <td><pre>private int[] datos;
private int frente;
private int fin;
private int tamano;
private int capacidad;
public ColaDobleArreglo(int capacidad) {
    this.capacidad = capacidad;
    this.datos = new int[capacidad];
    this.frente = -1;
    this.fin = -1;
    this.tamano = 0;
}</pre></td>
      <td><pre>class ColaDoble:
    def __init__(self):
        self.cola = []</pre></td>
    </tr>
    <tr>
      <td><b>Está vacía</b></td>
      <td><pre>bool isEmpty() {
    return size == 0;
}</pre></td>
      <td><pre>public boolean estaVacia() {
    return tamano == 0;
}</pre></td>
      <td><pre>def esta_vacia(self):
    return len(self.cola) == 0</pre></td>
    </tr>
    <tr>
      <td><b>Está llena</b></td>
      <td><pre>bool isFull() {
    return size == capacity;
}</pre></td>
      <td><pre>public boolean estaLlena() {
    return tamano == capacidad;
}</pre></td>
      <td><pre>// No definido en Colas_Dobles.py (lista dinámica en Python)</pre></td>
    </tr>
    <tr>
      <td><b>Insertar al frente</b></td>
      <td><pre>void insertFront(int key) {
    if (isFull()) return;
    if (isEmpty()) {
        front = rear = 0;
    } else if (front == 0) {
        front = capacity - 1;
    } else {
        front--;
    }
    arr[front] = key;
    size++;
}</pre></td>
      <td><pre>public void insertarFrente(int dato) {
    if (estaLlena()) {
        throw new IllegalStateException("Cola llena");
    }
    if (estaVacia()) {
        frente = fin = 0;
    } else if (frente == 0) {
        frente = capacidad - 1;
    } else {
        frente--;
    }
    datos[frente] = dato;
    tamano++;
}</pre></td>
      <td><pre>def insertar_frente(self, elemento):
    self.cola.insert(0, elemento)</pre></td>
    </tr>
    <tr>
      <td><b>Insertar al final</b></td>
      <td><pre>void insertRear(int key) {
    if (isFull()) return;
    if (isEmpty()) {
        front = rear = 0;
    } else if (rear == capacity - 1) {
        rear = 0;
    } else {
        rear++;
    }
    arr[rear] = key;
    size++;
}</pre></td>
      <td><pre>public void insertarFinal(int dato) {
    if (estaLlena()) {
        throw new IllegalStateException("Cola llena");
    }
    if (estaVacia()) {
        frente = fin = 0;
    } else if (fin == capacidad - 1) {
        fin = 0;
    } else {
        fin++;
    }
    datos[fin] = dato;
    tamano++;
}</pre></td>
      <td><pre>def insertar_final(self, elemento):
    self.cola.append(elemento)</pre></td>
    </tr>
    <tr>
      <td><b>Eliminar del frente</b></td>
      <td><pre>void deleteFront() {
    if (isEmpty()) return;
    if (front == rear) {
        front = rear = -1;
    } else if (front == capacity - 1) {
        front = 0;
    } else {
        front++;
    }
    size--;
}</pre></td>
      <td><pre>public int eliminarFrente() {
    if (estaVacia()) {
        throw new IllegalStateException("Cola vacía");
    }
    int valor = datos[frente];
    if (frente == fin) {
        frente = fin = -1;
    } else if (frente == capacidad - 1) {
        frente = 0;
    } else {
        frente++;
    }
    tamano--;
    return valor;
}</pre></td>
      <td><pre>def eliminar_frente(self):
    if self.esta_vacia():
        return None
    return self.cola.pop(0)</pre></td>
    </tr>
    <tr>
      <td><b>Eliminar del final</b></td>
      <td><pre>void deleteRear() {
    if (isEmpty()) return;
    if (front == rear) {
        front = rear = -1;
    } else if (rear == 0) {
        rear = capacity - 1;
    } else {
        rear--;
    }
    size--;
}</pre></td>
      <td><pre>public int eliminarFinal() {
    if (estaVacia()) {
        throw new IllegalStateException("Cola vacía");
    }
    int valor = datos[fin];
    if (frente == fin) {
        frente = fin = -1;
    } else if (fin == 0) {
        fin = capacidad - 1;
    } else {
        fin--;
    }
    tamano--;
    return valor;
}</pre></td>
      <td><pre>def eliminar_final(self):
    if self.esta_vacia():
        return None
    return self.cola.pop()</pre></td>
    </tr>
    <tr>
      <td><b>Ver frente</b></td>
      <td><pre>int getFront() {
    if (isEmpty()) return -1;
    return arr[front];
}</pre></td>
      <td><pre>public int verFrente() {
    if (estaVacia()) {
        throw new IllegalStateException("Cola vacía");
    }
    return datos[frente];
}</pre></td>
      <td><pre>def ver_frente(self):
    if self.esta_vacia():
        return None
    return self.cola[0]</pre></td>
    </tr>
    <tr>
      <td><b>Ver final</b></td>
      <td><pre>int getRear() {
    if (isEmpty()) return -1;
    return arr[rear];
}</pre></td>
      <td><pre>public int verFinal() {
    if (estaVacia()) {
        throw new IllegalStateException("Cola vacía");
    }
    return datos[fin];
}</pre></td>
      <td><pre>def ver_final(self):
    if self.esta_vacia():
        return None
    return self.cola[-1]</pre></td>
    </tr>
    <tr>
      <td><b>Tamaño</b></td>
      <td><pre>// No definido como método (se controla con el atributo size)</pre></td>
      <td><pre>public int tamano() {
    return tamano;
}</pre></td>
      <td><pre>def tamano(self):
    return len(self.cola)</pre></td>
    </tr>
    <tr>
      <td><b>Mostrar</b></td>
      <td><pre>void mostrar() {
    if (isEmpty()) return;
    int i = front;
    for (int contador = 0; contador &lt; size; contador++) {
        cout &lt;&lt; arr[i] &lt;&lt; " ";
        i = (i + 1) % capacity;
    }
}</pre></td>
      <td><pre>public void mostrar() {
    if (estaVacia()) return;
    StringBuilder sb = new StringBuilder("FRENTE -&gt; [");
    for (int i = 0; i &lt; tamano; i++) {
        sb.append(datos[(frente + i) % capacidad]);
        if (i &lt; tamano - 1) sb.append(" &lt;-&gt; ");
    }
    sb.append("] &lt;- FINAL");
    System.out.println(sb);
}</pre></td>
      <td><pre>def mostrar(self):
    if self.esta_vacia():
        return "Cola Doble: [vacía]"
    return "FRENTE -&gt; " + " &lt;-&gt; ".join(str(x) for x in self.cola) + " &lt;- FINAL"</pre></td>
    </tr>
  </tbody>
</table>

<br>

<table>
  <thead>
    <tr>
      <th colspan="4" align="center">Cola Doble (Bicola) : implementada con Nodos (Dinámica)</th>
    </tr>
    <tr>
      <th>Método / Operación</th>
      <th>C++ (<code>Nodos.cpp</code>)</th>
      <th>Java (<code>ColaDobleNodos.java</code>)</th>
      <th>Python (<code>Colas_Dobles.py</code>)</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><b>Nodo y Constructor</b></td>
      <td><pre>struct Nodo {
    int dato;
    Nodo* siguiente;
    Nodo* anterior;
    Nodo(int numero) : dato(numero),
        siguiente(nullptr), anterior(nullptr) {}
};
Deque() : frente(nullptr), cola(nullptr), tamano(0) {}</pre></td>
      <td><pre>class NodoDoble {
    int dato;
    NodoDoble siguiente;
    NodoDoble anterior;
    NodoDoble(int dato) {
        this.dato = dato;
        this.siguiente = null;
        this.anterior = null;
    }
}
private NodoDoble frente = null;
private NodoDoble fin = null;
private int tamano = 0;</pre></td>
      <td><pre>class ColaDoble:
    def __init__(self):
        self.cola = []</pre></td>
    </tr>
    <tr>
      <td><b>Está vacía</b></td>
      <td><pre>bool estaVacia() {
    return frente == nullptr;
}</pre></td>
      <td><pre>public boolean estaVacia() {
    return frente == null;
}</pre></td>
      <td><pre>def esta_vacia(self):
    return len(self.cola) == 0</pre></td>
    </tr>
    <tr>
      <td><b>Insertar al frente</b></td>
      <td><pre>void push_front(int val) {
    Nodo* nuevo = new Nodo(val);
    if (estaVacia()) {
        frente = cola = nuevo;
    } else {
        nuevo-&gt;siguiente = frente;
        frente-&gt;anterior = nuevo;
        frente = nuevo;
    }
    tamano++;
}</pre></td>
      <td><pre>public void insertarFrente(int dato) {
    NodoDoble nuevo = new NodoDoble(dato);
    if (estaVacia()) {
        frente = fin = nuevo;
    } else {
        nuevo.siguiente = frente;
        frente.anterior = nuevo;
        frente = nuevo;
    }
    tamano++;
}</pre></td>
      <td><pre>def insertar_frente(self, elemento):
    self.cola.insert(0, elemento)</pre></td>
    </tr>
    <tr>
      <td><b>Insertar al final</b></td>
      <td><pre>void push_back(int val) {
    Nodo* nuevo = new Nodo(val);
    if (estaVacia()) {
        frente = cola = nuevo;
    } else {
        cola-&gt;siguiente = nuevo;
        nuevo-&gt;anterior = cola;
        cola = nuevo;
    }
    tamano++;
}</pre></td>
      <td><pre>public void insertarFinal(int dato) {
    NodoDoble nuevo = new NodoDoble(dato);
    if (estaVacia()) {
        frente = fin = nuevo;
    } else {
        fin.siguiente = nuevo;
        nuevo.anterior = fin;
        fin = nuevo;
    }
    tamano++;
}</pre></td>
      <td><pre>def insertar_final(self, elemento):
    self.cola.append(elemento)</pre></td>
    </tr>
    <tr>
      <td><b>Eliminar del frente</b></td>
      <td><pre>void pop_front() {
    if (estaVacia()) return;
    Nodo* temp = frente;
    if (frente == cola) {
        frente = cola = nullptr;
    } else {
        frente = frente-&gt;siguiente;
        frente-&gt;anterior = nullptr;
    }
    delete temp;
    tamano--;
}</pre></td>
      <td><pre>public int eliminarFrente() {
    if (estaVacia()) {
        throw new IllegalStateException("Cola vacía");
    }
    int valor = frente.dato;
    if (frente == fin) {
        frente = fin = null;
    } else {
        frente = frente.siguiente;
        frente.anterior = null;
    }
    tamano--;
    return valor;
}</pre></td>
      <td><pre>def eliminar_frente(self):
    if self.esta_vacia():
        return None
    return self.cola.pop(0)</pre></td>
    </tr>
    <tr>
      <td><b>Eliminar del final</b></td>
      <td><pre>void pop_back() {
    if (estaVacia()) return;
    Nodo* temp = cola;
    if (frente == cola) {
        frente = cola = nullptr;
    } else {
        cola = cola-&gt;anterior;
        cola-&gt;siguiente = nullptr;
    }
    delete temp;
    tamano--;
}</pre></td>
      <td><pre>public int eliminarFinal() {
    if (estaVacia()) {
        throw new IllegalStateException("Cola vacía");
    }
    int valor = fin.dato;
    if (frente == fin) {
        frente = fin = null;
    } else {
        fin = fin.anterior;
        fin.siguiente = null;
    }
    tamano--;
    return valor;
}</pre></td>
      <td><pre>def eliminar_final(self):
    if self.esta_vacia():
        return None
    return self.cola.pop()</pre></td>
    </tr>
    <tr>
      <td><b>Ver frente</b></td>
      <td><pre>int front() {
    if (estaVacia()) throw runtime_error("Vacia");
    return frente-&gt;dato;
}</pre></td>
      <td><pre>public int verFrente() {
    if (estaVacia()) {
        throw new IllegalStateException("Cola vacía");
    }
    return frente.dato;
}</pre></td>
      <td><pre>def ver_frente(self):
    if self.esta_vacia():
        return None
    return self.cola[0]</pre></td>
    </tr>
    <tr>
      <td><b>Ver final</b></td>
      <td><pre>int back() {
    if (estaVacia()) throw runtime_error("Vacia");
    return cola-&gt;dato;
}</pre></td>
      <td><pre>public int verFinal() {
    if (estaVacia()) {
        throw new IllegalStateException("Cola vacía");
    }
    return fin.dato;
}</pre></td>
      <td><pre>def ver_final(self):
    if self.esta_vacia():
        return None
    return self.cola[-1]</pre></td>
    </tr>
    <tr>
      <td><b>Tamaño</b></td>
      <td><pre>// No definido como método (se controla con el atributo tamano)</pre></td>
      <td><pre>public int tamano() {
    return tamano;
}</pre></td>
      <td><pre>def tamano(self):
    return len(self.cola)</pre></td>
    </tr>
    <tr>
      <td><b>Mostrar</b></td>
      <td><pre>void mostrar() {
    if (estaVacia()) return;
    Nodo* actual = frente;
    while (actual != nullptr) {
        cout &lt;&lt; actual-&gt;dato &lt;&lt; " ";
        actual = actual-&gt;siguiente;
    }
}</pre></td>
      <td><pre>public void mostrar() {
    if (estaVacia()) return;
    StringBuilder sb = new StringBuilder("FRENTE -&gt; ");
    NodoDoble actual = frente;
    while (actual != null) {
        sb.append(actual.dato).append(" &lt;-&gt; ");
        actual = actual.siguiente;
    }
    System.out.println(sb);
}</pre></td>
      <td><pre>def mostrar(self):
    if self.esta_vacia():
        return "Cola Doble: [vacía]"
    return "FRENTE -&gt; " + " &lt;-&gt; ".join(str(x) for x in self.cola) + " &lt;- FINAL"</pre></td>
    </tr>
  </tbody>
</table>

<br>

<table>
  <thead>
    <tr>
      <th colspan="4" align="center">Cola Simple (FIFO) : implementada con Arreglo (Estática)</th>
    </tr>
    <tr>
      <th>Método / Operación</th>
      <th>C++ (<code>ColaSimpleArreglo.h</code>)</th>
      <th>Java (<code>ColaSimpleArreglo.java</code>)</th>
      <th>Python (<code>Colas_Simples.py</code>)</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><b>Atributos y Constructor</b></td>
      <td><pre>int* datos;
int capacidad;
int frente;
int final;
int cantidad;
ColaSimpleArreglo(int cap = 5) {
    capacidad = (cap &gt; 0) ? cap : 5;
    datos = new int[capacidad];
    frente = 0;
    final = -1;
    cantidad = 0;
}</pre></td>
      <td><pre>private int[] datos;
private int frente;
private int fin;
private int cantidad;
private int capacidad;
public ColaSimpleArreglo(int capacidad) {
    this.capacidad = capacidad;
    this.datos = new int[capacidad];
    this.frente = 0;
    this.fin = -1;
    this.cantidad = 0;
}</pre></td>
      <td><pre>class ColaSimple:
    def __init__(self):
        self.cola = []</pre></td>
    </tr>
    <tr>
      <td><b>Está vacía</b></td>
      <td><pre>bool estaVacia() const {
    return cantidad == 0;
}</pre></td>
      <td><pre>public boolean estaVacia() {
    return cantidad == 0;
}</pre></td>
      <td><pre>def esta_vacia(self):
    return len(self.cola) == 0</pre></td>
    </tr>
    <tr>
      <td><b>Está llena</b></td>
      <td><pre>bool estaLlena() const {
    return final == capacidad - 1;
}</pre></td>
      <td><pre>public boolean estaLlena() {
    return fin == capacidad - 1;
}</pre></td>
      <td><pre>// No definido en Colas_Simples.py (lista dinámica en Python)</pre></td>
    </tr>
    <tr>
      <td><b>Encolar</b></td>
      <td><pre>bool encolar(int valor) {
    if (estaLlena()) return false;
    final++;
    datos[final] = valor;
    cantidad++;
    return true;
}</pre></td>
      <td><pre>public void encolar(int dato) {
    if (estaLlena()) {
        throw new IllegalStateException("Cola llena");
    }
    fin++;
    datos[fin] = dato;
    cantidad++;
}</pre></td>
      <td><pre>def encolar(self, elemento):
    self.cola.append(elemento)</pre></td>
    </tr>
    <tr>
      <td><b>Desencolar</b></td>
      <td><pre>bool desencolar(int&amp; valorExtraido) {
    if (estaVacia()) return false;
    valorExtraido = datos[frente];
    frente++;
    cantidad--;
    if (cantidad == 0) {
        frente = 0;
        final = -1;
    }
    return true;
}</pre></td>
      <td><pre>public int desencolar() {
    if (estaVacia()) {
        throw new IllegalStateException("Cola vacía");
    }
    int valor = datos[frente];
    frente++;
    cantidad--;
    if (cantidad == 0) {
        frente = 0;
        fin = -1;
    }
    return valor;
}</pre></td>
      <td><pre>def desencolar(self):
    if self.esta_vacia():
        return None
    return self.cola.pop(0)</pre></td>
    </tr>
    <tr>
      <td><b>Ver frente / Primero</b></td>
      <td><pre>bool verFrente(int&amp; valorFrente) const {
    if (estaVacia()) return false;
    valorFrente = datos[frente];
    return true;
}</pre></td>
      <td><pre>public int verFrente() {
    if (estaVacia()) {
        throw new IllegalStateException("Cola vacía");
    }
    return datos[frente];
}</pre></td>
      <td><pre>def primero(self):
    if self.esta_vacia():
        return None
    return self.cola[0]</pre></td>
    </tr>
    <tr>
      <td><b>Ver final / Último</b></td>
      <td><pre>bool verFinal(int&amp; valorFinal) const {
    if (estaVacia()) return false;
    valorFinal = datos[final];
    return true;
}</pre></td>
      <td><pre>public int verFinal() {
    if (estaVacia()) {
        throw new IllegalStateException("Cola vacía");
    }
    return datos[fin];
}</pre></td>
      <td><pre>def ultimo(self):
    if self.esta_vacia():
        return None
    return self.cola[-1]</pre></td>
    </tr>
    <tr>
      <td><b>Tamaño</b></td>
      <td><pre>int tamano() const {
    return cantidad;
}</pre></td>
      <td><pre>public int tamano() {
    return cantidad;
}</pre></td>
      <td><pre>def tamano(self):
    return len(self.cola)</pre></td>
    </tr>
    <tr>
      <td><b>Mostrar</b></td>
      <td><pre>void mostrar() const {
    if (estaVacia()) return;
    for (int i = frente; i &lt;= final; ++i) {
        std::cout &lt;&lt; datos[i] &lt;&lt; " ";
    }
}</pre></td>
      <td><pre>public void mostrar() {
    if (estaVacia()) return;
    StringBuilder sb = new StringBuilder("FRENTE -&gt; [");
    for (int i = frente; i &lt;= fin; i++) {
        sb.append(datos[i]);
        if (i &lt; fin) sb.append(" -&gt; ");
    }
    sb.append("] &lt;- FINAL");
    System.out.println(sb);
}</pre></td>
      <td><pre>def mostrar(self):
    if self.esta_vacia():
        return "Cola Simple: [vacía]"
    return "FRENTE -&gt; " + " -&gt; ".join(str(x) for x in self.cola) + " &lt;- FINAL"</pre></td>
    </tr>
  </tbody>
</table>

<br>

<table>
  <thead>
    <tr>
      <th colspan="4" align="center">Cola Simple (FIFO) : implementada con Nodos (Dinámica)</th>
    </tr>
    <tr>
      <th>Método / Operación</th>
      <th>C++ (<code>ColaSimpleNodos.h</code>)</th>
      <th>Java (<code>ColaSimpleNodos.java</code>)</th>
      <th>Python (<code>Colas_Simples.py</code>)</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><b>Nodo y Constructor</b></td>
      <td><pre>struct NodoColaSimple {
    int dato;
    NodoColaSimple* siguiente;
    NodoColaSimple(int val) : dato(val), siguiente(nullptr) {}
};
ColaSimpleNodos() : frente(nullptr), final(nullptr), cantidad(0) {}</pre></td>
      <td><pre>class NodoSimple {
    int dato;
    NodoSimple siguiente;
    NodoSimple(int dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}
private NodoSimple frente = null;
private NodoSimple fin = null;
private int tamano = 0;</pre></td>
      <td><pre>class ColaSimple:
    def __init__(self):
        self.cola = []</pre></td>
    </tr>
    <tr>
      <td><b>Está vacía</b></td>
      <td><pre>bool estaVacia() const {
    return frente == nullptr;
}</pre></td>
      <td><pre>public boolean estaVacia() {
    return frente == null;
}</pre></td>
      <td><pre>def esta_vacia(self):
    return len(self.cola) == 0</pre></td>
    </tr>
    <tr>
      <td><b>Encolar</b></td>
      <td><pre>void encolar(int valor) {
    NodoColaSimple* nuevo = new NodoColaSimple(valor);
    if (estaVacia()) {
        frente = final = nuevo;
    } else {
        final-&gt;siguiente = nuevo;
        final = nuevo;
    }
    cantidad++;
}</pre></td>
      <td><pre>public void encolar(int dato) {
    NodoSimple nuevo = new NodoSimple(dato);
    if (estaVacia()) {
        frente = fin = nuevo;
    } else {
        fin.siguiente = nuevo;
        fin = nuevo;
    }
    tamano++;
}</pre></td>
      <td><pre>def encolar(self, elemento):
    self.cola.append(elemento)</pre></td>
    </tr>
    <tr>
      <td><b>Desencolar</b></td>
      <td><pre>bool desencolar(int&amp; valorExtraido) {
    if (estaVacia()) return false;
    NodoColaSimple* temp = frente;
    valorExtraido = temp-&gt;dato;
    frente = frente-&gt;siguiente;
    if (frente == nullptr) {
        final = nullptr;
    }
    delete temp;
    cantidad--;
    return true;
}</pre></td>
      <td><pre>public int desencolar() {
    if (estaVacia()) {
        throw new IllegalStateException("Cola vacía");
    }
    int valor = frente.dato;
    frente = frente.siguiente;
    if (frente == null) {
        fin = null;
    }
    tamano--;
    return valor;
}</pre></td>
      <td><pre>def desencolar(self):
    if self.esta_vacia():
        return None
    return self.cola.pop(0)</pre></td>
    </tr>
    <tr>
      <td><b>Ver frente / Primero</b></td>
      <td><pre>bool verFrente(int&amp; valorFrente) const {
    if (estaVacia()) return false;
    valorFrente = frente-&gt;dato;
    return true;
}</pre></td>
      <td><pre>public int verFrente() {
    if (estaVacia()) {
        throw new IllegalStateException("Cola vacía");
    }
    return frente.dato;
}</pre></td>
      <td><pre>def primero(self):
    if self.esta_vacia():
        return None
    return self.cola[0]</pre></td>
    </tr>
    <tr>
      <td><b>Ver final / Último</b></td>
      <td><pre>bool verFinal(int&amp; valorFinal) const {
    if (estaVacia()) return false;
    valorFinal = final-&gt;dato;
    return true;
}</pre></td>
      <td><pre>public int verFinal() {
    if (estaVacia()) {
        throw new IllegalStateException("Cola vacía");
    }
    return fin.dato;
}</pre></td>
      <td><pre>def ultimo(self):
    if self.esta_vacia():
        return None
    return self.cola[-1]</pre></td>
    </tr>
    <tr>
      <td><b>Tamaño</b></td>
      <td><pre>int tamano() const {
    return cantidad;
}</pre></td>
      <td><pre>public int tamano() {
    return tamano;
}</pre></td>
      <td><pre>def tamano(self):
    return len(self.cola)</pre></td>
    </tr>
    <tr>
      <td><b>Mostrar</b></td>
      <td><pre>void mostrar() const {
    if (estaVacia()) return;
    NodoColaSimple* actual = frente;
    while (actual != nullptr) {
        std::cout &lt;&lt; actual-&gt;dato &lt;&lt; " -&gt; ";
        actual = actual-&gt;siguiente;
    }
}</pre></td>
      <td><pre>public void mostrar() {
    if (estaVacia()) return;
    StringBuilder sb = new StringBuilder("FRENTE -&gt; ");
    NodoSimple actual = frente;
    while (actual != null) {
        sb.append(actual.dato).append(" -&gt; ");
        actual = actual.siguiente;
    }
    System.out.println(sb);
}</pre></td>
      <td><pre>def mostrar(self):
    if self.esta_vacia():
        return "Cola Simple: [vacía]"
    return "FRENTE -&gt; " + " -&gt; ".join(str(x) for x in self.cola) + " &lt;- FINAL"</pre></td>
    </tr>
  </tbody>
</table>
