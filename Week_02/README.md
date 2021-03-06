#学习笔记



## 学习笔记

​	待总结...



## 作业提交



**Week02 作业题目（周三）：**

**1.（选做）**使用 GCLogAnalysis.java 自己演练一遍串行 / 并行 /CMS/G1 的案例。



**2.（选做）**使用压测工具（wrk 或 sb），演练 gateway-server-0.0.1-SNAPSHOT.jar 示例。



**4.（必做）**根据上述自己对于 1 和 2 的演示，写一段对于不同 GC 和堆内存的总结，提交到 GitHub。

针对1使用各个垃圾收集器及JVM内存参数做了实验，小结如下：

​	1. 并行GC使用多线程，所以比单线程GC，GC时间更快

​	而CMS和G1更注重低延迟，整体STW时间比并行小，但是一次完整的GC过程（包括与用户线程并发的过程）时间明显

​	比并行GC多

 2. 随着内存增加，GC与FUllGC次数减少，但单次GC时间增长，针对实验的一秒钟来讲，并不能想当然内存越大越好，

    大内存会带来GC时间过长问题，反而可能降低效率



**对应JVM参数是：-XX:+UseSerialGC**

* 开启后会使用：Serial(Young区用) + Serial Old(Old区用) 的收集器组合
* 表示：新生代、老年代都会使用串行回收收集器，新生代使用复制算法，老年代使用标记-整理算法
* 会产生STW（stop the world），目前主要用于单核机子上



**常用JVM参数：-XX:+UseParallelGC 或 -XX:+UseParallelOldGC（可互相激活）使用Parallel Scanvenge收集器**

* 开启该参数后：新生代使用复制算法，老年代使用标记-整理算法
* 相比于serialgc是单线程，parallel使用多线程进行gc，同样会造成STW，吞吐性好



**JVM参数： -XX:+UseConcMarkSweepGC**

* 开启该参数后，会自动将 -XX:+UseParNewGC打开，开启该参数后，使用ParNew(young 区用）+ CMS（Old 区用） + Serial Old 的收集器组合，Serial Old将作为CMS出错的后备收集器

* ```java
   Concurrent Mark Sweep：并发标记清除，并发收集低停顿，并发指的是与用户线程一起执行
  * 四个步骤
  *  初始标记（CMS initial mark）
  *      只是标记一个GC Roots 能直接关联的对象，速度很快，仍然需要暂停所有的工作线程
  *  并发标记（CMS concurrent mark）和用户线程一起
  *      进行GC Roots跟踪过程，和用户线程一起工作，不需要暂停工作线程。主要标记过程，标记全部对象
  *  重新标记（CMS remark）
  *      为了修正在并发标记期间，因用户程序继续运行而导致标记产生变动的那一部分对象的标记记录，仍然需要暂停所有的工作线程，
  *      由于并发标记时，用户线程依然运行，因此在正式清理前，在做修正
  *  并发清除（CMS concurrent sweep）和用户线程一起
  *      清除GC Roots不可达对象，和用户线程一起工作，不需要暂停工作线程。基于标记结果，直接清理对象，
  *      由于耗时最长的并发标记和并发清除过程中，垃圾收集线程可以和用户现在一起并发工作，所以总体上来看CMS收集器的
  *      内存回收和用户线程是一起并发地执行。
  *
  * 优点：并发收集低停顿
  * 缺点：并发执行，对CPU资源压力大，采用的标记清除算法会导致大量碎片
  *
  * 由于并发进行，CMS在收集与应用线程会同时增加对堆内存的占用，也就是说，CMS必须在老年代堆内存用尽之前完成垃圾回收，
  * 否则CMS回收失败时，将触发担保机制，串行老年代收集器将会以STW方式进行一次GC，从而造成较大的停顿时间
  ```



**JVM参数：-XX:+UseG1GC****

G1整体基于标记整理算法，局部基于标记复制算法



四个步骤

* 初始标记（CMS initial marking）（**需要STW**）

  ​	只是标记一个GC Roots 能直接关联的对象，速度很快，仍然需要暂停所有的工作线程

* 并发标记（CMS concurrent marking）和用户线程一起

  ​	进行GC Roots跟踪过程，和用户线程一起工作，不需要暂停工作线程。主要标记过程，标记全部对象

* 最终标记（CMS final marking）（**需要STW**）

  ​	对用户线程做一个短暂的暂停，用于处理并发阶段结束后遗留下来的最后那少量的SATB记录

* 筛选回收（Live Data Counting and Evacuation）和用户线程一起（**需要STW**）

  ​	负责更新并清理region的空间

  > G1中基于Region的堆内存布局，虽然每一个Region也可以扮演新生代，老年代等角色



---



**Week02 作业题目（周日）：**

**1.（选做）**运行课上的例子，以及 Netty 的例子，分析相关现象。

运行了serversocket三种形式：

1. 一次接收并处理一个socket请求

2. 接收到请求后开启单独线程处理socket请求

3. 在**2的基础**上使用线程池管理线程

预期结果：处理请求数 3>2>1.

实际结果如图：第三种方式并没有预期中比2好。个人实验结果是差不多



第一种方式结果：

![01](https://github.com/Niverkk/JAVA-01/tree/main/Week_02/src/main/resources/serversocket01.png)



第二种方式结果：

![](https://github.com/Niverkk/JAVA-01/tree/main/Week_02/src/main/resources/serversocket02.png)



第三种方式结果：

![](https://github.com/Niverkk/JAVA-01/tree/main/Week_02/src/main/resources/serversocket03.png)




**2.（必做）**写一段代码，使用 HttpClient 或 OkHttp 访问 [http://localhost:8801 ](http://localhost:8801/)，代码提交到 GitHub

代码地址：(https://github.com/Niverkk/JAVA-01/blob/main/Week_02/src/main/java/info/niverkk/course04/HttpClientDemo.java)