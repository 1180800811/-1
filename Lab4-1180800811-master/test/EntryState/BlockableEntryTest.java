package EntryState;

import org.junit.Test;

public abstract class BlockableEntryTest {
	//Testing strategy for Block(Calendar time)
	//time != null 

	public abstract BlockableEntry emptyInstance() ;
	@Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

}
