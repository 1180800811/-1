package PlanningEntry;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import EntryState.UnBlockableEntryImpl;
import Location.Location;
import Location.TwoLocationEntryImpl;
import Resource.Plane;
import Resource.SingleDistinguishResourceEntryImpl;
import Timeslot.Timeslot;

public class FlightEntryTest {
	@Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
	
	/*
	 * 测试FlightEntry的一系列getter方法
	 */
	@Test
	public void getterTest() {
		Location loc1 = new Location(32, 25, "哈尔滨西", true);
		Location loc2 = new Location(24, 25, "北京南", true);
		TwoLocationEntryImpl loc = new TwoLocationEntryImpl();//起始位置和终止位置
		loc.setLocations(loc1, loc2);//设置起始位置和终止位置
		Plane plane = new Plane("AD56", "FGD56", 200, 1.5);
		SingleDistinguishResourceEntryImpl<Plane> res = new SingleDistinguishResourceEntryImpl<Plane>() ;//单一的资源:飞机
		res.setResource(plane);
		UnBlockableEntryImpl be = new UnBlockableEntryImpl() ;//不可阻塞
		Timeslot timeslot = new Timeslot() ;
		Calendar time1 = Calendar.getInstance() ;
		time1.set(2020, 1-1, 13, 9, 16);
		Calendar time2 = Calendar.getInstance() ;
		time2.set(2020, 1-1, 14, 8, 26);
		timeslot.setdate1(time1);
		timeslot.setdate2(time2);
		be.setTime(timeslot);
		FlightEntry flight = new FlightEntry("C919", loc, res, be);
		assertTrue(flight.getTime1().equals(time1));//测试getTime1方法
		assertTrue(flight.getTime2().equals(time2));//测试getTime2方法
		assertTrue(flight.getResource().equals(plane)) ;//测试getResource方法
		assertTrue(flight.getTimeslot().equals(timeslot)) ;//测试getTimeslot方法
		assertTrue(flight.getStartLocation().equals(loc1));//测试getStartLocation方法
		assertTrue(flight.getEndLocation().equals(loc2)) ;//测试getEndLocation方法
		List<Plane> p = new ArrayList<Plane>() ;
		p.add(plane) ;
		assertTrue(flight.getresource().equals(p)) ;//测试getresource方法
		assertTrue(flight.getBeginEndTime().equals(timeslot)) ;//测试getBeginEndTime方法
		assertTrue(flight.getTimeLocation().keySet().contains(timeslot)) ;
		//测试getTimeLocation方法
		List<Location> l = new ArrayList<Location>() ;
		l.add(loc1);
		l.add(loc2) ;
		assertTrue(flight.getTimeLocation().get(timeslot).equals(l));
	}
	
	/*
	 * 测试setLocations方法
	 */
	@Test
	public void setLocationsTest() {
		FlightEntry flight = new FlightEntry("C919") ;
		Location loc1 = new Location(32, 25, "哈尔滨西", true);
		Location loc2 = new Location(24, 25, "北京南", true);
		flight.setLocations(loc1, loc2);//测试setLocations方法
		assertTrue(flight.getStartLocation().equals(loc1));//测试getStartLocation方法
		assertTrue(flight.getEndLocation().equals(loc2)) ;//测试getEndLocation方法
	}
	
	@Test
	public void setResource() {
		FlightEntry flight = new FlightEntry("C919") ;
		Plane plane = new Plane("AD56", "FGD56", 200, 1.5);
		flight.setResource(plane);//测试setResource方法
		assertTrue(flight.getResource().equals(plane));
	}
	
	/*
	 * 测试setTime方法
	 */
	@Test
	public void setTime() {
		FlightEntry flight = new FlightEntry("C919") ;
		Timeslot timeslot = new Timeslot() ;
		Calendar time1 = Calendar.getInstance() ;
		time1.set(2020, 1-1, 13, 9, 16);
		Calendar time2 = Calendar.getInstance() ;
		time2.set(2020, 1-1, 14, 8, 26);
		timeslot.setdate1(time1);
		timeslot.setdate2(time2);
		flight.setTime(timeslot);//测试setTime方法
		assertTrue(flight.getTimeslot().equals(timeslot)) ;
	}
}
