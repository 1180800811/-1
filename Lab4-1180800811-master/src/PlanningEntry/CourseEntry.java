package PlanningEntry;

// a immutable class
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import EntryState.*;
import Location.Location;
import Location.SingleLocationEntryImpl;
import Resource.SingleDistinguishResourceEntryImpl;
import Resource.Teacher;
import Timeslot.Timeslot;

public class CourseEntry extends CommonPlanningEntry<Teacher> implements CoursePlanningEntry<Teacher> {

	//AF:
	//Representing a courseEntry that has single Location and single Resource and a start time and a end time
	//
	//RI:
	//loc != null , be != null  , res != null 
	//
	//Rep
	// all fields is private and there is no exposure of all field
	//

	private SingleLocationEntryImpl loc = new SingleLocationEntryImpl();//上课地点
	private UnBlockableEntryImpl be = new UnBlockableEntryImpl() ;//上课时间(不可阻塞)
	private SingleDistinguishResourceEntryImpl<Teacher> res = new SingleDistinguishResourceEntryImpl<Teacher>();//上课教师
	
	public void  checkRep() {
		assert loc != null ;
		assert be != null ;
		assert res != null ;
	}
	public CourseEntry(String name ) {
		super(name);
	}

	public CourseEntry(String name, SingleLocationEntryImpl loc, UnBlockableEntryImpl be,
			SingleDistinguishResourceEntryImpl<Teacher> res) {
		super(name);
		this.loc = loc;
		this.be = be;
		this.res = res;
		if(res.getResource() != null )
			super.setState(ALOCATED.instance );
		checkRep() ;
	}

	@Override
	public void setLocation(Location loc) {
		assert loc != null ;
		this.loc.setLocation(loc);
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
	public void setResource(Teacher r) {
		this.res.setResource(r);
		super.setState(ALOCATED.instance );
	}
	

	@Override
	public List<Teacher> getresource() {
		List<Teacher> t = new ArrayList<Teacher>() ;
		t.add(res.getResource()) ;
		return t ;
	}

	@Override
	public String toString() {
		return "CourseEntry [loc=" + loc + ", Timeslots=" + be.getTimeslot() + ", res=" + res + ", getName()=" + getName() + "]";
	}

	@Override
	public Location getLocation() {
		return loc.getLocation();
	}

	@Override
	public void setTime(Timeslot timeslot) {
		be.setTime(timeslot);
	}
	@Override
	public Map<Timeslot,List<Location>> getTimeLocation(){
		Map<Timeslot,List<Location>> t = new HashMap<Timeslot, List<Location>>() ;
		List<Location> lc = new ArrayList<Location>() ;
		lc.add(this.getLocation()) ;
		t.put(getTimeslot(), lc) ;
		return t ;
	}

	@Override
	public Timeslot getTimeslot() {
		return be.getTimeslot() ;
	}

	@Override
	public Teacher getResource() {
		return res.getResource() ;
	}
	@Override
	public Timeslot getBeginEndTime() {
		return getTimeslot();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((be == null) ? 0 : be.hashCode());
		result = prime * result + ((loc == null) ? 0 : loc.hashCode());
		result = prime * result + ((res == null) ? 0 : res.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CourseEntry other = (CourseEntry) obj;
		return be.equals(other.be)&&loc.equals(other.loc)&&res.equals(other.res);
	}
	




}
