package applications;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;

import APIs.PlanningEntryAPIs;
import APIs.checkResourceExclusiveConflict1;
import Board.CourseCalendarBoard;
import Board.CourseCompare;
import Board.FlightCompare1;
import Board.Show;
import PlanningEntry.*;
import Location.Location;
import Location.SingleLocationEntryImpl;
import Resource.Plane;
import Resource.SingleDistinguishResourceEntryImpl;
import Resource.Teacher;
import Timeslot.Timeslot;
import EntryState.*;

public class CourseCalendar {
	/*
	 * RI: 
	 * resource , location , courseEntry is not null
	 * 
	 * Abstraction function:
	 * ����һ���γ̹�����һЩ�еĿγ̡����õĽ�ʦ�����õĽ���λ�ú͵�ǰʱ�����
	 * 	Rep:
	 *	all the field is private and immutable
	 *	there is no exposure of all the fields
	 * 
	 */
	
	private List<Teacher> resource = new ArrayList<Teacher>() ;//���õ���Դ
	private List<Location> location = new ArrayList<Location>() ;//���õ�λ��
	private List<CourseEntry> courseEntry =  new ArrayList<CourseEntry>()  ;//һϵ�мƻ���
	private Calendar time = Calendar.getInstance() ;//��ǰʱ��
	
	/**
	 * ���õ�ǰʱ��
	 * @param time ��ǰʱ��
	 */
	public void setTime(Calendar time) {
		this.time  = time ;
	}
	/**
	 * ���������Ĺ�����
	 */
	public CourseCalendar() {
		super();
	}

	/**
	 * ����һ���µļƻ���
	 * @param loc λ��
	 * @param time1 ��ʼʱ��
	 * @param time2 ��ֹʱ��
	 * @param name �γ�����
	 */
	public boolean addPlanningEntry(String name ,Location loc , Calendar time1 , Calendar time2 ) throws Exception {
		if(!location.contains(loc))
			this.location.add(loc);
		Timeslot time = new Timeslot(time1, time2);
		SingleDistinguishResourceEntryImpl<Teacher> t = new SingleDistinguishResourceEntryImpl<Teacher>();
		t.setResource(null);
		SingleLocationEntryImpl l = new SingleLocationEntryImpl();
		l.setLocation(loc);
		UnBlockableEntryImpl ti = new UnBlockableEntryImpl();
		ti.setTime(time);
		CourseEntry course = new CourseEntry(name, l, ti, t);
		this.courseEntry.add(course);
		if(!check()) {//�ƻ�����ڳ�ͻ
			courseEntry.remove(course);//���ڳ�ͻ���Ƴ��ƻ���
			return false;
		}
		return true;
		
	}
	/**
	 * ȡ��ĳ�ſγ�
	 * @param Name �γ̵�����
	 * @param time1  �γ̿�ʼʱ��
	 * @param time2 �γ̽���ʱ��
	 * @param loc �γ̵Ľ���λ��
	 */
	public String cancelPlanningEntry(String Name ,Calendar time1 , Calendar time2  , Location loc)  {
		for(CourseEntry e : courseEntry	) {
			if(e.getName().equals(Name)) {//�γ�������ͬ
				if(e.getTime1().equals(time1)) {//�γ̿�ʼʱ����ͬ
					if(e.getTime2().equals(time2)) {//�γ̽���ʱ����ͬ
						if(e.getLocation().equals(loc)) {//�γ̽�����ͬ
							if(e.getState() instanceof ALOCATED || e.getState() instanceof WAITTING) {
								 e.setState(CANCELLED.instance);//��״̬����Ϊȡ��
								 return "�γ��Ѿ�ȡ��";
							 }else {
								 return "�γ̵�ǰ״̬Ϊ" +e.getState().toString() + ",����ȡ��" ;
							 }
							
						}
					}
				}
			}
		}
		return "ָ���Ŀγ̲�����";
	}
	/**
	 * ��ĳ�ſγ̷����ʦ
	 * @param Name �γ�����
	 * @param idNumber �����֤��
 	 * @param name ��ʦ����
	 * @param sex ��ʦ�Ա�
	 * @param title ��ʦְ��
	 * @return 0 :����ɹ� �� 1 ��ָ���Ŀγ��Ѿ������ʦ �� 2 ���Ҳ���ָ���Ŀγ� �� 3��������Դ��ͻ ,4 :������Ľ�ʦ���ڿ��õĽ�ʦ��
	 */
	public int FeiPeiResource(String Name , String idNumber , String name ,boolean sex , String title	) {
		int x = 0 ;
		Teacher teacher = new Teacher(idNumber, name, sex, title);//������Ľ�ʦ
		if(!resource.contains(teacher)) {
			return 4;//������Ľ�ʦ���ڿ��ý�ʦ��
		}
		CourseEntry c = null ;
		for(CourseEntry e : courseEntry	) {
			if(e.getName().equals(Name)) {
				c = e ;
				if(e.getResource() !=null) {
					x =  1 ;//ָ���Ŀγ��Ѿ������ʦ
				}else {
					x = 0 ; //ָ���Ŀγ�δ�����ʦ
					break ;
				}

			}
		}
		
		if(c == null) {
			return 2 ;//�Ҳ���ָ���Ŀγ�
		}
		if(x == 1)
			return x ;//��������ΪName�Ŀγ̾������˽�ʦ
		c.setResource(teacher);
		List<PlanningEntry> s = new ArrayList<PlanningEntry>() ;
		s.addAll(courseEntry);
		if(!APIs.PlanningEntryAPIs.checkResourceExclusiveConflict(s, new checkResourceExclusiveConflict1())) {//�ƻ�����ڳ�ͻ
			return 3 ;//������Դ��ͻ
		}
		 return 0 ;//����ɹ�
	}
	/**
	 * ����ĳ�ſγ�
	 * @param Name �������Ŀγ�����
	 * @param time1 �������Ŀγ̿�ʼʱ��
	 * @param time2 �������Ŀγ̽���ʱ��
	 * @param loc �������Ŀγ̵�λ��
	 * @return �����Ϣ
	 */
	public String BeginPlanningEntry(String Name ,Calendar time1 , Calendar time2  , Location loc) {
		for(CourseEntry e : courseEntry	) {
			if(e.getName().equals(Name)) {
				if(e.getTime1().equals(time1)) {
					if(e.getTime2().equals(time2)) {
						if(e.getLocation().equals(loc)) {
							if(e.getState() instanceof ALOCATED) {
								 e.setState(RUNNING.instance);//�γ��Ѿ�������״̬
								 return "�γ��Ѿ�����";
							 }else {
								 return "�γ̵�ǰ״̬Ϊ" +e.getState().toString() + ",��������" ;//�γ̵�ǰ״̬�޷�����
							 }
						}
					}
				}
			}
		}
		return "ָ���Ŀγ̲�����";
	}
	/**
	 * �ı�ĳ�ſγ̵�λ��
	 * @param Name ���ı�Ŀγ̵�����
	 * @param loc1 ���ı�Ŀγ�ԭ����λ��
	 * @param loc2 ���ı�Ŀγ̺��λ��
	 * @return true: �ı�ɹ� ��false:δ�ҵ��ÿγ�
	 */
	public boolean changeLocationEntry(String Name , Location loc1 , Location loc2) {
		for(CourseEntry e : courseEntry) {
			if(e.getName().equals(Name)) {
				if(e.getLocation().equals(loc1)) {
					e.setLocation(loc2);
					this.addLocation(loc2);
					return true ;
				}else {//û�ҵ��ÿγ�
					return false ;
				}
			}
		}
		return false ;//û�ҵ��ÿγ�
	}
	
	/**
	 * ���ӿ��õ�λ��
	 * @param loc λ��
	 * @return true:���ӳɹ� ,false :����ʧ��
	 */
	public boolean addLocation(Location loc) {
		if(location.contains(loc)) {
			return false;
		}
		location.add(loc);
		return true ;
	}
	/**
	 * ɾ�����õ�λ��
	 * @param loc λ��
	 * @return true:ɾ���ɹ� ,false :ɾ��ʧ��
	 */
	public boolean deleteLocation(Location loc) {
		if(location.contains(loc)) {
			location.remove(loc);
			return true;
		}

		return false ;
	}
	
	/**
	 * ����ĳ�ſγ�
	 * @param Name  �������Ŀγ̵�����
 	 * @param time1 �������Ŀγ̵Ŀ�ʼʱ��
	 * @param time2 �������Ŀγ̵Ľ���ʱ��
 	 * @param loc �������Ŀγ̵�λ��
	 * @return �����ʾ��Ϣ
	 */
	public String EndPlanningEntry(String Name ,Calendar time1 , Calendar time2  , Location loc){
		for(CourseEntry e : courseEntry	) {
			if(e.getName().equals(Name)) {
				if(e.getTime1().equals(time1)) {
					if(e.getTime2().equals(time2)) {
						if(e.getLocation().equals(loc)) {
							if(e.getState() instanceof  RUNNING) {
								 e.setState(ENDED.instance);
								 return "�γ��Ѿ�����";
							 }else {
								 return "�γ̵�ǰ״̬Ϊ" +e.getState().toString() + ",���ܽ���" ;
							 }
						}
					}
				}
			}
		}
		return "ָ���Ŀγ̲�����";
	}
	/**
	 * �鿴ĳ���ƻ����״̬
	 * @param name �ƻ��������
	 * @param time1 ��ǰʱ��
	 */
	public void WatchState(String name , Calendar time1)  {
		this.time = time1 ;
		List<CourseEntry> f = new ArrayList<CourseEntry>() ;//��ȡ��������Ϊname�Ŀγ�
		for(CourseEntry e : courseEntry	 ) {
			if(e.getName().equals(name)) {
				changeState(e);//���ݵ�ǰʱ��ı�״̬
				f.add(e);
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		Collections.sort(f , new CourseCompare());//��ʱ������
		String[] columNames = { "���" , "ʱ��" , "�γ�" , "״̬"} ;//����һ����Ϣ
		Object[][] rowData = new String[f.size()][] ;
		for(int i = 0 ; i < f.size(); i ++) {
			rowData[i] = new String[]{ String.valueOf(i+1) ,sdf.format(f.get(i).getTime1().getTime())+ "-" + sdf.format(f.get(i).getTime2().getTime()),f.get(i).getName(),f.get(i).getState().toString()} ;
		}
		String s = sdf.format(time.getTime());
		JFrame jf = new JFrame(s + "(��ǰʱ��)" + " , " );
		Show.show(columNames, rowData, jf);	//���ӻ���ʾ�ƻ������Ϣ
	}
	
	/**
	 * �õ�ʹ�ú�ĳ���ƻ���ʹ����ͬ��ĳ����Դ��ʱ����ӽ���ǰ��ƻ���
	 * @param name ��ʦ������
	 * @param Number  ��ʦ�����֤��
	 * @param s ��ʦ���Ա�
	 * @param title ��ʦ��ְ��
	 * @param time1 ��ǰʱ��
	 */
	public void getResourcePlanningEntry(String name , String Number , boolean s , String title, Calendar time1) {
		Teacher t = new Teacher(Number, name, s, title);
		this.time = time1 ;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		List<CourseEntry> course = new ArrayList<CourseEntry>() ;
		for(CourseEntry e :courseEntry) {
			if(e.getResource().equals(t)) {
				changeState(e);
				course.add(e);
			}
		}
		Collections.sort(course, new CourseCompare());//��ʱ������
		int size = course.size() ;
		String[] columNames = { "���" , "ʱ��" , "�γ�" ,"��ʦ", "״̬"} ;//����һ����Ϣ
		Object[][] rowData = new String[size][] ;
		for(int i = 0 ; i < course.size(); i ++) {
			rowData[i] = new String[] { String.valueOf(i+1) ,sdf.format(course.get(i).getTime1().getTime())+ "-" + sdf.format(course.get(i).getTime2().getTime()),course.get(i).getName(),course.get(i).getResource().getName(),course.get(i).getState().toString()};
		}
		String ss = sdf.format(time.getTime());
		JFrame jf = new JFrame(ss + "(��ǰʱ��)" + " , " );
		Show.show(columNames, rowData, jf);
	}
	
	/**
	 * ���ӻ�չʾ��ĳ�����ҵ����пγ���Ϣ
	 * @param loc ����λ�� 
	 * @param time1 ��ǰʱ��
	 */
	public void show(Location loc, Calendar time1 ) {
		this.time = time1 ;
		CourseCalendarBoard board = new CourseCalendarBoard(loc, courseEntry, time1) ;
		board.show();
	}
	
	/**
	 * ����ĳ�����õĽ�ʦ
	 * @param name �����ӵĽ�ʦ������
	 * @param Number �����ӵĽ�ʦ�����֤��
 	 * @param s �����ӵĽ�ʦ���Ա�
	 * @param title �����ӵĽ�ʦ��ְ��
	 * @return true:���ӳɹ� �� false :ʧ��
	 */
	public boolean addResource(String name , String Number , boolean s , String title) {
		Teacher tea = new Teacher(Number, name, s, title);
		if(!resource.contains(tea)) {
			resource.add(tea);
			return true ;
		}
		return false ;
		
	}
	/**
	 * ɾ��ĳ�����õĽ�ʦ
	 * @param name ��ʦ������
	 * @param Number ��ʦ���֤��
	 * @param s ��ʦ�Ա�
	 * @param title ��ʦְ��
	 * @return true: ɾ���ɹ� �� false :ɾ��ʧ��
	 */
	public boolean deleteResource(String name , String Number , boolean s , String title)  {
		Teacher tea = new Teacher(Number, name, s, title);
		if(!resource.contains(tea)) {
			return false ;
		}
		resource.remove(tea);
		return true ;
	}
	/**
	 * ���ӻ��г����õ���Դ����Ϣ
	 */
	public void showResource() {

		String[] columNames = { "���" , "���е���Դ"} ;//����һ����Ϣ
		Object[][] rowData = new String[resource.size()][] ;
		for(int i = 0 ; i < resource.size() ; i ++) {
			rowData[i] = new String[]{ String.valueOf(i+1) , resource.get(i).toString()} ;
		}
		JFrame jf =new JFrame("���п��õĽ�ʦ����Ϣ");
		Show.show(columNames, rowData, jf);//���ӻ�
	}
	/**
	 * ���ӻ��г����õ�λ�õ���Ϣ
	 */
	public void showLocation() {
		String[] columNames = { "���" , "���еĿ��õĽ���"} ;//����һ����Ϣ
		Object[][] rowData = new String[location.size()][] ;
		for(int i = 0 ; i < location.size() ; i ++) {
			rowData[i] = new String[]{ String.valueOf(i+1) , location.get(i).getName()} ;
		}
		JFrame jf =new JFrame("���п��õĽ��ҵ���Ϣ");
		Show.show(columNames, rowData, jf);//���ӻ�
	}
	
	/**
	 * �ı�ĳ�ſγ̵�״̬
	 * @param course �γ�
	 */
	public  void changeState(CourseEntry course) {
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
	 * չʾ��ǰλ�õ�ǰʱ�����Ϣ��(ĳ����ʦ�ĵ���Ŀγ���Ϣ)
	 * @param loc ��ǰλ��
	 * @param time1 ��ǰʱ��
	 */
	public void board(Location loc ,Calendar time1 ) {
		time= time1 ;
		CourseCalendarBoard Board = new CourseCalendarBoard(loc, courseEntry, time1) ;
		Board.visualize();
	}
	/**
	 * ���õ������������к͵�ǰλ�õ�ǰ��һ����صĿγ�
	 * @param loc ��ǰλ��
	 * @param time1 ��ǰ��һ��
	 */
	public void showFlightEntry(Location loc ,Calendar time1 ) {
		CourseCalendarBoard Board = new CourseCalendarBoard(loc, courseEntry, time1) ;
		Iterator<PlanningEntry> it = Board.iterator();
		while(it.hasNext()) {
			PlanningEntry<Plane> s = it.next() ;
			System.out.println(  s.getName() + "\t\t" +  s.getBeginEndTime() + "\t" + s.getTimeLocation().get(s.getBeginEndTime()) +"\t" + s.getState());
		}
		
	}
	/**
	 * �õ�ĳ����ĳ���γ�ʹ����ͬ��ʦ��ǰ��γ�
	 * @param Name �γ̵�����
	 * @param loc �γ̵Ľ���
	 * @param name ��ʦ������
	 * @param idNumber ��ʦ�����֤��
	 * @return true : ����ǰ��γ� , false :������ǰ��γ�
	 */
	public boolean getPrePlanningEntry(String Name ,  Location loc ,String name ,String idNumber ) {
		Teacher teacher = null ;
		for(int i = 0 ; i < resource.size() ; i++) {
			if(resource.get(i).getName().equals(name)) {
				if(resource.get(i).getIdNumber().equals(idNumber)) {
					teacher = resource.get(i);
				}
			}
		}
		CourseEntry course = null ;
		for(int j = 0 ; j < courseEntry.size() ; j ++) {
			if(courseEntry.get(j).getName().equals(Name)) {
				if(courseEntry.get(j).getLocation().equals(loc)) {
					course = courseEntry.get(j) ;
				}
					
			}
		}
		if(course == null || teacher ==null)
			return false ;
		PlanningEntry<Teacher> tt =  PlanningEntryAPIs.findPreEntryPerResource(teacher, course, courseEntry) ;
		if(tt == null) {
			return false ;
		}else {
			String[] columNames = { "ʱ��" , "�γ�" ,"��ʦ", "״̬"} ;//����һ����Ϣ
			Object[][] rowData = new String[1][] ;
			Location loc1 = tt.getTimeLocation().get(tt.getBeginEndTime()).get(0);
			rowData[0] = new String[] {tt.getBeginEndTime().getDate1() + "->" + tt.getBeginEndTime().getDate2() , tt.getName() , loc1.getName() , tt.getState().toString()};
			JFrame jf =new JFrame("�����ǰ��ƻ���");
			Show.show(columNames, rowData, jf);
		}
		return true ;
			
	}
	/**
	 * ���ƻ����Ƿ������Դ��ͻ��λ�ó�ͻ
	 * @return true : �����ڳ�ͻ��false: ���ڳ��ͻ
	 */
	public boolean check() {
		List<PlanningEntry> course = new ArrayList<>() ;
		course.addAll(courseEntry);
		if(!APIs.PlanningEntryAPIs.checkLocationConflict(course) || !APIs.PlanningEntryAPIs.checkResourceExclusiveConflict(course,new checkResourceExclusiveConflict1())) {//��Դ����λ�ô��ڳ�ͻ
			return false ;
		}
		return true ;
	}
}
