/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TareaAVL;

/**
 *
 * @author Montse Olivares 179905
 */
public interface AVLTADT<T extends Comparable<T>> {
    public void agrega(T elem);
    public boolean elimina(T elem);
    public NodoAVL<T> buscar(T elem);
    public String imprimeIzqDer();
}
