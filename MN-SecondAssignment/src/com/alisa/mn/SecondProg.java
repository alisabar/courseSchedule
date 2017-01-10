package com.alisa.mn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SecondProg {
/*
	public static String driver = "org.apache.derby.jdbc.ClientDriver";
	 public static String protocol = "jdbc:derby://localhost:1527/gagamoDB;create=true";
*/
	
	public static String driver = "com.mysql.jdbc.Driver";
	 public static String protocol = "jdbc:mysql://localhost:3306/schedule?user=root&password=123456";

	 public static void main(String[] args)
	 {
			Connection connection = null;
			Statement statement = null;
			ResultSet rs = null;
			
			try
			 {
				 connection = null;
				 Class.forName(driver);
				 connection = DriverManager.getConnection(protocol);
				 statement = connection.createStatement();
				
				 try {statement.execute("Drop table Teaching");
					} catch (Exception e) {
						System.out.println("could not remove Teaching "+e.getMessage());
					}
				 
				 try {statement.execute("Drop table AtThePlace");
					} catch (Exception e) {
						System.out.println("could not remove AtThePlace "+e.getMessage());
					}
				 
				try {
					statement.execute("Drop table Course");
				} catch (Exception e) {
					System.out.println("could not remove Course "+e.getMessage());
				}
				
				try {statement.execute("Drop table Lecturer");
				} catch (Exception e) {
					System.out.println("could not remove Lecturer "+e.getMessage());
				}
				
				try {statement.execute("Drop table Class");
				} catch (Exception e) {
					System.out.println("could not remove Class "+e.getMessage());
				}
			
				//Course
				System.out.println("Creating Course table");
				 statement.execute("CREATE TABLE IF NOT EXISTS Course ("+
						  "CourseID INT NOT NULL AUTO_INCREMENT,"+
						  "Name VARCHAR(45) NOT NULL,"+
						  "PRIMARY KEY (CourseID))");
				 
				 System.out.println("Course table created");
			
				//Class
				 System.out.println("Creating Class table");
				 statement.execute("CREATE TABLE IF NOT EXISTS Class ("+
					  "ClassNumber INT NOT NULL,"+
					  "Building VARCHAR(45) NOT NULL,"+
					  "PRIMARY KEY (ClassNumber))");
					
				 
				 System.out.println("Class table created");
			
				 //Lecturer
				 System.out.println("Creating Lecturer table");
				 statement.execute("CREATE TABLE IF NOT EXISTS Lecturer ("+
						  "LecturerID INT NOT NULL,"+
						  "FullName VARCHAR(45) NOT NULL,"+
						  "PhoneNum VARCHAR(45) NOT NULL,"+
						  "Address VARCHAR(45) NOT NULL,"+
						 "PRIMARY KEY (LecturerID))");
				 
				 System.out.println("Lecturer table created");	 
				
				 //TEACHING
				 System.out.println("Creating Teaching table");
				 statement.execute("CREATE TABLE IF NOT EXISTS Teaching ( "+
					  "LecturerID INT NOT NULL, "+
					  "CourseID INT NOT NULL, "+
					  "Date DATE NOT NULL, "+
					  "PRIMARY KEY (CourseID), "+
					  "foreign key (LecturerID) references Lecturer(LecturerID), "+
					  "foreign key (CourseID) references Course(CourseID) "+
					  "ON DELETE CASCADE "+
					  "ON UPDATE CASCADE "+
					  ")");
				 
				 System.out.println("Teaching table created");
				 
				 createAtThePlace(statement);
				 
				 
			
		insertCourse(statement);
		
		insertClass(statement);
		
		insertLecturer(statement);
		
		insertTeaching(statement);
		
		insertAtThePlace(statement);
			
		
	 }
		 catch(Exception e) { e.printStackTrace(); }
		 finally
		 {
		 if(statement!=null) try{statement.close();}catch(Exception e){}
		 if(connection!=null) try{connection.close();}catch(Exception e){}
		 if(rs!=null) try{rs.close();}catch(Exception e){}
		 }
		
	 }

	protected static void insertClass(Statement statement) throws SQLException {
		statement.execute("insert into Class (ClassNumber, Building)"
		           + " values"+"("+"2101"+","+"'Michael'"+")");
		 statement.execute("insert into Class (ClassNumber, Building)"
		           + " values"+"("+"2102"+","+"'Michael'"+")");
			
			statement.execute("insert into Class (ClassNumber, Building)"
			           + " values"+"("+"2103"+","+"'Frankle'"+")");
			statement.execute("insert into Class (ClassNumber, Building)"
			           + " values"+"("+"2104"+","+"'Frankle'"+")");
	}

	protected static void insertAtThePlace(Statement statement) throws SQLException {
		statement.execute (" insert into AtThePlace (LecturerID, CourseID,ClassNumber,Date,Time)"
		    + " values ("+"655478965"+","+"03567"+","+"2101"+","+"'20161015'"+","+"'08:00:00'"+")");
		statement.execute(" insert into AtThePlace (LecturerID, CourseID,ClassNumber,Date,Time)"
				+ " values"+"("+"655478966"+","+"03566"+","+"2102"+","+"'20161015'"+","+"'08:00:00'"+")");
		statement.execute(" insert into AtThePlace (LecturerID, CourseID,ClassNumber,Date,Time)"
				+ " values"+"("+"655478966"+","+"03568"+","+"2102"+","+"'20161015'"+","+"'10:00:00'"+")");
		statement.execute(" insert into AtThePlace (LecturerID, CourseID,ClassNumber,Date,Time)"
				+ " values"+"("+"655478977"+","+"03569"+","+"2101"+","+"'20161016'"+","+"'16:00:00'"+")");
		statement.execute(" insert into AtThePlace (LecturerID, CourseID,ClassNumber,Date,Time)"
				+ " values"+"("+"655478971"+","+"03570"+","+"2101"+","+"'20161016'"+","+"'18:00:00'"+")");

		statement.execute (" insert into AtThePlace (LecturerID, CourseID,ClassNumber,Date,Time)"
		    + " values ("+"655478914"+","+"03571"+","+"2103"+","+"'20161017'"+","+"'08:00:00'"+")");
		statement.execute(" insert into AtThePlace (LecturerID, CourseID,ClassNumber,Date,Time)"
				+ " values"+"("+"655478375"+","+"03572"+","+"2103"+","+"'20161017'"+","+"'10:00:00'"+")");
		statement.execute(" insert into AtThePlace (LecturerID, CourseID,ClassNumber,Date,Time)"
				+ " values"+"("+"655478375"+","+"03573"+","+"2104"+","+"'20161017'"+","+"'08:00:00'"+")");
		statement.execute(" insert into AtThePlace (LecturerID, CourseID,ClassNumber,Date,Time)"
				+ " values"+"("+"655478914"+","+"03574"+","+"2101"+","+"'20161018'"+","+"'14:00:00'"+")");
		statement.execute(" insert into AtThePlace (LecturerID, CourseID,ClassNumber,Date,Time)"
				+ " values"+"("+"765432987"+","+"03575"+","+"2101"+","+"'20161018'"+","+"'16:00:00'"+")");
	}

	protected static void insertTeaching(Statement statement) {
		try{	 	
			statement.execute (" insert into Teaching (LecturerID, CourseID,  Date)"
			        + " values"+"("+"655478965"+","+"03567"+","+ "'20161015'"+")");
			statement.execute(" insert into Teaching (LecturerID, CourseID,Date)"
			        + " values ("+"655478966"+","+"03566"+","+ "'20161015'"+")");
			statement.execute(" insert into Teaching (LecturerID, CourseID,Date)"
			        + " values ("+"655478966"+","+"03568"+","+ "'20161015'"+")");
			statement.execute(" insert into Teaching (LecturerID, CourseID,Date)"
			        + " values ("+"655478977"+","+"03569"+","+ "'20161016'"+")");
			statement.execute(" insert into Teaching (LecturerID, CourseID,Date)"
			        + " values ("+"655478971"+","+"03570"+","+ "'20161016'"+")");
		
			statement.execute (" insert into Teaching (LecturerID, CourseID,  Date)"
			        + " values"+"("+"655478914"+","+"03571"+","+ "'20161017'"+")");
			statement.execute(" insert into Teaching (LecturerID, CourseID,Date)"
			        + " values ("+"655478375"+","+"03572"+","+ "'20161017'"+")");
			statement.execute(" insert into Teaching (LecturerID, CourseID,Date)"
			        + " values ("+"655478975"+","+"03573"+","+ "'20161017'"+")");
			statement.execute(" insert into Teaching (LecturerID, CourseID,Date)"
			        + " values ("+"655478914"+","+"03574"+","+ "'20161018'"+")");
			statement.execute(" insert into Teaching (LecturerID, CourseID,Date)"
			        + " values ("+"765432987"+","+"03575"+","+ "'20161018'"+")");
			System.out.println("Teaching Inserted");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	protected static void createAtThePlace(Statement statement) throws SQLException {
		try {
			//AtThePlace
			 System.out.println("Creating AtThePlace table");
			 statement.execute("CREATE TABLE IF NOT EXISTS AtThePlace("+
				  "LecturerID INT NOT NULL,"+
				  "CourseID INT NOT NULL,"+
				  "ClassNumber INT NOT NULL,"+
				  "Date DATE NOT NULL,"+
				 "Time TIME NOT NULL,"+
				  "PRIMARY KEY (LecturerID,CourseID,ClassNumber,Date,Time ),"+
				  "foreign key (LecturerID) references Lecturer(LecturerID),"+
				  "foreign key (CourseID) references Course(CourseID),"+
				  "foreign key (ClassNumber) references Class(ClassNumber) "+
				  "ON DELETE CASCADE "+
					  "ON UPDATE CASCADE "
				  +")");
			 
			 System.out.println("AtThePlace table created");
		} catch (Exception e) {
			System.out.println("insertAtThePlace "+e.getMessage());
		}
	}

	protected static void insertCourse(Statement statement) {
		try{
			statement.execute("insert into Course (CourseID, Name)"
				           + " values"+"("+"03567"+","+"'Algebra'"+")");
			statement.execute("insert into Course (CourseID, Name)"
			         + " values"+"("+"03566"+","+"'Art'"+")");
			statement.execute("insert into Course (CourseID, Name)"
			         + " values"+"("+"03568"+","+"'History'"+")");
			statement.execute("insert into Course (CourseID, Name)"
			         + " values"+"("+"03569"+","+"'Computer Science'"+")");
			statement.execute("insert into Course (CourseID, Name)"
			         + " values"+"("+"03570"+","+"'Geography'"+")");
			statement.execute("insert into Course (CourseID, Name)"
				     + " values"+"("+"03571"+","+"'Fashion and Design'"+")");
			statement.execute("insert into Course (CourseID, Name)"
			         + " values"+"("+"03572"+","+"'SQl'"+")");
			statement.execute("insert into Course (CourseID, Name)"
			         + " values"+"("+"03573"+","+"'Algoritms'"+")");
			statement.execute("insert into Course (CourseID, Name)"
			         + " values"+"("+"03574"+","+"'Tekstil'"+")");
			statement.execute("insert into Course (CourseID, Name)"
			         + " values"+"("+"03575"+","+"'Chemistry'"+")");
			System.out.println("Course Inserted");
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	private static void insertLecturer(Statement statement) {
		try {
			statement.execute (" insert into Lecturer (LecturerID, FullName,PhoneNum,Address)"
					+ " values"+ "("+"655478965"+","+"'Erez perez'"+","+"'03-8765432'"+","+"'Hamaapilim 5 Tel Aviv'"+")");
		statement.execute(" insert into Lecturer (LecturerID, FullName,PhoneNum,Address)"
				+ " values "+"("+"655478966"+","+"'Ami Tami'"+","+ "'03-8765562'"+","+"'Herzel 10 Tel Aviv'"+")");
		statement.execute(" insert into Lecturer (LecturerID, FullName,PhoneNum,Address)"
				+ " values "+"("+"655478977"+","+"'Sharon Maron'"+","+ "'03-8765590'"+","+"'Osishkin 7 Tel Aviv'"+")");
		statement.execute(" insert into Lecturer (LecturerID, FullName,PhoneNum,Address)"
				+ " values "+"("+"655478971"+","+"'Dalya Zahav'"+","+ "'03-8766432'"+","+"'Shimshon 8 Tel Aviv'"+")");
		statement.execute (" insert into Lecturer (LecturerID, FullName,PhoneNum,Address)"
				+ " values "+"("+"655478914"+","+"'Yafa Simha'"+","+"'03-8835497'"+","+"'Ben Zvi 5 Tel Aviv'"+")");
		statement.execute(" insert into Lecturer (LecturerID, FullName,PhoneNum,Address)"
				+ " values "+"("+"655478375"+","+"'Ofer Kaz'"+","+ "'03-76462'"+","+"'Eli Cohen 6 Tel Aviv'"+")");
		statement.execute(" insert into Lecturer (LecturerID, FullName,PhoneNum,Address)"
				+ " values "+"("+"765432987"+","+"'Myryam Shtern'"+","+ "'03-45590'"+","+"'Menahem Begin 3 Tel Aviv'"+")");
		
		statement.execute(" insert into Lecturer (LecturerID, FullName,PhoneNum,Address)"
				+ " values "+"("+"655478975"+","+"'Alisa Barsky'"+","+ "'01-2345'"+","+"'Mavoe kalaniot 15 Ashkelon'"+")");
		
		
		
		System.out.println("Lecturer inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}	
		
 }

