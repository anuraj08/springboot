package com.tavant.springboot.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
@Component
@PropertySource("classpath:application.properties")

public class DBUtils {
//	@Autowired
//	private FileUtils fileUtils ;
	
//	@Autowired
//	Environment environment;
	
//	public DataSource getDataSource() {
//		// We worked with an application.properties file ==>
//		// those details we will use to create the datasource
//		// which is used to provide db details to your jdbc
//		
//		//this object will hold all your db related info
//		//which is used to establish the connection with mysql db
//		//MysqlDataSource dataSource = new MysqlDataSource();
//		BasicDataSource dataSource;
//		try {
//			dataSource = new BasicDataSource();
//			
//			//Properties properties = fileUtils.readProperties();
//			//dataSource.setServerName(properties.getProperty("db.serverName"));
//			//dataSource.setPortNumber(Integer.parseInt(properties.getProperty("db.port")));
//			//dataSource.setDatabaseName(properties.getProperty("db.name"));
//			dataSource.setUrl(environment.getProperty("db.url"));
//			dataSource.setUsername(environment.getProperty("db.username"));
//			dataSource.setPassword(environment.getProperty("db.password"));
//			
//			return dataSource;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
////		// To remove all exception thrown during closing an connection
////		// Here we are providing that we dont want any ssl certificate so we put to disabled
////		try {
////			dataSource.setSslMode("DISABLED");
////		} catch (SQLException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		return null;
//		
//	}
	
	public DataSource getDataSource() {
		return DataSourceBuilder.create().build();
		//return DataSourceBuilder.create().driverClassName("");
	}
	
	public Connection getConnection() {
		// to get the db connection for performing the operations
		
		//To get the connection first we need to load the driver
		//then we use DriverManager to get the connection object
		Connection connection = null;
		
		try {
			connection = this.getDataSource().getConnection();
			return connection;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void closeConnection(Connection connection) {
		// used to close the jdbc connection
		
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
