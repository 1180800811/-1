package MyException;

import Location.Location;

public class changeLocationException extends Exception{
	
	/**
	 * ���ļƻ����״̬�쳣
	 * @param loc1 ԭʼλ��
	 * @param loc2 ���ĺ��λ��
	 */
	public changeLocationException(Location loc1 , Location loc2) {
		super("�ı�λ�ô����쳣: ��λ��:" + loc1.getName() + "���Ϊλ��:" + loc2.getName() + "�󣬼ƻ���֮�����λ�ó�ͻ!");
	}
}
