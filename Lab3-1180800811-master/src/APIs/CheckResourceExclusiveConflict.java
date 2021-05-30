package APIs;

import java.util.List;

import PlanningEntry.PlanningEntry;

public interface CheckResourceExclusiveConflict {
	/**
	 * 检查计划项安排是否存在资源冲突
	 * @param entries 计划项列表
	 * @return  资源存在冲突，返回false ， 位置不存在冲突，返回true
	 */
	public  boolean checkResourceExclusiveConflict(List<PlanningEntry> entries);
	

		
	
}
