package EntryState;

import java.util.ArrayList;
// a immutable class 一系列的时间对
import java.util.Collections;
import java.util.List;
import Timeslot.Timeslot;

	//RI:
	//一系列的时间点对是不同的，而且是按时间顺序递增的
	//
	//AF:
	//表示一系列的时间点对

	// Rep:
	// the field is private
	// List is mutable , so make defensive copy for getTimeslots
	//
public class BlockableEntryImpl implements BlockableEntry {
	
	private List<Timeslot> timeslots = new ArrayList<Timeslot>();//高铁的一些列时间对
	
	/**
	 * 时间对是递增的
	 */
	public void checkRep() {
		assert timeslots != null ; 
		if(timeslots.size() > 1 ) {
			for(int i = 0 ; i < timeslots.size() - 1 ; i ++	) {
				assert timeslots.get(i).getdate2().compareTo(timeslots.get(i+1).getdate2()) < 0 ;
			}
		}
	}
	
	@Override
	public void Block() {
	}
	
	@Override
	public boolean addTimeslots(Timeslot s ) {
		if( !timeslots.contains(s)) {
			timeslots.add(s);
			Collections.sort(timeslots, new Compare1());
			checkRep();
			return true;
		}
		return false ;
	}
	/**
	 * 无参数的构造器
	 */
	public BlockableEntryImpl() {
		
	}
	/**
	 * 初始化一系列时间对
	 * @param timeslots 一系列时间对
	 */
	public BlockableEntryImpl(List<Timeslot> timeslots) {
		this.timeslots = timeslots;
		Collections.sort(timeslots, new Compare1());
		checkRep();
	}
	@Override
	public void setTimeslots(List<Timeslot> time) {
		List<Timeslot> time1 = new ArrayList<Timeslot>() ;
		time1.addAll(time) ;
		Collections.sort(time, new Compare1());
		this.timeslots = time1 ;
	}

	@Override
	public List<Timeslot> getTimeslots(){
		List<Timeslot> timeslotss = new ArrayList<Timeslot>();
		timeslotss.addAll(timeslots);
		Collections.sort(timeslotss, new Compare1());
		return timeslotss ;
	}



	@Override
	public String toString() {
		return "BlockableEntryImpl [timeslots=" + timeslots + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((timeslots == null) ? 0 : timeslots.hashCode());
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
		BlockableEntryImpl other = (BlockableEntryImpl) obj;
		if (timeslots == null) {
			if (other.timeslots != null)
				return false;
		} else if (!timeslots.equals(other.timeslots))
			return false;
		return true;
	}
	


	
}
