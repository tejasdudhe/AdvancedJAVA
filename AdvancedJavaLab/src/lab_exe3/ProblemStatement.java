/*
 * 
 * 		Lab Exercise 3

				Note: Use of CallableStatement class.
				
				Pre-condition: Table named ElectronicProducts should be present in the database with some records.
				The fields of the table - pcode, pname, brand, and price
				
				Problem Statement 1
				
				Write a stored procedure named applyDiscount with parameters – pcode, discount, updatedPrice. (Declare formal parameters as IN, OUT or INOUT as per the requirement). The method should update the price by calculating the "discount".
				
				Write a stored procedure named GetInformation() with parameter – pcode, pname, brand, price. The method should get the information based on pcode passed. The related information should be returned as OUT parameters.
				
				Note: Make the application database vendor independent using database properties file.
				
				Problem Statement 2
				
				Call the above created stored procedures from Java program.
 * 
 */


package lab_exe3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;
import java.util.Scanner;

public class ProblemStatement {

	public static void main(String[] args) 
	{
		Connection con = null;
		
		
		try {
			
			FileInputStream input = new FileInputStream("D:\\SEED_SPIC\\AdvancedJAVA\\AdvancedJavaLab\\src\\lab_exe3\\db.txt");
				Properties prop = new Properties();
				prop.load(input);
				
				String url = prop.getProperty("db.url");
				String user = prop.getProperty("db.username");
				String password = prop.getProperty("db.password");
				String driver = prop.getProperty("db.driver");
				
				Class.forName(driver);  
				con = DriverManager.getConnection(url, user, password);
				
				Scanner sc = new Scanner(System.in);
				
				System.out.println("Enter the Product code  to purchase : ");
				int code = sc.nextInt();
				
				System.out.println("Enter the discount  to on product to purchase : ");
				int dis = sc.nextInt();
				
				CallableStatement cs1 = con.prepareCall("{call applyDiscount(?,?,?)}");
				
				cs1.setInt(1,code);
				cs1.setInt(2,dis);
				cs1.registerOutParameter(3, Types.INTEGER);
				cs1.execute();
				
				
				System.out.print("\nPrice After Discount : "+cs1.getInt(3));
				
				
				CallableStatement cs2 = con.prepareCall("{call getInformation(?,?,?,?)}");
				
				cs2.setInt(1,code);
				
				cs2.registerOutParameter(1, Types.INTEGER);
				cs2.registerOutParameter(2, Types.VARCHAR);
				cs2.registerOutParameter(3, Types.VARCHAR);
				cs2.registerOutParameter(4, Types.INTEGER);
				
				cs2.execute();
				
				System.out.print("\nProduct Code : "+code);
				System.out.print("\nProduct Name : "+cs2.getString(2));
				System.out.print("\nProduct Brand : "+cs2.getString(3));
				System.out.print("\nProduct Price : "+cs2.getInt(4));
				
				
				
				
				
				
				
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			
		
		
	}

}




/*
 * 
 * 		  create or replace procedure applyDiscount(code IN number,discount IN number,updatePrice OUT Number)
				as
				 originalPrice NUMBER;
				begin
				    SELECT price INTO originalPrice 
				    FROM ElectronicProducts 
				    WHERE pcode = code;
				updatePrice := originalPrice - discount;
				end;
/
 * 
 * */




  /*		 
    create or replace procedure getInformation(
  						code IN number,
 						name OUT varchar2,
 						br OUT varchar2,
 						pri OUT number)
					as
						begin
						    SELECT pname, brand, price
						    INTO name, br, pri
						    FROM ElectronicProducts
						    WHERE pcode = code;
						    
						Exception
							WHEN NO_DATA_FOUND THEN
					        name := 'Product not found';
					        br := NULL;
					        pri := NULL;
						end;
						/
*/
