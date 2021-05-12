package com.gitee.swsk33.readandwrite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.gitee.swsk33.readandwrite.exception.JSONParseException;

/**
 * 一个简单的JSON处理实用类
 * 
 * @author swsk33
 *
 */
public class JSONUtils {

	/**
	 * 读取以字符串形式储存的转义字符
	 */
	private static char getEscapeChar(char escapeChar) throws JSONParseException {
		if (escapeChar == '\"') {
			return '\"';
		}
		if (escapeChar == '\'') {
			return '\'';
		}
		if (escapeChar == '\\') {
			return '\\';
		}
		if (escapeChar == 'r') {
			return '\r';
		}
		if (escapeChar == 'n') {
			return '\n';
		}
		throw new JSONParseException("转义字符错误或不支持的转义字符");
	}

	/**
	 * 把原字符串中的转义符化为字符串储存形式
	 * 
	 * @param 原字符串
	 * @return 替换了转义符的String
	 */
	private static String replaceEscapeInString(String origin) {
		StringBuilder result = new StringBuilder("");
		for (int i = 0; i < origin.length(); i++) {
			char current = origin.charAt(i);
			if (current == '\r') {
				result.append("\\r");
			} else if (current == '\n') {
				result.append("\\n");
			} else if (current == '\"') {
				result.append("\\\"");
			} else if (current == '\'') {
				result.append("\\\'");
			} else if (current == '\\') {
				result.append("\\\\");
			} else {
				result.append(current);
			}
		}
		return result.toString();
	}

	/**
	 * 判断是否是可跳过字符
	 */
	private static boolean isUselessChar(char judgeChar) {
		if (judgeChar == ' ' || judgeChar == '\r' || judgeChar == '\n' || judgeChar == '	') {
			return true;
		}
		return false;
	}

	/**
	 * 引号包围字符串
	 */
	private static String surByQut(String str) {
		return "\"" + str + "\"";
	}

	/**
	 * 读取json字符串
	 * 
	 * @param jsonString json字符串
	 * @return Object数组，第一个值表示读取的结果对象Map类型，第二个值表示读取到了哪一位，int型
	 * @throws JSONParseException json解析错误
	 */
	private static Object[] jsonReader(String jsonString) throws JSONParseException {
		// 裁剪两端无用字符
		while (isUselessChar(jsonString.charAt(0))) {
			jsonString = jsonString.substring(1, jsonString.length());
		}
		while (isUselessChar(jsonString.charAt(jsonString.length() - 1))) {
			jsonString = jsonString.substring(0, jsonString.length() - 1);
		}
		if (!jsonString.startsWith("{") || !jsonString.endsWith("}")) {
			throw new JSONParseException("json必须以花括号开头或结尾！");
		}
		Object[] totalResult = new Object[2];
		Map<String, Object> result = new HashMap<String, Object>();
		boolean isParsingValue = false;
		int pointer = 1;
		String fieldTemp = null;
		while (pointer < jsonString.length()) {
			char currentChar = jsonString.charAt(pointer);
			if (!isParsingValue) { // 当前未在解析值（解析表达式部分）
				if (isUselessChar(currentChar) || currentChar == ',') { // 遇到空白字符或者逗号时往下一位
					pointer++;
				} else if (currentChar == '\"') { // 遇到"说明读取到属性
					StringBuilder field = new StringBuilder("");
					int index = 1;
					char eachChar;
					while ((eachChar = jsonString.charAt(pointer + index)) != '\"') {
						if (eachChar == '\\') {
							if (jsonString.charAt(pointer + index + 1) == 'u') { // Unicode字符直接放入
								field.append("\\u");
							} else {
								field.append(getEscapeChar(jsonString.charAt(pointer + index + 1)));
							}
							index = index + 2;
						} else {
							field.append(eachChar);
							index++;
						}
					}
					fieldTemp = field.toString();
					pointer = pointer + index + 1;
				} else if (currentChar == ':') { // 遇到:说明后面接的是值，开始解析值，并标记当前解析状态为解析值
					isParsingValue = true;
					pointer++;
					while (isUselessChar(jsonString.charAt(pointer))) {
						pointer++;
					}
				} else if (currentChar == '}') { // 遇到}说明json结束
					totalResult[0] = result;
					totalResult[1] = pointer + 1;
					return totalResult;
				} else {
					throw new JSONParseException("JSON语法错误！");
				}
			} else { // 当前在解析值
				Object[] parseResult = parseValue(jsonString, pointer);
				result.put(fieldTemp, parseResult[0]);
				pointer = (int) parseResult[1];
				fieldTemp = null;
				isParsingValue = false;
			}
		}
		throw new JSONParseException("非正常结尾！");
	}

	/**
	 * 解析JSON的值
	 * 
	 * @param jsonString   json字符串
	 * @param currentIndex 当前解析位置
	 * @return Object数组，第一个值为解析的值，第二个值表示解析完该值后的当前字符串解析位置，int型
	 * @throws JSONParseException json解析异常
	 */
	@SuppressWarnings("unchecked")
	private static Object[] parseValue(String jsonString, int currentIndex) throws JSONParseException {
		Object[] result = new Object[2];
		char currentChar = jsonString.charAt(currentIndex);
		int index = 0; // 子索引
		if (currentChar == '\"') { // 遇到"说明值为字符串类型
			StringBuilder stringValue = new StringBuilder("");
			index = 1;
			while (jsonString.charAt(currentIndex + index) != '\"') {
				if (jsonString.charAt(currentIndex + index) == '\\') {
					if (jsonString.charAt(currentIndex + index + 1) == 'u') { // Unicode直接放入
						stringValue.append("\\u");
					} else {
						stringValue.append(getEscapeChar(jsonString.charAt(currentIndex + index + 1)));
					}
					index = index + 2;
				} else {
					stringValue.append(jsonString.charAt(currentIndex + index));
					index++;
				}
			}
			result[0] = stringValue.toString();
			result[1] = currentIndex + index + 1;
			return result;
		}
		if (currentChar == 'n') { // 如果读取到n，则可能是null
			StringBuilder value = new StringBuilder("");
			for (index = 0; index < 4; index++) {
				value.append(jsonString.charAt(currentIndex + index));
			}
			if (value.toString().equals("null")) {
				result[0] = null;
				result[1] = currentIndex + index;
				return result;
			} else {
				throw new JSONParseException("关键变量错误！");
			}
		}
		if (currentChar == 't') { // 如果读取到t，则可能是true
			StringBuilder value = new StringBuilder("");
			for (index = 0; index < 4; index++) {
				value.append(jsonString.charAt(currentIndex + index));
			}
			if (value.toString().equals("true")) {
				result[0] = true;
				result[1] = currentIndex + index;
				return result;
			} else {
				throw new JSONParseException("关键变量错误！");
			}
		}
		if (currentChar == 'f') { // 如果读取到f，则可能是false
			StringBuilder value = new StringBuilder("");
			for (index = 0; index < 5; index++) {
				value.append(jsonString.charAt(currentIndex + index));
			}
			if (value.toString().equals("false")) {
				result[0] = false;
				result[1] = currentIndex + index;
				return result;
			} else {
				throw new JSONParseException("关键变量错误！");
			}
		}
		if (currentChar == '-' || (currentChar >= '0' && currentChar <= '9')) { // 如果读取到-或者0-9之间的数字，说明是整型或者浮点型
			StringBuilder value = new StringBuilder("");
			char eachChar;
			while ((eachChar = jsonString.charAt(currentIndex + index)) == '-' || ((eachChar = jsonString.charAt(currentIndex + index)) == '.') || ((eachChar = jsonString.charAt(currentIndex + index)) >= '0' && (eachChar = jsonString.charAt(currentIndex + index)) <= '9')) {
				value.append(eachChar);
				index++;
			}
			if (value.toString().contains(".")) {
				result[0] = Double.parseDouble(value.toString());
				result[1] = currentIndex + index;
			} else {
				result[0] = Integer.parseInt(value.toString());
				result[1] = currentIndex + index;
			}
			return result;
		}
		if (currentChar == '[') { // 如果遇到[，说明是数组类型
			List<Object> value = new ArrayList<Object>();
			index = 1;
			while (jsonString.charAt(currentIndex + index) != ']') {
				while (isUselessChar(jsonString.charAt(currentIndex + index)) || jsonString.charAt(currentIndex + index) == ',') {
					index++;
				}
				// 进行递归读取数组中的对象值
				Object[] parseResult = parseValue(jsonString, currentIndex + index);
				value.add(parseResult[0]);
				index = (int) parseResult[1] - currentIndex;
			}
			result[0] = value;
			result[1] = currentIndex + index + 1;
			return result;
		}
		if (currentChar == '{') { // 遇到{说明嵌套了个json对象，调用json序列化读取器
			Object[] parseResult = jsonReader(jsonString.substring(currentIndex, jsonString.length()));
			index = (int) parseResult[1];
			result[0] = (Map<String, Object>) parseResult[0];
			result[1] = currentIndex + index;
			return result;
		}
		throw new JSONParseException("值的表达有误！");
	}

	/**
	 * 将实例转换为String
	 * 
	 * @param object 实例
	 * @return 实例字符串
	 */
	@SuppressWarnings("unchecked")
	private static String objectToString(Object object) {
		String result = "";
		if (object instanceof String) { // 如果是字符串类型，则以双引号包围
			result = replaceEscapeInString((String) object);
			result = surByQut(result);
		} else if (object instanceof Object[]) { // 如果是数组类型则依次递归转换
			result = "[";
			for (Object obj : (Object[]) object) {
				result = result + objectToString(obj);
				result = result + ",";
			}
			if (((Object[]) object).length != 0) {
				result = result.substring(0, result.length() - 1);
			}
			result = result + "]";
		} else if (object instanceof List) { // 如果是List类型
			result = "[";
			for (Object obj : (List<Object>) object) {
				result = result + objectToString(obj);
				result = result + ",";
			}
			if (((List<Object>) object).size() != 0) {
				result = result.substring(0, result.length() - 1);
			}
			result = result + "]";
		} else if (object instanceof Boolean) { // 如果是布尔型
			if ((boolean) object) {
				return "true";
			} else {
				result = "false";
			}
		} else if (object instanceof Number) { // 如果是数字
			result = "" + object;
		} else if (object instanceof Map) { // 如果是Map类型
			result = stringifyMap((Map<String, Object>) object);
		} else {
			result = "null";
		}
		return result;
	}

	/**
	 * json字符串格式化构造器
	 * 
	 * @param originJSON 待格式化json字符串
	 * @param level      当前层级
	 * @return 第一位是格式化的json字符串，第二个是当前解析到了哪一位
	 * @throws JSONParseException json字符串解析错误
	 */
	private static Object[] jsonFormatter(String originJSON, int level) throws JSONParseException {
		// 裁剪两端无用字符
		while (isUselessChar(originJSON.charAt(0))) {
			originJSON = originJSON.substring(1, originJSON.length());
		}
		while (isUselessChar(originJSON.charAt(originJSON.length() - 1))) {
			originJSON = originJSON.substring(0, originJSON.length() - 1);
		}
		if (!originJSON.startsWith("{") || !originJSON.endsWith("}")) {
			throw new JSONParseException("json必须以花括号开头或结尾！");
		}
		Object[] result = new Object[2];
		StringBuilder formattedJSON = new StringBuilder("{");
		int pointer = 1; // 主索引指示
		boolean isFormatValue = false; // 当前是否在读取值
		while (pointer < originJSON.length()) {
			char currentChar = originJSON.charAt(pointer);
			if (!isFormatValue) { // 当前在读取属性
				if (isUselessChar(currentChar) || currentChar == ',') {
					pointer++;
				} else if (currentChar == '\"') { // 说明读取到了属性
					formattedJSON.append("\r\n");
					for (int i = 0; i < level; i++) {
						formattedJSON.append('	');
					}
					formattedJSON.append('\"');
					int index = 1;
					char eachChar;
					while ((eachChar = originJSON.charAt(pointer + index)) != '\"') {
						if (eachChar == '\\' && originJSON.charAt(pointer + index + 1) == '\"') {
							formattedJSON.append("\\\"");
							index = index + 2;
						} else {
							formattedJSON.append(eachChar);
							index++;
						}
					}
					formattedJSON.append("\"");
					pointer = pointer + index + 1;
				} else if (currentChar == ':') { // 遇到:说明读取到了值
					formattedJSON.append(": ");
					isFormatValue = true;
					pointer++;
					while (isUselessChar(originJSON.charAt(pointer))) {
						pointer++;
					}
				} else if (currentChar == '}') { // 遇到}说明解析结束
					if (formattedJSON.charAt(formattedJSON.length() - 1) != '{') {
						formattedJSON = new StringBuilder(formattedJSON.substring(0, formattedJSON.length() - 1));
						formattedJSON.append("\r\n");
						for (int i = 0; i < level - 1; i++) {
							formattedJSON.append("	");
						}
					}
					formattedJSON.append("}");
					result[0] = formattedJSON.toString();
					result[1] = pointer + 1;
					return result;
				} else {
					throw new JSONParseException("语法错误！");
				}
			} else { // 当前在读取值
				Object[] formatResult = formatValueInJson(originJSON, pointer, level);
				formattedJSON.append(formatResult[0] + ",");
				pointer = (int) formatResult[1];
				isFormatValue = false;
			}
		}
		throw new JSONParseException("非正常结尾！");
	}

	/**
	 * 对json字符串中的每个值进行判断解析格式化
	 * 
	 * @param originJSON json字符串
	 * @param pointer    当前解析到的位数索引
	 * @param level      当前解析层级
	 * @return 数组第一个表示格式化后的值，第二个表示当前解析到了哪一位索引
	 * @throws JSONParseException 解析异常
	 */
	private static Object[] formatValueInJson(String originJSON, int pointer, int level) throws JSONParseException {
		Object[] result = new Object[2];
		StringBuilder value = new StringBuilder("");
		char eachChar;
		int index = 0; // 子索引
		while ((eachChar = originJSON.charAt(pointer + index)) != ',' && (eachChar = originJSON.charAt(pointer + index)) != '}' && (eachChar = originJSON.charAt(pointer + index)) != ']') {
			if (isUselessChar(eachChar)) {
				index++;
			} else if (eachChar == '[') { // 遇到[说明是数组，对其中的值进行递归格式化解析
				index = 1;
				value.append('[');
				while (originJSON.charAt(pointer + index) != ']') {
					if (originJSON.charAt(pointer + index) == ',') {
						index++;
					} else {
						Object[] formatResult = formatValueInJson(originJSON, pointer + index, level);
						value.append((String) formatResult[0] + ", ");
						index = (int) formatResult[1] - pointer;
					}
				}
				if (value.charAt(value.length() - 1) != '[') {
					value = new StringBuilder(value.substring(0, value.length() - 2));
				}
				value.append(']');
				index++;
			} else if (eachChar == '{') { // 遇到{说明嵌套了个json对象，进行json格式化
				String json = originJSON.substring(pointer, originJSON.length());
				Object[] formatResult = jsonFormatter(json, level + 1);
				value.append((String) formatResult[0]);
				index = (int) formatResult[1];
			} else if (eachChar == '\"') { // 遇到"说明是字符串
				index = 1;
				value.append("\"");
				while (originJSON.charAt(pointer + index) != '\"') {
					if (originJSON.charAt(pointer + index) == '\\' && originJSON.charAt(pointer + index + 1) == '\"') {
						value.append("\\\"");
						index = index + 2;
					} else {
						value.append(originJSON.charAt(pointer + index));
						index++;
					}
				}
				value.append("\"");
				index++;
			} else { // 其他情况，一般是null，true或者false或者数字
				if (!isUselessChar(eachChar)) {
					value.append(eachChar);
				}
				index++;
			}
		}
		result[0] = value.toString();
		result[1] = pointer + index;
		return result;
	}

	/**
	 * 将Map实例序列化为json字符串
	 * 
	 * @param data Map实例
	 * @return json字符串
	 */
	public static String stringifyMap(Map<String, Object> data) {
		String json = "{";
		for (String key : data.keySet()) {
			json = json + surByQut(replaceEscapeInString(key)) + ":";
			json = json + objectToString(data.get(key)) + ",";
		}
		if (data.size() != 0) {
			json = json.substring(0, json.length() - 1);
		}
		json = json + "}";
		return json;
	}

	/**
	 * 反序列化json，将json转为Map实例
	 * 
	 * @param jsonString json字符串
	 * @return 反序列化结果
	 * @throws JSONParseException json解析异常
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> parseJSONToMap(String jsonString) throws JSONParseException {
		return (Map<String, Object>) jsonReader(jsonString)[0];
	}

	/**
	 * 格式化json字符串
	 * 
	 * @param json 待格式化的json字符串
	 * @return 格式化的json字符串
	 * @throws JSONParseException json解析错误
	 */
	public static String formatJSONString(String json) throws JSONParseException {
		return (String) jsonFormatter(json, 1)[0];
	}

}