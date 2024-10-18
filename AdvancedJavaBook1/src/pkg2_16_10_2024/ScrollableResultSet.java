package pkg2_16_10_2024;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ScrollableResultSet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##hr", "hr");

			String sql = "select * from emp";

			Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stm.executeQuery(sql);

			System.out.println("Result Set (fw):");
			while (rs.next()) {
				System.out.println(" " + rs.getInt("eid") + " " + rs.getString(2) + " " + rs.getInt(3));
			}

			System.out.println("Result Set (Rev):");
			while (rs.previous()) {
				System.out.println(" " + rs.getInt("eid") + " " + rs.getString(2) + " " + rs.getInt(3));
			}
			
			
			  rs.first();
			  System.out.println(" "+rs.getInt("eid")+" "+rs.getString(2)+" "+rs.getInt(3));
			  rs.last();
			  System.out.println(" "+rs.getInt("eid")+" "+rs.getString(2)+" "+rs.getInt(3)); 
			  rs.relative(-2);
			  System.out.println(" "+rs.getInt("eid")+" "+rs.getString(2)+" "+rs.getInt(3));
			  rs.absolute(3);
			  System.out.println(" "+rs.getInt("eid")+" "+rs.getString(2)+" "+rs.getInt(3));
			  
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
