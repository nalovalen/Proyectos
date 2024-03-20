package colecciones.arbol;

import java.util.Comparator;
import java.util.List;


import java.util.LinkedList;

/**
 * Árbol binario de busqueda (ABB), es una implementación de {@code Diccionario}
 * mediante nodos encadenados que preserva las propiedades de Diccionario.
 * 
 * @param <T> Tipo del valor asociado a los nodos del árbol, debe ser posible
 *            definir un orden total para los mismos.
 * @see NodoBinario
 */
public class ABB<T> implements Diccionario<T> {

    private NodoBinario<T> raiz;

    /**
     * Comparador usado para mantener el orden en un ABB, o null.
     */
    private final Comparator<? super T> comparador;

    /**
     * Construye un nuevo árbol vacío ordenado acorde al comparador dado.
     * 
     * @param comparador define una forma de comparar los valores insertados en el
     *                   arbol.
     */
    public ABB(Comparator<? super T> comparador) {
        this.comparador = comparador;
        this.raiz = null;
    }

    /**
     * Construye un nuevo árbol con un elemento en la raiz, ordenado acorde al
     * comparador dado.
     * 
     * @param comparador define una forma de comparar los valores insertados en el
     *                   arbol.
     * @param valor      de la raiz del nuevo arbol si no es null.
     */
    public ABB(Comparator<? super T> comparador, T valor) {
        this.raiz = new NodoBinario<>(valor);
        this.comparador = comparador;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertar(T elem) {
        raiz = insertarRecursivo(raiz, elem);
    }

    private NodoBinario<T> insertarRecursivo(NodoBinario<T> nodo, T elem) {
        if (nodo == null) {
            return new NodoBinario<>(elem);
        }
        if (comparador.compare(nodo.getValor(), elem) < 0) {
            return insertarRecursivo(nodo.getDerecho(), elem);
        }
        if (comparador.compare(nodo.getValor(), elem) > 0) {
            return insertarRecursivo(nodo.getIzquierdo(), elem);
        }
        return raiz;
    }

    /**
     * {@inheritDoc}
     */
    public boolean pertenece(T elem) {
        return perteneceRecursivo(raiz, elem);
    }

    private boolean perteneceRecursivo(NodoBinario<T> nodo, T elem) {
        if (nodo == null) {
            return false;
        }
        if (comparador.compare(nodo.getValor(), elem) < 0) {
            return perteneceRecursivo(nodo.getDerecho(), elem);
        }
        if (comparador.compare(nodo.getValor(), elem) > 0) {
            return perteneceRecursivo(nodo.getIzquierdo(), elem);
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void borrar(T elem) {
       raiz = borrarRecursivo(raiz,elem);
    }

    private NodoBinario<T> borrarRecursivo (NodoBinario<T> nodo , T elem){
        if (nodo == null) {
            return nodo;
        }

        if (comparador.compare(elem,nodo.getValor()) < 0) {
            return borrarRecursivo(nodo.getIzquierdo(), elem);
        } else if (comparador.compare(elem, nodo.getValor()) > 0) {
            return  borrarRecursivo(nodo.getDerecho(), elem);
        } else {
            // Nodo con solo un hijo o sin hijos
            if (nodo.getIzquierdo() == null) {
                return nodo.getDerecho();
            } else if (nodo.getDerecho() == null) {
                return nodo.getIzquierdo();
            }

            nodo.setValor(menorValorRecursivo(nodo));

            nodo.setDerecho(borrarRecursivo(nodo.getDerecho(), nodo.getValor()));
        }
        return nodo;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void vaciar() {
        raiz = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T raiz() {
        return this.raiz.getValor();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Diccionario<T> subArbolIzquierdo() {
        ABB<T> aux = new ABB<>(comparador);
        aux.raiz = this.raiz.getIzquierdo();
        return aux;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Diccionario<T> subArbolDerecho() {
        ABB<T> aux = new ABB<>(comparador);
        aux.raiz = this.raiz.getDerecho();
        return aux;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int elementos() {
        if (this.raiz == null) {
            return 0;
        }
        return 1 + subArbolIzquierdo().elementos() + subArbolDerecho().elementos();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int altura() {
        if (this.raiz == null) {
            return 0;
        }
        if (subArbolIzquierdo().elementos() <= subArbolDerecho().elementos()) {
            return 1 + subArbolDerecho().elementos();
        } else {
            return 1 + subArbolIzquierdo().elementos();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean esVacio() {
        return elementos() == 0;
    }

    /**
     * {@inheritDoc}
     */
    public T mayorValor() {
        return mayorValorRecursivo(raiz);
    }

    private T mayorValorRecursivo(NodoBinario<T> nodo) {
        if (nodo == null) {
            return null;
        }
        if (nodo.getDerecho() == null) {
            return nodo.getValor();
        }
        return mayorValorRecursivo(nodo.getDerecho());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T menorValor() {
        return menorValorRecursivo(raiz);
    }

    private T menorValorRecursivo(NodoBinario<T> nodo) {
        if (nodo == null) {
            return null;
        }
        if (nodo.getIzquierdo() == null) {
            return nodo.getValor();
        }
        return menorValorRecursivo(nodo.getIzquierdo());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T sucesor(T elem) {
        if (!pertenece(elem)) {
            return null;
        }
        if (comparador.compare(elem, raiz.getValor()) < 0) {
            return subArbolIzquierdo().sucesor(elem);
        }
        if (comparador.compare(elem, raiz.getValor()) > 0) {
            return subArbolDerecho().sucesor(elem);
        }
        return subArbolDerecho().menorValor();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T predecesor(T elem) {
        if (!pertenece(elem)) {
            return null;
        }
        if (comparador.compare(elem, raiz.getValor()) < 0) {
            return subArbolIzquierdo().sucesor(elem);
        }
        if (comparador.compare(elem, raiz.getValor()) > 0) {
            return subArbolDerecho().sucesor(elem);
        }
        return subArbolIzquierdo().mayorValor();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean repOK() {
        throw new UnsupportedOperationException("TODO: implementar");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
            StringBuilder sb = new StringBuilder();
        inOrderTraversal(raiz, sb);
        return sb.toString();
    }

    private void inOrderTraversal(NodoBinario<T> nodo, StringBuilder sb) {
        if (nodo != null) {
            inOrderTraversal(nodo.getIzquierdo(), sb);
            sb.append(nodo.getValor()).append(" "); // Puedes personalizar el formato
            inOrderTraversal(nodo.getDerecho(), sb);
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true; // Misma referencia, son iguales
        }

        if (other == null || getClass() != other.getClass()) {
            return false; // Tipos diferentes, no son iguales
        }

        ABB<T> otroArbol = (ABB<T>) other;

        return sonArbolesIguales(this.raiz, otroArbol.raiz);
    }

    private boolean sonArbolesIguales(NodoBinario<T> nodo1, NodoBinario<T> nodo2) {
        if (nodo1 == null && nodo2 == null) {
            return true; // Ambos nodos son nulos, son iguales
        }
        if (nodo1 == null || nodo2 == null) {
            return false; // Uno es nulo y el otro no, no son iguales
        }
        if (nodo1.getValor() != nodo2.getValor()) {
            return false; // Los valores son diferentes, no son iguales
        }

        return sonArbolesIguales(nodo1.getIzquierdo(), nodo2.getIzquierdo()) &&
               sonArbolesIguales(nodo1.getDerecho(), nodo2.getDerecho());
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> aLista() {
        return aLista(Orden.INORDER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> aLista(Orden orden) {
        List<T> elementos = new LinkedList<>();
        switch (orden) {
            case INORDER:
                return aListaInOrder(raiz, elementos);
            case PREORDER:
                return aListaPreOrder(raiz, elementos);
            case POSTORDER:
                return aListaPostOrder(raiz, elementos);
        }
        return elementos;
    }

    /*
     * (non-Javadoc)
     * Este método toma un nodo (que puede ser null), una lista de elementos (que no
     * puede ser null)
     * y va llenando la lista con los elementos del árbol según un recorrido in
     * order.
     * Si bien el prefil está pensando para una implementación recursiva, puede
     * probar con una implementación iterativa.
     */
    private List<T> aListaInOrder(NodoBinario<T> raiz, List<T> elementos) {
        if (raiz != null) {
            aListaInOrder(raiz.getIzquierdo(), elementos);
            elementos.add(raiz.getValor());
            aListaInOrder(raiz.getDerecho(), elementos);
        }
        return elementos;
    }

    /*
     * (non-Javadoc)
     * Este método toma un nodo (que puede ser null), una lista de elementos (que no
     * puede ser null)
     * y va llenando la lista con los elementos del árbol según un recorrido pre
     * order.
     * Si bien el prefil está pensando para una implementación recursiva, puede
     * probar con una implementación iterativa.
     */
    private List<T> aListaPreOrder(NodoBinario<T> raiz, List<T> elementos) {
        if (raiz != null) {
            elementos.add(raiz.getValor());
            aListaInOrder(raiz.getIzquierdo(), elementos);
            aListaInOrder(raiz.getDerecho(), elementos);
        }
        return elementos;
    }

    /*
     * (non-Javadoc)
     * Este método toma un nodo (que puede ser null), una lista de elementos (que no
     * puede ser null)
     * y va llenando la lista con los elementos del árbol según un recorrido post
     * order.
     * Si bien el prefil está pensando para una implementación recursiva, puede
     * probar con una implementación iterativa.
     */
    private List<T> aListaPostOrder(NodoBinario<T> raiz, List<T> elementos) {
       if (raiz != null) {
            aListaInOrder(raiz.getIzquierdo(), elementos);
            aListaInOrder(raiz.getDerecho(), elementos);
            elementos.add(raiz.getValor());
        }
        return elementos;
    }
}
