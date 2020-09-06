package utilities;

import java.util.Random;

import entities.*;
import items.Armor;
import items.Item;
import items.MiscItem;
import items.Potion;
import items.Weapon;

public class Utilities
{
	public Weapon wep = new Weapon(null, null, 0);
	Armor armor = new Armor(null, null, 0);
	MiscItem item = new MiscItem(null, null);
	Random rand = new Random();
	public boolean weaponTrue;
	public boolean armorTrue;
	
	public Monster generateMonster()//random monster generator
	{
		Monster mon = new Monster();
		String string = new String();
		int toSummon = rand.nextInt(3) + 1;
		switch (toSummon)
		{
		case 1:
			string = "You encounter a zombie.";
			Zombie zomb = new Zombie();
			mon = zomb;
			break;
		case 2:
			string = "You encounter a skeleton.";
			Skeleton skel = new Skeleton();
			mon = skel;
			break;
		case 3:
			string = "You encounter a slime.";
			Slime slim = new Slime();
			mon = slim;
			break;
		}
		return mon;
	}

	public Item generateItem()//generates random item
	{
		int toSummon = rand.nextInt(4) + 1;
		if (toSummon == 1)
		{
			return generateRandomWeapon();
		} 
		else if (toSummon == 2)
		{
			return generateRandomArmor();
		} 
		else if (toSummon == 3)
		{
			Potion healthPotion = new Potion("Health Potion", "Restores 10 HP", 10);
			return healthPotion;
		} 
		else
		{
			return generateRandomMiscItem();
		}
	}
	
	public Weapon generateRandomWeapon()//generates random weapon
	{
		weaponTrue = true;
		int toSummon = rand.nextInt(5)+1;
		switch(toSummon)
		{
		case 1:
			Weapon woodenSword = new Weapon("Wooden Sword", "A child's toy", 1);
			wep = woodenSword;
			break;
		case 2:
			Weapon ironDagger = new Weapon("Iron Dagger", "A rusty blade that does little damage", 5);
			wep = ironDagger;
			break;
		case 3:
			Weapon mithrilAxe = new Weapon("Mithril Axe", "A dwarven axe lost to the ages", 11);
			wep = mithrilAxe;
			break;
		case 4:
			Weapon valyrianSword = new Weapon("Valyrian Sword", "A mastercrafted blade", 13);
			wep = valyrianSword;
			break;
		case 5:
			Weapon dragonTooth = new Weapon("Dragon's Tooth", "A dragon tooth fashioned into a weapon", 12);
			wep = dragonTooth;
			break;
		}
		return wep;
	}
	
	public Armor generateRandomArmor()//generates random armor
	{
		armorTrue = true;
		int toSummon = rand.nextInt(5)+1;
		switch(toSummon)
		{
		case 1:
			Armor ironPlate = new Armor("Iron Platemail", "Iron armor fit for a knight", 10);
			armor = ironPlate;
			break;
		case 2:
			Armor banditArmor = new Armor("Bandit Armor", "Leather garbs that once belonged to a pillaging bandit", 5);
			armor = banditArmor;
			break;
		case 3:
			Armor sorcererRobes = new Armor("Sorcerer Robes", "Robes that once belonged to a scribe of Vinheim ", 4);
			armor = sorcererRobes;
			break;
		case 4:
			Armor crystalArmor = new Armor("Crystal Armor", "An armor made up of enchanted crystals", 8);
			armor = crystalArmor;
			break;
		case 5:
			Armor wyvernArmor = new Armor("Wyvern Armor", "Armor crafted from wyvern scales", 11);
			armor = wyvernArmor;
			break;
		}
		return armor;
	}
	
	public MiscItem generateRandomMiscItem()//generates random misc item
	{
		int toSummon = rand.nextInt(5)+1;
		switch(toSummon)
		{
		case 1:
			MiscItem mirror = new MiscItem("Mirror", "An ornate looking class once belonging to a vain townsfolk");
			item = mirror;
			break;
		case 2:
			MiscItem goblet = new MiscItem("Ornate Goblet", "A goblet once belonging to a noble of Vinheim");
			item = goblet;
			break;
		case 3:
			MiscItem ark = new MiscItem("Ark of the Covenant", "Don't look inside!");
			item = ark;
			break;
		case 4:
			MiscItem stone = new MiscItem("Glowing Stone", "It whispers to you in a language you cannot understand");
			item = stone;
			break;
		case 5:
			MiscItem dungpie = new MiscItem("Dungpie", "Atrocious fecal waste material");
			item = dungpie;
			break;
		}
		return item;
	}

}
