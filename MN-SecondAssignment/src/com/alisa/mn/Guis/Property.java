package com.alisa.mn.Guis;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public abstract class Property extends JPanel {
public Property() {
		super(new GridLayout(1,0));
		
		Object[][] data = getData();
        String[] columnNames = getColumNames();
        
        _table = new JTable(data, columnNames);
        _table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        _table.setFillsViewportHeight(true);
  
        JScrollPane scrollPane = new JScrollPane(_table);
        
           final JPanel topPanel=new JPanel();
        	    topPanel.add(createAddButton());
        	    topPanel.add(createDeleteButton());
        	    topPanel.add(createUpdateButton());
            setLayout(new BorderLayout());
            add(topPanel,BorderLayout.NORTH);    
            //Add the scroll pane to this panel.
            add(scrollPane,BorderLayout.CENTER);
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

protected static String driver = "com.mysql.jdbc.Driver";
protected static String protocol = "jdbc:mysql://localhost:3306/schedule?user=root&password=123456";

protected Statement getStatement() throws ClassNotFoundException, SQLException
{

	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
	
		 Class.forName(driver);
		 connection = DriverManager.getConnection(protocol);
		 statement = connection.createStatement();
		 return statement;
}

/**
 * Create the GUI and show it.  For thread safety,
 * this method should be invoked from the
 * event-dispatching thread.
 */
protected static void createAndShowGUI(Property newContentPane) {
    //Create and set up the window.
    JFrame frame = new JFrame("SimpleTableDemo");
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    //Create and set up the content pane.
    //AtThePlace newContentPane = new AtThePlace();
    newContentPane.setOpaque(true); //content panes must be opaque
    frame.setContentPane(newContentPane);

    //Display the window.
    frame.pack();
    frame.setVisible(true);
}

protected  void printDebugData(JTable table) {
    int numRows = table.getRowCount();
    int numCols = table.getColumnCount();
    javax.swing.table.TableModel model = table.getModel();

    System.out.println("Value of data: ");
    for (int i=0; i < numRows; i++) {
        System.out.print("    row " + i + ":");
        for (int j=0; j < numCols; j++) {
            System.out.print("  " + model.getValueAt(i, j));
        }
        System.out.println();
    }
    System.out.println("--------------------------");
}

protected JTable _table;

protected void loadData(){
	 Object[][] data = getData();
    String[] columnNames = getColumNames(); 
    _table.setModel(new DefaultTableModel(data,columnNames));
}

abstract String[] getColumNames();

protected Object[][] getData() {
	Object [][] table;
    ArrayList<Object[]> rows=new ArrayList<Object[]>();
    Statement statement=null;
	try {
		statement = getStatement();
		ResultSet res = statement.executeQuery(getQueryString());
		  while (res.next()) {
			  rows.add(readRow(res));
		  }
		  int size=rows.size();
		  table=new Object[size][5];
		  for(int i=0; i<size;i++)
		  {
			  table[i]=rows.get(i);
			  
		  }
		  return table;
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		try {
			if(statement!=null)
			{
			statement.getConnection().close();
			statement.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
    
		return null;
	}

protected abstract String getQueryString();

protected abstract Object[] readRow(ResultSet res) throws SQLException ;

private JButton createDeleteButton() {
	
	JButton button=new JButton("Delete row");
	
	button.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int id=giveMeSelectedId(_table);
			if(id>0)
			{
			doDelete(id);
			loadData();
			}
		}

		
	});
	return button;
}

protected abstract void doDelete(int id);

protected JButton createAddButton() {
	
	JButton button=new JButton("Add a new row.");
	
	button.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			openNew();
		}
		});
	
	return button;
}

abstract void openNew();

protected JButton createUpdateButton() {
	
	JButton button=new JButton("Update a row.");
	
	button.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int id=giveMeSelectedId(_table);
			if(id>0)
			openUpdate(id);
			
		}
});
	
return button;
}

protected abstract void openUpdate(int rowId);


}
