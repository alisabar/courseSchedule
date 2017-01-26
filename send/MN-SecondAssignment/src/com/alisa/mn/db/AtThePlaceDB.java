package com.alisa.mn.db;

import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.TableModel;

import org.apache.derby.client.am.SqlException;

public class AtThePlaceDB extends classDBObjectBase
    {
    	public int lectureId; 
    	public int courseId; 
    	public int classNumber; 
    	public String date; 
    	public String time;
				
		public AtThePlaceDB()
		{
			
		}
		
		public AtThePlaceDB(TableModel model,int rowNum)
		{
			lectureId=Integer.parseInt(model.getValueAt(rowNum, 0).toString());
			courseId=Integer.parseInt(model.getValueAt(rowNum, 1).toString());
			classNumber=Integer.parseInt(model.getValueAt(rowNum, 2).toString());
			date=model.getValueAt(rowNum, 3).toString();
			time=model.getValueAt(rowNum, 4).toString();
		}
		
		@Override
		protected void update(Statement statement) {
			
		}

		@Override
		protected void delete(Statement statement) throws SQLException {
			String s="delete from AtThePlace "+
			           
					"where "+"LecturerID="+lectureId
						+	" and "+"CourseID="+courseId
								+	" and "+"ClassNumber="+classNumber
								+	" and "+"Date="+"'"+date+"'"
			   						+	" and "+"Time="+"'"+time+"'";
			statement.executeUpdate(s);	
		}

		@Override
		protected void insert(Statement statement) throws SQLException {
			
			String sql=" insert into AtThePlace (LecturerID, CourseID,ClassNumber,Date,Time)"
			        + " values ("+
							lectureId
							+","
							+courseId
							+","
							+classNumber
							+","
							+"'"+date+"'"
							+","
							+"'"+time+"'"+")";
			statement.executeUpdate(sql);

		}
    }
   