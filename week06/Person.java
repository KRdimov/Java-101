package week06;

public class Person {
	private String firstName;
	private final String SECONDNAME;
	
	public Person(String firstName, String secondName) {
		this.firstName = firstName;
		this.SECONDNAME = secondName;
	}
	
	public String getFirstName(){
		if(firstName == null)
			firstName = "Ivo";
		return firstName;
	}
	
	@Override
	public int hashCode() {
		return SECONDNAME.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean areEqual = false;;
		if(obj == null)
			areEqual = false;
		else if(this == obj)
			areEqual = true;
		else if(this.hashCode() == obj.hashCode()) {
			//TODO
		}
		return areEqual;
	}
}
