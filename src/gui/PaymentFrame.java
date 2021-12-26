package gui;

import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PaymentFrame extends JFrame implements ActionListener{
	private JLabel nameCardLl, cardNumberLl, dateExpLl, CCVLl;
	private JTextField nameCardTf, cardNumberTf, dateExpTf, CCVTf;
	
	private JLabel infoLl;
	
	private JRadioButton visaRb, masterRb, americanRb, discoverRb;
	
	private JButton payButton;
	private Boolean name, number, date, CCV;
	
	private User user;
	
	private Container container;
	
	private String payType;
	
	public void setUser(User u)
	{
		user = u;
	}
	
	public PaymentFrame()
	{
		payType = "Visa";
		
		name = number = date = CCV = false;
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screenSize.getWidth();
		int height = (int)screenSize.getHeight();
		
		setTitle("DINO - Payment");
		setSize(450, 400);
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(false);
		
		setBounds(width/2 - 225, height/2 - 200, 450, 400);
		
		payButton = new JButton("Pay - 9.99$ for 1 month");
		payButton.setBounds(110, 270, 180, 40);
		
		visaRb = new JRadioButton("Visa", true);
		visaRb.setBounds(30, 60, 60, 30);
		masterRb = new JRadioButton("Mastercard", false);
		masterRb.setBounds(90, 60, 100, 30);
		americanRb = new JRadioButton("American Express", false);
		americanRb.setBounds(190, 60, 130, 30);
		discoverRb = new JRadioButton("Discover", false);
		discoverRb.setBounds(330, 60, 90, 30);
		
		payButton.addActionListener(this);
		visaRb.addActionListener(this);
		visaRb.setBackground(new Color(52, 159, 245));
		americanRb.addActionListener(this);
		americanRb.setBackground(new Color(52, 159, 245));
		masterRb.addActionListener(this);
		masterRb.setBackground(new Color(52, 159, 245));
		discoverRb.addActionListener(this);
		discoverRb.setBackground(new Color(52, 159, 245));
		
		infoLl = new JLabel("");
		infoLl.setBounds(70, 20, 350, 30);
		nameCardLl = new JLabel("Name:");
		nameCardLl.setBounds(30, 100, 120, 30);
		cardNumberLl = new JLabel("Credit Card Number:");
		cardNumberLl.setBounds(30, 130, 120, 30);
		dateExpLl = new JLabel("Date Expiration:");
		dateExpLl.setBounds(30, 160, 120, 30);
		CCVLl = new JLabel("CCV:");
		CCVLl.setBounds(30, 190, 120, 30);
		
		nameCardTf = new JTextField("Name On Credit Card");
		nameCardTf.setBounds(160, 105, 170, 20);
		cardNumberTf = new JTextField("Card Number");
		cardNumberTf.setBounds(160, 135, 120, 20);
		dateExpTf = new JTextField("MM/YY");
		dateExpTf.setBounds(160, 165, 60, 20);
		CCVTf = new JTextField("CCV");
		CCVTf.setBounds(160, 195, 60, 20);
		
		nameCardTf.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				nameCardTf.setText("");
				name = true;
			}
		});

		cardNumberTf.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				cardNumberTf.setText("");
				number = true;
			}
		});

		dateExpTf.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dateExpTf.setText("");
				date = true;
			}
		});

		CCVTf.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				CCVTf.setText("");
				CCV = true;
			}
		});
		
		container = getContentPane();
		container.setLayout(null);
		container.setBackground(new Color(52, 159, 245));
		
		container.add(infoLl);
		
		container.add(visaRb);
		container.add(masterRb);
		container.add(americanRb);
		container.add(discoverRb);
		
		container.add(nameCardLl);
		container.add(nameCardTf);
		container.add(cardNumberLl);
		container.add(cardNumberTf);
		container.add(dateExpLl);
		container.add(dateExpTf);
		container.add(CCVLl);
		container.add(CCVTf);
		
		container.add(payButton);
	}
	
	private void beDefault()
	{
		visaRb.setSelected(false);
		masterRb.setSelected(false);
		americanRb.setSelected(false);
		discoverRb.setSelected(false);
		
		nameCardTf.setText("Name On Credit Card");
		cardNumberTf.setText("Card Number");
		dateExpTf.setText("MM/YY");
		CCVTf.setText("CCV");
		
		infoLl.setText("");
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == payButton)
		{
			if(!nameCardTf.getText().equals("") && !cardNumberTf.getText().equals("") && !dateExpTf.getText().equals("") && !CCVTf.getText().equals("")
					&& name && number && date && CCV)
			{
				infoLl.setText("Your payment progress has finished. Thank You!!!");
				infoLl.setForeground(Color.GREEN);
				
				Logger logger = new Logger();
				
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
				Date date = new Date();
				
				if(!user.isRenew)
					user.renewedTimes = 1;
				else
				{
					user.renewedTimes = user.renewedTimes + 1;
					user.renewDate = formatter.format(date);
					logger.incrementRenew(user, user.username);
				}
				
				if(user.isNew)
					user.fromTo = new String("NoneToPremium");
				else
					user.fromTo = new String(user.subType + "To" + "Premium");
				
				if(!user.isRenew)
				{
					user.renewDate = formatter.format(date);
					
					if(user.subType.equals("Premium"))
						logger.userLogged(user);
					else
						logger.setAsPremium(user.username);
					
					logger.logged(user);
				}
				
				beDefault();
				setVisible(false);
				
				InvoiceFrame invoiceFrame = new InvoiceFrame(user, payType);
				invoiceFrame.setVisible(true);
			}
		}
		
		if(ae.getSource() == visaRb)
		{
			masterRb.setSelected(false);
			americanRb.setSelected(false);
			discoverRb.setSelected(false);
			
			payType = "Visa";
		}
		
		if(ae.getSource() == masterRb)
		{
			visaRb.setSelected(false);
			americanRb.setSelected(false);
			discoverRb.setSelected(false);
			
			payType = "Mastercard";
		}
		
		if(ae.getSource() == americanRb)
		{
			visaRb.setSelected(false);
			masterRb.setSelected(false);
			discoverRb.setSelected(false);
			
			payType = "American Express";
		}
		
		if(ae.getSource() == discoverRb)
		{
			visaRb.setSelected(false);
			masterRb.setSelected(false);
			americanRb.setSelected(false);
			
			payType = "Discover";
		}
	}
}
