package Resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
public class MultipleSortedResourceEntryImplTest extends MultipleSortedResourceEntryTest{

	
	// Testing strategy for toString∑Ω∑®
	//input : List<Teacher> r == null , List<Teacher> r != null 
	//
	@Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
	
	@Override
	public MultipleSortedResourceEntry<Railway> emptyInstance() {
		return new  MultipleSortedResourceEntryImpl<Railway>() ;
	}
	
	/*
	 * ∏≤∏«:List<Teacher> r == null , List<Teacher> r != null 
	 */
	@Test
	public void toStringTest() {
		MultipleSortedResourceEntry<Railway> res = emptyInstance() ;
		assertEquals("[r=[]]" , res.toString()) ;
		Railway railway1 = new Railway("∏¥–À∫≈", Type.BUSINESS, 300, 2020);
		res.addResource(railway1);
		assertEquals("[r=[Railway [Number=∏¥–À∫≈, type=BUSINESS, Size=300, Year=2020]]]",res.toString()) ;
		Railway railway2 =  new Railway("≥Â∑Ê∫≈", Type.BUSINESS, 400, 2020);
		res.addResource(railway2);
		assertEquals("[r=[Railway [Number=∏¥–À∫≈, type=BUSINESS, Size=300, Year=2020], Railway [Number=≥Â∑Ê∫≈, type=BUSINESS, Size=400, Year=2020]]]",res.toString());
	}
	
	/*
	 * ≤‚ ‘Equals∑Ω∑®∫ÕhashCode∑Ω∑®
	 */
	@Test
	public void EqualsTest() {
		List<Railway> r = new ArrayList<>();
		Railway railway1 = new Railway("∏¥–À∫≈", Type.BUSINESS, 300, 2020);
		r.add(railway1);
		MultipleSortedResourceEntryImpl<Railway> tt1 = new MultipleSortedResourceEntryImpl<Railway>(r);
		MultipleSortedResourceEntryImpl<Railway> tt2 = new MultipleSortedResourceEntryImpl<Railway>(r);
		assertTrue(tt1.equals(tt2));
		assertTrue(tt1.hashCode() == tt2.hashCode()) ;
		assertFalse(tt1.equals(null));
	}

}
