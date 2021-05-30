package Resource;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public abstract class SingleDistinguishResourceEntryTest {

	//Testing strategy for setResource(R r)
	// input : r == null, r != null
	//
	//Testing strategy for getResource(R r)
	// input : r == null, r != null
	
	@Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
	
	public abstract SingleDistinguishResourceEntry<Teacher> emptyInstance() ;
	/*
	 *¸²¸Ç:r == null, r != null
	 */
	@Test
	public void setAndgetResourceTest() {
		SingleDistinguishResourceEntry<Teacher> res = emptyInstance() ;
		Teacher r = new Teacher("656864513265", "ÕÅÈðºÀ", true, "±¾¿Æ");
		res.setResource(r);//¸²¸ÇsetResource·½·¨:r != null 
		assertTrue(res.getResource().equals(r)) ;//¸²¸ÇgetResource:r != null 
		res.setResource(null);//¸²¸ÇsetResource·½·¨:r == null
		assertTrue(res.getResource() == null ) ;//¸²¸ÇgetResource:r == null
	}
	

}

