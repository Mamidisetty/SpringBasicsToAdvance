package com.example.beanlifecycle;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class StudentDAO {

	private String driver;

	private String url;

	private String userName;

	private String password;

	Connection con;

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		System.out.println("setting driver...");
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		System.out.println("setting url...");
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		System.out.println("setting username....");
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		System.out.println("Setting Password.....");
		this.password = password;
	}
	
//	@PostConstruct
	public void init() throws ClassNotFoundException, SQLException {
		System.out.println("Creating connection to DB");
		createStudentDBConnection();
	}

	
	public void createStudentDBConnection() throws ClassNotFoundException, SQLException {
		System.out.println("Creating connection....");
		
		// load driver
		Class.forName(driver);
		// get a connection
		
		con = DriverManager.getConnection(url, userName, password);

	}

	public void selectAllRows() throws ClassNotFoundException, SQLException {
		System.out.println("Retriving all the data...");

		Statement st = con.createStatement();
		// excute the query

		ResultSet rs = st.executeQuery("Select * FROM ESNew.HostelStudentInfo");

		while (rs.next()) {
			int studentId = rs.getInt(1);
			String studentname = rs.getString(2);
			double hostelFee = rs.getDouble(3);
			String foodType = rs.getString(4);
			System.out.println(studentId + " " + studentname + " " + hostelFee + " " + foodType + " ");
		}

	}

	public void deleteStudentRecord(int StudentId) throws ClassNotFoundException, SQLException {

		// excute the query
		Statement st = con.createStatement();

		st.executeUpdate("delete from ESNew.HostelStudentInfo where Student_id =" + StudentId);
		System.out.println("Record deleted with the id" + StudentId);

	}
	
	

	
	public void closeConnection() throws SQLException {
		
		//clean up job
		con.close();
	}
	
//	@PreDestroy
	public void destroy() throws SQLException {
		System.out.println("inside destroy method");
		closeConnection();
	}

}
