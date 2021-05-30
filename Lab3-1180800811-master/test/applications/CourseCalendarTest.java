package applications;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Test;

import EntryState.ALOCATED;
import EntryState.CANCELLED;
import EntryState.UnBlockableEntryImpl;
import EntryState.WAITTING;
import Location.Location;
import Location.SingleLocationEntryImpl;
import PlanningEntry.CourseEntry;
import Resource.SingleDistinguishResourceEntryImpl;
import Resource.Teacher;
import Timeslot.Timeslot;

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
	public void CancelPlanningEntryTest() throws Exception {

		Calendar time1 =Calendar.getInstance() ;
		time1.set(2020, 5-1, 6, 11, 22);
		Calendar time2 =Calendar.getInstance() ;
		time2.set(2020, 5-1, 6, 12, 22);
		Timeslot time = new Timeslot(time1, time2);
		Location loc = new Location("zhengx");
		String name = "s" ;
		CourseCalendar c = new CourseCalendar() ;
		c.addPlanningEntry(name, loc, time1, time2);
		assertTrue(c.cancelPlanningEntry(name, time1, time2, loc).equals("�γ��Ѿ�ȡ��"));
		Calendar time3 =Calendar.getInstance() ;
		time3.set(2020, 5-1, 6, 10, 22);
		c.setTime(time3);
		assertTrue(c.cancelPlanningEntry(name, time1, time2, loc).equals("�γ̵�ǰ״̬ΪCANCELLED,����ȡ��"));
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
		c1.FeiPeiResource("ss", "ss", "ss", true, "ss") ;	//	 ����3��������Դ��ͻ
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
		Timeslot time = new Timeslot(time1, time2);
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
	public void changeLocationEntryTest() throws Exception {
		Calendar time1 =Calendar.getInstance() ;
		time1.set(2020, 5-1, 6, 11, 22);
		Calendar time2 =Calendar.getInstance() ;
		time2.set(2020, 5-1, 6, 12, 22);
		Location loc = new Location("zhengx");
		String name = "s" ;
		CourseCalendar c = new CourseCalendar() ;
		c.addPlanningEntry(name, loc, time1, time2);
		assertFalse(c.changeLocationEntry("ss", loc, new Location("ssss")));//ָ���Ŀγ̲�����
		assertTrue(c.changeLocationEntry(name, loc, new Location("ssss")));//�ı�λ�óɹ�
	}
	
	/*
	 * ����addLocation����
	 */
	@Test
	public void addLocationTest() throws Exception {

		CourseCalendar c = new CourseCalendar() ;
		assertTrue(c.addLocation(new Location("ss")));//���λ�óɹ�
		assertFalse(c.addLocation(new Location("ss")));//��ӵ�λ���Ѿ�����
	}
	
	/*
	 * 
	 * ����deletLocation����
	 */
	@Test
	public void deleteLocationTest() {
		CourseCalendar c = new CourseCalendar() ;
		assertFalse(c.deleteLocation(new Location("ss")));//��ɾ����λ�ò�����
		c.addLocation(new Location("ss"));
		assertTrue(c.deleteLocation(new Location("ss")));//��ɾ����λ�ô���
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
		Timeslot time = new Timeslot(time1, time2);
		Location loc = new Location("zhengx");
		String name = "s" ;
		CourseCalendar c = new CourseCalendar() ;
		c.addPlanningEntry(name, loc, time1, time2);
		c.addResource("ss", "ss", true, "ss");//���ӿ��õĽ�ʦ
		c.FeiPeiResource(name, "ss", "ss", true, "ss");
		assertTrue(c.BeginPlanningEntry(name, time1, time2, loc).equals("�γ��Ѿ�����"));//�γ��Ѿ�����
		assertTrue(c.EndPlanningEntry("tt", time1, time2, loc).equals("ָ���Ŀγ̲�����"));//ָ���Ŀγ̲�����
		assertTrue(c.EndPlanningEntry(name, time1, time2, loc).equals("�γ��Ѿ�����"));//ָ���Ŀγ̲�����
	}
	/*
	 * ����addResource����
	 */
	@Test
	public void addResourceTest() throws Exception {

		CourseCalendar c = new CourseCalendar() ;
		assertTrue(c.addResource("ss", "ss", true, "ss"));//�����Դ�ɹ�
		assertFalse(c.addResource("ss", "ss", true, "ss"));//��ӵ���Դ�Ѿ�����
	}
	/*
	 * 
	 * ����deletResource����
	 */
	@Test
	public void deleteResourceTest() {
		CourseCalendar c = new CourseCalendar() ;
		assertFalse(c.deleteResource("ss", "ss", true, "ss"));//��ɾ������Դ������
		c.addResource("ss", "ss", true, "ss");
		assertTrue(c.deleteResource("ss", "ss", true, "ss"));//��ɾ����λ�ô���
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
		
	}
	
}
