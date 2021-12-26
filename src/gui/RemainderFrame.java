package gui;

import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;

public class RemainderFrame extends JFrame implements ActionListener{
	private JLabel remainder, remainder1, question;
	
	private JButton nextButton, agreeButton;
	
	private User tempUser;
	
	public RemainderFrame(User u)
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screenSize.getWidth();
		int height = (int)screenSize.getHeight();
		
		setTitle("DINO - Remainder");
		if(u.subType.equals("Free"))
		{
			setSize(550, 250);
			setBounds(width/2 - 225, height/2 - 125, 550, 250);
		}
		else
		{
			setSize(700, 250);
			setBounds(width/2 - 350, height/2 - 125, 700, 250);
		}
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(true);
		
		tempUser = u;
		
		int ques_x, next_x, agree_x;
		
		if(tempUser.subType.equals("Free"))
		{
			remainder = new JLabel("You are using Free subscription right now");
			remainder1 = new JLabel("You are going to watch with low quality, dont have parental control feature");
			ques_x = 130;
			next_x = 150;
			agree_x = 245;
		}
		else
		{
			remainder = new JLabel("You are using Trial subscription right now");
			remainder1 = new JLabel("You have just have only 30(or what is left) days to watch in high quality and use parental control feature");
			ques_x = 200;
			next_x = 225;
			agree_x = 320;
		}
		
		remainder.setBounds(10, 30, 490, 30);
		remainder.setFont(new Font("Arial", Font.BOLD, 20));
		remainder1.setBounds(10, 70, 790, 30);
		remainder1.setFont(new Font("Arial", Font.PLAIN, 15));
		
		question = new JLabel("Do you want to subscribe for Premium");
		question.setBounds(ques_x, 120, 250, 30);
		
		nextButton = new JButton("NO");
		nextButton.setBounds(next_x, 155, 70, 30);
		
		agreeButton = new JButton("YES");
		agreeButton.setBounds(agree_x, 155, 70, 30);
		
		nextButton.addActionListener(this);
		agreeButton.addActionListener(this);
		
		Container c = getContentPane();
		c.setLayout(null);
		
		c.setBackground(new Color(52, 159, 245));
		
		c.add(remainder);
		c.add(remainder1);
		c.add(question);
		
		c.add(nextButton);
		c.add(agreeButton);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == nextButton)
		{
			MainGUIFrame mainGUI = new MainGUIFrame(tempUser);
			setVisible(false);
			mainGUI.setVisible(true);
		}
		
		if(ae.getSource() == agreeButton)
		{
			PaymentFrame payFrame = new PaymentFrame();
			tempUser.isNew = false;
			tempUser.isRenew = false;
			payFrame.setUser(tempUser);
			setVisible(false);
			payFrame.setVisible(true);
		}
	}
}
