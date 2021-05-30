package Resource;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public abstract class MultipleSortedResourceEntryTest {

	
	//Testing strategy for setResource(List<R> r )
	// input : r == null , r != null 
	//
	//	Testing strategy for addResource(R resource  ) ;
	// input : resource == null , resource != null
	//  		r == null , r != null 
	//Testing strategy for deleteResource(R resource  ) ;
	//input : resource == null , resource != null and resource is not contained in List<R>
	//			resource != null and resource is contained in List<R>	
	//Testing strategy for getResource()
	//input : List<R> r == null ,List<R> r != null
	//
	@Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
	
	public abstract MultipleSortedResourceEntry<Railway> emptyInstance();
	/*
	 * ¸²¸Ç: r == null , r != null 
	 */
	@Test
	public void setResourceTest() {
		MultipleSortedResourceEntry<Railway> res = emptyInstance() ;
		Railway railway  = new Railway("¸´ÐËºÅ", Type.BUSINESS, 300, 2020);
		List<Railway> tea = new ArrayList<Railway>() ;
		tea.add(railway );
		res.setResource(tea);//¸²¸Ç: r != null
		assertTrue(res.getResource().contains(railway ));
	}
	
	/*
	 * ¸²¸Ç: resource == null , resource != null,r == null , r != null 
	 */
	@Test
	public void addResourceTest() {
		MultipleSortedResourceEntry<Railway > res = emptyInstance() ;
		Railway railway = new Railway("¸´ÐËºÅ", Type.BUSINESS, 300, 2020);
		res.addResource(railway); //¸²¸Ç:resource != null,r==null
		assertTrue(res.getResource().contains(railway));
		assertTrue(res.getResource().size() == 1) ;
		assertTrue(res.getResource().contains(railway));
		assertTrue(res.getResource().size() == 1) ;
	}
	
	/*
	 * ¸²¸Ç: resource == null , resource != null and resource is not contained in List<R>
	 *		resource != null and resource is contained in List<R> 
	 */
	@Test
	public void deleteResourceTest() {
		MultipleSortedResourceEntry<Railway> res = emptyInstance() ;
		Railway railway1 = new Railway("¸´ÐËºÅ", Type.BUSINESS, 300, 2020);
		res.addResource(railway1);
		assertTrue(res.getResource().size() == 1 ) ;
		Railway railway2 = new Railway("³å·æºÅ", Type.BUSINESS, 400, 2020);
		res.deleteResource(railway2);//¸²¸Ç:resource != null and resource is not contained in List<R>
		assertTrue(res.getResource().contains(railway1));
		assertTrue(res.getResource().size() == 1 ) ;
		res.deleteResource(railway1);//¸²¸Ç:resource != null and resource is contained in List<R>
		assertFalse(res.getResource().contains(railway1));
		assertTrue(res.getResource().size() == 0 ) ;
	}
	/*
	 * ¸²¸Ç:  List<R> r == null ,List<R> r != null
	 */
	@Test
	public void getResourceTest() {
		MultipleSortedResourceEntry<Railway> res = emptyInstance() ;
		List<Railway> list = res.getResource() ;
		assertTrue(list.size() ==  0);//List<R> r == null
		Railway railway =new Railway("¸´ÐËºÅ", Type.BUSINESS, 300, 2020);
		List<Railway> tea = new ArrayList<Railway>() ;
		tea.add( railway);
		res.setResource( tea);
		list =  res.getResource() ;
		assertTrue(list.equals(tea));
	}
	
}
