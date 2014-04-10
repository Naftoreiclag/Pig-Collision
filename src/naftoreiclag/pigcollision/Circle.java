package naftoreiclag.pigcollision;

public class Circle
{
	public Vector2d loc;
	public final double rad;
	public final double radsq;
	
	public Vector2d velocity = new Vector2d();
	
	public Circle(Vector2d loc, double rad)
	{
		this.loc = loc;
		this.rad = rad;
		this.radsq = rad * rad;
	}
	
	public Circle(double x, double y, double rad)
	{
		this(new Vector2d(x, y), rad);
	}
}
