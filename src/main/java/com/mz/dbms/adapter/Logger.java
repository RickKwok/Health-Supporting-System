package com.mz.dbms.adapter;

/**
 * Created by Yifan on 10/8/16.
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
public class Logger {
    /**
     *
     * @param userName
     * @param password
     * @return
     *
     * -1:  means no such user or incorrect username or incorrect password.
     * 0:   means super administrator.
     * 1:   means patient.
     * 2:   means supporter.
     */
	public static final String jdbcURL = "jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01";
	public static String user = "sdu3";
	public static String passwd = "200106017";
	public static Statement myStat;
	public static Integer NoRecordAlerts = 0;
	
    public static boolean getAuthority(String userName, String password, int desireIdentity) throws SQLException {    	
    	//Connect();
    	String sql = "select MID, PASSWORD from MEMBER";
    	HashMap<String,String> allMember = tran_query_into_map(myStat.executeQuery(sql));
    	boolean output = false;
    	if(allMember.containsKey(userName)) {
    		if(allMember.get(userName).equals(password)) {
    			//String Identity = myStat.executeQuery("select  from MEMBER where userName ="+ userName + ";").getString(0);
    			String sql1 = "select PID, SID from ASSIGN where PID = '" + userName + "' OR SID ='" + userName + "'";
    			//System.out.println(sql1);
    			HashMap<String, String> UserIdentity = tran_query_into_map(myStat.executeQuery(sql1));
    			if(desireIdentity == 1){
    					if(UserIdentity.containsKey(userName)){
    						output = true;
    					}
    			}	else if(desireIdentity == 2){
	    					if(UserIdentity.containsValue(userName)){
	    						output = true;
	    					}
	    				}	
    		} else {
    				output = false;
    				System.out.println("wrong password");
    				}
    	} else {
    			output = false;
    			}
    	return output;
    }
    
    public static void Connect(){
    	try{
    		Class.forName("oracle.jdbc.driver.OracleDriver");
    		Connection conn = DriverManager.getConnection(jdbcURL, user, passwd);
    		myStat = conn.createStatement();
    		System.out.println("connected");
    	} catch(Exception e){
    		e.printStackTrace();
    		Connect();
    	}
    }
    
    public static void AbnormalAlert(String name) {
    	try{
	    	String sql1 = "select INDICATOR, INFORMATION from MRECOMMEND";
			HashMap<String, String> type_normal = tran_query_into_map(myStat.executeQuery(sql1));
	    	String sql2 = "select INDICATOR, VALUE from OBSERVE where MID ='" + name + "'";
	    	HashMap<String, String> ObPerPerson = tran_query_into_map(myStat.executeQuery(sql2));
	    		
	    		for(String type_key : type_normal.keySet()){
	    			for(String Ob_key : ObPerPerson.keySet()){
	    				if(Ob_key.equals(type_key)){
	    					if(NotWithin(ObPerPerson.get(Ob_key), type_normal.get(type_key))){
	    						String sql3 = "select OBSERVEDATE from OBSERVE where INDICATOR ='" + Ob_key + "' AND VALUE = '" + ObPerPerson.get(Ob_key) + "'"; 
	    						System.out.println(sql3);
	    						ResultSet OBtime = myStat.executeQuery(sql3);
	    						ArrayList<String> time = tran_query_into_array(OBtime, "OBSERVEDATE");
	    						createAbAlert(name, type_key, ObPerPerson.get(Ob_key), time.get(0));
	    						System.out.println("Abnormal alert created.");
	    					} else {
	    						System.out.println("No need to create abnormal alert.");
	    					}
	    				}				
	    			}
	    		}			
    		}catch (Exception e){
    			e.printStackTrace();
    		}	
    	}
    
    private static void createAbAlert(String name, String indicator, String Value, String Atime) {
    	String sql3 = "insert into ALERT(MID, INDICATOR, VALUE, ATIME)values('" +name+ "'"
    			+ ",'"+ indicator + "','" + Value + "','" + Atime +"')";
    	try {
			myStat.executeUpdate(sql3);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void NoRecordAlert(String name) {
    	try{
	    	String sql1 = "select INDICATOR, FREQ from MRECOMMEND";
	    	HashMap<String, String> freq_normal = tran_query_into_map(myStat.executeQuery(sql1));
	    	String sql2 = "select INDICATOR, OBSERVEDATE from OBSERVE where MID = '" + name + "'";
	    	HashMap<String, ArrayList<String>> indic_date = new HashMap<String, ArrayList<String>>();
	    	ResultSet res = myStat.executeQuery(sql2);
	    	while(res.next()){
	    		String key = res.getString(1);
	    		String value = res.getString(2);
	    	
	    		if(!indic_date.containsKey(key)){
	    			ArrayList<String> temp = new ArrayList<String>();
	        		temp.add(value);
	        		indic_date.put(key, temp);
	    		} else {
	    			ArrayList<String> temp = new ArrayList<String>();
	    			temp.addAll(indic_date.get(key));
	    			temp.add(value);
	    			Collections.sort(temp);
	    			indic_date.put(key, temp);
	    		}
	    	}

	    	for(String person_key : indic_date.keySet()){
	    		for(String normal_key: freq_normal.keySet()){
	    			if(normal_key.equals(person_key)){
	    				ArrayList<Integer> obinterval = ObInterval(indic_date.get(person_key)); 
	    				for(int i = 0; i < obinterval.size(); i++){
	    					if(obinterval.get(i) >= Integer.parseInt(freq_normal.get(normal_key))){
	    						String missDate = indic_date.get(person_key).get(i);
	    						createMissAlert(name, normal_key, missDate);
	    						NoRecordAlerts++;
	    					} else {
	    						System.out.println("No need to create missing alert.");
	    					}
	    				}
	    			}
	    		}
	    	}
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
        
    private static void createMissAlert(String name, String normal_key, String missDate) {
    	String sql5 = "insert into ALERT(MID, INDICATOR, VALUE, ATIME)values('"+ name+ "','"+ normal_key + "',NULL,'"+ missDate+"')";
    	try {
			myStat.executeQuery(sql5);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static ArrayList<Integer> ObInterval(ArrayList<String> list) {
		ArrayList<Integer> interval = new ArrayList<Integer>();
		for(int i = 0; i < list.size() - 1; i++){

			Integer inter = 0;
			String[] prev = list.get(i).split("-");
			String[] next = list.get(i+1).split("-");
			int[] pre = {0,0,0};
			int[] nex = {0,0,0};
			for(int j = 0; j < 3; j++){
				pre[j] = Integer.parseInt(prev[j]);
				nex[j] = Integer.parseInt(next[j]);
			}
			if(nex[0] > pre[0]){
				inter = inter + (nex[0] - pre[0]) * 365;
			}	
			if(nex[1] > pre[1]){
				inter = inter + (nex[1] - pre[1])*30;
			}
			if(nex[2] > pre[2]){
				inter = inter + nex[2] - pre[2];
			} else if(nex[2] < pre[2]){
				inter = inter + nex[2] - pre[2];
			}
			interval.add(inter);
		}
		return interval;
	}

	private static HashMap<String, ArrayList<String>> sort_to_date(ResultSet result) {
		HashMap<String, ArrayList<String>> sort = new HashMap<String, ArrayList<String>>();
		ArrayList<String> indic;
		indic = tran_query_into_array(result, "INDICATOR");
		ArrayList<String> Ob = tran_query_into_array(result, "OBSERVEDATE");
		ArrayList<String> temp = new ArrayList<String>();
		
		for(int i = 0; i < indic.size(); i++)
		{
			if(!sort.containsKey(indic.get(i)))
			{
				temp.add(Ob.get(i));
			}	else {
				temp = sort.get(indic.get(i));
				temp.add(Ob.get(i));
			}
			Collections.sort(temp);
			sort.put(indic.get(i), temp);
		}
		return sort;	
	}

	private static boolean NotWithin(String str1, String str2) {
    	boolean notWithin = false;
    	String[] parts = str2.split("-");
    	int part1 = Integer.parseInt(parts[0]);
    	int part2 = Integer.parseInt(parts[1]);
    	int real = Integer.parseInt(str1);
    	if(real > part2 || real < part1){
    		notWithin = true;
    	} else{
    		notWithin = false;
    	}
    	return notWithin;
	}

	public static HashMap<String,String> tran_query_into_map(ResultSet result) {
		HashMap<String, String> newMap = new HashMap<String, String>();
		try{	
	    	while(result.next()){
				newMap.put(result.getString(1), result.getString(2));
				}
	    	} catch(Exception e){
				e.printStackTrace();
				}
		return newMap;
	}

    public static ArrayList<String> tran_query_into_array(ResultSet result, String string_to_get) {
		ArrayList<String> newArray = new ArrayList<String>();
		try {
			while(result.next())
			{
				newArray.add(result.getString(string_to_get));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newArray;	
	}
    
    public static boolean AssignEdit(String MID, String PRIMARY_SUPPORTER, String FROM_DATE1, String TO_DATE1, String SECONDDARY_SUPPORTER, String FROM_DATE2, String TO_DATE2){
		boolean output = false;
    	String sql1 = "DELETE FROM ASSIGN where PID ='" + MID + "'";
    	String sql2 = "insert into ASSIGN(PID, SID, ISPRIMARY, FROMDATE, TODATE)values('" + MID + "','" + PRIMARY_SUPPORTER + "','Y','" + FROM_DATE1 + "','" + TO_DATE1 + "')";
    	String sql3 = "insert into ASSIGN(PID, SID, ISPRIMARY, FROMDATE, TODATE)values('" + MID + "','" + SECONDDARY_SUPPORTER + "','N','" + FROM_DATE2 + "','" + TO_DATE2 + "')";
    	try {
    		//System.out.println(sql1);
			myStat.executeUpdate(sql1);
			myStat.executeUpdate(sql2);
			if(!SECONDDARY_SUPPORTER.equals(""))
			{
				myStat.executeUpdate(sql3);
			}
			output = true;
		} catch (SQLException e) {
			e.printStackTrace();
			output = false;
		}
    	return output;
    }
    
    public static boolean MReUpdate(String MID, String INDICATOR, String INFO, String FREQ){
    	boolean output = false;
    	String sql1 = "insert into MRECOMMEND(MID,INDICATOR, INFORMATION, FREQ) values('" + MID + "','" + INDICATOR + "','" + INFO +"','"+ FREQ + "')";
    	String sql2 = "delete from MRECOMMEND where MID ='" + MID + "'AND INDICATOR ='" + INDICATOR + "'";
    	String sql3 = "select MID, INDICATOR from MRECOMMEND where MID ='" + MID + "'AND INDICATOR ='" + INDICATOR + "'";
    	try {
			HashMap<String, String> map = tran_query_into_map(myStat.executeQuery(sql3));
			if(map.size() == 0){
				myStat.executeUpdate(sql1);
			} else {
				myStat.executeUpdate(sql2);
				myStat.executeUpdate(sql1);
			}
			output = true;
		} catch (SQLException e) {
			e.printStackTrace();
			output = false;
		}
		return output;
    }
    
    public static boolean MreDelete(String mid, String indicator){
    	boolean output = false;
    	String sql1 = "delete from MRECOMMEND where MID ='" + mid + "'AND INDICATOR ='" + indicator + "'";
    	try {
			myStat.executeUpdate(sql1);
			output = true;
		} catch (SQLException e) {
			e.printStackTrace();
			output = false;
		}
    	return output;
    }
    
	public static void main(String[] args) throws SQLException{
    	Connect();
//    	System.out.println("I m here");
 //   	AbnormalAlert("'P3'");
    	NoRecordAlert("'P2'");
//    	Alert a = new Alert();
//    	a.AlertForPatient("'P1'");
//    	a.AlertForSupporter("'P1'");
//    	if(MReUpdate("P3" , "Blood Pressure", "80-120", "1")){
//    		System.out.println("MRecommend Updated.");
//    	}
    	//AssignEdit("P4","P1","16-10-01","16-10-11","P2","16-10-01", "16-10-12");
    	//System.out.println(reportQuery2());
    	//getAuthority("P1", "password", 1);
    	//System.out.println("I m here");
    	//NoRecordAlert("'P2'");
    	//noSickPatients();
    	//diffObPatients();
    }
}
