package Decryption;

import java.util.Scanner;

/*
 * Caesar cipher encryption
 */
public class VigenereCipher extends Cipher {
	
	/*
	 * Construct cipher with given plaintxt and key
	 */
	public VigenereCipher(String plaintxt, String key) {
		key = assertKeyLength(plaintxt, key);
		encrypt(plaintxt, key);
	}
	
	/*
	 * Cipher algorithm encryption
	 */
	@Override
	public void encrypt(String plaintxt, String key) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < plaintxt.length(); ++i) {
			char plainChar = plaintxt.charAt(i),
				 keyChar = key.charAt(i);
			int position = addPosition(plainChar, keyChar);
			builder.append(ALPHABET.charAt(position));
		}
		ciphertxt = builder.toString();
		System.out.printf("ciphertxt: %s\n", ciphertxt);
	}
	
	/*
	 * Cipher algorithm decryption
	 */
	@Override
	public void decrypt(String key) {
		key = assertKeyLength(ciphertxt, key);
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < ciphertxt.length(); ++i) {
			char cipherChar = ciphertxt.charAt(i),
				 keyChar = key.charAt(i);
			int position = subPosition(cipherChar, keyChar);
			builder.append(ALPHABET.charAt(position));
		}
		System.out.printf("plaintxt: %s\n", builder);
	}

	/*
	 * Key must be at least same length as plaintxt
	 */
	private String assertKeyLength(String txt, String key) {
		Scanner scan = new Scanner(System.in);
		while (key.length() < txt.length()) {
			System.out.println("Key should be same length as plaintext");
			System.out.print("Enter key: ");
			key = scan.next();
			System.out.println();
		}
		scan.close();
		return key;
	}
	
	/*
	 * Return the added positions of the chars
	 */
	private int addPosition(char char1, char char2) {
		return (position(char1) + position(char2)) % 26;
	}
	
	/*
	 * Return the added positions of the chars
	 */
	private int subPosition(char char1, char char2) {
		int position = (position(char1) - position(char2)) % 26;
		return position < 0 ? position + 26 : position;
	}
	
	/*
	 * Return position of character within the alphabet
	 */
	private int position(char character) {
		return ALPHABET.indexOf(character);
	}
}












