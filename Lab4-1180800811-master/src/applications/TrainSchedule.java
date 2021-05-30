package applications;

// a immutable class 
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JFrame;
import APIs.PlanningEntryAPIs;
import APIs.checkResourceExclusiveConflict1;
import Board.Show;
import Board.TrainScheduleBoard;
import Location.Location;
import MyException.cancelPlanningEntryException;
import MyException.deleteLocationException;
import MyException.deleteResourceException;
import MyException.feipeiResourceException;
import PlanningEntry.PlanningEntry;
import PlanningEntry.TrainEntry;
import Timeslot.Timeslot;
import Location.*;
import Resource.*;
import EntryState.*;
public class TrainSchedule {
	/*
	 * RI: 
	 * resource , location , flightEntry ， time is not null
	 * 
	 * Abstraction function:
	 * 代表一个高铁管理，由一系列的高铁车次、可用的车厢、可用的位置和当前时间组成
	 * 	Safety from rep exposure:
	 *	all the field is private and immutable
	 *	there is no exposure of all the fields
	 * 
	 */
	private List<Railway> resource =new ArrayList<Railway>() ;//可用的资源
	private List<Location> location = new ArrayList<Location>() ;//可用的位置
	private List<TrainEntry> trainEntry =  new ArrayList<TrainEntry>()  ;//一系列计划项
	private Calendar time = Calendar.getInstance();
	
	
	public void checkRep() {
		assert resource != null ; 
		assert location != null ;
		assert trainEntry != null ;
		assert time != null ;
	}
	/**
	 * 增加一个高铁车次
	 * @param loc 高铁的一系列位置
	 * @param timeslot 高铁的一系列时间
	 * @param name 高铁车次号
	 * @return true:增加成功 ， false:增加失败
	 */
	public boolean addPlanningEntry(List<Location> loc , List<Timeslot> timeslot , String name) {

		TrainEntry train = new TrainEntry(name);//待增加的高铁车次
		train.setLocations(loc);
		train.setTimeslots(timeslot);
		if(check()) {//检测是否存在资源冲突
			this.trainEntry.add(train); 
			return true ;
		}
		return false;
	}
	/**
	 * 
	 * @param name
	 * @param loc1
	 * @param loc2
	 * @param loc3
	 * @param time1
	 * @param time2
	 * @return
	 */
	public boolean addplanningEntry(String name ,Location loc1 , Location loc2 ,  Calendar time1 ,Calendar time2) {
		boolean flag =false ;
		TrainEntry tra =new TrainEntry(name); 
		for(int i = 0 ; i < trainEntry.size() ; i ++) {
			if(trainEntry.get(i).getName().equals(name)) {
				tra= trainEntry.get(i);
				flag = true; 
			}
		}
		if(flag) {
			if( !tra.getLocation().get(tra.getLocation().size()-1).equals(loc1)  || !tra.addLocation(loc2)) {
				return false ;
			}
			if(!tra.addTimeslots(new Timeslot(time1, time2))) {
				return false ;
			}
		}else {
			if(!tra.addLocation(loc1) || !tra.addLocation(loc2)) {
				return false ;
			}
			if(!tra.addTimeslots(new Timeslot(time1, time2))) {
				return false ;
			}
			
			trainEntry.add(tra);
		}
		if(!location.contains(loc1))
			this.location.add(loc1);
		if(!location.contains(loc2))
			this.location.add(loc2);
		return true ;
	}
	/**
	 * 设置当前的时间
	 * @param time 待设定的时间
	 */
	public void setTime(Calendar time) {
		assert time != null ;
		this.time  = time ;
	}
	/**
	 * 取消某个高铁车次
	 * @param trainNumber 该取消的高铁车次号
	 * @param time1 待取消的高铁出发时间
	 * @return 提示相关信息
	 * @throws cancelPlanningEntryException 取消某计划项的时候，该计划项的当前状态不允许取消
	 */
	public String cancelPlanningEntry(String trainNumber , Calendar time1 ) throws cancelPlanningEntryException{
		for(TrainEntry e : trainEntry	) {
			if(e.getName().equals(trainNumber)) {
				if(e.getBeginEndTime().getdate1().equals(time1)) {
					 if(e.getState() instanceof ALOCATED || e.getState() instanceof WAITTING) {
						 e.setState(CANCELLED.instance);
						 return "高铁已经取消";
					 }else {
						 throw new cancelPlanningEntryException(e) ;
					 }
				}
			}
		}
		return "指定的高铁不存在";
	}
	
	/**
	 * 为某个计划项分配资源
	 * @param course  待分配资源的高铁车次
	 * @param teacher 为高铁车次分配的车厢
	 * @param return 0 : 想要分配的车厢不在可用车厢内 1 : 想要分配资源的计划项已经包含了该资源 2 :分配成功  , 4:想要分配车厢的高铁不存在
	 * @throws feipeiResourceException 分配资源之后存在资源冲突
	 */
	public int FeiPeiResource(String name , Calendar time , Railway railway	) throws feipeiResourceException {

			if( !resource.contains(railway)) {
				return 0 ;//想要分配的车厢不在可用车厢内
			}

		TrainEntry t = null ;
		for(TrainEntry e : trainEntry) {
			if(e.getName().equals(name)) {
				if(e.getTimeslots().get(0).getdate1().equals(time)) {
					t= e ;
					if( ! e.getResource().contains(railway)) {//未分配资源
						e.addResource(railway);
						List<PlanningEntry> t1 = new ArrayList<PlanningEntry>() ;  
						t1.addAll(trainEntry);
						if(!APIs.PlanningEntryAPIs.checkResourceExclusiveConflict(t1, new checkResourceExclusiveConflict1())){//检查资源冲突
							e.setResource(null);
							throw new feipeiResourceException(railway) ;
						}
						e.setState(ALOCATED.instance);
						return 2 ;//分配成功
					}else {//想要分配资源的计划项已经分配资源
						return 1 ;
					}
				}
			}
		}
		if(t == null) {
			return 4 ;//想要分配车厢的高铁不存在
		}
		return 2 ;
	}
	/**
	 * 查询航班的状态
	 * @param name 高铁车次
	 * @param time1 出发时间
	 * @param TIME 当前时间
	 * @return 高铁车次的当前状态
	 */
	public State WatchState(String name ,Calendar time1 ,Calendar TIME) {
		this.time = TIME;
		for(TrainEntry e : trainEntry	) {
			if(e.getName().equals(name)) {
				if(e.getBeginEndTime().getdate1().equals(time1)) {	
						changeState(e);//根据当前时间设定高铁的转态
						return e.getState();//返回设定后的状态
				}
			}
		}
		 return null ;//未找到高铁
	}
	/**
	 * 根据当前的时间来设置高铁的状态
	 * @param flightss
	 */
	public  void changeState(TrainEntry train) {
		assert train != null ;
		if(train.getResource() == null ) {
			train.setState(WAITTING.instance );//高铁还未分配车厢
			return ;
		}
		if(time.compareTo(train.getBeginEndTime().getdate1()) < 0) {
			train.setState(ALOCATED.instance );//高铁还未启动
			return ;
		}else if(time.compareTo(train.getBeginEndTime().getdate2()) > 0) {
			train.setState(ENDED.instance );//高铁已经结束
			return ;
		}else {
			for(int i = 0 ; i < train.getTimeslots().size() ; i++) {
				if((time.compareTo(train.getTimeslots().get(i).getdate1() )>= 0) && (time.compareTo(train.getTimeslots().get(i).getdate2() )<= 0)) {
					train.setState(RUNNING.instance );//高铁正在运行
					return ;
				}
			}
		}
		
			train.setState(BLOCKED.instance );//高铁阻塞中
			return ;

		
	}
	
	/**
	 * 启动某个高铁车次
	 * @param trainNumber 待启动的高铁车次号
	 * @param time1 待启动的高铁出发时间
	 * @return 提示关信息
	 */
	public String BeginPlanningEntry(String trainNumber , Calendar time1 ){
		assert trainNumber != null ;
		assert time1 != null ;
		for(TrainEntry e : trainEntry	) {
			if(e.getName().equals(trainNumber)) {
				if(e.getBeginEndTime().getdate1().equals(time1)) {
					 if(e.getState() instanceof ALOCATED) {
						 e.setState(RUNNING.instance);
						 return "高铁已经启动";
					 }else {
						 return "高铁当前状态为" +e.getState().toString() + ",不能启动" ;
					 }
				}
			}
		}
		return "指定的高铁不存在";
	}
	
	/**
	 * 结束某个高铁车次
	 * @param trainNumber 该结束的高铁车次号
	 * @param time1 待结束的高铁出发时间
	 * @return 提示相关信息
	 */
	public String EndPlanningEntry(String trainNumber , Calendar time1)  {
		assert trainNumber != null ;
		assert time1 != null ;
		for(TrainEntry e : trainEntry	) {
			if(e.getName().equals(trainNumber)) {
				if(e.getBeginEndTime().getdate1().equals(time1)) {
					 if(e.getState() instanceof  RUNNING) {
						 e.setState(ENDED.instance);
						 return "高铁已经结束";
					 }else {
						 return "高铁当前状态为" +e.getState().toString() + ",不能结束" ;
					 }
				}
			}
		}
		return "指定的高铁不存在";
	}
	/**
	 * 阻塞某个高铁车次
	 * @param trainNumber 待阻塞的高铁车次号
	 * @param time1 待阻塞的高铁出发时间
	 * @return 提示相关信息
	 */
	public String BlockPlanningEntry(String trainNumber , Calendar time1)  {
		assert trainNumber != null ;
		assert time1 != null ;
		for(TrainEntry e : trainEntry	) {
			if(e.getName().equals(trainNumber)) {
				if(e.getBeginEndTime().getdate1().equals(time1)) {
					 if(e.getState() instanceof  RUNNING) {
						 e.Block();//阻塞高铁车次
						 return "高铁已经阻塞";
					 }else {
						 return "高铁当前状态为" +e.getState().toString() + ",不能阻塞" ;
					 }
				}
			}
		}
		return "指定的高铁不存在";
	}
		
	
	/**
	 * 展示使用某个资源的所有高铁车次
	 * @param railNumber 车厢编号
	 * @param type 车厢类型
	 * @param size 载客人数
	 * @param year 出厂年份
	 * @param TIME 当前时间
	 */
	public void getResourcePlanningEntry(String railNumber , String type ,int size, int year,Calendar TIME ) {
		assert type != null : "车厢类型不能为null" ;
		Type s ;//车厢类型
		if(type.equals("商务")) {
			s = Type.BUSINESS;
		}else if(type.equals("一等")) {
			s = Type.FIRSTCLASS ;
		}else if(type.equals("二等")) {
			s = Type.SECONDCLASS;
		}else if(type.equals("软卧")) {
			s = Type.SOFTSLEPPER ;
		}else if(type.equals("硬卧")) {
			s = Type.TOURISTCOACH;
		}else if(type.equals("硬座")) {
			s = Type.HARDSEATS ;
		}else if(type.equals("行李车")) {
			s = Type.BUGGAGECAR ;
		}else {
			s = Type.RESTAURANTCAR ;
		}
		Railway rail = new Railway(railNumber, s, size, year);//车厢
		this.time = TIME ;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		List<TrainEntry> train = new ArrayList<TrainEntry>() ;//使用该车厢的全部高铁
		for(TrainEntry e : trainEntry) {
			if( e.getResource().contains(rail)) {//使用该车厢的高铁
				changeState(e);//改变状态
				train.add(e);
			}
		}
		Collections.sort(train, new Comparator<TrainEntry>() {//按时间排序
			@Override
			public int compare(TrainEntry o1, TrainEntry o2) {
				return o1.getTimeslots().get(0).getdate1().compareTo(o2.getTimeslots().get(0).getdate2());
			}	
		});//按时间排序
		String ss = sdf.format(time.getTime());//当前时间
		String[] columNames = { "序号" , "时间" , "高铁车次" ,"行程" , "状态"} ;//表格第一行信息
		Object[][] rowData = new String[train.size()][] ;
		for(int i = 0 ; i < train.size() ; i++) {
			rowData[i] = new String[] {String.valueOf(i+1), sdf.format(train.get(i).getBeginEndTime().getdate1().getTime()) + "->" + sdf.format(train.get(i).getBeginEndTime().getdate1().getTime()),
					train.get(i).getName() ,train.get(i).getLocation().get(0).getName()+ " - " + train.get(i).getLocation().get(train.get(i).getLocation().size()-1).getName() ,train.get(i).getState().toString()
			};
		}
		JFrame jf = new JFrame(ss  + "(当前时间)" + " , "+ "使用车厢" + railNumber + "所有高铁车次");
		Show.show(columNames, rowData, jf);//可视化

	}
	
	/**
	 * 增加可使用的车厢
	 * @param railNumber 车厢编号
	 * @param type 车厢类型
	 * @param size 载客人数
	 * @param year 出厂年份
	 * @return true:增加成功 ， false :增加失败
	 */
	public boolean addResource(String railNumber , String type ,int size, int year)  {
		assert type != null : "车厢类型不能为null";
		Type s ;//车厢类型
		if(type.equals("商务")) {
			s = Type.BUSINESS;
		}else if(type.equals("一等")) {
			s = Type.FIRSTCLASS ;
		}else if(type.equals("二等")) {
			s = Type.SECONDCLASS;
		}else if(type.equals("软卧")) {
			s = Type.SOFTSLEPPER ;
		}else if(type.equals("硬卧")) {
			s = Type.TOURISTCOACH;
		}else if(type.equals("硬座")) {
			s = Type.HARDSEATS ;
		}else if(type.equals("行李车")) {
			s = Type.BUGGAGECAR ;
		}else {
			s = Type.RESTAURANTCAR ;
		}
		Railway rail = new Railway(railNumber, s, size, year);//待增加的车厢
		if(!resource.contains(rail)) {
			resource.add(rail);
			return true ;
		}
		return false ;
		
	}
	
	/**
	 * 删除可使用的车厢
	 * @param railNumber 车厢编号
	 * @param type 车厢类型
	 * @param size 载客人数
	 * @param year 出厂年份
	 * @return true:删除成功 ， false :删除失败
	 * @throws deleteResourceException 删除资源的时候，有尚未结束的计划项正在占用该资源
	 */
 	public boolean deleteResource(String railNumber , String type ,int size, int year) throws deleteResourceException {
 		assert type != null :  "车厢类型不能为null" ;
		Type s ;//车厢类型
		if(type.equals("商务")) {
			s = Type.BUSINESS;
		}else if(type.equals("一等")) {
			s = Type.FIRSTCLASS ;
		}else if(type.equals("二等")) {
			s = Type.SECONDCLASS;
		}else if(type.equals("软卧")) {
			s = Type.SOFTSLEPPER ;
		}else if(type.equals("硬卧")) {
			s = Type.TOURISTCOACH;
		}else if(type.equals("硬座")) {
			s = Type.HARDSEATS ;
		}else if(type.equals("行李车")) {
			s = Type.BUGGAGECAR ;
		}else {
			s = Type.RESTAURANTCAR ;
		}
		Railway rail = new Railway(railNumber, s, size, year);
		for(Railway e  : resource) {
			if(e.equals(rail)) {
				for(TrainEntry f : trainEntry) {
					if(f.getResource().contains(rail)) {
						if(!f.getState().equals(ENDED.instance) || !f.getState().equals(CANCELLED.instance)) {
							throw new deleteResourceException(rail) ;
						}
					}
				}
				resource.remove(e);
				return true;
			}
		}
		return false ;
	}
	/**
	 * 列出可用的车厢的信息
	 */
	public void showResource() {
		String[] columNames = { "序号" , "可用的车厢的信息如下"} ;//表格第一行信息
		Object[][] rowData = new String[resource.size()][] ;
		for(int i = 0 ; i<resource.size() ; i++	) {
			rowData[i] = new String[] {String.valueOf(i+1) , resource.get(i).toString()};
		}
		JFrame jf =new JFrame("所有可用的车厢的信息");
		Show.show(columNames, rowData, jf);
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
	 * 增加可用的位置
	 * @param loc 位置
	 * @return true:增加成功 ,false :增加失败
	 */
	public boolean addLocation(Location loc) {
		assert loc != null ;
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
	 * @throws deleteLocationException 删除某位置的时候，有尚未结束的计划项会在该位置执行
	 */
	public boolean deleteLocation(Location loc) throws deleteLocationException {
		
		if(location.contains(loc)) {
			for(TrainEntry e : trainEntry) {
				if(e.getLocation().contains(loc)) {
					if(!e.getState().equals(ENDED.instance) || !e.getState().equals(CANCELLED.instance)) {
						throw new deleteLocationException(loc);
					}
				}
			}
			location.remove(loc);
			return true;
		}
		return false ;
	}
	
	/**
	 * 列出和某个位置相关的所有高铁的信息板
	 * @param loc 位置
	 */
	public void show(Location loc, Calendar time1 ) {
		TrainScheduleBoard Board = new TrainScheduleBoard(time1,loc, trainEntry ) ;
		Board.show(loc, time1);
	}
	/**
	 * 展示某个位置的信息板
	 */
	public void board(Location loc ,Calendar time1 , int x) {
		time= time1 ;
		TrainScheduleBoard Board = new TrainScheduleBoard(time1,loc, trainEntry ) ;
		Board.visualize(x);
	}
	
	/**
	 * 检测计划项是否存在资源冲突
	 * @return true : 不存在冲突，false: 存在充冲突
	 */
	public boolean check() {
		List<PlanningEntry> TRAIN = new ArrayList<>() ;
		TRAIN.addAll(trainEntry);
		if(!new APIs.PlanningEntryAPIs().checkResourceExclusiveConflict(TRAIN,new checkResourceExclusiveConflict1())) {//资源或者位置存在冲突
			return false ;
		}
		return true ;
	}
	/**
	 * 可视化某个和某个高铁使用相同车厢的前序高铁的信息
	 * @param railNumber 车厢编号
	 * @param type 车厢类型
	 * @param size 载客人数
	 * @param year 出厂年份
	 * @param name : 高铁车次号
	 * @param 贴膜 : 高铁出发时间
	 * @return true:删除成功 ， false :删除失败
	 */
	public boolean getPrePlanningEntry(String railNumber , String type ,int size, int year ,String name , Calendar time ) {
		assert type != null : "车厢类型不能为null" ;
		Type s ;//车厢类型
		if(type.equals("商务")) {
			s = Type.BUSINESS;
		}else if(type.equals("一等")) {
			s = Type.FIRSTCLASS ;
		}else if(type.equals("二等")) {
			s = Type.SECONDCLASS;
		}else if(type.equals("软卧")) {
			s = Type.SOFTSLEPPER ;
		}else if(type.equals("硬卧")) {
			s = Type.TOURISTCOACH;
		}else if(type.equals("硬座")) {
			s = Type.HARDSEATS ;
		}else if(type.equals("行李车")) {
			s = Type.BUGGAGECAR ;
		}else {
			s = Type.RESTAURANTCAR ;
		}
		Railway rail = new Railway(railNumber, s, size, year);
		TrainEntry train = null ;
		for(TrainEntry e : trainEntry) {
			if(e.getName().equals(name)) {
				if(e.getBeginEndTime().getdate1().equals(time)) {
					train = e ;//找到该高铁
				}
			}
		}
		if(train == null )
			return false ;
		PlanningEntry<Railway> tt =  PlanningEntryAPIs.findPreEntryPerResource(rail ,train, trainEntry) ;//找到前序高铁
		if(tt == null) {
			return false ;
		}else {
			String[] columNames = {   "时间" , "高铁车次" ,"行程" , "状态"} ;//表格第一行信息
			Object[][] rowData = new String[1][] ;
			Location loc1 = ((TrainEntry)tt).getLocation().get(0);
			Location loc2 = ((TrainEntry)tt).getLocation().get(((TrainEntry)tt).getLocation().size()-1);
			rowData[0] = new String[] {tt.getBeginEndTime().getDate1() + "->" + tt.getBeginEndTime().getDate2() , tt.getName() , loc1.getName() + " - " + loc2.getName() , tt.getState().toString()};
			JFrame jf =new JFrame("高铁车次:" + name +"的前序计划项");
			Show.show(columNames, rowData, jf);//可视化
		}	
		return true ;

	}

}
