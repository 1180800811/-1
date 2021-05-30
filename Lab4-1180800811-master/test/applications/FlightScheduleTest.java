package applications;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Test;

import Location.Location;
import MyException.cancelPlanningEntryException;
import MyException.deleteLocationException;
import MyException.deleteResourceException;
import MyException.feipeiResourceException;
import PlanningEntry.*;

public class FlightScheduleTest {
	/*
	 * Testing for addPlanningEntry方法
	 * Partition : there is conflict after adding a PlanningEntry , there is not conflict after adding a PlanningEntry 
	 * 
	 * Testing for cancelPlaningEntry方法
	 * Partition : the state of the PlanningEntry is ALOCATED, WAITING,CANCELLED,ENDED,RUNNING
	 * 
	 * Testing for FeiPeiResource
	 * output:0 : 想要分配的飞机不在可用飞机内 , 1 : 想要分配资源的计划项已经分配资源 2 :分配成功 , 3 :分配资源存在冲突 , 4:想要分配飞机的航班不存在
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
	
	@Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
	@Test
	public void addPlanningEntryTest() {
		Calendar time1 = Calendar.getInstance() ;
		time1.set(2020, 2-1, 13, 14, 30);
		Calendar time2 = Calendar.getInstance() ;
		time2.set(2020, 2-1, 13, 17, 30);
		Location loc1 = new Location(11, 22, "哈尔滨西", true);
		Location loc2 = new Location(11, 22, "北京南", true);
		String name = "ss" ;
		FlightSchedule f = new FlightSchedule();
		assertTrue(f.addPlanningEntry(loc1, loc2, time1, time2, name));	// there is not conflict after adding a PlanningEntry 	
	}
	
	/*
	 * 	测试 cancelPlaningEntry方法
	 * 覆盖：the state of the PlanningEntry is ALOCATED, WAITING,CANCELLED,ENDED,RUNNING
	 */
	@Test
	public void cancelPlaningEntryTest() throws cancelPlanningEntryException {
		Calendar time1 = Calendar.getInstance() ;
		time1.set(2020, 2-1, 13, 14, 30);
		Calendar time2 = Calendar.getInstance() ;
		time2.set(2020, 2-1, 13, 17, 30);
		Location loc1 = new Location(11, 22, "哈尔滨西", true);
		Location loc2 = new Location(11, 22, "北京南", true);
		String name = "ss" ;
		FlightSchedule f = new FlightSchedule();
		f.addPlanningEntry(loc1, loc2, time1, time2, name);
		assertTrue(f.cancelPlanningEntry(name, time1, time2).equals("航班已经取消"));
		Calendar time3 =Calendar.getInstance() ;
		time3.set(2020, 5-1, 6, 10, 22);
		f.setTime(time3);
		try {
			f.cancelPlanningEntry(name, time1, time2);
		}catch(cancelPlanningEntryException e ) {
			assertEquals("取消计划项异常: 计划项ss当前的状态不允许取消",e.getMessage());
		}

		
	}
	/*
	 * 测试FeiPeiResource方法
	 * 覆盖:0 : 想要分配的飞机不在可用飞机内 , 1 : 想要分配资源的计划项已经分配资源 2 :分配成功 , 3 :分配资源存在冲突 , 4:想要分配飞机的航班不存在
	 */
	@Test
	public void FeiPeiResourceTest() throws Exception {
		Calendar time1 = Calendar.getInstance() ;
		time1.set(2020, 2-1, 13, 14, 30);
		Calendar time2 = Calendar.getInstance() ;
		time2.set(2020, 2-1, 13, 17, 30);
		Location loc1 = new Location(11, 22, "哈尔滨西", true);
		Location loc2 = new Location(11, 22, "北京南", true);
		String name = "ss" ;
		
		FlightSchedule f = new FlightSchedule();
		f.addPlanningEntry(loc1, loc2, time1, time2, name);
		assertTrue(f.FeiPeiResource("tt", time1, time2, name) == 0 );//覆盖:想要分配的飞机不在可用飞机内
		f.addResource("tt", "ss", 400, 10.0);//增加可用的飞机
		assertTrue(f.FeiPeiResource("tt", time1, time2, "tt") == 4 );//覆盖:想要分配飞机的航班不存在
		assertTrue(f.FeiPeiResource("tt", time1, time2, name) == 2) ;//覆盖:分配成功
		assertTrue(f.FeiPeiResource("tt", time1, time2, name) == 1) ;//覆盖:想要分配资源的计划项已经分配资源
		Calendar time11 = Calendar.getInstance() ;
		time11.set(2020, 2-1, 13, 14, 30);
		Calendar time22 = Calendar.getInstance() ;
		time22.set(2020, 2-1, 13, 17, 30);
		Location loc11 = new Location(11, 22, "哈尔滨西", true);
		Location loc22 = new Location(11, 22, "北京南", true);
		String name1 = "ss" ;
		f.addPlanningEntry(loc11, loc22, time11, time22, name1);
		try {
			f.FeiPeiResource("tt", time11, time22, name1);//覆盖:分配资源存在冲突
		}catch(feipeiResourceException e) {
			System.out.println(e.getMessage());
			assertEquals("分配该资源:PlanningEntry.FlightEntry@6659c656后，计划项之间存在资源独占冲突" ,e.getMessage());
		}

	}
	/*
	 * 测试BeginPlanningEntry方法
	 * 覆盖：the state of the PlanningEntry is ALOCATED, WAITING,CANCELLED,ENDED,RUNNING
	 */
	@Test
	public void BeginPlanningEntryTest() throws Exception {
		Calendar time1 = Calendar.getInstance() ;
		time1.set(2020, 2-1, 13, 14, 30);
		Calendar time2 = Calendar.getInstance() ;
		time2.set(2020, 2-1, 13, 17, 30);
		Location loc1 = new Location(11, 22, "哈尔滨西", true);
		Location loc2 = new Location(11, 22, "北京南", true);
		String name = "ss" ;
		FlightSchedule g = new FlightSchedule();


		assertTrue(g.BeginPlanningEntry(name, time1, time2).equals("指定的航班不存在"));//覆盖:指定的航班不存在
		g.addPlanningEntry(loc1, loc2, time1, time2, name);
		assertTrue(g.BeginPlanningEntry(name, time1, time2).equals("航班当前状态为WAITTING,不能启动"));//覆盖:指定的航班不存在
		g.addResource("tt", "ss", 400, 10.0);//增加可用的飞机
		g.FeiPeiResource("tt", time1, time2, name);
		assertTrue(g.BeginPlanningEntry(name, time1, time2).equals("航班已经启动"));
	}
	
	/*
	 * 测试EndPlanningEntry方法
	 * 覆盖：the state of the PlanningEntry is ALOCATED, WAITING,CANCELLED,ENDED,RUNNING
	 */
	@Test
	public void EndPlanningEntryTest() throws Exception {
		Calendar time1 = Calendar.getInstance() ;
		time1.set(2020, 2-1, 13, 14, 30);
		Calendar time2 = Calendar.getInstance() ;
		time2.set(2020, 2-1, 13, 17, 30);
		Location loc1 = new Location(11, 22, "哈尔滨西", true);
		Location loc2 = new Location(11, 22, "北京南", true);
		String name = "ss" ;
		FlightSchedule g = new FlightSchedule();
		g.addPlanningEntry(loc1, loc2, time1, time2, name);
		g.addResource("tt", "ss", 400, 10.0);//增加可用的飞机
		g.FeiPeiResource("tt", time1, time2, name);
		assertTrue(g.EndPlanningEntry("sss", time1, time2).equals("指定的航班不存在"));
		assertTrue(g.EndPlanningEntry(name, time1, time2).equals("航班当前状态为ALOCATED,不能结束"));
		assertTrue(g.BeginPlanningEntry(name, time1, time2).equals("航班已经启动"));
		assertTrue(g.EndPlanningEntry(name, time1, time2).equals("航班已经结束"));
	}
	/*
	 * 测试addLocation方法
	 */
	@Test
	public void addLocationTest() throws Exception {

		FlightSchedule c = new FlightSchedule() ;
		assertTrue(c.addLocation(new Location("ss")));//添加位置成功
		assertFalse(c.addLocation(new Location("ss")));//添加的位置已经存在
		c.showLocation();
	}
	
	/*
	 * 
	 * 测试deletLocation方法
	 */
	@Test
	public void deleteLocationTest() throws deleteLocationException, feipeiResourceException {
		Calendar time1 = Calendar.getInstance() ;
		time1.set(2020, 2-1, 13, 14, 30);
		Calendar time2 = Calendar.getInstance() ;
		time2.set(2020, 2-1, 13, 17, 30);
		FlightSchedule c = new FlightSchedule() ;
		assertFalse(c.deleteLocation(new Location("ss")));//待删除的位置不存在
		c.addLocation(new Location("ss"));
		assertTrue(c.deleteLocation(new Location("ss")));//待删除的位置存在
		c.addLocation(new Location("ss"));
		c.addPlanningEntry(new Location("ss"), new Location("t"), time1, time2, "s");
		c.addResource("s", "s", 400, 16);
		c.FeiPeiResource("s", time1, time2, "s");
		c.BeginPlanningEntry("s", time1, time2);
		try {
			c.deleteLocation(new Location("ss"));
		}catch(deleteLocationException e) {
			assertEquals("删除位置异常: 有尚且未结束的计划项在位置:ss执行",e.getMessage());
		}

	}
	/*
	 * 测试addResource方法
	 */
	@Test
	public void addResourceTest() throws Exception {

		FlightSchedule c = new FlightSchedule("test/applications/FlightScheduel_2") ;
		assertTrue(c.addResource("ss", "ss", 400, 10.0));//添加资源成功
		assertFalse(c.addResource("ss", "ss", 400, 10.0));//添加的资源已经存在
		c.showResource();
	}
	/*
	 * 
	 * 测试deletResource方法
	 */
	@Test
	public void deleteResourceTest() throws deleteResourceException, feipeiResourceException, deleteLocationException {
		FlightSchedule c = new FlightSchedule();
		assertFalse(c.deleteResource("ss", "ss",400, 10.0));//待删除的资源不存在
		c.addResource("ss", "ss", 400, 10.0);
		assertTrue(c.deleteResource("ss", "ss", 400, 10.0));//待删除的资源存在
		Calendar time1 = Calendar.getInstance() ;
		time1.set(2020, 2-1, 13, 14, 30);
		Calendar time2 = Calendar.getInstance() ;
		time2.set(2020, 2-1, 13, 17, 30);
		c.addLocation(new Location("ss"));
		c.addPlanningEntry(new Location("ss"), new Location("t"), time1, time2, "s");
		c.addResource("s", "s", 400, 16);
		c.FeiPeiResource("s", time1, time2, "s");
		c.BeginPlanningEntry("s", time1, time2);
		try {
			c.deleteResource("s", "s", 400, 16);
		}catch(deleteResourceException e) {
			assertEquals("删除资源异常: 有尚未结束的计划项正在使用该资源:Plane [PlaneNumber=s, MachineNumber=s, Size=400, Age=16.0]",e.getMessage());
		}

	}
	
	
	/*
	 * 测试check方法
	 */
	@Test
	public void checkTest() throws Exception {
		Calendar time1 = Calendar.getInstance() ;
		time1.set(2020, 2-1, 13, 14, 30);
		Calendar time2 = Calendar.getInstance() ;
		time2.set(2020, 2-1, 13, 17, 30);
		Location loc1 = new Location(11, 22, "哈尔滨西", true);
		Location loc2 = new Location(11, 22, "北京南", true);
		String name = "ss" ;
		FlightSchedule g = new FlightSchedule();
		g.addPlanningEntry(loc1, loc2, time1, time2, name);
		g.addResource("tt", "ss", 400, 10.0);//增加可用的飞机
		g.FeiPeiResource("tt", time1, time2, name);
		assertTrue(g.check());
	}
	/*
	 * 测试getPrePlanningEntry方法
	 */
	@Test
	public void getPrePlanningEntryTest() throws Exception {
		Calendar time1 = Calendar.getInstance() ;
		time1.set(2020, 2-1, 13, 14, 30);
		Calendar time2 = Calendar.getInstance() ;
		time2.set(2020, 2-1, 13, 17, 30);
		Location loc1 = new Location(11, 22, "哈尔滨西", true);
		Location loc2 = new Location(11, 22, "北京南", true);
		String name = "ss" ;
		FlightSchedule g = new FlightSchedule();
		g.addPlanningEntry(loc1, loc2, time1, time2, name);
		g.addResource("tt", "ss", 400, 10.0);//增加可用的飞机
		g.FeiPeiResource("tt", time1, time2, name);
		assertFalse(g.getPrePlanningEntry(time1,time2,name,"tt"));
		
		Calendar time11 = Calendar.getInstance() ;
		time11.set(2020, 2-1, 12, 14, 30);
		Calendar time22 = Calendar.getInstance() ;
		time22.set(2020, 2-1, 12, 17, 30);
		Location loc11 = new Location(11, 22, "哈尔滨西", true);
		Location loc22 = new Location(11, 22, "北京南", true);
		String name1 = "ss" ;
		g.addPlanningEntry(loc11, loc22, time11, time22, name1);
		g.FeiPeiResource("tt", time11, time22, name1);
		assertTrue(g.getPrePlanningEntry(time1,time2,name,"tt"));
		g.getResourcePlanningEntry("tt", time11);
		g.showFlightEntry(loc11, time1);
		g.WatchState("ss", time11, time22);
		g.show(loc11, time11);
		g.board(loc11, time11, 0);
	}
}
