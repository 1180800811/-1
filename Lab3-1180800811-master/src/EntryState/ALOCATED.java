package EntryState;

public class ALOCATED implements State {
	
	public static ALOCATED instance = new ALOCATED();
	private ALOCATED() {} ;
	@Override
	public State changeState(String type) {
		switch(type) {
			case "RUNNING" : return RUNNING.instance ; 
			case "CANCLLED": return CANCELLED.instance;		
			default: throw new IllegalArgumentException();
		}		
	}

	@Override
	public boolean accept() {
		return false ;
	}
	@Override
	public String toString() {
		return "ALOCATED";
	}
	
}
