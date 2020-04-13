springboot2.x 整合 Elastic-Job 时会抛出一下异常

`Caused by: java.lang.ClassNotFoundException: org.apache.curator.connection.StandardConnectionHandlingPolicy
	at java.net.URLClassLoader.findClass(URLClassLoader.java:381) ~[na:1.8.0_172]
	at java.lang.ClassLoader.loadClass(ClassLoader.java:424) ~[na:1.8.0_172]
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:349) ~[na:1.8.0_172]
	at java.lang.ClassLoader.loadClass(ClassLoader.java:357) ~[na:1.8.0_172]
	... 40 common frames omitted`
	
原因: Elastic-Job项目基于开源产品Quartz和Zookeeper及其客户端Curator进行二次开发，如果与新版的Spring Boot一起使用，会出现Curator的版本冲突。

解决办法:在pom.xml中引入低版本的Curator依赖即可

`<dependency>
    <groupId>org.apache.curator</groupId>
    <artifactId>curator-recipes</artifactId>
    <version>2.10.0</version>
</dependency>
<dependency>
    <groupId>org.apache.curator</groupId>
    <artifactId>curator-framework</artifactId>
    <version>2.10.0</version>
</dependency>`	