package dungeons_and_zombies;

public interface Level {
	public void printMap();
	
	public void spawn(Hero hero);
	
	public void moveHero(String direction);
	
	public int heroAttack(Hero hero);
	
	public boolean checkIfHeroOnTreasure();
	
	public Object generateTreasure();
	
	public void updateHeroPosition();
	
	public void killEnemy(int heroRange);
}
