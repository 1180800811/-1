package MyException;

import PlanningEntry.PlanningEntry;

public class cancelPlanningEntryException extends Exception{
	/**
	 * 取消计划项异常
	 * @param pl 待取消的计划项
	 */
	public cancelPlanningEntryException(PlanningEntry pl) {
		super("取消计划项异常: 计划项" + pl.getName() + "当前的状态不允许取消");
	}
}
