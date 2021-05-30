package Location;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public abstract class TwoLocationEntryTest {
	
	@Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
	public abstract TwoLocationEntryImpl emptyInstance() ;
	
	/*
	 * 覆盖:
	 */
	@Test
	public void getAndsetLocationsTest() {
		TwoLocationEntry locs = emptyInstance() ;
		Location loc1 = new Location(52.3, 16.6, "正心", true) ;
		Location loc2 = new Location(51.5, 17.8, "格物", true) ;
		locs.setLocations(loc1, loc2);
		assertTrue(locs.getStartLocation().equals(loc1)) ;
		assertTrue(locs.getEndLocation().equals(loc2)) ;
	}
}
