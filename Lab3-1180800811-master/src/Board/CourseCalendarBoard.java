package Board;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;

import EntryState.ALOCATED;
import EntryState.ENDED;
import EntryState.RUNNING;
import EntryState.WAITTING;
import Location.Location;
import PlanningEntry.CourseEntry;
import PlanningEntry.PlanningEntry;
public class CourseCalendarBoard implements Iterable<PlanningEntry> {
	/*
	 * RI: 
	 * time , location , course is not null
	 * 
	 * Abstraction function:
	 * ����һ����Ϣ�壬����Ϣ���λ�á����еĿγ̡���ǰʱ�����
	 * 	Rep:
	 *	all the field is private and immutable
	 *	there is no exposure of all the fields
	 * 
	 */
	private Location location  ;//��Ϣ�����ڵĵ�λ��
	private List<CourseEntry> course = new ArrayList<CourseEntry>() ;//һϵ�еļƻ���
	private Calendar time = Calendar.getInstance() ; //��ǰʱ��
	
	
	/**
	 * ��������ʼ��
	 * @param location  ��Ϣ�����ڵ�λ��
	 * @param course һϵ�еĿγ�
	 * @param time ��ǰʱ��
	 */
	public CourseCalendarBoard(Location location, List<CourseEntry> course , Calendar time) {
		this.location = location;
		this.course = course;
		this.time  = time ;
		Collections.sort(course , new CourseCompare());
		
	}
	
	/**
	 * �õ�����λ����location�ļƻ���
	 * @return ����λ����location�İ���ʱ�����ӵļƻ����б�
	 */
	public List<CourseEntry> getCourse() {
		List<CourseEntry> t = new ArrayList<>() ;//����λ����location�Ŀγ�
		for(int i = 0 ; i < course.size() ; i++) {
			if(course.get(i).getLocation().equals(location)) {
				t.add(course.get(i));
			}
		}
		Collections.sort(t , new CourseCompare());//��ʱ������
		for(CourseEntry e : t) {
			changestate(e);//���ݵ�ǰʱ���趨�γ̵�״̬
		}
		return t ;	
	}
	
	/**
	 * չʾ�͵�ǰ��Ϣ�����ڵ�λ����ص����пγ�
	 */
	public void show() {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		String s = sdf1.format(time.getTime()); 
		List<CourseEntry> t1  = getCourse() ;//��ĳ��λ����ص����пγ�
		String[] columNames = { "���" , "ʱ��" , "�γ�" ,"��ʦ" , "״̬"} ;//����һ����Ϣ
		 Object[][] rowData = new String[t1.size()][] ;
		 for(int i = 0  ; i < t1.size() ; i ++	) {
			 rowData[i] = new String[] { String.valueOf(i+1) ,sdf1.format(t1.get(i).getTime1().getTime())+ "-" + 
		 sdf1.format(t1.get(i).getTime2().getTime()),t1.get(i).getName(),t1.get(i).getResource().getName(),t1.get(i).getState().toString()} ; 
		 }
		JFrame jf = new JFrame(s  + "(��ǰʱ��)" + " , " + location.getName());
		Show.show(columNames, rowData, jf);
		 
	}

	/**
	 * �жϸ�����ʱ���Ƿ���ͬһ��
	 */
	public static boolean isSameDay(Calendar cal1, Calendar cal2) {
		if(cal1 != null && cal2 != null ) {
			return cal1.get(0) == cal2.get(0)  && cal1.get(1) == cal2.get(1)  && cal1.get(6) == cal2.get(6) ;
		}
		return false ;
	}
	/**
	 * ���ݵ�ǰʱ�������ø�����״̬
	 * @param course �γ�
	 */
	public  void changestate(CourseEntry course) {
		if(course.getResource() == null ) {
			course.setState(WAITTING.instance );//�γ̻�δ�����ʦ
			return ;
		}
		if(time.compareTo(course.getTime1()) < 0) {
			course.setState(ALOCATED.instance );//�γ̻�δ����ʼ������״̬ΪALOCATED
			return ;
		}else if(time.compareTo(course.getTime2()) > 0) {
			course.setState(ENDED.instance );//�γ��Ѿ�����,����״̬ΪENDED
			return ;
		}else {
			course.setState(RUNNING.instance );//�γ����ڽ��У�����״̬ΪRUNNING
			return ;
		}
	}
	/**
	 * ���ӻ���Ϣ��
	 */
	public void visualize() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		String s = sdf.format(time.getTime());
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm"); 
		List<CourseEntry> t  = getCourse() ;
		String[] columNames = { "���" , "ʱ��" , "�γ�" ,"��ʦ" , "״̬"} ;//����һ����Ϣ
		List<CourseEntry> t1  = new ArrayList<CourseEntry>() ;//���к͵�ǰʱ��ͬһ��Ŀγ�
		for(int i = 0 ; i < t.size() ; i++) {
			if(isSameDay(time, t.get(i).getTime1())) {
				t1.add(t.get(i));
			}
		}
		 Object[][] rowData = new String[t1.size()][] ;
		 for(int i = 0  ; i < t1.size() ; i ++	) {
			 rowData[i] = new String[] { String.valueOf(i+1) ,sdf1.format(t1.get(i).getTime1().getTime())+ "-" + sdf1.format(t1.get(i).getTime2().getTime()),
					 t1.get(i).getName(),t1.get(i).getResource().getName(),t1.get(i).getState().toString()} ; 
		 }
		JFrame jf = new JFrame(s  + "(��ǰʱ��)" + " , " + location.getName());
		Show.show(columNames, rowData, jf);
	}
	
	/**
	 * ʵ�ֵ�����
	 */
	@Override
	public Iterator<PlanningEntry> iterator() {
		return new PlanningEntryInteator() ;
	}
	/**
	 * �Լ�ʵ�ֵĵ�����
	 */
	public class PlanningEntryInteator implements Iterator<PlanningEntry> {
		private List<CourseEntry> course1 = getCourse() ;//��ȡ�͵�ǰ�ƻ������ڵ�λ����صļƻ���
		private int curror = -1;//
		private int size = course1.size() ;//�ƻ��������
		@Override
		public boolean hasNext() {
			return curror + 1 < size ;
		}

		@Override
		public PlanningEntry next() {
			curror ++ ;
			return course1.get(curror) ;
		}
	}

}
