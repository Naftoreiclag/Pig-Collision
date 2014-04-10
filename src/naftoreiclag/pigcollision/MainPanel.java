package naftoreiclag.pigcollision;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainPanel extends JPanel
{
	Space space;
	Circle mainCircle;
	
	public MainPanel()
	{
		this.setSize(500, 500);
		
		space = new Space();
		
		mainCircle = new Circle(250, 250, 20);
		space.circles.add(mainCircle);
		
		space.lines.add(new Line(50, 50, 200, 50));
		space.lines.add(new Line(200, 50, 200, 200));
		space.lines.add(new Line(200, 200, 50, 200));
		space.lines.add(new Line(50, 200, 50, 50));
		
		
		this.addMouseMotionListener(new MouseMotionListener()
		{
			@Override
			public void mouseDragged(MouseEvent e){ mMove(e); }
			@Override
			public void mouseMoved(MouseEvent e){ mMove(e); }
		});
	}
	private void mMove(MouseEvent e)
	{
		mainCircle.velocity.a = e.getX() - mainCircle.loc.a;
		mainCircle.velocity.b = e.getY() - mainCircle.loc.b;
		
		space.simulate();
		
		this.repaint();
	}
	
	@Override
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.WHITE);

		g2.fillRect(0, 0, 500, 500);

		g2.setColor(Color.BLACK);
		
		for(Circle c : space.circles)
		{
			g2.drawOval((int) (c.loc.a - c.rad), (int) (c.loc.b - c.rad), (int) c.rad * 2, (int) c.rad * 2);
		}
		
		for(Line l : space.lines)
		{
			g2.drawLine((int) l.a.a, (int) l.a.b, (int) l.b.a, (int) l.b.b);
		}
	}
}
