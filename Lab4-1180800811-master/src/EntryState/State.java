package EntryState;



public interface State {
	/**
	 * 改变状态
	 * @param type 转变为状态 的类型
	 * @return 改变后的转态
	 */
	public State changeState(String type) ;
	
	/**
	 * 状态是否是可接受
	 * @return  true:状态是可接收  false:状态不可接受
	 */
	public boolean accept() ;
	
}
