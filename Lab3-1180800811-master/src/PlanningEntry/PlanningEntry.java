package PlanningEntry;

import java.util.List;
import java.util.Map;
import EntryState.State;
import Location.Location;
import Timeslot.Timeslot;
public interface PlanningEntry<R> {
	
	
	/**
	 * ����һ���µļƻ���
	 * @param <R> ��Դ
	 * @param type �ƻ�������
	 * @param name �ƻ�������
	 * @return �����ļƻ���
	 */
	static<R> PlanningEntry<R> NewFlightEntry(String type , String name){
		return new Factory().getPlanningEntry(type, name);
	}
	/**
	 * ����һ���ƻ���
	 * @throws Exception �ƻ�������������ȡ��������ֹ��δ������Դ
	 */
	public void BeginPlanningEntry() ;
	
	/**
	 * ȡ��һ��������������
	 * @throws Exception  �ƻ���������������ֹ
	 */
	public void CancelPlanningEntry();
	
	/**
	 * ����һ���ƻ���
	 * @throws Exception �ƻ���δ������Դ �� δ��������ȡ��������ֹ��������
	 */
	public void EndPlanningEntry() ;
	
	/**
	 * ��üƻ��������
	 * @return �ƻ��������
	 */
	public String getName();
	
	/**
	 * ��üƻ����״̬
	 * @return �ƻ����״̬
	 */
	public State getState() ;
	/**
	 * ���üƻ��������
	 * @param name �ƻ��������
	 */
	public void setName(String name);
	/**
	 * ���üƻ����״̬
	 * @param state �ƻ����״̬
	 */
	public void setState(State state ) ;
	
	/**
	 * �õ��ƻ������õ�λ�ú�ʱ��ԵĶ�Ӧ��ϵ
	 * @return �ƻ������õ�λ�ú�ʱ��ԵĶ�Ӧ��ϵ
	 */
	public Map<Timeslot,List<Location>> getTimeLocation() ; 
	
	/**
	 * ��ȡ�ƻ�����ʹ�õĵ���Դ
	 * @return
	 */
	public List<R> getresource() ;
	
	/**
	 * ��ȡ�ƻ������ֹʱ���
	 * @return
	 */
	public Timeslot getBeginEndTime() ;
	
	/**
	 * �жϼƻ���״̬�Ƿ��ǿɽ��յ�
	 * @return
	 */
	public boolean accept();
	
}
