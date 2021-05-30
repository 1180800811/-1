package applications;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import APIs.PlanningEntryAPIs;
import APIs.checkResourceExclusiveConflict1;
import Board.FlightCompare1;
import Board.FlightScheduleBoard;
import Board.Show;
import EntryState.*;
import Location.Location;
import Location.TwoLocationEntryImpl;
import PlanningEntry.FlightEntry;
import PlanningEntry.PlanningEntry;
import Resource.Plane;
import Resource.SingleDistinguishResourceEntryImpl;
import Timeslot.Timeslot;
public class FlightSchedule {
	private List<Plane> resource = new ArrayList<Plane>()  ;//可用的资源
	private List<Location> location = new ArrayList<Location>() ;//可用的位置
	private List<FlightEntry> flightEntry =  new ArrayList<FlightEntry>()  ;//一系列计划项
	private Calendar time = Calendar.getInstance();//当前的时间
	/*
	 * RI: 
	 * resource , location , flightEntry is not null
	 * 
	 * Abstraction function:
	 * 代表一个航班管理，由一系列的航班、可用的飞机、可用的机场和当前时间组成
	 * 	Rep:
	 *	all the field is private and immutable
	 *	there is no exposure of all the fields
	 * 
	 */
	
	
	
	/**
	 * 设置当前时间
	 * @param time 当前时间
	 */
	public void setTime(Calendar time ) {
		this.time = time ;
	}
	/**
	 * 
	 * @param loc1 起始位置
	 * @param loc2 终止位置
	 * @param time1 起始时间
	 * @param time2 终止时间
	 * @param name 航班名称
	 */
	public boolean addPlanningEntry(Location loc1 ,Location loc2, Calendar time1 , Calendar time2 ,String name) {

		Plane plane = null ;//计划项使用的飞机
		TwoLocationEntryImpl t = new TwoLocationEntryImpl() ;
		t.setLocations(loc1, loc2);
		Timeslot tt = new Timeslot(time1, time2);
		SingleDistinguishResourceEntryImpl<Plane> pl = new SingleDistinguishResourceEntryImpl<Plane>();
		pl.setResource(plane);
		UnBlockableEntryImpl be = new UnBlockableEntryImpl(tt);
		FlightEntry f = new FlightEntry(name, t, pl, be);
		flightEntry.add(f);
		if(!check()) {
			flightEntry.remove(f);
			return false;
		}
		if(!location.contains(loc1)) {
			location.add(loc1);
		}
		if(!location.contains(loc2)) {
			location.add(loc2);
		}
		return true;
	
	}
	
	/**
	 * 参数为空的构造器
	 */
	public FlightSchedule() {
		super();
	}

	/**
	 * 读入一个文件来初始化航班管理
	 * @param s
	 * @throws Exception
	 */
	public FlightSchedule(String s ) throws Exception {
		ReadFileCreatePlanningEntry(s);
	}


	/**
	 * 取消某个航班
	 * @param palneNumber 待取消的航班号
	 * @param time1 待取消的航班出发时间
	 * @param time2 待取消的航班到达时间
	 * @return 提示相关信息
	 */
	public String cancelPlanningEntry(String palneNumber , Calendar time1 , Calendar time2){
		for(FlightEntry e : flightEntry	) {
			if(e.getName().equals(palneNumber)) {
				if(e.getTime1().equals(time1)) {
					if(e.getTime2().equals(time2)) {
					 if(e.getState() instanceof ALOCATED || e.getState() instanceof WAITTING) {
						 e.setState(CANCELLED.instance);
						 return "航班已经取消";
					 }else {
						 return "航班当前状态为" +e.getState().toString() + ",不能取消" ;
					 }
					}
				}
			}
		}
		return "指定的航班不存在";
	}
	
	/**
	 * 为某个计划项分配资源
	 * @param palneNumber  待分配资源的航班
	 * @param time1 航班的起飞时间
	 * @param time2 航班的终止时间
	 * @param name 飞机的编号
	 * @return 0 : 想要分配的飞机不在可用飞机内 , 1 : 想要分配资源的计划项已经分配资源 2 :分配成功 , 3 :分配资源存在冲突 , 4:想要分配飞机的航班不存在
	 */
	public int FeiPeiResource(String palneNumber , Calendar time1 , Calendar time2  , String name){
		Plane plan = null ;
		for(int i = 0 ; i < resource.size() ; i++ ) {
			if(resource.get(i).getPlaneNumber().equals(palneNumber)) {
				plan = resource.get(i) ;
			}
		}
		if(plan == null ) {
			return 0 ;
		}
		FlightEntry F = null ;
		for(FlightEntry e : flightEntry	) {
			if(e.getName().equals(name)) {
				if(e.getTime1().equals(time1)) {
					if(e.getTime2().equals(time2)) {
						F = e ;
						if(e.getResource() == null) {
							e.setResource(plan);
							List<PlanningEntry> t = new ArrayList<PlanningEntry>() ;
							t.addAll(flightEntry) ;
							if(!APIs.PlanningEntryAPIs.checkResourceExclusiveConflict(t, new checkResourceExclusiveConflict1())) {
								F.setResource(null);
								return 3 ;
							}
							e.setState(ALOCATED.instance);
							return 2 ;
						}else {
							return 1 ;
						}
					}
				}
			}
		}
		if(F== null) {
			return 4 ;
		}
		return 2;
	}
	
	/**
	 * 启动某个高铁
	 * @param palneNumbe 待启动的航班号
	 * @param time1 待启动的航班出发时间
	 * @param time2 待启动的航班到达时间
	 * @return 提示相关信息
	 */
	public String BeginPlanningEntry(String Name , Calendar time1 , Calendar time2){
		
		for(FlightEntry e : flightEntry	) {
			if(e.getName().equals(Name)) {
				if(e.getTime1().equals(time1)) {
					if(e.getTime2().equals(time2)) {
					 if(e.getState() instanceof ALOCATED) {
						 e.setState(RUNNING.instance);
						 return "航班已经启动";
					 }else {
						 return "航班当前状态为" +e.getState().toString() + ",不能启动" ;
					 }
					}
				}
			}
		}
		return "指定的航班不存在";
	}
	
	/**
	 * 结束某个高铁
	 * @param palneNumbe 待结束的航班号
	 * @param time1 待结束的航班出发时间
	 * @param time2 待结束的航班到达时间
	 * @return 提示相关信息
	 */
	public String EndPlanningEntry(String palneNumber , Calendar time1 , Calendar time2)  {
		for(FlightEntry e : flightEntry	) {
			if(e.getName().equals(palneNumber)) {
				if(e.getTime1().equals(time1)) {
					if(e.getTime2().equals(time2)) {
					 if(e.getState() instanceof  RUNNING) {
						 e.setState(ENDED.instance);//设置状态为已经结束
						 return "航班已经结束";
					 }else {
						 return "航班当前状态为" +e.getState().toString() + ",不能结束" ;
					 }
					}
				}
			}
		}
		return "指定的航班不存在";
	}
	
	/**
	 * 根据当前的时间来设置航班的状态
	 * @param flightss 待设定状态的航班
	 */
	public  void changeState(FlightEntry flight) {
		if(flight.getResource() == null ) {
			flight.setState(WAITTING.instance );//航班还未分配飞机
			return ;
		}
		if(time.compareTo(flight.getTime1()) < 0) {
			flight.setState(ALOCATED.instance );//航班还未启动，设置状态为ALOCATED
			return ;
		}else if(time.compareTo(flight.getTime2()) > 0) {
			flight.setState(ENDED.instance );//航班已经结束,设置状态为ENDED
			return ;
		}else {
			flight.setState(RUNNING.instance );//航班正在启动中，设置状态为RUNNING
			return ;
		}	
	}
	
	/**
	 * 查看某个航班的当前状态
	 * @param name 航班号
	 * @param time1 航班的出发时间
	 * @param time2 航班的当前时间
	 * @return 航班的状态
	 */
	public State WatchState(String name , Calendar time1 , Calendar time2 ) {
		for(FlightEntry e : flightEntry	) {
			if(e.getName().equals(name)) {
				if(e.getTime1().equals(time1)) {
					if(e.getTime2().equals(time2)) {
						changeState(e);
						return e.getState();
					}
				}
			}
		}
		 return null ;
	}
	/**
	 * 查看使用某个飞机的所有计划项
	 * @param time1 当前时间
	 * @param planeNumber 飞机编号
	 */
	public void getResourcePlanningEntry(String planeNumber , Calendar time1) {
		this.time = time1 ;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		List<FlightEntry> f =new ArrayList<FlightEntry>();//使用该飞机的所有航班
		for(FlightEntry s : flightEntry) {
			if( s.getResource().getPlaneNumber().equals(planeNumber) ) {
				changeState(s);//根据当前时间设定使用该飞机的航班状态
				f.add(s);
			}
		}
		Collections.sort(f, new FlightCompare1());//按时间排序
		int size = f.size() ;
		String[] columNames = { "序号" , "时间" , "航班" ,"行程" , "状态"} ;//表格第一行信息
		Object[][] rowData = new String[f.size()][] ;
		for(int i = 0 ; i < size ; i ++) {
			rowData[i] = new String[] {String.valueOf(i+1),f.get(i).getBeginEndTime().getDate1() + "  ->  " + f.get(i).getBeginEndTime().getDate2()
					,f.get(i).getName() , f.get(i).getStartLocation().getName() + "-" +	f.get(i).getEndLocation().getName()  , f.get(i).getState().toString() 
					
			};
		}
		JFrame jf = new JFrame(sdf.format(time1.getTime())  + "(当前时间)" + "使用飞机" + planeNumber + "所有航班");
		Show.show(columNames, rowData, jf);//可视化
		
	}
	
	/**
	 * 增加可用的飞机
	 * @param planeNumber 飞机的编号
	 * @param machineNumber 飞机的机型号
	 * @param size 乘客人数
	 * @param age 飞机机龄
	 * @return true:增加成功 ,false :增加失败
	 */
	public boolean addResource(String planeNumber , String machineNumber , int size , double age )  {
		Plane plane = new Plane(planeNumber, machineNumber, size, age);
		if(!resource.contains(plane)) {
			resource.add(plane);
			return true ;
		}
		return false ;
		
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
	 * 删除可用的资源
	 * @param teacher 想要删除的飞机
	 * @return true :删除成功， false:删除失败
	 */
 	public boolean deleteResource(String planeNumber , String machineNumber , int size , double age) {
		Plane plane = new Plane(planeNumber, machineNumber, size, age) ;
		for(Plane e  : resource) {
			if(e.equals(plane)) {
				resource.remove(e);
				return true;
			}
		}
		return false ;
	}
	/**
	 * 列出可用的飞机的信息
	 */
	public void showResource() {
		String[] columNames = { "序号" , "可用的飞机的信息如下"} ;//表格第一行信息
		Object[][] rowData = new String[resource.size()][] ;
		for(int i = 0 ; i<resource.size() ; i++	) {
			rowData[i] = new String[] {String.valueOf(i+1) , resource.get(i).toString()};
		}
		JFrame jf =new JFrame("所有可用的飞机的信息");
		Show.show(columNames, rowData, jf);
	}
	/**
	 * 列出和某个位置相关的所有航班
	 * @param loc 位置
	 * @param time1 当前时间
	 */
	public void show(Location loc, Calendar time1 ) {
		FlightScheduleBoard board = new FlightScheduleBoard(loc, flightEntry, time1) ;
		board.show();
	}
	/**
	 * 列出可用的位置的信息
	 */
	public void showLocation() {
		String[] columNames = { "序号" , "可用的位置的信息如下"} ;//表格第一行信息
		Object[][] rowData = new String[location.size()][] ;
		for(int i = 0 ; i<location.size() ; i++	) {
			rowData[i] = new String[] {String.valueOf(i+1) , location.get(i).getName()};
		}
		JFrame jf =new JFrame("所有可用的位置的信息");
		Show.show(columNames, rowData, jf);
	}
	
	/**
	 * 判断相同的航班，起止时间是否相同以及航班日期和起飞时间日期是否 
	 * @param flight  
	 * @throws Exception 
	 */
	public void PanDuan(FlightEntry flight) throws Exception {
		String s = flight.getName().substring(0,2);//航班号前两个字符
		int x = Integer.valueOf(flight.getName().substring(2));//后面四个数字
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm"); //时间形式的字符串
		String s1 = sdf.format(flight.getTime1().getTime());//出发时间
		String s2 = sdf.format(flight.getTime2().getTime());//终止时间
		for(int i = 0 ; i < flightEntry.size() ; i++) {
			String ss = flightEntry.get(i).getName().substring(0,2);//航班号前两个字符
			int xx = Integer.valueOf(flightEntry.get(i).getName().substring(2));//后面四个数字
			if(ss.equals(s) && xx == x) {//航班号相同
				String s11 = sdf.format(flightEntry.get(i).getTime1().getTime());//出发时间
				String s22 = sdf.format(flightEntry.get(i).getTime2().getTime());//终止时间
				if(!s11.equals(s1)) {
					throw new Exception( "航班："+ flight.getName() + "的出发时间不一样!");
				}
				if(!s22.equals(s2)) {
					throw new Exception( "航班："+ flight.getName() + "的到达时间不一样!");
				}
				if(!flight.getStartLocation().equals(flightEntry.get(i).getStartLocation())) {
					throw new Exception( "航班："+ flight.getName() + "的出发机场时间不一样!");
				}
				if(!flight.getEndLocation().equals(flightEntry.get(i).getEndLocation())) {
					throw new Exception( "航班："+ flight.getName() + "的到达机场时间不一样!");
				}
			}
		}
	}
	/**
	 * 从正则表达式解析计划项的信息
	 * @param s  正则表达式
	 * @return  一个航班
	 * @throws Exception 
	 */
	public FlightEntry getFileFlightEntry(String s ) throws Exception {
		Pattern pattern1 =Pattern.compile("Flight:([\\d]{4}-[\\d]{2}-[\\d]{2}),([A-Z]{2}[\\d]{2,4})\n\\{\nDepartureAirport:(.*?)\nArrivalAirport:(.*?)\nDepatureTime:([0-9]{4}-[0-9]{2}-[0-9]{2}\\s[0-9]{2}:[0-9]{2})\nArrivalTime:([0-9]{4}-[0-9]{2}-[0-9]{2}\\s[0-9]{2}:[0-9]{2})\nPlane:([B || N][0-9]{4})\n\\{\nType:([0-9a-zA-Z]{4})\nSeats:([1-9][\\d]{1,2})\nAge:(.*?)\n\\}\\n\\}\n");
		Matcher matcher = pattern1.matcher(s);
		if(!matcher.find()) {
			throw new Exception("文件非法!");
		}
		String planningEntryTime = matcher.group(1) ;//航班时间
		String planningEntryNumber = matcher.group(2) ;//航班号
		String departureAirport = matcher.group(3);//出发机场
		String arrivalAirport = matcher.group(4);//抵达机场
		String departureTime = matcher.group(5);//出发时间
		String arrivalTime = matcher.group(6);//抵达时间
		String number = matcher.group(7);
	    String strType = matcher.group(8);
		FlightEntry t = new FlightEntry(planningEntryNumber);//航班
		Location loc1 = new Location(departureAirport);
		Location loc2 = new Location(arrivalAirport);
		if(!location.contains(loc1))
			this.location.add(loc1);//增加可用的位置
		if(!location.contains(loc2))
			this.location.add(loc2);//增加的可用位置
		t.setLocations(loc1, loc2);//航班的初始位置和终止位置
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		Calendar time1 = Calendar.getInstance() ;
		time1.setTime(sdf.parse(departureTime));//航班起飞时间
		Calendar time2 = Calendar.getInstance() ;
		time2.setTime(sdf.parse(arrivalTime));//航班到达时间
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");//判断是否是同一个日期
		if(!(sdf1.format(time1.getTime()).equals(planningEntryTime))){
			throw new Exception("航班:" + planningEntryNumber +"的时间和出发时间不是同一个日期");
		}
		t.setTime(new Timeslot(time1, time2));//设置航班的时间对
		if(Integer.valueOf(matcher.group(9)) < 50 ) {
			throw new Exception("文件非法:飞机承载人数少于50");
		}
		if(Integer.valueOf(matcher.group(9)) >600) {
			throw new Exception("文件非法:飞机承载人数大于600");
		}
		if(Double.valueOf(matcher.group(10)) > 30 ) {
			throw new Exception("文件非法:飞机机龄大于30");
		}
		if(Double.valueOf(matcher.group(10)) < 0) {
			throw new Exception("文件非法:飞机机龄小于0");
		}
		Plane plane1 = new Plane(number, strType, Integer.valueOf(matcher.group(9)), Double.valueOf(matcher.group(10)));//飞机
		if(!resource.contains(plane1))
			this.resource.add(plane1);//增加资源
		t.setResource(plane1);
		PanDuan(t);//检查所有的航班t的起止时间和起止位置是否相同
		return t ;
	}
	

	/**
	 * 从文件读取数据建立一系列计划项
	 * @param file 读取的文件
	 * @throws Exception  文件读取错误
	 */
	public void ReadFileCreatePlanningEntry(String file	) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(new File(file)));//读取文件
		String line1 = "" ;
		int l = 0 ;
		StringBuffer s = new StringBuffer("") ;
		while((line1 = reader.readLine()) != null) {
			if(line1.equals("")) {
				continue ;//跳过空行
			}
			s.append(line1 + "\n") ;
			l++;
			if(l % 13 == 0 ) {//已经读入完整的一个航班的信息
				this.flightEntry.add(getFileFlightEntry(s.toString()));
				s = new StringBuffer("");//重新读入13行的信息
			}

		}
		reader.close();//关闭文件
	}
	/**
	 * 列出和某个位置相关的所有航班并可视化
	 * @param loc 某个位置
	 * @param time 当前时间
	 */
	public void showFlightEntry(Location loc ,Calendar time ) {
		FlightScheduleBoard Board = new FlightScheduleBoard(loc, flightEntry, time) ;
		Iterator<PlanningEntry> it = Board.iterator();
		while(it.hasNext()) {
			PlanningEntry<Plane> s = it.next() ;
			System.out.println(  s.getName() + "\t\t" +  s.getBeginEndTime() + "\t" + s.getTimeLocation().get(s.getBeginEndTime()) +"\t" + s.getState());
		}
		
	}
	/**
	 * 展示某个位置的信息板
	 * @param loc 当前位置
	 * @param time1 当前时间
	 * @param x 0 :抵达航班 ， 1 :出发航班
	 */
	public void board(Location loc ,Calendar time1 , int x) {
		time= time1 ;
		FlightScheduleBoard Board = new FlightScheduleBoard(loc, flightEntry, time1) ;
		Board.visualize(x);
	}
	/**
	 * 检测计划项是否存在资源冲突
	 * @return true : 不存在冲突，false: 存在充冲突
	 */
	public boolean check() {
		List<PlanningEntry> flightt = new ArrayList<>() ;
		flightt.addAll(flightEntry);
		if(!APIs.PlanningEntryAPIs.checkResourceExclusiveConflict(flightt,new checkResourceExclusiveConflict1())) {//资源或者位置存在冲突
			return false ;
		}
		return true ;
	}

	/**
	 * 找出使用相同飞机的某个航班的前序计划项
	 * @param time1 航班起始时间
	 * @param time2 航班终止时间
	 * @param Name 航班名称
	 * @param planNumber 飞机编号
	 */
	public boolean getPrePlanningEntry(Calendar time1 , Calendar time2 , String Name , String planNumber) {
		Plane plane = null ;
		for(int i = 0 ; i < resource.size() ; i++ ) {
			if(resource.get(i).getPlaneNumber().equals(planNumber)) {
				plane = resource.get(i);//找到飞机
			}
		}
		FlightEntry ss = null ;
		for(int j = 0 ; j < flightEntry.size() ; j++) {
			if(flightEntry.get(j).getName().equals(Name)) {
				if(flightEntry.get(j).getTime1().equals(time1)) {
					if(flightEntry.get(j).getTime2().equals(time2)) {
						ss = flightEntry.get(j);//找到航班
					}
				}
			}
		}
		if(ss == null || plane ==null)
			return false ;
		PlanningEntry<Plane> tt =  PlanningEntryAPIs.findPreEntryPerResource(plane, ss, flightEntry) ;
		if(tt == null) {
			return false ;
		}else {
			String[] columNames = {  "时间" , "航班" ,"行程" , "状态"} ;//表格第一行信息
			Object[][] rowData = new String[1][] ;
			Location loc1 = tt.getTimeLocation().get(tt.getBeginEndTime()).get(0);
			Location loc2 = tt.getTimeLocation().get(tt.getBeginEndTime()).get(1);
			rowData[0] = new String[] {tt.getBeginEndTime().getDate1() + "->" + tt.getBeginEndTime().getDate2() , tt.getName() , loc1.getName() + " - " + loc2.getName() , tt.getState().toString()};
			JFrame jf =new JFrame("航班:" + planNumber +"的前序计划项");
			Show.show(columNames, rowData, jf);
		}	
		return true ;
	}
	

}
