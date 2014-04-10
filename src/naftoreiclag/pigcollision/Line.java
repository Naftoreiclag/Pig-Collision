package naftoreiclag.pigcollision;

public class Line
{
	public Vector2d a;
	public Vector2d b;
	
	public Line(Vector2d a, Vector2d b)
	{
		this.a = a;
		this.b = b;
	}
	
	public Line(double ax, double ay, double bx, double by)
	{
		this(new Vector2d(ax, ay), new Vector2d(bx, by));
	}
}
