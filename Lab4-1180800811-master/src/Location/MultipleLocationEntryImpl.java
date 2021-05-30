package Location;

// a immutable class ���λ�ü����Ӧ��ʱ��
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MultipleLocationEntryImpl implements MultipleLocationEntry  {
	
	//AI:
	//all the value of the locations is not null 
	//the size of locations > 2
	//
	
	//Abstraction function:
	//������λ��
	//ÿ��λ����ȷ����
	//	
	//Safety from rep exposure:
	// the field is private
	// List is immutable so getLocation make defensive copy
	//
	private List<Location> locations  = new ArrayList<Location>();//���λ�õ��б�
	

	
	
	public void checkRep() {
		assert locations != null ;
		assert locations.size() != 0 ;
		Set<Location> l = new HashSet<>() ;
		for(Location loc : locations) {
			l.add(loc);
		}
		assert l.size() == locations.size() : "���λ���в������ظ���λ��" ;
	}
	@Override
	public boolean addLocation(Location loc) {
		assert loc != null : "�����λ�ò���Ϊ��" ;
		if(!locations.contains(loc)) {
			locations.add(loc);
			checkRep();
			return true;
		}else {
			return false;
		}
	
	}
	/**
	 * �޲���������
	 */
	public MultipleLocationEntryImpl() {
		
	}
	
	/**
	 * ������
	 * @param locations һϵ��λ��
	 */
	public MultipleLocationEntryImpl(List<Location> locations) {
		super();
		this.locations = locations;
		checkRep();
	}
	
	/**
	 * 
	 * @param locations һϵ��λ��
	 */
	@Override
	public void setLocations(List<Location> locs) {
		List<Location> lc = new ArrayList<Location>() ;
		lc.addAll(locs);
		locations = lc ;
		checkRep();
	}


	@Override
	public List<Location> getLocation() {
		List<Location> l  = new ArrayList<Location>();//���λ�õ��б�
		l.addAll(locations);
		assert l != null ;
		return l ;
	}
	@Override
	public String toString() {
		return "locations=" + locations ;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((locations == null) ? 0 : locations.hashCode());
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
		MultipleLocationEntryImpl other = (MultipleLocationEntryImpl) obj;
		return locations.equals(other.locations);
	}

	
}
