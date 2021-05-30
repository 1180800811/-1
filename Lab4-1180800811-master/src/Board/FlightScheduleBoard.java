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
import PlanningEntry.FlightEntry;
import PlanningEntry.PlanningEntry;

public class FlightScheduleBoard implements Iterable<PlanningEntry>{
	/*
	 * RI: 
	 * time , location , flight is not null
	 * 
	 * Abstraction function:
	 * 代表一个信息板，由信息板的位置、所有的航班、当前时间组成
	 * 	Safety from rep exposure:
	 *	all the field is private and immutable
	 *	there is no exposure of all the fields
	 * 
	 */
	private Calendar time = Calendar.getInstance() ;//屏幕版当前时间
	private Location location ; //屏幕板所在的位置
	private List<FlightEntry> flight = new ArrayList<FlightEntry>() ;//一系列的计划项
	
	/**
	 * 构造器初始化
	 * @param location 信息板的位置
	 * @param flight 所有的航班
	 * @param time 当前时间
	 */
	public FlightScheduleBoard(Location location, List<FlightEntry> flight , Calendar time) {
		super();
		this.location = location;
		this.flight = flight;
		this.time = time ;
		Collections.sort(flight, new FlightCompare1());//按照航班起航时间递增排序
	}
	
	/**
	 * 获得所有出发位置在location并且按照出发时间递增排序的航班
	 */
	public List<FlightEntry> getFlight1(){
		List<FlightEntry> t = new ArrayList<>() ;//所有出发位置是location的航班
		for(int i = 0 ; i < flight.size() ; i ++  ) {
			if(flight.get(i).getStartLocation().equals(location)) {
				t.add(flight.get(i));
				changeState(flight.get(i));//根据当前时间改变状态
			}
		}
		Collections.sort(t, new FlightCompare1());//按时间排序
		return t ;
	}

	/**
	 * 获得所有到达位置在location并且按照到达时间递增排序的航班
	 */
	public List<FlightEntry> getFlight2(){
		List<FlightEntry> t = new ArrayList<>() ;//所有抵达位置是location的航班
		for(int i = 0 ; i < flight.size() ; i ++  ) {
			if(flight.get(i).getEndLocation().equals(location)) {
				t.add(flight.get(i));
				changeState(flight.get(i));//根据当前时间改变状态
			}
		}
		Collections.sort(t, new FlightCompare2());//按时间排序
		return t ;
	}
	

	
	/**
	 * 根据当前的时间来设置航班的状态
	 * @param flightss 待设置状态的航班
	 */
	public  void changeState(FlightEntry flight) {
		if(flight.getResource() == null ) {
			flight.setState(WAITTING.instance );//课程还未分配教师
			return ;
		}
		if(time.compareTo(flight.getTime1()) < 0) {
			flight.setState(ALOCATED.instance );//课程还未发开始，设置状态为ALOCATED
			return ;
		}else if(time.compareTo(flight.getTime2()) > 0) {
			flight.setState(ENDED.instance );//课程已经结束,设置状态为ENDED
			return ;
		}else {
			flight.setState(RUNNING.instance );//课程正在进行，设置状态为RUNNING
			return ;
		}	
	}

	/**
	 * 展示出所有与该位置相关的航班信息
	 */
	public void show() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		String s = sdf.format(time.getTime());//当前时间
		List<FlightEntry> flights = new ArrayList<FlightEntry>();//所有和该位置相关的航班
		flights.addAll(getFlight2());
		flights.addAll(getFlight1());
		String[] columNames = { "序号" , "时间" , "航班" ,"行程" , "状态"} ;//表格第一行信息
		Object[][] rowData = new String[flights.size()][] ;
		int i = 0 ;
		for( ; i <getFlight2().size() ; i++) {
			rowData[i] = new String[] {String.valueOf(i+1) ,flights.get(i).getBeginEndTime().getDate1() + "  ->  " + flights.get(i).getBeginEndTime().getDate2()
					,flights.get(i).getName() , flights.get(i).getStartLocation().getName() + "-" +	flights.get(i).getEndLocation().getName()  , flights.get(i).getState().toString() 
				 };
		}
		for( ; i <flights.size() ; i++) {
			rowData[i] = new String[] {String.valueOf(i+1) ,flights.get(i).getBeginEndTime().getDate1() + "  ->  " + flights.get(i).getBeginEndTime().getDate2()
					,flights.get(i).getName() , flights.get(i).getStartLocation().getName() + "-" +	flights.get(i).getEndLocation().getName()  , flights.get(i).getState().toString() 
				 };
		}
		JFrame jf = new JFrame(s  + "(当前时间)" + " , " + location.getName()+ "机场");
		Show.show(columNames, rowData, jf);//可视化

		
	}
	/**
	 * 可视化展示出当前位置的信息板
	 * @param x 0 ：展示抵达航班 ， 1 :展示出发航班
	 */
	public void visualize(int x ) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); //时间形式
		String s = sdf.format(time.getTime());
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm"); //时间形式
		List<FlightEntry> flights = new ArrayList<FlightEntry>();
		if(x==0 ) {
			flights = getFlight2() ;//所有抵达位置是location的航班
		}else {
			flights = getFlight1() ;//所有出发位置是location的航班
		}
		List<FlightEntry> flightss = new ArrayList<FlightEntry>();
		for(int i = 0  ; i < flights.size() ; i ++	) {
			if(Math.abs((x == 0 ? flights.get(i).getTime2().getTime() :flights.get(i).getTime1().getTime()).getTime() - time.getTime().getTime()) <= 3600000 ) {
				flightss.add(flights.get(i));
			}
		}
		String[] columNames = { "序号" , "时间" , "航班" ,"行程" , "状态"} ;//表格第一行信息
		Object[][] rowData = new String[flightss.size()][] ;
		for(int i = 0  ; i < flightss.size() ; i ++	) {
			 rowData[i] = new String[] {String.valueOf(i+1) ,sdf1.format( x == 0 ? flightss.get(i).getTime2().getTime() :flightss.get(i).getTime1().getTime())
				,flightss.get(i).getName() , flightss.get(i).getStartLocation().getName() + "-" +	flightss.get(i).getEndLocation().getName()  , flightss.get(i).getState().toString() 
			 };
		 }
		JFrame jf = null ;
		if(x == 0  )
			jf = new JFrame(s  + "(当前时间)" + " , " + location.getName()+ "机场" + "   抵达航班");
		else
		   jf = new JFrame(s  + "(当前时间)" + " , " + location.getName()+ "机场" + "   出发航班");
		Show.show(columNames, rowData, jf);//可视化
	}

	/**
	 * 迭代器
	 */
	@Override
	public Iterator<PlanningEntry> iterator() {
		return new PlanningEntryInteator() ;
	}
	/**
	 * 
	 * 自己实现的迭代器
	 */
	public class PlanningEntryInteator implements Iterator<PlanningEntry>{
		private List<FlightEntry> flight1 =  getFlight2() ;//抵达位置是location的所有航班
		private List<FlightEntry> flight2 =  getFlight1() ;//出发位置是location的所有航班
		private int curror = -1;
		private int size1 = flight1.size() ; //抵达位置是location的航班个数
		private int size2 = flight2.size();//出发位置是location的航班的个数
		@Override
		public boolean hasNext() {
			return curror + 1 < (size1 + size2) ;
		}
		
		@Override
		public PlanningEntry next() {
			curror ++ ;
			if(curror < size1)
				return flight1.get(curror) ;//先遍历抵达航班
			else
				return flight2.get(curror-size1);//再遍历出发航班
		}
	}
}
