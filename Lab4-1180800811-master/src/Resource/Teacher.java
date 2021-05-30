package Resource;

//A immutable class һ����ʦ�ĸ�����Ϣ
public class Teacher {
	private String IdNumber ;//��ʦ�����֤��
	private String Name ;//��ʦ������
	private boolean Sex ; //��ʦ���Ա�,true��ʾ�У�false��ʾŮ
	private String Title ;//��ʦְ��
	
	
	// RI:
	//all the field is not null
	// IdNumber[i] is a Integer between 0 to 9 , 0 <= i <= IdNumber.length-1 
	//
	//
	//Abstraction function:
	//����һ����ʦ����Ϣ����Ψһ�����֤�š����֡��Ա�ְ��
	
	//Safety from rep exposure:
	// all fields are private
	// there is no exposure of the field
	/**
	 * ��ʼ��
	 * @param idNumber ��ʦ�����֤��
	 * @param name	��ʦ������
	 * @param sex	��ʦ���Ա�
	 * @param title	��ʦְ��
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
	 * ��ȡ��ʦ�����֤��
	 * @return ��ʦ�����֤��
	 */
	public String getIdNumber() {
		return IdNumber;
	}
	/**
	 * ���ý�ʦ�����֤��
	 * @param idNumber ��ʦ�����֤��
	 */
	public void setIdNumber(String idNumber) {
		IdNumber = idNumber;
	}
	/**
	 * ��ý�ʦ������
	 * @return ��ʦ������
	 */
	public String getName() {
		return Name;
	}
	/**
	 * ���ý�ʦ������
	 * @param name ��ʦ������
	 */
	public void setName(String name) {
		Name = name;
	}
	/**
	 * ��ý�ʦ���Ա�
	 * @return	true��ʾ�У�false��ʾŮ
	 */
	public boolean isSex() {
		return Sex;
	}
	/**
	 * ���ý�ʦ���Ա�
	 * @param sex true��ʾ�У�false��ʾŮ
	 */
	public void setSex(boolean sex) {
		Sex = sex;
	}
	
	/**
	 * ��ý�ʦ��ְ��
	 * @return  ��ʦ��ְ��
	 */
	public String getTitle() {
		return Title;
	}
	
	/**
	 * ���ý�ʦ��ְ��
	 * @param title ��ʦ��ְ��
	 */
	public void setTitle(String title) {
		Title = title;
	}
	
	/**
	 * ��дtoString����
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
