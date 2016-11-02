package com.mz.dbms.dao;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class MemberDiagnoses_Table {
	private static final int MID = 0;
	private static final int DIAGNOSES = 1;
	
private String[] columnNames = {"MID", "DIAGNOSES"};
    
    
    private List<MemberDiagnoses> members;
    

    
    public MemberDiagnoses_Table(List<MemberDiagnoses> members) {
        this.members = new LinkedList<MemberDiagnoses>(members);
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
        MemberDiagnoses tmpMember = members.get(row);

        switch (col) {
            case MID:
            	return tmpMember.MID;
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

