package MyException;

public class deleteResourceException extends Exception{
	/**
	 * ɾ����Դ�����쳣:���ڼƻ��ǰ����ʹ����Դr
	 * @param <R> ��Դ�ķ���
	 * @param r ��ɾ������Դ
	 */
	public <R> deleteResourceException(R r) {
		super("ɾ����Դ�쳣: ����δ�����ļƻ�������ʹ�ø���Դ:" + r.toString()) ;
	}
}
