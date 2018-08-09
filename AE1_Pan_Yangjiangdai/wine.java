import javax.swing.JOptionPane;

public class wine
{
	private int q;
	private double p;
	
	//get information from GUI
	public void getInfo()
	{
		String name = LWMGUI.FieldN.getText();
		String quantity = LWMGUI.FieldQ.getText();
		String price = LWMGUI.FieldP.getText();

		//make sure to enter numbers
	    while(quantity != null && price != null)
		{
	    	//make sure name is not empty
	    	if(name.equals(""))
	    	{
	    		break;
	    	}
	    	else
			try 
			{
				Integer trynumber1 = Integer.valueOf(quantity.trim());
				try
				{
				double trynumber2 = Double.valueOf(price.trim());
 			    LWMGUI.labelWP.setText("Wine Purchased: " + name);
 				//change strings to integer/double
 			    q = Integer.parseInt(quantity.trim());
 			    p = Double.parseDouble(price.trim());
 			    break;
 			    }
				catch (NumberFormatException nfx) 
				{
	 	 			JOptionPane.showMessageDialog(null, "PLEASE ENTER A VALID NUMBER", "WARNING", JOptionPane.ERROR_MESSAGE);
				}
			}
 	 		catch (NumberFormatException nfx) 
			{
 	 			JOptionPane.showMessageDialog(null, "PLEASE ENTER A VALID NUMBER", "WARNING", JOptionPane.ERROR_MESSAGE);
			}
			break;
		}
	}
	public int returnq() 
	{
		return q;
	}
	public double returnp() 
	{
		return p;
	}
	
}