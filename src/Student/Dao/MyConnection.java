package Student.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
	static Connection con=null;
	public static Connection getConnection() {
		try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb","root","root");
				System.out.println("Connecting.......");
			} catch (SQLException e) {
				System.out.println("Driver class Not Foound");				
			} catch (ClassNotFoundException e) {
				System.out.println("Database Not Found!!");
			}		
		return con;
	}
/**	static Connection con=null;
	public static Connection getConnection(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb","root","root");
			System.out.println("Connecting...");
		}catch(ClassNotFoundException e){
			System.out.println("Driver class not found.");
		}catch(SQLException e) {
			System.out.println("Database not found.");
		}
			return con;
	}**/
}
