package Location;

import java.util.List;

public interface MultipleLocationEntry {
	/**
	 * 设置计划项的多个位置
	 * @param locs 计划项的位置列表
	 */
	public void setLocations(List<Location> locs);
	
	/**
	 * get the Locations of the PlanningEntry
	 * @return 计划项的位置
	 */
	public List<Location> getLocation() ;
	
	
	public boolean addLocation(Location loc);
}
