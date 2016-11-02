package com.mz.dbms.adapter;

import com.mz.dbms.dao.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Muchen on 10/23/16.
 */
public class Test {

    public static MEMBER_Table test1(String MID) throws SQLException{
        String mem1 = "select PID from ASSIGN where SID = '" + MID +"'";
        ResultSet res1 = Logger.myStat.executeQuery(mem1);
        String[] pid1 = new String[2];
        int flag = -1;
        while(res1.next()){
            pid1[flag+1] = res1.getString("PID");
            flag++;
        }
        String mem2 = new String();
        if(flag == 0){
            mem2 = "select * from MEMBER Where MID ='" + pid1[0] + "'";
        }else{
            mem2 = "select * from MEMBER Where MID ='" + pid1[0] + "' or MID = '" + pid1[1] + "'";
        }
//print for test purpose;
        System.out.println(mem2);
        ResultSet res = Logger.myStat.executeQuery(mem2);
        List<MEMBER> mo = new LinkedList<MEMBER>();
        while(res.next()) {
            MEMBER newMem = new MEMBER(res.getString("MID"),
                    res.getString("NAME"),res.getString("DOB"),res.getString("ADDR"),res.getString("GENDER"),res.getString("CONTACT"),res.getString("PASSWORD"),res.getString("REGISTDATE"),res.getString("SICKDATE"),res.getString("DIAGNOSES"));
            mo.add(newMem);
        }
        MEMBER_Table newTable = new MEMBER_Table(mo);
        return newTable;
    }

    public static Object adminSQL(String sql) {
        try {
            ResultSet res = Logger.myStat.executeQuery(sql);
            return res;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static MEMBER_Table test1() throws SQLException {
        String mem1 = "select * from MEMBER";
        ResultSet res = Logger.myStat.executeQuery(mem1);
        List<MEMBER> mo = new LinkedList<MEMBER>();
        while(res.next()) {
            MEMBER newMem = new MEMBER( res.getString("MID"),   res.getString("NAME"),
                                        res.getString("DOB"),   res.getString("ADDR"),
                                        res.getString("GENDER"),    res.getString("CONTACT"),
                                        res.getString("PASSWORD"),  res.getString("REGISTDATE"),
                                        res.getString("SICKDATE"),  res.getString("DIAGNOSES"));
            mo.add(newMem);
        }

        MEMBER_Table newTable = new MEMBER_Table(mo);

        return newTable;
    }

    /**
     *
     * @param MID
     * @param NAME
     * @param DOB
     * @param ADDR
     * @param GENDER
     * @param CONTACT
     * @param PASSWORD
     * @param SICKDATE
     * @param DIAGNOSES
     * @return
     */
    public static boolean addeditMember(String MID, String NAME, String DOB, String ADDR, String GENDER, String CONTACT, String PASSWORD, String SICKDATE, String DIAGNOSES){

        try{
            String sql1 = "select MID from MEMBER where MID ='" + MID +"'";
            System.out.println(sql1);
            ResultSet res = Logger.myStat.executeQuery(sql1);
            int size1 = 0;
            while(res.next()) {
                size1++;
            }
            if(size1 == 1){
                String sql2 = "Update MEMBER SET NAME='" + NAME + "'," + "DOB='" + DOB + "'," + "ADDR='" + ADDR + "'," + "GENDER='" + GENDER + "'," + "CONTACT='" + CONTACT + "'," + "PASSWORD='" + PASSWORD + "'," + "SICKDATE='" + SICKDATE + "'," + "DIAGNOSES='" + DIAGNOSES +"' WHERE MID='" + MID + "'";
                Logger.myStat.executeQuery(sql2);
            }
            else{
                String sql3 = "insert into MEMBER(MID, NAME, DOB, ADDR, GENDER, CONTACT, PASSWORD, REGISTDATE, SICKDATE, DIAGNOSES) values(";
                sql3 = sql3 + "'" + MID + "','" + NAME + "','" + DOB + "','" + ADDR + "','" + GENDER + "','" + CONTACT + "','" + PASSWORD + "','" + "null" + "','" + SICKDATE + "','" + DIAGNOSES + "')";
                Logger.myStat.executeQuery(sql3);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }

    public static OBSERVE_Table test2(String MID) throws SQLException{
        String obs1 = "select PID from ASSIGN where SID = '" + MID +"'";
        ResultSet res3 = Logger.myStat.executeQuery(obs1);
        String[] pid3 = new String[2];
        int flag = -1;
        while(res3.next()){
            pid3[flag+1] = res3.getString("PID");
            flag++;
        }
        String obs2 = new String();
        if(flag == 0){
            obs2 = "select * from OBSERVE Where MID ='" + pid3[0] + "'";
        }else{
            obs2 = "select * from OBSERVE Where MID ='" + pid3[0] + "' or MID = '" + pid3[1] + "'";
        }
//print for test purpose;
//System.out.println(obs2);
        ResultSet res = Logger.myStat.executeQuery(obs2);
        List<OBSERVE> mo = new LinkedList<OBSERVE>();
        while(res.next()) {
            OBSERVE newMem = new OBSERVE(res.getString("MID"),
                    res.getString("OID"),res.getString("INDICATOR"),res.getString("VALUE"),res.getString("OBSERVEDATE"),res.getString("REPORTDATE"));
            mo.add(newMem);
        }
        OBSERVE_Table newTable = new OBSERVE_Table(mo);
        return newTable;
    }
    public static OBSERVE_Table test3(String MID){
        try{
            String obs1 = "select PID from ASSIGN where SID = '" + MID +"'";
            ResultSet res3 = Logger.myStat.executeQuery(obs1);
            String[] pid3 = new String[2];
            int flag = -1;
            while(res3.next()){
                pid3[flag+1] = res3.getString("PID");
                flag++;
            }
            String obs2 = new String();
            if(flag == 0){
                obs2 = "select * from OBSERVE Where MID ='" + pid3[0] + "'";
            }else{
                obs2 = "select * from OBSERVE Where MID ='" + pid3[0] + "' or MID = '" + pid3[1] + "'";
            }
//print for test purpose;
//System.out.println(obs2);
            ResultSet res = Logger.myStat.executeQuery(obs2);
            List<OBSERVE> mo = new LinkedList<OBSERVE>();
            while(res.next()) {
                OBSERVE newMem = new OBSERVE(res.getString("MID"),
                        res.getString("OID"),res.getString("INDICATOR"),res.getString("VALUE"),res.getString("OBSERVEDATE"),res.getString("REPORTDATE"));
                mo.add(newMem);
            }
            OBSERVE_Table newTable = new OBSERVE_Table(mo);
            return newTable;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        try {
            Logger.Connect();
            Logger.myStat.executeQuery("insert into MEMBER(MID, NAME, DOB, ADDR, GENDER, CONTACT, PASSWORD, REGISTDATE, SICKDATE, DIAGNOSES) values('RICKRICK', 'RICKRICK', 'RICKRICK', 'RICKRICK', 'RICKRICK', 'RICKRICK', 'RICKRICK', 'RICKRICK', 'RICKRICK', 'RICKRICK')");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
