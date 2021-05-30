package P2;

public class Person {
	
	private String name ;
	
	// Abstraction Function:
	//	Represent a name of a Person that is not null 
	//
	//Representation invariant:
	//	name != null 
	//
	//Safety from rep exposure:
	// name is immutable so there is no need for getName() to make defensive copy 
	// the field of name is private 
	
	Person(String name){//��ʼ��
		this.name = name ;
	}
	/**
	 * Get the name of the Person
	 * @return ����
	 */
	public String getName() {
		return this.name;
	}
	@Override
	public boolean equals(Object other) {//��дequals����
		if(this == other)
			return true;
		if(other instanceof Person ) {//�ж��ǲ���person��ʵ��
			Person that = (Person) other ; 
			return this.getName()==that.getName();
		}
			return false ;
	}
	@Override
	public int hashCode() {//��дhashCode����
		int result = 1 ;
		result = 31 * result + this.name.hashCode() ;
		return result;
		
	}


}
