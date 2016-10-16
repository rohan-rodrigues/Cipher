
public class FinalCipher {
	public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789,.() '\"![]/%-_;?-=:" + '\n' + '\r';
	private static final String SIMPLE_ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	// Set this variable to the default alphabet you wish to use
	private static final String DEFAULT_ALPHABET = ALPHABET;
	private static final Dictionary dictionary = Dictionary.buildDictionary("/Users/rohanrodrigues/Documents/workspace_mars/CipherBlankTemplate/words.txt");
	private static final String numbersWordsEnglish = null;
	
	
	/**
	 * Returns plaintext encrypted by the vigenere cipher encoded with the String code
	 * @param alphabet the alphabet to be used for the encryption
	 * @param plainText the plain text to be encrypted.
	 * @param code the code to use as the encryption key.
	 * @return returns the encrypted plainText.
	 */
	public String vigenereCipherEncrypt(String plain, String password, String alphabet) {
		int [] indexes = indexes(password,plain);
		String cipherText = "";
		for (int i = 0; i < plain.length(); i++) {
			String character = plain.substring(i, i+1);
			int index= ALPHABET.indexOf(character);
			index += indexes[i];
			index %= ALPHABET.length();
			cipherText += ALPHABET.substring(index, index+1);
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
	public String vigenereCipherDecrypt(String cipher, String password, String alphabet) {
		int [] indexes = indexes(password,cipher);
		String plainText = "";
		for (int i = 0; i < cipher.length(); i++) {
			String character = cipher.substring(i, i+1);
			int index= ALPHABET.indexOf(character);
			index -= indexes[i];
			while (index < 0) {
				index += ALPHABET.length();
			}
			plainText += ALPHABET.substring(index, index+1);
		}
		return plainText;
	}
	
	public int[] indexes(String codeWord, String plaintext) {
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
	
	
	/***
	 * Returns plaintext encrypted by the rotation cipher with a shift of movement.
	 * @param alphabet the alphabet to be used for the encryption
	 * @param plainText the plain text to be encrypted.
	 * @param shiftAmount the number of characters in ALPHABET to shift by.
	 * @return returns the encrypted plainText.
	 */
	public String rotationCipherEncrypt(String plain, int shift, String alphabet) {
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
	public String rotationCipherDecrypt(String cipher, int shift, String alphabet) {
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
	 * returns the decoded ciphertext
	 * @param ciphertext - the input that is decoded
	 * @return returns the decoded ciphertext
	 */
	public String rotationCipherCrack(String cipher, String alphabet) {
		String plaintext = "";
 
		for (int shiftAmount = 0; shiftAmount < Integer.MAX_VALUE; shiftAmount++) {
			 plaintext = rotationCipherDecrypt(cipher, shiftAmount, alphabet);
			 
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
	public String removePunctuation(String plaintext) {
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
	private boolean isEnglish(String plaintext) {
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
	public String[] getWords(String str) {
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
	public int countStrings(String str) {
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
	public String trimString(String str) {
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
	
	
	/***
	 * Returns the password for the the cipher text
	 * @param cipher - the text that is decoded
	 * @param alphabet - the alphabet which is used to decode the cipher text
	 * @return - returns the password for the the cipher text
	 */
	public String vigenereCipherCrackThreeLetter(String cipher, String alphabet) {	
		String password = "";
		
		for (int i = 1; i <= 3; i++) {
			LetterBag bag = new LetterBag();
			String group = getGroupCode(cipher, i, bag);
			String letter = findLetter(bag, " ", group, alphabet);
			password += letter;
		}
		
		return password;
	}
	
	/***
	 * Returns the password for the the cipher text
	 * @param cipher - the text that is decoded
	 * @param passwordLength - the length of the password that is used to decode the cipher text
	 * @param alphabet - the alphabet which is used to decode the cipher text
	 * @return - returns the password for the the cipher text
	 */
	public String vigenereCipherCrack(String cipher, int passwordLength, String alphabet) {	
		String password = "";
		
		for (int i = 1; i <= passwordLength; i++) {
			LetterBag bag = new LetterBag();
			String group = getGroupCode(cipher, i, bag);
			String letter = findLetter(bag, " ", group, alphabet);
			
			password += letter;
		}
		return password;
	}
	
	public String getGroupCode(String str, int groupNum, LetterBag bag) {
		String group = "";
		for (int i = 0; i < str.length(); i += groupNum) {
			String character1 = str.substring(i,i+1);
			bag.add(character1);
			group += str.substring(i,i+1);
		}
		return group;
	}
	
	public String findLetter(LetterBag bag, String mostOccurringEnglish, String groupText, String alphabet) {
		String mostFrequent = bag.getMostFrequent();
		
		int index = alphabet.indexOf(mostFrequent);
		int indexOfMostOccurring = alphabet.indexOf(mostOccurringEnglish);
		
		int shiftAmount = Math.abs(indexOfMostOccurring - index);
		
		String letter = alphabet.substring(shiftAmount, shiftAmount+1);
		return letter;
	}
	
}
