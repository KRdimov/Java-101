package dungeons_and_zombies;

public class Enemy extends Character {
	private int damage;

	public Enemy(int health, int mana, int damage) {
		super(health, mana);
		setDamage(damage);
	}

	@Override
	public int attack(String attackType) {
		int damageDone;
		switch (attackType) {
			case "weapon":
				damageDone = currentWeapon.getDamage() / 2 + damage;
				break;
			case "spell":
				damageDone =  currentSpell.getDamage();
				break;
			case "default":
				damageDone = damage;
				break;
			default:
				damageDone = 0;
				break;
		}
		return damageDone;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
}
