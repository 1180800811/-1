package PlanningEntry;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
public class FactoryTest {
	/*
	 * Testing strategy for getPlanningEntry(String type , String name)
	 * Partition : type = "TrainEntry" , type = "FlightEntry" , type = "CourseEntry" , type = other
	 * 
	 */
	@Test
	public void getPlanningEntryTest1() {
		assertTrue(new Factory().getPlanningEntry("TrainEntry","s") instanceof TrainEntry) ;//림맨: type = "TrainEntry" 
		assertTrue(new Factory().getPlanningEntry("FlightEntry","s") instanceof FlightEntry) ;//림맨: type = "FlightEntry" 
		assertTrue(new Factory().getPlanningEntry("CourseEntry","s") instanceof CourseEntry) ;//림맨: type = "CourseEntry" 
	}
	@Test(expected= IllegalArgumentException.class)
	public void getPlanningEntryTest2() {
		assertFalse(new Factory().getPlanningEntry("ss", "s") instanceof PlanningEntry) ;
	}
}
