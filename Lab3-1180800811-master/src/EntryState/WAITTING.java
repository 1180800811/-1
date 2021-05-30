package EntryState;

public class WAITTING implements State{
	public static WAITTING instance = new WAITTING() ;
	private WAITTING() {} ;
	@Override
	public State changeState(String type) {
		switch(type) {
			case "ALOCATED" : return ALOCATED.instance ;
			case "CANCELLED": return CANCELLED.instance ;
			default : throw new IllegalArgumentException("WAITTING状态不能转换为" + type + "状态");
		}
	}

	@Override
	public boolean accept() {
		return false;
	}
	@Override
	public String toString() {
		return "WAITTING";
	}
	
}
