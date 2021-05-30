package Resource;

public class SingleDistinguishResourceEntryImpl<R> implements SingleDistinguishResourceEntry<R> {
	//
	// RI:
	//r not null
	// Size and Age is a positive Integer
	//
	//Abstraction function:
	//代表单个资源
	
	//Rep:
	// all fields are private
	// there is no exposure of the field
	public void checkRep() {
		assert r != null ;
	}
	private R r ;
	@Override
	public void setResource(R r) {
			this.r =r ;

	}
	
	@Override
	public R getResource() {
		return r;
	}

	public SingleDistinguishResourceEntryImpl(R r) {
		super();
		this.r = r;
		checkRep(); 
	}

	public SingleDistinguishResourceEntryImpl() {
		super();
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
		SingleDistinguishResourceEntryImpl other = (SingleDistinguishResourceEntryImpl) obj;
		if (r == null) {
			if (other.r != null)
				return false;
		} else if (!r.equals(other.r))
			return false;
		return true;
	}
	

}
