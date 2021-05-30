package EntryState;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class WAITTINGTest {
	/*
	 * Testing strategy :
	 * 	ֻ��Ҫ����WAITTING״̬������״̬��ת����toString������accept��������
	 */
	
	
	@Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
	/*
	 * ����:״̬ALOCATED״̬��������ת����״̬��ת����accept����
	 */
	@Test
	public void Test1() {
		State state= WAITTING.instance ;
		assertFalse(state.accept());//��ǰ״̬���ǽ���״̬
		state = state.changeState("ALOCATED");//����ALOCATED״̬��RUNNING״̬��ת��
		assertTrue(state instanceof ALOCATED) ;
		state= ALOCATED.instance ;
		state = state.changeState("CANCLLED");////����ALOCATED״̬��CANCELLED״̬��ת��
		assertTrue(state instanceof CANCELLED) ;
	}
	
	/*
	 * ����:״̬WAITTING״̬��WAITTING��״̬��ת��
	 */
	@Test(expected= IllegalArgumentException.class)
	public void Test2() {
		State state= WAITTING.instance ;
		state=state.changeState("WAITTING");//����ALOCATED״̬��WAITTING״̬��ת��	
	}
	
	/*
	 * ����:״̬WAITTING״̬��BLOCKED��״̬��ת��
	 */
	@Test(expected= IllegalArgumentException.class)
	public void Test3() {
		State state= WAITTING.instance ;
		state=state.changeState("BLOCKED");//����ALOCATED״̬��WAITTING״̬��ת��	
	}
	
	/*
	 * ����:״̬WAITTING״̬��RUNNING��״̬��ת��
	 */
	@Test(expected= IllegalArgumentException.class)
	public void Test4() {
		State state= WAITTING.instance ;
		state=state.changeState("RUNNING");//����ALOCATED״̬��WAITTING״̬��ת��	
	}
	/*
	 * ����:״̬WAITTING״̬��ENDED��״̬��ת��
	 */
	@Test(expected= IllegalArgumentException.class)
	public void Test5() {
		State state= WAITTING.instance ;
		state=state.changeState("ENDED");//����ALOCATED״̬��WAITTING״̬��ת��	
	}
	
	/*
	 * ����toString����
	 */
	@Test
	public void toStringTest() {
		State state= WAITTING.instance ;
		String s = "WAITTING" ;
		assertTrue(s.equals(state.toString()));
	}
}
