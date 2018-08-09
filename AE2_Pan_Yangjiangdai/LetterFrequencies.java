/**
 * Programming AE2
 * Processes report on letter frequencies
 */
public class LetterFrequencies
{
	
	/** Size of the alphabet */
	private final int SIZE = 26;
	
	/** Count for each letter */
	private int [] alphaCounts;
	
	/** The alphabet */
	private char [] alphabet; 
												 	
	/** Average frequency counts */
	private double [] avgCounts = {8.2, 1.5, 2.8, 4.3, 12.7, 2.2, 2.0, 6.1, 7.0,
							       0.2, 0.8, 4.0, 2.4, 6.7, 7.5, 1.9, 0.1, 6.0,  
								   6.3, 9.1, 2.8, 1.0, 2.4, 0.2, 2.0, 0.1};

	/** Character that occurs most frequently */
	private char maxCh;

	/** Total number of characters encrypted/decrypted */
	private int totChars;
	
	/**
	 * Instantiates a new letterFrequencies object.
	 */
	public LetterFrequencies()
	{
		alphabet = new char [SIZE];
		alphaCounts = new int [SIZE];
		
		for (int i = 0; i < SIZE; i++)
		{
			alphabet[i] = (char)('A' + i);
			alphaCounts[i] = 0;
		}
		totChars = 0;
	}
		
	/**
	 * Increases frequency details for given character
	 * @param ch the character just read
	 */
	public void addChar(char ch)
	{
		int index = ch - 65;
		int count = alphaCounts[index];
		alphaCounts[index] = count + 1;  					
		totChars ++;
	}
	
	/**
	 * Gets the maximum frequency
	 * @return the maximum frequency
	 */
	private double getMaxPC()
	{
		int max = alphaCounts[0];
		for(int i = 0; i < SIZE; i++)
		{
			
			//compare and get the max
			if(max <= alphaCounts[i])
			{
				max = alphaCounts[i];
				maxCh = alphabet[i];
			}
		}
		double MaxPC = (double) max / (double) totChars;
		String SMaxPC = String.format("%.2f", MaxPC);
		MaxPC = Double.valueOf(SMaxPC);
		return MaxPC;
	}
	
	/**
	 * Returns a String consisting of the full frequency report
	 * @return the report
	 */
	public String getReport()
	{
		String report = "LETTER ANALYSIS\r\n\r\n" + "Letter  Freq  Freq%  AvgFreq%  Diff\r\n";
		for(int i = 0; i < SIZE; i++) 
		{
			
			//Letter
			report = report + "   " + alphabet[i];
			report = report + Space1(alphaCounts[i]) + alphaCounts[i];
			
			//Freq
			double Freq = alphaCounts[i] / (double)totChars;
			String SFreq = String.format("%.2f", Freq);
			Freq = Double.valueOf(SFreq.trim());
			report = report + Space2(Freq) + Freq;
			
			//Freq% & AvgFreq%
			report = report + Space2(avgCounts[i]) + avgCounts[i];
			
			//Diff
			double Diff = Freq - avgCounts[i];
			String SDiff = String.format("%.2f", Diff);
			Diff = Double.valueOf(SDiff.trim());
			report = report + Space2(Diff) + " " + Diff;
			report = report + "\r\n";
		}
		getMaxPC();
		report = report + "The most frequent letter is " + maxCh + " at " + getMaxPC() + "%"; 
	    return report;
	}
	
	//space for integer
	public String Space1(int num) 
	{
		int length = 6 - String.valueOf(num).length();
		String space = "";
		for(int i = 0; i < length; i++)
		{
			space = space + " ";
		}
		return space;
	}

	//space for double
	public String Space2(double num) 
	{
		int length = 8 - String.valueOf(num).length();
		String space = "";
		for(int i = 0; i < length; i++)
		{
			space = space + " ";
		}
		return space;
	}
	
}

