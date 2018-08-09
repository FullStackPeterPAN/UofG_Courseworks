import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

/** 
 * Programming AE2
 * Class to display cipher GUI and listen for events
 */
public class CipherGUI extends JFrame implements ActionListener
{
	//instance variables which are the components
	private JPanel top, bottom, middle;
	private JButton monoButton, vigenereButton;
	private JTextField keyField, messageField;
	private JLabel keyLabel, messageLabel;

	//application instance variables
	//including the 'core' part of the textfile filename
	//some way of indicating whether encoding or decoding is to be done
	private MonoCipher mcipher;
	private VCipher vcipher;
	
	/**
	 * The constructor adds all the components to the frame
	 */
	public CipherGUI()
	{
		this.setSize(400,150);
		this.setLocation(100,100);
		this.setTitle("Cipher GUI");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.layoutComponents();
	}


	/**
	 * Helper method to add components to the frame
	 */
	public void layoutComponents()
	{

		//top panel is yellow and contains a text field of 10 characters
		top = new JPanel();
		top.setBackground(Color.yellow);
		keyLabel = new JLabel("Keyword : ");
		top.add(keyLabel);
		keyField = new JTextField(10);
		top.add(keyField);
		this.add(top,BorderLayout.NORTH);
		
		//middle panel is yellow and contains a text field of 10 characters
		middle = new JPanel();
		middle.setBackground(Color.yellow);
		messageLabel = new JLabel("Message file : ");
		middle.add(messageLabel);
		messageField = new JTextField(10);
		middle.add(messageField);
		this.add(middle,BorderLayout.CENTER);
		
		//bottom panel is green and contains 2 buttons
		bottom = new JPanel();
		bottom.setBackground(Color.green);
	
		//create mono button and add it to the top panel
		monoButton = new JButton("Process Mono Cipher");
		monoButton.addActionListener(this);
		bottom.add(monoButton);
	
		//create vigenere button and add it to the top panel
		vigenereButton = new JButton("Process Vigenere Cipher");
		vigenereButton.addActionListener(this);
		bottom.add(vigenereButton);
	
		//add the top panel
		this.add(bottom,BorderLayout.SOUTH);
	}
	
	/**
	 * Listen for and react to button press events
	 * (use helper methods below)
	 * @param e the event
	 */
	public void actionPerformed(ActionEvent e)
	{
		if(getKeyword() && processFileName())
		{
			boolean vigenere = true;
			if (e.getSource().equals(monoButton))
			{
				vigenere = false;
			}
			try
			{
				processFile(vigenere);
			} 
			
			//file not found
			catch (FileNotFoundException error)
			{
				clear();
				JOptionPane.showMessageDialog(null, "Invalid file name", "WARNING", JOptionPane.ERROR_MESSAGE);
				System.out.println("Error");
			}  
		}
	}
	
	/** 
	 * Obtains cipher keyword
	 * If the keyword is invalid, a message is produced
	 * @return whether a valid keyword was entered
	 */
	private boolean getKeyword()
	{
		String Key = keyField.getText();
		
		//if the keyword is empty - error
		if(Key == "" || Key.length() == 0)
		{
			//if message is also empty
			if(messageField.getText().length() == 0) 
			{
				JOptionPane.showMessageDialog(null, "Invalid keyword and file name", "WARNING", JOptionPane.ERROR_MESSAGE);
				clear();
				return false; 
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Invalid keyword", "WARNING", JOptionPane.ERROR_MESSAGE);
				clear();
				return false; 
			}
		}
		else
			for(int i = 0; i < Key.length(); i++)
			{
				for(int j = i + 1; j < Key.length(); j++) 
				{
					
					//repeat - error
					if(Key.charAt(i) == Key.charAt(j))
					{
						JOptionPane.showMessageDialog(null, "Invalid keyword", "WARNING", JOptionPane.ERROR_MESSAGE);
						clear();
						return false;
					}
				}
				
				//if not in capital
				if(Key.charAt(i) > (char)('Z') || Key.charAt(i) < (char)('A'))
				{
					JOptionPane.showMessageDialog(null, "Invalid keyword", "WARNING", JOptionPane.ERROR_MESSAGE);
					clear();
					return false;
				}
			}
		return true;
	}
	
	/** 
	 * Obtains filename from GUI
	 * The details of the filename and the type of coding are extracted
	 * If the filename is invalid, a message is produced 
	 * The details obtained from the filename must be remembered
	 * @return whether a valid filename was entered
	 */
	private boolean processFileName()
	{
		String FileName = messageField.getText();

		//if name empty - error
		if(FileName == "" || FileName.length() == 0)
		{
			JOptionPane.showMessageDialog(null, "Invalid file name", "WARNING", JOptionPane.ERROR_MESSAGE);
			clear();
			return false;
		}
		else
		{
			String LastLetter = FileName.substring((FileName.length()-1), (FileName.length()));		
			if(LastLetter.equals("P") || LastLetter.equals("C"))
			{
				return true;
			}
		
			//invalid format
			else
			{
				JOptionPane.showMessageDialog(null, "Invalid file name", "WARNING", JOptionPane.ERROR_MESSAGE);
				clear();
				return false;
			}
		}
	}
	
	/** 
	 * Reads the input text file character by character
	 * Each character is encoded or decoded as appropriate
	 * and written to the output text file
	 * @param vigenere whether the encoding is Vigenere (true) or Mono (false)
	 * @return whether the I/O operations were successful
	 */
	private boolean processFile(boolean vigenere) throws FileNotFoundException
	{
		LetterFrequencies LetterFrequencies = new LetterFrequencies();	
		String FileName = messageField.getText();
		String LastLetter = FileName.substring((FileName.length()-1), (FileName.length()));	
		String Message = FileName.substring(0, (messageField.getText().length()-1));
		
		//VCipher
		if(vigenere) 
		{	
			VCipher vc = new VCipher(keyField.getText());
			FileReader reader = null;
			FileWriter writer = null;
			FileWriter writerF = null;
			try 
			{
				try 
				{
					//if encode
					if(LastLetter.equals("P")) 
					{
						reader = new FileReader(messageField.getText() + ".txt"); 
						writer = new FileWriter(Message + "C.txt");
						writerF = new FileWriter(Message + "F.txt");

						//decide which array
						int i = 0;
						
						//if finished
						boolean done = false;
						while (!done)
						{
							int next = reader.read();
							
							//the end
							if (next == -1)
							{
								done = true;
							}
							else 
							{
								
								//ignore
								char c = (char) next;
								if(c < 65 || c > 90)
								{
									writer.write(c);
									i++;
								}
								
								//encode it
								else
								{
									c = vc.encode(c, i%(keyField.getText().length()));
									writer.write(c);
									LetterFrequencies.addChar(c);	
									i++;
								}
							}
						}
						
					}
					
					//decode
					else 
					{
						reader = new FileReader(messageField.getText() + ".txt"); 
						writer = new FileWriter(Message + "D.txt");
						writerF = new FileWriter(Message + "F.txt");

						//decide which array
						int i = 0;
						
						//if finished
						boolean done = false;
						while (!done)
						{
							int next = reader.read();
				
							//the end
							if (next == -1)
								done = true;
							else 
							{

								//ignore
								char c = (char) next;
								if(c < 65 || c > 90)
								{
									writer.write(c);
									i++;
								}
								
								//decode it
								else
								{
									c = vc.decode(c,i%(keyField.getText().length()));
									writer.write(c);
									LetterFrequencies.addChar(c);	
									i++;
								}
							}
						}
					}
				}
				finally 
				{
					
					//close the file
					if (reader != null || writer != null)
					{
						reader.close();
						writer.close();
						writerF.write(LetterFrequencies.getReport());
						writerF.close();
						clear();
					}
				}
			}
			catch (IOException e) 
			{
				clear();
				JOptionPane.showMessageDialog(null, "Error", "WARNING", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		//MonoCipher
		else 
		{
			MonoCipher mc = new MonoCipher(keyField.getText());
			FileReader reader = null;
			FileWriter writer = null;
			FileWriter writerF = null;
			try 
			{
				try 
				{
			
					//if encode
					if(LastLetter.equals("P")) 
					{
						reader = new FileReader(messageField.getText() + ".txt"); 
						writer = new FileWriter(Message + "C.txt");
						writerF = new FileWriter(Message + "F.txt");
				
						//if finished
						boolean done = false;
						while (!done)
						{

							int next = reader.read();
				
							//the end
							if (next == -1)
							done = true;
							else 
							{
							
								//ignore
								char c = (char) next;
								if(c < 65 || c > 90)
								{
									writer.write(c);
								}
								
								//encode it
								else
									{
									c = mc.encode(c);
									writer.write(c);
									LetterFrequencies.addChar(c);	
									}
							}
						}
					}
					
					//decode
					else 
					{
						reader = new FileReader(messageField.getText() + ".txt"); 
						writer = new FileWriter(Message + "D.txt");
						writerF = new FileWriter(Message + "F.txt");
						
						//if finished
						boolean done = false;
						while (!done)
						{
							int next = reader.read();
						
							//the end
							if (next == -1)
								done = true;
							else 
							{
							
								//ignore
								char c = (char) next;
								if(c < 65 || c > 90)
								{
									writer.write(c);
								}
								
								//encode it
								else
								{
									c = mc.decode(c);
									writer.write(c);
									LetterFrequencies.addChar(c);	
								}
							}
						}
					}
				}
				finally 
				{
					
					// close the file
					if (reader != null || writer != null)
						{
						reader.close();
						writer.close();
						writerF.write(LetterFrequencies.getReport());
						writerF.close();
						clear();
						}
				}
			}
			catch (IOException e) 
			{
				clear();
				JOptionPane.showMessageDialog(null, "Error", "WARNING", JOptionPane.ERROR_MESSAGE);
			}
		}
		return true;
	}
	
	//clear the fields
	public void clear()
	{
		keyField.setText("");
		messageField.setText("");
	}
}
