package week07;

import java.util.List;

public interface IPandaSocialNetwork {
	public void addPanda(Panda panda);
	
	public boolean hasPanda(Panda panda);
	
	public void makeFriends(Panda panda1, Panda panda2);
	
	public boolean areFriends(Panda panda1, Panda panda2);
	
	public List<Panda> friendsOf(Panda panda);
	
	public int connectionLevel(Panda panda1, Panda panda2);
	
	public boolean areConnected(Panda panda1, Panda panda2);
	
	public int howManyGenderInNetwork(int level, Panda panda, String gender);
	
	public void save(String fileName);
	
	public void load(String fileName);
}
