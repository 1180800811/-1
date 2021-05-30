package EntryState;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ENDEDTest {
	/*
	 * Testing strategy :
	 * 	ֻ��Ҫ����ENDED״̬������״̬��ת����toString������accept��������
	 */
	
	
	@Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

	
	/*
	 * ����:״̬ENDED״̬��ALOCATED��״̬��ת��
	 */
	@Test(expected= IllegalArgumentException.class)
	public void Test1() {
		State state= ENDED.instance ;
		state=state.changeState("ALOCATED");//����ALOCATED״̬��WAITTING״̬��ת��	
	}
	
	/*
	 * ����:״̬ENDED״̬��CANCELLED��״̬��ת��
	 */
	@Test(expected= IllegalArgumentException.class)
	public void Test2() {
		State state= ENDED.instance ;
		state=state.changeState("CANCELLED");//����ALOCATED״̬��WAITTING״̬��ת��	
	}
	
	/*
	 * ����:״̬ENDED״̬��BLOCKED��״̬��ת��
	 */
	@Test(expected= IllegalArgumentException.class)
	public void Test3() {
		State state= ENDED.instance ;
		state=state.changeState("BLOCKED");//����ALOCATED״̬��WAITTING״̬��ת��	
	}
	
	/*
	 * ����:״̬ENDED״̬��RUNNING��״̬��ת��
	 */
	@Test(expected= IllegalArgumentException.class)
	public void Test4() {
		State state= ENDED.instance ;
		state=state.changeState("RUNNING");//����ALOCATED״̬��WAITTING״̬��ת��	
	}
	/*
	 * ����:״̬ENDED״̬��ENDED��״̬��ת��
	 */
	@Test(expected= IllegalArgumentException.class)
	public void Test5() {
		State state= ENDED.instance ;
		state=state.changeState("ENDED");//����ALOCATED״̬��WAITTING״̬��ת��	
	}
	/*
	 * ����:״̬ENDED״̬��WAITTING��״̬��ת��
	 */
	@Test(expected= IllegalArgumentException.class)
	public void Test6() {
		State state= ENDED.instance ;
		state=state.changeState("WAITTING");//����ALOCATED״̬��WAITTING״̬��ת��	
	}
	
	/*
	 * ����toString������accept����
	 */
	@Test
	public void toStringTest() {
		State state= ENDED.instance ;
		String s = "ENDED" ;
		assertTrue(s.equals(state.toString()));
		assertTrue(state.accept())	;
	}
}
