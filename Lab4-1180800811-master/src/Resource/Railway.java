package Resource;
// a immutable class ����
public class Railway {

	//
	// RI:
	//all the field is not null
	// Size and Year is a positive Integer
	//
	//Abstraction function:
	//����һ����������ȷ���ı�š��������͡���Ա���ͳ������
	
	//Safety from rep exposure:
	// all fields are private
	// there is no exposure of the field	
	private String Number ;//���
	private Type type ; //��������
	private int Size ; //��Ա��
	private int Year ;//�������
	
	
	public void checkRep() {
		assert Number != null : "����ı�Ų���Ϊ��";
		assert type != null : "��������Ͳ���Ϊ��";
		assert Size > 0 : "���ᶨԱ���������0";
		assert Year < 2021 : "������ݲ�����Ҫ��";
	}
	/**
	 * ��ʼ��һ������
	 * @param number ���
	 * @param type  ��������
	 * @param size  ��Ա��
	 * @param year  �������
	 */
	public Railway(String number, Type type, int size, int year) {
		Number = number;
		this.type = type;
		Size = size;
		Year = year;
		checkRep();
	}
	/**
	 * ��ñ��
	 * @return  ���
	 */
	public String getNumber() {
		return Number;
	}
	/**
	 * ���ñ��
	 * @param number  ���
	 */
	public void setNumber(String number) {
		assert number != null : "����ı�Ų���Ϊ��";
		Number = number;
	}
	
	/**
	 * ��ó�������
	 * @return  ��������
	 */
	public Type getType() {
		return type;
	}
	/**
	 * ���ó�������
	 * @param type  ��������
	 */
	public void setType(Type type) {
		assert type != null : "��������Ͳ���Ϊ��";
		this.type = type;
	}
	/**
	 * ��ö�Ա��
	 * @return ��Ա��
	 */
	public int getSize() {
		return Size;
	}
	/**
	 * ���ö�Ա��
	 * @param size   ��Ա��
 	 */
	public void setSize(int size) {
		assert size > 0 : "���ᶨԱ���������0";
		Size = size;
	}
	/**
	 * ��ó������
	 * @return
	 */
	public int getYear() {
		return Year;
	}
	/**
	 * ���ó������
	 * @param year �������
	 */
	public void setYear(int year) {
		assert year < 2021 : "������ݲ�����Ҫ��";
		Year = year;
	}
	@Override
	public String toString() {
		return "Railway [Number=" + Number + ", type=" + type + ", Size=" + Size + ", Year=" + Year + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Number == null) ? 0 : Number.hashCode());
		result = prime * result + Size;
		result = prime * result + Year;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Railway other = (Railway) obj;
		if (Number == null) {
			if (other.Number != null)
				return false;
		} else if (!Number.equals(other.Number))
			return false;
		if (Size != other.Size)
			return false;
		if (Year != other.Year)
			return false;
		if (type != other.type)
			return false;
		return true;
	}

}
