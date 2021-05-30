package APIs;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import Location.Location;
import PlanningEntry.*;
import Timeslot.Timeslot;
public class PlanningEntryAPIs {
	
	/**
	 * 检查计划项安排是否存在资源冲突
	 * @param entries 计划项列表
	 * @s 输入的类型，根据类型来选择调用哪个方法
	 * @return  资源存在冲突，返回false ， 位置不存在冲突，返回true
	 */
	public static boolean checkResourceExclusiveConflict(List<PlanningEntry> entries , CheckResourceExclusiveConflict s ) {

			return s.checkResourceExclusiveConflict(entries);
	}
	/**
	 * 检查计划项安排是否存在位置冲突
	 * @param entries 计划项列表
	 * @return  位置存在冲突，返回false ， 位置不存在冲突，返回true
	 */
	public  static boolean checkLocationConflict(List< PlanningEntry> entries) {
		boolean flag = true ;
		for(int i = 0 ; i < entries.size() ; i ++) {
			Set<Timeslot> time1 = entries.get(i).getTimeLocation().keySet();
			for(Timeslot e : time1) {
				for( int j = i + 1 ; j < entries.size() ; j ++) {
					Set<Timeslot> time2 = entries.get(j).getTimeLocation().keySet();
					for(Timeslot f : time2) {
						Date time11 = e.getdate1().getTime() ;//第i个计划项的起始时间
						Date time22 = e.getdate2().getTime() ;//第i个计划项的结束时间
						Date time33 = f.getdate1().getTime() ;//第j个计划项的起始时间
						Date time44 = f.getdate2().getTime() ;//第j个计划项的结束时间
						if((time11.before(time33) && time22.after(time33)) || (time11.after(time33) && time11.before(time44))) {//时间存在重叠,则判断位置是否重叠
							List<Location> lc1 = (List<Location>) entries.get(i).getTimeLocation().get(e) ;//计划项1的所有位置
							List<Location> lc2 = (List<Location>) entries.get(j).getTimeLocation().get(f) ;//计划项2的所有位置
							for(int k = 0 ; k < lc1.size() ; k++) {
								for(int w = 0 ; w < lc2.size() ; w ++) {
									if(lc1.get(k).equals(lc2.get(w))) {//位置相同
										System.out.println(entries.get(i).getName() + "和" + entries.get(j).getName() + "存在位置: ("+lc1.get(k).getName() + ")  冲突");//打印冲突信息
										flag= false ;
									}
								}
							}
							
						}
					}
				}
			}
		}
		
		return flag;
	}
	
	/**
	 * 在一系列计划项中找出共用某个资源的某个计划项的前序计划项
	 * @param <R> 泛型，代表资源的类型
	 * @param r 共用的某个资源
	 * @param e 计划项
	 * @param entries 一系列计划项
	 * @return
	 */
	public static<R> PlanningEntry<R> findPreEntryPerResource(R r, PlanningEntry<R> e, List< ? extends PlanningEntry<R>> entries) {
		PlanningEntry<R> x = null ;//初始化计划项
		Calendar latestTime = null ;//在选定的计划项e之前且最接近e的结束时间的计划项的结束时间
		for(int i = 0 ; i < entries.size() ; i++ ) {
				if(entries.get(i).getresource().contains(r)) {
					Calendar time = entries.get(i).getBeginEndTime().getdate2();
					if( time.compareTo(e.getBeginEndTime().getdate1()) < 0 ) {//e的前序计划项
						if(latestTime == null) {
							latestTime = time ;//初始化
							x = entries.get(i);//改变最接近e的计划项
						}else {
							if(time.compareTo(latestTime) > 0) {
								latestTime = time ;//改变最接近e的计划项的结束时间的时间
								x = entries.get(i);//改变最接近e的计划项
							}
						}
					}
					
				}
	}

		return x ;
	} 
	
	
	
	
	
	
}
