package colecciones.arbol;

import java.util.Comparator;
import java.util.List;
import java.util.LinkedList;




/**
 * Una implementación de {@code Diccionario} mediante nodos encadenados que preserva,
 * las propiedades de ABB y ademas mantiene el arbol balanceado, es decir,
 * las alturas de los subárboles izquierdo y derecho de cada nodo difieren como máximo en uno.
 * @param <T>  Tipo del valor asociado a los nodos del árbol, debe ser posible definir un orden total para los mismos.
 * @see NodoBinario
 */
public class Avl<T> implements Diccionario<T> {

    private NodoBinario<T> raiz;

    /**
     * Comparador usado para mantener el orden en un ABB, o null.
     */
    private final Comparator<? super T> comparador;


    /**
     * Construye un nuevo árbol vacío ordenado acorde al comparador dado.
     *
     * @param comparador define una forma de comparar los valores insertados en el arbol.
     */
    public Avl(Comparator<? super T> comparador) {
        this.comparador = comparador;
        this.raiz = null;
    }

    /**
     * Construye un nuevo árbol con un elemento en la raiz, ordenado acorde al comparador dado.
     *
     * @param comparador define una forma de comparar los valores insertados en el arbol.
     * @param valor de la raiz del nuevo arbol si no es null.
     */
    public Avl(Comparator<? super T> comparador, T valor) {
        this.comparador = comparador;
        raiz = new NodoBinario<T>(valor);
    }

    private Avl(Comparator<? super T> comparador, NodoBinario<T> raiz){
        this.comparador = comparador;
        this.raiz = raiz;
    }

    private NodoBinario<T> rotIzquierda(NodoBinario<T> k1){
        NodoBinario<T> k2 = k1.getDerecho();
        k1.setDerecho(k2.getIzquierdo());
        k2.setIzquierdo(k1);
        return k2;
    }

    private NodoBinario<T> rotDerecha(NodoBinario<T> k1){
        NodoBinario<T> k2 = k1.getIzquierdo();
        k1.setIzquierdo(k2.getDerecho());
        k2.setDerecho(k1);
        return k2;
    }




    /**
     * {@inheritDoc}
     */
    @Override
    public void insertar( T elem ) {
        raiz = insertarRec(raiz,elem);
    }

    private NodoBinario<T> insertarRec(NodoBinario<T> nodo,T elem){
        if (nodo == null) {
            return new NodoBinario<T>(elem);
        }
        int cmp = comparador.compare(nodo.getValor(),elem);
        if (cmp > 0) {
            nodo.setIzquierdo(insertarRec(nodo.getIzquierdo(),elem));
        }
        else if (cmp < 0) {
            nodo.setDerecho(insertarRec(nodo.getDerecho(),elem));
        }
        else {
            return nodo;
        }
        int balnodo = balance(nodo);
        int balizq = (nodo.getIzquierdo() != null) ? balance(nodo.getIzquierdo()) : 0;
        int balder = (nodo.getDerecho() != null) ? balance(nodo.getDerecho()) : 0;
        //int bal = balance(nodo);
        //int cmpizq = (nodo.getIzquierdo() != null) ? comparador.compare(nodo.getIzquierdo().getValor(), elem) : 0;      //tipodevar nombrevar = (condicion) ? secumplecondicion : nosecumplecondicion ;
        //int cmpder = (nodo.getDerecho() != null) ? comparador.compare(nodo.getDerecho().getValor(), elem) : 0;

        //rotaciones
        if (balnodo > 1 && balizq > 0) {
            //rotacion R
            return rotDerecha(nodo);
        }
        if (balnodo > 1 && balizq < 0) {
            //rotacion LR
            nodo.setIzquierdo(rotIzquierda(nodo.getIzquierdo()));
            return rotDerecha(nodo);
        }
        if (balnodo < -1 && balder < 0) {
            //rotacion L
            return rotIzquierda(nodo);
        }
        if (balnodo > 1 && balder > 0) {
            //rotacion RL
            nodo.setDerecho(rotDerecha(nodo.getDerecho()));
            return rotIzquierda(nodo);
        }
        return nodo;
    }


    /**
     * {@inheritDoc}
     */
    public boolean pertenece(T elem) {
        if (raiz == null){
            return false;
        }
        int cmp = comparador.compare(raiz.getValor(),elem);
        if (cmp == 0){
            return true;
        }
        if (cmp > 0){
            Diccionario<T> izq = subArbolIzquierdo();
            return izq.pertenece(elem);
        }
        else{
            Diccionario<T> der = subArbolDerecho();
            return der.pertenece(elem);
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void borrar(T elem) {
        raiz = borrarRecursivo(raiz,elem);
    }

    private NodoBinario<T> borrarRecursivo(NodoBinario<T> nodo,T elem){
        if (nodo == null) {
            throw new IllegalStateException("elemento no encontrado");
        }
        int cmp = comparador.compare(nodo.getValor(),elem);
        if (cmp > 0) {
            nodo.setIzquierdo(borrarRecursivo(nodo.getIzquierdo(),elem));
        }
        else if (cmp < 0) {
            nodo.setDerecho(borrarRecursivo(nodo.getDerecho(),elem));
        }
        else {
            if (nodo.getIzquierdo() == null) {
                return nodo.getDerecho();
            }
            else if (nodo.getDerecho() == null) {
                return nodo.getIzquierdo();
            }
            nodo.setValor(menorValorRec(nodo.getDerecho()));
            nodo.setDerecho(borrarRecursivo(nodo.getDerecho(),nodo.getValor()));
        }
        int balnodo = balance(nodo);
        int balizq = (nodo.getIzquierdo() != null) ? balance(nodo.getIzquierdo()) : 0;
        int balder = (nodo.getDerecho() != null) ? balance(nodo.getDerecho()) : 0;
        //int cmpizq = (nodo.getIzquierdo() != null) ? comparador.compare(nodo.getIzquierdo().getValor(), elem) : 0;
        //int cmpder = (nodo.getDerecho() != null) ? comparador.compare(nodo.getDerecho().getValor(), elem) : 0;

        //rotaciones
        if (balnodo > 1 && balizq > 0/*cmpizq < 0*/) {
            //rotacion R
            return rotDerecha(nodo);
        }
        if (balnodo > 1 && balizq < 0/*cmpizq > 0*/) {
            //rotacion LR
            nodo.setIzquierdo(rotIzquierda(nodo.getIzquierdo()));
            return rotDerecha(nodo);
        }
        if (balnodo < -1 && balder < 0/*cmpder > 0*/) {
            //rotacion L
            return rotIzquierda(nodo);
        }
        if (balnodo > 1 && balder > 0/*cmpder < 0*/) {
            //rotacion RL
            nodo.setDerecho(rotDerecha(nodo.getDerecho()));
            return rotIzquierda(nodo);
        }
        return nodo;
    }

    /**{@inheritDoc}*/
    @Override
    public void vaciar() {
        raiz = null;
    }

    /**{@inheritDoc}*/
    @Override
    public T raiz() {
        return raiz.getValor();
    }
    private NodoBinario<T> getRaiz(){
        return raiz;
    }
    private void setRaiz(NodoBinario<T> raiz){
        this.raiz = raiz;
    }

    /**{@inheritDoc}*/
    @Override
    public Avl<T> subArbolIzquierdo() {
        if (raiz != null) {
            Avl<T> izq = new Avl<T>(comparador,raiz.getIzquierdo());
            return izq;
        }
        else {
            return new Avl<T>(comparador);
        }
    }

    /**{@inheritDoc}*/
    @Override
    public Avl<T> subArbolDerecho() {
        if (raiz != null) {
            Avl<T> der = new Avl<T>(comparador,raiz.getDerecho());
            return der;
        }
        else {
            return new Avl<T>(comparador);
        }
    }

    /**{@inheritDoc}*/
    @Override
    public int elementos() {
        if (raiz == null || raiz.getValor() == null) {
            return 0;
        }
        Diccionario<T> subizq = subArbolIzquierdo();
        int elemizq = subizq.elementos();
        Diccionario<T> subder = subArbolDerecho();
        int elemder = subder.elementos();
        return 1 + elemizq + elemder;
    }

    /**{@inheritDoc}*/
    @Override
    public int altura() {
        /*
        if (raiz == null || raiz.getValor() == null) {
            return 0;
        }
        Diccionario<T> subizq = subArbolIzquierdo();
        int alturaizq = subizq.altura();
        Diccionario<T> subder = subArbolDerecho();
        int alturader = subder.altura();
        return 1 + Math.max(alturaizq,alturader);*/
        return altura(raiz);
    }
    private int altura(NodoBinario<T> nodo){
        if (nodo == null) {
        return 0;
        }
        return 1 + Math.max(altura(nodo.getIzquierdo()), altura(nodo.getDerecho()));
    }

    /**{@inheritDoc}*/
    @Override
    public boolean esVacio() {
        return (raiz != null);
    }

    /**{@inheritDoc}*/
    @Override
    public T mayorValor(){
        return mayorValorRec(raiz);
    }
    public T mayorValorRec(NodoBinario<T> nodo){
        if (nodo.getDerecho() != null) {
            return mayorValorRec(nodo.getDerecho());
        }
        return nodo.getValor();
    }
    /**{@inheritDoc}*/
    @Override
    public T menorValor() {
        return menorValorRec(raiz);
    }
    public T menorValorRec(NodoBinario<T> nodo){
        if (nodo.getIzquierdo() != null) {
            return menorValorRec(nodo.getIzquierdo());
        }
        return nodo.getValor();
    }

    /**{@inheritDoc}*/
    @Override
    public T sucesor(T elem) {
        if (raiz == null) {
            throw new IllegalStateException("arbol vacio");
        }
        int cmp = comparador.compare(raiz.getValor(),elem);
        if (cmp == 0) {
            return subArbolDerecho().menorValor();
        }
        else{
            if (cmp > 0) {
                return subArbolIzquierdo().sucesor(elem);
            }
            if (cmp < 0) {
                return subArbolDerecho().sucesor(elem);
            }
        }
        return null;
    }

    /**{@inheritDoc}*/
    @Override
    public T predecesor(T elem) {
        if (raiz == null) {
            throw new IllegalStateException("arbol vacio");
        }
        int cmp = comparador.compare(raiz.getValor(),elem);
        if (cmp == 0) {
            return subArbolIzquierdo().mayorValor();
        }
        else{
            if (cmp > 0) {
                return subArbolIzquierdo().sucesor(elem);
            }
            if (cmp < 0) {
                return subArbolDerecho().sucesor(elem);
            }
        }
        return null;
    }

    /**
     * obtiene el balance del arbol, es decir, la diferencia de altura de sus subarboles.
     * @return diferencia de altura de los subarboles.
     */
    public int balance(){
        return balance(raiz);
    }
    private int balance(NodoBinario<T> nodo){
        return altura(nodo.getIzquierdo()) - altura(nodo.getDerecho());
    }

    /**{@inheritDoc}*/
    @Override
    public boolean repOK() {
        if (raiz == null) {
            return true;
        }
        if (raiz.getIzquierdo() != null && raiz.getDerecho() != null) {
            int compizq = comparador.compare(raiz.getValor(),raiz.getIzquierdo().getValor());
            int compder = comparador.compare(raiz.getValor(),raiz.getDerecho().getValor());
            boolean actualnodo = (compizq > 0) && (compder < 0) && (Math.abs(balance(raiz)) < 2);
            return actualnodo && subArbolIzquierdo().repOK() && subArbolDerecho().repOK();
        }
        else {
            return subArbolDerecho().repOK() && subArbolIzquierdo().repOK();
        }
    }

    /**{@inheritDoc}*/
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toStringPreOrder(raiz, sb);
        return sb.toString();
    }
    private void toStringPreOrder(NodoBinario<T> nodo, StringBuilder sb) {
        if (nodo != null) {
            sb.append(nodo.getValor()).append(" "); // Agrega el valor actual al StringBuilder
            toStringPreOrder(nodo.getIzquierdo(), sb); // Recorre el subárbol izquierdo
            toStringPreOrder(nodo.getDerecho(), sb);   // Recorre el subárbol derecho
        }
    }

    /**{@inheritDoc}*/
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !(other instanceof Avl)) {
            return false;
        }
        Avl<T> otherarbol = (Avl<T>) other;
        if (this.getRaiz() == null && otherarbol.getRaiz() == null) {
            return true;
        }
        else if (this.getRaiz() != null && otherarbol.getRaiz() != null) {
            return (raiz.getValor() == otherarbol.raiz.getValor()) && (this.subArbolIzquierdo().equals(otherarbol.subArbolIzquierdo())) && (this.subArbolDerecho().equals(otherarbol.subArbolDerecho()));
        }
        else{
            return false;
        }
    }

    /**{@inheritDoc}*/
    @Override
    public List<T> aLista() {
        return aLista(Orden.INORDER);
    }

    /**{@inheritDoc}*/
    @Override
    public List<T> aLista(Orden orden) {
        List<T> elementos = new LinkedList<>();
        switch (orden) {
            case INORDER : return aListaInOrder(raiz, elementos);
            case PREORDER : return aListaPreOrder(raiz, elementos);
            case POSTORDER : return aListaPostOrder(raiz, elementos);
        }
        return elementos;
    }

    /* (non-Javadoc)
     * Este método toma un nodo (que puede ser null), una lista de elementos (que no puede ser null)
     * y va llenando la lista con los elementos del árbol según un recorrido in order.
     * Si bien el prefil está pensando para una implementación recursiva, puede probar con una implementación iterativa.
     */
    private List<T> aListaInOrder(NodoBinario<T> raiz, List<T> elementos) {
        if (raiz != null) {
            aListaInOrder(raiz.getIzquierdo(),elementos);
            elementos.add(raiz.getValor());
            aListaInOrder(raiz.getDerecho(),elementos);
        }
        return elementos;
    }

    /* (non-Javadoc)
     * Este método toma un nodo (que puede ser null), una lista de elementos (que no puede ser null)
     * y va llenando la lista con los elementos del árbol según un recorrido pre order.
     * Si bien el prefil está pensando para una implementación recursiva, puede probar con una implementación iterativa.
     */
    private List<T> aListaPreOrder(NodoBinario<T> raiz, List<T> elementos) {
        if (raiz != null) {
            elementos.add(raiz.getValor());
            aListaPreOrder(raiz.getIzquierdo(),elementos);
            aListaPreOrder(raiz.getDerecho(),elementos);
        }
        return elementos;
    }

    /* (non-Javadoc)
     * Este método toma un nodo (que puede ser null), una lista de elementos (que no puede ser null)
     * y va llenando la lista con los elementos del árbol según un recorrido post order.
     * Si bien el prefil está pensando para una implementación recursiva, puede probar con una implementación iterativa.
     */
    private List<T> aListaPostOrder(NodoBinario<T> raiz, List<T> elementos) {
        if (raiz != null) {
            aListaPostOrder(raiz.getIzquierdo(),elementos);
            aListaPostOrder(raiz.getDerecho(),elementos);
            elementos.add(raiz.getValor());
        }
        return elementos;
    }

    public static void main(String[] args) {
        /*Avl<Integer> a1 = new Avl<Integer>(Comparator.naturalOrder(),5);
        a1.insertar(10);
        a1.insertar(20);
        a1.insertar(15);
        a1.insertar(16);
        a1.insertar(17);
        a1.insertar(21);
        a1.insertar(25);
        System.out.println(a1.toString());
        a1.borrar(20);*/
        Avl<Integer> t = new Avl<Integer>(Comparator.naturalOrder());
        t.insertar(10);
        t.insertar(100);
        t.insertar(30);
        t.insertar(80);
        t.insertar(50);
        t.borrar(10);
        t.insertar(60);
        t.insertar(70);
        t.insertar(40);
        t.borrar(80);
        t.insertar(90);
        t.insertar(20);
        t.borrar(30);
        t.borrar(70);
        System.out.println(t.toString());
        System.out.println(t.repOK());
    }

}
