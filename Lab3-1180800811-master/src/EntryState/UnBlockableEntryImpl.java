package EntryState;

// a immutable class ��ֹʱ���
import java.util.Calendar;

import Timeslot.Timeslot;

public class UnBlockableEntryImpl implements UnBlockableEntry{
	//RI:
	//ʱ��Բ�Ϊ��,�ҵڶ���ʱ��Ե�ʱ�����ڵڶ�ʱ��
	//
	//AF:
	//��ʾһϵ�е�ʱ����

	// Rep:
	// the field is private
	// List is mutable , so make defensive copy for getTimeslots
	//
	
	/**
	 * ʱ�䰴��������
	 */
	public void checkRep() {
		assert timeslot != null ; 
		assert timeslot.getdate1().compareTo(timeslot.getdate2()) < 0 ;
	}
	Timeslot timeslot = new Timeslot() ;
	/**
	 * �޲����Ĺ�����
	 */
	public UnBlockableEntryImpl() {
		
	}
	/**
	 * �������Ĺ�����
	 * @param timeslot ʱ���
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
