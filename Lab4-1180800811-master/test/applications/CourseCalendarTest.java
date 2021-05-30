package applications;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Test;

import Location.Location;
import MyException.cancelPlanningEntryException;
import MyException.changeLocationException;
import MyException.deleteLocationException;
import MyException.deleteResourceException;
import MyException.feipeiResourceException;


public class CourseCalendarTest {
	/*
	 * Testing for addPlanningEntry方法
	 * Partition : there is conflict after adding a PlanningEntry , there is not conflict after adding a PlanningEntry 
	 * 
	 * Testing for cancelPlaningEntry方法
	 * Partition : the state of the PlanningEntry is ALOCATED, WAITING,CANCELLED,ENDED,RUNNING
	 *
	 * Testing for FeiPeiResource
	 * output: 0:分配成功 ， 1 ：指定的课程已经分配教师 ， 2 ：找不到指定的课程 ， 3：存在资源冲突 ,4 :待分配的教师不在可用的教师内
	 *
	 *	Testing for BeginPlanningEntry方法
	 *	Partition : the state of the PlanningEntry is ALOCATED, WAITING,CANCELLED,ENDED,RUNNING
	 *
	 *	Testing for changeLocationEntry方法
	 *	output: false , true
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
	 *	Testing for EndPlanningEntry方法
	 *  Partition：the state of the PlanningEntry is ALOCATED, WAITING,CANCELLED,ENDED,RUNNING
	 *
	 * Testing for addPlanningEntry方法
	 * 	Partition:there is conflict after adding a PlanningEntry , there is not conflict after adding a PlanningEntry 
	 * 
	 * Testing for check 方法
	 * Partition:there is conflict between PlanningEntrys , there is not conflict between PlanningEntrys , 
	 * 
	 * Testing for getPrePlanningEntry 方法
	 * Partition: there is PrePlanningEntry , there is not PrePlanningEntry
	 * 
	 */
	/*
	 * 测试addPlanningEntry方法
	 * 覆盖:there is conflict after adding a PlanningEntry , there is not conflict after adding a PlanningEntry
	 */
	@Test
	public void addPlanningEntryTest() throws Exception {
		CourseCalendar c = new CourseCalendar() ;
		Calendar time1 =Calendar.getInstance() ;
		time1.set(2020, 5-1, 6, 11, 22);
		Calendar time2 =Calendar.getInstance() ;
		time2.set(2020, 5-1, 6, 12, 22);
		Location loc = new Location("zhengx");
		String name = "s" ;
		assertTrue(c.addPlanningEntry(name, loc, time1, time2));//覆盖there is not conflict after adding a PlanningEntry	
		Calendar time11 =Calendar.getInstance() ;
		time11.set(2020, 5-1, 6, 10, 22);
		Calendar time22 =Calendar.getInstance() ;
		time22.set(2020, 5-1, 6, 11, 42);
		Location loc1 = new Location("zhengx");
		String name1 = "s" ;
		assertFalse(c.addPlanningEntry(name1, loc1, time11, time22));//覆盖there is conflict after adding a PlanningEntry
		
	}
	
	/*
	 * 测试CancelPlanningEntry方法
	 * 覆盖:the state of the PlanningEntry is ALOCATED, WAITING,CANCELLED,ENDED,RUNNING
	 */
	@Test
	public void CancelPlanningEntryTest() {

		Calendar time1 =Calendar.getInstance() ;
		time1.set(2020, 5-1, 6, 11, 22);
		Calendar time2 =Calendar.getInstance() ;
		time2.set(2020, 5-1, 6, 12, 22);
		Location loc = new Location("zhengx");
		String name = "s" ;
		CourseCalendar c = new CourseCalendar() ;
		try {
			c.addPlanningEntry(name, loc, time1, time2);
		} catch (Exception e) {
			assert false ;
		}
		try {
			assertTrue(c.cancelPlanningEntry(name, time1, time2, loc).equals("课程已经取消"));
		} catch (cancelPlanningEntryException e) {
			assert false ;
		}
		Calendar time3 =Calendar.getInstance() ;
		time3.set(2020, 5-1, 6, 10, 22);
		c.setTime(time3);
		try {
			c.cancelPlanningEntry(name, time1, time2, loc).equals("课程当前状态为CANCELLED,不能取消");
		} catch (cancelPlanningEntryException e) {
			assertEquals("取消计划项异常: 计划项s当前的状态不允许取消",e.getMessage());
		}
	}
	
	/*
	 * 测试FeiPeiResource
	 * 覆盖：0:分配成功 ， 1 ：指定的课程已经分配教师 ， 2 ：找不到指定的课程 ， 3：存在资源冲突 ,4 :待分配的教师不在可用的教师内
	 */
	@Test
	public void FeiPeiResourceTest() throws Exception {
		Calendar time1 =Calendar.getInstance() ;
		time1.set(2020, 5-1, 6, 11, 22);
		Calendar time2 =Calendar.getInstance() ;
		time2.set(2020, 5-1, 6, 12, 22);
		Location loc = new Location("zhengx");
		String name = "s" ;
		CourseCalendar c1 = new CourseCalendar() ;
		c1.addPlanningEntry(name, loc, time1, time2);
		assertTrue(c1.FeiPeiResource("cc", "ss", "sss", true, "教师") ==4 );	//	覆盖:4 :待分配的教师不在可用的教师内
		c1.addResource("ss", "ss", true, "ss");//增加可用的教师
		assertTrue(c1.FeiPeiResource("ss", "ss", "ss", true, "ss") ==2 );	//	覆盖2: :找不到指定的课程
		assertTrue(c1.FeiPeiResource("s", "ss", "ss", true, "ss") ==0 );	//	覆盖0: :分配成功
		assertTrue(c1.FeiPeiResource("s", "ss", "ss", true, "ss") ==1 );	//	覆盖1: :指定的课程已经分配教师
		
		Calendar time11 =Calendar.getInstance() ;
		time11.set(2020, 5-1, 6, 11, 22);
		Calendar time22 =Calendar.getInstance() ;
		time22.set(2020, 5-1, 6, 12, 22);
		Location loc1 = new Location("zheng");
		String name1 = "ss" ;
		c1.addPlanningEntry(name1, loc1, time11, time22);
		try {
				c1.FeiPeiResource("ss", "ss", "ss", true, "ss") ;	//	 覆盖3：存在资源冲突
		}catch(feipeiResourceException e ) {
				assertEquals("分配该资源:Teacher [IdNumber=ss, Name=ss, Sex=true, Title=ss]后，计划项之间存在资源独占冲突" , e.getMessage());
		}

	}
	
	/*
	 * 测试BeginPlanningEntry方法
	 * 覆盖：the state of the PlanningEntry is ALOCATED, WAITING,CANCELLED,ENDED,RUNNING
	 */
	@Test
	public void BeginPlanningEntryTest() throws Exception {
		Calendar time1 =Calendar.getInstance() ;
		time1.set(2020, 5-1, 6, 11, 22);
		Calendar time2 =Calendar.getInstance() ;
		time2.set(2020, 5-1, 6, 12, 22);
		Location loc = new Location("zhengx");
		String name = "s" ;
		CourseCalendar c = new CourseCalendar() ;
		c.addPlanningEntry(name, loc, time1, time2);
		assertTrue(c.BeginPlanningEntry(name, time1, time2, loc).equals("课程当前状态为WAITTING,不能启动"));//课程当前状态为WAITTING,不能启动
		c.addResource("ss", "ss", true, "ss");//增加可用的教师
		c.FeiPeiResource(name, "ss", "ss", true, "ss");
		assertTrue(c.BeginPlanningEntry("tt", time1, time2, loc).equals("指定的课程不存在"));//指定的课程不存在
		assertTrue(c.BeginPlanningEntry(name, time1, time2, loc).equals("课程已经启动"));//课程已经启动
	}
	
	/*
	 * 测试changeLocationEntry方法
	 */
	@Test
	public void changeLocationEntryTest() throws Exception{
		Calendar time1 =Calendar.getInstance() ;
		time1.set(2020, 5-1, 6, 11, 22);
		Calendar time2 =Calendar.getInstance() ;
		time2.set(2020, 5-1, 6, 12, 22);
		Location loc = new Location("zhengx");
		String name = "s" ;
		CourseCalendar c = new CourseCalendar() ;
		c.addPlanningEntry(name, loc, time1, time2);
		assertFalse(c.changeLocationEntry("ss", loc, new Location("ssss")));//指定的课程不存在
	}
	
	/*
	 * 测试addLocation方法
	 */
	@Test
	public void addLocationTest() throws Exception {

		CourseCalendar c = new CourseCalendar() ;
		assertTrue(c.addLocation(new Location("ss")));//添加位置成功
		assertFalse(c.addLocation(new Location("ss")));//添加的位置已经存在
		c.showLocation();
	}
	
	/*
	 * 
	 * 测试deletLocation方法
	 */
	
	@Test
	public void deleteLocationTest() throws Exception {
		CourseCalendar c = new CourseCalendar() ;
		assertFalse(c.deleteLocation(new Location("ss")));//待删除的位置不存在
		c.addLocation(new Location("ss"));
		assertTrue(c.deleteLocation(new Location("ss")));//待删除的位置存在
		Calendar time1 =Calendar.getInstance() ;
		time1.set(2020, 5-1, 6, 11, 22);
		Calendar time2 =Calendar.getInstance() ;
		time2.set(2020, 5-1, 6, 12, 22);
		c.addPlanningEntry("s", new Location("ss"), time1, time2);
		c.FeiPeiResource("s", "s", "s", true, "s");
		c.BeginPlanningEntry("s", time1, time2, new Location("ss"));
		try {
			   c.deleteLocation(new Location("ss"));
		}catch(deleteLocationException e) {
			assertEquals("删除位置异常: 有尚且未结束的计划项在位置:ss执行" ,e.getMessage());
		}

	}
	
	/*
	 * 测试EndPlanningEntry方法
	 * 覆盖：the state of the PlanningEntry is ALOCATED, WAITING,CANCELLED,ENDED,RUNNING
	 */
	@Test
	public void EndPlanningEntryTest() throws Exception {
		Calendar time1 =Calendar.getInstance() ;
		time1.set(2020, 5-1, 6, 11, 22);
		Calendar time2 =Calendar.getInstance() ;
		time2.set(2020, 5-1, 6, 12, 22);
		Location loc = new Location("zhengx");
		String name = "s" ;
		CourseCalendar c = new CourseCalendar() ;
		c.addPlanningEntry(name, loc, time1, time2);
		c.addResource("ss", "ss", true, "ss");//增加可用的教师
		c.FeiPeiResource(name, "ss", "ss", true, "ss");
		assertTrue(c.BeginPlanningEntry(name, time1, time2, loc).equals("课程已经启动"));//课程已经启动
		assertTrue(c.EndPlanningEntry("tt", time1, time2, loc).equals("指定的课程不存在"));//指定的课程不存在
		assertTrue(c.EndPlanningEntry(name, time1, time2, loc).equals("课程已经结束"));//指定的课程不存在
		assertTrue(c.EndPlanningEntry(name, time1, time2, loc).equals("课程当前状态为ENDED,不能结束"));
	}
	/*
	 * 测试addResource方法
	 */
	@Test
	public void addResourceTest() throws Exception {

		CourseCalendar c = new CourseCalendar() ;
		assertTrue(c.addResource("ss", "ss", true, "ss"));//添加资源成功
		assertFalse(c.addResource("ss", "ss", true, "ss"));//添加的资源已经存在
		c.showResource();
	}
	/*
	 * 
	 * 测试deletResource方法
	 */
	@Test
	public void deleteResourceTest() throws Exception {
		CourseCalendar c = new CourseCalendar() ;
		Calendar time1 =Calendar.getInstance() ;
		time1.set(2020, 5-1, 6, 11, 22);
		Calendar time2 =Calendar.getInstance() ;
		time2.set(2020, 5-1, 6, 12, 22);
		assertFalse(c.deleteResource("ss", "ss", true, "ss"));//待删除的资源不存在
		c.addResource("ss", "ss", true, "ss");
		assertTrue(c.deleteResource("ss", "ss", true, "ss"));//待删除的资源已经存在
		c.addResource("ss", "ss", true, "ss");
		c.addPlanningEntry("ss", new Location("ss"), time1, time2);
		c.FeiPeiResource("ss", "ss", "ss", true, "ss");
		c.BeginPlanningEntry("ss", time1, time2, new Location("ss"));
		try {
				c.deleteResource("ss", "ss", true, "ss");
		}catch( deleteResourceException e) {
			assertEquals("删除资源异常: 有尚未结束的计划项正在使用该资源:Teacher [IdNumber=ss, Name=ss, Sex=true, Title=ss]",e.getMessage());
		}

		
	}
	
	/*
	 * 测试check方法
	 */
	@Test
	public void checkTest() throws Exception {
		Calendar time1 =Calendar.getInstance() ;
		time1.set(2020, 5-1, 6, 11, 22);
		Calendar time2 =Calendar.getInstance() ;
		time2.set(2020, 5-1, 6, 12, 22);
		Location loc = new Location("zhengx");
		String name = "s" ;
		CourseCalendar c = new CourseCalendar() ;
		c.addPlanningEntry(name, loc, time1, time2);
		c.addResource("ss", "ss", true, "ss");//增加可用的教师
		c.FeiPeiResource(name, "ss", "ss", true, "ss");
		assertTrue(c.check());
	}
	
	/*
	 * 测试getPrePlanningEntry方法
	 */
	@Test
	public void getPrePlanningEntryTest() throws Exception {
		Calendar time1 =Calendar.getInstance() ;
		time1.set(2020, 5-1, 6, 11, 22);
		Calendar time2 =Calendar.getInstance() ;
		time2.set(2020, 5-1, 6, 12, 22);
		Location loc = new Location("zhengx");
		String name = "s" ;
		CourseCalendar c = new CourseCalendar() ;
		c.addPlanningEntry(name, loc, time1, time2);
		c.addResource("ss", "ss", true, "ss");//增加可用的教师
		c.FeiPeiResource(name, "ss", "ss", true, "ss");
		
		assertFalse(c.getPrePlanningEntry(name, loc, "ss", "ss"));
		
		Calendar time11 =Calendar.getInstance() ;
		time11.set(2020, 5-1, 5, 11, 22);
		Calendar time22 =Calendar.getInstance() ;
		time22.set(2020, 5-1, 5, 12, 22);
		Location loc1 = new Location("zhex");
		String name1 = "s" ;
		c.addPlanningEntry(name1, loc1, time11, time22);
		c.FeiPeiResource(name1, "ss", "ss", true, "ss");
		assertTrue(c.getPrePlanningEntry(name, loc, "ss", "ss"));
		c.getResourcePlanningEntry("ss", "ss", true,"ss" , time1);
		c.showFlightEntry(loc1, time11);
		c.board(loc1, time11);
		c.show(loc1, time11);
		c.WatchState(name, time11);
	}
	
}
