
/*
 * Problem Statement 4
		Display total number of products, products with lowest price, products with highest price, and average price of all products.
 */

package lab_exe1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProblemStatment4 
{
	public static void main(String[] args) 
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##hr", "hr");
			
			int c=0,sum=0;
			String sql1 = "Select * from Products";
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery(sql1);
			
			while(rs.next())
			{
				c++;
				sum = sum +rs.getInt(3);
			}
			
			String sql2 = "Select * from Products where price =(select max(price) from Products)";
			String sql3 = "Select * from Products where price =(select min(price) from Products)";
			
			Statement st1 = con.createStatement();
			ResultSet rs2 = st1.executeQuery(sql2);
			
			Statement st2 = con.createStatement();
			ResultSet rs3 = st2.executeQuery(sql3);
			
			System.out.println("\n Count : "+c);
			System.out.println(" Avg : "+sum/c);
			
			System.out.println("\n\n Highest price : ");
			while(rs2.next())
			{
				System.out.println("\n\n Product code : "+rs2.getInt(1));
				System.out.println(" Product Name : "+rs2.getString(2));
				System.out.println(" Product Price : "+rs2.getInt(3));
			}
			
			System.out.println("\n\n Lowest price : ");
			while(rs3.next())
			{
				System.out.println("\n\n Product code : "+rs3.getInt(1));
				System.out.println(" Product Name : "+rs3.getString(2));
				System.out.println(" Product Price : "+rs3.getInt(3));
			}
			
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
