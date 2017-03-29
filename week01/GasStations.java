import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

class GasStations {
	public static ArrayList<Integer> getGasStations(int tripDistance, int tankSize, ArrayList<Integer> gasStations) {
		Integer lastPossibleGasSt = new Integer(0);
		int idLast = 0;
		ArrayList<Integer> tripGasStations = new ArrayList<>();
		int initialTankSize = tankSize;
		while(tripDistance > tankSize) {
			for(int i = idLast; i < gasStations.size();i++) {
				if(gasStations.get(i) <= tankSize) {
					lastPossibleGasSt = gasStations.get(i);
					idLast = i;
				}
				else 
					break;
			}
			
			if(!lastPossibleGasSt.equals(0)) {
				tripGasStations.add(lastPossibleGasSt);
				tankSize = initialTankSize + lastPossibleGasSt;
				idLast++;
				lastPossibleGasSt = new Integer(0);
			}
		}
		return tripGasStations;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int tripDistance = scanner.nextInt();
		int tankSize = scanner.nextInt();

		int gasStationsCount = scanner.nextInt();
		ArrayList<Integer> gasStations = new ArrayList<Integer>();

		for (int i = 0; i < gasStationsCount; i++) {
		  gasStations.add(scanner.nextInt());
		}

		ArrayList<Integer> result = getGasStations(tripDistance, tankSize, gasStations);
		System.out.println("----------------------");
		for (int i = 0; i < result.size(); i++) {
		    System.out.println(result.get(i));
		}
	}
}