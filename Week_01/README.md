# 学习笔记





## Week1-01

**问题2：自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 hello 方法，
此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件 **

​	作业连接:https://github.com/Niverkk/JAVA-01/tree/main/Week_01/week01/src/info/niverkk/jvm



**问题3：画一张图，展示 Xmx、Xms、Xmn、Metaspache、DirectMemory、Xss
这些内存参数的关系 **

​	![](week01\resources\JVM内存.png)



-XmxSize  最大堆内存

-XmsSize   初始堆内存

-XmnSize   年轻代大小

-XX:MetaspaceSize=Size  初始元空间大小（元空间为方法区的实现，受到GC影响）

-XX:DirectMemory=Size    直接内存（不是虚拟机运行时数据区的一部分，不受堆内存大小影响，属于堆外内存）

-XssSize  单个线程的栈内存大小

> Size可为1g，2g，512m等形式的参数











