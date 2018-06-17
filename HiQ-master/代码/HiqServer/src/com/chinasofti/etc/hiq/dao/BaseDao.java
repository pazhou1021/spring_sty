package com.chinasofti.etc.hiq.dao;

public interface BaseDao {
	public void getConn();
	public void closeAll();
	public void  doQuery(String sql,Object ... params);
	public void doOperate(String sql,Object ... params);
	boolean existTable(String tableName);
	public void doInit();
}
