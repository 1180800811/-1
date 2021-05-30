package PlanningEntry;

import EntryState.UnBlockableEntry;
import Location.TwoLocationEntry;
import Resource.SingleDistinguishResourceEntry;

public interface FlightPlanningEntry<R> extends  TwoLocationEntry,UnBlockableEntry,SingleDistinguishResourceEntry<R> {

}
