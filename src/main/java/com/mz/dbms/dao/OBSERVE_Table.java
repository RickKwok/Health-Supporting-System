package com.mz.dbms.dao;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class OBSERVE_Table extends AbstractTableModel {
	
	private static final int OID = 0;
    private static final int MID = 1;
    private static final int INDICATOR = 2;
    private static final int VALUE = 3;
    private static final int OBSERVEDATE = 4;
    private static final int REPORTDATE = 5;
    
    
    private String[] columnNames = {"OID", "MID", "INDICATOR", "VALUE", "OBSERVEDATE", "REPORTDATE"};
    
    private List<OBSERVE> observes;
    

    public OBSERVE_Table(List<OBSERVE> observes) {
        this.observes = new LinkedList<OBSERVE>(observes);
    }

    /**
     * @Override
     * @return
     */

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return observes.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
    	OBSERVE tmpObserve = observes.get(row);

        switch (col) {
            case OID:
            	return tmpObserve.OID;
            case MID:
            	return tmpObserve.MID;
            case INDICATOR:
            	return tmpObserve.INDICATOR;
            case VALUE: 
            	return tmpObserve.VALUE;
            case OBSERVEDATE:
            	return tmpObserve.OBSERVEDATE;
            case REPORTDATE:
            	return tmpObserve.REPORTDATE;
            default:
                return "烫";
        }
    }

    public Class getColumnClass(int c) {
        return "".getClass();
    }

}

