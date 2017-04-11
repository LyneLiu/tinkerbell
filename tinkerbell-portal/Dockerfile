#基础镜像：仓库是lyne/tomcat，标签用8.5-jre8-3.5
FROM tomcat:jre8
#将打包好的spring程序拷贝到容器中的指定位置
ADD target/tinkerbell-portal-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps
#容器对外暴露8080端口
EXPOSE 8080
#容器启动后需要执行的命令
CMD  ["catalina.sh", "run"]