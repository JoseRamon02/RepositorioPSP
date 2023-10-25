package Cifrado;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("Selecciona una opción:");
            System.out.println("1. Almacenar contraseña");
            System.out.println("2. Comprobar contraseña");
            System.out.println("3. Salir");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Introduce la nueva contraseña: ");
                    String nuevaContrasena = scanner.next();
                    almacenarContrasena(nuevaContrasena);
                    System.out.println("Contraseña almacenada con éxito.");
                    break;
                case 2:
                    System.out.print("Introduce la contraseña: ");
                    String contraseñaIngresada = scanner.next();
                    if (comprobarContrasena(contraseñaIngresada)) {
                        System.out.println("Contraseña correcta. Acceso concedido.");
                    } else {
                        System.out.println("Contraseña incorrecta. Acceso denegado.");
                    }
                    break;
                case 3:
                    salir = true; // Salir del bucle
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }

        scanner.close();
    }

    public static void almacenarContrasena(String contrasena) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("contrasena.txt"));
            writer.write(contrasena);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean comprobarContrasena(String contrasena) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("contrasena.txt"));
            String contrasenaAlmacenada = reader.readLine();
            reader.close();
            return contrasena != null && contrasena.equals(contrasenaAlmacenada);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
