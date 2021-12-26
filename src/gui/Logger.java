package gui;

import java.io.*;
import java.util.Scanner;

public class Logger {
	private int currentID;
	private Scanner reader;
	
	private String userFile;
	private String logFile;
	private String typeFile;
	
	Logger()
	{ 
		userFile = new String("users.txt");
		logFile = new String("log.txt");
		typeFile = new String("type.txt");
		
		File checkID = new File(userFile);
		
		try {
			reader = new Scanner(checkID);
			
			while(reader.hasNextLine())
			{
				String tempStr = reader.nextLine();
				String[] variables = tempStr.split(" ");
				
				currentID = Integer.parseInt(variables[0]);
			}
			
			currentID++;
		
		} catch (FileNotFoundException e) {
			System.out.println("Cant Open File");
			System.exit(1);
		} catch(Exception e) {
			System.out.println("::Logger:: Cant Defined Error Occured");
			System.exit(1);
		}
		finally
		{
			reader.close();
		}
	}
	
	public int getCurrentID()
	{
		return currentID;
	}
	
	public void logged(User user)
	{	
		FileOutputStream fileOut = null;
		try
		{
			fileOut = new FileOutputStream(logFile, true);
			fileOut.write(user.getLogText().getBytes());
		}
		catch(IOException ie)
		{
			System.out.println("Log File Cant Opened");
			System.exit(1);
		}
		finally
		{
			try {
				fileOut.flush();
				fileOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void userLogged(User user)
	{
		FileOutputStream fileOut = null;
		try
		{
			fileOut = new FileOutputStream(userFile, true);
			fileOut.write(user.getUserText().getBytes());
			currentID++;
		}
		catch(IOException ie)
		{
			System.out.println("Users File Cant Opened");
			System.exit(1);
		}
		finally
		{
			try {
				fileOut.flush();
				fileOut.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public User getUserData(String name)
	{
		User temp = new User();
		temp.username = "";
		
		File file = new File(userFile);
		
		try {
			reader = new Scanner(file);
			
			while(!temp.username.equals(name) && reader.hasNextLine())
			{
				String tempStr = reader.nextLine();
				String[] variables = tempStr.split(" ");
				
				temp.ID = Integer.parseInt(variables[0]);
				temp.username = variables[1];
				temp.password = variables[2];
				temp.email = variables[3];
				temp.parentalControl = Boolean.parseBoolean(variables[4]);
				temp.subType = variables[5];
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Cant Open File");
			System.exit(1);
		}
		finally
		{
			reader.close();
		}
		
		return temp;
	}
	
	public void setAsPremium(String name)
	{
		User temp = new User();
		temp.username = "";
		
		File file = new File(userFile);
		
		String storeFileContent = "";
		
		try {
			reader = new Scanner(file);
			
			while(reader.hasNextLine())
			{
				String tempStr = reader.nextLine();
				String[] variables = tempStr.split(" ");
				
				temp.ID = Integer.parseInt(variables[0]);
				temp.username = variables[1];
				temp.password = variables[2];
				temp.email = variables[3];
				temp.parentalControl = Boolean.parseBoolean(variables[4]);
				temp.subType = variables[5];
				
				if(temp.username.equals(name))
					temp.subType = "Premium";
				
				storeFileContent += temp.getUserText();
			}
			
			reader.close();
			
			try
			{
				FileWriter writer = new FileWriter(userFile);
				writer.write(storeFileContent);
				writer.close();
			}
			catch(Exception e)
			{
				System.out.println("::LOGGER:: Error occured when writing to file");
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Cant Open File");
			System.exit(1);
		}
		finally
		{
			reader.close();
		}
	}
	
	public void incrementRenew(User user, String name)
	{
		User temp = new User();
		temp.reset();
		
		File file = new File(logFile);
		
		String storeFileContent = "";
		
		String date = "";
		String time = "";
		
		try {
			reader = new Scanner(file);
			
			while(reader.hasNextLine())
			{
				String tempStr = reader.nextLine();
				String[] variables = tempStr.split(" ");
				
				temp.ID = Integer.parseInt(variables[0]);
				temp.username = variables[1];
				temp.email = variables[2];
				temp.parentalControl = Boolean.parseBoolean(variables[3]);
				temp.renewedTimes = Integer.parseInt(variables[4]);
				temp.fromTo = variables[5];
				date = variables[6];
				time = variables[7];
				temp.renewDate = date + " " +time;
				
				if(temp.username.equals(name))
				{
					temp.renewedTimes = user.renewedTimes;
					temp.renewDate = user.renewDate;
				}
				
				storeFileContent += temp.getLogText();
			}
			
			reader.close();
			
			try
			{
				FileWriter writer = new FileWriter(logFile);
				writer.write(storeFileContent);
				writer.close();
			}
			catch(Exception e)
			{
				System.out.println("::LOGGER:: Error occured when writing to file");
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Cant Open File");
			System.exit(1);
		}
		finally
		{
			reader.close();
		}
	}
	
	public void logForType()
	{
		User temp = new User();
		temp.reset();
		
		File file = new File(logFile);
		File userF = new File(userFile);
		
		String storeFileContent = "";
		
		int premiumNum = 0;
		int trialNum = 0;
		int freeNum = 0;
		int renewSum = 0;
		
		String date = "";
		String time = "";
		
		try {
			reader = new Scanner(userF);
			
			while(reader.hasNextLine())
			{
				String tempStr = reader.nextLine();
				String[] variables = tempStr.split(" ");
				
				temp.ID = Integer.parseInt(variables[0]);
				temp.username = variables[1];
				temp.password = variables[2];
				temp.email = variables[3];
				temp.parentalControl = Boolean.parseBoolean(variables[4]);
				temp.subType = variables[5];
				
				if(temp.subType.equals("Premium"))
					premiumNum++;
				else if(temp.subType.equals("Trial"))
					trialNum++;
				else
					freeNum++;
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Cant Open File");
			System.exit(1);
		}
		finally
		{
			reader.close();
		}
		
		temp.reset();
		
		try {
			reader = new Scanner(file);
			
			while(reader.hasNextLine())
			{
				String tempStr = reader.nextLine();
				String[] variables = tempStr.split(" ");
				
				temp.ID = Integer.parseInt(variables[0]);
				temp.username = variables[1];
				temp.email = variables[2];
				temp.parentalControl = Boolean.parseBoolean(variables[3]);
				temp.renewedTimes = Integer.parseInt(variables[4]);
				temp.fromTo = variables[5];
				date = variables[6];
				time = variables[7];
				temp.renewDate = date + " " +time;
				
				renewSum += temp.renewedTimes;
			}
			
			storeFileContent = "Premium subscription has 1080p quality, parental control and all genres. It has " + premiumNum + " members and renewed " + renewSum + " times.\n";
			storeFileContent += "Trial subscription has 720p quality, parental control and all genres. It has " + trialNum + " members and renewed " + trialNum + " times.\n";
			storeFileContent += "Free subscription has 360p quality, parental control and limited genres. It has " + freeNum + " members and renewed " + freeNum + " times.\n";
			
			reader.close();
			
			try
			{
				FileWriter writer = new FileWriter(typeFile);
				writer.write(storeFileContent);
				writer.close();
			}
			catch(Exception e)
			{
				System.out.println("::LOGGER:: Error occured when writing to file");
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Cant Open File");
			System.exit(1);
		}
		finally
		{
			reader.close();
		}
	}
	
	public void changeParentalControl(User user, String name)
	{
		User temp = new User();
		temp.username = "";
		
		File file = new File(userFile);
		
		String storeFileContent = "";
		
		try {
			reader = new Scanner(file);
			
			while(reader.hasNextLine())
			{
				String tempStr = reader.nextLine();
				String[] variables = tempStr.split(" ");
				
				temp.ID = Integer.parseInt(variables[0]);
				temp.username = variables[1];
				temp.password = variables[2];
				temp.email = variables[3];
				temp.parentalControl = Boolean.parseBoolean(variables[4]);
				temp.subType = variables[5];
				
				if(temp.username.equals(name))
				{
					temp.parentalControl = user.parentalControl;
				}
				
				storeFileContent += temp.getUserText();
			}
			
			reader.close();
			
			try
			{
				FileWriter writer = new FileWriter(userFile);
				writer.write(storeFileContent);
				writer.close();
			}
			catch(Exception e)
			{
				System.out.println("::LOGGER:: Error occured when writing to file");
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Cant Open File");
			System.exit(1);
		}
		{
			reader.close();
		}
		
		temp.reset();
		
		file = new File(logFile);
		
		storeFileContent = "";
		
		String date = "";
		String time = "";
		
		try {
			reader = new Scanner(file);
			
			while(reader.hasNextLine())
			{
				String tempStr = reader.nextLine();
				String[] variables = tempStr.split(" ");
				
				temp.ID = Integer.parseInt(variables[0]);
				temp.username = variables[1];
				temp.email = variables[2];
				temp.parentalControl = Boolean.parseBoolean(variables[3]);
				temp.renewedTimes = Integer.parseInt(variables[4]);
				temp.fromTo = variables[5];
				date = variables[6];
				time = variables[7];
				temp.renewDate = date + " " + time;
				
				if(temp.username.equals(name))
					temp.parentalControl = user.parentalControl;
				
				storeFileContent += temp.getLogText();
			}
			
			reader.close();
			
			try
			{
				FileWriter writer = new FileWriter(logFile);
				writer.write(storeFileContent);
				writer.close();
			}
			catch(Exception e)
			{
				System.out.println("::LOGGER:: Error occured when writing to file");
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Cant Open File");
			System.exit(1);
		}
		finally
		{
			reader.close();
		}
	}
}

class User
{	
	public int ID;
	public String username;
	public String password;
	public String email;
	public String subType;
	public boolean parentalControl;
	public int renewedTimes;
	public String fromTo;
	public String renewDate;
	public boolean isNew;
	public boolean isRenew;
	
	public String getLogText()
	{
		return (ID + " " + username + " " + email + " " + parentalControl + " " + renewedTimes + " " + fromTo + " " + renewDate + " \n");
	}
	
	public String getUserText()
	{
		return (ID + " " + username + " " + password + " " + email + " " + parentalControl + " " + subType + " \n");
	}
	
	public User()
	{
		ID = 0;
		username = "";
		password = "";
		email = "";
		subType = "";
		parentalControl = false;
		renewedTimes = 1;
		fromTo = "";
		renewDate = "";
		isNew = true;
		isRenew = false;
	}
	
	public void reset()
	{
		ID = 0;
		username = "";
		password = "";
		email = "";
		subType = "";
		parentalControl = false;
		renewedTimes = 1;
		fromTo = "";
		renewDate = "";
		isNew = true;
		isRenew = false;
	}
}
