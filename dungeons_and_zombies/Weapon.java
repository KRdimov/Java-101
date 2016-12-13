package dungeons_and_zombies;

public class Weapon {
	private String name;
	protected int damage;
	protected int durability;
	protected int range;
	
	public Weapon(String name, int damage, int durability, int range) {
		setName(name);
		setDamage(damage);
		setDurability(durability);
		setRange(range);
	}
	
	public int useWeapon() {
		if(durability > 0) {
			durability--;
			if(durability == 0) {
				System.out.print("Oh no! Hero weapon became barely usable due to low durability! Damn these Chinese weapons...");
			}
			return damage;
		} else {
			setRange(1);
			return 2;
		}
	}
	
	public int getDamage() {
		if(durability > 0) {
			return damage;
		} else {
			return 5;
		}
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public int getDurability() {
		return durability;
	}
	public void setDurability(int durability) {
		this.durability = durability;
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
