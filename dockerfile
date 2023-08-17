# 基础镜像
FROM openjdk:8-jdk-alpine
# 作者
MAINTAINER zhishikaishi
# 挂载目录(容器启动时)
VOLUME /usr/local/app
# 创建目录（该命令是容器运行时才会执行）
RUN mkdir -p /usr/local/app
# 指定路径
WORKDIR /usr/local/app
# 把当前路径的target目录下的jar包拷贝到镜像中，并重命名为water.jar
COPY ./target/test.jar /usr/local/app/nacostest.jar
ENTRYPOINT ["java","-jar","/usr/local/app/nacostest.jar"]
