package Resource;

//A immutable class 一个教师的个人信息
public class Teacher {
	private String IdNumber ;//教师的身份证号
	private String Name ;//教师的名字
	private boolean Sex ; //教师的性别,true表示男，false表示女
	private String Title ;//教师职称
	
	
	// RI:
	//all the field is not null
	// IdNumber[i] is a Integer between 0 to 9 , 0 <= i <= IdNumber.length-1 
	//
	//
	//Abstraction function:
	//代表一个教师的信息，有唯一的身份证号、名字、性别、职称
	
	//Safety from rep exposure:
	// all fields are private
	// there is no exposure of the field
	/**
	 * 初始化
	 * @param idNumber 教师的身份证号
	 * @param name	教师的名字
	 * @param sex	教师的性别
	 * @param title	教师职称
	 */
	
	public void checkRep() {
		assert IdNumber != null ;
		assert Name != null ;
		assert Title != null ;
	}
	public Teacher(String idNumber, String name, boolean sex, String title) {
		IdNumber = idNumber;
		Name = name;
		Sex = sex;
		Title = title;
		checkRep();
	}
	
	/**
	 * 获取教师的身份证号
	 * @return 教师的身份证号
	 */
	public String getIdNumber() {
		return IdNumber;
	}
	/**
	 * 设置教师的身份证号
	 * @param idNumber 教师的身份证号
	 */
	public void setIdNumber(String idNumber) {
		IdNumber = idNumber;
	}
	/**
	 * 获得教师的姓名
	 * @return 教师的姓名
	 */
	public String getName() {
		return Name;
	}
	/**
	 * 设置教师的姓名
	 * @param name 教师的姓名
	 */
	public void setName(String name) {
		Name = name;
	}
	/**
	 * 获得教师的性别，
	 * @return	true表示男，false表示女
	 */
	public boolean isSex() {
		return Sex;
	}
	/**
	 * 设置教师的性别
	 * @param sex true表示男，false表示女
	 */
	public void setSex(boolean sex) {
		Sex = sex;
	}
	
	/**
	 * 获得教师的职称
	 * @return  教师的职称
	 */
	public String getTitle() {
		return Title;
	}
	
	/**
	 * 设置教师的职称
	 * @param title 教师的职称
	 */
	public void setTitle(String title) {
		Title = title;
	}
	
	/**
	 * 重写toString方法
	 */
	@Override
	public String toString() {
		return "Teacher [IdNumber=" + IdNumber + ", Name=" + Name + ", Sex=" + Sex + ", Title=" + Title + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((IdNumber == null) ? 0 : IdNumber.hashCode());
		result = prime * result + ((Name == null) ? 0 : Name.hashCode());
		result = prime * result + (Sex ? 1231 : 1237);
		result = prime * result + ((Title == null) ? 0 : Title.hashCode());
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
		Teacher other = (Teacher) obj;
		if (IdNumber == null) {
			if (other.IdNumber != null)
				return false;
		} else if (!IdNumber.equals(other.IdNumber))
			return false;
		if (Name == null) {
			if (other.Name != null)
				return false;
		} else if (!Name.equals(other.Name))
			return false;
		if (Sex != other.Sex)
			return false;
		if (Title == null) {
			if (other.Title != null)
				return false;
		} else if (!Title.equals(other.Title))
			return false;
		return true;
	}


}
