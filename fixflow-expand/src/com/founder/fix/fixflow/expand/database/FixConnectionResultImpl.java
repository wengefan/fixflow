package com.founder.fix.fixflow.expand.database;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.founder.fix.dbcore.DBGetResult;
import com.founder.fix.fixflow.core.exception.FixFlowDbException;
import com.founder.fix.fixflow.core.impl.db.FixConnectionResult;

public class FixConnectionResultImpl implements FixConnectionResult {
	
	protected DBGetResult dbGetResult;
	protected String dataBaseId;
	protected Connection connection;
	
	public FixConnectionResultImpl(String dataBaseId){
		this.dataBaseId=dataBaseId;
		this.dbGetResult=new DBGetResult();
		
	}
	
	public FixConnectionResultImpl(String dataBaseId,Connection connection){
		this.dataBaseId=dataBaseId;
		//this.dbGetResult=new DBGetResult();
		this.connection=connection;
	}

	public void openConnection() {
		try {
			if(this.dbGetResult!=null){
				dbGetResult.openConn(dataBaseId);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new FixFlowDbException(e.getMessage(), e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FixFlowDbException(e.getMessage(), e);
		} catch (NamingException e) {
			e.printStackTrace();
			throw new FixFlowDbException(e.getMessage(), e);
		}
	}


	public Connection getConnection() {
		
		
		try {
			if(this.connection==null){
				this.connection=dbGetResult.getConnection();
				if(this.connection==null){
					throw new FixFlowDbException("ID为"+dataBaseId+"的数据库连接创建失败!");
				}

				return  this.connection;
			}else{
				return  this.connection;
				
			}
			
			
			
		} catch (Exception e) {
			
			throw new FixFlowDbException(e.getMessage(), e);
		}
	}

	public void colseConnection() {
		try {
			if(this.dbGetResult!=null){
				dbGetResult.closeConn();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FixFlowDbException(e.getMessage(), e);
		}
	}

	public void commitConnection() {
		try {
			this.connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FixFlowDbException(e.getMessage(), e);
		}
	}

	public void rollBackConnection() {
		try {
			this.connection.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FixFlowDbException(e.getMessage(), e);
		}
	}

	public String getDataBaseId() {
		return this.dataBaseId;
	}


}
