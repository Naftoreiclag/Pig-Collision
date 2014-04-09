package naftoreiclag.pigcollision;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class PCSpace
{
	public List<PCCircle> circles = new ArrayList<PCCircle>();
	public List<PCLine> lines = new ArrayList<PCLine>();
	
	public PCSpace()
	{
		
	}
	
	public void simulate(float delta)
	{
		for(PCCircle circle : circles)
		{
			for(PCLine segment : lines)
			{
				//float ac = (float) Math.sqrt(Math.pow(c.loc.x - l.loc.x, 2) + Math.pow(c.loc.y - l.loc.y, 2));
				
				//if(ac )
				
				float seg_mag = segment.direction.b;
				float circle_rad = circle.rad;
				
				Vector2DF seg_end = new Vector2DF((float) (seg_mag * Math.cos(segment.direction.a)), (float) (seg_mag * Math.sin(segment.direction.a)));
				
				float x1 = segment.loc.a;
				float y1 = segment.loc.b;
				float x2 = seg_end.a;
				float y2 = seg_end.b;
				
				float cross_product = (x1 * y2) - (x2 * y1);
				
				float r_sq = circle_rad * circle_rad;
				float dr_sq = seg_mag * seg_mag;
				
				float bd_sq = cross_product * cross_product;
				
				System.out.println((r_sq * dr_sq) - bd_sq > 0);
			}
		}
	}
	
	public void paint(Graphics2D g)
	{
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 500, 500);
		
		g.setColor(Color.BLACK);
		
		for(PCLine segment : lines)
		{
			float seg_mag = segment.direction.b;
			Vector2DF seg_end = new Vector2DF((float) (seg_mag * Math.cos(segment.direction.a)), (float) (seg_mag * Math.sin(segment.direction.a)));

			float x1 = segment.loc.a;
			float y1 = segment.loc.b;
			float x2 = seg_end.a;
			float y2 = seg_end.b;
			
			g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
		}
		
		for(PCCircle circle : circles)
		{
			g.drawOval((int) (circle.loc.a - circle.rad), (int) (circle.loc.b - circle.rad), (int) (circle.rad * 2), (int) (circle.rad * 2));
		}
		
	}
}
