package Resource;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
public class MultipleSortedResourceEntryImplTest extends MultipleSortedResourceEntryTest{

	
	// Testing strategy for toString����
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
	 * ����:List<Teacher> r == null , List<Teacher> r != null 
	 */
	@Test
	public void toStringTest() {
		MultipleSortedResourceEntry<Railway> res = emptyInstance() ;
		assertEquals("[r=[]]" , res.toString()) ;
		Railway railway1 = new Railway("���˺�", Type.BUSINESS, 300, 2020);
		res.addResource(railway1);
		assertEquals("[r=[Railway [Number=���˺�, type=BUSINESS, Size=300, Year=2020]]]",res.toString()) ;
		Railway railway2 =  new Railway("����", Type.BUSINESS, 400, 2020);
		res.addResource(railway2);
		assertEquals("[r=[Railway [Number=���˺�, type=BUSINESS, Size=300, Year=2020], Railway [Number=����, type=BUSINESS, Size=400, Year=2020]]]",res.toString());
	}
	

}
