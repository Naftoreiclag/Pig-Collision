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
			
			for(Line line : lines)
			{
				if(this.collides(line.a, line.b, circle.loc, circle.rad))
				{
					collides = true;
					break;
				}
			}
			
			if(collides)
			{
				circle.loc.subtractLocal(circle.velocity);
			}
		}
	}
	
	// I had to use uppercase letters here to avoid (more) confusion
	public Vector2d closestPoint(Vector2d A, Vector2d B, Vector2d C)
	{
		// What
		Vector2d AC = C.subtract(A);
		Vector2d AB = B.subtract(A);
		
		// I don't even
		double AB_distsq = (AB.a * AB.a) + (AB.b * AB.b);;
		
		// What is a dot product
		double AC_dot_AB = (AC.a * AB.a) + (AC.b * AB.b);
		
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
		
		System.out.println("Closest:\t" + (int) (returnVal.a) + ", " + (int) (returnVal.b));
		System.out.println("Circle: \t" + (int) (C.a) + ", " + (int) (C.b));
		System.out.println("Distsq \t" + (int) (returnVal.distanceSquared(C)));
		System.out.println("Distance \t" + (int) (Math.sqrt(returnVal.distanceSquared(C))));
		
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
	
	// I had to use uppercase letters here to avoid (more) confusion
	public boolean collides(Vector2d A, Vector2d B, Vector2d C, double rad)
	{
		// If the x values are the same, then the line is vertical
		if(A.a == B.a)
		{
			// Just check the differences in x
			return Math.abs(A.a - C.a) <= rad;
		}
		
		// If the y values are the same, then the line is horizontal
		if(A.b == B.b)
		{
			// Just check the differences in y
			return Math.abs(A.b - C.b) <= rad;
		}
		
		// Line is "non-axial" (i.e. not 0, 90, 180, or 270)
		
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
		
		// Get the closest point on an INFINITE line
		Vector2d returnVal = A.add(AB.multiply(magic));
		
		
		return returnVal.distanceSquared(C) <= (rad * rad);
	}
}
