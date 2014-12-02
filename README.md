puissance4
==========

#### Install tomcat

[Tomcat](http://tomcat.apache.org/download-80.cgi)

**Variables système**

    JRE_HOME = C:\Program Files\Java\jre1.8.0_25
    JAVA_HOME = C:\Program Files\Java\jdk1.8.0_25
    CATALINA_HOME = C:\tools\tomcat

#### Edit tomcat\conf\tomcat-users.xml
```xml
<role rolename="tomcat" />  
<role rolename="manager-gui" />  
<role rolename="manager-script" />  
<role rolename="admin-gui" />  
<user username="tomcat" password="tomcatuser" roles="tomcat,manager-gui,admin-gui,manager-script" />  
```

#### Launch tomcat

    ‪C:\tools\tomcat\bin\startup.bat
    
#### Maven

    mvn clean install
    mvn tomcat7:deploy

http://localhost:8080/
