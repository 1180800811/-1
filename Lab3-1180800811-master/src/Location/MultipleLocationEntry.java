package Location;

import java.util.List;

public interface MultipleLocationEntry {
	/**
	 * ���üƻ���Ķ��λ��
	 * @param locs �ƻ����λ���б�
	 */
	public void setLocations(List<Location> locs);
	
	/**
	 * get the Locations of the PlanningEntry
	 * @return �ƻ����λ��
	 */
	public List<Location> getLocation() ;
	
	
	public boolean addLocation(Location loc);
}
