# user-login

基于spring-security的权限认证前端demo。以下是对于后端部分的解释，但是该项目主要是个**前端DEMO**。

* 实现了简单的用户管理功能,包括：
  * 用户登录
  * 用户注册
  * 用户的邮箱验证
  * 用户的密码修改
  * 用户RBAC权限管理  
  * 基于用户权限开放服务端接口
  * 通过接口实现前后端分离
  * 用户界面实现

## 访问路径设计

### 公开的路径(`permitAll`)

* `/`:网站首页
* `/static/**`:静态资源
  * `/static/css/**`:存放静态css资源  
  * `/static/html/**`:存放静态html资源  
  * `/static/img/**`:存放图片资源  
  * `/static/js/**`:存放js资源(并没有使用到)  
  * `/static/favicon.ico`:网站图标  
* `/tools/hasLogin`:ToolsController开放的接口，用于查询用户是否已经登录
* `/tools/get/authorities`:ToolsController开放的接口，用于查询用户用户拥有权限（如果未登录，用户会具有权限`ANONYMOUS`）

### 匿名用户可访问的路径(`ANONYMOUS`)

* `/stranger/**`:限定匿名用户，主要由StrangerController控制
  * `/stranger/verify/{name}/{email}`:用于注册时发送验证邮件的接口
  * `/stranger/register`:接口接收POST请求，用于用户的注册
  * `/stranger/findBack/verify/{name}`:密码找回时验证邮箱
  * `/stranger/findBack`:接口接收POST请求，用于用户的密码修改
  * `/stranger/login`:由spring-security管理，用于用户的登录验证  

### 已授权用户可访问的路径(`authenticated`)

* `/tools/get/detail`:由ToolsController开放的接口，用于查询用户的详细信息。
* `/logout`: 由spring-security管理，用于用户的登出

## 配置文件(`application.yml`)

``` yml
spring:
  datasource:
    driver-class-name: #驱动类
    url: #jdbc连接
    username: #sql用户
    password: #sql密码
    hikari:
      max-lifetime: 60000 #用于维持hikari的存活时间
  jpa:
    database: #sql
    hibernate:
      ddl-auto: update
  mvc:
    static-path-pattern: /static/** #网站的静态资源路径
  mail:
    host: #smtp服务器
    password: #邮箱密码
    username: #邮箱用户
    port: 465 #使用465端口发送邮件
    properties:
      mail:
        smtp:
          ssl:
            enable: true
      imap:
        ssl:
          socketFactory:
            fallback: false
application:
  register:
    verification-length: 5 #验证码的长度
    verification-time-out: 1800000  #未实装
    max-name-length: 32 #用户名最大长度限制
    min-name-length: 1 #用户名最短长度
    max-password-length: 32 #密码的最大长度
    min-password-length: 6 #密码的最短长度
    default-roles: #用户注册时拥有的权限
      - ROLE_USER
```