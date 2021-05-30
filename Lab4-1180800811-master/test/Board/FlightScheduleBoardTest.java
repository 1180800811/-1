package Board;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import EntryState.ALOCATED;
import EntryState.ENDED;
import EntryState.RUNNING;
import EntryState.WAITTING;
import Location.Location;
import PlanningEntry.CourseEntry;
import PlanningEntry.FlightEntry;
import PlanningEntry.PlanningEntry;
import Resource.Plane;
import Resource.Teacher;
import Timeslot.Timeslot;

public class FlightScheduleBoardTest {
	/*
	 * Testing for getFlight1()
	 * input : a list of FlightEntry
	 * output : a orderly list of FlightEntry
	 * 
	 * 
 	 *Testing for getFlight2()
	 * input : a list of FlightEntry
	 * output : a orderly list of FlightEntry
	 * 
	 * Testing for iterator()
	 * input : a list of FlightEntry
	 * output: a orderly list of FlightEntry
	 * 
	 *  Testing strategy for changeState( )
	 * Partition :  course.getResource() == null , course.getResource != null
	 * 				time.compareTo(course.getTime1()) < 0 ,time.compareTo(course.getTime2()) > 0 , time.compareTo(course.getTime1()) > 0 && time.compareTo(course.getTime2())< 0 , 
	 */
	
	/*
	 * 测试getFlight1方法
	 */
	@Test
	public void getFlight1Test() {
		FlightEntry f = new FlightEntry("C919") ;
		
		Plane plan1 = new Plane("1s1e", "fadg8", 300, 2.5) ;
		f.setResource(plan1);
		Timeslot t1 = new Timeslot() ;
		Calendar time1 = Calendar.getInstance() ;
		time1.set(2020, 2-1, 13, 14, 30);
		Calendar time2 = Calendar.getInstance() ;
		time2.set(2020, 2-1, 13, 17, 30);
		t1.setdate1(time1);
		t1.setdate2(time2);
		f.setTime(t1);
		Location loc2 = new Location(11, 22, "哈尔滨西", true);
		Location loc1 = new Location(11, 22, "北京南", true);
		f.setLocations(loc1, loc2);
		
		FlightEntry g = new FlightEntry("AH48") ;
		Plane plan2 = new Plane("1s1e", "fadg8", 300, 2.5) ;
		f.setResource(plan2);
		Timeslot t2 = new Timeslot() ;
		Calendar time11 = Calendar.getInstance() ;
		time11.set(2020, 2-1, 13, 14, 00);
		Calendar time22 = Calendar.getInstance() ;
		time22.set(2020, 2-1, 13, 17, 45);
		t2.setdate1(time11);
		t2.setdate2(time22);
		g.setTime(t2);
		Location loc22 = new Location(11, 22, "南昌", true);
		Location loc11 = new Location(15, 42, "北京南", true);
		g.setLocations(loc11, loc22);
		
		Calendar Time = Calendar.getInstance();
		Time.set(2020, 2-1, 13, 16, 45);
		List<FlightEntry> ff = new ArrayList<FlightEntry>();
		ff.add(f);
		ff.add(g);
		FlightScheduleBoard board = new FlightScheduleBoard(loc1, ff,Time);
		List<FlightEntry> s = board.getFlight1() ;//得到出发位置是loc1的按出发时间排好序的航班list
		System.out.println(s);
		assertTrue(s.size() == 2 ) ;//
		assertTrue(s.get(0).equals(g));//判断是否是按出发时间排序
		assertTrue(s.get(1).equals(f));//判断是否是按出发时间排序
		
	}
	
	/*
	 * 测试getFlight2方法
	 */
	@Test
	public void getFlight2Test() {
		FlightEntry f = new FlightEntry("C919") ;
		
		Plane plan1 = new Plane("1s1e", "fadg8", 300, 2.5) ;
		f.setResource(plan1);
		Timeslot t1 = new Timeslot() ;
		Calendar time1 = Calendar.getInstance() ;
		time1.set(2020, 2-1, 13, 14, 30);
		Calendar time2 = Calendar.getInstance() ;
		time2.set(2020, 2-1, 13, 17, 30);
		t1.setdate1(time1);
		t1.setdate2(time2);
		f.setTime(t1);
		Location loc1 = new Location(11, 22, "哈尔滨西", true);
		Location loc2 = new Location(11, 22, "北京南", true);
		f.setLocations(loc1, loc2);
		
		FlightEntry g = new FlightEntry("AH48") ;
		Plane plan2 = new Plane("1s1e", "fadg8", 300, 2.5) ;
		f.setResource(plan2);
		Timeslot t2 = new Timeslot() ;
		Calendar time11 = Calendar.getInstance() ;
		time11.set(2020, 2-1, 13, 14, 00);
		Calendar time22 = Calendar.getInstance() ;
		time22.set(2020, 2-1, 13, 17, 45);
		t2.setdate1(time11);
		t2.setdate2(time22);
		g.setTime(t2);
		Location loc11 = new Location(11, 22, "南昌", true);
		Location loc22 = new Location(15, 42, "北京南", true);
		g.setLocations(loc11, loc22);
		
		Calendar Time = Calendar.getInstance();
		Time.set(2020, 2-1, 13, 16, 45);
		List<FlightEntry> ff = new ArrayList<FlightEntry>();
		ff.add(g);
		ff.add(f);
		FlightScheduleBoard board = new FlightScheduleBoard(loc2, ff,Time);
		List<FlightEntry> s = board.getFlight2() ;//得到抵达位置是loc1的按出发时间排好序的航班list
		assertTrue(s.size() == 2 ) ;//
		assertTrue(s.get(0).equals(f));//判断是否是按抵达时间排序
		assertTrue(s.get(1).equals(g));//判断是否是按抵达时间排序
		
	}
	
	/*
	 * 测试iterator方法
	 */
	@Test
	public void iteratorTest() {
		FlightEntry f = new FlightEntry("C919") ;
		
		Plane plan1 = new Plane("1s1e", "fadg8", 300, 2.5) ;
		f.setResource(plan1);
		Timeslot t1 = new Timeslot() ;
		Calendar time1 = Calendar.getInstance() ;
		time1.set(2020, 2-1, 13, 14, 30);
		Calendar time2 = Calendar.getInstance() ;
		time2.set(2020, 2-1, 13, 17, 30);
		t1.setdate1(time1);
		t1.setdate2(time2);
		f.setTime(t1);
		Location loc2 = new Location(11, 22, "哈尔滨西", true);
		Location loc1 = new Location(11, 22, "北京南", true);
		f.setLocations(loc1, loc2);
		
		FlightEntry g = new FlightEntry("AH48") ;
		Plane plan2 = new Plane("1s1e", "fadg8", 300, 2.5) ;
		f.setResource(plan2);
		Timeslot t2 = new Timeslot() ;
		Calendar time11 = Calendar.getInstance() ;
		time11.set(2020, 2-1, 13, 14, 00);
		Calendar time22 = Calendar.getInstance() ;
		time22.set(2020, 2-1, 13, 17, 45);
		t2.setdate1(time11);
		t2.setdate2(time22);
		g.setTime(t2);
		Location loc11 = new Location(11, 22, "南昌", true);
		Location loc22 = new Location(15, 42, "北京南", true);
		g.setLocations(loc11, loc22);
		
		Calendar Time = Calendar.getInstance();
		Time.set(2020, 2-1, 13, 16, 45);
		List<FlightEntry> ff = new ArrayList<FlightEntry>();
		ff.add(f);
		ff.add(g);
		FlightScheduleBoard board = new FlightScheduleBoard(loc1, ff,Time);
		List<PlanningEntry> ttt = new ArrayList<>() ;
		Iterator<PlanningEntry> iter = board.iterator() ;
		while(iter.hasNext()) {
			ttt.add(iter.next()) ;
		}
		assertTrue(ttt.get(0).equals(g));
		assertTrue(ttt.get(1).equals(f));
	}
	
	/*
	 * 测试changeState方法
	 * 覆盖:course.getResource() == null
	 */
	@Test
	public void changeStateTest1() {
		FlightEntry s1 = new FlightEntry("aaa") ;
		s1.setResource(null);
		Timeslot t1 = new Timeslot() ;
		Calendar time1 = Calendar.getInstance() ;
		time1.set(2020, 2-1, 13, 8, 00);
		Calendar time2 = Calendar.getInstance() ;
		time2.set(2020, 2-1, 13, 9, 45);
		t1.setdate1(time1);
		t1.setdate2(time2);
		s1.setTime(t1);
		Location loc1 = new Location(11, 2.2, "南昌", false);
		Location loc2 = new Location(1.1, 22, "南京", false);
		s1.setLocations(loc1,loc2);
		List<FlightEntry> tt = new ArrayList<FlightEntry>() ;
		tt.add(s1);
		FlightScheduleBoard board = new FlightScheduleBoard(loc1, tt, time1);
		board.changeState(s1);
		assertTrue(s1.getState() instanceof WAITTING) ;
	}
	/*
	 * 测试changeState方法
	 * 覆盖:flight.getResource != null , time.compareTo(flight.getTime1()) < 0,time.compareTo(flight.getTime2()) > 0 , time.compareTo(flight.getTime1()) > 0 && time.compareTo(flight.getTime2())< 0 , 
	 */
	@Test
	public void changeState2() {
		FlightEntry f = new FlightEntry("C919") ;
		
		Plane plan1 = new Plane("1s1e", "fadg8", 300, 2.5) ;
		f.setResource(plan1);
		Timeslot t1 = new Timeslot() ;
		Calendar time1 = Calendar.getInstance() ;
		time1.set(2020, 2-1, 13, 14, 30);
		Calendar time2 = Calendar.getInstance() ;
		time2.set(2020, 2-1, 13, 17, 30);
		t1.setdate1(time1);
		t1.setdate2(time2);
		f.setTime(t1);
		Location loc2 = new Location(11, 22, "哈尔滨西", true);
		Location loc1 = new Location(11, 22, "北京南", true);
		f.setLocations(loc1, loc2);
		List<FlightEntry> tt = new ArrayList<FlightEntry>() ;
		tt.add(f);
		Calendar time3 = Calendar.getInstance() ;
		time3.set(2020, 2-1, 13, 7, 45);
		FlightScheduleBoard board = new FlightScheduleBoard (loc2, tt, time3);//覆盖:time.compareTo(course.getTime1()) < 0
		board.changeState(f);
		assertTrue(f.getState() instanceof ALOCATED);
		time3.set(2020, 2-1, 13, 18, 45);
		FlightScheduleBoard board1 = new FlightScheduleBoard (loc1, tt, time3);//覆盖:time.compareTo(course.getTime1()) > 0 && time.compareTo(course.getTime2())< 0
		board1.changeState(f);
		assertTrue(f.getState() instanceof ENDED);
		time3.set(2020, 2-1, 13, 15, 00);
		FlightScheduleBoard board2 = new FlightScheduleBoard (loc1, tt, time3);//覆盖:time.compareTo(course.getTime1()) > 0
		board2.changeState(f);
		assertTrue(f.getState() instanceof RUNNING);
		board1.show();
		board.show();
		board1.visualize(0);
		board1.visualize(1);
	}
	
}
