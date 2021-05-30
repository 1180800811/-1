package Resource;

import java.util.List;

public interface MultipleSortedResourceEntry<R> {
	/**
	 * ���ö����Դ
	 * @param r  ��Դ�б�
	 */
	public void setResource(List<R> r ) ;
	
	/**
	 * �����Դ
	 * @param resource  ����ӵ���Դ
	 */
	public void addResource(R resource  ) ;
	
	/**
	 * ɾ����Դ
	 * @param resource ��ɾ������Դ
	 */
	public void deleteResource(R resource) ;
	
	
	public List<R> getResource() ;
	
}
