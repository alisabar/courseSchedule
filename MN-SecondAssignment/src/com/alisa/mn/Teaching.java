package com.alisa.mn;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.Date;
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

public class Teaching extends property {
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
	 protected void loadData(){
		 Object[][] data = getData();
	     String[] columnNames = {"LecturerID","CourseID","Date"}; 
	     _table.setModel(new DefaultTableModel(data,columnNames));
	 }
	 
	 final JTable _table;
	 
    public Teaching() {
        super(new GridLayout(1,0));
 
        
        Object[][] data = getData();
        String[] columnNames = {"LecturerID","CourseID","Date"};
        
        
        final JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);

            
            
            final JPanel topPanel=new JPanel();
        	    topPanel.add(createAddButton());
        	    topPanel.add(createDeleteButton());
        	    topPanel.add(createUpdateButton());
            setLayout(new BorderLayout());
            add(topPanel,BorderLayout.NORTH);    
            //Add the scroll pane to this panel.
            add(scrollPane,BorderLayout.CENTER);
          
}

private JButton createDeleteButton() {
		
		JButton button=new JButton("Delete row");
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int id=giveMeSelectedId(_table);
				if(id>0)
				{
				String[] input={"Teaching",id+""};
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
				
				JDialog DialogAddText= new JDialog(getFrame(Teaching.this),"Add a new course.");
				DialogAddText.getContentPane().setLayout(new BoxLayout(DialogAddText.getContentPane(), BoxLayout.Y_AXIS));
				
				DialogAddText.getContentPane().add(new JLabel("Please enter course ID:"));
				JTextField txtId=new JTextField();
				//txtId.setEnabled(false);
				DialogAddText.getContentPane().add(txtId);
				DialogAddText.getContentPane().add(new JLabel("Please enter course name:"));
				JTextField txtName =new JTextField();
				DialogAddText.getContentPane().add(txtName);
				
				JButton submitbut=new JButton("Submit");
				DialogAddText.getContentPane().add(submitbut);
			    submitbut.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String[] input={"Course",txtName.getText()};
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
					
					JDialog DialogAddText= new JDialog(getFrame(CourseGUI.this),"Adit course.");
					DialogAddText.getContentPane().setLayout(new BoxLayout(DialogAddText.getContentPane(), BoxLayout.Y_AXIS));
					
					DialogAddText.getContentPane().add(new JLabel("Course ID:"));
					JTextField txtId=new JTextField();
					txtId.setText(rowId+"");
					txtId.setEnabled(false);
					DialogAddText.getContentPane().add(txtId);
					DialogAddText.getContentPane().add(new JLabel("Please edit course name:"));
					JTextField txtName =new JTextField();
					DialogAddText.getContentPane().add(txtName);
					
					JButton submitbut=new JButton("Submit");
					DialogAddText.getContentPane().add(submitbut);
				    submitbut.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							String[] input={"Course",txtName.getText(),rowId+""};
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
		ResultSet res = statement.executeQuery("select * from Teaching");
		  while (res.next()) {
		  int LecturerID=res.getInt("LecturerID");
		  int CourseID=res.getInt("CourseID");
		  Date date=res.getDate("Date");
		  arr.add(new Object[] {LecturerID,CourseID,date});
		 
		  }
		  int size=arr.size();
		  table=new Object[size][3];
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
    Teaching newContentPane = new Teaching();
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




