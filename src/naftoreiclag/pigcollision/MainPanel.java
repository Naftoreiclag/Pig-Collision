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
	public Vector2d circlePos = new Vector2d(0, 0);
	public double circleRadius = 20;
	public double circleRadiusSq = circleRadius * circleRadius;
	
	public Vector2d lineA = new Vector2d(40, 60);
	public Vector2d lineB = new Vector2d(190, 200);
	
	public Vector2d point = new Vector2d(0, 0);
	
	public double mx;
	public double my;
	
	public MainPanel()
	{
		this.setSize(500, 500);
		
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
		mx = e.getX();
		my = e.getY();
		
		circlePos.a = mx;
		circlePos.b = my;
		
		point = closestPoint(lineA, lineB, circlePos);
		
		this.repaint();
	}
	
	public Vector2d closestPoint(Vector2d a, Vector2d b, Vector2d p)
	{
		Vector2d ap = p.subtract(a);
		Vector2d ab = b.subtract(a);
		
		double ab_distsq = (ab.a * ab.a) + (ab.b * ab.b);
		
		double ap_dot_ab = (ap.a * ab.a) + (ap.b * ab.b);
		
		double t = ap_dot_ab / ab_distsq;
		
		return new Vector2d(a.a + (ab.a * t), a.b + (ab.b * t));
	}
	
	@Override
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.WHITE);
		
		g2.fillRect(0, 0, 500, 500);

		g2.setColor(Color.BLACK);
		
		g2.drawLine((int) lineA.a, (int) lineA.b, (int) lineB.a, (int) lineB.b);
		
		g2.drawOval((int) (circlePos.a - circleRadius), (int) (circlePos.b - circleRadius), (int) circleRadius * 2, (int) circleRadius * 2);
	
		g2.drawOval((int) point.a - 4, (int) point.b - 4, 8, 8);
	}
}
