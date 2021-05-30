package Resource;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SingleDistinguishResourceEntryImplTest  extends SingleDistinguishResourceEntryTest{
    
	@Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
	@Override
	public SingleDistinguishResourceEntryImpl<Teacher> emptyInstance() {
		return new SingleDistinguishResourceEntryImpl<Teacher>() ;
	}

	@Test
	public void toStringTest() {
		Teacher teacher = new Teacher("362329199806262349", "张瑞豪", true, "本科");
		SingleDistinguishResourceEntryImpl<Teacher> le = emptyInstance() ;
		le.setResource(teacher);
		String s = "Teacher [IdNumber=362329199806262349, Name=张瑞豪, Sex=true, Title=本科]";
		assertEquals(s, teacher.toString());
	}
}
