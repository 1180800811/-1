package Location;

//A immutable class 一个位置
public class Location {
	
	/*
	 * RI: 
	 * longitude and latitude is positive value of double
	 * name is not null
	 * 
	 */
	
	//* Abstraction function:
	// 代表一个位置，由名字唯一确定(考虑到航班读取文件中位置只有名字这一属性)
	// 每一个位置都有名字
	// 每一个位置可以是可共用或者不能共用
	
	//Safety from rep exposure:
	// all the field is private and immutable
	//
	private double longitude ; //经度
	private double latitude ;//纬度
	private String name ; //位置名称
	private boolean share ;//位置是否可以共用，true表示可以共用，false表示不能共用
	
	public void checkRep() {
		assert name != null : "名字不能为空";
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
		checkRep();
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
		assert name != null ;
		return name;
	}
	/**
	 * set the name of the Location
	 * @param name : the name of the Location
	 */
	public void setName(String name) {
		assert name != null :" 名字不能为空";
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		return name.equals(other.name);
	}

	
	
	

}
