package MyException;

public class feipeiResourceException extends Exception{
	/**
	 * ������Դ�������Դ��ռ��ͻ
	 * @param <R> �������Դ�ķ���
	 * @param r ���������Դ
	 */
	public <R> feipeiResourceException(R r) {
		super("�������Դ:"+r.toString() + "�󣬼ƻ���֮�������Դ��ռ��ͻ");
	}
}
