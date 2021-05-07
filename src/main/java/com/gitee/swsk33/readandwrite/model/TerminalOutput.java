package com.gitee.swsk33.readandwrite.model;

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

	/**
	 * 命令是否运行完成
	 */
	private boolean done = false;

	/**
	 * 得到标准输出流结果
	 * 
	 * @return 标准输出
	 */
	public String getStandardOutput() {
		return standardOutput;
	}

	public void setStandardOutput(String standardOutput) {
		this.standardOutput = standardOutput;
	}

	public void appendStandardOutput(String content) {
		this.standardOutput = this.standardOutput + content + "\r\n";
	}

	/**
	 * 得到标准错误流结果
	 * 
	 * @return 标准错误输出
	 */
	public String getStandardError() {
		return standardError;
	}

	public void setStandardError(String standardError) {
		this.standardError = standardError;
	}

	public void appendStandardError(String content) {
		this.standardError = this.standardError + content + "\r\n";
	}

	/**
	 * 该命令是否已经执行完成
	 * 
	 * @return 是否完成
	 */
	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

}