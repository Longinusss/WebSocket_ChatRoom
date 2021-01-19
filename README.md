# Echat-SpringBoot
一款轻量级的基于SpringBoot + WebSocket的在线聊天室项目，在MccreeFei的聊天室基础上，将其升级为SpringBoot版本，去掉了JSP文件，去掉了xml编写的sql语句，使用thymeleaf以及注解的形式，更便于维护和使用。

#运行
1、先用JAVA的keytool生成pfx证书，以便https服务器用
https://blog.csdn.net/site_dave/article/details/90699459

keytool -genkey -alias test -keypass 123456 -keyalg RSA -sigalg sha256withrsa -keysize 1024 -validity 365 -keystore D:\KSEC\2021\1\WebSocket\Echat-springboot-master\src\main\resources\test.jks -storepass 123456
运行src/main/java/../util/JKS2PFX

2、运行.sql   (mysql 8+)
修改端口号为8088 和数据库配置(yml)和pfx证书目录（application.properties）
3、运行，访问首页      https://localhost:8088/

