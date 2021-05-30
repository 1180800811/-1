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
	 * 测试所读的航班数据的第一行航班日期不符合格式要求yyyy-MM-dd HH:mm
	 * @throws Exception 所读的航班数据的第一行航班日期格式不符合
	 */
	@Test(expected = fileChooseException.class)
	public void flightTest1() throws Exception {
		String file = "src/FlightText/flight1" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.getFileFlightEntry(file) ;
	}
	
	/**
	 * 测试所读的航班数据的第一行航班号不符合要求,航班号不是由两位大写字母和 2-4 位数字构成
	 * @throws Exception 所读的航班数据的第一行航班号不符合格式要求
	 */
	@Test(expected = fileChooseException.class)
	public void flightTest2() throws Exception {
		String file = "src/FlightText/flight2" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * 测试所读的航班数据的第一行格式不符合Flight:xxxx,xxxx
	 * @throws Exception 所读的航班数据的第一行航班号不符合格式要求
	 */
	
	@Test(expected = fileChooseException.class)
	public void flightTest3() throws Exception {
		String file = "src/FlightText/flight3" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * 测试所读的航班数据的第二行格式不符合要求，不是{
	 * @throws Exception 所读的航班数据的第二行航班号不符合格式要求，不是{
	 */
	
	@Test(expected = fileChooseException.class)
	public void flightTest4() throws Exception {
		String file = "src/FlightText/flight4" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * 测试所读的航班数据的第三行格式不符合DepartureAirport:xxxx
	 * @throws Exception 所读的航班数据的第三行航班号不符合格式要求:DepartureAirport:xxxx
	 */
	
	@Test(expected = fileChooseException.class)
	public void flightTest5() throws Exception {
		String file = "src/FlightText/flight5" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * 测试所读的航班数据的第四行格式不符合ArrivalAirport:xxxx
	 * @throws Exception 所读的航班数据的第四行航班号不符合格式要求:ArrivalAirport:xxxx
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest6() throws Exception {
		String file = "src/FlightText/flight6" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	

	/**
	 * 测试所读的航班数据的第五行格式中航班的出发时间不符合yyyy-MM-dd HH:mm格式要求
	 * @throws Exception 所读的航班数据的第五行航班号不符合格式yyyy-MM-dd HH:mm格式要求
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest7() throws Exception {
		String file = "src/FlightText/flight7" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * 测试所读的航班数据的第五行格式不符合DepatureTime:xxxx
	 * @throws Exception 所读的航班数据的第五行数据不符合格式要求:DepatureTime:xxxx
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest8() throws Exception {
		String file = "src/FlightText/flight8" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * 测试所读的航班数据的第六行航班的出发时间不符合yyyy-MM-dd HH:mm格式要求
	 * @throws Exception 航班数据的第六行航班的出发时间不符合yyyy-MM-dd HH:mm格式要求
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest9() throws Exception {
		String file = "src/FlightText/flight9" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * 测试所读的航班数据的第六行格式不符合ArrivalTime:xxx
	 * @throws Exception 所读的航班数据的第五六数据不符合格式要求:ArrivalTime:xx
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest10() throws Exception {
		String file = "src/FlightText/flight10" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * 测试所读的航班数据中航班日期和航班出发时间不是同一天
	 * @throws Exception 所读的航班数据中航班日期和航班出发时间不是同一天
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest11() throws Exception {
		String file = "src/FlightText/flight11" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * 测试所读的航班数据中航班的出发时间和抵达时间相差超过一天
	 * @throws Exception 所读的航班数据中航班的出发时间和抵达时间相差超过一天
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest12() throws Exception {
		String file = "src/FlightText/flight12" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * 测试所读的航班数据中第七行数据不符合格式要求:Plane:xxx
	 * @throws Exception 所读的航班数据中第七行数据不符合格式要求:Plane:xxx
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest13() throws Exception {
		String file = "src/FlightText/flight13" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	
	/**
	 * 测试所读的航班数据中第七行数据中飞机的编号不符合格式要求(第一位字母不是N或者B)
	 * @throws Exception 所读的航班数据中第七行数据中飞机的编号不符合格式要求
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest14() throws Exception {
		String file = "src/FlightText/flight14" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * 测试所读的航班数据中第七行数据中飞机的编号不符合格式要求(后面几位不都是数字)
	 * @throws Exception 所读的航班数据中第七行数据中飞机的编号不符合格式要求
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest15() throws Exception {
		String file = "src/FlightText/flight15" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * 测试所读的航班数据中第八行数据不符合格式要求，没有{
	 * @throws Exception 所读的航班数据中第八行数据不符合格式要求，没有{
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest16() throws Exception {
		String file = "src/FlightText/flight16" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * 测试所读的航班数据中第九行数据不符合格式要求，不是Type:xxx的格式
	 * @throws Exception 航班数据中第九行数据不符合格式要求，不是Type:xxx的格式
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest17() throws Exception {
		String file = "src/FlightText/flight17" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * 测试所读的航班数据中第九行数据不符合格式要求，飞机类型不是由字母和数字组成
	 * @throws Exception 航班数据中第九行数据不符合格式要求，不是Type:xxx的格式
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest18() throws Exception {
		String file = "src/FlightText/flight18" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * 测试所读的航班数据中第十行数据不符合格式要求，不是Seats:xxx的格式
	 * @throws Exception 航班数据中第十行数据不符合格式要求，不是Seats:xxx的格式
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest19() throws Exception {
		String file = "src/FlightText/flight19" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * 测试所读的航班数据中第十行数据不符合格式要求，座位数不在[50,600]
	 * @throws Exception 航班数据中第十行数据不符合格式要求，座位数不在[50,600]
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest20() throws Exception {
		String file = "src/FlightText/flight20" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * 测试所读的航班数据中第十一行数据不符合格式要求，不是Age:xxx的格式
	 * @throws Exception 航班数据中第十行数据不符合格式要求，不是Age:xxx的格式
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest21() throws Exception {
		String file = "src/FlightText/flight21" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * 测试所读的航班数据中第十行数据不符合格式要求，机龄不在[0,30]
	 * @throws Exception 航班数据中第十行数据不符合格式要求，机龄不在[0,30]
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest22() throws Exception {
		String file = "src/FlightText/flight22" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * 测试所读的航班数据中第十行数据不符合格式要求，机龄小数位数超过1
	 * @throws Exception 航班数据中第十行数据不符合格式要求，机龄小数位数超过1
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest23() throws Exception {
		String file = "src/FlightText/flight23" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * 测试所读的航班数据中第十二行数据不符合格式要求，没有{
	 * @throws Exception 所读的航班数据中第十二行数据不符合格式要求，没有{
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest24() throws Exception {
		String file = "src/FlightText/flight24" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * 测试所读的航班数据中第十三行数据不符合格式要求，没有{
	 * @throws Exception 所读的航班数据中第十三行数据不符合格式要求，没有{
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest25() throws Exception {
		String file = "src/FlightText/flight25" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * 测试所读的航班数据中第一行数据不符合格式要求，航班号长度不在[4,6]之间
	 * @throws Exception 所读的航班数据中第一行数据不符合格式要求，航班号长度不在[4,6]之间
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest26() throws Exception {
		String file = "src/FlightText/flight26" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * 测试所读的航班数据中第一行数据不符合格式要求，航班号长度后几位不是数字
	 * @throws Exception 所读的航班数据中第一行数据不符合格式要求，航班号长度后几位不是数字
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest27() throws Exception {
		String file = "src/FlightText/flight27" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * 测试所读的航班数据中第七行数据不符合格式要求，飞机编号长度不为5
	 * @throws Exception 所读的航班数据中第七行数据不符合格式要求，飞机编号长度不为5
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest28() throws Exception {
		String file = "src/FlightText/flight28" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * 测试所读的航班数据中第十行数据不符合格式要求，座位数不是整数
	 * @throws Exception 所读的航班数据中第十行数据不符合格式要求，座位数不是整数
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest29() throws Exception {
		String file = "src/FlightText/flight29" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * 测试所读的航班数据中第十一行数据不符合格式要求，飞机机龄不是浮点数
	 * @throws Exception 所读的航班数据中第十一行数据不符合格式要求，飞机机龄不是浮点数
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest30() throws Exception {
		String file = "src/FlightText/flight30" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * 测试航班的起飞时间是否早于抵达时间
	 * @throws Exception 航班的起飞时间是否晚于抵达时间
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest31() throws Exception {
		String file = "src/FlightText/flight31" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * 测试相同航班的出发时间是否相同
	 * @throws Exception 相同航班的出发时间不相同
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest32() throws Exception {
		String file = "src/FlightText/flight32" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
		
		
	}
	
	
	/**
	 * 测试相同航班的抵达时间是否相同
	 * @throws Exception 相同航班的抵达时间不相同
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest33() throws Exception {
		String file = "src/FlightText/flight33" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * 测试相同航班的出发机场是否相同
	 * @throws Exception 相同航班的出发时间不相同
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest34() throws Exception {
		String file = "src/FlightText/flight34" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
		
		
	}
	
	
	/**
	 * 测试相同航班的抵达机场是否相同
	 * @throws Exception 相同航班的抵达时间不相同
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest35() throws Exception {
		String file = "src/FlightText/flight35" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
	
	/**
	 * 测试航班文件存在完全相同的标签
	 * @throws Exception 测试航班文件存在完全相同的标签
	 * 
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest36() throws Exception {
		String file = "src/FlightText/flight36" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}

	
	/**
	 * 测试不同的航班使用的飞机编号相同但是飞机参数不相同
	 * @throws Exception 不同的航班使用的飞机编号相同但是飞机参数不相同
	 * 
	 */

	@Test(expected = fileChooseException.class)
	public void flightTest37() throws Exception {
		String file = "src/FlightText/flight37" ;
		FlightSchedule flight = new FlightSchedule();	
		flight.ReadFileCreatePlanningEntry(file);
	}
}

















