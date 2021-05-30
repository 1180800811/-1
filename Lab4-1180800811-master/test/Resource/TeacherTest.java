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
	//只需要测试Teacher类的setter方法和getter方法以及equals、HashCode和toString方法即可
	//
	
	/*
	 * 覆盖：Teacher类的setter方法和getter方法
	 */
	@Test
	public void Teacher1Test() {
		Teacher teacher = new Teacher("362329199806262349", "张瑞豪", true, "本科");
		assertEquals("362329199806262349",teacher.getIdNumber());//覆盖:Teacher.getIdNumber()方法
		assertEquals("张瑞豪",teacher.getName()) ;//覆盖Teacher.getName()方法
		assertEquals(true , teacher.isSex());//覆盖teacher.isSex()方法
		assertEquals("本科",teacher.getTitle()) ;//覆盖teacher.getTitle()方法
		//覆盖teacher.setIdNumber()方法
		teacher.setIdNumber("121356546789613265");
		assertEquals("121356546789613265", teacher.getIdNumber());
		//覆盖teacher.setName()方法
		teacher.setName("陈浩");
		assertEquals("陈浩", teacher.getName());
		//覆盖teacher.setSex()方法
		teacher.setSex(false);
		assertEquals(false, teacher.isSex());
		//覆盖teacher.getTitle()方法
		teacher.setTitle("教授");
		assertEquals("教授", teacher.getTitle());
	}
	
	/*
	 * 覆盖:Teacher类的HashCode()方法
	 */
	@Test
	public void HashCodeTest() {
		Teacher teacher1 = new Teacher("362329199806262349", "张瑞豪", true, "本科");
		Teacher teacher2 = new Teacher("362329199806262349", "张瑞豪", true, "本科");
		Teacher teacher3 = new Teacher("121356546789613265", "陈浩", true, "本科");
		assertTrue(teacher1.hashCode() == teacher2.hashCode()) ;
		assertFalse(teacher1.hashCode() == teacher3.hashCode());
		assertFalse(teacher2.hashCode() == teacher3.hashCode());
	}
	
	/*
	 * 覆盖:Teacher类的toString()方法
	 */
	@Test
	public void toStringTest() {
		Teacher teacher1 = new Teacher("362329199806262349", "张瑞豪", true, "本科");
		String result = "Teacher [IdNumber=362329199806262349, Name=张瑞豪, Sex=true, Title=本科]";
		assertEquals(result, teacher1.toString());
	}
	
	/*
	 * 覆盖:Teacher类的Equals方法
	 */
	@Test
	public void EqualsTest() {
		Teacher teacher1 = new Teacher("362329199806262349", "张瑞豪", true, "本科");
		Teacher teacher2 = new Teacher("362329199806262349", "张瑞豪", true, "本科");
		assertTrue(teacher1.equals(teacher1));////覆盖:this == obj
		assertTrue(teacher1.equals(teacher2));//相等的equals方法
		assertFalse(teacher1.equals(null));//覆盖:obj == null
		Integer x = 1 ;
		assertFalse(teacher1.equals(x)) ;//覆盖:getClass() != obj.getClass()
		teacher1.setIdNumber(null);//覆盖:this.IdNumber == null 
		assertFalse(teacher1.equals(teacher2));//覆盖:this.IdNumber == null , other.IdNumber != null 
		teacher1.setIdNumber("1231365432645645");
		assertFalse(teacher1.equals(teacher2));//覆盖:this.IdNumber != other.IdNumber 
		teacher1.setIdNumber("362329199806262349");
		teacher1.setName(null);//覆盖:this.Name == null
		assertFalse(teacher1.equals(teacher2));//覆盖:this.Name == null ,other.Name != null ;
		teacher1.setName("陈浩");
		assertFalse(teacher1.equals(teacher2));//覆盖:this.Name != other.Name
		teacher1.setName("张瑞豪");
		teacher1.setSex(false);
		assertFalse(teacher1.equals(teacher2));//覆盖:this.Sex != other.Sex
		teacher1.setTitle(null);//覆盖:this.Title == null  
		assertFalse(teacher1.equals(teacher2));//覆盖:this.Title == null , other.Title != null
		teacher1.setTitle("教授");
		assertFalse(teacher1.equals(teacher2));//覆盖:this.Title != other.Title
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
