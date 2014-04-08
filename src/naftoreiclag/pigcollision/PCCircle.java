package naftoreiclag.pigcollision;

public class PCCircle
{
	public Vector2DF loc;
	public float rad;
	
	public Vector2DF velocity;
	
	public PCCircle(float radius, Vector2DF location)
	{
		this.rad = radius;
		this.loc = location;
	}
}
