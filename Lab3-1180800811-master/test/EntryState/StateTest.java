package EntryState;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StateTest {
	/*
	 * Testing strategy :
	 * 	ֻ��Ҫ����ÿ��״̬�ൽ����״̬��ת������
	 */
	@Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
	
	

}
