package naftoreiclag.pigcollision;

public class Vector2d
{
	public double a;
	public double b;
	
	public Vector2d(double a, double b)
	{
		this.a = a;
		this.b = b;
	}
	
	public Vector2d clone()
	{
		return new Vector2d(this.a, this.b);
	}
	
	public Vector2d addLocal(Vector2d other)
	{
		this.a += other.a;
		this.b += other.b;
		
		return this;
	}
	
	public Vector2d add(Vector2d other)
	{
		return this.clone().addLocal(other);
	}
	
	public Vector2d addLocal(double a)
	{
		this.a += a;
		this.b += a;
		
		return this;
	}
	
	public Vector2d add(double a)
	{
		return this.clone().addLocal(a);
	}
	
	public Vector2d subtractLocal(Vector2d other)
	{
		this.a -= other.a;
		this.b -= other.b;
		
		return this;
	}
	
	public Vector2d subtract(Vector2d other)
	{
		return this.clone().subtractLocal(other);
	}
	
	public Vector2d subtractLocal(double a)
	{
		this.a -= a;
		this.b -= a;
		
		return this;
	}
	
	public Vector2d subtract(double a)
	{
		return this.clone().subtractLocal(a);
	}
	
	public Vector2d multiplyLocal(double a)
	{
		this.a *= a;
		this.b *= a;
		
		return this;
	}
	
	public Vector2d multiply(double a)
	{
		return this.clone().multiplyLocal(a);
	}
	
	public Vector2d negativeLocal()
	{
		this.a = -this.a;
		this.b = -this.b;
		
		return this;
	}
	
	public double distanceSquared(Vector2d other)
	{
		return ((this.a - other.a) * (this.a - other.a)) + ((this.b - other.b) * (this.b - other.b));
	}
	
	public double magnitudeSquared()
	{
		return (this.a * this.a) + (this.b * this.b);
	}
	
	public double dotProduct(Vector2d other)
	{
		return (this.a * other.a) + (this.b * other.b);
	}
	
	public Vector2d negative()
	{
		return this.clone().negativeLocal();
	}
}
