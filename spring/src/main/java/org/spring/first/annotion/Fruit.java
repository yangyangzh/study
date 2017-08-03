package org.spring.first.annotion;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration继承了Component注解，表示自己是一个bean，同时也是也相当于xml里的beans
 * 在Configuration注解的类里，可以有多个@Bean注解的方法，每个方法都生成一个bean，相当于xml里的bean
 * 在由Component注解的类里，可直接使用@Value来获取properties文件里的属性,需要先配置读取配置文件
 * @author Administrator
 *
 */
@Configuration(value="fff")
public class Fruit {

	@Value("${fruit.name}")
	private String name;
	
	public Fruit() {
	}

	public Fruit(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Fruit [name=" + name + "]";
	}
	
	@Bean(name="ogrg")
	//@Bean生成了一个Fruit类型的bean
	public Fruit newFruit(){
		return new Fruit("Banana");
	}
	
    @PostConstruct
    //@PostConstruct注解后的方法，在bean初始化后执行，具体执行时的顺序在bean的生命周期的例子里
    public void  init(){  
        System.out.println(" I'm  init method  using  @PostConstrut....");  
    }  
      
    @PreDestroy
    //@PreDestroy注解后的方法，在bean被销毁前执行
    public void  dostory(){  
        System.out.println("I'm  destory method  using  @PreDestroy.....");  
    }  
	
	
}
