import java.awt.*;
import javax.swing.*;

/**
 * Class to define window in which attendance report is displayed.
 */
public class ReportFrame extends JFrame
{
	//display the report
	private JTextArea report;
	public ReportFrame(FitnessProgram fitnessProgram)
	{
		//constructor
		setTitle("Attendance Report");
		setSize(800, 400);
		report = new JTextArea();
		report.setFont(new Font("Courier New", Font.PLAIN, 15));
		add(report, BorderLayout.CENTER);
		this.setLocation(400, 200);
		formatReport(fitnessProgram);
	}
	
	//format the report
	public void formatReport(FitnessProgram fitnessProgram) 
	{
		FitnessClass[] list = fitnessProgram.sort();

		//format the first 2 lines
		String above;
		above = " ID   Class             Tutor               Atttendance        Average Attendance\n"
		+ "=================================================================================\n";
		report.append(above);
		
		//print the list
		for(int i = 0; i < list.length; i++)
		{
			if(list[i] != null)
			{
				report.append(list[i].format() + "\n");	
			}
		}	
		
		//print the over all average
		report.append("\n                                              Overall average:    " + fitnessProgram.overallAverage());
	}
}
