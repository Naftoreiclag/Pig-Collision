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
		
		System.out.println(pntDistanceSq(lineA, lineB, circlePos));
		
		this.repaint();
	}
	
	// I had to use uppercase letters here to avoid (more) confusion
	public Vector2d closestPoint(Vector2d A, Vector2d B, Vector2d C)
	{
		// What
		Vector2d AC = C.subtract(A);
		Vector2d AB = B.subtract(A);
		
		// I don't even
		double AB_distsq = AB.magnitudeSquared();
		
		// What is a dot product
		double AC_dot_AB = AC.dotProduct(AB);
		
		// What is this
		double magic = AC_dot_AB / AB_distsq;
		
		// Get the closest point on an INFINITE line
		Vector2d returnVal = A.add(AB.multiplyLocal(magic));
		
		// Clamp the values to be within the points
		if(returnVal.a < (A.a < B.a ? A.a : B.a))
		{
			return A;
		}
		if(returnVal.a > (A.a > B.a ? A.a : B.a))
		{
			return B;
		}
		
		// Return this beast
		return returnVal;
	}
	
	
	// I had to use uppercase letters here to avoid (more) confusion
	public double pntDistanceSq(Vector2d A, Vector2d B, Vector2d C)
	{
		// What
		Vector2d AC = C.subtract(A);
		Vector2d AB = B.subtract(A);
		
		// I don't even
		double AB_distsq = AB.magnitudeSquared();
		
		// What is a dot product
		double AC_dot_AB = (AC.a * AB.a) + (AC.b * AB.b);
		
		// What is this
		double magic = AC_dot_AB / AB_distsq;
		
		// Get the closest point on an INFINITE line
		Vector2d returnVal = A.add(AB.multiplyLocal(magic));
		
		return returnVal.distanceSquared(C);
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
