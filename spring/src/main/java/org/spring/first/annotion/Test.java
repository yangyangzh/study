package org.spring.first.annotion;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * spring 两大特性DI和IOC即控制反转和依赖注入 控制反转表示将对象的生成的过程交给spring容器来控制
 * 依赖注入表示将对象间的依赖关系交给spring容器 注入方式：setter方法注入 构造器注入
 * 注意各种类型的对象如何注入：List,Set,Map,Object 除了手动注入，sping还提供自动注入方式
 * 详细看http://blog.csdn.net/topwqp/article/details/8686025
 * 
 * spring配置有XML和注解两种方式，可自行选择配置方式，目前趋势是注解，xml太繁琐
 * 后面例子都尽量使用注解,spring3之后支持selvet3.0规范，不需要任何配置xml文件即可开发web项目，web.xml也不需要
 * 
 * @author Administrator
 *
 */
@Configuration
// 表示这是一个配置类的bean，其他注解使用时往往依托这个注解
@PropertySource(value = "classpath:org\\spring\\first\\annotion\\fruit.properties")
// @PropertySource启动时读取properties配置文件，继承Envirronment接口的bean都可以直接get到这里的配置
// 但是@Value中的占位符需要增加bean，在下面介绍
@ComponentScan(basePackages = "org.spring.first.annotion*")
// 表示注解扫描范围
public class Test {

	public static void main(String[] args) {

		System.out.println(System.getProperty("java.class.path"));// 系统的classpaht路径
		System.out.println(System.getProperty("user.dir"));// 用户的当前路径

		AbstractApplicationContext context = new AnnotationConfigApplicationContext(Test.class);

		Fruit f1 = (Fruit) context.getBean("fff");
		System.out.println(f1);
		Fruits fs1 = (Fruits) context.getBean("fffsss");
		System.out.println(fs1);
		
		System.out.println(context.getBean("lKL"));

		// 关闭spring content,可以调用bean的各种destory方法
		context.close();

	}

	//返回一个bean，PropertySourcesPlaceholderConfigurer用来解决@value占位符问题，想当于xml里的context:property-placeholder
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}
