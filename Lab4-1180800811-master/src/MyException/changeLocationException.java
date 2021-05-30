package MyException;

import Location.Location;

public class changeLocationException extends Exception{
	
	/**
	 * 更改计划项的状态异常
	 * @param loc1 原始位置
	 * @param loc2 更改后的位置
	 */
	public changeLocationException(Location loc1 , Location loc2) {
		super("改变位置存在异常: 将位置:" + loc1.getName() + "变更为位置:" + loc2.getName() + "后，计划项之间存在位置冲突!");
	}
}
