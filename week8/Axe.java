package week8;

public class Axe extends ToSmash {

	public Axe(int damage, int durability) {
		super(damage, durability);
	}
	
	public String toString() {
		return "Axe: " + damage + " - " + durability;
	}
}
