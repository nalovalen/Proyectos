package java.compras;

public class Lcs {

    // Función para encontrar la subsecuencia común más larga
    public static String findLCS(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();

        // Caso base: si una de las cadenas es vacía, la LCS es una cadena vacía
        if (m == 0 || n == 0) {
            return "";
        }

        // Si los últimos caracteres de las cadenas coinciden
        if (str1.charAt(m - 1) == str2.charAt(n - 1)) {
            // Añadir el último caracter y resolver el subproblema restante
            return findLCS(str1.substring(0, m - 1), str2.substring(0, n - 1)) + str1.charAt(m - 1);
        } else {
            // Comparar las subsecuencias al quitar el último caracter de una de las cadenas
            String lcs1 = findLCS(str1.substring(0, m - 1), str2);
            String lcs2 = findLCS(str1, str2.substring(0, n - 1));

            // Retornar la subsecuencia común más larga de las dos subsecuencias encontradas
            return (lcs1.length() > lcs2.length()) ? lcs1 : lcs2;
        }
    }

    public static void main(String[] args) {
        String str1 = "ABCBDAB";
        String str2 = "BDCAB";

        String lcs = findLCS(str1, str2);
        System.out.println("La subsecuencia común más larga es: " + lcs);
    }
}
