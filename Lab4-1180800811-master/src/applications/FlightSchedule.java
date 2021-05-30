package applications;

// a immutable class
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
import MyException.cancelPlanningEntryException;
import MyException.deleteLocationException;
import MyException.deleteResourceException;
import MyException.feipeiResourceException;
import MyException.fileChooseException;
import MyException.illegalRegularException;
import MyException.sameLabelException;
import MyException.wrongDependenceException;
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

	private int cloum =  1 ;//标识读取文件的行数
	/*
	 * RI: 
	 * resource , location , flightEntry , time is not null
	 * 
	 * Abstraction function:
	 * 代表一个航班管理，由一系列的航班、可用的飞机、可用的机场和当前时间组成
	 * 	Safety from rep exposure:
	 *	all the field is private and immutable
	 *	there is no exposure of all the fields
	 * 
	 */
	
	public void checkRep() {
		assert resource != null ;
		assert location != null ;
		assert flightEntry != null ;
		assert time != null ;
	}
	
	/**
	 * 设置当前时间
	 * @param time 当前时间
	 */
 	public void setTime(Calendar time ) {
 		assert time != null ;
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
	 * @throws IOException 
	 * @throws SecurityException 
	 */
	public FlightSchedule(){
	}

	/**
	 * 读入一个文件来初始化航班管理
	 * @param s
	 * @throws Exception
	 */
	public FlightSchedule(String s ) throws Exception {
		ReadFileCreatePlanningEntry(s);
		checkRep();
	}


	/**
	 * 取消某个航班
	 * @param palneNumber 待取消的航班号
	 * @param time1 待取消的航班出发时间
	 * @param time2 待取消的航班到达时间
	 * @return 提示相关信息
	 * @throws cancelPlanningEntryException 取消计划项的时候，该计划项的当前状态不允许取消；
	 */
	public String cancelPlanningEntry(String palneNumber , Calendar time1 , Calendar time2) throws cancelPlanningEntryException{
		assert palneNumber != null : "航班号不能为null" ;
		for(FlightEntry e : flightEntry	) {
			if(e.getName().equals(palneNumber)) {
				if(e.getTime1().equals(time1)) {
					if(e.getTime2().equals(time2)) {
					 if(e.getState() instanceof ALOCATED || e.getState() instanceof WAITTING) {
						 e.setState(CANCELLED.instance);
						 return "航班已经取消";
					 }else {
						 throw new cancelPlanningEntryException(e) ;
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
	 * @throws cancelPlanningEntryException 分配资源后导致与已有的其他计划项产生“资源独占冲突”；
	 * @throws feipeiResourceException 
	 */
	public int FeiPeiResource(String palneNumber , Calendar time1 , Calendar time2  , String name) throws feipeiResourceException{
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
								throw new feipeiResourceException(F);
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
		assert flight != null ;
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
			System.out.println("s");
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
	 * @throws deleteLocationException 删除位置的时候，有尚未结束的计划项会在该位置执行；
	 */
	public boolean deleteLocation(Location loc) throws deleteLocationException {
		
		for(FlightEntry e : flightEntry) {
			if(e.getState().equals(RUNNING.instance)) {
				if(e.getStartLocation().equals(loc) || e.getEndLocation().equals(loc)) {
					throw new deleteLocationException(loc) ;
				}
			}
		}
		
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
	 * @throws deleteResourceException 删除资源的时候，如果有尚未结束的计划项正在占用该资源
	 */
 	public boolean deleteResource(String planeNumber , String machineNumber , int size , double age) throws deleteResourceException {
			Plane plane = new Plane(planeNumber, machineNumber, size, age) ;
 			for( FlightEntry e : flightEntry) {
 				if(e.getResource().equals(plane)) {
 					if(!e.getState().equals(ENDED.instance) || !e.getState().equals(CANCELLED.instance)) {
 						throw new deleteResourceException(plane) ;
 					}
 				}
 			}
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
	 * 判断是航班flight和已经读取的航班集是否存在依赖关系错误 或者具有完全相同的航班
	 * @param flight  
	 * @throws Exception 
	 */
	public void PanDuan(FlightEntry flight) throws Exception {
		String s = flight.getName().substring(0,2);//航班号前两个字符
		int x = Integer.valueOf(flight.getName().substring(2));//后面四个数字
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm"); //时间形式的字符串
		String s1 = sdf.format(flight.getTime1().getTime());//出发时间
		String s2 = sdf.format(flight.getTime2().getTime());//终止时间
		String t = new String() ;
		try {
			for(int i = 0 ; i < flightEntry.size() ; i++) {
				String ss = flightEntry.get(i).getName().substring(0,2);//航班号前两个字符
				int xx = Integer.valueOf(flightEntry.get(i).getName().substring(2));//后面四个数字
				if(ss.equals(s) && xx == x) {//航班号相同
					String s11 = sdf.format(flightEntry.get(i).getTime1().getTime());//出发时间
					String s22 = sdf.format(flightEntry.get(i).getTime2().getTime());//终止时间
					if(!s11.equals(s1)) {
						t = "航班："+ flight.getName() + "的出发时间不一样!" ;
						throw new wrongDependenceException();
					}
					if(!s22.equals(s2)) {
						t = "航班："+ flight.getName() + "的到达时间不一样!" ;
						throw new wrongDependenceException();
					}
					if(!flight.getStartLocation().equals(flightEntry.get(i).getStartLocation())) {
						t =  "航班："+ flight.getName() + "的出发机场不一样!" ;
						throw new wrongDependenceException(t);
					}
					if(!flight.getEndLocation().equals(flightEntry.get(i).getEndLocation())) {
						t = "航班："+ flight.getName() + "的到达机场不一样!";
						throw new wrongDependenceException();
					}
					if(flightEntry.get(i).getResource().equals(flight.getResource()) ) {
						if(flightEntry.get(i).getBeginEndTime().equals(flight.getBeginEndTime())) {
							t = "输入文件存在标签完全一样的航班:"  + flight.getName()  ;
							throw new sameLabelException();
						}
					}

				}else {
					if(flight.getResource().getPlaneNumber().equals(flightEntry.get(i).getResource().getPlaneNumber())) {
						if(!flight.getResource().equals(flightEntry.get(i).getResource())){
							t = "航班" + flight.getName() + " 和航班 " + flightEntry.get(i).getName() + " 使用相同的飞机，但是飞机的类型、座位数或机龄却不一致";
							throw new wrongDependenceException();
						}
					}
				}

			}
		}catch(wrongDependenceException e ) {
			throw new fileChooseException( "FileChooseException:文件不合法" +t +  " , 请重新选择文件");
		}catch(sameLabelException e) {
			throw new fileChooseException( "FileChooseException:文件不合法" +t +  " , 请重新选择文件");
		}
		
	}
	/**
	 * 从正则表达式解析计划项的信息
	 * @param s  正则表达式
	 * @return  一个航班
	 * @throws Exception 
	 */
	public FlightEntry getFileFlightEntry(String s ) throws Exception {
		assert s != null ;
		IsillegalRegular(s);//判断是否存在格式错误
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
		try {
			if(!(sdf1.format(time1.getTime()).equals(planningEntryTime))){
				throw new wrongDependenceException();
			}
		}catch(wrongDependenceException e ) {
			throw new fileChooseException( "FileChooseException:文件格式不合法" +"航班:" + planningEntryNumber +"的时间和出发时间不是同一个日期"+  " , 请重新选择文件");
		}
		try {
			if(!time1.before(time2)) {
				throw new wrongDependenceException();
			}
		}catch(wrongDependenceException e) {
			throw new fileChooseException( "FileChooseException:文件格式不合法" +"航班:" + planningEntryNumber +"出发时间晚于到达时间"+  " , 请重新选择文件");
		}
		try {
			if((time2.getTime().getTime() - time1.getTime().getTime()) > 3600*24*1000) {
				throw new wrongDependenceException();
			}
		}catch(wrongDependenceException e ) {
			throw new fileChooseException( "FileChooseException:文件格式不合法" +"航班:" + planningEntryNumber +"的时间和出发时间相差大于一天" +  " , 请重新选择文件");
		}
		
		t.setTime(new Timeslot(time1, time2));//设置航班的时间对
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
		assert file != null ;
		BufferedReader reader = new BufferedReader(new FileReader(new File(file)));//读取文件
		String line1 = "" ;
		int l = 0 ;
		StringBuffer s = new StringBuffer("") ;
		while((line1 = reader.readLine()) != null) {
			if(line1.equals("")) {
				cloum ++ ;
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
	 * 判断一个表示一个航班的13行信息是否存在格式错误
	 * @param ff 13行信息的字符串
	 * @throws fileChooseException 
	 */
	public void IsillegalRegular(String ff ) throws fileChooseException {
		String t = new String() ;
		try {
//			Pattern pattern = Pattern.compile("Flight:(.*?),(.*?)\n\\{\nDepartureAirport:(.*?)\nArrivalAirport:(.*?)\nDepatureTime:(.*?)\nArrivalTime:(.*?)\nPlane:(.*?)\n\\{\nType:(.*?)\nSeats:(.*?)\nAge:(.*?)\n\\}\n\\}\n");
//			Pattern pattern =Pattern.compile("Flight:([\\d]{4}-[\\d]{2}-[\\d]{2}),([A-Z]{2}[\\d]{2,4})\n\\{\nDepartureAirport:(.*?)\nArrivalAirport:(.*?)\nDepatureTime:([0-9]{4}-[0-9]{2}-[0-9]{2}\\s[0-9]{2}:[0-9]{2})\nArrivalTime:([0-9]{4}-[0-9]{2}-[0-9]{2}\\s[0-9]{2}:[0-9]{2})\nPlane:([B || N][0-9]{4})\n\\{\nType:([0-9a-zA-Z]{4})\nSeats:([1-9][\\d]{1,2})\nAge:(.*?)\n\\}\\n\\}\n");
			Pattern pattern1 = Pattern.compile("Flight:([\\S]*),([\\w]*)\n");
			Pattern pattern2 = Pattern.compile("Flight:([\\d]{4}-[\\d]{2}-[\\d]{2}),([A-Z]{2}[\\d]{2,4})\n(\\{)\n");
			Pattern pattern3 = Pattern.compile("Flight:([\\d]{4}-[\\d]{2}-[\\d]{2}),([A-Z]{2}[\\d]{2,4})\n\\{\nDepartureAirport:(.*?)\n");
			Pattern pattern4 = Pattern.compile("Flight:([\\d]{4}-[\\d]{2}-[\\d]{2}),([A-Z]{2}[\\d]{2,4})\n\\{\nDepartureAirport:(.*?)\nArrivalAirport:(.*?)\n");
			Pattern pattern5 = Pattern.compile("Flight:([\\d]{4}-[\\d]{2}-[\\d]{2}),([A-Z]{2}[\\d]{2,4})\n\\{\nDepartureAirport:(.*?)\nArrivalAirport:(.*?)\nDepatureTime:([0-9]{4}-[0-9]{2}-[0-9]{2}\\s[0-9]{2}:[0-9]{2})\n");
			Pattern pattern6 = Pattern.compile("Flight:([\\d]{4}-[\\d]{2}-[\\d]{2}),([A-Z]{2}[\\d]{2,4})\n\\{\nDepartureAirport:(.*?)\nArrivalAirport:(.*?)\nDepatureTime:([0-9]{4}-[0-9]{2}-[0-9]{2}\\s[0-9]{2}:[0-9]{2})\nArrivalTime:([0-9]{4}-[0-9]{2}-[0-9]{2}\\s[0-9]{2}:[0-9]{2})\n");
			Pattern pattern7 = Pattern.compile("Flight:([\\d]{4}-[\\d]{2}-[\\d]{2}),([A-Z]{2}[\\d]{2,4})\n\\{\nDepartureAirport:(.*?)\nArrivalAirport:(.*?)\nDepatureTime:([0-9]{4}-[0-9]{2}-[0-9]{2}\\s[0-9]{2}:[0-9]{2})\nArrivalTime:([0-9]{4}-[0-9]{2}-[0-9]{2}\\s[0-9]{2}:[0-9]{2})\nPlane:([\\w]*)\n");
			Pattern pattern13 = Pattern.compile("Flight:([\\d]{4}-[\\d]{2}-[\\d]{2}),([A-Z]{2}[\\d]{2,4})\n\\{\nDepartureAirport:(.*?)\nArrivalAirport:(.*?)\nDepatureTime:([0-9]{4}-[0-9]{2}-[0-9]{2}\\s[0-9]{2}:[0-9]{2})\nArrivalTime:([0-9]{4}-[0-9]{2}-[0-9]{2}\\s[0-9]{2}:[0-9]{2})\nPlane:([B || N][0-9]{4})\n\\{\n");
			Pattern pattern8 = Pattern.compile("Flight:([\\d]{4}-[\\d]{2}-[\\d]{2}),([A-Z]{2}[\\d]{2,4})\n\\{\nDepartureAirport:(.*?)\nArrivalAirport:(.*?)\nDepatureTime:([0-9]{4}-[0-9]{2}-[0-9]{2}\\s[0-9]{2}:[0-9]{2})\nArrivalTime:([0-9]{4}-[0-9]{2}-[0-9]{2}\\s[0-9]{2}:[0-9]{2})\nPlane:([B || N][0-9]{4})\n\\{\nType:([0-9a-zA-Z]{4})\n");
			Pattern pattern9 = Pattern.compile("Flight:([\\d]{4}-[\\d]{2}-[\\d]{2}),([A-Z]{2}[\\d]{2,4})\n\\{\nDepartureAirport:(.*?)\nArrivalAirport:(.*?)\nDepatureTime:([0-9]{4}-[0-9]{2}-[0-9]{2}\\s[0-9]{2}:[0-9]{2})\nArrivalTime:([0-9]{4}-[0-9]{2}-[0-9]{2}\\s[0-9]{2}:[0-9]{2})\nPlane:([B || N][0-9]{4})\n\\{\nType:([0-9a-zA-Z]{4})\nSeats:([1-9][\\d]{1,2})\n");
			Pattern pattern10 = Pattern.compile("Flight:([\\d]{4}-[\\d]{2}-[\\d]{2}),([A-Z]{2}[\\d]{2,4})\n\\{\nDepartureAirport:(.*?)\nArrivalAirport:(.*?)\nDepatureTime:([0-9]{4}-[0-9]{2}-[0-9]{2}\\s[0-9]{2}:[0-9]{2})\nArrivalTime:([0-9]{4}-[0-9]{2}-[0-9]{2}\\s[0-9]{2}:[0-9]{2})\nPlane:([B || N][0-9]{4})\n\\{\nType:([0-9a-zA-Z]{4})\nSeats:([1-9][\\d]{1,2})\nAge:(.*?)\n");
			Pattern pattern11 = Pattern.compile("Flight:([\\d]{4}-[\\d]{2}-[\\d]{2}),([A-Z]{2}[\\d]{2,4})\n\\{\nDepartureAirport:(.*?)\nArrivalAirport:(.*?)\nDepatureTime:([0-9]{4}-[0-9]{2}-[0-9]{2}\\s[0-9]{2}:[0-9]{2})\nArrivalTime:([0-9]{4}-[0-9]{2}-[0-9]{2}\\s[0-9]{2}:[0-9]{2})\nPlane:([B || N][0-9]{4})\n\\{\nType:([0-9a-zA-Z]{4})\nSeats:([1-9][\\d]{1,2})\nAge:(.*?)\n\\}\\n");
			Pattern pattern12 = Pattern.compile("Flight:([\\d]{4}-[\\d]{2}-[\\d]{2}),([A-Z]{2}[\\d]{2,4})\n\\{\nDepartureAirport:(.*?)\nArrivalAirport:(.*?)\nDepatureTime:([0-9]{4}-[0-9]{2}-[0-9]{2}\\s[0-9]{2}:[0-9]{2})\nArrivalTime:([0-9]{4}-[0-9]{2}-[0-9]{2}\\s[0-9]{2}:[0-9]{2})\nPlane:([B || N][0-9]{4})\n\\{\nType:([0-9a-zA-Z]{4})\nSeats:([1-9][\\d]{1,2})\nAge:(.*?)\n\\}\\n\\}\n");
			Matcher matcher  ;
			if((matcher = pattern1.matcher(ff)).find()) {
				if(Pattern.matches("[\\d]{4}-[\\d]{2}-[\\d]{2}", matcher.group(1))) {//判断航班的时间格式是否符合要求
					String s1 = matcher.group(2);
					if(s1.length() < 4 || s1.length() > 6) {
						t = "文件第" +cloum +  "行输入不合法，航班号:" + s1 + "的长度不在[4,6]";
						throw new illegalRegularException(t);//航班号长度是否在[3,6]之间
					}else {
						if(!Character.isUpperCase(s1.charAt(0)) || !Character.isUpperCase(s1.charAt(1))) {
							t = "文件第" +cloum +  "行输入不合法，航班号:" + s1 + "的前两位不是大写字母";
							throw new illegalRegularException(t);//航班号前两位是否是大写字母
						}
						for(int i = 2 ; i <s1.length() ;i++) {
							if(!Character.isDigit(s1.charAt(i))) {
								t = "文件第" +cloum +  "行输入不合法，航班号:" + s1 + "的后" + (s1.length() -2) + "位不是数字" ;
								throw new illegalRegularException(t);//航班号后四位是否都是数字
							}
						}
					}
				}else {
					t = "文件第" +cloum +  "行输入不合法，航班" + matcher.group(2)+"的日期: " + matcher.group(1) + "不符合 yyyy-MM-dd 格式" ;
					throw new illegalRegularException(t);
				}
			
			}else {
				t = "文件第" +cloum +  "行输入不合法，第一行的信息不符合Flight:xx,xx格式" ;
				throw new illegalRegularException(t);
			}
			cloum ++ ;
			if(!(matcher = pattern2.matcher(ff)).find()) {
					t = "文件第" +cloum +  "行输入不合法，航班的13行数据的第二行不是'{'" ;
					throw new illegalRegularException(t);
			}
			cloum ++ ;
			if(!(matcher = pattern3.matcher(ff)).find()) {
				t = "文件第" +cloum +  "行输入不合法，航班的13行数据的第三行数据不符合要求" ;
				throw new illegalRegularException(t);
			}
			cloum ++ ;
			if(!(matcher = pattern4.matcher(ff)).find()) {
				t =  "文件第" +cloum +  "行输入不合法，航班的13行数据的第四行数据不符合要求" ;
				throw new illegalRegularException(t);
			}
			cloum ++ ;
			if(!(matcher = pattern5.matcher(ff)).find()) {
				t = "文件第" +cloum +  "行输入不合法，航班的出发时间不符合格式要求";
				throw new illegalRegularException(t);
			}
			cloum ++ ;
			if(!(matcher = pattern6.matcher(ff)).find()) {
				 t = "文件第" +cloum +  "行输入不合法，航班的抵达时间不符合格式要求";
				throw new illegalRegularException(t);
			}
			cloum ++ ;
			if((matcher = pattern7.matcher(ff)).find()) {
				String s1 = matcher.group(7) ;

				if(s1.length() != 5) {
					t  = "文件第" +cloum +  "行输入不合法，飞机编号长度不为5" ;
					throw new illegalRegularException(t);//飞机编号长度是否为5
				}else {
					if( !((s1.charAt(0) == 'N') || (s1.charAt(0) == 'B'))) {
						t = "文件第" +cloum +  "行输入不合法，飞机第一位字母不是N或者B" ;
						throw new illegalRegularException(t);//飞机第一位字母是不是N或者B
					}
					for(int i = 1 ; i <s1.length() ;i++) {
						if(!Character.isDigit(s1.charAt(i))) {
							t = "文件第" +cloum +  "行输入不合法，飞机的后几位不是数字" ;
							throw new illegalRegularException(t);//飞机后四位是否都是数字
						}
					}
				}
			}else {
				t = "文件第" +cloum +  "行输入不合法，航班的13行数据的第七行数据不符合要求" ;
				throw new illegalRegularException(t);
			}
			cloum ++ ;
			if(!(matcher = pattern13.matcher(ff)).find()) {
				t = "文件第" +cloum +  "行输入不合法，航班的13行数据的第13行数据不符合要求" ;
				throw new illegalRegularException(t);
			}
			cloum ++ ;
			if(!(matcher = pattern8.matcher(ff)).find()) {
				t = "文件第" +cloum +  "行输入不合法，飞机的机型不符合格式要求" ;
				throw new illegalRegularException(t);
			}
			cloum ++ ;
			if(!(matcher = pattern9.matcher(ff)).find()) {
				t = "文件第" +cloum +  "行输入不合法，飞机的座位数不是一个整数" ;
				throw new illegalRegularException(t);
			}else {
				String s1 = matcher.group(9);
				int x = 0 ;
				 x = Integer.valueOf(s1);
				if(x < 50 || x > 600) {
					t = "文件第" +cloum +  "行输入不合法，飞机的座位数不在[50,600]";
					throw new illegalRegularException(t);
				}
			}
			cloum ++ ;
			if(!(matcher = pattern10.matcher(ff)).find()) {
				t = "文件第" +cloum +  "行输入不合法，飞机的机龄不符合格式要求" ;
				throw new illegalRegularException(t);
			}else {
				double x = 0  ;
				try {
					x = Double.valueOf(matcher.group(10)) ;
				}catch(NumberFormatException e) {
					t= "文件第" +cloum +  "行输入不合法，飞机的机龄不是一个浮点数";
					throw new illegalRegularException(t);
				}
				 

					if(x < 0  || x > 30) {
						t = "文件第" +cloum +  "行输入不合法，飞机的机龄不在[0,30]" ;
						throw new illegalRegularException(t);
					}
					for(int i = 0 ; i < matcher.group(10).length() ; i ++ ) {
						if( matcher.group(10).charAt(i) == '.') {
							if(i != matcher.group(10).length() -2) {
								t = "文件第" +cloum +  "行输入不合法，飞机的小数位数超过1";
								throw new illegalRegularException(t);
							}
						}
					}
			}
			cloum ++ ;
			if(!(matcher = pattern11.matcher(ff)).find()) {
				t = "文件第" +cloum +  "行输入不合法，航班的13行数据的第11行数据不符合要求";
				throw new illegalRegularException(t);
			}
			cloum ++ ;
			if(!(matcher = pattern12.matcher(ff)).find()) {
				t = "文件第" +cloum +  "行输入不合法，航班的13行数据的第12行数据不符合要求";
				throw new illegalRegularException(t);
			}
			
		}catch(illegalRegularException e) {
			throw new fileChooseException( "FileChooseException:文件格式不合法 : " +t +  " , 请重新选择文件");
		}
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
