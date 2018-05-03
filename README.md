# zsso
可跨域的单点登录系统（包含server端和client端）  
本工程一共分为4个模块：zsso-client,zsso-client-demo,zsso-common,zsso-server  
职责划分如下：  
zsso-client:客户端相关代码，以jar包形式存在，客户端接入时需要依赖此模块，此模块集成了跟server端的交互逻辑已经对于zsso命名空间的支持  
zsso-server:服务端实现，以war包形式存在，直接部署  
zsso-common:公共模块  
zsso-client-demo:客户端demo，供使用者参考  
使用方式：  
1.在web.xml中配置过滤器 
```java
  <filter>
    <filter-name>zssoFilter</filter-name>
    <filter-class>com.zdp.zsso.client.filter.ZssoFilter</filter-class>
  </filter>
  <filter-mapping>
    <filter-name>zssoFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
 ```
2.在应用层spring配置文件配置自动驱动 
```java
<zsso:auto-driven/>  
```
3.配置zsso-client配置文件，配置文件可以分环境配置，分别见src/main/profiles/(dev|beta|product)，配置项示例如下：  
```java
#系统名，需要在server端备案  
zsso.system.name=wiki  
#本地cookie的domain  
zsso.system.cookie.domain=.wiki.com  
#server端url前缀  
zsso.server.url.prefix=http://www.zsso.com:8080  
```
4.按需拓展相关组件，组件一共包括4个部分，且都有默认实现，接入方可以按需自己实现相关组件：  
UrlHelper:拼接url的组件，如无特殊需求不建议重新实现  
ZssoClient:跟服务端交互的组件，如无特殊需求不建议重新实现  
ZssoConfigResolver:解析配置文件的组件，默认实现是读取本地classpath下zsso-client.properties配置文件，如无特殊需求不建议重新实现  
UserStore:存取用户信息的组件，包括根据token解析用户信息，绑定token与userId关系，解绑token，默认实现只是为了演示用，接入方请务必自己实现此接口  
5.快速使用：  
5.1.为了模拟跨域的情况，先绑定本地host  
```java
127.0.0.1       www.zsso.com  
127.0.0.1       www.wiki.com  
127.0.0.1       www.bbs.com  
```
5.2.启动zsso-server端,使用8080端口  
5.3.启动zsso-client-demo，使用8081端口  
5.4.访问http://www.wiki.com:8081/profile/detail?id=123  
5.5.在zsso登录页登录，账号：zhoudapeng 密码：12345  


