# 学习笔记



## 学习总结





## 本周作业

### Week03 作业题目（周三）：

##### 1.（必做）整合你上次作业的 httpclient/okhttp；

完成，整合进老师项目，写回响应是仿照老师代码写的。

代码(https://github.com/Niverkk/JAVA-01/blob/main/Week_03/src/main/java/io/github/kimmking/gateway/utils/MyHttpClientUtils.java)

##### 2.（选做）使用 netty 实现后端 http 访问（代替上一步骤）

未做



### Week03 作业题目（周日）：

##### 1.（必做）实现过滤器。

完成，只是做了请求头简单增强。

代码(https://github.com/Niverkk/JAVA-01/blob/main/Week_03/src/main/java/io/github/kimmking/gateway/filter/HeaderHttpRequestFilter.java)

##### 2.（选做）实现路由。

完成。

实现了了轮询路由，轮询访问后端。

代码(https://github.com/Niverkk/JAVA-01/blob/main/Week_03/src/main/java/io/github/kimmking/gateway/router/LoadBalanceEndpointRouter.java)



handle处理(https://github.com/Niverkk/JAVA-01/blob/main/Week_03/src/main/java/io/github/kimmking/gateway/outbound/httpclient4/HttpOutboundHandler.java#L86-L96)



##### 3.（选做）跑一跑课上的各个例子，加深对多线程的理解



##### 4.（选做）完善网关的例子，试着调整其中的线程池参数