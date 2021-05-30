package Location;

public interface SingleLocationEntry {
	/**
	 * 设定计划项的位置
	 * @param loc 位置
	 */
	public void setLocation(Location loc) ;
	
	/**
	 * get the Location of the PlanningEntry
	 * @return 计划项的位置
	 */
	public Location getLocation() ;
}
