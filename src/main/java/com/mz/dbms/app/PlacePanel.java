//package com.mz.dbms.app;
//
///**
// * Created by Muchen on 10/7/16.
// */
//
//import java.awt.*;
//import java.awt.event.*;
//import javax.swing.*;
//
//public class PlacePanel {
//    static void loginPlaceComponents(JPanel panel) {
////        panel.setSize(300, 150);
//        panel.setLayout(null);
//
//        JLabel userLabel = new JLabel("User");
//        userLabel.setBounds(10, 10, 80, 25);
//        panel.add(userLabel);
//
//        final JTextField userText = new JTextField(20);
//        userText.setBounds(100, 10, 160, 25);
//        panel.add(userText);
//
//        JLabel passwordLabel = new JLabel("Password");
//        passwordLabel.setBounds(10, 40, 80, 25);
//        panel.add(passwordLabel);
//
//        final JPasswordField passwordText = new JPasswordField(20);
//        passwordText.setBounds(100, 40, 160, 25);
//        panel.add(passwordText);
//
//        JButton loginButton = new JButton("login");
//        loginButton.setBounds(10, 80, 80, 25);
//        panel.add(loginButton);
//
//        JButton resetButton = new JButton("reset");
//        resetButton.setBounds(95, 80, 80, 25);
//        panel.add(resetButton);
//
//        JButton registerButton = new JButton("register");
//        registerButton.setBounds(180, 80, 80, 25);
//        panel.add(registerButton);
//
//        loginButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                String userName = userText.getText();
//                String password = passwordText.getText();
//
//                if (userName.equals("")) {
//                    JOptionPane.showMessageDialog((Component) e.getSource(),
//                            "User name cannot be empty!");
//                } else if (password.equals("")) {
//                    JOptionPane.showMessageDialog((Component) e.getSource(),
//                            "Password cannot be empty!");
//                } else {
////                     JOptionPane.showMessageDialog((Component) e.getSource(),
////                             "In!");
//                    AppWindow.setPanel(1);
//                }
//            }
//        });
//
//        resetButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                userText.setText(null);
//                passwordText.setText(null);
//            }
//        });
//
//        registerButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog((Component) e.getSource(),
//                        "We do not support new register right now!");
//            }
//        });
//    }
//
//
//    static void patientPlaceComponents(JPanel panel) {
//        panel.setLayout(null);
//
//        JLabel userLabel = new JLabel("Patient Panel");
//        userLabel.setBounds(10, 10, 80, 25);
//        panel.add(userLabel);
//    }
//
//
//    static void supporterPlaceComponents(JPanel panel) {
//        panel.setLayout(null);
//
//        JLabel userLabel = new JLabel("Supporter Panel");
//        userLabel.setBounds(10, 10, 80, 25);
//        panel.add(userLabel);
//    }
//
//}