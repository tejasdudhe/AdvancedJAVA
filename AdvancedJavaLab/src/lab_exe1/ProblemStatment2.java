/*
 * 	Problem Statement 2

	Insert 2 records in the ‘Product’ table.
	Delete a product based on product code.
 * */

package lab_exe1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ProblemStatment2 {
	public static void main(String[] args) {
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##hr", "hr");
			
			

//			String sql = "Insert into Products values (106,'Joystick',12000)";
			
			String sql = "delete from Products where product_code = 106";
			Statement st = con.createStatement();

			int n = st.executeUpdate(sql);
			
			if(n==1)
			{
				System.out.println("Record Inserted");
			}


		}catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
	}

}
