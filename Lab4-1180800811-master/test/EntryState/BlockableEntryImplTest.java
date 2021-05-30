package EntryState;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import Timeslot.Timeslot;

public class BlockableEntryImplTest extends BlockableEntryTest {

	@Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
	@Override
	public BlockableEntryImpl emptyInstance() {
		return new BlockableEntryImpl() ;
	}
	@Test
	public void setTimeslotsTest() {
		BlockableEntryImpl bl = emptyInstance() ;
		Timeslot t1 = new Timeslot() ;
		Calendar time1 = Calendar.getInstance() ;
		time1.set(2020, 1-1, 13, 9, 16);
		Calendar time2 = Calendar.getInstance() ;
		time2.set(2020, 1-1, 14, 8, 26);
		t1.setdate1(time1);
		t1.setdate2(time2);	
		Timeslot t2 = new Timeslot() ;
		Calendar time3 = Calendar.getInstance() ;
		time3.set(2020, 3-1, 16, 21,12);
		Calendar time4 = Calendar.getInstance() ;
		time4.set(2020, 3-1, 18, 22, 26);
		t2.setdate1(time3);
		t2.setdate2(time4);
		Timeslot t3 = new Timeslot() ;
		Calendar time5 = Calendar.getInstance() ;
		time5.set(2020, 2-1, 15, 21,12);
		Calendar time6 = Calendar.getInstance() ;
		time6.set(2020, 2-1, 16, 22,26);
		t3.setdate1(time5);
		t3.setdate2(time6);
		List<Timeslot> ss = new ArrayList<Timeslot>() ;
		ss.add(t1);
		ss.add(t2);
		ss.add(t3);
		bl.setTimeslots(ss);//测试setTimeslots方法
		List<Timeslot> result = new ArrayList<Timeslot>() ;
		result.add(t1);
		result.add(t3);
		result.add(t2);
		assertTrue(bl.getTimeslots().equals(result)) ;//判断时间是否是升序,且是否和目标列表相同
	}
	@Test
	public void getTimeslotsTest() {
		BlockableEntryImpl bl = emptyInstance() ;
		Timeslot t1 = new Timeslot() ;
		Calendar time1 = Calendar.getInstance() ;
		time1.set(2020, 1-1, 13, 9, 16);
		Calendar time2 = Calendar.getInstance() ;
		time2.set(2020, 1-1, 14, 8, 26);
		t1.setdate1(time1);
		t1.setdate2(time2);	
		Timeslot t2 = new Timeslot() ;
		Calendar time3 = Calendar.getInstance() ;
		time3.set(2020, 3-1, 16, 21,12);
		Calendar time4 = Calendar.getInstance() ;
		time4.set(2020, 3-1, 18, 22, 26);
		t2.setdate1(time3);
		t2.setdate2(time4);
		Timeslot t3 = new Timeslot() ;
		Calendar time5 = Calendar.getInstance() ;
		time5.set(2020, 2-1, 15, 21,12);
		Calendar time6 = Calendar.getInstance() ;
		time6.set(2020, 2-1, 16, 22,26);
		t3.setdate1(time5);
		t3.setdate2(time6);
		List<Timeslot> ss = new ArrayList<Timeslot>() ;
		ss.add(t1);
		ss.add(t2);
		ss.add(t3);
		bl = new BlockableEntryImpl(ss);
		List<Timeslot> result = new ArrayList<Timeslot>() ;
		result.add(t1);
		result.add(t3);
		result.add(t2);
		assertTrue(bl.getTimeslots().equals(result)) ;//测试getTimeslots方法，同时判断时间是否是升序,且是否和目标列表相同
	}
	
	/*
	 * 测试Equals方法， hashCode方法和toString方法
	 */
	@Test
	public void EqualsTest() {
		BlockableEntryImpl bl = emptyInstance() ;
		Timeslot t1 = new Timeslot() ;
		Calendar time1 = Calendar.getInstance() ;
		time1.set(2020, 1-1, 13, 9, 16);
		Calendar time2 = Calendar.getInstance() ;
		time2.set(2020, 1-1, 14, 8, 26);
		t1.setdate1(time1);
		t1.setdate2(time2);	
		List<Timeslot> ss = new ArrayList<Timeslot>() ;
		ss.add(t1);
		bl.addTimeslots(t1);
		BlockableEntryImpl bl2 = new BlockableEntryImpl(ss);
		assertTrue(bl.hashCode() == bl2.hashCode()) ;
		assertTrue(bl.equals(bl2));
		assertTrue(bl.toString().equals("BlockableEntryImpl [timeslots=[2020-01-13 09:16	2020-01-14 08:26	]]"));
		assertFalse(bl.equals(null));
		assertFalse(bl.equals(new Integer(1)));
		bl.Block();
	}
}
