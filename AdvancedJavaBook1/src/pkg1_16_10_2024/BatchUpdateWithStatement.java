package pkg1_16_10_2024;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchUpdateWithStatement {

	public static void main(String[] args) {
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##hr","hr");
			
			Statement stm = con.createStatement();
			
			con.setAutoCommit(false);
			
			stm.addBatch("Insert into Student VALUES(102,'Pratik',25,'Pune')");
			stm.addBatch("Insert into Student VALUES(103,'Ritesh',24,'Mumbai')");
			stm.addBatch("Insert into Student VALUES(104,'Aditya',25,'Pune')");
			
			int[] c = stm.executeBatch();
			System.out.println("Batch of "+c.length+" Records");
			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}
