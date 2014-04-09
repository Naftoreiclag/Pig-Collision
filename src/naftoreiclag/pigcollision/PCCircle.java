package naftoreiclag.pigcollision;

public class PCCircle
{
	public Vector2DF loc;
	public float rad;
	
	public Vector2DF velocity;
	
	public PCCircle(Vector2DF location, float radius)
	{
		this.rad = radius;
		this.loc = location;
	}
}
