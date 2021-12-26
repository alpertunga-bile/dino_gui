package gui;

public class MainFrame {

	public static void main(String[] args) {
		try
		{
			SignFrame signFrame = new SignFrame();
		}	
		catch(Exception e)
		{
			System.out.println("::MAIN:: Unknown Error Occured");
			e.printStackTrace();
		}
	}
}
