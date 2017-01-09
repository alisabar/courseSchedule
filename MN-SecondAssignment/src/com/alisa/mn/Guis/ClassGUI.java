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

public class ClassGUI extends Property {
	
/**
 * Create the GUI and show it.  For thread safety,
 * this method should be invoked from the
 * event-dispatching thread.
 */

public static void main(String[] args) {
    //Schedule a job for the event-dispatching thread:
    //creating and showing this application's GUI.
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            createAndShowGUI(new ClassGUI());
        }
    });
}

@Override
String[] getColumNames() {
	return new String[]{"ClassNumber","Building"};
}

@Override
protected String getQueryString() {
	return "select * from Class";
}

@Override
protected Object[] readRow(ResultSet res) throws SQLException {
	int ClassNumber=res.getInt("ClassNumber");
	  String Building=res.getString("Building");
	  return new Object[] {ClassNumber,Building};	 
}

@Override
protected void doDelete(int id) {
	String[] input={"Class",id+""};
	DeleteRow.main(input);
}

@Override
void openNew() {
		
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
				String[] input={"Class",txtId.getText(),txtName.getText()};
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
}


