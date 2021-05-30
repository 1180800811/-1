package PlanningEntry;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.junit.Test;

import EntryState.UnBlockableEntryImpl;
import Location.Location;
import Location.SingleLocationEntryImpl;
import Resource.SingleDistinguishResourceEntryImpl;
import Resource.Teacher;
import Timeslot.Timeslot;

public class CourseEntryTest {
	@Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
	
	

	/*
	 * ����getLocation������getTime1������getTime2������getresource����,getLocation������getTimeslot����, getResource���� getTimeLocation������
	 */
	@Test
	public void getterTest() {
		SingleLocationEntryImpl loc = new SingleLocationEntryImpl() ;
		Location loc1 = new Location(212, 221, "����", false);
		Timeslot timeslot = new Timeslot() ;
		Calendar time1 = Calendar.getInstance() ;
		time1.set(2020, 1-1, 13, 9, 16);
		Calendar time2 = Calendar.getInstance() ;
		time2.set(2020, 1-1, 14, 8, 26);
		timeslot.setdate1(time1);
		timeslot.setdate2(time2);
		loc.setLocation(loc1);
		UnBlockableEntryImpl be = new UnBlockableEntryImpl(timeslot) ;
		SingleDistinguishResourceEntryImpl<Teacher> res = new SingleDistinguishResourceEntryImpl<Teacher>();
		Teacher r = new Teacher("55554", "44", true, "g");
		res.setResource(r);
		CourseEntry s = new CourseEntry("�������", loc, be, res);
		assertTrue(s.getLocation().equals(loc1));//����getLocation����
		assertTrue(s.getTime1().equals(time1));//����getTime1����
		assertTrue(s.getTime2().equals(time2));//����getTime2����
		List<Teacher> t = new ArrayList<Teacher>() ;
		t.add(r);
		assertTrue(t.equals(s.getresource()));//����getresource����
		assertTrue(loc1.equals(s.getLocation()));//����getLocation����
		assertTrue(s.getTimeslot().equals(timeslot)) ;//����getTimeslot����
		assertTrue(s.getResource().equals(r));//����getResource����
		assertTrue(s.getBeginEndTime().equals(timeslot)) ;//����getBeginEndTime����
		List<Location> l = new ArrayList<Location>() ;
		l.add(loc1);
		//����getTimeLocation����
		assertTrue(s.getTimeLocation().keySet().contains(timeslot));
		assertTrue(s.getTimeLocation().get(timeslot).equals(l));
	}
	/*
	 * ����setLocation()����
	 */
	@Test
	public void setLocationTest() {
		CourseEntry s = new CourseEntry("�������") ;
		Location loc = new Location(212, 221, "����", false);
		s.setLocation(loc);
		System.out.println(s.getLocation());
		assertTrue(loc.equals(s.getLocation()));//����setLocaton����
	}
	
	/*
	 * ����setResource����
	 */
	@Test
	public void setResourceTest() {
		CourseEntry s = new CourseEntry("�������") ;
		Teacher r = new Teacher("55554", "44", true, "g");
		s.setResource(r);
		assertTrue(r.equals(s.getResource())) ;
	}
	
	/*
	 * ����setTime����
	 */
	@Test
	public void setTimeTest() {
		CourseEntry s = new CourseEntry("�������") ;
		Timeslot timeslot = new Timeslot() ;
		Calendar time1 = Calendar.getInstance() ;
		time1.set(2020, 1-1, 13, 9, 16);
		Calendar time2 = Calendar.getInstance() ;
		time2.set(2020, 1-1, 14, 8, 26);
		timeslot.setdate1(time1);
		timeslot.setdate2(time2);
		s.setTime(timeslot);
		assertTrue(s.getTimeslot().equals(timeslot));
	}
	
	/*
	 * ����toString����
	 */
	@Test
	public void toStringTest() {
		SingleLocationEntryImpl loc = new SingleLocationEntryImpl() ;
		Location loc1 = new Location(212, 221, "����", false);
		Timeslot timeslot = new Timeslot() ;
		Calendar time1 = Calendar.getInstance() ;
		time1.set(2020, 1-1, 13, 9, 16);
		Calendar time2 = Calendar.getInstance() ;
		time2.set(2020, 1-1, 14, 8, 26);
		timeslot.setdate1(time1);
		timeslot.setdate2(time2);
		loc.setLocation(loc1);
		UnBlockableEntryImpl be = new UnBlockableEntryImpl(timeslot) ;
		SingleDistinguishResourceEntryImpl<Teacher> res = new SingleDistinguishResourceEntryImpl<Teacher>();
		Teacher r = new Teacher("55554", "�캺��", true, "����");
		res.setResource(r);
		CourseEntry s = new CourseEntry("�������", loc, be, res);
		String result = "CourseEntry [loc=location=����, Timeslots=2020-01-13 09:16	2020-01-14 08:26	, res=[r=Teacher [IdNumber=55554, Name=�캺��, Sex=true, Title=����]], getName()=�������]";
		System.out.println(s.toString());
		assertTrue(result.equals(s.toString()));
	}
	
	/*
	 * ����Equals������hashCode����
	 */
	@Test
	public void EqualsTest() {
		SingleLocationEntryImpl loc = new SingleLocationEntryImpl() ;
		Location loc1 = new Location(212, 221, "����", false);
		Timeslot timeslot = new Timeslot() ;
		Calendar time1 = Calendar.getInstance() ;
		time1.set(2020, 1-1, 13, 9, 16);
		Calendar time2 = Calendar.getInstance() ;
		time2.set(2020, 1-1, 14, 8, 26);
		timeslot.setdate1(time1);
		timeslot.setdate2(time2);
		loc.setLocation(loc1);
		UnBlockableEntryImpl be = new UnBlockableEntryImpl(timeslot) ;
		SingleDistinguishResourceEntryImpl<Teacher> res = new SingleDistinguishResourceEntryImpl<Teacher>();
		Teacher r = new Teacher("55554", "�캺��", true, "����");
		res.setResource(r);
		CourseEntry s1 = new CourseEntry("�������", loc, be, res);
		CourseEntry s2 = new CourseEntry("�������", loc, be, res);
		assertTrue(s1.hashCode() == s2.hashCode());
		assertTrue(s1.equals(s2)) ;
		assertFalse(s1.equals(null));
		assertFalse(s1.equals(new Integer(1)));
	}
	
}
