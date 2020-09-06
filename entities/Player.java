package entities;

import java.util.ArrayList;

import utilities.Constants;
import utilities.Spells;
import world.Location;

public class Player extends Entity
{
	public ArrayList <String> spells=new ArrayList <String>();//Arraylist for spell enums
	public ArrayList <Spells> spellsList=new ArrayList <Spells>();
	
	public Player(String name, Location loc)
	{
		setName(name);
		setLoc(loc);
		setStatus(Status.ALIVE);
		setBaseDamage(1);
	}

	public String consumePotion()//method to remove potion from inventory and add to your health
	{
		String string = null;
		try
		{
			backpack.removeConsumable(0);
			int health = getHealth() + Constants.HP_POTION;
			addHealth(health);
			string = "You drink a health potion.";
		} 
		catch (IndexOutOfBoundsException e)
		{
			string = "No potions in backpack.";
		}
		return string;
	}

	public void fillSpellBook(){//fills spell arraylist
		for(Spells s: Spells.values()){
			spells.add(s.toString());
		}
		
		for(Spells s: Spells.values()){
			spellsList.add(s);
		}
	}

	public void displaySpells() {
		// TODO Auto-generated method stub
		
	}


	
}
