package listas;
public class ListaSimple {
    class Nodo { int dato; Nodo siguiente; Nodo(int d){dato=d;} }
    private Nodo cabeza; private int tamano=0;
    public void insertar(int d){ Nodo n=new Nodo(d); if(cabeza==null) cabeza=n; else { Nodo t=cabeza; while(t.siguiente!=null) t=t.siguiente; t.siguiente=n; } tamano++; }
    public void insertarAlInicio(int d){ Nodo n=new Nodo(d); n.siguiente=cabeza; cabeza=n; tamano++; }
    public void eliminarAlInicio(){ if(cabeza!=null){ cabeza=cabeza.siguiente; tamano--; } }
    public void eliminarAlFinal(){ if(cabeza!=null){ if(cabeza.siguiente==null) cabeza=null; else { Nodo t=cabeza; while(t.siguiente.siguiente!=null) t=t.siguiente; t.siguiente=null; } tamano--; } }
    public int getTamano(){ return tamano; }
    public boolean estaVacia(){ return tamano==0; }
    public void vaciar(){ cabeza=null; tamano=0; }
    public boolean buscar(int d){ Nodo t=cabeza; while(t!=null){ if(t.dato==d) return true; t=t.siguiente; } return false; }
    public void mostrar(){ Nodo t=cabeza; while(t!=null){ System.out.print(t.dato+" "); t=t.siguiente; } System.out.println(); }
}