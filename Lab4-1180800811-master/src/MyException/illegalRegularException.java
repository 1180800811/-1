package MyException;

public class illegalRegularException extends Exception{
	/**
	 * 格式不符合要求的异常
	 * @param message 提示信息
	 */
	public illegalRegularException(String message) {
		super(message);
	}
}
