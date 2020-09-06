package entities;

import items.Consumable;
import items.Equippable;
import items.Inventory;
import items.Item;
import items.MiscItem;
import world.Location;

public abstract class Entity
{
	private String name, description; // age, location may also be possible
	private int health, maxHealth, hitRating, dodgeChance;
	protected int baseDamage;
	private Status status;
	public Location loc;
	public Inventory backpack;

	public Entity()//Entity constructor
	{
		backpack = new Inventory();
		this.name = null;
		this.description = null;
		this.health = 1;
		this.maxHealth = 1;
		this.hitRating = 1;
		this.dodgeChance = 1;
		this.status = null;
		this.loc = null;
	}

	public Entity(String name, String description, int health, int hitRating, int dodgeChance, Status status,//Entity constructor
			Location loc)
	{
		this.name = name;
		this.description = description;
		this.health = health;
		this.hitRating = hitRating;
		this.dodgeChance = dodgeChance;
		this.status = status;
		this.loc = loc;
	}

	public int getMaxHealth()
	{
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth)
	{
		this.maxHealth = health;
	}

	

	@Override
	public String toString()
	{
		return "Name: " + name + "\nDescription: " + description + "\nHealth: " + health + "\nHit Rating: " + hitRating
				+ "\nDodge Chance: " + dodgeChance + "\nStatus: " + status + "\n" + loc;
	}

	public String locString()
	{
		return "You are at " + loc;
	}

	public Location getLoc()
	{
		return loc;
	}

	public void setLoc(Location loc)
	{
		this.loc = loc;
	}

	public enum Status
	{
		ALIVE, DEAD
	}

	public void setStatus(Status in)
	{
		status = in;
	}

	public Status getStatus()
	{
		return status;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public int getHealth()
	{
		if (health < 0)
			return 0;
		else
			return health;
	}

	protected void setHealth(int health)
	{
		this.health = health;
	}

	public void addHealth(int healing)//Refills health to no higher than max
	{
		this.health += healing;
		if (health > maxHealth)
			health = maxHealth;
	}

	public void loseHealth(int damage)//damages health
	{
		health -= damage;
	}

	public int getDodgeChance()
	{
		return dodgeChance;
	}

	public void setDodgeChance(int dodgeChance)
	{
		this.dodgeChance = dodgeChance;
	}

	public int getHitRating()
	{
		return hitRating;
	}

	public void setHitRating(int hitRating)
	{
		this.hitRating = hitRating;
	}

	public int getAttack()
	{
		return backpack.getAttackBonus() + baseDamage;
	}

	public int getDefense()
	{
		return backpack.getDefenseBonus();
	}

	public void setBaseDamage(int damage)
	{
		baseDamage = damage;
	}
	
	public int getBaseDamage()
	{
		return baseDamage;
	}

	public int getLocY()
	{
		return loc.getCol();
	}

	public int getLocX()
	{
		return loc.getRow();
	}

	public void setLocX(int in)
	{
		loc.setRow(in);
	}

	public void setLocY(int in)
	{
		loc.setCol(in);
	}

	public String addItem(Item item)
	{
		String string = null;
		if (item instanceof Equippable)
		{
			backpack.addEquippableToInventory(item);
			string = item.getItemName() + " obtained!\n";
		}

		else if (item instanceof Consumable)
		{
			backpack.addConsumableToInventory(item);
			string = item.getItemName() + " obtained!\n";
		} else if (item instanceof MiscItem)
		{
			backpack.addMiscItemToInventory(item);
			string = item.getItemName() + " obtained!\n";
		}
		return string;
	}

} // End
