import java.util.*;

/**
 * Maintains a list of Fitness Class objects
 * The list is initialised in order of start time
 * The methods allow objects to be added and deleted from the list
 * In addition an array can be returned in order of average attendance
 */
public class FitnessProgram
{
	private final int max = 7;
	private int array;
	private FitnessClass[] fitnessClassList;
	
	//constructor
	public FitnessProgram()
	{
		fitnessClassList = new FitnessClass[max];
		array = 0;   
	}
	
	//get fitness class
	public FitnessClass[] getFitnessClass()
	{   
		return fitnessClassList;
	}
	
	//get number
	public int getArray()
	{
		return array;   
	}
	
	//return the FitnessClass object at index X
	public FitnessClass findByIndex(int x)
	{	   
		if(fitnessClassList[x] != null)
		{	   
			return fitnessClassList[x];    
		}   
		return null;  
	   }
	
	//return the FitnessClass starting at a particular time
	public FitnessClass findByTime(int t)
	{	
		if(findByIndex(t - 9) != null)
		{		
			return findByIndex(t - 9);
		}
		return null;
	}
	
	//return the first start time that is available for a class
	public int firstAvailableTime() 
	{
		for(int i = 0; i < max; i++)
		{	
			if(findByIndex(i) == null)
			{		
				return i + 9;
			}
		}
		return -1;		
	}
	
	//return a FitnessClass object with a given ID number in the array
	public FitnessClass findById(String id)
	{	   	
		for(int i = 0; i < 7; i++)
		{	   
			if(fitnessClassList[i] != null && fitnessClassList[i].getId().equals(id))
			{   		
				return fitnessClassList[i];
			}
		}   
		return null;   
	}
	
	//insert a FitnessClass object 
	public void insertClass(FitnessClass Class, int i)
	{		   
		   fitnessClassList[i] = Class;
		   array++;  
	}

	//delete a FitnessClass object 
	 public void deleteClass(String id)
	 {	   
		 for(int i = 0; i < 7; i++)
		 {
			 if(fitnessClassList[i] != null && fitnessClassList[i].getId().equals(id))
			 {
				 fitnessClassList[i] = null;
				 array--;
			 }  
		 }
	 }
	 
	 //return a list sorted in non-increasing order on the average attendance at each class
	 public FitnessClass[] sort() 
	 {
		 FitnessClass[] list = new FitnessClass[array];
		 int j = 0;
		 for(int i = 0 ; i < max; i++) 
		 {
			 //check if it is null to make sure there will not be error message
			 if(fitnessClassList[i] != null)
			 {
				 list[j] = fitnessClassList[i];
				 j++;
			 }
		 }
		 Arrays.sort(list);
		 return list;
	 }
	 
	 //return the overall average attendance
	 public String overallAverage() 
	 {
		 double sum = 0;
		 for(int i = 0; i < fitnessClassList.length; i++)
		 {
			 if(fitnessClassList[i] != null)
			 {
				 sum = sum + fitnessClassList[i].getAverage();
			 } 
		 }
		 double avg = sum / array;
		 return String.format("%.2f", avg);
	 }
	 
	 //check class if exist
	 public boolean checkClass(String id)
	 {
		 boolean check = true;
		 for(int i = 0; i < 7; i++)
		 {	   
			 if(findById(id) == null)
			 {		   
				 check = false; 
			 }  
		 }  
		 return check;  
	   }
	
}
