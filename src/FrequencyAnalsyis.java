
public class FrequencyAnalsyis {

	public static void main(String[] args) {
		System.out.println(vigenereCipherCrack("spnkl xhwsrci tjwt gftew", "abcdefghiklmnopqrstuvwxyz "));
	}
	
	
	public static String vigenereCipherCrack(String cipher, String alphabet) {	
		String password = "";
		
		for (int i = 1; i <= 3; i++) {
			LetterBag bag = new LetterBag();
			String group = getGroupCode(cipher, i, bag);
			String letter = findLetter(bag, " ", group, alphabet);
			password += letter;
		}
		
		return password;
	}
	
	
	public static String getGroupCode(String str, int groupNum, LetterBag bag) {
		String group = "";
		for (int i = 0; i < str.length(); i += groupNum) {
			String character1 = str.substring(i,i+1);
			bag.add(character1);
			group += str.substring(i,i+1);
		}
		return group;
	}
	
	public static String findLetter(LetterBag bag, String mostOccurringEnglish, String groupText, String alphabet) {
		String mostFrequent = bag.getMostFrequent();

		int index = alphabet.indexOf(mostFrequent);
		int indexOfMostOccurring = alphabet.indexOf(mostOccurringEnglish);
		
		int shiftAmount = Math.abs(indexOfMostOccurring - index);
		
		String letter = alphabet.substring(shiftAmount, shiftAmount+1);
		return letter;
	}
	

}
