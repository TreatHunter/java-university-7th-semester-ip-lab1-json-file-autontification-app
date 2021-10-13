package org_treathunter.java_university_7th_semester_ip_lab1_json_file_autontification_app.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;

public class PasswordChangeDialog extends JDialog
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private JPasswordField pfPassword;
    private JPasswordField pfPasswordConfirm;
    private JLabel lbPassword;
    private JLabel lbPasswordConfirm;
    private JButton btnLogin;
    private JButton btnCancel;
    private boolean loginAttempt = false;
    private String resPassword = "";
    
    public PasswordChangeDialog(Frame parent) 
    {
        super(parent, "Ввод нового пароля", true);
        //
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;

        lbPassword = new JLabel("Пароль: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lbPassword, cs);

        pfPassword = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(pfPassword, cs);

        lbPasswordConfirm = new JLabel("Подтверждение: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lbPasswordConfirm, cs);

        pfPasswordConfirm = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(pfPasswordConfirm, cs);
        panel.setBorder(new LineBorder(Color.GRAY));

        btnLogin = new JButton("Принять");

        btnLogin.addActionListener(new ActionListener() 
        {
        	String numberRegex = ".*[0-9].*";	
        	String mathOpRegex = ".*[/ * + -].*";
        	
            public void actionPerformed(ActionEvent e) 
            {
            	String pas1 = getPassword();
            	String pas2 = getPasswordConfirm();

            	if(!pas1.equals(pas2))
            	{
            		JOptionPane.showMessageDialog(PasswordChangeDialog.this,
                            "Пароли не совпадают",
                            "Пароли не совпадают",
                            JOptionPane.ERROR_MESSAGE);
            	}
            	else if(pas1.length() == 0)
            	{
            		JOptionPane.showMessageDialog(PasswordChangeDialog.this,
                            "Пароль не может быть пустым",
                            "Пароль не может быть пустым",
                            JOptionPane.ERROR_MESSAGE);            		
            	}
            	else if(!Pattern.matches(numberRegex,pas1) | !Pattern.matches(mathOpRegex,pas1))
            	{
            		JOptionPane.showMessageDialog(PasswordChangeDialog.this,
            				"Пароль должен содержать числа и знаки / * + -",
            				"Пароль не cовпадает по требованиям",
                            
                            JOptionPane.ERROR_MESSAGE);              		
            	}
            	else
            	{
            		resPassword = pas1;
            	}
            	loginAttempt = true;
                dispose();
            }
        });
        btnCancel = new JButton("Отмена");
        btnCancel.addActionListener(new ActionListener() 
        {

            public void actionPerformed(ActionEvent e) 
            {
            	loginAttempt = false;
                dispose();
            }
        });
        JPanel bp = new JPanel();
        bp.add(btnLogin);
        bp.add(btnCancel);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    private String getPassword() 
    {
        return new String(pfPassword.getPassword());
    }    
    
    private String getPasswordConfirm() 
    {
        return new String(pfPasswordConfirm.getPassword());
    }
    
    public String getResPassword() 
    {
    	return resPassword;
    }

    public boolean isLoginAttempt() 
    {
        return loginAttempt;
    }
}
