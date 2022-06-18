package com.gitee.swsk33.readandwrite;

/**
 * 文件文本对比器
 */
public class StringComparer {

	/**
	 * 比较某一行内容是否与被比较字符串一致
	 *
	 * @param filepath     待比较的文件相对路径/绝对路径
	 * @param lineCompared 被比较的字符串
	 * @return 文件中有一行的内容和被比较字符串一致时返回true
	 */
	public static boolean compareLine(String filepath, String lineCompared) throws Exception {
		String[] fileContent = TextFileReader.readFileToArray(filepath);
		for (String line : fileContent) {
			if (line.equals(lineCompared)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断文本文档内是否包含被比较的字符串
	 *
	 * @param filepath     待比较的文件相对路径/绝对路径
	 * @param textCompared 被比较的字符串
	 * @return 文件中某一行的内容包含被比较的字符串时返回true
	 */
	public static boolean compareText(String filepath, String textCompared) throws Exception {
		String[] fileContent = TextFileReader.readFileToArray(filepath);
		for (String line : fileContent) {
			if (line.contains(textCompared)) {
				return true;
			}
		}
		return false;
	}

}