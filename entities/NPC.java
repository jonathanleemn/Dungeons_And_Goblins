package entities;

import java.util.Random;

public class NPC extends Entity 
{
	Random rand = new Random();

	public NPC()
	{
		setName(getRandomName() + getRandomTitle());
		setHealth(3);
		setHitRating(40);
		setDodgeChance(30);
	}
	
	public String getRandomName()//Generates random name for characters
	{
		Random rand = new Random();
		String name = null;
		
		switch (rand.nextInt(4)+1){
		case 1:
			name = "M'iaq"; break;
		case 2: 
			name =  "Lira"; break;
		case 3: 
			name = "Kragus"; break;
		case 4:
			name = "Phenil"; break;
		}
		return name;
	}
	
	public String getRandomTitle(){//generates random title to add to name
		String title = null;
		
		switch (rand.nextInt(6)+1){
		case 1:
			title = " the Weary"; break;
		case 2: 
			title =  " the Wealthy"; break;
		case 3:
			title = " the Lost"; break;
		case 4:
			title = " the Chicken Chaser"; break;
		case 5:
			title = " the Proud"; break;
		case 6:
			title = " the Liar"; break;
		}
		return title;
	}
	
	@Override
	public String getName()//gives full npc name
	{
		return (getRandomName() + getRandomTitle());
	}
	
	public String npcDialogue()//Generates random npc dialogue
	{
		int saying = rand.nextInt(5)+1;
		String string = null;
		switch(saying)
		{
		case 1:
			string = "\"By Azura, by Azura, by Azura! It's the grand champion.\"";
			break;
		case 2:
			string = "\"I was an adventurer like you, then I took an arrow in the knee.\"";
			break;
		case 3:
			string = "\"My cousin's out fighting dragons, and what do I get? Guard duty.\"";
			break;
		case 4:
			string = "\"Let me guess, someone stole your sweetroll?\"";
			break;
		case 5:
			string = "\"I find your wolfish grin... unsettling.\"";
			break;
		}
		return string;
	}
		
}
