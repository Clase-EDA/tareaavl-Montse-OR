/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TareaAVL;

import Tarea0.EmptyCollectionException;
import java.util.ArrayList;

/**
 *
 * @author bratz
 */
public class AVLT <T extends Comparable<T>> implements AVLTADT<T>{
    protected int cont;
    protected NodoAVL<T> raiz;
    
    public AVLT(){
        cont=0;
        raiz=null;
    }
    
    public void agrega(T elem){
        if (elem!=null){
            NodoAVL <T> nuevo = new NodoAVL(elem);
            NodoAVL <T> aux= raiz;
            //caso de que no haya datos, lo mete en la raíz
            if(aux==null){
                raiz=nuevo;
                cont++;
                return;
            }
            else{
                agrega(elem, raiz, raiz);
                
                NodoAVL <T> actual = nuevo;
                boolean termine=false;
                
                while(!termine && actual.getPapa()!=null){
                    if(actual==actual.getPapa().getElement()){
                        actual.getPapa().fe-=1;
                    }
                    else{
                        actual.getPapa().fe+=1;
                    }
                    if (Math.abs(actual.getPapa().getFe())==2){
                        rotacion(actual.getPapa());
                        termine=true;
                    }
                    else{
                        if (actual.getPapa().getFe()==0)
                            termine=true;
                    }
                    actual=actual.getPapa();
                }
                
                
            }
        }
        else{
            throw new EmptyCollectionException("\n Dato null");
        }
    }
        private NodoAVL<T> agrega(T elem, NodoAVL<T> anterior, NodoAVL<T> actual){
            if(actual==null){
                NodoAVL<T> nuevo=new NodoAVL(elem);
                anterior.cuelga(nuevo);
                nuevo.setPapa(anterior);
                cont++;
                return nuevo;
            }
            else{
                if(actual.getElement().compareTo(elem)<0){
                    return agrega(elem, actual, actual.getDer());
                }
                else{
                    return agrega(elem, actual, actual.getIzq());
                }
            }
        }
        
    private void rotacion(NodoAVL<T> n){
        NodoAVL<T> papa, alfa, beta, gamma, a, b, c, d;
        if(n.getFe()==-2 && (n.getIzq().getFe()==-1)||(n.getIzq().getFe()==0)){
            //izq-izq
            alfa=n;
            papa=n.getPapa();
            beta=alfa.getIzq();
            gamma=beta.getIzq();
            a=gamma.getIzq();
            b=gamma.getDer();
            c=beta.getDer();
            d=alfa.getDer();
            
            beta.cuelga(alfa);
            alfa.setPapa(beta);
            beta.cuelga(gamma);
            gamma.setPapa(beta);
            if(a!=null){
                gamma.cuelga(a);
                a.setPapa(gamma);
            }
            if(b!=null){
                gamma.cuelga(b);
                b.setPapa(gamma);
            }
            else{
                beta.setDer(null);
            }
            if(c!=null){
                alfa.cuelga(c);
                c.setPapa(alfa);
            }
            else{
                alfa.setIzq(null);
            }
            if(d!=null){
                alfa.cuelga(d);
                d.setPapa(alfa);
            }
            if(papa!=null){
                papa.cuelga(beta);
                beta.setPapa(papa);
            }
            else{
                raiz=beta;
                beta.setPapa(null);
            }
            if(beta.getFe()==0){
                beta.setFe(1);
            }
            else{
                beta.setFe(0);
            }
            alfa.setFe(0);
        }
        else{
            //der-der
            if(n.getFe()==2 && (n.getDer().getFe()==1 || n.getDer().getFe()==0)){
                papa=n.getPapa();
                alfa=n;
                beta=alfa.getDer();
                gamma=beta.getDer();
                a=alfa.getIzq();
                b=beta.getIzq();
                c=gamma.getIzq();
                d=gamma.getDer();
                
                beta.cuelga(alfa);
                alfa.setPapa(beta);
                beta.cuelga(gamma);
                gamma.setPapa(beta);
                if(a!=null){
                    alfa.cuelga(a);
                    a.setPapa(alfa);
                }
                if(b!=null){
                    alfa.cuelga(b);
                    b.setPapa(alfa);
                }
                else{
                    alfa.setDer(null);
                }
                if(c!=null){
                    gamma.cuelga(c);
                    c.setPapa(gamma);
                }
                if(d!=null){
                    gamma.cuelga(d);
                    d.setPapa(gamma);
                }
                if(papa!=null){
                    papa.cuelga(beta);
                    beta.setPapa(papa);
                }
                else{
                    raiz=beta;
                    beta.setPapa(null);
                }
                if(beta.getFe()==0){
                    beta.setFe(-1);
                }
                else{
                    beta.setFe(0);
                }
                alfa.setFe(0);
            }
            else{
                //izq-der
                if(n.getFe()==-2 && n.getIzq().getFe()==1){
                    papa=n.getPapa();
                    alfa=n;
                    beta=alfa.getIzq();
                    gamma=beta.getDer();
                    a=beta.getIzq();
                    b=gamma.getIzq();
                    c=gamma.getDer();
                    d=alfa.getDer();
                    
                    gamma.cuelga(beta);
                    beta.setPapa(gamma);
                    gamma.cuelga(alfa);
                    alfa.setPapa(gamma);
                    if(a!=null){
                        beta.cuelga(a);
                        a.setPapa(beta);
                    }
                    if(b!=null){
                        beta.cuelga(b);
                        b.setPapa(beta);
                    }
                    else{
                        beta.setDer(null);
                    }
                    if(c!=null){
                        alfa.cuelga(c);
                        c.setPapa(alfa);
                    }
                    else{
                        alfa.setIzq(null);
                    }
                    if(d!=null){
                        alfa.cuelga(d);
                        d.setPapa(alfa);
                    }
                    if(papa!=null){
                        papa.cuelga(gamma);
                        gamma.setPapa(papa);
                    }
                    else{
                        raiz=gamma;
                        gamma.setPapa(null);
                    }
                    if(gamma.getFe()==-1){
                        alfa.setFe(1);
                        beta.setFe(0);
                    }
                    else{
                        if(gamma.getFe()==0){
                            alfa.setFe(0);
                            beta.setFe(0);
                        }
                        else{
                            alfa.setFe(0);
                            beta.setFe(-1);
                        }
                    }
                    gamma.setFe(0);
                }
                else{
                    //der-izq
                    papa=n.getPapa();
                    alfa=n;
                    beta=alfa.getDer();
                    gamma=beta.getIzq();
                    a=alfa.getIzq();
                    b=gamma.getIzq();
                    c=gamma.getDer();
                    d=beta.getDer();
                    
                    gamma.cuelga(alfa);
                    alfa.setPapa(gamma);
                    gamma.cuelga(beta);
                    beta.setPapa(gamma);
                    if(a!=null){
                        alfa.cuelga(a);
                        a.setPapa(alfa);
                    }
                    if(b!=null){
                        alfa.cuelga(b);
                        b.setPapa(alfa);
                    }
                    else{
                        alfa.setDer(null);
                    }
                    if(c!=null){
                        beta.cuelga(c);
                        c.setPapa(beta);
                    }
                    else{
                        beta.setIzq(null);
                    }
                    if(d!=null){
                        beta.cuelga(d);
                        d.setPapa(null);
                    }
                    if(papa!=null){
                        papa.cuelga(gamma);
                        gamma.setPapa(papa);
                    }
                    else{
                        raiz=gamma;
                        gamma.setPapa(null);
                    }
                    if(gamma.getFe()==0){
                        alfa.setFe(0);
                        beta.setFe(0);
                    }
                    else{
                        if(gamma.getFe()==-1){
                            alfa.setFe(0);
                            beta.setFe(1);
                        }
                        else{
                            alfa.setFe(-1);
                            beta.setFe(0);
                        }
                    }
                    gamma.setFe(0);
            }
            }
        }
    }    
    
    public boolean elimina(T elem){
        boolean res=false, band=false;
        NodoAVL<T> actual=null, pos=buscar(elem), hijoI, hijoD;
        
        if(pos!=null){
            hijoI=pos.getIzq();
            hijoD=pos.getDer();
            res=true;
            cont--;
            if(hijoI==null && hijoD==null){
                actual=pos.getPapa();
                if(raiz==pos){
                    raiz=null;
                }
                else{
                    if(pos.getPapa().getElement().compareTo(elem)<0){
                        pos.getPapa().setDer(null);
                    }
                    else{
                        pos.getPapa().setIzq(null);
                    }
                }
            }
            else{
                if(hijoI!=null && hijoD!=null){
                    while(hijoD.getIzq()!=null){
                        hijoD=hijoD.getIzq();
                    }
                    actual=hijoD.getPapa();
                    if(actual==pos){
                        actual.setDer(null);
                    }
                    else{
                        actual.setIzq(null);
                    }
                    if(hijoD.getDer()!=null){
                        hijoD.getPapa().cuelga(hijoD.getDer());
                    }
                    if(pos.getIzq()!=null){
                        hijoD.cuelga(pos.getIzq());
                        pos.getIzq().setPapa(hijoD);
                    }
                    if(pos.getDer()!=null && pos.getDer()!=hijoD){
                        hijoD.cuelga(pos.getDer());
                        pos.getDer().setPapa(hijoD);
                    }
                    if(raiz==pos){
                        raiz=hijoD;
                        raiz.setPapa(null);
                    }
                    else{
                        hijoD.setPapa(pos.getPapa());
                        pos.getPapa().cuelga(hijoD);
                    }
                }
                else{
                    if(hijoI!=null){
                        if(raiz==pos){
                            raiz=hijoI;
                            raiz.setPapa(null);
                        }
                        else{
                            pos.getPapa().cuelga(hijoI);
                            hijoI.setPapa(pos.getPapa());
                        }
                        actual=hijoI.getPapa();
                    }
                    else{
                        if(raiz==pos){
                            raiz=hijoD;
                        }
                        else{
                            pos.getPapa().cuelga(hijoD);
                            hijoD.setPapa(pos.getPapa());
                        }
                        actual=hijoD.getPapa();
                    }
                }
            }
        }
        if(actual!=null){
            while(actual!=null && !band){
                if(actual.getElement().compareTo(elem)<0){
                    actual.setFe(actual.getFe()-1);
                }
                else{
                    actual.setFe(actual.getFe()+1);
                }
                if(Math.abs(actual.getFe())==1){
                    band=true;
                }
                if(Math.abs(actual.getFe())>=2){
                    band=true;
                    rotacion(actual.getPapa());
                }
                actual=actual.getPapa();
            }
        }
        return res;
    }
    public NodoAVL<T> buscar(T elem){
        boolean res=false;
        NodoAVL<T> actual=raiz;
        NodoAVL<T> encontrado=raiz;
        
        while(actual!=null && !res){
            if(actual.getElement().compareTo(elem)<0)
                actual=actual.getDer();
            else{
                if(actual.getElement().compareTo(elem)>0)
                    actual=actual.getIzq();
                else{
                    res=true;
                    encontrado=actual;
                }
            }
        }
        return encontrado;
    }
    public String imprimeIzqDer(){
        NodoAVL<T> actual=raiz;
        int c=0, n=0;
        ArrayList<T> arbol= new ArrayList<T>();
        
        while(actual!=null){
            n++;
            if(actual.getFe()==-1)
                actual=actual.getIzq();
            else
                actual=actual.getDer();
        }
        for(int i=0; i<=n; i++){
            imprimeIzqDer(raiz, c, i, arbol);
        }
        return arbol.toString();
    }
    private void imprimeIzqDer(NodoAVL<T> actual, int c, int i, ArrayList<T> arbol){
        if(actual==null){
            return;
        }
        else{
            if(c==i){
                arbol.add(actual.getElement());
            }
            else{
                c++;
                imprimeIzqDer(actual.getIzq(), c, i, arbol);
                imprimeIzqDer(actual.getDer(), c, i, arbol);
            }
        }
    }
    public String imprimeIzqDerFactores(){
        NodoAVL<T> actual=raiz;
        int c=0, n=0;
        ArrayList<Integer> arbol= new ArrayList<Integer>();
        
        while(actual!=null){
            n++;
            if(actual.getFe()==-1)
                actual=actual.getIzq();
            else
                actual=actual.getDer();
        }
        for(int i=0; i<=n; i++){
            imprimeIzqDerFactores(raiz, c, i, arbol);
        }
        return arbol.toString();
    }
    private void imprimeIzqDerFactores(NodoAVL<T> actual, int c, int i, ArrayList<Integer> arbol){
        if(actual==null){
            return;
        }
        else{
            if(c==i){
                arbol.add(actual.getFe());
            }
            else{
                c++;
                imprimeIzqDerFactores(actual.getIzq(), c, i, arbol);
                imprimeIzqDerFactores(actual.getDer(), c, i, arbol);
            }
        }
    }
    public static void main(String[] args) {
        AVLT<Integer> arbol= new AVLT();
        
        arbol.agrega(1);
        arbol.agrega(2);
        arbol.agrega(3);
        arbol.agrega(4);
        arbol.agrega(5);
        arbol.agrega(6);
        arbol.agrega(7);
        System.out.println(arbol.imprimeIzqDer());
        System.out.println("Imprime factores de equilibrio");
        System.out.println(arbol.imprimeIzqDerFactores());
        
        
        arbol.elimina(7);
        System.out.println(arbol.imprimeIzqDer());
        System.out.println("Imprime factores de equilibrio");
        System.out.println(arbol.imprimeIzqDerFactores());
    }
}
