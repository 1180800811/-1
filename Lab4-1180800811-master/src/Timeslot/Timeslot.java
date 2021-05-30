package Timeslot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//A immutable class һ����ֹʱ���
public class Timeslot {
	/*
	 * RI:
	 * date1 and date2 is not null
	 * date1 and date2 comply the rule of "yyyy-MM-dd HH:mm "
	 */
	//Abstraction function:
	//����һ��ʱ�����ԣ���ӦΨһ����ʼʱ�����ֹʱ��
	
	//Rep:
	// all fields are private
	// there is no exposure of the date1 and date2
	private  Calendar date1 = Calendar.getInstance();//��ʼʱ��
	private  Calendar date2 = Calendar.getInstance();//��ֹʱ��
	
	/**
	 * �޲ι�����
	 */
	public Timeslot() {
		
	}
	public void checkRep() {
		assert date1 != null ;
		assert date2 != null ;
		assert date1.compareTo(date2) < 0 ;//��ʼʱ��������ֹʱ��
	}
	/**
	 * ������
	 * @param time1 ��ʼʱ��
	 * @param time2 ��ֹʱ��
	 */
	public Timeslot(Calendar time1 , Calendar time2) {
		if(time1.before(time2)) {
			date1 = time1 ;
			date2 = time2 ;
			checkRep();
		}
	}
	/**
	 * �����ʼʱ��
	 * @return	��ʼʱ��
	 */
	public Calendar getdate1() {
		assert date1 != null ;
		return date1 ;
	}
	/**
	 * �����ֹʱ��
	 * @return	��ֹʱ��
	 */
	public Calendar getdate2() {
		assert date2 != null ;
		return date2 ;
	}
	/**
	 * ������ʼʱ�� 
	 * @param date1  ��ʼʱ��
	 */
	public void setdate1(Calendar date1) {
		this.date1 = date1;
		checkRep();
	}
	/**
	 * ������ֹʱ��
	 * @param date2
	 */
	public void setdate2(Calendar date2) {
		this.date2 = date2;
		checkRep();
	}
	/**
	 * get the string of the date1
	 * @return the string of the date1
	 */
	public String getDate1() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		return sdf.format(date1.getTime());
	}
	/**
	 * set the date of the date1
	 * @param s the string of date
	 * @throws ParseException  s is not comply the rule of "yyyy-MM-dd HH:mm"
	 */
	public void setDate1(String s ) throws ParseException  {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
		Date date= sdf.parse(s);
		date1.setTime(date);
		checkRep();
		
	}
	/**
	 * get the string of the date2
	 * @return the string of the date2
	 * @return
	 */
	public String getDate2() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		checkRep();
		return sdf.format(date2.getTime());

	}

	/**
	 * set the date of the date2
	 * @param s the string of date
	 * @throws ParseException  s is not comply the rule of "yyyy-MM-dd HH:mm"
	 */
	public void setDate2(String s) throws ParseException  {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		Date date= sdf.parse(s);
		date2.setTime(date);
		checkRep();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date1 == null) ? 0 : date1.hashCode());
		result = prime * result + ((date2 == null) ? 0 : date2.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Timeslot other = (Timeslot) obj;

		return date1.equals(other.date1) && date2.equals(other.date2);
	}
	@Override
	public String toString() {

		return  getDate1() +"\t" +  getDate2() + "\t" ;
	}
		
}
