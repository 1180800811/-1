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
		s1.set(2020, 4 -1, 26,8,25);//�趨ʱ��
		Calendar s2 = Calendar.getInstance() ; 
		s2.set(2020, 8-1, 20, 9, 16, 23);//�趨ʱ��
		Timeslot timeslot = new Timeslot(s1, s2);
		bl.setTime(timeslot);//����setTime����
		bl = new UnBlockableEntryImpl(timeslot);
		String str1 = "2020-04-26 08:25" ;//Ŀ���ʽ��ʱ����ʽ
		String str2 = "2020-08-20 09:16" ;//Ŀ���ʽ��ʱ����ʽ
		assertTrue(str1.equals(bl.gettime1()));//����gettime1����
		assertTrue(str2.equals(bl.gettime2()));//����gettime2����
		assertTrue(bl.getTimeslot().equals(timeslot));//����getTimeslot����
	}
}
