package MyException;

import Location.Location;

public class deleteLocationException extends Exception{
	/**
	 * ɾ��λ�ô����쳣
	 * @param loc ��ɾ����λ��
	 */
	public deleteLocationException(Location loc) {
		super("ɾ��λ���쳣: " + "������δ�����ļƻ�����λ��:" + loc.getName() + "ִ��");
	}
}
