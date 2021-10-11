package org_treathunter.java_university_7th_semester_ip_lab1_json_file_autontification_app;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org_treathunter.java_university_7th_semester_ip_lab1_json_file_autontification_app.file_json_storage_system.JsonFileStorage;
import org_treathunter.java_university_7th_semester_ip_lab1_json_file_autontification_app.file_json_storage_system.User;

public class Main {
	public static void main(String[] args) 
	{
		try {
			JsonFileStorage db = new JsonFileStorage();
			ArrayList<User> users= db.getUsers();
			users.forEach(System.out::println);
			//users.add(new User("wew","",User.Role.user,false,false));
			//db.setUsers(users);
			//ArrayList<User> users2= db.getUsers();
			//users2.forEach(System.out::println);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), "Exceprion: "+ e.getMessage());
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
	}
}
