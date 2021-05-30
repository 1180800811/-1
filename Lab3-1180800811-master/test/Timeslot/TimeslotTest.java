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
		s.setdate1(s1);//�趨ʱ��
		Calendar s2 = Calendar.getInstance() ; 
		s2.set(2020, 4 - 1, 26);//Ԥ�ڵ�ʱ��
		Calendar t = s.getdate1();
		assertTrue(t.equals(s2));//�ж�Ԥ�ڵ�ʱ��͵õ���ʱ���Ƿ����
	}
	
	@Test
	public void getdate2Test() {
		Timeslot s = new Timeslot() ;
		Calendar s1 = Calendar.getInstance() ; 
		s1.set(2020, 4 -1, 26);//�趨ʱ��
		s.setdate2(s1);
		Calendar s2 = Calendar.getInstance() ; 
		s2.set(2020, 4 - 1, 26);//Ԥ�ڵ�ʱ��
		assertTrue(s2.equals(s.getdate2()));//�ж�Ԥ�ڵ�ʱ��͵õ���ʱ���Ƿ����
	}
	
	@Test
	public void getDate1() {
		Timeslot s = new Timeslot() ;
		Calendar s1 = Calendar.getInstance() ; 
		s1.set(2020, 4-1, 26, 9, 16, 23);//�趨ʱ��
		s.setdate1(s1);
		assertTrue(s.getDate1().equals("2020-04-26 09:16"));//�ж�Ԥ�ڵĽ���͵õ��ı�ʾʱ����ַ����Ƿ����
	}
	
	@Test
	public void getDate2() {
		Timeslot s = new Timeslot() ;
		Calendar s2 = Calendar.getInstance() ; 
		s2.set(2020, 4-1, 26, 9, 16, 23);//�趨ʱ��
		s.setdate2(s2);
		assertTrue(s.getDate2().equals("2020-04-26 09:16"));//�ж�Ԥ�ڵĽ���͵õ��ı�ʾʱ����ַ����Ƿ����
	}
	
	/*
	 * ����:a String comply the rule of "yyyy-MM-dd HH:mm" 
	 */
	@Test
	public void setDate1Test1() throws ParseException {
		Timeslot s = new Timeslot() ;
		String s1 = "2020-04-26 09:16" ;//����"yyyy-MM-dd HH:mm"��ʽ���ַ���
		s.setDate1(s1);//�������ڶ���
		assertTrue(s.getDate1().equals(s1));//�ж�Ԥ�ڽ���͵õ��Ľ���Ƿ���ͬ
	}
	
	/*
	 * ����:a String doesn't comply the rule of "yyyy-MM-dd HH:mm" 
	 */
	@Test
	public void setDate1Test2() throws ParseException {
		Timeslot s = new Timeslot() ;
		String s1 = "2020-04-26 09:16:32" ;//������"yyyy-MM-dd HH:mm"��ʽ���ַ���
		s.setDate1(s1);//�������ڶ���
		assertFalse(s.getDate1().equals(s1));//�ж�Ԥ�ڽ���͵õ��Ľ���Ƿ���ͬ
	}
	
	/*
	 * ����:a String comply the rule of "yyyy-MM-dd HH:mm" 
	 */
	@Test
	public void setDate2Test1() throws ParseException {
		Timeslot s = new Timeslot() ;
		String s2 = "2020-04-26 09:16" ;//����"yyyy-MM-dd HH:mm"��ʽ���ַ���
		s.setDate2(s2);//�������ڶ���
		assertTrue(s.getDate2().equals(s2));//�ж�Ԥ�ڽ���͵õ��Ľ���Ƿ���ͬ
	}
	
	/*
	 * ����:a String doesn't comply the rule of "yyyy-MM-dd HH:mm" 
	 */
	@Test
	public void setDate2Test2() throws ParseException {
		Timeslot s = new Timeslot() ;
		String s2 = "2020-04-26 09:16:32" ;//������"yyyy-MM-dd HH:mm"��ʽ���ַ���
		s.setDate1(s2);//�������ڶ���
		assertFalse(s.getDate2().equals(s2));//�ж�Ԥ�ڽ���͵õ��Ľ���Ƿ���ͬ
	}
	
	@Test
	public void setdate1Test() throws ParseException {
		Timeslot s = new Timeslot() ;
		Calendar s1 = Calendar.getInstance() ;
		s1.set(2020, 4 -1, 26,9,16,32);
		s.setdate1(s1);//�趨ʱ��
		String s2 = "2020-04-26 09:16" ;//Ԥ�ڽ��
		assertTrue(s.getDate1().equals(s2));//�ж�Ԥ�ڽ���͵õ��Ľ���Ƿ���ͬ
	}
	
	@Test
	public void setdate2Test() throws ParseException {
		Timeslot s = new Timeslot() ;
		Calendar s1 = Calendar.getInstance() ;
		s1.set(2020, 4 -1, 26,9,16,32);
		s.setdate2(s1);//�趨ʱ��
		String s2 = "2020-04-26 09:16" ;//Ԥ�ڽ��
		assertTrue(s.getDate2().equals(s2));//�ж�Ԥ�ڽ���͵õ��Ľ���Ƿ���ͬ
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
	 * ���ǣ�the equal object of Timeslot
	 */
	@Test
	public void EqualsTest1() {
		Timeslot s11= new Timeslot() ;
		Timeslot t = s11 ;
		assertTrue(s11.equals(t));//����������ȫ��ͬ��equals����
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
		assertTrue(s11.equals(s22)) ;//�������������ȫ��ͬ��equals����
	}
	
	/*
	 * ���ǣ�the equal object of Timeslot
	 */
	@Test
	public void EqualsTest2() {
		Timeslot s11= new Timeslot() ;
		Timeslot s22 =  new Timeslot() ;
		assertFalse(s11.equals(null)) ;//����һ������Ϊ�յ�Equals����
		Calendar t = Calendar.getInstance() ;
		assertFalse(s11.equals(t)) ;//����������಻ͬ��equals����
		
		s11.setdate1(null);
		assertFalse(s11.equals(s22)) ;//s11��date1��Ϊ�յ�equals����
		Calendar s1 = Calendar.getInstance() ;
		s1.set(2020, 4 -1, 26,9,16,32);
		s11.setdate1(s1);
		assertFalse(s11.equals(s22)) ;//���������date1��ͬ��equals����
		Calendar s2 = Calendar.getInstance() ;
		s2.set(2020, 5 -1, 22,8,14,17);
		s11.setdate2(s2);
		s22.setdate2(null);
		assertFalse(s22.equals(s11)) ;//s22��date2��Ϊ�յ�equals����
		Calendar s3 = Calendar.getInstance() ;
		s3.set(2020, 4 -1, 26,9,16,32);
		Calendar s4 = Calendar.getInstance() ;
		s4.set(2020, 5 -1, 23,8,14,17);
		s22.setdate1(s3);
		s22.setdate2(s4);
		assertFalse(s11.equals(s22)) ;//�������������ͬ��equals����
	}
	/*
	 * ���ǣ�the equal object of Timeslot
	 */
	@Test
	public void EqualsHashCode1() {
		Timeslot s11= new Timeslot() ;
		Timeslot t = s11 ;
		assertTrue(s11.equals(t));//����������ȫ��ͬ��equals����
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
		assertTrue((s11.hashCode())== (s22.hashCode())) ;//�������������ȫ��ͬ��equals����
	}
	/*
	 * ���ǣ�the not equal object of Timeslot
	 */
	@Test
	public void EqualsHashCode2() {
		Timeslot s11= new Timeslot() ;
		Timeslot t = s11 ;
		assertTrue(s11.equals(t));//����������ȫ��ͬ��equals����
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
		assertTrue((s11.hashCode())!= (s22.hashCode())) ;//�������������ȫ��ͬ��equals����
	}
	
	
	
	
}
