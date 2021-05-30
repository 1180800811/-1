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
	 * resource , location , flightEntry �� time is not null
	 * 
	 * Abstraction function:
	 * ����һ������������һϵ�еĸ������Ρ����õĳ��ᡢ���õ�λ�ú͵�ǰʱ�����
	 * 	Safety from rep exposure:
	 *	all the field is private and immutable
	 *	there is no exposure of all the fields
	 * 
	 */
	private List<Railway> resource =new ArrayList<Railway>() ;//���õ���Դ
	private List<Location> location = new ArrayList<Location>() ;//���õ�λ��
	private List<TrainEntry> trainEntry =  new ArrayList<TrainEntry>()  ;//һϵ�мƻ���
	private Calendar time = Calendar.getInstance();
	
	
	public void checkRep() {
		assert resource != null ; 
		assert location != null ;
		assert trainEntry != null ;
		assert time != null ;
	}
	/**
	 * ����һ����������
	 * @param loc ������һϵ��λ��
	 * @param timeslot ������һϵ��ʱ��
	 * @param name �������κ�
	 * @return true:���ӳɹ� �� false:����ʧ��
	 */
	public boolean addPlanningEntry(List<Location> loc , List<Timeslot> timeslot , String name) {

		TrainEntry train = new TrainEntry(name);//�����ӵĸ�������
		train.setLocations(loc);
		train.setTimeslots(timeslot);
		if(check()) {//����Ƿ������Դ��ͻ
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
	 * ���õ�ǰ��ʱ��
	 * @param time ���趨��ʱ��
	 */
	public void setTime(Calendar time) {
		assert time != null ;
		this.time  = time ;
	}
	/**
	 * ȡ��ĳ����������
	 * @param trainNumber ��ȡ���ĸ������κ�
	 * @param time1 ��ȡ���ĸ�������ʱ��
	 * @return ��ʾ�����Ϣ
	 * @throws cancelPlanningEntryException ȡ��ĳ�ƻ����ʱ�򣬸üƻ���ĵ�ǰ״̬������ȡ��
	 */
	public String cancelPlanningEntry(String trainNumber , Calendar time1 ) throws cancelPlanningEntryException{
		for(TrainEntry e : trainEntry	) {
			if(e.getName().equals(trainNumber)) {
				if(e.getBeginEndTime().getdate1().equals(time1)) {
					 if(e.getState() instanceof ALOCATED || e.getState() instanceof WAITTING) {
						 e.setState(CANCELLED.instance);
						 return "�����Ѿ�ȡ��";
					 }else {
						 throw new cancelPlanningEntryException(e) ;
					 }
				}
			}
		}
		return "ָ���ĸ���������";
	}
	
	/**
	 * Ϊĳ���ƻ��������Դ
	 * @param course  ��������Դ�ĸ�������
	 * @param teacher Ϊ�������η���ĳ���
	 * @param return 0 : ��Ҫ����ĳ��᲻�ڿ��ó����� 1 : ��Ҫ������Դ�ļƻ����Ѿ������˸���Դ 2 :����ɹ�  , 4:��Ҫ���䳵��ĸ���������
	 * @throws feipeiResourceException ������Դ֮�������Դ��ͻ
	 */
	public int FeiPeiResource(String name , Calendar time , Railway railway	) throws feipeiResourceException {

			if( !resource.contains(railway)) {
				return 0 ;//��Ҫ����ĳ��᲻�ڿ��ó�����
			}

		TrainEntry t = null ;
		for(TrainEntry e : trainEntry) {
			if(e.getName().equals(name)) {
				if(e.getTimeslots().get(0).getdate1().equals(time)) {
					t= e ;
					if( ! e.getResource().contains(railway)) {//δ������Դ
						e.addResource(railway);
						List<PlanningEntry> t1 = new ArrayList<PlanningEntry>() ;  
						t1.addAll(trainEntry);
						if(!APIs.PlanningEntryAPIs.checkResourceExclusiveConflict(t1, new checkResourceExclusiveConflict1())){//�����Դ��ͻ
							e.setResource(null);
							throw new feipeiResourceException(railway) ;
						}
						e.setState(ALOCATED.instance);
						return 2 ;//����ɹ�
					}else {//��Ҫ������Դ�ļƻ����Ѿ�������Դ
						return 1 ;
					}
				}
			}
		}
		if(t == null) {
			return 4 ;//��Ҫ���䳵��ĸ���������
		}
		return 2 ;
	}
	/**
	 * ��ѯ�����״̬
	 * @param name ��������
	 * @param time1 ����ʱ��
	 * @param TIME ��ǰʱ��
	 * @return �������εĵ�ǰ״̬
	 */
	public State WatchState(String name ,Calendar time1 ,Calendar TIME) {
		this.time = TIME;
		for(TrainEntry e : trainEntry	) {
			if(e.getName().equals(name)) {
				if(e.getBeginEndTime().getdate1().equals(time1)) {	
						changeState(e);//���ݵ�ǰʱ���趨������ת̬
						return e.getState();//�����趨���״̬
				}
			}
		}
		 return null ;//δ�ҵ�����
	}
	/**
	 * ���ݵ�ǰ��ʱ�������ø�����״̬
	 * @param flightss
	 */
	public  void changeState(TrainEntry train) {
		assert train != null ;
		if(train.getResource() == null ) {
			train.setState(WAITTING.instance );//������δ���䳵��
			return ;
		}
		if(time.compareTo(train.getBeginEndTime().getdate1()) < 0) {
			train.setState(ALOCATED.instance );//������δ����
			return ;
		}else if(time.compareTo(train.getBeginEndTime().getdate2()) > 0) {
			train.setState(ENDED.instance );//�����Ѿ�����
			return ;
		}else {
			for(int i = 0 ; i < train.getTimeslots().size() ; i++) {
				if((time.compareTo(train.getTimeslots().get(i).getdate1() )>= 0) && (time.compareTo(train.getTimeslots().get(i).getdate2() )<= 0)) {
					train.setState(RUNNING.instance );//������������
					return ;
				}
			}
		}
		
			train.setState(BLOCKED.instance );//����������
			return ;

		
	}
	
	/**
	 * ����ĳ����������
	 * @param trainNumber �������ĸ������κ�
	 * @param time1 �������ĸ�������ʱ��
	 * @return ��ʾ����Ϣ
	 */
	public String BeginPlanningEntry(String trainNumber , Calendar time1 ){
		assert trainNumber != null ;
		assert time1 != null ;
		for(TrainEntry e : trainEntry	) {
			if(e.getName().equals(trainNumber)) {
				if(e.getBeginEndTime().getdate1().equals(time1)) {
					 if(e.getState() instanceof ALOCATED) {
						 e.setState(RUNNING.instance);
						 return "�����Ѿ�����";
					 }else {
						 return "������ǰ״̬Ϊ" +e.getState().toString() + ",��������" ;
					 }
				}
			}
		}
		return "ָ���ĸ���������";
	}
	
	/**
	 * ����ĳ����������
	 * @param trainNumber �ý����ĸ������κ�
	 * @param time1 �������ĸ�������ʱ��
	 * @return ��ʾ�����Ϣ
	 */
	public String EndPlanningEntry(String trainNumber , Calendar time1)  {
		assert trainNumber != null ;
		assert time1 != null ;
		for(TrainEntry e : trainEntry	) {
			if(e.getName().equals(trainNumber)) {
				if(e.getBeginEndTime().getdate1().equals(time1)) {
					 if(e.getState() instanceof  RUNNING) {
						 e.setState(ENDED.instance);
						 return "�����Ѿ�����";
					 }else {
						 return "������ǰ״̬Ϊ" +e.getState().toString() + ",���ܽ���" ;
					 }
				}
			}
		}
		return "ָ���ĸ���������";
	}
	/**
	 * ����ĳ����������
	 * @param trainNumber �������ĸ������κ�
	 * @param time1 �������ĸ�������ʱ��
	 * @return ��ʾ�����Ϣ
	 */
	public String BlockPlanningEntry(String trainNumber , Calendar time1)  {
		assert trainNumber != null ;
		assert time1 != null ;
		for(TrainEntry e : trainEntry	) {
			if(e.getName().equals(trainNumber)) {
				if(e.getBeginEndTime().getdate1().equals(time1)) {
					 if(e.getState() instanceof  RUNNING) {
						 e.Block();//������������
						 return "�����Ѿ�����";
					 }else {
						 return "������ǰ״̬Ϊ" +e.getState().toString() + ",��������" ;
					 }
				}
			}
		}
		return "ָ���ĸ���������";
	}
		
	
	/**
	 * չʾʹ��ĳ����Դ�����и�������
	 * @param railNumber ������
	 * @param type ��������
	 * @param size �ؿ�����
	 * @param year �������
	 * @param TIME ��ǰʱ��
	 */
	public void getResourcePlanningEntry(String railNumber , String type ,int size, int year,Calendar TIME ) {
		assert type != null : "�������Ͳ���Ϊnull" ;
		Type s ;//��������
		if(type.equals("����")) {
			s = Type.BUSINESS;
		}else if(type.equals("һ��")) {
			s = Type.FIRSTCLASS ;
		}else if(type.equals("����")) {
			s = Type.SECONDCLASS;
		}else if(type.equals("����")) {
			s = Type.SOFTSLEPPER ;
		}else if(type.equals("Ӳ��")) {
			s = Type.TOURISTCOACH;
		}else if(type.equals("Ӳ��")) {
			s = Type.HARDSEATS ;
		}else if(type.equals("���")) {
			s = Type.BUGGAGECAR ;
		}else {
			s = Type.RESTAURANTCAR ;
		}
		Railway rail = new Railway(railNumber, s, size, year);//����
		this.time = TIME ;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		List<TrainEntry> train = new ArrayList<TrainEntry>() ;//ʹ�øó����ȫ������
		for(TrainEntry e : trainEntry) {
			if( e.getResource().contains(rail)) {//ʹ�øó���ĸ���
				changeState(e);//�ı�״̬
				train.add(e);
			}
		}
		Collections.sort(train, new Comparator<TrainEntry>() {//��ʱ������
			@Override
			public int compare(TrainEntry o1, TrainEntry o2) {
				return o1.getTimeslots().get(0).getdate1().compareTo(o2.getTimeslots().get(0).getdate2());
			}	
		});//��ʱ������
		String ss = sdf.format(time.getTime());//��ǰʱ��
		String[] columNames = { "���" , "ʱ��" , "��������" ,"�г�" , "״̬"} ;//����һ����Ϣ
		Object[][] rowData = new String[train.size()][] ;
		for(int i = 0 ; i < train.size() ; i++) {
			rowData[i] = new String[] {String.valueOf(i+1), sdf.format(train.get(i).getBeginEndTime().getdate1().getTime()) + "->" + sdf.format(train.get(i).getBeginEndTime().getdate1().getTime()),
					train.get(i).getName() ,train.get(i).getLocation().get(0).getName()+ " - " + train.get(i).getLocation().get(train.get(i).getLocation().size()-1).getName() ,train.get(i).getState().toString()
			};
		}
		JFrame jf = new JFrame(ss  + "(��ǰʱ��)" + " , "+ "ʹ�ó���" + railNumber + "���и�������");
		Show.show(columNames, rowData, jf);//���ӻ�

	}
	
	/**
	 * ���ӿ�ʹ�õĳ���
	 * @param railNumber ������
	 * @param type ��������
	 * @param size �ؿ�����
	 * @param year �������
	 * @return true:���ӳɹ� �� false :����ʧ��
	 */
	public boolean addResource(String railNumber , String type ,int size, int year)  {
		assert type != null : "�������Ͳ���Ϊnull";
		Type s ;//��������
		if(type.equals("����")) {
			s = Type.BUSINESS;
		}else if(type.equals("һ��")) {
			s = Type.FIRSTCLASS ;
		}else if(type.equals("����")) {
			s = Type.SECONDCLASS;
		}else if(type.equals("����")) {
			s = Type.SOFTSLEPPER ;
		}else if(type.equals("Ӳ��")) {
			s = Type.TOURISTCOACH;
		}else if(type.equals("Ӳ��")) {
			s = Type.HARDSEATS ;
		}else if(type.equals("���")) {
			s = Type.BUGGAGECAR ;
		}else {
			s = Type.RESTAURANTCAR ;
		}
		Railway rail = new Railway(railNumber, s, size, year);//�����ӵĳ���
		if(!resource.contains(rail)) {
			resource.add(rail);
			return true ;
		}
		return false ;
		
	}
	
	/**
	 * ɾ����ʹ�õĳ���
	 * @param railNumber ������
	 * @param type ��������
	 * @param size �ؿ�����
	 * @param year �������
	 * @return true:ɾ���ɹ� �� false :ɾ��ʧ��
	 * @throws deleteResourceException ɾ����Դ��ʱ������δ�����ļƻ�������ռ�ø���Դ
	 */
 	public boolean deleteResource(String railNumber , String type ,int size, int year) throws deleteResourceException {
 		assert type != null :  "�������Ͳ���Ϊnull" ;
		Type s ;//��������
		if(type.equals("����")) {
			s = Type.BUSINESS;
		}else if(type.equals("һ��")) {
			s = Type.FIRSTCLASS ;
		}else if(type.equals("����")) {
			s = Type.SECONDCLASS;
		}else if(type.equals("����")) {
			s = Type.SOFTSLEPPER ;
		}else if(type.equals("Ӳ��")) {
			s = Type.TOURISTCOACH;
		}else if(type.equals("Ӳ��")) {
			s = Type.HARDSEATS ;
		}else if(type.equals("���")) {
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
	 * �г����õĳ������Ϣ
	 */
	public void showResource() {
		String[] columNames = { "���" , "���õĳ������Ϣ����"} ;//����һ����Ϣ
		Object[][] rowData = new String[resource.size()][] ;
		for(int i = 0 ; i<resource.size() ; i++	) {
			rowData[i] = new String[] {String.valueOf(i+1) , resource.get(i).toString()};
		}
		JFrame jf =new JFrame("���п��õĳ������Ϣ");
		Show.show(columNames, rowData, jf);
	}
	/**
	 * �г����õ�λ�õ���Ϣ
	 */
	public void showLocation() {
		String[] columNames = { "���" , "���õ�λ�õ���Ϣ����"} ;//����һ����Ϣ
		Object[][] rowData = new String[location.size()][] ;
		for(int i = 0 ; i<location.size() ; i++	) {
			rowData[i] = new String[] {String.valueOf(i+1) , location.get(i).getName()};
		}
		JFrame jf =new JFrame("���п��õ�λ�õ���Ϣ");
		Show.show(columNames, rowData, jf);
	}
	/**
	 * ���ӿ��õ�λ��
	 * @param loc λ��
	 * @return true:���ӳɹ� ,false :����ʧ��
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
	 * ɾ�����õ�λ��
	 * @param loc λ��
	 * @return true:ɾ���ɹ� ,false :ɾ��ʧ��
	 * @throws deleteLocationException ɾ��ĳλ�õ�ʱ������δ�����ļƻ�����ڸ�λ��ִ��
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
	 * �г���ĳ��λ����ص����и�������Ϣ��
	 * @param loc λ��
	 */
	public void show(Location loc, Calendar time1 ) {
		TrainScheduleBoard Board = new TrainScheduleBoard(time1,loc, trainEntry ) ;
		Board.show(loc, time1);
	}
	/**
	 * չʾĳ��λ�õ���Ϣ��
	 */
	public void board(Location loc ,Calendar time1 , int x) {
		time= time1 ;
		TrainScheduleBoard Board = new TrainScheduleBoard(time1,loc, trainEntry ) ;
		Board.visualize(x);
	}
	
	/**
	 * ���ƻ����Ƿ������Դ��ͻ
	 * @return true : �����ڳ�ͻ��false: ���ڳ��ͻ
	 */
	public boolean check() {
		List<PlanningEntry> TRAIN = new ArrayList<>() ;
		TRAIN.addAll(trainEntry);
		if(!new APIs.PlanningEntryAPIs().checkResourceExclusiveConflict(TRAIN,new checkResourceExclusiveConflict1())) {//��Դ����λ�ô��ڳ�ͻ
			return false ;
		}
		return true ;
	}
	/**
	 * ���ӻ�ĳ����ĳ������ʹ����ͬ�����ǰ���������Ϣ
	 * @param railNumber ������
	 * @param type ��������
	 * @param size �ؿ�����
	 * @param year �������
	 * @param name : �������κ�
	 * @param ��Ĥ : ��������ʱ��
	 * @return true:ɾ���ɹ� �� false :ɾ��ʧ��
	 */
	public boolean getPrePlanningEntry(String railNumber , String type ,int size, int year ,String name , Calendar time ) {
		assert type != null : "�������Ͳ���Ϊnull" ;
		Type s ;//��������
		if(type.equals("����")) {
			s = Type.BUSINESS;
		}else if(type.equals("һ��")) {
			s = Type.FIRSTCLASS ;
		}else if(type.equals("����")) {
			s = Type.SECONDCLASS;
		}else if(type.equals("����")) {
			s = Type.SOFTSLEPPER ;
		}else if(type.equals("Ӳ��")) {
			s = Type.TOURISTCOACH;
		}else if(type.equals("Ӳ��")) {
			s = Type.HARDSEATS ;
		}else if(type.equals("���")) {
			s = Type.BUGGAGECAR ;
		}else {
			s = Type.RESTAURANTCAR ;
		}
		Railway rail = new Railway(railNumber, s, size, year);
		TrainEntry train = null ;
		for(TrainEntry e : trainEntry) {
			if(e.getName().equals(name)) {
				if(e.getBeginEndTime().getdate1().equals(time)) {
					train = e ;//�ҵ��ø���
				}
			}
		}
		if(train == null )
			return false ;
		PlanningEntry<Railway> tt =  PlanningEntryAPIs.findPreEntryPerResource(rail ,train, trainEntry) ;//�ҵ�ǰ�����
		if(tt == null) {
			return false ;
		}else {
			String[] columNames = {   "ʱ��" , "��������" ,"�г�" , "״̬"} ;//����һ����Ϣ
			Object[][] rowData = new String[1][] ;
			Location loc1 = ((TrainEntry)tt).getLocation().get(0);
			Location loc2 = ((TrainEntry)tt).getLocation().get(((TrainEntry)tt).getLocation().size()-1);
			rowData[0] = new String[] {tt.getBeginEndTime().getDate1() + "->" + tt.getBeginEndTime().getDate2() , tt.getName() , loc1.getName() + " - " + loc2.getName() , tt.getState().toString()};
			JFrame jf =new JFrame("��������:" + name +"��ǰ��ƻ���");
			Show.show(columNames, rowData, jf);//���ӻ�
		}	
		return true ;

	}

}
