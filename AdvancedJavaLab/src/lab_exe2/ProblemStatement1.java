/*
Lab Exercise 2
Note: Use of PreparedStatement class.

Pre-condition: Table named ElectronicProducts should be present in the database with some records. The fields of the table - pcode, pname, brand, and price

Problem Statement 1
Create a class and write a method - static Connection getConnection(); This method will return Connection object.

Write a menu driven program to perform the following operations:

Search all products of a particular brand. The brand name should be accepted from the user.
Insert a new record in the Product table using PreparedStatement.
Delete a record from the table based on pcode. pcode should be accepted from the user.
Update a record in the table based on pcode. pcode should be accepted from the user.
Display all the records.
Note: Write 5 different methods to perform the above-mentioned operations.

*/

package lab_exe2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ProblemStatement1 {

	public static void main(String[] args) {
		try {
			
			//searchProductByBrand();
			//insertProduct();
			// deleteProduct();
			//UpdateProduct();
			displayAll();
			
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##hr", "hr");
	}
	
	public static void checkId(int id) throws ClassNotFoundException, SQLException
	{
		Connection con = getConnection();
		Statement st = con.createStatement();
		
		ResultSet rs = st.executeQuery("Select * from ElectronicProducts");
		
		int flag = 0;
		
		while(rs.next())
		{
			if(id == rs.getInt(1))
			{
				flag = 1;
				break;
			}
		}
		
		if(flag==0)
		{
			throw new SecurityException();
		}
		
	}

	public static void searchProductByBrand() throws ClassNotFoundException, SQLException {
		
		Connection con = getConnection();

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter the Brand Name : ");
		String brand = sc.next();
		brand = brand.substring(0, 1).toUpperCase() + brand.substring(1).toLowerCase();

		PreparedStatement ps = con.prepareStatement("select * from ElectronicProducts where brand = ?");
		ps.setString(1, brand);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			System.out.println("\n\n pcode : " + rs.getInt(1));
			System.out.println(" pname : " + rs.getString(2));
			System.out.println(" pBrand : " + rs.getString(3));
			System.out.println(" pPrice: " + rs.getInt(4));
		}
		
	}
	
	
	public static void insertProduct() throws ClassNotFoundException, SQLException {
		
		Connection con = getConnection();

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter the Product code : ");
		int pcode = sc.nextInt();
		try 
		{
			checkId(pcode);
			
		} catch (Exception e) {
			System.out.println("Invalid Product Code");
			return;
		}
		
		
		sc.nextLine();
		System.out.print("Enter the Product Name : ");
		String pname = sc.nextLine();
		
		System.out.print("Enter the Product Brand : ");
		String brand = sc.nextLine();
		
		System.out.print("Enter the Product Price : ");
		int price = sc.nextInt();
		
		brand = brand.substring(0, 1).toUpperCase() + brand.substring(1).toLowerCase();
		

		PreparedStatement ps = con.prepareStatement("insert into ElectronicProducts values(?,?,?,?)");
		ps.setInt(1, pcode);
		ps.setString(2, pname);
		ps.setString(3, brand);
		ps.setInt(4, price);
		int rs = ps.executeUpdate();
		
		if(rs>0)
		{
			System.out.println("Record is Inserted Successfully..........");
		}
		else
		{
			System.out.println("Record is insertion Failed....");
		}

	}

	
	
	public static void deleteProduct() throws ClassNotFoundException, SQLException {
		
		Connection con = getConnection();

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter the Product code to Delete : ");
		int pcode = sc.nextInt();
		try 
		{
			checkId(pcode);
			
		} catch (Exception e) {
			System.out.println("Invalid Product Code");
			return;
		}
		
		

		PreparedStatement ps = con.prepareStatement("delete ElectronicProducts where pcode = ?");
		ps.setInt(1, pcode);
		int rs = ps.executeUpdate();
		
		if(rs>0)
		{
			System.out.println("Record is Deleted Successfully..........");
		}
		else
		{
			System.out.println("Record is Deletation Failed....");
		}

	}

	
	public static void UpdateProduct() throws ClassNotFoundException, SQLException {
		
		Connection con = getConnection();

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter the Product code to Update data : ");
		int pcode = sc.nextInt();
		try 
		{
			checkId(pcode);
			
		} catch (Exception e) {
			System.out.println("Invalid Product Code");
			return;
		}
		
		
		sc.nextLine();
		System.out.print("Enter the Product Name : ");
		String pname = sc.nextLine();
		
		System.out.print("Enter the Product Brand : ");
		String brand = sc.nextLine();
		
		System.out.print("Enter the Product Price : ");
		int price = sc.nextInt();
		
		brand = brand.substring(0, 1).toUpperCase() + brand.substring(1).toLowerCase();
		

		PreparedStatement ps = con.prepareStatement("update ElectronicProducts set pname =?,brand=?,price=? where pcode=?");
		ps.setInt(4, pcode);
		ps.setString(1, pname);
		ps.setString(2, brand);
		ps.setInt(3, price);
		
		int rs = ps.executeUpdate();
		
		if(rs>0)
		{
			System.out.println("Record is Update Successfully..........");
		}
		else
		{
			System.out.println("Record is Updatation Failed....");
		}

	}
	
	
	public static void displayAll() throws ClassNotFoundException, SQLException
	{
		Connection con = getConnection();
		Statement st = con.createStatement();
		
		ResultSet rs = st.executeQuery("Select * from ElectronicProducts");
		
		
		
		while(rs.next())
		{
			System.out.println("\n\n pcode : " + rs.getInt(1));
			System.out.println(" pname : " + rs.getString(2));
			System.out.println(" pBrand : " + rs.getString(3));
			System.out.println(" pPrice: " + rs.getInt(4));
		}
		
		
		
	}


}
