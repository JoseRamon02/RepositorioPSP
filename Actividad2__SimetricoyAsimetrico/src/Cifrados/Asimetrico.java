package Cifrados;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Asimetrico {

	static Scanner sc = new Scanner(System.in);

	public static void menu(int opcion,boolean salir) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {

		KeyPairGenerator keyP = KeyPairGenerator.getInstance("RSA");
		KeyPair pair = keyP.generateKeyPair();

		String TextoADescifrar = "";

		switch (opcion) {
		case 1: {
			CifrarMensaje(pair);
			break;
		}
		case 2: {
			DescifrarMensaje(TextoADescifrar, pair);
			break;
		}
		case 3: {
			RegenerarClaves();
			break;
		}
		case 4:{
			salir = true;
			break;
		}
		}
		
		System.out.println("Cerrando el programa...");
		System.exit(0);
	}

	public static String CifrarMensaje(KeyPair pair) {
		try {
			PublicKey publicKey = pair.getPublic();
			String TextoACifrar = "";
			System.out.println("Introduce texto a cifrar");
			TextoACifrar = sc.next();

			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);

			byte[] textoPlano = TextoACifrar.getBytes();
			byte[] textoCifrado = cipher.doFinal(textoPlano);
			System.out.println("Texto Cirfrado: " + Base64.getEncoder().encodeToString(textoCifrado));

			return Base64.getEncoder().encodeToString(textoCifrado);

		} catch (Exception e) {
			System.out.println("Algo anda mal :'(");
			return null;
		}
	}

	public static String DescifrarMensaje(String TextoADescifrar, KeyPair pair) {
		PrivateKey privKey = pair.getPrivate();
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privKey);
			byte[] textoCifrado = Base64.getDecoder().decode(TextoADescifrar);
			byte[] textoDescifrado = cipher.doFinal(textoCifrado);
			return new String(textoDescifrado);
			
		} catch (Exception e) {
			System.out.println("Error al desencriptar: " + e.getMessage());
			return null;
		}

	}

	public static void RegenerarClaves() {
	}

	public static void main(String[] args) {

		System.out.println("Introduce una opcion 1.Cifrar mensaje, 2.Descifrar mensaje, 3.Regenerar claves, 4. Salir");
		int opcion = sc.nextInt();
		boolean salir = false;
		
		try {
		
			while(!salir) menu(opcion,salir);	
		
		} catch (Exception e) {
			System.out.println("Creo que tienes que cambiar algo");
		}
	}
}
