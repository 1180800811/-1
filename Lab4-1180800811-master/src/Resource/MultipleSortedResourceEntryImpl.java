package Resource;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MultipleSortedResourceEntryImpl<R> implements MultipleSortedResourceEntry<R> {
	//
	// RI:
	//	r is not null  , 
	//
	//Abstraction function:
	//代表一架飞机，有确定的飞机编号、机型号、座位数和机龄
	
	//Safety from rep exposure:
	// all fields are private
	// there is no exposure of the field
	
	private List<R> r = new ArrayList<R>();
	
	
	public void checkRep() {
		assert this.r != null : "资源不能为空";
	}
	/**
	 * 无参构造器
	 */
	public MultipleSortedResourceEntryImpl() {
		super();
	}
	/**
	 * 构造器
	 * @param r   多个资源的列表
	 */
	public MultipleSortedResourceEntryImpl(List<R> r) {
		super();
		this.r = r;
		checkRep();
	}
	@Override
	public void setResource(List<R> r) {
		this.r = r ; 
		checkRep() ;
	}
	@Override
	public List<R> getResource() {
		
		List<R> r1 = new ArrayList<R>() ;
		r1.addAll(r);
		checkRep();
		return r1;



	}
	@Override
	public void addResource(R resource  ) {
		assert resource != null : "待增加的资源不能为空" ;
			r.add(resource);
			checkRep();
	}
	@Override
	public void deleteResource(R resource) {
		assert resource != null : "待删除的资源不能为空" ;
			r.remove(resource);
	}

	@Override
	public String toString() {
		return "[r=" + r + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((r == null) ? 0 : r.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MultipleSortedResourceEntryImpl other = (MultipleSortedResourceEntryImpl) obj;
		return r.equals(other.r);
	}



}
