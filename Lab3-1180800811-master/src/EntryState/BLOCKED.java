package EntryState;

public class BLOCKED implements State{
	 public static BLOCKED instance = new BLOCKED() ;
		private BLOCKED() {} ;
	@Override
	public State changeState(String type) {
		switch(type) {
			case "RUNNING" : return RUNNING.instance ;
			case "CANCELLED" : return CANCELLED.instance ;
			default : throw new IllegalArgumentException("BLOCKED状态不能转换为" + type + "状态");
		}
	}

	@Override
	public boolean accept() {
		return false;
	}
	@Override
	public String toString() {
		return "BLOCKED";
	}
	
}
