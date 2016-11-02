package com.mz.dbms.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedList;

import com.mz.dbms.adapter.SupportSQL;
import com.mz.dbms.adapter.Test;
import com.mz.dbms.constants.*;

import com.mz.dbms.dao.*;

/**
 * Created by Muchen on 10/8/16.
 */
public class SupporterPanel {
    static JTable table;

    static void set(JPanel panel) {
//        patientPanel = panel;
        table = new JTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        panel.removeAll();
        panel.setLayout(null);

        /**
         * Patient Section;
         */

        JButton patientButton = new JButton("Patients");
        patientButton.setBounds(10, 10, 130, 25);
        panel.add(patientButton);

        JButton patientAddButton = new JButton("ADD/EDIT");
        patientAddButton.setBounds(10, 40, 100, 25);
        panel.add(patientAddButton);

        JButton patientDeleteButton = new JButton("DELETE");
        patientDeleteButton.setBounds(10, 70, 100, 25);
        panel.add(patientDeleteButton);

        /**
         * Alert Section;
         */

        JButton alertButton = new JButton("Alerts");
        alertButton.setBounds(160, 10, 130, 25);
        panel.add(alertButton);

        final JButton alertClearButton = new JButton("CLEAR");
        alertClearButton.setBounds(160, 40, 100, 25);
        panel.add(alertClearButton);

        /**
         * Observation Section;
         */

        JButton getObservationButton = new JButton("Observation");
        getObservationButton.setBounds(310, 10, 130, 25);
        panel.add(getObservationButton);

        JButton enterObservationButton = new JButton("ADD");
        enterObservationButton.setBounds(310, 40, 100, 25);
        panel.add(enterObservationButton);

        final JButton deleteObservationButton = new JButton("DELETE");
        deleteObservationButton.setBounds(310, 70, 100, 25);
        panel.add(deleteObservationButton);

        /**
         * Disease Section;
         */

        JButton diseaseButton = new JButton("Disease");
        diseaseButton.setBounds(460, 10, 130, 25);
        panel.add(diseaseButton);

        final JButton addDiseaseButton = new JButton("ADD");
        addDiseaseButton.setBounds(460, 40, 100, 25);
        panel.add(addDiseaseButton);

        /**
         * Recommendation Section;
         */

        JButton recommendationButton = new JButton("Recommendation");
        recommendationButton.setBounds(610, 10, 130, 25);
        panel.add(recommendationButton);

        final JButton addRecommendationButton = new JButton("ADD/EDIT");
        addRecommendationButton.setBounds(610, 40, 100, 25);
        panel.add(addRecommendationButton);

        JButton deleteRecommendationButton = new JButton("DELETE");
        deleteRecommendationButton.setBounds(610, 70, 100, 25);
        panel.add(deleteRecommendationButton);

        /**
         * Assign Section;
         */
        JButton assignButton = new JButton("Assignment");
        assignButton.setBounds(760, 10, 130, 25);
        panel.add(assignButton);

        JButton assignEditButton = new JButton("EDIT");
        assignEditButton.setBounds(760, 40, 100, 25);
        panel.add(assignEditButton);




        final JScrollPane scollPane = new JScrollPane();

        scollPane.setBounds(10, 130, 880, 245);  //Position
        scollPane.setBackground(Color.BLUE);
        scollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panel.add(scollPane);


        /**
         * Lower Half;
         */

        JButton profileButton = new JButton("Profile");
        profileButton.setBounds(10, 380, 130, 25);
        panel.add(profileButton);


        final JButton specialButton = new JButton("Special Query");
        specialButton.setBounds(160, 380, 280, 25);
        panel.add(specialButton);




        final JScrollPane textScollPane = new JScrollPane();
        textScollPane.setBounds(10, 410, 880, 245);
        textScollPane.setBackground(Color.BLUE);
        textScollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        textScollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panel.add(textScollPane);




        /**
         * Logout Section:
         */

        JLabel labelUser = new JLabel("Currently Login As: Supporter");
        labelUser.setBounds(10, 660, 200, 25);
        panel.add(labelUser);

        JButton logoutButton = new JButton("logout");
        logoutButton.setBounds(760, 660, 130, 25);
        panel.add(logoutButton);



        /*******************************************************
         * Button Action Section;                              *
         *******************************************************/



        /**
         * Patient Button;
         */
        //  view: table view;
        patientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scollPane.setViewportView(table);

                try {
                    MEMBER_Table member_table = SupportSQL.searchMember(AppWindow.getCurrUser());
                    table.setModel(member_table);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        //  add: text view;
        patientAddButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPanel patientAddJP = patientAddPanel();

                textScollPane.setViewportView(patientAddJP);
            }
        });

        //  delete: text view;
        patientDeleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPanel patientDeleteJP = patientDeletePanel();

                textScollPane.setViewportView(patientDeleteJP);
            }
        });


        /**
         * Alert Button;
         */
        //  view: table view
        alertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scollPane.setViewportView(table);

                ALERT_Table alert_table = SupportSQL.searchAlert(AppWindow.getCurrUser());
                table.setModel(alert_table);
            }
        });

        //  clear: text view
        alertClearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPanel alertClearJP = alertClearPanel();

                textScollPane.setViewportView(alertClearJP);
            }
        });


        /**
         * Observation Button;
         */
        //  view: table view
        getObservationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scollPane.setViewportView(table);

                OBSERVE_Table observe_table = SupportSQL.searchObserve(AppWindow.getCurrUser());
                table.setModel(observe_table);

            }
        });

        //  enter: text view
        enterObservationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPanel addObservationJP = enterObservationPanel();

                textScollPane.setViewportView(addObservationJP);
            }
        });

        //  delete: text view
        deleteObservationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPanel deleteObservationJP = deleteObservationPanel();

                textScollPane.setViewportView(deleteObservationJP);
            }
        });


        /**
         * Disease Button;
         */
        //  view: table view;
        diseaseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scollPane.setViewportView(table);

                DISEASE_Table disease_table = SupportSQL.searchDisease();
                table.setModel(disease_table);
            }
        });

        //  add:
        addDiseaseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPanel addDiseaseJP = addDiseasePanel();

                textScollPane.setViewportView(addDiseaseJP);
            }
        });


        /**
         * Recommendation Button;
         */
        //  view:   table view;
        recommendationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scollPane.setViewportView(table);

                MRECOMMEND_Table mrecommend_table = SupportSQL.seaMrecommend(AppWindow.getCurrUser());
                table.setModel(mrecommend_table);
            }
        });

        //  add:    text view;
        addRecommendationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPanel addRecommendationJP = addRecommendationPanel();

                textScollPane.setViewportView(addRecommendationJP);
            }
        });

        //  delete: text view;
        deleteRecommendationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPanel deleteRecommendationJP = deleteRecommendationPanel();

                textScollPane.setViewportView(deleteRecommendationJP);
            }
        });


        /**
         * Assign Section;
         */
        //  view: table view;
        assignButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scollPane.setViewportView(table);

                try {
                    ASSIGN_Table assign_table = SupportSQL.searchAssign(AppWindow.getCurrUser());
                    table.setModel(assign_table);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        //  edit: text view;
        assignEditButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPanel editAssignJP = editAssignPanel();

                textScollPane.setViewportView(editAssignJP);
            }
        });


        /**
         * Profile Button;
         */

        profileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPanel profileJP = generateProfilePanel();

                textScollPane.setViewportView(profileJP);
            }
        });


        /**
         * Special Button;
         */
        specialButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPanel specialJP = generateSpecialPanel();

                textScollPane.setViewportView(specialJP);
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


    static JPanel patientAddPanel() {

        JPanel profileJP = new JPanel();
        profileJP.setLayout(null);
        profileJP.setPreferredSize(new Dimension(800, 400));

        JLabel labelTitle = new JLabel("Add/Edit patient:");
        labelTitle.setBounds(300, 10, 200, 25);
        profileJP.add(labelTitle);

        JLabel labelMID = new JLabel("MID:");
        labelMID.setBounds(10, 40, 200, 25);
        profileJP.add(labelMID);

        final JTextField textMID = new JTextField("MID must be unique");
        profileJP.add(textMID);
        textMID.setBounds(240, 40, 400, 25);

        JLabel label1 = new JLabel("Name:");
        label1.setBounds(10, 70, 200, 25);
        profileJP.add(label1);

        final JTextField text1 = new JTextField(20);
        profileJP.add(text1);
        text1.setBounds(240, 70, 400, 25);

        JLabel label2 = new JLabel("Date of Birth:");
        label2.setBounds(10, 100, 200, 25);
        profileJP.add(label2);

        final JTextField text2 = new JTextField(20);
        profileJP.add(text2);
        text2.setBounds(240, 100, 400, 25);

        JLabel label3 = new JLabel("Address:");
        label3.setBounds(10, 130, 200, 25);
        profileJP.add(label3);

        final JTextField text3 = new JTextField(20);
        profileJP.add(text3);
        text3.setBounds(240, 130, 400, 25);

        JLabel label4 = new JLabel("Gender: ");
        label4.setBounds(10, 160, 200, 25);
        profileJP.add(label4);

        final JTextField text4 = new JTextField(20);
        profileJP.add(text4);
        text4.setBounds(240, 160, 400, 25);

        JLabel label5 = new JLabel("Contact:");
        label5.setBounds(10, 190, 200, 25);
        profileJP.add(label5);

        final JTextField text5 = new JTextField(20);
        profileJP.add(text5);
        text5.setBounds(240, 190, 400, 25);

        JLabel label6= new JLabel("Password:");
        label6.setBounds(10, 220, 200, 25);
        profileJP.add(label6);

        final JTextField text6 = new JTextField(20);
        profileJP.add(text6);
        text6.setBounds(240, 220, 400, 25);

        JLabel labelSick = new JLabel("Sick date:");
        labelSick.setBounds(10, 250, 200, 25);
        profileJP.add(labelSick);

        final JTextField textSick = new JTextField(20);
        textSick.setBounds(240, 250, 400, 25);
        profileJP.add(textSick);

        JLabel label7 = new JLabel("Diagnoses:");
        label7.setBounds(10, 280, 200, 25);
        profileJP.add(label7);

        final JTextField text7 = new JTextField(20);
        text7.setBounds(240, 280, 400, 25);
        profileJP.add(text7);

        JButton updateButton = new JButton("UPDATE");
        updateButton.setBounds(330, 310, 80, 25);
        profileJP.add(updateButton);


        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!SupportSQL.addNewMember(textMID.getText(), text1.getText(),
                        text2.getText(), text3.getText(),
                        text4.getText(), text5.getText(),
                        text6.getText(), textSick.getText(), text7.getText()))
                    JOptionPane.showMessageDialog((Component) e.getSource(), "Cannot add this member!");
            }
        });

        return profileJP;

    }

    static JPanel patientDeletePanel() {

        JPanel profileJP = new JPanel();
        profileJP.setLayout(null);
        profileJP.setPreferredSize(new Dimension(800, 100));

        JLabel labelTitle = new JLabel("Delete patient:");
        labelTitle.setBounds(300, 10, 200, 25);
        profileJP.add(labelTitle);

        JLabel label1 = new JLabel("Delete MID:");
        label1.setBounds(10, 40, 200, 25);
        profileJP.add(label1);

        final JTextField text1 = new JTextField(20);
        profileJP.add(text1);
        text1.setBounds(240, 40, 400, 25);

        JButton updateButton = new JButton("DELETE");
        updateButton.setBounds(300, 100, 80, 25);
        profileJP.add(updateButton);

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!SupportSQL.delMember(text1.getText()))
                    JOptionPane.showMessageDialog((Component) e.getSource(), "Cannot delete this member!");
            }
        });

        return profileJP;

    }

    static JPanel alertClearPanel() {

        JPanel profileJP = new JPanel();
        profileJP.setLayout(null);
        profileJP.setPreferredSize(new Dimension(800, 100));

        JLabel labelTitle = new JLabel("Clear alert:");
        labelTitle.setBounds(300, 10, 200, 25);
        profileJP.add(labelTitle);

        //
        JLabel label1 = new JLabel("Which MID:");
        label1.setBounds(10, 40, 200, 25);
        profileJP.add(label1);

        final JTextField text1 = new JTextField(20);
        profileJP.add(text1);
        text1.setBounds(240, 40, 400, 25);

        //
        JLabel label2 = new JLabel("Which INDICATOR:");
        label2.setBounds(10, 70, 200, 25);
        profileJP.add(label2);

//        final JTextField text2 = new JTextField(20);
//        profileJP.add(text2);
//        text2.setBounds(240, 70, 400, 25);

        JPanel comboBoxPane = new JPanel(); //use FlowLayout
        ArrayList<String> indicators = new ArrayList<String>(AppWindow.indicators);
        String[] comboBoxItems = new String[indicators.size()];
        for (int i = 0; i < indicators.size(); i++)
            comboBoxItems[i] = indicators.get(i);
        final JComboBox cb = new JComboBox(comboBoxItems);
        cb.setEditable(false);

        comboBoxPane.add(cb);
        comboBoxPane.setBounds(240, 70, 400, 30);
        profileJP.add(comboBoxPane);


        //
        JLabel label3 = new JLabel("When:");
        label3.setBounds(10, 100, 200, 25);
        profileJP.add(label3);

        final JTextField text3 = new JTextField("YY-MM-DD");
        profileJP.add(text3);
        text3.setBounds(240, 100, 400, 25);

        JButton updateButton = new JButton("CLEAR");
        updateButton.setBounds(300, 160, 80, 25);
        profileJP.add(updateButton);


        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!SupportSQL.delAlert(text1.getText(),
                        AppWindow.indicators.get(cb.getSelectedIndex()),
                        text3.getText()))
                {
                    JOptionPane.showMessageDialog((Component) e.getSource(),
                            "Cannot delete this alert !");
                }
            }
        });

        return profileJP;

    }

    static JPanel enterObservationPanel() {

        JPanel enterJP = new JPanel();
        enterJP.setLayout(null);
        enterJP.setPreferredSize(new Dimension(800, 300));

        JLabel labelTitle = new JLabel("Enter Observation:");
        labelTitle.setBounds(300, 10, 200, 25);
        enterJP.add(labelTitle);

        //
        final JLabel labelMid1 = new JLabel("Mid:");
        labelMid1.setBounds(10, 40, 200, 25);
        enterJP.add(labelMid1);

        final JTextField labelMid2 = new JTextField(20);
        labelMid2.setBounds(240, 40, 400, 25);
        enterJP.add(labelMid2);

        //
        JLabel labelInd1 = new JLabel("Indicator:");
        labelInd1.setBounds(10, 70, 200, 25);
        enterJP.add(labelInd1);

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

        //
        JLabel label1 = new JLabel("Value:");
        label1.setBounds(10, 110, 200, 25);
        enterJP.add(label1);

        final JTextField text1 = new JTextField(20);
        text1.setBounds(240, 110, 400, 25);
        enterJP.add(text1);

        //
        JLabel label2 = new JLabel("Observation Date:");
        label2.setBounds(10, 140, 200, 25);
        enterJP.add(label2);

        final JTextField text2 = new JTextField("YY-MM-DD");
        text2.setBounds(240, 140, 400, 25);
        enterJP.add(text2);

        //
        JLabel label3 = new JLabel("Report Date:");
        label3.setBounds(10, 170, 200, 25);
        enterJP.add(label3);

        final JTextField text3 = new JTextField("YY-MM-DD");
        text3.setBounds(240, 170, 400, 25);
        enterJP.add(text3);


        JButton insertButton = new JButton("ENTER");
        insertButton.setBounds(300, 230, 80, 25);
        enterJP.add(insertButton);

        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!SupportSQL.addObserve(labelMid2.getText(),
                        AppWindow.indicators.get(cb.getSelectedIndex()),
                        text1.getText(),
                        text2.getText(),
                        text3.getText()))
                {
                    JOptionPane.showMessageDialog((Component) e.getSource(),
                            "Insert Failed !");
                }
            }
        });

        return enterJP;

    }

    static JPanel deleteObservationPanel() {

        JPanel profileJP = new JPanel();
        profileJP.setLayout(null);
        profileJP.setPreferredSize(new Dimension(800, 100));

        JLabel labelTitle = new JLabel("Delete observation:");
        labelTitle.setBounds(300, 10, 200, 25);
        profileJP.add(labelTitle);

        JLabel label1 = new JLabel("Delete OID:");
        label1.setBounds(10, 40, 200, 25);
        profileJP.add(label1);

        final JTextField text1 = new JTextField(20);
        profileJP.add(text1);
        text1.setBounds(240, 40, 400, 25);

        JButton updateButton = new JButton("DELETE");
        updateButton.setBounds(300, 100, 80, 25);
        profileJP.add(updateButton);

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!SupportSQL.delObserve(text1.getText())) {
                    JOptionPane.showMessageDialog((Component) e.getSource(),
                            "Delete Failed !");
                }
            }
        });

        return profileJP;

    }

    static JPanel addDiseasePanel() {

        JPanel enterJP = new JPanel();
        enterJP.setLayout(null);
        enterJP.setPreferredSize(new Dimension(800, 300));

        JLabel labelTitle = new JLabel("Add a new Disease:");
        labelTitle.setBounds(300, 10, 200, 25);
        enterJP.add(labelTitle);

        //
        JLabel labelDname1 = new JLabel("Disease Name:");
        labelDname1.setBounds(10, 40, 200, 25);
        enterJP.add(labelDname1);

        final JTextField labelDname2 = new JTextField(20);
        labelDname2.setBounds(240, 40, 400, 25);
        enterJP.add(labelDname2);

        //
        JLabel labelDes1 = new JLabel("Description:");
        labelDes1.setBounds(10, 70, 200, 25);
        enterJP.add(labelDes1);

        final JTextField labelDes2 = new JTextField(20);
        labelDes2.setBounds(240, 70, 400, 25);
        enterJP.add(labelDes2);

        JButton insertButton = new JButton("ADD");
        insertButton.setBounds(300, 130, 80, 25);
        enterJP.add(insertButton);

        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                if (!SupportSQL.addDisease(labelDname2.getText(),
//                        labelDes2.getText(),
//                        ))
//                {
//                    JOptionPane.showMessageDialog((Component) e.getSource(),
//                            "Insert Failed !");
//                }
            }
        });

        return enterJP;

    }

    static JPanel addRecommendationPanel() {

        JPanel profileJP = new JPanel();
        profileJP.setLayout(null);
        profileJP.setPreferredSize(new Dimension(800, 400));

        JLabel labelTitle = new JLabel("Add/Edit Recommendation:");
        labelTitle.setBounds(300, 10, 200, 25);
        profileJP.add(labelTitle);

        //
        JLabel labelMID = new JLabel("MID:");
        labelMID.setBounds(10, 40, 200, 25);
        profileJP.add(labelMID);

        final JTextField textMID = new JTextField(20);
        profileJP.add(textMID);
        textMID.setBounds(240, 40, 400, 25);

        //
        JLabel label1 = new JLabel("Indicator:");
        label1.setBounds(10, 70, 200, 25);
        profileJP.add(label1);

//        final JTextField text1 = new JTextField(20);
//        profileJP.add(text1);
//        text1.setBounds(240, 70, 400, 25);

        JPanel comboBoxPane = new JPanel(); //use FlowLayout
        ArrayList<String> indicators = new ArrayList<String>(AppWindow.indicators);
        String[] comboBoxItems = new String[indicators.size()];
        for (int i = 0; i < indicators.size(); i++)
            comboBoxItems[i] = indicators.get(i);
        final JComboBox cb = new JComboBox(comboBoxItems);
        cb.setEditable(false);

        comboBoxPane.add(cb);
        comboBoxPane.setBounds(240, 70, 400, 30);
        profileJP.add(comboBoxPane);

        //
        JLabel label2 = new JLabel("Information:");
        label2.setBounds(10, 100, 200, 25);
        profileJP.add(label2);

        final JTextField text2 = new JTextField(20);
        profileJP.add(text2);
        text2.setBounds(240, 100, 400, 25);

        //
        JLabel label3 = new JLabel("Frequency (once ? days):");
        label3.setBounds(10, 130, 200, 25);
        profileJP.add(label3);

        final JTextField text3 = new JTextField(20);
        profileJP.add(text3);
        text3.setBounds(240, 130, 400, 25);

        JButton updateButton = new JButton("UPDATE");
        updateButton.setBounds(330, 190, 80, 25);
        profileJP.add(updateButton);


        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!SupportSQL.MReUpdate(textMID.getText(),
                        AppWindow.indicators.get(cb.getSelectedIndex()),
                        text2.getText(),
                        text3.getText()
                        ))
                {
                    JOptionPane.showMessageDialog((Component) e.getSource(),
                            "Update Failed !");
                }
            }
        });

        return profileJP;

    }

    static JPanel deleteRecommendationPanel() {

        JPanel profileJP = new JPanel();
        profileJP.setLayout(null);
        profileJP.setPreferredSize(new Dimension(800, 100));

        JLabel labelTitle = new JLabel("Delete Recommendation:");
        labelTitle.setBounds(300, 10, 200, 25);
        profileJP.add(labelTitle);

        //
        JLabel label1 = new JLabel("MID:");
        label1.setBounds(10, 40, 200, 25);
        profileJP.add(label1);

        final JTextField text1 = new JTextField(20);
        profileJP.add(text1);
        text1.setBounds(240, 40, 400, 25);

        //
        JLabel label2 = new JLabel("Indicator:");
        label2.setBounds(10, 70, 200, 25);
        profileJP.add(label2);

//        final JTextField text2 = new JTextField(20);
//        profileJP.add(text2);
//        text2.setBounds(240, 70, 400, 25);

        JPanel comboBoxPane = new JPanel(); //use FlowLayout
        ArrayList<String> indicators = new ArrayList<String>(AppWindow.indicators);
        String[] comboBoxItems = new String[indicators.size()];
        for (int i = 0; i < indicators.size(); i++)
            comboBoxItems[i] = indicators.get(i);
        final JComboBox cb = new JComboBox(comboBoxItems);
        cb.setEditable(false);

        comboBoxPane.add(cb);
        comboBoxPane.setBounds(240, 70, 400, 30);
        profileJP.add(comboBoxPane);

        JButton updateButton = new JButton("DELETE");
        updateButton.setBounds(300, 130, 80, 25);
        profileJP.add(updateButton);

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!SupportSQL.MreDelete(text1.getText(),
                        AppWindow.indicators.get(cb.getSelectedIndex())))
                {
                    JOptionPane.showMessageDialog((Component) e.getSource(),
                            "Delete Failed !");
                }
            }
        });

        return profileJP;

    }

    static JPanel editAssignPanel() {
//        create table ASSIGN(PID varchar(20), SID varchar(20), ISPRIMARY varchar(20), FROMDATE varchar(20), TODATE varchar(20), PRIMARY KEY(PID, SID, FROMDATE, TODATE));

        JPanel enterJP = new JPanel();
        enterJP.setLayout(null);
        enterJP.setPreferredSize(new Dimension(800, 300));

        JLabel labelTitle = new JLabel("Reassign Patient-Supporters:");
        labelTitle.setBounds(300, 10, 200, 25);
        enterJP.add(labelTitle);

        //
        JLabel labelDname1 = new JLabel("MID:");
        labelDname1.setBounds(10, 40, 200, 25);
        enterJP.add(labelDname1);

        final JTextField labelDname2 = new JTextField(20);
        labelDname2.setBounds(240, 40, 400, 25);
        enterJP.add(labelDname2);

        //
        JLabel labelDes1 = new JLabel("Primary Supporter:");
        labelDes1.setBounds(10, 70, 200, 25);
        enterJP.add(labelDes1);

        final JTextField labelDes2 = new JTextField(20);
        labelDes2.setBounds(240, 70, 400, 25);
        enterJP.add(labelDes2);

        //
        JLabel labelDate1 = new JLabel("-From date:");
        labelDate1.setBounds(10, 100, 200, 25);
        enterJP.add(labelDate1);

        final JTextField labelDate2 = new JTextField(20);
        labelDate2.setBounds(240, 100, 400, 25);
        enterJP.add(labelDate2);

        //
        JLabel labelDate3 = new JLabel("-To date:");
        labelDate3.setBounds(10, 130, 200, 25);
        enterJP.add(labelDate3);

        final JTextField labelDate4 = new JTextField(20);
        labelDate4.setBounds(240, 130, 400, 25);
        enterJP.add(labelDate4);

        //
        JLabel labelDes3 = new JLabel("Secondary Supporter:");
        labelDes3.setBounds(10, 160, 200, 25);
        enterJP.add(labelDes3);

        final JTextField labelDes4 = new JTextField(20);
        labelDes4.setBounds(240, 160, 400, 25);
        enterJP.add(labelDes4);

        //
        JLabel labelDate5 = new JLabel("-From date:");
        labelDate5.setBounds(10, 190, 200, 25);
        enterJP.add(labelDate5);

        final JTextField labelDate6 = new JTextField(20);
        labelDate6.setBounds(240, 190, 400, 25);
        enterJP.add(labelDate6);

        //
        JLabel labelDate7 = new JLabel("-To date:");
        labelDate7.setBounds(10, 220, 200, 25);
        enterJP.add(labelDate7);

        final JTextField labelDate8 = new JTextField(20);
        labelDate8.setBounds(240, 220, 400, 25);
        enterJP.add(labelDate8);


        JButton insertButton = new JButton("UPDATE");
        insertButton.setBounds(300, 280, 80, 25);
        enterJP.add(insertButton);


        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!SupportSQL.AssignEdit(labelDname2.getText(),
                        labelDes2.getText(),
                        labelDate2.getText(),
                        labelDate4.getText(),
                        labelDes4.getText(),
                        labelDate6.getText(),
                        labelDate8.getText()
                        ))
                {
                    JOptionPane.showMessageDialog((Component) e.getSource(),
                            "Edit Failed !");
                }
            }
        });


        return enterJP;

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

    static JPanel generateSpecialPanel() {

        JPanel profileJP = new JPanel();
        profileJP.setLayout(null);
        profileJP.setPreferredSize(new Dimension(800, 400));

        JLabel labelTitle = new JLabel("Special Query:");
        labelTitle.setBounds(300, 10, 200, 25);
        profileJP.add(labelTitle);

        //
        JLabel label1 = new JLabel("Find patients who belong to more than");
        label1.setBounds(10, 40, 250, 25);
        profileJP.add(label1);

        final JTextField text1 = new JTextField();
        profileJP.add(text1);
        text1.setBounds(270, 40, 20, 25);

        JLabel label11 = new JLabel("Sick Patient class(s)");
        label11.setBounds(300, 40, 180, 25);
        profileJP.add(label11);

        JButton label1Button = new JButton("Execute");
        label1Button.setBounds(600, 40, 80, 25);
        profileJP.add(label1Button);


        //
        JLabel label2 = new JLabel("Find all Sick patients whose Health Supporters are also Sick patients.");
        label2.setBounds(10, 70, 480, 25);
        profileJP.add(label2);

        JButton label2Button = new JButton("Execute");
        label2Button.setBounds(600, 70, 80, 25);
        profileJP.add(label2Button);


        //
        JLabel label3 = new JLabel("Find patients who have");
        label3.setBounds(10, 100, 250, 25);
        profileJP.add(label3);

        final JTextField text3 = new JTextField();
        profileJP.add(text3);
        text3.setBounds(270, 100, 20, 25);

        JLabel label33 = new JLabel("Health Supporter(s)");
        label33.setBounds(300, 100, 180, 25);
        profileJP.add(label33);

        JButton label3Button = new JButton("Execute");
        label3Button.setBounds(600, 100, 80, 25);
        profileJP.add(label3Button);


        //
        JLabel label4 = new JLabel("For each patient and each month of");
        label4.setBounds(10, 130, 240, 25);
        profileJP.add(label4);

        final JTextField text4 = new JTextField();
        profileJP.add(text4);
        text4.setBounds(250, 130, 40, 25);

        JLabel label44 = new JLabel("list all the alerts that were generated.");
        label44.setBounds(300, 130, 240, 25);
        profileJP.add(label44);

        JButton label4Button = new JButton("Execute");
        label4Button.setBounds(600, 130, 80, 25);
        profileJP.add(label4Button);


        //
        JLabel label5 = new JLabel("For each month of");
        label5.setBounds(10, 160, 240, 25);
        profileJP.add(label5);

        final JTextField text5 = new JTextField();
        profileJP.add(text5);
        text5.setBounds(250, 160, 40, 25);

        JLabel label55 = new JLabel("list the patients with the most alerts.");
        label55.setBounds(300, 160, 240, 25);
        profileJP.add(label55);

        JButton label5Button = new JButton("Execute");
        label5Button.setBounds(600, 160, 80, 25);
        profileJP.add(label5Button);

//        updateButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                if (!Test.addeditMember(textMID.getText(), text1.getText(), text2.getText(), text3.getText(), text4.getText(), text5.getText(), text6.getText(), textSick.getText(), text7.getText()))
//                    JOptionPane.showMessageDialog((Component) e.getSource(), "Cannot add this member!");
//            }
//        });

        return profileJP;

    }

}
