package flight;

import org.junit.Test;

import MyException.fileChooseException;
import applications.FlightSchedule;

public class flightTest {
	@Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
	
	/**
	 * ���������ĺ������ݵĵ�һ�к������ڲ����ϸ�ʽҪ��yyyy-MM-dd HH:mm
	 * @throws Exception �����ĺ������ݵĵ�һ�к������ڸ�ʽ������
	 */
	@Test(expected = fileChooseException.class)
	public void flightTest1() throws Exception {
		String file = "src/FlightText/flight1" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.getFileFlightEntry(file) ;
	}
	
	/**
	 * ���������ĺ������ݵĵ�һ�к���Ų�����Ҫ��,����Ų�������λ��д��ĸ�� 2-4 λ���ֹ���
	 * @throws Exception �����ĺ������ݵĵ�һ�к���Ų����ϸ�ʽҪ��
	 */
	@Test(expected = fileChooseException.class)
	public void flightTest2() throws Exception {
		String file = "src/FlightText/flight2" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * ���������ĺ������ݵĵ�һ�и�ʽ������Flight:xxxx,xxxx
	 * @throws Exception �����ĺ������ݵĵ�һ�к���Ų����ϸ�ʽҪ��
	 */
	
	@Test(expected = fileChooseException.class)
	public void flightTest3() throws Exception {
		String file = "src/FlightText/flight3" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * ���������ĺ������ݵĵڶ��и�ʽ������Ҫ�󣬲���{
	 * @throws Exception �����ĺ������ݵĵڶ��к���Ų����ϸ�ʽҪ�󣬲���{
	 */
	
	@Test(expected = fileChooseException.class)
	public void flightTest4() throws Exception {
		String file = "src/FlightText/flight4" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * ���������ĺ������ݵĵ����и�ʽ������DepartureAirport:xxxx
	 * @throws Exception �����ĺ������ݵĵ����к���Ų����ϸ�ʽҪ��:DepartureAirport:xxxx
	 */
	
	@Test(expected = fileChooseException.class)
	public void flightTest5() throws Exception {
		String file = "src/FlightText/flight5" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * ���������ĺ������ݵĵ����и�ʽ������ArrivalAirport:xxxx
	 * @throws Exception �����ĺ������ݵĵ����к���Ų����ϸ�ʽҪ��:ArrivalAirport:xxxx
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest6() throws Exception {
		String file = "src/FlightText/flight6" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	

	/**
	 * ���������ĺ������ݵĵ����и�ʽ�к���ĳ���ʱ�䲻����yyyy-MM-dd HH:mm��ʽҪ��
	 * @throws Exception �����ĺ������ݵĵ����к���Ų����ϸ�ʽyyyy-MM-dd HH:mm��ʽҪ��
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest7() throws Exception {
		String file = "src/FlightText/flight7" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * ���������ĺ������ݵĵ����и�ʽ������DepatureTime:xxxx
	 * @throws Exception �����ĺ������ݵĵ��������ݲ����ϸ�ʽҪ��:DepatureTime:xxxx
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest8() throws Exception {
		String file = "src/FlightText/flight8" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * ���������ĺ������ݵĵ����к���ĳ���ʱ�䲻����yyyy-MM-dd HH:mm��ʽҪ��
	 * @throws Exception �������ݵĵ����к���ĳ���ʱ�䲻����yyyy-MM-dd HH:mm��ʽҪ��
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest9() throws Exception {
		String file = "src/FlightText/flight9" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * ���������ĺ������ݵĵ����и�ʽ������ArrivalTime:xxx
	 * @throws Exception �����ĺ������ݵĵ��������ݲ����ϸ�ʽҪ��:ArrivalTime:xx
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest10() throws Exception {
		String file = "src/FlightText/flight10" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * ���������ĺ��������к������ںͺ������ʱ�䲻��ͬһ��
	 * @throws Exception �����ĺ��������к������ںͺ������ʱ�䲻��ͬһ��
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest11() throws Exception {
		String file = "src/FlightText/flight11" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * ���������ĺ��������к���ĳ���ʱ��͵ִ�ʱ������һ��
	 * @throws Exception �����ĺ��������к���ĳ���ʱ��͵ִ�ʱ������һ��
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest12() throws Exception {
		String file = "src/FlightText/flight12" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * ���������ĺ��������е��������ݲ����ϸ�ʽҪ��:Plane:xxx
	 * @throws Exception �����ĺ��������е��������ݲ����ϸ�ʽҪ��:Plane:xxx
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest13() throws Exception {
		String file = "src/FlightText/flight13" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	
	/**
	 * ���������ĺ��������е����������зɻ��ı�Ų����ϸ�ʽҪ��(��һλ��ĸ����N����B)
	 * @throws Exception �����ĺ��������е����������зɻ��ı�Ų����ϸ�ʽҪ��
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest14() throws Exception {
		String file = "src/FlightText/flight14" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * ���������ĺ��������е����������зɻ��ı�Ų����ϸ�ʽҪ��(���漸λ����������)
	 * @throws Exception �����ĺ��������е����������зɻ��ı�Ų����ϸ�ʽҪ��
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest15() throws Exception {
		String file = "src/FlightText/flight15" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * ���������ĺ��������еڰ������ݲ����ϸ�ʽҪ��û��{
	 * @throws Exception �����ĺ��������еڰ������ݲ����ϸ�ʽҪ��û��{
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest16() throws Exception {
		String file = "src/FlightText/flight16" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * ���������ĺ��������еھ������ݲ����ϸ�ʽҪ�󣬲���Type:xxx�ĸ�ʽ
	 * @throws Exception ���������еھ������ݲ����ϸ�ʽҪ�󣬲���Type:xxx�ĸ�ʽ
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest17() throws Exception {
		String file = "src/FlightText/flight17" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * ���������ĺ��������еھ������ݲ����ϸ�ʽҪ�󣬷ɻ����Ͳ�������ĸ���������
	 * @throws Exception ���������еھ������ݲ����ϸ�ʽҪ�󣬲���Type:xxx�ĸ�ʽ
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest18() throws Exception {
		String file = "src/FlightText/flight18" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * ���������ĺ��������е�ʮ�����ݲ����ϸ�ʽҪ�󣬲���Seats:xxx�ĸ�ʽ
	 * @throws Exception ���������е�ʮ�����ݲ����ϸ�ʽҪ�󣬲���Seats:xxx�ĸ�ʽ
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest19() throws Exception {
		String file = "src/FlightText/flight19" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * ���������ĺ��������е�ʮ�����ݲ����ϸ�ʽҪ����λ������[50,600]
	 * @throws Exception ���������е�ʮ�����ݲ����ϸ�ʽҪ����λ������[50,600]
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest20() throws Exception {
		String file = "src/FlightText/flight20" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * ���������ĺ��������е�ʮһ�����ݲ����ϸ�ʽҪ�󣬲���Age:xxx�ĸ�ʽ
	 * @throws Exception ���������е�ʮ�����ݲ����ϸ�ʽҪ�󣬲���Age:xxx�ĸ�ʽ
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest21() throws Exception {
		String file = "src/FlightText/flight21" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * ���������ĺ��������е�ʮ�����ݲ����ϸ�ʽҪ�󣬻��䲻��[0,30]
	 * @throws Exception ���������е�ʮ�����ݲ����ϸ�ʽҪ�󣬻��䲻��[0,30]
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest22() throws Exception {
		String file = "src/FlightText/flight22" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * ���������ĺ��������е�ʮ�����ݲ����ϸ�ʽҪ�󣬻���С��λ������1
	 * @throws Exception ���������е�ʮ�����ݲ����ϸ�ʽҪ�󣬻���С��λ������1
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest23() throws Exception {
		String file = "src/FlightText/flight23" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * ���������ĺ��������е�ʮ�������ݲ����ϸ�ʽҪ��û��{
	 * @throws Exception �����ĺ��������е�ʮ�������ݲ����ϸ�ʽҪ��û��{
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest24() throws Exception {
		String file = "src/FlightText/flight24" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * ���������ĺ��������е�ʮ�������ݲ����ϸ�ʽҪ��û��{
	 * @throws Exception �����ĺ��������е�ʮ�������ݲ����ϸ�ʽҪ��û��{
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest25() throws Exception {
		String file = "src/FlightText/flight25" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * ���������ĺ��������е�һ�����ݲ����ϸ�ʽҪ�󣬺���ų��Ȳ���[4,6]֮��
	 * @throws Exception �����ĺ��������е�һ�����ݲ����ϸ�ʽҪ�󣬺���ų��Ȳ���[4,6]֮��
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest26() throws Exception {
		String file = "src/FlightText/flight26" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * ���������ĺ��������е�һ�����ݲ����ϸ�ʽҪ�󣬺���ų��Ⱥ�λ��������
	 * @throws Exception �����ĺ��������е�һ�����ݲ����ϸ�ʽҪ�󣬺���ų��Ⱥ�λ��������
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest27() throws Exception {
		String file = "src/FlightText/flight27" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * ���������ĺ��������е��������ݲ����ϸ�ʽҪ�󣬷ɻ���ų��Ȳ�Ϊ5
	 * @throws Exception �����ĺ��������е��������ݲ����ϸ�ʽҪ�󣬷ɻ���ų��Ȳ�Ϊ5
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest28() throws Exception {
		String file = "src/FlightText/flight28" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * ���������ĺ��������е�ʮ�����ݲ����ϸ�ʽҪ����λ����������
	 * @throws Exception �����ĺ��������е�ʮ�����ݲ����ϸ�ʽҪ����λ����������
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest29() throws Exception {
		String file = "src/FlightText/flight29" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * ���������ĺ��������е�ʮһ�����ݲ����ϸ�ʽҪ�󣬷ɻ����䲻�Ǹ�����
	 * @throws Exception �����ĺ��������е�ʮһ�����ݲ����ϸ�ʽҪ�󣬷ɻ����䲻�Ǹ�����
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest30() throws Exception {
		String file = "src/FlightText/flight30" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * ���Ժ�������ʱ���Ƿ����ڵִ�ʱ��
	 * @throws Exception ��������ʱ���Ƿ����ڵִ�ʱ��
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest31() throws Exception {
		String file = "src/FlightText/flight31" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * ������ͬ����ĳ���ʱ���Ƿ���ͬ
	 * @throws Exception ��ͬ����ĳ���ʱ�䲻��ͬ
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest32() throws Exception {
		String file = "src/FlightText/flight32" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
		
		
	}
	
	
	/**
	 * ������ͬ����ĵִ�ʱ���Ƿ���ͬ
	 * @throws Exception ��ͬ����ĵִ�ʱ�䲻��ͬ
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest33() throws Exception {
		String file = "src/FlightText/flight33" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * ������ͬ����ĳ��������Ƿ���ͬ
	 * @throws Exception ��ͬ����ĳ���ʱ�䲻��ͬ
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest34() throws Exception {
		String file = "src/FlightText/flight34" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
		
		
	}
	
	
	/**
	 * ������ͬ����ĵִ�����Ƿ���ͬ
	 * @throws Exception ��ͬ����ĵִ�ʱ�䲻��ͬ
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest35() throws Exception {
		String file = "src/FlightText/flight35" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * ���Ժ����ļ�������ȫ��ͬ�ı�ǩ
	 * @throws Exception ���Ժ����ļ�������ȫ��ͬ�ı�ǩ
	 * 
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest36() throws Exception {
		String file = "src/FlightText/flight36" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}

	
	/**
	 * ���Բ�ͬ�ĺ���ʹ�õķɻ������ͬ���Ƿɻ���������ͬ
	 * @throws Exception ��ͬ�ĺ���ʹ�õķɻ������ͬ���Ƿɻ���������ͬ
	 * 
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest37() throws Exception {
		String file = "src/FlightText/flight37" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
}

















