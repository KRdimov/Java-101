package week7;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class PandaSocialNetwork implements IPandaSocialNetwork {
	private List<Panda> pandaNetwork;
	
	public PandaSocialNetwork() {
		pandaNetwork = new ArrayList<>();
	}
	
	@Override
	public void addPanda(Panda panda) {
		pandaNetwork.add(panda);
	}

	@Override
	public boolean hasPanda(Panda panda) {
		return pandaNetwork.contains(panda);
	}

	@Override
	public void makeFriends(Panda panda1, Panda panda2) {
		if(!pandaNetwork.contains(panda1))
			pandaNetwork.add(panda1);
		if(!pandaNetwork.contains(panda2))
			pandaNetwork.add(panda2);
		panda1.addFriend(panda2);
		panda2.addFriend(panda1);
	}

	@Override
	public boolean areFriends(Panda panda1, Panda panda2) {
		return panda1.hasFriend(panda2);
	}

	@Override
	public List<Panda> friendsOf(Panda panda) {
		return new ArrayList<Panda>(panda.getFriends());
	}

	@Override
	public int connectionLevel(Panda panda1, Panda panda2) {
		int level = 0;
		boolean connectionFound = false;
		Queue<Panda> pandaQueue = new LinkedList<>();
		pandaQueue.add(panda1);
		Set<Panda> alreadyChecked = new HashSet<>();
		while(!pandaQueue.isEmpty()) {
			level++;
			Panda p1 = pandaQueue.poll();
			alreadyChecked.add(p1);
			for (Panda p : p1.getFriends()) {
				if(p.equals(panda2)) {
					connectionFound = true;
					break;
				}
				if(!alreadyChecked.contains(p))
					pandaQueue.add(p);
			}
			if(connectionFound)
				break;
		}
		return connectionFound ? level : -1;
	}
	
	private Panda goToLevel(Panda panda, int level) {
		int reachedLevel = 0;
		Stack<Panda> pandaStack = new Stack<>();
		pandaStack.push(panda);
		Panda p1 = null;
		while(!pandaStack.isEmpty()) {
			p1 = pandaStack.pop();
			reachedLevel++;
			if(reachedLevel == level) 
				break;
			else
				pandaStack.push(p1.getFriends().iterator().next());
			
		}
		return p1;
	}

	@Override
	public boolean areConnected(Panda panda1, Panda panda2) {
		return connectionLevel(panda1, panda2) != -1 ? true : false;
	}

	@Override
	public int howManyGenderInNetwork(int level, Panda panda, String gender) {
		int count = 0;
		Panda p1 = goToLevel(panda, level);
		Queue<Panda> pandaQueue = new LinkedList<>();
		pandaQueue.add(p1);
		Set<Panda> alreadyChecked = new HashSet<>();
		while(!pandaQueue.isEmpty()) {
			Panda p = pandaQueue.poll();
			for (Panda f : p.getFriends()) {
				alreadyChecked.add(f);
				if(f.getGender() == gender) {
					count++;
				}
				if(!alreadyChecked.contains(f))
					pandaQueue.add(f);
			}
		}
		return count;
	}

	//TODO Frog Game!!
	@Override
	public void save(String fileName) {
		//TODO
	}

	@Override
	public void load(String fileName) {
		//TODO
	}
}
