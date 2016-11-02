package com.mz.dbms.dao;

public class OBSERVE {
	public String OID;
	public String MID;
	public String INDICATOR;
	public String VALUE;
	public String OBSERVEDATE;
	public String REPORTDATE;
	
	public OBSERVE(String oid, String mid, String indicator, String value,String observedate, String reportdate){
		OID = oid;
		MID = mid;
		INDICATOR = indicator;
		VALUE = value;
		OBSERVEDATE = observedate;
		REPORTDATE = reportdate;
	}

}
