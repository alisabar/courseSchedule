package com.alisa.mn.Guis;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPanel;

public class SubqueryClass extends Property{

	private int _classNumberInclude, _classNumberExclude;
	
	private boolean canLoadData=false;
	
	public SubqueryClass(int classNumberInclude, int classNumberExclude)
	{
		super();
		this._classNumberInclude=classNumberInclude;
		this._classNumberExclude=classNumberExclude;
		canLoadData=true;
		loadData();
	}
	
	@Override
	protected void loadData() {
		if(canLoadData)
		{
			super.loadData();
		}
	}
	
	@Override
	String[] getColumNames() {
		return new String []{
			"FullName"
		};
	}
	
	@Override
	protected void createButtons(JPanel topPanel) {
		
	}
	
	@Override
	protected String getQueryString() {
		
		String sql = " select "+
				" distinct L.FullName "+
				" from "+
				" attheplace A"+
				" inner join lecturer L on L.LecturerID=A.LecturerID"+
				" where"+
				" ClassNumber = "+this._classNumberInclude+
				" and not L.LecturerID in ("+
				"	select "+
				"	LecturerID"+
				"	from "+
				"	attheplace"+
				"	where"+
				"	 ClassNumber = "+this._classNumberExclude+
				" )";
		
		return sql ;
		
	}

	@Override
	protected Object[] readRow(ResultSet res) throws SQLException {
		
		return new Object[]{
				res.getString("FullName")				
		};
	}

	@Override
	protected void doDelete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void openNew() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void openUpdate(int rowId) {
		// TODO Auto-generated method stub
		
	}

}
