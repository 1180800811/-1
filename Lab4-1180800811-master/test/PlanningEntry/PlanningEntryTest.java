package PlanningEntry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import EntryState.*;
import Resource.Teacher;

public abstract class PlanningEntryTest {
	
	//Testing strategy for BeginPlanningEntry()
	//the State : WAITING,ALOCATED , RUNNING , ENDED ,CANCELLED ,BLOCKED
	//
	//Testing strategy for CancelPlanningEntry()
	//the State : WAITING,ALOCATED , RUNNING , ENDED ,CANCELLED ,BLOCKED
	//
	//Testing strategy for EndPlanningEntry()
	//the State : WAITING,ALOCATED , RUNNING , ENDED ,CANCELLED ,BLOCKED
	//
	//Testing strategy for getName()
	//Name == null , Name != null 
	//
	//Testing strategy for getState()
	//the State : WAITING,ALOCATED , RUNNING , ENDED ,CANCELLED ,BLOCKED
	//
	//Testing strategy for setName
	//Name == null , Name != null 
	//
	//Testing strategy for setState()
	//the State : WAITING,ALOCATED , RUNNING , ENDED ,CANCELLED ,BLOCKED
	//
	@Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
	public abstract PlanningEntry<Teacher> emptyInstance() ;
	
	/*
	 * 测试BeginPlanningEntry方法
	 * 覆盖:the State = WAITING
	 */
	@Test(expected= IllegalArgumentException.class)
	public void BeginWAITINGPlanningEntryTest() throws Exception {
		PlanningEntry<Teacher> plan = emptyInstance() ;
		plan.setName("软件构造");
		plan.BeginPlanningEntry();//启动等待分配资源的计划项
		assertFalse(plan.getState() instanceof RUNNING);
		assertTrue(plan.getState() instanceof WAITTING);//计划项当前状态为等待分配资源中
	}
	/*
	 * 测试BeginPlanningEntry方法
	 * 覆盖:the State = ALOCATED
	 */
	@Test
	public void BeginALOCATEDPlanningEntryTest() throws Exception {
		PlanningEntry<Teacher> plan = emptyInstance() ;
		plan.setName("软件构造");
		plan.setState(ALOCATED.instance );
		plan.BeginPlanningEntry();//启动已经分配资源的计划项
		assertFalse(plan.getState() instanceof ALOCATED);
		assertTrue(plan.getState() instanceof RUNNING);//计划项当前状态为启动中
	}
	
	/*
	 * 测试BeginPlanningEntry方法
	 * 覆盖:the State = RUNNING
	 */
	@Test(expected= IllegalArgumentException.class)
	public void BeginRUNNINGPlanningEntryTest() throws Exception {
		PlanningEntry<Teacher> plan = emptyInstance() ;
		plan.setName("软件构造");
		plan.setState(RUNNING.instance );
		plan.BeginPlanningEntry();//启动已经启动的计划项
		assertTrue(plan.getState() instanceof RUNNING);//计划项当前状态为启动中
	}
	
	/*
	 * 测试BeginPlanningEntry方法
	 * 覆盖:the State = ENDED
	 */
	@Test(expected= IllegalArgumentException.class)
	public void BeginENDEDPlanningEntryTest() throws Exception {
		PlanningEntry<Teacher> plan = emptyInstance() ;
		plan.setName("软件构造");
		plan.setState(ENDED.instance );
		plan.BeginPlanningEntry();//启动已经结束的计划项
		assertFalse(plan.getState() instanceof RUNNING);
		assertTrue(plan.getState() instanceof ENDED);//计划项当前状态为结束中
	}
	
	/*
	 * 测试BeginPlanningEntry方法
	 * 覆盖:the State = CANCELLED
	 */
	@Test(expected= IllegalArgumentException.class)
	public void BeginCANCELLEDPlanningEntryTest() throws Exception {
		PlanningEntry<Teacher> plan = emptyInstance() ;
		plan.setName("软件构造");
		plan.setState(CANCELLED.instance );
		plan.BeginPlanningEntry();//启动已经取消的计划项
		assertFalse(plan.getState() instanceof RUNNING);
		assertTrue(plan.getState() instanceof CANCELLED);//计划项当前状态为结束中
	}
	
	/*
	 * 测试BeginPlanningEntry方法
	 * 覆盖:the State = BLOCKED
	 */
	@Test
	public void BeginBLOCKEDPlanningEntryTest() throws Exception {
		PlanningEntry<Teacher> plan = emptyInstance() ;
		plan.setName("软件构造");
		plan.setState(BLOCKED.instance );
		plan.BeginPlanningEntry();//启动已经阻塞的计划项
		assertFalse(plan.getState() instanceof BLOCKED);
		assertTrue(plan.getState() instanceof RUNNING);//计划项当前状态为结束中
	}
	
	
	/*
	 * 测试CanlcelPlanningEntry方法
	 * 覆盖:the State = WAITING
	 */
	@Test
	public void CanlcelWAITINGPlanningEntryTest() throws Exception {
		PlanningEntry<Teacher> plan = emptyInstance() ;
		plan.setName("软件构造");
		plan.CancelPlanningEntry();//取消等待分配资源的计划项
		assertFalse(plan.getState() instanceof WAITTING);
		assertTrue(plan.getState() instanceof CANCELLED);//计划项当前状态为已经取消
	}
	/*
	 * 测试CanlcelPlanningEntry方法
	 * 覆盖:the State = ALOCATED
	 */
	@Test(expected= IllegalArgumentException.class)
	public void CanlcelALOCATEDPlanningEntryTest() throws Exception {
		PlanningEntry<Teacher> plan = emptyInstance() ;
		plan.setName("软件构造");
		plan.setState(ALOCATED.instance );
		plan.CancelPlanningEntry();;//取消已经分配资源的计划项
		assertFalse(plan.getState() instanceof ALOCATED);
		assertTrue(plan.getState() instanceof CANCELLED);//计划项当前状态为已经取消
	}
	
	/*
	 * 测试CanlcelPlanningEntry方法
	 * 覆盖:the State = RUNNING
	 */
	@Test(expected= IllegalArgumentException.class)
	public void CanlcelRUNNINGPlanningEntryTest() throws Exception {
		PlanningEntry<Teacher> plan = emptyInstance() ;
		plan.setName("软件构造");
		plan.setState(RUNNING.instance );
		plan.CancelPlanningEntry();//取消已经启动的计划项
		assertTrue(plan.getState() instanceof RUNNING);//计划项当前状态为启动中
		assertFalse(plan.getState() instanceof CANCELLED);//计划项当前状态为已经取消
	}
	
	/*
	 * 测试CanlcelPlanningEntry方法
	 * 覆盖:the State = ENDED
	 */
	@Test(expected= IllegalArgumentException.class)
	public void CanlcelENDEDPlanningEntryTest() throws Exception {
		PlanningEntry<Teacher> plan = emptyInstance() ;
		plan.setName("软件构造");
		plan.setState(ENDED.instance );
		plan.CancelPlanningEntry();//取消已经结束的计划项
		assertFalse(plan.getState() instanceof CANCELLED);
		assertTrue(plan.getState() instanceof ENDED);//计划项当前状态为结束中
	}
	
	/*
	 * 测试CanlcelPlanningEntry方法
	 * 覆盖:the State = CANCELLED
	 */
	@Test(expected= IllegalArgumentException.class)
	public void CanlcelCANCELLEDPlanningEntryTest() throws Exception {
		PlanningEntry<Teacher> plan = emptyInstance() ;
		plan.setName("软件构造");
		plan.setState(CANCELLED.instance );
		plan.CancelPlanningEntry();//取消等待分配资源的计划项
		assertTrue(plan.getState() instanceof CANCELLED);//计划项当前状态为取消中
	}
	
	/*
	 * 测试CanlcelPlanningEntry方法
	 * 覆盖:the State = BLOCKED
	 */
	@Test
	public void CanlcelBLOCKEDPlanningEntryTest() throws Exception {
		PlanningEntry<Teacher> plan = emptyInstance() ;
		plan.setName("软件构造");
		plan.setState(BLOCKED.instance );
		plan.CancelPlanningEntry();//取消已经阻塞的计划项
		assertFalse(plan.getState() instanceof BLOCKED);
		assertTrue(plan.getState() instanceof CANCELLED);//计划项当前状态为取消中
	}
	
	/*
	 * 测试EndPlanningEntry方法
	 * 覆盖:the State = WAITING
	 */
	@Test(expected= IllegalArgumentException.class)
	public void EndWAITINGPlanningEntryTest() throws Exception {
		PlanningEntry<Teacher> plan = emptyInstance() ;
		plan.setName("软件构造");
		plan.EndPlanningEntry();//终止等待分配资源的计划项
		assertTrue(plan.getState() instanceof WAITTING);//等待分配资源的计划项不可终止
	}
	/*
	 * 测试EndPlanningEntry方法
	 * 覆盖:the State = ALOCATED
	 */
	@Test(expected= IllegalArgumentException.class)
	public void EndALOCATEDPlanningEntryTest() throws Exception {
		PlanningEntry<Teacher> plan = emptyInstance() ;
		plan.setName("软件构造");
		plan.setState(ALOCATED.instance );
		plan.EndPlanningEntry();//终止已经分配资源的计划项
		assertTrue(plan.getState() instanceof ALOCATED);//等待启动的计划项不可终止

	}
	
	/*
	 * 测试EndPlanningEntry方法
	 * 覆盖:the State = RUNNING
	 */
	@Test
	public void EndRUNNINGPlanningEntryTest() throws Exception {
		PlanningEntry<Teacher> plan = emptyInstance() ;
		plan.setName("软件构造");
		plan.setState(RUNNING.instance );
		plan.EndPlanningEntry();//终止已经启动的计划项
		assertTrue(plan.getState() instanceof ENDED);//计划项当前状态为终止中
		assertFalse(plan.getState() instanceof RUNNING);//终止启动的计划项
	}
	
	/*
	 * 测试EndPlanningEntry方法
	 * 覆盖:the State = ENDED
	 */
	@Test(expected= IllegalArgumentException.class)
	public void EndENDEDPlanningEntryTest() throws Exception {
		PlanningEntry<Teacher> plan = emptyInstance() ;
		plan.setName("软件构造");
		plan.setState(ENDED.instance );
		plan.EndPlanningEntry();//终止已经结束的计划项
		assertTrue(plan.getState() instanceof ENDED);//终止状态的计划项不可终止
	}
	
	/*
	 * 测试EndPlanningEntry方法
	 * 覆盖:the State = CANCELLED
	 */
	@Test(expected= IllegalArgumentException.class)
	public void EndCANCELLEDPlanningEntryTest() throws Exception {
		PlanningEntry<Teacher> plan = emptyInstance() ;
		plan.setName("软件构造");
		plan.setState(CANCELLED.instance );
		plan.EndPlanningEntry();//终止已经取消的计划项
		assertTrue( plan.getState() instanceof ENDED);//取消状态的计划项不可终止
	}
	
	/*
	 * 测试EndPlanningEntry方法
	 * 覆盖:the State = BLOCKED
	 */
	@Test(expected= IllegalArgumentException.class)
	public void EndBLOCKEDPlanningEntryTest() throws Exception {
		PlanningEntry<Teacher> plan = emptyInstance() ;
		plan.setName("软件构造");
		plan.setState(BLOCKED.instance );
		plan.EndPlanningEntry();//终止已经阻塞的计划项
		assertTrue(plan.getState() instanceof BLOCKED);//阻塞状态的计划项不可终止
	}
	/*
	 * 测试getName和setName方法
	 */
	@Test
	public void getNameTest() {
		PlanningEntry<Teacher> plan = emptyInstance() ;
		plan.setName("软件构造");
		assertEquals("软件构造",plan.getName()) ;
	}
	
	/*
	 * 测试setState和getState方法
	 */
	@Test
	public void getState() {
		PlanningEntry<Teacher> plan = emptyInstance() ;
		plan.setState(ALOCATED.instance );
		assertTrue(plan.getState() instanceof ALOCATED);
	}
	
}
