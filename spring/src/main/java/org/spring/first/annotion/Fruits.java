package org.spring.first.annotion;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component(value = "fffsss")
public class Fruits {

	@Resource(name="ogrg") //可单独使用，byName来注入
	//@Autowired //先byType注入，如果有多个相同的Type的bean，在byName,如果仍没有，报错;可配合Qualifier来byName注入
	//@Qualifier(value="ogrg") //
	private Fruit f;

	public Fruit getF() {
		return f;
	}

	public void setF(Fruit f) {
		this.f = f;
	}

	@Override
	public String toString() {
		return "Fruits [f=" + f + "]";
	}
	
	@Bean(name="lKL")
	//@Bean生成了一个Fruit类型的bean
	public Fruit newFruit(){
		return new Fruit("Banana");
	}
	


}
