package zombies;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Dungeon implements Level{
	private char[][] map = new char[5][12];
	private int heroPositionX;
	private int heroPositionY;
	private String lastMovement = "";
	
	public Dungeon(String fileName) {
		loadLevel(fileName);
	}

	private void loadLevel(String fileName) {
		File file = new File(fileName);
		try {
			Scanner sc = new Scanner(file);
			for (int i = 0; sc.hasNextLine(); i++) {
				map[i] = sc.nextLine().toCharArray();
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
	}

	@Override
	public void printMap() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	@Override
	public void spawn(Hero hero) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if(map[i][j] == 'S') {
					map[i][j] = 'H';
					heroPositionX = j;
					heroPositionY = i;
				}
			}
		}
	}

	@Override
	public void moveHero(String direction) {
		String report = "";
		switch (direction) {
			case "up":
				if(allowedMovement(heroPositionY - 1, heroPositionX)) {
					report += "Hero moved up";
					heroPositionY--;
				} else {
					report += "Hero didn't move";
				}
				break;
			case "down":
				if(allowedMovement(heroPositionY + 1, heroPositionX)) {
					report += "Hero moved down";
					heroPositionY++;
				}else {
					report += "Hero didn't move";
				}
				break;
			case "left":
				if(allowedMovement(heroPositionY, heroPositionX - 1)) {
					report += "Hero moved left";
					heroPositionX--;
				}else {
					report += "Hero didn't move";
				}
				break;
			case "right":
				if(allowedMovement(heroPositionY, heroPositionX + 1)) {
					report += "Hero moved right";
					heroPositionX++;
				}else {
					report += "Hero didn't move";
				}
				break;
			default:
				report += "Hero didn't move";
				break;
		}
		System.out.println(report);
		if(report.contains("Hero didn't move"))
			lastMovement = "";
		else
			lastMovement = direction;
	}

	private boolean allowedMovement(int y, int x) {
		boolean inBounds = x >= 0 && x < map[0].length && y >= 0 && y < map.length;
		boolean canMove;
		if(inBounds) {
			canMove = map[y][x] != '#';
		} else {
			canMove = false;
		}
		return inBounds && canMove;
	}

	@Override
	public int heroAttack(Hero hero) {
		int spellAttackRange;
		int weaponAttackRange;
		if(hero.getCurrentSpell() == null && hero.getCurrentWeapon() == null) {
			spellAttackRange = 1;
			weaponAttackRange = 1;
		} else {
			if(hero.getCurrentSpell() == null) {
				spellAttackRange = 1;
				weaponAttackRange = hero.getCurrentWeapon().getRange();
			} else if(hero.getCurrentWeapon() == null) {
				spellAttackRange = hero.getCurrentSpell().getRange();
				weaponAttackRange = 1;
			} else {
				spellAttackRange = hero.getCurrentSpell().getRange();
				weaponAttackRange = hero.getCurrentWeapon().getRange();
			}
		}
		int finalAttackRange = getAttackRange(spellAttackRange, weaponAttackRange);
		return finalAttackRange;
	}

	private int getAttackRange(int spellAttackRange, int weaponAttackRange) {
		int finalAttackRange = -1;
		
		if(heroPositionY - spellAttackRange >= 0) {
			if(map[heroPositionY - spellAttackRange][heroPositionX] == 'E')
				finalAttackRange = spellAttackRange;
		}
		if(heroPositionY + spellAttackRange < map.length) {
			if(map[heroPositionY + spellAttackRange][heroPositionX] == 'E')
				finalAttackRange = spellAttackRange;
		}
		if(heroPositionY + weaponAttackRange < map.length) {
			if(map[heroPositionY + weaponAttackRange][heroPositionX] == 'E')
				finalAttackRange = weaponAttackRange;
		}
		if(heroPositionY - weaponAttackRange >= 0) {
			if(map[heroPositionY - weaponAttackRange][heroPositionX] == 'E')
				finalAttackRange = weaponAttackRange;
		}
		if(heroPositionX - weaponAttackRange >= 0) {
			if(map[heroPositionY][heroPositionX - weaponAttackRange] == 'E')
				finalAttackRange = weaponAttackRange;
		}
		if(heroPositionX + weaponAttackRange < map[heroPositionY].length) {
			if(map[heroPositionY][heroPositionX + weaponAttackRange] == 'E')
				finalAttackRange = weaponAttackRange;
		}
		if(heroPositionX - spellAttackRange >= 0) {
			if(map[heroPositionY][heroPositionX - spellAttackRange] == 'E')
				finalAttackRange = spellAttackRange;
		}
		if(heroPositionX + spellAttackRange < map[heroPositionY].length) {
			if(map[heroPositionY][heroPositionX + spellAttackRange] == 'E')
				finalAttackRange = spellAttackRange;
		}
		
		return finalAttackRange;
	}

	@Override
	public boolean checkIfHeroOnTreasure() {
		return map[heroPositionY][heroPositionX] == 'T';
	}

	@Override
	public Object generateTreasure() {
		Random random = new Random();
		int max = 4;
		int min = 1;
		int generatedInt = random.nextInt(max - min + 1) + min;
		Object treasureObject;
		
		switch (generatedInt) {
			case 1:
				treasureObject = new Sword("Bastard Sword", 26);
				break;
			case 2:
				treasureObject = new Bow("Short Bow", 20);
				break;
			case 3:
				treasureObject = new Spell("Fire ball", 20, 25);
				break;
			case 4:
				treasureObject = new Integer(25);
				break;
			default:
				treasureObject = null;
				break;
		}
		return treasureObject;
	}

	@Override
	public void updateHeroPosition() {
		switch (lastMovement) {
			case "up":
				map[heroPositionY + 1][heroPositionX] = '.';
				break;
			case "down":
				map[heroPositionY - 1][heroPositionX] = '.';
				break;
			case "left":
				map[heroPositionY][heroPositionX + 1] = '.';
				break;
			case "right":
				map[heroPositionY][heroPositionX - 1] = '.';
				break;
			default:
				break;
		}
		map[heroPositionY][heroPositionX] = 'H';
	}

	@Override
	public void killEnemy(int heroRange) {
		if(heroPositionY - heroRange >= 0) {
			if(map[heroPositionY - heroRange][heroPositionX] == 'E')
				map[heroPositionY - heroRange][heroPositionX] = '.';
		}
		if(heroPositionY + heroRange < map.length) {
			if(map[heroPositionY + heroRange][heroPositionX] == 'E')
				map[heroPositionY + heroRange][heroPositionX] = '.';
		}
		if(heroPositionX - heroRange >= 0) {
			if(map[heroPositionY][heroPositionX - heroRange] == 'E')
				map[heroPositionY][heroPositionX - heroRange] = '.';
		}
		if(heroPositionX + heroRange < map[heroPositionY].length) {
			if(map[heroPositionY][heroPositionX + heroRange] == 'E')
				map[heroPositionY][heroPositionX + heroRange] = '.';
		}
	}
}
