# hello-spring

使用：
docker执行mysql5.7下docker-compose

功能

1.多模块拆分,使用maven profile管理多环境

2.引入依赖

3.引入mysql+mybatis

4.日志

5.全局异常处理

6.引入redis

7.mybais多数据源,使用德鲁伊数据连接池,动态数据源切换

8.使用maven-resources-plugin插件将其他模块的配置文件打包到任意模块下；
使用maven-assembly-plugin插件将bin目录、jar包、yml打包至根目录；
运行 jar 包读取外部配置文件(默认：相同目录下的配置文件优先级要高于jar包中的配置文件。)

9.java8相关 Date/time API、函数式编程、Lambda表达式

10.定时任务/定时器（多种常用corn表达式举例）

11.使用参数校验

12.spring security 
RBAC权限模型
支持前后端分离开发模式
重写登录过滤器
csrf token校验
cors 跨域问题
rememberMe 持久化，防止客户端session过期
session  控制单个用户最大连接数、达到最大连接禁止登录
自定义异常处理

遗留问题
1.docker网络
2.validation验证get请求

计划
java8相关：并发（Concurrency）
mysql相关：分库分表，事务,超时（异常配置）
docker:基本使用
redis：锁
netty

web3
