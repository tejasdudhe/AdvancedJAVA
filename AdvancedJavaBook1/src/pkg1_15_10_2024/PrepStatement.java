package pkg1_15_10_2024;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PrepStatement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc= new Scanner(System.in);
		
		try {
			
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##hr","hr");
		
		String sql="select * from emp where name=?";
		
		System.out.print("Enter the Employee name:");
		String ename=sc.next();
		

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1,ename);
		
		ResultSet rs= ps.executeQuery();
		
		if(rs.next())
		{
			  System.out.println(" "+rs.getInt("eid")+" "+rs.getString(2)+" "+rs.getInt(3));
		}
		
		}
		catch(SQLException e) 
		 {
		   e.printStackTrace();
		 }
		 catch(ClassNotFoundException e) 
		 {
		   e.printStackTrace();
		 }

	}

}
