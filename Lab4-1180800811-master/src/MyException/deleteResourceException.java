package MyException;

public class deleteResourceException extends Exception{
	/**
	 * 删除资源存在异常:存在计划项当前正在使用资源r
	 * @param <R> 资源的泛型
	 * @param r 待删除的资源
	 */
	public <R> deleteResourceException(R r) {
		super("删除资源异常: 有尚未结束的计划项正在使用该资源:" + r.toString()) ;
	}
}
