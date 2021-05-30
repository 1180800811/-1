package Resource;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

public class MultipleSortedResourceEntryImpl<R> implements MultipleSortedResourceEntry<R> {
	//
	// RI:
	//	r is not null  , 
	//
	//Abstraction function:
	//����һ�ܷɻ�����ȷ���ķɻ���š����ͺš���λ���ͻ���
	
	//Rep:
	// all fields are private
	// there is no exposure of the field
	
	private List<R> r = new ArrayList<R>();
	
	
	public void checkRep() {
		assert r != null ;
	}
	/**
	 * �޲ι�����
	 */
	public MultipleSortedResourceEntryImpl() {
		super();
	}
	/**
	 * ������
	 * @param r   �����Դ���б�
	 */
	public MultipleSortedResourceEntryImpl(List<R> r) {
		super();
		this.r = r;
		checkRep();
	}
	@Override
	public void setResource(List<R> r) {
			if(r != null)
				this.r = r ; 
	}
	@Override
	public List<R> getResource() {
		if(r != null) {
			List<R> r1 = new ArrayList<R>() ;
			r1.addAll(r);
			return r1;
		}
		return null ;

	}
	@Override
	public void addResource(R resource  ) {
		if(resource != null)
			r.add(resource);
		
		
	}
	@Override
	public void deleteResource(R resource) {
		if(resource != null )
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MultipleSortedResourceEntryImpl other = (MultipleSortedResourceEntryImpl) obj;
		if (r == null) {
			if (other.r != null)
				return false;
		} else if (!r.equals(other.r))
			return false;
		return true;
	}



}
