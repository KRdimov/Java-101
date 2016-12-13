package week7;

public class Main {

	public static void main(String[] args) {
		PandaSocialNetwork network = new PandaSocialNetwork();
		Panda ivo = new Panda("Ivo", "ivo@pandamail.com", "male");
		Panda rado = new Panda("Rado", "rado@pandamail.com", "male");
		Panda tony = new Panda("Tony", "tony@pandamail.com", "female");

		network.addPanda(ivo);
		network.addPanda(rado);
		network.addPanda(tony);

		network.makeFriends(ivo, rado);
		network.makeFriends(rado, tony);

		boolean b1 = network.connectionLevel(ivo, rado) == 1; //true
		boolean b2 = network.connectionLevel(ivo, tony) == 2 ; // true
		boolean b3 = network.howManyGenderInNetwork(1, rado, "female") == 1; //true
		
		System.out.println(b1);
		System.out.println(b2);
		System.out.println(b3);
	}

}
