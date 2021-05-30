package EntryState;

import java.util.List;

import Timeslot.Timeslot;

public interface BlockableEntry {
	/**
	 * �ƻ�������
	 * @param time �ƻ�������
	 */
	public void Block();

	/**
	 * ����һϵ��ʱ���
	 * @return һϵ��ʱ���
	 */
	public void setTimeslots(List<Timeslot> time);
	/**
	 * ���һϵ��ʱ���
	 * @return һϵ��ʱ���
	 */
	public List<Timeslot> getTimeslots() ;
	
	/**
	 * ����һ��ʱ���
	 * @param s ʱ���
	 * @return true: ���ӳɹ� �� false:����ʧ��
	 */
	public boolean addTimeslots(Timeslot s );
	
}
