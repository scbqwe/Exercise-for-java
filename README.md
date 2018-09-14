# Exercise-for-java

>java环境配置与初步使用

1.下载jdk
http://www.oracle.com/technetwork/java/javase/overview/index.html
2.系统环境配置
 1)新建系统变量
 变量名：JAVA_HOME
 值：安装的jdk的路径 eg:C:\Program Files\Java\jdk-10.0.2
 2)新建系统变量 名：CLASSPATH 值：%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%\lib\tools.jar;%ANT_HOME%\lib;
 3）在Path中加入  %JAVA_HOME%\bin;%JAVA_HOME%\jre\bin
3.进入Dos界面
>javac java代码文件路径
将##.java文件编译成##.class文件
>java class文件名
运行class文件
