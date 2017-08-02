springboot
目的：利用约定俗成的配置简化spring工程的配置和开发，使原来繁琐的配置变得简介
springboot本身没有提供特定的功能，只是方便开发，需要增加功能时，只需要应用对应的springboot依赖
	如需要实现web功能只需要添加spring-boot-starter-web依赖
springboot项目是微服务的发展发向，也是下一代微服务框架spring cloud的基础

学习要点：

如何搭建springboot项目
1.创建一个maven项目
2.pom.xml
加入：	
	<!--加入springboot的依赖-->
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>1.5.2.RELEASE</version>
	</parent>
	
	<!--springboot编译插件-->
	<build>
		<pluginManagement>
			<plugins>
				<plugin>
					<groupId>org.springframework.boot</groupId>
					<artifactId>spring-boot-maven-plugin</artifactId>
				</plugin>
			</plugins>
		</pluginManagement>
	</build>
	
	
3.pom.xml
加入：需要引用的模块，具体模块需要官网查看或百度搜索

如：
		<!--增加web功能-->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		
		<!--增加redis功能-->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-redis</artifactId>
		</dependency>
		
		<!--增加mybaits功能-->
		<dependency>
			<groupId>org.mybatis.spring.boot</groupId>
			<artifactId>mybatis-spring-boot-starter</artifactId>
			<version>1.3.0</version>
		</dependency>

4.代码看例子吧



springboot配置文件，以及优先级
springboot启动时会检查配置文件和classpath下的依赖，如果用到则会默认启动依赖功能
配置文件
默认加载：application.properties，存在优先级

1.当前目录的/config子目录。
2.当前目录
3.classpath 中的/config包。
4.classpath

如果1中有application.properties文件则会去1中的配置信息
如果1没有，则取2中的配置
以此类推

配置文件也可以自定义，需要在启动时指定
详细配置问题可见：http://www.cnblogs.com/softidea/p/5759180.html


常见springboot模块的功能引入