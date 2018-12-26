package com.asset.dbConnect;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnect {
	
	public static Connection getSqlConnection() throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
	}

}
