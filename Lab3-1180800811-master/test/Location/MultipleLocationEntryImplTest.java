package Location;

import static org.junit.Assert.assertEquals;

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
		Location loc1 = new Location(52.3, 16.6, "����", true) ;
		Location loc2 = new Location(51.5, 17.8, "����", true) ;
		List<Location> ls = new ArrayList<Location>() ;
		ls.add(loc1);
		ls.add(loc2);
		loc.setLocations(ls);
		assertEquals("locations=[����, ����]",loc.toString());
	}

}
