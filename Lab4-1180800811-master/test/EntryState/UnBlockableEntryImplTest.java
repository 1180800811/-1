package EntryState;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Test;

import Timeslot.Timeslot;

public class UnBlockableEntryImplTest extends UnBlockableEntryTest{

	@Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
	@Override
	public UnBlockableEntryImpl emptyInstance() {
		return new UnBlockableEntryImpl() ;
	}
	@Test
	public void gettimeTest() {
		UnBlockableEntryImpl bl = emptyInstance() ;
		Calendar s1 = Calendar.getInstance() ; 
		s1.set(2020, 4 -1, 26,8,25);//设定时间
		Calendar s2 = Calendar.getInstance() ; 
		s2.set(2020, 8-1, 20, 9, 16, 23);//设定时间
		Timeslot timeslot = new Timeslot(s1, s2);
		bl.setTime(timeslot);//测试setTime方法
		bl = new UnBlockableEntryImpl(timeslot);
		String str1 = "2020-04-26 08:25" ;//目标格式的时间表达式
		String str2 = "2020-08-20 09:16" ;//目标格式的时间表达式
		assertTrue(str1.equals(bl.gettime1()));//测试gettime1方法
		assertTrue(str2.equals(bl.gettime2()));//测试gettime2方法
		assertTrue(bl.getTimeslot().equals(timeslot));//测试getTimeslot方法
	}
}
