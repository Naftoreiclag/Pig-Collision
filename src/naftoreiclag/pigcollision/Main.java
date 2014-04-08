package naftoreiclag.pigcollision;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Main extends JFrame
{
	private Main()
	{
		super("Pig Collision Demo");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300, 200);
		this.setLocationRelativeTo(null);
		
		MainPanel m = new MainPanel();
		
		this.add(m);
	}
	
	public static void main(String[] args)
	{
		System.out.println("lofewjgfioew");
		
		Main m = new Main();
		m.setVisible(true);
	}
}
