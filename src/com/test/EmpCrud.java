package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class EmpCrud {
		static Connection con;
		static Scanner sc = new Scanner(System.in);
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root" ,"Ganesha");
			int choice;
			do {
				System.out.println("\n----- Employee Menu -----");
				System.out.println("1.Insert");
				System.out.println("2.Display");
				System.out.println("3.Update");
				System.out.println("4.Delete");
				System.out.println("5.Exit");
				System.out.println("Enter a choice ");
				
				choice = sc.nextInt();
				
				switch (choice) {
				case 1:
					insertEmployee(sc);
					break;
					
				case 2:
					displayEmployee(sc);
					break;
					
				case 3:
					updateEmployee(sc);
					break;
					
				case 4:
					deleteEmployee(sc);
					break;
					
				case 5:
					System.out.println("Exiting...");
					break;
					
				default:
					System.out.println("Invalid choice");
					
				}
			}while (choice!=5);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}
		
	}




	private static void insertEmployee(Scanner sc2) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("Enter Employee Id");
		int eid =  sc.nextInt();
		sc.nextLine();
		
		System.out.println("Enter Name");
		String name = sc.nextLine();
		
		System.out.println("Enter Department");
		String dept = sc.nextLine();
		
		System.out.println("Enter Salary");
		double sal = sc.nextDouble();
		
		String sql ="INSERT into employee VALUES(?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, eid);
		ps.setString(2, name);
		ps.setString(3, dept);
		ps.setDouble(4, sal);
		
		ps.executeUpdate();
		System.out.println("Record Updated Successfully");
		
	}
	private static void displayEmployee(Scanner sc2) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM employee";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		System.out.println("\nEID\tNAME\tDEPT\tSALARY");
		while(rs.next()) {
			System.out.println(rs.getInt(1)+"\t"+
								rs.getString(2)+"\t"+
								rs.getString(3)+"\t"+
								rs.getDouble(4));
			
			
		}
	}

	
	private static void updateEmployee(Scanner sc2) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("Enter Employee ID to Update: ");
		int eid = sc.nextInt();
		sc.nextLine();
		
		System.out.println("Enter New Department: ");
		String dept = sc.nextLine();
		
		System.out.println("Enter new Salary: ");
		double sal = sc.nextDouble();
		
		String sql = "UPDATE employee SET dept=?,sal=? WHERE eid=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, dept);
		ps.setDouble(2,sal);
		ps.setInt(3, eid);
		
		ps.executeUpdate();
		System.out.println("Recorded Update Successfully ");
	}
	
	private static void deleteEmployee(Scanner sc2) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("Enter Employee ID to Delete");
		int eid = sc.nextInt();
		String sql = "DELETE FROM employee WHERE eid=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, eid);
		
		ps.executeUpdate();
		
		System.out.println("Record Deleted Successfully");
	}


}
