package EntryState;



public interface State {
	/**
	 * �ı�״̬
	 * @param type ת��Ϊ״̬ ������
	 * @return �ı���ת̬
	 */
	public State changeState(String type) ;
	
	/**
	 * ״̬�Ƿ��ǿɽ���
	 * @return  true:״̬�ǿɽ���  false:״̬���ɽ���
	 */
	public boolean accept() ;
	
}
