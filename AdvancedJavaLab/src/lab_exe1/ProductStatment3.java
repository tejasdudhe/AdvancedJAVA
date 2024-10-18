
/*
 * Problem Statement 3

		Display details of products in the given range of price (lower limit and upper limit to be accepted from the user).
		Search a product based on product code and display its details.
 */


package lab_exe1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ProductStatment3 {

	public static void main(String[] args) {
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##hr", "hr");
			
			Scanner sc = new Scanner(System.in);
			
			System.out.print("Enter the Lower Limit : ");
			int lower = sc.nextInt();
			
			System.out.print("Enter the Upper Limit : ");
			int upper = sc.nextInt();
			
			String sql ="select * from products where price between ? and ?";;
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, lower);
			ps.setInt(2, upper);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				System.out.println("\n\n Product code : "+rs.getInt(1));
				System.out.println(" Product Name : "+rs.getString(2));
				System.out.println(" Product Price : "+rs.getInt(3));
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
