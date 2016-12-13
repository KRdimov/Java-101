package week8;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int zombieCount = input.nextInt();
		int zombiesHealth = input.nextInt();
		int counter = 0;
		
		HashMap<String, Weapon> weapons = new HashMap<>();
		weapons.put("axe", new Axe(10, 6));
		weapons.put("shotgun", new Shotgun(25, 10));
		weapons.put("sword", new AncientSword(12, 8));
		weapons.put("revolver", new Revolver(15, 6));
		
		Weapon weapon = weapons.get(input.next());
		
		for (int i = 0; i < zombieCount; i++) {
			int oneZombieHealth = zombiesHealth;
			while (oneZombieHealth > 0) {
				oneZombieHealth-= weapon.hit();
				counter++;
			}
		}
		
		System.out.println(counter);
	}

}
