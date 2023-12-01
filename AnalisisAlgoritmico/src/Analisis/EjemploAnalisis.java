package Analisis;

public class EjemploAnalisis {

    // Algoritmo de suma de elementos con complejidad en el peor caso T(n) = N
    public static int sumaArray(int[] array) {
        int suma = 0;
        for (int i = 0; i < array.length; i++) {
            suma += array[i];
        }
        return suma;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // Sumamos todos los elementos del array
        int resultado = sumaArray(array);

        System.out.println("La suma de los elementos del array es: " + resultado);
    }
}

