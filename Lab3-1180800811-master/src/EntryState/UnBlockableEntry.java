package EntryState;

import java.util.Calendar;

import Timeslot.Timeslot;

public interface UnBlockableEntry {
	
	/**
	 * �õ���ʼʱ��
	 * @return  ��ʼʱ��
	 */
	/**
	 * �����ʼʱ��
	 * @return  ��ʼʱ��
	 */
	public Calendar getTime1() ;
	
	/**
	 * �õ���ֹʱ��
	 * @return ��ֹʱ��
	 */
	public Calendar getTime2() ;
	
	
	public void setTime(Timeslot timeslot);
	
	public Timeslot getTimeslot() ;
}
