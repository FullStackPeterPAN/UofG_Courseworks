/** Defines an object representing a single fitness class
 */
public class FitnessClass implements Comparable<FitnessClass> {
	private String id;
	private String name;
	private String tutorName;
	private int startTime;
	private int[] attendance = {0, 0, 0, 0, 0};
	
	//constructor
	public FitnessClass(String id, String name, String tutorName, int startTime, int[] attendance) 
	{
		setId(id);
		setName(name);
		setTutorName(tutorName);
		setStartTime(startTime);
		setAttendance(attendance);
	}
	
	public FitnessClass(String id, String name, String tutorName, int startTime)
	{
		setId(id);
		setName(name);
		setTutorName(tutorName);
		setStartTime(startTime);	
	}

	//store id
	public void setId(String id)
	{	
		this.id = id;
	}
	
	//store course name
	public void setName(String name)
	{	
		this.name = name;	
	}
	
	//store tutor name
	public void setTutorName(String tutorName)
	{
		this.tutorName = tutorName;
	}
	
	//store start time
	public void setStartTime(int startTime)
	{
		this.startTime = startTime;
	}
	
	//store attendance week
	public void setAttendance(int[] attendance)
	{
		for(int i  =0; i < 5; i++)
		{		
			this.attendance[i] = attendance[i];
		}
	}
	
	//get id
	public String getId()
	{	
		return id;	
	}
	
	//get course name
	public String getName()
	{	
		return name;	
	}
	
	//get tutor name
	public String getTutorName()
	{
		return tutorName;
	}
	
	//get class start time
		public int getStartTime()
		{
			return startTime;
		}
		
	//get attendance week
	public int[] getAttendance()
	{
		return attendance;
	}
	
	//return the average attendance for the class
	public double getAverage()
	{	
		int sum = 0;	
		for(int i = 0; i < 5; i++)
		{	
			sum = sum + attendance[i];	
		}
		double avg = (double)sum / (double)5;
		return avg;
	}
	
	//format attendance
		public String formatAttendance()
		{
			String report = "";									
			for(int i = 0; i < 5; i++)
			{				
				
				//add a space to format the report if the length is 1
				if((String.valueOf(attendance[i]).length()) == 1)
				{
					report = report + " " + String.valueOf(attendance[i]);
				}
				else
				{	
					report = report + String.valueOf(attendance[i]);
				}
				report = report + "  ";
			}
			return report;
		}
		
	//return a String formatted appropriately for the attendance report
	public String format()
	{
		String format;
		format = " " + getId() + "  " + getName() + formatName(getName()) + getTutorName() + formatTutorName(getTutorName()) + formatAttendance() + formatAverage(String.format("%.2f", getAverage())) + String.format("%.2f", getAverage());
		return format;
	}
	
	//method to format name
	public String formatName(String name)
	{
		String space = "";
		int length = 18 - name.length();

		//add space to make the length to 18
		for(int i = 0; i < length; i++)
		{
			space = space + " ";
		}
		return space;
	}
	
	//method to format tutor name
	public String formatTutorName(String tname)
	{
		String space = "";
		int length = 18 - tname.length();

		//add space to make the length to 18
		for(int i = 0; i < length; i++)
		{
			space = space + " ";
		}
		return space;
	}
		
	//method to format average
	public String formatAverage(String a)
	{
		String space = "";
		int length = 10 - String.valueOf(getAverage()).length();

		//add space to make the length to 18
		for(int i = 0; i < length; i++)
		{
			space = space + " ";
		}
		return space;
	}
			
	//compare attendance average
	public int compareTo(FitnessClass other)
	{
		double avg = getAverage();
		
		//attendance average greater than the previous
		if(avg < other.getAverage())
		{	
			return 1;
    	}
		else
		{
			
			//attendance average equals the previous
			if(avg == other.getAverage())
			{	
				return 0;
			}
			
			//attendance average less than the previous
			else
			{
				return -1;
			}
		}
    }
	
}
