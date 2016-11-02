package com.mz.dbms.adapter;

import com.mz.dbms.dao.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class PatientSQL {
	public static int oid = 0;
	
	
	//Member search
	public static MEMBER_Table test2(String MID){
		try{
		String mem1 = "select * from MEMBER where  MID = '" + MID +"'";
		ResultSet res = Logger.myStat.executeQuery(mem1);
		List<MEMBER> mo = new LinkedList<MEMBER>();
		while(res.next()) {
			MEMBER newMem = new MEMBER(res.getString("MID"),
					res.getString("NAME"),res.getString("DOB"),res.getString("ADDR"),res.getString("GENDER"),res.getString("CONTACT"),res.getString("PASSWORD"),res.getString("REGISTDATE"),res.getString("SICKDATE"),res.getString("DIAGNOSES"));
			mo.add(newMem);
    	}
		System.out.println(mo);
		
		 MEMBER_Table newTable = new MEMBER_Table(mo);
		 return newTable;
		 //System.out.println(newTable.getString("NAME"));
		 } catch (Exception ex) {
			 ex.printStackTrace();
			return null;
		}
	}
	
	
	//DIAGNOSES search 
	//What should I return? I don't really understand. SO do I need to make a new constructor in the MEMBER.java?
	public static MemberDiagnoses_Table diagnoses(String MID){
		try{
		String mem1 = "select MID,DIAGNOSES from MEMBER where  MID = '" + MID +"'";
		ResultSet res = Logger.myStat.executeQuery(mem1);
		List<MemberDiagnoses> mo = new LinkedList<MemberDiagnoses>();
		while(res.next()) {
			MemberDiagnoses newMem = new MemberDiagnoses(res.getString("MID"),
					res.getString("DIAGNOSES"));
			mo.add(newMem);
    	}
		System.out.println(mo);
		
		 MemberDiagnoses_Table newTable = new MemberDiagnoses_Table(mo);
		 return newTable;
		 //System.out.println(newTable.getString("NAME"));
		 } catch (Exception ex) {
			 ex.printStackTrace();
			return null;
		}
	}
	
	
	//show recommendation of a given patient
	public static MRECOMMEND_Table recommend(String MID){
		try{
		String mem1 = "select * from MRECOMMEND where  MID = '" + MID +"'";
		ResultSet res = Logger.myStat.executeQuery(mem1);
		List<MRECOMMEND> mo = new LinkedList<MRECOMMEND>();
		while(res.next()) {
			MRECOMMEND newMem = new MRECOMMEND(res.getString("MID"),
					res.getString("INDICATOR"),res.getString("INFORMATION"),res.getString("FREQ"));
			mo.add(newMem);
    	}
		System.out.println(mo);
		
		 MRECOMMEND_Table newTable = new MRECOMMEND_Table(mo);
		 return newTable;
		 //System.out.println(newTable.getString("NAME"));
		 } catch (Exception ex) {
			 ex.printStackTrace();
			return null;
		}
	}
	
	public static ALERT_Table alert(String MID){
		try{
		String mem1 = "select * from ALERT where  MID = '" + MID +"'";
		ResultSet res = Logger.myStat.executeQuery(mem1);
		List<ALERT> mo = new LinkedList<ALERT>();
		while(res.next()) {
			ALERT newMem = new ALERT(res.getString("MID"),
					res.getString("INDICATOR"),res.getString("VALUE"),res.getString("ATIME"));
			mo.add(newMem);
    	}
		System.out.println(mo.size());
		
		 ALERT_Table newTable = new ALERT_Table(mo);
		 return newTable;
		 //System.out.println(newTable.getString("NAME"));
		 } catch (Exception ex) {
			 ex.printStackTrace();
			return null;
		}
	}
	
	
	//Show all supports of a specific patients
	public static ASSIGN_Table assign(String PID){
		try{
		String mem1 = "select * from ASSIGN where  PID = '" + PID +"'";
		ResultSet res = Logger.myStat.executeQuery(mem1);
		List<ASSIGN> mo = new LinkedList<ASSIGN>();
		while(res.next()) {
			ASSIGN newMem = new ASSIGN(res.getString("PID"),
					res.getString("SID"),res.getString("ISPRIMARY"),res.getString("FROMDATE"),res.getString("TODATE"));
			mo.add(newMem);
    	}
		System.out.println(mo.size());
		
		 ASSIGN_Table newTable = new ASSIGN_Table(mo);
		 return newTable;
		 //System.out.println(newTable.getString("NAME"));
		 } catch (Exception ex) {
			 ex.printStackTrace();
			return null;
		}
	}
	
	
	//Show all observations of a specific member
	public static OBSERVE_Table observe(String MID){
		try{
		String mem1 = "select * from OBSERVE where  MID = '" + MID +"'";
		ResultSet res = Logger.myStat.executeQuery(mem1);
		List<OBSERVE> mo = new LinkedList<OBSERVE>();
		while(res.next()) {
			OBSERVE newMem = new OBSERVE(res.getString("OID"),
					res.getString("MID"),res.getString("INDICATOR"),res.getString("VALUE"),res.getString("OBSERVEDATE"),res.getString("REPORTDATE"));
			mo.add(newMem);
    	}
		System.out.println(mo.size());
		
		 OBSERVE_Table newTable = new OBSERVE_Table(mo);
		 return newTable;
		 //System.out.println(newTable.getString("NAME"));
		 } catch (Exception ex) {
			 ex.printStackTrace();
			return null;
		}
	}
	
	
//	//get the max value of current oid.
//	public static void getMaxoid(){
//    	String max = "select OID from OBSERVE";
//    	try{
//	        ResultSet res = Logger.myStat.executeQuery(max);
//	        
//	        while (res.next()) {
//	            int tmp = Integer.parseInt(res.getString("OID"));
//	            oid = Math.max(oid, tmp);
//	        }
//        
//	        oid++;
//	        System.out.println(oid);
//        } catch (Exception ex) {
//        	ex.printStackTrace();
//        }
//    }
	
	
	//add observe to a given member
	//MID should be a constant value for each member
	
	public static int getMaxoid(){
    	String max = "select OID from OBSERVE";
    	try{
	        ResultSet res = Logger.myStat.executeQuery(max);
	        
	        while (res.next()) {
	            int tmp = Integer.parseInt(res.getString("OID"));
	            oid = Math.max(oid, tmp);
	        }
        
	        oid++;
	        System.out.println(oid);
	        return oid;
        } catch (Exception ex) {
        	ex.printStackTrace();
        	return 0;
        }
    }
	
	public static boolean addObserve(String mid, String indicator, String value, String Odate, String Rdate){
//		int oid = SupportSQL.oid + 1;
		oid = getMaxoid();
		System.out.println(oid);
        String add_observe = "Insert Into OBSERVE(OID, MID, INDICATOR, VALUE, OBSERVEDATE, REPORTDATE)values('" + oid + "','" + mid + "','" + indicator + "','" + value + "','" + Odate + "','" + Rdate + "')";
        System.out.println(add_observe);
        try{
        	Logger.myStat.executeQuery(add_observe);
        }
        catch(Exception ex){
        	ex.printStackTrace();
        	return false;
        }
        return true;
    }
}
