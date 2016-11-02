package com.mz.dbms.dao;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ASSIGN_Table extends AbstractTableModel {
	
    private static final int PID = 0;
    private static final int SID = 1;
    private static final int ISPRIMARY = 2;
    private static final int FROMDATE = 3;
    private static final int TODATE = 4;
    
    
    
    private String[] columnNames = {"PID", "SID", "ISPRIMARY", "FROMDATE", "TODATE"};
    
    private List<ASSIGN> assigns;
    

    public ASSIGN_Table(List<ASSIGN> assigns) {
        this.assigns = new LinkedList<ASSIGN>(assigns);
    }

    /**
     * @Override
     * @return
     */

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return assigns.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
    	ASSIGN tmpAssign = assigns.get(row);

        switch (col) {
            case PID:
            	return tmpAssign.PID;
            case SID:
            	return tmpAssign.SID;
            case ISPRIMARY: 
            	return tmpAssign.ISPRIMARY;
            case FROMDATE:
            	return tmpAssign.FROMDATE;
            case TODATE:
            	return tmpAssign.TODATE;
            default:
                return "烫";
        }
    }

    public Class getColumnClass(int c) {
        return "".getClass();
    }
}
