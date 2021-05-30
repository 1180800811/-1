package PlanningEntry;

import Location.SingleLocationEntry;
import Resource.SingleDistinguishResourceEntry;
import EntryState.UnBlockableEntry;
public interface CoursePlanningEntry<R> extends SingleLocationEntry , UnBlockableEntry ,SingleDistinguishResourceEntry<R>{

}
