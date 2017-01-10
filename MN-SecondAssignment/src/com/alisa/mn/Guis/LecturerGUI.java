package com.alisa.mn.Guis;

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

import com.alisa.mn.AddRow;
import com.alisa.mn.DeleteRow;
import com.alisa.mn.UpdateRow;

public class LecturerGUI extends Property {
	
public static void main(String[] args) {
    //Schedule a job for the event-dispatching thread:
    //creating and showing this application's GUI.
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            createAndShowGUI(new LecturerGUI());
        }
    });
}

@Override
String[] getColumNames() {
	return new String[]{"LecturerID","FullName","PhoneNum","Address"};
}

@Override
protected String getQueryString() {
	return "select * from Lecturer";
}

@Override
protected Object[] readRow(ResultSet res) throws SQLException {
	int LecturerID=res.getInt("LecturerID");
	  String FullName=res.getString("FullName");
	  String PhoneNum=res.getString("PhoneNum");
	  String Address=res.getString("Address");
	  return new Object[] {LecturerID,FullName,PhoneNum,Address};
}

@Override
protected void doDelete(int id) {
	String[] input={"Lecturer",id+""};
	DeleteRow.main(input);	
}

@Override
void openNew() {
	JDialog DialogAddText= new JDialog(getFrame(LecturerGUI.this),"Add a new lecturer.");
	DialogAddText.getContentPane().setLayout(new BoxLayout(DialogAddText.getContentPane(), BoxLayout.Y_AXIS));
	
	DialogAddText.getContentPane().add(new JLabel("Please enter lecturer ID:"));
	JTextField txtId=new JTextField();
	DialogAddText.getContentPane().add(txtId);
	DialogAddText.getContentPane().add(new JLabel("Please enter lecturer full name:"));
	JTextField txtName =new JTextField();
	DialogAddText.getContentPane().add(txtName);
	DialogAddText.getContentPane().add(new JLabel("Please enter lecturer phone number:"));
	JTextField phone =new JTextField();
	DialogAddText.getContentPane().add(phone);
	DialogAddText.getContentPane().add(new JLabel("Please enter lecturer address:"));
	JTextField address =new JTextField();
	DialogAddText.getContentPane().add(address);
	
	JButton submitbut=new JButton("Submit");
	DialogAddText.getContentPane().add(submitbut);
    submitbut.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String[] input={"Lecturer",txtId+"",txtName.getText(),phone+"",address.getText()};
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

@Override
protected void openUpdate(int rowId) {
	JDialog DialogAddText= new JDialog(getFrame(this),"Adit lecturer");
	DialogAddText.getContentPane().setLayout(new BoxLayout(DialogAddText.getContentPane(), BoxLayout.Y_AXIS));
	
	
	DialogAddText.getContentPane().add(new JLabel("Please enter lecturer ID:"));
	JTextField txtId=new JTextField();
	txtId.setText(rowId+"");
	txtId.setEnabled(false);
	DialogAddText.getContentPane().add(txtId);
	DialogAddText.getContentPane().add(new JLabel("Please enter lecturer full name:"));
	JTextField txtName =new JTextField();
	DialogAddText.getContentPane().add(txtName);
	DialogAddText.getContentPane().add(new JLabel("Please enter lecturer phone number:"));
	JTextField phone =new JTextField();
	txtId.setText(phone+"");
	DialogAddText.getContentPane().add(phone);
	DialogAddText.getContentPane().add(new JLabel("Please enter lecturer address:"));
	JTextField address =new JTextField();
	DialogAddText.getContentPane().add(address);
	
	JButton submitbut=new JButton("Submit");
	DialogAddText.getContentPane().add(submitbut);
    submitbut.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String[] input={"Lecturer",rowId+"",txtName.getText(),phone+"",address.getText()};
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
}

