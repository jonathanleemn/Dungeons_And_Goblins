package game;

import java.util.Scanner;
import entities.Fighter;
import entities.Mage;
import entities.Monster;
import entities.Player;
import entities.Rogue;
import events.Events;
import items.Armor;
import items.MiscItem;
import items.Potion;
import items.Weapon;
import utilities.Utilities;
import world.Location;
import world.Map;

public class Commands {
	//initalizes values
	Fighter fighter = new Fighter("Grognak", new Location(0, 0));
	Mage mage = new Mage("Beatrice", new Location(0, 0));
	Rogue rogue = new Rogue("Minsc", new Location(0, 0));
	public Player player = new Player("Player", new Location(0, 0));
	Monster mon = new Monster();
	Map map = new Map(player);
	Events event = new Events();
	Utilities util = new Utilities();
	boolean promptKey = false;

	Potion healthPotion = new Potion("Health Potion", "Restores 10 HP", 10);
	Weapon ironSword = new Weapon("Iron Sword", "An ordinary blade made of iron", 10);
	Armor leatherArmor = new Armor("Leather Armor", "An unassuming set of leather garbs", 5);
	MiscItem brassKey = new MiscItem("Brass Key", "A mysterious key made of brass");
	Scanner input = new Scanner(System.in);

	public String chooseCommand()
	{
		boolean move = false;
		
		String string = player.locString();
		while(move == false)
		{
			return "Commands: Inventory - Consume - Equip - Unequip - Move - Map"
					+ "\nWhat do you want to do? ";
		}
		return string;
	}
	
	// equip prompt/command
		public void equip(){
			try
			{
				System.out.print("Enter the index of the item you wish to equip: ");
				int itemCommand = input.nextInt();
				player.backpack.equipItem(itemCommand);

				System.out.println(player.backpack.getEquipped());
				System.out.println();
			} catch (IndexOutOfBoundsException e)
			{
				System.out.println("No item in that index.");
				System.out.println();
			}
		}
		
		// unequip prompt/command
		public void unequip(){
			try
			{
				System.out.print("Enter the index of the item you wish to unequip: ");
				int itemCommand = input.nextInt();
				player.backpack.unequipItem(itemCommand);
				System.out.println(player.backpack.getEquipped());
				System.out.println();
			} catch (IndexOutOfBoundsException e)
			{
				System.out.println("No item in that index.");
				System.out.println();
			}
		}

	public void selectAClass(String textArea)//switch to select player class
	{
		do
		{
			textArea = textArea.toLowerCase();
			
			switch(textArea){
				case"fighter":
					player=fighter;
					break;
				case"mage":
					player=mage;
					break;
				case"rogue":
					player=rogue;
					break;
			}
			player.setMaxHealth(player.getHealth());

		} while (!(textArea.equalsIgnoreCase("fighter") || textArea.equalsIgnoreCase("mage")
				|| textArea.equalsIgnoreCase("rogue")));
	}
	 
	// checks for valid move then performs accordingly
		public void makeMove()
		{
			boolean isValid = false;
			while (isValid == false){
				System.out.println("Which direction do you want to move in? (w = north, a = west, s = south, d = east) ");
				char direction = input.next().toUpperCase().charAt(0);
				int tempY = player.loc.getRow();
				int tempX = player.loc.getCol();
				
				try{
					switch(direction)
					{
					case 'W': case 'w':
						player.loc.setRow(player.loc.getRow()-1);
						isValid = true;
						break;
					case 'A': case 'a':
						player.loc.setCol(player.loc.getCol()-1);
						isValid = true;
						break;
					case 'S': case 's':
						player.loc.setRow(player.loc.getRow()+1);
						isValid = true;
						break;
					case 'D': case 'd':
						player.loc.setCol(player.loc.getCol()+1);
						isValid = true;
						break;
						
						// cheats to test end game constraints
					case '/':
						player.loc.setCol(9);
						player.loc.setRow(9);
						isValid = true;
						break;
					case '.':
						player.loc.setCol(0);
						player.loc.setRow(0);
						isValid = true;
						break;
					default:
						System.out.println("Invalid input");
						isValid = false;
						break;
					} //end switch
					map.fillMap(player);
				} catch (ArrayIndexOutOfBoundsException e){
					player.loc.setRow(tempY);
					player.loc.setCol(tempX);
					System.out.println("You've run into a wall, good job.");
					isValid = false;
				} // end catch
			} // end check for valid move
		}
	
}
