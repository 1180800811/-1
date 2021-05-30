package PlanningEntry;

import java.util.List;
import java.util.Map;
// a immutable class 一个计划项
import EntryState.*;
import Location.Location;
import Resource.Teacher;
import Timeslot.Timeslot;

public class CommonPlanningEntry<R> implements PlanningEntry<R>{
	
	//RI:
	//Name is not null 
	//state is not null
	//
	//AF:
	// 表示一个计划项，有确定的名字和状态。
	//
	//
	//Rep:
	// the field of the Name is private
	// there is no exposure of the field 
	private String Name = new String() ; //计划项名字

	protected State state = WAITTING.instance; ;//计划项的状态,设置为保护型，便于Train类对状态进行转换

	public CommonPlanningEntry() {
		state = WAITTING.instance;//设置计划项初始状态
	}
	/**
	 * 初始化一个计划项
	 * @param name 计划项的名字
	 */
	public CommonPlanningEntry(String name) {
		Name = name;
		assert name != null ;
	}
	
	@Override
	public void BeginPlanningEntry()  {
		state = state.changeState("RUNNING");
	}

	@Override
	public void CancelPlanningEntry()  {

		state = state.changeState("CANCELLED");
	}
	@Override
	public void EndPlanningEntry()  {
		state = state.changeState("ENDED");
	}
	
	@Override
	public State getState() {
		return state;
	}
	@Override
	public String getName() {
		assert Name != null ;
		return this.Name ;
		
	}
	@Override
	public void setName(String name) {
		assert name != null ;
		this.Name = name ;
			
	}
	@Override
	public void setState(State state) {
		this.state = state ;
	}
	@Override
	public Map<Timeslot, List<Location>> getTimeLocation() {
		return null;
	}
	@Override
	public List<R> getresource() {
		return null;
	}
	@Override
	public Timeslot getBeginEndTime() {
		return null;
	}
	@Override
	public boolean accept()  { return state.accept(); }


}
