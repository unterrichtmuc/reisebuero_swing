package de.cmt.reisebuero.swing.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DbHelper {

	public static Connection get() {
		try {
			Class.forName("com.mysql.jdbc.Driver");		

			return DriverManager.getConnection("jdbc:mysql://127.0.0.1/reisebuero", "root", "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Database Con faild");
			
			System.exit(1);
		} 
		
		return null;
	}
	
}
