package CadeenasMayus;

public class Cadenas {
    
    public static boolean cad(String s1,String s2){
       
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                continue;
            }else{
                if (Character.toUpperCase(s1.charAt(i)) == s2.charAt(i)) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }


    private static String eliminarMinusculas(String cadena) {
        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < cadena.length(); i++) {
            char caracter = cadena.charAt(i);
            if (!Character.isLowerCase(caracter)) {
                resultado.append(caracter);
            }
        }

        return resultado.toString();
    }

    public static void main(String[] args) {
        String s2 = "HOLA";
        String s1 = "HhOoLlAaI";

        boolean res = (cad(s1, s2) || cad(eliminarMinusculas(s1),s2));

        System.out.println(res);
    }
}
