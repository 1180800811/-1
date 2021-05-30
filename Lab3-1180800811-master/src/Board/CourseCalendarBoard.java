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
	 * 代表一个信息板，由信息板的位置、所有的课程、当前时间组成
	 * 	Rep:
	 *	all the field is private and immutable
	 *	there is no exposure of all the fields
	 * 
	 */
	private Location location  ;//信息板所在的的位置
	private List<CourseEntry> course = new ArrayList<CourseEntry>() ;//一系列的计划项
	private Calendar time = Calendar.getInstance() ; //当前时间
	
	
	/**
	 * 构造器初始化
	 * @param location  信息板所在的位置
	 * @param course 一系列的课程
	 * @param time 当前时间
	 */
	public CourseCalendarBoard(Location location, List<CourseEntry> course , Calendar time) {
		this.location = location;
		this.course = course;
		this.time  = time ;
		Collections.sort(course , new CourseCompare());
		
	}
	
	/**
	 * 得到所有位置是location的计划项
	 * @return 所有位置是location的按照时间增加的计划项列表
	 */
	public List<CourseEntry> getCourse() {
		List<CourseEntry> t = new ArrayList<>() ;//所有位置是location的课程
		for(int i = 0 ; i < course.size() ; i++) {
			if(course.get(i).getLocation().equals(location)) {
				t.add(course.get(i));
			}
		}
		Collections.sort(t , new CourseCompare());//按时间排序
		for(CourseEntry e : t) {
			changestate(e);//根据当前时间设定课程的状态
		}
		return t ;	
	}
	
	/**
	 * 展示和当前信息板所在的位置相关的所有课程
	 */
	public void show() {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		String s = sdf1.format(time.getTime()); 
		List<CourseEntry> t1  = getCourse() ;//和某个位置相关的所有课程
		String[] columNames = { "序号" , "时间" , "课程" ,"教师" , "状态"} ;//表格第一行信息
		 Object[][] rowData = new String[t1.size()][] ;
		 for(int i = 0  ; i < t1.size() ; i ++	) {
			 rowData[i] = new String[] { String.valueOf(i+1) ,sdf1.format(t1.get(i).getTime1().getTime())+ "-" + 
		 sdf1.format(t1.get(i).getTime2().getTime()),t1.get(i).getName(),t1.get(i).getResource().getName(),t1.get(i).getState().toString()} ; 
		 }
		JFrame jf = new JFrame(s  + "(当前时间)" + " , " + location.getName());
		Show.show(columNames, rowData, jf);
		 
	}

	/**
	 * 判断给定的时间是否是同一天
	 */
	public static boolean isSameDay(Calendar cal1, Calendar cal2) {
		if(cal1 != null && cal2 != null ) {
			return cal1.get(0) == cal2.get(0)  && cal1.get(1) == cal2.get(1)  && cal1.get(6) == cal2.get(6) ;
		}
		return false ;
	}
	/**
	 * 根据当前时间来设置高铁的状态
	 * @param course 课程
	 */
	public  void changestate(CourseEntry course) {
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
	 * 可视化信息板
	 */
	public void visualize() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		String s = sdf.format(time.getTime());
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm"); 
		List<CourseEntry> t  = getCourse() ;
		String[] columNames = { "序号" , "时间" , "课程" ,"教师" , "状态"} ;//表格第一行信息
		List<CourseEntry> t1  = new ArrayList<CourseEntry>() ;//所有和当前时间同一天的课程
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
		JFrame jf = new JFrame(s  + "(当前时间)" + " , " + location.getName());
		Show.show(columNames, rowData, jf);
	}
	
	/**
	 * 实现迭代器
	 */
	@Override
	public Iterator<PlanningEntry> iterator() {
		return new PlanningEntryInteator() ;
	}
	/**
	 * 自己实现的迭代器
	 */
	public class PlanningEntryInteator implements Iterator<PlanningEntry> {
		private List<CourseEntry> course1 = getCourse() ;//获取和当前计划板所在的位置相关的计划项
		private int curror = -1;//
		private int size = course1.size() ;//计划项的数量
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
