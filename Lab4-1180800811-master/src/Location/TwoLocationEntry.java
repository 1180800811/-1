package Location;

public interface TwoLocationEntry {
	/**
	 * 设置计划项的起始和终止位置
	 * @param start 计划项的起始位置
	 * @param end   计划项的终止位置
	 */
	public void setLocations(Location start,Location end);
	
	/**
	 * get the start Location 
	 * @return the start Location
	 */
	public Location getStartLocation();
	
	/**
	 * get the end Location 
	 * @return the end Location
	 */
	public Location getEndLocation();
}
