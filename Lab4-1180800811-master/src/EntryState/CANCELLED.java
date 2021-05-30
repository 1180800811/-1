package EntryState;

public class CANCELLED implements State{
	public static CANCELLED instance = new CANCELLED() ;
	private CANCELLED() {} ;
	@Override
	public State changeState(String type) {
		 throw new IllegalArgumentException("CANCELLED状态不能转换为" + type + "状态");
	}

	@Override
	public boolean accept() {
		return true;
	}
	@Override
	public String toString() {
		return "CANCELLED";
	}
	
}
