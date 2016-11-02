package com.mz.dbms.app;

import javax.swing.*;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.LinkedList;

import com.mz.dbms.adapter.Test;
import com.mz.dbms.constants.*;

import com.mz.dbms.dao.*;

/**
 * Created by Muchen on 10/22/16.
 */
public class AdminPanel {
    static JTable table;

    static void set(JPanel panel) {
//        patientPanel = panel;
        table = new JTable();

        panel.removeAll();
        panel.setLayout(null);

        JButton logoutButton = new JButton("logout");
        logoutButton.setBounds(10, 10, 80, 25);
        panel.add(logoutButton);

        JButton execButton = new JButton("Execute");
        execButton.setBounds(10, 50, 80, 25);
        panel.add(execButton);

        final JTextField userText = new JTextField(100);
        userText.setBounds(100, 50, 800, 25);
        panel.add(userText);


        final JScrollPane scollPane = new JScrollPane();
        scollPane.setViewportView(table);
        scollPane.setBounds(10, 90, 880, 480);  //Position
        scollPane.setBackground(Color.BLUE);
        panel.add(scollPane, 0);


        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                AppWindow.setCurrUser("");
                AppWindow.setCurrAccess(-1);

                AppWindow.setPanel(Const.LOGIN_PANEL);
            }
        });

        execButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String sql = userText.getText();
                ResultSet res = (ResultSet) Test.adminSQL(sql);

                if (showResult(sql)) {
                    JTextArea ta = new JTextArea();
                    ta.setText(parseResult(res));

                    scollPane.setViewportView(ta);
                }
            }
        });
    }

    private static boolean showResult(String str) {
        if (str == null || str.length() < 6)
            return false;

        return str.toLowerCase().trim().split(" ")[0].equals("select");
    }

    private static String parseResult(ResultSet res) {
        if (res == null)
            return "";

        try {
            ResultSetMetaData rsmd = res.getMetaData();

            int columnsNumber = rsmd.getColumnCount();
            StringBuilder txt = new StringBuilder();

            for (int i = 1; i <= columnsNumber; i++) {
                txt.append(rsmd.getColumnName(i));
                txt.append("\t");
            }
            txt.append("\n");

            while (res.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    txt.append(res.getString(i));
                    txt.append("\t");
                }
                txt.append("\n");
            }

            return txt.toString();

        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
}
