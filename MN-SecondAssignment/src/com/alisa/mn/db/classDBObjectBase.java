package com.alisa.mn.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class classDBObjectBase
    {
    	public static String driver = "com.mysql.jdbc.Driver";
    	public static String protocol = "jdbc:mysql://localhost:3306/schedule?user=root&password=123456";

    	protected Statement createStatement() throws ClassNotFoundException, SQLException {
			Connection connection = null;
				 connection = null;
				 	Class.forName(driver);
					 connection = DriverManager.getConnection(protocol);
					 return connection.createStatement();
    	}
    	
    	public void delete() throws ClassNotFoundException, SQLException
    	{
    		Statement statement=null;
    		try{
    		statement = createStatement();
    		delete(statement);
    		}finally{
    			if(statement!=null){
    				statement.close();
    			}
    		}
    	}

    	public void update() throws ClassNotFoundException, SQLException
    	{
    		Statement statement=null;
    		try{
    		statement = createStatement();
    		update(statement);
    		}finally{
    			if(statement!=null){
    				statement.close();
    			}
    		}
    	}

    	public void insert() throws ClassNotFoundException, SQLException
    	{
    		Statement statement=null;
    		try{
    		statement = createStatement();
    		insert(statement);
    		}finally{
    			if(statement!=null){
    				statement.close();
    			}
    		}
    	}

    	protected abstract void update(Statement statement) throws SQLException;
		protected abstract void delete(Statement statement) throws SQLException;
		protected abstract void insert(Statement statement) throws SQLException;
    }
    