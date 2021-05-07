package com.gitee.swsk33.readandwrite.exception;

public class SizeOutOfBoundsException extends Exception {

	/**
	 * 生成的序列id
	 */
	private static final long serialVersionUID = -8326286617311902061L;

	/**
	 * 文件大小溢出异常：当读取文件大小超过可读大小时抛出此异常
	 * 
	 * @param msg 抛出异常的信息
	 */
	public SizeOutOfBoundsException(String msg) {
		super(msg);
	}

}
