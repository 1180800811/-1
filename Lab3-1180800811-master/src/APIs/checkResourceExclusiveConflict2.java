package APIs;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import PlanningEntry.PlanningEntry;

public class checkResourceExclusiveConflict2 implements CheckResourceExclusiveConflict{


	@Override
	public boolean checkResourceExclusiveConflict(List<PlanningEntry> entries) {
		boolean flag = true ;	
		for(int i = 0 ; i < entries.size() ; i ++) {
			for(int j =i + 1 ;j <entries.size() ; j++) {
				for(int k = 0 ; k < entries.get(i).getresource().size() ; k ++) {
					for(int w = 0 ; w < entries.get(j).getresource().size() ; w++) {
						if(entries.get(i).getresource().get(k).equals(entries.get(j).getresource().get(w))) {//资源相同
							Calendar time1 = entries.get(i).getBeginEndTime().getdate1() ;//第i个计划项的起始时间
							Calendar time2 = entries.get(i).getBeginEndTime().getdate2()  ;//第i个计划项的结束时间
							Calendar time3 = entries.get(j).getBeginEndTime().getdate1()  ;//第j个计划项的起始时间
							Calendar time4 = entries.get(j).getBeginEndTime().getdate2()  ;//第j个计划项的结束时间
							if((time1.compareTo(time3) < 0 && time2.compareTo(time3) > 0)  || ((time1.compareTo(time3) > 0 && time1.compareTo(time4) < 0))) {//时间重叠
								System.out.println(entries.get(i).getName() + "和" + entries.get(j).getName() + 
										" 存在资源:(" +entries.get(j).getresource().get(w).toString()  + ")冲突");
								flag = false ;
							}
						}

					}
				}


			}
		}
		return flag;
	}



}
