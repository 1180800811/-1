package MyException;

import PlanningEntry.PlanningEntry;

public class cancelPlanningEntryException extends Exception{
	/**
	 * ȡ���ƻ����쳣
	 * @param pl ��ȡ���ļƻ���
	 */
	public cancelPlanningEntryException(PlanningEntry pl) {
		super("ȡ���ƻ����쳣: �ƻ���" + pl.getName() + "��ǰ��״̬������ȡ��");
	}
}
