package CifradoHash;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class CifradoHashMessageDigest {
    public static void main(String[] args) throws IOException {
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
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }

        scanner.close();
    }

    public static void almacenarContrasena(String contrasena) throws IOException {
        try {
        	
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(contrasena.getBytes());

            //Convertir cada byte en su representación hexadecimal de dos dígitos 
            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            String hash = hexString.toString();

            
            BufferedWriter writer = new BufferedWriter(new FileWriter("contrasena.txt"));
            writer.write(hash);
            writer.close();
            
            System.out.println("Hash de contraseña almacenado: " + hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    
    public static boolean comprobarContrasena(String contrasena) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(contrasena.getBytes());

            StringBuilder hexString = new StringBuilder(); // Creamos un StringBuilder para construir la representación hexadecimal.
            // Convertimos cada byte del hash en un valor hexadecimal.
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
            // Si el valor hexadecimal resultante tiene solo un dígito, agregamos un "0" al principio para que tenga dos dígitos.
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            // Agregamos el valor hexadecimal al StringBuilder.

            String hash = hexString.toString();

            // Leer el hash almacenado desde el archivo "contrasena.txt"
            BufferedReader reader = new BufferedReader(new FileReader("contrasena.txt"));
            String hashAlmacenado = reader.readLine();
            reader.close();

            return hash.equals(hashAlmacenado);
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }
    }
}
