package MyException;

public class wrongDependenceException extends Exception{
	/**
	 * 错误的依赖关系的异常
	 * @param message 提示信息
	 */
	public wrongDependenceException(String message) {
		super(message);
	}
	public wrongDependenceException() {}
}
