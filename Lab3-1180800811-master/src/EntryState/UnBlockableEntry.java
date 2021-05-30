package EntryState;

import java.util.Calendar;

import Timeslot.Timeslot;

public interface UnBlockableEntry {
	
	/**
	 * 得到起始时间
	 * @return  起始时间
	 */
	/**
	 * 获得起始时间
	 * @return  起始时间
	 */
	public Calendar getTime1() ;
	
	/**
	 * 得到终止时间
	 * @return 终止时间
	 */
	public Calendar getTime2() ;
	
	
	public void setTime(Timeslot timeslot);
	
	public Timeslot getTimeslot() ;
}
