package pkg1_16_10_2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BatchUpdateWithPreparedStatment {

	public static void main(String[] args) {
		
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##hr","hr");
			
			PreparedStatement ps = con.prepareStatement("insert into Student values(?,?,?,?)");
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in)); 
			
			while(true)
			{
				System.out.print("Enter the Student Id:");  
				int sid=Integer.parseInt(br.readLine());
				
				System.out.print("Enter the Student Name:");  
				String sname=br.readLine();  
				
				System.out.print("Enter the Student Age:");  
				int sage=Integer.parseInt(br.readLine());
				
				System.out.println("Enter the Student city:");  
				String scity=br.readLine(); 
				
				ps.setInt(1,sid);  
				ps.setString(2,sname);  
				ps.setInt(3,sage); 
				ps.setString(4,scity);
				
				ps.addBatch();  
				System.out.print("do want to add more records?y/n: ");  
				String ans=br.readLine();  
				if(ans.equals("n")){  
				break;  
				}
				
			}
			
			int c[]=ps.executeBatch();  
			System.out.println("Batch of "+c.length+" Records");
			
		} catch (ClassNotFoundException | SQLException | IOException e) {
			
			e.printStackTrace();
		}
		

	}

}
