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
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.alisa.mn.db.AddRow;
import com.alisa.mn.db.DeleteRow;
import com.alisa.mn.db.UpdateRow;

public class TeachingGui extends Property {
	
public static void main(String[] args) {
    //Schedule a job for the event-dispatching thread:
    //creating and showing this application's GUI.
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            createAndShowGUI(new TeachingGui());
        }
    });
}

@Override
String[] getColumNames() {
	return new String[]{"LecturerID","CourseID","Date"};
}

@Override
protected String getQueryString() {
	return "select * from Teaching";
}

protected int giveMeSelectedId(JTable table) {
	return table.getSelectedRow();
}

@Override
protected Object[] readRow(ResultSet res) throws SQLException {
	int LecturerID=res.getInt("LecturerID");
	int CourseID=res.getInt("CourseID");
	Date date=res.getDate("Date");
	return new Object[] {LecturerID,CourseID,date}; 
}

@Override
protected void doDelete(int rowId) {
	final Integer oldLecturerId=Integer.parseInt(_table.getModel().getValueAt(rowId, 0).toString());
	final Integer oldCourseId=Integer.parseInt(_table.getModel().getValueAt(rowId, 1).toString());
	final String oldDate=_table.getModel().getValueAt(rowId, 2).toString();
	
	String[] input={"Teaching",oldLecturerId.toString(),oldCourseId.toString(),oldDate};
	DeleteRow.main(input);
}

public static class ComboItem
{
	public ComboItem(int id , String text){
		this.id=id;
		this.text=text;
	}
	public int id;
	public String text;
	public String toString(){
		return text;
	}
}


@Override
void openNew() {
	
				
	JDialog DialogAddText= new JDialog(getFrame(TeachingGui.this),"Add a new course.");
	DialogAddText.getContentPane().setLayout(new BoxLayout(DialogAddText.getContentPane(), BoxLayout.Y_AXIS));
	
	DialogAddText.getContentPane().add(new JLabel("Please enter Lecturer ID:"));
	JTextField txtLecturerId=new JTextField();
	DialogAddText.getContentPane().add(txtLecturerId);
	
	DialogAddText.getContentPane().add(new JLabel("Select Course:"));
	JComboBox<ComboItem> combocourse = createCombo("Course","CourseId","Name");
	DialogAddText.getContentPane().add(combocourse);
	
	DialogAddText.getContentPane().add(new JLabel("Please enter Date:"));
	JTextField txtDate =new JTextField();
	DialogAddText.getContentPane().add(txtDate);
	
	JButton submitbut=new JButton("Submit");
	DialogAddText.getContentPane().add(submitbut);
    submitbut.addActionListener(new ActionListener() {
		
    	
    	
		@Override
		public void actionPerformed(ActionEvent e) {
			String[] input={"Teaching"
					,txtLecturerId.getText()
					,((ComboItem)combocourse.getSelectedItem()).id+""
					,txtDate.getText()
			};
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
	final Integer oldLecturerId=Integer.parseInt(_table.getModel().getValueAt(rowId, 0).toString());
	final Integer oldCourseId=Integer.parseInt(_table.getModel().getValueAt(rowId, 1).toString());
	final String oldDate=_table.getModel().getValueAt(rowId, 2).toString();
	
	JDialog DialogAddText= new JDialog(getFrame(TeachingGui.this),"Add a new course.");
	DialogAddText.getContentPane().setLayout(new BoxLayout(DialogAddText.getContentPane(), BoxLayout.Y_AXIS));
	
	DialogAddText.getContentPane().add(new JLabel("Please enter Lecturer ID:"));
	JTextField txtLecturerId=new JTextField();
	DialogAddText.getContentPane().add(txtLecturerId);
	
	DialogAddText.getContentPane().add(new JLabel("Select Course:"));
	JComboBox<ComboItem> combocourse = createCombo("Course","CourseId","Name");
	DialogAddText.getContentPane().add(combocourse);
	
	DialogAddText.getContentPane().add(new JLabel("Please enter Date:"));
	JTextField txtDate =new JTextField();
	DialogAddText.getContentPane().add(txtDate);
	
	JButton submitbut=new JButton("Submit");
	DialogAddText.getContentPane().add(submitbut);
    submitbut.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			//remove old value
			DeleteRow.main(new String[]{
			"Teaching"
			,oldLecturerId.toString()
			,oldCourseId.toString()
			,oldDate.toString()
			});
			
			//insert new table
			String[] input={"Teaching"
					,txtLecturerId.getText()
					,((ComboItem)combocourse.getSelectedItem()).id+""
					,txtDate.getText()
			};
			
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
}




