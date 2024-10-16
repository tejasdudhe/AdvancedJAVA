package pkg_15_10_2024_p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class PrepStatment2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		try
		{  
		
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##hr","hr");  
		
		PreparedStatement stmt = con.prepareStatement("update emp set name=? where eid=?");  
		
		stmt.setString(1,"bbb");		
		stmt.setInt(2,104); 
		
		
		  
		int i=stmt.executeUpdate();  
		System.out.println(i+" records Updated");  
		
		con.close();  
	}catch(Exception e){ System.out.println(e);}  


	}
	

}
