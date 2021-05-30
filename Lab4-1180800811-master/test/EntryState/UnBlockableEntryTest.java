package EntryState;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Test;

import Timeslot.Timeslot;

public abstract class UnBlockableEntryTest {
	@Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
	
	public abstract UnBlockableEntryImpl emptyInstance(); 
	
	@Test
	public void getAndSetTimeTest() {
		UnBlockableEntry bl = emptyInstance() ;
		Calendar s1 = Calendar.getInstance() ; 
		s1.set(2020, 4 -1, 26,8,25);
		Calendar s2 = Calendar.getInstance() ; 
		s2.set(2020, 8-1, 20, 9, 16, 23);//设定时间
		Timeslot timeslot = new Timeslot(s1, s2);
		bl.setTime(timeslot);//测试setTime方法
		assertTrue(s1.equals(bl.getTime1()));//测试getTime1方法
		assertTrue(s2.equals(bl.getTime2()));//测试getTime2方法
	}
}
