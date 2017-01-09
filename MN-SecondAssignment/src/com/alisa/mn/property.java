package com.alisa.mn;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

public class property extends JPanel {
public property(GridLayout gridLayout) {
		super(gridLayout);// TODO Auto-generated constructor stub
	}

protected static JFrame getFrame(Component component){
    	
    	if(component==null) {
    		return null;
    	}
    	if(component instanceof JFrame ){
    		return (JFrame)component; 
    	}
    	
    	return getFrame(component.getParent());
  }
protected int giveMeSelectedId(JTable _table) {
	
	int rowNum=_table.getSelectedRow();
	if(rowNum<0)
		return 0;
	return (int)_table.getModel().getValueAt(rowNum, 0);
		
}
}
