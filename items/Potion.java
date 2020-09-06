package items;

public class Potion extends Item implements Consumable
{
	
	private int HPBonus;

	public Potion(String name, String description, int bonus)
	{
		super(name, description);
		setHPBonus(bonus);
	}
	
	private void setHPBonus(int bonus)
	{
		HPBonus = bonus;
	}

	@Override
	public int getHPBonus()
	{
		return HPBonus;
	}

}
