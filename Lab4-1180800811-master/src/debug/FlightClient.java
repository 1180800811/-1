package debug;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Note that this class may use the other two class: Flight and Plane.
 * 
 * Debug and fix errors. DON'T change the initial logic of the code.
 *
 */
public class FlightClient {
	
	/**

	*����һ�������嵥��һ���ɻ��嵥������ÿ��������δ

	*������һ���ɻ����˷�������Ϊÿ���������һ���ɻ�����ȷ��

	*���з���֮��û���κ�ʱ���ͻ��

	* ���磺

	*����1��2020-01-01 8��00-10��00���ͺ���2��2020-01-01 9��50-10��40���ѷ���

	*ͬһƽ��B0001������ڳ�ͻ����Ϊ��B0001��9:50��10:00

	*����ͬʱΪ�����������
	 * @param planes a list of planes
	 * @param flights a list of flights each of which has no plane allocated
	 * @return false if there's no allocation solution that could avoid any conflicts
	 * @throws Exception 
	 */
	
	public boolean planeAllocation(List<Plane> planes, List<Flight> flights) throws Exception {
		
		for(int i = 0 ; i < flights.size() ; i ++) {
			if( flights.get(i).getDepartTime().compareTo(flights.get(i).getArrivalTime()) >= 0 ) {
				throw new Exception("�������ʱ��Ӧ�����ڵ���ʱ��");
			}
		}
		boolean bFeasible = true;
		Random r = new Random();
		
		Collections.sort(flights , new Comparator<Flight>() {//�Ժ��ఴ�ճ���ʱ�������������

			@Override
			public int compare(Flight o1, Flight o2) {
				if(o1.getDepartTime().before(o2.getDepartTime())) {
					return -1 ;
				}else if(o1.getDepartTime().after(o2.getDepartTime())) {
					return 1 ;
				}else {
					return 0 ;
				}
			}
		});
		

		for (Flight f : flights) {
			boolean bAllocated = false;
			Set<Plane> pp  = new HashSet<Plane>() ;
			while (!bAllocated) {
				Plane p = planes.get(r.nextInt(planes.size()));//�������ɻ�
				if(pp.contains(p)) {//�ж�����ɻ�֮ǰ�Ƿ��жϹ�
					continue ;
				}
				pp.add(p) ;
				Calendar fStart = f.getDepartTime();
				Calendar fEnd = f.getArrivalTime();
				boolean bConflict = false;
				
				for (Flight t : flights) {
					Plane q = t.getPlane();
					if (! p.equals(q))
						continue;
					
					Calendar tStart = t.getDepartTime();
					Calendar tEnd = t.getArrivalTime();
					
					if ((fStart.compareTo(tStart) >= 0 && fStart.compareTo(tEnd) <= 0) || (tStart.compareTo(fStart) >= 0 && tStart.compareTo(fEnd) <= 0)) {
						bConflict = true;
						break ;
					}
				}
				
				if (!bConflict) {
					f.setPlane(p);
					bAllocated = true ;
					break;
				}
				if(pp.size() == planes.size()) {//�ж��Ƿ���������еķɻ�
					break ;
				}
			}
			if(bAllocated == false)
				return false ;
		}
		return bFeasible;
	}
//	public static void main(String[] args) throws Exception {
//		Flight f1 = new Flight() ;
//		Calendar time1 = Calendar.getInstance() ;
//		time1.set(2020, 1-1, 1, 1, 1);//2020-01-01
//		Calendar time2 = Calendar.getInstance() ;
//		time2.set(2020, 1-1, 2, 1, 1);//2020-01-02
//		f1.setDepartTime(time1);
//		f1.setArrivalTime(time2);
//		Plane p1 = new Plane() ;
//		p1.setPlaneNo("ss");//�ɻ�ss
//		List<Plane> l1 = new ArrayList<Plane>() ;
//		List<Flight> f = new ArrayList<Flight>() ;
//		l1.add(p1);
//		f.add(f1);
////		System.out.println(new FlightClient().planeAllocation(l1, f));
//		Flight f2 = new Flight() ;
//		Calendar time3 = Calendar.getInstance() ;
//		time3.set(2020, 3-1, 1,3, 1);//2020-01-01
//		Calendar time4 = Calendar.getInstance() ;
//		time4.set(2020, 3-1, 3, 1, 1);//2020-01-02
//		f2.setDepartTime(time3);
//		f2.setArrivalTime(time4);
//		f.add(f2);
//		System.out.println(new FlightClient().planeAllocation(l1, f));
//		
//	}

}
