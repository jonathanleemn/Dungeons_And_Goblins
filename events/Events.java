package events;

import java.util.Random;
import java.util.Scanner;
import entities.*;
import items.Item;
import items.MiscItem;
import utilities.Utilities;

public class Events extends Utilities
{
	Combat combat;
	Player player;
	Monster mon;
	private boolean keyGained;
	private boolean turnIn;
	private boolean gaveQuest;
	private QuestGiver elJefe;
	public boolean monsterTrue;

	// create events that take in player to perform actions, use a switch
	// statement in game
	// to implement
	public Events()
	{
		combat = new Combat();
		player = new Player(null, null);
		mon = new Monster();
		elJefe = new QuestGiver();
	}

	//generates a random event based on location
	public String getEvent(Player player)
	{
		this.player = player;
		return checkLoc();
	}

	public boolean isComplete()//returns true if quest is complete
	{
		return turnIn;
	}

	public String monsterRoom()//if room is monster room then it generates random monster combat
	{
		monsterTrue = true;
		return getDescription() + combat.run();
	}

	public String emptyRoom()//returns description if room is empty
	{
		return getDescription() + "There is nothing of interest here.";

	}

	public String npcRoom()//gives random npc encounter
	{
		NPC npc = new NPC();
		return getDescription() + "You encounter " + npc.getName() + "\n" + npc.npcDialogue();
	}

	public String itemRoom()//generates random item
	{
		return getDescription() + "You find an item.\n" + player.addItem(generateItem());
	}
	
	
	public String dlcRoom(){
		return "To view this room you must purchase the Dawnguard DLC";
	}

	// generates random room description
	public String getDescription()
	{
		Random rand = new Random();
		int in = rand.nextInt(5) + 1;
		String string = "room description";

		switch (in)
		{
		case 1:
			return string = "You are in a dusty old room, covered in cobwebs.\n";
		case 2:
			return string = "You have entered an open courtyard.\n";
		case 3:
			return string = "You are in an open room, with bookshelves on all sides.\n";
		case 4:
			return string = "You have entered a beautiful garden.\n";
		case 5:
			return string = "The room you have entered has a large hole in the ceiling.\nThe light shining through is almost blinding.\n";
		}
		return string;
	}

	// gets set room or generates room based on player location
	public String checkLoc()
	{
		int x = player.loc.getRow();
		int y = player.loc.getCol();
		String string = "generate room based on player loc";

		switch (x)
		{
		case 0:
			if (y == 0)
			{
				string = origin();
			} else 
			{
				string = getRoom();
			}
			break;
		case 1:

		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
			string = getRoom();
			break;
		case 9:
			if (y == 9)
			{
				string = questItemRoom();
			} else
			{
				string = getRoom();
			}
			break;
		}
		return string;
	}

	/*
	 * left over as a possible way to assign specific spaces to room types
	 * public void checkY(int y){ switch (y){ case 0: case 1: case 2: case 3:
	 * case 4: case 5: case 6: case 7: case 8: case 9: getRoom(); break; } }
	 */

	// generates random room
	public String getRoom()
	{
		Random rand = new Random();
		int room = rand.nextInt(5) + 1;
		String string = "generates random room";

		switch (room)
		{
		case 1:
			return string = emptyRoom();
		case 2:
			return string = itemRoom();
		case 3:
			return string = monsterRoom();
		case 4:
			return npcRoom();
		case 5:
			return dlcRoom();
		}
		return string;
	}

	public String origin()
	{
		String string = "origin";
		getDescription();
		// initial meeting
		if (keyGained == false && gaveQuest == false)
		{
			gaveQuest = true;
			string = elJefe.giveQuest();

		}
		// meeting without quest item
		else if (gaveQuest == true && keyGained == false)
		{
			string = elJefe.repeatScript();
		}
		// final meeting with quest item
		else if (keyGained == true)
		{
			string = elJefe.finishQuest();
			turnIn = true;
			keyGained = false;
		}
		return string;
	}

	public String questItemRoom()
	{
		MiscItem brassKey = new MiscItem("Brass Key", "A mysterious key made of brass");
		getDescription();

		// prompt for first getting key
		if (keyGained == false)
		{
			player.backpack.miscItems.add(brassKey);
			keyGained = true;

			return getDescription() + "You see a brass key sitting on a glowing pedestal\nYou take the key"
					+ "\nBrass Key obtained!";
		}
		// prompts for after key has been gained
		else
			return getDescription() + "You see the glowing pedestal where the brass key once sat";
	}

}