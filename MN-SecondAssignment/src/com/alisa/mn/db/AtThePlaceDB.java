package com.alisa.mn.db;

import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.TableModel;

public class AtThePlaceDB extends classDBObjectBase
    {
    	String lectureId; 
    	String courseId; 
		String classNumber; 
		String date; 
		String time;
		
		public AtThePlaceDB(TableModel model,int rowNum)
		{
			lectureId=model.getValueAt(rowNum, 0).toString();
			courseId=model.getValueAt(rowNum, 1).toString();
			classNumber=model.getValueAt(rowNum, 2).toString();
			date=model.getValueAt(rowNum, 3).toString();
			time=model.getValueAt(rowNum, 4).toString();
		}
		
		@Override
		protected void update(Statement statement) {
			// TODO Auto-generated method stub
			
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
		protected void insert(Statement statement) {
			// TODO Auto-generated method stub
			
		}
    }
   