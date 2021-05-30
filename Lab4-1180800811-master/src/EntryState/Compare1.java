package EntryState;

import java.util.Comparator;

import Timeslot.Timeslot;

public class Compare1 implements Comparator<Timeslot>{

	@Override
	public int compare(Timeslot time1, Timeslot time2) {
		if(time1.getdate2().getTime().before(time2.getdate1().getTime())) {
			return -1 ;
		}else if(time1.getdate2().getTime().after(time2.getdate1().getTime())) {
			return 1 ;
		}else {		
		return 0 ;
		}
	}

}
