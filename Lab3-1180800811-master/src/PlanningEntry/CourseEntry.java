package PlanningEntry;

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


	private SingleLocationEntryImpl loc = new SingleLocationEntryImpl();//上课地点
	private UnBlockableEntryImpl be = new UnBlockableEntryImpl() ;//上课时间(不可阻塞)
	private SingleDistinguishResourceEntryImpl<Teacher> res = new SingleDistinguishResourceEntryImpl<Teacher>();//上课教师
	
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
	}

	@Override
	public void setLocation(Location loc) {
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
		int result = super.hashCode();
		result = prime * result + ((be == null) ? 0 : be.hashCode());
		result = prime * result + ((loc == null) ? 0 : loc.hashCode());
		result = prime * result + ((res == null) ? 0 : res.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CourseEntry other = (CourseEntry) obj;
		if (be == null) {
			if (other.be != null)
				return false;
		} else if (!be.equals(other.be))
			return false;
		if (loc == null) {
			if (other.loc != null)
				return false;
		} else if (!loc.equals(other.loc))
			return false;
		if (res == null) {
			if (other.res != null)
				return false;
		} else if (!res.equals(other.res))
			return false;
		return true;
	}



}
