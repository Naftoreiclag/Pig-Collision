package naftoreiclag.pigcollision;

import java.util.ArrayList;
import java.util.List;

public class Space
{
	public List<Circle> circles = new ArrayList<Circle>();
	public List<Line> lines = new ArrayList<Line>();
	
	public void simulate()
	{
		for(Circle circle : circles)
		{
			circle.loc.addLocal(circle.velocity);
			
			boolean collides = false;
			
			Vector2d penetration;
			
			for(Line line : lines)
			{
				////////////////////////
				
				
				
				// What
				Vector2d AC = circle.loc.subtract(line.a);
				Vector2d AB = line.b.subtract(line.a);

				// I don't even
				double AB_distsq = (AB.a * AB.a) + (AB.b * AB.b);
				
				// What is a dot product
				double AC_dot_AB = (AC.a * AB.a) + (AC.b * AB.b);
				
				// What is this
				double magic = AC_dot_AB / AB_distsq;

				// Past B
				if(magic > 1)
				{
					continue;
				}
				
				// Past A
				else if(magic < 0)
				{
					double AC_distsq = AC.magnitudeSquared();
					
					if(AC_distsq <= circle.radsq)
					{
						collides = true;
						break;
					}
				}
				
				// Within the line
				else
				{
					if(line.a.add(AB.multiply(magic)).distanceSquared(circle.loc) <= circle.radsq)
					{
						collides = true;
						break;
					}
				}
			}
			
			if(collides)
			{
				circle.loc.subtractLocal(circle.velocity);
				
				
			}
		}
	}
	
	// I had to use uppercase letters here to avoid (more) confusion
	public boolean collides(Vector2d A, Vector2d B, Vector2d C, double rad)
	{
		// TODO: put checks for horizontal / vertical lines for max efficiency over 9000
		
		// What
		Vector2d AC = C.subtract(A);
		Vector2d AB = B.subtract(A);

		// I don't even
		double AB_distsq = (AB.a * AB.a) + (AB.b * AB.b);
		
		// What is a dot product
		double AC_dot_AB = (AC.a * AB.a) + (AC.b * AB.b);
		
		// What is this
		double magic = AC_dot_AB / AB_distsq;

		if(magic > 1) return false;
		else if(magic < 0)
		{
			double AC_distsq = AC.magnitudeSquared();
			
			return AC_distsq <= (rad * rad);
		}
		
		return A.add(AB.multiply(magic)).distanceSquared(C) <= (rad * rad);
	}
}
