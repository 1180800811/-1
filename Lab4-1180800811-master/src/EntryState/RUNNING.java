package EntryState;

public class RUNNING implements State{
	public static RUNNING instance = new RUNNING() ;
	private RUNNING() {} ;
	@Override
	public State changeState(String type) {
		switch(type) {
			case "ENDED": return ENDED.instance ;
			case "BLOCKED": return BLOCKED.instance;
			default : throw new IllegalArgumentException("RUNNING状态不能转换为" + type + "状态");
		}
	}

	@Override
	public boolean accept() {
		return false ;
	}
	@Override
	public String toString() {
		return "RUNNING";
	}
	
}
