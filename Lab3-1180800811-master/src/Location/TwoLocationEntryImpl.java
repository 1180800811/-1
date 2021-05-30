package Location;

public class TwoLocationEntryImpl implements TwoLocationEntry{
	//AI:
	// the value of the location is not null 
	//start and end is different
	
	//Abstraction function:
	//�����ƻ����������ʼλ�ú���ֹλ��
	//λ����ȷ����
	//Rep:
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TwoLocationEntryImpl other = (TwoLocationEntryImpl) obj;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		return true;
	}


	private Location start ;//��ʼλ��
	private Location end ;//��ֹλ��
	public void checkRep() {
		assert start != null ;
		assert end != null ;
	}
	
	@Override
	public void setLocations(Location start, Location end) {
		this.start = start ;
		this.end = end ;	
	}
	
	public TwoLocationEntryImpl() {
		super();
	}

	public Location getStartLocation() {
		return start ;
	}

	public Location getEndLocation() {
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