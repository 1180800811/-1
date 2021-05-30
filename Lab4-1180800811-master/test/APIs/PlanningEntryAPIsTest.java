package APIs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import Location.Location;
import Location.MultipleLocationEntryImpl;
import PlanningEntry.CourseEntry;
import PlanningEntry.FlightEntry;
import PlanningEntry.PlanningEntry;
import PlanningEntry.TrainEntry;
import Resource.MultipleSortedResourceEntryImpl;
import Resource.Plane;
import Resource.Railway;
import Resource.Teacher;
import Resource.Type;
import Timeslot.Timeslot;
import APIs.PlanningEntryAPIs;
import EntryState.BlockableEntryImpl;
public class PlanningEntryAPIsTest {

	/*
	 * Testing strategy for checkLocationConflict()
	 * Partition for PlanningEntry: CourseEntry
	 * Partition for output : 		there is Location Conflict between PlanningEntrys
	 * 								there is not Location Conflict between PlanningEntrys
	 * 
	 * Testing strategy for checkLocationConflict()
	 *  Partition for PlanningEntry: CourseEntry , FlightEntry , TrainEntry
	 * 	Partition for output : 		there is Resource Conflict between PlanningEntrys
	 * 								there is not Resource Conflict between PlanningEntrys
	 * 
	 * Testing strategy for findPreEntryPerResource(R r, PlanningEntry<R> e, List< ? extends PlanningEntry<R>> entries)
	 * Partition for PlanningEntry: CourseEntry, FlightEntry , TrainEntry
	 * Partition for output : PlanningEntry == null  , PlanningEntry != null
	 */
	
	
	
	
	/*
	 * 测试checkLocationConflict方法
	 * 覆盖:CourseEntry
	 * 	there is Location Conflict between CourseEntrys
	 *  there is not Location Conflict between CourseEntrys
	 */
	@Test
	public void checkLocationConflictTest() {
		CourseEntry s1 = new CourseEntry("软件构造") ;
		Teacher chuanhanxu1 = new Teacher("156", "chuan", true, "教师") ;
		s1.setResource(chuanhanxu1);
		Timeslot t1 = new Timeslot() ;
		Calendar time1 = Calendar.getInstance() ;
		time1.set(2020, 2-1, 13, 8, 00);
		Calendar time2 = Calendar.getInstance() ;
		time2.set(2020, 2-1, 13, 9, 45);
		t1.setdate1(time1);
		t1.setdate2(time2);
		s1.setTime(t1);
		Location loc1 = new Location(11, 22, "正心", false);
		s1.setLocation(loc1);
		
		CourseEntry s2 = new CourseEntry("算法设计") ;
		Teacher chuanhanxu2 = new Teacher("156", "chen", true, "教师") ;
		s2.setResource(chuanhanxu2);
		Timeslot t2 = new Timeslot() ;
		Calendar time11 = Calendar.getInstance() ;
		time11.set(2020, 2-1, 13, 9, 00);
		Calendar time22 = Calendar.getInstance() ;
		time22.set(2020, 2-1, 13, 10, 45);
		t2.setdate1(time11);
		t2.setdate2(time22);
		s2.setTime(t2);
		Location loc2 = new Location(11, 22, "正心", false);
		s2.setLocation(loc2);	
		
		List<PlanningEntry> course = new ArrayList<PlanningEntry>() ;
		course.add(s1);
		course.add(s2);
		assertFalse(PlanningEntryAPIs.checkLocationConflict(course));//计划项存在位置冲突
		
		time11.set(2020, 2-1, 13, 10, 00);
		time22.set(2020, 2-1, 13, 11, 45);
		t2.setdate1(time11);
		t2.setdate2(time22);
		s2.setTime(t2);
		List<PlanningEntry> course1 = new ArrayList<PlanningEntry>() ;
		course1.add(s1);
		course1.add(s2);
		assertTrue(PlanningEntryAPIs.checkLocationConflict(course));//计划项不存在位置冲突
	}
	
	/*
	 * 覆盖:CourseEntry
	 * 	  	there is Resource Conflict between CourseEntrys
	 *  	there is not Resource Conflict between CourseEntrys
	 */
	@Test
	public void checkResourceExclusiveConflictTest1() {
		CourseEntry s1 = new CourseEntry("软件构造") ;
		Teacher chuanhanxu1 = new Teacher("156", "chuan", true, "教师") ;
		s1.setResource(chuanhanxu1);
		Timeslot t1 = new Timeslot() ;
		Calendar time1 = Calendar.getInstance() ;
		time1.set(2020, 2-1, 13, 8, 00);
		Calendar time2 = Calendar.getInstance() ;
		time2.set(2020, 2-1, 13, 9, 45);
		t1.setdate1(time1);
		t1.setdate2(time2);
		s1.setTime(t1);
		Location loc1 = new Location(11, 22, "正心21", false);
		s1.setLocation(loc1);
		
		
		CourseEntry s2 = new CourseEntry("算法设计") ;
		Teacher chuanhanxu2 = new Teacher("156", "chen", true, "教师") ;
		s2.setResource(chuanhanxu2);
		Timeslot t2 = new Timeslot() ;
		Calendar time11 = Calendar.getInstance() ;
		time11.set(2020, 2-1, 13, 8, 00);
		Calendar time22 = Calendar.getInstance() ;
		time22.set(2020, 2-1, 13, 9, 45);
		t2.setdate1(time11);
		t2.setdate2(time22);
		s2.setTime(t2);
		Location loc2 = new Location(11, 22, "格物21", false);
		s2.setLocation(loc2);	
		List<PlanningEntry> course = new ArrayList<>() ;
		course.add(s1);
		course.add(s2);

		assertTrue(PlanningEntryAPIs.checkResourceExclusiveConflict(course,new checkResourceExclusiveConflict1()));//使用第一种方法来判断不存在资源冲突
		assertTrue(PlanningEntryAPIs.checkResourceExclusiveConflict(course,new checkResourceExclusiveConflict2()));//使用第二种方法来判断不存在资源冲突
		CourseEntry s3 = new CourseEntry("算法设计") ;
		Teacher chuanhanxu3 = new Teacher("156", "chuan", true, "教师") ;
		s3.setResource(chuanhanxu3);
		Timeslot t3 = new Timeslot() ;
		Calendar time111 = Calendar.getInstance() ;
		time111.set(2020, 2-1, 13, 8, 00);
		Calendar time222 = Calendar.getInstance() ;
		time222.set(2020, 2-1, 13, 9, 45);
		t3.setdate1(time111);
		t3.setdate2(time222);
		s3.setTime(t3);
		Location loc3 = new Location(11, 22, "格物21", false);
		s2.setLocation(loc3);	
		
		

		List<PlanningEntry> course1 = new ArrayList<PlanningEntry>() ;
		course1.add(s1);
		course1.add(s3);
		
		assertFalse(PlanningEntryAPIs.checkResourceExclusiveConflict(course1,new checkResourceExclusiveConflict1()));//使用第一种方法来判断存在资源冲突
		assertFalse(PlanningEntryAPIs.checkResourceExclusiveConflict(course1,new checkResourceExclusiveConflict2()));//使用第二种方法来判断存在资源冲突
	}
	
	
	/*
	 * 测试checkResourceExclusiveConflict函数
	 * 覆盖:FlightEntry
	 * 	  	there is Resource Conflict between FlightEntrys
	 *  	there is not Resource Conflict between FlightEntrys
	 */
	@Test
	public void checkResourceExclusiveConflictTest2() {
		FlightEntry f = new FlightEntry("C919") ;
		
		Plane plan1 = new Plane("1s1e", "fadg8", 300, 2.5) ;
		f.setResource(plan1);
		Timeslot t1 = new Timeslot() ;
		Calendar time1 = Calendar.getInstance() ;
		time1.set(2020, 2-1, 13, 14, 30);
		Calendar time2 = Calendar.getInstance() ;
		time2.set(2020, 2-1, 13, 17, 30);
		t1.setdate1(time1);
		t1.setdate2(time2);
		f.setTime(t1);
		Location loc1 = new Location(11, 22, "哈尔滨西", true);
		Location loc2 = new Location(11, 22, "北京南", true);
		f.setLocations(loc1, loc2);
		
		FlightEntry g = new FlightEntry("AH48") ;
		Plane plan2 =  new Plane("1s1e", "fadg8", 300, 2.5) ;
		g.setResource(plan2);
		Timeslot t2 = new Timeslot() ;
		Calendar time11 = Calendar.getInstance() ;
		time11.set(2020, 2-1, 13, 14, 00);
		Calendar time22 = Calendar.getInstance() ;
		time22.set(2020, 2-1, 13, 17, 45);
		t2.setdate1(time11);
		t2.setdate2(time22);
		g.setTime(t2);
		Location loc11 = new Location(11, 22, "南昌", true);
		Location loc22 = new Location(15, 42, "北京南", true);
		g.setLocations(loc11, loc22);
		
		List<PlanningEntry>	flight = new ArrayList<PlanningEntry>() ;
		flight.add(f);
		flight.add(g);
		assertFalse(PlanningEntryAPIs.checkResourceExclusiveConflict(flight,new checkResourceExclusiveConflict1()));//使用第一种方法来判断存在资源冲突
		assertFalse(PlanningEntryAPIs.checkResourceExclusiveConflict(flight,new checkResourceExclusiveConflict2()));//使用第二种方法来判断存在资源冲突
		FlightEntry h = new FlightEntry("AH48") ;
		Plane plan3 =  new Plane("1s1ee", "fadg8", 300, 2.5) ;
		g.setResource(plan3);
		Timeslot t3 = new Timeslot() ;
		Calendar time111 = Calendar.getInstance() ;
		time111.set(2020, 2-1, 13, 14, 00);
		Calendar time222 = Calendar.getInstance() ;
		time222.set(2020, 2-1, 13, 17, 45);
		t3.setdate1(time111);
		t3.setdate2(time222);
		g.setTime(t3);
		Location loc111 = new Location(11, 22, "南昌", true);
		Location loc222 = new Location(15, 42, "北京南", true);
		g.setLocations(loc111, loc222);
		
		List<PlanningEntry>	flight1 = new ArrayList<PlanningEntry>() ;
		flight.add(f);
		flight.add(h);
		assertTrue(PlanningEntryAPIs.checkResourceExclusiveConflict(flight1,new checkResourceExclusiveConflict1()));//使用第一种方法判断不存在资源冲突
		assertTrue(PlanningEntryAPIs.checkResourceExclusiveConflict(flight1,new checkResourceExclusiveConflict2()));//使用第二种方法判断不存在资源冲突
	}
	
	/*
	 * 测试checkResourceExclusiveConflict函数
	 * 覆盖:TrainEntry
	 * 	  	there is Resource Conflict between TrainEntrys
	 *  	there is not Resource Conflict between TrainEntrys
	 */
	@Test
	public void checkResourceExclusiveConflictTest3() throws ParseException {
		Location loc1 = new Location(1.1, 1.2, "哈尔滨西", true);
		Location loc2 = new Location(1.21, 1.23, "南京", true);
		Location loc3 = new Location(2.5, 2.3, "昆明", true);
		List<Location> ss = new ArrayList<Location>() ;
		ss.add(loc1);
		ss.add(loc2);
		ss.add(loc3);
		MultipleLocationEntryImpl locs = new MultipleLocationEntryImpl(ss);	//火车1的一系列位置
		
		Timeslot s1 = new Timeslot() ; 
		Timeslot s2 = new Timeslot() ;
		s1.setDate1("2020-03-21 10:20");
		s1.setDate2("2020-03-21 17:20");
		s2.setDate1("2020-03-22 10:20");
		s2.setDate2("2020-03-22 17:20");
		List<Timeslot> time = new ArrayList<Timeslot>() ;
		time.add(s1);
		time.add(s2);
		BlockableEntryImpl be1 = new BlockableEntryImpl(time);//火车1的一系列时间
		
		Railway r1 = new Railway("aa", Type.BUSINESS, 30, 2019);
		Railway r2 = new Railway("bb", Type.BUSINESS, 30, 2019);
		
		List<Railway> msre1 = new ArrayList<Railway>() ;
		msre1.add(r1);
		msre1.add(r2);
		
		MultipleSortedResourceEntryImpl<Railway> msre2 = new MultipleSortedResourceEntryImpl<Railway>() ;//火车1的车厢
		msre2.setResource(msre1);
	
		TrainEntry train = new TrainEntry("长征一号", locs,be1, msre2);//火车1

		
		
		Location loc11 = new Location(1.1, 1.2, "北京南", true);
		Location loc22 = new Location(1.21, 1.23, "上海", true);	
		Location loc33 = new Location(1.21, 1.23, "南昌", true);	
		List<Location> sss = new ArrayList<Location>() ;
		sss.add(loc11);
		sss.add(loc22);
		sss.add(loc33);
		MultipleLocationEntryImpl locs1 = new MultipleLocationEntryImpl(sss);	//火车2的一系列位置
		
		Railway r11 = new Railway("aa", Type.BUSINESS, 30, 2019);
		Railway r22 = new Railway("cc", Type.FIRSTCLASS, 30, 2019);
		
		List<Railway> msre11 = new ArrayList<Railway>() ;
		msre11.add(r11);
		msre11.add(r22);
		
		MultipleSortedResourceEntryImpl<Railway> msre22 = new MultipleSortedResourceEntryImpl<Railway>() ;//火车2的车厢
		msre22.setResource(msre11);
		
		Timeslot s11 = new Timeslot() ; 
		Timeslot s22 = new Timeslot() ;
		s11.setDate1("2020-03-21 11:20");
		s11.setDate2("2020-03-21 17:20");
		s22.setDate1("2020-03-22 10:20");
		s22.setDate2("2020-03-22 16:20");
		List<Timeslot> time1 = new ArrayList<Timeslot>() ;
		time1.add(s11);
		time1.add(s22);
		BlockableEntryImpl be11 = new BlockableEntryImpl(time1);//火车2的一系列时间
		TrainEntry train1 = new TrainEntry("复兴号", locs1,be11, msre22);//火车2
		
		List<PlanningEntry> s = new ArrayList<PlanningEntry>() ;
		s.add(train1);
		s.add(train);
		assertFalse(PlanningEntryAPIs.checkResourceExclusiveConflict(s,new checkResourceExclusiveConflict1()));//使用第一种方法判断火车1和火车2存在资源使用冲突
		assertFalse(PlanningEntryAPIs.checkResourceExclusiveConflict(s,new checkResourceExclusiveConflict2()));//使用第二种方法判断火车1和火车2存在资源使用冲突
		Timeslot s111 = new Timeslot() ; 
		Timeslot s222 = new Timeslot() ;
		s111.setDate1("2020-04-21 11:20");
		s111.setDate2("2020-04-21 17:20");
		s222.setDate1("2020-04-22 10:20");
		s222.setDate2("2020-04-22 16:20");
		List<Timeslot> time11 = new ArrayList<Timeslot>() ;
		time11.add(s11);
		time11.add(s22);
		
		train.setTimeslots(time11);//改变火车2的时间，让火车1和火车2不存在资源使用冲突
		List<PlanningEntry> t = new ArrayList<PlanningEntry>() ;
		t.add(train1);
		t.add(train);
		assertTrue(PlanningEntryAPIs.checkResourceExclusiveConflict(t,new checkResourceExclusiveConflict1()));//使用第一种方法判断火车1和火车2不存在资源使用冲突
		assertTrue(PlanningEntryAPIs.checkResourceExclusiveConflict(t,new checkResourceExclusiveConflict2()));//使用第二种方法判断火车1和火车2不存在资源使用冲突
	}
	
	/*
	* 测试findPreEntryPerResource函数
	 * 覆盖:TrainEntry
	 * 	  	there is Resource Conflict between TrainEntrys
	 *  	there is not Resource Conflict between TrainEntrys
	 */
	@Test
	public void findPreEntryPerResourceTest1() throws ParseException {
		Location loc1 = new Location(1.1, 1.2, "哈尔滨西", true);
		Location loc2 = new Location(1.21, 1.23, "南京", true);
		Location loc3 = new Location(2.5, 2.3, "昆明", true);
		List<Location> ss = new ArrayList<Location>() ;
		ss.add(loc1);
		ss.add(loc2);
		ss.add(loc3);
		MultipleLocationEntryImpl locs = new MultipleLocationEntryImpl(ss);	//火车1的一系列位置
		
		Timeslot s1 = new Timeslot() ; 
		Timeslot s2 = new Timeslot() ;
		s1.setDate1("2020-03-21 10:20");
		s1.setDate2("2020-03-21 17:20");
		s2.setDate1("2020-03-22 10:20");
		s2.setDate2("2020-03-22 17:20");
		List<Timeslot> time = new ArrayList<Timeslot>() ;
		time.add(s1);
		time.add(s2);
		BlockableEntryImpl be1 = new BlockableEntryImpl(time);//火车1的一系列时间
		
		Railway r1 = new Railway("aa", Type.BUSINESS, 30, 2019);
		Railway r2 = new Railway("bb", Type.BUSINESS, 30, 2019);
		
		List<Railway> msre1 = new ArrayList<Railway>() ;
		msre1.add(r1);
		msre1.add(r2);
		
		MultipleSortedResourceEntryImpl<Railway> msre2 = new MultipleSortedResourceEntryImpl<Railway>() ;//火车1的车厢
		msre2.setResource(msre1);
	
		TrainEntry train = new TrainEntry("长征一号", locs,be1, msre2);//火车1
		
		List<PlanningEntry<Railway>> t = new ArrayList<PlanningEntry<Railway>>() ;
		t.add(train);
		assertTrue(PlanningEntryAPIs.findPreEntryPerResource(r1, train, t) == null ) ;//覆盖前序计划项为null
		
		Location loc11 = new Location(1.1, 1.2, "北京南", true);
		Location loc22 = new Location(1.21, 1.23, "上海", true);	
		Location loc33 = new Location(1.21, 1.23, "南昌", true);	
		List<Location> sss = new ArrayList<Location>() ;
		sss.add(loc11);
		sss.add(loc22);
		sss.add(loc33);
		MultipleLocationEntryImpl locs1 = new MultipleLocationEntryImpl(sss);	//火车2的一系列位置
		
		Railway r22 = new Railway("cc", Type.FIRSTCLASS, 30, 2019);
		
		List<Railway> msre11 = new ArrayList<Railway>() ;
		msre11.add(r1);
		msre11.add(r22);
		
		MultipleSortedResourceEntryImpl<Railway> msre22 = new MultipleSortedResourceEntryImpl<Railway>() ;//火车2的车厢
		msre22.setResource(msre11);
		
		Timeslot s11 = new Timeslot() ; 
		Timeslot s22 = new Timeslot() ;
		s11.setDate1("2020-03-20 11:20");
		s11.setDate2("2020-03-20 17:20");
		s22.setDate1("2020-03-21 8:20");
		s22.setDate2("2020-03-21 9:10");
		List<Timeslot> time1 = new ArrayList<Timeslot>() ;
		time1.add(s11);
		time1.add(s22);
		BlockableEntryImpl be11 = new BlockableEntryImpl(time1);//火车2的一系列时间
		TrainEntry train1 = new TrainEntry("复兴号", locs1,be11, msre22);//火车2
		
		t.add(train1);
		assertTrue(PlanningEntryAPIs.findPreEntryPerResource(r1, train, t) != null ) ;//覆盖前序计划项不为空
		assertTrue(PlanningEntryAPIs.findPreEntryPerResource(r1, train, t).equals(train1) ) ;//覆盖前序计划项不为空
	}
	
	
	/*
	* 测试findPreEntryPerResource函数
	 * 覆盖:FlightEntry
	 * 	  	there is Resource Conflict between TrainEntrys
	 *  	there is not Resource Conflict between TrainEntrys
	 */
	@Test
	public void findPreEntryPerResourceTest2() throws ParseException{
		FlightEntry f = new FlightEntry("C919") ;
		
		Plane plan1 = new Plane("1s1e", "fadg8", 300, 2.5) ;
		f.setResource(plan1);
		Timeslot t1 = new Timeslot() ;
		Calendar time1 = Calendar.getInstance() ;
		time1.set(2020, 2-1, 13, 14, 30);
		Calendar time2 = Calendar.getInstance() ;
		time2.set(2020, 2-1, 13, 17, 30);
		t1.setdate1(time1);
		t1.setdate2(time2);
		f.setTime(t1);
		Location loc1 = new Location(11, 22, "哈尔滨西", true);
		Location loc2 = new Location(11, 22, "北京南", true);
		f.setLocations(loc1, loc2);
		
		List<FlightEntry> fl = new ArrayList<FlightEntry>() ;
		fl.add(f);
		assertTrue(PlanningEntryAPIs.findPreEntryPerResource(plan1,f, fl) == null ) ;//覆盖前序计划项为空
		
		FlightEntry g = new FlightEntry("AH48") ;
		g.setResource(plan1);
		Timeslot t2 = new Timeslot() ;
		Calendar time11 = Calendar.getInstance() ;
		time11.set(2020, 2-1, 12, 14, 00);
		Calendar time22 = Calendar.getInstance() ;
		time22.set(2020, 2-1, 12, 17, 20);
		t2.setdate1(time11);
		t2.setdate2(time22);
		g.setTime(t2);
		Location loc11 = new Location(11, 22, "南昌", true);
		Location loc22 = new Location(15, 42, "北京南", true);
		g.setLocations(loc11, loc22);
		
		fl.add(g);
		assertTrue(PlanningEntryAPIs.findPreEntryPerResource(plan1,f, fl) != null ) ;//覆盖前序计划项不为空
		assertTrue(PlanningEntryAPIs.findPreEntryPerResource(plan1,f, fl).equals(g) ) ;//覆盖前序计划项不为空
		
	}
	/*
	* 测试findPreEntryPerResource函数
	 * 覆盖:CourseEntry
	 * 	  	there is Resource Conflict between TrainEntrys
	 *  	there is not Resource Conflict between TrainEntrys
	 */
	@Test
	public void findPreEntryPerResourceTest3() throws ParseException{
		CourseEntry s1 = new CourseEntry("软件构造") ;
		Teacher chuanhanxu1 = new Teacher("156", "chuan", true, "教师") ;
		s1.setResource(chuanhanxu1);
		Timeslot t1 = new Timeslot() ;
		Calendar time1 = Calendar.getInstance() ;
		time1.set(2020, 2-1, 12, 8, 00);
		Calendar time2 = Calendar.getInstance() ;
		time2.set(2020, 2-1, 12, 9, 45);
		t1.setdate1(time1);
		t1.setdate2(time2);
		s1.setTime(t1);
		Location loc1 = new Location(11, 22, "正心21", false);
		s1.setLocation(loc1);
		List<PlanningEntry<Teacher>> course = new ArrayList<>() ;
		course.add(s1);
		assertTrue(PlanningEntryAPIs.findPreEntryPerResource(chuanhanxu1,s1, course) == null ) ;//覆盖前序计划项为空
		
		CourseEntry s2 = new CourseEntry("算法设计") ;
		s2.setResource(chuanhanxu1);
		Timeslot t2 = new Timeslot() ;
		Calendar time11 = Calendar.getInstance() ;
		time11.set(2020, 2-1, 13, 8, 00);
		Calendar time22 = Calendar.getInstance() ;
		time22.set(2020, 2-1, 13, 9, 45);
		t2.setdate1(time11);
		t2.setdate2(time22);
		s2.setTime(t2);
		Location loc2 = new Location(11, 22, "格物21", false);
		s2.setLocation(loc2);	

		course.add(s2);
		assertTrue(PlanningEntryAPIs.findPreEntryPerResource(chuanhanxu1,s2, course) != null ) ;//覆盖前序计划项不为空
		assertTrue(PlanningEntryAPIs.findPreEntryPerResource(chuanhanxu1,s2, course).equals(s1) ) ;//覆盖前序计划项不为空
		
		CourseEntry s3 = new CourseEntry("计算机系统") ;
		s3.setResource(chuanhanxu1);
		Timeslot t3 = new Timeslot() ;
		Calendar time111 = Calendar.getInstance() ;
		time111.set(2020, 2-1, 13, 7, 00);
		Calendar time222 = Calendar.getInstance() ;
		time222.set(2020, 2-1, 13, 7, 45);
		t3.setdate1(time111);
		t3.setdate2(time222);
		s3.setTime(t3);
		Location loc3 = new Location(11, 22, "格物21", false);
		s3.setLocation(loc3);	
		course.add(s3);
		assertTrue(PlanningEntryAPIs.findPreEntryPerResource(chuanhanxu1,s2, course) != null ) ;//覆盖前序计划项不为空
		assertTrue(PlanningEntryAPIs.findPreEntryPerResource(chuanhanxu1,s2, course).equals(s3) ) ;//覆盖前序计划项不为空，并选择最接近计划项f的那一个计划项e
		
	}
	
	
}
