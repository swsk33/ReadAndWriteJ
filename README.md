# ReadAndWriteJ使用说明
这是一个简单的包，用于快速方便地写入、读取文件。

## 主要功能
1. 写入文件：把指定内容写入指定文件（按行写入），替换文件某行内容，插入指定内容至指定位置，移除某一行的内容，清空文件
2. 行数读取：读取文本文档行数
3. 指定读取：读取指定行内容，读取整个文件的内容，读取指定文件行数范围的内容
4. 文本对比：比较某一行内容是否与被比较字符串一致，判断文本文档内是否包含被比较的字符串
5. 文件对比：对比两个文件是否一致
6. 引入字体：在GUI编程中可以快速指定字体文件以设置字体
7. 文件分析器：读取文件大小和格式（扩展名）
8. Jar包内工具：读取Jar包内的图片资源，有释放包内文件功能
9. 二进制文件工具：读写二进制文件为字节数据，复制文件
10. 终端工具：执行命令并获取命令输出结果

## 下载地址

右侧发行版/Releases处可以下载jar包，或者直接使用Maven导入。

## 其它版本

现已完成C#版的读写类库：[.NET Framework](https://gitee.com/swsk33/ReadAndWriteSharp)
## 使用方法
### 1，添加依赖，有下列两种情况：

① Eclipse直接添加jar：先下载这个jar包并把这个包导入到IDE里面

② Maven工程：在项目的配置文件`pom.xml`中的```<dependencies>```标签里加入下列依赖，此操作无需在上面手动下载jar包（推荐）：

```xml
<dependency>
	<groupId>io.github.swsk33</groupId>
	<artifactId>read-and-write</artifactId>
	<version>7.0.1</version>
</dependency>
```
### 2，导入`com.gitee.swsk33.readandwrite`下所有类或者需要的类。（`import swsk33.readandwrite.*;`）
### 3，主要类和方法：
#### 类`BinaryUtils`：二进制文件工具
- `static byte[] readBinaryFile(String filePath)` 读取二进制文件并储存在byte数组当中
- `static boolean writeBinaryFile(String filePath, byte[] data)` 写入二进制文件，文件夹不存在自动创建
- `static <T> boolean writeObjectToFile(String filePath, T object)` 将对象（实例）序列化并写入文件
- `static <T> T readObjectFromFile(String filePath)` 从文件中读取数据并反序列化为对象
- `static boolean copyFile(String origin, String destination)` 复制文件，把文件从原文件路径复制到目标文件路径，目标文件路径目录不存在会自动创建
#### 类`FileAnalyzer`：文件分析器，用于读取文件大小和格式（扩展名）
- `static String getFileMD5(String filePath)` 用于获取文件的MD5值
- `static long getFileSizeByte(String filePath)` 获取文件的大小，单位为B
- `static double getFileSizeKb(String filePath)` 获取文件的大小，单位为KB
- ...
- `static String getFileFormat(String filePath)` 获取文件扩展名
#### 类`FileComparer`：文件对比
- `static boolean compareTextFile(String filePath1, String filePath2)` 比较两个文本文件的内容是否完全相同
- `static boolean compareBinaryFile(String filePath1, String filePath2)` 比较两个二进制文件的内容是否完全相同
#### 类`ImportFont`：引入字体：在GUI编程中可以快速指定字体文件以设置字体
- `static Font getFont(String filePath, int wordSize)` 引入字体文件为普通字体形式
- `static Font getBoldFont(String filePath, int wordSize)` 引入字体文件为加粗字体形式
- `static Font getItalicFont(String filePath, int wordSize)` 引入字体文件为斜体字体形式
- `static Font getItalicBoldFont(String filePath, int wordSize)` 引入字体文件为斜体加粗字体形式
#### 类`JarUtils`：用于获取或者释放jar包内的文件和资源
- `static boolean releaseFileInJar(Class<?> c, String classPath, String outputPath)` 释放jar包内文件到jar包外部
- `static ImageIcon getImageInJar(Class<?> c, String classPath)` 直接读取jar内图片资源为`ImageIcon`对象
#### 类`LineScanner`：文件行数获取
- `static int getLineCount(String filePath)` 获取文本文件行数
- `static int getTextAtLine(String filePath, String text)` 判断一段文本在文件的哪一行
#### 类`StringComparer`：文本文件对比器
- `static boolean compareLine(String filepath, String lineCompared)` 比较某一行内容是否与被比较字符串一致
- `static boolean compareText(String filepath, String textCompared)` 判断文本文档内是否包含被比较的字符串
#### 类`TerminalUtils`：用于命令行的执行和读取
- `static TerminalOutput runCommand(String command)` 向终端运行一条命令并获取输出结果
- `static TerminalOutput runCommand(String command, String charSet)` 向终端运行一条命令并以指定的编码获取输出结果
- `static TerminalOutput runCommand(String[] commandArray)` 向终端运行一个命令数组并获取输出结果
- `static TerminalOutput runCommand(String[] commandArray, String charSet)` 向终端运行一个命令数组并以指定的编码获取输出结果
- `static void runCommandAsyn(String command, TerminalOutput result)` 异步运行命令并实时获取输出结果
- `static void runCommandAsyn(String command, String charset, TerminalOutput result)` 以指定的编码异步运行命令并实时获取输出结果
- `static void runCommandAsyn(String[] commandArray, TerminalOutput result)：异步运行命令数组并实时获取输出结果
- static void runCommandAsyn(String[] commandArray, String charset, TerminalOutput result)` 以指定的编码异步运行命令数组并实时获取输出结果
#### 类`TextFileWriter`：文件写入器
- `static boolean replaceLine(String filePath, int whichLine, String text)` 用指定内容替换文件指定行
- `static boolean replaceLine(String filePath, int whichLine, String text, String charSet)` 用指定内容替换文件指定行，以指定的字符编码形式
- `static boolean appendText(String filePath, String text)` 写入指定内容至文件末尾，每执行一次该方法就在末尾写入一行内容
- `static boolean appendText(String filePath, String text, String charSet)` 以指定的编码格式，写入指定内容至文件末尾，每执行一次该方法就在末尾写入一行内容
- `static boolean writeText(String filePath, String text)` 将文本写入文件，文件原有内容会被覆盖
- `static boolean writeText(String filePath, String text, String charSet)` 将文本以特定的编码写入到一个文件，文件原有内容会被覆盖
- `static boolean removeLine(String filePath, int whichLine)` 移除某一行的内容
- `static boolean removeLine(String filePath, int whichLine, String charSet)` 移除某一行的内容，并指定文件编码
- `static boolean clearAll(String filePath)` 清空一个文件
#### 类`TextReader`：文件读取器
- `static String readLine(String filePath, int line)` 读取文本文件指定行内容
- `static String readLine(String filePath, int line, String charSet)` 指定编码读取文本文件指定行内容
- `static String readFileRange(String filePath, int start, int end)` 读取指定行数范围内的内容并以字符串形式储存
- `static String readFileRange(String filePath, int start, int end, String charSet)` 以指定编码读取指定行数范围内的内容并以字符串形式储存
- `static String[] readFileRangeToArray(String filePath, int start, int end)` 读取指定行数范围内的内容并以字符串数组形式储存
- `static String[] readFileRangeToArray(String filePath, int start, int end, String charSet)` 以指定编码读取指定行数范围内的内容并以字符串数组形式储存
- `static String readFile(String filePath)` 读取整个文本文档并将内容储存在字符串中
- `static String readFile(String filePath, String charSet)` 以指定编码读取整个文本文档并将内容储存在字符串中
- `static String[] readFileToArray(String filePath)` 读取整个文本文档并将内容储存在字符串数组中
- `static String[] readFileToArray(String filePath, String charSet)` 以指定编码读取整个文本文档并将内容储存在字符串数组中

**详细的使用可以在调用类的方法时查看，IDE中会显示其中的详细文档。**

>最后更新：2022.6.19