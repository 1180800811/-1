package debug;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

public class FlightClientTest {
	// Partition for planeAllocation����
	//		�������� > �ɻ����� �� �������� = �ɻ����� �� �������� < �ɻ�����
	//		����ʱ����ڽ��� ������ʱ�䲻���ڽ���
	//		���ڳ�ͻ �� �����ڳ�ͻ
	//      �����쳣:�������ʱ�����ڵ���ʱ��
	/*
	 * ����planeAllocation
	 * ����:����ʱ�䲻���ڽ��� 
	 * 		�����ڳ�ͻ
	 * 		�������� > �ɻ�����
	 */
	@Test
	public void planeAllocationTest1() throws Exception {
		Flight f1 = new Flight() ;
		Calendar time1 = Calendar.getInstance() ;
		time1.set(2020, 1-1, 1, 1, 1);//2020-01-01
		Calendar time2 = Calendar.getInstance() ;
		time2.set(2020, 1-1, 2, 1, 1);//2020-01-02
		f1.setDepartTime(time1);
		f1.setArrivalTime(time2);
		Plane p1 = new Plane() ;
		p1.setPlaneNo("ss");//�ɻ�ss
		List<Plane> l1 = new ArrayList<Plane>() ;
		List<Flight> f = new ArrayList<Flight>() ;
		l1.add(p1);
		f.add(f1);
		Flight f2 = new Flight() ;
		Calendar time3 = Calendar.getInstance() ;
		time3.set(2020, 3-1, 1,3, 1);//2020-03-01
		Calendar time4 = Calendar.getInstance() ;
		time4.set(2020, 3-1, 3, 1, 1);//2020-03-03
		f2.setDepartTime(time3);
		f2.setArrivalTime(time4);
		f.add(f2);
		assertEquals(true ,new FlightClient().planeAllocation(l1, f));//��������Ϊ2 �� �ɻ�����Ϊ1 ������֮��ʱ�䲻���ڽ���,�����ڳ�ͻ
	}
	
	/*
	 * ����planeAllocation
	 * ����:����ʱ����ڽ��� 
	 * 		���ڳ�ͻ
	 * 		�������� > �ɻ�����
	 */
	@Test
	public void planeAllocationTest2() throws Exception {
		Flight f1 = new Flight() ;
		Calendar time1 = Calendar.getInstance() ;
		time1.set(2020, 1-1, 1, 1, 1);//2020-01-01 01:01
		Calendar time2 = Calendar.getInstance() ;
		time2.set(2020, 1-1, 2, 1, 1);//2020-01-02 01:01
		f1.setDepartTime(time1);
		f1.setArrivalTime(time2);
		Plane p1 = new Plane() ;
		p1.setPlaneNo("ss");//�ɻ�ss
		Plane p2 = new Plane() ;
		p2.setPlaneNo("sss");//�ɻ�ss
		List<Plane> l1 = new ArrayList<Plane>() ;
		List<Flight> f = new ArrayList<Flight>() ;
		l1.add(p1);
		l1.add(p2);
		f.add(f1);
		Flight f2 = new Flight() ;
		Calendar time3 = Calendar.getInstance() ;
		time3.set(2020, 1-1, 1,2, 1);//2020-01-01 02:01
		Calendar time4 = Calendar.getInstance() ;
		time4.set(2020, 1-1, 3, 1, 1);//2020-01-03 01:01
		f2.setDepartTime(time3);
		f2.setArrivalTime(time4);
		f.add(f2);
		Flight f3 = new Flight() ;
		Calendar time5 = Calendar.getInstance() ;
		time5.set(2020, 1-1, 1,3, 1);//2020-01-01 03:01
		Calendar time6 = Calendar.getInstance() ;
		time6.set(2020, 4-1, 3, 1, 1);//2020-04-03 01:01
		f3.setDepartTime(time5);
		f3.setArrivalTime(time6);
		f.add(f3);
		Flight f4 = new Flight() ;
		Calendar time7 = Calendar.getInstance() ;
		time7.set(2020, 4-1, 2, 3, 1);//2020-04-02 03:01
		Calendar time8 = Calendar.getInstance() ;
		time8.set(2020, 4-1, 4, 1, 1);//2020-04-04 01:01
		f4.setDepartTime(time7);
		f4.setArrivalTime(time8);
		f.add(f4);
		assertEquals(false ,new FlightClient().planeAllocation(l1, f));//��������Ϊ4 �� �ɻ�����Ϊ2 ������֮��ʱ����ڽ���,���ڳ�ͻ
	}
	/*
	 * ����planeAllocation
	 * ����:����ʱ����ڽ��� 
	 * 		�����ڳ�ͻ
	 * 		�������� = �ɻ�����
	 */
	@Test
	public void planeAllocationTest3() throws Exception {
		Flight f1 = new Flight() ;
		Calendar time1 = Calendar.getInstance() ;
		time1.set(2020, 1-1, 1, 1, 1);//2020-01-01 01:01
		Calendar time2 = Calendar.getInstance() ;
		time2.set(2020, 1-1, 2, 1, 1);//2020-01-02 01:01
		f1.setDepartTime(time1);
		f1.setArrivalTime(time2);
		Plane p1 = new Plane() ;
		p1.setPlaneNo("ss");//�ɻ�1ss
		Plane p2 = new Plane() ;
		p2.setPlaneNo("sss");//�ɻ�1ss
		List<Plane> l1 = new ArrayList<Plane>() ;
		List<Flight> f = new ArrayList<Flight>() ;
		l1.add(p1);
		l1.add(p2);
		f.add(f1);
		Flight f2 = new Flight() ;
		Calendar time3 = Calendar.getInstance() ;
		time3.set(2020, 1-1, 1,3, 1);//2020-01-01 03:01
		Calendar time4 = Calendar.getInstance() ;
		time4.set(2020, 3-1, 3, 1, 1);//2020-03-03 01:01
		f2.setDepartTime(time3);
		f2.setArrivalTime(time4);
		f.add(f2);
		assertEquals(true,new FlightClient().planeAllocation(l1, f));//��������Ϊ2 �� �ɻ�����Ϊ2 ������֮��ʱ����ڽ���,�����ڳ�ͻ
	}
	
	/*
	 * ����planeAllocation
	 * ����:����ʱ����ڽ��� 
	 * 		�����ڳ�ͻ
	 * 		�������� < �ɻ�����
	 */
	@Test
	public void planeAllocationTest4() throws Exception {
		Flight f1 = new Flight() ;
		Calendar time1 = Calendar.getInstance() ;
		time1.set(2020, 1-1, 1, 1, 1);//2020-01-01 01:01
		Calendar time2 = Calendar.getInstance() ;
		time2.set(2020, 1-1, 2, 1, 1);//2020-01-02 01:01
		f1.setDepartTime(time1);
		f1.setArrivalTime(time2);
		Plane p1 = new Plane() ;
		p1.setPlaneNo("ss");//�ɻ�1ss
		Plane p2 = new Plane() ;
		p2.setPlaneNo("sss");//�ɻ�2ss
		Plane p3 = new Plane() ;
		p3.setPlaneNo("ssss");//�ɻ�31ss
		List<Plane> l1 = new ArrayList<Plane>() ;
		List<Flight> f = new ArrayList<Flight>() ;
		l1.add(p1);
		l1.add(p2);
		l1.add(p3);
		f.add(f1);
		Flight f2 = new Flight() ;
		Calendar time3 = Calendar.getInstance() ;
		time3.set(2020, 1-1, 1,3, 1);//2020-01-01 03:01
		Calendar time4 = Calendar.getInstance() ;
		time4.set(2020, 3-1, 3, 1, 1);//2020-03-03 01:01
		f2.setDepartTime(time3);
		f2.setArrivalTime(time4);
		f.add(f2);
		assertEquals(true,new FlightClient().planeAllocation(l1, f));//��������Ϊ2 �� �ɻ�����Ϊ3 ������֮��ʱ����ڽ���,�����ڳ�ͻ
	}
	
	/*
	 * �����쳣:����ĳ���ʱ�����ڵ���ʱ��
	 */
	@Test
	public void planeAllocationTest5() {
		Flight f1 = new Flight() ;
		Calendar time1 = Calendar.getInstance() ;
		time1.set(2020, 1-1, 1, 1, 1);//2020-01-01 01:01
		Calendar time2 = Calendar.getInstance() ;
		time2.set(2020, 1-1, 2, 1, 1);//2020-01-02 01:01
		f1.setDepartTime(time1);
		f1.setArrivalTime(time2);
		Plane p1 = new Plane() ;
		p1.setPlaneNo("ss");//�ɻ�1ss
		List<Plane> l1 = new ArrayList<Plane>() ;
		List<Flight> f = new ArrayList<Flight>() ;
		l1.add(p1);
		f.add(f1);
		try {
			new FlightClient().planeAllocation(l1, f);
		} catch (Exception e) {
			assertEquals("�������ʱ��Ӧ�����ڵ���ʱ��" ,e.getMessage());
		}
	}
}
