package com.mz.dbms.dao;

/**
 * Created by Muchen on 10/8/16.
 */
public class DISEASE {
//    private String name;
//    private String date;
//
//    public String getName() {
//        return name;
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
	public String DID;
	public String DNAME;
	public String DESCRIPT;
	
	public DISEASE(String did, String dname, String descript){
		DID = did;
		DNAME =dname;
		DESCRIPT = descript;
	}
}
