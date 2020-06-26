# housing_mess_management
毕业设计--居民住房信息管理系统

## 1. 关于配置文件

配置文件在 src/main/resources 下

### 1.1  application.properties

+ server.port:80	端口号
+ spring.thymeleaf.cache=false    关闭前端页面的缓存
+ 其它在配置文件中都已经说明了



### 1.2 application.yml

我用的是MySQL 8.0 版本，数据库需要如下配置

+ driver-class-name: com.mysql.cj.jdbc.Driver	驱动
+ url: jdbc:mysql://localhost:3306/housing_info_management?useSSL=false&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=CONVERT_TO_NULL
+ username: root    数据库账号
  password: 123      数据库密码
+ type: com.alibaba.druid.pool.DruidDataSource    使用了阿里巴巴Druid数据源







## 2. 关于项目

### 2.1 居民信息的照片

+ 照片文件夹residentPhoto放在和项目同一目录下



### 2.2 发布到linux服务器

+ 修改ResidentController的fileUpload函数的path路径
+ 修改ResidentServiceImpl的deleteById函数中的path路径
+ 修改数据库名，保证和linux服务器的mysql一致
+ 修改application.yml中的 username 和 password
+ 修改端口号
+ 打成jar包发布



### 2.3 还可以打包成 Docker 镜像运行

1. 构建SpringBoot项目

2. maven打包

   `mvn package`

3. 编写dockerfile

   ```shell
   FROM java:8
   COPY *.jar /app.jar
   CMD ["--server.port=8080"]
   EXPOSE 8080
   ENTRYPOINT ["java","-jar","app.jar"]
   ```

4. 构建镜像

+ 复制jar和DockerFIle到服务器

+ 构建镜像

+ `$ docker build -t xxxxx:xx  .`

5. 发布运行

   以后我们使用了Docker之后，给别人交付就是一个镜像即可。



