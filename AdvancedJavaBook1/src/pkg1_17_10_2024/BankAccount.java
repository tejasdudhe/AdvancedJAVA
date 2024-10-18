package pkg1_17_10_2024;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BankAccount {

	public static void main(String[] args) throws SQLException {
		

		Connection con = null;
		Scanner sc = new Scanner(System.in);		
			
				try {
					
					con = Conncetion();
					con.setAutoCommit(false);
					
					System.out.print("\n Enter the Acc_No to display :");
					int accNo = sc.nextInt();
					display(con,accNo);
					
					transfer(con);
					con.commit();
				} catch (ClassNotFoundException  e) {
					
					e.printStackTrace();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
				catch (SecurityException e) {
					if(con!=null)
					{
						con.rollback();
					}
					System.out.println("Inavlid AccountNumber");
					e.printStackTrace();
				}
				catch (Exception e) {
					if(con!=null)
					{
						con.rollback();
					}
					e.printStackTrace();
				}
				
				
	}
	
	
	public static Connection Conncetion() throws SQLException, ClassNotFoundException
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##hr", "hr");
	}
	
	
	
	public static void display(Connection con,int accNo) throws ClassNotFoundException, SQLException
	{
		
		
		Scanner sc = new Scanner(System.in);
		
		String sql = "select * from Accounts where acc_no = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1,accNo);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next())
		{
			System.out.println("\n\n Product code : "+rs.getInt(1));
			System.out.println(" Product Name : "+rs.getString(2));
			System.out.println(" Product Price : "+rs.getInt(3));
		}
		
		
	}
	
	public static void transfer(Connection con) throws ClassNotFoundException, SQLException
	{
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println(" Enter the Amount you want to Transfer : ");
		int amount = sc.nextInt();
		 
		
		System.out.print("\n Enter the Acc_No From you want to Transfer Amount :");
		int fromAcc = sc.nextInt();
		System.out.println("\n Before Transfer : ");
		display(con,fromAcc);
		String sql1 = "update Accounts set acc_bal = acc_bal - ? where acc_no = ?";
		PreparedStatement st1 = con.prepareStatement(sql1);
		st1.setInt(1, amount);
		st1.setInt(2, fromAcc);
		
		int ch1 = st1.executeUpdate();
		
		if(ch1>0)
		{
			
			System.out.println("\n After Transfer : ");
			display(con,fromAcc);
		}
		else
		{
			throw new SecurityException();
		}
		
		System.out.print(" Enter the Acc_No In you want to Transfer Amount : ");
		int toAcc = sc.nextInt();
		display(con,toAcc);
		
		
		String sql2 = "update Accounts set acc_bal = acc_bal + ? where acc_no = ?";
		PreparedStatement st2 = con.prepareStatement(sql2);
		st2.setInt(1, amount);
		st2.setInt(2, toAcc);
		int ch2 = st2.executeUpdate();
		
		
		
		
		
		if(ch2>0)
		{
			System.out.println("\n After Transfer : ");
			display(con,toAcc);
			
		}else {
			throw new SecurityException();
		}
			
		
	}

}
