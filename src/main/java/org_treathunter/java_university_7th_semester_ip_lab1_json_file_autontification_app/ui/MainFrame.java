package org_treathunter.java_university_7th_semester_ip_lab1_json_file_autontification_app.ui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org_treathunter.java_university_7th_semester_ip_lab1_json_file_autontification_app.DataBaseSystem.DatabaseSystem;
import org_treathunter.java_university_7th_semester_ip_lab1_json_file_autontification_app.file_json_storage_system.User;

public class MainFrame 
{
	JFrame fr;
	JButton loginButton;
	DatabaseSystem db;
	JMenu usersMenu;
	JMenuItem passwordChange ;
	JMenuItem addUser;
	JMenuItem showUsers;
	JMenuItem exit;	
	
	public MainFrame() throws Exception
	{
		db = new DatabaseSystem();
		fr = new JFrame("Лабораторная 1");
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = fr.getContentPane();
		pane.setLayout(new BoxLayout(pane,BoxLayout.X_AXIS));
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(createUsersMenu());
		usersMenu.setVisible(false);
		menuBar.add(createReferenceMenu());
		
		loginButton = new JButton("Вход в систему");
		//button action listener
		loginButton.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e) {
						User user = null;
						while(user == null)
						{
							UsernameDialog usernameDlg = new UsernameDialog(fr);
							usernameDlg.setVisible(true);
	                        if(usernameDlg.isLoginAttempt())
	                        {
	                        	user = db.getUserByUsername(usernameDlg.getUsername());
	                        	if(user == null)
	                        	{
	                        		JOptionPane.showMessageDialog(fr,
		                                    "Такого пользователя не существует",
		                                    "Пользователь не найден",
		                                    JOptionPane.ERROR_MESSAGE);
	                        	}
	                        }
	                        else 
	                        {
	                        	return;
	                        }        
						}
						if(user.getPassword().length() == 0)
						{
							while(user.getPassword().length() == 0)
							{
								PasswordChangeDialog pswrdChngDlg = new PasswordChangeDialog(fr);
								pswrdChngDlg.setVisible(true);
		                        if(pswrdChngDlg.isLoginAttempt())
		                        {
		                        	user.setPassword(pswrdChngDlg.getResPassword());
		                        }
		                        else 
		                        {
		                        	return; 
		                        }  								
							}
							try {
								db.SaveChangesToFile();
							} catch (Exception ex) {
								JOptionPane.showMessageDialog(new JFrame(), "Exception: "+ ex.getMessage());
								System.out.println(ex.getMessage());
								System.exit(0);
							}
						}
						else 
						{
							int atemtNumber = 3;	
							do
							{
								PasswordDialog pswrdDlg = new PasswordDialog(fr);
								pswrdDlg.setVisible(true);
								try {
									Thread.sleep(100);
								} catch (InterruptedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
		                        if(pswrdDlg.isLoginAttempt())
		                        {
		                        	if(user.getPassword().equals(pswrdDlg.getPassword()))
		                        	{
		                        		break;
		                        	}else {
										atemtNumber--;
										if(atemtNumber == 0)
										{
											return;
										}
		                        		JOptionPane.showMessageDialog(fr,
			                                    "Не правильный пароль. Осталось попыток: " + atemtNumber,
			                                    "Не правильный пароль",
			                                    JOptionPane.ERROR_MESSAGE);
		                        	}
		                        }
		                        else 
		                        {
		                        	return; 
		                        }  
							}while(atemtNumber != 0);
						}
                		JOptionPane.showMessageDialog(fr,
                                user.getUsername()+", Вы вошли в систему",
                                "Успешный вход",
                                JOptionPane.INFORMATION_MESSAGE
                                );
                		loginButton.setVisible(false);
                		usersMenu.setVisible(true);
                		if(user.getRole().equals(User.Role.user))
                		{
                			passwordChange.setVisible(true);
                			addUser.setVisible(false);
                			showUsers.setVisible(false);
                			exit.setVisible(true);
                		}else {
                			passwordChange.setVisible(true);
                			addUser.setVisible(true);
                			showUsers.setVisible(true);
                			exit.setVisible(true);
                		}
						
					}
				});
		loginButton.setPreferredSize(new Dimension(100,50));
		pane.add(Box.createRigidArea(new Dimension(500, 0)));
		pane.add(loginButton);

		
		fr.setJMenuBar(menuBar);
		fr.setSize(1280,720);
		fr.setVisible(true);		
	}
	
	private JMenu createUsersMenu()
	{
		usersMenu = new JMenu("Пользователи");
		
		passwordChange = new JMenuItem("Смена пароля");
		addUser = new JMenuItem("Новый пользователь");
		showUsers = new JMenuItem("Все пользователи");
		exit = new JMenuItem("Выход");
		
		//action listeners
		
		usersMenu.add(passwordChange);
		usersMenu.add(addUser);
		usersMenu.add(showUsers);
		usersMenu.add(exit);
		return usersMenu;
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