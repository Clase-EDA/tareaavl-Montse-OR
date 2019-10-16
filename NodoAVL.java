/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TareaAVL;

/**
 *
 * @author bratz
 */
public class NodoAVL<T extends Comparable<T>> {
    protected T element;
    protected int fe;
    protected NodoAVL<T> izq, der, papa;
    
    public NodoAVL(T elem){
        element=elem;
        papa=null;
        izq=null;
        der=null;
        fe=0;
    }
    public int numDescendientes(){
        int hijos=0;
        if(izq!=null){
            hijos+=1+izq.numDescendientes();
        }
        if(der!=null){
            hijos+=1+der.numDescendientes();
        }
        return hijos;
    }
    public int numDescendientesIzq(){
        int hijosI=0;
        if(izq!=null){
            hijosI=izq.numDescendientesIzq()+1;
        }
        return hijosI;
    }
    public int numDescendientesDer(){
        int hijosD=0;
        if(der!=null){
            hijosD=der.numDescendientesDer()+1;
        }
        return hijosD;
    }
    
    public int getAltura(NodoAVL<T> nodo){
        if(nodo==null)
            return 0;
        else
            return Math.max(nodo.numDescendientesDer(), nodo.numDescendientesIzq());
    }

    public int getFe() {
        return fe;
    }

    public void setFe(int fe) {
         this.fe=fe;
    }
    public T getElement() {
        return element;
    }

    public NodoAVL<T> getIzq() {
        return izq;
    }

    public NodoAVL<T> getDer() {
        return der;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public void setIzq(NodoAVL<T> izq) {
        this.izq = izq;
    }

    public void setDer(NodoAVL<T> der) {
        this.der = der;
    }

    public NodoAVL<T> getPapa() {
        return papa;
    }

    public void setPapa(NodoAVL<T> papa) {
        this.papa = papa;
    }
    public void cuelga(NodoAVL<T> n){
        if(n==null)
            return;
        if(n.getElement().compareTo(element)<0)
            izq=n;
        else
            der=n;
        n.setPapa(this);
    }
    //toString
    @Override
    public String toString() {
        return "\nNodo" + "\nDato:" + element.toString() + "\nFactor de equilibrio:" + fe;
    }
}
