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
	 * 代表一个课程管理，由一些列的课程、可用的教师、可用的教室位置和当前时间组成
	 * 	Rep:
	 *	all the field is private and immutable
	 *	there is no exposure of all the fields
	 * 
	 */
	
	private List<Teacher> resource = new ArrayList<Teacher>() ;//可用的资源
	private List<Location> location = new ArrayList<Location>() ;//可用的位置
	private List<CourseEntry> courseEntry =  new ArrayList<CourseEntry>()  ;//一系列计划项
	private Calendar time = Calendar.getInstance() ;//当前时间
	
	/**
	 * 设置当前时间
	 * @param time 当前时间
	 */
	public void setTime(Calendar time) {
		this.time  = time ;
	}
	/**
	 * 不带参数的构造器
	 */
	public CourseCalendar() {
		super();
	}

	/**
	 * 增加一条新的计划项
	 * @param loc 位置
	 * @param time1 起始时间
	 * @param time2 终止时间
	 * @param name 课程名称
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
		if(!check()) {//计划项存在冲突
			courseEntry.remove(course);//存在冲突则移除计划项
			return false;
		}
		return true;
		
	}
	/**
	 * 取消某门课程
	 * @param Name 课程的名字
	 * @param time1  课程开始时间
	 * @param time2 课程结束时间
	 * @param loc 课程的教室位置
	 */
	public String cancelPlanningEntry(String Name ,Calendar time1 , Calendar time2  , Location loc)  {
		for(CourseEntry e : courseEntry	) {
			if(e.getName().equals(Name)) {//课程名字相同
				if(e.getTime1().equals(time1)) {//课程开始时间相同
					if(e.getTime2().equals(time2)) {//课程结束时间相同
						if(e.getLocation().equals(loc)) {//课程教室相同
							if(e.getState() instanceof ALOCATED || e.getState() instanceof WAITTING) {
								 e.setState(CANCELLED.instance);//将状态设置为取消
								 return "课程已经取消";
							 }else {
								 return "课程当前状态为" +e.getState().toString() + ",不能取消" ;
							 }
							
						}
					}
				}
			}
		}
		return "指定的课程不存在";
	}
	/**
	 * 给某门课程分配教师
	 * @param Name 课程名称
	 * @param idNumber 教身份证号
 	 * @param name 教师姓名
	 * @param sex 教师性别
	 * @param title 教师职称
	 * @return 0 :分配成功 ， 1 ：指定的课程已经分配教师 ， 2 ：找不到指定的课程 ， 3：存在资源冲突 ,4 :待分配的教师不在可用的教师内
	 */
	public int FeiPeiResource(String Name , String idNumber , String name ,boolean sex , String title	) {
		int x = 0 ;
		Teacher teacher = new Teacher(idNumber, name, sex, title);//待分配的教师
		if(!resource.contains(teacher)) {
			return 4;//待分配的教师不在可用教师内
		}
		CourseEntry c = null ;
		for(CourseEntry e : courseEntry	) {
			if(e.getName().equals(Name)) {
				c = e ;
				if(e.getResource() !=null) {
					x =  1 ;//指定的课程已经分配教师
				}else {
					x = 0 ; //指定的课程未分配教师
					break ;
				}

			}
		}
		
		if(c == null) {
			return 2 ;//找不到指定的课程
		}
		if(x == 1)
			return x ;//所有名字为Name的课程均分配了教师
		c.setResource(teacher);
		List<PlanningEntry> s = new ArrayList<PlanningEntry>() ;
		s.addAll(courseEntry);
		if(!APIs.PlanningEntryAPIs.checkResourceExclusiveConflict(s, new checkResourceExclusiveConflict1())) {//计划项存在冲突
			return 3 ;//存在资源冲突
		}
		 return 0 ;//分配成功
	}
	/**
	 * 启动某门课程
	 * @param Name 待启动的课程名称
	 * @param time1 待启动的课程开始时间
	 * @param time2 待启动的课程结束时间
	 * @param loc 待启动的课程的位置
	 * @return 相关信息
	 */
	public String BeginPlanningEntry(String Name ,Calendar time1 , Calendar time2  , Location loc) {
		for(CourseEntry e : courseEntry	) {
			if(e.getName().equals(Name)) {
				if(e.getTime1().equals(time1)) {
					if(e.getTime2().equals(time2)) {
						if(e.getLocation().equals(loc)) {
							if(e.getState() instanceof ALOCATED) {
								 e.setState(RUNNING.instance);//课程已经是启动状态
								 return "课程已经启动";
							 }else {
								 return "课程当前状态为" +e.getState().toString() + ",不能启动" ;//课程当前状态无法启动
							 }
						}
					}
				}
			}
		}
		return "指定的课程不存在";
	}
	/**
	 * 改变某门课程的位置
	 * @param Name 待改变的课程的名字
	 * @param loc1 待改变的课程原来的位置
	 * @param loc2 待改变的课程后的位置
	 * @return true: 改变成功 ，false:未找到该课程
	 */
	public boolean changeLocationEntry(String Name , Location loc1 , Location loc2) {
		for(CourseEntry e : courseEntry) {
			if(e.getName().equals(Name)) {
				if(e.getLocation().equals(loc1)) {
					e.setLocation(loc2);
					this.addLocation(loc2);
					return true ;
				}else {//没找到该课程
					return false ;
				}
			}
		}
		return false ;//没找到该课程
	}
	
	/**
	 * 增加可用的位置
	 * @param loc 位置
	 * @return true:增加成功 ,false :增加失败
	 */
	public boolean addLocation(Location loc) {
		if(location.contains(loc)) {
			return false;
		}
		location.add(loc);
		return true ;
	}
	/**
	 * 删除可用的位置
	 * @param loc 位置
	 * @return true:删除成功 ,false :删除失败
	 */
	public boolean deleteLocation(Location loc) {
		if(location.contains(loc)) {
			location.remove(loc);
			return true;
		}

		return false ;
	}
	
	/**
	 * 结束某门课程
	 * @param Name  待结束的课程的名字
 	 * @param time1 待结束的课程的开始时间
	 * @param time2 待结束的课程的结束时间
 	 * @param loc 待结束的课程的位置
	 * @return 相关提示信息
	 */
	public String EndPlanningEntry(String Name ,Calendar time1 , Calendar time2  , Location loc){
		for(CourseEntry e : courseEntry	) {
			if(e.getName().equals(Name)) {
				if(e.getTime1().equals(time1)) {
					if(e.getTime2().equals(time2)) {
						if(e.getLocation().equals(loc)) {
							if(e.getState() instanceof  RUNNING) {
								 e.setState(ENDED.instance);
								 return "课程已经结束";
							 }else {
								 return "课程当前状态为" +e.getState().toString() + ",不能结束" ;
							 }
						}
					}
				}
			}
		}
		return "指定的课程不存在";
	}
	/**
	 * 查看某个计划项的状态
	 * @param name 计划项的名字
	 * @param time1 当前时间
	 */
	public void WatchState(String name , Calendar time1)  {
		this.time = time1 ;
		List<CourseEntry> f = new ArrayList<CourseEntry>() ;//获取所有名字为name的课程
		for(CourseEntry e : courseEntry	 ) {
			if(e.getName().equals(name)) {
				changeState(e);//根据当前时间改变状态
				f.add(e);
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		Collections.sort(f , new CourseCompare());//按时间排序
		String[] columNames = { "序号" , "时间" , "课程" , "状态"} ;//表格第一行信息
		Object[][] rowData = new String[f.size()][] ;
		for(int i = 0 ; i < f.size(); i ++) {
			rowData[i] = new String[]{ String.valueOf(i+1) ,sdf.format(f.get(i).getTime1().getTime())+ "-" + sdf.format(f.get(i).getTime2().getTime()),f.get(i).getName(),f.get(i).getState().toString()} ;
		}
		String s = sdf.format(time.getTime());
		JFrame jf = new JFrame(s + "(当前时间)" + " , " );
		Show.show(columNames, rowData, jf);	//可视化显示计划项的信息
	}
	
	/**
	 * 得到使用和某个计划项使用相同的某个资源的时间最接近的前序计划项
	 * @param name 教师的名字
	 * @param Number  教师的身份证号
	 * @param s 教师的性别
	 * @param title 教师的职称
	 * @param time1 当前时间
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
		Collections.sort(course, new CourseCompare());//按时间排序
		int size = course.size() ;
		String[] columNames = { "序号" , "时间" , "课程" ,"教师", "状态"} ;//表格第一行信息
		Object[][] rowData = new String[size][] ;
		for(int i = 0 ; i < course.size(); i ++) {
			rowData[i] = new String[] { String.valueOf(i+1) ,sdf.format(course.get(i).getTime1().getTime())+ "-" + sdf.format(course.get(i).getTime2().getTime()),course.get(i).getName(),course.get(i).getResource().getName(),course.get(i).getState().toString()};
		}
		String ss = sdf.format(time.getTime());
		JFrame jf = new JFrame(ss + "(当前时间)" + " , " );
		Show.show(columNames, rowData, jf);
	}
	
	/**
	 * 可视化展示和某个教室的所有课程信息
	 * @param loc 教室位置 
	 * @param time1 当前时间
	 */
	public void show(Location loc, Calendar time1 ) {
		this.time = time1 ;
		CourseCalendarBoard board = new CourseCalendarBoard(loc, courseEntry, time1) ;
		board.show();
	}
	
	/**
	 * 增加某个可用的教师
	 * @param name 待增加的教师的姓名
	 * @param Number 待增加的教师的身份证号
 	 * @param s 待增加的教师的性别
	 * @param title 待增加的教师的职称
	 * @return true:增加成功 ， false :失败
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
	 * 删除某个可用的教师
	 * @param name 教师的姓名
	 * @param Number 教师身份证号
	 * @param s 教师性别
	 * @param title 教师职称
	 * @return true: 删除成功 ， false :删除失败
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
	 * 可视化列出可用的资源的信息
	 */
	public void showResource() {

		String[] columNames = { "序号" , "所有的资源"} ;//表格第一行信息
		Object[][] rowData = new String[resource.size()][] ;
		for(int i = 0 ; i < resource.size() ; i ++) {
			rowData[i] = new String[]{ String.valueOf(i+1) , resource.get(i).toString()} ;
		}
		JFrame jf =new JFrame("所有可用的教师的信息");
		Show.show(columNames, rowData, jf);//可视化
	}
	/**
	 * 可视化列出可用的位置的信息
	 */
	public void showLocation() {
		String[] columNames = { "序号" , "所有的可用的教室"} ;//表格第一行信息
		Object[][] rowData = new String[location.size()][] ;
		for(int i = 0 ; i < location.size() ; i ++) {
			rowData[i] = new String[]{ String.valueOf(i+1) , location.get(i).getName()} ;
		}
		JFrame jf =new JFrame("所有可用的教室的信息");
		Show.show(columNames, rowData, jf);//可视化
	}
	
	/**
	 * 改变某门课程的状态
	 * @param course 课程
	 */
	public  void changeState(CourseEntry course) {
		if(course.getResource() == null ) {
			course.setState(WAITTING.instance );//课程还未分配教师
			return ;
		}
		if(time.compareTo(course.getTime1()) < 0) {
			course.setState(ALOCATED.instance );//课程还未发开始，设置状态为ALOCATED
			return ;
		}else if(time.compareTo(course.getTime2()) > 0) {
			course.setState(ENDED.instance );//课程已经结束,设置状态为ENDED
			return ;
		}else {
			course.setState(RUNNING.instance );//课程正在进行，设置状态为RUNNING
			return ;
		}	
	}
	
	/**
	 * 展示当前位置当前时间的信息板(某个教师的当天的课程信息)
	 * @param loc 当前位置
	 * @param time1 当前时间
	 */
	public void board(Location loc ,Calendar time1 ) {
		time= time1 ;
		CourseCalendarBoard Board = new CourseCalendarBoard(loc, courseEntry, time1) ;
		Board.visualize();
	}
	/**
	 * 利用迭代器遍历所有和当前位置当前这一天相关的课程
	 * @param loc 当前位置
	 * @param time1 当前这一天
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
	 * 得到某个和某个课程使用相同教师的前序课程
	 * @param Name 课程的名称
	 * @param loc 课程的教室
	 * @param name 教师的名字
	 * @param idNumber 教师的身份证号
	 * @return true : 存在前序课程 , false :不存在前序课程
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
			String[] columNames = { "时间" , "课程" ,"教师", "状态"} ;//表格第一行信息
			Object[][] rowData = new String[1][] ;
			Location loc1 = tt.getTimeLocation().get(tt.getBeginEndTime()).get(0);
			rowData[0] = new String[] {tt.getBeginEndTime().getDate1() + "->" + tt.getBeginEndTime().getDate2() , tt.getName() , loc1.getName() , tt.getState().toString()};
			JFrame jf =new JFrame("航班的前序计划项");
			Show.show(columNames, rowData, jf);
		}
		return true ;
			
	}
	/**
	 * 检测计划项是否存在资源冲突和位置冲突
	 * @return true : 不存在冲突，false: 存在充冲突
	 */
	public boolean check() {
		List<PlanningEntry> course = new ArrayList<>() ;
		course.addAll(courseEntry);
		if(!APIs.PlanningEntryAPIs.checkLocationConflict(course) || !APIs.PlanningEntryAPIs.checkResourceExclusiveConflict(course,new checkResourceExclusiveConflict1())) {//资源或者位置存在冲突
			return false ;
		}
		return true ;
	}
}
