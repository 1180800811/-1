package EntryState;

// a immutable class 起止时间对
import java.util.Calendar;

import Timeslot.Timeslot;

public class UnBlockableEntryImpl implements UnBlockableEntry{
	//RI:
	//时间对不为空,且第二个时间对的时间晚于第二时间
	//
	//AF:
	//表示一系列的时间点对

	// Rep:
	// the field is private
	// List is mutable , so make defensive copy for getTimeslots
	//
	
	/**
	 * 时间按递增排序
	 */
	public void checkRep() {
		assert timeslot != null ; 
		assert timeslot.getdate1().compareTo(timeslot.getdate2()) < 0 ;
	}
	Timeslot timeslot = new Timeslot() ;
	/**
	 * 无参数的构造器
	 */
	public UnBlockableEntryImpl() {
		
	}
	/**
	 * 带参数的构造器
	 * @param timeslot 时间对
	 */
	public UnBlockableEntryImpl(Timeslot timeslot) {
		super();
		this.timeslot = timeslot;
		checkRep();
	}

	@Override
	public Calendar getTime1() {
		return timeslot.getdate1();
	}

	@Override
	public Calendar getTime2() {
		return timeslot.getdate2();
	}
	
	public String gettime1() {
		return timeslot.getDate1();
	}
	public String gettime2() {
		return timeslot.getDate2();
	}
	@Override
	public void setTime(Timeslot timeslot) {
		this.timeslot = timeslot ;
	}
	@Override
	public Timeslot getTimeslot() {
		return timeslot ;
	}
	

}
