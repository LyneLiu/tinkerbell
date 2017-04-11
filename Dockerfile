#基础镜像：仓库是java，标签用3.3.9-jdk-8
FROM maven:3.3.9-jdk-8
#当前镜像的维护者和联系方式
MAINTAINER lyne lyne@liu332520@163.com
#将打包好的spring程序拷贝到容器中的指定位置
ADD tinkerbell-portal/target/tinkerbell-portal-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps
#容器对外暴露8080端口
EXPOSE 8080
#容器启动后需要执行的命令
CMD  ["catalina.sh", "run"]