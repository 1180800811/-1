package Resource;

// a immutable class 一架飞机
public class Plane {
	
	//
	// RI:
	//all the field is not null
	// Size and Age is a positive Integer
	//
	//Abstraction function:
	//代表多个资源
	
	//Rep:
	// all fields are private
	// there is no exposure of the field
	private String PlaneNumber ;//飞机编号
	private String MachineNumber ;//机型号
	private int Size ;//座位数
	private double Age ;//机龄
	
	/**
	 * 初始化一架飞机
	 * @param planeNumber //飞机编号
	 * @param machineNumber //机型号
	 * @param size //座位数
	 * @param age //机龄
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
	 * 获得飞机编号
	 * @return 飞机编号
	 */
	public String getPlaneNumber() {
		return PlaneNumber;
	}
	/**
	 * 设置飞机编号
	 * @param planeNumber 飞机编号
	 */
	public void setPlaneNumber(String planeNumber) {
		PlaneNumber = planeNumber;
	}
	/**
	 * 获得机型号
	 * @return  机型号
	 */
	public String getMachineNumber() {
		return MachineNumber;
	}
	/**
	 * 设置机型号
	 * @param machineNumber  机型号
	 */
	public void setMachineNumber(String machineNumber) {
		MachineNumber = machineNumber;
	}
	/**
	 * 获得座位个数
	 * @return	座位个数
	 */
	public int getSize() {
		return Size;
	}
	/**
	 * 设置座位个数
	 * @param size  座位个数
	 */
	public void setSize(int size) {
		Size = size;
	}
	/**
	 * 获得飞机机龄
	 * @return 飞机机龄
	 */
	public double getAge() {
		return Age;
	}
	/**
	 * 设置飞机机龄
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
