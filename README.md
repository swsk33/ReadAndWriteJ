# ReadAndWriteJ使用说明
### 这是一个简单的包，用于快速方便地写入、读取文件。<br>
### 其功能有：
1，写入文件：把指定内容写入指定文件（按行写入），替换文件某行内容，清空文件。<br>
2，行数读取：读取文本文档行数。<br>
3，指定读取：读取指定行内容，读取整个文件的内容，读取指定文件行数范围的内容。<br>
4，随机读取：随机读取指定文件某一行，从指定行开始随机读取文件后面的某行，从第一行开始随机读取文本文档前指定行数，从某行起随机读取后指定行。<br>
5，文本对比：比较某一行内容是否与被比较字符串一致，判断文本文档内是否包含被比较的字符串。<br>
6，文件对比：对比两个文件内容是否一致。<br>
7，引入字体：在GUI编程中可以快速指定字体文件以设置字体。<br>
8，文件分析器：读取文件大小和格式（扩展名）。<br>
### 下载地址:[点击进入下载jar包](https://gitee.com/swsk33/ReadAndWriteJ/releases)
## 使用方法：
### 1，添加依赖：
 ①Eclipse直接添加jar：先把这个包导入到IDE里面，例如eclipse。不知道如何导入请查看教程：[eclipse导入外部jar包](https://blog.csdn.net/czbqoo01/article/details/72803450)<br>
 ②Maven工程：在项目的配置文件pom.xml中的```<dependencies>```标签里加入下列依赖，此操作无需在上面手动下载jar包（推荐）：
 ```
 <dependency>
 	<groupId>com.gitee.swsk33</groupId>
 	<artifactId>ReadAndWriteJ</artifactId>
 	<version>3.0.1</version>
 </dependency>
 ```
### 2，导入swsk33.RaW下所有类或者需要的类。（import swsk33.RaW.*;）
### 3，语法：
**说在最前：下面语法示例中用了最快捷的方法去执行了某个类中的某个方法。**<br>
**实际上这两种方式执行效果相同：**<br>
**方式一：**<br>
```A a=new A();```<br>
```a.af();```<br>
**方式二:**<br>
```new A().af();```<br>
**上述方式一、二效果相同，都是执行了A类里的af方法。只是方法一先生成了对象。下面示例基本上用方法二进行演示。**<br>
--------------------------------------------------------------------------------------------------------------------
#### (1)文件写入(返回值void)：
写入文件：```new TextFileWriter().writeText(文件目录,写入内容);```<br>
替换文件某行内容：```new TextFileWriter().replaceLine(文件目录,待替换行数,待替换内容);```<br>
清空文件所有内容：```new TextFileWriter().clearAll(文件目录);```<br>
#### (2)读取文本文档行数(返回值int)：
```new LineScanner.getLineCount(文件目录)```<br>
例如输出D盘的1.txt的文件行数：<br>
```LineScanner la=new LineScanner();```<br>
```System.out.println(la.getLineCount("D:\\1.txt"));```
#### (3)读取指定行内容(返回值String或者String[])：
读取指定行：```new TextReader().readText(文件目录,读取第几行);```<br>
读取指定行数范围的内容（从第m行读取至第n行）并返回字符串：```new TextReader().readFileRange(文件路径,起始行,终止行);```<br>
读取指定行数范围的内容（从第m行读取至第n行）并返回字符串数组：```new TextReader().readFileRangeToArray(文件路径,起始行,终止行);```<br>
读取整个文件所有内容并返回字符串：```new TextReader().readFile(文件目录);```<br>
读取整个文件所有内容并返回字符串数组：```new TextReader().readFileToArray(文件目录);```<br>
例如读取D盘的1.txt的第3行并输出：<br>
```TextReader tr=new TextReader();```<br>
```System.out.println(tr.readText("D:\\1.txt", 3));```<br>
例如从D盘的2.txt的第3行开始读取至第7行的内容并输出:<br>
```TextReader tr=new TextReader();```<br>
```System.out.println(tr.readFileRange("D:\\2.txt", 3, 7));```<br>
#### (4)随机读取(返回值String)：
随机读取指定文件某一行:<br>```new RandomReader().readRandomly(文件目录);```<br>
从指定行开始随机读取文件后面的某行：<br>```new RandomReader().readRandomlyStart(文件目录,开始行数);```<br>
从第一行开始随机读取文本文档前指定行数内一行：<br>```new RandomReader().readRandomlyUntil(文件目录,结束行数);```<br>
从某行起随机读取后指定行数内的某一行：<br>```new RandomReader().randomAtSpecifiedRanges(文件目录,开始行数,向后划定行数);```<br>
#### (5)文本对比(返回值boolean):
比较某一行内容是否与被比较字符串一致：<br>```new StringComparer().compareLine(文件目录,待比较的字符串);```<br>
判断文本文档内是否包含某字符串:<br>```new StringComparer().compareText(文件目录,待比较的字符串);```<br>
#### (6)引入字体文件作为字体对象(返回值Font):
这个是用于GUI中的，可以引用系统未安装的字体文件作为字体对象。
例如：<br>
新建一个通常字体对象f:<br>
```Font f=new ImportFont().getFont(字体文件路径,字体大小);```<br>
新建一个加粗字体对象fb:<br>
```Font fb=new ImportFont().getBoldFont(字体文件路径,字体大小);```<br>
新建一个倾斜字体对象fl:<br>
```Font fl=new ImportFont().getItalicFont(字体文件路径,字体大小);```<br>
新建一个倾斜加粗体字体对象flb:<br>
```Font flb=new ImportFont().getItalicBoldFont(字体文件路径,字体大小);```<br>
#### (7)比较两个文件里的内容是否完全相同(返回值boolean)
也就是比较两个文件是否完全相同，例如比较文件1与文件2里所包含的内容是否完全相同:<br>
```new FileComparer().compareFile(文件1路径,文件2路径)```<br>
#### (8)读取文件信息：
##### (8.1)读取文件MD5(返回值String):
```new FileAnalyzer().getFileMD5(文件路径)```<br>
##### (8.2)读取文件大小(返回值long和double):
获取文件大小，单位字节(Byte)(返回值long):<br>
```new FileAnalyzer().getFileSizeb(文件路径)```<br>
获取文件大小，单位千字节(KB)(返回值double):<br>
```new FileAnalyzer().getFileSizekb(文件路径)```<br>
获取文件大小并设定保留小数点位数，单位千字节(KB)(返回值double):<br>
```new FileAnalyzer().getFileSizekb(文件路径,要保留的小数点位数)```<br>
获取文件大小，单位兆字节(MB)(返回值double):<br>
```new FileAnalyzer().getFileSizemb(文件路径)```<br>
获取文件大小并设定保留小数点位数，单位兆字节(MB)(返回值double):<br>
```new FileAnalyzer().getFileSizemb(文件路径,要保留的小数点位数)```<br>
获取文件大小，单位吉字节(GB)(返回值double):<br>
```new FileAnalyzer().getFileSizegb(文件路径)```<br>
获取文件大小并设定保留小数点位数，单位吉字节(GB)(返回值double):<br>
```new FileAnalyzer().getFileSizegb(文件路径,要保留的小数点位数)```<br>
##### (8.3)读取文件扩展名(返回值String):
```new FileAnalyzer().getFileFormat(文件路径)```<br>
无扩展名文件会返回空值并显示文件没有扩展名。