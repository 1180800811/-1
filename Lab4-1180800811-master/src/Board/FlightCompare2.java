package Board;

import java.util.Comparator;

import PlanningEntry.FlightEntry;

public class FlightCompare2 implements Comparator<FlightEntry> {
	/**
	 * ���������ΰ��մ�location������ʱ���������
	 */
	@Override
	public int compare(FlightEntry o1, FlightEntry o2) {
		if(o1.getTime2().compareTo(o2.getTime2()) < 0) {
			return -1 ;
		}else if(o1.getTime2().compareTo(o2.getTime2()) > 0){
			return 1 ;
		}
		return 0 ;
	}
}
