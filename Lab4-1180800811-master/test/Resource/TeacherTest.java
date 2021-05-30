package Resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TeacherTest {
	@Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
	//Testing Strategy
	//ֻ��Ҫ����Teacher���setter������getter�����Լ�equals��HashCode��toString��������
	//
	
	/*
	 * ���ǣ�Teacher���setter������getter����
	 */
	@Test
	public void Teacher1Test() {
		Teacher teacher = new Teacher("362329199806262349", "�����", true, "����");
		assertEquals("362329199806262349",teacher.getIdNumber());//����:Teacher.getIdNumber()����
		assertEquals("�����",teacher.getName()) ;//����Teacher.getName()����
		assertEquals(true , teacher.isSex());//����teacher.isSex()����
		assertEquals("����",teacher.getTitle()) ;//����teacher.getTitle()����
		//����teacher.setIdNumber()����
		teacher.setIdNumber("121356546789613265");
		assertEquals("121356546789613265", teacher.getIdNumber());
		//����teacher.setName()����
		teacher.setName("�º�");
		assertEquals("�º�", teacher.getName());
		//����teacher.setSex()����
		teacher.setSex(false);
		assertEquals(false, teacher.isSex());
		//����teacher.getTitle()����
		teacher.setTitle("����");
		assertEquals("����", teacher.getTitle());
	}
	
	/*
	 * ����:Teacher���HashCode()����
	 */
	@Test
	public void HashCodeTest() {
		Teacher teacher1 = new Teacher("362329199806262349", "�����", true, "����");
		Teacher teacher2 = new Teacher("362329199806262349", "�����", true, "����");
		Teacher teacher3 = new Teacher("121356546789613265", "�º�", true, "����");
		assertTrue(teacher1.hashCode() == teacher2.hashCode()) ;
		assertFalse(teacher1.hashCode() == teacher3.hashCode());
		assertFalse(teacher2.hashCode() == teacher3.hashCode());
	}
	
	/*
	 * ����:Teacher���toString()����
	 */
	@Test
	public void toStringTest() {
		Teacher teacher1 = new Teacher("362329199806262349", "�����", true, "����");
		String result = "Teacher [IdNumber=362329199806262349, Name=�����, Sex=true, Title=����]";
		assertEquals(result, teacher1.toString());
	}
	
	/*
	 * ����:Teacher���Equals����
	 */
	@Test
	public void EqualsTest() {
		Teacher teacher1 = new Teacher("362329199806262349", "�����", true, "����");
		Teacher teacher2 = new Teacher("362329199806262349", "�����", true, "����");
		assertTrue(teacher1.equals(teacher1));////����:this == obj
		assertTrue(teacher1.equals(teacher2));//��ȵ�equals����
		assertFalse(teacher1.equals(null));//����:obj == null
		Integer x = 1 ;
		assertFalse(teacher1.equals(x)) ;//����:getClass() != obj.getClass()
		teacher1.setIdNumber(null);//����:this.IdNumber == null 
		assertFalse(teacher1.equals(teacher2));//����:this.IdNumber == null , other.IdNumber != null 
		teacher1.setIdNumber("1231365432645645");
		assertFalse(teacher1.equals(teacher2));//����:this.IdNumber != other.IdNumber 
		teacher1.setIdNumber("362329199806262349");
		teacher1.setName(null);//����:this.Name == null
		assertFalse(teacher1.equals(teacher2));//����:this.Name == null ,other.Name != null ;
		teacher1.setName("�º�");
		assertFalse(teacher1.equals(teacher2));//����:this.Name != other.Name
		teacher1.setName("�����");
		teacher1.setSex(false);
		assertFalse(teacher1.equals(teacher2));//����:this.Sex != other.Sex
		teacher1.setTitle(null);//����:this.Title == null  
		assertFalse(teacher1.equals(teacher2));//����:this.Title == null , other.Title != null
		teacher1.setTitle("����");
		assertFalse(teacher1.equals(teacher2));//����:this.Title != other.Title
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
