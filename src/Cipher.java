import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class Cipher {
	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789,.() '\"![]/%-_;?-=:" + '\n' + '\r';
	private static final String SIMPLE_ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	// Set this variable to the default alphabet you wish to use
	private static final String DEFAULT_ALPHABET = ALPHABET;
	private static final Dictionary dictionary = Dictionary.buildDictionary("/Users/rohanrodrigues/Documents/workspace_mars/CipherBlankTemplate/words.txt");
	private static final String numbersWordsEnglish = null;

	
	/***
	 * Returns plaintext encrypted by the rotation cipher with a shift of movement.
	 * @param alphabet the alphabet to be used for the encryption
	 * @param plainText the plain text to be encrypted.
	 * @param shiftAmount the number of characters in ALPHABET to shift by.
	 * @return returns the encrypted plainText.
	 */
	public static String rotationCipherEncrypt(String plain, int shift, String alphabet) {
		String newStr = "";
		for (int i = 0; i < plain.length(); i++) {
			int indexChar = alphabet.indexOf(plain.substring(i,i+1));
			int shiftedIndex = (indexChar + shift) % alphabet.length();
			
			while (shiftedIndex < 0) {
				shiftedIndex += alphabet.length();
			}
		
			newStr += alphabet.substring(shiftedIndex, shiftedIndex+1);
		}
		
		return newStr;
	}
	

	/**
	 * Returns a the result of decrypting cipherText by shiftAmount using the rotation cipher.
	 * @param alphabet the alphabet to be used for the encryption
	 * @param cipherText the encrypted text.
	 * @param shiftAmount the key to decrypt the cipher.
	 * @return returns the decrypted cipherText.
	 */
	public static String rotationCipherDecrypt(String cipher, int shift, String alphabet) {
		String newStr = "";
		for (int i = 0; i < cipher.length(); i++) {
			int indexChar = alphabet.indexOf(cipher.substring(i,i+1));
			int shiftedIndex = (indexChar - shift) % alphabet.length();
			
			while (shiftedIndex < 0) {
				shiftedIndex += alphabet.length();
			}
		
			newStr += alphabet.substring(shiftedIndex, shiftedIndex+1);
		}
		
		return newStr;
	}

	
	/***
	 * Returns a new string with the letters replaced.
	 * @param plaintext - the text to be encrypted.
	 * @param permutation - the array that describes how the letters are being replaced.
	 * @param alphabet - the alphabet that is used to encrypt the plaintext.
	 * @return return a new string with the letters replaced.
	 */
	public static String substitutionCipher(String plaintext, int[] permutation, String alphabet) {
		String newStr = "";
		for (int i = 0; i < plaintext.length(); i++) {
			String character = plaintext.substring(i,i+1);
			int permutationChar = permutation[alphabet.indexOf(character)];
			String newCharacter = alphabet.substring(permutationChar,permutationChar+1);
			newStr += newCharacter;
		}
		return newStr;
	}
	
	/***
	 * Returns true if the input permutation is valid and false if otherwise.
	 * @param permutation - the array that is checked to see whether it is a valid permutation.
	 * @return returns true if the input permutation is valid and false if otherwise.
	 */
	public static boolean isValidPermutation(int[] permutation) {
		for (int i = 0; i < permutation.length; i++) {
			for (int j = i + 1; j < permutation.length; j++) {
				if (permutation[i] == permutation[j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	/***
	 * Returns a random permutation of a the input length.
	 * @param length - the size of the permutation that the method will generate.
	 * @return returns a random permutation of the input length.
	 */
	public static int[] randomPermutation(int length) {
		int[] permutation = new int[length];
		
		do {
			for (int i = 0; i < length; i++) {
				permutation[i] = (int)(1 + Math.random() * length);
			}
		} while (!isValidPermutation(permutation));
		return permutation;
	}

	/**
	 * Returns plaintext encrypted by the vigenere cipher encoded with the String code
	 * @param alphabet the alphabet to be used for the encryption
	 * @param plainText the plain text to be encrypted.
	 * @param code the code to use as the encryption key.
	 * @return returns the encrypted plainText.
	 */
	public static String vigenereCipherEncrypt(String plainText, String code, String alphabet) {
		int [] indexes = indexes(code,plainText, alphabet);
		String cipherText = "";
		for (int i = 0; i < plainText.length(); i++) {
			String character = plainText.substring(i, i+1);
			int index= alphabet.indexOf(character);
			index += indexes[i];
			index %= alphabet.length();
			cipherText += alphabet.substring(index, index+1);
		}
		return cipherText;
	}
	

	/**
	 * Returns the result of decrypting cipherText with the key code.
	 * @param alphabet the alphabet to be used for the encryption
	 * @param cipherText the encrypted text.
	 * @param code the decryption key
	 * @return returns the decrypted cipherText.
	 */
	public static String vigenereCipherDecrypt(String cipherText, String code, String alphabet) {
		int [] indexes = indexes(code,cipherText, alphabet);
		String plainText = "";
		for (int i = 0; i < cipherText.length(); i++) {
			String character = cipherText.substring(i, i+1);
			int index= alphabet.indexOf(character);
			index -= indexes[i];
			while (index < 0) {
				index += alphabet.length();
			}
			plainText += alphabet.substring(index, index+1);
		}
		return plainText;
	}
	

	public static int[] indexes(String codeWord, String plaintext, String alphabet) {
		int [] indexes = new int [codeWord.length()];
		int [] finalIndexArray = new int [plaintext.length()];
		int finalIndex = 0;
		
		for (int index = 0; index < codeWord.length(); index++) {
			String character = codeWord.substring(index,index+1);
			indexes[index]= alphabet.indexOf(character);
		}

		for (int i = 0; i < finalIndexArray.length; i++) {
			finalIndex = i%indexes.length;
			
			finalIndexArray[i] = indexes[finalIndex];			
			
		} 
		return finalIndexArray;
	}

	/**
	 * returns a copy of the input plaintext String with invalid characters
	 * stripped out.
	 * 
	 * @param plaintext
	 *          The plaintext string you wish to remove illegal characters from
	 * @param alphabet
	 *          A string of all legal characters.
	 * @return String A copy of plain with all characters not in alphabet removed.
	 */
	private static String stripInvalidChars(String plaintext, String alphabet) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < plaintext.length(); i++) { 			// loop through plaintext
			if (alphabet.indexOf(plaintext.charAt(i)) >= 0) 	// get index of char
				b.append(plaintext.charAt(i)); 									// if it exists, keep it
			else																// otherwise skip it &
				System.out.println("Stripping letter: \"" + plaintext.charAt(i) + "\"");
		}
		return b.toString();
	}
	
	
	/***
	 * returns the decoded ciphertext
	 * @param ciphertext - the input that is decoded
	 * @return returns the decoded ciphertext
	 */
	public static String crackRotationCipher(String ciphertext, String alphabet) {
		String plaintext = "";
 
		for (int shiftAmount = 0; shiftAmount < Integer.MAX_VALUE; shiftAmount++) {
			 plaintext = rotationCipherDecrypt(ciphertext, shiftAmount, alphabet);
			 
			 plaintext = removePunctuation(plaintext);
			 if (isEnglish(plaintext)) {
			 	return plaintext;
			 } 
		}
		return "";
	}
	
	
	/***
	 * Returns a new string without any punctuation
	 * @param plaintext - the string that is copied to the new string.
	 * @return returns a new string without any punctuation
	 */
	public static String removePunctuation(String plaintext) {
		String newStr = "";
		 plaintext = plaintext.toLowerCase(); 
		 String basicAlphabet = "abcdefghijklmnopqrstuvwxyz";
		 
		 for (int i = 0; i < plaintext.length(); i++) {
			 if (basicAlphabet.contains(plaintext.substring(i,i+1))) {
				 newStr += plaintext.substring(i,i+1);
			 }
		 }
		 
		 return newStr;
	}
	
	
	/***
	 * Returns true if plaintext is valid English and false if otherwise.
	 * @param plaintext - the text you wish to test for whether it's valid English.
	 * @return returns true if plaintext is valid English and false if otherwise
	 */
	private static boolean isEnglish(String plaintext) {
		String[] words = getWords(plaintext);
		
		int numberWordsEnglish = 0;
		for (int i = 0; i < words.length; i++) {
			if (dictionary.isWord(words[i])) {
				numberWordsEnglish++;
			}
		}
		if (((double) numberWordsEnglish) / (words.length) >= 0.8) {
			return true;
		}
		
		return false;
	}

	/***
	 * returns an array of the words inside the input string.
	 * @param str - the String that is used to find the words inside of it.
	 * @return returns an array of the words inside the input string.
	 */
	public static String[] getWords(String str) {
		String[] strings = new String[countStrings(str)];
		String trimmedString = trimString(str);
		
		int lastIndexOfString = trimmedString.lastIndexOf(" ");
		
		for (int stringsIndex = 0; stringsIndex < strings.length-1; stringsIndex++) {
			for (int i = 0; i < lastIndexOfString; i++, stringsIndex++) {
				String indivString = "";
				while (!trimmedString.substring(i, i+1).equals(" ")) {
					indivString += trimmedString.substring(i, i+1);
					i++;
				}
				strings[stringsIndex] = indivString;
			}
		}
		
		String lastString = "";
		for (int i = lastIndexOfString+1; i < trimmedString.length(); i++) {
			lastString += trimmedString.substring(i,i+1);
		}
		strings[strings.length-1] = lastString;
		
		return strings;
	}
	
	/***
	 * Returns the number of words in the input string.
	 * @param str - the String that is used to check for the number of words.
	 * @return returns the number of words in the input string.
	 */
	public static int countStrings(String str) {
		String trimmedString = trimString(str);
		
		int numOfStrings = 0;
		boolean isString = true;
		
		for (int i = 0; i < trimmedString.length()-1; i++) {
			if (isString) {
				if (trimmedString.substring(i,i+1).equals(" ")) {
					isString = false;
					numOfStrings++;
				}
			}
			else {
				if (!trimmedString.substring(i,i+1).equals(" ")) {
					isString = true;
				}
			}
		}
		
		if (!trimmedString.substring(trimmedString.length()-1, trimmedString.length()).equals(" ")) {
			numOfStrings++;
		}
		
		return numOfStrings;
	}
	
	/***
	 * Returns a new string without spaces at the front or at the end.
	 * @param str - the string that is copied to a new string to be trimmed.
	 * @return returns a new string without spaces at the front or at the end.
	 */
	public static String trimString(String str) {
		String newStr = "";
		
		int lastIndexOfFirstSpace = 0;
		int firstIndexOfLastSpace = str.length();
		
		while(str.substring(lastIndexOfFirstSpace,lastIndexOfFirstSpace+1).equals(" ")) {
			lastIndexOfFirstSpace++;
		}
		
		while (str.substring(firstIndexOfLastSpace-1,firstIndexOfLastSpace).equals(" ")) {
			firstIndexOfLastSpace--;
		}
		
		for (int i = lastIndexOfFirstSpace; i < firstIndexOfLastSpace; i++) {
			newStr += str.substring(i, i+1);
		}
		
		return newStr;
	}

}