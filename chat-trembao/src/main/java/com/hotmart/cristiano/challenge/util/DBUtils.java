package com.hotmart.cristiano.challenge.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DBUtils {

	@Autowired
	private DataSource dataSource;

	@SuppressWarnings("restriction")
	@PostConstruct
	public void initialize(){
		try {
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			statement.execute("DROP TABLE IF EXISTS USER");
			statement.executeUpdate(
					"CREATE TABLE USER(" +
							"ID INTEGER Primary key, " +
							"LOGIN varchar(30) not null, " +
							"PASSWORD varchar(30) not null)" 
					);
			
			
			statement.execute("DROP TABLE IF EXISTS USER_CONTACT");
			statement.executeUpdate(
					"CREATE TABLE USER_CONTACT(" +
							"ID INTEGER Primary key, " +
							"USER_ID INTEGER not null, " +
							"CONTACT_ID INTEGER not null, " +
							"CONSTRAINT FK_USER_CONTACT_USER FOREIGN KEY (USER_ID) " +
							"REFERENCES USER (ID), "+ 
							"CONSTRAINT FK_CONTACT_CONTACT_USER FOREIGN KEY (CONTACT_ID) " +
							"REFERENCES USER (ID))" 
					);
			
			statement.close();
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
