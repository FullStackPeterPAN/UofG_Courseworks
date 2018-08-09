import javax.swing.JOptionPane;

public class AssEx1
{
	public static String CustomerName = "";
	public static double balance = 0;
	
	//main
	public static void main(String [] args)
	{
		GetNameNBalance();
		//Set LWMGUI visible if name&balance are not null or empty
		if(CustomerName != null) {
		CustomerAccount model1 = new CustomerAccount();
		wine model2 = new wine();
		LWMGUI controller = new LWMGUI(model1);
		controller = new LWMGUI(model2);
		LWMGUI view = new LWMGUI(model1, model2, controller);
		controller.setView(view);
		view.setVisible(true);
		double B = returnBalance();
		LWMGUI.FieldCb.setText("£" + String.format("%.2f", B));}
		else
			System.exit(0);
	}

	public static void GetNameNBalance()
	{
		//get name
		CustomerName = JOptionPane.showInputDialog (null, "Please enter your name: ");
		String blc = "";
		 while (CustomerName != null)
		 {
			 if(CustomerName.equals(""))
			 {
				 System.exit(0);
			 }
			 else
				 blc = JOptionPane.showInputDialog (null, "Please enter your balance: ");
			 if(blc !=null)
			 {
		 		if(blc.equals(""))
		 		{
		 			System.exit(0);
		 		}
		 		else		
		 			//make sure to get a double
		 			try {
		 				double trynumber=Integer.valueOf(blc.trim());
		 				balance = Double.parseDouble(blc.trim());
		 				break;
		 			}
		 		catch (NumberFormatException nfx) 
		 		{
		 			JOptionPane.showMessageDialog(null, "PLEASE ENTER A VALID NUMBER", "WARNING", JOptionPane.ERROR_MESSAGE);
		 		}
			 }
		 	else
		 		System.exit(0);
		 }
	}

	//return balance
	public static double returnBalance() 
	{
		return balance;
	}

	//return name
	public static String returnName()
	{
		return CustomerName;
	}
	
}