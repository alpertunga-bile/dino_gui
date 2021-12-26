package gui;

import javax.swing.*;
import java.awt.*; // Container, Color, 
import java.awt.event.*;//ActionListener

public class SignFrame extends JFrame implements ActionListener{
	private JLabel userName, passName, guiLabel, warningLl;
	private JTextField userField;
	private JPasswordField passField;
	private JButton signUpButton, signInButton;
	
	private Logger logger;
	
	private Boolean user, pass;
	
	public SignFrame()
	{
		logger = new Logger();
		
		user = pass = false;
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screenSize.getWidth();
		int height = (int)screenSize.getHeight();
		
		setTitle("DINO - Welcome");
		setSize(300, 300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(width/2 - 150, height/2 - 150, 300, 300);
		
		guiLabel = new JLabel("DINO");
		warningLl = new JLabel("");
		userName = new JLabel("Username:");
		userField = new JTextField("Enter Username");
		passName = new JLabel("Password:");
		passField = new JPasswordField("Enter Password", 20);
		signUpButton = new JButton("Sign Up");
		signInButton = new JButton("Sign In");
		
		// @params column, row, width, height
		guiLabel.setBounds(90, 15, 100, 50);
		guiLabel.setForeground(new Color(250, 123, 6));
		guiLabel.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 40));
		warningLl.setBounds(40, 60, 260, 30);
		warningLl.setForeground(new Color(250, 0, 0));
		warningLl.setFont(new Font("Arial", Font.BOLD, 15));
		userName.setBounds(40, 90, 70, 30);
		userField.setBounds(120, 95, 135, 20);
		passName.setBounds(40, 120, 70, 30);
		passField.setBounds(120, 125, 135, 20);
		signUpButton.setBounds(40, 170, 90, 30);
		signInButton.setBounds(160, 170, 90, 30);
		
		userField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				userField.setText("");
				user = true;
			}
		});
		
		passField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				passField.setText("");
				pass = true;
			}
		});
		
		signUpButton.addActionListener(this);
		signInButton.addActionListener(this);
		
		Container container = getContentPane();
		container.setLayout(null);
		
		container.setBackground(new Color(52, 159, 245));
		
		container.add(guiLabel);
		container.add(warningLl);
		container.add(userName);
		container.add(userField);
		container.add(passName);
		container.add(passField);
		container.add(signUpButton);
		container.add(signInButton);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == signUpButton)
		{
			SignUpFrame signupFrame = new SignUpFrame();
			userField.setText("Enter Username");
			passField.setText("Enter Password");
			signupFrame.setVisible(true);
		}
		
		if(ae.getSource() == signInButton)
		{
			User newUser = new User();
			
			String strPass = String.valueOf(passField.getPassword());
			
			if(userField.getText().equals("")) user = false;
			if(strPass.equals("")) pass = false;
			
			if(user && pass)
			{
				boolean isSigned = false;
				
				newUser = logger.getUserData(userField.getText());
					
				if(newUser.username.equals(userField.getText()) && newUser.password.equals(strPass))
					isSigned = true;
				
				if(isSigned)
				{
					if(!newUser.subType.equals("Premium"))
					{
						setVisible(false);
						new RemainderFrame(newUser);
					}
					else
					{
						MainGUIFrame mgf = new MainGUIFrame(newUser);
						setVisible(false);
						mgf.setVisible(true);
					}
				}
				else
					warningLl.setText("Wrong username or password");
			}
		}
	}
}
