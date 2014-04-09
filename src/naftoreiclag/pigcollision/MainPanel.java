package naftoreiclag.pigcollision;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainPanel extends JPanel
{
	PCSpace space;
	
	PCCircle circle;
	
	public MainPanel()
	{
		System.out.println("lofewjgfioew");
		this.setSize(300, 200);
		
		space = new PCSpace();
		
		PCLine line = new PCLine(new Vector2DF(30, 30), new Vector2DF(270, 100));
		
		circle = new PCCircle(new Vector2DF(35, 35), 20.0f);
		
		space.circles.add(circle);
		space.lines.add(line);
		
		space.simulate(0.0f);
		
	}
	
	@Override
	public void paint(Graphics g)
	{
		space.paint((Graphics2D) g);
	}
}
