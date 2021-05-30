package Location;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SingleLocationEntryImplTest extends  SingleLocationEntryTest{
	@Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

	@Override
	public SingleLocationEntryImpl emptyInstance() {
		return new SingleLocationEntryImpl();
	}
	
	@Test
	public void toStringTest() {
		SingleLocationEntryImpl loc = emptyInstance();
		Location loc1 = new Location(52.3, 16.6, "����", true) ;
		loc.setLocation(loc1);
		String s = "location=����" ;
		assertEquals(s , loc.toString()) ;
	}
	
	@Test
	public void EqualsTest() {
		Location loc1 = new Location(52, 16, "����", false) ;
		SingleLocationEntryImpl loc  = new SingleLocationEntryImpl(loc1);
		Location locc1 = new Location(52.3, 16.6, "����", true) ;
		SingleLocationEntryImpl locc = new SingleLocationEntryImpl(locc1);
		assertTrue(loc.equals(locc));
		assertTrue(loc1.equals(locc1));
	}
}
