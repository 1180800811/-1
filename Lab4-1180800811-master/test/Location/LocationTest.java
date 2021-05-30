package Location;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;



public class LocationTest {
	//Testing Strategy
	//ֻ��Ҫ����Location���setter������getter�����Լ�equals��HashCode��toString��������
	//
	@Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
	
	/*
	 * ����:Location���setter������getter����
	 */
	@Test
	public void Location1Test() {
		Location location = new Location(31.1, 163.2, "����¥", true) ;
		assertTrue(location.getLongitude() == 31.1) ;//����:Location.getLongitude����
		assertTrue(location.getLatitude() == 163.2) ;//����:Location.getLatitude����
		assertTrue(location.getName().equals("����¥")) ;//����:Location.getName����
		assertTrue(location.isShare() == true) ;//����:Location.isShare����
		//����:Location.setLongitude����
		location.setLongitude(16.5);
		assertTrue(location.getLongitude() == 16.5) ;
		//����:Location.setLatitude����
		location.setLatitude(153.2);
		assertTrue(location.getLatitude() == 153.2) ;
		//����:Location.setName����
		location.setName("����¥");
		assertTrue(location.getName().equals("����¥")) ;
	}
	
	/*
	 * ����:Location.HashCode()����
	 */
	@Test
	public void HashCodeTest() {
		Location location1 = new Location( "����¥") ;
		Location location2 = new Location(86.1, 134.2, "����¥", true) ;
		Location location3 = new Location(31.1, 163.2, "����¥", true) ;
		assertTrue(location1.hashCode() != location2.hashCode()) ;
		assertTrue(location1.hashCode() == location3.hashCode()) ;
		assertTrue(location3.hashCode() != location2.hashCode()) ;
	}
	

	/*
	 * ����:Location.toString����
	 */
	@Test
	public void toStringTest() {
		Location location1 = new Location(31.1, 163.2, "����¥", true) ;
		String s = "����¥" ;
		assertEquals(s,location1.toString()) ;
	}
	
	/*
	 * ����:Location.Equals()����
	 */
	@Test
	public void EqualsTest() {
		Location location1 = new Location(31.1, 163.2, "����¥", true) ;
		Location location2 = new Location(31.1, 163.2, "����¥", true) ;
		assertTrue(location1.equals(location2));//����:this.equals(obj)
		assertTrue(location1.equals(location1));//����:this == obj
		assertFalse(location1.equals(null)) ;//����:obj == null
		Integer x = 1 ;
		assertFalse(location1.equals(x)) ;//����:getClass() != obj.getClass()
		location1.setName("����");//����:this.Name != other.Name
		assertFalse(location1.equals(location2)) ;

		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
