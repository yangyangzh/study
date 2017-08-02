package org.springboot.web.first;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 这个例子最煎蛋，直接运行main方法，访问http://localhost:8080/test/init
 * 可以看到TestController已经被发布成rest服务
 * 运行时注释或删掉所有的application.properties文件，运行时完全是springboot默认的参数
 * SpringBootApplication这个注解可以看下源码，由三个注解组成
  * SpringBootConfiguration  
  * EnableAutoConfiguration
  * ComponentScan
          可以加这个三个注解来替换这个注解
 * @author Administrator
 *
 */
@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);

	}
}
