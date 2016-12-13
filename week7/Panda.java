package week7;

import java.util.HashSet;
import java.util.Set;

public class Panda {
	private String name;
	private String email;
	private String gender;
	private boolean isMale;
	private boolean isFemale;
	private Set<Panda> friends;
	
	public Panda(String name, String email, String gender) {
		if(!setEmail(email))
			return;
		setName(name);
		setGender(gender);
		friends = new HashSet<Panda>();
	}

	// hashCode and equals generated by Eclipse
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Panda other = (Panda) obj;
		if(this.hashCode() == other.hashCode()) {
			if (email == null) {
				if (other.email != null)
					return false;
			} else if (!email.equals(other.email))
				return false;
			if (gender == null) {
				if (other.gender != null)
					return false;
			} else if (!gender.equals(other.gender))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}
		return false;
	}

	public String getName() {
		return name;
	}
	
	public void addFriend(Panda panda) {
		friends.add(panda);
	}
	
	public boolean hasFriend(Panda panda) {
		return friends.contains(panda);
	}
	
	public Set<Panda> getFriends(){
		return friends;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public boolean setEmail(String email) {
		if(email.contains("@") && email.length() > 5 && email.contains(".")) {
			int index = email.indexOf("@");
			if(email.substring(index, email.length()).contains(".")){
				this.email = email;
				return true;
			} else
				return false;
		}
		else {
			System.out.println("Invalid email!");
			return false;
		}
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
		if(gender == "male") {
			setMale(true);
			setFemale(false);
		} else if(gender == "female") {
			setMale(false);
			setFemale(true);
		} else {
			setMale(false);
			setFemale(false);
		}
	}

	public boolean isMale() {
		return isMale;
	}

	public void setMale(boolean isMale) {
		this.isMale = isMale;
	}

	public boolean isFemale() {
		return isFemale;
	}

	public void setFemale(boolean isFemale) {
		this.isFemale = isFemale;
	}

	@Override
	public String toString() {
		return "Panda [name= " + name + ", email = " + email + ", gender = " + gender + "]";
	}
}
