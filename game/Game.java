package game;

/*
 * by Jonathan Lee and Mark Hawes
 * Version too high to count
 */

public class Game extends Commands
{
	//executes the game
	public void playGame()
	{
		while (!event.isComplete() && player.getHealth() > 0)
		{
			event.getEvent(player);
			if (!event.isComplete() && player.getHealth() > 0)
				chooseCommand();
		}
		if(event.isComplete() == true)
		System.out.println("Congratulations! Thank you for playing!");
	}
	
	//code left over from MUD
//	public void initialStats(){
//		System.out.println("Name: " + player.getName() + "\nDescription: " + player.getDescription() + "\nHealth: "
//				+ player.getHealth() + "\nHit Rating: " + player.getHitRating() + "\nDodge Chance: "
//				+ player.getDodgeChance());
//		System.out.println();
//	}
	
	//adds starting items to inventory
	public void addStartItems(){
		player.backpack.addConsumableToInventory(healthPotion);
		player.backpack.addEquippableToInventory(ironSword);
		player.backpack.addEquippableToInventory(leatherArmor);
	}
	
}