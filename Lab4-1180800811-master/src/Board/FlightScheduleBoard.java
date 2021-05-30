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
	 * ����һ����Ϣ�壬����Ϣ���λ�á����еĺ��ࡢ��ǰʱ�����
	 * 	Safety from rep exposure:
	 *	all the field is private and immutable
	 *	there is no exposure of all the fields
	 * 
	 */
	private Calendar time = Calendar.getInstance() ;//��Ļ�浱ǰʱ��
	private Location location ; //��Ļ�����ڵ�λ��
	private List<FlightEntry> flight = new ArrayList<FlightEntry>() ;//һϵ�еļƻ���
	
	/**
	 * ��������ʼ��
	 * @param location ��Ϣ���λ��
	 * @param flight ���еĺ���
	 * @param time ��ǰʱ��
	 */
	public FlightScheduleBoard(Location location, List<FlightEntry> flight , Calendar time) {
		super();
		this.location = location;
		this.flight = flight;
		this.time = time ;
		Collections.sort(flight, new FlightCompare1());//���պ�����ʱ���������
	}
	
	/**
	 * ������г���λ����location���Ұ��ճ���ʱ���������ĺ���
	 */
	public List<FlightEntry> getFlight1(){
		List<FlightEntry> t = new ArrayList<>() ;//���г���λ����location�ĺ���
		for(int i = 0 ; i < flight.size() ; i ++  ) {
			if(flight.get(i).getStartLocation().equals(location)) {
				t.add(flight.get(i));
				changeState(flight.get(i));//���ݵ�ǰʱ��ı�״̬
			}
		}
		Collections.sort(t, new FlightCompare1());//��ʱ������
		return t ;
	}

	/**
	 * ������е���λ����location���Ұ��յ���ʱ���������ĺ���
	 */
	public List<FlightEntry> getFlight2(){
		List<FlightEntry> t = new ArrayList<>() ;//���еִ�λ����location�ĺ���
		for(int i = 0 ; i < flight.size() ; i ++  ) {
			if(flight.get(i).getEndLocation().equals(location)) {
				t.add(flight.get(i));
				changeState(flight.get(i));//���ݵ�ǰʱ��ı�״̬
			}
		}
		Collections.sort(t, new FlightCompare2());//��ʱ������
		return t ;
	}
	

	
	/**
	 * ���ݵ�ǰ��ʱ�������ú����״̬
	 * @param flightss ������״̬�ĺ���
	 */
	public  void changeState(FlightEntry flight) {
		if(flight.getResource() == null ) {
			flight.setState(WAITTING.instance );//�γ̻�δ�����ʦ
			return ;
		}
		if(time.compareTo(flight.getTime1()) < 0) {
			flight.setState(ALOCATED.instance );//�γ̻�δ����ʼ������״̬ΪALOCATED
			return ;
		}else if(time.compareTo(flight.getTime2()) > 0) {
			flight.setState(ENDED.instance );//�γ��Ѿ�����,����״̬ΪENDED
			return ;
		}else {
			flight.setState(RUNNING.instance );//�γ����ڽ��У�����״̬ΪRUNNING
			return ;
		}	
	}

	/**
	 * չʾ���������λ����صĺ�����Ϣ
	 */
	public void show() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		String s = sdf.format(time.getTime());//��ǰʱ��
		List<FlightEntry> flights = new ArrayList<FlightEntry>();//���к͸�λ����صĺ���
		flights.addAll(getFlight2());
		flights.addAll(getFlight1());
		String[] columNames = { "���" , "ʱ��" , "����" ,"�г�" , "״̬"} ;//����һ����Ϣ
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
		JFrame jf = new JFrame(s  + "(��ǰʱ��)" + " , " + location.getName()+ "����");
		Show.show(columNames, rowData, jf);//���ӻ�

		
	}
	/**
	 * ���ӻ�չʾ����ǰλ�õ���Ϣ��
	 * @param x 0 ��չʾ�ִﺽ�� �� 1 :չʾ��������
	 */
	public void visualize(int x ) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); //ʱ����ʽ
		String s = sdf.format(time.getTime());
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm"); //ʱ����ʽ
		List<FlightEntry> flights = new ArrayList<FlightEntry>();
		if(x==0 ) {
			flights = getFlight2() ;//���еִ�λ����location�ĺ���
		}else {
			flights = getFlight1() ;//���г���λ����location�ĺ���
		}
		List<FlightEntry> flightss = new ArrayList<FlightEntry>();
		for(int i = 0  ; i < flights.size() ; i ++	) {
			if(Math.abs((x == 0 ? flights.get(i).getTime2().getTime() :flights.get(i).getTime1().getTime()).getTime() - time.getTime().getTime()) <= 3600000 ) {
				flightss.add(flights.get(i));
			}
		}
		String[] columNames = { "���" , "ʱ��" , "����" ,"�г�" , "״̬"} ;//����һ����Ϣ
		Object[][] rowData = new String[flightss.size()][] ;
		for(int i = 0  ; i < flightss.size() ; i ++	) {
			 rowData[i] = new String[] {String.valueOf(i+1) ,sdf1.format( x == 0 ? flightss.get(i).getTime2().getTime() :flightss.get(i).getTime1().getTime())
				,flightss.get(i).getName() , flightss.get(i).getStartLocation().getName() + "-" +	flightss.get(i).getEndLocation().getName()  , flightss.get(i).getState().toString() 
			 };
		 }
		JFrame jf = null ;
		if(x == 0  )
			jf = new JFrame(s  + "(��ǰʱ��)" + " , " + location.getName()+ "����" + "   �ִﺽ��");
		else
		   jf = new JFrame(s  + "(��ǰʱ��)" + " , " + location.getName()+ "����" + "   ��������");
		Show.show(columNames, rowData, jf);//���ӻ�
	}

	/**
	 * ������
	 */
	@Override
	public Iterator<PlanningEntry> iterator() {
		return new PlanningEntryInteator() ;
	}
	/**
	 * 
	 * �Լ�ʵ�ֵĵ�����
	 */
	public class PlanningEntryInteator implements Iterator<PlanningEntry>{
		private List<FlightEntry> flight1 =  getFlight2() ;//�ִ�λ����location�����к���
		private List<FlightEntry> flight2 =  getFlight1() ;//����λ����location�����к���
		private int curror = -1;
		private int size1 = flight1.size() ; //�ִ�λ����location�ĺ������
		private int size2 = flight2.size();//����λ����location�ĺ���ĸ���
		@Override
		public boolean hasNext() {
			return curror + 1 < (size1 + size2) ;
		}
		
		@Override
		public PlanningEntry next() {
			curror ++ ;
			if(curror < size1)
				return flight1.get(curror) ;//�ȱ����ִﺽ��
			else
				return flight2.get(curror-size1);//�ٱ�����������
		}
	}
}
