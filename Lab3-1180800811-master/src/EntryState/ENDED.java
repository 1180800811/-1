package EntryState;

public class ENDED implements State{
	public static ENDED instance = new ENDED() ;
	private ENDED() {} ;
	@Override
	public State changeState(String type) {
		throw new IllegalArgumentException("ENDED状态不能转换为" + type + "状态");
	}

	@Override
	public boolean accept() {
		return true ;
	}
	@Override
	public String toString() {
		return "ENDED";
	}
	
	
}
