package Resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RailwayTest {
	
	//Testing Strategy
	//只需要测试Railway类的setter方法和getter方法以及equals、HashCode和toString方法即可
	//
	@Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
	
	/*
	 * 覆盖:Railway类的setter方法和getter方法
	 */
	@Test
	public void Railway1Test() {
		Railway railway = new Railway("复兴号", Type.BUSINESS, 300, 2020) ;
		assertEquals("复兴号",railway.getNumber());//覆盖:Railway.getNumber()方法
		assertEquals(Type.BUSINESS,railway.getType());//覆盖:Railway.getType()方法
		assertEquals(300,railway.getSize());//覆盖:Railway.getSize()方法
		assertEquals(2020,railway.getYear());//覆盖:Railway.getYear()方法
		//覆盖:Railway.setNumber()方法
		railway.setNumber("冲锋号");
		assertTrue(railway.getNumber() == "冲锋号");
		//覆盖:Railway.setType()方法
		railway.setType(Type.BUGGAGECAR);
		assertTrue(railway.getType() == Type.BUGGAGECAR );
		//覆盖:Railway.setSize()方法
		railway.setSize(250);
		assertTrue(railway.getSize() == 250) ;
		//覆盖:Railway.setYear()方法
		railway.setYear(2019);
		assertTrue(railway.getYear() == 2019) ;
	}
	
	/*
	 * 覆盖:Railway.HashCode()方法
	 */
	@Test
	public void HashCodeTest() {
		Railway railway1 = new Railway("复兴号", Type.BUSINESS, 300, 2020) ;
		Railway railway2 = new Railway("冲锋号", Type.BUGGAGECAR, 200, 2019) ;
		Railway railway3 = new Railway("复兴号", Type.BUSINESS, 300, 2020) ;
		assertTrue(railway1.hashCode() == railway3.hashCode());
		assertTrue(railway2.hashCode() != railway3.hashCode());
		assertTrue(railway1.hashCode() != railway2.hashCode());
	}
	
	/*
	 * 覆盖:Plane.toString方法
	 */
	@Test
	public void toStringTest() {
		Railway railway1 = new Railway("复兴号", Type.BUSINESS, 300, 2020) ;
		String s = "Railway [Number=复兴号, type=BUSINESS, Size=300, Year=2020]" ;
		assertEquals(s,railway1.toString()) ;
	}
	/*
	 * 覆盖:Plane.Equals()方法
	 */
	@Test
	public void EqualsTest() {
		Railway railway1 = new Railway("复兴号", Type.BUSINESS, 300, 2020) ;
		Railway railway2 = new Railway("复兴号", Type.BUSINESS, 300, 2020) ;
		assertTrue(railway1.equals(railway2));//覆盖:this.equals(obj)
		assertTrue(railway1.equals(railway1));//覆盖:this == obj
		assertFalse(railway1.equals(null)) ;//覆盖:obj == null
		Integer x = 1 ;
		assertFalse(railway1.equals(x)) ;//覆盖:getClass() != obj.getClass()
		railway1.setNumber("冲锋号");
		assertFalse(railway1.equals(railway2)) ;//覆盖:!Number.equals(other.Number)
		railway1.setNumber("复兴号");
		railway1.setSize(200);
		assertFalse(railway1.equals(railway2)) ;//覆盖:Size != other.Size
		railway1.setSize(300);
		railway1.setYear(2019);
		assertFalse(railway1.equals(railway2)) ;//覆盖:Year != other.Year
		railway1.setYear(2020);
		railway1.setType(Type.FIRSTCLASS);
		assertFalse(railway1.equals(railway2)) ;//覆盖:type != other.type
	}
	
}
