package com.gitee.swsk33.readandwrite;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import com.gitee.swsk33.readandwrite.param.CharSetValue;

/**
 * 网络实用类
 * 
 * @author swsk33
 *
 */
public class NetworkUtils {

	/**
	 * 发送GET请求
	 * 
	 * @param urlString 请求地址
	 * @return 请求结果
	 * @throws Exception 请求异常
	 */
	public static String sendGetRequest(String urlString) throws Exception {
		String urlProtocol; // 网址协议
		String urlContent; // 网址内容
		if (!urlString.startsWith("http://") && !urlString.startsWith("https://")) {
			urlProtocol = "http://";
			urlContent = urlString;
			urlString = urlProtocol + urlString;
		} else {
			urlProtocol = urlString.substring(0, urlString.indexOf("//") + 2);
			urlContent = urlString.substring(urlString.indexOf("//") + 2);
		}
		URL url = new URL(urlString);
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
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), CharSetValue.UTF_8));
		StringBuilder result = new StringBuilder("");
		String eachLine = "";
		while ((eachLine = reader.readLine()) != null) {
			result.append(eachLine + "\r\n");
		}
		reader.close();
		return result.substring(0, result.length() - 2);
	}

	/**
	 * 以一个特定的User-Agent发送GET请求
	 * 
	 * @param urlString 请求地址
	 * @param userAgent 模拟浏览器UA，可以自定义输入也可以使用com.gitee.swsk33.readandwrite.param.UserAgentValue类中的常用值
	 * @return 请求结果
	 * @throws Exception 请求异常
	 */
	public static String sendGetRequest(String urlString, String userAgent) throws Exception {
		String urlProtocol; // 网址协议
		String urlContent; // 网址内容
		if (!urlString.startsWith("http://") && !urlString.startsWith("https://")) {
			urlProtocol = "http://";
			urlContent = urlString;
			urlString = urlProtocol + urlString;
		} else {
			urlProtocol = urlString.substring(0, urlString.indexOf("//") + 2);
			urlContent = urlString.substring(urlString.indexOf("//") + 2);
		}
		URL url = new URL(urlString);
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
		connection.addRequestProperty("User-Agent", userAgent);
		connection.setConnectTimeout(5000);
		connection.connect();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), CharSetValue.UTF_8));
		StringBuilder result = new StringBuilder("");
		String eachLine = "";
		while ((eachLine = reader.readLine()) != null) {
			result.append(eachLine + "\r\n");
		}
		reader.close();
		return result.substring(0, result.length() - 2);
	}

	/**
	 * 发送一个自定义头的GET请求
	 * 
	 * @param urlString 请求地址
	 * @param headers   自定义的请求头
	 * @return 请求结果
	 * @throws Exception 请求异常
	 */
	public static String sendGetRequest(String urlString, Map<String, String> headers) throws Exception {
		if (!urlString.startsWith("http://") && !urlString.startsWith("https://")) {
			urlString = "http://" + urlString;
		}
		URL url = new URL(urlString);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		for (String key : headers.keySet()) {
			connection.addRequestProperty(key, headers.get(key));
		}
		connection.setConnectTimeout(5000);
		connection.connect();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), CharSetValue.UTF_8));
		StringBuilder result = new StringBuilder("");
		String eachLine = "";
		while ((eachLine = reader.readLine()) != null) {
			result.append(eachLine + "\r\n");
		}
		reader.close();
		return result.substring(0, result.length() - 2);
	}

	/**
	 * 发送POST请求
	 * 
	 * @param urlString   请求地址
	 * @param contentType 内容类型，可以自定义也可以使用com.gitee.swsk33.readandwrite.param.RequestContentType中的常用值
	 * @param requestBody 请求体，需要注意的是不同的内容类型有不同的请求体格式，例如application/x-www-form-urlencoded中请求体通常是：键1=值1&amp;键2=值2&amp;...
	 * @return 请求结果
	 * @throws Exception 请求错误
	 */
	public static String sendPostRequest(String urlString, String contentType, String requestBody) throws Exception {
		String urlProtocol; // 网址协议
		String urlContent; // 网址内容
		if (!urlString.startsWith("http://") && !urlString.startsWith("https://")) {
			urlProtocol = "http://";
			urlContent = urlString;
			urlString = urlProtocol + urlString;
		} else {
			urlProtocol = urlString.substring(0, urlString.indexOf("//") + 2);
			urlContent = urlString.substring(urlString.indexOf("//") + 2);
		}
		URL url = new URL(urlString);
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
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.connect();
		OutputStreamWriter output = new OutputStreamWriter(connection.getOutputStream(), CharSetValue.UTF_8);
		output.write(requestBody);
		output.flush();
		output.close();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), CharSetValue.UTF_8));
		StringBuilder result = new StringBuilder("");
		String eachLine = "";
		while ((eachLine = reader.readLine()) != null) {
			result.append(eachLine + "\r\n");
		}
		reader.close();
		return result.substring(0, result.length() - 2);
	}

	/**
	 * 以一个特定的User-Agent发送POST请求
	 * 
	 * @param urlString   请求地址
	 * @param contentType 内容类型，可以自定义也可以使用com.gitee.swsk33.readandwrite.param.RequestContentType中的常用值
	 * @param requestBody 请求体，需要注意的是不同的内容类型有不同的请求体格式，例如application/x-www-form-urlencoded中请求体通常是：键1=值1&amp;键2=值2&amp;...
	 * @return 请求结果
	 * @throws Exception 请求错误
	 */
	public static String sendPostRequest(String urlString, String contentType, String requestBody, String userAgent) throws Exception {
		String urlProtocol; // 网址协议
		String urlContent; // 网址内容
		if (!urlString.startsWith("http://") && !urlString.startsWith("https://")) {
			urlProtocol = "http://";
			urlContent = urlString;
			urlString = urlProtocol + urlString;
		} else {
			urlProtocol = urlString.substring(0, urlString.indexOf("//") + 2);
			urlContent = urlString.substring(urlString.indexOf("//") + 2);
		}
		URL url = new URL(urlString);
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
		connection.addRequestProperty("User-Agent", userAgent);
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.connect();
		OutputStreamWriter output = new OutputStreamWriter(connection.getOutputStream(), CharSetValue.UTF_8);
		output.write(requestBody);
		output.flush();
		output.close();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), CharSetValue.UTF_8));
		StringBuilder result = new StringBuilder("");
		String eachLine = "";
		while ((eachLine = reader.readLine()) != null) {
			result.append(eachLine + "\r\n");
		}
		reader.close();
		return result.substring(0, result.length() - 2);
	}

	/**
	 * 发送自定义请求头的POST请求
	 * 
	 * @param urlString   请求地址
	 * @param headers     自定义的请求头
	 * @param requestBody 请求体，需要注意的是不同的内容类型有不同的请求体格式，例如application/x-www-form-urlencoded中请求体通常是：键1=值1&amp;键2=值2&amp;...
	 * @return 请求结果
	 * @throws Exception 请求错误
	 */
	public static String sendPostRequest(String urlString, Map<String, String> headers, String requestBody) throws Exception {
		if (!urlString.startsWith("http://") && !urlString.startsWith("https://")) {
			urlString = "http://" + urlString;
		}
		URL url = new URL(urlString);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		for (String key : headers.keySet()) {
			connection.addRequestProperty(key, headers.get(key));
		}
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.connect();
		OutputStreamWriter output = new OutputStreamWriter(connection.getOutputStream(), CharSetValue.UTF_8);
		output.write(requestBody);
		output.flush();
		output.close();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), CharSetValue.UTF_8));
		StringBuilder result = new StringBuilder("");
		String eachLine = "";
		while ((eachLine = reader.readLine()) != null) {
			result.append(eachLine + "\r\n");
		}
		reader.close();
		return result.substring(0, result.length() - 2);
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
		String urlProtocol; // 网址协议
		String urlContent; // 网址内容
		if (!url.startsWith("http://") && !url.startsWith("https://")) {
			urlProtocol = "http://";
			urlContent = url;
			url = urlProtocol + url;
		} else {
			urlProtocol = url.substring(0, url.indexOf("//") + 2);
			urlContent = url.substring(url.indexOf("//") + 2);
		}
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
		connection.connect();
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
	 * @param userAgent 模拟浏览器UA，可以自定义输入也可以使用com.gitee.swsk33.readandwrite.param.UserAgentValue类中的常用值
	 * @return 是否下载成功
	 * @throws Exception 网络异常，文件写入异常
	 */
	public static boolean downloadFile(String url, String filePath, String userAgent) throws Exception {
		String urlProtocol; // 网址协议
		String urlContent; // 网址内容
		if (!url.startsWith("http://") && !url.startsWith("https://")) {
			urlProtocol = "http://";
			urlContent = url;
			url = urlProtocol + url;
		} else {
			urlProtocol = url.substring(0, url.indexOf("//") + 2);
			urlContent = url.substring(url.indexOf("//") + 2);
		}
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
		connection.connect();
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
	 * 以自定义的请求头，从网络上下载文件
	 * 
	 * @param url      网络文件地址
	 * @param filePath 下载保存到位置
	 * @param headers  自定义的请求头
	 * @return 是否下载成功
	 * @throws Exception 网络异常，文件写入异常
	 */
	public static boolean downloadFile(String url, String filePath, Map<String, String> headers) throws Exception {
		if (!url.startsWith("http://") && !url.startsWith("https://")) {
			url = "http://" + url;
		}
		URL netUrl = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) netUrl.openConnection();
		for (String key : headers.keySet()) {
			connection.addRequestProperty(key, headers.get(key));
		}
		connection.setConnectTimeout(5000);
		connection.connect();
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