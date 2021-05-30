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
						if(entries.get(i).getresource().get(k).equals(entries.get(j).getresource().get(w))) {//��Դ��ͬ
							Calendar time1 = entries.get(i).getBeginEndTime().getdate1() ;//��i���ƻ������ʼʱ��
							Calendar time2 = entries.get(i).getBeginEndTime().getdate2()  ;//��i���ƻ���Ľ���ʱ��
							Calendar time3 = entries.get(j).getBeginEndTime().getdate1()  ;//��j���ƻ������ʼʱ��
							Calendar time4 = entries.get(j).getBeginEndTime().getdate2()  ;//��j���ƻ���Ľ���ʱ��
							if((time1.compareTo(time3) < 0 && time2.compareTo(time3) > 0)  || ((time1.compareTo(time3) > 0 && time1.compareTo(time4) < 0))) {//ʱ���ص�
								System.out.println(entries.get(i).getName() + "��" + entries.get(j).getName() + 
										" ������Դ:(" +entries.get(j).getresource().get(w).toString()  + ")��ͻ");
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
