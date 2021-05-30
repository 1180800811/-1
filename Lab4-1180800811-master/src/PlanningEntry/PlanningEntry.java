package PlanningEntry;

import java.util.List;
import java.util.Map;
import EntryState.State;
import Location.Location;
import Timeslot.Timeslot;
public interface PlanningEntry<R> {
	
	
	/**
	 * 创建一个新的计划项
	 * @param <R> 资源
	 * @param type 计划项类型
	 * @param name 计划项名字
	 * @return 创建的计划项
	 */
	static<R> PlanningEntry<R> NewFlightEntry(String type , String name){
		return new Factory().getPlanningEntry(type, name);
	}
	/**
	 * 启动一个计划项
	 * @throws Exception 计划项已启动、已取消、已终止、未分配资源
	 */
	public void BeginPlanningEntry() ;
	
	/**
	 * 取消一个计已阻塞划项
	 * @throws Exception  计划项已启动、已终止
	 */
	public void CancelPlanningEntry();
	
	/**
	 * 结束一个计划项
	 * @throws Exception 计划项未分配资源 、 未启动、已取消、已终止、已阻塞
	 */
	public void EndPlanningEntry() ;
	
	/**
	 * 获得计划项的名字
	 * @return 计划项的名字
	 */
	public String getName();
	
	/**
	 * 获得计划项的状态
	 * @return 计划项的状态
	 */
	public State getState() ;
	/**
	 * 设置计划项的名字
	 * @param name 计划项的名字
	 */
	public void setName(String name);
	/**
	 * 设置计划项的状态
	 * @param state 计划项的状态
	 */
	public void setState(State state ) ;
	
	/**
	 * 得到计划项所用的位置和时间对的对应关系
	 * @return 计划项所用的位置和时间对的对应关系
	 */
	public Map<Timeslot,List<Location>> getTimeLocation() ; 
	
	/**
	 * 获取计划项所使用的的资源
	 * @return
	 */
	public List<R> getresource() ;
	
	/**
	 * 获取计划项的起止时间对
	 * @return
	 */
	public Timeslot getBeginEndTime() ;
	
	/**
	 * 判断计划项状态是否是可接收的
	 * @return
	 */
	public boolean accept();
	
}
