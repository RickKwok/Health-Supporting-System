package com.mz.dbms.dao;

public class ASSIGN {
	/*State these variables in the table of member;
	 * 
	 */
	public String PID;
	public String SID;
	public String ISPRIMARY;
	public String FROMDATE;
	public String TODATE;
	
	public ASSIGN(String pid, String sid, String isprimary, String fromdate, String todate){
		PID = pid;
		SID = sid;
		ISPRIMARY = isprimary;
		FROMDATE = fromdate;
		TODATE = todate;
	}
}