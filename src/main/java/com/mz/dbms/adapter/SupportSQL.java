package com.mz.dbms.adapter;
import com.mz.dbms.dao.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.mz.dbms.dao.DISEASE;

public class SupportSQL {
	//global variable for the observe table.
	public static int oid = 10;

	/**
	 * Member Part;
	 */
	//MEMBER search;
	public static MEMBER_Table searchMember(String MID) throws SQLException{
		String mem1 = "select PID from ASSIGN where SID = '" + MID +"'";
		ResultSet res1 = Logger.myStat.executeQuery(mem1);
		String[] pid1 = new String[50];
		int flag = -1;
		while(res1.next()){
			pid1[flag+1] = res1.getString("PID");
			flag++;
		}
		String mem2 = "select * from MEMBER Where MID ='" + pid1[flag] + "'";
		while(flag > 0){
			flag--;
			mem2 = mem2 + " or MID = '" + pid1[flag] + "'";
		}

//		System.out.println(mem2);
		ResultSet res2 = Logger.myStat.executeQuery(mem2);
		List<MEMBER> mo = new LinkedList<MEMBER>();
		while(res2.next()) {
			MEMBER newMem = new MEMBER(res2.getString("MID"),
					res2.getString("NAME"),res2.getString("DOB"),res2.getString("ADDR"),res2.getString("GENDER"),res2.getString("CONTACT"),res2.getString("PASSWORD"),res2.getString("REGISTDATE"),res2.getString("SICKDATE"),res2.getString("DIAGNOSES"));
			mo.add(newMem);
    	}
		 MEMBER_Table newTable = new MEMBER_Table(mo);
		 return newTable;
	}

	//Member add/edit
	public static boolean addNewMember(String MID, String NAME, String	DOB, String ADDR, String GENDER, String CONTACT, String PASSWORD, String SICKDATE, String DIAGNOSES) {
		String res = "insert into MEMBER(MID, NAME, DOB, ADDR, GENDER, CONTACT, PASSWORD, REGISTDATE, SICKDATE, DIAGNOSES) values(";
		res = res + "'" + MID + "','" + NAME + "','" + DOB + "','" + ADDR + "','" + GENDER + "','" + CONTACT + "','" + PASSWORD + "','" + "RICK" + "','" + SICKDATE + "','" + DIAGNOSES + "')";
		System.out.println(res);
		try{
			Logger.myStat.executeQuery(res);
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	//Delete Member
	public static boolean delMember(String MID) {
		String res = "delete from MEMBER where MID='"+ MID + "'";
		System.out.println(res);
		try{
			Logger.myStat.executeQuery(res);
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		return true;
	}


	/**
	 * Alert Part;
	 */
	//search alert
	public static ALERT_Table searchAlert(String SUPPORTER_ID){
		try{
			String mem1 = "select PID from ASSIGN where SID= '" + SUPPORTER_ID + "'";
			ResultSet res1 = Logger.myStat.executeQuery(mem1);

			String[] pid1 = new String[50];

			int flag = -1;
			while(res1.next()){
				pid1[flag+1] = res1.getString("PID");
				flag++;
			}
			String mem2 = "select * from ALERT Where MID ='" + pid1[flag] + "'";

			while(flag > 0){
				flag--;
				mem2 = mem2 + " or MID = '" + pid1[flag] + "'";
			}
			System.out.println(mem2);
			ResultSet res = Logger.myStat.executeQuery(mem2);
			List<ALERT> mo = new LinkedList<ALERT>();
			while(res.next()) {
				ALERT newMem = new ALERT(res.getString("MID"),
						res.getString("INDICATOR"),res.getString("VALUE"),res.getString("ATIME"));
				mo.add(newMem);
			}
			System.out.println(mo.size());

			ALERT_Table newTable = new ALERT_Table(mo);
			return newTable;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	//Delete Alert
	public static boolean delAlert(String MID, String INDICATOR, String ATIME){
		String res = "delete from ALERT where MID='"+ MID + "'and INDICATOR='"+ INDICATOR + "'and ATIME='" + ATIME + "'";
		try{
			Logger.myStat.executeQuery(res);
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		return true;
	}


	/**
	 * Observation Part;
	 */
	///Observe Search
	public static OBSERVE_Table searchObserve(String MID){
		try{
		String obs1 = "select PID from ASSIGN where SID = '" + MID +"'";
		ResultSet res3 = Logger.myStat.executeQuery(obs1);
		String[] pid3 = new String[50];
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
		System.out.println(obs2);
		ResultSet res = Logger.myStat.executeQuery(obs2);
		List<OBSERVE> mo = new LinkedList<OBSERVE>();
		while(res.next()) {
			OBSERVE newMem = new OBSERVE(res.getString("OID"),
					res.getString("MID"),res.getString("INDICATOR"),res.getString("VALUE"),res.getString("OBSERVEDATE"),res.getString("REPORTDATE"));
			mo.add(newMem);
    	}
		 OBSERVE_Table newTable = new OBSERVE_Table(mo);
		 return newTable;
		 } catch (Exception ex) {
			 ex.printStackTrace();
			return null;
		 }
	}
	
	//add observe
	public static boolean addObserve(String mid, String indicator, String value, String Odate, String Rdate){
		oid++;
        String add_observe = "Insert Into OBSERVE(OID, MID, INDICATOR, VALUE, OBSERVEDATE, REPORTDATE)values('" + oid + "','" + mid + "','" + indicator + "','" + value + "','" + Odate + "','" + Rdate + "')";
        try{
        	Logger.myStat.executeQuery(add_observe);
        }
        catch(Exception ex){
        	ex.printStackTrace();
        	return false;
        }
        return true;
    }

	//delete observe
	public static boolean delObserve(String OID){
		String del_observe = "Delete from OBSERVE where OID = '" + OID + "'";
		try{
        	Logger.myStat.executeQuery(del_observe);
        }
        catch(Exception ex){
        	ex.printStackTrace();
        	return false;
        }
        return true;
	}


	/**
	 * Disease Part
	 */
	public static DISEASE_Table searchDisease(){
		try{
			String mem2 = "select * from DISEASE";
			ResultSet res = Logger.myStat.executeQuery(mem2);
			List<DISEASE> mo = new LinkedList<DISEASE>();
			while(res.next()) {
				DISEASE newMem = new DISEASE(res.getString("DID"),
						res.getString("DNAME"),res.getString("DESCRIPT"));
				mo.add(newMem);
			}
			DISEASE_Table newTable = new DISEASE_Table(mo);
			return newTable;
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static boolean addDisease(String DID, String DNAME, String DESCRIPT) {

		String res = "insert into DISEASE(DID, DNAME, DESCRIPT) values(";

		res = res + "'" + DID + "','" + DNAME + "','" + DESCRIPT + "')";

		System.out.println(res);

		try{

			Logger.myStat.executeQuery(res);

		}

		catch(Exception ex){

			ex.printStackTrace();

			return false;

		}

		return true;

	}


	/**
	 * Recommendation Part;
	 */
	//search Recommendation;
	public static MRECOMMEND_Table seaMrecommend(String SUPPORTER_ID){
		try{
			String mem1 = "select PID from ASSIGN where SID= '" + SUPPORTER_ID + "'";
			ResultSet res1 = Logger.myStat.executeQuery(mem1);
			System.out.println(mem1);
			String[] pid1 = new String[10];

			int flag = -1;
			while(res1.next()){
				pid1[flag+1] = res1.getString("PID");
				flag++;
			}
			String mem2 = "select * from MRECOMMEND Where MID ='" + pid1[flag] + "'";

			while(flag > 0){
				flag--;
				mem2 = mem2 + " or MID = '" + pid1[flag] + "'";
			}
			System.out.println(mem2);
			ResultSet res = Logger.myStat.executeQuery(mem2);
			List<MRECOMMEND> mo = new LinkedList<MRECOMMEND>();
			while(res.next()) {
				MRECOMMEND newMem = new MRECOMMEND(res.getString("MID"),
						res.getString("INDICATOR"),res.getString("INFORMATION"),res.getString("FREQ"));
				mo.add(newMem);
			}
			System.out.println(mo.size());

			MRECOMMEND_Table newTable = new MRECOMMEND_Table(mo);
			return newTable;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	//add/edit recommendation table
	public static boolean MReUpdate(String MID, String INDICATOR, String INFO, String FREQ){
		boolean output = false;
		String sql1 = "insert into MRECOMMEND(MID,INDICATOR, INFORMATION, FREQ) values('" + MID + "','" + INDICATOR + "','" + INFO +"','"+ FREQ + "')";
		String sql2 = "delete from MRECOMMEND where MID ='" + MID + "'AND INDICATOR ='" + INDICATOR + "'";
		String sql3 = "select MID, INDICATOR from MRECOMMEND where MID ='" + MID + "'AND INDICATOR ='" + INDICATOR + "'";
		try {
			HashMap<String, String> map = Logger.tran_query_into_map(Logger.myStat.executeQuery(sql3));
			if(map.size() == 0){
				Logger.myStat.executeUpdate(sql1);
			} else {
				Logger.myStat.executeUpdate(sql2);
				Logger.myStat.executeUpdate(sql1);
			}
			output = true;
		} catch (SQLException e) {
			e.printStackTrace();
			output = false;
		}
		return output;
	}

	//delete recommendation table
	public static boolean MreDelete(String mid, String indicator){
		boolean output = false;
		String sql1 = "delete from MRECOMMEND where MID ='" + mid + "'AND INDICATOR ='" + indicator + "'";
		try {
			Logger.myStat.executeUpdate(sql1);
			output = true;
		} catch (SQLException e) {
			e.printStackTrace();
			output = false;
		}
		return output;
	}


	/**
	 * Assign Part
	 */
	//search ASSIGN;
	public static ASSIGN_Table searchAssign(String MID){
		try{
		String obs1 = "select PID from ASSIGN where SID = '" + MID +"'";
		ResultSet res3 = Logger.myStat.executeQuery(obs1);
		String[] pid3 = new String[50];
		int flag = -1;
		while(res3.next()){
			pid3[flag+1] = res3.getString("PID");
			flag++;
		}
		String obs2 = "select * from ASSIGN Where PID ='" + pid3[flag] + "'";
		while(flag > 0){
			flag--;
			obs2 = obs2 + " or PID = '" + pid3[flag] + "'";
		}
		//the version below has some bugs.
//		if(flag == 0){
//			 obs2 = "select * from OBSERVE Where MID ='" + pid3[0] + "'";
//		}else{
//			 obs2 = "select * from OBSERVE Where MID ='" + pid3[0] + "' or MID = '" + pid3[1] + "'";
//		}
		//print for test purpose;
		//System.out.println(obs2);
		System.out.println(obs2);
		ResultSet res = Logger.myStat.executeQuery(obs2);
		List<ASSIGN> mo = new LinkedList<ASSIGN>();
		while(res.next()) {
			ASSIGN newMem = new ASSIGN(res.getString("PID"),
					res.getString("SID"),res.getString("ISPRIMARY"),res.getString("FROMDATE"),res.getString("TODATE"));
			mo.add(newMem);	
		}
		System.out.println(mo.size());
		
		 ASSIGN_Table newTable = new ASSIGN_Table(mo);
		 return newTable;
		 } catch (Exception ex) {
			 ex.printStackTrace();
			return null;
		 }
	}

	public static boolean AssignEdit(String MID, String PRIMARY_SUPPORTER, String FROM_DATE1, String TO_DATE1, String SECONDDARY_SUPPORTER, String FROM_DATE2, String TO_DATE2){
		String sql1 = "DELETE FROM ASSIGN where PID ='" + MID + "'";
		String sql2 = "insert into ASSIGN(PID, SID, ISPRIMARY, FROMDATE, TODATE)values('" + MID + "','" + PRIMARY_SUPPORTER + "','Y','" + FROM_DATE1 + "','" + TO_DATE1 + "')";
		String sql3 = "insert into ASSIGN(PID, SID, ISPRIMARY, FROMDATE, TODATE)values('" + MID + "','" + SECONDDARY_SUPPORTER + "','N','" + FROM_DATE2 + "','" + TO_DATE2 + "')";
		try {
			Logger.myStat.executeQuery(sql1);
			Logger.myStat.executeQuery(sql2);
			if(!SECONDDARY_SUPPORTER.equals(""))
			{
				Logger.myStat.executeQuery(sql3);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}




	public static void getMaxoid(){
		String max = "select OID from OBSERVE";
		try{
			ResultSet res = Logger.myStat.executeQuery(max);

			while (res.next()) {
				int tmp = Integer.parseInt(res.getString("OID"));
				oid = Math.max(oid, tmp);
			}

			oid++;
			System.out.println(oid);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
