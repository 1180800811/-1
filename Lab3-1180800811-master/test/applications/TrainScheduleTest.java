package applications;

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
import PlanningEntry.PlanningEntry;
import PlanningEntry.TrainEntry;
import Resource.MultipleSortedResourceEntryImpl;
import Resource.Railway;
import Resource.Type;
import Timeslot.Timeslot;

public class TrainScheduleTest {
	/*
	 * Testing for addPlanningEntry方法
	 * Partition : there is conflict after adding a PlanningEntry , there is not conflict after adding a PlanningEntry 
	 * 
	 * Testing for cancelPlaningEntry方法
	 * Partition : the state of the PlanningEntry is ALOCATED, WAITING,CANCELLED,ENDED,RUNNING
	 * 
	 * Testing for FeiPeiResource
	 * output:0 : 想要分配的车厢不在可用车厢内 , 1 : 想要分配资源的计划项已经分配资源 2 :分配成功 , 3 :分配资源存在冲突 , 4:想要分配车厢的高铁不存在
	 *
	 *	Testing for BeginPlanningEntry方法
	 *	Partition : the state of the PlanningEntry is ALOCATED, WAITING,CANCELLED,ENDED,RUNNING
	 *
	 * Testing for EndPlanningEntry方法
	 *  Partition：the state of the PlanningEntry is ALOCATED, WAITING,CANCELLED,ENDED,RUNNING
	 *  
	 *	Testing for addLocation方法
	 *	Partition : 添加的位置不存在，添加的位置已经存在
	 *
	 *	Testing for deleteLocation方法
	 *	Partition : 待删除的位置不存在，待删除的位置已经存在
	 *
	 *	Testing for addResource方法
	 *	Partition : 添加的资源不存在，添加的资源已经存在
	 *
	 *	Testing for deleteResource方法
	 *	Partition : 待删除的资源不存在，待删除的资源已经存在
	 *
	 * Testing for check 方法
	 * Partition:there is conflict between PlanningEntrys , there is not conflict between PlanningEntrys , 
	 * 
	 * Testing for getPrePlanningEntry 方法
	 * Partition: there is PrePlanningEntry , there is not PrePlanningEntry
	 */
	
	/*
	 * 测试addPlanningEntry方法
	 * 覆盖：there is conflict after adding a PlanningEntry , there is not conflict after adding a PlanningEntry 
	 */
	@Test
	public void addPlanningEntryTest() throws ParseException  {
		Location loc1 = new Location(1.1, 1.2, "哈尔滨西", true);
		Location loc2 = new Location(1.21, 1.23, "南京", true);
		Location loc3 = new Location(2.5, 2.3, "昆明", true);
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
	 * 	测试 cancelPlaningEntry方法
	 * 覆盖：the state of the PlanningEntry is ALOCATED, WAITING,CANCELLED,ENDED,RUNNING
	 */
	@Test
	public void cancelPlaningEntryTest() throws ParseException {
		Location loc1 = new Location(1.1, 1.2, "哈尔滨西", true);
		Location loc2 = new Location(1.21, 1.23, "南京", true);
		Location loc3 = new Location(2.5, 2.3, "昆明", true);
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
		assertTrue(t.cancelPlanningEntry(name, s1.getdate1()).equals("高铁已经取消"));
		assertTrue(t.cancelPlanningEntry(name, s1.getdate1()).equals("高铁当前状态为CANCELLED,不能取消"));
	}
	
	/*
	 * 测试FeiPeiResource方法
	 * 覆盖:0 : 想要分配的飞机不在可用飞机内 , 1 : 想要分配资源的计划项已经分配资源 2 :分配成功 , 3 :分配资源存在冲突 , 4:想要分配车厢的高铁不存在
	 */
	@Test
	public void FeiPeiResourceTest() throws Exception {
		Location loc1 = new Location(1.1, 1.2, "哈尔滨西", true);
		Location loc2 = new Location(1.21, 1.23, "南京", true);
		Location loc3 = new Location(2.5, 2.3, "昆明", true);
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
		assertTrue(t.FeiPeiResource(name, s1.getdate1(), r1) == 0 ) ;//覆盖:想要分配的车厢不在可用车厢内
		t.addResource("aa", "商务", 30, 2019);
		t.addResource("bb","商务", 30, 2019);
		assertTrue(t.FeiPeiResource("sss", s1.getdate1(), r1) == 4 ) ;//覆盖:想要分配车厢的高铁不存在
		assertTrue(t.FeiPeiResource(name, s1.getdate1(), r1) == 2 ) ;//分配成功
		assertTrue(t.FeiPeiResource(name, s1.getdate1(), r1) == 1 ) ;//覆盖:想要分配资源的计划项已经分配了该资源
		
	}
	
	/*
	 * 测试 WatchState方法
	 * 
	 */
	@Test
	public void WatchStateTest() throws ParseException {
		Location loc1 = new Location(1.1, 1.2, "哈尔滨西", true);
		Location loc2 = new Location(1.21, 1.23, "南京", true);
		Location loc3 = new Location(2.5, 2.3, "昆明", true);
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
	 * 测试BeginPlanningEntry方法
	 * 覆盖：the state of the PlanningEntry is ALOCATED, WAITING,CANCELLED,ENDED,RUNNING
	 */
	@Test
	public void BeginPlanningEntryTest() throws Exception {
		Location loc1 = new Location(1.1, 1.2, "哈尔滨西", true);
		Location loc2 = new Location(1.21, 1.23, "南京", true);
		Location loc3 = new Location(2.5, 2.3, "昆明", true);
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
		assertTrue(t.BeginPlanningEntry("s", s1.getdate1()).equals("指定的高铁不存在"));
		t.addPlanningEntry(ss, time, name);
		assertTrue(t.BeginPlanningEntry(name, s1.getdate1()).equals("高铁当前状态为WAITTING,不能启动"));
		Railway r1 = new Railway("aa", Type.BUSINESS, 30, 2019);
		Railway r2 = new Railway("bb", Type.BUSINESS, 30, 2019);

		t.addResource("aa", "商务", 30, 2019);
		t.addResource("bb", "商务", 30, 2019);
		t.FeiPeiResource(name, s1.getdate1(), r1);
		t.FeiPeiResource(name, s1.getdate1(), r2);
		assertTrue(t.BeginPlanningEntry(name, s1.getdate1()).equals("高铁已经启动"));
	}
	
	
	/*
	 * 测试EndPlanningEntry方法
	 * 覆盖：the state of the PlanningEntry is ALOCATED, WAITING,CANCELLED,ENDED,RUNNING
	 */
	@Test
	public void EndPlanningEntryTest() throws Exception {
		Location loc1 = new Location(1.1, 1.2, "哈尔滨西", true);
		Location loc2 = new Location(1.21, 1.23, "南京", true);
		Location loc3 = new Location(2.5, 2.3, "昆明", true);
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
		assertTrue(t.EndPlanningEntry("s", s1.getdate1()).equals("指定的高铁不存在"));
		t.addPlanningEntry(ss, time, name);
		assertTrue(t.EndPlanningEntry(name, s1.getdate1()).equals("高铁当前状态为WAITTING,不能结束"));
		Railway r1 = new Railway("aa", Type.BUSINESS, 30, 2019);
		Railway r2 = new Railway("bb", Type.BUSINESS, 30, 2019);
		List<Railway> msre1 = new ArrayList<Railway>() ;
		msre1.add(r1);
		msre1.add(r2);
		t.addResource("aa", "商务", 30, 2019);
		t.addResource("bb", "商务", 30, 2019);
		t.FeiPeiResource(name, s1.getdate1(), r1);
		t.FeiPeiResource(name, s1.getdate1(), r2);
		assertTrue(t.BeginPlanningEntry(name, s1.getdate1()).equals("高铁已经启动"));
		assertTrue(t.EndPlanningEntry(name, s1.getdate1()).equals("高铁已经结束"));
	}
	
	/*
	 * 测试BlockPlanningEntry方法
	 */
	@Test
	public void BlockPlanningEntryTest() throws Exception {
		Location loc1 = new Location(1.1, 1.2, "哈尔滨西", true);
		Location loc2 = new Location(1.21, 1.23, "南京", true);
		Location loc3 = new Location(2.5, 2.3, "昆明", true);
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
		assertTrue(t.BlockPlanningEntry("s", s1.getdate1()).equals("指定的高铁不存在"));
		t.addPlanningEntry(ss, time, name);
		assertTrue(t.BlockPlanningEntry(name, s1.getdate1()).equals("高铁当前状态为WAITTING,不能阻塞"));
		Railway r1 = new Railway("aa", Type.BUSINESS, 30, 2019);
		Railway r2 = new Railway("bb", Type.BUSINESS, 30, 2019);
		List<Railway> msre1 = new ArrayList<Railway>() ;
		msre1.add(r1);
		msre1.add(r2);
		t.addResource("aa", "商务", 30, 2019);
		t.addResource("bb", "商务", 30, 2019);
		t.FeiPeiResource(name, s1.getdate1(), r1);
		t.FeiPeiResource(name, s1.getdate1(), r2);
		assertTrue(t.BeginPlanningEntry(name, s1.getdate1()).equals("高铁已经启动"));
		assertTrue(t.BlockPlanningEntry(name, s1.getdate1()).equals("高铁已经阻塞"));
	}
	/*
	 * 测试addLocation方法
	 */
	@Test
	public void addLocationTest() throws Exception {

		TrainSchedule c = new TrainSchedule() ;
		assertTrue(c.addLocation(new Location("ss")));//添加位置成功
		assertFalse(c.addLocation(new Location("ss")));//添加的位置已经存在
	}
	
	/*
	 * 
	 * 测试deletLocation方法
	 */
	@Test
	public void deleteLocationTest() {
		TrainSchedule c = new TrainSchedule() ;
		assertFalse(c.deleteLocation(new Location("ss")));//待删除的位置不存在
		c.addLocation(new Location("ss"));
		assertTrue(c.deleteLocation(new Location("ss")));//待删除的位置存在
	}
	
	/*
	 * 测试addResource方法
	 */
	@Test
	public void addResourceTest() throws Exception {

		TrainSchedule c = new TrainSchedule() ;
		assertTrue(c.addResource("aa", "商务", 30, 2019));//添加资源成功
		assertFalse(c.addResource("aa", "商务", 30, 2019));//添加的资源已经存在
	}
	/*
	 * 
	 * 测试deletResource方法
	 */
	@Test
	public void deleteResourceTest() {
		TrainSchedule c = new TrainSchedule() ;
		assertFalse(c.deleteResource("aa", "商务", 30, 2019));//待删除的资源不存在
		c.addResource("aa", "商务", 30, 2019);
		assertTrue(c.deleteResource("aa", "商务", 30, 2019));//待删除的资源存在
	}
	
	/*
	 * 测试check方法
	 */
	@Test
	public void checkTest() throws Exception {
		Location loc1 = new Location(1.1, 1.2, "哈尔滨西", true);
		Location loc2 = new Location(1.21, 1.23, "南京", true);
		Location loc3 = new Location(2.5, 2.3, "昆明", true);
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
		t.addResource("aa", "商务", 30, 2019);
		t.addResource("bb", "商务", 30, 2019);
		t.FeiPeiResource(name, s1.getdate1(), r1);
		t.FeiPeiResource(name, s1.getdate1(), r2);
		assertTrue(t.check());
	}
	
	/*
	 * 测试getPrePlanningEntry方法
	 */
	@Test
	public void getPrePlanningEntryTest() throws Exception {
		Location loc1 = new Location(1.1, 1.2, "哈尔滨西", true);
		Location loc2 = new Location(1.21, 1.23, "南京", true);
		Location loc3 = new Location(2.5, 2.3, "昆明", true);
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
		Railway r1 = new Railway("aa", Type.BUSINESS, 30, 2019);
		Railway r2 = new Railway("bb", Type.BUSINESS, 30, 2019);
		List<Railway> msre1 = new ArrayList<Railway>() ;
		msre1.add(r1);
		msre1.add(r2);
		t.addResource("aa", "商务", 30, 2019);
		t.addResource("bb", "商务", 30, 2019);
		t.FeiPeiResource(name, s1.getdate1(), r1);
		t.FeiPeiResource(name, s1.getdate1(), r2);
		


		
		Location loc11 = new Location(1.1, 1.2, "北京南", true);
		Location loc22 = new Location(1.21, 1.23, "上海", true);	
		Location loc33 = new Location(1.21, 1.23, "南昌", true);	
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
		t.addResource("aa", "商务", 30, 2019);
		t.addResource("cc", "商务", 30, 2019);
		t.FeiPeiResource(name1, s11.getdate1(), r11);
		t.FeiPeiResource(name1, s11.getdate1(), r22);
		assertTrue(t.getPrePlanningEntry("aa", "商务", 30, 2019, name1, s11.getdate1()));
	}
	@Test
	public void addplanningEntryTest() throws ParseException {
		Location loc1 = new Location(1.1, 1.2, "哈尔滨西", true);
		Location loc2 = new Location(1.21, 1.23, "南京", true);
		Timeslot s11 = new Timeslot() ; 
		s11.setDate1("2020-03-21 11:20");
		s11.setDate2("2020-03-21 17:20");
		TrainSchedule t = new TrainSchedule() ;
		String name = "s" ;
		assertTrue(t.addplanningEntry(name, loc1, loc2, s11.getdate1(), s11.getdate2())) ;
		assertFalse(t.addplanningEntry(name, loc1, loc2,  s11.getdate1(), s11.getdate2()));
	}
}
