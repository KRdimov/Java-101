package week9;

public class Main {

	public static void main(String[] args) {
		
		MySQLHelper db = new MySQLHelper("localhost/TESTINGSQL", "usr-name-goes-here", "pass-goes-here");
		System.out.println(db.selectFrom("client"));
	}
}
