package week8;

public abstract class Weapon {
	int damage;
	int durability;
	
	public Weapon(int damage, int durability) {
		this.damage = damage;
		this.durability = durability;
	}
	
	public abstract int hit();
}
