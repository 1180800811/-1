package debug;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EventMangerTest {
	//Partition :
	// 前置条件的等价类划分: day < 1 || day > 365 || day >= 1 && day <= 365
	//			start : start < 0 || start >= 24 || start >= 0 && start <= 24 	
	//			end : end <=0 || end > 24 || end > 0 && end <= 24 	
	//			start = end || start > end || start < end
	// 前置条件正确后输入的划分：
	//			k = 1 , k > 1 
	//          all of the day is same , the day is not all same 
	//			there exists two events that have the same start/end and the day  , there don't exist two events that have the same start/end and the day 
	//
	//
	//
	
	/*
	 * 测试前置条件不符合 
	 * 覆盖:day < 1 || day > 365 || day >= 1 && day <= 365 
	 * 		k = 1 
	 * 		all of the day is same
	 * 	there don't exist two events that have the same start/end and the day 
	 */
	@Test
	public void dayWrongTest1() {
		try {
			EventManager.book(-1, 1, 2) ;//覆盖:day < 1
		} catch (Exception e) {
			assertEquals("day的范围应该在[0,365)" , e.getMessage() );
		}
		try {
			EventManager.book(366, 1, 2) ;//覆盖:day > 365
		}catch(Exception e) {
			assertEquals("day的范围应该在[0,365)" , e.getMessage() );
		}
			try {
				assertEquals(1 ,EventManager.book(2, 1, 2));//覆盖 day >= 0 && day <= 365 ,k = 1 ,all of the day is same
			} catch (Exception e) {
				assert false ;
				e.printStackTrace();
			}
	}
	
	/*
	 * 测试前置条件不符合 
	 * 覆盖: start < 0 || start >= 24 || start >= 0 && start <= 24 	
	 * 		k = 1 
	 * 		there don't exist two events that have the same start/end and the day 
	 */
	@Test
	public void startWrongTest() {
		try {
			EventManager.book(2, -1, 1) ;//覆盖: start < 0
		} catch (Exception e) {
			assertEquals("start的范围应该在[0,24)" , e.getMessage() );
		}
		try {
			EventManager.book(2, 24, 1) ;//覆盖:start > 24
		} catch (Exception e) {
			assertEquals("start的范围应该在[0,24)" , e.getMessage() );
		}
		try {
			EventManager.book(2, 30, 1) ;//覆盖:start =24
		} catch (Exception e) {
			assertEquals("start的范围应该在[0,24)" , e.getMessage() );
		}
		try {
			assertEquals(1,EventManager.book(2, 23, 24)) ;//覆盖:start >= 0 && start < 24 ,k = 1 ,all of the day is same
		} catch (Exception e) {
			assert false ;
		}
	}
	
	/*
	 * 测试前置条件不符合 
	 * 覆盖:end <=0 || end > 24 || end > 0 && end <= 24 	,k = 1 ,
	 * 		k = 1 
	 * 		all of the day is same
	 * 		there don't exist two events that have the same start/end and the day 
	 */
	@Test
	public void endWrongTest() {
		try {
			EventManager.book(2, 1, 0) ;//覆盖: end = 0
		} catch (Exception e) {
			assertEquals("end的范围应该在(0,24]" , e.getMessage() );
		}
		try {
			EventManager.book(2, 4, 25) ;//覆盖:end > 24
		} catch (Exception e) {
			assertEquals("end的范围应该在(0,24]" , e.getMessage() );
		}
		try {
			EventManager.book(2, 2, 5) ;//覆盖:end > 0 && end <= 24 	
		} catch (Exception e) {
			assertEquals("end的范围应该在(0,24]" , e.getMessage() );
		}
		try {
			assertEquals(1,EventManager.book(2, 5, 6)) ;//覆盖:end > 0 && end < 24 ,k = 1 ,all of the day is same
		} catch (Exception e) {
			assert false ;
		}
	}
	
	/*
	 * 测试前置条件不符合 
	 * 覆盖:start = end || start > end || start < end 	,all of the day is same
	 * 		k = 1 
	 * 		all of the day is same
	 * 		there don't exist two events that have the same start/end and the day 
	 */
	@Test
	public void startAndendWrongTest() {
		try {
			EventManager.book(2, 1, 1) ;//覆盖: start = end
		} catch (Exception e) {
			assertEquals("start应该小于end" , e.getMessage() );
		}
		try {
			EventManager.book(2, 4, 3) ;//覆盖:start > end
		} catch (Exception e) {
			assertEquals("start应该小于end" , e.getMessage() );
		}
		try {
			assertEquals(1,EventManager.book(2, 7, 8)) ;//覆盖:start < end	,k = 1 ,all of the day is same
		} catch (Exception e) {
			assert false ;
		}
	}
	
	
	/*
	 * 测试book方法
	 * 覆盖: ,k =1 ,k > 1 ,  the day is same ,the day is not all same
	 */
	@Test
	public void RightTest() throws Exception {
		assertEquals(1,EventManager.book(1, 10, 20));	//	k =1 
		assertEquals(1,EventManager.book(1, 1, 7));	   // k =1 
		assertEquals(2,EventManager.book(1, 10, 22));  // k =2 
		assertEquals(3,EventManager.book(1, 5, 15));// k =3 
		assertEquals(4,EventManager.book(1, 5, 12));// k =4 
		assertEquals(4,EventManager.book(1, 7, 10));// k =4 
		assertEquals(4,EventManager.book(2, 7, 10));// 覆盖 ：k =4 ,the day is not all same
	}
}
