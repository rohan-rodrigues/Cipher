import static org.junit.Assert.*;

import org.junit.Test;

public class LetterBagTest {

	@Test
	public void getTotalWords() {
		LetterBag b = new LetterBag();
		b.add("h");
		b.add("b");
		
		int total = b.getTotalWords();
		assertEquals(total, 2);
	}
	
	@Test
	public void NumUniqueWords() {
		LetterBag b = new LetterBag();
		b.add("h");
		b.add("h");
		b.add("h");
		b.add("h");
		b.add("h");
		b.add("a");
		
		int uniqueWords = b.NumUniqueWords();
		assertEquals(uniqueWords, 2);
	}
	
	@Test
	public void NumOccurances() {
		LetterBag b = new LetterBag();
		b.add("h");
		b.add("h");
		b.add("h");
		b.add("h");
		b.add("b");
		b.add("a");
		
		int occurances = b.numOccurances("h");
		assertEquals(occurances, 4);
	}
	
	@Test
	public void getMostFrequent() {
		LetterBag b = new LetterBag();
		b.add("h");
		b.add("h");
		b.add("h");
		b.add("h");
		b.add("b");
		b.add("a");
		
		String mostFrequent = b.getMostFrequent();
		assertEquals(mostFrequent, "h");
	}
	
	@Test
	public void getNMostFrequent() {
		LetterBag b = new LetterBag();
		b.add("h");
		b.add("h");
		b.add("h");
		b.add("h");
		b.add("b");
		b.add("a");
		
		String[] mostFrequent = b.getNMostFrequent(2);
		String[] correctAnswer = {"h", "a"};
	//	assertEquals(mostFrequent[0], correctAnswer[0]);
		assertEquals(mostFrequent[1], correctAnswer[1]);
	}

}
