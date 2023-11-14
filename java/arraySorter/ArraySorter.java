package arraySorter;

import java.util.Arrays;
import java.util.Random;

/**
* Provee métodos para ordenar arreglos de objetos comparables.
* Los algoritmos de ordenamiento provistos por esta clase son:
* <ul>
* <li>Bubble sort.</li>
* <li>Selection sort.</li>
* <li>Shell sort.</li>
* <li>Quick sort.</li>
* <li>Merge sort.</li>
* </ul>
* El invariante que deben cumplir todos los arreglos {@code array} para ser utilizados como argumentos de los distintos algoritmos de ordenamiento es:
* <pre>
* array != null &amp;&amp; for (T elem : array) {elem != null}
* </pre>
*/
public class ArraySorter {

   /**
   * Ordena un arreglo, <i>in place</i>, usando el orden natural de sus elementos utilizando Bubble Sort.
   * @param <T> el tipo de los elementos del arreglo, los cuales deben ser comparables entre sí
   * @param array el arreglo de los elementos a ordenar, no puede ser {@code null}
   */
   public static <T extends Comparable<? super T>> void bubbleSort(T[] array) {
      if (array == null) throw new IllegalArgumentException("El arreglo es null, no se puede ordenar");
      boolean sorted = false;
      int n = array.length; 
      for (int pass = 1; (pass < n) && !sorted; ++pass) {
         sorted = true;
         for (int index = 0; index < n - pass; ++index) {
            int nextIndex = index + 1;
            if (array[index].compareTo(array[nextIndex]) > 0) {
               swap(array, index, nextIndex);
               sorted = false;
            }
         }
      }
   }

   /**
   * Ordena un arreglo, <i>in place</i>, usando el orden natural de sus elementos utilizando Selection Sort.
   * @param <T> el tipo de los elementos del arreglo, los cuales deben ser comparables entre sí
   * @param array el arreglo de los elementos a ordenar, no puede ser {@code null}
   */
   public static <T extends Comparable<? super T>> void selectionSort(T[] array) {
      if (array == null) throw new IllegalArgumentException("array is null, can't sort");
      //last = indice del ultimo elemento de la parte no ordenada
      for (int last = array.length - 1; last >= 1; last--) {
         //largest = posicion del elemento mas grande
         int largest = indexOfLargest(array, last + 1);
         swap(array, last, largest);
      }
   }


   /**
   * Ordena un arreglo, <i>in place</i>, usando el orden natural de sus elementos utilizando Shell Sort.
   * @param <T> el tipo de los elementos del arreglo, los cuales deben ser comparables entre sí
   * @param array el arreglo de los elementos a ordenar, no puede ser {@code null}
   */
   public static <T extends Comparable<? super T>> void shellSort(T[] array) {
      if (array == null) throw new IllegalArgumentException("El arreglo es null, no se puede ordenar");
      int n = array.length; 
      for (int intervalo = n/2 ; intervalo > 0; intervalo /= 2) {
         for (int i = intervalo; i < n; i++) {
            T temp = array[i];
            int j = i; 
            while (j >= intervalo && array[j-intervalo].compareTo(temp) > 0) {
               array[j] = array[j-intervalo];
               j-=intervalo;
            }  
            array[j] = temp; 
         }
         
      }
   }

   /**
   * Ordena un arreglo, <i>in place</i>, usando el orden natural de sus elementos utilizando Quick Sort.
   * @param <T> el tipo de los elementos del arreglo, los cuales deben ser comparables entre sí
   * @param array el arreglo de los elementos a ordenar, no puede ser {@code null}
   */
   public static <T extends Comparable<? super T>> void quickSort(T[] array, int begin , int end) {
      if (array == null) throw new IllegalArgumentException("El arreglo es null, no se puede ordenar");
      if (begin < end){
         // Calculo la particion
         int p = partition(array, begin, end);
         // ordeno la parte izq
         quickSort(array, begin, p);
         // ordeno la parte derecha
         quickSort(array, p+1, end);
         }
   }

   private static<T extends Comparable<? super T>> int partition(T[] array, int begin, int end){
      Random random = new Random();
      int aux = random.nextInt(end - begin + 1) + begin;
      swap(array, aux, end);
      T pivot = array[end];
      int i = begin - 1;
      int j = end + 1;
      while (i < j) {
      //invariante:
      //para k < = i : a[k] <= pivot y para k >= j : pivot <= a[k]
      do j--; while (array[j].compareTo(pivot) > 0);
      do i++; while (array[i].compareTo(pivot) < 0);
      if (i < j) {swap(array, i, j);}
      }
      return j;
      }
   /**
   * Ordena un arreglo, usando el orden natural de sus elementos utilizando Merge Sort.
   * @param <T> el tipo de los elementos del arreglo, los cuales deben ser comparables entre sí
   * @param array el arreglo de los elementos a ordenar, no puede ser {@code null}
   */
   public static <T extends Comparable<? super T>> void mergeSort(T[] array, int begin, int end) {
      if (array == null) throw new IllegalArgumentException("El arreglo es null, no se puede ordenar");
      if (begin < end){
         int mid = (begin + end)/2;
         mergeSort(array, begin, mid);//ordena la primera mitad
         mergeSort(array, mid+1, end);//ordena la segunda mitad
         merge(array, begin, mid, end);//mezcla las mitades ordenadas
      }
   }
   public static  <T extends Comparable<? super T>> void merge(T[] array, int begin,int mid, int end) {
      int n1 = mid - begin + 1;
      int n2 = end - mid;
 
      T[] izquierda = Arrays.copyOfRange(array, begin, mid + 1);
      T[] derecha = Arrays.copyOfRange(array, mid + 1, end + 1);
 
      for (int i = 0; i < n1; i++) {
             izquierda[i] = array[begin + i];
      }
 
      for (int j = 0; j < n2; j++) {
         derecha[j] = array[mid + 1 + j];
      }
 
      int i = 0, j = 0, k = begin;
 
      while (i < n1 && j < n2) {
            if (izquierda[i].compareTo(derecha[j])<=0) {
               array[k] = izquierda[i];
               i++;
            } else {
               array[k] = derecha[j];
               j++;
            }
            k++;
      }
 
      while (i < n1) {
            array[k] = izquierda[i];
            i++;
            k++;
      }
 
      while (j < n2) {
            array[k] = derecha[j];
            j++;
            k++;
      }
   }



   /* (non-Javadoc)
   * Este método intercambia dos posiciones de un arreglo.
   */ 
   private static <T extends Comparable<? super T>> void swap(T[] array, int i, int j) {
      T temp = array[i];
      array[i] = array[j];
      array[j] = temp;
   }

   /* (non-Javadoc)
   * Este método retorna el indice del elemento mas grande. 
   */
   private static <T extends Comparable<? super T>> int indexOfLargest(T[] array, int n){
      int largest = 0;
      for (int i = 1; i < n; i++){
         if (array[i].compareTo(array[largest]) > 0){
            largest = i;
         }
      }  
      return largest;
   }


}
