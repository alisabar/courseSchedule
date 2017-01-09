package com.alisa.mn;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ClassGUI extends property {
	public static String driver = "com.mysql.jdbc.Driver";
    public static String protocol = "jdbc:mysql://localhost:3306/schedule?user=root&password=123456";
	
	 private Statement getStatement() throws ClassNotFoundException, SQLException
		{

			Connection connection = null;
			Statement statement = null;
			ResultSet rs = null;
			
				 Class.forName(driver);
				 connection = DriverManager.getConnection(protocol);
				 statement = connection.createStatement();
				 return statement;
		}
	 final JTable _table;
	 protected void loadData(){
		 Object[][] data = getData();
	     String[] columnNames =  {"ClassNumber","Building"};  
	     _table.setModel(new DefaultTableModel(data,columnNames));
	 }
	
    public ClassGUI() {
        super(new GridLayout(1,0));
 
        
        Object[][] data = getData();
        String[] columnNames = {"ClassNumber","Building"};
        
        
        _table = new JTable(data, columnNames);
        _table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        _table.setFillsViewportHeight(true);
 

    //Create the scroll pane and add the table to it.
    JScrollPane scrollPane = new JScrollPane(_table);


    
    final JPanel topPanel=new JPanel();
	    topPanel.add(createAddButton());
	    topPanel.add(createDeleteButton());
	    topPanel.add(createUpdateButton());
    setLayout(new BorderLayout());
    add(topPanel,BorderLayout.NORTH);    
    //Add the scroll pane to this panel.
    add(scrollPane,BorderLayout.CENTER);
    
    //Add the scroll pane to this panel.
    add(scrollPane);
}

private JButton createDeleteButton() {
		
		JButton button=new JButton("Delete row");
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int id=giveMeSelectedId(_table);
				if(id>0)
				{
				String[] input={"Class",id+""};
				DeleteRow.main(input);
				loadData();
				}
			}
			
		
	});
		return button;
}

private JButton createAddButton() {
		
		JButton button=new JButton("Add a new row.");
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openNewCoursePopup();
			}

			private void openNewCoursePopup() {
				
				JDialog DialogAddText= new JDialog(getFrame(ClassGUI.this),"Add a new class.");
				DialogAddText.getContentPane().setLayout(new BoxLayout(DialogAddText.getContentPane(), BoxLayout.Y_AXIS));
	
				DialogAddText.getContentPane().add(new JLabel("Please enter Class Number:"));
				JTextField txtId=new JTextField();
				//txtId.setEnabled(false);
				DialogAddText.getContentPane().add(txtId);
				DialogAddText.getContentPane().add(new JLabel("Please enter Building name:"));
				JTextField txtName =new JTextField();
				DialogAddText.getContentPane().add(txtName);
				
				JButton submitbut=new JButton("Submit");
				DialogAddText.getContentPane().add(submitbut);
			    submitbut.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String[] input={"Class",txtId+"",txtName.getText()};
						AddRow.main(input);
						
						//close window , from http://stackoverflow.com/questions/1234912/how-to-programmatically-close-a-jframe
						DialogAddText.dispatchEvent(new WindowEvent(DialogAddText, WindowEvent.WINDOW_CLOSING));
						
						//refresh table
						loadData();
					}
				});
				
				DialogAddText.setSize(600, 200);
				DialogAddText.pack();
				DialogAddText.setVisible(true);
				
			}
		});
		
		return button;
}
	

	
	   
private JButton createUpdateButton() {
			
			JButton button=new JButton("Update a row.");
			
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int id=giveMeSelectedId(_table);
					if(id>0)
					openNewCoursePopup(id);
					
				}

				

				private void openNewCoursePopup(int rowId) {
					
					JDialog DialogAddText= new JDialog(getFrame(ClassGUI.this),"Adit course.");
					DialogAddText.getContentPane().setLayout(new BoxLayout(DialogAddText.getContentPane(), BoxLayout.Y_AXIS));
					DialogAddText.getContentPane().add(new JLabel("Please enter Class Number:"));
					JTextField txtId=new JTextField();
					txtId.setText(rowId+"");
					txtId.setEnabled(false);
					DialogAddText.getContentPane().add(txtId);
					DialogAddText.getContentPane().add(new JLabel("Please enter Building name:"));
					JTextField txtName =new JTextField();
					DialogAddText.getContentPane().add(txtName);
		
					
					JButton submitbut=new JButton("Submit");
					DialogAddText.getContentPane().add(submitbut);
				    submitbut.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							String[] input={"Class",txtId+"",txtName.getText()};
							UpdateRow.main(input);
							
							//close window , from http://stackoverflow.com/questions/1234912/how-to-programmatically-close-a-jframe
							DialogAddText.dispatchEvent(new WindowEvent(DialogAddText, WindowEvent.WINDOW_CLOSING));
							
							//refresh table
							loadData();
						}
					});
					
					DialogAddText.setSize(600, 200);
					DialogAddText.pack();
					DialogAddText.setVisible(true);
					
				}
			});
			
			return button;
}
private Object[][] getData() {
	Object [][] table;
    ArrayList<Object[]> arr=new ArrayList<Object[]>();
    Statement statement=null;
	try {
		statement = getStatement();
		ResultSet res = statement.executeQuery("select * from Class");
		  while (res.next()) {
		  int ClassNumber=res.getInt("ClassNumber");
		  String Building=res.getString("Building");
		  arr.add(new Object[] {ClassNumber,Building});
		 
		  }
		  int size=arr.size();
		  table=new Object[size][2];
		  for(int i=0; i<size;i++)
		  {
			  table[i]=arr.get(i);
			  
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


private void printDebugData(JTable table) {
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
/**
 * Create the GUI and show it.  For thread safety,
 * this method should be invoked from the
 * event-dispatching thread.
 */
private static void createAndShowGUI() {
    //Create and set up the window.
    JFrame frame = new JFrame("SimpleTableDemo");
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    //Create and set up the content pane.
    ClassGUI newContentPane = new ClassGUI();
    newContentPane.setOpaque(true); //content panes must be opaque
    frame.setContentPane(newContentPane);

    //Display the window.
    frame.pack();
    frame.setVisible(true);
}

public static void main(String[] args) {
    //Schedule a job for the event-dispatching thread:
    //creating and showing this application's GUI.
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            createAndShowGUI();
        }
    });
}
}

