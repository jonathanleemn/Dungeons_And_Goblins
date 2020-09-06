package events;

import java.util.Random;
import java.util.Scanner;

import entities.Entity;
import entities.Monster;
import entities.Player;
import utilities.Utilities;

public class Combat 
{
	Monster mon = new Monster();
	Player player = new Player(null, null);
	Utilities util = new Utilities();
	Scanner input = new Scanner(System.in);

	public Combat()
	{

	}

	public Combat(Player player, Monster mon)
	{
		this.mon = mon;
		this.player = player;
	}

	public String run()//Generates random monster and informs player that they have run into a monster
	{
		String string = "run method";
		mon = util.generateMonster();

		if ((player.getHealth() > 0) && (mon.getHealth() > 0))
		{
			string = "You've run into a monster." + "\n";
		}
		return string;
	}


	public boolean calculateHit(Entity attacker, Entity defender)//Calculates chance of attack hitting target
	{
		Random rand = new Random();
		boolean hit = false;

		if (rand.nextInt(100) < attacker.getHitRating())
		{
			if (rand.nextInt(100) > (defender.getDodgeChance() / 2))
				hit = true;
			else
				hit = false;
		}//end outer if else
		return hit;
	}

	public String attack(Entity attacker, Entity defender)//attacker's attack is taken from enemy's health, or attacker is informed of missed strike
	{
		int damage = 0;
		int defenderHealth = defender.getHealth();
		String string = "attack method";

		if (calculateHit(attacker, defender) == true)
		{
			damage = attacker.getAttack() - defender.getDefense();
			if (damage < 0)
				damage = 0;
			string = attacker.getName() + " did " + damage + " damage\n";
		} 
		else
			string = attacker.getName() + " missed\n";

		defender.loseHealth(damage);
		return string;
	}

	public String healthStatus()//returns health of combatants
	{
		return "Your health: " + player.getHealth() + " Monster health: " + mon.getHealth();
	}

	// in battle commands
	// made the if-else into switch statements for easier readability
	public void attackCommands(Player player, Monster mon)//commands for user to control character
	{
		boolean done = false;
		while (!done)
		{
			System.out.println("What would you like to do? Attack - Inventory - Examine");
			String command = input.next();
			done = true;

			switch (command)
			{
			case "inventory":
				System.out.println(player.backpack.toString());
				break;
			case "consume":
				player.consumePotion();
				break;
			case "equip":
				try
				{
					System.out.print("Enter the index of the item you wish to equip: ");
					int itemCommand = input.nextInt();
					player.backpack.equipItem(itemCommand);

					System.out.println(player.backpack.getEquipped());
				} catch (IndexOutOfBoundsException e)
				{
					System.out.println("No item in that index.");
				}
				break;
			case "unequip":
				try
				{
					System.out.print("Enter the index of the item you wish to unequip: ");
					int itemCommand = input.nextInt();
					player.backpack.unequipItem(itemCommand);
					System.out.println(player.backpack.getEquipped());
				} catch (IndexOutOfBoundsException e)
				{
					System.out.println("No item in that index.");
				}
				break;
			case "attack":
				attack(player, mon);
				break;
			case "examine":
				mon.getDescription();
				break;
			default:
				System.out.println("Invalid command");
				done = false;
				break;
			} // end switch
		} // end loop
	} // end method

}
