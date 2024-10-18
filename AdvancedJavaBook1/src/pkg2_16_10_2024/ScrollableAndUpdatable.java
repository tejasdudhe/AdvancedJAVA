package pkg2_16_10_2024;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ScrollableAndUpdatable {

	public static void main(String[] args) {

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##hr", "hr");

			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			ResultSet rs = stmt.executeQuery("select eid,name,sal from emp"); // * asterick not allowed

			int ch;
			Scanner sc = new Scanner(System.in);

			do {
				System.out.println("\n ************************************* \n");
				System.out.println("1)Move First");
				System.out.println("2)Move Last");
				System.out.println("3)Move Next");
				System.out.println("4)Move Previous");
				System.out.println("5)Move nth Record(abs)");
				System.out.println("6)Move -nth Record(rel)");
				System.out.println("7)Insert Record");
				System.out.println("8)Update Record");
				System.out.println("9)Delete Record");
				System.out.println("10)Display all record.");
				System.out.println("0) Exit");

				System.out.println("Enter your choice:");
				ch = sc.nextInt();

				System.out.println("\n ************************************* \n");
				switch (ch) {
				case 1:

					rs.first();
					System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getInt(3));

					break;

				case 2:
					rs.last();
					System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getInt(3));
					break;
				case 3:
					rs.next();
					System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getInt(3));
					break;
				case 4:
					rs.previous();
					System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getInt(3));
					break;
				case 5:
					System.out.println("Enter your abs record number:");
					int a = sc.nextInt();
					rs.absolute(a);
					System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getInt(3));
					break;
				case 6:
					System.out.println("Enter your releative record number:");
					int r = sc.nextInt();
					rs.relative(r);
					System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getInt(3));
					break;
				case 7:
					System.out.println(rs.getInt(1));
					rs.last();
					rs.moveToInsertRow();
					rs.updateInt(1, 107);
					rs.updateString(2, "rahul");
					rs.updateInt(3, 15750);
					rs.insertRow();
					break;

				case 8:
					rs.last();
					rs.updateString(2, "SACHIN");
					rs.updateInt(3, 15750);
					rs.updateRow();
					break;
				case 9:
					rs.last();
					rs.deleteRow();
					break;
					
				case 10:
					rs.first();
					while(rs.next())
					System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getInt(3)+"\n");
					break;

				default:

					break;
				}
			} while (ch != 0);

		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}

	}
}
