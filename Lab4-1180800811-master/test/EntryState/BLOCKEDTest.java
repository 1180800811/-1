package EntryState;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BLOCKEDTest {
	/*
	 * Testing strategy :
	 * 	只需要测试BLOCKED状态到其他状态的转换、toString方法和accept方法即可
	 */
	
	
	@Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
	/*
	 * 覆盖:状态BLOCKED状态向其他可转换的状态的转换和accept方法
	 */
	@Test
	public void Test1() {
		State state= BLOCKED.instance ;
		assertFalse(state.accept());//当前状态不是接收状态
		state = state.changeState("CANCELLED");//覆盖ALOCATED状态向RUNNING状态的转换
		assertTrue(state instanceof CANCELLED) ;
		state= BLOCKED.instance ;
		state = state.changeState("RUNNING");////覆盖ALOCATED状态向CANCELLED状态的转换
		assertTrue(state instanceof RUNNING) ;
	}
	
	/*
	 * 覆盖:状态BLOCKED状态向WAITTING的状态的转换
	 */
	@Test(expected= IllegalArgumentException.class)
	public void Test2() {
		State state= BLOCKED.instance ;
		state=state.changeState("WAITTING");//覆盖ALOCATED状态向WAITTING状态的转换	
	}
	
	/*
	 * 覆盖:状态BLOCKED状态向ALOCATED的状态的转换
	 */
	@Test(expected= IllegalArgumentException.class)
	public void Test3() {
		State state= BLOCKED.instance ;
		state=state.changeState("ALOCATED");//覆盖ALOCATED状态向WAITTING状态的转换	
	}
	
	/*
	 * 覆盖:状态BLOCKED状态向ENDED的状态的转换
	 */
	@Test(expected= IllegalArgumentException.class)
	public void Test4() {
		State state= BLOCKED.instance ;
		state=state.changeState("ENDED");//覆盖ALOCATED状态向WAITTING状态的转换	
	}
	/*
	 * 覆盖:状态BLOCKED状态向BLOCKED的状态的转换
	 */
	@Test(expected= IllegalArgumentException.class)
	public void Test5() {
		State state= BLOCKED.instance ;
		state=state.changeState("BLOCKED");//覆盖ALOCATED状态向WAITTING状态的转换	
	}
	
	/*
	 * 测试toString方法
	 */
	@Test
	public void toStringTest() {
		State state= BLOCKED.instance ;
		String s = "BLOCKED" ;
		assertTrue(s.equals(state.toString()));
	}
}
