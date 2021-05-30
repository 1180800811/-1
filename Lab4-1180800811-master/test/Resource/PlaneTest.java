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
	//ֻ��Ҫ����Plane���setter������getter�����Լ�equals��HashCode��toString��������
	//
	
	/*
	 * ���ǣ�Plane���setter������getter����
	 */
	@Test
	public void PlaneTest1() {
		Plane plane = new Plane("AH30", "DS36", 300, 10.5) ;
		assertEquals("AH30", plane.getPlaneNumber());//test Plane.getPlaneNumber()����
		assertEquals("DS36",plane.getMachineNumber());//test Plane.getMachineNumber()����
		assertEquals(300,plane.getSize()) ;//test Plane.getSize()����
		assertTrue(plane.getAge() == 10.5 );//test Plane.getAge()����
		//test Plane.setAge()����
		plane.setAge(11);
		assertTrue(plane.getAge() == 11 );
		//test Plane.setMachineNumber()����
		plane.setMachineNumber("sd36");
		assertEquals("sd36", plane.getMachineNumber());
		//test Plane.setSize()����
		plane.setSize(400);
		assertEquals(400, plane.getSize());
		//test Plane.setPlaneNumber()����
		plane.setPlaneNumber("hs36");
		assertEquals("hs36", plane.getPlaneNumber());
		
	}
	/*
	 * ����:Plane.HashCode����
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
	 * ����:Plane.toString����
	 */
	@Test
	public void toStringTest() {
		Plane plane1 = new Plane("AH30", "DS36", 300, 10.5) ;
		String s = "Plane [PlaneNumber=AH30, MachineNumber=DS36, Size=300, Age=10.5]";
		assertEquals(s, plane1.toString());
	}
	/*
	 * ����:Teacher.Equals()����
	 */
	@Test
	public void EqualsTest() {
		Plane plane1 = new Plane("AH30", "DS36", 300, 10.5) ;
		Plane plane2 = new Plane("AH30", "DS36", 300, 10.5) ;
		assertTrue(plane1.equals(plane2));//��ȵ�equals����
		assertTrue(plane1.equals(plane1));//����:this == obj
		assertFalse(plane1.equals(null));//����:obj == null
		Integer x = 1 ;
		assertFalse(plane1.equals(x)) ;//����:getClass() != obj.getClass()
		plane1.setAge(10);
		assertFalse(plane1.equals(plane2)) ;//���ǣ�this.age != other.age 
		plane1.setAge(10.5);
		plane1.setMachineNumber("ss30");
		assertFalse(plane1.equals(plane2));//����:this.MachineNumber != other.MachineNumber
		plane1.setMachineNumber("AH30");
		plane1.setPlaneNumber("ss36");
		assertFalse(plane1.equals(plane2)) ;//����:this.PlaneNumber != other.PlaneNumber 
		
	}
}
