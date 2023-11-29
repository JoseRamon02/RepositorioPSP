package backtrack;
import java.util.*;

public class Solucion {
static int cont;

	static void n_reinas(int n) {
	    int[] c = new int[n];

	    boolean[] f = new boolean[n];
	    for (int i = 0; i < n; i++)
	        f[i] = true;

	    boolean[] dp = new boolean[2 * n - 1];
	    for (int i = 0; i < 2 * n - 1; i++)
	        dp[i] = true;

	    boolean[] ds = new boolean[2 * n - 1];
	    for (int i = 0; i < 2 * n - 1; i++)
	        ds[i] = true;

	    buscarReinas(n, 0, c, f, dp, ds);
	}

	
	static void buscarReinas(int n, int i, int[] solucion, boolean[] f, boolean[] dp, boolean[] ds) {
	    if (i == n) {
	        imprimir(solucion);
	        return;
	    }

	    for (int j = 0; j < n; j++) {
	        if (f[j] && dp[i - j + n - 1] && ds[i + j]) {
	            solucion[i] = j;
	            f[j] = false;
	            dp[i - j + n - 1] = false;
	            ds[i + j] = false;

	            buscarReinas(n, i + 1, solucion, f, dp, ds);

	            f[j] = true;
	            dp[i - j + n - 1] = true;
	            ds[i + j] = true;
	        }
	    }
	}

	
	static void imprimir(int[] solucion) {
	    int n = solucion.length;
	    for (int i = 0; i < n; i++) {
	        for (int j = 0; j < n; j++) {
	            if (solucion[i] == j) {
	                System.out.print("Q "); // Representa una reina en la casilla (i, j)
	            } else {
	                System.out.print(". "); // Representa una casilla vacía
	            }
	        }
	        System.out.println();
	    }
	    System.out.println();
	    cont++;
	}


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce el numero del tamaño del tablaro, Ejemplo si insertas 4 te hara un tablero de 4x4");
	    int n = sc.nextInt(); // Cambia el valor de 'n' según el número de reinas que desees
	    n_reinas(n);
	    System.out.println(cont);
	}

	
}
