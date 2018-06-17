package com.chinasofti.etc.hiq.biz.bizimpl;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.chinasofti.etc.hiq.biz.InitDataBaseBiz;
import com.chinasofti.etc.hiq.dao.BaseDao;


public class InitDataBaseBizImpl implements InitDataBaseBiz {

	
	private static BaseDao baseDao = null; 
	static{
		File file = new File("f:\\myconfig.properties");
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String DaoString = properties.getProperty("tBaseDao");
		try {
			baseDao = (BaseDao)Class.forName(DaoString).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void InitDataBase() {
		// TODO Auto-generated method stub
		baseDao.doInit();
	}

}
