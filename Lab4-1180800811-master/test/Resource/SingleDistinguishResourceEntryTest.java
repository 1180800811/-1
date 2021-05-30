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
	 *����:r == null, r != null
	 */
	@Test
	public void setAndgetResourceTest() {
		SingleDistinguishResourceEntry<Teacher> res = emptyInstance() ;
		Teacher r = new Teacher("656864513265", "�����", true, "����");
		res.setResource(r);//����setResource����:r != null 
		assertTrue(res.getResource().equals(r)) ;//����getResource:r != null 
		res.setResource(null);//����setResource����:r == null
		assertTrue(res.getResource() == null ) ;//����getResource:r == null
	}
	

}

