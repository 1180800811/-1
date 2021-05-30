package Timeslot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//A immutable class 一个起止时间对
public class Timeslot {
	/*
	 * RI:
	 * date1 and date2 is not null
	 * date1 and date2 comply the rule of "yyyy-MM-dd HH:mm "
	 */
	//Abstraction function:
	//代表一个时间属性，对应唯一的起始时间和终止时间
	
	//Rep:
	// all fields are private
	// there is no exposure of the date1 and date2
	private  Calendar date1 = Calendar.getInstance();//起始时间
	private  Calendar date2 = Calendar.getInstance();//终止时间
	
	/**
	 * 无参构造器
	 */
	public Timeslot() {
		
	}
	public void checkRep() {
		assert date1 != null ;
		assert date2 != null ;
		assert date1.compareTo(date2) < 0 ;
	}
	/**
	 * 构造器
	 * @param time1 起始时间
	 * @param time2 终止时间
	 */
	public Timeslot(Calendar time1 , Calendar time2) {
		if(time1.before(time2)) {
			date1 = time1 ;
			date2 = time2 ;
			checkRep();
		}
	}
	/**
	 * 获得起始时间
	 * @return	起始时间
	 */
	public Calendar getdate1() {
		return date1 ;
	}
	/**
	 * 获得终止时间
	 * @return	终止时间
	 */
	public Calendar getdate2() {
		return date2 ;
	}
	/**
	 * 设置起始时间 
	 * @param date1  起始时间
	 */
	public void setdate1(Calendar date1) {
		this.date1 = date1;
	}
	/**
	 * 设置终止时间
	 * @param date2
	 */
	public void setdate2(Calendar date2) {
		this.date2 = date2;
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
	}
	/**
	 * get the string of the date2
	 * @return the string of the date2
	 * @return
	 */
	public String getDate2() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Timeslot other = (Timeslot) obj;
		if (date1 == null) {
			if (other.date1 != null)
				return false;
		} else if (!date1.equals(other.date1))
			return false;
		if (date2 == null) {
			if (other.date2 != null)
				return false;
		} else if (!date2.equals(other.date2))
			return false;
		return true;
	}
	@Override
	public String toString() {

		return  getDate1() +"\t" +  getDate2() + "\t" ;
	}
		
}
