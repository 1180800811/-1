package PlanningEntry;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.junit.Test;

import EntryState.*;
import Location.Location;
import Location.MultipleLocationEntryImpl;
import Location.SingleLocationEntryImpl;
import Resource.MultipleSortedResourceEntryImpl;
import Resource.Railway;
import Resource.SingleDistinguishResourceEntryImpl;
import Resource.Teacher;
import Resource.Type;
import Timeslot.Timeslot;

public class TrainEntryTest {
	@Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
	
	/*
	 * ����TrainEntry��getter������toString����
	 */
	@Test
	public void getterTest() throws ParseException {
		Location loc1 = new Location(1.1, 1.2, "����¥", true);
		Location loc2 = new Location(1.21, 1.23, "����¥", true);	
		List<Location> ss = new ArrayList<Location>() ;
		ss.add(loc1);
		ss.add(loc2);
		MultipleLocationEntryImpl locs = new MultipleLocationEntryImpl(ss);	
		
		Timeslot s1 = new Timeslot() ; 
		Timeslot s2 = new Timeslot() ;
		Timeslot s3 = new Timeslot() ;
		s1.setDate1("2020-03-21 10:20");
		s1.setDate2("2020-03-21 17:20");
		s2.setDate1("2020-03-22 10:20");
		s2.setDate2("2020-03-22 17:20");
		s3.setDate1("2020-03-21 19:30");
		s3.setDate2("2020-03-22 08:30");
		List<Timeslot> time = new ArrayList<Timeslot>() ;
		time.add(s1);
		time.add(s2);
		time.add(s3);
		BlockableEntryImpl be1 = new BlockableEntryImpl(time);
		
		Railway r1 = new Railway("aa", Type.BUSINESS, 30, 2019);
		Railway r2 = new Railway("bb", Type.BUSINESS, 30, 2019);
		
		List<Railway> msre1 = new ArrayList<Railway>() ;
		msre1.add(r1);
		msre1.add(r2);
		
		MultipleSortedResourceEntryImpl<Railway> msre2 = new MultipleSortedResourceEntryImpl<Railway>() ;
		msre2.setResource(msre1);
	
		TrainEntry train = new TrainEntry("����һ��", locs,be1, msre2);
		
		assertTrue(train.getresource().equals(msre1)) ;//����getresource����
		assertTrue(train.getResource().equals(msre1)) ;//����getResource����
		assertTrue(train.getLocation().equals(ss))  ;//����getLocation����
		List<Timeslot> time1 = new ArrayList<Timeslot>() ;
		time1.add(s1);
		time1.add(s3);
		time1.add(s2);
		assertTrue(train.getTimeslots().equals(time1));//����getTimeslots����(�Ѿ�����ʱ����������)
		Timeslot time2 = new Timeslot() ;
		time2.setdate1(train.getTimeslots().get(0).getdate1());
		time2.setdate2(train.getTimeslots().get(time1.size()-1).getdate2());
		assertTrue(train.getBeginEndTime().equals(time2)) ;//����getBeginEndTime����
		String result = "train:����һ��" + "\n";
		result += "Location :[����¥, ����¥]\n" ;
		result +="Time: [2020-03-21 10:20	2020-03-21 17:20	, 2020-03-21 19:30	2020-03-22 08:30	, 2020-03-22 10:20	2020-03-22 17:20	]"+ "\n";
		result +="Resource: [Railway [Number=aa, type=BUSINESS, Size=30, Year=2019], Railway [Number=bb, type=BUSINESS, Size=30, Year=2019]]";
		assertTrue(result.equals(train.toString())) ;
	
	}
	
	/*
	 * ����setLocations����
	 */
	@Test
	public void setLocationsTest() {
		TrainEntry train = new TrainEntry("���˺�") ;
		Location loc1 = new Location(1.1, 1.2, "����¥", true);
		Location loc2 = new Location(1.21, 1.23, "����¥", true);	
		List<Location> ss = new ArrayList<Location>() ;
		ss.add(loc1);
		ss.add(loc2);
		train.setLocations(ss);//����setLocations����
		assertTrue(train.getLocation().equals(ss)) ;
	}
	/*
	 * ����setResource������addResource������deleteResource����
	 */
	@Test
	public void setResourceTest() {
		TrainEntry train = new TrainEntry("���˺�") ;
		Railway r1 = new Railway("aa", Type.BUSINESS, 30, 2019);
		Railway r2 = new Railway("bb", Type.BUSINESS, 30, 2019);
		List<Railway> msre1 = new ArrayList<Railway>() ;
		msre1.add(r1);
		msre1.add(r2);
		train.setResource(msre1);
		assertTrue(train.getResource().equals(msre1));
		Railway r3 = new Railway("cc", Type.BUSINESS, 30, 2019);
		msre1.add(r3) ;
		train.addResource(r3);//����addResource����
		assertTrue(train.getResource().equals(msre1));
		train.deleteResource(r2);//����deleteResource����
		msre1.remove(r2);
		assertTrue(train.getResource().equals(msre1));
	}
	
	/*
	 * ����getTimeLocation����
	 */
	@Test
	public void getTimeLocationTest() throws ParseException {
		Location loc1 = new Location(1.1, 1.2, "����¥", true);
		Location loc2 = new Location(1.21, 1.23, "����¥", true);	
		Location loc3 = new Location(1.23, 2.34, "��֪¥", true);
		List<Location> ss = new ArrayList<Location>() ;
		ss.add(loc1);
		ss.add(loc2);
		ss.add(loc3);
		MultipleLocationEntryImpl locs = new MultipleLocationEntryImpl(ss);	
		
		Timeslot s1 = new Timeslot() ; 
		Timeslot s2 = new Timeslot() ;
		s1.setDate1("2020-03-21 10:20");
		s1.setDate2("2020-03-21 17:20");
		s2.setDate1("2020-03-22 10:20");
		s2.setDate2("2020-03-22 17:20");
		List<Timeslot> time = new ArrayList<Timeslot>() ;
		time.add(s1);
		time.add(s2);
		BlockableEntryImpl be1 = new BlockableEntryImpl(time);
		
		Railway r1 = new Railway("aa", Type.BUSINESS, 30, 2019);
		Railway r2 = new Railway("bb", Type.BUSINESS, 30, 2019);
		
		List<Railway> msre1 = new ArrayList<Railway>() ;
		msre1.add(r1);
		msre1.add(r2);
		
		MultipleSortedResourceEntryImpl<Railway> msre2 = new MultipleSortedResourceEntryImpl<Railway>() ;
		msre2.setResource(msre1);
	
		TrainEntry train = new TrainEntry("����һ��", locs,be1, msre2);
		List<Location> l1 = new ArrayList<Location>() ;
		l1.add(loc1);
		l1.add(loc2);
		List<Location> l2 = new ArrayList<Location>() ;
		l2.add(loc2);
		l2.add(loc3);
		//����getTimeLocation����
		assertTrue(train.getTimeLocation().keySet().contains(s1));
		assertTrue(train.getTimeLocation().get(s1).equals(l1)) ;
		assertTrue(train.getTimeLocation().keySet().contains(s2));
		assertTrue(train.getTimeLocation().get(s2).equals(l2)) ;
		
	}
	
	/*
	 * ����setTimeslots����
	 */
	@Test
	public void setTimeslotsTest() throws ParseException {
		TrainEntry train = new TrainEntry("���˺�") ;
		Timeslot s1 = new Timeslot() ; 
		Timeslot s2 = new Timeslot() ;
		s1.setDate1("2020-03-21 10:20");
		s1.setDate2("2020-03-21 17:20");
		s2.setDate1("2020-03-22 10:20");
		s2.setDate2("2020-03-22 17:20");
		List<Timeslot> time = new ArrayList<Timeslot>() ;
		time.add(s1);
		time.add(s2);
		train.setTimeslots(time);
		assertTrue(train.getTimeslots().equals(time));
	}
	
	/*
	 * ����block����
	 */
	@Test
	public void blockTest() throws Exception {
		Location loc1 = new Location(1.1, 1.2, "����¥", true);
		Location loc2 = new Location(1.21, 1.23, "����¥", true);	
		List<Location> ss = new ArrayList<Location>() ;
		ss.add(loc1);
		ss.add(loc2);
		MultipleLocationEntryImpl locs = new MultipleLocationEntryImpl(ss);	
		
		Timeslot s1 = new Timeslot() ; 
		Timeslot s2 = new Timeslot() ;
		Timeslot s3 = new Timeslot() ;
		s1.setDate1("2020-03-21 10:20");
		s1.setDate2("2020-03-21 17:20");
		s2.setDate1("2020-03-22 10:20");
		s2.setDate2("2020-03-22 17:20");
		s3.setDate1("2020-03-21 19:30");
		s3.setDate2("2020-03-22 08:30");
		List<Timeslot> time = new ArrayList<Timeslot>() ;
		time.add(s1);
		time.add(s2);
		time.add(s3);
		BlockableEntryImpl be1 = new BlockableEntryImpl(time);
		
		Railway r1 = new Railway("aa", Type.BUSINESS, 30, 2019);
		Railway r2 = new Railway("bb", Type.BUSINESS, 30, 2019);
		
		List<Railway> msre1 = new ArrayList<Railway>() ;
		msre1.add(r1);
		msre1.add(r2);
		
		MultipleSortedResourceEntryImpl<Railway> msre2 = new MultipleSortedResourceEntryImpl<Railway>() ;
		msre2.setResource(msre1);
	
		TrainEntry train = new TrainEntry("����һ��", locs,be1, msre2);
		train.BeginPlanningEntry();
		assertTrue(train.getState() instanceof RUNNING) ;
		train.Block();
		assertTrue(train.getState() instanceof BLOCKED) ;
		train.RunBlockedPlanningEntry();
		assertTrue(train.getState() instanceof RUNNING) ;
	}
	

	
	/*
	 * ����toString����
	 */
	@Test
	public void EqualsTest() {
		Calendar time1 =Calendar.getInstance() ;
		time1.set(2020, 5-1, 6, 11, 22);
		Calendar time2 =Calendar.getInstance() ;
		time2.set(2020, 5-1, 6, 12, 22);
		Timeslot time = new Timeslot(time1, time2);
		SingleDistinguishResourceEntryImpl<Teacher> t = new SingleDistinguishResourceEntryImpl<Teacher>();
		t.setResource(null);
		Location loc = new Location("zhengx");
		SingleLocationEntryImpl l = new SingleLocationEntryImpl();
		l.setLocation(loc);
		UnBlockableEntryImpl ti = new UnBlockableEntryImpl();
		ti.setTime(time);
		String name = "s" ;
		CourseEntry course = new CourseEntry(name, l, ti, t);
		
		Calendar time11 =Calendar.getInstance() ;
		time11.set(2020, 5-1, 6, 11, 22);
		Calendar time22 =Calendar.getInstance() ;
		time22.set(2020, 5-1, 6, 12, 22);
		Timeslot timee = new Timeslot(time1, time2);
		SingleDistinguishResourceEntryImpl<Teacher> t1 = new SingleDistinguishResourceEntryImpl<Teacher>();
		t.setResource(null);
		Location loc1 = new Location("zhengx");
		SingleLocationEntryImpl l1 = new SingleLocationEntryImpl();
		l1.setLocation(loc1);
		UnBlockableEntryImpl ti1 = new UnBlockableEntryImpl();
		ti1.setTime(timee);
		String name1 = "s" ;
		CourseEntry course1 = new CourseEntry(name1, l1, ti1, t1);
		System.out.println(course.equals(course1) );
	}
	
}
