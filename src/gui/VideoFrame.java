package gui;

import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;

public class VideoFrame extends JFrame{
	private User user;
	private String movieName;
	
	private JLabel quality;
	
	public VideoFrame(User u, String mn) {
		user = u;
		movieName = mn;
		
		Logger logger = new Logger();
		
		logger.logForType();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screenSize.getWidth();
		int height = (int)screenSize.getHeight();
		
		setTitle("DINO - " + movieName);
		setSize(800, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(true);
		
		setBounds(width/2 - 400, height/2 - 300, 800, 600);
		
		quality = new JLabel("");
		
		if(user.subType.equals("Premium"))
			quality.setText("1080p - HD Quality");
		else if (user.subType.equals("Trial"))
			quality.setText("720p - HD Quality");
		else
			quality.setText("360p - SD Quality");
		
		quality.setBounds(300, 260, 200, 40);
		
		Container c = getContentPane();
		c.setLayout(null);
		
		c.add(quality);
	}
}
