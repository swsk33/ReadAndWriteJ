package com.gitee.swsk33.readandwrite.exception;

/**
 * 文件大小溢出异常：当读取文件大小超过可读大小时抛出此异常
 */
public class SizeOutOfBoundsException extends Exception {

	/**
	 * 文件大小溢出异常：当读取文件大小超过可读大小时抛出此异常
	 *
	 * @param msg 抛出异常的信息
	 */
	public SizeOutOfBoundsException(String msg) {
		super(msg);
	}

}