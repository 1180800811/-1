package Board;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JFrame;
import EntryState.ALOCATED;
import EntryState.BLOCKED;
import EntryState.ENDED;
import EntryState.RUNNING;
import EntryState.WAITTING;
import Location.Location;
import PlanningEntry.PlanningEntry;
import PlanningEntry.TrainEntry;


public class TrainScheduleBoard implements Iterable<PlanningEntry>{
	/*
	 * RI: 
	 * time , location , train is not null
	 * 
	 * Abstraction function:
	 * 代表一个信息板，由信息板的位置、所有的高铁车次、当前时间组成
	 * 	Safety from rep exposure:
	 *	all the field is private and immutable
	 *	there is no exposure of all the fields
	 * 
	 */
	private Calendar time = Calendar.getInstance() ;//屏幕版当前时间
	private Location location ; //屏幕板所在的位置
	private List<TrainEntry> train = new ArrayList<TrainEntry>() ;//一系列的计划项
	
	
	/**
	 * 构造器初始化
	 * @param time 当前时间
	 * @param location 信息板所在的位置
 	 * @param train 所有的高铁车次
	 */
	public TrainScheduleBoard(Calendar time, Location location, List<TrainEntry> train) {
		super();
		this.time = time;
		this.location = location;
		this.train = train;
	}

	/**
	 * 得到所有和location相关的高铁车次
	 * @return
	 */
	public List<TrainEntry> getTrain(){
		List<TrainEntry> Train = new ArrayList<TrainEntry>() ;//得到所有经停location的高铁车次
		for(int i = 0 ; i < train.size() ; i++	) {
			if(train.get(i).getLocation().contains(location)) {
				changeState(train.get(i));
				Train.add(train.get(i)) ;
			}
		}
		Collections.sort(Train, new Comparator<TrainEntry>() {//按照出发时间排序
			@Override
			public int compare(TrainEntry o1, TrainEntry o2) {
				return o1.getBeginEndTime().getdate1().compareTo(o2.getBeginEndTime().getdate1());
			}		
		});
		return Train ;
	}

	/**
	 * 根据当前的时间来设置高铁的状态
	 * @param flightss
	 */
	public  void changeState(TrainEntry train) {
		boolean flag = true ;
		if(train.getResource().size() == 0 ) {
			train.setState(WAITTING.instance );//高铁还未分配车厢
			return ;
		}
		if(time.compareTo(train.getBeginEndTime().getdate1()) < 0) {
			train.setState(ALOCATED.instance );//高铁还未分配车厢
			return ;
		}else if(time.compareTo(train.getBeginEndTime().getdate2()) > 0) {
			train.setState(ENDED.instance );//高铁还未分配车厢
			return ;
		}else {
			for(int i = 0 ; i < train.getTimeslots().size() ; i++) {
				if((time.compareTo(train.getTimeslots().get(i).getdate1() )> 0) && (time.compareTo(train.getTimeslots().get(i).getdate2() )< 0)) {
					flag =false ;//高铁正在运行中
				}
			}
		}
		if(flag) {
			train.setState(BLOCKED.instance );//高铁还未分配车厢
			return ;
		}else {
			train.setState(RUNNING.instance );//高铁还未分配车厢
			return ;
		}
		
	}
	
	/**
	 * 得到所有的抵达location的高铁车次及其对应的抵达时间
	 */
	public Map<Calendar,TrainEntry>  gettrain1(){  
		List<TrainEntry> trains = getTrain() ;
		Map<Calendar,TrainEntry> t = new TreeMap<>(//按照键的时间大小进行排序
				new Comparator<Calendar>() {
					@Override
					public int compare(Calendar o1, Calendar o2) {
						if(o1.getTime().before(o2.getTime())) {
							return -1 ;
						}else if(o1.getTime().before(o2.getTime())) {
							return 1 ;
						}
						return 0 ;
					}
				
				}) ;  
		for(int i = 0 ; i < trains.size() ; i++ ) { 
				for(int j = 1 ; j < trains.get(i).getLocation().size() ; j ++) {
					if(trains.get(i).getLocation().get(j).equals(location)) {
						t.put( trains.get(i).getTimeslots().get(j-1).getdate2(),trains.get(i)) ;
					}
				}		
		}
		return t ;
	}
	
	/**
	 * 得到所有的从location出发的高铁车次及其对应的出发时间
	 */
	public Map<Calendar,TrainEntry>  gettrain2(){//按照键的时间大小进行排序
		List<TrainEntry> trains = getTrain() ;
		Map<Calendar,TrainEntry> t = new TreeMap<>(
				new Comparator<Calendar>() {
					@Override
					public int compare(Calendar o1, Calendar o2) {
						if(o1.getTime().before(o2.getTime())) {
							return -1 ;
						}else if(o1.getTime().before(o2.getTime())) {
							return 1 ;
						}
						return 0 ;
					}
				
				}) ;  
		for(int i = 0 ; i < trains.size() ; i++ ) {
				for(int j = 0 ; j < trains.get(i).getLocation().size()-1 ; j ++) {
					if(trains.get(i).getLocation().get(j).equals(location)) {
						t.put( trains.get(i).getTimeslots().get(j).getdate1(),trains.get(i)) ;
					}
				}
		}
		return t ;
	}
	
	/**
	 * 列出和某个位置相关的所有计划项的信息
	 * @param loc 位置
	 */
	public void show(Location loc, Calendar time1 ) {
		this.location = loc ;
		List<TrainEntry> train = getTrain() ;//得到所有经过loc的位置
		Collections.sort(train, new Comparator<TrainEntry>() {//按时间增序排列高铁计划项
			@Override
			public int compare(TrainEntry o1, TrainEntry o2) {
				return o1.getTimeslots().get(0).getdate1().compareTo(o2.getTimeslots().get(0).getdate2());
			}
			
		});
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		String s = sdf.format(time.getTime());//当前时间
		String[] columNames = { "序号" , "时间" , "高铁车次" ,"行程" , "状态"} ;//表格第一行信息
		Object[][] rowData = new String[train.size()][] ;
		for(int i = 0 ; i < train.size() ; i++) {
			rowData[i] = new String[] {String.valueOf(i+1), sdf.format(train.get(i).getBeginEndTime().getdate1().getTime()) + "->" + sdf.format(train.get(i).getBeginEndTime().getdate1().getTime()),
					train.get(i).getName() ,train.get(i).getLocation().get(0).getName()+ " - " + train.get(i).getLocation().get(train.get(i).getLocation().size()-1).getName() ,train.get(i).getState().toString()
			};
		}
		JFrame jf = new JFrame(s  + "(当前时间)" + " , " + location.getName());
		Show.show(columNames, rowData, jf);
	}
	/**
	 * 可视化信息板
	 * @param x 0:展示抵达车次， 1 ：展示出发车次
	 */
	public void visualize(int x) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		String s = sdf.format(time.getTime());//当前时间
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm"); 
		Map<Calendar,TrainEntry> trainn = new TreeMap<Calendar, TrainEntry>() ;
		if(x == 0) {
			trainn = gettrain1() ;
		}else {
			trainn = gettrain2();
		}
		Map<Calendar,TrainEntry> train1 = new TreeMap<Calendar, TrainEntry>() ;
		Iterator<Calendar> TRAIN = trainn.keySet().iterator();//得到所有和当前时间相差1个小时的计划项及其对应的时间关系图
		while(TRAIN.hasNext()) {
			Calendar TIME= TRAIN.next();
			if(Math.abs(TIME.getTime().getTime() - this.time.getTime().getTime()) <= 3600000) {
				train1.put(TIME, trainn.get(TIME)) ;
			}
		}
		String[] columNames = { "序号" , "时间" , "高铁车次" ,"行程" , "状态"} ;//表格第一行信息
		Object[][] rowData = new String[train1.size()][] ;
		Iterator<Calendar> TRAIN1 = train1.keySet().iterator();
		int i = 0 ;
		while(TRAIN1.hasNext()) {
			Calendar ttt = TRAIN1.next() ;
			rowData[i] = new String[]{String.valueOf(i+1) ,sdf1.format(ttt.getTime()) ,train1.get(ttt).getName() ,train1.get(ttt).getLocation().get(0).getName() + "-" +
			train1.get(ttt).getLocation().get(train1.get(ttt).getLocation().size()-1).getName() , train1.get(ttt).getState().toString()  };
			i++ ;
		}
		JFrame jf = new JFrame(s  + "(当前时间)" + " , " + location.getName());
		Show.show(columNames, rowData, jf);
		
		
	}
	/**
	 * 迭代器
	 */
	@Override
	public Iterator<PlanningEntry> iterator() {
		return new PlanningEntryInteator() ;
	}
	/**
	 * 自己实现的迭代器
	 */
	public class PlanningEntryInteator implements Iterator<PlanningEntry>{
		private  List<TrainEntry> t = getTrain() ;//所以经停location的高铁车次
		private int curror = -1;
		private int size = t.size() ;//高铁车次个数
		@Override
		public boolean hasNext() {
			return curror + 1 < size;
		}

		@Override
		public PlanningEntry next() {
			curror ++ ;
			return t.get(curror);
	}	
}	
}





