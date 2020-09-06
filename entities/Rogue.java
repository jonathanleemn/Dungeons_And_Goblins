package entities;

import world.Location;

public class Rogue extends Player {

	public Rogue(String name, Location loc) 
	{
		super(name, loc);
		setHealth(5);
		setHitRating(70);
		setDodgeChance(80);
		setDescription("A rogue who wanders the shadows as though they are sunlit streets");
	}

}
