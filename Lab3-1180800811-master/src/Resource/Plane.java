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
	
	//Rep:
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
		assert PlaneNumber != null;
		assert MachineNumber !=null ;
		assert Size > 0 ;
		assert Age > 0 ;
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
