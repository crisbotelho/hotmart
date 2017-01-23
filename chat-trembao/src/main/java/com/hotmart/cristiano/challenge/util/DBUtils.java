package com.hotmart.cristiano.challenge.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
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
			if(!existTable("USER", connection, statement)){
			statement.execute("DROP TABLE IF EXISTS USER");
				System.out.println("Construindo base");
			statement.executeUpdate(
					"CREATE TABLE USER(" +
							"ID INTEGER Primary key, " +
							"LOGIN varchar(30) not null, " +
							"LASTLOGOUT datetime not null, " +
							"PASSWORD varchar(30) not null," +
							"STATUS INTEGER not null DEFAULT 0)" 
					);
			
			}
			
			if(!existTable("USER_CONTACT", connection, statement)){
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
			
		}
			
			if(!existTable("HISTORY", connection, statement)){
				statement.execute("DROP TABLE IF EXISTS HISTORY");
				statement.executeUpdate(
						"CREATE TABLE HISTORY(" +
								"ID INTEGER Primary key, " +
								"SENDER varchar(30) not null, " +
								"RECEIVER varchar(30) not null, " +
								"MESSAGE varchar(500) not null, " +
								"DATEHOUR datetime not null, " +
								"USER_CONTACT_ID INTEGER not null, " +
								"CONSTRAINT FK_HISTORY_USER_CONTACT FOREIGN KEY (USER_CONTACT_ID) " +
								"REFERENCES USER_CONTACT (ID))" 
						);
				
			}
			
			statement.close();
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
	}
	
	public boolean existTable(String tableName, Connection con, Statement st) throws ClassNotFoundException, SQLException {
		DatabaseMetaData dbm = con.getMetaData();
		ResultSet tables = dbm.getTables(null, null, tableName, null);
        if(tables.next()){
        	return true;
        } else{
        	return false;
        }
    }
}
