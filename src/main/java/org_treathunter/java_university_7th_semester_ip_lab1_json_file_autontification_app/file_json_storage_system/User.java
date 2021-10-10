package org_treathunter.java_university_7th_semester_ip_lab1_json_file_autontification_app.file_json_storage_system;

public class User {
	enum Role
	{
		admin,
		user
	}
	String username;
	String password;
	Role role;
	boolean isBanned;
	boolean passwordRestictions;
	
	public User(String username,String password,Role role,boolean isBanned,boolean passwordRestictions)
	{
		this.username = username;
		this.password = password;
		this.role = role;
		this.isBanned = isBanned;
		this.passwordRestictions = passwordRestictions;
	}
}
