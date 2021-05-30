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
	//����һ�ܷɻ�����ȷ���ķɻ���š����ͺš���λ���ͻ���
	
	//Safety from rep exposure:
	// all fields are private
	// there is no exposure of the field
	
	private List<R> r = new ArrayList<R>();
	
	
	public void checkRep() {
		assert this.r != null : "��Դ����Ϊ��";
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
		assert resource != null : "�����ӵ���Դ����Ϊ��" ;
			r.add(resource);
			checkRep();
	}
	@Override
	public void deleteResource(R resource) {
		assert resource != null : "��ɾ������Դ����Ϊ��" ;
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
