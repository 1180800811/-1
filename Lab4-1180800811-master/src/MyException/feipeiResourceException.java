package MyException;

public class feipeiResourceException extends Exception{
	/**
	 * 分配资源后存在资源独占冲突
	 * @param <R> 分配的资源的泛型
	 * @param r 待分配的资源
	 */
	public <R> feipeiResourceException(R r) {
		super("分配该资源:"+r.toString() + "后，计划项之间存在资源独占冲突");
	}
}
