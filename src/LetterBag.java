import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;

public class LetterBag {
	
	private final String alphabet = "abcdefghiklmnopqrstuvwxyz ";
	private int[] letterFrequencies;
	
	public LetterBag() {
		letterFrequencies = new int[27];
	}
	
	/***
	 * adds a letter to the bag
	 * @param letter - the letter to be added
	 */
	public void add(String letter) {
		String lower = letter.toLowerCase();
		int index = getIndexForLetter(lower);
		letterFrequencies[index]++;
	}
	
	private int getIndexForLetter(String lower) {
		return alphabet.indexOf(lower);
	}
	
	/***
	 * Finds the total words in the letterFrequencies array.
	 * @return returns the total words in the letterFrequencies array.
	 */
	public int getTotalWords() {
		int totalWords = 0;
		
		for (int i = 0; i < letterFrequencies.length; i++) {
			totalWords += letterFrequencies[i];
		}
		return totalWords;
	}
	
	
	/***
	 * Finds the total number of unique words in the letterFrequencies array.
	 * @return returns the total number of unique words in the letterFrequencies array.
	 */
	public int NumUniqueWords() {
		int uniqueWords = 0;
		for (int i = 0; i < letterFrequencies.length; i++) {
			if (letterFrequencies[i] > 0) {
				uniqueWords++;
			}
		}
		return uniqueWords;
	}
	
	
	/***
	 * Finds the number of times that the input letter is in the letterFrequencies array.
	 * @param letter - the letter that is used to find how many times it is in the letterFrequencies array.
	 * @return returns the number of times that the input letter is in the letterFrequencies array.
	 */
	public int numOccurances(String letter) {
		int indexOfLetter = alphabet.indexOf(letter);
		int occurance = letterFrequencies[indexOfLetter];
		return occurance;
	}
	
	
	/***
	 * Finds the letter with the highest frequency.
	 * @return returns the letter with the highest frequency.
	 */
	public String getMostFrequent(){
		String mostFrequentLetter = " ";
		int highestFrequency = 0;
		
		for (int i = 0; i < letterFrequencies.length-1; i++) {
			if (letterFrequencies[i] > highestFrequency) {
				mostFrequentLetter = alphabet.substring(i, i+1);
				highestFrequency = letterFrequencies[i];
			}
		}
		return mostFrequentLetter;
	}
	

	/***
	 * Finds the most frequent letter.
	 * @param arr - the input array that is used to find the most frequent letter.
	 * @return returns the most frequent letter.
	 */
	public String getMostFrequentLetterInAnyArray(int[] arr) {
		String mostFrequentLetter = " ";
		int highestFrequency = 0;
		
		for (int i = 0; i < arr.length-1; i++) {
			if (arr[i] > highestFrequency) {
				mostFrequentLetter = alphabet.substring(i, i+1);
				highestFrequency = arr[i];
			}
		}
		return mostFrequentLetter;
	}
	
	
	/***
	 * Finds the input number of most frequent letters.
	 * @param num - the number of most frequent letters that the method is trying to find.
	 * @return returns the input number of most frequent letters.
	 */
	public String[] getNMostFrequent(int num) {
		ArrayList<Integer>letterIndeces = convertToArrayList(letterFrequencies);
		ArrayList<String> newFrequencies = new ArrayList<String>();
		
		String mostFrequentLetter = getMostFrequentLetterInAnyArray(letterFrequencies);
		newFrequencies.add(mostFrequentLetter);
		letterIndeces.remove(alphabet.indexOf(mostFrequentLetter));
		
		for (int i = 1; i < num; i++) {
			mostFrequentLetter = getMostFrequentLetterInAnyArray(letterFrequencies);
			newFrequencies.add(mostFrequentLetter);
			letterIndeces.remove(alphabet.indexOf(mostFrequentLetter));
		}
		
		String[] array = new String[newFrequencies.size()];
		return newFrequencies.toArray(array);
	}
	
	
	/***
	 * Converts an array to an ArrayList.
	 * @param letters - the array that is converted to an ArrayList.
	 * @return an arrayList that has been converted from an array.
	 */
	public ArrayList<Integer> convertToArrayList(int[]letters) {
		ArrayList<Integer> lettersIndex = new ArrayList<Integer>();
		for (int i = 0; i < letters.length; i++) {
			lettersIndex.add(i,letters[i]);
		}
		
		return lettersIndex;	
	}
	

}
