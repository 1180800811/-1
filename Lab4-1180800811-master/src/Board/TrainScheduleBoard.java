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
	 * ����һ����Ϣ�壬����Ϣ���λ�á����еĸ������Ρ���ǰʱ�����
	 * 	Safety from rep exposure:
	 *	all the field is private and immutable
	 *	there is no exposure of all the fields
	 * 
	 */
	private Calendar time = Calendar.getInstance() ;//��Ļ�浱ǰʱ��
	private Location location ; //��Ļ�����ڵ�λ��
	private List<TrainEntry> train = new ArrayList<TrainEntry>() ;//һϵ�еļƻ���
	
	
	/**
	 * ��������ʼ��
	 * @param time ��ǰʱ��
	 * @param location ��Ϣ�����ڵ�λ��
 	 * @param train ���еĸ�������
	 */
	public TrainScheduleBoard(Calendar time, Location location, List<TrainEntry> train) {
		super();
		this.time = time;
		this.location = location;
		this.train = train;
	}

	/**
	 * �õ����к�location��صĸ�������
	 * @return
	 */
	public List<TrainEntry> getTrain(){
		List<TrainEntry> Train = new ArrayList<TrainEntry>() ;//�õ����о�ͣlocation�ĸ�������
		for(int i = 0 ; i < train.size() ; i++	) {
			if(train.get(i).getLocation().contains(location)) {
				changeState(train.get(i));
				Train.add(train.get(i)) ;
			}
		}
		Collections.sort(Train, new Comparator<TrainEntry>() {//���ճ���ʱ������
			@Override
			public int compare(TrainEntry o1, TrainEntry o2) {
				return o1.getBeginEndTime().getdate1().compareTo(o2.getBeginEndTime().getdate1());
			}		
		});
		return Train ;
	}

	/**
	 * ���ݵ�ǰ��ʱ�������ø�����״̬
	 * @param flightss
	 */
	public  void changeState(TrainEntry train) {
		boolean flag = true ;
		if(train.getResource().size() == 0 ) {
			train.setState(WAITTING.instance );//������δ���䳵��
			return ;
		}
		if(time.compareTo(train.getBeginEndTime().getdate1()) < 0) {
			train.setState(ALOCATED.instance );//������δ���䳵��
			return ;
		}else if(time.compareTo(train.getBeginEndTime().getdate2()) > 0) {
			train.setState(ENDED.instance );//������δ���䳵��
			return ;
		}else {
			for(int i = 0 ; i < train.getTimeslots().size() ; i++) {
				if((time.compareTo(train.getTimeslots().get(i).getdate1() )> 0) && (time.compareTo(train.getTimeslots().get(i).getdate2() )< 0)) {
					flag =false ;//��������������
				}
			}
		}
		if(flag) {
			train.setState(BLOCKED.instance );//������δ���䳵��
			return ;
		}else {
			train.setState(RUNNING.instance );//������δ���䳵��
			return ;
		}
		
	}
	
	/**
	 * �õ����еĵִ�location�ĸ������μ����Ӧ�ĵִ�ʱ��
	 */
	public Map<Calendar,TrainEntry>  gettrain1(){  
		List<TrainEntry> trains = getTrain() ;
		Map<Calendar,TrainEntry> t = new TreeMap<>(//���ռ���ʱ���С��������
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
	 * �õ����еĴ�location�����ĸ������μ����Ӧ�ĳ���ʱ��
	 */
	public Map<Calendar,TrainEntry>  gettrain2(){//���ռ���ʱ���С��������
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
	 * �г���ĳ��λ����ص����мƻ������Ϣ
	 * @param loc λ��
	 */
	public void show(Location loc, Calendar time1 ) {
		this.location = loc ;
		List<TrainEntry> train = getTrain() ;//�õ����о���loc��λ��
		Collections.sort(train, new Comparator<TrainEntry>() {//��ʱ���������и����ƻ���
			@Override
			public int compare(TrainEntry o1, TrainEntry o2) {
				return o1.getTimeslots().get(0).getdate1().compareTo(o2.getTimeslots().get(0).getdate2());
			}
			
		});
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		String s = sdf.format(time.getTime());//��ǰʱ��
		String[] columNames = { "���" , "ʱ��" , "��������" ,"�г�" , "״̬"} ;//����һ����Ϣ
		Object[][] rowData = new String[train.size()][] ;
		for(int i = 0 ; i < train.size() ; i++) {
			rowData[i] = new String[] {String.valueOf(i+1), sdf.format(train.get(i).getBeginEndTime().getdate1().getTime()) + "->" + sdf.format(train.get(i).getBeginEndTime().getdate1().getTime()),
					train.get(i).getName() ,train.get(i).getLocation().get(0).getName()+ " - " + train.get(i).getLocation().get(train.get(i).getLocation().size()-1).getName() ,train.get(i).getState().toString()
			};
		}
		JFrame jf = new JFrame(s  + "(��ǰʱ��)" + " , " + location.getName());
		Show.show(columNames, rowData, jf);
	}
	/**
	 * ���ӻ���Ϣ��
	 * @param x 0:չʾ�ִﳵ�Σ� 1 ��չʾ��������
	 */
	public void visualize(int x) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		String s = sdf.format(time.getTime());//��ǰʱ��
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm"); 
		Map<Calendar,TrainEntry> trainn = new TreeMap<Calendar, TrainEntry>() ;
		if(x == 0) {
			trainn = gettrain1() ;
		}else {
			trainn = gettrain2();
		}
		Map<Calendar,TrainEntry> train1 = new TreeMap<Calendar, TrainEntry>() ;
		Iterator<Calendar> TRAIN = trainn.keySet().iterator();//�õ����к͵�ǰʱ�����1��Сʱ�ļƻ�����Ӧ��ʱ���ϵͼ
		while(TRAIN.hasNext()) {
			Calendar TIME= TRAIN.next();
			if(Math.abs(TIME.getTime().getTime() - this.time.getTime().getTime()) <= 3600000) {
				train1.put(TIME, trainn.get(TIME)) ;
			}
		}
		String[] columNames = { "���" , "ʱ��" , "��������" ,"�г�" , "״̬"} ;//����һ����Ϣ
		Object[][] rowData = new String[train1.size()][] ;
		Iterator<Calendar> TRAIN1 = train1.keySet().iterator();
		int i = 0 ;
		while(TRAIN1.hasNext()) {
			Calendar ttt = TRAIN1.next() ;
			rowData[i] = new String[]{String.valueOf(i+1) ,sdf1.format(ttt.getTime()) ,train1.get(ttt).getName() ,train1.get(ttt).getLocation().get(0).getName() + "-" +
			train1.get(ttt).getLocation().get(train1.get(ttt).getLocation().size()-1).getName() , train1.get(ttt).getState().toString()  };
			i++ ;
		}
		JFrame jf = new JFrame(s  + "(��ǰʱ��)" + " , " + location.getName());
		Show.show(columNames, rowData, jf);
		
		
	}
	/**
	 * ������
	 */
	@Override
	public Iterator<PlanningEntry> iterator() {
		return new PlanningEntryInteator() ;
	}
	/**
	 * �Լ�ʵ�ֵĵ�����
	 */
	public class PlanningEntryInteator implements Iterator<PlanningEntry>{
		private  List<TrainEntry> t = getTrain() ;//���Ծ�ͣlocation�ĸ�������
		private int curror = -1;
		private int size = t.size() ;//�������θ���
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





