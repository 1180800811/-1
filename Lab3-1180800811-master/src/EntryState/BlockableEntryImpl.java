package EntryState;

import java.util.ArrayList;
// a immutable class һϵ�е�ʱ���
import java.util.Collections;
import java.util.List;
import Timeslot.Timeslot;

	//RI:
	//һϵ�е�ʱ�����ǲ�ͬ�ģ������ǰ�ʱ��˳�������
	//
	//AF:
	//��ʾһϵ�е�ʱ����

	// Rep:
	// the field is private
	// List is mutable , so make defensive copy for getTimeslots
	//
public class BlockableEntryImpl implements BlockableEntry {
	
	private List<Timeslot> timeslots = new ArrayList<Timeslot>();//������һЩ��ʱ���
	
	/**
	 * ʱ����ǵ�����
	 */
	public void checkRep() {
		assert timeslots != null ; 
		if(timeslots.size() > 1 ) {
			for(int i = 0 ; i < timeslots.size() - 1 ; i ++	) {
				assert timeslots.get(i).getdate2().compareTo(timeslots.get(i+1).getdate2()) < 0 ;
			}
		}
	}
	
	@Override
	public void Block() {
	}
	
	@Override
	public boolean addTimeslots(Timeslot s ) {
		if( !timeslots.contains(s)) {
			timeslots.add(s);
			Collections.sort(timeslots, new Compare1());
			checkRep();
			return true;
		}
		return false ;
	}
	/**
	 * �޲����Ĺ�����
	 */
	public BlockableEntryImpl() {
		
	}
	/**
	 * ��ʼ��һϵ��ʱ���
	 * @param timeslots һϵ��ʱ���
	 */
	public BlockableEntryImpl(List<Timeslot> timeslots) {
		this.timeslots = timeslots;
		Collections.sort(timeslots, new Compare1());
		checkRep();
	}
	@Override
	public void setTimeslots(List<Timeslot> time) {
		List<Timeslot> time1 = new ArrayList<Timeslot>() ;
		time1.addAll(time) ;
		Collections.sort(time, new Compare1());
		this.timeslots = time1 ;
	}

	@Override
	public List<Timeslot> getTimeslots(){
		List<Timeslot> timeslotss = new ArrayList<Timeslot>();
		timeslotss.addAll(timeslots);
		Collections.sort(timeslotss, new Compare1());
		return timeslotss ;
	}



	@Override
	public String toString() {
		return "BlockableEntryImpl [timeslots=" + timeslots + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((timeslots == null) ? 0 : timeslots.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlockableEntryImpl other = (BlockableEntryImpl) obj;
		if (timeslots == null) {
			if (other.timeslots != null)
				return false;
		} else if (!timeslots.equals(other.timeslots))
			return false;
		return true;
	}
	


	
}
