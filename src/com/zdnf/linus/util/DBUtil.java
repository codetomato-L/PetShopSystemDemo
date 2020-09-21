package com.zdnf.linus.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class DBUtil<T> {

	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	static final String URL = "jdbc:mysql://localhost:3306/petshop?characterEncoding=utf-8";
	static final String USER = "root";
	static final String PASSWORD = "123";
	
	/**
	 * 加载驱动
	 */
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取链接
	 * @return Connection
	 */
	public Connection getConnection(){
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}	
	
	/**
	 * 关闭数据库
	 * @param connection
	 * @param preparedStatement
	 * @param resultSet
	 */
	public void getClose(Connection connection,PreparedStatement preparedStatement,ResultSet resultSet){
		try {
			if(resultSet != null)
				resultSet.close();
			if(preparedStatement != null)
				preparedStatement.close();
			if(connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 数据更新模板
	 */
	public boolean update(String sql,Object...objects){		
		try {
			preparedStatement = getConnection().prepareStatement(sql);
			
			for (int i = 0; i < objects.length; i++) {
				preparedStatement.setObject(i+1, objects[i]);				
			}
			int num = preparedStatement.executeUpdate();
			if(num > 0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	/**
	 * 数据查询模板
	 */
	public List<T> query(String sql,Object...objects){
		List<T> list = new ArrayList<T>();		
		
		try {
			preparedStatement = getConnection().prepareStatement(sql);
			for (int i = 0; i < objects.length; i++) {
				preparedStatement.setObject(i+1, objects[i]);
			}
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				list.add(getEntity(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 将结果集的属性返回成对象(抽象方法)
	 * @param resultSet
	 * @return T
	 */
	public abstract T getEntity(ResultSet resultSet) throws SQLException;

}
