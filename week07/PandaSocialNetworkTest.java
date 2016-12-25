package week07;

import static org.junit.Assert.*;

import org.junit.Test;

public class PandaSocialNetworkTest {

	
	
	@Test
	public void testCreatingNewPanda() {
		Panda p = new Panda("Panda", "panda@pandamail.com", "male");
		assertEquals("Panda", p.getName());
		assertEquals("panda@pandamail.com", p.getEmail());
		assertEquals("male", p.getGender());
//		fail("Not yet implemented");
	}

}
