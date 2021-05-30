package Location;

// a immutable class 多个位置及其对应的时间
import java.util.ArrayList;
import java.util.List;

public class MultipleLocationEntryImpl implements MultipleLocationEntry  {
	
	//AI:
	//all the value of the locations is not null 
	//the size of locations > 2
	//
	
	//Abstraction function:
	//代表多个位置
	//每个位置是确定的
	//	
	//Rep:
	// the field is private
	// List is immutable so getLocation make defensive copy
	//
	private List<Location> locations  = new ArrayList<Location>();//多个位置的列表
	

	
	
	public void checkRep() {
		assert locations != null ;
		assert locations.size() != 0 ;
	}
	@Override
	public boolean addLocation(Location loc) {
		if(!locations.contains(loc)) {
			locations.add(loc);
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 无参数构造器
	 */
	public MultipleLocationEntryImpl() {
		
	}
	
	/**
	 * 构造器
	 * @param locations 一系列位置
	 */
	public MultipleLocationEntryImpl(List<Location> locations) {
		super();
		this.locations = locations;
		checkRep();
	}
	
	/**
	 * 
	 * @param locations 一系列位置
	 */
	@Override
	public void setLocations(List<Location> locs) {
		List<Location> lc = new ArrayList<Location>() ;
		lc.addAll(locs);
		locations = lc ;
	}


	@Override
	public List<Location> getLocation() {
		List<Location> l  = new ArrayList<Location>();//多个位置的列表
		l.addAll(locations);
		return l ;
	}
	@Override
	public String toString() {
		return "locations=" + locations ;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((locations == null) ? 0 : locations.hashCode());
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
		MultipleLocationEntryImpl other = (MultipleLocationEntryImpl) obj;
		if (locations == null) {
			if (other.locations != null)
				return false;
		} else if (!locations.equals(other.locations))
			return false;
		return true;
	}

	
}
