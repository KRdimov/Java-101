package week7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

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
	
	public int connectionLevelNew(Panda panda1, Panda panda2) {
		if(panda1.equals(panda2)){
			return 0;
		}

		int depth = 1;
		int elementsToDepthIncrease = 0;
		Queue<Panda> pandaQueue = new LinkedList<>();
		boolean targetReached = false;
		pandaQueue.add(panda1);
		List<Integer> depths = new ArrayList<>();
		Set<Panda> alreadyChecked = new HashSet<>();
		alreadyChecked.add(panda1);
		
		while(!pandaQueue.isEmpty()) {
			Panda p = pandaQueue.poll();
			if(elementsToDepthIncrease == 0) {
				elementsToDepthIncrease += p.getFriends().size();
				depth++;
			}
			for (Panda friend : p.getFriends()) {
				if(!alreadyChecked.contains(friend)) {
					if(panda2.equals(friend)) {
						targetReached = true;
					}
					pandaQueue.add(friend);
					alreadyChecked.add(friend);
				}
			}
			if(targetReached) {
				depths.add(depth);
				targetReached = false;
			}
			elementsToDepthIncrease--;
		}
		return Collections.min(depths);
	}

	@Override
	public boolean areConnected(Panda panda1, Panda panda2) {
		return connectionLevel(panda1, panda2) != -1 ? true : false;
	}

	@Override
	public int howManyGenderInNetwork(int level, Panda panda, String gender) {
		int count = 0;
		int currentLevel = 0;
		//Panda p1 = goToLevel(panda, level);
		Queue<Panda> pandaQueue = new LinkedList<>();
		pandaQueue.add(panda);
		Set<Panda> alreadyChecked = new HashSet<>();
		while(!pandaQueue.isEmpty()) {
			currentLevel++;
			Panda p = pandaQueue.poll();
			for (Panda f : p.getFriends()) {
				if(f.getGender() == gender) {
					count++;
				}
				if(!alreadyChecked.contains(f)) {
					pandaQueue.add(f);
					alreadyChecked.add(f);
				}
			}
			if(currentLevel == level)
				break;
		}
		return count;
	}

	@Override
	public void save(String fileName) {
		//TODO
	}

	@Override
	public void load(String fileName) {
		//TODO
	}
}
