# ReadAndWriteJ使用说明
### 这是一个简单的包，用于快速方便地写入、读取文件。<br>
### 其功能有：
1，写入文件：把指定内容写入指定文件(按行写入),替换文件某行内容。<br>
2，行数读取：读取文本文档行数。<br>
3，指定读取：读取指定行内容，读取整个文件的内容，读取文件指定行之后的所有内容，读取文件指定行之前的所有内容，读取文件的扩展名。<br>
4，随机读取：随机读取指定文件某一行，从指定行开始随机读取文件后面的某行，从第一行开始随机读取文本文档前指定行数，从某行起随机读取后指定行。<br>
5，文本对比：(1)比较某一行内容是否与被比较字符串一致(2)判断文本文档内是否包含被比较的字符串<br>
6，文件对比：对比两个文件内容是否一致。<br>
7，引入字体：在GUI编程中可以快速指定字体文件以设置字体。<br>
8，图像读取：获取图片文件宽，获取图片文件高，获取图片对象（防止重复操作时内存错误）。<br>
### 下载地址:[点击进入下载jar包](https://gitee.com/swsk33/ReadAndWriteJ/releases)
## 使用方法：
### 1，先把这个包导入到IDE里面，例如eclipse。不知道如何导入请查看教程：[eclipse导入外部jar包](https://blog.csdn.net/czbqoo01/article/details/72803450)
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
------------------------------------------------------------------------------------------------------------------------------
#### (1)文件写入(返回值void)：
写入文件：```new TextFileWriter().writeText(文件目录,写入内容);```<br>
替换文件某行内容：```new TextFileWriter().replaceLine(文件目录,待替换行数,待替换内容);```<br>
清空文件所有内容：```new TextFileWriter().clearAll(文件目录);```<br>
#### (2)读取文本文档行数(返回值int)：
```new LineScanner.GetLinage(文件目录)```<br>
例如输出D盘的1.txt的文件行数：<br>
```LineScanner la=new LineScanner();```<br>
```System.out.println(la.GetLinage("D:\\1.txt"));```
#### (3)读取指定行内容(返回值String)：
读取指定行：```new TextReader().ReadText(文件目录，读取第几行);```<br>
读取指定行之后的所有内容：```new TextReader().ReadFileStart(文件目录,起始行);```<br>
读取指定行之前的所有内容：```new TextReader().ReadFileUntil(文件目录,终止行);```<br>
读取整个文件所有内容：```new TextReader().ReadFile(文件目录);```<br>
例如读取D盘的1.txt的第3行并输出：<br>
```TextReader tr=new TextReader();```<br>
```System.out.println(tr.ReadText("D:\\1.txt",3));```
#### (4)随机读取(返回值String)：
随机读取指定文件某一行:<br>```new RandomReader().ReadRandomly(文件目录);```<br>
从指定行开始随机读取文件后面的某行：<br>```new RandomReader().ReadRandomlyStart(文件目录,开始行数);```<br>
从第一行开始随机读取文本文档前指定行数内一行：<br>```new RandomReader().ReadRandomlyUntil(文件目录,结束行数);```<br>
从某行起随机读取后指定行数内的某一行：<br>```new RandomReader().RandomAtSpecifiedRanges(文件目录,开始行数,向后划定行数);```<br>
#### (5)文本对比(返回值boolean):
比较某一行内容是否与被比较字符串一致：<br>```new StringComparer().CompareLine(文件目录,待比较的字符串);```<br>
判断文本文档内是否包含某字符串:<br>```new StringComparer().CompareText(文件目录,待比较的字符串);```<br>
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
```new FileComparer().comFile(文件1路径,文件2路径)```<br>
#### (8)获取图片文件信息：
##### （8.1）获取图片宽高(返回值int):
获取图片文件宽：<br>
```new ImageReader().getImageWidth(图片文件路径)```<br>
获取图片文件高：<br>
```new ImageReader().getImageHeight(图片文件路径)```<br>
##### (8.2)获取图片对象(返回值ImageIcon):
用于GUI开发中，这里另行获取防止重复获取图片是内存出错。<br>
```ImageIcon ic=new ImageReader().getImg(文件图片路径)```<br>