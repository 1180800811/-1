package applications;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import EntryState.BlockableEntryImpl;
import EntryState.RUNNING;
import EntryState.WAITTING;
import Location.Location;
import Location.MultipleLocationEntryImpl;
import MyException.cancelPlanningEntryException;
import MyException.deleteLocationException;
import MyException.deleteResourceException;
import MyException.feipeiResourceException;
import PlanningEntry.PlanningEntry;
import PlanningEntry.TrainEntry;
import Resource.MultipleSortedResourceEntryImpl;
import Resource.Railway;
import Resource.Type;
import Timeslot.Timeslot;

public class TrainScheduleTest {
	/*
	 * Testing for addPlanningEntry����
	 * Partition : there is conflict after adding a PlanningEntry , there is not conflict after adding a PlanningEntry 
	 * 
	 * Testing for cancelPlaningEntry����
	 * Partition : the state of the PlanningEntry is ALOCATED, WAITING,CANCELLED,ENDED,RUNNING
	 * 
	 * Testing for FeiPeiResource
	 * output:0 : ��Ҫ����ĳ��᲻�ڿ��ó����� , 1 : ��Ҫ������Դ�ļƻ����Ѿ�������Դ 2 :����ɹ� , 3 :������Դ���ڳ�ͻ , 4:��Ҫ���䳵��ĸ���������
	 *
	 *	Testing for BeginPlanningEntry����
	 *	Partition : the state of the PlanningEntry is ALOCATED, WAITING,CANCELLED,ENDED,RUNNING
	 *
	 * Testing for EndPlanningEntry����
	 *  Partition��the state of the PlanningEntry is ALOCATED, WAITING,CANCELLED,ENDED,RUNNING
	 *  
	 *	Testing for addLocation����
	 *	Partition : ��ӵ�λ�ò����ڣ���ӵ�λ���Ѿ�����
	 *
	 *	Testing for deleteLocation����
	 *	Partition : ��ɾ����λ�ò����ڣ���ɾ����λ���Ѿ�����
	 *
	 *	Testing for addResource����
	 *	Partition : ��ӵ���Դ�����ڣ���ӵ���Դ�Ѿ�����
	 *
	 *	Testing for deleteResource����
	 *	Partition : ��ɾ������Դ�����ڣ���ɾ������Դ�Ѿ�����
	 *
	 * Testing for check ����
	 * Partition:there is conflict between PlanningEntrys , there is not conflict between PlanningEntrys , 
	 * 
	 * Testing for getPrePlanningEntry ����
	 * Partition: there is PrePlanningEntry , there is not PrePlanningEntry
	 */
	
	/*
	 * ����addPlanningEntry����
	 * ���ǣ�there is conflict after adding a PlanningEntry , there is not conflict after adding a PlanningEntry 
	 */
	@Test
	public void addPlanningEntryTest() throws ParseException  {
		Location loc1 = new Location(1.1, 1.2, "��������", true);
		Location loc2 = new Location(1.21, 1.23, "�Ͼ�", true);
		Location loc3 = new Location(2.5, 2.3, "����", true);
		List<Location> ss = new ArrayList<Location>() ;
		ss.add(loc1);
		ss.add(loc2);
		ss.add(loc3);
		Timeslot s1 = new Timeslot() ; 
		Timeslot s2 = new Timeslot() ;
		s1.setDate1("2020-03-21 10:20");
		s1.setDate2("2020-03-21 17:20");
		s2.setDate1("2020-03-22 10:20");
		s2.setDate2("2020-03-22 17:20");
		List<Timeslot> time = new ArrayList<Timeslot>() ;
		time.add(s1);
		time.add(s2);	
		String name = "tt" ;
		TrainSchedule t = new TrainSchedule() ;
		assertTrue(t.addPlanningEntry(ss, time, name));
	}
	
	/*
	 * 	���� cancelPlaningEntry����
	 * ���ǣ�the state of the PlanningEntry is ALOCATED, WAITING,CANCELLED,ENDED,RUNNING
	 */
	@Test
	public void cancelPlaningEntryTest() throws ParseException, cancelPlanningEntryException {
		Location loc1 = new Location(1.1, 1.2, "��������", true);
		Location loc2 = new Location(1.21, 1.23, "�Ͼ�", true);
		Location loc3 = new Location(2.5, 2.3, "����", true);
		List<Location> ss = new ArrayList<Location>() ;
		ss.add(loc1);
		ss.add(loc2);
		ss.add(loc3);
		Timeslot s1 = new Timeslot() ; 
		Timeslot s2 = new Timeslot() ;
		s1.setDate1("2020-03-21 10:20");
		s1.setDate2("2020-03-21 17:20");
		s2.setDate1("2020-03-22 10:20");
		s2.setDate2("2020-03-22 17:20");
		List<Timeslot> time = new ArrayList<Timeslot>() ;
		time.add(s1);
		time.add(s2);	
		String name = "tt" ;
		TrainSchedule t = new TrainSchedule() ;
		t.addPlanningEntry(ss, time, name);
		assertTrue(t.cancelPlanningEntry(name, s1.getdate1()).equals("�����Ѿ�ȡ��"));
		try {
			t.cancelPlanningEntry(name, s1.getdate1()).equals("������ǰ״̬ΪCANCELLED,����ȡ��");
		}catch( cancelPlanningEntryException e) {
			assertEquals("ȡ���ƻ����쳣: �ƻ���tt��ǰ��״̬������ȡ��" ,e.getMessage());
		}

	}
	
	/*
	 * ����FeiPeiResource����
	 * ����:0 : ��Ҫ����ķɻ����ڿ��÷ɻ��� , 1 : ��Ҫ������Դ�ļƻ����Ѿ�������Դ 2 :����ɹ� , 3 :������Դ���ڳ�ͻ , 4:��Ҫ���䳵��ĸ���������
	 */
	@Test
	public void FeiPeiResourceTest() throws Exception {
		Location loc1 = new Location(1.1, 1.2, "��������", true);
		Location loc2 = new Location(1.21, 1.23, "�Ͼ�", true);
		Location loc3 = new Location(2.5, 2.3, "����", true);
		List<Location> ss = new ArrayList<Location>() ;
		ss.add(loc1);
		ss.add(loc2);
		ss.add(loc3);
		Timeslot s1 = new Timeslot() ; 
		Timeslot s2 = new Timeslot() ;
		s1.setDate1("2020-03-21 10:20");
		s1.setDate2("2020-03-21 17:20");
		s2.setDate1("2020-03-22 10:20");
		s2.setDate2("2020-03-22 17:20");
		List<Timeslot> time = new ArrayList<Timeslot>() ;
		time.add(s1);
		time.add(s2);	
		String name = "tt" ;
		TrainSchedule t = new TrainSchedule() ;
		t.addPlanningEntry(ss, time, name);
		Railway r1 = new Railway("aa", Type.BUSINESS, 30, 2019);
		Railway r2 = new Railway("bb", Type.BUSINESS, 30, 2019);
		List<Railway> msre1 = new ArrayList<Railway>() ;
		msre1.add(r1);
		msre1.add(r2);
		assertTrue(t.FeiPeiResource(name, s1.getdate1(), r1) == 0 ) ;//����:��Ҫ����ĳ��᲻�ڿ��ó�����
		t.addResource("aa", "����", 30, 2019);
		t.addResource("bb","����", 30, 2019);
		assertTrue(t.FeiPeiResource("sss", s1.getdate1(), r1) == 4)  ;//����:��Ҫ���䳵��ĸ���������
		assertTrue(t.FeiPeiResource(name, s1.getdate1(), r1)==2)   ;//����ɹ�
		assertTrue(t.FeiPeiResource(name, s1.getdate1(), r1)==1)  ;//����:��Ҫ������Դ�ļƻ����Ѿ������˸���Դ
		
	}
	
	/*
	 * ���� WatchState����
	 * 
	 */
	@Test
	public void WatchStateTest() throws ParseException {
		Location loc1 = new Location(1.1, 1.2, "��������", true);
		Location loc2 = new Location(1.21, 1.23, "�Ͼ�", true);
		Location loc3 = new Location(2.5, 2.3, "����", true);
		List<Location> ss = new ArrayList<Location>() ;
		ss.add(loc1);
		ss.add(loc2);
		ss.add(loc3);
		Timeslot s1 = new Timeslot() ; 
		Timeslot s2 = new Timeslot() ;
		s1.setDate1("2020-03-21 10:20");
		s1.setDate2("2020-03-21 17:20");
		s2.setDate1("2020-03-22 10:20");
		s2.setDate2("2020-03-22 17:20");
		List<Timeslot> time = new ArrayList<Timeslot>() ;
		time.add(s1);
		time.add(s2);	
		String name = "tt" ;
		TrainSchedule t = new TrainSchedule() ;
		t.addPlanningEntry(ss, time, name);
		assertTrue(t.WatchState(name, s1.getdate1(), s1.getdate1()) instanceof RUNNING );
	}
	
	/*
	 * ����BeginPlanningEntry����
	 * ���ǣ�the state of the PlanningEntry is ALOCATED, WAITING,CANCELLED,ENDED,RUNNING
	 */
	@Test
	public void BeginPlanningEntryTest() throws feipeiResourceException, ParseException {
		Location loc1 = new Location(1.1, 1.2, "��������", true);
		Location loc2 = new Location(1.21, 1.23, "�Ͼ�", true);
		Location loc3 = new Location(2.5, 2.3, "����", true);
		List<Location> ss = new ArrayList<Location>() ;
		ss.add(loc1);
		ss.add(loc2);
		ss.add(loc3);
		Timeslot s1 = new Timeslot() ; 
		Timeslot s2 = new Timeslot() ;
		s1.setDate1("2020-03-21 10:20");
		s1.setDate2("2020-03-21 17:20");
		s2.setDate1("2020-03-22 10:20");
		s2.setDate2("2020-03-22 17:20");
		List<Timeslot> time = new ArrayList<Timeslot>() ;
		time.add(s1);
		time.add(s2);	
		String name = "tt" ;
		TrainSchedule t = new TrainSchedule() ;
		assertTrue(t.BeginPlanningEntry("s", s1.getdate1()).equals("ָ���ĸ���������"));
		t.addPlanningEntry(ss, time, name);
		assertTrue(t.BeginPlanningEntry(name, s1.getdate1()).equals("������ǰ״̬ΪWAITTING,��������"));
		Railway r1 = new Railway("aa", Type.BUSINESS, 30, 2019);
		Railway r2 = new Railway("bb", Type.BUSINESS, 30, 2019);

		t.addResource("aa", "����", 30, 2019);
		t.addResource("bb", "����", 30, 2019);
		t.FeiPeiResource(name, s1.getdate1(), r1);
		t.FeiPeiResource(name, s1.getdate1(), r2);
		assertTrue(t.BeginPlanningEntry(name, s1.getdate1()).equals("�����Ѿ�����"));
		t.board(new Location("������"), s1.getdate1(), 0);
		t.show(new Location("������"), s1.getdate1());
	}
	
	
	/*
	 * ����EndPlanningEntry����
	 * ���ǣ�the state of the PlanningEntry is ALOCATED, WAITING,CANCELLED,ENDED,RUNNING
	 */
	@Test
	public void EndPlanningEntryTest() throws Exception {
		Location loc1 = new Location(1.1, 1.2, "��������", true);
		Location loc2 = new Location(1.21, 1.23, "�Ͼ�", true);
		Location loc3 = new Location(2.5, 2.3, "����", true);
		List<Location> ss = new ArrayList<Location>() ;
		ss.add(loc1);
		ss.add(loc2);
		ss.add(loc3);
		Timeslot s1 = new Timeslot() ; 
		Timeslot s2 = new Timeslot() ;
		s1.setDate1("2020-03-21 10:20");
		s1.setDate2("2020-03-21 17:20");
		s2.setDate1("2020-03-22 10:20");
		s2.setDate2("2020-03-22 17:20");
		List<Timeslot> time = new ArrayList<Timeslot>() ;
		time.add(s1);
		time.add(s2);	
		String name = "tt" ;
		TrainSchedule t = new TrainSchedule() ;
		assertTrue(t.EndPlanningEntry("s", s1.getdate1()).equals("ָ���ĸ���������"));
		t.addPlanningEntry(ss, time, name);
		assertTrue(t.EndPlanningEntry(name, s1.getdate1()).equals("������ǰ״̬ΪWAITTING,���ܽ���"));
		Railway r1 = new Railway("aa", Type.BUSINESS, 30, 2019);
		Railway r2 = new Railway("bb", Type.BUSINESS, 30, 2019);
		List<Railway> msre1 = new ArrayList<Railway>() ;
		msre1.add(r1);
		msre1.add(r2);
		t.addResource("aa", "����", 30, 2019);
		t.addResource("bb", "����", 30, 2019);
		t.FeiPeiResource(name, s1.getdate1(), r1);
		t.FeiPeiResource(name, s1.getdate1(), r2);
		assertTrue(t.BeginPlanningEntry(name, s1.getdate1()).equals("�����Ѿ�����"));
		assertTrue(t.EndPlanningEntry(name, s1.getdate1()).equals("�����Ѿ�����"));
	}
	
	/*
	 * ����BlockPlanningEntry����
	 */
	@Test
	public void BlockPlanningEntryTest() throws Exception {
		Location loc1 = new Location(1.1, 1.2, "��������", true);
		Location loc2 = new Location(1.21, 1.23, "�Ͼ�", true);
		Location loc3 = new Location(2.5, 2.3, "����", true);
		List<Location> ss = new ArrayList<Location>() ;
		ss.add(loc1);
		ss.add(loc2);
		ss.add(loc3);
		Timeslot s1 = new Timeslot() ; 
		Timeslot s2 = new Timeslot() ;
		s1.setDate1("2020-03-21 10:20");
		s1.setDate2("2020-03-21 17:20");
		s2.setDate1("2020-03-22 10:20");
		s2.setDate2("2020-03-22 17:20");
		List<Timeslot> time = new ArrayList<Timeslot>() ;
		time.add(s1);
		time.add(s2);	
		String name = "tt" ;
		TrainSchedule t = new TrainSchedule() ;
		assertTrue(t.BlockPlanningEntry("s", s1.getdate1()).equals("ָ���ĸ���������"));
		t.addPlanningEntry(ss, time, name);
		assertTrue(t.BlockPlanningEntry(name, s1.getdate1()).equals("������ǰ״̬ΪWAITTING,��������"));
		Railway r1 = new Railway("aa", Type.BUSINESS, 30, 2019);
		Railway r2 = new Railway("bb", Type.BUSINESS, 30, 2019);
		List<Railway> msre1 = new ArrayList<Railway>() ;
		msre1.add(r1);
		msre1.add(r2);
		t.addResource("aa", "����", 30, 2019);
		t.addResource("bb", "����", 30, 2019);
		t.FeiPeiResource(name, s1.getdate1(), r1);
		t.FeiPeiResource(name, s1.getdate1(), r2);
		assertTrue(t.BeginPlanningEntry(name, s1.getdate1()).equals("�����Ѿ�����"));
		assertTrue(t.BlockPlanningEntry(name, s1.getdate1()).equals("�����Ѿ�����"));
	}
	/*
	 * ����addLocation����
	 */
	@Test
	public void addLocationTest() throws Exception {

		TrainSchedule c = new TrainSchedule() ;
		assertTrue(c.addLocation(new Location("ss")));//���λ�óɹ�
		assertFalse(c.addLocation(new Location("ss")));//��ӵ�λ���Ѿ�����
		c.showLocation();
	}
	
	/*
	 * 
	 * ����deletLocation����
	 */
	@Test
	public void deleteLocationTest() throws deleteLocationException, deleteResourceException, ParseException, feipeiResourceException {
		TrainSchedule c = new TrainSchedule() ;
		assertFalse(c.deleteLocation(new Location("ss")));//��ɾ����λ�ò�����
		c.addLocation(new Location("ss"));
		assertTrue(c.deleteLocation(new Location("ss")));//��ɾ����λ�ô���
		assertFalse(c.deleteResource("aa", "����", 30, 2019));//��ɾ������Դ������
		assertFalse(c.deleteResource("aa", "һ��", 30, 2019));//��ɾ������Դ������
		assertFalse(c.deleteResource("aa", "����", 30, 2019));//��ɾ������Դ������
		assertFalse(c.deleteResource("aa", "����", 30, 2019));//��ɾ������Դ������
		assertFalse(c.deleteResource("aa", "Ӳ��", 30, 2019));//��ɾ������Դ������
		assertFalse(c.deleteResource("aa", "Ӳ��", 30, 2019));//��ɾ������Դ������
		assertFalse(c.deleteResource("aa", "���", 30, 2019));//��ɾ������Դ������
		assertFalse(c.deleteResource("aa", "�ͳ�", 30, 2019));//��ɾ������Դ������
		c.addResource("aa", "����", 30, 2019);
		c.addResource("aa", "һ��", 30, 2019);
		c.addResource("aa", "����", 30, 2019);
		c.addResource("aa", "����", 30, 2019);
		c.addResource("aa", "Ӳ��", 30, 2019);
		c.addResource("aa", "���", 30, 2019);
		c.addResource("aa", "�ͳ�", 30, 2019);
		Timeslot s1 = new Timeslot() ; 
		s1.setDate1("2020-03-21 10:20");
		s1.setDate2("2020-03-21 17:20");
		assertTrue(c.deleteResource("aa", "һ��", 30, 2019));//��ɾ������Դ����]
		c.addplanningEntry("s", new Location("Nan"), new Location("ha"),s1.getdate1(), s1.getdate2());
		c.FeiPeiResource("s", s1.getdate1(), new Railway("aa", Type.BUSINESS, 30, 2019));
		c.BeginPlanningEntry("s", s1.getdate1());
		try {
			c.deleteLocation(new Location("Nan"));	
		}catch( deleteLocationException e ) {
			assertEquals("ɾ��λ���쳣: ������δ�����ļƻ�����λ��:Nanִ��" ,e.getMessage());
		}

	}
	
	/*
	 * ����addResource����
	 */
	@Test
	public void addResourceTest() throws Exception {

		TrainSchedule c = new TrainSchedule() ;
		assertTrue(c.addResource("aa", "����", 30, 2019));//�����Դ�ɹ�
		assertFalse(c.addResource("aa", "����", 30, 2019));//��ӵ���Դ�Ѿ�����
	}
	/*
	 * 
	 * ����deletResource����
	 */
	@Test
	public void deleteResourceTest() throws deleteResourceException, ParseException, feipeiResourceException {
		TrainSchedule c = new TrainSchedule() ;
		assertFalse(c.deleteResource("aa", "����", 30, 2019));//��ɾ������Դ������
		assertFalse(c.deleteResource("aa", "һ��", 30, 2019));//��ɾ������Դ������
		assertFalse(c.deleteResource("aa", "����", 30, 2019));//��ɾ������Դ������
		assertFalse(c.deleteResource("aa", "����", 30, 2019));//��ɾ������Դ������
		assertFalse(c.deleteResource("aa", "Ӳ��", 30, 2019));//��ɾ������Դ������
		assertFalse(c.deleteResource("aa", "Ӳ��", 30, 2019));//��ɾ������Դ������
		assertFalse(c.deleteResource("aa", "���", 30, 2019));//��ɾ������Դ������
		assertFalse(c.deleteResource("aa", "�ͳ�", 30, 2019));//��ɾ������Դ������
		c.addResource("aa", "����", 30, 2019);
		c.addResource("aa", "һ��", 30, 2019);
		c.addResource("aa", "����", 30, 2019);
		c.addResource("aa", "����", 30, 2019);
		c.addResource("aa", "Ӳ��", 30, 2019);
		c.addResource("aa", "���", 30, 2019);
		c.addResource("aa", "�ͳ�", 30, 2019);
		Timeslot s1 = new Timeslot() ; 
		s1.setDate1("2020-03-21 10:20");
		s1.setDate2("2020-03-21 17:20");
		assertTrue(c.deleteResource("aa", "һ��", 30, 2019));//��ɾ������Դ����]
		c.addplanningEntry("s", new Location("Nan"), new Location("ha"),s1.getdate1(), s1.getdate2());
		c.FeiPeiResource("s", s1.getdate1(), new Railway("aa", Type.BUSINESS, 30, 2019));
		c.BeginPlanningEntry("s", s1.getdate1());
		try {
			c.deleteResource("aa", "����", 30, 2019);
		}catch(deleteResourceException e) {
			assertEquals("ɾ����Դ�쳣: ����δ�����ļƻ�������ʹ�ø���Դ:Railway [Number=aa, type=BUSINESS, Size=30, Year=2019]",e.getMessage());
		}

	}
	
	/*
	 * ����check����
	 */
	@Test
	public void checkTest() throws Exception {
		Location loc1 = new Location(1.1, 1.2, "��������", true);
		Location loc2 = new Location(1.21, 1.23, "�Ͼ�", true);
		Location loc3 = new Location(2.5, 2.3, "����", true);
		List<Location> ss = new ArrayList<Location>() ;
		ss.add(loc1);
		ss.add(loc2);
		ss.add(loc3);
		Timeslot s1 = new Timeslot() ; 
		Timeslot s2 = new Timeslot() ;
		s1.setDate1("2020-03-21 10:20");
		s1.setDate2("2020-03-21 17:20");
		s2.setDate1("2020-03-22 10:20");
		s2.setDate2("2020-03-22 17:20");
		List<Timeslot> time = new ArrayList<Timeslot>() ;
		time.add(s1);
		time.add(s2);	
		String name = "tt" ;
		TrainSchedule t = new TrainSchedule() ;
		t.addPlanningEntry(ss, time, name);
		Railway r1 = new Railway("aa", Type.BUSINESS, 30, 2019);
		Railway r2 = new Railway("bb", Type.BUSINESS, 30, 2019);
		List<Railway> msre1 = new ArrayList<Railway>() ;
		msre1.add(r1);
		msre1.add(r2);
		t.addResource("aa", "����", 30, 2019);
		t.addResource("bb", "����", 30, 2019);
		t.FeiPeiResource(name, s1.getdate1(), r1);
		t.FeiPeiResource(name, s1.getdate1(), r2);
		assertTrue(t.check());
	}
	
	/*
	 * ����getPrePlanningEntry����
	 */
	@Test
	public void getPrePlanningEntryTest() throws Exception {
		
		Location loc1 = new Location(1.1, 1.2, "��������", true);
		Location loc2 = new Location(1.21, 1.23, "�Ͼ�", true);
		Location loc3 = new Location(2.5, 2.3, "����", true);
		List<Location> ss = new ArrayList<Location>() ;
		ss.add(loc1);
		ss.add(loc2);
		ss.add(loc3);
		Timeslot s1 = new Timeslot() ; 
		Timeslot s2 = new Timeslot() ;
		s1.setDate1("2020-02-21 10:20");
		s1.setDate2("2020-02-21 17:20");
		s2.setDate1("2020-02-22 10:20");
		s2.setDate2("2020-02-22 17:20");
		List<Timeslot> time = new ArrayList<Timeslot>() ;
		time.add(s1);
		time.add(s2);	
		String name = "tt" ;
		TrainSchedule t = new TrainSchedule() ;
		t.addPlanningEntry(ss, time, name);
		t.setTime(s1.getdate1());
		Railway r1 = new Railway("aa", Type.BUSINESS, 30, 2019);
		Railway r2 = new Railway("bb", Type.BUSINESS, 30, 2019);
		List<Railway> msre1 = new ArrayList<Railway>() ;
		msre1.add(r1);
		msre1.add(r2);
		t.addResource("aa", "����", 30, 2019);
		t.addResource("bb", "����", 30, 2019);
		t.FeiPeiResource(name, s1.getdate1(), r1);
		t.FeiPeiResource(name, s1.getdate1(), r2);
		


		
		Location loc11 = new Location(1.1, 1.2, "������", true);
		Location loc22 = new Location(1.21, 1.23, "�Ϻ�", true);	
		Location loc33 = new Location(1.21, 1.23, "�ϲ�", true);	
		List<Location> sss = new ArrayList<Location>() ;
		sss.add(loc11);
		sss.add(loc22);
		sss.add(loc33);
		
		Railway r11 = new Railway("aa", Type.BUSINESS, 30, 2019);
		Railway r22 = new Railway("cc", Type.BUSINESS, 30, 2019);
		
		List<Railway> msre11 = new ArrayList<Railway>() ;
		msre11.add(r11);
		msre11.add(r22);
		

		Timeslot s11 = new Timeslot() ; 
		Timeslot s22 = new Timeslot() ;
		s11.setDate1("2020-03-21 11:20");
		s11.setDate2("2020-03-21 17:20");
		s22.setDate1("2020-03-22 10:20");
		s22.setDate2("2020-03-22 16:20");
		List<Timeslot> time1 = new ArrayList<Timeslot>() ;
		time1.add(s11);
		time1.add(s22);
		String name1 = "sssss" ;
		t.addPlanningEntry(sss, time1, name1);
		t.addResource("aa", "����", 30, 2019);
		t.addResource("cc", "����", 30, 2019);
		t.FeiPeiResource(name1, s11.getdate1(), r11);
		t.FeiPeiResource(name1, s11.getdate1(), r22);
		assertTrue(t.getPrePlanningEntry("aa", "����", 30, 2019, name1, s11.getdate1()));
		assertFalse(t.getPrePlanningEntry("aa", "һ��", 30, 2019, name1, s11.getdate1()));
		assertFalse(t.getPrePlanningEntry("aa", "����", 30, 2019, name1, s11.getdate1()));
		assertFalse(t.getPrePlanningEntry("aa", "����", 30, 2019, name1, s11.getdate1()));
		assertFalse(t.getPrePlanningEntry("aa", "Ӳ��", 30, 2019, name1, s11.getdate1()));
		assertFalse(t.getPrePlanningEntry("aa", "Ӳ��", 30, 2019, name1, s11.getdate1()));
		assertFalse(t.getPrePlanningEntry("aa", "�ͳ�", 30, 2019, name1, s11.getdate1()));
		assertFalse(t.getPrePlanningEntry("aa", "���", 30, 2019, name1, s11.getdate1()));
		t.getResourcePlanningEntry("aa", "����", 30, 2019, s11.getdate1());
		t.getResourcePlanningEntry("aa", "һ��", 30, 2019, s11.getdate1());
		t.getResourcePlanningEntry("aa", "����", 30, 2019, s11.getdate1());
		t.getResourcePlanningEntry("aa", "����", 30, 2019, s11.getdate1());
		t.getResourcePlanningEntry("aa", "Ӳ��", 30, 2019, s11.getdate1());
		t.getResourcePlanningEntry("aa", "Ӳ��", 30, 2019, s11.getdate1());
		t.getResourcePlanningEntry("aa", "�ͳ�", 30, 2019, s11.getdate1());
		t.getResourcePlanningEntry("aa", "���", 30, 2019, s11.getdate1());

		
		
		
		t.showLocation();
		t.showResource();
	}
	@Test
	public void addplanningEntryTest() throws ParseException {
		Location loc1 = new Location(1.1, 1.2, "��������", true);
		Location loc2 = new Location(1.21, 1.23, "�Ͼ�", true);
		Timeslot s11 = new Timeslot() ; 
		s11.setDate1("2020-03-21 11:20");
		s11.setDate2("2020-03-21 17:20");
		Timeslot s12 = new Timeslot() ; 
		s12.setDate1("2020-03-22 11:20");
		s12.setDate2("2021-03-22 17:20");
		TrainSchedule t = new TrainSchedule() ;
		String name = "s" ;

		assertTrue(t.addplanningEntry(name, loc1, loc2, s11.getdate1(), s11.getdate2())) ;
		assertTrue(t.addplanningEntry(name, loc2, new Location("s"), s12.getdate1(), s12.getdate2()));
		assertFalse(t.addplanningEntry(name, loc1, loc2,  s11.getdate1(), s11.getdate2()));
		assertFalse(t.addplanningEntry("ss", loc1, loc1, s11.getdate1(), s11.getdate2()));
//		assertFalse(t.addplanningEntry("ss", loc1, loc2,  s11.getdate2(), s11.getdate1()));
	}
}
