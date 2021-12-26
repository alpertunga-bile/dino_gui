package gui;

import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;

public class MainGUIFrame extends JFrame implements ActionListener{
	private JButton renewButton, actionButton, scifiButton, parentalControlButton, comedyButton, cartoonButton;
	private JLabel actionLl, scifiLl, parentalControlLl, comedyLl, cartoonLl, infoParentalControl;
	
	private JLabel sayHi, thanks;
	
	private Container c;
	private User user;
	
	private JLabel firstMovieLl, firstMovieTitleLl, secondMovieLl, secondMovieTitleLl;
	private JButton firstWatchButton, secondWatchButton;
	
	private String firstCurrentMovie, secondCurrentMovie;
	
	public MainGUIFrame(User u)
	{
		user = u;
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screenSize.getWidth();
		int height = (int)screenSize.getHeight();
		
		setTitle("DINO - Main Frame");
		setSize(1280, 720);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		setBounds(width/2 - 640, height/2 - 360, 1280, 720);
		
		c = getContentPane();
		c.setLayout(null);
		
		infoParentalControl = new JLabel("");
		firstMovieLl = new JLabel("");
		firstMovieTitleLl = new JLabel("");
		secondMovieLl = new JLabel("");
		secondMovieTitleLl = new JLabel("");
		sayHi = new JLabel("Welcome " + user.username + ",");
		sayHi.setFont(new Font("Arial", Font.BOLD, 25));
		sayHi.setBounds(60, 20, 300, 50);
		thanks = new JLabel("Thank You For Using DINO");
		thanks.setFont(new Font("Arial", Font.BOLD, 25));
		thanks.setBounds(60, 75, 500, 50);
		
		firstMovieTitleLl.setFont(new Font("Arial", Font.BOLD, 20));
		firstMovieLl.setFont(new Font("Arial", Font.PLAIN, 15));
		
		firstMovieTitleLl.setBounds(300, 150, 500, 40);
		firstMovieLl.setBounds(305, 200, 500, 40);
		
		secondMovieTitleLl.setFont(new Font("Arial", Font.BOLD, 20));
		secondMovieLl.setFont(new Font("Arial", Font.PLAIN, 15));
		
		secondMovieTitleLl.setBounds(300, 300, 500, 40);
		secondMovieLl.setBounds(305, 350, 500, 40);
		
		firstWatchButton = new JButton("Watch");
		firstWatchButton.setBounds(1002, 175, 80, 60);
		secondWatchButton = new JButton("Watch");
		secondWatchButton.setBounds(1002, 325, 80, 60);
		
		if(!user.parentalControl)
		{
			infoParentalControl.setText("Off");
			infoParentalControl.setForeground(new Color(255, 0, 0));
		}
		else
		{
			infoParentalControl.setText("On");
			infoParentalControl.setForeground(new Color(0, 255, 0));
		}

		infoParentalControl.setFont(new Font("Arial", Font.BOLD, 15));
		infoParentalControl.setBounds(1125, 50, 60, 40);
		
		actionLl = new JLabel("Action");
		actionButton = new JButton("");
		scifiLl = new JLabel("Scifi");
		scifiButton = new JButton("");
		comedyLl = new JLabel("Comedy");
		comedyButton = new JButton("");
		cartoonLl = new JLabel("Cartoon");
		cartoonButton = new JButton("");
		
		parentalControlLl = new JLabel("Parental Control");
		parentalControlButton = new JButton("");
	
		designForType();
		createLabelAndButton("Parental Control", parentalControlLl, parentalControlButton, 1000, 50, 200, 40);
		
		if(user.subType.equals("Premium"))
		{
			renewButton = new JButton("RENEW");
			renewButton.setBounds(50, 600, 100, 40);
			
			renewButton.addActionListener(this);
			
			c.add(renewButton);
		}
		
		firstWatchButton.addActionListener(this);
		secondWatchButton.addActionListener(this);
		
		c.add(sayHi);
		c.add(thanks);
		c.add(infoParentalControl);
		c.add(firstMovieLl);
		c.add(firstMovieTitleLl);
		c.add(secondMovieLl);
		c.add(secondMovieTitleLl);
		c.add(firstWatchButton);
		c.add(secondWatchButton);
	}
	
	private void createLabelAndButton(String name, JLabel label, JButton button, int x, int y, int width, int height)
	{
		label.setText(name);
		
		label.setBounds(x, y, width, height);
		button.setBounds(x - 12, y, width, height);
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);

		button.setEnabled(true);
		
		button.addActionListener(this);
			
		c.add(label);
		c.add(button);
	}
	
	private void designForType()
	{
		if(user.subType.equals("Free") && user.parentalControl)
		{
			createLabelAndButton("Comedy", comedyLl, comedyButton, 80, 175, 70, 30);
			createLabelAndButton("Cartoon", cartoonLl, cartoonButton, 80, 250, 70, 30);
			
			actionLl.setText("");
			scifiLl.setText("");
			actionButton.setEnabled(false);
			scifiButton.setEnabled(false);
		}
		else if(user.subType.equals("Free") && !user.parentalControl)
		{
			createLabelAndButton("Action", actionLl, actionButton, 80, 175, 70, 30);
			createLabelAndButton("Comedy", comedyLl, comedyButton, 80, 250, 70, 30);
			createLabelAndButton("Cartoon", cartoonLl, cartoonButton, 80, 325, 70, 30);
			
			scifiLl.setText("");
			scifiButton.setEnabled(false);
		}
		else if(!user.subType.equals("Free") && user.parentalControl)
		{
			createLabelAndButton("Comedy", comedyLl, comedyButton, 80, 175, 70, 30);
			createLabelAndButton("Sci-fi", scifiLl, scifiButton, 80, 250, 70, 30);
			createLabelAndButton("Cartoon", cartoonLl, cartoonButton, 80, 325, 70, 30);
			
			actionLl.setText("");
			actionButton.setEnabled(false);
		}
		else if(!user.subType.equals("Free") && !user.parentalControl)
		{
			createLabelAndButton("Action", actionLl, actionButton, 80, 175, 70, 30);
			createLabelAndButton("Comedy", comedyLl, comedyButton, 80, 250, 70, 30);
			createLabelAndButton("Sci-fi", scifiLl, scifiButton, 80, 325, 70, 30);
			createLabelAndButton("Cartoon", cartoonLl, cartoonButton, 80, 400, 70, 30);
		}
	}
	
	private void movieDescription(ActionEvent ae)
	{
		if(ae.getSource() == actionButton)
		{
			actionButton.setBorderPainted(true);
			scifiButton.setBorderPainted(false);
			comedyButton.setBorderPainted(false);
			cartoonButton.setBorderPainted(false);
			
			firstMovieTitleLl.setText("Avengers: Infinity War");
			firstCurrentMovie = "Avengers: Infinity War";
			firstMovieLl.setText("Description: -");
			
			secondMovieTitleLl.setText("Captain America");
			secondCurrentMovie = "Captain America";
			secondMovieLl.setText("Description: -");
		}
		
		if(ae.getSource() == scifiButton)
		{
			actionButton.setBorderPainted(false);
			scifiButton.setBorderPainted(true);
			comedyButton.setBorderPainted(false);
			cartoonButton.setBorderPainted(false);
			
			firstMovieTitleLl.setText("Star Wars: Episode V");
			firstCurrentMovie = "Star Wars: Episode V";
			firstMovieLl.setText("Description: -");
			
			secondMovieTitleLl.setText("Inception");
			secondCurrentMovie = "Inception";
			secondMovieLl.setText("Description: -");
		}
		
		if(ae.getSource() == comedyButton)
		{
			actionButton.setBorderPainted(false);
			scifiButton.setBorderPainted(false);
			comedyButton.setBorderPainted(true);
			cartoonButton.setBorderPainted(false);
			
			firstMovieTitleLl.setText("Truman Show");
			firstCurrentMovie = "Truman Show";
			firstMovieLl.setText("Description: -");
			
			secondMovieTitleLl.setText("Intouchables");
			secondCurrentMovie = "Intouchables";
			secondMovieLl.setText("Description: -");
		}
		
		if(ae.getSource() == cartoonButton)
		{
			actionButton.setBorderPainted(false);
			scifiButton.setBorderPainted(false);
			comedyButton.setBorderPainted(false);
			cartoonButton.setBorderPainted(true);
			
			firstMovieTitleLl.setText("Batman: The Animated Series");
			firstCurrentMovie = "Batman: The Animated Series";
			firstMovieLl.setText("Description: -");
			
			secondMovieTitleLl.setText("Avatar: The Last Airbender");
			secondCurrentMovie = "Avatar: The Last Airbender";
			secondMovieLl.setText("Description: -");
		}
		
		if(ae.getSource() == parentalControlButton)
		{
			actionButton.setBorderPainted(false);
			
			firstMovieTitleLl.setText("");
			firstMovieLl.setText("");
			secondMovieTitleLl.setText("");
			secondMovieLl.setText("");
		}
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		Logger logger = new Logger();
		
		movieDescription(ae);
		
		if(ae.getSource() == parentalControlButton)
		{
			user.parentalControl = !user.parentalControl;
			
			if(!user.parentalControl)
			{
				infoParentalControl.setText("Off");
				infoParentalControl.setForeground(new Color(255, 0, 0));
			}
			else
			{
				infoParentalControl.setText("On");
				infoParentalControl.setForeground(new Color(0, 255, 0));
			}
			
			logger.changeParentalControl(user, user.username);
			
			designForType();
		}
		
		if(ae.getSource() == firstWatchButton)
		{
			new VideoFrame(user, firstCurrentMovie);
		}
		
		if(ae.getSource() == secondWatchButton)
		{
			new VideoFrame(user, secondCurrentMovie);
		}
		
		if(ae.getSource() == renewButton)
		{
			PaymentFrame payFrame = new PaymentFrame();
			user.isRenew = true;
			
			payFrame.setUser(user);
			payFrame.setVisible(true);
		}
	}
}