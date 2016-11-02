package com.mz.dbms.dao;

public class MEMBER {
	/*State these variables in the table of member;
	 * 
	 */
	public String MID;
	public String NAME;
	public String DOB;
	public String ADDR;
	public String GENDER;
	public String CONTACT;
	public String PASSWORD;
	public String REGISTDATE;
	public String SICKDATE;
	public String DIAGNOSES;
	
	public MEMBER(String mid, String name, String dob, String addr, String gender, String contact, String password,String registdate, String sickdate, String diagnoses){
		MID = mid;
		NAME = name;
		DOB = dob;
		ADDR = addr;
		GENDER = gender;
		CONTACT = contact;
		PASSWORD = password;
		REGISTDATE = registdate;
		SICKDATE = sickdate;
		DIAGNOSES = diagnoses;
	}
}
