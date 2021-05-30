package Board;

import java.util.Comparator;

import PlanningEntry.CourseEntry;

public class CourseCompare implements Comparator<CourseEntry> {

	@Override
	public int compare(CourseEntry o1 ,CourseEntry o2 ) {
		if(o1.getTime1().compareTo(o2.getTime1()) < 0) {
			return -1 ;
		}else if(o1.getTime1().compareTo(o2.getTime1()) > 0){
			return 1 ;
		}
		return 0 ;
	}
}
