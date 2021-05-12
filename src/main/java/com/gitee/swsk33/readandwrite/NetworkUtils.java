package com.gitee.swsk33.readandwrite;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 网络实用类
 * 
 * @author swsk33
 *
 */
public class NetworkUtils {

	/**
	 * 发送get请求
	 * 
	 * @param urlString 请求地址
	 * @return 请求结果
	 * @throws Exception 请求异常
	 */
	public static String sendGetEquest(String urlString) throws Exception {
		URL url = new URL(urlString);
		String urlProtocol = urlString.substring(0, urlString.indexOf("//") + 2); // 网址协议
		String urlContent = urlString.substring(urlString.indexOf("//") + 2); // 网址内容
		String urlHost;
		if (urlContent.contains("/")) {
			urlHost = urlContent.substring(0, urlContent.indexOf("/"));
		} else {
			urlHost = urlContent;
		}
		String urlReferer = urlProtocol + urlHost + "/";
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.addRequestProperty("Referer", urlReferer);
		connection.addRequestProperty("Host", urlHost);
		connection.setConnectTimeout(5000);
		connection.connect();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder result = new StringBuilder("");
		String eachLine = "";
		while ((eachLine = reader.readLine()) != null) {
			result.append(eachLine + "\r\n");
		}
		reader.close();
		return result.toString();
	}

	/**
	 * 从网络上下载文件
	 * 
	 * @param url      网络文件地址
	 * @param filePath 下载保存到位置
	 * @return 是否下载成功
	 * @throws Exception 网络异常，文件写入异常
	 */
	public static boolean downloadFile(String url, String filePath) throws Exception {
		String urlProtocol = url.substring(0, url.indexOf("//") + 2); // 网址协议
		String urlContent = url.substring(url.indexOf("//") + 2); // 网址内容
		String urlHost;
		if (urlContent.contains("/")) {
			urlHost = urlContent.substring(0, urlContent.indexOf("/"));
		} else {
			urlHost = urlContent;
		}
		String urlReferer = urlProtocol + urlHost + "/";
		URL netUrl = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) netUrl.openConnection();
		connection.addRequestProperty("Referer", urlReferer);
		connection.addRequestProperty("Host", urlHost);
		connection.setConnectTimeout(5000);
		InputStream inputStream = connection.getInputStream();
		ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int index = 0;
		while ((index = inputStream.read(buffer)) != -1) {
			byteOutput.write(buffer, 0, index);
		}
		byteOutput.close();
		inputStream.close();
		return BinaryUtils.writeBinaryFile(filePath, byteOutput.toByteArray());
	}

	/**
	 * 以一个特定的User-Agent从网络上下载文件
	 * 
	 * @param url       网络文件地址
	 * @param filePath  下载保存到位置
	 * @param userAgent 模拟浏览器UA，可以自定义输入也可以使用com.gitee.swsk33.readandwrite.util.UserAgentValue类中的常用值
	 * @return 是否下载成功
	 * @throws Exception 网络异常，文件写入异常
	 */
	public static boolean downloadFile(String url, String filePath, String userAgent) throws Exception {
		String urlProtocol = url.substring(0, url.indexOf("//") + 2); // 网址协议
		String urlContent = url.substring(url.indexOf("//") + 2); // 网址内容
		String urlHost;
		if (urlContent.contains("/")) {
			urlHost = urlContent.substring(0, urlContent.indexOf("/"));
		} else {
			urlHost = urlContent;
		}
		String urlReferer = urlProtocol + urlHost + "/";
		URL netUrl = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) netUrl.openConnection();
		connection.addRequestProperty("User-Agent", userAgent);
		connection.addRequestProperty("Referer", urlReferer);
		connection.addRequestProperty("Host", urlHost);
		connection.setConnectTimeout(5000);
		InputStream inputStream = connection.getInputStream();
		ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int index = 0;
		while ((index = inputStream.read(buffer)) != -1) {
			byteOutput.write(buffer, 0, index);
		}
		byteOutput.close();
		inputStream.close();
		return BinaryUtils.writeBinaryFile(filePath, byteOutput.toByteArray());
	}

}