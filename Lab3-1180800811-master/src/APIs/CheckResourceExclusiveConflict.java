package APIs;

import java.util.List;

import PlanningEntry.PlanningEntry;

public interface CheckResourceExclusiveConflict {
	/**
	 * ���ƻ�����Ƿ������Դ��ͻ
	 * @param entries �ƻ����б�
	 * @return  ��Դ���ڳ�ͻ������false �� λ�ò����ڳ�ͻ������true
	 */
	public  boolean checkResourceExclusiveConflict(List<PlanningEntry> entries);
	

		
	
}
