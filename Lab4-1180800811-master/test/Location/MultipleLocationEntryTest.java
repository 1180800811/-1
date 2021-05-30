package Location;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public abstract class MultipleLocationEntryTest {
	
	//Testing strategy for setLocations(List<Location> locs)
	// input : locs == null , locs != null 
	//
	//Testing strategy for getLocations()
	// input : locations != null 
	//
	@Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
	public abstract MultipleLocationEntryImpl emptyInstance() ;
	
	/*
	 *覆盖:locs == null , locs != null ,locations == null , locations != null 
	 */
	@Test
	public void setAndgetLocationsTest() {
		MultipleLocationEntry loc = emptyInstance();
		Location loc1 = new Location(52.3, 16.6, "正心", true) ;
		Location loc2 = new Location(51.5, 17.8, "格物", true) ;
		List<Location> ls = new ArrayList<Location>() ;
		ls.add(loc1);
		ls.add(loc2);
		loc.setLocations(ls);
		assertTrue(loc.getLocation().contains(loc1));
		assertTrue(loc.getLocation().contains(loc2));
		assertTrue(loc.getLocation().size() == 2 ) ;
	}
	
	/*
	 * 测试addLocation方法
	 */
	@Test
	public void addLocationTest() {
		MultipleLocationEntry loc = emptyInstance();
		assertTrue(loc.addLocation(new Location("s")));
		assertTrue(loc.getLocation().contains(new Location("s")));
		assertFalse(loc.addLocation(new Location("s")));
		
	}
}
