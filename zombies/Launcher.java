package zombies;

import java.util.Scanner;

public class Launcher {
	public static void main(String[] args) {
		Level level = new Dungeon("/home/milush/Coding/Java/HackCourse/HackEclipse/src/zombies/level1");
		Scanner sc = new Scanner(System.in);
		Hero hero = new Hero("Milush", "Coder", 100, 100, 100, 5, 5);
		//hero.equip(new Sword("Bytesword", 20));
		hero.learn(new Spell("Ice Ball", 34, 34));
		level.spawn(hero);
		System.out.println("A hero steps into this dungeon. They call him " + 
				hero.getName() + " the " + hero.getTitle() + ". Will he be able to fight his way out?");
		
		while(hero.isAlive()) {
			level.printMap();
			hero.regenerateStats();
			int heroRangeAttack = level.heroAttack(hero);
			if(heroRangeAttack != -1) {
				Fight battle = new Fight(hero, new Enemy(100, 100, 15));
				String fightReport = battle.fight(heroRangeAttack);
				System.out.println(fightReport);
				if(hero.isAlive()) {
					level.killEnemy(heroRangeAttack);
					level.printMap();
				}
			}
			if(hero.isAlive())
				level.moveHero(sc.nextLine());
			if(hero.isAlive() && level.checkIfHeroOnTreasure()) {
				takeTreasure(hero, level.generateTreasure());
			}
			level.updateHeroPosition();
		}
		
	}
	
	public static void takeTreasure(Hero hero, Object treasure) {
		String report = "Hero got a treasure!\n";
		if(treasure instanceof Sword) {
			Sword sword = (Sword) treasure;
			hero.equip(sword);
			report += "Hero equiped a new Sword called " + sword.getName() + ". It deals " + sword.getDamage() + " damage.";
		} else if(treasure instanceof Bow) {
			Bow bow = (Bow) treasure;
			hero.equip(bow);
			report += "Hero equiped a new Bow called " + bow.getName() + ". It deals " + bow.getDamage() + " damage.";
		} else if(treasure instanceof Spell) {
			Spell spell = (Spell) treasure;
			hero.learn(spell);
			report += "Hero learned a new Spell called " + spell.getName() + ". It deals " + spell.getDamage() + " damage.";
		} else {
			Integer healPoints = (Integer) treasure;
			hero.takeHealing(healPoints);
			report += "Hero got healed for " + healPoints + " health points. Hero health now is " + hero.getHealth();
		}
		System.out.println(report);
	}
}


