package week8;

public class AncientSword extends ToSmash {

	public AncientSword(int damage, int durability) {
		super(damage, durability);
	}
	
	public String toString() {
		return "Sword: " + damage + " - " + durability;
	}
}
