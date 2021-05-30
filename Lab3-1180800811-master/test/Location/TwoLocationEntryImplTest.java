package Location;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TwoLocationEntryImplTest extends TwoLocationEntryTest{
    
	@Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
	@Override
	public TwoLocationEntryImpl emptyInstance() {
			return new TwoLocationEntryImpl() ;
	}
	
	@Test
	public void toStringTest() {
		TwoLocationEntryImpl loc = emptyInstance();
		Location loc1 = new Location(52.3, 16.6, "��������", true) ;
		Location loc2 = new Location(51.5, 17.8, "����", true) ;
		loc.setLocations(loc1, loc2);
		String s1 = "[start=��������, end=����]";
		assertEquals(s1,loc.toString()) ;
	}

}
