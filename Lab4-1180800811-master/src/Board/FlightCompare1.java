package Board;

import java.util.Comparator;

import PlanningEntry.FlightEntry;

public class FlightCompare1 implements Comparator<FlightEntry> {

	/**
	 * ���������ΰ��յִ�location��ʱ���������
	 */
	@Override
	public int compare(FlightEntry o1, FlightEntry o2) {
		if(o1.getTime1().compareTo(o2.getTime1()) < 0) {
			return -1 ;
		}else if(o1.getTime1().compareTo(o2.getTime1()) > 0){
			return 1 ;
		}
		return 0 ;
	}
}
