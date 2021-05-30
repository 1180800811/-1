package Board;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import EntryState.ALOCATED;
import EntryState.BLOCKED;
import EntryState.BlockableEntryImpl;
import EntryState.ENDED;
import EntryState.RUNNING;
import EntryState.WAITTING;
import Location.Location;
import Location.MultipleLocationEntryImpl;
import PlanningEntry.PlanningEntry;
import PlanningEntry.TrainEntry;
import Resource.MultipleSortedResourceEntryImpl;
import Resource.Railway;
import Resource.Type;
import Timeslot.Timeslot;

public class TrainScheduleBoardTest {

	/*
	 * Testing strategy for changeState()
	 * Partition :  train.getResource() == null , train.getResource != null
	 * 				time.compareTo(train.getTime1()) < 0 ,time.compareTo(train.getTime2()) > 0 , time.compareTo(train.getTime1()) > 0 && time.compareTo(train.getTime2())< 0 , 
	 *
	 *Testing strategy for iterator
	 * 				input : a list of TrainEntry
	 * 				output: a orderly list of TrainEntry
	 *
	 *Testing strategy for gettrain1()
	 *				input : a list of TrainEntry
	 *				output: a Map with orderly key of TrainEntry
	 *Testing strategy for gettrain2()
	 *				input : a list of TrainEntry
	 *				output: a Map with orderly key of TrainEntry
	 *
	 *
	 */
	
	/*
	 * ����changeState����
	 * ����:train.getResource() == null
	 */
	@Test
	public void changeStateTest1() throws ParseException {
		Location loc1 = new Location(1.1, 1.2, "��������", true);
		Location loc2 = new Location(1.21, 1.23, "�Ͼ�", true);
		Location loc3 = new Location(2.5, 2.3, "����", true);
		List<Location> ss = new ArrayList<Location>() ;
		ss.add(loc1);
		ss.add(loc2);
		ss.add(loc3);
		MultipleLocationEntryImpl locs = new MultipleLocationEntryImpl(ss);	//��1��һϵ��λ��
		
		Timeslot s1 = new Timeslot() ; 
		Timeslot s2 = new Timeslot() ;
		s1.setDate1("2020-03-21 10:20");
		s1.setDate2("2020-03-21 17:20");
		s2.setDate1("2020-03-22 10:20");
		s2.setDate2("2020-03-22 17:20");
		List<Timeslot> time = new ArrayList<Timeslot>() ;
		time.add(s1);
		time.add(s2);
		BlockableEntryImpl be1 = new BlockableEntryImpl(time);//��1��һϵ��ʱ��
		
		MultipleSortedResourceEntryImpl<Railway> msre2 = new MultipleSortedResourceEntryImpl<Railway>() ;//��1�ĳ���
		TrainEntry train = new TrainEntry("����һ��", locs,be1, msre2);//��1
		List<TrainEntry> t = new ArrayList<TrainEntry>() ;
		t.add(train);
		
		Calendar time3 = Calendar.getInstance() ;
		time3.set(2020, 3-1, 21, 8, 20);
		
		TrainScheduleBoard bo = new TrainScheduleBoard(time3,  loc2,t);
		bo.changeState(train);
		assertTrue(train.getState() instanceof WAITTING);
		
	}
	/*
	 * Partition :  train.getResource() == null , train.getResource != null
	 * 				time.compareTo(train.getTime1()) < 0 ,time.compareTo(train.getTime2()) > 0 , time.compareTo(train.getTime1()) > 0 && time.compareTo(train.getTime2())< 0 , 
	 */
	@Test
	public void changeStateTest21() throws ParseException {
		Location loc1 = new Location(1.1, 1.2, "��������", true);
		Location loc2 = new Location(1.21, 1.23, "�Ͼ�", true);
		Location loc3 = new Location(2.5, 2.3, "����", true);
		List<Location> ss = new ArrayList<Location>() ;
		ss.add(loc1);
		ss.add(loc2);
		ss.add(loc3);
		MultipleLocationEntryImpl locs = new MultipleLocationEntryImpl(ss);	//��1��һϵ��λ��
		
		Timeslot s1 = new Timeslot() ; 
		Timeslot s2 = new Timeslot() ;
		s1.setDate1("2020-03-21 10:20");
		s1.setDate2("2020-03-21 17:20");
		s2.setDate1("2020-03-22 10:20");
		s2.setDate2("2020-03-22 17:20");
		List<Timeslot> time = new ArrayList<Timeslot>() ;
		time.add(s1);
		time.add(s2);
		BlockableEntryImpl be1 = new BlockableEntryImpl(time);//��1��һϵ��ʱ��
		Railway r1 = new Railway("aa", Type.BUSINESS, 30, 2019);
		Railway r2 = new Railway("bb", Type.BUSINESS, 30, 2019);
		
		List<Railway> msre1 = new ArrayList<Railway>() ;
		msre1.add(r1);
		msre1.add(r2);
		
		MultipleSortedResourceEntryImpl<Railway> msre2 = new MultipleSortedResourceEntryImpl<Railway>() ;//��1�ĳ���
		msre2.setResource(msre1);
		TrainEntry train = new TrainEntry("����һ��", locs,be1, msre2);//��1
		List<TrainEntry> t = new ArrayList<TrainEntry>() ;
		t.add(train);
		
		Calendar time3 = Calendar.getInstance() ;
		time3.set(2020, 3-1, 21, 8, 20);
		
		TrainScheduleBoard bo = new TrainScheduleBoard(time3,  loc2,t);
		bo.changeState(train);
		assertTrue(train.getState() instanceof ALOCATED);//�𳵴�����
		time3.set(2020, 3-1, 22, 18, 20);
		TrainScheduleBoard bo1 = new TrainScheduleBoard(time3,  loc2,t);
		bo1.changeState(train);
		assertTrue(train.getState() instanceof ENDED);//�����Ѿ�����
		
		time3.set(2020, 3-1, 21, 15, 20);
		TrainScheduleBoard bo2 = new TrainScheduleBoard(time3,  loc2,t);
		bo2.changeState(train);
		assertTrue(train.getState() instanceof RUNNING);//������������
		
		time3.set(2020, 3-1, 22, 8, 20);
		TrainScheduleBoard bo3 = new TrainScheduleBoard(time3,  loc2,t);
		bo3.changeState(train);
		assertTrue(train.getState() instanceof BLOCKED);//��������������
		
	}
	/*
	 * ����gettrain1����
	 */
	@Test
	public void gettrain1Test() throws ParseException {
		Location loc1 = new Location(1.1, 1.2, "��������", true);
		Location loc2 = new Location(1.21, 1.23, "�ϲ�", true);
		Location loc3 = new Location(2.5, 2.3, "����", true);
		List<Location> ss = new ArrayList<Location>() ;
		ss.add(loc1);
		ss.add(loc2);
		ss.add(loc3);
		MultipleLocationEntryImpl locs = new MultipleLocationEntryImpl(ss);	//��1��һϵ��λ��
		
		Timeslot s1 = new Timeslot() ; 
		Timeslot s2 = new Timeslot() ;
		s1.setDate1("2020-03-21 10:20");
		s1.setDate2("2020-03-21 17:20");
		s2.setDate1("2020-03-22 10:20");
		s2.setDate2("2020-03-22 17:20");
		List<Timeslot> time = new ArrayList<Timeslot>() ;
		time.add(s1);
		time.add(s2);
		BlockableEntryImpl be1 = new BlockableEntryImpl(time);//��1��һϵ��ʱ��
		Railway r1 = new Railway("aa", Type.BUSINESS, 30, 2019);
		Railway r2 = new Railway("bb", Type.BUSINESS, 30, 2019);
		
		List<Railway> msre1 = new ArrayList<Railway>() ;
		msre1.add(r1);
		msre1.add(r2);
		
		MultipleSortedResourceEntryImpl<Railway> msre2 = new MultipleSortedResourceEntryImpl<Railway>() ;//��1�ĳ���
		msre2.setResource(msre1);
		TrainEntry train = new TrainEntry("����һ��", locs,be1, msre2);//��1
		Location loc11 = new Location(1.1, 1.2, "������", true);
		Location loc22 = new Location(1.21, 1.23, "�Ϻ�", true);	
		Location loc33 = new Location(1.21, 1.23, "�ϲ�", true);	
		List<Location> sss = new ArrayList<Location>() ;
		sss.add(loc11);
		sss.add(loc22);
		sss.add(loc33);
		MultipleLocationEntryImpl locs1 = new MultipleLocationEntryImpl(sss);	//��2��һϵ��λ��
		
		Railway r11 = new Railway("aa", Type.BUSINESS, 30, 2019);
		Railway r22 = new Railway("cc", Type.FIRSTCLASS, 30, 2019);
		
		List<Railway> msre11 = new ArrayList<Railway>() ;
		msre11.add(r11);
		msre11.add(r22);
		
		MultipleSortedResourceEntryImpl<Railway> msre22 = new MultipleSortedResourceEntryImpl<Railway>() ;//��2�ĳ���
		msre22.setResource(msre11);
		
		Timeslot s11 = new Timeslot() ; 
		Timeslot s22 = new Timeslot() ;
		s11.setDate1("2020-03-21 11:20");
		s11.setDate2("2020-03-21 16:20");
		s22.setDate1("2020-03-22 9:20");
		s22.setDate2("2020-03-22 16:20");
		List<Timeslot> time1 = new ArrayList<Timeslot>() ;
		time1.add(s11);
		time1.add(s22);
		BlockableEntryImpl be11 = new BlockableEntryImpl(time1);//��2��һϵ��ʱ��
		TrainEntry train1 = new TrainEntry("���˺�", locs1,be11, msre22);//��2
		
		List<TrainEntry> s = new ArrayList<TrainEntry>() ;
		s.add(train1);
		s.add(train);
		
		Calendar time3 = Calendar.getInstance() ;
		time3.set(2020, 3-1, 21, 8, 20);
		
		
		TrainScheduleBoard bo = new TrainScheduleBoard(time3,  loc2,s);
		Map<Calendar , TrainEntry> map = bo.gettrain1();
		assertTrue(map.get(s1.getdate2()).equals(train1));
		assertTrue(map.get(s22.getdate2()).equals(train1));
	}
	
	
	/*
	 * ����gettrain2����
	 */
	@Test
	public void gettrain2Test() throws ParseException {
		Location loc1 = new Location(1.1, 1.2, "��������", true);
		Location loc2 = new Location(1.21, 1.23, "�ϲ�", true);
		Location loc3 = new Location(2.5, 2.3, "����", true);
		List<Location> ss = new ArrayList<Location>() ;
		ss.add(loc1);
		ss.add(loc2);
		ss.add(loc3);
		MultipleLocationEntryImpl locs = new MultipleLocationEntryImpl(ss);	//��1��һϵ��λ��
		
		Timeslot s1 = new Timeslot() ; 
		Timeslot s2 = new Timeslot() ;
		s1.setDate1("2020-03-21 10:20");
		s1.setDate2("2020-03-21 17:20");
		s2.setDate1("2020-03-22 10:20");
		s2.setDate2("2020-03-22 17:20");
		List<Timeslot> time = new ArrayList<Timeslot>() ;
		time.add(s1);
		time.add(s2);
		BlockableEntryImpl be1 = new BlockableEntryImpl(time);//��1��һϵ��ʱ��
		Railway r1 = new Railway("aa", Type.BUSINESS, 30, 2019);
		Railway r2 = new Railway("bb", Type.BUSINESS, 30, 2019);
		
		List<Railway> msre1 = new ArrayList<Railway>() ;
		msre1.add(r1);
		msre1.add(r2);
		
		MultipleSortedResourceEntryImpl<Railway> msre2 = new MultipleSortedResourceEntryImpl<Railway>() ;//��1�ĳ���
		msre2.setResource(msre1);
		TrainEntry train = new TrainEntry("����һ��", locs,be1, msre2);//��1
		Location loc11 = new Location(1.1, 1.2, "������", true);
		Location loc22 = new Location(1.21, 1.23, "�ϲ�", true);	
		Location loc33 = new Location(1.21, 1.23, "�Ϻ�", true);	
		List<Location> sss = new ArrayList<Location>() ;
		sss.add(loc11);
		sss.add(loc22);
		sss.add(loc33);
		MultipleLocationEntryImpl locs1 = new MultipleLocationEntryImpl(sss);	//��2��һϵ��λ��
		
		Railway r11 = new Railway("aa", Type.BUSINESS, 30, 2019);
		Railway r22 = new Railway("cc", Type.FIRSTCLASS, 30, 2019);
		
		List<Railway> msre11 = new ArrayList<Railway>() ;
		msre11.add(r11);
		msre11.add(r22);
		
		MultipleSortedResourceEntryImpl<Railway> msre22 = new MultipleSortedResourceEntryImpl<Railway>() ;//��2�ĳ���
		msre22.setResource(msre11);
		
		Timeslot s11 = new Timeslot() ; 
		Timeslot s22 = new Timeslot() ;
		s11.setDate1("2020-03-21 11:20");
		s11.setDate2("2020-03-21 17:20");
		s22.setDate1("2020-03-22 10:30");
		s22.setDate2("2020-03-22 16:20");
		List<Timeslot> time1 = new ArrayList<Timeslot>() ;
		time1.add(s11);
		time1.add(s22);
		BlockableEntryImpl be11 = new BlockableEntryImpl(time1);//��2��һϵ��ʱ��
		TrainEntry train1 = new TrainEntry("���˺�", locs1,be11, msre22);//��2
		
		List<TrainEntry> s = new ArrayList<TrainEntry>() ;
		s.add(train1);
		s.add(train);
		
		Calendar time3 = Calendar.getInstance() ;
		time3.set(2020, 3-1, 21, 8, 20);
		TrainScheduleBoard bo = new TrainScheduleBoard(time3,  loc2,s);
		Map<Calendar , TrainEntry> map = bo.gettrain2();
		assertTrue(map.get(s2.getdate1()).equals(train1));
		assertTrue(map.get(s22.getdate1()).equals(train1));
	}
	
	/*
	 * ����getTrain����
	 */
	@Test
	public void getTrainTest() throws ParseException {
		Location loc1 = new Location(1.1, 1.2, "��������", true);
		Location loc2 = new Location(1.21, 1.23, "�ϲ�", true);
		Location loc3 = new Location(2.5, 2.3, "����", true);
		List<Location> ss = new ArrayList<Location>() ;
		ss.add(loc1);
		ss.add(loc2);
		ss.add(loc3);
		MultipleLocationEntryImpl locs = new MultipleLocationEntryImpl(ss);	//��1��һϵ��λ��
		
		Timeslot s1 = new Timeslot() ; 
		Timeslot s2 = new Timeslot() ;
		s1.setDate1("2020-03-21 10:20");
		s1.setDate2("2020-03-21 17:20");
		s2.setDate1("2020-03-22 10:30");
		s2.setDate2("2020-03-22 17:20");
		List<Timeslot> time = new ArrayList<Timeslot>() ;
		time.add(s1);
		time.add(s2);
		BlockableEntryImpl be1 = new BlockableEntryImpl(time);//��1��һϵ��ʱ��
		Railway r1 = new Railway("aa", Type.BUSINESS, 30, 2019);
		Railway r2 = new Railway("bb", Type.BUSINESS, 30, 2019);
		
		List<Railway> msre1 = new ArrayList<Railway>() ;
		msre1.add(r1);
		msre1.add(r2);
		
		MultipleSortedResourceEntryImpl<Railway> msre2 = new MultipleSortedResourceEntryImpl<Railway>() ;//��1�ĳ���
		msre2.setResource(msre1);
		TrainEntry train = new TrainEntry("����һ��", locs,be1, msre2);//��1
		Location loc11 = new Location(1.1, 1.2, "������", true);
		Location loc22 = new Location(1.21, 1.23, "�ϲ�", true);	
		Location loc33 = new Location(1.21, 1.23, "�Ϻ�", true);	
		List<Location> sss = new ArrayList<Location>() ;
		sss.add(loc11);
		sss.add(loc22);
		sss.add(loc33);
		MultipleLocationEntryImpl locs1 = new MultipleLocationEntryImpl(sss);	//��2��һϵ��λ��
		
		Railway r11 = new Railway("aa", Type.BUSINESS, 30, 2019);
		Railway r22 = new Railway("cc", Type.FIRSTCLASS, 30, 2019);
		
		List<Railway> msre11 = new ArrayList<Railway>() ;
		msre11.add(r11);
		msre11.add(r22);
		
		MultipleSortedResourceEntryImpl<Railway> msre22 = new MultipleSortedResourceEntryImpl<Railway>() ;//��2�ĳ���
		msre22.setResource(msre11);
		
		Timeslot s11 = new Timeslot() ; 
		Timeslot s22 = new Timeslot() ;
		s11.setDate1("2020-03-21 11:20");
		s11.setDate2("2020-03-21 17:20");
		s22.setDate1("2020-03-22 10:30");
		s22.setDate2("2020-03-22 16:20");
		List<Timeslot> time1 = new ArrayList<Timeslot>() ;
		time1.add(s11);
		time1.add(s22);
		BlockableEntryImpl be11 = new BlockableEntryImpl(time1);//��2��һϵ��ʱ��
		TrainEntry train1 = new TrainEntry("���˺�", locs1,be11, msre22);//��2
		
		List<TrainEntry> s = new ArrayList<TrainEntry>() ;
		s.add(train1);
		s.add(train);
		
		Calendar time3 = Calendar.getInstance() ;
		time3.set(2020, 3-1, 21, 8, 20);
		TrainScheduleBoard bo = new TrainScheduleBoard(time3,  loc2,s);

		List<TrainEntry> tr = bo.getTrain();
		assertTrue(tr.get(0).equals(train));		
		assertTrue(tr.get(1).equals(train1));
	}
	
	/*
	 * ����iterator����
	 */
	@Test
	public void iteratorTest() throws ParseException {
		Location loc1 = new Location(1.1, 1.2, "��������", true);
		Location loc2 = new Location(1.21, 1.23, "�ϲ�", true);
		Location loc3 = new Location(2.5, 2.3, "����", true);
		List<Location> ss = new ArrayList<Location>() ;
		ss.add(loc1);
		ss.add(loc2);
		ss.add(loc3);
		MultipleLocationEntryImpl locs = new MultipleLocationEntryImpl(ss);	//��1��һϵ��λ��
		
		Timeslot s1 = new Timeslot() ; 
		Timeslot s2 = new Timeslot() ;
		s1.setDate1("2020-03-21 10:20");
		s1.setDate2("2020-03-21 17:10");
		s2.setDate1("2020-03-21 18:10");
		s2.setDate2("2020-03-22 17:20");
		List<Timeslot> time = new ArrayList<Timeslot>() ;
		time.add(s1);
		time.add(s2);
		BlockableEntryImpl be1 = new BlockableEntryImpl(time);//��1��һϵ��ʱ��
		Railway r1 = new Railway("aa", Type.BUSINESS, 30, 2019);
		Railway r2 = new Railway("bb", Type.BUSINESS, 30, 2019);
		
		List<Railway> msre1 = new ArrayList<Railway>() ;
		msre1.add(r1);
		msre1.add(r2);
		
		MultipleSortedResourceEntryImpl<Railway> msre2 = new MultipleSortedResourceEntryImpl<Railway>() ;//��1�ĳ���
		msre2.setResource(msre1);
		TrainEntry train = new TrainEntry("����һ��", locs,be1, msre2);//��1
		Location loc11 = new Location(1.1, 1.2, "������", true);
		Location loc22 = new Location(1.21, 1.23, "�ϲ�", true);	
		Location loc33 = new Location(1.21, 1.23, "�Ϻ�", true);	
		List<Location> sss = new ArrayList<Location>() ;
		sss.add(loc11);
		sss.add(loc22);
		sss.add(loc33);
		MultipleLocationEntryImpl locs1 = new MultipleLocationEntryImpl(sss);	//��2��һϵ��λ��
		
		Railway r11 = new Railway("aa", Type.BUSINESS, 30, 2019);
		Railway r22 = new Railway("cc", Type.FIRSTCLASS, 30, 2019);
		
		List<Railway> msre11 = new ArrayList<Railway>() ;
		msre11.add(r11);
		msre11.add(r22);
		
		MultipleSortedResourceEntryImpl<Railway> msre22 = new MultipleSortedResourceEntryImpl<Railway>() ;//��2�ĳ���
		msre22.setResource(msre11);
		
		Timeslot s11 = new Timeslot() ; 
		Timeslot s22 = new Timeslot() ;
		s11.setDate1("2020-03-21 11:20");
		s11.setDate2("2020-03-21 17:20");
		s22.setDate1("2020-03-21 18:00");
		s22.setDate2("2020-03-22 16:20");
		List<Timeslot> time1 = new ArrayList<Timeslot>() ;
		time1.add(s11);
		time1.add(s22);
		BlockableEntryImpl be11 = new BlockableEntryImpl(time1);//��2��һϵ��ʱ��
		TrainEntry train1 = new TrainEntry("���˺�", locs1,be11, msre22);//��2
		
		List<TrainEntry> s = new ArrayList<TrainEntry>() ;
		s.add(train1);
		s.add(train);
		
		Calendar time3 = Calendar.getInstance() ;
		time3.set(2020, 3-1, 21, 8, 20);
		TrainScheduleBoard bo = new TrainScheduleBoard(time3,  loc2,s);
		Iterator<PlanningEntry> pl = bo.iterator() ;
		List<PlanningEntry> ssss = new ArrayList<PlanningEntry>() ;
		while(pl.hasNext()) {
			ssss.add(pl.next());
		}
		assertTrue(ssss.get(0).equals(train));
		assertTrue(ssss.get(1).equals(train1));
		bo.show(loc22, s11.getdate1());
		bo.visualize(0);
		bo.visualize(1);
		
	}
}
