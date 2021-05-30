package EntryState;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ALOCATEDTest {
	/*
	 * Testing strategy :
	 * 	ֻ��Ҫ����ALOCATED״̬������״̬��ת����toString������accept��������
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
		State state= ALOCATED.instance ;
		assertFalse(state.accept());//��ǰ״̬���ǽ���״̬
		state = state.changeState("RUNNING");//����ALOCATED״̬��RUNNING״̬��ת��
		assertTrue(state instanceof RUNNING) ;
		state= ALOCATED.instance ;
		state = state.changeState("CANCLLED");////����ALOCATED״̬��CANCELLED״̬��ת��
		assertTrue(state instanceof CANCELLED) ;		
	}
	
	/*
	 * ����:״̬ALOCATED״̬��WAITTING��״̬��ת��
	 */
	@Test(expected= IllegalArgumentException.class)
	public void Test2() {
		State state= ALOCATED.instance ;
		state.changeState("WAITTING");//����ALOCATED״̬��WAITTING״̬��ת��	
	}
	
	/*
	 * ����:״̬ALOCATED״̬��WAITTING��״̬��ת��
	 */
	@Test(expected= IllegalArgumentException.class)
	public void Test3() {
		State state= ALOCATED.instance ;
		state.changeState("BLOCKED");//����ALOCATED״̬��WAITTING״̬��ת��	
	}
	
	/*
	 * ����:״̬ALOCATED״̬��ALOCATED��״̬��ת��
	 */
	@Test(expected= IllegalArgumentException.class)
	public void Test4() {
		State state= ALOCATED.instance ;
		state.changeState("ALOCATED");//����ALOCATED״̬��WAITTING״̬��ת��	
	}
	/*
	 * ����:״̬ALOCATED״̬��ENDED��״̬��ת��
	 */
	@Test(expected= IllegalArgumentException.class)
	public void Test5() {
		State state= ALOCATED.instance ;
		state.changeState("ENDED");//����ALOCATED״̬��WAITTING״̬��ת��	
	}
	
	/*
	 * ����toString����
	 */
	@Test
	public void toStringTest() {
		State state= ALOCATED.instance ;
		String s = "ALOCATED" ;
		assertTrue(s.equals(state.toString()));
	}
}
