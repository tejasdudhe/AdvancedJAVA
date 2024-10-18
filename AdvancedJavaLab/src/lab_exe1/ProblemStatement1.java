/**
 *      Problem Statement 1
        Display information of all products in tabular form.
 */

package lab_exe1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProblemStatement1 
{
	public static void main(String[] args) 
	{
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##hr","hr");
			
			String sql = "Select * from Products";
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery(sql);
			
			System.out.println(" "+"Product_code "+" Product_Name "+"\tPrice\n");
			while(rs.next())
			{
				System.out.println(rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getInt(3)+" ");
			}
			
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		
	}
}
