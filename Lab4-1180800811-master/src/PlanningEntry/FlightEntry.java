package PlanningEntry;

import Resource.Plane;
import Resource.SingleDistinguishResourceEntryImpl;
import Timeslot.Timeslot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import EntryState.*;

import Location.Location;
import Location.TwoLocationEntryImpl;
// a immutable class
public class FlightEntry extends CommonPlanningEntry<Plane> implements FlightPlanningEntry<Plane> {
	//
	//RI : 
	// loc != null , res != null , be != null
	//
	//Rep 
	// Representing a FlightEntry that has single resource , two location , start time and end time 
	//
	//Rep:
	// all fields are private and there is no exposure of the fields
	//
	//

	private TwoLocationEntryImpl loc = new TwoLocationEntryImpl() ;//起始位置和终止位置
	private SingleDistinguishResourceEntryImpl<Plane> res = new SingleDistinguishResourceEntryImpl<Plane>() ;//单一的资源:飞机
	private UnBlockableEntryImpl be = new UnBlockableEntryImpl() ;//不可阻塞
	
	
	
	public void checkRep() {
		assert loc != null ;
		assert res != null ;
		assert be != null ;
	}
	@Override
	public void setLocations(Location start, Location end) {
		loc.setLocations(start, end);
		checkRep();
	}

	public FlightEntry(String name) {
		super(name);
	}

	public FlightEntry(String Name ,TwoLocationEntryImpl loc, SingleDistinguishResourceEntryImpl<Plane> res, UnBlockableEntryImpl be) {
		super(Name) ;
		this.loc = loc;
		this.res = res;
		this.be = be;
		if(res.getResource()!= null )
			super.setState(ALOCATED.instance);
		checkRep();
	}

	@Override
	public void setResource(Plane r) {
		res.setResource(r);
		
	}
	@Override
	public Calendar getTime1() {
		return be.getTime1();
	}

	@Override
	public Calendar getTime2() {
		return be.getTime2();
	}

	@Override
	public List<Plane> getresource() {
		List<Plane>  t = new ArrayList<Plane>() ;
		t.add(res.getResource()) ;
		return t ;
	}

	@Override
	public Location getStartLocation() {
		return loc.getStartLocation();
	}

	@Override
	public Location getEndLocation() {
		return loc.getEndLocation();
	}

	@Override
	public void setTime(Timeslot timeslot) {
		be.setTime(timeslot);
	}
	@Override
	public Map<Timeslot,List<Location>> getTimeLocation(){
		Map<Timeslot,List<Location>> t = new HashMap<Timeslot,List<Location>>() ;
		List<Location> lc = new ArrayList<Location>() ;
		lc.add(this.getStartLocation()) ;
		lc.add(getEndLocation());
		t.put(getTimeslot(), lc) ;
		return t ;
	}
	@Override
	public Timeslot getTimeslot() {
		return be.getTimeslot();
	}

	@Override
	public Plane getResource() {
		return res.getResource() ;
	}
	@Override
	public Timeslot getBeginEndTime() {
		return be.getTimeslot();
	}
}
