package com.alisa.mn.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateRow {
	public static String driver = "com.mysql.jdbc.Driver";
	
	public static String protocol = "jdbc:mysql://localhost:3306/schedule?user=root&password=123456";
		
	
	public static void updateRow(Statement statement, String[] args)
	{
		if(args[0].equals("Course"))
		{
		  
			 
			try {
				String s="UPDATE Course "
				           + " SET"+" Name="+"'"+args[1]+"'"+
						" where"+" CourseID="+args[2];
				statement.execute(s);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(args[0].equals("Lecturer"))
		{
			 
		 
			try {
				String s="UPDATE Lecturer "
				           + " SET "+" FullName ="+"'"+args[1]+"'"+","
				           + " PhoneNum ="+args[2]+","
				           + " Address ="+"'"+args[3]+"'"+","+
						" where "+" LecturerID="+args[4];
				statement.execute(s);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(args[0].equals("Class"))
		{
			 
		 
			try {
				String s="UPDATE Class"
						+" set "+" ClassNumber= "+"'"+args[1]+"'"+","
						+" Building= "+"'"+args[2]+"'";
				statement.execute(s);
				        
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(args[0].equals("Teaching"))
		{
			 
			try {
				String s="UPDATE Teaching "
				           + " SET"+" LecturerID "+args[1]+","
				           + " CourseID "+args[2]+","
				           + " Date "+"'"+args[3]+"'"+","+
						" where"+" LecturerID="+args[5];
				statement.execute(s);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(args[0].equals("AtThePlace"))
		{
		 
			try {
				statement.execute(" insert into AtThePlace (LecturerID, CourseID,ClassNumber,Date,Time)"
				        + " values ("+"655478965"+","+"03567"+","+"2101"+","+"'20161015'"+","+"'08:00:00'"+")");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
				 updateRow(statement, args);
		}
	
	
}
