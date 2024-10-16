package pkg_15_10_2024_p1;


import java.sql.*;

public class CalliableSt1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##hr","hr");
			
			 CallableStatement stmt=con.prepareCall("{call addnum(?,?,?)}");
			 stmt.setInt(1,10);
			 stmt.setInt(2,5);
			 stmt.registerOutParameter(3,Types.INTEGER);
			 stmt.execute();
			 System.out.println("Addition of Numbers:"+stmt.getInt(3));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
