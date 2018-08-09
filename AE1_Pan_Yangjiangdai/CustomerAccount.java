/*
 * Get the name and balance of the customer
 * Do the calculation of sales and returns
 */
public class CustomerAccount {
	static double b = AssEx1.returnBalance();
	//Sale method
	public static void Sales(double balance, int quantity, double price)
	{
		balance = b + quantity * price;
		b = balance;
		if(balance < 0)
		{
			balance = -balance;
		    LWMGUI.FieldCb.setText("£" + String.format("%.2f", balance) + "CR");
		}
		else
	    LWMGUI.FieldCb.setText("£" + String.format("%.2f", balance));
	}
	//Return method
	public static void Returns(double balance, int quantity, double price)
	{
		balance = b - 0.8 * quantity * price; 
		b = balance;
		if(balance < 0)
		{
			balance = -balance;
		    LWMGUI.FieldCb.setText("£" + String.format("%.2f", balance) + "CR");
		   
		}
		else
	    LWMGUI.FieldCb.setText("£" + String.format("%.2f", balance));
	}

}
