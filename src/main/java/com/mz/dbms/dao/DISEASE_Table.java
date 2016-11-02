package com.mz.dbms.dao;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class DISEASE_Table extends AbstractTableModel {
	private static final int DID = 0;
    private static final int DNAME = 1;
    private static final int DESCRIPT = 2;
    private static final int ATIME = 3;
    
private String[] columnNames = {"DID", "DNAME", "DESCRIPT"};
    
    private List<DISEASE> diseases;
    

    public DISEASE_Table(List<DISEASE> diseases) {
        this.diseases = new LinkedList<DISEASE>(diseases);
    }

    /**
     * @Override
     * @return
     */

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return diseases.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
    	DISEASE tmpDisease = diseases.get(row);

        switch (col) {
            case DID:
            	return tmpDisease.DID;
            case DNAME:
            	return tmpDisease.DNAME;
            case DESCRIPT: 
            	return tmpDisease.DESCRIPT;
            default:
                return "烫";
        }
    }

    public Class getColumnClass(int c) {
        return "".getClass();
    }

}
