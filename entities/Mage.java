package entities;

import java.util.ArrayList;
//import utilities.Spell;
import utilities.Spells;
import world.Location;

public class Mage extends Player {
//	String [] Spells= {"Fireball", "Cone of Cold", "Rock Spire", "Wind Blade"};
	public ArrayList <Spells> spells=new ArrayList <Spells>();//Arraylist for spell enums
	
	public Mage(String name, Location loc) {
		super(name, loc);
		setHealth(4);
		setHitRating(90);
		setDodgeChance(70);
		setDescription("A master of harnessing the elements");
	}
	
	public void fillSpellBook(){//fills spell arraylist
		for(Spells s: Spells.values()){
			spells.add(s);
		}
	}
	
	@Override
	public int getAttack()
	{
		return backpack.getAttackBonus() + baseDamage;
	}
	
	@Override
	public void setBaseDamage(int damage)
	{
		baseDamage = damage;
	}
	
	@Override
	public int getBaseDamage()
	{
		return baseDamage;
	}
	
	@Override
	public void displaySpells(){//prints all spells
		for(int i = 0; i < spells.size(); i++){
			System.out.printf(spells.get(i).getSpellName()+", "+ spells.get(i).getSpellPower()+" ");
		}
		
	}

}
