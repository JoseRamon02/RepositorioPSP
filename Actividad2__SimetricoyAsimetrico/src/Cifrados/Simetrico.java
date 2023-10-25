package Cifrados;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.util.Base64;
import java.util.Scanner;
import java.security.SecureRandom;


public class Simetrico {
    public static void main(String[] args) throws Exception {
    	Scanner inputScanner = new Scanner(System.in);

    	System.out.print("¿Tiene una clave secreta? (Yes/No): ");
    	String secretResponse = inputScanner.nextLine().trim();

    	byte[] secretKeyBytes;

    	if ("Yes".equalsIgnoreCase(secretResponse)) {
    	    System.out.println("Introduzca la clave secreta: ");
    	    System.out.println("Tienen que ser exactamente 16 caracteres");
    	    
    	    // Solicita la clave secreta en formato de texto normal
    	    String keyText = inputScanner.nextLine();

    	    // Asegúrate de que la clave tenga 16 bytes (128 bits)
    	    if (keyText.length() != 16) {
    	        System.out.println("La clave debe tener 16 caracteres (128 bits).");
    	        return;
    	    }

    	    secretKeyBytes = keyText.getBytes(); // Convierte la clave en bytes directamente
    	    System.out.println(secretKeyBytes);
    	    
    	} else {
    		
    	    KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
    	    keyGenerator.init(128);
    	    SecretKey secretKey = keyGenerator.generateKey();
    	    secretKeyBytes = secretKey.getEncoded();
    	    
    	}

    	// Texto sin cifrar
    	System.out.println("Ingresa texto para cifrar");
    	String plainText = inputScanner.nextLine();

    	// Cifrar el texto
    	byte[] encryptedText = encryptSymmetric(secretKeyBytes, plainText.getBytes());
    	String textoCifrado = Base64.getEncoder().encodeToString(encryptedText);
    	System.out.println("Texto cifrado: " + textoCifrado);

    	// Descifrar el texto
    	byte[] decryptedText = decryptSymmetric(secretKeyBytes, encryptedText);
    	System.out.println("Texto descifrado: " + new String(decryptedText));
    	}

    	public static byte[] encryptSymmetric(byte[] key, byte[] plainText) throws Exception {
    		//Generamos clave secreta 
    	SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
    		//Generamos el encriptador
    	Cipher encryptCipher = Cipher.getInstance("AES");
    		//Encriptamos
    	encryptCipher.init(Cipher.ENCRYPT_MODE, keySpec);
    		//Devolvemos el encriptado
    	return encryptCipher.doFinal(plainText);
    	}

    	public static byte[] decryptSymmetric(byte[] key, byte[] encryptedText) throws Exception {
    		//Generamos clave secreta 
    	SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
			//Generamos el desencriptador
    	Cipher decryptCipher = Cipher.getInstance("AES");
    		//Desencriptamos
    	decryptCipher.init(Cipher.DECRYPT_MODE, keySpec);
    		//Devolvemos el texto desencriptado
    	return decryptCipher.doFinal(encryptedText);
    	}

    	
}
