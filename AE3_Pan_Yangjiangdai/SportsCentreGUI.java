import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

/**
 * Defines a GUI that displays details of a FitnessProgram object
 * and contains buttons enabling access to the required functionality.
 */
public class SportsCentreGUI extends JFrame implements ActionListener {
	
	/** GUI JButtons */
	private JButton closeButton, attendanceButton;
	private JButton addButton, deleteButton;

	/** GUI JTextFields */
	private JTextField idIn, classIn, tutorIn;

	/** Display of class timetable */
	private JTextArea display;

	/** Display of attendance information */
	private ReportFrame report;

	/** Names of input text files */
	private final String classesInFile = "ClassesIn.txt";
	private final String classesOutFile = "ClassesOut.txt";
	private final String attendancesFile = "AttendancesIn.txt";
	
	FitnessProgram fitnessProgram;
	
	/**
	 * Constructor for AssEx3GUI class
	 * @throws FileNotFoundException 
	 */
	public SportsCentreGUI() throws FileNotFoundException {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Boyd-Orr Sports Centre");
		setSize(700, 300);
		display = new JTextArea();
		display.setFont(new Font("Courier New", Font.PLAIN, 14));
		add(display, BorderLayout.CENTER);
		layoutTop();
		layoutBottom();
		fitnessProgram = new FitnessProgram();
		initLadiesDay();
		updateDisplay();
	}

	/**
	 * Creates the FitnessProgram list ordered by start time
	 * using data from the file ClassesIn.txt
	 * @throws FileNotFoundException 
	 */
	public void initLadiesDay() throws FileNotFoundException {
		FitnessClass fitnessClass;
		int count = 0;
		
		//read the file
		File file = new File(classesInFile);
		
		//scan the file until the end
		Scanner scanner = new Scanner(file);
		String contents = "";
		while(scanner.hasNext())
		{
			contents = contents + scanner.next() + " ";
		}
		scanner.close();
		
		//change to an array
		String[] contentsArray = contents.split(" ");		
		for(int i = 0; i < ((contentsArray.length) / 4); i++) 
		{
			fitnessClass = new FitnessClass(contentsArray[count], contentsArray[count+1], contentsArray[count+2], Integer.parseInt(contentsArray[count+3]));
			
			//add the class
			fitnessProgram.insertClass(fitnessClass, fitnessClass.getStartTime() - 9);
			count = count + 4;
		}
		initAttendances();
	}

	/**
	 * Initialises the attendances using data
	 * from the file AttendancesIn.txt
	 * @throws FileNotFoundException 
	 */
	public void initAttendances() throws FileNotFoundException {
		//read the file
		File file = new File(attendancesFile);
				
		//scan the file until the end
		Scanner scanner = new Scanner(file);
		String contents = "";
		while(scanner.hasNext())
		{
			contents = contents + scanner.next() + " ";
		}
		scanner.close();
				
		//change to an array
		String[] contentsArray = (contents).split(" ");
		
		//set every attendance
		int attendance1, attendance2, attendance3, attendance4, attendance5;
		for(int i = 0; i < (contentsArray.length) / 6; i++)
		{
			attendance1 = Integer.parseInt(contentsArray[i * 6 + 1]);
			attendance2 = Integer.parseInt(contentsArray[i * 6 + 2]);
			attendance3 = Integer.parseInt(contentsArray[i * 6 + 3]);
			attendance4 = Integer.parseInt(contentsArray[i * 6 + 4]);
			attendance5 = Integer.parseInt(contentsArray[i * 6 + 5]);
					
			//array of attendances
			int[] attendances = {attendance1, attendance2, attendance3, attendance4, attendance5};	
			
			//attendances for classes
			fitnessProgram.findById(contentsArray[i * 6]).setAttendance(attendances);
		}		
	}

	/**
	 * Instantiates timetable display and adds it to GUI
	 */
	public void updateDisplay()
	{
		//deal with each line separately
		String line1 = "", line2 = "", line3 = "";
		FitnessClass[] list = fitnessProgram.getFitnessClass();
		line1 = " " + "9-10"  + "       " + "10-11" + "       " + "11-12" + "       " + "12-13" + "       " + "13-14" + "       " + "14-15" + "       " + "15-16" + "\n";
	    display.setText(line1);
	    if(list[0] == null) 
	    {
	    	line2 = line2 + " Available ";
	    	line3 = line3 + "           ";
	    }
	    else
	    {
	    	line2 = line2 + " " + list[0].getName();
	    	line3 = line3 + " " + list[0].getTutorName() +  "      "; 
	    }

	    //add the rest
	    for(int i = 1; i < 7; i++)
	    {
	    	if(list[i] == null)
	    	{
	    		line2 = line2 + addSpace(line2,i) + "Available  ";
	    		line3 = line3 + addSpace(line3,i) + "           ";	
	    	}
	    	
	    	else
	    	{
	    		line2 = line2 + addSpace(line2,i) + list[i].getName();
	    		line3 = line3 + addSpace(line3,i) + list[i].getTutorName();
	    	}
	    }
	    
	    //new line
	    line2 = line2 + "\n";
	    display.append(line2);
	    display.append(line3);
	}

	/**
	 * adds buttons to top of GUI
	 */
	public void layoutTop() {
		JPanel top = new JPanel();
		closeButton = new JButton("Save and Exit");
		closeButton.addActionListener(this);
		top.add(closeButton);
		attendanceButton = new JButton("View Attendances");
		attendanceButton.addActionListener(this);
		top.add(attendanceButton);
		add(top, BorderLayout.NORTH);
	}

	/**
	 * adds labels, text fields and buttons to bottom of GUI
	 */
	public void layoutBottom() {
		// instantiate panel for bottom of display
		JPanel bottom = new JPanel(new GridLayout(3, 3));

		// add upper label, text field and button
		JLabel idLabel = new JLabel("Enter Class Id");
		bottom.add(idLabel);
		idIn = new JTextField();
		bottom.add(idIn);
		JPanel panel1 = new JPanel();
		addButton = new JButton("Add");
		addButton.addActionListener(this);
		panel1.add(addButton);
		bottom.add(panel1);

		// add middle label, text field and button
		JLabel nmeLabel = new JLabel("Enter Class Name");
		bottom.add(nmeLabel);
		classIn = new JTextField();
		bottom.add(classIn);
		JPanel panel2 = new JPanel();
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(this);
		panel2.add(deleteButton);
		bottom.add(panel2);

		// add lower label text field and button
		JLabel tutLabel = new JLabel("Enter Tutor Name");
		bottom.add(tutLabel);
		tutorIn = new JTextField();
		bottom.add(tutorIn);

		add(bottom, BorderLayout.SOUTH);
	}

	/**
	 * Processes adding a class
	 */
	public void processAdding()
	{
		
		//get the entered info
		String id = idIn.getText();
		String name = classIn.getText();
		String tutorName = tutorIn.getText();
		int time = fitnessProgram.firstAvailableTime();
		
		//if not available
		if(time == -1 )
		{
			errorMessage(" No available time. ");
		}
		
		//too long id
		else if(id.length() > 3)
		{
			errorMessage(" The ID is too long. ");
		}

		//class name characters contain non-alpha
		else if(formatCheck(name))
		{
			errorMessage(" The class name is in wrong format. ");
		}
	
		//tutor name characters contain non-alpha
		else if(formatCheck(tutorName))	
		{
			errorMessage(" The tutor name is in wrong format. ");
		}
		
		//check name length
		else if(name.length() > 11)
		{
			errorMessage(" The class name is too long. ");
		}
		
		//check tutor name length
		else if(name.length() > 11)
		{
			errorMessage(" The tutor name is too long. ");
		}
		
		 else if(fitnessProgram.checkClass(id))
		 {
			 errorMessage(" Repeat class id. ");
		 }
		
		//check if there is empty text field
		 else if(id.isEmpty() || name.isEmpty() || tutorName.isEmpty())
		 {
			 errorMessage(" Empty text field. ");	
		 }
		
		//add class if no error
		 else
		 {
			 FitnessClass fitnessClass = new FitnessClass(id, name, tutorName, time);	
			 fitnessProgram.insertClass(fitnessClass, time-9);
			 updateDisplay();
		 }
	}

	/**
	 * Processes deleting a class
	 */
	public void processDeletion()
	{
		//id of the class going to be deleted
		 String id = idIn.getText();
		 if(id.isEmpty())
		 {
			 errorMessage(" No input ID. ");
		 }
		 
		 //check if id exist
		 else if(!fitnessProgram.checkClass(id))
		 {
			 errorMessage("No class can be found with id: " + id);
		 }
		 
		 //delete if no error
		 else 
		 {
			 fitnessProgram.deleteClass(id);
			 updateDisplay(); 
		 }		 
	}

	/**
	 * Instantiates a new window and displays the attendance report
	 */
	public void displayReport() {
		report = new ReportFrame(fitnessProgram);
		report.setVisible(true);
	}

	/**
	 * Writes lines to file representing class name, 
	 * tutor and start time and then exits from the program
	 * @throws FileNotFoundException 
	 */
	public void processSaveAndClose() throws FileNotFoundException {
		String write = "";
		FitnessClass fitnessClass;
		
		//create the file
		File out = new File(classesOutFile);
		PrintWriter printWriter = new PrintWriter(out);
		
		//write info
		for(int i = 0; i < 7; i++)
		{
			fitnessClass = fitnessProgram.findByIndex(i);
			if(fitnessClass != null)
			{
				write = write + fitnessClass.getId() + " " + fitnessClass.getName() + " " + fitnessClass.getTutorName() + " "  + fitnessClass.getStartTime() + "\r\n";
			}
		}
		
		//write into the file
		printWriter.write(write);
		printWriter.close();
		System.exit(0);
	}

	/**
	 * Process button clicks.
	 * @param ae the ActionEvent
	 */
	public void actionPerformed(ActionEvent ae)
	{
		
		//display
		if(ae.getSource().equals(attendanceButton))
		{
			displayReport();	
		}
		
		//close and save
		else if(ae.getSource().equals(closeButton))
		{
			try
			{
				processSaveAndClose();
			} 
			
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
		}
		
		//delete
		else if(ae.getSource().equals(deleteButton))
		{
			processDeletion();
		}
		
		//add
		else if(ae.getSource().equals(addButton))
		{
			processAdding();
		}
	}
	
	public String addSpace(String input,int i)
	{	
		//count space
		int count = i * 12 - input.length();					
		String space = "";
		for(int j = 0; j < count; j++)
		{
			space = space + " ";
		}
		return space;
	}
	
	//error message
	public void errorMessage(String error)
	{	
		JOptionPane.showMessageDialog(null, error);
		
		//set null
		idIn.setText(null);
		classIn.setText(null);
		tutorIn.setText(null);
	}
	
	//if it is all alpha
	public boolean formatCheck(String input)
	{
		for(int i = 0; i < input.length(); i++)
		{
			if(input.charAt(i) > 122 || input.charAt(i) < 97 )
			{
				return true;
			}
		}
		return false;
	}
	
}

