package PlanningEntry;

import EntryState.BlockableEntry;
import Location.MultipleLocationEntry;
import Resource.MultipleSortedResourceEntry;

public interface TrainPlanningEntry<R> extends MultipleLocationEntry, BlockableEntry, MultipleSortedResourceEntry<R>  {
	
}
