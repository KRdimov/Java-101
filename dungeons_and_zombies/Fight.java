package dungeons_and_zombies;

public class Fight {
	private Hero hero;
	private Enemy enemy;
	
	public Fight(Hero hero, Enemy enemy) {
		setCharacterOne(hero);
		setCharacterTwo(enemy);
	}
	
	public String fight(int rangeStartedBattle) {
		String report = "Hero and enemy have engaged in an epic battle! Hero has " + hero.getHealth() + " and enemy has " + enemy.getHealth() + " health.\n";
		while(hero.isAlive() && enemy.isAlive()) {
			int heroSpellDamage = hero.getCurrentSpell() == null ? 0 : hero.getCurrentSpell().getDamage();
			int heroWeaponDamage = hero.getCurrentWeapon() == null ? 0 : hero.getCurrentWeapon().getDamage();
			String biggestDamageAttackHero = getBiggestDamage(heroSpellDamage, heroWeaponDamage, 2);
			String biggestDamageAttackEnemy;
			if(rangeStartedBattle > 1) {
				biggestDamageAttackEnemy = "no damage, enemy too far away from hero";
				rangeStartedBattle--;
			} else {
				biggestDamageAttackEnemy = getBiggestDamage(0, 0, enemy.getDamage());
			}
			
			int heroDamage = hero.attack(biggestDamageAttackHero);
			enemy.takeDamage(heroDamage);
			report += "Hero attacked with " + biggestDamageAttackHero + " for " + heroDamage + " health damage. Enemy is at " + enemy.getHealth() + " health.\n";
			if(!enemy.isAlive()) {
				report += "Enemy is dead! Hero won this battle and is at " + hero.getHealth() + " health.\n";
				break;
			}
			hero.regenerateStats();
			int enemyDamage = enemy.attack(biggestDamageAttackEnemy);
			hero.takeDamage(enemyDamage);
			if(rangeStartedBattle > 1) {
				report += "Enemy is too far away to do any damage. Enemy move 1 step closer to hero.\n";
			} else {
				report += "Enemy attacked with " + biggestDamageAttackEnemy + " for " + enemyDamage + " health damage. "
						+ "Hero is at " + hero.getHealth() + " health, " + hero.getStamina() + " stamina and " + hero.getMana() + " mana.\n";
			}
			if(!hero.isAlive()) {
				report += "Hero is dead! Enemy won this battle and is at " + enemy.getHealth() + " health.\n";
			}
		}
		return report;
	}

	private String getBiggestDamage(int damageSpell, int damageWeapon, int damageDefault) {
		String attackType;
		int biggest = Math.max(damageSpell, Math.max(damageWeapon, damageDefault));
		
		if(biggest == damageSpell) {
			attackType = "spell";
		} else if(biggest == damageWeapon) {
			attackType = "weapon";
		} else {
			attackType = "default";
		}
		return attackType;
	}

	public Character getCharacterOne() {
		return hero;
	}

	public void setCharacterOne(Hero hero) {
		this.hero = hero;
	}

	public Character getCharacterTwo() {
		return enemy;
	}

	public void setCharacterTwo(Enemy enemy) {
		this.enemy = enemy;
	}
}
