package EntryState;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ENDEDTest {
	/*
	 * Testing strategy :
	 * 	只需要测试ENDED状态到其他状态的转换、toString方法和accept方法即可
	 */
	
	
	@Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

	
	/*
	 * 覆盖:状态ENDED状态向ALOCATED的状态的转换
	 */
	@Test(expected= IllegalArgumentException.class)
	public void Test1() {
		State state= ENDED.instance ;
		state=state.changeState("ALOCATED");//覆盖ALOCATED状态向WAITTING状态的转换	
	}
	
	/*
	 * 覆盖:状态ENDED状态向CANCELLED的状态的转换
	 */
	@Test(expected= IllegalArgumentException.class)
	public void Test2() {
		State state= ENDED.instance ;
		state=state.changeState("CANCELLED");//覆盖ALOCATED状态向WAITTING状态的转换	
	}
	
	/*
	 * 覆盖:状态ENDED状态向BLOCKED的状态的转换
	 */
	@Test(expected= IllegalArgumentException.class)
	public void Test3() {
		State state= ENDED.instance ;
		state=state.changeState("BLOCKED");//覆盖ALOCATED状态向WAITTING状态的转换	
	}
	
	/*
	 * 覆盖:状态ENDED状态向RUNNING的状态的转换
	 */
	@Test(expected= IllegalArgumentException.class)
	public void Test4() {
		State state= ENDED.instance ;
		state=state.changeState("RUNNING");//覆盖ALOCATED状态向WAITTING状态的转换	
	}
	/*
	 * 覆盖:状态ENDED状态向ENDED的状态的转换
	 */
	@Test(expected= IllegalArgumentException.class)
	public void Test5() {
		State state= ENDED.instance ;
		state=state.changeState("ENDED");//覆盖ALOCATED状态向WAITTING状态的转换	
	}
	/*
	 * 覆盖:状态ENDED状态向WAITTING的状态的转换
	 */
	@Test(expected= IllegalArgumentException.class)
	public void Test6() {
		State state= ENDED.instance ;
		state=state.changeState("WAITTING");//覆盖ALOCATED状态向WAITTING状态的转换	
	}
	
	/*
	 * 测试toString方法和accept方法
	 */
	@Test
	public void toStringTest() {
		State state= ENDED.instance ;
		String s = "ENDED" ;
		assertTrue(s.equals(state.toString()));
		assertTrue(state.accept())	;
	}
}
