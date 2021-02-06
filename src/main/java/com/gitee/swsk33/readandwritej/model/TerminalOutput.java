package com.gitee.swsk33.readandwritej.model;

/**
 * 终端输出数据模型
 * 
 * @author swsk33
 *
 */
public class TerminalOutput {

	/**
	 * 标准输出流
	 */
	private String standardOutput = "";

	/**
	 * 标准错误流
	 */
	private String standardError = "";

	public String getStandardOutput() {
		return standardOutput;
	}

	public void setStandardOutput(String standardOutput) {
		this.standardOutput = standardOutput;
	}

	public String getStandardError() {
		return standardError;
	}

	public void setStandardError(String standardError) {
		this.standardError = standardError;
	}

}