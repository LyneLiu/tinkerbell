#基础镜像：仓库是lyne/tomcat，标签用8.5-jre8-3.5
FROM lyne/tomcat:8.5-jre8-3.5
#当前镜像的维护者和联系方式
MAINTAINER lyne lyne@liu332520@163.com
#将打包好的spring程序拷贝到容器中的指定位置
ADD tinkerbell-portal/target/tinkerbell-portal-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps
#容器对外暴露8080端口
EXPOSE 8080
#容器启动后需要执行的命令
CMD  ["catalina.sh", "run"]