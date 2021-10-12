package org_treathunter.java_university_7th_semester_ip_lab1_json_file_autontification_app.ui;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainFrame 
{
	JFrame fr;
	JButton loginButton;
	
	public MainFrame()
	{
		fr = new JFrame("Лабораторная 1");
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = fr.getContentPane();
		pane.setLayout(new BoxLayout(pane,BoxLayout.X_AXIS));
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(createUsersMenu());
		menuBar.add(createReferenceMenu());
		
		loginButton = new JButton("Вход в систему");
		//button action listener
		loginButton.setPreferredSize(new Dimension(100,50));
		pane.add(Box.createRigidArea(new Dimension(500, 0)));
		pane.add(loginButton);

		
		fr.setJMenuBar(menuBar);
		fr.setSize(1280,720);
		fr.setVisible(true);		
	}
	
	private JMenu createUsersMenu()
	{
		JMenu users = new JMenu("Пользователи");
		
		JMenuItem passwordChange = new JMenuItem("Смена пароля");
		JMenuItem addUser = new JMenuItem("Новый пользователь");
		JMenuItem showUsers = new JMenuItem("Все пользователи");
		JMenuItem exit = new JMenuItem("Выход");
		
		//action listeners
		
		users.add(passwordChange);
		users.add(addUser);
		users.add(showUsers);
		users.add(exit);
		return users;
	}
	
	private JMenu createReferenceMenu()
	{
		JMenu reference = new JMenu("Справка");
		
		JMenuItem aboutProgram = new JMenuItem("О прогамме");
		
		//action listeners
		
		reference.add(aboutProgram);
		return reference;
	}
}
