package Permutaciones;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Permutaciones{

    
    public static List<List<Integer>> obtenerPermutaciones(List<Integer> lista) {

        List<List<Integer>> permutaciones = new ArrayList<>();
        obtenerPermutacionesAux(permutaciones, new ArrayList<>(), lista);
        return permutaciones;
        
    }

    private static void obtenerPermutacionesAux(List<List<Integer>> permutaciones, List<Integer> parcial, List<Integer> restantes) {
        if (restantes.isEmpty()) {
            permutaciones.add(new ArrayList<>(parcial));
        } else {
            for (int i = 0; i < restantes.size(); i++) {
                List<Integer> nuevaParcial = new ArrayList<>(parcial);
                nuevaParcial.add(restantes.get(i));

                List<Integer> nuevosRestantes = new ArrayList<>(restantes);
                nuevosRestantes.remove(i);

                obtenerPermutacionesAux(permutaciones, nuevaParcial, nuevosRestantes);
            }
        }
    }

    public static Boolean isOrdened(List<Integer> lista){
        for(int i = 0; i < lista.size() - 1; i++){
            if(lista.get(i) > lista.get(i+1)){
                return false;
            }
        }
        return true;
    }

    public static List<Integer> generarListaAleatoria(int tamano, int minimo, int maximo) {
        Random rand = new Random();
        List<Integer> lista = new ArrayList<>();
        for (int i = 0; i < tamano; i++) {
            lista.add(rand.nextInt(maximo - minimo + 1) + minimo);
        }
        return lista;
    }

}