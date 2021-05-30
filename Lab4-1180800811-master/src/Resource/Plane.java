package Resource;

// a immutable class һ�ܷɻ�
public class Plane {
	
	//
	// RI:
	//all the field is not null
	// Size and Age is a positive Integer
	//
	//Abstraction function:
	//��������Դ
	
	//Safety from rep exposure:
	// all fields are private
	// there is no exposure of the field
	private String PlaneNumber ;//�ɻ����
	private String MachineNumber ;//���ͺ�
	private int Size ;//��λ��
	private double Age ;//����
	
	/**
	 * ��ʼ��һ�ܷɻ�
	 * @param planeNumber //�ɻ����
	 * @param machineNumber //���ͺ�
	 * @param size //��λ��
	 * @param age //����
	 */
	
	public void checkRep() {
		assert PlaneNumber != null : "�ɻ���Ų���Ϊ��";
		assert MachineNumber !=null : "�ɻ����ͺŲ���Ϊ��" ;
		assert Size > 50 && Size < 600 : "�ɻ���������������[50,600]" ;
		assert Age > 0 && Age < 30 : "�ɻ����������[0,30]" ;
	}
	public Plane(String planeNumber, String machineNumber, int size, double age) {
		PlaneNumber = planeNumber;
		MachineNumber = machineNumber;
		Size = size;
		Age = age;
		checkRep();
	}
	/**
	 * ��÷ɻ����
	 * @return �ɻ����
	 */
	public String getPlaneNumber() {
		return PlaneNumber;
	}
	/**
	 * ���÷ɻ����
	 * @param planeNumber �ɻ����
	 */
	public void setPlaneNumber(String planeNumber) {
		PlaneNumber = planeNumber;
	}
	/**
	 * ��û��ͺ�
	 * @return  ���ͺ�
	 */
	public String getMachineNumber() {
		return MachineNumber;
	}
	/**
	 * ���û��ͺ�
	 * @param machineNumber  ���ͺ�
	 */
	public void setMachineNumber(String machineNumber) {
		assert machineNumber != null : "�ɻ���Ų���Ϊ��" ;
		MachineNumber = machineNumber;
	}
	/**
	 * �����λ����
	 * @return	��λ����
	 */
	public int getSize() {
		return Size;
	}
	/**
	 * ������λ����
	 * @param size  ��λ����
	 */
	public void setSize(int size) {
		assert size > 50 && Size < 600 : "�ɻ���������������[50,600]" ;
		Size = size;
	}
	/**
	 * ��÷ɻ�����
	 * @return �ɻ�����
	 */
	public double getAge() {
		return Age;
	}
	/**
	 * ���÷ɻ�����
	 * @param age
	 */
	public void setAge(double age) {
		assert age > 0 && Age < 30 : "�ɻ����������[0,30]" ;
		Age = age;
	}

	@Override
	public String toString() {
		return "Plane [PlaneNumber=" + PlaneNumber + ", MachineNumber=" + MachineNumber + ", Size=" + Size + ", Age="
				+ Age + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(Age);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((MachineNumber == null) ? 0 : MachineNumber.hashCode());
		result = prime * result + ((PlaneNumber == null) ? 0 : PlaneNumber.hashCode());
		result = prime * result + Size;
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
		Plane other = (Plane) obj;
		if (Double.doubleToLongBits(Age) != Double.doubleToLongBits(other.Age))
			return false;
		if (MachineNumber == null) {
			if (other.MachineNumber != null)
				return false;
		} else if (!MachineNumber.equals(other.MachineNumber))
			return false;
		if (PlaneNumber == null) {
			if (other.PlaneNumber != null)
				return false;
		} else if (!PlaneNumber.equals(other.PlaneNumber))
			return false;
		if (Size != other.Size)
			return false;
		return true;
	}




	
}
