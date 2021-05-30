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
	
	Person(String name){//初始化
		this.name = name ;
	}
	/**
	 * Get the name of the Person
	 * @return 名字
	 */
	public String getName() {
		return this.name;
	}
	@Override
	public boolean equals(Object other) {//重写equals方法
		if(this == other)
			return true;
		if(other instanceof Person ) {//判断是不是person的实例
			Person that = (Person) other ; 
			return this.getName()==that.getName();
		}
			return false ;
	}
	@Override
	public int hashCode() {//重写hashCode方法
		int result = 1 ;
		result = 31 * result + this.name.hashCode() ;
		return result;
		
	}


}
