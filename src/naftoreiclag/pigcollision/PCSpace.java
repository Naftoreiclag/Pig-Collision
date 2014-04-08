package naftoreiclag.pigcollision;

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
		for(PCCircle c : circles)
		{
			for(PCLine l : lines)
			{
				float ac = (float) Math.sqrt(Math.pow(c.loc.x - l.loc.x, 2) + Math.pow(c.loc.y - l.loc.y, 2));
			}
		}
	}
}
