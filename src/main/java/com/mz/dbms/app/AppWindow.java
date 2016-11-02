package com.mz.dbms.app;

/**
 * Created by Muchen on 10/7/16.
 */

import java.awt.*;
import java.sql.ResultSet;
import java.util.*;
import javax.swing.*;

import com.mz.dbms.adapter.Logger;
import com.mz.dbms.adapter.SupportSQL;
import com.mz.dbms.constants.*;

public class AppWindow {
    private static JFrame frame = new JFrame("Personal Health Management");

    private static final JPanel mainPane = new JPanel(new CardLayout());

    private static JPanel loginPanel;
    private static JPanel patientPanel;
    private static JPanel supporterPanel;
    private static JPanel adminPanel;

    private static String currUser = "";
    private static int currAccess = -1;

    public final static ArrayList<String> indicators = new ArrayList<String>();

    static void refreshFrame() {
        frame.validate();
        frame.repaint();
    }

    static String getCurrUser() {
        return currUser;
    }

    static int getCurrAccess() {
        return currAccess;
    }

    static void setCurrUser(String newUser) {
        currUser = newUser;
    }

    static void setCurrAccess(int newAccess) {
        currAccess = newAccess;
    }

    static void setPanel(int state){
        CardLayout layout = (CardLayout)mainPane.getLayout();

        switch (state) {
            case Const.LOGIN_PANEL:
                LoginPanel.set(loginPanel);
                layout.show(mainPane, "login");
                break;
            case Const.ADMIN_PANEL:
                AdminPanel.set(adminPanel);
                layout.show(mainPane, "admin");
                break;
            case Const.PATIENT_PANEL:
                PatientPanel.set(patientPanel);
                layout.show(mainPane, "patient");
                break;
            case Const.SUPPORTER_PANEL:
                SupporterPanel.set(supporterPanel);
                layout.show(mainPane, "supporter");
                break;
            default:
                ;
        }
     
    }

    private static void initPanel(Container framePane) {

        mainPane.add(loginPanel, "login");
        mainPane.add(adminPanel, "admin");
        mainPane.add(patientPanel, "patient");
        mainPane.add(supporterPanel, "supporter");

        framePane.add(mainPane);

    }

    private static void getAllIndicator() {

        String sql = "select DISTINCT INDICATOR FROM GRECOMMEND";

        try{
            ResultSet res = Logger.myStat.executeQuery(sql);
            while (res.next()) {
                indicators.add(res.getString(1));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        for (String str : indicators)
            System.out.println(str);

    }

    public static void main(String[] args) {
        frame.setSize(900, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        loginPanel = new JPanel();
        patientPanel = new JPanel();
        supporterPanel = new JPanel();
        adminPanel = new JPanel();

        //Create and set up the content pane.
        initPanel(frame.getContentPane());

        setPanel(Const.LOGIN_PANEL);
//        setPanel(Const.SUPPORTER_PANEL);

        frame.setVisible(true);

        Logger.Connect();

        getAllIndicator();

        SupportSQL.getMaxoid();
    }
}