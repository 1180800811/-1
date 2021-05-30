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
	private List<Plane> resource = new ArrayList<Plane>()  ;//���õ���Դ
	private List<Location> location = new ArrayList<Location>() ;//���õ�λ��
	private List<FlightEntry> flightEntry =  new ArrayList<FlightEntry>()  ;//һϵ�мƻ���
	private Calendar time = Calendar.getInstance();//��ǰ��ʱ��

	private int cloum =  1 ;//��ʶ��ȡ�ļ�������
	/*
	 * RI: 
	 * resource , location , flightEntry , time is not null
	 * 
	 * Abstraction function:
	 * ����һ�����������һϵ�еĺ��ࡢ���õķɻ������õĻ����͵�ǰʱ�����
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
	 * ���õ�ǰʱ��
	 * @param time ��ǰʱ��
	 */
 	public void setTime(Calendar time ) {
 		assert time != null ;
		this.time = time ;
	}
	/**
	 * 
	 * @param loc1 ��ʼλ��
	 * @param loc2 ��ֹλ��
	 * @param time1 ��ʼʱ��
	 * @param time2 ��ֹʱ��
	 * @param name ��������
	 */
	public boolean addPlanningEntry(Location loc1 ,Location loc2, Calendar time1 , Calendar time2 ,String name) {

		Plane plane = null ;//�ƻ���ʹ�õķɻ�
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
	 * ����Ϊ�յĹ�����
	 * @throws IOException 
	 * @throws SecurityException 
	 */
	public FlightSchedule(){
	}

	/**
	 * ����һ���ļ�����ʼ���������
	 * @param s
	 * @throws Exception
	 */
	public FlightSchedule(String s ) throws Exception {
		ReadFileCreatePlanningEntry(s);
		checkRep();
	}


	/**
	 * ȡ��ĳ������
	 * @param palneNumber ��ȡ���ĺ����
	 * @param time1 ��ȡ���ĺ������ʱ��
	 * @param time2 ��ȡ���ĺ��ൽ��ʱ��
	 * @return ��ʾ�����Ϣ
	 * @throws cancelPlanningEntryException ȡ���ƻ����ʱ�򣬸üƻ���ĵ�ǰ״̬������ȡ����
	 */
	public String cancelPlanningEntry(String palneNumber , Calendar time1 , Calendar time2) throws cancelPlanningEntryException{
		assert palneNumber != null : "����Ų���Ϊnull" ;
		for(FlightEntry e : flightEntry	) {
			if(e.getName().equals(palneNumber)) {
				if(e.getTime1().equals(time1)) {
					if(e.getTime2().equals(time2)) {
					 if(e.getState() instanceof ALOCATED || e.getState() instanceof WAITTING) {
						 e.setState(CANCELLED.instance);
						 return "�����Ѿ�ȡ��";
					 }else {
						 throw new cancelPlanningEntryException(e) ;
					 }
					}
				}
			}
		}
		return "ָ���ĺ��಻����";
	}
	
	/**
	 * Ϊĳ���ƻ��������Դ
	 * @param palneNumber  ��������Դ�ĺ���
	 * @param time1 ��������ʱ��
	 * @param time2 �������ֹʱ��
	 * @param name �ɻ��ı��
	 * @return 0 : ��Ҫ����ķɻ����ڿ��÷ɻ��� , 1 : ��Ҫ������Դ�ļƻ����Ѿ�������Դ 2 :����ɹ� , 3 :������Դ���ڳ�ͻ , 4:��Ҫ����ɻ��ĺ��಻����
	 * @throws cancelPlanningEntryException ������Դ���������е������ƻ����������Դ��ռ��ͻ����
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
	 * ����ĳ������
	 * @param palneNumbe �������ĺ����
	 * @param time1 �������ĺ������ʱ��
	 * @param time2 �������ĺ��ൽ��ʱ��
	 * @return ��ʾ�����Ϣ
	 */
	public String BeginPlanningEntry(String Name , Calendar time1 , Calendar time2){
		
		for(FlightEntry e : flightEntry	) {
			if(e.getName().equals(Name)) {
				if(e.getTime1().equals(time1)) {
					if(e.getTime2().equals(time2)) {
					 if(e.getState() instanceof ALOCATED) {
						 e.setState(RUNNING.instance);
						 return "�����Ѿ�����";
					 }else {
						 return "���൱ǰ״̬Ϊ" +e.getState().toString() + ",��������" ;
					 }
					}
				}
			}
		}
		return "ָ���ĺ��಻����";
	}
	
	/**
	 * ����ĳ������
	 * @param palneNumbe �������ĺ����
	 * @param time1 �������ĺ������ʱ��
	 * @param time2 �������ĺ��ൽ��ʱ��
	 * @return ��ʾ�����Ϣ
	 */
	public String EndPlanningEntry(String palneNumber , Calendar time1 , Calendar time2)  {
		for(FlightEntry e : flightEntry	) {
			if(e.getName().equals(palneNumber)) {
				if(e.getTime1().equals(time1)) {
					if(e.getTime2().equals(time2)) {
					 if(e.getState() instanceof  RUNNING) {
						 e.setState(ENDED.instance);//����״̬Ϊ�Ѿ�����
						 return "�����Ѿ�����";
					 }else {
						 return "���൱ǰ״̬Ϊ" +e.getState().toString() + ",���ܽ���" ;
					 }
					}
				}
			}
		}
		return "ָ���ĺ��಻����";
	}
	
	/**
	 * ���ݵ�ǰ��ʱ�������ú����״̬
	 * @param flightss ���趨״̬�ĺ���
	 */
	public  void changeState(FlightEntry flight) {
		assert flight != null ;
		if(flight.getResource() == null ) {
			flight.setState(WAITTING.instance );//���໹δ����ɻ�
			return ;
		}
		if(time.compareTo(flight.getTime1()) < 0) {
			flight.setState(ALOCATED.instance );//���໹δ����������״̬ΪALOCATED
			return ;
		}else if(time.compareTo(flight.getTime2()) > 0) {
			flight.setState(ENDED.instance );//�����Ѿ�����,����״̬ΪENDED
			return ;
		}else {
			flight.setState(RUNNING.instance );//�������������У�����״̬ΪRUNNING
			return ;
		}	
	}
	
	/**
	 * �鿴ĳ������ĵ�ǰ״̬
	 * @param name �����
	 * @param time1 ����ĳ���ʱ��
	 * @param time2 ����ĵ�ǰʱ��
	 * @return �����״̬
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
	 * �鿴ʹ��ĳ���ɻ������мƻ���
	 * @param time1 ��ǰʱ��
	 * @param planeNumber �ɻ����
	 */
	public void getResourcePlanningEntry(String planeNumber , Calendar time1) {
		this.time = time1 ;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		List<FlightEntry> f =new ArrayList<FlightEntry>();//ʹ�ø÷ɻ������к���
		for(FlightEntry s : flightEntry) {
			if( s.getResource().getPlaneNumber().equals(planeNumber) ) {
				changeState(s);//���ݵ�ǰʱ���趨ʹ�ø÷ɻ��ĺ���״̬
				f.add(s);
			}
		}
		Collections.sort(f, new FlightCompare1());//��ʱ������
		int size = f.size() ;
		String[] columNames = { "���" , "ʱ��" , "����" ,"�г�" , "״̬"} ;//����һ����Ϣ
		Object[][] rowData = new String[f.size()][] ;
		for(int i = 0 ; i < size ; i ++) {
			rowData[i] = new String[] {String.valueOf(i+1),f.get(i).getBeginEndTime().getDate1() + "  ->  " + f.get(i).getBeginEndTime().getDate2()
					,f.get(i).getName() , f.get(i).getStartLocation().getName() + "-" +	f.get(i).getEndLocation().getName()  , f.get(i).getState().toString() 
					
			};
		}
		JFrame jf = new JFrame(sdf.format(time1.getTime())  + "(��ǰʱ��)" + "ʹ�÷ɻ�" + planeNumber + "���к���");
		Show.show(columNames, rowData, jf);//���ӻ�
		
	}
	
	/**
	 * ���ӿ��õķɻ�
	 * @param planeNumber �ɻ��ı��
	 * @param machineNumber �ɻ��Ļ��ͺ�
	 * @param size �˿�����
	 * @param age �ɻ�����
	 * @return true:���ӳɹ� ,false :����ʧ��
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
	 * ���ӿ��õ�λ��
	 * @param loc λ��
	 * @return true:���ӳɹ� ,false :����ʧ��
	 */
	public boolean addLocation(Location loc) {
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
	 * @throws deleteLocationException ɾ��λ�õ�ʱ������δ�����ļƻ�����ڸ�λ��ִ�У�
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
	 * ɾ�����õ���Դ
	 * @param teacher ��Ҫɾ���ķɻ�
	 * @return true :ɾ���ɹ��� false:ɾ��ʧ��
	 * @throws deleteResourceException ɾ����Դ��ʱ���������δ�����ļƻ�������ռ�ø���Դ
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
	 * �г����õķɻ�����Ϣ
	 */
	public void showResource() {
		String[] columNames = { "���" , "���õķɻ�����Ϣ����"} ;//����һ����Ϣ
		Object[][] rowData = new String[resource.size()][] ;
		for(int i = 0 ; i<resource.size() ; i++	) {
			rowData[i] = new String[] {String.valueOf(i+1) , resource.get(i).toString()};
		}
		JFrame jf =new JFrame("���п��õķɻ�����Ϣ");
		Show.show(columNames, rowData, jf);
	}
	/**
	 * �г���ĳ��λ����ص����к���
	 * @param loc λ��
	 * @param time1 ��ǰʱ��
	 */
	public void show(Location loc, Calendar time1 ) {
		FlightScheduleBoard board = new FlightScheduleBoard(loc, flightEntry, time1) ;
		board.show();
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
	 * �ж��Ǻ���flight���Ѿ���ȡ�ĺ��༯�Ƿ����������ϵ���� ���߾�����ȫ��ͬ�ĺ���
	 * @param flight  
	 * @throws Exception 
	 */
	public void PanDuan(FlightEntry flight) throws Exception {
		String s = flight.getName().substring(0,2);//�����ǰ�����ַ�
		int x = Integer.valueOf(flight.getName().substring(2));//�����ĸ�����
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm"); //ʱ����ʽ���ַ���
		String s1 = sdf.format(flight.getTime1().getTime());//����ʱ��
		String s2 = sdf.format(flight.getTime2().getTime());//��ֹʱ��
		String t = new String() ;
		try {
			for(int i = 0 ; i < flightEntry.size() ; i++) {
				String ss = flightEntry.get(i).getName().substring(0,2);//�����ǰ�����ַ�
				int xx = Integer.valueOf(flightEntry.get(i).getName().substring(2));//�����ĸ�����
				if(ss.equals(s) && xx == x) {//�������ͬ
					String s11 = sdf.format(flightEntry.get(i).getTime1().getTime());//����ʱ��
					String s22 = sdf.format(flightEntry.get(i).getTime2().getTime());//��ֹʱ��
					if(!s11.equals(s1)) {
						t = "���ࣺ"+ flight.getName() + "�ĳ���ʱ�䲻һ��!" ;
						throw new wrongDependenceException();
					}
					if(!s22.equals(s2)) {
						t = "���ࣺ"+ flight.getName() + "�ĵ���ʱ�䲻һ��!" ;
						throw new wrongDependenceException();
					}
					if(!flight.getStartLocation().equals(flightEntry.get(i).getStartLocation())) {
						t =  "���ࣺ"+ flight.getName() + "�ĳ���������һ��!" ;
						throw new wrongDependenceException(t);
					}
					if(!flight.getEndLocation().equals(flightEntry.get(i).getEndLocation())) {
						t = "���ࣺ"+ flight.getName() + "�ĵ��������һ��!";
						throw new wrongDependenceException();
					}
					if(flightEntry.get(i).getResource().equals(flight.getResource()) ) {
						if(flightEntry.get(i).getBeginEndTime().equals(flight.getBeginEndTime())) {
							t = "�����ļ����ڱ�ǩ��ȫһ���ĺ���:"  + flight.getName()  ;
							throw new sameLabelException();
						}
					}

				}else {
					if(flight.getResource().getPlaneNumber().equals(flightEntry.get(i).getResource().getPlaneNumber())) {
						if(!flight.getResource().equals(flightEntry.get(i).getResource())){
							t = "����" + flight.getName() + " �ͺ��� " + flightEntry.get(i).getName() + " ʹ����ͬ�ķɻ������Ƿɻ������͡���λ�������ȴ��һ��";
							throw new wrongDependenceException();
						}
					}
				}

			}
		}catch(wrongDependenceException e ) {
			throw new fileChooseException( "FileChooseException:�ļ����Ϸ�" +t +  " , ������ѡ���ļ�");
		}catch(sameLabelException e) {
			throw new fileChooseException( "FileChooseException:�ļ����Ϸ�" +t +  " , ������ѡ���ļ�");
		}
		
	}
	/**
	 * ��������ʽ�����ƻ������Ϣ
	 * @param s  ������ʽ
	 * @return  һ������
	 * @throws Exception 
	 */
	public FlightEntry getFileFlightEntry(String s ) throws Exception {
		assert s != null ;
		IsillegalRegular(s);//�ж��Ƿ���ڸ�ʽ����
		Pattern pattern1 =Pattern.compile("Flight:([\\d]{4}-[\\d]{2}-[\\d]{2}),([A-Z]{2}[\\d]{2,4})\n\\{\nDepartureAirport:(.*?)\nArrivalAirport:(.*?)\nDepatureTime:([0-9]{4}-[0-9]{2}-[0-9]{2}\\s[0-9]{2}:[0-9]{2})\nArrivalTime:([0-9]{4}-[0-9]{2}-[0-9]{2}\\s[0-9]{2}:[0-9]{2})\nPlane:([B || N][0-9]{4})\n\\{\nType:([0-9a-zA-Z]{4})\nSeats:([1-9][\\d]{1,2})\nAge:(.*?)\n\\}\\n\\}\n");
		Matcher matcher = pattern1.matcher(s);
		if(!matcher.find()) {
			throw new Exception("�ļ��Ƿ�!");
		}
		String planningEntryTime = matcher.group(1) ;//����ʱ��
		String planningEntryNumber = matcher.group(2) ;//�����
		String departureAirport = matcher.group(3);//��������
		String arrivalAirport = matcher.group(4);//�ִ����
		String departureTime = matcher.group(5);//����ʱ��
		String arrivalTime = matcher.group(6);//�ִ�ʱ��
		String number = matcher.group(7);
	    String strType = matcher.group(8);
		FlightEntry t = new FlightEntry(planningEntryNumber);//����
		Location loc1 = new Location(departureAirport);
		Location loc2 = new Location(arrivalAirport);
		if(!location.contains(loc1))
			this.location.add(loc1);//���ӿ��õ�λ��
		if(!location.contains(loc2))
			this.location.add(loc2);//���ӵĿ���λ��
		t.setLocations(loc1, loc2);//����ĳ�ʼλ�ú���ֹλ��
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		Calendar time1 = Calendar.getInstance() ;
		time1.setTime(sdf.parse(departureTime));//�������ʱ��
		Calendar time2 = Calendar.getInstance() ;
		time2.setTime(sdf.parse(arrivalTime));//���ൽ��ʱ��
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");//�ж��Ƿ���ͬһ������
		try {
			if(!(sdf1.format(time1.getTime()).equals(planningEntryTime))){
				throw new wrongDependenceException();
			}
		}catch(wrongDependenceException e ) {
			throw new fileChooseException( "FileChooseException:�ļ���ʽ���Ϸ�" +"����:" + planningEntryNumber +"��ʱ��ͳ���ʱ�䲻��ͬһ������"+  " , ������ѡ���ļ�");
		}
		try {
			if(!time1.before(time2)) {
				throw new wrongDependenceException();
			}
		}catch(wrongDependenceException e) {
			throw new fileChooseException( "FileChooseException:�ļ���ʽ���Ϸ�" +"����:" + planningEntryNumber +"����ʱ�����ڵ���ʱ��"+  " , ������ѡ���ļ�");
		}
		try {
			if((time2.getTime().getTime() - time1.getTime().getTime()) > 3600*24*1000) {
				throw new wrongDependenceException();
			}
		}catch(wrongDependenceException e ) {
			throw new fileChooseException( "FileChooseException:�ļ���ʽ���Ϸ�" +"����:" + planningEntryNumber +"��ʱ��ͳ���ʱ��������һ��" +  " , ������ѡ���ļ�");
		}
		
		t.setTime(new Timeslot(time1, time2));//���ú����ʱ���
		Plane plane1 = new Plane(number, strType, Integer.valueOf(matcher.group(9)), Double.valueOf(matcher.group(10)));//�ɻ�
		if(!resource.contains(plane1))
			this.resource.add(plane1);//������Դ
		t.setResource(plane1);
		PanDuan(t);//������еĺ���t����ֹʱ�����ֹλ���Ƿ���ͬ
		return t ;
	}
	
	/**
	 * ���ļ���ȡ���ݽ���һϵ�мƻ���
	 * @param file ��ȡ���ļ�
	 * @throws Exception  �ļ���ȡ����
	 */
	public void ReadFileCreatePlanningEntry(String file	) throws Exception {
		assert file != null ;
		BufferedReader reader = new BufferedReader(new FileReader(new File(file)));//��ȡ�ļ�
		String line1 = "" ;
		int l = 0 ;
		StringBuffer s = new StringBuffer("") ;
		while((line1 = reader.readLine()) != null) {
			if(line1.equals("")) {
				cloum ++ ;
				continue ;//��������
			}
			s.append(line1 + "\n") ;
			l++;
			if(l % 13 == 0 ) {//�Ѿ�����������һ���������Ϣ
				this.flightEntry.add(getFileFlightEntry(s.toString()));
				s = new StringBuffer("");//���¶���13�е���Ϣ
			}

		}
		reader.close();//�ر��ļ�
	}
	
	/**
	 * �ж�һ����ʾһ�������13����Ϣ�Ƿ���ڸ�ʽ����
	 * @param ff 13����Ϣ���ַ���
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
				if(Pattern.matches("[\\d]{4}-[\\d]{2}-[\\d]{2}", matcher.group(1))) {//�жϺ����ʱ���ʽ�Ƿ����Ҫ��
					String s1 = matcher.group(2);
					if(s1.length() < 4 || s1.length() > 6) {
						t = "�ļ���" +cloum +  "�����벻�Ϸ��������:" + s1 + "�ĳ��Ȳ���[4,6]";
						throw new illegalRegularException(t);//����ų����Ƿ���[3,6]֮��
					}else {
						if(!Character.isUpperCase(s1.charAt(0)) || !Character.isUpperCase(s1.charAt(1))) {
							t = "�ļ���" +cloum +  "�����벻�Ϸ��������:" + s1 + "��ǰ��λ���Ǵ�д��ĸ";
							throw new illegalRegularException(t);//�����ǰ��λ�Ƿ��Ǵ�д��ĸ
						}
						for(int i = 2 ; i <s1.length() ;i++) {
							if(!Character.isDigit(s1.charAt(i))) {
								t = "�ļ���" +cloum +  "�����벻�Ϸ��������:" + s1 + "�ĺ�" + (s1.length() -2) + "λ��������" ;
								throw new illegalRegularException(t);//����ź���λ�Ƿ�������
							}
						}
					}
				}else {
					t = "�ļ���" +cloum +  "�����벻�Ϸ�������" + matcher.group(2)+"������: " + matcher.group(1) + "������ yyyy-MM-dd ��ʽ" ;
					throw new illegalRegularException(t);
				}
			
			}else {
				t = "�ļ���" +cloum +  "�����벻�Ϸ�����һ�е���Ϣ������Flight:xx,xx��ʽ" ;
				throw new illegalRegularException(t);
			}
			cloum ++ ;
			if(!(matcher = pattern2.matcher(ff)).find()) {
					t = "�ļ���" +cloum +  "�����벻�Ϸ��������13�����ݵĵڶ��в���'{'" ;
					throw new illegalRegularException(t);
			}
			cloum ++ ;
			if(!(matcher = pattern3.matcher(ff)).find()) {
				t = "�ļ���" +cloum +  "�����벻�Ϸ��������13�����ݵĵ��������ݲ�����Ҫ��" ;
				throw new illegalRegularException(t);
			}
			cloum ++ ;
			if(!(matcher = pattern4.matcher(ff)).find()) {
				t =  "�ļ���" +cloum +  "�����벻�Ϸ��������13�����ݵĵ��������ݲ�����Ҫ��" ;
				throw new illegalRegularException(t);
			}
			cloum ++ ;
			if(!(matcher = pattern5.matcher(ff)).find()) {
				t = "�ļ���" +cloum +  "�����벻�Ϸ�������ĳ���ʱ�䲻���ϸ�ʽҪ��";
				throw new illegalRegularException(t);
			}
			cloum ++ ;
			if(!(matcher = pattern6.matcher(ff)).find()) {
				 t = "�ļ���" +cloum +  "�����벻�Ϸ�������ĵִ�ʱ�䲻���ϸ�ʽҪ��";
				throw new illegalRegularException(t);
			}
			cloum ++ ;
			if((matcher = pattern7.matcher(ff)).find()) {
				String s1 = matcher.group(7) ;

				if(s1.length() != 5) {
					t  = "�ļ���" +cloum +  "�����벻�Ϸ����ɻ���ų��Ȳ�Ϊ5" ;
					throw new illegalRegularException(t);//�ɻ���ų����Ƿ�Ϊ5
				}else {
					if( !((s1.charAt(0) == 'N') || (s1.charAt(0) == 'B'))) {
						t = "�ļ���" +cloum +  "�����벻�Ϸ����ɻ���һλ��ĸ����N����B" ;
						throw new illegalRegularException(t);//�ɻ���һλ��ĸ�ǲ���N����B
					}
					for(int i = 1 ; i <s1.length() ;i++) {
						if(!Character.isDigit(s1.charAt(i))) {
							t = "�ļ���" +cloum +  "�����벻�Ϸ����ɻ��ĺ�λ��������" ;
							throw new illegalRegularException(t);//�ɻ�����λ�Ƿ�������
						}
					}
				}
			}else {
				t = "�ļ���" +cloum +  "�����벻�Ϸ��������13�����ݵĵ��������ݲ�����Ҫ��" ;
				throw new illegalRegularException(t);
			}
			cloum ++ ;
			if(!(matcher = pattern13.matcher(ff)).find()) {
				t = "�ļ���" +cloum +  "�����벻�Ϸ��������13�����ݵĵ�13�����ݲ�����Ҫ��" ;
				throw new illegalRegularException(t);
			}
			cloum ++ ;
			if(!(matcher = pattern8.matcher(ff)).find()) {
				t = "�ļ���" +cloum +  "�����벻�Ϸ����ɻ��Ļ��Ͳ����ϸ�ʽҪ��" ;
				throw new illegalRegularException(t);
			}
			cloum ++ ;
			if(!(matcher = pattern9.matcher(ff)).find()) {
				t = "�ļ���" +cloum +  "�����벻�Ϸ����ɻ�����λ������һ������" ;
				throw new illegalRegularException(t);
			}else {
				String s1 = matcher.group(9);
				int x = 0 ;
				 x = Integer.valueOf(s1);
				if(x < 50 || x > 600) {
					t = "�ļ���" +cloum +  "�����벻�Ϸ����ɻ�����λ������[50,600]";
					throw new illegalRegularException(t);
				}
			}
			cloum ++ ;
			if(!(matcher = pattern10.matcher(ff)).find()) {
				t = "�ļ���" +cloum +  "�����벻�Ϸ����ɻ��Ļ��䲻���ϸ�ʽҪ��" ;
				throw new illegalRegularException(t);
			}else {
				double x = 0  ;
				try {
					x = Double.valueOf(matcher.group(10)) ;
				}catch(NumberFormatException e) {
					t= "�ļ���" +cloum +  "�����벻�Ϸ����ɻ��Ļ��䲻��һ��������";
					throw new illegalRegularException(t);
				}
				 

					if(x < 0  || x > 30) {
						t = "�ļ���" +cloum +  "�����벻�Ϸ����ɻ��Ļ��䲻��[0,30]" ;
						throw new illegalRegularException(t);
					}
					for(int i = 0 ; i < matcher.group(10).length() ; i ++ ) {
						if( matcher.group(10).charAt(i) == '.') {
							if(i != matcher.group(10).length() -2) {
								t = "�ļ���" +cloum +  "�����벻�Ϸ����ɻ���С��λ������1";
								throw new illegalRegularException(t);
							}
						}
					}
			}
			cloum ++ ;
			if(!(matcher = pattern11.matcher(ff)).find()) {
				t = "�ļ���" +cloum +  "�����벻�Ϸ��������13�����ݵĵ�11�����ݲ�����Ҫ��";
				throw new illegalRegularException(t);
			}
			cloum ++ ;
			if(!(matcher = pattern12.matcher(ff)).find()) {
				t = "�ļ���" +cloum +  "�����벻�Ϸ��������13�����ݵĵ�12�����ݲ�����Ҫ��";
				throw new illegalRegularException(t);
			}
			
		}catch(illegalRegularException e) {
			throw new fileChooseException( "FileChooseException:�ļ���ʽ���Ϸ� : " +t +  " , ������ѡ���ļ�");
		}
	}
	/**
	 * �г���ĳ��λ����ص����к��ಢ���ӻ�
	 * @param loc ĳ��λ��
	 * @param time ��ǰʱ��
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
	 * չʾĳ��λ�õ���Ϣ��
	 * @param loc ��ǰλ��
	 * @param time1 ��ǰʱ��
	 * @param x 0 :�ִﺽ�� �� 1 :��������
	 */
	public void board(Location loc ,Calendar time1 , int x) {
		time= time1 ;
		FlightScheduleBoard Board = new FlightScheduleBoard(loc, flightEntry, time1) ;
		Board.visualize(x);
	}
	/**
	 * ���ƻ����Ƿ������Դ��ͻ
	 * @return true : �����ڳ�ͻ��false: ���ڳ��ͻ
	 */
	public boolean check() {
		List<PlanningEntry> flightt = new ArrayList<>() ;
		flightt.addAll(flightEntry);
		if(!APIs.PlanningEntryAPIs.checkResourceExclusiveConflict(flightt,new checkResourceExclusiveConflict1())) {//��Դ����λ�ô��ڳ�ͻ
			return false ;
		}
		return true ;
	}

	/**
	 * �ҳ�ʹ����ͬ�ɻ���ĳ�������ǰ��ƻ���
	 * @param time1 ������ʼʱ��
	 * @param time2 ������ֹʱ��
	 * @param Name ��������
	 * @param planNumber �ɻ����
	 */
	public boolean getPrePlanningEntry(Calendar time1 , Calendar time2 , String Name , String planNumber) {
		Plane plane = null ;
		for(int i = 0 ; i < resource.size() ; i++ ) {
			if(resource.get(i).getPlaneNumber().equals(planNumber)) {
				plane = resource.get(i);//�ҵ��ɻ�
			}
		}
		FlightEntry ss = null ;
		for(int j = 0 ; j < flightEntry.size() ; j++) {
			if(flightEntry.get(j).getName().equals(Name)) {
				if(flightEntry.get(j).getTime1().equals(time1)) {
					if(flightEntry.get(j).getTime2().equals(time2)) {
						ss = flightEntry.get(j);//�ҵ�����
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
			String[] columNames = {  "ʱ��" , "����" ,"�г�" , "״̬"} ;//����һ����Ϣ
			Object[][] rowData = new String[1][] ;
			Location loc1 = tt.getTimeLocation().get(tt.getBeginEndTime()).get(0);
			Location loc2 = tt.getTimeLocation().get(tt.getBeginEndTime()).get(1);
			rowData[0] = new String[] {tt.getBeginEndTime().getDate1() + "->" + tt.getBeginEndTime().getDate2() , tt.getName() , loc1.getName() + " - " + loc2.getName() , tt.getState().toString()};
			JFrame jf =new JFrame("����:" + planNumber +"��ǰ��ƻ���");
			Show.show(columNames, rowData, jf);
		}	
		return true ;
	}
	

}
