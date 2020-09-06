package items;

public class Armor extends Item implements Equippable
{
	private int armorBonus;
	
	public Armor(String name, String description, int bonus)
	{
		super(name, description);
		setDefBonus(bonus);
	}

	private void setDefBonus(int bonus)
	{
		armorBonus = bonus;	
	}

	@Override
	public int getAttBonus()
	{
		return 0;
	}

	@Override
	public int getDefBonus()
	{
		return armorBonus;
	}	


}
