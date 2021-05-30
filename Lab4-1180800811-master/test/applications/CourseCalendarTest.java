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
	 * Testing for addPlanningEntry����
	 * Partition : there is conflict after adding a PlanningEntry , there is not conflict after adding a PlanningEntry 
	 * 
	 * Testing for cancelPlaningEntry����
	 * Partition : the state of the PlanningEntry is ALOCATED, WAITING,CANCELLED,ENDED,RUNNING
	 *
	 * Testing for FeiPeiResource
	 * output: 0:����ɹ� �� 1 ��ָ���Ŀγ��Ѿ������ʦ �� 2 ���Ҳ���ָ���Ŀγ� �� 3��������Դ��ͻ ,4 :������Ľ�ʦ���ڿ��õĽ�ʦ��
	 *
	 *	Testing for BeginPlanningEntry����
	 *	Partition : the state of the PlanningEntry is ALOCATED, WAITING,CANCELLED,ENDED,RUNNING
	 *
	 *	Testing for changeLocationEntry����
	 *	output: false , true
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
	 *	Testing for EndPlanningEntry����
	 *  Partition��the state of the PlanningEntry is ALOCATED, WAITING,CANCELLED,ENDED,RUNNING
	 *
	 * Testing for addPlanningEntry����
	 * 	Partition:there is conflict after adding a PlanningEntry , there is not conflict after adding a PlanningEntry 
	 * 
	 * Testing for check ����
	 * Partition:there is conflict between PlanningEntrys , there is not conflict between PlanningEntrys , 
	 * 
	 * Testing for getPrePlanningEntry ����
	 * Partition: there is PrePlanningEntry , there is not PrePlanningEntry
	 * 
	 */
	/*
	 * ����addPlanningEntry����
	 * ����:there is conflict after adding a PlanningEntry , there is not conflict after adding a PlanningEntry
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
		assertTrue(c.addPlanningEntry(name, loc, time1, time2));//����there is not conflict after adding a PlanningEntry	
		Calendar time11 =Calendar.getInstance() ;
		time11.set(2020, 5-1, 6, 10, 22);
		Calendar time22 =Calendar.getInstance() ;
		time22.set(2020, 5-1, 6, 11, 42);
		Location loc1 = new Location("zhengx");
		String name1 = "s" ;
		assertFalse(c.addPlanningEntry(name1, loc1, time11, time22));//����there is conflict after adding a PlanningEntry
		
	}
	
	/*
	 * ����CancelPlanningEntry����
	 * ����:the state of the PlanningEntry is ALOCATED, WAITING,CANCELLED,ENDED,RUNNING
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
			assertTrue(c.cancelPlanningEntry(name, time1, time2, loc).equals("�γ��Ѿ�ȡ��"));
		} catch (cancelPlanningEntryException e) {
			assert false ;
		}
		Calendar time3 =Calendar.getInstance() ;
		time3.set(2020, 5-1, 6, 10, 22);
		c.setTime(time3);
		try {
			c.cancelPlanningEntry(name, time1, time2, loc).equals("�γ̵�ǰ״̬ΪCANCELLED,����ȡ��");
		} catch (cancelPlanningEntryException e) {
			assertEquals("ȡ���ƻ����쳣: �ƻ���s��ǰ��״̬������ȡ��",e.getMessage());
		}
	}
	
	/*
	 * ����FeiPeiResource
	 * ���ǣ�0:����ɹ� �� 1 ��ָ���Ŀγ��Ѿ������ʦ �� 2 ���Ҳ���ָ���Ŀγ� �� 3��������Դ��ͻ ,4 :������Ľ�ʦ���ڿ��õĽ�ʦ��
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
		assertTrue(c1.FeiPeiResource("cc", "ss", "sss", true, "��ʦ") ==4 );	//	����:4 :������Ľ�ʦ���ڿ��õĽ�ʦ��
		c1.addResource("ss", "ss", true, "ss");//���ӿ��õĽ�ʦ
		assertTrue(c1.FeiPeiResource("ss", "ss", "ss", true, "ss") ==2 );	//	����2: :�Ҳ���ָ���Ŀγ�
		assertTrue(c1.FeiPeiResource("s", "ss", "ss", true, "ss") ==0 );	//	����0: :����ɹ�
		assertTrue(c1.FeiPeiResource("s", "ss", "ss", true, "ss") ==1 );	//	����1: :ָ���Ŀγ��Ѿ������ʦ
		
		Calendar time11 =Calendar.getInstance() ;
		time11.set(2020, 5-1, 6, 11, 22);
		Calendar time22 =Calendar.getInstance() ;
		time22.set(2020, 5-1, 6, 12, 22);
		Location loc1 = new Location("zheng");
		String name1 = "ss" ;
		c1.addPlanningEntry(name1, loc1, time11, time22);
		try {
				c1.FeiPeiResource("ss", "ss", "ss", true, "ss") ;	//	 ����3��������Դ��ͻ
		}catch(feipeiResourceException e ) {
				assertEquals("�������Դ:Teacher [IdNumber=ss, Name=ss, Sex=true, Title=ss]�󣬼ƻ���֮�������Դ��ռ��ͻ" , e.getMessage());
		}

	}
	
	/*
	 * ����BeginPlanningEntry����
	 * ���ǣ�the state of the PlanningEntry is ALOCATED, WAITING,CANCELLED,ENDED,RUNNING
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
		assertTrue(c.BeginPlanningEntry(name, time1, time2, loc).equals("�γ̵�ǰ״̬ΪWAITTING,��������"));//�γ̵�ǰ״̬ΪWAITTING,��������
		c.addResource("ss", "ss", true, "ss");//���ӿ��õĽ�ʦ
		c.FeiPeiResource(name, "ss", "ss", true, "ss");
		assertTrue(c.BeginPlanningEntry("tt", time1, time2, loc).equals("ָ���Ŀγ̲�����"));//ָ���Ŀγ̲�����
		assertTrue(c.BeginPlanningEntry(name, time1, time2, loc).equals("�γ��Ѿ�����"));//�γ��Ѿ�����
	}
	
	/*
	 * ����changeLocationEntry����
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
		assertFalse(c.changeLocationEntry("ss", loc, new Location("ssss")));//ָ���Ŀγ̲�����
	}
	
	/*
	 * ����addLocation����
	 */
	@Test
	public void addLocationTest() throws Exception {

		CourseCalendar c = new CourseCalendar() ;
		assertTrue(c.addLocation(new Location("ss")));//���λ�óɹ�
		assertFalse(c.addLocation(new Location("ss")));//��ӵ�λ���Ѿ�����
		c.showLocation();
	}
	
	/*
	 * 
	 * ����deletLocation����
	 */
	
	@Test
	public void deleteLocationTest() throws Exception {
		CourseCalendar c = new CourseCalendar() ;
		assertFalse(c.deleteLocation(new Location("ss")));//��ɾ����λ�ò�����
		c.addLocation(new Location("ss"));
		assertTrue(c.deleteLocation(new Location("ss")));//��ɾ����λ�ô���
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
			assertEquals("ɾ��λ���쳣: ������δ�����ļƻ�����λ��:ssִ��" ,e.getMessage());
		}

	}
	
	/*
	 * ����EndPlanningEntry����
	 * ���ǣ�the state of the PlanningEntry is ALOCATED, WAITING,CANCELLED,ENDED,RUNNING
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
		c.addResource("ss", "ss", true, "ss");//���ӿ��õĽ�ʦ
		c.FeiPeiResource(name, "ss", "ss", true, "ss");
		assertTrue(c.BeginPlanningEntry(name, time1, time2, loc).equals("�γ��Ѿ�����"));//�γ��Ѿ�����
		assertTrue(c.EndPlanningEntry("tt", time1, time2, loc).equals("ָ���Ŀγ̲�����"));//ָ���Ŀγ̲�����
		assertTrue(c.EndPlanningEntry(name, time1, time2, loc).equals("�γ��Ѿ�����"));//ָ���Ŀγ̲�����
		assertTrue(c.EndPlanningEntry(name, time1, time2, loc).equals("�γ̵�ǰ״̬ΪENDED,���ܽ���"));
	}
	/*
	 * ����addResource����
	 */
	@Test
	public void addResourceTest() throws Exception {

		CourseCalendar c = new CourseCalendar() ;
		assertTrue(c.addResource("ss", "ss", true, "ss"));//�����Դ�ɹ�
		assertFalse(c.addResource("ss", "ss", true, "ss"));//��ӵ���Դ�Ѿ�����
		c.showResource();
	}
	/*
	 * 
	 * ����deletResource����
	 */
	@Test
	public void deleteResourceTest() throws Exception {
		CourseCalendar c = new CourseCalendar() ;
		Calendar time1 =Calendar.getInstance() ;
		time1.set(2020, 5-1, 6, 11, 22);
		Calendar time2 =Calendar.getInstance() ;
		time2.set(2020, 5-1, 6, 12, 22);
		assertFalse(c.deleteResource("ss", "ss", true, "ss"));//��ɾ������Դ������
		c.addResource("ss", "ss", true, "ss");
		assertTrue(c.deleteResource("ss", "ss", true, "ss"));//��ɾ������Դ�Ѿ�����
		c.addResource("ss", "ss", true, "ss");
		c.addPlanningEntry("ss", new Location("ss"), time1, time2);
		c.FeiPeiResource("ss", "ss", "ss", true, "ss");
		c.BeginPlanningEntry("ss", time1, time2, new Location("ss"));
		try {
				c.deleteResource("ss", "ss", true, "ss");
		}catch( deleteResourceException e) {
			assertEquals("ɾ����Դ�쳣: ����δ�����ļƻ�������ʹ�ø���Դ:Teacher [IdNumber=ss, Name=ss, Sex=true, Title=ss]",e.getMessage());
		}

		
	}
	
	/*
	 * ����check����
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
		c.addResource("ss", "ss", true, "ss");//���ӿ��õĽ�ʦ
		c.FeiPeiResource(name, "ss", "ss", true, "ss");
		assertTrue(c.check());
	}
	
	/*
	 * ����getPrePlanningEntry����
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
		c.addResource("ss", "ss", true, "ss");//���ӿ��õĽ�ʦ
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
