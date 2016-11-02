package com.mz.dbms.adapter;

import java.util.ArrayList;

public class Alert {

	public static void AlertForSupporter(String usr) {
		//Logger.Connect();
		try{
			String sql = "select PID from ASSIGN where SID = '" + usr + "'";
			ArrayList<String> myPatients = Logger.tran_query_into_array(Logger.myStat.executeQuery(sql), "PID");
			for(String a : myPatients){
				Logger.NoRecordAlert(a);
				Logger.AbnormalAlert(a);
				System.out.println("My patients alerts created.");
			} 
		} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	public static void AlertForPatient(String usr) {
		try {
			Logger.NoRecordAlert(usr);
			Logger.AbnormalAlert(usr);
			System.out.println("My alert created.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
