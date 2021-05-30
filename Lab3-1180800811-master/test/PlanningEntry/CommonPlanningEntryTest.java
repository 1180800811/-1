package PlanningEntry;

import org.junit.Test;

import Resource.Teacher;

public class CommonPlanningEntryTest extends PlanningEntryTest{
	
	@Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
	
	@Override
	public CommonPlanningEntry<Teacher> emptyInstance() {
		return new CommonPlanningEntry<Teacher>() ;
	}
	
	
}
