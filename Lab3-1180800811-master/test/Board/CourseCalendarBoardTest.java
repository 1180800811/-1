package Board;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import Board.*;
import EntryState.ALOCATED;
import EntryState.ENDED;
import EntryState.RUNNING;
import EntryState.WAITTING;

import org.junit.Test;

import Location.Location;
import PlanningEntry.CourseEntry;
import PlanningEntry.PlanningEntry;
import Resource.Teacher;
import Timeslot.Timeslot;

public class CourseCalendarBoardTest {
	/*
	 * Testing strategy for isSameDay(Calendar cal1, Calendar cal2)
	 * Partition : cal1 and cal2 is same day , cal1 and cal2 is not same day
	 * 
	 * Testing strategy for changeState()
	 * Partition :  course.getResource() == null , course.getResource != null
	 * 				time.compareTo(course.getTime1()) < 0 ,time.compareTo(course.getTime2()) > 0 , time.compareTo(course.getTime1()) > 0 && time.compareTo(course.getTime2())< 0 , 
	 *
	 * Testing strategy for getCourse()
	 *				input : a list of CourseEntry
	 *
	 * Testing strategy for iterator
	 * 				input : a list of CourseEntry
	 * 				output: a orderly list of CourseEntry
	 *
	 */
	
	/*
	 * 测试isSameDay方法
	 * 覆盖：cal1 and cal2 is same day , cal1 and cal2 is not same day
	 */
	@Test
	public void isSameDayTest() {
		Calendar cal1 = Calendar.getInstance() ;
		Calendar cal2 = Calendar.getInstance() ;
		cal1.set(2020, 5-1, 12,15, 30);
		cal2.set(2020, 5-1, 13,15, 30);
		assertFalse(CourseCalendarBoard.isSameDay(cal1,cal2));//覆盖cal1 and cal2 is not same day
		cal2.set(2020, 5-1, 12,16, 30);
		assertTrue(CourseCalendarBoard.isSameDay(cal1,cal2));//覆盖cal1 and cal2 is same day 
	}
	
	/*
	 * 测试changeState方法
	 * 覆盖:course.getResource() == null
	 */
	@Test
	public void changeStateTest1() {
		CourseEntry s1 = new CourseEntry("软件构造") ;
		s1.setResource(null);
		Timeslot t1 = new Timeslot() ;
		Calendar time1 = Calendar.getInstance() ;
		time1.set(2020, 2-1, 13, 8, 00);
		Calendar time2 = Calendar.getInstance() ;
		time2.set(2020, 2-1, 13, 9, 45);
		t1.setdate1(time1);
		t1.setdate2(time2);
		s1.setTime(t1);
		Location loc1 = new Location(11, 22, "正心", false);
		s1.setLocation(loc1);
		List<CourseEntry> tt = new ArrayList<CourseEntry>() ;
		tt.add(s1);
		CourseCalendarBoard board = new CourseCalendarBoard(loc1, tt, time1);
		board.changestate(s1);
		assertTrue(s1.getState() instanceof WAITTING) ;
	}
	/*
	 * 测试changeState方法
	 * 覆盖:course.getResource != null , time.compareTo(course.getTime1()) < 0,time.compareTo(course.getTime2()) > 0 , time.compareTo(course.getTime1()) > 0 && time.compareTo(course.getTime2())< 0 , 
	 */
	@Test
	public void changeState2() {
		CourseEntry s1 = new CourseEntry("软件构造") ;
		System.out.println(s1.getName());
		Teacher chuanhanxu1 = new Teacher("156", "chuan", true, "教师") ;
		s1.setResource(chuanhanxu1);
		Timeslot t1 = new Timeslot() ;
		Calendar time1 = Calendar.getInstance() ;
		time1.set(2020, 2-1, 13, 8, 00);
		Calendar time2 = Calendar.getInstance() ;
		time2.set(2020, 2-1, 13, 9, 45);
		t1.setdate1(time1);
		t1.setdate2(time2);
		s1.setTime(t1);
		Location loc1 = new Location(11, 22, "正心", false);
		s1.setLocation(loc1);
		List<CourseEntry> tt = new ArrayList<CourseEntry>() ;
		tt.add(s1);
		Calendar time3 = Calendar.getInstance() ;
		time3.set(2020, 2-1, 13, 7, 45);
		CourseCalendarBoard board = new CourseCalendarBoard(loc1, tt, time3);//覆盖:time.compareTo(course.getTime1()) < 0
		board.changestate(s1);
		assertTrue(s1.getState() instanceof ALOCATED);
		time3.set(2020, 2-1, 13, 10, 45);
		CourseCalendarBoard board1 = new CourseCalendarBoard(loc1, tt, time3);//覆盖:time.compareTo(course.getTime1()) > 0 && time.compareTo(course.getTime2())< 0
		board1.changestate(s1);
		assertTrue(s1.getState() instanceof ENDED);
		time3.set(2020, 2-1, 13, 9, 00);
		CourseCalendarBoard board2 = new CourseCalendarBoard(loc1, tt, time3);//覆盖:time.compareTo(course.getTime1()) > 0
		board2.changestate(s1);
		assertTrue(s1.getState() instanceof RUNNING);
	}
	
	/*
	 * 测试getCourse方法
	 */
	@Test
	public void getCourseTest() {
		CourseEntry s1 = new CourseEntry("软件构造") ;
		System.out.println(s1.getName());
		Teacher chuanhanxu1 = new Teacher("156", "chuan", true, "教师") ;
		s1.setResource(chuanhanxu1);
		Timeslot t1 = new Timeslot() ;
		Calendar time1 = Calendar.getInstance() ;
		time1.set(2020, 2-1, 14, 8, 00);
		Calendar time2 = Calendar.getInstance() ;
		time2.set(2020, 2-1, 14, 9, 45);
		t1.setdate1(time1);
		t1.setdate2(time2);
		s1.setTime(t1);
		Location loc1 = new Location(11, 22, "正心", false);
		s1.setLocation(loc1);
		
		CourseEntry s2 = new CourseEntry("算法设计") ;
		System.out.println(s2.getName());
		Teacher chuanhanxu2 = new Teacher("156", "chen", true, "教师") ;
		s2.setResource(chuanhanxu2);
		Timeslot t2 = new Timeslot() ;
		Calendar time11 = Calendar.getInstance() ;
		time11.set(2020, 2-1, 13, 10, 00);
		Calendar time22 = Calendar.getInstance() ;
		time22.set(2020, 2-1, 13, 11, 45);
		t2.setdate1(time11);
		t2.setdate2(time22);
		s2.setTime(t2);
		Location loc2 = new Location(11, 22, "正心", false);
		s2.setLocation(loc2);	
		List<CourseEntry> course = new ArrayList<CourseEntry>() ;
		course.add(s1);
		course.add(s2);
		CourseCalendarBoard board = new CourseCalendarBoard(loc1, course, time2);
		List<CourseEntry> cou = new ArrayList<CourseEntry>() ;//按时间排好序的计划项
		cou.add(s2);
		cou.add(s1);
		assertTrue(cou.equals(board.getCourse()));
	}
	/*
	 * 测试iterator方法
	 */
	@Test
	public void iteratorTest() {
		CourseEntry s1 = new CourseEntry("软件构造") ;
		System.out.println(s1.getName());
		Teacher chuanhanxu1 = new Teacher("156", "chuan", true, "教师") ;
		s1.setResource(chuanhanxu1);
		Timeslot t1 = new Timeslot() ;
		Calendar time1 = Calendar.getInstance() ;
		time1.set(2020, 2-1, 14, 8, 00);
		Calendar time2 = Calendar.getInstance() ;
		time2.set(2020, 2-1, 14, 9, 45);
		t1.setdate1(time1);
		t1.setdate2(time2);
		s1.setTime(t1);
		Location loc1 = new Location(11, 22, "正心", false);
		s1.setLocation(loc1);
		
		CourseEntry s2 = new CourseEntry("算法设计") ;
		System.out.println(s2.getName());
		Teacher chuanhanxu2 = new Teacher("156", "chen", true, "教师") ;
		s2.setResource(chuanhanxu2);
		Timeslot t2 = new Timeslot() ;
		Calendar time11 = Calendar.getInstance() ;
		time11.set(2020, 2-1, 13, 10, 00);
		Calendar time22 = Calendar.getInstance() ;
		time22.set(2020, 2-1, 13, 11, 45);
		t2.setdate1(time11);
		t2.setdate2(time22);
		s2.setTime(t2);
		Location loc2 = new Location(11, 22, "正心", false);
		s2.setLocation(loc2);	
		List<CourseEntry> course = new ArrayList<CourseEntry>() ;
		course.add(s1);
		course.add(s2);
		CourseCalendarBoard board = new CourseCalendarBoard(loc1, course, time2);
		Iterator<PlanningEntry> s = board.iterator() ;
		List<PlanningEntry> t = new ArrayList<PlanningEntry>() ;
		while(s.hasNext()) {
			t.add(s.next());
		}
		assertTrue(t.get(0).equals(s2)) ;
		assertTrue(t.get(1).equals(s1)) ;
	}
	
}
