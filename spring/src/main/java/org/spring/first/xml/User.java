package org.spring.first.xml;

public class User {

	private String userName;
	private String age;

	public User() {

	}

	public User(String userName, String age) {
		super();
		this.userName = userName;
		this.age = age;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", age=" + age + "]";
	}

}
