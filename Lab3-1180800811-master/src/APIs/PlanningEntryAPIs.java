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
	 * ���ƻ�����Ƿ������Դ��ͻ
	 * @param entries �ƻ����б�
	 * @s ��������ͣ�����������ѡ������ĸ�����
	 * @return  ��Դ���ڳ�ͻ������false �� λ�ò����ڳ�ͻ������true
	 */
	public static boolean checkResourceExclusiveConflict(List<PlanningEntry> entries , CheckResourceExclusiveConflict s ) {

			return s.checkResourceExclusiveConflict(entries);
	}
	/**
	 * ���ƻ�����Ƿ����λ�ó�ͻ
	 * @param entries �ƻ����б�
	 * @return  λ�ô��ڳ�ͻ������false �� λ�ò����ڳ�ͻ������true
	 */
	public  static boolean checkLocationConflict(List< PlanningEntry> entries) {
		boolean flag = true ;
		for(int i = 0 ; i < entries.size() ; i ++) {
			Set<Timeslot> time1 = entries.get(i).getTimeLocation().keySet();
			for(Timeslot e : time1) {
				for( int j = i + 1 ; j < entries.size() ; j ++) {
					Set<Timeslot> time2 = entries.get(j).getTimeLocation().keySet();
					for(Timeslot f : time2) {
						Date time11 = e.getdate1().getTime() ;//��i���ƻ������ʼʱ��
						Date time22 = e.getdate2().getTime() ;//��i���ƻ���Ľ���ʱ��
						Date time33 = f.getdate1().getTime() ;//��j���ƻ������ʼʱ��
						Date time44 = f.getdate2().getTime() ;//��j���ƻ���Ľ���ʱ��
						if((time11.before(time33) && time22.after(time33)) || (time11.after(time33) && time11.before(time44))) {//ʱ������ص�,���ж�λ���Ƿ��ص�
							List<Location> lc1 = (List<Location>) entries.get(i).getTimeLocation().get(e) ;//�ƻ���1������λ��
							List<Location> lc2 = (List<Location>) entries.get(j).getTimeLocation().get(f) ;//�ƻ���2������λ��
							for(int k = 0 ; k < lc1.size() ; k++) {
								for(int w = 0 ; w < lc2.size() ; w ++) {
									if(lc1.get(k).equals(lc2.get(w))) {//λ����ͬ
										System.out.println(entries.get(i).getName() + "��" + entries.get(j).getName() + "����λ��: ("+lc1.get(k).getName() + ")  ��ͻ");//��ӡ��ͻ��Ϣ
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
	 * ��һϵ�мƻ������ҳ�����ĳ����Դ��ĳ���ƻ����ǰ��ƻ���
	 * @param <R> ���ͣ�������Դ������
	 * @param r ���õ�ĳ����Դ
	 * @param e �ƻ���
	 * @param entries һϵ�мƻ���
	 * @return
	 */
	public static<R> PlanningEntry<R> findPreEntryPerResource(R r, PlanningEntry<R> e, List< ? extends PlanningEntry<R>> entries) {
		PlanningEntry<R> x = null ;//��ʼ���ƻ���
		Calendar latestTime = null ;//��ѡ���ļƻ���e֮ǰ����ӽ�e�Ľ���ʱ��ļƻ���Ľ���ʱ��
		for(int i = 0 ; i < entries.size() ; i++ ) {
				if(entries.get(i).getresource().contains(r)) {
					Calendar time = entries.get(i).getBeginEndTime().getdate2();
					if( time.compareTo(e.getBeginEndTime().getdate1()) < 0 ) {//e��ǰ��ƻ���
						if(latestTime == null) {
							latestTime = time ;//��ʼ��
							x = entries.get(i);//�ı���ӽ�e�ļƻ���
						}else {
							if(time.compareTo(latestTime) > 0) {
								latestTime = time ;//�ı���ӽ�e�ļƻ���Ľ���ʱ���ʱ��
								x = entries.get(i);//�ı���ӽ�e�ļƻ���
							}
						}
					}
					
				}
	}

		return x ;
	} 
	
	
	
	
	
	
}
