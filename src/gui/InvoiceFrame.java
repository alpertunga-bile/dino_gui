package gui;

import javax.swing.*;
import java.awt.*; 

public class InvoiceFrame extends JFrame{
	private JLabel date, total, paymentType, name, email;
	
	public InvoiceFrame(User u, String pType)
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screenSize.getWidth();
		int height = (int)screenSize.getHeight();
		
		setTitle("DINO - Invoice");
		setSize(400, 400);
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(false);
		setBackground(new Color(255, 255, 255));
		
		setBounds(width/2 - 200, height/2 - 200, 400, 400);
				
		name = new JLabel(u.username + "'s Premium Invoice:");
		name.setBounds(10, 50, 340, 30);
		name.setFont(new Font("Arial", Font.BOLD, 20));
		
		email = new JLabel("Email: " + u.email);
		email.setBounds(50, 90, 340, 30);
		email.setFont(new Font("Arial", Font.PLAIN, 15));
		
		date = new JLabel("Date: " + u.renewDate);
		date.setBounds(50, 120, 340, 30);
		date.setFont(new Font("Arial", Font.PLAIN, 15));
		
		paymentType = new JLabel("Payment Type: " + pType);
		paymentType.setBounds(50, 150, 340, 30);
		paymentType.setFont(new Font("Arial", Font.PLAIN, 15));
		
		total = new JLabel("Total Price: 9.99$");
		total.setBounds(50, 180, 340, 30);
		total.setFont(new Font("Arial", Font.PLAIN, 15));
		
		Container c = getContentPane();
		c.setLayout(null);
		
		c.add(name);
		c.add(email);
		c.add(date);
		c.add(total);
		c.add(paymentType);
	}
}
