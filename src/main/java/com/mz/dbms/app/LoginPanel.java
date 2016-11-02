package com.mz.dbms.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.mz.dbms.adapter.*;
import com.mz.dbms.constants.Const;
import com.sun.tools.corba.se.idl.ConstEntry;

/**
 * Created by Muchen on 10/8/16.
 */
public class LoginPanel {


    static void set(JPanel panel) {
        panel.removeAll();
        panel.setLayout(null);

        //  Username input:

        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10, 10, 80, 25);
        panel.add(userLabel);

        final JTextField userText = new JTextField(20);
        userText.setBounds(100, 10, 160, 25);
        panel.add(userText);

        //  Password input:

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 40, 80, 25);
        panel.add(passwordLabel);

        final JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 40, 160, 25);
        panel.add(passwordText);

        //  Login as:

        JLabel identLabel = new JLabel("Login as");
        identLabel.setBounds(10, 70, 80, 25);
        panel.add(identLabel);

        //Put the JComboBox in a JPanel to get a nicer look.
        JPanel comboBoxPane = new JPanel(); //use FlowLayout
        String comboBoxItems[] = { "Admin", "Patient", "Supporter" };
        final JComboBox cb = new JComboBox(comboBoxItems);
        cb.setEditable(false);

        comboBoxPane.add(cb);
        comboBoxPane.setBounds(100, 70, 160, 30);
        panel.add(comboBoxPane);



        JButton loginButton = new JButton("login");
        loginButton.setBounds(10, 110, 80, 25);
        panel.add(loginButton);

        JButton resetButton = new JButton("reset");
        resetButton.setBounds(95, 110, 80, 25);
        panel.add(resetButton);

        JButton registerButton = new JButton("register");
        registerButton.setBounds(180, 110, 80, 25);
        panel.add(registerButton);



        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userName = userText.getText();
                String password = passwordText.getText();
                int identity = cb.getSelectedIndex();

                System.out.println("Try to login: " + identity);


                if (userName.equals("")) {
                    JOptionPane.showMessageDialog((Component) e.getSource(),
                            "User name cannot be empty!");
                } else if (password.equals("")) {
                    JOptionPane.showMessageDialog((Component) e.getSource(),
                            "Password cannot be empty!");
                } else {

                    if (userName.equals("admin") && password.equals("admin") && identity == 0) {

                        AppWindow.setCurrUser(userName);
                        AppWindow.setCurrAccess(0);

                        AppWindow.setPanel(Const.ADMIN_PANEL);

                    } else {

                        boolean allowed = false;
                        try {
                            allowed = Logger.getAuthority(userName, password, identity);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                        System.out.println("Authority: " + allowed);

                        if (!allowed) {
                            JOptionPane.showMessageDialog((Component) e.getSource(),
                                    "Incorrect username or password!");
                        } else {
                            AppWindow.setCurrUser(userName);
                            AppWindow.setCurrAccess(identity);

                            if (identity == Const.PATIENT_USER)
                                Alert.AlertForPatient(AppWindow.getCurrUser());

                            if (identity == Const.SUPPORTER_USER)
                                Alert.AlertForSupporter(AppWindow.getCurrUser());

                            if (identity > -2 && identity < 3)
                                AppWindow.setPanel(identity);
                        }

                    }
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userText.setText(null);
                passwordText.setText(null);
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

//                System.out.println(cb.getSelectedItem().toString());

                JOptionPane.showMessageDialog((Component) e.getSource(),
                        "We do not support new register right now!");
            }
        });
    }
}
