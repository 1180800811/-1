package Resource;

public interface SingleDistinguishResourceEntry<R> {
	/**
	 * 设置资源
	 * @param r  资源类型(Teacher、Plane或Railway)
	 */
	public void setResource(R r);
	
	/**
	 * 获得资源
	 * @return  资源
	 */
	public R getResource() ;
}
