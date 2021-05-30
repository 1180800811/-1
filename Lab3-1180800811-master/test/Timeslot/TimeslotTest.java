package Timeslot;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.Calendar;

import org.junit.Test;
public class TimeslotTest {
	
	//Testing strategy for Timeslot.getdate1()
	// input : a Calendar date1
	
	//Testing strategy for Timeslot.getdate2()
	// input : a Calendar date2
	
	//Testing strategy for Timeslot.getDate1()
	// input : a Calendar date1
	
	//Testing strategy for Timeslot.getDate2()
	// input : a Calendar date2
	
	//Testing strategy for Timeslot.setDate1()
	// input : a String comply the rule of "yyyy-MM-dd HH:mm" , a String doesn't comply the rule of "yyyy-MM-dd HH:mm"
	
	//Testing strategy for Timeslot.setDate2()
	// input : a String comply the rule of "yyyy-MM-dd HH:mm" , a String doesn't comply the rule of "yyyy-MM-dd HH:mm"
	
	//Testing strategy for Timeslot.setdate1()
	// input : a Calendar date1
	
	//Testing strategy for Timeslot.setdate2()
	// input : a Calendar date2
	
	//Testing strategy for Timeslot.toString()
	//input :  a Calendar date1 and date2
	
	//Testing strategy for Timeslot.equals()
	//input : the equal object of Timeslot , the not equal object of Timeslot

	//Testing strategy for Timeslot.hashCode()
	//input : the equal object of Timeslot , the not equal object of Timeslot
	//
	//
	
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
	@Test
	public void getdate1Test() {
		Timeslot s = new Timeslot() ;
		Calendar s1 = Calendar.getInstance() ; 
		s1.set(2020, 4 -1, 26);
		s.setdate1(s1);//设定时间
		Calendar s2 = Calendar.getInstance() ; 
		s2.set(2020, 4 - 1, 26);//预期的时间
		Calendar t = s.getdate1();
		assertTrue(t.equals(s2));//判断预期的时间和得到的时间是否相等
	}
	
	@Test
	public void getdate2Test() {
		Timeslot s = new Timeslot() ;
		Calendar s1 = Calendar.getInstance() ; 
		s1.set(2020, 4 -1, 26);//设定时间
		s.setdate2(s1);
		Calendar s2 = Calendar.getInstance() ; 
		s2.set(2020, 4 - 1, 26);//预期的时间
		assertTrue(s2.equals(s.getdate2()));//判断预期的时间和得到的时间是否相等
	}
	
	@Test
	public void getDate1() {
		Timeslot s = new Timeslot() ;
		Calendar s1 = Calendar.getInstance() ; 
		s1.set(2020, 4-1, 26, 9, 16, 23);//设定时间
		s.setdate1(s1);
		assertTrue(s.getDate1().equals("2020-04-26 09:16"));//判断预期的结果和得到的表示时间的字符串是否相等
	}
	
	@Test
	public void getDate2() {
		Timeslot s = new Timeslot() ;
		Calendar s2 = Calendar.getInstance() ; 
		s2.set(2020, 4-1, 26, 9, 16, 23);//设定时间
		s.setdate2(s2);
		assertTrue(s.getDate2().equals("2020-04-26 09:16"));//判断预期的结果和得到的表示时间的字符串是否相等
	}
	
	/*
	 * 覆盖:a String comply the rule of "yyyy-MM-dd HH:mm" 
	 */
	@Test
	public void setDate1Test1() throws ParseException {
		Timeslot s = new Timeslot() ;
		String s1 = "2020-04-26 09:16" ;//符合"yyyy-MM-dd HH:mm"格式的字符串
		s.setDate1(s1);//创建日期对象
		assertTrue(s.getDate1().equals(s1));//判断预期结果和得到的结果是否相同
	}
	
	/*
	 * 覆盖:a String doesn't comply the rule of "yyyy-MM-dd HH:mm" 
	 */
	@Test
	public void setDate1Test2() throws ParseException {
		Timeslot s = new Timeslot() ;
		String s1 = "2020-04-26 09:16:32" ;//不符合"yyyy-MM-dd HH:mm"格式的字符串
		s.setDate1(s1);//创建日期对象
		assertFalse(s.getDate1().equals(s1));//判断预期结果和得到的结果是否相同
	}
	
	/*
	 * 覆盖:a String comply the rule of "yyyy-MM-dd HH:mm" 
	 */
	@Test
	public void setDate2Test1() throws ParseException {
		Timeslot s = new Timeslot() ;
		String s2 = "2020-04-26 09:16" ;//符合"yyyy-MM-dd HH:mm"格式的字符串
		s.setDate2(s2);//创建日期对象
		assertTrue(s.getDate2().equals(s2));//判断预期结果和得到的结果是否相同
	}
	
	/*
	 * 覆盖:a String doesn't comply the rule of "yyyy-MM-dd HH:mm" 
	 */
	@Test
	public void setDate2Test2() throws ParseException {
		Timeslot s = new Timeslot() ;
		String s2 = "2020-04-26 09:16:32" ;//不符合"yyyy-MM-dd HH:mm"格式的字符串
		s.setDate1(s2);//创建日期对象
		assertFalse(s.getDate2().equals(s2));//判断预期结果和得到的结果是否相同
	}
	
	@Test
	public void setdate1Test() throws ParseException {
		Timeslot s = new Timeslot() ;
		Calendar s1 = Calendar.getInstance() ;
		s1.set(2020, 4 -1, 26,9,16,32);
		s.setdate1(s1);//设定时间
		String s2 = "2020-04-26 09:16" ;//预期结果
		assertTrue(s.getDate1().equals(s2));//判断预期结果和得到的结果是否相同
	}
	
	@Test
	public void setdate2Test() throws ParseException {
		Timeslot s = new Timeslot() ;
		Calendar s1 = Calendar.getInstance() ;
		s1.set(2020, 4 -1, 26,9,16,32);
		s.setdate2(s1);//设定时间
		String s2 = "2020-04-26 09:16" ;//预期结果
		assertTrue(s.getDate2().equals(s2));//判断预期结果和得到的结果是否相同
	}
	
	@Test 
	public void toStringTest() {
		Timeslot s = new Timeslot() ;
		Calendar s1 = Calendar.getInstance() ;
		s1.set(2020, 4 -1, 26,9,16,32);
		Calendar s2 = Calendar.getInstance() ;
		s2.set(2020, 5 -1, 22,8,14,17);
		s.setdate1(s1);
		s.setdate2(s2);
		String result = "2020-04-26 09:16" + "\t" + "2020-05-22 08:14" + "\t" ;
		assertTrue(s.toString().equals(result));
	}
	/*
	 * 覆盖：the equal object of Timeslot
	 */
	@Test
	public void EqualsTest1() {
		Timeslot s11= new Timeslot() ;
		Timeslot t = s11 ;
		assertTrue(s11.equals(t));//两个对象完全相同的equals方法
		Calendar s1 = Calendar.getInstance() ;
		s1.set(2020, 4 -1, 26,9,16,32);
		Calendar s2 = Calendar.getInstance() ;
		s2.set(2020, 5 -1, 22,8,14,17);
		s11.setdate1(s1);
		s11.setdate2(s2);
		Timeslot s22 =  new Timeslot() ;
		Calendar s3 = Calendar.getInstance() ;
		s3.set(2020, 4 -1, 26,9,16,32);
		Calendar s4 = Calendar.getInstance() ;
		s4.set(2020, 5 -1, 22,8,14,17);
		s22.setdate1(s3);
		s22.setdate2(s4);
		assertTrue(s11.equals(s22)) ;//两个对象的域完全相同的equals方法
	}
	
	/*
	 * 覆盖：the equal object of Timeslot
	 */
	@Test
	public void EqualsTest2() {
		Timeslot s11= new Timeslot() ;
		Timeslot s22 =  new Timeslot() ;
		assertFalse(s11.equals(null)) ;//其中一个对象为空的Equals方法
		Calendar t = Calendar.getInstance() ;
		assertFalse(s11.equals(t)) ;//两个对象的类不同的equals方法
		
		s11.setdate1(null);
		assertFalse(s11.equals(s22)) ;//s11的date1域为空的equals方法
		Calendar s1 = Calendar.getInstance() ;
		s1.set(2020, 4 -1, 26,9,16,32);
		s11.setdate1(s1);
		assertFalse(s11.equals(s22)) ;//两个对象的date1不同的equals方法
		Calendar s2 = Calendar.getInstance() ;
		s2.set(2020, 5 -1, 22,8,14,17);
		s11.setdate2(s2);
		s22.setdate2(null);
		assertFalse(s22.equals(s11)) ;//s22的date2域为空的equals方法
		Calendar s3 = Calendar.getInstance() ;
		s3.set(2020, 4 -1, 26,9,16,32);
		Calendar s4 = Calendar.getInstance() ;
		s4.set(2020, 5 -1, 23,8,14,17);
		s22.setdate1(s3);
		s22.setdate2(s4);
		assertFalse(s11.equals(s22)) ;//两个对象的域不相同的equals方法
	}
	/*
	 * 覆盖：the equal object of Timeslot
	 */
	@Test
	public void EqualsHashCode1() {
		Timeslot s11= new Timeslot() ;
		Timeslot t = s11 ;
		assertTrue(s11.equals(t));//两个对象完全相同的equals方法
		Calendar s1 = Calendar.getInstance() ;
		s1.set(2020, 4 -1, 26,9,16,32);
		Calendar s2 = Calendar.getInstance() ;
		s2.set(2020, 5 -1, 22,8,14,17);
		s11.setdate1(s1);
		s11.setdate2(s2);
		Timeslot s22 =  new Timeslot() ;
		Calendar s3 = Calendar.getInstance() ;
		s3.set(2020, 4 -1, 26,9,16,32);
		Calendar s4 = Calendar.getInstance() ;
		s4.set(2020, 5 -1, 22,8,14,17);
		s22.setdate1(s3);
		s22.setdate2(s4);
		assertTrue((s11.hashCode())== (s22.hashCode())) ;//两个对象的域完全相同的equals方法
	}
	/*
	 * 覆盖：the not equal object of Timeslot
	 */
	@Test
	public void EqualsHashCode2() {
		Timeslot s11= new Timeslot() ;
		Timeslot t = s11 ;
		assertTrue(s11.equals(t));//两个对象完全相同的equals方法
		Calendar s1 = Calendar.getInstance() ;
		s1.set(2020, 4 -1, 26,9,16,32);
		Calendar s2 = Calendar.getInstance() ;
		s2.set(2020, 5 -1, 22,8,14,17);
		s11.setdate1(s1);
		s11.setdate2(s2);
		Timeslot s22 =  new Timeslot() ;
		Calendar s3 = Calendar.getInstance() ;
		s3.set(2020, 4 -1, 26,9,16,32);
		Calendar s4 = Calendar.getInstance() ;
		s4.set(2020, 5 -1, 22,6,14,17);
		s22.setdate1(s3);
		s22.setdate2(s4);
		assertTrue((s11.hashCode())!= (s22.hashCode())) ;//两个对象的域完全相同的equals方法
	}
	
	
	
	
}
