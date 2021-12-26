package gui;

import javax.swing.*;
import java.util.Arrays;
import java.awt.*; 
import java.awt.event.*;

public class SignUpFrame extends JFrame implements ActionListener{
	private JLabel newUserLl, newPassLl, confirmPassLl, emailLl;
	private JPasswordField newPassTf, confirmPassTf; 
	private JTextField newUserTf, emailTf;
	private JButton acceptJButton;
	private JRadioButton freeBox, trialBox, premiumBox;
	private Container c;
	private JLabel qualityLl, parentalLl, priceLl;
	private JLabel warningLl;
	
	private Logger logger;
	private User tempUser;
	
	public SignUpFrame()
	{
		setTitle("DINO - Sign Up");
		setSize(500, 400);
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(false);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screenSize.getWidth();
		int height = (int)screenSize.getHeight();
		
		setBounds(width/2 - 250, height/2 - 200, 500, 400);
		
		logger = new Logger();
		tempUser = new User();
		
		// --------------------------------------------------
		// JLabelS 
		// --------------------------------------------------
		
		newUserLl = new JLabel("Username:");
		newUserLl.setBounds(100, 40, 70, 30);
		newPassLl = new JLabel("Password:");
		newPassLl.setBounds(100, 75, 70, 30);
		confirmPassLl = new JLabel("Confirm Password:");
		confirmPassLl.setBounds(55, 110, 120, 30);
		emailLl = new JLabel("Email:");
		emailLl.setBounds(120, 145, 50, 30);
		qualityLl = new JLabel("");
		qualityLl.setBounds(80, 210, 200, 30);
		parentalLl = new JLabel("");
		parentalLl.setBounds(80, 230, 200, 30);
		priceLl = new JLabel("");
		priceLl.setBounds(80, 250, 200, 30);
		warningLl = new JLabel("");
		warningLl.setBounds(160, 10, 200, 30);
		
		acceptJButton = new JButton("Sign Up");
		acceptJButton.setBounds(180, 300, 90, 30);
		
		// --------------------------------------------------
		// TEXT FIELDS 
		// --------------------------------------------------
		
		newUserTf = new JTextField("Enter Username");
		newUserTf.setBounds(180, 45, 160, 20);
		newUserTf.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				newUserTf.setText("");
			}
		});
		newPassTf = new JPasswordField("Enter Password", 20);
		newPassTf.setBounds(180, 80, 160, 20);
		newPassTf.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				newPassTf.setText("");
			}
		});
		confirmPassTf = new JPasswordField("Confirm Password", 20);
		confirmPassTf.setBounds(180, 115, 160, 20);
		confirmPassTf.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				confirmPassTf.setText("");
			}
		});
		emailTf = new JTextField("Enter Email");
		emailTf.setBounds(180, 150, 160, 20);
		emailTf.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				emailTf.setText("");
			}
		});
		
		// --------------------------------------------------
		// CHECK BOXES 
		// --------------------------------------------------
		freeBox = new JRadioButton("Free", false);
		trialBox = new JRadioButton("Trial (30 Days)", false);
		premiumBox = new JRadioButton("Premium", false);
		
		freeBox.setBounds(80, 180, 50, 40);
		trialBox.setBounds(180, 180, 140, 40);
		premiumBox.setBounds(320, 180, 90, 40);
		
		freeBox.setBackground(new Color(52, 159, 245));
		trialBox.setBackground(new Color(52, 159, 245));
		premiumBox.setBackground(new Color(52, 159, 245));
		
		freeBox.addActionListener(this);
		trialBox.addActionListener(this);
		premiumBox.addActionListener(this);
		acceptJButton.addActionListener(this);
		
		c = getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(52, 159, 245));
		
		c.add(warningLl);
		c.add(newUserLl);
		c.add(newUserTf);
		c.add(newPassLl);
		c.add(newPassTf);
		c.add(confirmPassLl);
		c.add(confirmPassTf);
		c.add(emailLl);
		c.add(emailTf);
		c.add(acceptJButton);
		c.add(freeBox);
		c.add(trialBox);
		c.add(premiumBox);
		c.add(qualityLl);
		c.add(parentalLl);
		c.add(priceLl);
	}
	
	private void beDefault()
	{
		freeBox.setSelected(false);
		trialBox.setSelected(false);
		premiumBox.setSelected(false);
		
		newUserTf.setText("Enter Username");
		newPassTf.setText("Enter Password");
		confirmPassTf.setText("Confirm Password");
		emailTf.setText("Enter Email");
		
		qualityLl.setText("");
		parentalLl.setText("");
		priceLl.setText("");
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == acceptJButton)
		{
			boolean canLogged = true;
			boolean isUserFound = false;
			User controlUser = new User();
			
			tempUser.ID = logger.getCurrentID();
			tempUser.username = newUserTf.getText();
			tempUser.password = String.valueOf(newPassTf.getPassword());
			tempUser.email = emailTf.getText();
			
			controlUser = logger.getUserData(tempUser.username);
			
			try 
			{
				if(controlUser.username.equals(tempUser.username))
					isUserFound = true;
			}
			catch(Exception e)
			{
					
			}
			
			if(isUserFound)
			{
				canLogged = false;
				
				warningLl.setText("Username Has Been Used Before");
				warningLl.setForeground(Color.RED);
			}
			
			if(!Arrays.equals(newPassTf.getPassword(), confirmPassTf.getPassword()))
			{				
				canLogged = false;
				
				warningLl.setText("Passwords Didnt Match");
				warningLl.setForeground(Color.RED);
			}
			
			if(canLogged)
			{
				if(!tempUser.subType.equals("Premium"))
					logger.userLogged(tempUser);
				
				setVisible(false);
				beDefault();
				
				if(tempUser.subType.equals("Premium"))
				{
					PaymentFrame payFrame = new PaymentFrame();
					payFrame.setVisible(true);
					tempUser.isNew = true;
					tempUser.isRenew = false;
					payFrame.setUser(tempUser);
				}
			}
		}
		
		if(ae.getSource() == freeBox)
		{
			qualityLl.setText("Low Quality / SD (360p)");
			parentalLl.setText("Have Limited Genres");
			priceLl.setText("Unlimited Free");
			trialBox.setSelected(false);
			premiumBox.setSelected(false);
			tempUser.subType = "Free";
		}
		
		if(ae.getSource() == trialBox)
		{
			qualityLl.setText("High Quality / HD (720p)");
			parentalLl.setText("Have All Genres");
			priceLl.setText("Free For 30 Days");
			freeBox.setSelected(false);
			premiumBox.setSelected(false);
			tempUser.subType = "Trial";
		}
		
		if(ae.getSource() == premiumBox)
		{
			qualityLl.setText("High Quality / HD (1080p)");
			parentalLl.setText("Have All Genres");
			priceLl.setText("9.99$/month");
			freeBox.setSelected(false);
			trialBox.setSelected(false);
			tempUser.subType = "Premium";
		}
	}
}
