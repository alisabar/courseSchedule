package com.alisa.mn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddRow{
public static String driver = "com.mysql.jdbc.Driver";
	
public static String protocol = "jdbc:mysql://localhost:3306/schedule?user=root&password=123456";
	
public static void addRow(Statement statement, String[] args)
{
	if(args[0].equals("Course"))
	{
	 
		try {
			String s="INSERT INTO Course (Name)"
			           + " values"+"("+"'"+args[1]+"'"+")";
			statement.executeUpdate(s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	else if(args[0].equals("Lecturer"))
	{
		 
	 
		try {
			statement.executeUpdate(" insert into Lecturer (LecturerID, FullName,PhoneNum,Address)"
					+ " values"+ "("+args[1]+","+"'"+args[2]+"'"+","+args[3]+","+"'"+args[4]+"'"+")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	else if(args[0].equals("Class"))
	{
		 
	 
		try {
			statement.executeUpdate("insert into Class (ClassNumber, Building)"
			           + " values"+"("+args[1]+","+"'"+args[2]+"'"+")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	else if(args[0].equals("Teaching"))
	{
		 
	 
		try {
			statement.executeUpdate(" insert into Teaching (LecturerID, CourseID,  Date)"
			        + " values"+"("+args[1]+","+args[2]+","+"'"+args[3]+"'"+")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	if(args[0].equals("AtThePlace"))
	{
	
		 
	 
		try {
			statement.executeUpdate(" insert into AtThePlace (LecturerID, CourseID,ClassNumber,Date,Time)"
			        + " values ("+args[1]+","+args[2]+","+args[3]+","+"'"+args[4]+"'"+","+"'"+args[5]+"'"+")");
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
			addRow(statement, args);
	}
}
