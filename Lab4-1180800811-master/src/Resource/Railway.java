package Resource;
// a immutable class 地铁
public class Railway {

	//
	// RI:
	//all the field is not null
	// Size and Year is a positive Integer
	//
	//Abstraction function:
	//代表一个地铁，有确定的编号、车厢类型、定员数和出厂年份
	
	//Safety from rep exposure:
	// all fields are private
	// there is no exposure of the field	
	private String Number ;//编号
	private Type type ; //车厢类型
	private int Size ; //定员数
	private int Year ;//出厂年份
	
	
	public void checkRep() {
		assert Number != null : "车厢的编号不能为空";
		assert type != null : "车厢的类型不能为空";
		assert Size > 0 : "车厢定员数必须大于0";
		assert Year < 2021 : "车厢年份不符合要求";
	}
	/**
	 * 初始化一个地铁
	 * @param number 编号
	 * @param type  车厢类型
	 * @param size  定员数
	 * @param year  出厂年份
	 */
	public Railway(String number, Type type, int size, int year) {
		Number = number;
		this.type = type;
		Size = size;
		Year = year;
		checkRep();
	}
	/**
	 * 获得编号
	 * @return  编号
	 */
	public String getNumber() {
		return Number;
	}
	/**
	 * 设置编号
	 * @param number  编号
	 */
	public void setNumber(String number) {
		assert number != null : "车厢的编号不能为空";
		Number = number;
	}
	
	/**
	 * 获得车厢类型
	 * @return  车厢类型
	 */
	public Type getType() {
		return type;
	}
	/**
	 * 设置车厢类型
	 * @param type  车厢类型
	 */
	public void setType(Type type) {
		assert type != null : "车厢的类型不能为空";
		this.type = type;
	}
	/**
	 * 获得定员数
	 * @return 定员数
	 */
	public int getSize() {
		return Size;
	}
	/**
	 * 设置定员数
	 * @param size   定员数
 	 */
	public void setSize(int size) {
		assert size > 0 : "车厢定员数必须大于0";
		Size = size;
	}
	/**
	 * 获得出厂年份
	 * @return
	 */
	public int getYear() {
		return Year;
	}
	/**
	 * 设置出厂年份
	 * @param year 出厂年份
	 */
	public void setYear(int year) {
		assert year < 2021 : "车厢年份不符合要求";
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
