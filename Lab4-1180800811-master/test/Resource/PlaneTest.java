package Resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PlaneTest {
    
	@Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
	//Testing Strategy
	//只需要测试Plane类的setter方法和getter方法以及equals、HashCode和toString方法即可
	//
	
	/*
	 * 覆盖：Plane类的setter方法和getter方法
	 */
	@Test
	public void PlaneTest1() {
		Plane plane = new Plane("AH30", "DS36", 300, 10.5) ;
		assertEquals("AH30", plane.getPlaneNumber());//test Plane.getPlaneNumber()方法
		assertEquals("DS36",plane.getMachineNumber());//test Plane.getMachineNumber()方法
		assertEquals(300,plane.getSize()) ;//test Plane.getSize()方法
		assertTrue(plane.getAge() == 10.5 );//test Plane.getAge()方法
		//test Plane.setAge()方法
		plane.setAge(11);
		assertTrue(plane.getAge() == 11 );
		//test Plane.setMachineNumber()方法
		plane.setMachineNumber("sd36");
		assertEquals("sd36", plane.getMachineNumber());
		//test Plane.setSize()方法
		plane.setSize(400);
		assertEquals(400, plane.getSize());
		//test Plane.setPlaneNumber()方法
		plane.setPlaneNumber("hs36");
		assertEquals("hs36", plane.getPlaneNumber());
		
	}
	/*
	 * 覆盖:Plane.HashCode方法
	 */
	@Test
	public void HashCodeTest() {
		Plane plane1 = new Plane("AH30", "DS36", 300, 10.5) ;
		Plane plane2 = new Plane("AH30", "DS36", 300, 10.5) ;	
		Plane plane3 = new Plane("AH30", "DS36", 200, 10.5) ;	
		assertTrue(plane1.hashCode() == plane2.hashCode());
		assertTrue(plane1.hashCode() != plane3.hashCode()) ;
		assertFalse(plane2.hashCode() == plane3.hashCode()) ;
	}
	/*
	 * 覆盖:Plane.toString方法
	 */
	@Test
	public void toStringTest() {
		Plane plane1 = new Plane("AH30", "DS36", 300, 10.5) ;
		String s = "Plane [PlaneNumber=AH30, MachineNumber=DS36, Size=300, Age=10.5]";
		assertEquals(s, plane1.toString());
	}
	/*
	 * 覆盖:Teacher.Equals()方法
	 */
	@Test
	public void EqualsTest() {
		Plane plane1 = new Plane("AH30", "DS36", 300, 10.5) ;
		Plane plane2 = new Plane("AH30", "DS36", 300, 10.5) ;
		assertTrue(plane1.equals(plane2));//相等的equals方法
		assertTrue(plane1.equals(plane1));//覆盖:this == obj
		assertFalse(plane1.equals(null));//覆盖:obj == null
		Integer x = 1 ;
		assertFalse(plane1.equals(x)) ;//覆盖:getClass() != obj.getClass()
		plane1.setAge(10);
		assertFalse(plane1.equals(plane2)) ;//覆盖：this.age != other.age 
		plane1.setAge(10.5);
		plane1.setMachineNumber("ss30");
		assertFalse(plane1.equals(plane2));//覆盖:this.MachineNumber != other.MachineNumber
		plane1.setMachineNumber("AH30");
		plane1.setPlaneNumber("ss36");
		assertFalse(plane1.equals(plane2)) ;//覆盖:this.PlaneNumber != other.PlaneNumber 
		
	}
}
