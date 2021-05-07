package com.gitee.swsk33.readandwrite;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.gitee.swsk33.readandwrite.model.TerminalOutput;

/**
 * 用于命令行的执行和读取。
 * 
 * @author swsk33
 *
 */
public class TerminalUtils {

	/**
	 * 向终端输入（运行）一条命令并获取输出结果，注意这是同步方法，会堵塞当前线程
	 * 
	 * @param command 运行的命令
	 * @return 标准结果
	 * @throws Exception 输入输出异常
	 */
	public static TerminalOutput getCommandOutput(String command) throws Exception {
		TerminalOutput result = new TerminalOutput();
		Process process = Runtime.getRuntime().exec(command);
		BufferedReader stdOutReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		BufferedReader stdErrReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
		String stdOutLine = "";
		String stdErrLine = "";
		String outResult = "";
		String errResult = "";
		while ((stdOutLine = stdOutReader.readLine()) != null) {
			outResult = outResult + stdOutLine + "\r\n";
		}
		while ((stdErrLine = stdErrReader.readLine()) != null) {
			errResult = errResult + stdErrLine + "\r\n";
		}
		stdOutReader.close();
		stdErrReader.close();
		process.waitFor();
		result.setStandardOutput(outResult);
		result.setStandardError(errResult);
		return result;
	}

	/**
	 * 向终端输入（运行）一条命令并以指定的编码获取输出结果，注意这是同步方法，会堵塞当前线程<br>
	 * 可用的编码常量值有：
	 * <ul>
	 * <li>CharSetValue.US_ASCII：<strong>US-ASCII</strong></li>
	 * <li>CharSetValue.ISO_8859_1：<strong>ISO-8859-1</strong></li>
	 * <li>CharSetValue.GBK：<strong>GBK</strong></li>
	 * <li>CharSetValue.UTF_8：<strong>UTF-8</strong></li>
	 * <li>CharSetValue.UTF_16：<strong>UTF-16</strong></li>
	 * <li>CharSetValue.UTF_16BE：<strong>UTF-16BE</strong></li>
	 * <li>CharSetValue.UTF_16LE：<strong>UTF-16LE</strong></li>
	 * </ul>
	 * 
	 * @param command 运行的命令
	 * @param charSet 编码格式
	 * @return 标准结果
	 * @throws Exception 输入输出异常
	 * 
	 */
	public static TerminalOutput getCommandOutput(String command, String charSet) throws Exception {
		TerminalOutput result = new TerminalOutput();
		Process process = Runtime.getRuntime().exec(command);
		BufferedReader stdOutReader = new BufferedReader(new InputStreamReader(process.getInputStream(), charSet));
		BufferedReader stdErrReader = new BufferedReader(new InputStreamReader(process.getErrorStream(), charSet));
		String stdOutLine = "";
		String stdErrLine = "";
		String outResult = "";
		String errResult = "";
		while ((stdOutLine = stdOutReader.readLine()) != null) {
			outResult = outResult + stdOutLine + "\r\n";
		}
		while ((stdErrLine = stdErrReader.readLine()) != null) {
			errResult = errResult + stdErrLine + "\r\n";
		}
		stdOutReader.close();
		stdErrReader.close();
		process.waitFor();
		result.setStandardOutput(outResult);
		result.setStandardError(errResult);
		return result;
	}

}