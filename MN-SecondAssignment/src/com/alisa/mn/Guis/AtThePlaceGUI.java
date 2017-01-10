package com.alisa.mn.Guis;

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
import java.sql.Time;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.alisa.mn.db.AddRow;
import com.alisa.mn.db.AtThePlaceDB;
import com.alisa.mn.db.DeleteRow;
import com.alisa.mn.db.UpdateRow;

public class AtThePlaceGUI extends Property {
	
	@Override
	String[] getColumNames() {
		return new String[]{"LecturerID","CourseID","ClassNumber","Date","Time"};
	}
	
    public AtThePlaceGUI() {
        super();
 
    }
    
    @Override
    protected JButton createDeleteButton(){
    	JButton button=new JButton("Delete row");
    	
    	button.addActionListener(new ActionListener() {
    		
    		@Override
    		public void actionPerformed(ActionEvent e) {

    			int rowNum=_table.getSelectedRow();
    			if(rowNum<0)
    				return ;
    			
    			TableModel model = _table.getModel();
    			
    			try {
					new AtThePlaceDB(model,rowNum).delete();
					loadData();
				} catch(Exception ex){
					handleError(ex);
				}
    		   }
    	});
    	return button;

    }
    
    protected void openNew() {
		
    	JDialog DialogAddText= new JDialog(getFrame(AtThePlaceGUI.this),"Add a new row.");
		DialogAddText.getContentPane().setLayout(new BoxLayout(DialogAddText.getContentPane(), BoxLayout.Y_AXIS));
		
		DialogAddText.getContentPane().add(new JLabel("Please enter Lecturer ID:"));
		JTextField txtLecturerId=new JTextField();
		//txtId.setEnabled(false);
		DialogAddText.getContentPane().add(txtLecturerId);
		DialogAddText.getContentPane().add(new JLabel("Please enter Course ID:"));
		JTextField txtCourseId =new JTextField();
		DialogAddText.getContentPane().add(txtCourseId);
		DialogAddText.getContentPane().add(new JLabel("Please enter Class Number:"));
		JTextField txtClassNum =new JTextField();
		DialogAddText.getContentPane().add(txtClassNum);
		DialogAddText.getContentPane().add(new JLabel("Please enter Date:"));
		JTextField txtDate =new JTextField();
		DialogAddText.getContentPane().add(txtDate);
		DialogAddText.getContentPane().add(new JLabel("Please enter Time:"));
		JTextField txtTime =new JTextField();
		DialogAddText.getContentPane().add(txtTime);
		
		JButton submitbut=new JButton("Submit");
		DialogAddText.getContentPane().add(submitbut);
	    submitbut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					AtThePlaceDB newData = new AtThePlaceDB();
					newData.lectureId=Integer.parseInt(txtLecturerId.getText());
					newData.courseId=Integer.parseInt(txtCourseId.getText());
					newData.classNumber=Integer.parseInt(txtClassNum.getText());
					newData.date=txtDate.getText();
					newData.time=txtTime.getText();

					newData.insert();
					
					//refresh table
					loadData();
				} catch (Exception ex) {
					handleError(ex);
				}
				
			}
		});
		
		DialogAddText.setSize(600, 200);
		DialogAddText.pack();
		DialogAddText.setVisible(true);
		
	}

    protected void openUpdate(int rowId) {

    	
		AtThePlaceDB oldData = new AtThePlaceDB(_table.getModel(),rowId);

    	JDialog DialogAddText= new JDialog(getFrame(AtThePlaceGUI.this),"Add a new row.");
		DialogAddText.getContentPane().setLayout(new BoxLayout(DialogAddText.getContentPane(), BoxLayout.Y_AXIS));
		
		DialogAddText.getContentPane().add(new JLabel("Please enter Lecturer ID:"));
		JTextField txtLecturerId=new JTextField();
		//txtId.setEnabled(false);
		DialogAddText.getContentPane().add(txtLecturerId);
		DialogAddText.getContentPane().add(new JLabel("Please enter Course ID:"));
		JTextField txtCourseId =new JTextField();
		DialogAddText.getContentPane().add(txtCourseId);
		DialogAddText.getContentPane().add(new JLabel("Please enter Class Number:"));
		JTextField txtClassNum =new JTextField();
		DialogAddText.getContentPane().add(txtClassNum);
		DialogAddText.getContentPane().add(new JLabel("Please enter Date:"));
		JTextField txtDate =new JTextField();
		DialogAddText.getContentPane().add(txtDate);
		DialogAddText.getContentPane().add(new JLabel("Please enter Time:"));
		JTextField txtTime =new JTextField();
		DialogAddText.getContentPane().add(txtTime);
		
		JButton submitbut=new JButton("Submit");
		DialogAddText.getContentPane().add(submitbut);
	    submitbut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				try {

					AtThePlaceDB newData = new AtThePlaceDB();
					newData.lectureId=Integer.parseInt(txtLecturerId.getText());
					newData.courseId=Integer.parseInt(txtCourseId.getText());
					newData.classNumber=Integer.parseInt(txtClassNum.getText());
					newData.date=txtDate.getText();
					newData.time=txtTime.getText();
					
					oldData.delete();
					newData.insert();
				} catch (Exception ex) {
					handleError(ex);
				}
				/*
				String[] input=
					{"AtThePlace"
							,txtLecturerId+""
							,txtCourseId+""
							,txtClassNum+""
							,txtDate+""
							,txtTime+""};
				AddRow.main(input);
				
				//close window , from http://stackoverflow.com/questions/1234912/how-to-programmatically-close-a-jframe
				DialogAddText.dispatchEvent(new WindowEvent(DialogAddText, WindowEvent.WINDOW_CLOSING));
				*/
				//refresh table
				loadData();
			}
		});
		
		DialogAddText.setSize(600, 200);
		DialogAddText.pack();
		DialogAddText.setVisible(true);
		
	}

    @Override
	protected String getQueryString() {
		return "select * from AtThePlace";
	}

	@Override
	protected Object[] readRow(ResultSet res) throws SQLException {
		int LecturerID=res.getInt("LecturerID");
		  int CourseID=res.getInt("CourseID");
		  int ClassNumber=res.getInt("ClassNumber");
		  Date date=res.getDate("Date");
		  Time time=res.getTime("Time");
		  
		  return new Object[]  {LecturerID,CourseID,ClassNumber,date,time};
	}

	public static void main(String[] args) {
    //Schedule a job for the event-dispatching thread:
    //creating and showing this application's GUI.
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            createAndShowGUI(new AtThePlaceGUI());
        }
    });
}

	@Override
	protected void doDelete(int id) {
		// TODO Auto-generated method stub
		
	}

}
