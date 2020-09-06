package items;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.collections.ObservableList;

public class Inventory
{



	@Override
	public String toString()
	{
		return "Equippable Items=" + equippableItems + ", \nItems Equipped=" + Arrays.toString(itemsEquipped)
				+ ", \nConsumable Items=" + consumableItems + ", \nMisc. Items=" + miscItems;
	}

	public String getEquipped()
	{
		return "Items Equipped=" + Arrays.toString(itemsEquipped);

	}

	private ArrayList<Equippable> equippableItems;
	private Equippable[] itemsEquipped;
	private ArrayList<Consumable> consumableItems;
	public ArrayList<MiscItem> miscItems;
	public ArrayList<String> equippableWeapons;
	public ArrayList<String> equippableArmor;


	public Inventory()
	{
		equippableItems = new ArrayList<Equippable>();
		itemsEquipped = new Equippable[2];
		consumableItems = new ArrayList<Consumable>();
		miscItems = new ArrayList<MiscItem>();
		equippableWeapons = new ArrayList<String>();
		equippableArmor = new ArrayList<String>();
	}

	public void addMiscItemToInventory(Item item)//adds Misc item to inventory
	{
		miscItems.add((MiscItem) item);
	}

	public MiscItem removeMiscItemFromInventory(int index)//removes Misc item from inventory
	{
		return miscItems.remove(index);
	}

	public void addEquippableToInventory(Item item)//adds equipable to inventory
	{
		equippableItems.add((Equippable) item);
		if (item instanceof Weapon)
		{
			equippableWeapons.add(item.getItemName());
		}
		else if(item instanceof Armor)
		{
			equippableArmor.add(item.getItemName());
		}
	}

	public Equippable removeEquippableFromInventory(int index)//removes equipable from inventory
	{
		return equippableItems.remove(index);
	}

	public void addConsumableToInventory(Item item)
	{
		consumableItems.add((Consumable) item);
	}

	public void equipItem(int item)//equips item based on armor or weapon class
	{
		Equippable equippableItem = equippableItems.remove(item);
		if (equippableItem instanceof Armor)
		{
			unequipItem(0);
			itemsEquipped[0] = equippableItem;
		} 
		else if (equippableItem instanceof Weapon)
		{
			unequipItem(1);
			itemsEquipped[1] = equippableItem;
		}
	}

	public Consumable removeConsumable(int index)//removes consumable from inventory
	{
		return consumableItems.remove(index);
	}

	public ArrayList<Consumable> getConsumableItems()//gets consumables Items
	{
		return consumableItems;
	}

	public void setConsumableItems(ArrayList<Consumable> consumableItems)//sets consumable items
	{
		this.consumableItems = consumableItems;
	}

	public void unequipItem(int index)//unequips items 
	{
		if (itemsEquipped[index] != null)
		{
			equippableItems.add(itemsEquipped[index]);
			itemsEquipped[index] = null;
		}
		else
		{
			
		}
	}

	public ArrayList<Equippable> getEquippableItems()
	{
		return equippableItems;
	}

	public ArrayList<String> getEquippableWeapons()
	{
		return equippableWeapons;
	}

	public void setEquippableWeapons(ArrayList<String> equippableWeapons)
	{
		this.equippableWeapons = equippableWeapons;
	}

	public void setEquippableItems(ArrayList<Equippable> equippableItems)
	{
		this.equippableItems = equippableItems;
	}

	public Equippable[] getItemsEquipped()
	{
		return itemsEquipped;
	}

	public void setItemsEquipped(Equippable[] itemsEquipped)
	{
		this.itemsEquipped = itemsEquipped;
	}

	public int getAttackBonus()//adds bonus to attack from weapons
	{
		int total = 0;

		for (int i = 0; i < itemsEquipped.length; i++)
		{
			try
			{
				total += itemsEquipped[i].getAttBonus();
			} catch (NullPointerException e)
			{
				total += 0;
			}
		}

		return total;
	}

	public int getDefenseBonus()//gets defence bonus from armor
	{
		int total = 0;

		for (int i = 0; i < itemsEquipped.length; i++)
		{
			try
			{
				total += itemsEquipped[i].getDefBonus();
			} catch (NullPointerException e)
			{
				total += 0;
			}
		}
		return total;
	}

	public ArrayList<String> getEquippableArmor()
	{
		return equippableArmor;
	}

	public void setEquippableArmor(ArrayList<String> equippableArmor)
	{
		this.equippableArmor = equippableArmor;
	}

	
}
