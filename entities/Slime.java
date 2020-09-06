package entities;

import java.util.Random;

public class Slime extends Monster {

	public Slime(){
		setName("Slime");
		setHealth(2);
		setHitRating(30);
		setDodgeChance(25);
		setDescription(getRandomDescription());
	}
	
	public String getRandomDescription(){//produces random description for slime
		Random rand = new Random();
		String description = null;
		
		switch (rand.nextInt(6)+1){
		case 1:
			description = "A blue slime"; break;
		case 2:
			description =  "A green slime"; break;
		case 3:
			description =  "A red slime"; break;
		case 4:
			description =  "A shiny blue slime!"; break;
		case 5:
			description = "A shiny red slime!"; break;
		case 6:
			description = "A shiny green slime!"; break;
		}
		return description;
	}
}
