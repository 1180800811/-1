package PlanningEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import EntryState.*;
import Location.Location;
import Location.MultipleLocationEntryImpl;
import Resource.MultipleSortedResourceEntryImpl;
import Resource.Railway;
import Resource.Type;
import Timeslot.Timeslot;
public class TrainEntry extends CommonPlanningEntry<Railway> implements TrainPlanningEntry<Railway> {
	
	private MultipleLocationEntryImpl mle = new MultipleLocationEntryImpl();//一系列高铁站
	private BlockableEntryImpl be = new BlockableEntryImpl();//可阻塞
	private MultipleSortedResourceEntryImpl<Railway> msre = new MultipleSortedResourceEntryImpl<Railway>();//一系列车厢
	
	/**
	 * 构造器方法
	 * @param name
	 */
	public TrainEntry(String name) {
		super(name);
	}
	/**
	 * 不带参数的构造器方法
	 */
	public TrainEntry() {
		super();
	}

	public TrainEntry(String Name ,MultipleLocationEntryImpl mle, BlockableEntryImpl be,
			MultipleSortedResourceEntryImpl<Railway> msre) {
		super(Name) ;
		this.mle = mle;
		this.be = be;
		this.msre = msre;
		if(msre.getResource() != null ) {
			if(msre.getResource().size() != 0)
				super.setState(ALOCATED.instance);
		}

	}

	@Override
	public void setLocations(List<Location> locs) {
		mle.setLocations(locs);
		
	}

	@Override
	public void Block() {
		state = state.changeState("BLOCKED");
	}

	@Override
	public void setResource(List<Railway> r) {
		msre.setResource(r);
		super.setState(ALOCATED.instance);
	}

	@Override
	public void addResource(Railway resource) {
		msre.addResource(resource);
		
	}

	/**
	 * 删除资源
	 */
	public void deleteResource(Railway resource) {
		msre.deleteResource(resource);
		super.setState(WAITTING.instance);
	}

	/**
	 * 启动阻塞的计划项
	 * @throws Exception
	 */
	public void RunBlockedPlanningEntry() throws Exception {
		state =  state.changeState("RUNNING");
	}


	
	
	@Override
	public String toString() {
		return "train:" + getName() + "\n" + "Location :" + getLocation() + "\n" + "Time: " + getTimeslots() + "\n" + "Resource: " + getResource();
	}

	@Override
	public List<Railway> getResource() {
		return msre.getResource();
	}

	@Override
	public List<Location> getLocation() {
		return mle.getLocation();
	}

	@Override
	public void setTimeslots(List<Timeslot> time) {
			be.setTimeslots(time);
	}

	@Override
	public List<Timeslot> getTimeslots() {
		return be.getTimeslots();
	}
	@Override
	public Map<Timeslot,List<Location>> getTimeLocation(){
		Map<Timeslot,List<Location>> t = new HashMap<Timeslot,List<Location>>() ;
		for( int i = 0 ; i < getTimeslots().size() ; i ++ ) {
			List<Location> loca = new ArrayList<Location>() ;
			loca.add(getLocation().get(i));
			loca.add(getLocation().get(i+1));
			t.put(getTimeslots().get(i), loca) ;
		}
		return t ;
	}
	@Override
	public List<Railway> getresource() {
		return msre.getResource();
	}
	@Override
	public Timeslot getBeginEndTime() {
		Timeslot time1 = new Timeslot() ;
		time1.setdate1(getTimeslots().get(0).getdate1());
		time1.setdate2(getTimeslots().get(getTimeslots().size()-1).getdate2());
		return time1 ;
	}
	@Override
	public boolean addLocation(Location loc) {
		return mle.addLocation(loc);
	}
	@Override
	public boolean addTimeslots(Timeslot s) {
		return be.addTimeslots(s);
	}
	
}
