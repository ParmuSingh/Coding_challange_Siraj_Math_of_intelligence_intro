/*
THIS IS FOR SIRAJ.
Jump to line 90, actionPerformed() of EventClass for main algo used to find line's equation.
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class CurveFitting extends JFrame
{
	private JLabel text;
	private JTextField field;
	private JButton button;
	public int n;
	public double x[], y[];
	private Graph frame = new Graph("Graph");
	private float m, c;
	
	public static void main(String args[])
	{
		new CurveFitting();
	}
	
	private class Graph extends JFrame
	{
		public void paint(Graphics g)
		{
			super.paint(g);
	        Graphics2D g2d = (Graphics2D) g;
	        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        
	        int i = 0;
	        g2d.setColor(Color.RED);
	        g2d.drawLine(1375/2, 0, 1375/2, 730);
	        g2d.drawLine(0, 730/2, 1375, 730/2);
	        
	        g2d.setColor(Color.WHITE);
	        while(i<n)//shows input points with the help of circle of r=10
	        {
	        g2d.fillOval((int)x[i] + (1375/2) - /*radius*/3, (730/2) - (int)y[i] - 3, 10, 10);
	        i++;
	        }
	        g2d.setColor(Color.YELLOW);
	        i=-500;
	        while(i<500)//draws line with the help of consecutive ovals
	        {
	        	g2d.drawOval(i+ (1375/2) - 2, (730/2) - ((int)(m*i + c)) - 2, 3, 3);//Drawing the line.
	        	i++;
	        }
		}
		
		public Graph(String s)
		{
			super(s);
			setLayout(new FlowLayout());
		}
		
	}
	
	
	
	public CurveFitting()//constructor where all codes for the window are written
	{
		setLayout(new FlowLayout());//layout like a text:one after another
		text = new JLabel("Please Enter The Number Points Known: ");
		add(text);//add to frame
		field = new JTextField(20);
		add(field);
		button = new JButton("Enter");
		add(button);
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 300);
		setResizable(false);
		setVisible(true);
		
		EventClass eventObject = new EventClass();
		button.addActionListener(eventObject);
	}
	
	
	private class EventClass implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			
			try
			{
			n = Integer.parseInt(field.getText().toString());/*number of known points.
			field's text is string. so convert it to integer*/
			x = new double[n];// Xs.
			y = new double[n];// Ys.
			
			//float m, c;// Slope and y intercept.
			float sumx = 0, sumy = 0, sumxy = 0, sumx_sq = 0;
			
			String X, Y;
			int i = 0;
			
			if(event.getSource()==button)//event is obj of listner. if button pressed then:
			{
				
				//n = Integer.parseInt(field.getText().toString());//number of known points.
				
				while(i<n)
				{
					X = JOptionPane.showInputDialog("Enter x" + ++i).toString();
					x[i-1] = Double.parseDouble(X);
					Y = JOptionPane.showInputDialog("Enter y" + i).toString();
					y[i-1] = Double.parseDouble(Y);	
				}
				//d_error_m = -2 SUM(i=1 to n, xi*(yi-m*xi-c));
				//d_error_c = -2 SUM(i=1 to n, (yi-m*xi-c));
				
				
				// Calculating summation for above equation
				for(int j = 0; j < n; j++)
				{
					sumx += x[j];
					sumy += y[j];
					sumxy += x[j]*y[j];
					sumx_sq += x[j]*x[j];
				}
				
				m = ((sumy/n) - sumxy)/((sumx/n) - sumx_sq);
				c = (sumy - m*sumx)/n;
				
				JOptionPane.showMessageDialog(null, "f(x) = " + m + "x + " + c , "The Required Equation", JOptionPane.PLAIN_MESSAGE);

				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				frame.setSize(1370, 730);
				frame.getContentPane().setBackground(Color.BLACK);
			}
			 
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Enter a number only", "Error", JOptionPane.ERROR_MESSAGE);
			}

		}
	}
}
