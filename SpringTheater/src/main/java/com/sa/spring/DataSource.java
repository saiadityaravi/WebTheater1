package com.sa.spring;

public class DataSource {
	String driver="com.mysql.jdbc.Driver";
	String url="jdbc:mysql://localhost:3306/webtheater";
	String username="root";
	String password="admin";
	public String getDriver() {
		return driver;
	}
	public String getUrl() {
		return url;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}

}
