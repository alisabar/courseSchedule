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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.alisa.mn.AddRow;
import com.alisa.mn.DeleteRow;
import com.alisa.mn.UpdateRow;

public class AtThePlaceGUI extends Property {
	
	@Override
	String[] getColumNames() {
		return new String[]{"LecturerID","CourseID","ClassNumber","Date","Time"};
	}
	
    public AtThePlaceGUI() {
        super();
 
}

    protected void doDelete(int id) {
		String[] input={"AtThePlace",id+""};
		DeleteRow.main(input);
	}
    
    protected void openNew() {
		
		JDialog DialogAddText= new JDialog(getFrame(AtThePlaceGUI.this),"Add a new row.");
		DialogAddText.getContentPane().setLayout(new BoxLayout(DialogAddText.getContentPane(), BoxLayout.Y_AXIS));
		
		DialogAddText.getContentPane().add(new JLabel("Please enter Lecturer ID:"));
		JTextField txtId=new JTextField();
		//txtId.setEnabled(false);
		DialogAddText.getContentPane().add(txtId);
		DialogAddText.getContentPane().add(new JLabel("Please enter Course ID:"));
		JTextField courseId =new JTextField();
		DialogAddText.getContentPane().add(courseId);
		DialogAddText.getContentPane().add(new JLabel("Please enter Class Number:"));
		JTextField classNum =new JTextField();
		DialogAddText.getContentPane().add(classNum);
		DialogAddText.getContentPane().add(new JLabel("Please enter Date:"));
		JTextField date =new JTextField();
		DialogAddText.getContentPane().add(date);
		DialogAddText.getContentPane().add(new JLabel("Please enter Time:"));
		JTextField time =new JTextField();
		DialogAddText.getContentPane().add(time);
		
		JButton submitbut=new JButton("Submit");
		DialogAddText.getContentPane().add(submitbut);
	    submitbut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] input={"AtThePlace",txtId+"",courseId+"",classNum+"",date+"",time+""};
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

    protected void openUpdate(int rowId) {
		
		JDialog DialogAddText= new JDialog(getFrame(AtThePlaceGUI.this),"Adit course.");
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

}
