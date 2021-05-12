# ReadAndWriteJ使用说明
### 这是一个简单的包，用于快速方便地写入、读取文件。<br>
### 其功能有：
1，写入文件：把指定内容写入指定文件（按行写入），替换文件某行内容，插入指定内容至指定位置，移除某一行的内容，清空文件。<br>
2，行数读取：读取文本文档行数。<br>
3，指定读取：读取指定行内容，读取整个文件的内容，读取指定文件行数范围的内容。<br>
4，文本对比：比较某一行内容是否与被比较字符串一致，判断文本文档内是否包含被比较的字符串。<br>
5，文件对比：对比两个文件是否一致。<br>
6，引入字体：在GUI编程中可以快速指定字体文件以设置字体。<br>
7，文件分析器：读取文件大小和格式（扩展名）。<br>
8，Jar包内工具：读取Jar包内的图片资源，有释放包内文件功能。<br>
9，二进制文件工具：读写二进制文件为字节数据，复制文件。<br>
10，终端工具：执行命令并获取命令输出结果。<br>
11，JSON工具：简单的JSON解析和格式化。<br>
12，网络工具：网络请求和文件下载。<br>
### 下载地址:[点击进入下载jar包](https://gitee.com/swsk33/ReadAndWriteJ/releases)
### 其它版本：
现已完成C#版的读写类库，可根据自己的框架前往查看：<br>
[.NET Framework](https://gitee.com/swsk33/ReadAndWriteSharp)
## 使用方法：
### 1，添加依赖，有下列两种情况：
①Eclipse直接添加jar：先下载这个jar包并把这个包导入到IDE里面，例如eclipse。不知道如何导入请查看教程：[eclipse导入外部jar包](https://blog.csdn.net/czbqoo01/article/details/72803450)<br>
②Maven工程：在项目的配置文件pom.xml中的```<dependencies>```标签里加入下列依赖，此操作无需在上面手动下载jar包（推荐）：<br>
```
<dependency>
    <groupId>com.gitee.swsk33</groupId>
    <artifactId>read-and-write</artifactId>
    <version>6.3.0</version>
</dependency>
```
### 2，导入com.gitee.swsk33.readandwrite下所有类或者需要的类。（import swsk33.readandwrite.*;）
### 3，主要类和方法：
#### 类BinaryUtils：二进制文件工具
- static byte[] readBinaryFile(String filePath)：读取二进制文件并储存在byte数组当中
- static byte[][] readBinaryFile(String filePath, int fragments)：分段读取二进制文件并储存在byte二维数组中
- static boolean writeBinaryFile(String filePath, byte[] data)：写入二进制文件（小文件），文件夹不存在自动创建
- static <T> boolean writeObjectToFile(String filePath, T object)：将对象（实例）序列化并写入文件
- static <T> T readObjectFromFile(String filePath)：从文件中读取数据并反序列化为对象
- static boolean copyFile(String origin, String destination)：复制文件，把文件从原文件路径复制到目标文件路径，目标文件路径目录不存在会自动创建
#### 类FileAnalyzer：文件分析器，用于读取文件大小和格式（扩展名）
- static String getFileMD5(String filePath)：用于获取文件的MD5值
- static long getFileSizeb(String filePath)：获取文件的大小，单位为B
- static double getFileSizekb(String filePath)：获取文件的大小，单位为KB
- static double getFileSizekb(String filePath, int numofdecimres)：获取文件大小，单位为KB-保留小数
- ...
- static String getFileFormat(String filePath)：获取文件扩展名
#### 类FileComparer：文件对比
- static boolean compareTextFile(String filePath1, String filePath2)：比较两个文本文件的内容是否完全相同
- static boolean compareBinaryFile(String filePath1, String filePath2)：比较两个二进制文件的内容是否完全相同
#### 类ImportFont：引入字体：在GUI编程中可以快速指定字体文件以设置字体
- static Font getFont(String filePath, int wordSize)：引入字体文件为普通字体形式
- static Font getBoldFont(String filePath, int wordSize)：引入字体文件为加粗字体形式
- static Font getItalicFont(String filePath, int wordSize)：引入字体文件为斜体字体形式
- static Font getItalicBoldFont(String filePath, int wordSize)：引入字体文件为斜体加粗字体形式
#### 类JarUtils：用于获取或者释放jar包内的文件和资源
- static boolean releaseFileInJar(@SuppressWarnings("rawtypes") Class c, String classPath, String outputPath)：释放jar包内文件到jar包外部
- static ImageIcon getImageInJar(@SuppressWarnings("rawtypes") Class c, String classPath)：直接读取jar内图片资源为ImageIcon对象
#### 类LineScanner：文件行数获取
- static int getLineCount(String filePath)：获取文本文件行数
#### 类StringComparer：文本文件对比器
- static boolean compareLine(String filepath, String lineCompared)：比较某一行内容是否与被比较字符串一致
- static boolean compareText(String filepath, String textCompared)：判断文本文档内是否包含被比较的字符串
#### 类TerminalUtils：用于命令行的执行和读取
- static TerminalOutput getCommandOutput(String command)：向终端输入（运行）一条命令并获取输出结果
- static TerminalOutput getCommandOutput(String command, String charSet)：向终端输入（运行）一条命令并以指定的编码获取输出结果
- static void runCommandAsyn(String command, TerminalOutput result)：异步运行命令并实时获取输出结果
- static void runCommandAsyn(String command, String charset, TerminalOutput result)：以指定的编码异步运行命令并实时获取输出结果
#### 类TextFileWriter：文件写入器
- static boolean replaceLine(String filePath, int whichLine, String text)：用指定内容替换文件指定行
- static boolean replaceLine(String filePath, int whichLine, String text, String charSet)：用指定内容替换文件指定行，以指定的字符编码形式
- static boolean writeText(String filePath, String text)：写入指定内容至文件末尾，每执行一次该方法就在末尾写入一行内容
- static boolean writeText(String filePath, String text, String charSet)：以指定的编码格式，写入指定内容至文件末尾，每执行一次该方法就在末尾写入一行内容
- static boolean insertText(String filePath, String insertText, int whichLine, boolean isAfterLine)：插入文本至指定行之后或者之前
- static boolean insertText(String filePath, String insertText, int whichLine, boolean isAfterLine, String charSet)：插入文本至指定行之后或者之前，以指定的编码格式
- static boolean removeLine(String filePath, int whichLine)：移除某一行的内容
- static boolean clearAll(String filePath)：清空一个文件
#### 类TextReader：文件读取器
- static String readText(String filePath, int line)：读取文本文件指定行内容
- static String readText(String filePath, int line, String charSet)：指定编码读取文本文件指定行内容
- static String readFileRange(String filePath, int start, int end)：读取指定行数范围内的内容并以字符串形式储存
- static String readFileRange(String filePath, int start, int end, String charSet)：以指定编码读取指定行数范围内的内容并以字符串形式储存
- static String[] readFileRangeToArray(String filePath, int start, int end)：读取指定行数范围内的内容并以字符串数组形式储存
- static String[] readFileRangeToArray(String filePath, int start, int end, String charSet)：以指定编码读取指定行数范围内的内容并以字符串数组形式储存
- static String readFile(String filePath)：读取整个文本文档并将内容储存在字符串中
- static String readFile(String filePath, String charSet)：以指定编码读取整个文本文档并将内容储存在字符串中
- static String[] readFileToArray(String filePath)：读取整个文本文档并将内容储存在字符串数组中
- static String[] readFileToArray(String filePath, String charSet)：以指定编码读取整个文本文档并将内容储存在字符串数组中
#### 类NetworkUtils：网络实用类
- static String sendGetRequest(String urlString)：发送GET请求
- static String sendGetRequest(String urlString, String userAgent)：以一个特定的User-Agent发送GET请求
- static String sendPostRequest(String urlString, String contentType, String requestBody)：发送POST请求
- static String sendPostRequest(String urlString, String contentType, String requestBody, String userAgent)：以一个特定的User-Agent发送POST请求
- static boolean downloadFile(String url, String filePath)：从网络上下载文件
- static boolean downloadFile(String url, String filePath, String userAgent)：以一个特定的User-Agent从网络上下载文件
#### 类JSONUtils：一个简单的JSON处理实用类
- static String stringifyMap(Map<String, Object> data)：将Map实例序列化为json字符串
- static Map<String, Object> parseJSONToMap(String jsonString)：反序列化json，将json转为Map实例
- static String formatJSONString(String json)：格式化json字符串

**详细的使用可以在调用类的方法时查看，IDE中会显示其中的详细文档**

>最后更新：2021.5.7