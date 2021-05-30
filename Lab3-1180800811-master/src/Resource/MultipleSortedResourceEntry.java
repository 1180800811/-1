package Resource;

import java.util.List;

public interface MultipleSortedResourceEntry<R> {
	/**
	 * 设置多个资源
	 * @param r  资源列表
	 */
	public void setResource(List<R> r ) ;
	
	/**
	 * 添加资源
	 * @param resource  待添加的资源
	 */
	public void addResource(R resource  ) ;
	
	/**
	 * 删除资源
	 * @param resource 待删除的资源
	 */
	public void deleteResource(R resource) ;
	
	
	public List<R> getResource() ;
	
}
