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

	*给定一个航班清单和一个飞机清单，假设每个航班尚未

	*分配了一个飞机，此方法尝试为每个航班分配一个飞机，并确保

	*所有分配之间没有任何时间冲突。

	* 例如：

	*航班1（2020-01-01 8：00-10：00）和航班2（2020-01-01 9：50-10：40）已分配

	*同一平面B0001，则存在冲突，因为从B0001的9:50到10:00

	*不能同时为两个航班服务。
	 * @param planes a list of planes
	 * @param flights a list of flights each of which has no plane allocated
	 * @return false if there's no allocation solution that could avoid any conflicts
	 * @throws Exception 
	 */
	
	public boolean planeAllocation(List<Plane> planes, List<Flight> flights) throws Exception {
		
		for(int i = 0 ; i < flights.size() ; i ++) {
			if( flights.get(i).getDepartTime().compareTo(flights.get(i).getArrivalTime()) >= 0 ) {
				throw new Exception("航班出发时间应该早于到达时间");
			}
		}
		boolean bFeasible = true;
		Random r = new Random();
		
		Collections.sort(flights , new Comparator<Flight>() {//对航班按照出发时间进行增序排序

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
				Plane p = planes.get(r.nextInt(planes.size()));//随机分配飞机
				if(pp.contains(p)) {//判断这个飞机之前是否判断过
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
				if(pp.size() == planes.size()) {//判断是否遍历了所有的飞机
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
//		p1.setPlaneNo("ss");//飞机ss
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
