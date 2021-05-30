package Location;

public class SingleLocationEntryImpl implements SingleLocationEntry {

	//AI:
	// location is not null 
	//
	
	//Abstraction function:
	//����ƻ��������λ��
	//λ����ȷ����
	//
	
	//Safety from rep exposure:
	// the field is private
	// Location is mutable and there is no exposure 
	//
	
	public void checkRep() {
		assert location != null ;
	}
	private Location location ;
	
	/**
	 * ������
	 * @param location λ��
	 */
	public SingleLocationEntryImpl(Location location) {
		this.location = location;
		checkRep();
	}
	/**
	 * �޲ι�����
	 */
	public SingleLocationEntryImpl() {
	}

	@Override
	public void setLocation(Location loc) {
		assert loc != null ;
		this.location = loc ;
	}
	@Override
	public Location getLocation() {
		assert location != null ;
		return this.location;
	}
	@Override
	public String toString() {
		return "location=" + location ;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SingleLocationEntryImpl other = (SingleLocationEntryImpl) obj;
		return location.getName().equals(other.getLocation().getName());
	}
	

}
