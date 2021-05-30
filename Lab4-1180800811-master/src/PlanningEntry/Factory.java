package PlanningEntry;

public class Factory implements FactoryInterface{
	
	@Override
	public PlanningEntry getPlanningEntry(String type , String name) {
		if(type.equals("CourseEntry")) {
			return new CourseEntry(name) ;
		}else if(type.equals("FlightEntry")) {
			return new FlightEntry(name) ;
		}else if(type.equals("TrainEntry")){
			return new TrainEntry(name) ;
		}else {
			throw new IllegalArgumentException(" ‰»Î¥ÌŒÛ£¨«Î ‰»ÎCourseEntry/FlightEntry/TrainEntry");
		}
	}
	
}
