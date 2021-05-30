package EntryState;

public class CANCELLED implements State{
	public static CANCELLED instance = new CANCELLED() ;
	private CANCELLED() {} ;
	@Override
	public State changeState(String type) {
		 throw new IllegalArgumentException("CANCELLED״̬����ת��Ϊ" + type + "״̬");
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
