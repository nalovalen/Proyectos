package colecciones.arbol;

import java.util.Comparator;

public class MainABB {
    public static void main(String[] args) {
        Comparator<Integer> comp = Comparator.naturalOrder();
        ABB<Integer> arbol = new ABB<>(comp);

        System.out.println("Árbol ABB: ");
        System.out.println("Insertando elementos...");
        arbol.insertar(10);
        arbol.insertar(16);
        arbol.insertar(1);
        arbol.insertar(5);
        arbol.insertar(20);
        arbol.insertar(8);

        System.out.println("Elementos en in-order: " + arbol.aLista().toString());
        System.out.println("Elementos en pre-order: " + arbol.aLista(Diccionario.Orden.PREORDER).toString());
        System.out.println("Elementos en post-order: " + arbol.aLista(Diccionario.Orden.POSTORDER).toString());

        System.out.println("Altura del árbol: " + arbol.altura());
        System.out.println("Cantidad de elementos en el árbol: " + arbol.elementos());
        System.out.println("Raíz del árbol: " + arbol.raiz());
        System.out.println("Mayor valor en el árbol: " + arbol.mayorValor());
        System.out.println("Menor valor en el árbol: " + arbol.menorValor());

        int elementoABuscar = 16;
        System.out.println("¿El elemento " + elementoABuscar + " pertenece al árbol? " + arbol.pertenece(elementoABuscar));

        int elementoABorrar = 5;
        System.out.println("Borrando el elemento " + elementoABorrar + " del árbol...");
        arbol.borrar(elementoABorrar);
        System.out.println("Elementos en in-order después de borrar: " + arbol.aLista().toString());

        System.out.println("¿El árbol cumple con el invariante repOK? " + arbol.repOK());
    }
}
