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
	 * ����BeginPlanningEntry����
	 * ����:the State = WAITING
	 */
	@Test(expected= IllegalArgumentException.class)
	public void BeginWAITINGPlanningEntryTest() throws Exception {
		PlanningEntry<Teacher> plan = emptyInstance() ;
		plan.setName("�������");
		plan.BeginPlanningEntry();//�����ȴ�������Դ�ļƻ���
		assertFalse(plan.getState() instanceof RUNNING);
		assertTrue(plan.getState() instanceof WAITTING);//�ƻ��ǰ״̬Ϊ�ȴ�������Դ��
	}
	/*
	 * ����BeginPlanningEntry����
	 * ����:the State = ALOCATED
	 */
	@Test
	public void BeginALOCATEDPlanningEntryTest() throws Exception {
		PlanningEntry<Teacher> plan = emptyInstance() ;
		plan.setName("�������");
		plan.setState(ALOCATED.instance );
		plan.BeginPlanningEntry();//�����Ѿ�������Դ�ļƻ���
		assertFalse(plan.getState() instanceof ALOCATED);
		assertTrue(plan.getState() instanceof RUNNING);//�ƻ��ǰ״̬Ϊ������
	}
	
	/*
	 * ����BeginPlanningEntry����
	 * ����:the State = RUNNING
	 */
	@Test(expected= IllegalArgumentException.class)
	public void BeginRUNNINGPlanningEntryTest() throws Exception {
		PlanningEntry<Teacher> plan = emptyInstance() ;
		plan.setName("�������");
		plan.setState(RUNNING.instance );
		plan.BeginPlanningEntry();//�����Ѿ������ļƻ���
		assertTrue(plan.getState() instanceof RUNNING);//�ƻ��ǰ״̬Ϊ������
	}
	
	/*
	 * ����BeginPlanningEntry����
	 * ����:the State = ENDED
	 */
	@Test(expected= IllegalArgumentException.class)
	public void BeginENDEDPlanningEntryTest() throws Exception {
		PlanningEntry<Teacher> plan = emptyInstance() ;
		plan.setName("�������");
		plan.setState(ENDED.instance );
		plan.BeginPlanningEntry();//�����Ѿ������ļƻ���
		assertFalse(plan.getState() instanceof RUNNING);
		assertTrue(plan.getState() instanceof ENDED);//�ƻ��ǰ״̬Ϊ������
	}
	
	/*
	 * ����BeginPlanningEntry����
	 * ����:the State = CANCELLED
	 */
	@Test(expected= IllegalArgumentException.class)
	public void BeginCANCELLEDPlanningEntryTest() throws Exception {
		PlanningEntry<Teacher> plan = emptyInstance() ;
		plan.setName("�������");
		plan.setState(CANCELLED.instance );
		plan.BeginPlanningEntry();//�����Ѿ�ȡ���ļƻ���
		assertFalse(plan.getState() instanceof RUNNING);
		assertTrue(plan.getState() instanceof CANCELLED);//�ƻ��ǰ״̬Ϊ������
	}
	
	/*
	 * ����BeginPlanningEntry����
	 * ����:the State = BLOCKED
	 */
	@Test
	public void BeginBLOCKEDPlanningEntryTest() throws Exception {
		PlanningEntry<Teacher> plan = emptyInstance() ;
		plan.setName("�������");
		plan.setState(BLOCKED.instance );
		plan.BeginPlanningEntry();//�����Ѿ������ļƻ���
		assertFalse(plan.getState() instanceof BLOCKED);
		assertTrue(plan.getState() instanceof RUNNING);//�ƻ��ǰ״̬Ϊ������
	}
	
	
	/*
	 * ����CanlcelPlanningEntry����
	 * ����:the State = WAITING
	 */
	@Test
	public void CanlcelWAITINGPlanningEntryTest() throws Exception {
		PlanningEntry<Teacher> plan = emptyInstance() ;
		plan.setName("�������");
		plan.CancelPlanningEntry();//ȡ���ȴ�������Դ�ļƻ���
		assertFalse(plan.getState() instanceof WAITTING);
		assertTrue(plan.getState() instanceof CANCELLED);//�ƻ��ǰ״̬Ϊ�Ѿ�ȡ��
	}
	/*
	 * ����CanlcelPlanningEntry����
	 * ����:the State = ALOCATED
	 */
	@Test(expected= IllegalArgumentException.class)
	public void CanlcelALOCATEDPlanningEntryTest() throws Exception {
		PlanningEntry<Teacher> plan = emptyInstance() ;
		plan.setName("�������");
		plan.setState(ALOCATED.instance );
		plan.CancelPlanningEntry();;//ȡ���Ѿ�������Դ�ļƻ���
		assertFalse(plan.getState() instanceof ALOCATED);
		assertTrue(plan.getState() instanceof CANCELLED);//�ƻ��ǰ״̬Ϊ�Ѿ�ȡ��
	}
	
	/*
	 * ����CanlcelPlanningEntry����
	 * ����:the State = RUNNING
	 */
	@Test(expected= IllegalArgumentException.class)
	public void CanlcelRUNNINGPlanningEntryTest() throws Exception {
		PlanningEntry<Teacher> plan = emptyInstance() ;
		plan.setName("�������");
		plan.setState(RUNNING.instance );
		plan.CancelPlanningEntry();//ȡ���Ѿ������ļƻ���
		assertTrue(plan.getState() instanceof RUNNING);//�ƻ��ǰ״̬Ϊ������
		assertFalse(plan.getState() instanceof CANCELLED);//�ƻ��ǰ״̬Ϊ�Ѿ�ȡ��
	}
	
	/*
	 * ����CanlcelPlanningEntry����
	 * ����:the State = ENDED
	 */
	@Test(expected= IllegalArgumentException.class)
	public void CanlcelENDEDPlanningEntryTest() throws Exception {
		PlanningEntry<Teacher> plan = emptyInstance() ;
		plan.setName("�������");
		plan.setState(ENDED.instance );
		plan.CancelPlanningEntry();//ȡ���Ѿ������ļƻ���
		assertFalse(plan.getState() instanceof CANCELLED);
		assertTrue(plan.getState() instanceof ENDED);//�ƻ��ǰ״̬Ϊ������
	}
	
	/*
	 * ����CanlcelPlanningEntry����
	 * ����:the State = CANCELLED
	 */
	@Test(expected= IllegalArgumentException.class)
	public void CanlcelCANCELLEDPlanningEntryTest() throws Exception {
		PlanningEntry<Teacher> plan = emptyInstance() ;
		plan.setName("�������");
		plan.setState(CANCELLED.instance );
		plan.CancelPlanningEntry();//ȡ���ȴ�������Դ�ļƻ���
		assertTrue(plan.getState() instanceof CANCELLED);//�ƻ��ǰ״̬Ϊȡ����
	}
	
	/*
	 * ����CanlcelPlanningEntry����
	 * ����:the State = BLOCKED
	 */
	@Test
	public void CanlcelBLOCKEDPlanningEntryTest() throws Exception {
		PlanningEntry<Teacher> plan = emptyInstance() ;
		plan.setName("�������");
		plan.setState(BLOCKED.instance );
		plan.CancelPlanningEntry();//ȡ���Ѿ������ļƻ���
		assertFalse(plan.getState() instanceof BLOCKED);
		assertTrue(plan.getState() instanceof CANCELLED);//�ƻ��ǰ״̬Ϊȡ����
	}
	
	/*
	 * ����EndPlanningEntry����
	 * ����:the State = WAITING
	 */
	@Test(expected= IllegalArgumentException.class)
	public void EndWAITINGPlanningEntryTest() throws Exception {
		PlanningEntry<Teacher> plan = emptyInstance() ;
		plan.setName("�������");
		plan.EndPlanningEntry();//��ֹ�ȴ�������Դ�ļƻ���
		assertTrue(plan.getState() instanceof WAITTING);//�ȴ�������Դ�ļƻ������ֹ
	}
	/*
	 * ����EndPlanningEntry����
	 * ����:the State = ALOCATED
	 */
	@Test(expected= IllegalArgumentException.class)
	public void EndALOCATEDPlanningEntryTest() throws Exception {
		PlanningEntry<Teacher> plan = emptyInstance() ;
		plan.setName("�������");
		plan.setState(ALOCATED.instance );
		plan.EndPlanningEntry();//��ֹ�Ѿ�������Դ�ļƻ���
		assertTrue(plan.getState() instanceof ALOCATED);//�ȴ������ļƻ������ֹ

	}
	
	/*
	 * ����EndPlanningEntry����
	 * ����:the State = RUNNING
	 */
	@Test
	public void EndRUNNINGPlanningEntryTest() throws Exception {
		PlanningEntry<Teacher> plan = emptyInstance() ;
		plan.setName("�������");
		plan.setState(RUNNING.instance );
		plan.EndPlanningEntry();//��ֹ�Ѿ������ļƻ���
		assertTrue(plan.getState() instanceof ENDED);//�ƻ��ǰ״̬Ϊ��ֹ��
		assertFalse(plan.getState() instanceof RUNNING);//��ֹ�����ļƻ���
	}
	
	/*
	 * ����EndPlanningEntry����
	 * ����:the State = ENDED
	 */
	@Test(expected= IllegalArgumentException.class)
	public void EndENDEDPlanningEntryTest() throws Exception {
		PlanningEntry<Teacher> plan = emptyInstance() ;
		plan.setName("�������");
		plan.setState(ENDED.instance );
		plan.EndPlanningEntry();//��ֹ�Ѿ������ļƻ���
		assertTrue(plan.getState() instanceof ENDED);//��ֹ״̬�ļƻ������ֹ
	}
	
	/*
	 * ����EndPlanningEntry����
	 * ����:the State = CANCELLED
	 */
	@Test(expected= IllegalArgumentException.class)
	public void EndCANCELLEDPlanningEntryTest() throws Exception {
		PlanningEntry<Teacher> plan = emptyInstance() ;
		plan.setName("�������");
		plan.setState(CANCELLED.instance );
		plan.EndPlanningEntry();//��ֹ�Ѿ�ȡ���ļƻ���
		assertTrue( plan.getState() instanceof ENDED);//ȡ��״̬�ļƻ������ֹ
	}
	
	/*
	 * ����EndPlanningEntry����
	 * ����:the State = BLOCKED
	 */
	@Test(expected= IllegalArgumentException.class)
	public void EndBLOCKEDPlanningEntryTest() throws Exception {
		PlanningEntry<Teacher> plan = emptyInstance() ;
		plan.setName("�������");
		plan.setState(BLOCKED.instance );
		plan.EndPlanningEntry();//��ֹ�Ѿ������ļƻ���
		assertTrue(plan.getState() instanceof BLOCKED);//����״̬�ļƻ������ֹ
	}
	/*
	 * ����getName��setName����
	 */
	@Test
	public void getNameTest() {
		PlanningEntry<Teacher> plan = emptyInstance() ;
		plan.setName("�������");
		assertEquals("�������",plan.getName()) ;
	}
	
	/*
	 * ����setState��getState����
	 */
	@Test
	public void getState() {
		PlanningEntry<Teacher> plan = emptyInstance() ;
		plan.setState(ALOCATED.instance );
		assertTrue(plan.getState() instanceof ALOCATED);
	}
	
}
