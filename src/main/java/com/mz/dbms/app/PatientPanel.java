package com.mz.dbms.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import com.mz.dbms.constants.*;

import com.mz.dbms.dao.*;

/**
 * Created by Muchen on 10/8/16.
 */
public class PatientPanel {
//    static JPanel patientPanel;
    static JTable table;

    static void set(JPanel panel) {
//        patientPanel = panel;
        table = new JTable();

        panel.removeAll();
        panel.setLayout(null);

        JButton profileButton = new JButton("Profile");
        profileButton.setBounds(10, 10, 130, 25);
        panel.add(profileButton);

        JButton diagnosesButton = new JButton("Diagnoses");
        diagnosesButton.setBounds(160, 10, 130, 25);
        panel.add(diagnosesButton);

        JButton indicatorButton = new JButton("Health Indicator");
        indicatorButton.setBounds(310, 10, 130, 25);
        panel.add(indicatorButton);

        JButton alertButton = new JButton("Alerts");
        alertButton.setBounds(460, 10, 130, 25);
        panel.add(alertButton);

        JButton supportersButton = new JButton("Health Supporter");
        supportersButton.setBounds(610, 10, 130, 25);
        panel.add(supportersButton);

        JButton getObservationButton = new JButton("View Existing Observation");
        getObservationButton.setBounds(10, 40, 280, 25);
        panel.add(getObservationButton);

        JButton enterObservationButton = new JButton("Enter New Observation");
        enterObservationButton.setBounds(310, 40, 280, 25);
        panel.add(enterObservationButton);


        final JScrollPane scollPane = new JScrollPane();

        scollPane.setBounds(10, 70, 880, 480);  //Position
        scollPane.setBackground(Color.BLUE);
        scollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        panel.add(scollPane);



        JLabel labelUser = new JLabel("Currently Login As: Patient");
        labelUser.setBounds(10, 560, 200, 25);
        panel.add(labelUser);

        JButton logoutButton = new JButton("logout");
        logoutButton.setBounds(760, 560, 130, 25);
        panel.add(logoutButton);

        /**
         * Button Section;
         */


        /**
         * Profile Button
         * text view;
         */
        profileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPanel profileJP = generateProfilePanel();

                scollPane.setViewportView(profileJP);
            }
        });

        /**
         * Diagnoses Button
         * table view;
         */
        diagnosesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                scollPane.setViewportView(table);


//                table.setModel(mockTable);
            }
        });

        /**
         * Health Indicator Button
         * table view;
         */
        indicatorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                scollPane.setViewportView(table);

//                table.setModel(mockTable);
            }
        });


        /**
         * Alert Button
         * table view;
         */
        alertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scollPane.setViewportView(table);

//                table.setModel(mockTable);
            }
        });

        /**
         * Health Supporters Button
         * table view;
         */
        supportersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scollPane.setViewportView(table);

//                table.setModel(mockTable);
            }
        });

        /**
         * View Observation Button
         */
        getObservationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scollPane.setViewportView(table);

//                table.setModel(mockTable);
            }
        });

        /**
         * Enter Observation Button
         * text view;
         */
        enterObservationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPanel enterObservationJP = enterObservationPanel();

                scollPane.setViewportView(enterObservationJP);
            }
        });

        /**
         * Logout Button;
         */
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                AppWindow.setCurrUser("");
                AppWindow.setCurrAccess(-1);

                AppWindow.setPanel(Const.LOGIN_PANEL);
            }
        });
    }

    static JPanel generateProfilePanel() {
        JPanel profileJP = new JPanel();
        profileJP.setLayout(null);
        profileJP.setPreferredSize(new Dimension(800, 400));

        JLabel labelMid1 = new JLabel("Member ID:");
        labelMid1.setBounds(10, 10, 200, 25);
        profileJP.add(labelMid1);

        JLabel labelMid2 = new JLabel("P1");
        labelMid2.setBounds(240, 10, 400, 25);
        profileJP.add(labelMid2);

        JLabel label1 = new JLabel("Name:");
        label1.setBounds(10, 40, 200, 25);
        profileJP.add(label1);

        final JTextField text1 = new JTextField("Rick");
        profileJP.add(text1);
        text1.setBounds(240, 40, 400, 25);

        JLabel label2 = new JLabel("Date of Birth:");
        label2.setBounds(10, 70, 200, 25);
        profileJP.add(label2);

        final JTextField text2 = new JTextField(20);
        profileJP.add(text2);
        text2.setBounds(240, 70, 400, 25);

        JLabel label3 = new JLabel("Address:");
        label3.setBounds(10, 100, 200, 25);
        profileJP.add(label3);

        final JTextField text3 = new JTextField(20);
        profileJP.add(text3);
        text3.setBounds(240, 100, 400, 25);

        JLabel label4 = new JLabel("Gender: ");
        label4.setBounds(10, 130, 200, 25);
        profileJP.add(label4);

        final JTextField text4 = new JTextField(20);
        profileJP.add(text4);
        text4.setBounds(240, 130, 400, 25);

        JLabel label5 = new JLabel("Contact:");
        label5.setBounds(10, 160, 200, 25);
        profileJP.add(label5);

        final JTextField text5 = new JTextField(20);
        profileJP.add(text5);
        text5.setBounds(240, 160, 400, 25);

        JLabel label6= new JLabel("Password:");
        label6.setBounds(10, 190, 200, 25);
        profileJP.add(label6);

        final JTextField text6 = new JTextField("Rick`s password");
        profileJP.add(text6);
        text6.setBounds(240, 190, 400, 25);

        JLabel labelReg1 = new JLabel("Registration date:");
        labelReg1.setBounds(10, 220, 200, 25);
        profileJP.add(labelReg1);

        JLabel labelReg2 = new JLabel("Oct 21 2016");
        labelReg2.setBounds(240, 220, 400, 25);
        profileJP.add(labelReg2);

        JLabel labelSick1 = new JLabel("Sick date:");
        labelSick1.setBounds(10, 250, 200, 25);
        profileJP.add(labelSick1);

        JLabel labelSick2 = new JLabel("Oct 22 2016");
        labelSick2.setBounds(240, 250, 400, 25);
        profileJP.add(labelSick2);

        JLabel label7 = new JLabel("Diagnoses:");
        label7.setBounds(10, 280, 200, 25);
        profileJP.add(label7);

        final JTextField text7 = new JTextField("Too Strong");
        text7.setBounds(240, 280, 400, 25);
        profileJP.add(text7);

        JButton updateButton = new JButton("UPDATE");
        updateButton.setBounds(300, 340, 80, 25);
        profileJP.add(updateButton);

        return profileJP;
    }

    static JPanel enterObservationPanel() {
        JPanel enterJP = new JPanel();
        enterJP.setLayout(null);
        enterJP.setPreferredSize(new Dimension(800, 300));


        JLabel labelOid1 = new JLabel("Oid:");
        labelOid1.setBounds(10, 10, 200, 25);
        enterJP.add(labelOid1);

        JLabel labelOid2 = new JLabel("111");
        labelOid2.setBounds(240, 10, 400, 25);
        enterJP.add(labelOid2);


        JLabel labelMid1 = new JLabel("Mid:");
        labelMid1.setBounds(10, 40, 200, 25);
        enterJP.add(labelMid1);

        JLabel labelMid2 = new JLabel("P3");
        labelMid2.setBounds(240, 40, 400, 25);
        enterJP.add(labelMid2);


        JLabel labelInd1 = new JLabel("Indicator:");
        labelInd1.setBounds(10, 70, 200, 25);
        enterJP.add(labelInd1);

//        JPanel comboBoxPane = new JPanel(); //use FlowLayout
//        String comboBoxItems[] = { "Temperature", "Blood Presure", "Mood" };
//        final JComboBox cb = new JComboBox(comboBoxItems);
//        cb.setEditable(false);
//
//        comboBoxPane.add(cb);
//        comboBoxPane.setBounds(240, 70, 400, 30);
//        enterJP.add(comboBoxPane);

        JPanel comboBoxPane = new JPanel(); //use FlowLayout
        ArrayList<String> indicators = new ArrayList<String>(AppWindow.indicators);
        String[] comboBoxItems = new String[indicators.size()];
        for (int i = 0; i < indicators.size(); i++)
            comboBoxItems[i] = indicators.get(i);
        final JComboBox cb = new JComboBox(comboBoxItems);
        cb.setEditable(false);

        comboBoxPane.add(cb);
        comboBoxPane.setBounds(240, 70, 400, 30);
        enterJP.add(comboBoxPane);


        JLabel label1 = new JLabel("Value:");
        label1.setBounds(10, 110, 200, 25);
        enterJP.add(label1);

        final JTextField text1 = new JTextField("36.5C");
        text1.setBounds(240, 110, 400, 25);
        enterJP.add(text1);


        JLabel label2 = new JLabel("Observation Date:");
        label2.setBounds(10, 140, 200, 25);
        enterJP.add(label2);

        final JTextField text2 = new JTextField("MM/DD/YY");
        text2.setBounds(240, 140, 400, 25);
        enterJP.add(text2);


        JLabel label3 = new JLabel("Report Date:");
        label3.setBounds(10, 170, 200, 25);
        enterJP.add(label3);

        final JTextField text3 = new JTextField("MM/DD/YY");
        text3.setBounds(240, 170, 400, 25);
        enterJP.add(text3);


        JButton insertButton = new JButton("INSERT");
        insertButton.setBounds(300, 230, 80, 25);
        enterJP.add(insertButton);

        return enterJP;

    }
}
