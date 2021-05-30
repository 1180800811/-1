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
	 * ����FlightEntry��һϵ��getter����
	 */
	@Test
	public void getterTest() {
		Location loc1 = new Location(32, 25, "��������", true);
		Location loc2 = new Location(24, 25, "������", true);
		TwoLocationEntryImpl loc = new TwoLocationEntryImpl();//��ʼλ�ú���ֹλ��
		loc.setLocations(loc1, loc2);//������ʼλ�ú���ֹλ��
		Plane plane = new Plane("AD56", "FGD56", 200, 1.5);
		SingleDistinguishResourceEntryImpl<Plane> res = new SingleDistinguishResourceEntryImpl<Plane>() ;//��һ����Դ:�ɻ�
		res.setResource(plane);
		UnBlockableEntryImpl be = new UnBlockableEntryImpl() ;//��������
		Timeslot timeslot = new Timeslot() ;
		Calendar time1 = Calendar.getInstance() ;
		time1.set(2020, 1-1, 13, 9, 16);
		Calendar time2 = Calendar.getInstance() ;
		time2.set(2020, 1-1, 14, 8, 26);
		timeslot.setdate1(time1);
		timeslot.setdate2(time2);
		be.setTime(timeslot);
		FlightEntry flight = new FlightEntry("C919", loc, res, be);
		assertTrue(flight.getTime1().equals(time1));//����getTime1����
		assertTrue(flight.getTime2().equals(time2));//����getTime2����
		assertTrue(flight.getResource().equals(plane)) ;//����getResource����
		assertTrue(flight.getTimeslot().equals(timeslot)) ;//����getTimeslot����
		assertTrue(flight.getStartLocation().equals(loc1));//����getStartLocation����
		assertTrue(flight.getEndLocation().equals(loc2)) ;//����getEndLocation����
		List<Plane> p = new ArrayList<Plane>() ;
		p.add(plane) ;
		assertTrue(flight.getresource().equals(p)) ;//����getresource����
		assertTrue(flight.getBeginEndTime().equals(timeslot)) ;//����getBeginEndTime����
		assertTrue(flight.getTimeLocation().keySet().contains(timeslot)) ;
		//����getTimeLocation����
		List<Location> l = new ArrayList<Location>() ;
		l.add(loc1);
		l.add(loc2) ;
		assertTrue(flight.getTimeLocation().get(timeslot).equals(l));
	}
	
	/*
	 * ����setLocations����
	 */
	@Test
	public void setLocationsTest() {
		FlightEntry flight = new FlightEntry("C919") ;
		Location loc1 = new Location(32, 25, "��������", true);
		Location loc2 = new Location(24, 25, "������", true);
		flight.setLocations(loc1, loc2);//����setLocations����
		assertTrue(flight.getStartLocation().equals(loc1));//����getStartLocation����
		assertTrue(flight.getEndLocation().equals(loc2)) ;//����getEndLocation����
	}
	
	@Test
	public void setResource() {
		FlightEntry flight = new FlightEntry("C919") ;
		Plane plane = new Plane("AD56", "FGD56", 200, 1.5);
		flight.setResource(plane);//����setResource����
		assertTrue(flight.getResource().equals(plane));
	}
	
	/*
	 * ����setTime����
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
		flight.setTime(timeslot);//����setTime����
		assertTrue(flight.getTimeslot().equals(timeslot)) ;
	}
}
