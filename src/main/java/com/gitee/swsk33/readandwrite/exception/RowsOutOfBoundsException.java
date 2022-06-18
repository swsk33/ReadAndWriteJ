package com.gitee.swsk33.readandwrite.exception;

/**
 * 行数越界异常，当读取文件指定的行数超过其本有的行数时抛出此异常
 */
public class RowsOutOfBoundsException extends Exception {

	/**
	 * 行数越界异常，当读取文件指定的行数超过其本有的行数时抛出此异常
	 *
	 * @param msg 抛出异常的信息
	 */
	public RowsOutOfBoundsException(String msg) {
		super(msg);
	}

}