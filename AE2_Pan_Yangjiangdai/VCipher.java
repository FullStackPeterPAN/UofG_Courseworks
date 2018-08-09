/**
 * Programming AE2 Class contains V cipher and methods to encode and
 * decode a character
 */
public class VCipher {
	private char[] alphabet; 
	
	// the letters of the alphabet
	private final int SIZE = 26;
	
	// more instance variables
	public char[][] cipher;

	/**
	 * The constructor generates the cipher
	 * 
	 * @param keyword
	 * the cipher keyword
	 */
	public VCipher(String keyword) 
	{
		alphabet = new char[SIZE];
		for (int i = 0; i < SIZE; i++) 
		{
			alphabet[i] = (char) ('A' + i);
		}
		
		//create the v cipher
		cipher = new char[keyword.length()][SIZE];
		for (int i = 0; i < keyword.length(); i++) 
		{
			int jj = 0;
			for (int j = 0; j < SIZE; j++) 
			{
				if (j < ('Z' - keyword.charAt(i) + 1))
				{
					cipher[i][j] = (char) (keyword.charAt(i) + j);
				} 
				else
				{
					cipher[i][j] = (char) ('A' + jj);
					jj++;
				}
			}
		}
		
		//print it out
		for (int i = 0; i < keyword.length(); i++) 
		{
			for (int j = 0; j < SIZE; j++)
			{
				System.out.print(cipher[i][j] + " ");
			}
			System.out.println("");
		}
	}

	/**
	 * Encode a character
	 * 
	 * @param ch
	 *            the character to be encoded
	 * @return the encoded character
	 */
	public char encode(char ch, int array) 
	{

		for (int i = 0; i < SIZE; i++)
		{
			if (ch == alphabet[i]) 
			{
				return cipher[array][i];
			}
		}
		return ' ';
	}

	
	/**
	 * Decode a character
	 * 
	 * @param ch
	 *            the character to be decoded
	 * @return the decoded character
	 */
	public char decode(char ch, int array) 
	{
		for (int i = 0; i < SIZE; i++)
		{
			if (ch == cipher[array][i]) 
			{
				return alphabet[i];
			}
		}
		return ' ';
	}
	
}
