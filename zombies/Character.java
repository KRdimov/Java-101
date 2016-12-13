package zombies;

public abstract class Character {
	protected Weapon currentWeapon;
	protected Spell currentSpell;
	protected int health;
	protected int maxHealth;
	protected int mana;
	protected int maxMana;
	
	public Character(int health, int mana) {
		setHealth(health);
		maxHealth = health;
		setMana(mana);
		maxMana = mana;
	}
	
	public void takeDamage(int damage) {
		if(health >= damage){
			health -= damage;
		} else {
			health = 0;
		}
	}
	
	public void takeHealing(int healingPoints) {
		if(health + healingPoints <= maxHealth) {
			health += healingPoints;
		} else {
			health = maxHealth;
		}
	}
	
	public void equip(Weapon weapon) {
		currentWeapon = weapon;
	}
	
	public void learn(Spell spell) {
		currentSpell = spell;
	}
	
	public abstract int attack(String attackType);
	
	public boolean isAlive() {
		return health > 0;
	}
	
	public boolean canCast() {
		return currentSpell.getManaCost() > mana;
	}
	
	public Weapon getCurrentWeapon() {
		return currentWeapon;
	}
	
	public Spell getCurrentSpell() {
		return currentSpell;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}
}
