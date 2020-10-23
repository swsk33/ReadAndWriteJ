# ReadAndWriteJ使用说明
### 这是一个简单的包，用于快速方便地写入、读取文件。<br>
### 其功能有：
1，写入文件：把指定内容写入指定文件（按行写入），替换文件某行内容，插入指定内容至指定位置，移除某一行的内容，清空文件。<br>
2，行数读取：读取文本文档行数。<br>
3，指定读取：读取指定行内容，读取整个文件的内容，读取指定文件行数范围的内容。<br>
4，文本对比：比较某一行内容是否与被比较字符串一致，判断文本文档内是否包含被比较的字符串。<br>
5，文件对比：对比两个文件内容是否一致。<br>
6，引入字体：在GUI编程中可以快速指定字体文件以设置字体。<br>
7，文件分析器：读取文件大小和格式（扩展名）。<br>
8，Jar包内读取器：读取Jar包内的文件资源，有释放包内文件功能。<br>
### 下载地址:[点击进入下载jar包](https://gitee.com/swsk33/ReadAndWriteJ/releases)
## 使用方法：
### 1，添加依赖，有下列两种情况：
①Eclipse直接添加jar：先下载这个jar包并把这个包导入到IDE里面，例如eclipse。不知道如何导入请查看教程：[eclipse导入外部jar包](https://blog.csdn.net/czbqoo01/article/details/72803450)<br>
②Maven工程：在项目的配置文件pom.xml中的```<dependencies>```标签里加入下列依赖，此操作无需在上面手动下载jar包（推荐）：<br>
```
<dependency>
    <groupId>com.gitee.swsk33</groupId>
    <artifactId>read-and-write</artifactId>
    <version>4.5.12</version>
</dependency>
```
### 2，导入swsk33.readandwritej下所有类或者需要的类。（import swsk33.readandwritej.*;）
### 3，公共类和方法：
#### 类**FileAnalyzer**：用于分析文件信息
* String getFileMD5(String filePath)
	+ 作用：用于获取文件的MD5值 
	+ 参数：
		- filePath 文件的相对路径/绝对路径
	+ 返回值：String 文件MD5值

* long getFileSizeb(String filePath)
	+ 作用：获取文件的大小，单位为B
	+ 参数：
		- filePath 文件的相对路径/绝对路径
	+ 返回值：long 文件大小

* double getFileSizekb(String filePath)
	+ 作用：获取文件的大小，单位为KB
	+ 参数：
		- filePath 文件的相对路径/绝对路径
	+ 返回值：double 文件大小

* double getFileSizekb(String filePath, int numofdecimres)
	+ 作用：获取文件大小，单位为KB-保留小数
	+ 参数：
		- filePath      文件的相对路径/绝对路径
		- numofdecimres 要保留的小数位数
	+ 返回值：double 文件大小

* double getFileSizemb(String filePath)
	+ 作用：获取文件的大小，单位为MB
	+ 参数：
		- filePath 文件的相对路径/绝对路径
	+ 返回值：double 文件大小

* double getFileSizemb(String filePath, int numofdecimres)
	+ 作用：获取文件大小，单位为MB-保留小数
	+ 参数：
		- filePath 文件的相对路径/绝对路径
		- numofdecimres 要保留的小数位数
	+ 返回值：double 文件大小

* double getFileSizegb(String filePath)
	+ 作用：获取文件的大小，单位为GB
	+ 参数：
		- filePath 文件的相对路径/绝对路径
	+ 返回值：double 文件大小

* double getFileSizegb(String filePath, int numofdecimres)
	+ 作用：获取文件的大小，单位为GB-保留小数
	+ 参数：
		- filePath 文件的相对路径/绝对路径
		- numofdecimres 要保留的小数位数
	+ 返回值：double 文件大小

* String getFileFormat(String filePath)
	+ 作用：获取文件格式（扩展名）
	+ 参数：
		- filePath 文件的相对路径/绝对路径
	+ 返回值：String 为获取文件扩展名（不带.）

#### 类**FileComparer**：用于对比两个文件内容是否一致
* boolean compareFile(String filePath1, String filePath2)
	+ 作用：比较两个文件的内容是否完全相同
	+ 参数：
		- filePath1 待比较的文件1
		- filePath2 待比较的文件2
	+ 返回值：boolean 表示两个文件是否一样

#### 类**ImportFont**：用于在GUI编程中可以快速指定字体文件以设置字体
* Font getFont(String filePath, int wordSize)
	+ 作用：引入字体文件为普通字体形式
	+ 参数：
		- filePath 字体文件的相对路径/绝对路径
		- wordSize 字体大小
	+ 返回值：Font 字体对象

* Font getBoldFont(String filePath, int wordSize)
	+ 作用：引入字体文件为加粗字体形式
	+ 参数：
		- filePath 字体文件的相对路径/绝对路径
		- wordSize 字体大小
	+ 返回值：Font 字体对象

* Font getItalicFont(String filePath, int wordSize)
	+ 作用：引入字体文件为斜体字体形式
	+ 参数：
		- filePath 字体文件的相对路径/绝对路径
		- wordSize 字体大小
	+ 返回值：Font 字体对象

* Font getItalicBoldFont(String filePath, int wordSize)
	+ 作用：引入字体文件为斜体加粗字体形式
	+ 参数：
		- filePath 字体文件的相对路径/绝对路径
		- wordSize 字体大小
	+ 返回值：Font 字体对象

#### 类**JarInternalReader**：用于获取或者释放jar包内的文件和资源
* boolean releaseFileInJar(Class c, String classPath, String outputPath)
	+ 作用：释放jar包内文件到jar包外部
	+ 参数：
		- Class 指定的类（一般是当前类，非静态方法填入当前内用this.getClass()即可）
		- classPath  指定被释放的资源文件路径，可以是指定的类（c）为基准的相对路径，也可以是绝对路径。绝对路径以/开头，表示jar包的根目录
		- outputPath 指定的输出路径
	+ 返回值：boolean 释放成功则为true

* ImageIcon getImageInJar(Class c, String classPath)
	+ 作用：直接读取jar内图片资源为ImageIcon对象
	+ 参数：
		- c         Class 指定的类（一般是当前类，非静态方法填入当前内用this.getClass()即可）
		- classPath 资源文件路径，可以是指定的类（c）为基准的相对路径，也可以是绝对路径。绝对路径以/开头，表示jar包的根目录
	+ 返回值：ImageIcon 图片对象

#### 类**LineScanner**：文件行数读取器
* int getLineCount(String filePath)
	+ 作用：获取文本文件的内容行数
	+ 参数：
		- filePath 待读取文件的相对路径/绝对路径
	+ 返回值：int 文件行数

#### 类**StringComparer**：文件文本对比器
* boolean compareLine(String filepath, String lineCompared)
	+ 作用：比较某一行内容是否与被比较字符串一致
	+ 参数：
		- filepath     待比较的文件相对路径/绝对路径
		- lineCompared 被比较的字符串
	+ 返回值：boolean 表示是否有一行的内容和被比较字符串一致

* boolean compareText(String filepath, String textCompared)
	+ 作用：判断文本文档内是否包含被比较的字符串
	+ 参数：
		- filepath     待比较的文件相对路径/绝对路径
		- textCompared 被比较的字符串
	+ 返回值：boolean 表示是否有一行的内容包含被比较的字符串

#### 类**TextFileWriter**：文件写入器
* boolean replaceLine(String filePath, int whichLine, String text)
	+ 作用：用指定内容替换文件指定行
	+ 参数：
		- filePath  待写入文件相对/绝对路径
		- whichLine 待替换的行数
		- text      待替换的内容
	+ 返回值：boolean 替换写入操作成功即返回true

* boolean replaceLine(String filePath, int whichLine, String text, String charSet)
	+ 作用：用指定内容替换文件指定行，以指定的字符编码形式
	+ 参数：
		- filePath  待写入文件相对/绝对路径
		- whichLine 待替换的行数
		- text      待替换的内容
		- charSet   指定编码格式写入文件
	+ 返回值：boolean 替换写入操作成功即返回true

* boolean writeText(String filePath, String text)
	+ 作用：写入指定内容至文件末尾，每执行一次该方法就在末尾写入一行内容
	+ 参数：
		- filePath 待写入文件相对/绝对路径
		- text     待写入内容
	+ 返回值：boolean 写入操作成功即返回true

* boolean writeText(String filePath, String text, String charSet)
	+ 作用：以指定的编码格式，写入指定内容至文件末尾，每执行一次该方法就在末尾写入一行内容
	+ 参数：
		- filePath 待写入文件相对/绝对路径
		- text     待写入内容
		- charSet  指定编码格式写入文件
	+ 返回值：boolean 写入操作成功即返回true

* boolean insertText(String filePath, String insertText, int whichLine, boolean isAfterLine)
	+ 作用：插入文本至指定行之后或者之前
	+ 参数：
		- filePath    待操作文件的相对/绝对路径
		- insertText  待插入的内容
		- whichLine   指定要插入的某一行
		- isAfterLine 是否在这一行之后插入，true则把内容插入至指定行之后，否则插入到某一行之前
	+ 返回值：boolean 是否插入成功

* boolean insertText(String filePath, String insertText, int whichLine, boolean isAfterLine, String charSet)
	+ 作用：插入文本至指定行之后或者之前，以指定的编码格式
	+ 参数：
		- filePath    待操作文件的相对/绝对路径
		- insertText  待插入的内容
		- whichLine   指定要插入的某一行
		- isAfterLine 是否在这一行之后插入，true则把内容插入至指定行之后，否则插入到某一行之前
		- charSet     指定编码格式写入文件
	+ 返回值：boolean 是否插入成功

* boolean removeLine(String filePath, int whichLine)
	+ 作用：移除某一行的内容
	+ 参数：
		- filePath  待操作文件相对/绝对路径
		- whichLine 指定被移除的行
	+ 返回值：boolean 是否移除成功

* boolean clearAll(String filePath)
	+ 作用：把指定文件内容清空
	+ 参数：
		- filePath 待清空文件相对/绝对路径
	+ 返回值： boolean 清空操作成功时返回true

#### 类**TextReader**：文件读取器
* String readText(String filePath, int line)
	+ 作用：读取文本文件指定行内容
	+ 参数：
		- filePath 待读取文件相对/绝对路径
		- line     待读取的行数
	+ 返回值：String 读取的内容

* String readText(String filePath, int line, String charSet)
	+ 作用：指定编码读取文本文件指定行内容
	+ 参数：
		- filePath 待读取文件相对/绝对路径
		- line     待读取的行数
		- charSet  指定编码格式读取文件
	+ 返回值：String 读取的内容

* String readFileRange(String filePath, int start, int end)
	+ 作用：读取指定行数范围内的内容并以字符串形式储存
	+ 参数：
		- filePath 待读取文件相对/绝对路径
		- start    指定起始行
		- end      指定终止行
	+ 返回值：String 读取的内容

* String readFileRange(String filePath, int start, int end, String charSet)
	+ 作用：以指定编码读取指定行数范围内的内容并以字符串形式储存
	+ 参数：
		- filePath 待读取文件相对/绝对路径
		- start    指定起始行
		- end      指定终止行
		- charSet  指定编码格式读取文件
	+ 返回值：String 读取的内容

* String[] readFileRangeToArray(String filePath, int start, int end)
	+ 作用：读取指定行数范围内的内容并以字符串数组形式储存 每一行的内容即为数组中的一个String元素
	+ 参数：
		- filePath 待读取文件相对/绝对路径
		- start    指定起始行
		- end      指定终止行
	+ 返回值：String[]  读取的内容

* String[] readFileRangeToArray(String filePath, int start, int end, String charSet)
	+ 作用：以指定编码读取指定行数范围内的内容并以字符串数组形式储存 每一行的内容即为数组中的一个String元素
	+ 参数：
		- ilePath 待读取文件相对/绝对路径
		- start    指定起始行
		- end      指定终止行
		- charSet  指定编码格式读取文件
	+ 返回值：String[] 读取的内容

* String readFile(String filePath)
	+ 作用：读取整个文本文档并将内容储存在字符串中
	+ 参数：
		- filePath 待读取文件相对/绝对路径
	+ 返回值：String 读取的内容

* String readFile(String filePath, String charSet)
	+ 作用：以指定编码读取整个文本文档并将内容储存在字符串中
	+ 参数：
		- filePath 待读取文件相对/绝对路径
		- charSet  指定编码格式读取文件
	+ 返回值：String 读取的内容

* String[] readFileToArray(String filePath)
	+ 作用：读取整个文本文档并将内容储存在字符串数组中 每一行的内容都是String数组中的一个元素
	+ 参数：
		- filePath 待读取文件相对/绝对路径
	+ 返回值：String[] 读取的内容

* String[] readFileToArray(String filePath, String charSet)
	+ 作用：以指定编码读取整个文本文档并将内容储存在字符串数组中 每一行的内容都是String数组中的一个元素
	+ 参数：
		- filePath 待读取文件相对/绝对路径
		- charSet  指定编码格式读取文件
	+ 返回值：String[] 读取的内容

#### 上述charSet（编码格式参数）可选常量值如下：
```
CharSetValue.US_ASCII：US-ASCII
CharSetValue.ISO_8859_1：ISO-8859-1
CharSetValue.GBK：GBK
CharSetValue.UTF_8：UTF-8
CharSetValue.UTF_16：UTF-16
CharSetValue.UTF_16BE：UTF-16BE
CharSetValue.UTF_16LE：UTF-16LE
```

### 实例
**例如输出D盘的1.txt的文件行数：**<br>
```
LineScanner la = new LineScanner();
System.out.println(la.getLineCount("D:\\1.txt"));
```
**例如读取D盘的1.txt的第3行并输出：**<br>
```
TextReader tr = new TextReader();
System.out.println(tr.readText("D:\\1.txt", 3));
```
**例如从D盘的2.txt的第3行开始读取至第7行的内容并输出:**<br>
```
TextReader tr = new TextReader();
System.out.println(tr.readFileRange("D:\\2.txt", 3, 7));
```
**例如以GBK编码格式读取D盘根目录下的3.txt的第3行并输出：**<br>
```
TextReader tr = new TextReader();
System.out.println(tr.readText("D:\\3.txt", 3, CharSetValue.GBK));
```
**例如，在jar包内的目录结构如下：**<br>
![](https://i.niupic.com/images/2020/08/22/8yS7.png)<br>
那么在Test类中，读取b.txt文件所有内容：
```
String content = new JarInternalReader().readFileInJar(Test.class, "b.txt");
```
非静态方法中可以直接写成：
```
String content = new JarInternalReader().readFileInJar(this.getClass(), "b.txt");
```
在Test类中，释放sda.pdf到E:\中转\out.pdf:
```
boolean s = new releaseFileInJar(Test.class, "sda.pdf", "E:\\中转\\out.pdf");
System.out.println("是否释放成功：" + s);
```
在Test类中，读取这个jar根目录下的a.txt的第二行内容：
```
String ct = new JarInternalReader().readLineInJar(Test.class, "/a.txt", 2);
```
注意，表示jar根目录，路径开头加上"/"即可！<br>

>最后更新：2020.10.23