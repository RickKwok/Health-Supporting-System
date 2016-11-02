package com.mz.dbms.dao;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class MEMBER_Table extends AbstractTableModel {
	
	private static final int MID = 0;
    private static final int NAME = 1;
    private static final int DOB = 2;
    private static final int ADDR = 3;
    private static final int GENDER = 4;
    private static final int CONTACT = 5;
    private static final int PASSWORD = 6;
    private static final int REGISTDATE = 7;
    private static final int SICKDATE = 8;
    private static final int DIAGNOSES = 9;
    
    private String[] columnNames = {"MID", "NAME", "DOB", "ADDR", "GENDER", "CONTACT", "PASSWORD", "REGISTDATE", "SICKDATE", "DIAGNOSES"};
    
    private List<MEMBER> members;
    

    public MEMBER_Table(List<MEMBER> members) {
        this.members = new LinkedList<MEMBER>(members);
    }

    /**
     * @Override
     * @return
     */

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return members.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        MEMBER tmpMember = members.get(row);

        switch (col) {
            case MID:
            	return tmpMember.MID;
            case NAME:
            	return tmpMember.NAME;
            case DOB:
            	return tmpMember.DOB;
            case ADDR: 
            	return tmpMember.ADDR;
            case GENDER:
            	return tmpMember.GENDER;
            case CONTACT:
            	return tmpMember.CONTACT;
            case PASSWORD:
            	return tmpMember.PASSWORD;
            case REGISTDATE:
            	return tmpMember.REGISTDATE;
            case SICKDATE:
            	return tmpMember.SICKDATE;
            case DIAGNOSES:
            	return tmpMember.DIAGNOSES;
            default:
                return "烫";
        }
    }

    public Class getColumnClass(int c) {
        return "".getClass();
    }

}
