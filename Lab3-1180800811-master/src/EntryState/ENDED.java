package EntryState;

public class ENDED implements State{
	public static ENDED instance = new ENDED() ;
	private ENDED() {} ;
	@Override
	public State changeState(String type) {
		throw new IllegalArgumentException("ENDED״̬����ת��Ϊ" + type + "״̬");
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
