package com.mz.dbms.dao;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class MRECOMMEND_Table extends AbstractTableModel {
	
	private static final int MID = 0;
    private static final int INDICATOR = 1;
    private static final int INFORMATION = 2;
    private static final int FREQ = 3;
    
    private String[] columnNames = {"MID", "INDICATOR", "INFORMATION", "FREQ"};
    
    private List<MRECOMMEND> mrecommends;
    

    public MRECOMMEND_Table(List<MRECOMMEND> mrecommends) {
        this.mrecommends = new LinkedList<MRECOMMEND>(mrecommends);
    }

    /**
     * @Override
     * @return
     */

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return mrecommends.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
    	MRECOMMEND tmpMrecommend = mrecommends.get(row);

        switch (col) {
            case MID:
            	return tmpMrecommend.MID;
            case INDICATOR:
            	return tmpMrecommend.INDICATOR;
            case INFORMATION:
            	return tmpMrecommend.INFORMATION;
            case FREQ: 
            	return tmpMrecommend.FREQ;
            default:
                return "烫";
        }
    }

    public Class getColumnClass(int c) {
        return "".getClass();
    }

}

