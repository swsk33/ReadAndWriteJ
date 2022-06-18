package com.gitee.swsk33.readandwrite.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 终端输出数据模型
 */
@Getter
@Setter
public class TerminalOutput {

	/**
	 * 标准输出流
	 */
	private String standardOutput = "";

	/**
	 * 标准错误流
	 */
	private String standardError = "";

	/**
	 * 命令是否运行完成
	 */
	private boolean done;

}