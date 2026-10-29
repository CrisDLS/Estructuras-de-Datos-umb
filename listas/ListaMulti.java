package listas;
public class ListaMulti {
    class Nodo { int dato; Nodo siguiente, abajo; Nodo(int d){dato=d;} }
    private Nodo cabeza; private int tamano=0;
    public void insertar(int d){ Nodo n=new Nodo(d); if(cabeza==null) cabeza=n; else { Nodo t=cabeza; while(t.siguiente!=null) t=t.siguiente; t.siguiente=n; } tamano++; System.out.println("Insertado en multi: "+d); }
    public int getTamano(){ return tamano; }
    public boolean estaVacia(){ return tamano==0; }
    public void mostrar(){ Nodo t=cabeza; while(t!=null){ System.out.print(t.dato+" "); t=t.siguiente; } System.out.println(); }
}