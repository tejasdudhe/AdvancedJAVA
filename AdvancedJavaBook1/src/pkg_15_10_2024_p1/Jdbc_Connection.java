package pkg_15_10_2024_p1;

import java.sql.*;


public class Jdbc_Connection {

	public static void main(String[] args) {
		
		try {
			
			// Step 1 : Load the Driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// Step 2 : Create the Connection
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##hr","hr");
			
			//Step 3 : Handle the exception
			
			//Step 4 : Create SQl Query
			
			String sql = "Select * from COPY_EMP where EMPLOYEE_ID = 106"; 
			
//			Step 5 : Associate SQl Query with  Connection 
			
			Statement st = con.createStatement();
			
			//Step 6 : Get the Result
			
			ResultSet rs = st.executeQuery(sql);
			
			// step 7 : 
			while(rs.next())
			{
				System.out.println("  "+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(7));
			}
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
