package entities;

import world.Location;

public class Fighter extends Player
{
	
	public Fighter(String name, Location loc)
	{
		super(name, loc);
		setHealth(8);
		setHitRating(70); 
		setDodgeChance(50);
		setBaseDamage(2);
		setDescription("A strong warrior who lets his strength do the talking");
	}
	
	public void test(){
		System.out.println("test");
	}

}
