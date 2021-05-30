package EntryState;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RUNNINGTest {
	/*
	 * Testing strategy :
	 * 	ֻ��Ҫ����RUNNING״̬������״̬��ת����toString������accept��������
	 */
	
	
	@Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
	/*
	 * ����:״̬RUNNING״̬��������ת����״̬��ת����accept����
	 */
	@Test
	public void Test1() {
		State state= RUNNING.instance ;
		assertFalse(state.accept());//��ǰ״̬���ǽ���״̬
		state = state.changeState("ENDED");//����ALOCATED״̬��RUNNING״̬��ת��
		assertTrue(state instanceof ENDED) ;
		state= RUNNING.instance ;
		state = state.changeState("BLOCKED");////����ALOCATED״̬��CANCELLED״̬��ת��
		assertTrue(state instanceof BLOCKED) ;
	}
	
	/*
	 * ����:״̬RUNNING״̬��WAITTING��״̬��ת��
	 */
	@Test(expected= IllegalArgumentException.class)
	public void Test2() {
		State state= WAITTING.instance ;
		state=state.changeState("WAITTING");//����ALOCATED״̬��WAITTING״̬��ת��	
	}
	
	/*
	 * ����:״̬RUNNING״̬��ALOCATED��״̬��ת��
	 */
	@Test(expected= IllegalArgumentException.class)
	public void Test3() {
		State state= RUNNING.instance ;
		state=state.changeState("ALOCATED");//����ALOCATED״̬��WAITTING״̬��ת��	
	}
	
	/*
	 * ����:״̬RUNNING״̬��RUNNING��״̬��ת��
	 */
	@Test(expected= IllegalArgumentException.class)
	public void Test4() {
		State state= RUNNING.instance ;
		state=state.changeState("RUNNING");//����ALOCATED״̬��WAITTING״̬��ת��	
	}
	/*
	 * ����:״̬RUNNING״̬��CANCLLED��״̬��ת��
	 */
	@Test(expected= IllegalArgumentException.class)
	public void Test5() {
		State state= RUNNING.instance ;
		state=state.changeState("CANCLLED");//����ALOCATED״̬��WAITTING״̬��ת��	
	}
	
	/*
	 * ����toString����
	 */
	@Test
	public void toStringTest() {
		State state= RUNNING.instance ;
		String s = "RUNNING" ;
		assertTrue(s.equals(state.toString()));
	}
}
