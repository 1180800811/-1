package Resource;

public interface SingleDistinguishResourceEntry<R> {
	/**
	 * ������Դ
	 * @param r  ��Դ����(Teacher��Plane��Railway)
	 */
	public void setResource(R r);
	
	/**
	 * �����Դ
	 * @return  ��Դ
	 */
	public R getResource() ;
}
