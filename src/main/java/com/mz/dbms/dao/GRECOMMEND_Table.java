package com.mz.dbms.dao;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class GRECOMMEND_Table extends AbstractTableModel {
	
	private static final int DID = 0;
    private static final int INDICATOR = 1;
    private static final int INFORMATION = 2;
    private static final int FREQ = 3;
    
    private String[] columnNames = {"DID", "INDICATOR", "INFORMATION", "FREQ"};
    
    private List<GRECOMMEND> grecommends;
    

    public GRECOMMEND_Table(List<GRECOMMEND> mrecommend) {
        this.grecommends = new LinkedList<GRECOMMEND>(grecommends);
    }

    /**
     * @Override
     * @return
     */

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return grecommends.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
    	GRECOMMEND tmpGrecommend = grecommends.get(row);

        switch (col) {
            case DID:
            	return tmpGrecommend.DID;
            case INDICATOR:
            	return tmpGrecommend.INDICATOR;
            case INFORMATION:
            	return tmpGrecommend.INFORMATION;
            case FREQ: 
            	return tmpGrecommend.FREQ;
            default:
                return "烫";
        }
    }

    public Class getColumnClass(int c) {
        return "".getClass();
    }

}

