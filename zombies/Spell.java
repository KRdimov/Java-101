package zombies;

public class Spell {
	private String name;
	private int manaCost;
	private int damage;
	private int range;
	
	public Spell(String name,  int damage, int spellCost) {
		setName(name);
		setManaCost(spellCost);
		setDamage(damage);
		setRange(2);
	}

	public int getManaCost() {
		return manaCost;
	}

	public void setManaCost(int spellCost) {
		this.manaCost = spellCost;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
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
