package Location;

public class TwoLocationEntryImpl implements TwoLocationEntry{
	//AI:
	// the value of the location is not null 
	//start and end is different
	
	//Abstraction function:
	//代表计划项的所在起始位置和终止位置
	//位置是确定的
	//Safety from rep exposure:
	// the field is private
	// Location is mutable and there is no exposure 
	//
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TwoLocationEntryImpl other = (TwoLocationEntryImpl) obj;
		return end.equals(other.end) && start.equals(other.start);
	}


	private Location start ;//起始位置
	private Location end ;//终止位置
	public void checkRep() {
		assert start != null ;
		assert end != null ;
		assert !start.equals(end) : "起始位置和终止位置不能相同" ;
	}
	
	@Override
	public void setLocations(Location start, Location end) {
		this.start = start ;
		this.end = end ;	
		checkRep() ;
	}
	
	public TwoLocationEntryImpl() {
		super();
	}

	public Location getStartLocation() {
		assert start != null ;
		return start ;
	}

	public Location getEndLocation() {
		assert end != null ;
		return end ;
	}

	@Override
	public String toString() {
		return "[start=" + start + ", end=" + end + "]";
	}

	public TwoLocationEntryImpl(Location start, Location end) {
		super();
		this.start = start;
		this.end = end;
		checkRep();
	}

	

}
