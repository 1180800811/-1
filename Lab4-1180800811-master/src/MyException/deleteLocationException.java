package MyException;

import Location.Location;

public class deleteLocationException extends Exception{
	/**
	 * 删除位置存在异常
	 * @param loc 待删除的位置
	 */
	public deleteLocationException(Location loc) {
		super("删除位置异常: " + "有尚且未结束的计划项在位置:" + loc.getName() + "执行");
	}
}
