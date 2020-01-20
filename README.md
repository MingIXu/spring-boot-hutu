[![](https://img.shields.io/badge/Author-Hutu-orange.svg)](https://github.com/MingIXu)
[![](https://img.shields.io/badge/Version-1.0.0-brightgreen.svg)](https://github.com/MingIXu/spring-boot-hutu)
[![GitHub stars](https://img.shields.io/github/stars/MingIXu/spring-boot-hutu.svg?style=social&label=Stars)](https://github.com/MingIXu/spring-boot-hutu)
[![GitHub forks](https://img.shields.io/github/forks/MingIXu/spring-boot-hutu.svg?style=social&label=Fork)](https://github.com/MingIXu/spring-boot-hutu)


## spring-boot-hutu 快速开发脚手架
* 采用前后端分离的模式，此项目是服务端代码。
* 此项目基于SpringBoot进行高度封装，模块划分清楚，根据需要增减所需的功能。
* 项目所有依赖全放到一个项目中统一管理，便于三方包的版本管理。
* 基于jwt鉴权实现，并以注解的方式做到接口级权限校验。
* 封装MybatisPlus通用数据库操作实现，做到单表操作零sql，提高开发效率。
* 项目日志的记录通过注解的方式实现，并调用异步线程写入数据库，提高主线程工作效率。
* 封装统一的返回参数格式，统一的Controller层异常处理，做到前端接口返回值规范高效。
* 封装七牛云存储操作接口，封装sm.ms免费图床上传接口，封装自定义模板的mail接口。


## 工程结构
```
spring-boot-hutu
├── hutu-boot-demo -- 业务模块
├── hutu-common -- 基础模块
├── hutu-dependencies -- 依赖管理
├── hutu-generator -- 代码生成
├── hutu-log -- 日志收集模块
├── hutu-mail -- 邮件模块
├── hutu-mybatis -- 数据库访问配置
├── hutu-security -- 安全模块
├── hutu-storage -- 对象存储
└── 
```

## 技术文档
编写中。。。