import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CipherTest {
	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789,.() '\"![]/%-_;?-=:" + '\n' + '\r';


	@Test
	public void rotationCipherEncryptBy3() {
		String plaintext = "the quick brown fox jumped over the lazy dogs";
		String correctCipherText = "wkh!txlfn!eurzq!irA!mxpshg!ryhu!wkh!odCB!grjv";
		String testCipherText = OldCipher.rotationCipherEncrypt(plaintext, 3, ALPHABET);
		assertEquals(testCipherText, correctCipherText);
	}
	
	@Test
	public void rotationCipherDecryptBy3() {
		String plaintext = "the quick brown fox jumped over the lazy dogs";
		String correctCipherText = "wkh!txlfn!eurzq!irA!mxpshg!ryhu!wkh!odCB!grjv";
		String testPlainText = OldCipher.rotationCipherDecrypt(correctCipherText, 3, ALPHABET);
		assertEquals(testPlainText, plaintext);
	}
	
	@Test
	public void rotationCipherDecryptBy100() {
		String plaintext = "the quick brown fox jumped over the lazy dogs";
		String correctCipherText = "KyvaHLztBasIFNEawFOaALDGvuaFMvIaKyvaCrQPauFxJ";
		String testPlainText = OldCipher.rotationCipherDecrypt(correctCipherText, 100, ALPHABET);
		assertEquals(testPlainText, plaintext);
	}
	
	@Test
	public void rotationCipherEncryptBy100() {
		String plaintext = "the quick brown fox jumped over the lazy dogs";
		String correctCipherText = "KyvaHLztBasIFNEawFOaALDGvuaFMvIaKyvaCrQPauFxJ";
		String testCipherText = OldCipher.rotationCipherEncrypt(plaintext, 100, ALPHABET);
		assertEquals(testCipherText, correctCipherText);
	}
	
	@Test
	public void rotationCipherEncryptBy3CapsWithPunctuation() {
		String plaintext = "\"THE\n\rQUICK\nBROWN FOX. JUMPED OVER THE LAZY DOGS!\"";
		String correctCipherText = "]WKHbcTXLFNbEURZQ!IR0 !MXPSHG!RYHU!WKH!OD21!GRJV/]";
		String testCipherText = OldCipher.rotationCipherEncrypt(plaintext, 3, ALPHABET);
		assertEquals(testCipherText, correctCipherText);
	}
	
	@Test
	public void rotationCipherDecryptBy3CapsWithPunctuation() {
		String plaintext = "\"THE\n\rQUICK\nBROWN FOX. JUMPED OVER THE LAZY DOGS!\"";
		String correctCipherText = "]WKHbcTXLFNbEURZQ!IR0 !MXPSHG!RYHU!WKH!OD21!GRJV/]";
		String testPlainText = OldCipher.rotationCipherDecrypt(correctCipherText, 3, ALPHABET);
		assertEquals(testPlainText, plaintext);
	}
	
	@Test
	public void rotationCipherDecryptBy100CapsWithPunctuation() {
		String plaintext = "\"THE\n\rQUICK\nBROWN FOX. JUMPED OVER THE LAZY DOGS!\"";
		String correctCipherText = "c,YVpq7.ZT1pS85)4aW5 :a0.36VUa5(V8a,YVa2R\"'aU5X9dc";
		String testPlainText = OldCipher.rotationCipherDecrypt(correctCipherText, 100, ALPHABET);
		assertEquals(testPlainText, plaintext);
	}
	
	@Test
	public void rotationCipherEncryptBy100CapsWithPunctuation() {
		String plaintext = "\"THE\n\rQUICK\nBROWN FOX. JUMPED OVER THE LAZY DOGS!\"";
		String correctCipherText = "c,YVpq7.ZT1pS85)4aW5 :a0.36VUa5(V8a,YVa2R\"'aU5X9dc";
		String testCipherText = OldCipher.rotationCipherEncrypt(plaintext, 100, ALPHABET);
		assertEquals(testCipherText, correctCipherText);
	}
}
