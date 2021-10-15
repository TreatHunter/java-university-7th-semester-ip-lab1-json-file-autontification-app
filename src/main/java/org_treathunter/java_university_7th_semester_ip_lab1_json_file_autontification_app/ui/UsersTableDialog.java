package org_treathunter.java_university_7th_semester_ip_lab1_json_file_autontification_app.ui;

import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org_treathunter.java_university_7th_semester_ip_lab1_json_file_autontification_app.DataBaseSystem.DatabaseSystem;
import org_treathunter.java_university_7th_semester_ip_lab1_json_file_autontification_app.file_json_storage_system.User;

public class UsersTableDialog extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DatabaseSystem db;
	
    public UsersTableDialog(Frame parent, DatabaseSystem db) 
    {
        super(parent, "Cписок пользователей", true);
        this.db = db;
        UsersTableModel model = new UsersTableModel(db.getUsers());
        JTable usersTable = new JTable(model);
        usersTable.addMouseListener(new MouseAdapter() {
        	 public void mouseClicked(MouseEvent e) {
        		  if (e.getClickCount() == 1) {
        		   JTable target = (JTable)e.getSource();
        		   int row = target.getSelectedRow();
        		   int column = target.getSelectedColumn();
        		   ArrayList<User> usersList =db.getUsers();
        		   User currientUser = usersList.get(row);
        		   if(currientUser.getRole() == User.Role.admin)
        			   return;
        		   if(column == 1)
        		   {
        			   currientUser.setPasswordRestictions(!currientUser.isPasswordRestictions());
        		   }else if(column == 2){
        			   currientUser.setBanned(!currientUser.isBanned());
        		   }
        		   try {
					db.SaveChangesToFile();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(new JFrame(), "Exception: "+ e1.getMessage());
					System.out.println(e1.getMessage());
				}
        		  }
        		 }
        		});
        getContentPane().add(new JScrollPane(usersTable));
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
