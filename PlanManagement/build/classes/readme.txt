核心模块有
spring-core和spring-beans提供基础框架支持，包括控制反转和依赖注入等功能；
说明：The spring-core and spring-beans modules provide the fundamental parts of the framework, including the IoC and Dependency Injection features. 
spring-context
说明：Spring提供在基础IoC功能上的扩展服务，此外还提供许多企业级服务的支持，如邮件服务、任务调度、JNDI定位、EJB集成、远程访问、缓存以及各种视图层框架的封装等
spring-context-support 
说明：Spring-context的扩展支持,用于MVC方面；provides support for integrating common third-party libraries into a Spring application context for caching (EhCache, Guava, JCache), mailing (JavaMail), scheduling (CommonJ, Quartz) and template engines (FreeMarker, JasperReports, Velocity).
spring-expression
说明：Spring表达式语言；The spring-expression module provides a powerful Expression Language for querying and manipulating an object graph at runtime. 

AOP模块包含 
spring-aop
说明：The spring-aop module provides an AOP Alliance-compliant aspect-oriented programming implementation allowing you to define
spring-aspects
说明：The separate spring-aspects module provides integration with AspectJ.
spring-instrument（no）
说明：Spring对服务器的代理接口
spring-instrument-tomcat（no）
说明：对 Tomcat 的连接池的集成的包
**依赖aopalliance-1.0.jar
**依赖aspectjweaver-1.8.9.jar


Messaging模块
spring-messaging（no）
说明：模块为集成messaging api和消息协议提供支持。

Data Access/Integration模块
spring-jdbc
说明：jdbc
spring-tx
说明：事务
spring-orm（no）
说明：整合第三方的ORM框架，如hibernate,ibatis,jdo，以及 spring的JPA实现
spring-oxm（no）
说明：Spring 对Object/XMl的映射支持,可以让Java与XML之间来回切换
spring-jms（no）
说明：为简化JMS API的使用而作的简单封装

Web模块
spring-web
说明：SpringWeb下的工具包；module provides basic web-oriented integration features such as multipart file upload functionality and the initialization of the IoC container using Servlet listeners and a web-oriented application context. 
spring-webmvc
说明：这个jar文件包含Spring MVC框架相关的所有类。包含国际化、标签、Theme、视图展现的FreeMarker、JasperReports、Tiles、Velocity、XSLT相关类。当然，如果你的应用使用了独立的MVC框架，则无需这个JAR文件里的任何类。
说明：The spring-webmvc module (also known as the Web-Servlet module) contains Spring’s model-view-controller (MVC) and REST Web Services implementation for web applications.
spring-websocket（no）
说明：搭建websocket服务
spring-webmvc-portlet（no）
说明：

Test模块
spring-test
说明：测试

shiro模块
shiro-core
shiro-web：Enables support for web-based applications.
shiro-ehcache：Enables Ehcache-based famework caching.
shiro-spring：Enables Spring Framework integration.

其它
fastjson 1.2.29
Jackson Jackson可以轻松的将Java对象转换成json对象和xml文档，同样也可以将json、xml转换成Java对象。
log4j
log4j的依赖slf4j：
SLF4J，即简单日志门面（Simple Logging Facade for Java），不是具体的日志解决方案，它只服务于各种各样的日志系统。按照官方的说法，SLF4J是一个用于日志系统的简单Facade，允许最终用户在部署其应用时使用其所希望的日志System
slf4j-api
commons-logging：common-logging是apache提供的一个通用的日志接口。
commons-dbcp2
commons-pool2
commons-fileupload
commons-io
