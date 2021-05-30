package MyException;

public class fileChooseException extends Exception {
	/**
	 * 读取文件异常
	 * @param message 提示信息
	 */
	public fileChooseException(String message) {
		super(message) ;
	}
}
