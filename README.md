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
    <version>4.5.10</version>
</dependency>
```
### 2，导入swsk33.readandwritej下所有类或者需要的类。（import swsk33.readandwritej.*;）
### 3，公共类和方法：
#### 类**FileAnalyzer**：用于分析文件信息，其方法有：
* String getFileMD5(String filePath)
	+ 作用：用于获取文件的MD5值 
	+ 参数：
		- filePath 文件的相对路径/绝对路径
	+ 返回值：String 文件大小

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