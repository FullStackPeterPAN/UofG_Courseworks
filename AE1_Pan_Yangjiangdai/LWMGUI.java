/*
 * GUI
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

	public class LWMGUI extends JFrame implements ActionListener
	{  
		//instance variables are all the components 
		private JPanel top, middle1, middle2, bottom;
		private JButton buttonPS, buttonPR;
		private JLabel labelN, labelQ, labelP;
		static JLabel labelWP;
		private JLabel labelAoT;
		private JLabel labelCb;
		private JLabel labelSpace1;
		private JLabel labelSpace2;
		static JTextField FieldN;
		static JTextField FieldQ;
		static JTextField FieldP;
		static JTextField FieldAoT;
		static JTextField FieldCb;
		public CustomerAccount modelObject1;
		public wine modelObject2;
		private LWMGUI viewObject;
		private LWMGUI controllerObject;
		
		public LWMGUI(CustomerAccount model1)
		{
			modelObject1 = model1;
		}
		
		public LWMGUI(wine model2)
		{
			modelObject2 = model2;
		}
			
		public void setView(LWMGUI view) 
		{
			 viewObject = view;
		}
		
		public LWMGUI(CustomerAccount model1, wine model2, LWMGUI controller)
			{
				
				controllerObject = controller;
				modelObject1 = model1;
				modelObject2 = model2;
				setDefaultCloseOperation(EXIT_ON_CLOSE);
				
				//add title
				this.setSize(600,200);
				this.setTitle("Lilybank Wine Merchants: " + AssEx1.returnName());
				
				this.setLayout(new FlowLayout());
				
				//ADD COMPONENTS 
				this.layouttop();
    			this.layoutmiddle1();
    			this.layoutmiddle2();
    			this.layoutbottom();
			}
			
		//top panel
			private void layouttop()
			{
				top = new JPanel();
				//put in name, quantity, price
				labelN = new JLabel("Name: ");
				labelQ = new JLabel("Quantity: ");
				labelP = new JLabel("Price: ");
				FieldN = new JTextField("",10);
				FieldQ = new JTextField("",10);
				FieldP = new JTextField("",10);
				top.add(labelN);
				top.add(FieldN);
				top.add(labelQ);
				top.add(FieldQ);
				top.add(labelP);
				top.add(FieldP);
				this.add(top);
				FieldN.setVisible(true);
			}
			
			//middle1 panel
			private void layoutmiddle1()
			{
				//buttons of different processes
				middle1 = new JPanel();
				buttonPS = new JButton("Process Sell");
				buttonPR = new JButton("Process Return");
				labelSpace1 = new JLabel("                                      ");
				labelSpace2 = new JLabel("                                      ");
				middle1.add(labelSpace1);
				middle1.add(buttonPS);
				middle1.add(buttonPR);
				buttonPS.addActionListener(controllerObject);
				buttonPR.addActionListener(controllerObject);
				middle1.add(labelSpace2);
				this.add(middle1);
			}
			
			//middle2 panel
			private void layoutmiddle2()
			{
				//show the name of purchased wine
				middle2 = new JPanel();
				labelWP = new JLabel("Wine Purchased: ");
				middle2.add(labelWP);

				this.add(middle2);
			}
			
			//bottom panel
			private void layoutbottom()
			{
				//show the transaction and the balance
				bottom = new JPanel();
				labelAoT = new JLabel("Amount of Transaction:");
				labelCb = new JLabel("Current balance:");
				FieldAoT = new JTextField("",10);
				FieldCb = new JTextField("",10);
				FieldAoT.setEditable(false);
				FieldCb.setEditable(false);
				bottom.add(labelAoT);
				bottom.add(FieldAoT);
				bottom.add(labelCb);
				bottom.add(FieldCb);
				this.add(bottom);
			}
			
			//press the bottom to get information
			public void actionPerformed(ActionEvent getWine) {
				if(getWine.getSource() == viewObject.buttonPS)
				{
					wine wine = new	wine();
					wine.getInfo();
					double AoT = wine.returnp() * wine.returnq();
				    LWMGUI.FieldAoT.setText("£" + String.format("%.2f", AoT));
					CustomerAccount.Sales(AssEx1.returnBalance(), wine.returnq(), wine.returnp());
					FieldN.setText("");
					FieldQ.setText("");
					FieldP.setText("");
				}
				else 
				{
					wine wine = new	wine();
					wine.getInfo();
					double AoT = 0.8 * wine.returnp() * wine.returnq();
				    LWMGUI.FieldAoT.setText("£" + String.format("%.2f", AoT));
					CustomerAccount.Returns(AssEx1.returnBalance(), wine.returnq(), wine.returnp());
					FieldN.setText("");
					FieldQ.setText("");
					FieldP.setText("");
				}
			}


	}