package P3;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {
	//Testing strategy
	//partition for Player.getName
	//	input : name
	
	/**
	 * ≤‚ ‘getName∑Ω∑®
	 */
	@Test
	public void testgetName() {
		Player player = new Player("a") ;
		assertEquals("a", player.getName());
	}
}
