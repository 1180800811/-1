package Location;

//A immutable class һ��λ��
public class Location {
	
	/*
	 * RI: 
	 * longitude and latitude is positive value of double
	 * name is not null
	 * 
	 */
	
	//* Abstraction function:
	// ����һ��λ�ã�������Ψһȷ��(���ǵ������ȡ�ļ���λ��ֻ��������һ����)
	// ÿһ��λ�ö�������
	// ÿһ��λ�ÿ����ǿɹ��û��߲��ܹ���
	
	//Rep:
	// all the field is private and immutable
	//
	private double longitude ; //����
	private double latitude ;//γ��
	private String name ; //λ������
	private boolean share ;//λ���Ƿ���Թ��ã�true��ʾ���Թ��ã�false��ʾ���ܹ���
	
	public void checkRep() {
		assert name != null ;
	}
	public Location(String name) {
		this.name = name ;
		checkRep();
	}
	public Location(double longitude, double latitude, String name, boolean share) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.name = name;
		this.share = share;
	}
	
	/**
	 * get the longitude of the Location
	 * @return the longitude of the Location
	 */
	public double getLongitude() {
		return longitude;
	}
	
	/**
	 * set the longitude of the Location
	 * @param longitude : the longitude of the Location
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	/**
	 * get the latitude of the Location
	 * @return the longitude of the Location
	 */
	public double getLatitude() {
		return latitude;
	}
	/**
	 * set the latitude of the Location
	 * @param latitude : the latitude of the Location
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	/**
	 *  return the name of the Location
	 * @return the name of the Location
	 */
	public String getName() {
		return name;
	}
	/**
	 * set the name of the Location
	 * @param name : the name of the Location
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * get the name of the Location
	 * @return the name of the Location
	 */
	public boolean isShare() {
		return share;
	}

	@Override
	public String toString() {
		return  name  ;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Location other = (Location) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	
	
	

}
