package Resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RailwayTest {
	
	//Testing Strategy
	//ֻ��Ҫ����Railway���setter������getter�����Լ�equals��HashCode��toString��������
	//
	@Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
	
	/*
	 * ����:Railway���setter������getter����
	 */
	@Test
	public void Railway1Test() {
		Railway railway = new Railway("���˺�", Type.BUSINESS, 300, 2020) ;
		assertEquals("���˺�",railway.getNumber());//����:Railway.getNumber()����
		assertEquals(Type.BUSINESS,railway.getType());//����:Railway.getType()����
		assertEquals(300,railway.getSize());//����:Railway.getSize()����
		assertEquals(2020,railway.getYear());//����:Railway.getYear()����
		//����:Railway.setNumber()����
		railway.setNumber("����");
		assertTrue(railway.getNumber() == "����");
		//����:Railway.setType()����
		railway.setType(Type.BUGGAGECAR);
		assertTrue(railway.getType() == Type.BUGGAGECAR );
		//����:Railway.setSize()����
		railway.setSize(250);
		assertTrue(railway.getSize() == 250) ;
		//����:Railway.setYear()����
		railway.setYear(2019);
		assertTrue(railway.getYear() == 2019) ;
	}
	
	/*
	 * ����:Railway.HashCode()����
	 */
	@Test
	public void HashCodeTest() {
		Railway railway1 = new Railway("���˺�", Type.BUSINESS, 300, 2020) ;
		Railway railway2 = new Railway("����", Type.BUGGAGECAR, 200, 2019) ;
		Railway railway3 = new Railway("���˺�", Type.BUSINESS, 300, 2020) ;
		assertTrue(railway1.hashCode() == railway3.hashCode());
		assertTrue(railway2.hashCode() != railway3.hashCode());
		assertTrue(railway1.hashCode() != railway2.hashCode());
	}
	
	/*
	 * ����:Plane.toString����
	 */
	@Test
	public void toStringTest() {
		Railway railway1 = new Railway("���˺�", Type.BUSINESS, 300, 2020) ;
		String s = "Railway [Number=���˺�, type=BUSINESS, Size=300, Year=2020]" ;
		assertEquals(s,railway1.toString()) ;
	}
	/*
	 * ����:Plane.Equals()����
	 */
	@Test
	public void EqualsTest() {
		Railway railway1 = new Railway("���˺�", Type.BUSINESS, 300, 2020) ;
		Railway railway2 = new Railway("���˺�", Type.BUSINESS, 300, 2020) ;
		assertTrue(railway1.equals(railway2));//����:this.equals(obj)
		assertTrue(railway1.equals(railway1));//����:this == obj
		assertFalse(railway1.equals(null)) ;//����:obj == null
		Integer x = 1 ;
		assertFalse(railway1.equals(x)) ;//����:getClass() != obj.getClass()
		railway1.setNumber("����");
		assertFalse(railway1.equals(railway2)) ;//����:!Number.equals(other.Number)
		railway1.setNumber("���˺�");
		railway1.setSize(200);
		assertFalse(railway1.equals(railway2)) ;//����:Size != other.Size
		railway1.setSize(300);
		railway1.setYear(2019);
		assertFalse(railway1.equals(railway2)) ;//����:Year != other.Year
		railway1.setYear(2020);
		railway1.setType(Type.FIRSTCLASS);
		assertFalse(railway1.equals(railway2)) ;//����:type != other.type
	}
	
}
