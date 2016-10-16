import java.util.Arrays;

public class VigenereCipher {

	public static void main(String[] args) {
		System.out.println(vigenereCipher("HELPMEIAMUNDERATTACK", "CODE"));
		System.out.println("JSOTOSLEOJQHGGDXVOFO");
		System.out.println(vigenereDecipher("JSOTOSLEOJQHGGDXVOFO", "CODE"));

	}
	private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	
	public static String vigenereCipher(String plainText, String codeWord) {
		int [] indexes = indexes(codeWord,plainText);
		String string = "";
		for (int i = 0; i < plainText.length(); i++) {
			String character = plainText.substring(i, i+1);
			int index= ALPHABET.indexOf(character);
			index += indexes[i];
			index %= ALPHABET.length();
			string += ALPHABET.substring(index, index+1);
			
		}
		return string;
	}
	public static String vigenereDecipher(String string, String codeWord) {
		int [] indexes = indexes(codeWord,string);
		String plainText = "";
		for (int i = 0; i < string.length(); i++) {
			String character = string.substring(i, i+1);
			int index= ALPHABET.indexOf(character);
			index -= indexes[i];
			while (index < 0) {
				index += ALPHABET.length();
			}
			plainText += ALPHABET.substring(index, index+1);
			
		}
		return plainText;
	}
	
	
	public static int[] indexes(String codeWord, String plaintext) {
		int [] indexes = new int [codeWord.length()];
		int [] finalIndexArray = new int [plaintext.length()];
		int finalIndex = 0;
		
		for (int index = 0; index < codeWord.length(); index++) {
			String character = codeWord.substring(index,index+1);
			indexes[index]= ALPHABET.indexOf(character);
		}

		for (int i = 0; i < finalIndexArray.length; i++) {
			finalIndex = i%indexes.length;
			
			finalIndexArray[i] = indexes[finalIndex];			
			
		} 
		return finalIndexArray;
	}

}
