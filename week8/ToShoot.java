package week8;

public class ToShoot extends Weapon {
	int damage;
	int durability;
	
	public ToShoot(int damage, int durability) {
		super(damage, durability);
	}
	
	public int hit() {
		if (durability > 0) {
			durability--;
			return damage;
		}
		return 1;
	}
}
