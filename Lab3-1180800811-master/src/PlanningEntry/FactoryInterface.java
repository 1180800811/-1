package PlanningEntry;

public interface FactoryInterface {
	/**
	 * 工厂方法
	 * @param type 类型
	 * @param name 计划项名字
	 * @return 指定类型的计划项
	 */
	public PlanningEntry getPlanningEntry(String type , String name);
}
