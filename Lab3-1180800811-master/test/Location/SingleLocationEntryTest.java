package Location;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public abstract class SingleLocationEntryTest {
	//Testing strategy for setLocations(Location loc)
	// input : loc == null , loc != null 
	//
	//Testing strategy for getLocations()
	// input : location != null 
	
	@Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
	public abstract SingleLocationEntryImpl emptyInstance() ;
	
	/*
	 * 
	 */
	@Test
	public void setAndgetLocation() {
		SingleLocationEntry loc = emptyInstance();
		Location loc1 = new Location(52.3, 16.6, "ÕýÐÄ", true) ;
		loc.setLocation(loc1);
		assertTrue(loc.getLocation().equals(loc1));
	}
}
