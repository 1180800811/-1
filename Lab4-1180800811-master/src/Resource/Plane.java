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
	
	//Safety from rep exposure:
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
		assert PlaneNumber != null : "飞机编号不能为空";
		assert MachineNumber !=null : "飞机机型号不能为空" ;
		assert Size > 50 && Size < 600 : "飞机承载人数必须在[50,600]" ;
		assert Age > 0 && Age < 30 : "飞机机龄必须在[0,30]" ;
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
		assert machineNumber != null : "飞机编号不能为空" ;
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
		assert size > 50 && Size < 600 : "飞机承载人数必须在[50,600]" ;
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
		assert age > 0 && Age < 30 : "飞机机龄必须在[0,30]" ;
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
