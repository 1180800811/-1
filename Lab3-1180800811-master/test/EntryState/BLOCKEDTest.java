package EntryState;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BLOCKEDTest {
	/*
	 * Testing strategy :
	 * 	ֻ��Ҫ����BLOCKED״̬������״̬��ת����toString������accept��������
	 */
	
	
	@Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
	/*
	 * ����:״̬BLOCKED״̬��������ת����״̬��ת����accept����
	 */
	@Test
	public void Test1() {
		State state= BLOCKED.instance ;
		assertFalse(state.accept());//��ǰ״̬���ǽ���״̬
		state = state.changeState("CANCELLED");//����ALOCATED״̬��RUNNING״̬��ת��
		assertTrue(state instanceof CANCELLED) ;
		state= BLOCKED.instance ;
		state = state.changeState("RUNNING");////����ALOCATED״̬��CANCELLED״̬��ת��
		assertTrue(state instanceof RUNNING) ;
	}
	
	/*
	 * ����:״̬BLOCKED״̬��WAITTING��״̬��ת��
	 */
	@Test(expected= IllegalArgumentException.class)
	public void Test2() {
		State state= BLOCKED.instance ;
		state=state.changeState("WAITTING");//����ALOCATED״̬��WAITTING״̬��ת��	
	}
	
	/*
	 * ����:״̬BLOCKED״̬��ALOCATED��״̬��ת��
	 */
	@Test(expected= IllegalArgumentException.class)
	public void Test3() {
		State state= BLOCKED.instance ;
		state=state.changeState("ALOCATED");//����ALOCATED״̬��WAITTING״̬��ת��	
	}
	
	/*
	 * ����:״̬BLOCKED״̬��ENDED��״̬��ת��
	 */
	@Test(expected= IllegalArgumentException.class)
	public void Test4() {
		State state= BLOCKED.instance ;
		state=state.changeState("ENDED");//����ALOCATED״̬��WAITTING״̬��ת��	
	}
	/*
	 * ����:״̬BLOCKED״̬��BLOCKED��״̬��ת��
	 */
	@Test(expected= IllegalArgumentException.class)
	public void Test5() {
		State state= BLOCKED.instance ;
		state=state.changeState("BLOCKED");//����ALOCATED״̬��WAITTING״̬��ת��	
	}
	
	/*
	 * ����toString����
	 */
	@Test
	public void toStringTest() {
		State state= BLOCKED.instance ;
		String s = "BLOCKED" ;
		assertTrue(s.equals(state.toString()));
	}
}
