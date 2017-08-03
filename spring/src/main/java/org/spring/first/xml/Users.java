package org.spring.first.xml;

import java.util.List;
import java.util.Map;

public class Users {

	Map<String,User> mapuser;
	
	List<User> listUser;
	
	User objctUser;

	public Map<String, User> getMapuser() {
		return mapuser;
	}

	public void setMapuser(Map<String, User> mapuser) {
		this.mapuser = mapuser;
	}

	public List<User> getListUser() {
		return listUser;
	}

	public void setListUser(List<User> listUser) {
		this.listUser = listUser;
	}

	public User getObjctUser() {
		return objctUser;
	}

	public void setObjctUser(User objctUser) {
		this.objctUser = objctUser;
	}

	@Override
	public String toString() {
		return "Users [mapuser=" + mapuser + ", listUser=" + listUser + ", objctUser=" + objctUser + "]";
	}
	
	
}
