package naftoreiclag.pigcollision;

public class Circle
{
	public Vector2d loc;
	public final double rad;
	public final double radsq;
	public final double pushStrength;
	public final double pushResistance;
	
	public Vector2d motion = new Vector2d();
	
	public Circle(Vector2d loc, double rad, double strength, double weight)
	{
		this.loc = loc;
		this.rad = rad;
		this.radsq = rad * rad;
		this.pushStrength = strength;
		this.pushResistance = weight;
	}
	
	public Circle(double x, double y, double rad, double strength, double weight)
	{
		this(new Vector2d(x, y), rad, strength, weight);
	}
}