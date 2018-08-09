/**
 * Programming AE2
 * Contains mono alphabetic cipher and methods to encode and decode a character.
 */
public class MonoCipher
{
	/** The size of the alphabet. */
	private final int SIZE = 26;

	/** The alphabet. */
	private char [] alphabet;
	
	/** The cipher array. */
	public char [] cipher;

	/**
	 * Instantiates a new mono cipher.
	 * @param keyword the cipher keyword
	 */
	public MonoCipher(String keyword)
	{
		//create alphabet
		alphabet = new char [SIZE];
		for (int i = 0; i < SIZE; i++)
			alphabet[i] = (char)('A' + i);
		
		// create first part of cipher from keyword
		cipher = new char [SIZE];
		for (int j = 0; j < keyword.length();j++)
			cipher[j] = keyword.charAt(j);
		
		// create remainder of cipher from the remaining characters of the alphabet
		boolean check;
		int jj = keyword.length();
		for(int i = 0; i < SIZE; i++) 
		{
			check = true;
			for(int j = 0; j < keyword.length(); j++) 
			{
				if(keyword.charAt(j) == ((char)('Z' - i)))
				{
					check = false;
				}
			}
				if(check && jj < SIZE)
					{
						cipher[jj] = (char)('Z' - i);
						jj++;
					}
		}
		
		// print cipher array for testing and tutors
		System.out.println(cipher);
	}
	
	/**
	 * Encode a character
	 * @param ch the character to be encoded
	 * @return the encoded character
	 */
	public char encode(char ch)
	{
		for(int i = 0; i < SIZE; i++)
		{
			if(ch == alphabet[i])
			{
				return cipher[i];
			}
		}
	    return ' '; 
	}

	/**
	 * Decode a character
	 * @param ch the character to be encoded
	 * @return the decoded character
	 */
	public char decode(char ch)
	{
		for(int i = 0; i < SIZE; i++)
		{
			if(ch == cipher[i])
			{
				return alphabet[i];
			}
		}
	    return ' '; 
	}
	
}
