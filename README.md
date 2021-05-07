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
9，二进制文件工具：读写二进制文件为字节数据，复制下载文件。<br>
10，终端工具：执行命令并获取命令输出结果。<br>
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
    <version>6.0.0</version>
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
- static boolean downloadFile(String url, String filePath)：从网络上下载文件
- static boolean downloadFile(String url, String filePath, String userAgent)：以一个特定的User-Agent从网络上下载文件
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

>最后更新：2021.2.6