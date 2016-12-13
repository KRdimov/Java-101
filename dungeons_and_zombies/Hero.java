package dungeons_and_zombies;

public class Hero extends Character {
	private String name;
	private String title;
	private int stamina;
	private int maxStamina;
	private int staminaRegenRate;
	private int manaRegenRate;
	
	public Hero(String name, String title, int stamina, int health, 
			int mana, int staminaRegenRate, int manaRegenRate) {
		
		super(health, mana);
		setName(name);
		setTitle(title);
		setStamina(stamina);
		maxStamina = stamina;
		setStaminaRegenRate(staminaRegenRate);
		setManaRegenRate(manaRegenRate);
	}
	
	public void takeMana(int manaPoints) {
		if(mana + manaPoints <= maxMana) {
			mana += manaPoints;
		} else {
			mana = maxMana;
		}
	}

	@Override
	public int attack(String attackType) {
		int damageDone;
		switch (attackType) {
			case "weapon":
				if(stamina < 15) {
					damageDone = 0;
				}
				else {
					stamina -= 15;
					damageDone = currentWeapon.useWeapon();
				}
				break;
			case "spell":
				if(mana < currentSpell.getManaCost()) {
					damageDone =  0;
				} else {
					mana -= currentSpell.getManaCost();
					damageDone = currentSpell.getDamage();
				}
				break;
			default:
				damageDone = 2;
				break;
		}
		return damageDone;
	}
	
	public void regenerateStats() {
		if(stamina + staminaRegenRate > maxStamina)
			stamina = maxStamina;
		else
			stamina += staminaRegenRate;
		
		if(mana + manaRegenRate > maxMana)
			mana = maxMana;
		else
			mana += manaRegenRate;
	}
	
	public String knownAs() {
		return name + " the " + title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getStamina() {
		return stamina;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
	}
	
	public void takeStamina(int staminaPoints) {
		if(stamina + staminaPoints <= maxStamina) {
			stamina += staminaPoints;
		} else {
			stamina = maxStamina;
		}
	}

	public int getStaminaRegenRate() {
		return staminaRegenRate;
	}

	public void setStaminaRegenRate(int staminaRegenRate) {
		this.staminaRegenRate = staminaRegenRate;
	}

	public int getManaRegenRate() {
		return manaRegenRate;
	}

	public void setManaRegenRate(int manaRegenRate) {
		this.manaRegenRate = manaRegenRate;
	}
}
