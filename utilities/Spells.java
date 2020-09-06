package utilities;

public enum Spells {
	FireBall("Fireball", 6),
	ConeOfCold("Cone of Cold", 9),
	RockSpire("Rock Spire", 13),
	WindBlade("Wind Blade", 56);
	
	public final String name;
	public final int power;

	
	Spells(String n, int i){
		name=n;
		power=i;
	}
	
	public String getSpellName(){
		return name;
	}
	
	public int getSpellPower(){
		return power;
	}
	
}
