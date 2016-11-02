package com.mz.dbms.dao;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ALERT_Table extends AbstractTableModel {
	
    private static final int MID = 0;
    private static final int INDICATOR = 1;
    private static final int VALUE = 2;
    private static final int ATIME = 3;
    
    
    private String[] columnNames = {"MID", "INDICATOR", "VALUE", "ATIME"};
    
    private List<ALERT> alerts;
    

    public ALERT_Table(List<ALERT> alerts) {
        this.alerts = new LinkedList<ALERT>(alerts);
    }

    /**
     * @Override
     * @return
     */

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return alerts.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
    	ALERT tmpAlert = alerts.get(row);

        switch (col) {
            case MID:
            	return tmpAlert.MID;
            case INDICATOR:
            	return tmpAlert.INDICATOR;
            case VALUE: 
            	return tmpAlert.VALUE;
            case ATIME:
            	return tmpAlert.ATIME;
            default:
                return "烫";
        }
    }

    public Class getColumnClass(int c) {
        return "".getClass();
    }

}

