package EntryState;

import java.util.List;

import Timeslot.Timeslot;

public interface BlockableEntry {
	/**
	 * 计划项阻塞
	 * @param time 计划项阻塞
	 */
	public void Block();

	/**
	 * 设置一系列时间对
	 * @return 一系列时间对
	 */
	public void setTimeslots(List<Timeslot> time);
	/**
	 * 获得一系列时间对
	 * @return 一系列时间对
	 */
	public List<Timeslot> getTimeslots() ;
	
	/**
	 * 增加一个时间对
	 * @param s 时间对
	 * @return true: 增加成功 ， false:增加失败
	 */
	public boolean addTimeslots(Timeslot s );
	
}
