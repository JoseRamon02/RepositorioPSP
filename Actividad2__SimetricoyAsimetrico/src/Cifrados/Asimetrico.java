package Cifrados;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

public class Asimetrico {

	static Scanner sc = new Scanner(System.in);
	static KeyPair pair = generarClaves(); // Inicializa el par de claves al principio.

	public static KeyPair generarClaves() {
	    try {
	        KeyPairGenerator keyP = KeyPairGenerator.getInstance("RSA"); // Crea un generador de par de claves RSA
	        return keyP.generateKeyPair(); // Genera y devuelve un nuevo par de claves
	    } catch (Exception e) {
	        System.out.println("Error al generar las claves: " + e.getMessage()); // Muestra un mensaje de error si hay una excepción
	        return null; // Devuelve null en caso de error
	    }
	}

	public static String CifrarMensaje() {
	    try {
	        PublicKey publicKey = pair.getPublic(); // Obtiene la clave pública del par de claves
	        String TextoACifrar = "";
	        
	        System.out.println("Introduce texto a cifrar");
	        TextoACifrar = sc.next(); // Lee el texto a cifrar desde la entrada estándar

	        Cipher cipher = Cipher.getInstance("RSA"); // Crea una instancia del cifrador RSA
	        cipher.init(Cipher.ENCRYPT_MODE, publicKey); // Inicializa el cifrador en modo cifrado con la clave pública

	        byte[] textoPlano = TextoACifrar.getBytes(); // Convierte el texto a cifrar en bytes
	        byte[] textoCifrado = cipher.doFinal(textoPlano); // Realiza el cifrado del texto
	     
	        System.out.println("Texto Cifrado: " + Base64.getEncoder().encodeToString(textoCifrado)); // Muestra el texto cifrado en la consola
	        return Base64.getEncoder().encodeToString(textoCifrado); // Devuelve el texto cifrado en formato base64
	    
	    } catch (Exception e) {
	        System.out.println("Algo anda mal :'("); // Muestra un mensaje de error si hay una excepción
	        return null; // Devuelve null en caso de error
	    }
	}


	public static String DescifrarMensaje(String TextoADescifrar) {
	    PrivateKey privKey = pair.getPrivate(); // Obtiene la clave privada del par de claves
	    
	    try {
	        Cipher cipher = Cipher.getInstance("RSA"); // Crea una instancia del cifrador RSA
	        cipher.init(Cipher.DECRYPT_MODE, privKey); // Inicializa el cifrador en modo descifrado con la clave privada
	        
	        byte[] textoCifrado = Base64.getDecoder().decode(TextoADescifrar); // Decodifica el texto cifrado en base64
	        byte[] textoDescifrado = cipher.doFinal(textoCifrado); // Descifra el texto cifrado
	     
	        String mensajeDescifrado = new String(textoDescifrado); // Convierte los bytes descifrados en una cadena de texto
	        System.out.println("Texto Descifrado: " + mensajeDescifrado); // Muestra el texto descifrado en la consola
	       
	        return mensajeDescifrado; // Devuelve el mensaje descifrado
	    } catch (Exception e) {
	        System.out.println("Error al desencriptar: " + e.getMessage()); // Muestra un mensaje de error si hay una excepción
	        return null; // Devuelve null en caso de error
	    }
	}


	public static void RegenerarClaves() {
		pair = generarClaves(); // Asigna las nuevas claves generadas.
	}

	public static void main(String[] args) {
		String textoCifrado = null; // Variable para almacenar el texto cifrado

		System.out.println(
				"Introduce una opción: 1. Cifrar mensaje, 2. Descifrar mensaje, 3. Regenerar claves, 4. Salir");
		try {
			while (true) {
				int opcion = sc.nextInt();

				switch (opcion) {
				case 1: {
					if (textoCifrado == null) {
						textoCifrado = CifrarMensaje(); // Llama al método solo si textoCifrado es nulo
					}
					break;
				}
				case 2: {
					// Aquí puedes usar textoCifrado para descifrar si no es nulo
					if (textoCifrado != null) {
						DescifrarMensaje(textoCifrado);
					} else {
						System.out.println("Primero debes cifrar un mensaje.");
					}
					break;
				}
				case 3: {
					RegenerarClaves();
					textoCifrado = null; // Restablece el texto cifrado a null después de regenerar las claves
					break;
				}
				case 4: {
					System.out.println("Cerrando el programa...");
					System.exit(0);
				}
				}

				System.out.println(
						"Introduce una opción: 1. Cifrar mensaje, 2. Descifrar mensaje, 3. Regenerar claves, 4. Salir");
			}
		} catch (Exception e) {
			System.out.println("Introduce una de las opciones");
		}

	}
}
