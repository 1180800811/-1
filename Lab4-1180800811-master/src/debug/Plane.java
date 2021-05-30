package debug;

public class Plane {
	private String planeNo; // �ɻ�Ψһ���
	private String planeType; // ���ͣ�����A350,B787,C929
	private int seatsNum; // ��λ��
	private double planeAge;// ����

	@Override
	public boolean equals(Object another) {
		if (another == null)
			return false;
		if (!(another instanceof Plane))
			return false;
		Plane ap = (Plane) another;
		if (this.getPlaneNo().equals(ap.getPlaneNo()))
			return true;
		return false;
	}

	@Override
	public int hashCode() {
		return this.planeNo.hashCode();
	}

	public String getPlaneNo() {
		assert planeNo != null ;
		return planeNo;
	}

	public void setPlaneNo(String planeNo) {
		assert planeNo != null ;
		this.planeNo = planeNo;
	}

	public String getPlaneType() {
		return planeType;
	}

	public void setPlaneType(String planeType) {
		this.planeType = planeType;
	}

	public int getSeatsNum() {
		return seatsNum;
	}

	public void setSeatsNum(int seatsNum) {
		this.seatsNum = seatsNum;
	}

	public double getPlaneAge() {
		return planeAge;
	}

	public void setPlaneAge(double planeAge) {
		this.planeAge = planeAge;
	}
}
