package week7;

public class Main {

	public static void main(String[] args) {
		testWithExample1();  // expected output met
		testWithExample2(); // expected output met
	}
	
	private static void testWithExample2() {
		PandaSocialNetwork network = new PandaSocialNetwork();
		Panda p1 = new Panda("P1", "p1panda@pandamail.com", "male");
		Panda p2 = new Panda("P2", "p2panda@pandamail.com", "male");
		Panda p3 = new Panda("P3", "p3panda@pandamail.com", "female");
		Panda p4 = new Panda("P4", "p4panda@pandamail.com", "female");
		Panda p5 = new Panda("P5", "p5panda@pandamail.com", "male");
		Panda p6 = new Panda("P6", "p6panda@pandamail.com", "female");
		Panda p7 = new Panda("P7", "p7panda@pandamail.com", "male");
		
		network.addPanda(p1);
		network.addPanda(p2);
		network.addPanda(p3);
		network.addPanda(p4);
		network.addPanda(p5);
		network.addPanda(p6);
		network.addPanda(p7);
		
		network.makeFriends(p1, p2);
		network.makeFriends(p1, p3);
		network.makeFriends(p3, p4);
		network.makeFriends(p3, p5);
		network.makeFriends(p3, p6);
		network.makeFriends(p5, p7);
		network.makeFriends(p4, p7);
		network.makeFriends(p7, p1);
		
		boolean b1 = network.connectionLevel(p1, p7) == 1; 
		boolean b2 = network.connectionLevel(p1, p4) == 2 ; 
		boolean b3 = network.connectionLevel(p2, p4) == 3 ; 
		boolean b4 = network.howManyGenderInNetwork(1, p1, "female") == 1;
		boolean b5 = network.howManyGenderInNetwork(2, p3, "male") == 4;
		boolean b6 = network.howManyGenderInNetwork(2, p1, "female") == 3;
		
		System.out.println(b1);
		System.out.println(b2);
		System.out.println(b3);
		System.out.println(b4);
		System.out.println(b5);
		System.out.println(b6);
	}

	public static void testWithExample1() {
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
