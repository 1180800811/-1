package Location;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MultipleLocationEntryImplTest extends MultipleLocationEntryTest{
    
	@Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
	@Override
	public MultipleLocationEntryImpl emptyInstance() {
		return new MultipleLocationEntryImpl() ;
	}
	
	@Test
	public void toStringTest() {
		MultipleLocationEntryImpl loc = emptyInstance();
		Location loc1 = new Location(52.3, 16.6, "正心", true) ;
		Location loc2 = new Location(51.5, 17.8, "格物", true) ;
		List<Location> ls = new ArrayList<Location>() ;
		ls.add(loc1);
		ls.add(loc2);
		loc.setLocations(ls);
		assertEquals("locations=[正心, 格物]",loc.toString());
	}
	
	
	/*
	 * 测试equals方法
	 */
	@Test
	public void equalsTest() {
		MultipleLocationEntryImpl loc1 = emptyInstance();
		List<Location> locations = new ArrayList<Location>();
		Location lo = new Location("s");
		locations.add(lo);
		MultipleLocationEntryImpl loc2 = new MultipleLocationEntryImpl(locations);
		loc1.addLocation(new Location("s"));
		assertFalse(loc2.addLocation(new Location("s")));
		assertTrue(loc1.hashCode() == loc2.hashCode());
		assertTrue(loc1.equals(loc2));
		assertFalse(loc1.equals(null));
		assertFalse(loc1.equals(new Integer(1)));
	}

}
