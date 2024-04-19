package arraySorter;

import java.util.ArrayList;

public class MergeSort {
    public static <T extends Comparable<? super T>> void mergeSort(ArrayList<T> list, int begin, int end){
        if(begin<end){
            int mid = (begin + end) / 2;
            mergeSort(list, begin, mid);
            mergeSort(list, mid + 1, end);
            merge(list, begin,mid, end);
        }
    }


    public static <T extends Comparable<? super T>> void merge(ArrayList<T> list, int begin,int mid, int end){
        ArrayList<T> izquierda = new ArrayList<T>();
        ArrayList<T> derecha = new ArrayList<T>();

        for(int i = 0; i<mid; i++){
            izquierda.set(i, list.get(begin + i));
        }

        for(int j = mid + 1; j<end; j++){
            derecha.set(j, list.get(mid + 1 + j));
        }

        int i = 0;
        int j = 0;
        int k = begin;

        while (i < izquierda.size() && j < derecha.size()) {
            if(izquierda.get(i).compareTo(derecha.get(j)) <= 0){
                list.set(k,izquierda.get(i));
                i++;
            } else {
                list.set(k,derecha.get(j));
                j++;
            }
            k++;
        }

        while (i < izquierda.size()) {
            list.set(k, izquierda.get(i));
            i++;
            k++;            
        }

        while (j < derecha.size()) {
            list.set(k,derecha.get(j));
            j++;
            k++;            
        }
    }

    public static  <T extends Comparable<? super T>> void mergeSort1(ArrayList<T> list, int begin, int end){
        if(begin<end){
            int mid = (begin + end) / 2;
            mergeSort1(list, begin, mid);
            mergeSort1(list, mid+1, end);
            merge1(list, begin, mid, end);
        }
    }

    public static <T extends Comparable<? super T>> void merge1(ArrayList<T> list, int begin, int mid, int end){
        ArrayList<T> izquierda = new ArrayList<>();
        ArrayList<T> derecha = new ArrayList<>();

        for(int i= 0; i<mid; i++){
            izquierda.set(i, list.get(begin+i));
        }

        for(int j = mid + 1 ; j<end ; j++){
            derecha.set(j, list.get(mid + 1 + j));
        }

        int i = 0;
        int j = 0;
        int k = begin;

        while (i<izquierda.size() && j < derecha.size()) {
            if(izquierda.get(i).compareTo(derecha.get(j))<=0){
                list.set(k, izquierda.get(i));
                i++;
            }else{
                list.set(k, derecha.get(j));
                j++;
            }
            k++;
        }
        
        while (i < izquierda.size()) {
            list.set(k, izquierda.get(i));
            i++;
            k++;            
        }

        while (j < derecha.size()) {
            list.set(k,derecha.get(j));
            j++;
            k++;            
        }
    }
}
