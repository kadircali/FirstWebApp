package com.example.interf;

import java.sql.Connection;
import java.sql.ResultSet;

public interface DBConnection {

	Connection connectToDb();
	
	boolean closeconnection();
	
	boolean addToDb(String name,String surname,int age);
	
	ResultSet listUser();
	
	
	ResultSet listUserById(int id);
	
	boolean update(String name,String surname,int age,int id);
}
