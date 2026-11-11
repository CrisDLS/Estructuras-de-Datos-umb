<table>
  <thead>
    <tr>
      <th colspan="4" align="center">Listas Enlazadas : Clase Base Abstracta (Contrato Polimórfico)</th>
    </tr>
    <tr>
      <th>Método / Operación</th>
      <th>C++ (<code>Lista.h</code>)</th>
      <th>Java (<code>ListaEnlazada.java</code>)</th>
      <th>Python (<code>lista.py</code>)</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><b>Clase Base y Atributos</b></td>
      <td><pre>class Lista {
public:
    virtual ~Lista() = default;
};</pre></td>
      <td><pre>public abstract class ListaEnlazada {
    protected Nodo cabeza;
    protected int tamano;
}</pre></td>
      <td><pre>class Lista(ABC):
    def __init__(self):
        self._tamano = 0</pre></td>
    </tr>
    <tr>
      <td><b>Está vacía</b></td>
      <td><pre>virtual bool estaVacia() const = 0;</pre></td>
      <td><pre>public boolean estaVacia() {
    return tamano == 0;
}</pre></td>
      <td><pre>def esta_vacia(self):
    return self._tamano == 0</pre></td>
    </tr>
    <tr>
      <td><b>Tamaño</b></td>
      <td><pre>virtual size_t tamano() const = 0;</pre></td>
      <td><pre>public int getTamano() {
    return tamano;
}</pre></td>
      <td><pre>def obtener_tamano(self):
    return self._tamano</pre></td>
    </tr>
    <tr>
      <td><b>Insertar</b></td>
      <td><pre>virtual void insertarInicio(int dato) = 0;
virtual void insertarFinal(int dato) = 0;
virtual void insertarEnPosicion(int dato, int pos) = 0;</pre></td>
      <td><pre>public abstract void insertar(int dato);</pre></td>
      <td><pre>@abstractmethod
def insertar(self, dato):
    pass</pre></td>
    </tr>
    <tr>
      <td><b>Eliminar</b></td>
      <td><pre>virtual bool eliminar(int dato) = 0;</pre></td>
      <td><pre>public abstract boolean eliminar(int dato);</pre></td>
      <td><pre>@abstractmethod
def eliminar(self, dato):
    pass</pre></td>
    </tr>
    <tr>
      <td><b>Buscar</b></td>
      <td><pre>virtual bool buscar(int dato) const = 0;</pre></td>
      <td><pre>public boolean buscar(int dato) {
    for (Nodo a = cabeza; a != null; a = a.getSiguiente()) {
        if (a.getDato() == dato) return true;
    }
    return false;
}</pre></td>
      <td><pre>@abstractmethod
def buscar(self, dato):
    pass</pre></td>
    </tr>
    <tr>
      <td><b>Mostrar</b></td>
      <td><pre>virtual void mostrar() const = 0;</pre></td>
      <td><pre>public void mostrar() {
    if (estaVacia()) {
        System.out.println("[lista vacía]");
        return;
    }
    StringBuilder sb = new StringBuilder();
    for (Nodo a = cabeza; a != null; a = a.getSiguiente()) {
        sb.append(a.getDato()).append(" -&gt; ");
    }
    System.out.println(sb + "null");
}</pre></td>
      <td><pre>@abstractmethod
def mostrar(self):
    pass</pre></td>
    </tr>
    <tr>
      <td><b>Vaciar / Limpiar</b></td>
      <td><pre>virtual void vaciar() = 0;</pre></td>
      <td><pre>public void limpiar() {
    cabeza = null;
    tamano = 0;
}</pre></td>
      <td><pre>// No definido en lista.py</pre></td>
    </tr>
  </tbody>
</table>

<br>

<table>
  <thead>
    <tr>
      <th colspan="4" align="center">Lista Simplemente Enlazada : implementada con Nodos (Dinámica)</th>
    </tr>
    <tr>
      <th>Método / Operación</th>
      <th>C++ (<code>ListaSimple.h</code>)</th>
      <th>Java (<code>ListaSimple.java</code>)</th>
      <th>Python (<code>lista_simple.py</code>)</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><b>Nodo y Constructor</b></td>
      <td><pre>struct NodoSimple {
    int dato;
    NodoSimple* siguiente;
    NodoSimple(int val, NodoSimple* sig = nullptr)
        : dato(val), siguiente(sig) {}
};
ListaSimple() : cabeza(nullptr), longitud(0) {}</pre></td>
      <td><pre>public class Nodo {
    private int dato;
    private Nodo siguiente;
    public Nodo(int dato) {
        this.dato = dato;
    }
}</pre></td>
      <td><pre>class NodoSimple:
    def __init__(self, dato):
        self.dato = dato
        self.siguiente = None</pre></td>
    </tr>
    <tr>
      <td><b>Está vacía</b></td>
      <td><pre>bool estaVacia() const {
    return cabeza == nullptr;
}</pre></td>
      <td><pre>public boolean estaVacia() {
    return tamano == 0;
}</pre></td>
      <td><pre>def esta_vacia(self):
    return self._tamano == 0</pre></td>
    </tr>
    <tr>
      <td><b>Insertar al inicio</b></td>
      <td><pre>void insertarInicio(int dato) {
    cabeza = new NodoSimple(dato, cabeza);
    longitud++;
}</pre></td>
      <td><pre>public void insertarAlInicio(int dato) {
    Nodo nuevo = new Nodo(dato);
    nuevo.setSiguiente(cabeza);
    cabeza = nuevo;
    tamano++;
}</pre></td>
      <td><pre>// No definido por separado (se asigna self.cabeza = nuevo dentro de insertar cuando está vacía)</pre></td>
    </tr>
    <tr>
      <td><b>Insertar al final</b></td>
      <td><pre>void insertarFinal(int dato) {
    if (estaVacia()) {
        insertarInicio(dato);
        return;
    }
    NodoSimple* actual = cabeza;
    while (actual-&gt;siguiente != nullptr) {
        actual = actual-&gt;siguiente;
    }
    actual-&gt;siguiente = new NodoSimple(dato, nullptr);
    longitud++;
}</pre></td>
      <td><pre>public void insertar(int dato) {
    Nodo nuevo = new Nodo(dato);
    if (cabeza == null) {
        cabeza = nuevo;
    } else {
        Nodo a = cabeza;
        while (a.getSiguiente() != null) {
            a = a.getSiguiente();
        }
        a.setSiguiente(nuevo);
    }
    tamano++;
}</pre></td>
      <td><pre>def insertar(self, dato):
    nuevo = NodoSimple(dato)
    if self.esta_vacia():
        self.cabeza = nuevo
    else:
        actual = self.cabeza
        while actual.siguiente is not None:
            actual = actual.siguiente
        actual.siguiente = nuevo
    self._tamano += 1</pre></td>
    </tr>
    <tr>
      <td><b>Insertar en posición</b></td>
      <td><pre>void insertarEnPosicion(int dato, int pos) {
    if (pos &lt;= 0 || estaVacia()) {
        insertarInicio(dato);
        return;
    }
    if (pos &gt;= static_cast&lt;int&gt;(longitud)) {
        insertarFinal(dato);
        return;
    }
    NodoSimple* actual = cabeza;
    for (int i = 0; i &lt; pos - 1; ++i) {
        actual = actual-&gt;siguiente;
    }
    actual-&gt;siguiente = new NodoSimple(dato, actual-&gt;siguiente);
    longitud++;
}</pre></td>
      <td><pre>// No definido en ListaSimple.java</pre></td>
      <td><pre>// No definido en lista_simple.py</pre></td>
    </tr>
    <tr>
      <td><b>Eliminar por valor</b></td>
      <td><pre>bool eliminar(int dato) {
    if (estaVacia()) return false;
    if (cabeza-&gt;dato == dato) {
        NodoSimple* temp = cabeza;
        cabeza = cabeza-&gt;siguiente;
        delete temp;
        longitud--;
        return true;
    }
    NodoSimple* actual = cabeza;
    while (actual-&gt;siguiente != nullptr &amp;&amp;
           actual-&gt;siguiente-&gt;dato != dato) {
        actual = actual-&gt;siguiente;
    }
    if (actual-&gt;siguiente == nullptr) return false;
    NodoSimple* aEliminar = actual-&gt;siguiente;
    actual-&gt;siguiente = aEliminar-&gt;siguiente;
    delete aEliminar;
    longitud--;
    return true;
}</pre></td>
      <td><pre>public boolean eliminar(int dato) {
    if (cabeza == null) return false;
    if (cabeza.getDato() == dato) {
        cabeza = cabeza.getSiguiente();
        tamano--;
        return true;
    }
    Nodo a = cabeza;
    while (a.getSiguiente() != null) {
        if (a.getSiguiente().getDato() == dato) {
            a.setSiguiente(a.getSiguiente().getSiguiente());
            tamano--;
            return true;
        }
        a = a.getSiguiente();
    }
    return false;
}</pre></td>
      <td><pre>def eliminar(self, dato):
    anterior = None
    actual = self.cabeza
    while actual is not None:
        if actual.dato == dato:
            if anterior is None:
                self.cabeza = actual.siguiente
            else:
                anterior.siguiente = actual.siguiente
            self._tamano -= 1
            return True
        anterior, actual = actual, actual.siguiente
    return False</pre></td>
    </tr>
    <tr>
      <td><b>Eliminar al inicio</b></td>
      <td><pre>// Validado dentro de eliminar(dato) cuando cabeza-&gt;dato == dato</pre></td>
      <td><pre>public boolean eliminarAlInicio() {
    if (estaVacia()) return false;
    cabeza = cabeza.getSiguiente();
    tamano--;
    return true;
}</pre></td>
      <td><pre>// Validado dentro de eliminar(dato) cuando anterior is None</pre></td>
    </tr>
    <tr>
      <td><b>Eliminar al final</b></td>
      <td><pre>// Validado dentro del recorrido de eliminar(dato)</pre></td>
      <td><pre>public boolean eliminarAlFinal() {
    if (estaVacia()) return false;
    if (cabeza.getSiguiente() == null) {
        cabeza = null;
        tamano = 0;
        return true;
    }
    Nodo a = cabeza;
    while (a.getSiguiente().getSiguiente() != null) {
        a = a.getSiguiente();
    }
    a.setSiguiente(null);
    tamano--;
    return true;
}</pre></td>
      <td><pre>// Validado dentro del recorrido de eliminar(dato)</pre></td>
    </tr>
    <tr>
      <td><b>Buscar</b></td>
      <td><pre>bool buscar(int dato) const {
    NodoSimple* actual = cabeza;
    while (actual != nullptr) {
        if (actual-&gt;dato == dato) return true;
        actual = actual-&gt;siguiente;
    }
    return false;
}</pre></td>
      <td><pre>public boolean buscar(int dato) {
    for (Nodo a = cabeza; a != null; a = a.getSiguiente()) {
        if (a.getDato() == dato) return true;
    }
    return false;
}</pre></td>
      <td><pre>def buscar(self, dato):
    actual = self.cabeza
    posicion = 0
    while actual is not None:
        if actual.dato == dato:
            return posicion
        actual = actual.siguiente
        posicion += 1
    return -1</pre></td>
    </tr>
    <tr>
      <td><b>Tamaño</b></td>
      <td><pre>size_t tamano() const {
    return longitud;
}</pre></td>
      <td><pre>public int getTamano() {
    return tamano;
}</pre></td>
      <td><pre>def obtener_tamano(self):
    return self._tamano</pre></td>
    </tr>
    <tr>
      <td><b>Mostrar</b></td>
      <td><pre>void mostrar() const {
    if (estaVacia()) {
        std::cout &lt;&lt; "[Lista simple vacia]" &lt;&lt; std::endl;
        return;
    }
    NodoSimple* actual = cabeza;
    while (actual != nullptr) {
        std::cout &lt;&lt; actual-&gt;dato &lt;&lt; " -&gt; ";
        actual = actual-&gt;siguiente;
    }
    std::cout &lt;&lt; "nullptr" &lt;&lt; std::endl;
}</pre></td>
      <td><pre>public void mostrar() {
    if (estaVacia()) {
        System.out.println("[lista vacía]");
        return;
    }
    StringBuilder sb = new StringBuilder();
    for (Nodo a = cabeza; a != null; a = a.getSiguiente()) {
        sb.append(a.getDato()).append(" -&gt; ");
    }
    System.out.println(sb + "null");
}</pre></td>
      <td><pre>def mostrar(self):
    valores = []
    actual = self.cabeza
    while actual is not None:
        valores.append(str(actual.dato))
        actual = actual.siguiente
    return ' -&gt; '.join(valores) + ' -&gt; None' if valores else '(lista vacía)'</pre></td>
    </tr>
    <tr>
      <td><b>Vaciar / Limpiar</b></td>
      <td><pre>void vaciar() {
    while (cabeza != nullptr) {
        NodoSimple* temp = cabeza;
        cabeza = cabeza-&gt;siguiente;
        delete temp;
    }
    longitud = 0;
}</pre></td>
      <td><pre>public void limpiar() {
    cabeza = null;
    tamano = 0;
}</pre></td>
      <td><pre>// No definido en lista_simple.py</pre></td>
    </tr>
  </tbody>
</table>

<br>

<table>
  <thead>
    <tr>
      <th colspan="4" align="center">Lista Doblemente Enlazada : implementada con Nodos (Dinámica)</th>
    </tr>
    <tr>
      <th>Método / Operación</th>
      <th>C++ (<code>ListaDoble.h</code>)</th>
      <th>Java (<code>ListaDoble.java</code>)</th>
      <th>Python (<code>lista_doble.py</code>)</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><b>Nodo y Constructor</b></td>
      <td><pre>struct NodoDoble {
    int dato;
    NodoDoble* siguiente;
    NodoDoble* anterior;
    NodoDoble(int val, NodoDoble* sig = nullptr, NodoDoble* ant = nullptr)
        : dato(val), siguiente(sig), anterior(ant) {}
};
ListaDoble() : cabeza(nullptr), cola(nullptr), longitud(0) {}</pre></td>
      <td><pre>public class NodoDoble extends Nodo {
    private NodoDoble anterior;
    public NodoDoble(int dato) {
        super(dato);
        this.anterior = null;
    }
}
private NodoDoble cola = null;</pre></td>
      <td><pre>class NodoDoble:
    def __init__(self, dato):
        self.dato = dato
        self.anterior = None
        self.siguiente = None</pre></td>
    </tr>
    <tr>
      <td><b>Está vacía</b></td>
      <td><pre>bool estaVacia() const {
    return cabeza == nullptr;
}</pre></td>
      <td><pre>public boolean estaVacia() {
    return tamano == 0;
}</pre></td>
      <td><pre>def esta_vacia(self):
    return self._tamano == 0</pre></td>
    </tr>
    <tr>
      <td><b>Insertar al inicio</b></td>
      <td><pre>void insertarInicio(int dato) {
    NodoDoble* nuevo = new NodoDoble(dato, cabeza, nullptr);
    if (estaVacia()) {
        cabeza = cola = nuevo;
    } else {
        cabeza-&gt;anterior = nuevo;
        cabeza = nuevo;
    }
    longitud++;
}</pre></td>
      <td><pre>public void insertarAlInicio(int dato) {
    NodoDoble nuevo = new NodoDoble(dato);
    if (estaVacia()) {
        cabeza = nuevo;
        cola = nuevo;
    } else {
        nuevo.setSiguiente(cabeza);
        ((NodoDoble) cabeza).setAnterior(nuevo);
        cabeza = nuevo;
    }
    tamano++;
}</pre></td>
      <td><pre>// No definido por separado (se asigna self.cabeza = self.cola = nuevo dentro de insertar)</pre></td>
    </tr>
    <tr>
      <td><b>Insertar al final</b></td>
      <td><pre>void insertarFinal(int dato) {
    if (estaVacia()) {
        insertarInicio(dato);
        return;
    }
    NodoDoble* nuevo = new NodoDoble(dato, nullptr, cola);
    cola-&gt;siguiente = nuevo;
    cola = nuevo;
    longitud++;
}</pre></td>
      <td><pre>public void insertar(int dato) {
    NodoDoble nuevo = new NodoDoble(dato);
    if (estaVacia()) {
        cabeza = nuevo;
        cola = nuevo;
    } else {
        cola.setSiguiente(nuevo);
        nuevo.setAnterior(cola);
        cola = nuevo;
    }
    tamano++;
}</pre></td>
      <td><pre>def insertar(self, dato):
    nuevo = NodoDoble(dato)
    if self.esta_vacia():
        self.cabeza = self.cola = nuevo
    else:
        nuevo.anterior = self.cola
        self.cola.siguiente = nuevo
        self.cola = nuevo
    self._tamano += 1</pre></td>
    </tr>
    <tr>
      <td><b>Insertar en posición</b></td>
      <td><pre>void insertarEnPosicion(int dato, int pos) {
    if (pos &lt;= 0 || estaVacia()) {
        insertarInicio(dato);
        return;
    }
    if (pos &gt;= static_cast&lt;int&gt;(longitud)) {
        insertarFinal(dato);
        return;
    }
    NodoDoble* actual = cabeza;
    for (int i = 0; i &lt; pos; ++i) {
        actual = actual-&gt;siguiente;
    }
    NodoDoble* nuevo = new NodoDoble(dato, actual, actual-&gt;anterior);
    actual-&gt;anterior-&gt;siguiente = nuevo;
    actual-&gt;anterior = nuevo;
    longitud++;
}</pre></td>
      <td><pre>// No definido en ListaDoble.java</pre></td>
      <td><pre>// No definido en lista_doble.py</pre></td>
    </tr>
    <tr>
      <td><b>Eliminar por valor</b></td>
      <td><pre>bool eliminar(int dato) {
    if (estaVacia()) return false;
    NodoDoble* actual = cabeza;
    while (actual != nullptr &amp;&amp; actual-&gt;dato != dato) {
        actual = actual-&gt;siguiente;
    }
    if (actual == nullptr) return false;
    if (cabeza == cola) {
        cabeza = cola = nullptr;
    } else if (actual == cabeza) {
        cabeza = cabeza-&gt;siguiente;
        cabeza-&gt;anterior = nullptr;
    } else if (actual == cola) {
        cola = cola-&gt;anterior;
        cola-&gt;siguiente = nullptr;
    } else {
        actual-&gt;anterior-&gt;siguiente = actual-&gt;siguiente;
        actual-&gt;siguiente-&gt;anterior = actual-&gt;anterior;
    }
    delete actual;
    longitud--;
    return true;
}</pre></td>
      <td><pre>public boolean eliminar(int dato) {
    if (estaVacia()) return false;
    NodoDoble actual = (NodoDoble) cabeza;
    while (actual != null) {
        if (actual.getDato() == dato) {
            if (actual.getAnterior() != null) {
                actual.getAnterior().setSiguiente(actual.getSiguiente());
            } else {
                cabeza = actual.getSiguiente();
            }
            if (actual.getSiguiente() != null) {
                actual.getSiguiente().setAnterior(actual.getAnterior());
            } else {
                cola = actual.getAnterior();
            }
            tamano--;
            return true;
        }
        actual = actual.getSiguiente();
    }
    return false;
}</pre></td>
      <td><pre>def eliminar(self, dato):
    actual = self.cabeza
    while actual is not None:
        if actual.dato == dato:
            if actual.anterior is None:
                self.cabeza = actual.siguiente
            else:
                actual.anterior.siguiente = actual.siguiente
            if actual.siguiente is None:
                self.cola = actual.anterior
            else:
                actual.siguiente.anterior = actual.anterior
            self._tamano -= 1
            return True
        actual = actual.siguiente
    return False</pre></td>
    </tr>
    <tr>
      <td><b>Eliminar al inicio</b></td>
      <td><pre>// Validado dentro de eliminar(dato) cuando actual == cabeza</pre></td>
      <td><pre>public boolean eliminarAlInicio() {
    if (estaVacia()) return false;
    if (cabeza == cola) {
        cabeza = null;
        cola = null;
    } else {
        cabeza = cabeza.getSiguiente();
        ((NodoDoble) cabeza).setAnterior(null);
    }
    tamano--;
    return true;
}</pre></td>
      <td><pre>// Validado dentro de eliminar(dato) cuando actual.anterior is None</pre></td>
    </tr>
    <tr>
      <td><b>Eliminar al final</b></td>
      <td><pre>// Validado dentro de eliminar(dato) cuando actual == cola</pre></td>
      <td><pre>public boolean eliminarAlFinal() {
    if (estaVacia()) return false;
    if (cabeza == cola) {
        cabeza = null;
        cola = null;
    } else {
        cola = cola.getAnterior();
        cola.setSiguiente(null);
    }
    tamano--;
    return true;
}</pre></td>
      <td><pre>// Validado dentro de eliminar(dato) cuando actual.siguiente is None</pre></td>
    </tr>
    <tr>
      <td><b>Buscar</b></td>
      <td><pre>bool buscar(int dato) const {
    NodoDoble* actual = cabeza;
    while (actual != nullptr) {
        if (actual-&gt;dato == dato) return true;
        actual = actual-&gt;siguiente;
    }
    return false;
}</pre></td>
      <td><pre>public boolean buscar(int dato) {
    for (Nodo a = cabeza; a != null; a = a.getSiguiente()) {
        if (a.getDato() == dato) return true;
    }
    return false;
}</pre></td>
      <td><pre>def buscar(self, dato):
    actual = self.cabeza
    posicion = 0
    while actual is not None:
        if actual.dato == dato:
            return posicion
        actual = actual.siguiente
        posicion += 1
    return -1</pre></td>
    </tr>
    <tr>
      <td><b>Mostrar normal</b></td>
      <td><pre>void mostrar() const {
    if (estaVacia()) {
        std::cout &lt;&lt; "[Lista doble vacia]" &lt;&lt; std::endl;
        return;
    }
    std::cout &lt;&lt; "nullptr &lt;-&gt; ";
    NodoDoble* actual = cabeza;
    while (actual != nullptr) {
        std::cout &lt;&lt; actual-&gt;dato &lt;&lt; " &lt;-&gt; ";
        actual = actual-&gt;siguiente;
    }
    std::cout &lt;&lt; "nullptr" &lt;&lt; std::endl;
}</pre></td>
      <td><pre>public void mostrar() {
    if (estaVacia()) {
        System.out.println("[lista vacía]");
        return;
    }
    StringBuilder sb = new StringBuilder();
    for (Nodo a = cabeza; a != null; a = a.getSiguiente()) {
        sb.append(a.getDato()).append(" -&gt; ");
    }
    System.out.println(sb + "null");
}</pre></td>
      <td><pre>def mostrar(self):
    valores = []
    actual = self.cabeza
    while actual is not None:
        valores.append(str(actual.dato))
        actual = actual.siguiente
    return 'None &lt;- ' + ' &lt;-&gt; '.join(valores) + ' -&gt; None' if valores else '(lista vacía)'</pre></td>
    </tr>
    <tr>
      <td><b>Mostrar inverso</b></td>
      <td><pre>void mostrarInverso() const {
    if (estaVacia()) {
        std::cout &lt;&lt; "[Lista doble vacia]" &lt;&lt; std::endl;
        return;
    }
    std::cout &lt;&lt; "nullptr &lt;-&gt; ";
    NodoDoble* actual = cola;
    while (actual != nullptr) {
        std::cout &lt;&lt; actual-&gt;dato &lt;&lt; " &lt;-&gt; ";
        actual = actual-&gt;anterior;
    }
    std::cout &lt;&lt; "nullptr" &lt;&lt; std::endl;
}</pre></td>
      <td><pre>public void mostrarInverso() {
    if (estaVacia()) {
        System.out.println("[lista vacía]");
        return;
    }
    StringBuilder sb = new StringBuilder("null &lt;- ");
    for (NodoDoble a = cola; a != null; a = a.getAnterior()) {
        sb.append(a.getDato()).append(" &lt;-&gt; ");
    }
    System.out.println(sb + "[cabeza]");
}</pre></td>
      <td><pre>def mostrar_inverso(self):
    valores = []
    actual = self.cola
    while actual is not None:
        valores.append(str(actual.dato))
        actual = actual.anterior
    return 'None &lt;- ' + ' &lt;-&gt; '.join(valores) + ' -&gt; None' if valores else '(lista vacía)'</pre></td>
    </tr>
    <tr>
      <td><b>Vaciar / Limpiar</b></td>
      <td><pre>void vaciar() {
    NodoDoble* actual = cabeza;
    while (actual != nullptr) {
        NodoDoble* temp = actual;
        actual = actual-&gt;siguiente;
        delete temp;
    }
    cabeza = nullptr;
    cola = nullptr;
    longitud = 0;
}</pre></td>
      <td><pre>public void limpiar() {
    super.limpiar();
    cola = null;
}</pre></td>
      <td><pre>// No definido en lista_doble.py</pre></td>
    </tr>
  </tbody>
</table>

<br>

<table>
  <thead>
    <tr>
      <th colspan="4" align="center">Lista Enlazada Circular : implementada con Nodos (Dinámica)</th>
    </tr>
    <tr>
      <th>Método / Operación</th>
      <th>C++ (<code>ListaCircular.h</code>)</th>
      <th>Java (<code>ListaCircular.java</code>)</th>
      <th>Python (<code>lista_circular.py</code>)</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><b>Nodo y Constructor</b></td>
      <td><pre>struct NodoCircular {
    int dato;
    NodoCircular* siguiente;
    NodoCircular(int val, NodoCircular* sig = nullptr)
        : dato(val), siguiente(sig) {}
};
ListaCircular() : cabeza(nullptr), longitud(0) {}</pre></td>
      <td><pre>public class Nodo {
    private int dato;
    private Nodo siguiente;
    public Nodo(int dato) {
        this.dato = dato;
    }
}</pre></td>
      <td><pre>class NodoCircular:
    def __init__(self, dato):
        self.dato = dato
        self.siguiente = None</pre></td>
    </tr>
    <tr>
      <td><b>Está vacía</b></td>
      <td><pre>bool estaVacia() const {
    return cabeza == nullptr;
}</pre></td>
      <td><pre>public boolean estaVacia() {
    return tamano == 0;
}</pre></td>
      <td><pre>def esta_vacia(self):
    return self._tamano == 0</pre></td>
    </tr>
    <tr>
      <td><b>Insertar al inicio</b></td>
      <td><pre>void insertarInicio(int dato) {
    NodoCircular* nuevo = new NodoCircular(dato);
    if (estaVacia()) {
        nuevo-&gt;siguiente = nuevo;
        cabeza = nuevo;
    } else {
        NodoCircular* ultimo = cabeza;
        while (ultimo-&gt;siguiente != cabeza) {
            ultimo = ultimo-&gt;siguiente;
        }
        nuevo-&gt;siguiente = cabeza;
        ultimo-&gt;siguiente = nuevo;
        cabeza = nuevo;
    }
    longitud++;
}</pre></td>
      <td><pre>public void insertarAlInicio(int dato) {
    Nodo nuevo = new Nodo(dato);
    if (estaVacia()) {
        cabeza = nuevo;
        nuevo.setSiguiente(cabeza);
    } else {
        Nodo ultimo = cabeza;
        while (ultimo.getSiguiente() != cabeza) {
            ultimo = ultimo.getSiguiente();
        }
        nuevo.setSiguiente(cabeza);
        ultimo.setSiguiente(nuevo);
        cabeza = nuevo;
    }
    tamano++;
}</pre></td>
      <td><pre>// No definido por separado (se asigna self.cabeza = nuevo dentro de insertar)</pre></td>
    </tr>
    <tr>
      <td><b>Insertar al final</b></td>
      <td><pre>void insertarFinal(int dato) {
    if (estaVacia()) {
        insertarInicio(dato);
        return;
    }
    NodoCircular* nuevo = new NodoCircular(dato, cabeza);
    NodoCircular* ultimo = cabeza;
    while (ultimo-&gt;siguiente != cabeza) {
        ultimo = ultimo-&gt;siguiente;
    }
    ultimo-&gt;siguiente = nuevo;
    longitud++;
}</pre></td>
      <td><pre>public void insertar(int dato) {
    Nodo nuevo = new Nodo(dato);
    if (estaVacia()) {
        cabeza = nuevo;
        nuevo.setSiguiente(cabeza);
    } else {
        Nodo actual = cabeza;
        while (actual.getSiguiente() != cabeza) {
            actual = actual.getSiguiente();
        }
        actual.setSiguiente(nuevo);
        nuevo.setSiguiente(cabeza);
    }
    tamano++;
}</pre></td>
      <td><pre>def insertar(self, dato):
    nuevo = NodoCircular(dato)
    if self.esta_vacia():
        self.cabeza = nuevo
        self.cola = nuevo
        nuevo.siguiente = self.cabeza
    else:
        nuevo.siguiente = self.cabeza
        self.cola.siguiente = nuevo
        self.cola = nuevo
    self._tamano += 1</pre></td>
    </tr>
    <tr>
      <td><b>Insertar en posición</b></td>
      <td><pre>void insertarEnPosicion(int dato, int pos) {
    if (pos &lt;= 0 || estaVacia()) {
        insertarInicio(dato);
        return;
    }
    if (pos &gt;= static_cast&lt;int&gt;(longitud)) {
        insertarFinal(dato);
        return;
    }
    NodoCircular* actual = cabeza;
    for (int i = 0; i &lt; pos - 1; ++i) {
        actual = actual-&gt;siguiente;
    }
    actual-&gt;siguiente = new NodoCircular(dato, actual-&gt;siguiente);
    longitud++;
}</pre></td>
      <td><pre>// No definido en ListaCircular.java</pre></td>
      <td><pre>// No definido en lista_circular.py</pre></td>
    </tr>
    <tr>
      <td><b>Eliminar por valor</b></td>
      <td><pre>bool eliminar(int dato) {
    if (estaVacia()) return false;
    if (cabeza-&gt;dato == dato) {
        if (cabeza-&gt;siguiente == cabeza) {
            delete cabeza;
            cabeza = nullptr;
        } else {
            NodoCircular* ultimo = cabeza;
            while (ultimo-&gt;siguiente != cabeza) {
                ultimo = ultimo-&gt;siguiente;
            }
            NodoCircular* temp = cabeza;
            cabeza = cabeza-&gt;siguiente;
            ultimo-&gt;siguiente = cabeza;
            delete temp;
        }
        longitud--;
        return true;
    }
    NodoCircular* actual = cabeza;
    while (actual-&gt;siguiente != cabeza &amp;&amp;
           actual-&gt;siguiente-&gt;dato != dato) {
        actual = actual-&gt;siguiente;
    }
    if (actual-&gt;siguiente == cabeza) return false;
    NodoCircular* aEliminar = actual-&gt;siguiente;
    actual-&gt;siguiente = aEliminar-&gt;siguiente;
    delete aEliminar;
    longitud--;
    return true;
}</pre></td>
      <td><pre>public boolean eliminar(int dato) {
    if (estaVacia()) return false;
    Nodo actual = cabeza;
    Nodo anterior = null;
    Nodo ultimo = cabeza;
    while (ultimo.getSiguiente() != cabeza) {
        ultimo = ultimo.getSiguiente();
    }
    do {
        if (actual.getDato() == dato) {
            if (actual == cabeza) {
                if (tamano == 1) {
                    cabeza = null;
                } else {
                    cabeza = cabeza.getSiguiente();
                    ultimo.setSiguiente(cabeza);
                }
            } else {
                anterior.setSiguiente(actual.getSiguiente());
            }
            tamano--;
            return true;
        }
        anterior = actual;
        actual = actual.getSiguiente();
    } while (actual != cabeza);
    return false;
}</pre></td>
      <td><pre>def eliminar(self, dato):
    if self.esta_vacia():
        return False
    actual = self.cabeza
    anterior = self.cola
    for _ in range(self._tamano):
        if actual.dato == dato:
            if self._tamano == 1:
                self.cabeza = None
                self.cola = None
            else:
                anterior.siguiente = actual.siguiente
                if actual == self.cabeza:
                    self.cabeza = actual.siguiente
                if actual == self.cola:
                    self.cola = anterior
                self.cola.siguiente = self.cabeza
            self._tamano -= 1
            return True
        anterior = actual
        actual = actual.siguiente
    return False</pre></td>
    </tr>
    <tr>
      <td><b>Eliminar al inicio</b></td>
      <td><pre>// Validado dentro de eliminar(dato) cuando cabeza-&gt;dato == dato</pre></td>
      <td><pre>public boolean eliminarAlInicio() {
    if (estaVacia()) return false;
    if (tamano == 1) {
        cabeza = null;
    } else {
        Nodo ultimo = cabeza;
        while (ultimo.getSiguiente() != cabeza) {
            ultimo = ultimo.getSiguiente();
        }
        cabeza = cabeza.getSiguiente();
        ultimo.setSiguiente(cabeza);
    }
    tamano--;
    return true;
}</pre></td>
      <td><pre>// Validado dentro de eliminar(dato) cuando actual == self.cabeza</pre></td>
    </tr>
    <tr>
      <td><b>Eliminar al final</b></td>
      <td><pre>// Validado dentro del recorrido de eliminar(dato)</pre></td>
      <td><pre>public boolean eliminarAlFinal() {
    if (estaVacia()) return false;
    if (tamano == 1) {
        cabeza = null;
    } else {
        Nodo anterior = null;
        Nodo actual = cabeza;
        while (actual.getSiguiente() != cabeza) {
            anterior = actual;
            actual = actual.getSiguiente();
        }
        anterior.setSiguiente(cabeza);
    }
    tamano--;
    return true;
}</pre></td>
      <td><pre>// Validado dentro de eliminar(dato) cuando actual == self.cola</pre></td>
    </tr>
    <tr>
      <td><b>Buscar</b></td>
      <td><pre>bool buscar(int dato) const {
    if (estaVacia()) return false;
    NodoCircular* actual = cabeza;
    do {
        if (actual-&gt;dato == dato) return true;
        actual = actual-&gt;siguiente;
    } while (actual != cabeza);
    return false;
}</pre></td>
      <td><pre>public boolean buscar(int dato) {
    if (estaVacia()) return false;
    Nodo actual = cabeza;
    do {
        if (actual.getDato() == dato) return true;
        actual = actual.getSiguiente();
    } while (actual != cabeza);
    return false;
}</pre></td>
      <td><pre>def buscar(self, dato):
    actual = self.cabeza
    for posicion in range(self._tamano):
        if actual.dato == dato:
            return posicion
        actual = actual.siguiente
    return -1</pre></td>
    </tr>
    <tr>
      <td><b>Mostrar</b></td>
      <td><pre>void mostrar() const {
    if (estaVacia()) {
        std::cout &lt;&lt; "[Lista circular vacia]" &lt;&lt; std::endl;
        return;
    }
    NodoCircular* actual = cabeza;
    do {
        std::cout &lt;&lt; actual-&gt;dato &lt;&lt; " -&gt; ";
        actual = actual-&gt;siguiente;
    } while (actual != cabeza);
    std::cout &lt;&lt; "(regresa a cabeza: " &lt;&lt; cabeza-&gt;dato &lt;&lt; ")" &lt;&lt; std::endl;
}</pre></td>
      <td><pre>public void mostrar() {
    if (estaVacia()) {
        System.out.println("[lista vacía]");
        return;
    }
    StringBuilder sb = new StringBuilder();
    Nodo actual = cabeza;
    do {
        sb.append(actual.getDato()).append(" -&gt; ");
        actual = actual.getSiguiente();
    } while (actual != cabeza);
    sb.append("(cabeza)");
    System.out.println(sb);
}</pre></td>
      <td><pre>def mostrar(self):
    if self.esta_vacia():
        return '(lista vacía)'
    valores = []
    actual = self.cabeza
    for _ in range(self._tamano):
        valores.append(str(actual.dato))
        actual = actual.siguiente
    return ' -&gt; '.join(valores) + ' -&gt; vuelve a ' + str(self.cabeza.dato)</pre></td>
    </tr>
    <tr>
      <td><b>Vaciar / Limpiar</b></td>
      <td><pre>void vaciar() {
    if (estaVacia()) return;
    NodoCircular* ultimo = cabeza;
    while (ultimo-&gt;siguiente != cabeza) {
        ultimo = ultimo-&gt;siguiente;
    }
    ultimo-&gt;siguiente = nullptr;
    while (cabeza != nullptr) {
        NodoCircular* temp = cabeza;
        cabeza = cabeza-&gt;siguiente;
        delete temp;
    }
    cabeza = nullptr;
    longitud = 0;
}</pre></td>
      <td><pre>public void limpiar() {
    cabeza = null;
    tamano = 0;
}</pre></td>
      <td><pre>// No definido en lista_circular.py</pre></td>
    </tr>
  </tbody>
</table>

<br>

<table>
  <thead>
    <tr>
      <th colspan="4" align="center">Multilista : implementada con Nodos (Dinámica)</th>
    </tr>
    <tr>
      <th>Método / Operación</th>
      <th>C++ (<code>Multilista.h</code>)</th>
      <th>Java (<code>ListaMulti.java</code>)</th>
      <th>Python (<code>multilista.py</code>)</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><b>Nodo y Constructor</b></td>
      <td><pre>struct NodoSublista {
    int dato;
    NodoSublista* siguiente;
    NodoSublista(int val, NodoSublista* sig = nullptr)
        : dato(val), siguiente(sig) {}
};
struct NodoMultilista {
    int dato;
    NodoSublista* sublista;
    NodoMultilista* siguiente;
    NodoMultilista(int val, NodoMultilista* sig = nullptr)
        : dato(val), sublista(nullptr), siguiente(sig) {}
};</pre></td>
      <td><pre>public class NodoMulti extends Nodo {
    private String nombre;
    private NodoMulti siguienteEdad;
    public NodoMulti(String nombre, int edad) {
        super(edad);
        this.nombre = nombre;
        this.siguienteEdad = null;
    }
}
protected NodoMulti cabezaEdad = null;</pre></td>
      <td><pre>class Nodot:
    def __init__(self, dato):
        self.dato = dato
        self.siguiente = None
        self.abajo = None</pre></td>
    </tr>
    <tr>
      <td><b>Insertar principal</b></td>
      <td><pre>void insertarFinal(int dato) {
    if (estaVacia()) {
        cabeza = new NodoMultilista(dato, cabeza);
        longitud++;
        return;
    }
    NodoMultilista* actual = cabeza;
    while (actual-&gt;siguiente != nullptr) {
        actual = actual-&gt;siguiente;
    }
    actual-&gt;siguiente = new NodoMultilista(dato, nullptr);
    longitud++;
}</pre></td>
      <td><pre>public void insertar(String nombre, int edad) {
    NodoMulti nuevo = new NodoMulti(nombre.trim(), edad);
    if (cabeza == null || nuevo.getNombre().compareToIgnoreCase(((NodoMulti) cabeza).getNombre()) &lt;= 0) {
        nuevo.setSiguiente(cabeza);
        cabeza = nuevo;
    } else {
        NodoMulti actualAlfa = (NodoMulti) cabeza;
        while (actualAlfa.getSiguiente() != null &amp;&amp;
               actualAlfa.getSiguiente().getNombre().compareToIgnoreCase(nuevo.getNombre()) &lt;= 0) {
            actualAlfa = actualAlfa.getSiguiente();
        }
        nuevo.setSiguiente(actualAlfa.getSiguiente());
        actualAlfa.setSiguiente(nuevo);
    }
    if (cabezaEdad == null || edad &lt;= cabezaEdad.getEdad()) {
        nuevo.setSiguienteEdad(cabezaEdad);
        cabezaEdad = nuevo;
    } else {
        NodoMulti actualEdad = cabezaEdad;
        while (actualEdad.getSiguienteEdad() != null &amp;&amp;
               actualEdad.getSiguienteEdad().getEdad() &lt;= edad) {
            actualEdad = actualEdad.getSiguienteEdad();
        }
        nuevo.setSiguienteEdad(actualEdad.getSiguienteEdad());
        actualEdad.setSiguienteEdad(nuevo);
    }
    tamano++;
}</pre></td>
      <td><pre>def insertar_principal(self, dato):
    nuevo = Nodot(dato)
    if self.cabeza is None:
        self.cabeza = nuevo
    else:
        actual = self.cabeza
        while actual.siguiente is not None:
            actual = actual.siguiente
        actual.siguiente = nuevo
    self._tamano += 1
    return nuevo</pre></td>
    </tr>
    <tr>
      <td><b>Insertar en sublista</b></td>
      <td><pre>bool insertarEnSublista(int idPrincipal, int datoSublista) {
    NodoMultilista* actual = cabeza;
    while (actual != nullptr &amp;&amp; actual-&gt;dato != idPrincipal) {
        actual = actual-&gt;siguiente;
    }
    if (actual == nullptr) return false;
    if (actual-&gt;sublista == nullptr) {
        actual-&gt;sublista = new NodoSublista(datoSublista);
    } else {
        NodoSublista* sub = actual-&gt;sublista;
        while (sub-&gt;siguiente != nullptr) {
            sub = sub-&gt;siguiente;
        }
        sub-&gt;siguiente = new NodoSublista(datoSublista);
    }
    return true;
}</pre></td>
      <td><pre>// Enlazado en el segundo criterio (cabezaEdad) dentro de insertar(nombre, edad)</pre></td>
      <td><pre>def insertar_sub(self, principal, dato):
    padre = self.buscar_principal(principal)
    if padre is None:
        return False
    nuevo = Nodot(dato)
    if padre.abajo is None:
        padre.abajo = nuevo
    else:
        actual = padre.abajo
        while actual.siguiente is not None:
            actual = actual.siguiente
        actual.siguiente = nuevo
    self._tamano += 1
    return True</pre></td>
    </tr>
    <tr>
      <td><b>Eliminar principal</b></td>
      <td><pre>bool eliminar(int dato) {
    if (estaVacia()) return false;
    if (cabeza-&gt;dato == dato) {
        NodoMultilista* temp = cabeza;
        cabeza = cabeza-&gt;siguiente;
        liberarSublista(temp-&gt;sublista);
        delete temp;
        longitud--;
        return true;
    }
    NodoMultilista* actual = cabeza;
    while (actual-&gt;siguiente != nullptr &amp;&amp;
           actual-&gt;siguiente-&gt;dato != dato) {
        actual = actual-&gt;siguiente;
    }
    if (actual-&gt;siguiente == nullptr) return false;
    NodoMultilista* aEliminar = actual-&gt;siguiente;
    actual-&gt;siguiente = aEliminar-&gt;siguiente;
    liberarSublista(aEliminar-&gt;sublista);
    delete aEliminar;
    longitud--;
    return true;
}</pre></td>
      <td><pre>public boolean eliminar(String nombre) {
    if (estaVacia() || nombre == null || !buscar(nombre)) {
        return false;
    }
    if (((NodoMulti) cabeza).getNombre().equalsIgnoreCase(nombre)) {
        cabeza = cabeza.getSiguiente();
    } else {
        Nodo actualAlfa = cabeza;
        while (actualAlfa.getSiguiente() != null &amp;&amp;
               !((NodoMulti) actualAlfa.getSiguiente()).getNombre().equalsIgnoreCase(nombre)) {
            actualAlfa = actualAlfa.getSiguiente();
        }
        if (actualAlfa.getSiguiente() != null) {
            actualAlfa.setSiguiente(actualAlfa.getSiguiente().getSiguiente());
        }
    }
    if (cabezaEdad != null &amp;&amp; cabezaEdad.getNombre().equalsIgnoreCase(nombre)) {
        cabezaEdad = cabezaEdad.getSiguienteEdad();
    } else if (cabezaEdad != null) {
        NodoMulti actualEdad = cabezaEdad;
        while (actualEdad.getSiguienteEdad() != null &amp;&amp;
               !actualEdad.getSiguienteEdad().getNombre().equalsIgnoreCase(nombre)) {
            actualEdad = actualEdad.getSiguienteEdad();
        }
        if (actualEdad.getSiguienteEdad() != null) {
            actualEdad.setSiguienteEdad(actualEdad.getSiguienteEdad().getSiguienteEdad());
        }
    }
    tamano--;
    return true;
}</pre></td>
      <td><pre>def eliminar_principal(self, dato):
    anterior = None
    actual = self.cabeza
    while actual is not None and actual.dato != dato:
        anterior, actual = actual, actual.siguiente
    if actual is None:
        return False
    sublista_eliminada = self.contar_sub(dato)
    if anterior is None:
        self.cabeza = actual.siguiente
    else:
        anterior.siguiente = actual.siguiente
    self._tamano -= 1 + sublista_eliminada
    return True</pre></td>
    </tr>
    <tr>
      <td><b>Eliminar de sublista</b></td>
      <td><pre>bool eliminarDeSublista(int idPrincipal, int datoSublista) {
    NodoMultilista* actual = cabeza;
    while (actual != nullptr &amp;&amp; actual-&gt;dato != idPrincipal) {
        actual = actual-&gt;siguiente;
    }
    if (actual == nullptr || actual-&gt;sublista == nullptr) return false;
    if (actual-&gt;sublista-&gt;dato == datoSublista) {
        NodoSublista* temp = actual-&gt;sublista;
        actual-&gt;sublista = actual-&gt;sublista-&gt;siguiente;
        delete temp;
        return true;
    }
    NodoSublista* sub = actual-&gt;sublista;
    while (sub-&gt;siguiente != nullptr &amp;&amp;
           sub-&gt;siguiente-&gt;dato != datoSublista) {
        sub = sub-&gt;siguiente;
    }
    if (sub-&gt;siguiente == nullptr) return false;
    NodoSublista* aEliminar = sub-&gt;siguiente;
    sub-&gt;siguiente = aEliminar-&gt;siguiente;
    delete aEliminar;
    return true;
}</pre></td>
      <td><pre>public boolean eliminar(int dato) {
    if (estaVacia()) return false;
    NodoMulti actual = cabezaEdad;
    while (actual != null) {
        if (actual.getEdad() == dato) {
            return eliminar(actual.getNombre());
        }
        if (actual.getEdad() &gt; dato) break;
        actual = actual.getSiguienteEdad();
    }
    return false;
}</pre></td>
      <td><pre>def eliminar_sub(self, principal, dato):
    padre = self.buscar_principal(principal)
    if padre is None:
        return False
    anterior = None
    actual = padre.abajo
    while actual is not None and actual.dato != dato:
        anterior, actual = actual, actual.siguiente
    if actual is None:
        return False
    if anterior is None:
        padre.abajo = actual.siguiente
    else:
        anterior.siguiente = actual.siguiente
    self._tamano -= 1
    return True</pre></td>
    </tr>
    <tr>
      <td><b>Buscar</b></td>
      <td><pre>bool buscar(int dato) const {
    NodoMultilista* actual = cabeza;
    while (actual != nullptr) {
        if (actual-&gt;dato == dato) return true;
        NodoSublista* subActual = actual-&gt;sublista;
        while (subActual != nullptr) {
            if (subActual-&gt;dato == dato) return true;
            subActual = subActual-&gt;siguiente;
        }
        actual = actual-&gt;siguiente;
    }
    return false;
}</pre></td>
      <td><pre>public boolean buscar(String nombre) {
    if (nombre == null) return false;
    for (Nodo a = cabeza; a != null; a = a.getSiguiente()) {
        NodoMulti nm = (NodoMulti) a;
        int cmp = nm.getNombre().compareToIgnoreCase(nombre);
        if (cmp == 0) return true;
        if (cmp &gt; 0) return false;
    }
    return false;
}</pre></td>
      <td><pre>def buscar(self, dato):
    posicion = 0
    principal = self.cabeza
    while principal is not None:
        if principal.dato == dato:
            return posicion
        posicion += 1
        sub = principal.abajo
        while sub is not None:
            if sub.dato == dato:
                return posicion
            posicion += 1
            sub = sub.siguiente
        principal = principal.siguiente
    return -1</pre></td>
    </tr>
    <tr>
      <td><b>Mostrar</b></td>
      <td><pre>void mostrar() const {
    if (estaVacia()) {
        std::cout &lt;&lt; "[Multilista vacia]" &lt;&lt; std::endl;
        return;
    }
    NodoMultilista* actual = cabeza;
    while (actual != nullptr) {
        std::cout &lt;&lt; "[Principal: " &lt;&lt; actual-&gt;dato &lt;&lt; "] -&gt; ";
        NodoSublista* sub = actual-&gt;sublista;
        while (sub != nullptr) {
            std::cout &lt;&lt; sub-&gt;dato &lt;&lt; " ";
            sub = sub-&gt;siguiente;
        }
        std::cout &lt;&lt; std::endl;
        actual = actual-&gt;siguiente;
    }
}</pre></td>
      <td><pre>public void mostrar() {
    if (estaVacia()) {
        System.out.println("[Multilista vacía]");
        return;
    }
    mostrarAlfabetico();
    mostrarPorEdad();
}</pre></td>
      <td><pre>def mostrar(self):
    if self.cabeza is None:
        return "(multilista vacía)"
    lineas = []
    principal = self.cabeza
    while principal is not None:
        lineas.append(str(principal.dato))
        sub = principal.abajo
        while sub is not None:
            lineas.append(f"   |-- {sub.dato}")
            sub = sub.siguiente
        principal = principal.siguiente
    return "\n".join(lineas)</pre></td>
    </tr>
    <tr>
      <td><b>Vaciar / Limpiar</b></td>
      <td><pre>void vaciar() {
    while (cabeza != nullptr) {
        NodoMultilista* temp = cabeza;
        cabeza = cabeza-&gt;siguiente;
        liberarSublista(temp-&gt;sublista);
        delete temp;
    }
    longitud = 0;
}</pre></td>
      <td><pre>public void limpiar() {
    super.limpiar();
    this.cabezaEdad = null;
}</pre></td>
      <td><pre>def vaciar(self):
    self.cabeza = None
    self._tamano = 0</pre></td>
    </tr>
  </tbody>
</table>
