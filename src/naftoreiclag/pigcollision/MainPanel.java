package naftoreiclag.pigcollision;

import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainPanel extends JPanel
{
	public MainPanel()
	{
		this.setSize(300, 200);
	}
	
	@Override
	public void paint(Graphics g)
	{
		System.out.println("lolo");
	}
}
