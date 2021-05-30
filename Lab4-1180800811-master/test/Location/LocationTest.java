package Location;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;



public class LocationTest {
	//Testing Strategy
	//只需要测试Location类的setter方法和getter方法以及equals、HashCode和toString方法即可
	//
	@Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
	
	/*
	 * 覆盖:Location类的setter方法和getter方法
	 */
	@Test
	public void Location1Test() {
		Location location = new Location(31.1, 163.2, "正心楼", true) ;
		assertTrue(location.getLongitude() == 31.1) ;//覆盖:Location.getLongitude方法
		assertTrue(location.getLatitude() == 163.2) ;//覆盖:Location.getLatitude方法
		assertTrue(location.getName().equals("正心楼")) ;//覆盖:Location.getName方法
		assertTrue(location.isShare() == true) ;//覆盖:Location.isShare方法
		//覆盖:Location.setLongitude方法
		location.setLongitude(16.5);
		assertTrue(location.getLongitude() == 16.5) ;
		//覆盖:Location.setLatitude方法
		location.setLatitude(153.2);
		assertTrue(location.getLatitude() == 153.2) ;
		//覆盖:Location.setName方法
		location.setName("格物楼");
		assertTrue(location.getName().equals("格物楼")) ;
	}
	
	/*
	 * 覆盖:Location.HashCode()方法
	 */
	@Test
	public void HashCodeTest() {
		Location location1 = new Location( "正心楼") ;
		Location location2 = new Location(86.1, 134.2, "格物楼", true) ;
		Location location3 = new Location(31.1, 163.2, "正心楼", true) ;
		assertTrue(location1.hashCode() != location2.hashCode()) ;
		assertTrue(location1.hashCode() == location3.hashCode()) ;
		assertTrue(location3.hashCode() != location2.hashCode()) ;
	}
	

	/*
	 * 覆盖:Location.toString方法
	 */
	@Test
	public void toStringTest() {
		Location location1 = new Location(31.1, 163.2, "正心楼", true) ;
		String s = "正心楼" ;
		assertEquals(s,location1.toString()) ;
	}
	
	/*
	 * 覆盖:Location.Equals()方法
	 */
	@Test
	public void EqualsTest() {
		Location location1 = new Location(31.1, 163.2, "正心楼", true) ;
		Location location2 = new Location(31.1, 163.2, "正心楼", true) ;
		assertTrue(location1.equals(location2));//覆盖:this.equals(obj)
		assertTrue(location1.equals(location1));//覆盖:this == obj
		assertFalse(location1.equals(null)) ;//覆盖:obj == null
		Integer x = 1 ;
		assertFalse(location1.equals(x)) ;//覆盖:getClass() != obj.getClass()
		location1.setName("格物");//覆盖:this.Name != other.Name
		assertFalse(location1.equals(location2)) ;

		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
