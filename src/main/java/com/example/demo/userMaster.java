package com.example.demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Users")
public class userMaster {
	@Id
	public String id;
	public String name;
	public String password;
	
	
	public userMaster(String name,String password) {
		super();
		this.name = name;
		this.password = password;
	}
	
	public userMaster() {
		// TODO Auto-generated constructor stub
	}
	
	
	public String getName() {
		return name;
	}
	
	public String GetPassword()
	{
		return password;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "userMaster [id=" + id + ", name=" + name + "]";
	}

	
	
}

