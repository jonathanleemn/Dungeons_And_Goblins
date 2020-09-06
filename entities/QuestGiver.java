package entities;

public class QuestGiver extends NPC
{
	// boolean to check to see if quest was given or not
	boolean gaveQuest;

	public QuestGiver()
	{
		setDescription("An upstanding looking townsfolk");
		gaveQuest = false;
	}

	public void interact()
	{// gives quest if not already given, repeats request if quest already given
		if (gaveQuest = false)
		{
			giveQuest();
			gaveQuest = true;
		}
		if (gaveQuest = true)
		{
			repeatScript();
		}
	}

	// gives intial quest dialogue
	public String giveQuest()
	{
		return "A villager approaches you: \n\"Good day, Adventurer! I require your assistance."
				+ "\nWould you kindly return my house key to me?\nIt's made of brass. I'll reward you accordlingly!\"";
	}

	//repeats script if you haven't found the key yet and run into the questGiver npc
	public String repeatScript()
	{
		return "Have you found my key yet?";
	}

	public String finishQuest()
	{// endgame line
		return "You found it! Oh thank you, thank you, thank you!\nNow I can finally return home!\n\n";
	}
}
