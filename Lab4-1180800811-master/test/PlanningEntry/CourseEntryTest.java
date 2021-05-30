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
	 * 测试getLocation方法、getTime1方法，getTime2方法，getresource方法,getLocation方法，getTimeslot方法, getResource方法 getTimeLocation方法，
	 */
	@Test
	public void getterTest() {
		SingleLocationEntryImpl loc = new SingleLocationEntryImpl() ;
		Location loc1 = new Location(212, 221, "正心", false);
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
		CourseEntry s = new CourseEntry("软件构造", loc, be, res);
		assertTrue(s.getLocation().equals(loc1));//测试getLocation方法
		assertTrue(s.getTime1().equals(time1));//测试getTime1方法
		assertTrue(s.getTime2().equals(time2));//测试getTime2方法
		List<Teacher> t = new ArrayList<Teacher>() ;
		t.add(r);
		assertTrue(t.equals(s.getresource()));//测试getresource方法
		assertTrue(loc1.equals(s.getLocation()));//测试getLocation方法
		assertTrue(s.getTimeslot().equals(timeslot)) ;//测试getTimeslot方法
		assertTrue(s.getResource().equals(r));//测试getResource方法
		assertTrue(s.getBeginEndTime().equals(timeslot)) ;//测试getBeginEndTime方法
		List<Location> l = new ArrayList<Location>() ;
		l.add(loc1);
		//测试getTimeLocation方法
		assertTrue(s.getTimeLocation().keySet().contains(timeslot));
		assertTrue(s.getTimeLocation().get(timeslot).equals(l));
	}
	/*
	 * 测试setLocation()方法
	 */
	@Test
	public void setLocationTest() {
		CourseEntry s = new CourseEntry("软件构造") ;
		Location loc = new Location(212, 221, "正心", false);
		s.setLocation(loc);
		System.out.println(s.getLocation());
		assertTrue(loc.equals(s.getLocation()));//测试setLocaton方法
	}
	
	/*
	 * 测试setResource方法
	 */
	@Test
	public void setResourceTest() {
		CourseEntry s = new CourseEntry("软件构造") ;
		Teacher r = new Teacher("55554", "44", true, "g");
		s.setResource(r);
		assertTrue(r.equals(s.getResource())) ;
	}
	
	/*
	 * 测试setTime方法
	 */
	@Test
	public void setTimeTest() {
		CourseEntry s = new CourseEntry("软件构造") ;
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
	 * 测试toString方法
	 */
	@Test
	public void toStringTest() {
		SingleLocationEntryImpl loc = new SingleLocationEntryImpl() ;
		Location loc1 = new Location(212, 221, "正心", false);
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
		Teacher r = new Teacher("55554", "徐汉川", true, "教授");
		res.setResource(r);
		CourseEntry s = new CourseEntry("软件构造", loc, be, res);
		String result = "CourseEntry [loc=location=正心, Timeslots=2020-01-13 09:16	2020-01-14 08:26	, res=[r=Teacher [IdNumber=55554, Name=徐汉川, Sex=true, Title=教授]], getName()=软件构造]";
		System.out.println(s.toString());
		assertTrue(result.equals(s.toString()));
	}
	
	/*
	 * 测试Equals方法，hashCode方法
	 */
	@Test
	public void EqualsTest() {
		SingleLocationEntryImpl loc = new SingleLocationEntryImpl() ;
		Location loc1 = new Location(212, 221, "正心", false);
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
		Teacher r = new Teacher("55554", "徐汉川", true, "教授");
		res.setResource(r);
		CourseEntry s1 = new CourseEntry("软件构造", loc, be, res);
		CourseEntry s2 = new CourseEntry("软件构造", loc, be, res);
		assertTrue(s1.hashCode() == s2.hashCode());
		assertTrue(s1.equals(s2)) ;
		assertFalse(s1.equals(null));
		assertFalse(s1.equals(new Integer(1)));
	}
	
}
