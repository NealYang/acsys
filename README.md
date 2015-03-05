acsys
=====

Accounting System

=====
Repository https://github.com/NealYang/acsys.git

=====
Start

Tomcat config file: /resources/server.xml

Run by eclipse:

Run-Run Configurations/Debug Configurations-Java Application-Create a new configuration

 - Main-Project: this project
 - Main-main class :org.apache.catalina.startup.Bootstrap

 - Arguments-Program arguments: -config "D:\java\tomcat-6.0.41\conf\server_new.xml" start
 - Arguments-VM arguments: -Dcatalina.home=D:\java\tomcat-6.0.41 -Xmx512m -Xms256m -XX:PermSize=256m -XX:MaxPermSize=512m -XX:NewSize=200m -XX:MaxNewSize=200m

 - Classpath-User Entries: bootstrap.jar - D:\java\tomcat-6.0.41\bin