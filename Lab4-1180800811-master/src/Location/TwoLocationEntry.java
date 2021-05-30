package Location;

public interface TwoLocationEntry {
	/**
	 * ���üƻ������ʼ����ֹλ��
	 * @param start �ƻ������ʼλ��
	 * @param end   �ƻ������ֹλ��
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
