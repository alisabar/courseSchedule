package com.alisa.mn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteRow {
public static String driver = "com.mysql.jdbc.Driver";
	
	public static String protocol = "jdbc:mysql://localhost:3306/schedule?user=root&password=123456";
		
	
	public static void deleteRow(Statement statement, String[] args)
	{
		if(args[0].equals("Course"))
		{
			DeleteCourse(statement, args);
		}
		else if(args[0].equals("Lecturer"))
		{
			DeleteLecturer(statement, args);
		}
		else if(args[0].equals("Class"))
		{
			deleteClass(statement, args);
		}
		else if(args[0].equals("Teaching")){
			deleteTeaching(statement, args);
		}
		else if(args[0].equals("AtThePlace")){
				deleteAtThePlace(statement, args); 
		}
		
	}
	protected static void deleteClass(Statement statement, String[] args) {
		try {
			String s="delete from Class "+
			           
					"where "+"ClassNumber="+args[1];
			statement.executeUpdate(s);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected static void deleteTeaching(Statement statement, String[] args) {
		try {
			String s="delete from Teaching "+
			           
					"where "+"LecturerID="+args[1]
						+	" and "+"CourseID="+args[2]
								+	" and "+"Date="+"'"+args[3]+"'";
			statement.executeUpdate(s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected static void deleteAtThePlace(Statement statement, String[] args) {
		try {
			String s="delete from AtThePlace "+
			           
					"where "+"LecturerID="+args[1]
						+	" and "+"CourseID="+args[2]
								+	" and "+"ClassNumber="+args[3]
								+	" and "+"Date="+"'"+args[4]+"'"
			   						+	" and "+"Time="+"'"+args[5]+"'";
			statement.executeUpdate(s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected static void DeleteLecturer(Statement statement, String[] args) {
		try {
			String s="delete from Lecturer "+
			           
					"where "+"LecturerID="+args[1];
			statement.executeUpdate(s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected static void DeleteCourse(Statement statement, String[] args) {
		try {
			String s="Delete from Course "+
			           
					" where "+" CourseID="+args[1];
			statement.executeUpdate(s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args)
		{
			Connection connection = null;
			Statement statement = null;
		
			
			
				 connection = null;
				 try {
					Class.forName(driver);
					 connection = DriverManager.getConnection(protocol);
					 statement = connection.createStatement();
				 } catch(Exception ex)
						{
							ex.printStackTrace();
						}
				 deleteRow(statement, args);
		}
	
}
